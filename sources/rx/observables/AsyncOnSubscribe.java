package rx.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;

@Experimental
public abstract class AsyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {
    /* access modifiers changed from: protected */
    public abstract S generateState();

    /* access modifiers changed from: protected */
    public abstract S next(S s, long j, Observer<Observable<? extends T>> observer);

    /* access modifiers changed from: protected */
    public void onUnsubscribe(S s) {
    }

    @Experimental
    public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3) {
        return new AsyncOnSubscribeImpl(func0, new Func3<S, Long, Observer<Observable<? extends T>>, S>() {
            public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
                action3.call(s, l, observer);
                return s;
            }
        });
    }

    @Experimental
    public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3, Action1<? super S> action1) {
        return new AsyncOnSubscribeImpl(func0, new Func3<S, Long, Observer<Observable<? extends T>>, S>() {
            public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
                action3.call(s, l, observer);
                return s;
            }
        }, action1);
    }

    @Experimental
    public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
        return new AsyncOnSubscribeImpl(func0, func3, action1);
    }

    @Experimental
    public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
        return new AsyncOnSubscribeImpl(func0, func3);
    }

    @Experimental
    public static <T> Observable.OnSubscribe<T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2) {
        return new AsyncOnSubscribeImpl(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() {
            public Void call(Void voidR, Long l, Observer<Observable<? extends T>> observer) {
                action2.call(l, observer);
                return voidR;
            }
        });
    }

    @Experimental
    public static <T> Observable.OnSubscribe<T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2, final Action0 action0) {
        return new AsyncOnSubscribeImpl(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() {
            public Void call(Void voidR, Long l, Observer<Observable<? extends T>> observer) {
                action2.call(l, observer);
                return null;
            }
        }, new Action1<Void>() {
            public void call(Void voidR) {
                action0.call();
            }
        });
    }

    private static final class AsyncOnSubscribeImpl<S, T> extends AsyncOnSubscribe<S, T> {
        private final Func0<? extends S> generator;
        private final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;

        public /* bridge */ /* synthetic */ void call(Object obj) {
            AsyncOnSubscribe.super.call((Subscriber) obj);
        }

        private AsyncOnSubscribeImpl(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
            this.generator = func0;
            this.next = func3;
            this.onUnsubscribe = action1;
        }

        public AsyncOnSubscribeImpl(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
            this(func0, func3, (Action1) null);
        }

        public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> func3, Action1<? super S> action1) {
            this((Func0) null, func3, action1);
        }

        public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> func3) {
            this((Func0) null, func3, (Action1) null);
        }

        /* access modifiers changed from: protected */
        public S generateState() {
            if (this.generator == null) {
                return null;
            }
            return this.generator.call();
        }

        /* access modifiers changed from: protected */
        public S next(S s, long j, Observer<Observable<? extends T>> observer) {
            return this.next.call(s, Long.valueOf(j), observer);
        }

        /* access modifiers changed from: protected */
        public void onUnsubscribe(S s) {
            if (this.onUnsubscribe != null) {
                this.onUnsubscribe.call(s);
            }
        }
    }

    public final void call(final Subscriber<? super T> subscriber) {
        try {
            Object generateState = generateState();
            UnicastSubject create = UnicastSubject.create();
            final AsyncOuterManager asyncOuterManager = new AsyncOuterManager(this, generateState, create);
            AnonymousClass6 r0 = new Subscriber<T>() {
                public void onNext(T t) {
                    subscriber.onNext(t);
                }

                public void onError(Throwable th) {
                    subscriber.onError(th);
                }

                public void onCompleted() {
                    subscriber.onCompleted();
                }

                public void setProducer(Producer producer) {
                    asyncOuterManager.setConcatProducer(producer);
                }
            };
            create.onBackpressureBuffer().concatMap(new Func1<Observable<T>, Observable<T>>() {
                public Observable<T> call(Observable<T> observable) {
                    return observable.onBackpressureBuffer();
                }
            }).unsafeSubscribe(r0);
            subscriber.add(r0);
            subscriber.add(asyncOuterManager);
            subscriber.setProducer(asyncOuterManager);
        } catch (Throwable th) {
            subscriber.onError(th);
        }
    }

    static final class AsyncOuterManager<S, T> implements Observer<Observable<? extends T>>, Producer, Subscription {
        private static final AtomicIntegerFieldUpdater<AsyncOuterManager> IS_UNSUBSCRIBED = AtomicIntegerFieldUpdater.newUpdater(AsyncOuterManager.class, "isUnsubscribed");
        Producer concatProducer;
        boolean emitting;
        long expectedDelivery;
        private boolean hasTerminated;
        private volatile int isUnsubscribed;
        private final UnicastSubject<Observable<T>> merger;
        private boolean onNextCalled;
        private final AsyncOnSubscribe<S, T> parent;
        List<Long> requests;
        private final SerializedObserver<Observable<? extends T>> serializedSubscriber;
        private S state;
        /* access modifiers changed from: private */
        public final CompositeSubscription subscriptions = new CompositeSubscription();

        public AsyncOuterManager(AsyncOnSubscribe<S, T> asyncOnSubscribe, S s, UnicastSubject<Observable<T>> unicastSubject) {
            this.parent = asyncOnSubscribe;
            this.serializedSubscriber = new SerializedObserver<>(this);
            this.state = s;
            this.merger = unicastSubject;
        }

        public void unsubscribe() {
            if (IS_UNSUBSCRIBED.compareAndSet(this, 0, 1)) {
                synchronized (this) {
                    if (this.emitting) {
                        this.requests = new ArrayList();
                        this.requests.add(0L);
                        return;
                    }
                    this.emitting = true;
                    cleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setConcatProducer(Producer producer) {
            if (this.concatProducer == null) {
                this.concatProducer = producer;
                return;
            }
            throw new IllegalStateException("setConcatProducer may be called at most once!");
        }

        public boolean isUnsubscribed() {
            return this.isUnsubscribed != 0;
        }

        public void nextIteration(long j) {
            this.state = this.parent.next(this.state, j, this.serializedSubscriber);
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            this.subscriptions.unsubscribe();
            try {
                this.parent.onUnsubscribe(this.state);
            } catch (Throwable th) {
                handleThrownError(th);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0045, code lost:
            r5 = r5.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x004d, code lost:
            if (r5.hasNext() == false) goto L_0x0038;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x005d, code lost:
            if (tryEmit(r5.next().longValue()) == false) goto L_0x0049;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x005f, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void request(long r5) {
            /*
                r4 = this;
                r0 = 0
                int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r2 != 0) goto L_0x0007
                return
            L_0x0007:
                int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r2 < 0) goto L_0x0066
                monitor-enter(r4)
                boolean r0 = r4.emitting     // Catch:{ all -> 0x0063 }
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x0025
                java.util.List<java.lang.Long> r0 = r4.requests     // Catch:{ all -> 0x0063 }
                if (r0 != 0) goto L_0x001d
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0063 }
                r0.<init>()     // Catch:{ all -> 0x0063 }
                r4.requests = r0     // Catch:{ all -> 0x0063 }
            L_0x001d:
                java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0063 }
                r0.add(r3)     // Catch:{ all -> 0x0063 }
                goto L_0x0028
            L_0x0025:
                r4.emitting = r1     // Catch:{ all -> 0x0063 }
                r1 = 0
            L_0x0028:
                monitor-exit(r4)     // Catch:{ all -> 0x0063 }
                rx.Producer r0 = r4.concatProducer
                r0.request(r5)
                if (r1 == 0) goto L_0x0031
                return
            L_0x0031:
                boolean r5 = r4.tryEmit(r5)
                if (r5 == 0) goto L_0x0038
                return
            L_0x0038:
                monitor-enter(r4)
                java.util.List<java.lang.Long> r5 = r4.requests     // Catch:{ all -> 0x0060 }
                if (r5 != 0) goto L_0x0041
                r4.emitting = r2     // Catch:{ all -> 0x0060 }
                monitor-exit(r4)     // Catch:{ all -> 0x0060 }
                return
            L_0x0041:
                r6 = 0
                r4.requests = r6     // Catch:{ all -> 0x0060 }
                monitor-exit(r4)     // Catch:{ all -> 0x0060 }
                java.util.Iterator r5 = r5.iterator()
            L_0x0049:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x0038
                java.lang.Object r6 = r5.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r0 = r6.longValue()
                boolean r6 = r4.tryEmit(r0)
                if (r6 == 0) goto L_0x0049
                return
            L_0x0060:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0060 }
                throw r5
            L_0x0063:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0063 }
                throw r5
            L_0x0066:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Request can't be negative! "
                r1.append(r2)
                r1.append(r5)
                java.lang.String r5 = r1.toString()
                r0.<init>(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.observables.AsyncOnSubscribe.AsyncOuterManager.request(long):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
            if (tryEmit(r4) == false) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002f, code lost:
            monitor-enter(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r4 = r3.requests;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
            if (r4 != null) goto L_0x0039;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0034, code lost:
            r3.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0037, code lost:
            monitor-exit(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0038, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0039, code lost:
            r3.requests = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x003c, code lost:
            monitor-exit(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x003d, code lost:
            r4 = r4.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0045, code lost:
            if (r4.hasNext() == false) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0055, code lost:
            if (tryEmit(r4.next().longValue()) == false) goto L_0x0041;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0057, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void requestRemaining(long r4) {
            /*
                r3 = this;
                r0 = 0
                int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r2 != 0) goto L_0x0007
                return
            L_0x0007:
                int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r2 < 0) goto L_0x005e
                monitor-enter(r3)
                boolean r0 = r3.emitting     // Catch:{ all -> 0x005b }
                if (r0 == 0) goto L_0x0024
                java.util.List<java.lang.Long> r0 = r3.requests     // Catch:{ all -> 0x005b }
                if (r0 != 0) goto L_0x001b
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x005b }
                r0.<init>()     // Catch:{ all -> 0x005b }
                r3.requests = r0     // Catch:{ all -> 0x005b }
            L_0x001b:
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x005b }
                r0.add(r4)     // Catch:{ all -> 0x005b }
                monitor-exit(r3)     // Catch:{ all -> 0x005b }
                return
            L_0x0024:
                r0 = 1
                r3.emitting = r0     // Catch:{ all -> 0x005b }
                monitor-exit(r3)     // Catch:{ all -> 0x005b }
                boolean r4 = r3.tryEmit(r4)
                if (r4 == 0) goto L_0x002f
                return
            L_0x002f:
                monitor-enter(r3)
                java.util.List<java.lang.Long> r4 = r3.requests     // Catch:{ all -> 0x0058 }
                if (r4 != 0) goto L_0x0039
                r4 = 0
                r3.emitting = r4     // Catch:{ all -> 0x0058 }
                monitor-exit(r3)     // Catch:{ all -> 0x0058 }
                return
            L_0x0039:
                r5 = 0
                r3.requests = r5     // Catch:{ all -> 0x0058 }
                monitor-exit(r3)     // Catch:{ all -> 0x0058 }
                java.util.Iterator r4 = r4.iterator()
            L_0x0041:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x002f
                java.lang.Object r5 = r4.next()
                java.lang.Long r5 = (java.lang.Long) r5
                long r0 = r5.longValue()
                boolean r5 = r3.tryEmit(r0)
                if (r5 == 0) goto L_0x0041
                return
            L_0x0058:
                r4 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0058 }
                throw r4
            L_0x005b:
                r4 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x005b }
                throw r4
            L_0x005e:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Request can't be negative! "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.observables.AsyncOnSubscribe.AsyncOuterManager.requestRemaining(long):void");
        }

        /* access modifiers changed from: package-private */
        public boolean tryEmit(long j) {
            if (isUnsubscribed()) {
                cleanup();
                return true;
            }
            try {
                this.onNextCalled = false;
                this.expectedDelivery = j;
                nextIteration(j);
                if (!this.hasTerminated) {
                    if (!isUnsubscribed()) {
                        if (this.onNextCalled) {
                            return false;
                        }
                        handleThrownError(new IllegalStateException("No events emitted!"));
                        return true;
                    }
                }
                cleanup();
                return true;
            } catch (Throwable th) {
                handleThrownError(th);
                return true;
            }
        }

        private void handleThrownError(Throwable th) {
            if (this.hasTerminated) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.hasTerminated = true;
            this.merger.onError(th);
            cleanup();
        }

        public void onCompleted() {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                this.merger.onCompleted();
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        public void onError(Throwable th) {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                this.merger.onError(th);
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        public void onNext(Observable<? extends T> observable) {
            if (!this.onNextCalled) {
                this.onNextCalled = true;
                if (!this.hasTerminated) {
                    subscribeBufferToObservable(observable);
                    return;
                }
                return;
            }
            throw new IllegalStateException("onNext called multiple times!");
        }

        private void subscribeBufferToObservable(Observable<? extends T> observable) {
            final BufferUntilSubscriber create = BufferUntilSubscriber.create();
            final long j = this.expectedDelivery;
            final AnonymousClass1 r3 = new Subscriber<T>() {
                long remaining = j;

                public void onNext(T t) {
                    this.remaining--;
                    create.onNext(t);
                }

                public void onError(Throwable th) {
                    create.onError(th);
                }

                public void onCompleted() {
                    create.onCompleted();
                    long j = this.remaining;
                    if (j > 0) {
                        AsyncOuterManager.this.requestRemaining(j);
                    }
                }
            };
            this.subscriptions.add(r3);
            observable.doOnTerminate(new Action0() {
                public void call() {
                    AsyncOuterManager.this.subscriptions.remove(r3);
                }
            }).subscribe(r3);
            this.merger.onNext(create);
        }
    }

    static final class UnicastSubject<T> extends Observable<T> implements Observer<T> {
        private State<T> state;

        public static <T> UnicastSubject<T> create() {
            return new UnicastSubject<>(new State());
        }

        protected UnicastSubject(State<T> state2) {
            super(state2);
            this.state = state2;
        }

        public void onCompleted() {
            this.state.subscriber.onCompleted();
        }

        public void onError(Throwable th) {
            this.state.subscriber.onError(th);
        }

        public void onNext(T t) {
            this.state.subscriber.onNext(t);
        }

        static final class State<T> implements Observable.OnSubscribe<T> {
            /* access modifiers changed from: private */
            public Subscriber<? super T> subscriber;

            State() {
            }

            public void call(Subscriber<? super T> subscriber2) {
                synchronized (this) {
                    if (this.subscriber == null) {
                        this.subscriber = subscriber2;
                    } else {
                        subscriber2.onError(new IllegalStateException("There can be only one subscriber"));
                    }
                }
            }
        }
    }
}