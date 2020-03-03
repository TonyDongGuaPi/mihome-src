package com.amap.api.services.a;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public final class cw implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    static final Pattern f4383a = Pattern.compile("[a-z0-9_-]{1,120}");
    static ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), q);
    private static final ThreadFactory q = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f4384a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f4384a.getAndIncrement());
        }
    };
    /* access modifiers changed from: private */
    public static final OutputStream s = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };
    /* access modifiers changed from: private */
    public final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    /* access modifiers changed from: private */
    public final int i;
    private long j = 0;
    /* access modifiers changed from: private */
    public Writer k;
    private int l = 1000;
    private final LinkedHashMap<String, c> m = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int n;
    private cx o;
    private long p = 0;
    private final Callable<Void> r = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.amap.api.services.a.cw r0 = com.amap.api.services.a.cw.this
                monitor-enter(r0)
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.k     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0028 }
                r1.k()     // Catch:{ all -> 0x0028 }
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.i()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0028 }
                r1.h()     // Catch:{ all -> 0x0028 }
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0028 }
                r3 = 0
                int unused = r1.n = r3     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.AnonymousClass2.call():java.lang.Void");
        }
    };

    public void a(int i2) {
        if (i2 < 10) {
            i2 = 10;
        } else if (i2 > 10000) {
            i2 = 10000;
        }
        this.l = i2;
    }

    public static ThreadPoolExecutor a() {
        try {
            if (b == null || b.isShutdown()) {
                b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(256), q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b;
    }

    private cw(File file, int i2, int i3, long j2) {
        this.c = file;
        this.g = i2;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i3;
        this.h = j2;
    }

    public static cw a(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            cw cwVar = new cw(file, i2, i3, j2);
            if (cwVar.d.exists()) {
                try {
                    cwVar.f();
                    cwVar.g();
                    cwVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cwVar.d, true), cz.f4392a));
                    return cwVar;
                } catch (Throwable unused) {
                    cwVar.d();
                }
            }
            file.mkdirs();
            cw cwVar2 = new cw(file, i2, i3, j2);
            cwVar2.h();
            return cwVar2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.n = r1 - r8.m.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005d */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x006a=Splitter:B:20:0x006a, B:16:0x005d=Splitter:B:16:0x005d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f() throws java.io.IOException {
        /*
            r8 = this;
            com.amap.api.services.a.cy r0 = new com.amap.api.services.a.cy
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.io.File r2 = r8.d
            r1.<init>(r2)
            java.nio.charset.Charset r2 = com.amap.api.services.a.cz.f4392a
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x009f }
            java.lang.String r2 = r0.a()     // Catch:{ all -> 0x009f }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x009f }
            java.lang.String r4 = r0.a()     // Catch:{ all -> 0x009f }
            java.lang.String r5 = r0.a()     // Catch:{ all -> 0x009f }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x009f }
            if (r6 == 0) goto L_0x006a
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x009f }
            if (r6 == 0) goto L_0x006a
            int r6 = r8.g     // Catch:{ all -> 0x009f }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x009f }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x009f }
            if (r3 == 0) goto L_0x006a
            int r3 = r8.i     // Catch:{ all -> 0x009f }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x009f }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x009f }
            if (r3 == 0) goto L_0x006a
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x009f }
            if (r3 == 0) goto L_0x006a
            r1 = 0
        L_0x0053:
            java.lang.String r2 = r0.a()     // Catch:{ EOFException -> 0x005d }
            r8.d((java.lang.String) r2)     // Catch:{ EOFException -> 0x005d }
            int r1 = r1 + 1
            goto L_0x0053
        L_0x005d:
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r2 = r8.m     // Catch:{ all -> 0x009f }
            int r2 = r2.size()     // Catch:{ all -> 0x009f }
            int r1 = r1 - r2
            r8.n = r1     // Catch:{ all -> 0x009f }
            com.amap.api.services.a.cz.a((java.io.Closeable) r0)
            return
        L_0x006a:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x009f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009f }
            r6.<init>()     // Catch:{ all -> 0x009f }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x009f }
            r6.append(r1)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009f }
            r6.append(r2)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009f }
            r6.append(r4)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009f }
            r6.append(r5)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x009f }
            r3.<init>(r1)     // Catch:{ all -> 0x009f }
            throw r3     // Catch:{ all -> 0x009f }
        L_0x009f:
            r1 = move-exception
            com.amap.api.services.a.cz.a((java.io.Closeable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.f():void");
    }

    private void d(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                    this.m.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            c cVar = this.m.get(str2);
            if (cVar == null) {
                cVar = new c(str2);
                this.m.put(str2, cVar);
            }
            if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                boolean unused = cVar.d = true;
                a unused2 = cVar.e = null;
                cVar.a(split);
            } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
                a unused3 = cVar.e = new a(cVar);
            } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void g() throws IOException {
        a(this.e);
        Iterator<c> it = this.m.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i2 = 0;
            if (next.e == null) {
                while (i2 < this.i) {
                    this.j += next.c[i2];
                    i2++;
                }
            } else {
                a unused = next.e = null;
                while (i2 < this.i) {
                    a(next.a(i2));
                    a(next.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void h() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), cz.f4392a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (c next : this.m.values()) {
                if (next.e != null) {
                    bufferedWriter.write("DIRTY " + next.b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + next.b + next.a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), cz.f4392a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r11.n++;
        r11.k.append("READ " + r12 + 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        if (i() == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        a().submit(r11.r);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0076, code lost:
        return new com.amap.api.services.a.cw.b(r11, r12, com.amap.api.services.a.cw.c.e(r0), r8, com.amap.api.services.a.cw.c.b(r0), (com.amap.api.services.a.cw.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0077 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.amap.api.services.a.cw.b a(java.lang.String r12) throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            r11.j()     // Catch:{ all -> 0x0089 }
            r11.e((java.lang.String) r12)     // Catch:{ all -> 0x0089 }
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r0 = r11.m     // Catch:{ all -> 0x0089 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ all -> 0x0089 }
            com.amap.api.services.a.cw$c r0 = (com.amap.api.services.a.cw.c) r0     // Catch:{ all -> 0x0089 }
            r1 = 0
            if (r0 != 0) goto L_0x0014
            monitor-exit(r11)
            return r1
        L_0x0014:
            boolean r2 = r0.d     // Catch:{ all -> 0x0089 }
            if (r2 != 0) goto L_0x001c
            monitor-exit(r11)
            return r1
        L_0x001c:
            int r2 = r11.i     // Catch:{ all -> 0x0089 }
            java.io.InputStream[] r8 = new java.io.InputStream[r2]     // Catch:{ all -> 0x0089 }
            r2 = 0
            r3 = 0
        L_0x0022:
            int r4 = r11.i     // Catch:{ FileNotFoundException -> 0x0077 }
            if (r3 >= r4) goto L_0x0034
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0077 }
            java.io.File r5 = r0.a((int) r3)     // Catch:{ FileNotFoundException -> 0x0077 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0077 }
            r8[r3] = r4     // Catch:{ FileNotFoundException -> 0x0077 }
            int r3 = r3 + 1
            goto L_0x0022
        L_0x0034:
            int r1 = r11.n     // Catch:{ all -> 0x0089 }
            int r1 = r1 + 1
            r11.n = r1     // Catch:{ all -> 0x0089 }
            java.io.Writer r1 = r11.k     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r2.<init>()     // Catch:{ all -> 0x0089 }
            java.lang.String r3 = "READ "
            r2.append(r3)     // Catch:{ all -> 0x0089 }
            r2.append(r12)     // Catch:{ all -> 0x0089 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0089 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0089 }
            r1.append(r2)     // Catch:{ all -> 0x0089 }
            boolean r1 = r11.i()     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0064
            java.util.concurrent.ThreadPoolExecutor r1 = a()     // Catch:{ all -> 0x0089 }
            java.util.concurrent.Callable<java.lang.Void> r2 = r11.r     // Catch:{ all -> 0x0089 }
            r1.submit(r2)     // Catch:{ all -> 0x0089 }
        L_0x0064:
            com.amap.api.services.a.cw$b r1 = new com.amap.api.services.a.cw$b     // Catch:{ all -> 0x0089 }
            long r6 = r0.f     // Catch:{ all -> 0x0089 }
            long[] r9 = r0.c     // Catch:{ all -> 0x0089 }
            r10 = 0
            r3 = r1
            r4 = r11
            r5 = r12
            r3.<init>(r5, r6, r8, r9)     // Catch:{ all -> 0x0089 }
            monitor-exit(r11)
            return r1
        L_0x0077:
            int r12 = r11.i     // Catch:{ all -> 0x0089 }
            if (r2 >= r12) goto L_0x0087
            r12 = r8[r2]     // Catch:{ all -> 0x0089 }
            if (r12 == 0) goto L_0x0087
            r12 = r8[r2]     // Catch:{ all -> 0x0089 }
            com.amap.api.services.a.cz.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0089 }
            int r2 = r2 + 1
            goto L_0x0077
        L_0x0087:
            monitor-exit(r11)
            return r1
        L_0x0089:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a(java.lang.String):com.amap.api.services.a.cw$b");
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.amap.api.services.a.cw.a a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.j()     // Catch:{ all -> 0x0061 }
            r5.e((java.lang.String) r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r0 = r5.m     // Catch:{ all -> 0x0061 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0061 }
            com.amap.api.services.a.cw$c r0 = (com.amap.api.services.a.cw.c) r0     // Catch:{ all -> 0x0061 }
            r1 = -1
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r1 = 0
            if (r3 == 0) goto L_0x0022
            if (r0 == 0) goto L_0x0020
            long r2 = r0.f     // Catch:{ all -> 0x0061 }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
        L_0x0020:
            monitor-exit(r5)
            return r1
        L_0x0022:
            if (r0 != 0) goto L_0x002f
            com.amap.api.services.a.cw$c r0 = new com.amap.api.services.a.cw$c     // Catch:{ all -> 0x0061 }
            r0.<init>(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r7 = r5.m     // Catch:{ all -> 0x0061 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0037
        L_0x002f:
            com.amap.api.services.a.cw$a r7 = r0.e     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r1
        L_0x0037:
            com.amap.api.services.a.cw$a r7 = new com.amap.api.services.a.cw$a     // Catch:{ all -> 0x0061 }
            r7.<init>(r0)     // Catch:{ all -> 0x0061 }
            com.amap.api.services.a.cw.a unused = r0.e = r7     // Catch:{ all -> 0x0061 }
            java.io.Writer r8 = r5.k     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0061 }
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0061 }
            r8.write(r6)     // Catch:{ all -> 0x0061 }
            java.io.Writer r6 = r5.k     // Catch:{ all -> 0x0061 }
            r6.flush()     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return r7
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a(java.lang.String, long):com.amap.api.services.a.cw$a");
    }

    public File b() {
        return this.c;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.amap.api.services.a.cw.a r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.amap.api.services.a.cw$c r0 = r10.b     // Catch:{ all -> 0x0113 }
            com.amap.api.services.a.cw$a r1 = r0.e     // Catch:{ all -> 0x0113 }
            if (r1 != r10) goto L_0x010d
            r1 = 0
            if (r11 == 0) goto L_0x004d
            boolean r2 = r0.d     // Catch:{ all -> 0x0113 }
            if (r2 != 0) goto L_0x004d
            r2 = 0
        L_0x0015:
            int r3 = r9.i     // Catch:{ all -> 0x0113 }
            if (r2 >= r3) goto L_0x004d
            boolean[] r3 = r10.c     // Catch:{ all -> 0x0113 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0113 }
            if (r3 == 0) goto L_0x0033
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x0113 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0113 }
            if (r3 != 0) goto L_0x0030
            r10.b()     // Catch:{ all -> 0x0113 }
            monitor-exit(r9)
            return
        L_0x0030:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0033:
            r10.b()     // Catch:{ all -> 0x0113 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r11.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x0113 }
            r11.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0113 }
            r10.<init>(r11)     // Catch:{ all -> 0x0113 }
            throw r10     // Catch:{ all -> 0x0113 }
        L_0x004d:
            int r10 = r9.i     // Catch:{ all -> 0x0113 }
            if (r1 >= r10) goto L_0x0082
            java.io.File r10 = r0.b((int) r1)     // Catch:{ all -> 0x0113 }
            if (r11 == 0) goto L_0x007c
            boolean r2 = r10.exists()     // Catch:{ all -> 0x0113 }
            if (r2 == 0) goto L_0x007f
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0113 }
            r10.renameTo(r2)     // Catch:{ all -> 0x0113 }
            long[] r10 = r0.c     // Catch:{ all -> 0x0113 }
            r3 = r10[r1]     // Catch:{ all -> 0x0113 }
            long r5 = r2.length()     // Catch:{ all -> 0x0113 }
            long[] r10 = r0.c     // Catch:{ all -> 0x0113 }
            r10[r1] = r5     // Catch:{ all -> 0x0113 }
            long r7 = r9.j     // Catch:{ all -> 0x0113 }
            r10 = 0
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.j = r7     // Catch:{ all -> 0x0113 }
            goto L_0x007f
        L_0x007c:
            a((java.io.File) r10)     // Catch:{ all -> 0x0113 }
        L_0x007f:
            int r1 = r1 + 1
            goto L_0x004d
        L_0x0082:
            int r10 = r9.n     // Catch:{ all -> 0x0113 }
            r1 = 1
            int r10 = r10 + r1
            r9.n = r10     // Catch:{ all -> 0x0113 }
            r10 = 0
            com.amap.api.services.a.cw.a unused = r0.e = r10     // Catch:{ all -> 0x0113 }
            boolean r10 = r0.d     // Catch:{ all -> 0x0113 }
            r10 = r10 | r11
            r2 = 10
            if (r10 == 0) goto L_0x00c9
            boolean unused = r0.d = r1     // Catch:{ all -> 0x0113 }
            java.io.Writer r10 = r9.k     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r1.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = "CLEAN "
            r1.append(r3)     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = r0.b     // Catch:{ all -> 0x0113 }
            r1.append(r3)     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x0113 }
            r1.append(r3)     // Catch:{ all -> 0x0113 }
            r1.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0113 }
            r10.write(r1)     // Catch:{ all -> 0x0113 }
            if (r11 == 0) goto L_0x00ef
            long r10 = r9.p     // Catch:{ all -> 0x0113 }
            r1 = 1
            long r1 = r1 + r10
            r9.p = r1     // Catch:{ all -> 0x0113 }
            long unused = r0.f = r10     // Catch:{ all -> 0x0113 }
            goto L_0x00ef
        L_0x00c9:
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r10 = r9.m     // Catch:{ all -> 0x0113 }
            java.lang.String r11 = r0.b     // Catch:{ all -> 0x0113 }
            r10.remove(r11)     // Catch:{ all -> 0x0113 }
            java.io.Writer r10 = r9.k     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r11.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = "REMOVE "
            r11.append(r1)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = r0.b     // Catch:{ all -> 0x0113 }
            r11.append(r0)     // Catch:{ all -> 0x0113 }
            r11.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0113 }
            r10.write(r11)     // Catch:{ all -> 0x0113 }
        L_0x00ef:
            java.io.Writer r10 = r9.k     // Catch:{ all -> 0x0113 }
            r10.flush()     // Catch:{ all -> 0x0113 }
            long r10 = r9.j     // Catch:{ all -> 0x0113 }
            long r0 = r9.h     // Catch:{ all -> 0x0113 }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0102
            boolean r10 = r9.i()     // Catch:{ all -> 0x0113 }
            if (r10 == 0) goto L_0x010b
        L_0x0102:
            java.util.concurrent.ThreadPoolExecutor r10 = a()     // Catch:{ all -> 0x0113 }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.r     // Catch:{ all -> 0x0113 }
            r10.submit(r11)     // Catch:{ all -> 0x0113 }
        L_0x010b:
            monitor-exit(r9)
            return
        L_0x010d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0113 }
            r10.<init>()     // Catch:{ all -> 0x0113 }
            throw r10     // Catch:{ all -> 0x0113 }
        L_0x0113:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a(com.amap.api.services.a.cw$a, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean i() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0093, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean c(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.j()     // Catch:{ all -> 0x0096 }
            r7.e((java.lang.String) r8)     // Catch:{ all -> 0x0096 }
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r0 = r7.m     // Catch:{ all -> 0x0096 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0096 }
            com.amap.api.services.a.cw$c r0 = (com.amap.api.services.a.cw.c) r0     // Catch:{ all -> 0x0096 }
            r1 = 0
            if (r0 == 0) goto L_0x0094
            com.amap.api.services.a.cw$a r2 = r0.e     // Catch:{ all -> 0x0096 }
            if (r2 == 0) goto L_0x001a
            goto L_0x0094
        L_0x001a:
            int r2 = r7.i     // Catch:{ all -> 0x0096 }
            if (r1 >= r2) goto L_0x005d
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0096 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0096 }
            if (r3 == 0) goto L_0x0046
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0096 }
            if (r3 == 0) goto L_0x002f
            goto L_0x0046
        L_0x002f:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0096 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0096 }
            r0.<init>()     // Catch:{ all -> 0x0096 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0096 }
            r0.append(r2)     // Catch:{ all -> 0x0096 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0096 }
            r8.<init>(r0)     // Catch:{ all -> 0x0096 }
            throw r8     // Catch:{ all -> 0x0096 }
        L_0x0046:
            long r2 = r7.j     // Catch:{ all -> 0x0096 }
            long[] r4 = r0.c     // Catch:{ all -> 0x0096 }
            r5 = r4[r1]     // Catch:{ all -> 0x0096 }
            r4 = 0
            long r2 = r2 - r5
            r7.j = r2     // Catch:{ all -> 0x0096 }
            long[] r2 = r0.c     // Catch:{ all -> 0x0096 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0096 }
            int r1 = r1 + 1
            goto L_0x001a
        L_0x005d:
            int r0 = r7.n     // Catch:{ all -> 0x0096 }
            r1 = 1
            int r0 = r0 + r1
            r7.n = r0     // Catch:{ all -> 0x0096 }
            java.io.Writer r0 = r7.k     // Catch:{ all -> 0x0096 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0096 }
            r2.<init>()     // Catch:{ all -> 0x0096 }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x0096 }
            r2.append(r8)     // Catch:{ all -> 0x0096 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0096 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0096 }
            r0.append(r2)     // Catch:{ all -> 0x0096 }
            java.util.LinkedHashMap<java.lang.String, com.amap.api.services.a.cw$c> r0 = r7.m     // Catch:{ all -> 0x0096 }
            r0.remove(r8)     // Catch:{ all -> 0x0096 }
            boolean r8 = r7.i()     // Catch:{ all -> 0x0096 }
            if (r8 == 0) goto L_0x0092
            java.util.concurrent.ThreadPoolExecutor r8 = a()     // Catch:{ all -> 0x0096 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.r     // Catch:{ all -> 0x0096 }
            r8.submit(r0)     // Catch:{ all -> 0x0096 }
        L_0x0092:
            monitor-exit(r7)
            return r1
        L_0x0094:
            monitor-exit(r7)
            return r1
        L_0x0096:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.c(java.lang.String):boolean");
    }

    private void j() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void c() throws IOException {
        j();
        k();
        this.k.flush();
    }

    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.b();
                }
            }
            k();
            this.k.close();
            this.k = null;
        }
    }

    /* access modifiers changed from: private */
    public void k() throws IOException {
        while (true) {
            if (this.j > this.h || this.m.size() > this.l) {
                String str = (String) this.m.entrySet().iterator().next().getKey();
                c(str);
                if (this.o != null) {
                    this.o.a(str);
                }
            } else {
                return;
            }
        }
    }

    public void d() throws IOException {
        close();
        cz.a(this.c);
    }

    private void e(String str) {
        if (!f4383a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public void close() {
            for (InputStream a2 : this.d) {
                cz.a((Closeable) a2);
            }
        }
    }

    public final class a {
        /* access modifiers changed from: private */
        public final c b;
        /* access modifiers changed from: private */
        public final boolean[] c;
        /* access modifiers changed from: private */
        public boolean d;
        private boolean e;

        private a(c cVar) {
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[cw.this.i];
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.OutputStream a(int r4) throws java.io.IOException {
            /*
                r3 = this;
                if (r4 < 0) goto L_0x0053
                com.amap.api.services.a.cw r0 = com.amap.api.services.a.cw.this
                int r0 = r0.i
                if (r4 >= r0) goto L_0x0053
                com.amap.api.services.a.cw r0 = com.amap.api.services.a.cw.this
                monitor-enter(r0)
                com.amap.api.services.a.cw$c r1 = r3.b     // Catch:{ all -> 0x0050 }
                com.amap.api.services.a.cw$a r1 = r1.e     // Catch:{ all -> 0x0050 }
                if (r1 != r3) goto L_0x004a
                com.amap.api.services.a.cw$c r1 = r3.b     // Catch:{ all -> 0x0050 }
                boolean r1 = r1.d     // Catch:{ all -> 0x0050 }
                if (r1 != 0) goto L_0x0022
                boolean[] r1 = r3.c     // Catch:{ all -> 0x0050 }
                r2 = 1
                r1[r4] = r2     // Catch:{ all -> 0x0050 }
            L_0x0022:
                com.amap.api.services.a.cw$c r1 = r3.b     // Catch:{ all -> 0x0050 }
                java.io.File r4 = r1.b((int) r4)     // Catch:{ all -> 0x0050 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x002e }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x002e }
                goto L_0x003c
            L_0x002e:
                com.amap.api.services.a.cw r1 = com.amap.api.services.a.cw.this     // Catch:{ all -> 0x0050 }
                java.io.File r1 = r1.c     // Catch:{ all -> 0x0050 }
                r1.mkdirs()     // Catch:{ all -> 0x0050 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0044 }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0044 }
            L_0x003c:
                com.amap.api.services.a.cw$a$a r4 = new com.amap.api.services.a.cw$a$a     // Catch:{ all -> 0x0050 }
                r2 = 0
                r4.<init>(r1)     // Catch:{ all -> 0x0050 }
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                return r4
            L_0x0044:
                java.io.OutputStream r4 = com.amap.api.services.a.cw.s     // Catch:{ all -> 0x0050 }
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                return r4
            L_0x004a:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0050 }
                r4.<init>()     // Catch:{ all -> 0x0050 }
                throw r4     // Catch:{ all -> 0x0050 }
            L_0x0050:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                throw r4
            L_0x0053:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Expected index "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = " to be greater than 0 and less than the maximum value count of "
                r1.append(r4)
                com.amap.api.services.a.cw r4 = com.amap.api.services.a.cw.this
                int r4 = r4.i
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cw.a.a(int):java.io.OutputStream");
        }

        public void a() throws IOException {
            if (this.d) {
                cw.this.a(this, false);
                cw.this.c(this.b.b);
            } else {
                cw.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            cw.this.a(this, false);
        }

        /* renamed from: com.amap.api.services.a.cw$a$a  reason: collision with other inner class name */
        private class C0035a extends FilterOutputStream {
            private C0035a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }
        }
    }

    private final class c {
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public final long[] c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public a e;
        /* access modifiers changed from: private */
        public long f;

        private c(String str) {
            this.b = str;
            this.c = new long[cw.this.i];
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.c) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length == cw.this.i) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.c[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        throw b(strArr);
                    }
                }
                return;
            }
            throw b(strArr);
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            File f2 = cw.this.c;
            return new File(f2, this.b + "." + i);
        }

        public File b(int i) {
            File f2 = cw.this.c;
            return new File(f2, this.b + "." + i + DefaultDiskStorage.FileType.TEMP);
        }
    }
}
