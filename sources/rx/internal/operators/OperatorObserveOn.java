package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.ImmediateScheduler;
import rx.schedulers.TrampolineScheduler;

public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final Scheduler scheduler;

    public OperatorObserveOn(Scheduler scheduler2) {
        this.scheduler = scheduler2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        if ((this.scheduler instanceof ImmediateScheduler) || (this.scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(this.scheduler, subscriber);
        observeOnSubscriber.init();
        return observeOnSubscriber;
    }

    private static final class ObserveOnSubscriber<T> extends Subscriber<T> {
        final Action0 action = new Action0() {
            public void call() {
                ObserveOnSubscriber.this.pollQueue();
            }
        };
        final Subscriber<? super T> child;
        final AtomicLong counter = new AtomicLong();
        volatile Throwable error;
        volatile boolean finished = false;
        final NotificationLite<T> on = NotificationLite.instance();
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final ScheduledUnsubscribe scheduledUnsubscribe;

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> subscriber) {
            this.child = subscriber;
            this.recursiveScheduler = scheduler.createWorker();
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.queue = new SynchronizedQueue(RxRingBuffer.SIZE);
            }
            this.scheduledUnsubscribe = new ScheduledUnsubscribe(this.recursiveScheduler);
        }

        /* access modifiers changed from: package-private */
        public void init() {
            this.child.add(this.scheduledUnsubscribe);
            this.child.setProducer(new Producer() {
                public void request(long j) {
                    BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, j);
                    ObserveOnSubscriber.this.schedule();
                }
            });
            this.child.add(this.recursiveScheduler);
            this.child.add(this);
        }

        public void onStart() {
            request((long) RxRingBuffer.SIZE);
        }

        public void onNext(T t) {
            if (!isUnsubscribed()) {
                if (!this.queue.offer(this.on.next(t))) {
                    onError(new MissingBackpressureException());
                } else {
                    schedule();
                }
            }
        }

        public void onCompleted() {
            if (!isUnsubscribed() && !this.finished) {
                this.finished = true;
                schedule();
            }
        }

        public void onError(Throwable th) {
            if (!isUnsubscribed() && !this.finished) {
                this.error = th;
                unsubscribe();
                this.finished = true;
                schedule();
            }
        }

        /* access modifiers changed from: protected */
        public void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this.action);
            }
        }

        /* access modifiers changed from: package-private */
        public void pollQueue() {
            Object poll;
            AtomicLong atomicLong = this.requested;
            AtomicLong atomicLong2 = this.counter;
            int i = 0;
            do {
                atomicLong2.set(1);
                long j = atomicLong.get();
                long j2 = 0;
                while (!this.child.isUnsubscribed()) {
                    if (this.finished) {
                        Throwable th = this.error;
                        if (th != null) {
                            this.queue.clear();
                            this.child.onError(th);
                            return;
                        } else if (this.queue.isEmpty()) {
                            this.child.onCompleted();
                            return;
                        }
                    }
                    if (j > 0 && (poll = this.queue.poll()) != null) {
                        this.child.onNext(this.on.getValue(poll));
                        j--;
                        i++;
                        j2++;
                    } else if (j2 > 0 && atomicLong.get() != Long.MAX_VALUE) {
                        atomicLong.addAndGet(-j2);
                    }
                }
                return;
            } while (atomicLong2.decrementAndGet() > 0);
            if (i > 0) {
                request((long) i);
            }
        }
    }

    static final class ScheduledUnsubscribe extends AtomicInteger implements Subscription {
        volatile boolean unsubscribed = false;
        final Scheduler.Worker worker;

        public ScheduledUnsubscribe(Scheduler.Worker worker2) {
            this.worker = worker2;
        }

        public boolean isUnsubscribed() {
            return this.unsubscribed;
        }

        public void unsubscribe() {
            if (getAndSet(1) == 0) {
                this.worker.schedule(new Action0() {
                    public void call() {
                        ScheduledUnsubscribe.this.worker.unsubscribe();
                        ScheduledUnsubscribe.this.unsubscribed = true;
                    }
                });
            }
        }
    }
}
