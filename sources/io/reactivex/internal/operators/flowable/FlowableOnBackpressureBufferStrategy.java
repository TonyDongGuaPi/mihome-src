package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Action;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnBackpressureBufferStrategy<T> extends AbstractFlowableWithUpstream<T, T> {
    final long bufferSize;
    final Action onOverflow;
    final BackpressureOverflowStrategy strategy;

    public FlowableOnBackpressureBufferStrategy(Flowable<T> flowable, long j, Action action, BackpressureOverflowStrategy backpressureOverflowStrategy) {
        super(flowable);
        this.bufferSize = j;
        this.onOverflow = action;
        this.strategy = backpressureOverflowStrategy;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new OnBackpressureBufferStrategySubscriber(subscriber, this.onOverflow, this.strategy, this.bufferSize));
    }

    static final class OnBackpressureBufferStrategySubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 3240706908776709697L;
        final long bufferSize;
        volatile boolean cancelled;
        final Deque<T> deque = new ArrayDeque();
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final Action onOverflow;
        final AtomicLong requested = new AtomicLong();
        final BackpressureOverflowStrategy strategy;
        Subscription upstream;

        OnBackpressureBufferStrategySubscriber(Subscriber<? super T> subscriber, Action action, BackpressureOverflowStrategy backpressureOverflowStrategy, long j) {
            this.downstream = subscriber;
            this.onOverflow = action;
            this.strategy = backpressureOverflowStrategy;
            this.bufferSize = j;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
            r1 = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNext(T r7) {
            /*
                r6 = this;
                boolean r0 = r6.done
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                java.util.Deque<T> r0 = r6.deque
                monitor-enter(r0)
                int r1 = r0.size()     // Catch:{ all -> 0x0064 }
                long r1 = (long) r1     // Catch:{ all -> 0x0064 }
                long r3 = r6.bufferSize     // Catch:{ all -> 0x0064 }
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                r1 = 0
                r2 = 1
                if (r5 != 0) goto L_0x0032
                int[] r3 = io.reactivex.internal.operators.flowable.FlowableOnBackpressureBufferStrategy.AnonymousClass1.$SwitchMap$io$reactivex$BackpressureOverflowStrategy     // Catch:{ all -> 0x0064 }
                io.reactivex.BackpressureOverflowStrategy r4 = r6.strategy     // Catch:{ all -> 0x0064 }
                int r4 = r4.ordinal()     // Catch:{ all -> 0x0064 }
                r3 = r3[r4]     // Catch:{ all -> 0x0064 }
                switch(r3) {
                    case 1: goto L_0x002a;
                    case 2: goto L_0x0023;
                    default: goto L_0x0022;
                }     // Catch:{ all -> 0x0064 }
            L_0x0022:
                goto L_0x0036
            L_0x0023:
                r0.poll()     // Catch:{ all -> 0x0064 }
                r0.offer(r7)     // Catch:{ all -> 0x0064 }
                goto L_0x0030
            L_0x002a:
                r0.pollLast()     // Catch:{ all -> 0x0064 }
                r0.offer(r7)     // Catch:{ all -> 0x0064 }
            L_0x0030:
                r1 = 1
                goto L_0x0035
            L_0x0032:
                r0.offer(r7)     // Catch:{ all -> 0x0064 }
            L_0x0035:
                r2 = 0
            L_0x0036:
                monitor-exit(r0)     // Catch:{ all -> 0x0064 }
                if (r1 == 0) goto L_0x0050
                io.reactivex.functions.Action r7 = r6.onOverflow
                if (r7 == 0) goto L_0x0063
                io.reactivex.functions.Action r7 = r6.onOverflow     // Catch:{ Throwable -> 0x0043 }
                r7.run()     // Catch:{ Throwable -> 0x0043 }
                goto L_0x0063
            L_0x0043:
                r7 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r7)
                org.reactivestreams.Subscription r0 = r6.upstream
                r0.cancel()
                r6.onError(r7)
                goto L_0x0063
            L_0x0050:
                if (r2 == 0) goto L_0x0060
                org.reactivestreams.Subscription r7 = r6.upstream
                r7.cancel()
                io.reactivex.exceptions.MissingBackpressureException r7 = new io.reactivex.exceptions.MissingBackpressureException
                r7.<init>()
                r6.onError(r7)
                goto L_0x0063
            L_0x0060:
                r6.drain()
            L_0x0063:
                return
            L_0x0064:
                r7 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0064 }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableOnBackpressureBufferStrategy.OnBackpressureBufferStrategySubscriber.onNext(java.lang.Object):void");
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                clear(this.deque);
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(Deque<T> deque2) {
            synchronized (deque2) {
                deque2.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            boolean isEmpty;
            T poll;
            if (getAndIncrement() == 0) {
                Deque<T> deque2 = this.deque;
                Subscriber<? super T> subscriber = this.downstream;
                int i = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            clear(deque2);
                            return;
                        }
                        boolean z = this.done;
                        synchronized (deque2) {
                            poll = deque2.poll();
                        }
                        boolean z2 = poll == null;
                        if (z) {
                            Throwable th = this.error;
                            if (th != null) {
                                clear(deque2);
                                subscriber.onError(th);
                                return;
                            } else if (z2) {
                                subscriber.onComplete();
                                return;
                            }
                        }
                        if (z2) {
                            break;
                        }
                        subscriber.onNext(poll);
                        j2++;
                    }
                    if (j2 == j) {
                        if (this.cancelled) {
                            clear(deque2);
                            return;
                        }
                        boolean z3 = this.done;
                        synchronized (deque2) {
                            isEmpty = deque2.isEmpty();
                        }
                        if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                clear(deque2);
                                subscriber.onError(th2);
                                return;
                            } else if (isEmpty) {
                                subscriber.onComplete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        BackpressureHelper.produced(this.requested, j2);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }
}
