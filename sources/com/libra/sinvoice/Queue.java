package com.libra.sinvoice;

public class Queue implements IQueue {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6248a = "DataQueue";
    private static final int b = 1;
    private static final int c = 2;
    private final int d;
    private BufferData[] e;
    private int f = 1;
    private volatile int g;
    private volatile int h;
    private volatile int i;

    public Queue(int i2) {
        this.d = i2;
        if (this.d > 0) {
            this.e = new BufferData[this.d];
        }
        this.i = 0;
        this.g = 0;
        this.h = 0;
    }

    public synchronized void a() {
        if (2 == this.f) {
            this.f = 1;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            notifyAll();
        }
    }

    public synchronized void a(BufferData[] bufferDataArr) {
        if (1 == this.f) {
            this.f = 2;
            if (bufferDataArr != null) {
                this.i = bufferDataArr.length;
                if (this.i > this.d) {
                    this.i = this.d;
                }
                for (int i2 = 0; i2 < this.i; i2++) {
                    this.e[i2] = bufferDataArr[i2];
                }
            } else {
                this.i = 0;
            }
        }
    }

    public final int c() {
        return this.i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.libra.sinvoice.BufferData b() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 2
            int r1 = r4.f     // Catch:{ all -> 0x005c }
            r2 = 0
            if (r0 != r1) goto L_0x005a
            int r0 = r4.i     // Catch:{ all -> 0x005c }
            r1 = 1
            if (r0 > 0) goto L_0x001b
            r4.wait()     // Catch:{ InterruptedException -> 0x0015 }
            int r0 = r4.f     // Catch:{ all -> 0x005c }
            if (r1 != r0) goto L_0x001b
            monitor-exit(r4)
            return r2
        L_0x0015:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x005c }
            monitor-exit(r4)
            return r2
        L_0x001b:
            int r0 = r4.i     // Catch:{ all -> 0x005c }
            if (r0 <= 0) goto L_0x0042
            com.libra.sinvoice.BufferData[] r0 = r4.e     // Catch:{ all -> 0x005c }
            int r2 = r4.g     // Catch:{ all -> 0x005c }
            int r3 = r2 + 1
            r4.g = r3     // Catch:{ all -> 0x005c }
            r2 = r0[r2]     // Catch:{ all -> 0x005c }
            int r0 = r4.g     // Catch:{ all -> 0x005c }
            int r3 = r4.d     // Catch:{ all -> 0x005c }
            if (r0 < r3) goto L_0x0032
            r0 = 0
            r4.g = r0     // Catch:{ all -> 0x005c }
        L_0x0032:
            int r0 = r4.i     // Catch:{ all -> 0x005c }
            int r0 = r0 - r1
            r4.i = r0     // Catch:{ all -> 0x005c }
            int r0 = r4.i     // Catch:{ all -> 0x005c }
            int r0 = r0 + r1
            int r1 = r4.d     // Catch:{ all -> 0x005c }
            if (r0 != r1) goto L_0x005a
            r4.notify()     // Catch:{ all -> 0x005c }
            goto L_0x005a
        L_0x0042:
            java.lang.String r0 = "DataQueue"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            java.lang.String r3 = "getBuffer error mCount:"
            r1.append(r3)     // Catch:{ all -> 0x005c }
            int r3 = r4.i     // Catch:{ all -> 0x005c }
            r1.append(r3)     // Catch:{ all -> 0x005c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x005c }
            com.libra.sinvoice.LogHelper.c(r0, r1)     // Catch:{ all -> 0x005c }
        L_0x005a:
            monitor-exit(r4)
            return r2
        L_0x005c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.libra.sinvoice.Queue.b():com.libra.sinvoice.BufferData");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005d, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(com.libra.sinvoice.BufferData r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 2
            int r1 = r5.f     // Catch:{ all -> 0x005e }
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L_0x005c
            int r0 = r5.i     // Catch:{ all -> 0x005e }
            int r1 = r5.d     // Catch:{ all -> 0x005e }
            if (r0 != r1) goto L_0x001d
            r5.wait()     // Catch:{ InterruptedException -> 0x0017 }
            int r0 = r5.f     // Catch:{ all -> 0x005e }
            if (r3 != r0) goto L_0x001d
            monitor-exit(r5)
            return r2
        L_0x0017:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x005e }
            monitor-exit(r5)
            return r2
        L_0x001d:
            int r0 = r5.i     // Catch:{ all -> 0x005e }
            int r1 = r5.d     // Catch:{ all -> 0x005e }
            if (r0 >= r1) goto L_0x0044
            com.libra.sinvoice.BufferData[] r0 = r5.e     // Catch:{ all -> 0x005e }
            int r1 = r5.h     // Catch:{ all -> 0x005e }
            int r4 = r1 + 1
            r5.h = r4     // Catch:{ all -> 0x005e }
            r0[r1] = r6     // Catch:{ all -> 0x005e }
            int r6 = r5.h     // Catch:{ all -> 0x005e }
            int r0 = r5.d     // Catch:{ all -> 0x005e }
            if (r6 < r0) goto L_0x0035
            r5.h = r2     // Catch:{ all -> 0x005e }
        L_0x0035:
            int r6 = r5.i     // Catch:{ all -> 0x005e }
            int r6 = r6 + r3
            r5.i = r6     // Catch:{ all -> 0x005e }
            int r6 = r5.i     // Catch:{ all -> 0x005e }
            int r6 = r6 - r3
            if (r6 != 0) goto L_0x0042
            r5.notify()     // Catch:{ all -> 0x005e }
        L_0x0042:
            r2 = 1
            goto L_0x005c
        L_0x0044:
            java.lang.String r6 = "DataQueue"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
            r0.<init>()     // Catch:{ all -> 0x005e }
            java.lang.String r1 = "putBuffer error mCount:"
            r0.append(r1)     // Catch:{ all -> 0x005e }
            int r1 = r5.i     // Catch:{ all -> 0x005e }
            r0.append(r1)     // Catch:{ all -> 0x005e }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005e }
            com.libra.sinvoice.LogHelper.c(r6, r0)     // Catch:{ all -> 0x005e }
        L_0x005c:
            monitor-exit(r5)
            return r2
        L_0x005e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.libra.sinvoice.Queue.a(com.libra.sinvoice.BufferData):boolean");
    }
}
