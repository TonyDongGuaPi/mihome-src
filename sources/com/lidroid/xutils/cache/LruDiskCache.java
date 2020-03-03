package com.lidroid.xutils.cache;

import com.facebook.cache.disk.DefaultDiskStorage;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class LruDiskCache implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    static final String f6305a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    private static final char h = 'C';
    private static final char i = 'U';
    private static final char j = 'D';
    private static final char k = 'R';
    private static final char l = 't';
    /* access modifiers changed from: private */
    public static final OutputStream z = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };
    private FileNameGenerator A = new MD5FileNameGenerator();
    final ThreadPoolExecutor g = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */
    public final File m;
    private final File n;
    private final File o;
    private final File p;
    private final int q;
    private long r;
    /* access modifiers changed from: private */
    public final int s;
    private long t = 0;
    /* access modifiers changed from: private */
    public Writer u;
    private final LinkedHashMap<String, Entry> v = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int w;
    private long x = 0;
    private final Callable<Void> y = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.lidroid.xutils.cache.LruDiskCache r0 = com.lidroid.xutils.cache.LruDiskCache.this
                monitor-enter(r0)
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.u     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0028 }
                r1.n()     // Catch:{ all -> 0x0028 }
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.l()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0028 }
                r1.k()     // Catch:{ all -> 0x0028 }
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0028 }
                r3 = 0
                r1.w = r3     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.AnonymousClass1.call():java.lang.Void");
        }
    };

    private LruDiskCache(File file, int i2, int i3, long j2) {
        File file2 = file;
        this.m = file2;
        this.q = i2;
        this.n = new File(file2, f6305a);
        this.o = new File(file2, b);
        this.p = new File(file2, c);
        this.s = i3;
        this.r = j2;
    }

    public static LruDiskCache a(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, c);
            if (file2.exists()) {
                File file3 = new File(file, f6305a);
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            LruDiskCache lruDiskCache = new LruDiskCache(file, i2, i3, j2);
            if (lruDiskCache.n.exists()) {
                try {
                    lruDiskCache.i();
                    lruDiskCache.j();
                    lruDiskCache.u = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lruDiskCache.n, true), "US-ASCII"));
                    return lruDiskCache;
                } catch (Throwable th) {
                    LogUtils.b("DiskLruCache " + file + " is corrupt: " + th.getMessage() + ", removing", th);
                    lruDiskCache.f();
                }
            }
            if (!file.exists() && !file.mkdirs()) {
                return lruDiskCache;
            }
            LruDiskCache lruDiskCache2 = new LruDiskCache(file, i2, i3, j2);
            lruDiskCache2.k();
            return lruDiskCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|19|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r9.w = r0 - r9.v.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0065, code lost:
        com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x005c */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0069=Splitter:B:22:0x0069, B:18:0x005c=Splitter:B:18:0x005c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() throws java.io.IOException {
        /*
            r9 = this;
            r0 = 0
            com.lidroid.xutils.cache.LruDiskCache$StrictLineReader r1 = new com.lidroid.xutils.cache.LruDiskCache$StrictLineReader     // Catch:{ all -> 0x009d }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x009d }
            java.io.File r3 = r9.n     // Catch:{ all -> 0x009d }
            r2.<init>(r3)     // Catch:{ all -> 0x009d }
            r1.<init>(r9, r2)     // Catch:{ all -> 0x009d }
            java.lang.String r0 = r1.a()     // Catch:{ all -> 0x009b }
            java.lang.String r2 = r1.a()     // Catch:{ all -> 0x009b }
            java.lang.String r3 = r1.a()     // Catch:{ all -> 0x009b }
            java.lang.String r4 = r1.a()     // Catch:{ all -> 0x009b }
            java.lang.String r5 = r1.a()     // Catch:{ all -> 0x009b }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r0)     // Catch:{ all -> 0x009b }
            if (r6 == 0) goto L_0x0069
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x009b }
            if (r6 == 0) goto L_0x0069
            int r6 = r9.q     // Catch:{ all -> 0x009b }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x009b }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x009b }
            if (r3 == 0) goto L_0x0069
            int r3 = r9.s     // Catch:{ all -> 0x009b }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x009b }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x009b }
            if (r3 == 0) goto L_0x0069
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x009b }
            if (r3 == 0) goto L_0x0069
            r0 = 0
        L_0x0052:
            java.lang.String r2 = r1.a()     // Catch:{ EOFException -> 0x005c }
            r9.e((java.lang.String) r2)     // Catch:{ EOFException -> 0x005c }
            int r0 = r0 + 1
            goto L_0x0052
        L_0x005c:
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r2 = r9.v     // Catch:{ all -> 0x009b }
            int r2 = r2.size()     // Catch:{ all -> 0x009b }
            int r0 = r0 - r2
            r9.w = r0     // Catch:{ all -> 0x009b }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r1)
            return
        L_0x0069:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x009b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009b }
            java.lang.String r7 = "unexpected journal header: ["
            r6.<init>(r7)     // Catch:{ all -> 0x009b }
            r6.append(r0)     // Catch:{ all -> 0x009b }
            java.lang.String r0 = ", "
            r6.append(r0)     // Catch:{ all -> 0x009b }
            r6.append(r2)     // Catch:{ all -> 0x009b }
            java.lang.String r0 = ", "
            r6.append(r0)     // Catch:{ all -> 0x009b }
            r6.append(r4)     // Catch:{ all -> 0x009b }
            java.lang.String r0 = ", "
            r6.append(r0)     // Catch:{ all -> 0x009b }
            r6.append(r5)     // Catch:{ all -> 0x009b }
            java.lang.String r0 = "]"
            r6.append(r0)     // Catch:{ all -> 0x009b }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x009b }
            r3.<init>(r0)     // Catch:{ all -> 0x009b }
            throw r3     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            goto L_0x00a1
        L_0x009d:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x00a1:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.i():void");
    }

    private void e(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == 1) {
            char charAt = str.charAt(0);
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (charAt == 'D') {
                    this.v.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            Entry entry = this.v.get(str2);
            if (entry == null) {
                entry = new Entry(this, str2, (Entry) null);
                this.v.put(str2, entry);
            }
            if (charAt == 'C') {
                entry.e = true;
                entry.f = null;
                String[] split = str.substring(indexOf2 + 1).split(" ");
                if (split.length > 0) {
                    try {
                        if (split[0].charAt(0) == 't') {
                            entry.c = Long.valueOf(split[0].substring(1)).longValue();
                            entry.a(split, 1);
                            return;
                        }
                        entry.c = Long.MAX_VALUE;
                        entry.a(split, 0);
                    } catch (Throwable unused) {
                        throw new IOException("unexpected journal line: " + str);
                    }
                }
            } else if (charAt == 'R') {
            } else {
                if (charAt == 'U') {
                    entry.f = new Editor(this, entry, (Editor) null);
                    return;
                }
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void j() throws IOException {
        a(this.o);
        Iterator<Entry> it = this.v.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i2 = 0;
            if (next.f == null) {
                while (i2 < this.s) {
                    this.t += next.d[i2];
                    i2++;
                }
            } else {
                next.f = null;
                while (i2 < this.s) {
                    a(next.a(i2));
                    a(next.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void k() throws IOException {
        BufferedWriter bufferedWriter;
        Throwable th;
        if (this.u != null) {
            IOUtils.a((Closeable) this.u);
        }
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.o), "US-ASCII"));
            try {
                bufferedWriter.write(d);
                bufferedWriter.write("\n");
                bufferedWriter.write("1");
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.q));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.s));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (Entry next : this.v.values()) {
                    if (next.f != null) {
                        bufferedWriter.write("U " + next.b + 10);
                    } else {
                        bufferedWriter.write("C " + next.b + " " + 't' + next.c + next.a() + 10);
                    }
                }
                IOUtils.a((Closeable) bufferedWriter);
                if (this.n.exists()) {
                    a(this.n, this.p, true);
                }
                a(this.o, this.n, false);
                this.p.delete();
                this.u = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.n, true), "US-ASCII"));
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((Closeable) bufferedWriter);
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedWriter = null;
            th = th4;
            IOUtils.a((Closeable) bufferedWriter);
            throw th;
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z2) throws IOException {
        if (z2) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized long a(String str) throws IOException {
        String a2 = this.A.a(str);
        m();
        Entry entry = this.v.get(a2);
        if (entry == null) {
            return 0;
        }
        return entry.c;
    }

    public File a(String str, int i2) {
        String a2 = this.A.a(str);
        File file = this.m;
        File file2 = new File(file, String.valueOf(a2) + "." + i2);
        if (file2.exists()) {
            return file2;
        }
        try {
            d(str);
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    public Snapshot b(String str) throws IOException {
        return f(this.A.a(str));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:46|47|(2:64|49)(2:50|(2:52|53)(1:63))) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r13.w++;
        r13.u.append("R " + r14 + 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c1, code lost:
        if (l() == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c3, code lost:
        r13.g.submit(r13.y);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00dc, code lost:
        return new com.lidroid.xutils.cache.LruDiskCache.Snapshot(r13, r14, com.lidroid.xutils.cache.LruDiskCache.Entry.f(r0), r10, com.lidroid.xutils.cache.LruDiskCache.Entry.d(r0), (com.lidroid.xutils.cache.LruDiskCache.Snapshot) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ed, code lost:
        if (r3 >= r13.s) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f2, code lost:
        if (r10[r3] != null) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f4, code lost:
        com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r10[r3]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f9, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fd, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x00eb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.lidroid.xutils.cache.LruDiskCache.Snapshot f(java.lang.String r14) throws java.io.IOException {
        /*
            r13 = this;
            monitor-enter(r13)
            r13.m()     // Catch:{ all -> 0x00fe }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r0 = r13.v     // Catch:{ all -> 0x00fe }
            java.lang.Object r0 = r0.get(r14)     // Catch:{ all -> 0x00fe }
            com.lidroid.xutils.cache.LruDiskCache$Entry r0 = (com.lidroid.xutils.cache.LruDiskCache.Entry) r0     // Catch:{ all -> 0x00fe }
            r1 = 0
            if (r0 != 0) goto L_0x0011
            monitor-exit(r13)
            return r1
        L_0x0011:
            boolean r2 = r0.e     // Catch:{ all -> 0x00fe }
            if (r2 != 0) goto L_0x0019
            monitor-exit(r13)
            return r1
        L_0x0019:
            long r2 = r0.c     // Catch:{ all -> 0x00fe }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00fe }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r2 = 10
            r3 = 0
            if (r6 >= 0) goto L_0x0098
        L_0x0028:
            int r4 = r13.s     // Catch:{ all -> 0x00fe }
            if (r3 < r4) goto L_0x005c
            int r0 = r13.w     // Catch:{ all -> 0x00fe }
            int r0 = r0 + 1
            r13.w = r0     // Catch:{ all -> 0x00fe }
            java.io.Writer r0 = r13.u     // Catch:{ all -> 0x00fe }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            java.lang.String r4 = "D "
            r3.<init>(r4)     // Catch:{ all -> 0x00fe }
            r3.append(r14)     // Catch:{ all -> 0x00fe }
            r3.append(r2)     // Catch:{ all -> 0x00fe }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00fe }
            r0.append(r2)     // Catch:{ all -> 0x00fe }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r0 = r13.v     // Catch:{ all -> 0x00fe }
            r0.remove(r14)     // Catch:{ all -> 0x00fe }
            boolean r14 = r13.l()     // Catch:{ all -> 0x00fe }
            if (r14 == 0) goto L_0x005a
            java.util.concurrent.ThreadPoolExecutor r14 = r13.g     // Catch:{ all -> 0x00fe }
            java.util.concurrent.Callable<java.lang.Void> r0 = r13.y     // Catch:{ all -> 0x00fe }
            r14.submit(r0)     // Catch:{ all -> 0x00fe }
        L_0x005a:
            monitor-exit(r13)
            return r1
        L_0x005c:
            java.io.File r4 = r0.a((int) r3)     // Catch:{ all -> 0x00fe }
            boolean r5 = r4.exists()     // Catch:{ all -> 0x00fe }
            if (r5 == 0) goto L_0x0081
            boolean r5 = r4.delete()     // Catch:{ all -> 0x00fe }
            if (r5 == 0) goto L_0x006d
            goto L_0x0081
        L_0x006d:
            java.io.IOException r14 = new java.io.IOException     // Catch:{ all -> 0x00fe }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            java.lang.String r1 = "failed to delete "
            r0.<init>(r1)     // Catch:{ all -> 0x00fe }
            r0.append(r4)     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00fe }
            r14.<init>(r0)     // Catch:{ all -> 0x00fe }
            throw r14     // Catch:{ all -> 0x00fe }
        L_0x0081:
            long r4 = r13.t     // Catch:{ all -> 0x00fe }
            long[] r6 = r0.d     // Catch:{ all -> 0x00fe }
            r7 = r6[r3]     // Catch:{ all -> 0x00fe }
            r6 = 0
            long r4 = r4 - r7
            r13.t = r4     // Catch:{ all -> 0x00fe }
            long[] r4 = r0.d     // Catch:{ all -> 0x00fe }
            r5 = 0
            r4[r3] = r5     // Catch:{ all -> 0x00fe }
            int r3 = r3 + 1
            goto L_0x0028
        L_0x0098:
            int r4 = r13.s     // Catch:{ all -> 0x00fe }
            java.io.FileInputStream[] r10 = new java.io.FileInputStream[r4]     // Catch:{ all -> 0x00fe }
            r4 = 0
        L_0x009d:
            int r5 = r13.s     // Catch:{ FileNotFoundException -> 0x00eb }
            if (r4 < r5) goto L_0x00dd
            int r1 = r13.w     // Catch:{ all -> 0x00fe }
            int r1 = r1 + 1
            r13.w = r1     // Catch:{ all -> 0x00fe }
            java.io.Writer r1 = r13.u     // Catch:{ all -> 0x00fe }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            java.lang.String r4 = "R "
            r3.<init>(r4)     // Catch:{ all -> 0x00fe }
            r3.append(r14)     // Catch:{ all -> 0x00fe }
            r3.append(r2)     // Catch:{ all -> 0x00fe }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00fe }
            r1.append(r2)     // Catch:{ all -> 0x00fe }
            boolean r1 = r13.l()     // Catch:{ all -> 0x00fe }
            if (r1 == 0) goto L_0x00ca
            java.util.concurrent.ThreadPoolExecutor r1 = r13.g     // Catch:{ all -> 0x00fe }
            java.util.concurrent.Callable<java.lang.Void> r2 = r13.y     // Catch:{ all -> 0x00fe }
            r1.submit(r2)     // Catch:{ all -> 0x00fe }
        L_0x00ca:
            com.lidroid.xutils.cache.LruDiskCache$Snapshot r1 = new com.lidroid.xutils.cache.LruDiskCache$Snapshot     // Catch:{ all -> 0x00fe }
            long r8 = r0.g     // Catch:{ all -> 0x00fe }
            long[] r11 = r0.d     // Catch:{ all -> 0x00fe }
            r12 = 0
            r5 = r1
            r6 = r13
            r7 = r14
            r5.<init>(r6, r7, r8, r10, r11, r12)     // Catch:{ all -> 0x00fe }
            monitor-exit(r13)
            return r1
        L_0x00dd:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00eb }
            java.io.File r6 = r0.a((int) r4)     // Catch:{ FileNotFoundException -> 0x00eb }
            r5.<init>(r6)     // Catch:{ FileNotFoundException -> 0x00eb }
            r10[r4] = r5     // Catch:{ FileNotFoundException -> 0x00eb }
            int r4 = r4 + 1
            goto L_0x009d
        L_0x00eb:
            int r14 = r13.s     // Catch:{ all -> 0x00fe }
            if (r3 < r14) goto L_0x00f0
            goto L_0x00fc
        L_0x00f0:
            r14 = r10[r3]     // Catch:{ all -> 0x00fe }
            if (r14 == 0) goto L_0x00fc
            r14 = r10[r3]     // Catch:{ all -> 0x00fe }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r14)     // Catch:{ all -> 0x00fe }
            int r3 = r3 + 1
            goto L_0x00eb
        L_0x00fc:
            monitor-exit(r13)
            return r1
        L_0x00fe:
            r14 = move-exception
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.f(java.lang.String):com.lidroid.xutils.cache.LruDiskCache$Snapshot");
    }

    public Editor c(String str) throws IOException {
        return a(this.A.a(str), -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.lidroid.xutils.cache.LruDiskCache.Editor a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.m()     // Catch:{ all -> 0x005b }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r0 = r5.v     // Catch:{ all -> 0x005b }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x005b }
            com.lidroid.xutils.cache.LruDiskCache$Entry r0 = (com.lidroid.xutils.cache.LruDiskCache.Entry) r0     // Catch:{ all -> 0x005b }
            r1 = -1
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r1 = 0
            if (r3 == 0) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            long r2 = r0.g     // Catch:{ all -> 0x005b }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r5)
            return r1
        L_0x001f:
            if (r0 != 0) goto L_0x002c
            com.lidroid.xutils.cache.LruDiskCache$Entry r0 = new com.lidroid.xutils.cache.LruDiskCache$Entry     // Catch:{ all -> 0x005b }
            r0.<init>(r5, r6, r1)     // Catch:{ all -> 0x005b }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r7 = r5.v     // Catch:{ all -> 0x005b }
            r7.put(r6, r0)     // Catch:{ all -> 0x005b }
            goto L_0x0034
        L_0x002c:
            com.lidroid.xutils.cache.LruDiskCache$Editor r7 = r0.f     // Catch:{ all -> 0x005b }
            if (r7 == 0) goto L_0x0034
            monitor-exit(r5)
            return r1
        L_0x0034:
            com.lidroid.xutils.cache.LruDiskCache$Editor r7 = new com.lidroid.xutils.cache.LruDiskCache$Editor     // Catch:{ all -> 0x005b }
            r7.<init>(r5, r0, r1)     // Catch:{ all -> 0x005b }
            r0.f = r7     // Catch:{ all -> 0x005b }
            java.io.Writer r8 = r5.u     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "U "
            r0.<init>(r1)     // Catch:{ all -> 0x005b }
            r0.append(r6)     // Catch:{ all -> 0x005b }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x005b }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x005b }
            r8.write(r6)     // Catch:{ all -> 0x005b }
            java.io.Writer r6 = r5.u     // Catch:{ all -> 0x005b }
            r6.flush()     // Catch:{ all -> 0x005b }
            monitor-exit(r5)
            return r7
        L_0x005b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.a(java.lang.String, long):com.lidroid.xutils.cache.LruDiskCache$Editor");
    }

    public File a() {
        return this.m;
    }

    public synchronized long b() {
        return this.r;
    }

    public synchronized void a(long j2) {
        this.r = j2;
        this.g.submit(this.y);
    }

    public synchronized long c() {
        return this.t;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.lidroid.xutils.cache.LruDiskCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.lidroid.xutils.cache.LruDiskCache$Entry r0 = r10.b     // Catch:{ all -> 0x011b }
            com.lidroid.xutils.cache.LruDiskCache$Editor r1 = r0.f     // Catch:{ all -> 0x011b }
            if (r1 != r10) goto L_0x0115
            r1 = 0
            if (r11 == 0) goto L_0x004b
            boolean r2 = r0.e     // Catch:{ all -> 0x011b }
            if (r2 != 0) goto L_0x004b
            r2 = 0
        L_0x0015:
            int r3 = r9.s     // Catch:{ all -> 0x011b }
            if (r2 < r3) goto L_0x001a
            goto L_0x004b
        L_0x001a:
            boolean[] r3 = r10.c     // Catch:{ all -> 0x011b }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x011b }
            if (r3 == 0) goto L_0x0034
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x011b }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x011b }
            if (r3 != 0) goto L_0x0031
            r10.b()     // Catch:{ all -> 0x011b }
            monitor-exit(r9)
            return
        L_0x0031:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0034:
            r10.b()     // Catch:{ all -> 0x011b }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x011b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x011b }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.<init>(r0)     // Catch:{ all -> 0x011b }
            r11.append(r2)     // Catch:{ all -> 0x011b }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x011b }
            r10.<init>(r11)     // Catch:{ all -> 0x011b }
            throw r10     // Catch:{ all -> 0x011b }
        L_0x004b:
            int r10 = r9.s     // Catch:{ all -> 0x011b }
            if (r1 < r10) goto L_0x00e3
            int r10 = r9.w     // Catch:{ all -> 0x011b }
            r1 = 1
            int r10 = r10 + r1
            r9.w = r10     // Catch:{ all -> 0x011b }
            r10 = 0
            r0.f = r10     // Catch:{ all -> 0x011b }
            boolean r10 = r0.e     // Catch:{ all -> 0x011b }
            r10 = r10 | r11
            r2 = 10
            if (r10 == 0) goto L_0x00a4
            r0.e = r1     // Catch:{ all -> 0x011b }
            java.io.Writer r10 = r9.u     // Catch:{ all -> 0x011b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x011b }
            java.lang.String r3 = "C "
            r1.<init>(r3)     // Catch:{ all -> 0x011b }
            java.lang.String r3 = r0.b     // Catch:{ all -> 0x011b }
            r1.append(r3)     // Catch:{ all -> 0x011b }
            java.lang.String r3 = " "
            r1.append(r3)     // Catch:{ all -> 0x011b }
            r3 = 116(0x74, float:1.63E-43)
            r1.append(r3)     // Catch:{ all -> 0x011b }
            long r3 = r0.c     // Catch:{ all -> 0x011b }
            r1.append(r3)     // Catch:{ all -> 0x011b }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x011b }
            r1.append(r3)     // Catch:{ all -> 0x011b }
            r1.append(r2)     // Catch:{ all -> 0x011b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x011b }
            r10.write(r1)     // Catch:{ all -> 0x011b }
            if (r11 == 0) goto L_0x00c7
            long r10 = r9.x     // Catch:{ all -> 0x011b }
            r1 = 1
            long r1 = r1 + r10
            r9.x = r1     // Catch:{ all -> 0x011b }
            r0.g = r10     // Catch:{ all -> 0x011b }
            goto L_0x00c7
        L_0x00a4:
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r10 = r9.v     // Catch:{ all -> 0x011b }
            java.lang.String r11 = r0.b     // Catch:{ all -> 0x011b }
            r10.remove(r11)     // Catch:{ all -> 0x011b }
            java.io.Writer r10 = r9.u     // Catch:{ all -> 0x011b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x011b }
            java.lang.String r1 = "D "
            r11.<init>(r1)     // Catch:{ all -> 0x011b }
            java.lang.String r0 = r0.b     // Catch:{ all -> 0x011b }
            r11.append(r0)     // Catch:{ all -> 0x011b }
            r11.append(r2)     // Catch:{ all -> 0x011b }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x011b }
            r10.write(r11)     // Catch:{ all -> 0x011b }
        L_0x00c7:
            java.io.Writer r10 = r9.u     // Catch:{ all -> 0x011b }
            r10.flush()     // Catch:{ all -> 0x011b }
            long r10 = r9.t     // Catch:{ all -> 0x011b }
            long r0 = r9.r     // Catch:{ all -> 0x011b }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x00da
            boolean r10 = r9.l()     // Catch:{ all -> 0x011b }
            if (r10 == 0) goto L_0x00e1
        L_0x00da:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.g     // Catch:{ all -> 0x011b }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.y     // Catch:{ all -> 0x011b }
            r10.submit(r11)     // Catch:{ all -> 0x011b }
        L_0x00e1:
            monitor-exit(r9)
            return
        L_0x00e3:
            java.io.File r10 = r0.b((int) r1)     // Catch:{ all -> 0x011b }
            if (r11 == 0) goto L_0x010e
            boolean r2 = r10.exists()     // Catch:{ all -> 0x011b }
            if (r2 == 0) goto L_0x0111
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x011b }
            r10.renameTo(r2)     // Catch:{ all -> 0x011b }
            long[] r10 = r0.d     // Catch:{ all -> 0x011b }
            r3 = r10[r1]     // Catch:{ all -> 0x011b }
            long r5 = r2.length()     // Catch:{ all -> 0x011b }
            long[] r10 = r0.d     // Catch:{ all -> 0x011b }
            r10[r1] = r5     // Catch:{ all -> 0x011b }
            long r7 = r9.t     // Catch:{ all -> 0x011b }
            r10 = 0
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.t = r7     // Catch:{ all -> 0x011b }
            goto L_0x0111
        L_0x010e:
            a((java.io.File) r10)     // Catch:{ all -> 0x011b }
        L_0x0111:
            int r1 = r1 + 1
            goto L_0x004b
        L_0x0115:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x011b }
            r10.<init>()     // Catch:{ all -> 0x011b }
            throw r10     // Catch:{ all -> 0x011b }
        L_0x011b:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.a(com.lidroid.xutils.cache.LruDiskCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean l() {
        return this.w >= 2000 && this.w >= this.v.size();
    }

    public boolean d(String str) throws IOException {
        return g(this.A.a(str));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0089, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean g(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.m()     // Catch:{ all -> 0x008a }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r0 = r7.v     // Catch:{ all -> 0x008a }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x008a }
            com.lidroid.xutils.cache.LruDiskCache$Entry r0 = (com.lidroid.xutils.cache.LruDiskCache.Entry) r0     // Catch:{ all -> 0x008a }
            r1 = 0
            if (r0 == 0) goto L_0x0088
            com.lidroid.xutils.cache.LruDiskCache$Editor r2 = r0.f     // Catch:{ all -> 0x008a }
            if (r2 == 0) goto L_0x0016
            goto L_0x0088
        L_0x0016:
            int r2 = r7.s     // Catch:{ all -> 0x008a }
            if (r1 < r2) goto L_0x004c
            int r0 = r7.w     // Catch:{ all -> 0x008a }
            r1 = 1
            int r0 = r0 + r1
            r7.w = r0     // Catch:{ all -> 0x008a }
            java.io.Writer r0 = r7.u     // Catch:{ all -> 0x008a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            java.lang.String r3 = "D "
            r2.<init>(r3)     // Catch:{ all -> 0x008a }
            r2.append(r8)     // Catch:{ all -> 0x008a }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x008a }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008a }
            r0.append(r2)     // Catch:{ all -> 0x008a }
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r0 = r7.v     // Catch:{ all -> 0x008a }
            r0.remove(r8)     // Catch:{ all -> 0x008a }
            boolean r8 = r7.l()     // Catch:{ all -> 0x008a }
            if (r8 == 0) goto L_0x004a
            java.util.concurrent.ThreadPoolExecutor r8 = r7.g     // Catch:{ all -> 0x008a }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.y     // Catch:{ all -> 0x008a }
            r8.submit(r0)     // Catch:{ all -> 0x008a }
        L_0x004a:
            monitor-exit(r7)
            return r1
        L_0x004c:
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x008a }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x008a }
            if (r3 == 0) goto L_0x0071
            boolean r3 = r2.delete()     // Catch:{ all -> 0x008a }
            if (r3 == 0) goto L_0x005d
            goto L_0x0071
        L_0x005d:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x008a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            java.lang.String r1 = "failed to delete "
            r0.<init>(r1)     // Catch:{ all -> 0x008a }
            r0.append(r2)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008a }
            r8.<init>(r0)     // Catch:{ all -> 0x008a }
            throw r8     // Catch:{ all -> 0x008a }
        L_0x0071:
            long r2 = r7.t     // Catch:{ all -> 0x008a }
            long[] r4 = r0.d     // Catch:{ all -> 0x008a }
            r5 = r4[r1]     // Catch:{ all -> 0x008a }
            r4 = 0
            long r2 = r2 - r5
            r7.t = r2     // Catch:{ all -> 0x008a }
            long[] r2 = r0.d     // Catch:{ all -> 0x008a }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x008a }
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0088:
            monitor-exit(r7)
            return r1
        L_0x008a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.g(java.lang.String):boolean");
    }

    public synchronized boolean d() {
        return this.u == null;
    }

    private void m() {
        if (this.u == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void e() throws IOException {
        m();
        n();
        this.u.flush();
    }

    public synchronized void close() throws IOException {
        if (this.u != null) {
            Iterator it = new ArrayList(this.v.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.f != null) {
                    entry.f.b();
                }
            }
            n();
            this.u.close();
            this.u = null;
        }
    }

    /* access modifiers changed from: private */
    public void n() throws IOException {
        while (this.t > this.r) {
            g((String) this.v.entrySet().iterator().next().getKey());
        }
    }

    public void f() throws IOException {
        IOUtils.a((Closeable) this);
        b(this.m);
    }

    /* access modifiers changed from: private */
    public static String b(InputStream inputStream) throws IOException {
        return a((Reader) new InputStreamReader(inputStream, "UTF-8"));
    }

    public final class Snapshot implements Closeable {
        private final String b;
        private final long c;
        private final FileInputStream[] d;
        private final long[] e;

        private Snapshot(String str, long j, FileInputStream[] fileInputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = fileInputStreamArr;
            this.e = jArr;
        }

        /* synthetic */ Snapshot(LruDiskCache lruDiskCache, String str, long j, FileInputStream[] fileInputStreamArr, long[] jArr, Snapshot snapshot) {
            this(str, j, fileInputStreamArr, jArr);
        }

        public Editor a() throws IOException {
            return LruDiskCache.this.a(this.b, this.c);
        }

        public FileInputStream a(int i) {
            return this.d[i];
        }

        public String b(int i) throws IOException {
            return LruDiskCache.b((InputStream) a(i));
        }

        public long c(int i) {
            return this.e[i];
        }

        public void close() {
            for (FileInputStream a2 : this.d) {
                IOUtils.a((Closeable) a2);
            }
        }
    }

    public final class Editor {
        /* access modifiers changed from: private */
        public final Entry b;
        /* access modifiers changed from: private */
        public final boolean[] c;
        /* access modifiers changed from: private */
        public boolean d;
        private boolean e;

        private Editor(Entry entry) {
            this.b = entry;
            this.c = entry.e ? null : new boolean[LruDiskCache.this.s];
        }

        /* synthetic */ Editor(LruDiskCache lruDiskCache, Entry entry, Editor editor) {
            this(entry);
        }

        public void a(long j) {
            this.b.c = j;
        }

        public InputStream a(int i) throws IOException {
            synchronized (LruDiskCache.this) {
                if (this.b.f != this) {
                    throw new IllegalStateException();
                } else if (!this.b.e) {
                    return null;
                } else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.b.a(i));
                        return fileInputStream;
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                }
            }
        }

        public String b(int i) throws IOException {
            InputStream a2 = a(i);
            if (a2 != null) {
                return LruDiskCache.b(a2);
            }
            return null;
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.OutputStream c(int r4) throws java.io.IOException {
            /*
                r3 = this;
                com.lidroid.xutils.cache.LruDiskCache r0 = com.lidroid.xutils.cache.LruDiskCache.this
                monitor-enter(r0)
                com.lidroid.xutils.cache.LruDiskCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                com.lidroid.xutils.cache.LruDiskCache$Editor r1 = r1.f     // Catch:{ all -> 0x0046 }
                if (r1 != r3) goto L_0x0040
                com.lidroid.xutils.cache.LruDiskCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                boolean r1 = r1.e     // Catch:{ all -> 0x0046 }
                if (r1 != 0) goto L_0x0018
                boolean[] r1 = r3.c     // Catch:{ all -> 0x0046 }
                r2 = 1
                r1[r4] = r2     // Catch:{ all -> 0x0046 }
            L_0x0018:
                com.lidroid.xutils.cache.LruDiskCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                java.io.File r4 = r1.b((int) r4)     // Catch:{ all -> 0x0046 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0024 }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0024 }
                goto L_0x0032
            L_0x0024:
                com.lidroid.xutils.cache.LruDiskCache r1 = com.lidroid.xutils.cache.LruDiskCache.this     // Catch:{ all -> 0x0046 }
                java.io.File r1 = r1.m     // Catch:{ all -> 0x0046 }
                r1.mkdirs()     // Catch:{ all -> 0x0046 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x003a }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x003a }
            L_0x0032:
                com.lidroid.xutils.cache.LruDiskCache$Editor$FaultHidingOutputStream r4 = new com.lidroid.xutils.cache.LruDiskCache$Editor$FaultHidingOutputStream     // Catch:{ all -> 0x0046 }
                r2 = 0
                r4.<init>(r3, r1, r2)     // Catch:{ all -> 0x0046 }
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                return r4
            L_0x003a:
                java.io.OutputStream r4 = com.lidroid.xutils.cache.LruDiskCache.z     // Catch:{ all -> 0x0046 }
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                return r4
            L_0x0040:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0046 }
                r4.<init>()     // Catch:{ all -> 0x0046 }
                throw r4     // Catch:{ all -> 0x0046 }
            L_0x0046:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.Editor.c(int):java.io.OutputStream");
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(i), "UTF-8");
                try {
                    outputStreamWriter2.write(str);
                    IOUtils.a((Closeable) outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    IOUtils.a((Closeable) outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((Closeable) outputStreamWriter);
                throw th;
            }
        }

        public void a() throws IOException {
            if (this.d) {
                LruDiskCache.this.a(this, false);
                boolean unused = LruDiskCache.this.g(this.b.b);
            } else {
                LruDiskCache.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            LruDiskCache.this.a(this, false);
        }

        public void c() {
            if (!this.e) {
                try {
                    b();
                } catch (Throwable unused) {
                }
            }
        }

        private class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ FaultHidingOutputStream(Editor editor, OutputStream outputStream, FaultHidingOutputStream faultHidingOutputStream) {
                this(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (Throwable unused) {
                    Editor.this.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                    this.out.flush();
                } catch (Throwable unused) {
                    Editor.this.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (Throwable unused) {
                    Editor.this.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (Throwable unused) {
                    Editor.this.d = true;
                }
            }
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public final long[] d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public Editor f;
        /* access modifiers changed from: private */
        public long g;

        private Entry(String str) {
            this.c = Long.MAX_VALUE;
            this.b = str;
            this.d = new long[LruDiskCache.this.s];
        }

        /* synthetic */ Entry(LruDiskCache lruDiskCache, String str, Entry entry) {
            this(str);
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.d) {
                sb.append(" ");
                sb.append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void a(String[] strArr, int i) throws IOException {
            if (strArr.length - i == LruDiskCache.this.s) {
                int i2 = 0;
                while (i2 < LruDiskCache.this.s) {
                    try {
                        this.d[i2] = Long.parseLong(strArr[i2 + i]);
                        i2++;
                    } catch (NumberFormatException unused) {
                        throw a(strArr);
                    }
                }
                return;
            }
            throw a(strArr);
        }

        private IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            File f2 = LruDiskCache.this.m;
            return new File(f2, String.valueOf(this.b) + "." + i);
        }

        public File b(int i) {
            File f2 = LruDiskCache.this.m;
            return new File(f2, String.valueOf(this.b) + "." + i + DefaultDiskStorage.FileType.TEMP);
        }
    }

    private static String a(Reader reader) throws IOException {
        StringWriter stringWriter;
        Throwable th;
        try {
            stringWriter = new StringWriter();
            try {
                char[] cArr = new char[1024];
                while (true) {
                    int read = reader.read(cArr);
                    if (read == -1) {
                        String stringWriter2 = stringWriter.toString();
                        IOUtils.a((Closeable) reader);
                        IOUtils.a((Closeable) stringWriter);
                        return stringWriter2;
                    }
                    stringWriter.write(cArr, 0, read);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            stringWriter = null;
            th = th4;
            IOUtils.a((Closeable) reader);
            IOUtils.a((Closeable) stringWriter);
            throw th;
        }
    }

    private static void b(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i2 = 0;
            while (i2 < length) {
                File file2 = listFiles[i2];
                if (file2.isDirectory()) {
                    b(file2);
                }
                if (!file2.exists() || file2.delete()) {
                    i2++;
                } else {
                    throw new IOException("failed to delete file: " + file2);
                }
            }
            return;
        }
        throw new IOException("not a readable directory: " + file);
    }

    private class StrictLineReader implements Closeable {
        private static final byte b = 13;
        private static final byte c = 10;
        private final InputStream d;
        /* access modifiers changed from: private */
        public final Charset e;
        private byte[] f;
        private int g;
        private int h;

        public StrictLineReader(LruDiskCache lruDiskCache, InputStream inputStream) {
            this(inputStream, 8192);
        }

        public StrictLineReader(InputStream inputStream, int i) {
            this.e = Charset.forName("US-ASCII");
            if (inputStream == null) {
                throw new NullPointerException();
            } else if (i >= 0) {
                this.d = inputStream;
                this.f = new byte[i];
            } else {
                throw new IllegalArgumentException("capacity <= 0");
            }
        }

        public void close() throws IOException {
            synchronized (this.d) {
                if (this.f != null) {
                    this.f = null;
                    this.d.close();
                }
            }
        }

        public String a() throws IOException {
            int i;
            int i2;
            synchronized (this.d) {
                if (this.f != null) {
                    if (this.g >= this.h) {
                        b();
                    }
                    for (int i3 = this.g; i3 != this.h; i3++) {
                        if (this.f[i3] == 10) {
                            if (i3 != this.g) {
                                i2 = i3 - 1;
                                if (this.f[i2] == 13) {
                                    String str = new String(this.f, this.g, i2 - this.g, this.e.name());
                                    this.g = i3 + 1;
                                    return str;
                                }
                            }
                            i2 = i3;
                            String str2 = new String(this.f, this.g, i2 - this.g, this.e.name());
                            this.g = i3 + 1;
                            return str2;
                        }
                    }
                    AnonymousClass1 r1 = new ByteArrayOutputStream((this.h - this.g) + 80) {
                        public String toString() {
                            try {
                                return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, StrictLineReader.this.e.name());
                            } catch (UnsupportedEncodingException e) {
                                throw new AssertionError(e);
                            }
                        }
                    };
                    loop1:
                    while (true) {
                        r1.write(this.f, this.g, this.h - this.g);
                        this.h = -1;
                        b();
                        i = this.g;
                        while (i != this.h) {
                            if (this.f[i] == 10) {
                                break loop1;
                            }
                            i++;
                        }
                    }
                    if (i != this.g) {
                        r1.write(this.f, this.g, i - this.g);
                    }
                    r1.flush();
                    this.g = i + 1;
                    String byteArrayOutputStream = r1.toString();
                    return byteArrayOutputStream;
                }
                throw new IOException("LineReader is closed");
            }
        }

        private void b() throws IOException {
            int read = this.d.read(this.f, 0, this.f.length);
            if (read != -1) {
                this.g = 0;
                this.h = read;
                return;
            }
            throw new EOFException();
        }
    }

    public FileNameGenerator g() {
        return this.A;
    }

    public void a(FileNameGenerator fileNameGenerator) {
        if (fileNameGenerator != null) {
            this.A = fileNameGenerator;
        }
    }
}
