package rx.internal.producers;

import java.util.Iterator;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;

public final class ProducerObserverArbiter<T> implements Observer<T>, Producer {
    static final Producer NULL_PRODUCER = new Producer() {
        public void request(long j) {
        }
    };
    final Subscriber<? super T> child;
    Producer currentProducer;
    boolean emitting;
    volatile boolean hasError;
    Producer missedProducer;
    long missedRequested;
    Object missedTerminal;
    List<T> queue;
    long requested;

    public ProducerObserverArbiter(Subscriber<? super T> subscriber) {
        this.child = subscriber;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r4.child.onNext(r5);
        r0 = r4.requested;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r0 == Long.MAX_VALUE) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r4.requested = r0 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        emitLoop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0036, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(T r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.emitting     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0016
            java.util.List<T> r0 = r4.queue     // Catch:{ all -> 0x003a }
            if (r0 != 0) goto L_0x0011
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x003a }
            r1 = 4
            r0.<init>(r1)     // Catch:{ all -> 0x003a }
            r4.queue = r0     // Catch:{ all -> 0x003a }
        L_0x0011:
            r0.add(r5)     // Catch:{ all -> 0x003a }
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            return
        L_0x0016:
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            rx.Subscriber<? super T> r0 = r4.child     // Catch:{ all -> 0x0030 }
            r0.onNext(r5)     // Catch:{ all -> 0x0030 }
            long r0 = r4.requested     // Catch:{ all -> 0x0030 }
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x002c
            r2 = 1
            long r0 = r0 - r2
            r4.requested = r0     // Catch:{ all -> 0x0030 }
        L_0x002c:
            r4.emitLoop()     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r5 = move-exception
            monitor-enter(r4)
            r0 = 0
            r4.emitting = r0     // Catch:{ all -> 0x0037 }
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            throw r5
        L_0x0037:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            throw r5
        L_0x003a:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.producers.ProducerObserverArbiter.onNext(java.lang.Object):void");
    }

    public void onError(Throwable th) {
        boolean z;
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = th;
                z = false;
            } else {
                this.emitting = true;
                z = true;
            }
        }
        if (z) {
            this.child.onError(th);
        } else {
            this.hasError = true;
        }
    }

    public void onCompleted() {
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = true;
                return;
            }
            this.emitting = true;
            this.child.onCompleted();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r2 = r6.currentProducer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3 = r6.requested + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        if (r3 >= 0) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        r3 = Long.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        r6.requested = r3;
        emitLoop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        r2.request(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003c, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void request(long r7) {
        /*
            r6 = this;
            r0 = 0
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0043
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x000b
            return
        L_0x000b:
            monitor-enter(r6)
            boolean r2 = r6.emitting     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x0018
            long r0 = r6.missedRequested     // Catch:{ all -> 0x0040 }
            r2 = 0
            long r0 = r0 + r7
            r6.missedRequested = r0     // Catch:{ all -> 0x0040 }
            monitor-exit(r6)     // Catch:{ all -> 0x0040 }
            return
        L_0x0018:
            r2 = 1
            r6.emitting = r2     // Catch:{ all -> 0x0040 }
            monitor-exit(r6)     // Catch:{ all -> 0x0040 }
            rx.Producer r2 = r6.currentProducer
            long r3 = r6.requested     // Catch:{ all -> 0x0036 }
            r5 = 0
            long r3 = r3 + r7
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x002b
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x002b:
            r6.requested = r3     // Catch:{ all -> 0x0036 }
            r6.emitLoop()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0035
            r2.request(r7)
        L_0x0035:
            return
        L_0x0036:
            r7 = move-exception
            monitor-enter(r6)
            r8 = 0
            r6.emitting = r8     // Catch:{ all -> 0x003d }
            monitor-exit(r6)     // Catch:{ all -> 0x003d }
            throw r7
        L_0x003d:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003d }
            throw r7
        L_0x0040:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0040 }
            throw r7
        L_0x0043:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "n >= 0 required"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.producers.ProducerObserverArbiter.request(long):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        r5.currentProducer = r6;
        r0 = r5.requested;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        emitLoop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0019, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        if (r0 == 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        r6.request(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0025, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0026, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r5.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002b, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProducer(rx.Producer r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.emitting     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x000e
            if (r6 == 0) goto L_0x0008
            goto L_0x000a
        L_0x0008:
            rx.Producer r6 = NULL_PRODUCER     // Catch:{ all -> 0x002f }
        L_0x000a:
            r5.missedProducer = r6     // Catch:{ all -> 0x002f }
            monitor-exit(r5)     // Catch:{ all -> 0x002f }
            return
        L_0x000e:
            r0 = 1
            r5.emitting = r0     // Catch:{ all -> 0x002f }
            monitor-exit(r5)     // Catch:{ all -> 0x002f }
            r5.currentProducer = r6
            long r0 = r5.requested
            r5.emitLoop()     // Catch:{ all -> 0x0025 }
            if (r6 == 0) goto L_0x0024
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0024
            r6.request(r0)
        L_0x0024:
            return
        L_0x0025:
            r6 = move-exception
            monitor-enter(r5)
            r0 = 0
            r5.emitting = r0     // Catch:{ all -> 0x002c }
            monitor-exit(r5)     // Catch:{ all -> 0x002c }
            throw r6
        L_0x002c:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x002c }
            throw r6
        L_0x002f:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x002f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.producers.ProducerObserverArbiter.setProducer(rx.Producer):void");
    }

    /* access modifiers changed from: package-private */
    public void emitLoop() {
        long j;
        Producer producer;
        Object obj;
        List<T> list;
        boolean z;
        boolean z2;
        long j2;
        Subscriber<? super T> subscriber = this.child;
        Producer producer2 = null;
        long j3 = 0;
        while (true) {
            synchronized (this) {
                j = this.missedRequested;
                producer = this.missedProducer;
                obj = this.missedTerminal;
                list = this.queue;
                z = false;
                if (j == 0 && producer == null && list == null && obj == null) {
                    this.emitting = false;
                    z2 = true;
                } else {
                    this.missedRequested = 0;
                    this.missedProducer = null;
                    this.queue = null;
                    this.missedTerminal = null;
                    z2 = false;
                }
            }
            if (!z2) {
                if (list == null || list.isEmpty()) {
                    z = true;
                }
                if (obj != null) {
                    if (obj != Boolean.TRUE) {
                        subscriber.onError((Throwable) obj);
                        return;
                    } else if (z) {
                        subscriber.onCompleted();
                        return;
                    }
                }
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            j2 = ((long) list.size()) + 0;
                            break;
                        }
                        T next = it.next();
                        if (!subscriber.isUnsubscribed()) {
                            if (this.hasError) {
                                break;
                            }
                            try {
                                subscriber.onNext(next);
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber, next);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    j2 = 0;
                }
                long j4 = this.requested;
                if (j4 != Long.MAX_VALUE) {
                    if (j != 0) {
                        j4 += j;
                        if (j4 < 0) {
                            j4 = Long.MAX_VALUE;
                        }
                    }
                    if (!(j2 == 0 || j4 == Long.MAX_VALUE)) {
                        j4 -= j2;
                        if (j4 < 0) {
                            throw new IllegalStateException("More produced than requested");
                        }
                    }
                    this.requested = j4;
                }
                if (producer == null) {
                    producer = this.currentProducer;
                    if (producer != null && j != 0) {
                        j3 = BackpressureUtils.addCap(j3, j);
                    }
                } else if (producer == NULL_PRODUCER) {
                    this.currentProducer = null;
                } else {
                    this.currentProducer = producer;
                    if (j4 != 0) {
                        j3 = BackpressureUtils.addCap(j3, j4);
                    }
                }
                producer2 = producer;
            } else if (j3 != 0 && producer2 != null) {
                producer2.request(j3);
                return;
            } else {
                return;
            }
        }
        while (true) {
        }
    }
}
