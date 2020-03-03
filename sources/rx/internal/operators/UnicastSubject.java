package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    public static <T> UnicastSubject<T> create() {
        return create(16);
    }

    public static <T> UnicastSubject<T> create(int i) {
        return new UnicastSubject<>(new State(i));
    }

    private UnicastSubject(State<T> state2) {
        super(state2);
        this.state = state2;
    }

    public void onNext(T t) {
        this.state.onNext(t);
    }

    public void onError(Throwable th) {
        this.state.onError(th);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }

    public boolean hasObservers() {
        return this.state.subscriber.get() != null;
    }

    static final class State<T> extends AtomicLong implements Observable.OnSubscribe<T>, Observer<T>, Producer, Action0 {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final NotificationLite<T> nl = NotificationLite.instance();
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();

        public State(int i) {
            Queue<Object> queue2;
            if (i > 1) {
                queue2 = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(i) : new SpscUnboundedAtomicArrayQueue<>(i);
            } else {
                queue2 = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = queue2;
        }

        public void onNext(T t) {
            if (!this.done) {
                if (!this.caughtUp) {
                    boolean z = false;
                    synchronized (this) {
                        if (!this.caughtUp) {
                            this.queue.offer(this.nl.next(t));
                            z = true;
                        }
                    }
                    if (z) {
                        replay();
                        return;
                    }
                }
                Subscriber subscriber2 = this.subscriber.get();
                try {
                    subscriber2.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    subscriber2.onError(OnErrorThrowable.addValueAsLastCause(th, t));
                }
            }
        }

        public void onError(Throwable th) {
            boolean z;
            if (!this.done) {
                this.error = th;
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        z = true ^ this.caughtUp;
                    }
                    if (z) {
                        replay();
                        return;
                    }
                }
                this.subscriber.get().onError(th);
            }
        }

        public void onCompleted() {
            boolean z;
            if (!this.done) {
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        z = true ^ this.caughtUp;
                    }
                    if (z) {
                        replay();
                        return;
                    }
                }
                this.subscriber.get().onCompleted();
            }
        }

        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (j > 0) {
                BackpressureUtils.getAndAddRequest(this, j);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        public void call(Subscriber<? super T> subscriber2) {
            if (this.subscriber.compareAndSet((Object) null, subscriber2)) {
                subscriber2.add(Subscriptions.create(this));
                subscriber2.setProducer(this);
                return;
            }
            subscriber2.onError(new IllegalStateException("Only a single subscriber is allowed"));
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
            r2 = r14.subscriber.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
            if (r2 == null) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            if (checkTerminated(r14.done, r0.isEmpty(), r2) == false) goto L_0x0027;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
            r4 = get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
            if (r4 != Long.MAX_VALUE) goto L_0x0036;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
            r6 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
            r6 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
            r9 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
            if (r4 == 0) goto L_0x0070;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
            r11 = r14.done;
            r12 = r0.poll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
            if (r12 != null) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
            r13 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
            r13 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
            if (checkTerminated(r11, r13, r2) == false) goto L_0x0050;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
            if (r13 == false) goto L_0x0053;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0053, code lost:
            r11 = r14.nl.getValue(r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            r2.onNext(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x005c, code lost:
            r4 = r4 - 1;
            r9 = r9 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0062, code lost:
            r0.clear();
            rx.exceptions.Exceptions.throwIfFatal(r1);
            r2.onError(rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r11));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x006f, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0070, code lost:
            if (r6 != false) goto L_0x007c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0074, code lost:
            if (r9 == 0) goto L_0x007c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0076, code lost:
            addAndGet(-r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x007b, code lost:
            r6 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x007c, code lost:
            monitor-enter(r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x007f, code lost:
            if (r14.missed != false) goto L_0x008f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0081, code lost:
            if (r6 == false) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0087, code lost:
            if (r0.isEmpty() == false) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0089, code lost:
            r14.caughtUp = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x008b, code lost:
            r14.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x008d, code lost:
            monitor-exit(r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x008e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x008f, code lost:
            r14.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0091, code lost:
            monitor-exit(r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
            r0 = r14.queue;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay() {
            /*
                r14 = this;
                monitor-enter(r14)
                boolean r0 = r14.emitting     // Catch:{ all -> 0x0097 }
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r14.missed = r1     // Catch:{ all -> 0x0097 }
                monitor-exit(r14)     // Catch:{ all -> 0x0097 }
                return
            L_0x000a:
                r14.emitting = r1     // Catch:{ all -> 0x0097 }
                monitor-exit(r14)     // Catch:{ all -> 0x0097 }
                java.util.Queue<java.lang.Object> r0 = r14.queue
            L_0x000f:
                java.util.concurrent.atomic.AtomicReference<rx.Subscriber<? super T>> r2 = r14.subscriber
                java.lang.Object r2 = r2.get()
                rx.Subscriber r2 = (rx.Subscriber) r2
                r3 = 0
                if (r2 == 0) goto L_0x007b
                boolean r4 = r14.done
                boolean r5 = r0.isEmpty()
                boolean r4 = r14.checkTerminated(r4, r5, r2)
                if (r4 == 0) goto L_0x0027
                return
            L_0x0027:
                long r4 = r14.get()
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 != 0) goto L_0x0036
                r6 = 1
                goto L_0x0037
            L_0x0036:
                r6 = 0
            L_0x0037:
                r7 = 0
                r9 = r7
            L_0x003a:
                int r11 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r11 == 0) goto L_0x0070
                boolean r11 = r14.done
                java.lang.Object r12 = r0.poll()
                if (r12 != 0) goto L_0x0048
                r13 = 1
                goto L_0x0049
            L_0x0048:
                r13 = 0
            L_0x0049:
                boolean r11 = r14.checkTerminated(r11, r13, r2)
                if (r11 == 0) goto L_0x0050
                return
            L_0x0050:
                if (r13 == 0) goto L_0x0053
                goto L_0x0070
            L_0x0053:
                rx.internal.operators.NotificationLite<T> r11 = r14.nl
                java.lang.Object r11 = r11.getValue(r12)
                r2.onNext(r11)     // Catch:{ Throwable -> 0x0061 }
                r11 = 1
                long r4 = r4 - r11
                long r9 = r9 + r11
                goto L_0x003a
            L_0x0061:
                r1 = move-exception
                r0.clear()
                rx.exceptions.Exceptions.throwIfFatal(r1)
                java.lang.Throwable r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r11)
                r2.onError(r0)
                return
            L_0x0070:
                if (r6 != 0) goto L_0x007c
                int r2 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r2 == 0) goto L_0x007c
                long r4 = -r9
                r14.addAndGet(r4)
                goto L_0x007c
            L_0x007b:
                r6 = 0
            L_0x007c:
                monitor-enter(r14)
                boolean r2 = r14.missed     // Catch:{ all -> 0x0094 }
                if (r2 != 0) goto L_0x008f
                if (r6 == 0) goto L_0x008b
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0094 }
                if (r0 == 0) goto L_0x008b
                r14.caughtUp = r1     // Catch:{ all -> 0x0094 }
            L_0x008b:
                r14.emitting = r3     // Catch:{ all -> 0x0094 }
                monitor-exit(r14)     // Catch:{ all -> 0x0094 }
                return
            L_0x008f:
                r14.missed = r3     // Catch:{ all -> 0x0094 }
                monitor-exit(r14)     // Catch:{ all -> 0x0094 }
                goto L_0x000f
            L_0x0094:
                r0 = move-exception
                monitor-exit(r14)     // Catch:{ all -> 0x0094 }
                throw r0
            L_0x0097:
                r0 = move-exception
                monitor-exit(r14)     // Catch:{ all -> 0x0097 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.UnicastSubject.State.replay():void");
        }

        public void call() {
            this.done = true;
            synchronized (this) {
                if (!this.emitting) {
                    this.emitting = true;
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber2) {
            if (subscriber2.isUnsubscribed()) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    subscriber2.onError(th);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    subscriber2.onCompleted();
                    return true;
                }
            }
        }
    }
}
