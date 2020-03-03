package com.ximalaya.ting.android.sdkdownloader.util;

import android.text.TextUtils;
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;

public final class ProcessLock implements Closeable {
    private static final String f = "process_lock";
    private static final DoubleKeyValueMap<String, Integer, ProcessLock> g = new DoubleKeyValueMap<>();
    private static final DecimalFormat h = new DecimalFormat("0.##################");

    /* renamed from: a  reason: collision with root package name */
    private final String f2377a;
    private final FileLock b;
    private final File c;
    private final Closeable d;
    private final boolean e;

    static {
        IOUtil.a(XmDownloadManager.b().c().getDir(f, 0));
    }

    private ProcessLock(String str, File file, FileLock fileLock, Closeable closeable, boolean z) {
        this.f2377a = str;
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
        a(this.f2377a, this.b, this.c, this.d);
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
                    IOUtil.a((Closeable) fileLock.channel());
                    throw th;
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:46|47) */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        com.ximalaya.ting.android.sdkdownloader.util.IOUtil.a((java.io.Closeable) r13);
        com.ximalaya.ting.android.sdkdownloader.util.IOUtil.a((java.io.Closeable) r1);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x00cb */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.ximalaya.ting.android.sdkdownloader.util.ProcessLock a(java.lang.String r12, java.lang.String r13, boolean r14) {
        /*
            com.ximalaya.ting.android.sdkdownloader.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, com.ximalaya.ting.android.sdkdownloader.util.ProcessLock> r0 = g
            monitor-enter(r0)
            com.ximalaya.ting.android.sdkdownloader.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, com.ximalaya.ting.android.sdkdownloader.util.ProcessLock> r1 = g     // Catch:{ all -> 0x00d3 }
            java.util.concurrent.ConcurrentHashMap r1 = r1.a(r12)     // Catch:{ all -> 0x00d3 }
            r2 = 0
            if (r1 == 0) goto L_0x0046
            boolean r3 = r1.isEmpty()     // Catch:{ all -> 0x00d3 }
            if (r3 != 0) goto L_0x0046
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x00d3 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00d3 }
        L_0x001a:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00d3 }
            if (r3 == 0) goto L_0x0046
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00d3 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00d3 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00d3 }
            com.ximalaya.ting.android.sdkdownloader.util.ProcessLock r3 = (com.ximalaya.ting.android.sdkdownloader.util.ProcessLock) r3     // Catch:{ all -> 0x00d3 }
            if (r3 == 0) goto L_0x0042
            boolean r4 = r3.a()     // Catch:{ all -> 0x00d3 }
            if (r4 != 0) goto L_0x0038
            r1.remove()     // Catch:{ all -> 0x00d3 }
            goto L_0x001a
        L_0x0038:
            if (r14 == 0) goto L_0x003c
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r2
        L_0x003c:
            boolean r3 = r3.e     // Catch:{ all -> 0x00d3 }
            if (r3 == 0) goto L_0x001a
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r2
        L_0x0042:
            r1.remove()     // Catch:{ all -> 0x00d3 }
            goto L_0x001a
        L_0x0046:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x00c9 }
            com.ximalaya.ting.android.sdkdownloader.XmDownloadManager r1 = com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.b()     // Catch:{ Throwable -> 0x00c9 }
            android.app.Application r1 = r1.c()     // Catch:{ Throwable -> 0x00c9 }
            java.lang.String r3 = "process_lock"
            r4 = 0
            java.io.File r1 = r1.getDir(r3, r4)     // Catch:{ Throwable -> 0x00c9 }
            r5.<init>(r1, r13)     // Catch:{ Throwable -> 0x00c9 }
            boolean r13 = r5.exists()     // Catch:{ Throwable -> 0x00c9 }
            if (r13 != 0) goto L_0x0066
            boolean r13 = r5.createNewFile()     // Catch:{ Throwable -> 0x00c9 }
            if (r13 == 0) goto L_0x00d1
        L_0x0066:
            if (r14 == 0) goto L_0x0072
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00c9 }
            r13.<init>(r5, r4)     // Catch:{ Throwable -> 0x00c9 }
            java.nio.channels.FileChannel r1 = r13.getChannel()     // Catch:{ Throwable -> 0x00c9 }
            goto L_0x007b
        L_0x0072:
            java.io.FileInputStream r13 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00c9 }
            r13.<init>(r5)     // Catch:{ Throwable -> 0x00c9 }
            java.nio.channels.FileChannel r1 = r13.getChannel()     // Catch:{ Throwable -> 0x00c9 }
        L_0x007b:
            if (r1 == 0) goto L_0x00ae
            r7 = 0
            r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r11 = r14 ^ 1
            r6 = r1
            java.nio.channels.FileLock r9 = r6.tryLock(r7, r9, r11)     // Catch:{ Throwable -> 0x00cb }
            boolean r3 = a((java.nio.channels.FileLock) r9)     // Catch:{ Throwable -> 0x00cb }
            if (r3 == 0) goto L_0x00aa
            com.ximalaya.ting.android.sdkdownloader.util.ProcessLock r10 = new com.ximalaya.ting.android.sdkdownloader.util.ProcessLock     // Catch:{ Throwable -> 0x00cb }
            r3 = r10
            r4 = r12
            r6 = r9
            r7 = r13
            r8 = r14
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x00cb }
            com.ximalaya.ting.android.sdkdownloader.util.DoubleKeyValueMap<java.lang.String, java.lang.Integer, com.ximalaya.ting.android.sdkdownloader.util.ProcessLock> r14 = g     // Catch:{ Throwable -> 0x00cb }
            int r3 = r9.hashCode()     // Catch:{ Throwable -> 0x00cb }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x00cb }
            r14.a(r12, r3, r10)     // Catch:{ Throwable -> 0x00cb }
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r10
        L_0x00aa:
            a(r12, r9, r5, r13)     // Catch:{ Throwable -> 0x00cb }
            goto L_0x00d1
        L_0x00ae:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ Throwable -> 0x00cb }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cb }
            r14.<init>()     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r3 = "can not get file channel:"
            r14.append(r3)     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r3 = r5.getAbsolutePath()     // Catch:{ Throwable -> 0x00cb }
            r14.append(r3)     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r14 = r14.toString()     // Catch:{ Throwable -> 0x00cb }
            r12.<init>(r14)     // Catch:{ Throwable -> 0x00cb }
            throw r12     // Catch:{ Throwable -> 0x00cb }
        L_0x00c9:
            r13 = r2
            r1 = r13
        L_0x00cb:
            com.ximalaya.ting.android.sdkdownloader.util.IOUtil.a((java.io.Closeable) r13)     // Catch:{ all -> 0x00d3 }
            com.ximalaya.ting.android.sdkdownloader.util.IOUtil.a((java.io.Closeable) r1)     // Catch:{ all -> 0x00d3 }
        L_0x00d1:
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r2
        L_0x00d3:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.util.ProcessLock.a(java.lang.String, java.lang.String, boolean):com.ximalaya.ting.android.sdkdownloader.util.ProcessLock");
    }

    public String toString() {
        return this.f2377a + ": " + this.c.getName();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        b();
    }
}
