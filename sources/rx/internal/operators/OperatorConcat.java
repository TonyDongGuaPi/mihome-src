package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

public final class OperatorConcat<T> implements Observable.Operator<T, Observable<? extends T>> {

    private static final class Holder {
        static final OperatorConcat<Object> INSTANCE = new OperatorConcat<>();

        private Holder() {
        }
    }

    public static <T> OperatorConcat<T> instance() {
        return Holder.INSTANCE;
    }

    private OperatorConcat() {
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        ConcatSubscriber concatSubscriber = new ConcatSubscriber(serializedSubscriber, serialSubscription);
        subscriber.setProducer(new ConcatProducer(concatSubscriber));
        return concatSubscriber;
    }

    static final class ConcatProducer<T> implements Producer {
        final ConcatSubscriber<T> cs;

        ConcatProducer(ConcatSubscriber<T> concatSubscriber) {
            this.cs = concatSubscriber;
        }

        public void request(long j) {
            this.cs.requestFromChild(j);
        }
    }

    static final class ConcatSubscriber<T> extends Subscriber<Observable<? extends T>> {
        private final ProducerArbiter arbiter;
        private final Subscriber<T> child;
        private final SerialSubscription current;
        volatile ConcatInnerSubscriber<T> currentSubscriber;
        final NotificationLite<Observable<? extends T>> nl = NotificationLite.instance();
        final ConcurrentLinkedQueue<Object> queue;
        private final AtomicLong requested = new AtomicLong();
        final AtomicInteger wip = new AtomicInteger();

        public ConcatSubscriber(Subscriber<T> subscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.child = subscriber;
            this.current = serialSubscription;
            this.arbiter = new ProducerArbiter();
            this.queue = new ConcurrentLinkedQueue<>();
            add(Subscriptions.create(new Action0() {
                public void call() {
                    ConcatSubscriber.this.queue.clear();
                }
            }));
        }

        public void onStart() {
            request(2);
        }

        /* access modifiers changed from: private */
        public void requestFromChild(long j) {
            if (j > 0) {
                long andAddRequest = BackpressureUtils.getAndAddRequest(this.requested, j);
                this.arbiter.request(j);
                if (andAddRequest == 0 && this.currentSubscriber == null && this.wip.get() > 0) {
                    subscribeNext();
                }
            }
        }

        /* access modifiers changed from: private */
        public void decrementRequested() {
            this.requested.decrementAndGet();
        }

        public void onNext(Observable<? extends T> observable) {
            this.queue.add(this.nl.next(observable));
            if (this.wip.getAndIncrement() == 0) {
                subscribeNext();
            }
        }

        public void onError(Throwable th) {
            this.child.onError(th);
            unsubscribe();
        }

        public void onCompleted() {
            this.queue.add(this.nl.completed());
            if (this.wip.getAndIncrement() == 0) {
                subscribeNext();
            }
        }

        /* access modifiers changed from: package-private */
        public void completeInner() {
            this.currentSubscriber = null;
            if (this.wip.decrementAndGet() > 0) {
                subscribeNext();
            }
            request(1);
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            if (this.requested.get() > 0) {
                Object poll = this.queue.poll();
                if (this.nl.isCompleted(poll)) {
                    this.child.onCompleted();
                } else if (poll != null) {
                    this.currentSubscriber = new ConcatInnerSubscriber<>(this, this.child, this.arbiter);
                    this.current.set(this.currentSubscriber);
                    this.nl.getValue(poll).unsafeSubscribe(this.currentSubscriber);
                }
            } else {
                if (this.nl.isCompleted(this.queue.peek())) {
                    this.child.onCompleted();
                }
            }
        }
    }

    static class ConcatInnerSubscriber<T> extends Subscriber<T> {
        private final ProducerArbiter arbiter;
        private final Subscriber<T> child;
        private final AtomicInteger once = new AtomicInteger();
        private final ConcatSubscriber<T> parent;

        public ConcatInnerSubscriber(ConcatSubscriber<T> concatSubscriber, Subscriber<T> subscriber, ProducerArbiter producerArbiter) {
            this.parent = concatSubscriber;
            this.child = subscriber;
            this.arbiter = producerArbiter;
        }

        public void onNext(T t) {
            this.child.onNext(t);
            this.parent.decrementRequested();
            this.arbiter.produced(1);
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(0, 1)) {
                this.parent.onError(th);
            }
        }

        public void onCompleted() {
            if (this.once.compareAndSet(0, 1)) {
                this.parent.completeInner();
            }
        }

        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }
    }
}
