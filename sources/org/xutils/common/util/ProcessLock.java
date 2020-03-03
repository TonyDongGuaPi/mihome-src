package org.xutils.common.util;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.x;

public final class ProcessLock implements Closeable {
    private static final String f = "process_lock";
    private static final DoubleKeyValueMap<String, Integer, ProcessLock> g = new DoubleKeyValueMap<>();
    private static final DecimalFormat h = new DecimalFormat("0.##################");

    /* renamed from: a  reason: collision with root package name */
    private final String f4236a;
    private final FileLock b;
    private final File c;
    private final Closeable d;
    private final boolean e;

    static {
        IOUtil.a(x.b().getDir(f, 0));
    }

    private ProcessLock(String str, File file, FileLock fileLock, Closeable closeable, boolean z) {
        this.f4236a = str;
        this.b = fileLock;
        this.c = file;
        this.d = closeable;
        this.e = z;
    }

    public static ProcessLock a(String str, boolean z) {
        return a(str, a(str), z);
    }

    public static ProcessLock a(String str, boolean z, long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + j;
        String a2 = a(str);
        ProcessLock processLock = null;
        while (System.currentTimeMillis() < currentTimeMillis && (processLock = a(str, a2, z)) == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e2) {
                throw e2;
            } catch (Throwable unused) {
            }
        }
        return processLock;
    }

    public boolean a() {
        return a(this.b);
    }

    public void b() {
        a(this.f4236a, this.b, this.c, this.d);
    }

    public void close() throws IOException {
        b();
    }

    private static boolean a(FileLock fileLock) {
        return fileLock != null && fileLock.isValid();
    }

    private static void a(String str, FileLock fileLock, File file, Closeable closeable) {
        FileChannel channel;
        synchronized (g) {
            if (fileLock != null) {
                try {
                    g.c(str, Integer.valueOf(fileLock.hashCode()));
                    ConcurrentHashMap<Integer, ProcessLock> a2 = g.a(str);
                    if (a2 == null || a2.isEmpty()) {
                        IOUtil.a(file);
                    }
                    if (fileLock.channel().isOpen()) {
                        fileLock.release();
                    }
                    channel = fileLock.channel();
                } catch (Throwable th) {
                    try {
                        LogUtil.b(th.getMessage(), th);
                        channel = fileLock.channel();
                    } catch (Throwable th2) {
                        IOUtil.a((Closeable) fileLock.channel());
                        throw th2;
                    }
                }
                IOUtil.a((Closeable) channel);
            }
            IOUtil.a(closeable);
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        double d2 = 0.0d;
        byte[] bytes = str.getBytes();
        for (int i = 0; i < str.length(); i++) {
            double d3 = (double) bytes[i];
            Double.isNaN(d3);
            d2 = ((d2 * 255.0d) + d3) * 0.005d;
        }
        return h.format(d2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.xutils.common.util.ProcessLock a(java.lang.String r12, java.lang.String r13, boolean r14) {
        /*
            org.xutils.common.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, org.xutils.common.util.ProcessLock> r0 = g
            monitor-enter(r0)
            org.xutils.common.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, org.xutils.common.util.ProcessLock> r1 = g     // Catch:{ all -> 0x00f3 }
            java.util.concurrent.ConcurrentHashMap r1 = r1.a(r12)     // Catch:{ all -> 0x00f3 }
            r2 = 0
            if (r1 == 0) goto L_0x0046
            boolean r3 = r1.isEmpty()     // Catch:{ all -> 0x00f3 }
            if (r3 != 0) goto L_0x0046
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x00f3 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00f3 }
        L_0x001a:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x0046
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00f3 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00f3 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00f3 }
            org.xutils.common.util.ProcessLock r3 = (org.xutils.common.util.ProcessLock) r3     // Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x0042
            boolean r4 = r3.a()     // Catch:{ all -> 0x00f3 }
            if (r4 != 0) goto L_0x0038
            r1.remove()     // Catch:{ all -> 0x00f3 }
            goto L_0x001a
        L_0x0038:
            if (r14 == 0) goto L_0x003c
            monitor-exit(r0)     // Catch:{ all -> 0x00f3 }
            return r2
        L_0x003c:
            boolean r3 = r3.e     // Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x001a
            monitor-exit(r0)     // Catch:{ all -> 0x00f3 }
            return r2
        L_0x0042:
            r1.remove()     // Catch:{ all -> 0x00f3 }
            goto L_0x001a
        L_0x0046:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x00c7 }
            android.app.Application r1 = org.xutils.x.b()     // Catch:{ Throwable -> 0x00c7 }
            java.lang.String r3 = "process_lock"
            r4 = 0
            java.io.File r1 = r1.getDir(r3, r4)     // Catch:{ Throwable -> 0x00c7 }
            r5.<init>(r1, r13)     // Catch:{ Throwable -> 0x00c7 }
            boolean r13 = r5.exists()     // Catch:{ Throwable -> 0x00c7 }
            if (r13 != 0) goto L_0x0062
            boolean r13 = r5.createNewFile()     // Catch:{ Throwable -> 0x00c7 }
            if (r13 == 0) goto L_0x00f1
        L_0x0062:
            if (r14 == 0) goto L_0x006e
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00c7 }
            r13.<init>(r5, r4)     // Catch:{ Throwable -> 0x00c7 }
            java.nio.channels.FileChannel r1 = r13.getChannel()     // Catch:{ Throwable -> 0x00c7 }
            goto L_0x0077
        L_0x006e:
            java.io.FileInputStream r13 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00c7 }
            r13.<init>(r5)     // Catch:{ Throwable -> 0x00c7 }
            java.nio.channels.FileChannel r1 = r13.getChannel()     // Catch:{ Throwable -> 0x00c7 }
        L_0x0077:
            if (r1 == 0) goto L_0x00aa
            r7 = 0
            r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r11 = r14 ^ 1
            r6 = r1
            java.nio.channels.FileLock r9 = r6.tryLock(r7, r9, r11)     // Catch:{ Throwable -> 0x00c5 }
            boolean r3 = a((java.nio.channels.FileLock) r9)     // Catch:{ Throwable -> 0x00c5 }
            if (r3 == 0) goto L_0x00a6
            org.xutils.common.util.ProcessLock r10 = new org.xutils.common.util.ProcessLock     // Catch:{ Throwable -> 0x00c5 }
            r3 = r10
            r4 = r12
            r6 = r9
            r7 = r13
            r8 = r14
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x00c5 }
            org.xutils.common.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, org.xutils.common.util.ProcessLock> r14 = g     // Catch:{ Throwable -> 0x00c5 }
            int r3 = r9.hashCode()     // Catch:{ Throwable -> 0x00c5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x00c5 }
            r14.a(r12, r3, r10)     // Catch:{ Throwable -> 0x00c5 }
            monitor-exit(r0)     // Catch:{ all -> 0x00f3 }
            return r10
        L_0x00a6:
            a(r12, r9, r5, r13)     // Catch:{ Throwable -> 0x00c5 }
            goto L_0x00f1
        L_0x00aa:
            java.io.IOException r14 = new java.io.IOException     // Catch:{ Throwable -> 0x00c5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c5 }
            r3.<init>()     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r4 = "can not get file channel:"
            r3.append(r4)     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r4 = r5.getAbsolutePath()     // Catch:{ Throwable -> 0x00c5 }
            r3.append(r4)     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00c5 }
            r14.<init>(r3)     // Catch:{ Throwable -> 0x00c5 }
            throw r14     // Catch:{ Throwable -> 0x00c5 }
        L_0x00c5:
            r14 = move-exception
            goto L_0x00cb
        L_0x00c7:
            r13 = move-exception
            r14 = r13
            r13 = r2
            r1 = r13
        L_0x00cb:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            r3.<init>()     // Catch:{ all -> 0x00f3 }
            java.lang.String r4 = "tryLock: "
            r3.append(r4)     // Catch:{ all -> 0x00f3 }
            r3.append(r12)     // Catch:{ all -> 0x00f3 }
            java.lang.String r12 = ", "
            r3.append(r12)     // Catch:{ all -> 0x00f3 }
            java.lang.String r12 = r14.getMessage()     // Catch:{ all -> 0x00f3 }
            r3.append(r12)     // Catch:{ all -> 0x00f3 }
            java.lang.String r12 = r3.toString()     // Catch:{ all -> 0x00f3 }
            org.xutils.common.util.LogUtil.a((java.lang.String) r12)     // Catch:{ all -> 0x00f3 }
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r13)     // Catch:{ all -> 0x00f3 }
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r1)     // Catch:{ all -> 0x00f3 }
        L_0x00f1:
            monitor-exit(r0)     // Catch:{ all -> 0x00f3 }
            return r2
        L_0x00f3:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00f3 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.common.util.ProcessLock.a(java.lang.String, java.lang.String, boolean):org.xutils.common.util.ProcessLock");
    }

    public String toString() {
        return this.f4236a + ": " + this.c.getName();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        b();
    }
}
