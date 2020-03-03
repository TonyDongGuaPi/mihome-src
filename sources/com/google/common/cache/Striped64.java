package com.google.common.cache;

import java.util.Random;
import sun.misc.Unsafe;

abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;

    /* access modifiers changed from: package-private */
    public abstract long fn(long j, long j2);

    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;
        volatile long p0;
        volatile long p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;
        volatile long q0;
        volatile long q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        Cell(long j) {
            this.value = j;
        }

        /* access modifiers changed from: package-private */
        public final boolean cas(long j, long j2) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j, j2);
        }

        static {
            try {
                UNSAFE = Striped64.getUnsafe();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            Class<Striped64> cls = Striped64.class;
            baseOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    /* access modifiers changed from: package-private */
    public final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    /* access modifiers changed from: package-private */
    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0092, code lost:
        if (r1.cells != r9) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0094, code lost:
        r8 = new com.google.common.cache.Striped64.Cell[(r10 << 1)];
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0099, code lost:
        if (r11 >= r10) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009b, code lost:
        r8[r11] = r9[r11];
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a2, code lost:
        r1.cells = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0027 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void retryUpdate(long r18, int[] r20, boolean r21) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r4 = 1
            r5 = 0
            if (r20 != 0) goto L_0x0020
            java.lang.ThreadLocal<int[]> r0 = threadHashCode
            int[] r6 = new int[r4]
            r0.set(r6)
            java.util.Random r0 = rng
            int r0 = r0.nextInt()
            if (r0 != 0) goto L_0x0018
            r0 = 1
        L_0x0018:
            r6[r5] = r0
            r16 = r6
            r6 = r0
            r0 = r16
            goto L_0x0024
        L_0x0020:
            r6 = r20[r5]
            r0 = r20
        L_0x0024:
            r7 = r21
        L_0x0026:
            r8 = 0
        L_0x0027:
            com.google.common.cache.Striped64$Cell[] r9 = r1.cells
            if (r9 == 0) goto L_0x00b9
            int r10 = r9.length
            if (r10 <= 0) goto L_0x00b9
            int r11 = r10 + -1
            r11 = r11 & r6
            r11 = r9[r11]
            if (r11 != 0) goto L_0x0067
            int r9 = r1.busy
            if (r9 != 0) goto L_0x0065
            com.google.common.cache.Striped64$Cell r9 = new com.google.common.cache.Striped64$Cell
            r9.<init>(r2)
            int r10 = r1.busy
            if (r10 != 0) goto L_0x0065
            boolean r10 = r17.casBusy()
            if (r10 == 0) goto L_0x0065
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells     // Catch:{ all -> 0x0061 }
            if (r10 == 0) goto L_0x005a
            int r11 = r10.length     // Catch:{ all -> 0x0061 }
            if (r11 <= 0) goto L_0x005a
            int r11 = r11 + -1
            r11 = r11 & r6
            r12 = r10[r11]     // Catch:{ all -> 0x0061 }
            if (r12 != 0) goto L_0x005a
            r10[r11] = r9     // Catch:{ all -> 0x0061 }
            r9 = 1
            goto L_0x005b
        L_0x005a:
            r9 = 0
        L_0x005b:
            r1.busy = r5
            if (r9 == 0) goto L_0x0027
            goto L_0x00f1
        L_0x0061:
            r0 = move-exception
            r1.busy = r5
            throw r0
        L_0x0065:
            r8 = 0
            goto L_0x00ac
        L_0x0067:
            if (r7 != 0) goto L_0x006b
            r7 = 1
            goto L_0x00ac
        L_0x006b:
            long r12 = r11.value
            long r14 = r1.fn(r12, r2)
            boolean r11 = r11.cas(r12, r14)
            if (r11 == 0) goto L_0x0079
            goto L_0x00f1
        L_0x0079:
            int r11 = NCPU
            if (r10 >= r11) goto L_0x0065
            com.google.common.cache.Striped64$Cell[] r11 = r1.cells
            if (r11 == r9) goto L_0x0082
            goto L_0x0065
        L_0x0082:
            if (r8 != 0) goto L_0x0086
            r8 = 1
            goto L_0x00ac
        L_0x0086:
            int r11 = r1.busy
            if (r11 != 0) goto L_0x00ac
            boolean r11 = r17.casBusy()
            if (r11 == 0) goto L_0x00ac
            com.google.common.cache.Striped64$Cell[] r8 = r1.cells     // Catch:{ all -> 0x00a8 }
            if (r8 != r9) goto L_0x00a4
            int r8 = r10 << 1
            com.google.common.cache.Striped64$Cell[] r8 = new com.google.common.cache.Striped64.Cell[r8]     // Catch:{ all -> 0x00a8 }
            r11 = 0
        L_0x0099:
            if (r11 >= r10) goto L_0x00a2
            r12 = r9[r11]     // Catch:{ all -> 0x00a8 }
            r8[r11] = r12     // Catch:{ all -> 0x00a8 }
            int r11 = r11 + 1
            goto L_0x0099
        L_0x00a2:
            r1.cells = r8     // Catch:{ all -> 0x00a8 }
        L_0x00a4:
            r1.busy = r5
            goto L_0x0026
        L_0x00a8:
            r0 = move-exception
            r1.busy = r5
            throw r0
        L_0x00ac:
            int r9 = r6 << 13
            r6 = r6 ^ r9
            int r9 = r6 >>> 17
            r6 = r6 ^ r9
            int r9 = r6 << 5
            r6 = r6 ^ r9
            r0[r5] = r6
            goto L_0x0027
        L_0x00b9:
            int r10 = r1.busy
            if (r10 != 0) goto L_0x00e5
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells
            if (r10 != r9) goto L_0x00e5
            boolean r10 = r17.casBusy()
            if (r10 == 0) goto L_0x00e5
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells     // Catch:{ all -> 0x00e1 }
            if (r10 != r9) goto L_0x00db
            r9 = 2
            com.google.common.cache.Striped64$Cell[] r9 = new com.google.common.cache.Striped64.Cell[r9]     // Catch:{ all -> 0x00e1 }
            r10 = r6 & 1
            com.google.common.cache.Striped64$Cell r11 = new com.google.common.cache.Striped64$Cell     // Catch:{ all -> 0x00e1 }
            r11.<init>(r2)     // Catch:{ all -> 0x00e1 }
            r9[r10] = r11     // Catch:{ all -> 0x00e1 }
            r1.cells = r9     // Catch:{ all -> 0x00e1 }
            r9 = 1
            goto L_0x00dc
        L_0x00db:
            r9 = 0
        L_0x00dc:
            r1.busy = r5
            if (r9 == 0) goto L_0x0027
            goto L_0x00f1
        L_0x00e1:
            r0 = move-exception
            r1.busy = r5
            throw r0
        L_0x00e5:
            long r9 = r1.base
            long r11 = r1.fn(r9, r2)
            boolean r9 = r1.casBase(r9, r11)
            if (r9 == 0) goto L_0x0027
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.retryUpdate(long, int[], boolean):void");
    }

    /* access modifiers changed from: package-private */
    public final void internalReset(long j) {
        Cell[] cellArr = this.cells;
        this.base = j;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.cache.Striped64.AnonymousClass1());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static sun.misc.Unsafe getUnsafe() {
        /*
            sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
            return r0
        L_0x0005:
            com.google.common.cache.Striped64$1 r0 = new com.google.common.cache.Striped64$1     // Catch:{ PrivilegedActionException -> 0x0011 }
            r0.<init>()     // Catch:{ PrivilegedActionException -> 0x0011 }
            java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x0011 }
            sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.Throwable r0 = r0.getCause()
            java.lang.String r2 = "Could not initialize intrinsics"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.getUnsafe():sun.misc.Unsafe");
    }
}
