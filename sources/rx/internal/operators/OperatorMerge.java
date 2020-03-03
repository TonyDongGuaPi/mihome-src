package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    private static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        private HolderNoDelay() {
        }
    }

    private static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        private HolderDelayErrors() {
        }
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        if (z) {
            return HolderDelayErrors.INSTANCE;
        }
        return HolderNoDelay.INSTANCE;
    }

    public static <T> OperatorMerge<T> instance(boolean z, int i) {
        if (i == Integer.MAX_VALUE) {
            return instance(z);
        }
        return new OperatorMerge<>(z, i);
    }

    private OperatorMerge(boolean z, int i) {
        this.delayErrors = z;
        this.maxConcurrent = i;
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        MergeSubscriber mergeSubscriber = new MergeSubscriber(subscriber, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> mergeProducer = new MergeProducer<>(mergeSubscriber);
        mergeSubscriber.producer = mergeProducer;
        subscriber.add(mergeSubscriber);
        subscriber.setProducer(mergeProducer);
        return mergeSubscriber;
    }

    static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> mergeSubscriber) {
            this.subscriber = mergeSubscriber;
        }

        public void request(long j) {
            if (j > 0) {
                if (get() != Long.MAX_VALUE) {
                    BackpressureUtils.getAndAddRequest(this, j);
                    this.subscriber.emit();
                }
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int i) {
            return addAndGet((long) (-i));
        }
    }

    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        final NotificationLite<T> nl = NotificationLite.instance();
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        public MergeSubscriber(Subscriber<? super T> subscriber, boolean z, int i) {
            this.child = subscriber;
            this.delayErrors = z;
            this.maxConcurrent = i;
            request(i == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) i);
        }

        /* access modifiers changed from: package-private */
        public Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (concurrentLinkedQueue == null) {
                synchronized (this) {
                    concurrentLinkedQueue = this.errors;
                    if (concurrentLinkedQueue == null) {
                        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                        this.errors = concurrentLinkedQueue;
                    }
                }
            }
            return concurrentLinkedQueue;
        }

        /* access modifiers changed from: package-private */
        public CompositeSubscription getOrCreateComposite() {
            CompositeSubscription compositeSubscription;
            CompositeSubscription compositeSubscription2 = this.subscriptions;
            if (compositeSubscription2 != null) {
                return compositeSubscription2;
            }
            boolean z = false;
            synchronized (this) {
                compositeSubscription = this.subscriptions;
                if (compositeSubscription == null) {
                    CompositeSubscription compositeSubscription3 = new CompositeSubscription();
                    this.subscriptions = compositeSubscription3;
                    compositeSubscription = compositeSubscription3;
                    z = true;
                }
            }
            if (z) {
                add(compositeSubscription);
            }
            return compositeSubscription;
        }

        public void onNext(Observable<? extends T> observable) {
            if (observable != null) {
                if (observable instanceof ScalarSynchronousObservable) {
                    tryEmit(((ScalarSynchronousObservable) observable).get());
                    return;
                }
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                InnerSubscriber innerSubscriber = new InnerSubscriber(this, j);
                addInner(innerSubscriber);
                observable.unsafeSubscribe(innerSubscriber);
                emit();
            }
        }

        private void reportError() {
            ArrayList arrayList = new ArrayList(this.errors);
            if (arrayList.size() == 1) {
                this.child.onError((Throwable) arrayList.get(0));
            } else {
                this.child.onError(new CompositeException(arrayList));
            }
        }

        public void onError(Throwable th) {
            getOrCreateErrorQueue().offer(th);
            this.done = true;
            emit();
        }

        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* access modifiers changed from: package-private */
        public void addInner(InnerSubscriber<T> innerSubscriber) {
            getOrCreateComposite().add(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[(length + 1)];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        /* access modifiers changed from: package-private */
        public void removeInner(InnerSubscriber<T> innerSubscriber) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer != null) {
                rxRingBuffer.release();
            }
            this.subscriptions.remove(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerSubscriber.equals(innerSubscriberArr[i2])) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        this.innerSubscribers = EMPTY;
                        return;
                    }
                    InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[(length - 1)];
                    System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, i);
                    System.arraycopy(innerSubscriberArr, i + 1, innerSubscriberArr2, i, (length - i) - 1);
                    this.innerSubscribers = innerSubscriberArr2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(InnerSubscriber<T> innerSubscriber, T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(innerSubscriber, t, j);
            } else {
                queueScalar(innerSubscriber, t);
            }
        }

        /* access modifiers changed from: protected */
        public void queueScalar(InnerSubscriber<T> innerSubscriber, T t) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer == null) {
                rxRingBuffer = RxRingBuffer.getSpscInstance();
                innerSubscriber.add(rxRingBuffer);
                innerSubscriber.queue = rxRingBuffer;
            }
            try {
                rxRingBuffer.onNext(this.nl.next(t));
                emit();
            } catch (MissingBackpressureException e) {
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e);
            } catch (IllegalStateException e2) {
                if (!innerSubscriber.isUnsubscribed()) {
                    innerSubscriber.unsubscribe();
                    innerSubscriber.onError(e2);
                }
            }
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
            	at java.base/java.util.Objects.checkIndex(Objects.java:372)
            	at java.base/java.util.ArrayList.get(ArrayList.java:458)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
            */
        protected void emitScalar(rx.internal.operators.OperatorMerge.InnerSubscriber<T> r5, T r6, long r7) {
            /*
                r4 = this;
                r0 = 1
                r1 = 0
                rx.Subscriber<? super T> r2 = r4.child     // Catch:{ Throwable -> 0x000b }
                r2.onNext(r6)     // Catch:{ Throwable -> 0x000b }
                goto L_0x0023
            L_0x0008:
                r5 = move-exception
                r0 = 0
                goto L_0x0049
            L_0x000b:
                r6 = move-exception
                boolean r2 = r4.delayErrors     // Catch:{ all -> 0x0008 }
                if (r2 != 0) goto L_0x001c
                rx.exceptions.Exceptions.throwIfFatal(r6)     // Catch:{ all -> 0x0008 }
                r5.unsubscribe()     // Catch:{ all -> 0x001a }
                r5.onError(r6)     // Catch:{ all -> 0x001a }
                return
            L_0x001a:
                r5 = move-exception
                goto L_0x0049
            L_0x001c:
                java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch:{ all -> 0x0008 }
                r2.offer(r6)     // Catch:{ all -> 0x0008 }
            L_0x0023:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r6 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r6 == 0) goto L_0x0031
                rx.internal.operators.OperatorMerge$MergeProducer<T> r6 = r4.producer     // Catch:{ all -> 0x0008 }
                r6.produced(r0)     // Catch:{ all -> 0x0008 }
            L_0x0031:
                r6 = 1
                r5.requestMore(r6)     // Catch:{ all -> 0x0008 }
                monitor-enter(r4)     // Catch:{ all -> 0x0008 }
                boolean r5 = r4.missed     // Catch:{ all -> 0x0046 }
                if (r5 != 0) goto L_0x003f
                r4.emitting = r1     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                return
            L_0x003f:
                r4.missed = r1     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                r4.emitLoop()
                return
            L_0x0046:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                throw r5     // Catch:{ all -> 0x001a }
            L_0x0049:
                if (r0 != 0) goto L_0x0053
                monitor-enter(r4)
                r4.emitting = r1     // Catch:{ all -> 0x0050 }
                monitor-exit(r4)     // Catch:{ all -> 0x0050 }
                goto L_0x0053
            L_0x0050:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0050 }
                throw r5
            L_0x0053:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber, java.lang.Object, long):void");
        }

        public void requestMore(long j) {
            request(j);
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(t, j);
            } else {
                queueScalar(t);
            }
        }

        /* access modifiers changed from: protected */
        public void queueScalar(T t) {
            Queue<Object> queue2;
            Queue<Object> queue3 = this.queue;
            if (queue3 == null) {
                int i = this.maxConcurrent;
                if (i == Integer.MAX_VALUE) {
                    queue3 = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else {
                    if (!Pow2.isPowerOfTwo(i)) {
                        queue2 = new SpscExactAtomicArrayQueue<>(i);
                    } else if (UnsafeAccess.isUnsafeAvailable()) {
                        queue2 = new SpscArrayQueue<>(i);
                    } else {
                        queue2 = new SpscAtomicArrayQueue<>(i);
                    }
                    queue3 = queue2;
                }
                this.queue = queue3;
            }
            if (!queue3.offer(t)) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
                return;
            }
            emit();
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
            	at java.base/java.util.Objects.checkIndex(Objects.java:372)
            	at java.base/java.util.ArrayList.get(ArrayList.java:458)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
            */
        protected void emitScalar(T r5, long r6) {
            /*
                r4 = this;
                r0 = 1
                r1 = 0
                rx.Subscriber<? super T> r2 = r4.child     // Catch:{ Throwable -> 0x000b }
                r2.onNext(r5)     // Catch:{ Throwable -> 0x000b }
                goto L_0x0023
            L_0x0008:
                r5 = move-exception
                r0 = 0
                goto L_0x0049
            L_0x000b:
                r5 = move-exception
                boolean r2 = r4.delayErrors     // Catch:{ all -> 0x0008 }
                if (r2 != 0) goto L_0x001c
                rx.exceptions.Exceptions.throwIfFatal(r5)     // Catch:{ all -> 0x0008 }
                r4.unsubscribe()     // Catch:{ all -> 0x001a }
                r4.onError(r5)     // Catch:{ all -> 0x001a }
                return
            L_0x001a:
                r5 = move-exception
                goto L_0x0049
            L_0x001c:
                java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch:{ all -> 0x0008 }
                r2.offer(r5)     // Catch:{ all -> 0x0008 }
            L_0x0023:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r5 == 0) goto L_0x0031
                rx.internal.operators.OperatorMerge$MergeProducer<T> r5 = r4.producer     // Catch:{ all -> 0x0008 }
                r5.produced(r0)     // Catch:{ all -> 0x0008 }
            L_0x0031:
                r5 = 1
                r4.requestMore(r5)     // Catch:{ all -> 0x0008 }
                monitor-enter(r4)     // Catch:{ all -> 0x0008 }
                boolean r5 = r4.missed     // Catch:{ all -> 0x0046 }
                if (r5 != 0) goto L_0x003f
                r4.emitting = r1     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                return
            L_0x003f:
                r4.missed = r1     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                r4.emitLoop()
                return
            L_0x0046:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                throw r5     // Catch:{ all -> 0x001a }
            L_0x0049:
                if (r0 != 0) goto L_0x0053
                monitor-enter(r4)
                r4.emitting = r1     // Catch:{ all -> 0x0050 }
                monitor-exit(r4)     // Catch:{ all -> 0x0050 }
                goto L_0x0053
            L_0x0050:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0050 }
                throw r5
            L_0x0053:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(java.lang.Object, long):void");
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x01c3, code lost:
            r0 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x01c4, code lost:
            r3 = r2;
         */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* JADX WARNING: Removed duplicated region for block: B:177:0x01cc  */
        /* JADX WARNING: Removed duplicated region for block: B:208:0x0191 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x00f2 A[Catch:{ Throwable -> 0x0047, all -> 0x01c8, all -> 0x0057 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitLoop() {
            /*
                r21 = this;
                r1 = r21
                rx.Subscriber<? super T> r4 = r1.child     // Catch:{ all -> 0x01c8 }
            L_0x0004:
                boolean r0 = r21.checkTerminate()     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                java.util.Queue<java.lang.Object> r5 = r1.queue     // Catch:{ all -> 0x01c8 }
                rx.internal.operators.OperatorMerge$MergeProducer<T> r0 = r1.producer     // Catch:{ all -> 0x01c8 }
                long r6 = r0.get()     // Catch:{ all -> 0x01c8 }
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r0 != 0) goto L_0x001e
                r10 = 1
                goto L_0x001f
            L_0x001e:
                r10 = 0
            L_0x001f:
                r11 = 1
                r14 = 0
                if (r5 == 0) goto L_0x008d
                r0 = 0
            L_0x0026:
                r16 = r0
                r0 = 0
                r2 = 0
            L_0x002a:
                int r17 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
                if (r17 <= 0) goto L_0x006e
                java.lang.Object r8 = r5.poll()     // Catch:{ all -> 0x01c8 }
                boolean r0 = r21.checkTerminate()     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x0039
                return
            L_0x0039:
                if (r8 != 0) goto L_0x003d
                r0 = r8
                goto L_0x006e
            L_0x003d:
                rx.internal.operators.NotificationLite<T> r0 = r1.nl     // Catch:{ all -> 0x01c8 }
                java.lang.Object r0 = r0.getValue(r8)     // Catch:{ all -> 0x01c8 }
                r4.onNext(r0)     // Catch:{ Throwable -> 0x0047 }
                goto L_0x0062
            L_0x0047:
                r0 = move-exception
                r9 = r0
                boolean r0 = r1.delayErrors     // Catch:{ all -> 0x01c8 }
                if (r0 != 0) goto L_0x005b
                rx.exceptions.Exceptions.throwIfFatal(r9)     // Catch:{ all -> 0x01c8 }
                r21.unsubscribe()     // Catch:{ all -> 0x0057 }
                r4.onError(r9)     // Catch:{ all -> 0x0057 }
                return
            L_0x0057:
                r0 = move-exception
                r3 = 1
                goto L_0x01ca
            L_0x005b:
                java.util.Queue r0 = r21.getOrCreateErrorQueue()     // Catch:{ all -> 0x01c8 }
                r0.offer(r9)     // Catch:{ all -> 0x01c8 }
            L_0x0062:
                int r16 = r16 + 1
                int r2 = r2 + 1
                long r6 = r6 - r11
                r0 = r8
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x002a
            L_0x006e:
                if (r2 <= 0) goto L_0x007e
                if (r10 == 0) goto L_0x0078
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x007e
            L_0x0078:
                rx.internal.operators.OperatorMerge$MergeProducer<T> r6 = r1.producer     // Catch:{ all -> 0x01c8 }
                long r6 = r6.produced(r2)     // Catch:{ all -> 0x01c8 }
            L_0x007e:
                int r2 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
                if (r2 == 0) goto L_0x008f
                if (r0 != 0) goto L_0x0085
                goto L_0x008f
            L_0x0085:
                r0 = r16
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x0026
            L_0x008d:
                r16 = 0
            L_0x008f:
                boolean r0 = r1.done     // Catch:{ all -> 0x01c8 }
                java.util.Queue<java.lang.Object> r2 = r1.queue     // Catch:{ all -> 0x01c8 }
                rx.internal.operators.OperatorMerge$InnerSubscriber<?>[] r5 = r1.innerSubscribers     // Catch:{ all -> 0x01c8 }
                int r8 = r5.length     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x00b5
                if (r2 == 0) goto L_0x00a0
                boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x00b5
            L_0x00a0:
                if (r8 != 0) goto L_0x00b5
                java.util.concurrent.ConcurrentLinkedQueue<java.lang.Throwable> r0 = r1.errors     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x00b1
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x01c8 }
                if (r0 == 0) goto L_0x00ad
                goto L_0x00b1
            L_0x00ad:
                r21.reportError()     // Catch:{ all -> 0x01c8 }
                goto L_0x00b4
            L_0x00b1:
                r4.onCompleted()     // Catch:{ all -> 0x01c8 }
            L_0x00b4:
                return
            L_0x00b5:
                if (r8 <= 0) goto L_0x019e
                long r11 = r1.lastId     // Catch:{ all -> 0x01c8 }
                int r0 = r1.lastIndex     // Catch:{ all -> 0x01c8 }
                if (r8 <= r0) goto L_0x00c8
                r2 = r5[r0]     // Catch:{ all -> 0x01c8 }
                r20 = r4
                long r3 = r2.id     // Catch:{ all -> 0x01c8 }
                int r2 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
                if (r2 == 0) goto L_0x00ed
                goto L_0x00ca
            L_0x00c8:
                r20 = r4
            L_0x00ca:
                if (r8 > r0) goto L_0x00cd
                r0 = 0
            L_0x00cd:
                r2 = r0
                r0 = 0
            L_0x00cf:
                if (r0 >= r8) goto L_0x00e4
                r3 = r5[r2]     // Catch:{ all -> 0x01c8 }
                long r3 = r3.id     // Catch:{ all -> 0x01c8 }
                int r9 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
                if (r9 != 0) goto L_0x00da
                goto L_0x00e4
            L_0x00da:
                int r3 = r2 + 1
                if (r3 != r8) goto L_0x00e0
                r2 = 0
                goto L_0x00e1
            L_0x00e0:
                r2 = r3
            L_0x00e1:
                int r0 = r0 + 1
                goto L_0x00cf
            L_0x00e4:
                r1.lastIndex = r2     // Catch:{ all -> 0x01c8 }
                r0 = r5[r2]     // Catch:{ all -> 0x01c8 }
                long r3 = r0.id     // Catch:{ all -> 0x01c8 }
                r1.lastId = r3     // Catch:{ all -> 0x01c8 }
                r0 = r2
            L_0x00ed:
                r2 = r0
                r0 = 0
                r3 = 0
            L_0x00f0:
                if (r0 >= r8) goto L_0x0191
                boolean r4 = r21.checkTerminate()     // Catch:{ all -> 0x01c8 }
                if (r4 == 0) goto L_0x00f9
                return
            L_0x00f9:
                r4 = r5[r2]     // Catch:{ all -> 0x01c8 }
                r9 = 0
            L_0x00fc:
                r11 = r9
                r9 = 0
            L_0x00fe:
                int r12 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
                if (r12 <= 0) goto L_0x0114
                boolean r12 = r21.checkTerminate()     // Catch:{ all -> 0x01c8 }
                if (r12 == 0) goto L_0x0109
                return
            L_0x0109:
                rx.internal.util.RxRingBuffer r12 = r4.queue     // Catch:{ all -> 0x01c8 }
                if (r12 != 0) goto L_0x010e
                goto L_0x0114
            L_0x010e:
                java.lang.Object r11 = r12.poll()     // Catch:{ all -> 0x01c8 }
                if (r11 != 0) goto L_0x0119
            L_0x0114:
                r13 = r20
                r18 = 1
                goto L_0x0140
            L_0x0119:
                rx.internal.operators.NotificationLite<T> r12 = r1.nl     // Catch:{ all -> 0x01c8 }
                java.lang.Object r12 = r12.getValue(r11)     // Catch:{ all -> 0x01c8 }
                r13 = r20
                r13.onNext(r12)     // Catch:{ Throwable -> 0x012e }
                r12 = 0
                r18 = 1
                long r6 = r6 - r18
                int r9 = r9 + 1
                r20 = r13
                goto L_0x00fe
            L_0x012e:
                r0 = move-exception
                r2 = r0
                rx.exceptions.Exceptions.throwIfFatal(r2)     // Catch:{ all -> 0x0057 }
                r13.onError(r2)     // Catch:{ all -> 0x013a }
                r21.unsubscribe()     // Catch:{ all -> 0x0057 }
                return
            L_0x013a:
                r0 = move-exception
                r2 = r0
                r21.unsubscribe()     // Catch:{ all -> 0x0057 }
                throw r2     // Catch:{ all -> 0x0057 }
            L_0x0140:
                if (r9 <= 0) goto L_0x0156
                if (r10 != 0) goto L_0x014b
                rx.internal.operators.OperatorMerge$MergeProducer<T> r6 = r1.producer     // Catch:{ all -> 0x01c8 }
                long r6 = r6.produced(r9)     // Catch:{ all -> 0x01c8 }
                goto L_0x0150
            L_0x014b:
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            L_0x0150:
                long r14 = (long) r9     // Catch:{ all -> 0x01c8 }
                r4.requestMore(r14)     // Catch:{ all -> 0x01c8 }
                r14 = 0
            L_0x0156:
                int r9 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
                if (r9 == 0) goto L_0x0163
                if (r11 != 0) goto L_0x015d
                goto L_0x0163
            L_0x015d:
                r9 = r11
                r20 = r13
                r14 = 0
                goto L_0x00fc
            L_0x0163:
                boolean r9 = r4.done     // Catch:{ all -> 0x01c8 }
                rx.internal.util.RxRingBuffer r11 = r4.queue     // Catch:{ all -> 0x01c8 }
                if (r9 == 0) goto L_0x017e
                if (r11 == 0) goto L_0x0171
                boolean r9 = r11.isEmpty()     // Catch:{ all -> 0x01c8 }
                if (r9 == 0) goto L_0x017e
            L_0x0171:
                r1.removeInner(r4)     // Catch:{ all -> 0x01c8 }
                boolean r3 = r21.checkTerminate()     // Catch:{ all -> 0x01c8 }
                if (r3 == 0) goto L_0x017b
                return
            L_0x017b:
                int r16 = r16 + 1
                r3 = 1
            L_0x017e:
                r11 = 0
                int r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r4 != 0) goto L_0x0185
                goto L_0x0193
            L_0x0185:
                int r2 = r2 + 1
                if (r2 != r8) goto L_0x018a
                r2 = 0
            L_0x018a:
                int r0 = r0 + 1
                r14 = r11
                r20 = r13
                goto L_0x00f0
            L_0x0191:
                r13 = r20
            L_0x0193:
                r1.lastIndex = r2     // Catch:{ all -> 0x01c8 }
                r0 = r5[r2]     // Catch:{ all -> 0x01c8 }
                long r4 = r0.id     // Catch:{ all -> 0x01c8 }
                r1.lastId = r4     // Catch:{ all -> 0x01c8 }
                r0 = r16
                goto L_0x01a2
            L_0x019e:
                r13 = r4
                r0 = r16
                r3 = 0
            L_0x01a2:
                if (r0 <= 0) goto L_0x01a8
                long r4 = (long) r0     // Catch:{ all -> 0x01c8 }
                r1.request(r4)     // Catch:{ all -> 0x01c8 }
            L_0x01a8:
                if (r3 == 0) goto L_0x01ad
            L_0x01aa:
                r4 = r13
                goto L_0x0004
            L_0x01ad:
                monitor-enter(r21)     // Catch:{ all -> 0x01c8 }
                boolean r0 = r1.missed     // Catch:{ all -> 0x01bf }
                if (r0 != 0) goto L_0x01ba
                r2 = 0
                r1.emitting = r2     // Catch:{ all -> 0x01b7 }
                monitor-exit(r21)     // Catch:{ all -> 0x01b7 }
                return
            L_0x01b7:
                r0 = move-exception
                r2 = 1
                goto L_0x01c1
            L_0x01ba:
                r2 = 0
                r1.missed = r2     // Catch:{ all -> 0x01bf }
                monitor-exit(r21)     // Catch:{ all -> 0x01bf }
                goto L_0x01aa
            L_0x01bf:
                r0 = move-exception
                r2 = 0
            L_0x01c1:
                monitor-exit(r21)     // Catch:{ all -> 0x01c6 }
                throw r0     // Catch:{ all -> 0x01c3 }
            L_0x01c3:
                r0 = move-exception
                r3 = r2
                goto L_0x01ca
            L_0x01c6:
                r0 = move-exception
                goto L_0x01c1
            L_0x01c8:
                r0 = move-exception
                r3 = 0
            L_0x01ca:
                if (r3 != 0) goto L_0x01d5
                monitor-enter(r21)
                r2 = 0
                r1.emitting = r2     // Catch:{ all -> 0x01d2 }
                monitor-exit(r21)     // Catch:{ all -> 0x01d2 }
                goto L_0x01d5
            L_0x01d2:
                r0 = move-exception
                monitor-exit(r21)     // Catch:{ all -> 0x01d2 }
                throw r0
            L_0x01d5:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitLoop():void");
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (this.delayErrors || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int limit = (RxRingBuffer.SIZE / 4);
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> mergeSubscriber, long j) {
            this.parent = mergeSubscriber;
            this.id = j;
        }

        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request((long) RxRingBuffer.SIZE);
        }

        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        public void onError(Throwable th) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(th);
            this.parent.emit();
        }

        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long j) {
            int i = this.outstanding - ((int) j);
            if (i > limit) {
                this.outstanding = i;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int i2 = RxRingBuffer.SIZE - i;
            if (i2 > 0) {
                request((long) i2);
            }
        }
    }
}
