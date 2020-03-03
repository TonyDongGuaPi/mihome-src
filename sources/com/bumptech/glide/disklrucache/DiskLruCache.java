package com.bumptech.glide.disklrucache;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
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

public final class DiskLruCache implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    static final String f4820a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    private static final String h = "CLEAN";
    private static final String i = "DIRTY";
    private static final String j = "REMOVE";
    private static final String k = "READ";
    final ThreadPoolExecutor g = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DiskLruCacheThreadFactory());
    /* access modifiers changed from: private */
    public final File l;
    private final File m;
    private final File n;
    private final File o;
    private final int p;
    private long q;
    /* access modifiers changed from: private */
    public final int r;
    private long s = 0;
    /* access modifiers changed from: private */
    public Writer t;
    private final LinkedHashMap<String, Entry> u = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int v;
    private long w = 0;
    private final Callable<Void> x = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.bumptech.glide.disklrucache.DiskLruCache r0 = com.bumptech.glide.disklrucache.DiskLruCache.this
                monitor-enter(r0)
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.t     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.l()     // Catch:{ all -> 0x0028 }
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.j()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.i()     // Catch:{ all -> 0x0028 }
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r3 = 0
                int unused = r1.v = r3     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.AnonymousClass1.call():java.lang.Void");
        }
    };

    private DiskLruCache(File file, int i2, int i3, long j2) {
        File file2 = file;
        this.l = file2;
        this.p = i2;
        this.m = new File(file2, f4820a);
        this.n = new File(file2, b);
        this.o = new File(file2, c);
        this.r = i3;
        this.q = j2;
    }

    public static DiskLruCache a(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, c);
            if (file2.exists()) {
                File file3 = new File(file, f4820a);
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i2, i3, j2);
            if (diskLruCache.m.exists()) {
                try {
                    diskLruCache.g();
                    diskLruCache.h();
                    return diskLruCache;
                } catch (IOException e2) {
                    PrintStream printStream = System.out;
                    printStream.println("DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                    diskLruCache.f();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i2, i3, j2);
            diskLruCache2.i();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.v = r1 - r8.u.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (r0.b() != false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        r8.t = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r8.m, true), com.bumptech.glide.disklrucache.Util.f4827a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0089, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005d */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x008a=Splitter:B:23:0x008a, B:16:0x005d=Splitter:B:16:0x005d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() throws java.io.IOException {
        /*
            r8 = this;
            com.bumptech.glide.disklrucache.StrictLineReader r0 = new com.bumptech.glide.disklrucache.StrictLineReader
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.io.File r2 = r8.m
            r1.<init>(r2)
            java.nio.charset.Charset r2 = com.bumptech.glide.disklrucache.Util.f4827a
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r2 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r5 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x00bf }
            if (r6 == 0) goto L_0x008a
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x00bf }
            if (r6 == 0) goto L_0x008a
            int r6 = r8.p     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x00bf }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x008a
            int r3 = r8.r     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x00bf }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x008a
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x008a
            r1 = 0
        L_0x0053:
            java.lang.String r2 = r0.a()     // Catch:{ EOFException -> 0x005d }
            r8.d((java.lang.String) r2)     // Catch:{ EOFException -> 0x005d }
            int r1 = r1 + 1
            goto L_0x0053
        L_0x005d:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r8.u     // Catch:{ all -> 0x00bf }
            int r2 = r2.size()     // Catch:{ all -> 0x00bf }
            int r1 = r1 - r2
            r8.v = r1     // Catch:{ all -> 0x00bf }
            boolean r1 = r0.b()     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x0070
            r8.i()     // Catch:{ all -> 0x00bf }
            goto L_0x0086
        L_0x0070:
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ all -> 0x00bf }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00bf }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x00bf }
            java.io.File r4 = r8.m     // Catch:{ all -> 0x00bf }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x00bf }
            java.nio.charset.Charset r4 = com.bumptech.glide.disklrucache.Util.f4827a     // Catch:{ all -> 0x00bf }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00bf }
            r1.<init>(r2)     // Catch:{ all -> 0x00bf }
            r8.t = r1     // Catch:{ all -> 0x00bf }
        L_0x0086:
            com.bumptech.glide.disklrucache.Util.a((java.io.Closeable) r0)
            return
        L_0x008a:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00bf }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r6.<init>()     // Catch:{ all -> 0x00bf }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x00bf }
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r2)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r4)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r5)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x00bf }
            r3.<init>(r1)     // Catch:{ all -> 0x00bf }
            throw r3     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r1 = move-exception
            com.bumptech.glide.disklrucache.Util.a((java.io.Closeable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.g():void");
    }

    private void d(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (indexOf == j.length() && str.startsWith(j)) {
                    this.u.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            Entry entry = this.u.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.u.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == h.length() && str.startsWith(h)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                boolean unused = entry.f = true;
                Editor unused2 = entry.g = null;
                entry.a(split);
            } else if (indexOf2 == -1 && indexOf == i.length() && str.startsWith(i)) {
                Editor unused3 = entry.g = new Editor(entry);
            } else if (indexOf2 != -1 || indexOf != k.length() || !str.startsWith(k)) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void h() throws IOException {
        a(this.n);
        Iterator<Entry> it = this.u.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i2 = 0;
            if (next.g == null) {
                while (i2 < this.r) {
                    this.s += next.e[i2];
                    i2++;
                }
            } else {
                Editor unused = next.g = null;
                while (i2 < this.r) {
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
    public synchronized void i() throws IOException {
        if (this.t != null) {
            this.t.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.n), Util.f4827a));
        try {
            bufferedWriter.write(d);
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.p));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.r));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry next : this.u.values()) {
                if (next.g != null) {
                    bufferedWriter.write("DIRTY " + next.d + 10);
                } else {
                    bufferedWriter.write("CLEAN " + next.d + next.a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.m.exists()) {
                a(this.m, this.o, true);
            }
            a(this.n, this.m, false);
            this.o.delete();
            this.t = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.m, true), Util.f4827a));
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

    public synchronized Value a(String str) throws IOException {
        k();
        Entry entry = this.u.get(str);
        if (entry == null) {
            return null;
        }
        if (!entry.f) {
            return null;
        }
        for (File exists : entry.f4823a) {
            if (!exists.exists()) {
                return null;
            }
        }
        this.v++;
        this.t.append(k);
        this.t.append(' ');
        this.t.append(str);
        this.t.append(10);
        if (j()) {
            this.g.submit(this.x);
        }
        return new Value(str, entry.h, entry.f4823a, entry.e);
    }

    public Editor b(String str) throws IOException {
        return a(str, -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.bumptech.glide.disklrucache.DiskLruCache.Editor a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.k()     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r5.u     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x005d }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x005d }
            r1 = -1
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r1 = 0
            if (r3 == 0) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            long r2 = r0.h     // Catch:{ all -> 0x005d }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r5)
            return r1
        L_0x001f:
            if (r0 != 0) goto L_0x002c
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry     // Catch:{ all -> 0x005d }
            r0.<init>(r6)     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r7 = r5.u     // Catch:{ all -> 0x005d }
            r7.put(r6, r0)     // Catch:{ all -> 0x005d }
            goto L_0x0034
        L_0x002c:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = r0.g     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x0034
            monitor-exit(r5)
            return r1
        L_0x0034:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor     // Catch:{ all -> 0x005d }
            r7.<init>(r0)     // Catch:{ all -> 0x005d }
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.g = r7     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.t     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "DIRTY"
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.t     // Catch:{ all -> 0x005d }
            r0 = 32
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.t     // Catch:{ all -> 0x005d }
            r8.append(r6)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.t     // Catch:{ all -> 0x005d }
            r8 = 10
            r6.append(r8)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.t     // Catch:{ all -> 0x005d }
            r6.flush()     // Catch:{ all -> 0x005d }
            monitor-exit(r5)
            return r7
        L_0x005d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.a(java.lang.String, long):com.bumptech.glide.disklrucache.DiskLruCache$Editor");
    }

    public File a() {
        return this.l;
    }

    public synchronized long b() {
        return this.q;
    }

    public synchronized void a(long j2) {
        this.q = j2;
        this.g.submit(this.x);
    }

    public synchronized long c() {
        return this.s;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0108, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.bumptech.glide.disklrucache.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = r10.b     // Catch:{ all -> 0x010f }
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = r0.g     // Catch:{ all -> 0x010f }
            if (r1 != r10) goto L_0x0109
            r1 = 0
            if (r11 == 0) goto L_0x004d
            boolean r2 = r0.f     // Catch:{ all -> 0x010f }
            if (r2 != 0) goto L_0x004d
            r2 = 0
        L_0x0015:
            int r3 = r9.r     // Catch:{ all -> 0x010f }
            if (r2 >= r3) goto L_0x004d
            boolean[] r3 = r10.c     // Catch:{ all -> 0x010f }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x010f }
            if (r3 == 0) goto L_0x0033
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x010f }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x010f }
            if (r3 != 0) goto L_0x0030
            r10.b()     // Catch:{ all -> 0x010f }
            monitor-exit(r9)
            return
        L_0x0030:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0033:
            r10.b()     // Catch:{ all -> 0x010f }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x010f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x010f }
            r11.<init>()     // Catch:{ all -> 0x010f }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x010f }
            r11.append(r2)     // Catch:{ all -> 0x010f }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x010f }
            r10.<init>(r11)     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x004d:
            int r10 = r9.r     // Catch:{ all -> 0x010f }
            if (r1 >= r10) goto L_0x0082
            java.io.File r10 = r0.b((int) r1)     // Catch:{ all -> 0x010f }
            if (r11 == 0) goto L_0x007c
            boolean r2 = r10.exists()     // Catch:{ all -> 0x010f }
            if (r2 == 0) goto L_0x007f
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x010f }
            r10.renameTo(r2)     // Catch:{ all -> 0x010f }
            long[] r10 = r0.e     // Catch:{ all -> 0x010f }
            r3 = r10[r1]     // Catch:{ all -> 0x010f }
            long r5 = r2.length()     // Catch:{ all -> 0x010f }
            long[] r10 = r0.e     // Catch:{ all -> 0x010f }
            r10[r1] = r5     // Catch:{ all -> 0x010f }
            long r7 = r9.s     // Catch:{ all -> 0x010f }
            r10 = 0
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.s = r7     // Catch:{ all -> 0x010f }
            goto L_0x007f
        L_0x007c:
            a((java.io.File) r10)     // Catch:{ all -> 0x010f }
        L_0x007f:
            int r1 = r1 + 1
            goto L_0x004d
        L_0x0082:
            int r10 = r9.v     // Catch:{ all -> 0x010f }
            r1 = 1
            int r10 = r10 + r1
            r9.v = r10     // Catch:{ all -> 0x010f }
            r10 = 0
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.g = r10     // Catch:{ all -> 0x010f }
            boolean r10 = r0.f     // Catch:{ all -> 0x010f }
            r10 = r10 | r11
            r2 = 10
            r3 = 32
            if (r10 == 0) goto L_0x00ca
            boolean unused = r0.f = r1     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            java.lang.String r1 = "CLEAN"
            r10.append(r1)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            r10.append(r3)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            java.lang.String r1 = r0.d     // Catch:{ all -> 0x010f }
            r10.append(r1)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x010f }
            r10.append(r1)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            r10.append(r2)     // Catch:{ all -> 0x010f }
            if (r11 == 0) goto L_0x00ed
            long r10 = r9.w     // Catch:{ all -> 0x010f }
            r1 = 1
            long r1 = r1 + r10
            r9.w = r1     // Catch:{ all -> 0x010f }
            long unused = r0.h = r10     // Catch:{ all -> 0x010f }
            goto L_0x00ed
        L_0x00ca:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r10 = r9.u     // Catch:{ all -> 0x010f }
            java.lang.String r11 = r0.d     // Catch:{ all -> 0x010f }
            r10.remove(r11)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            java.lang.String r11 = "REMOVE"
            r10.append(r11)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            r10.append(r3)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            java.lang.String r11 = r0.d     // Catch:{ all -> 0x010f }
            r10.append(r11)     // Catch:{ all -> 0x010f }
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            r10.append(r2)     // Catch:{ all -> 0x010f }
        L_0x00ed:
            java.io.Writer r10 = r9.t     // Catch:{ all -> 0x010f }
            r10.flush()     // Catch:{ all -> 0x010f }
            long r10 = r9.s     // Catch:{ all -> 0x010f }
            long r0 = r9.q     // Catch:{ all -> 0x010f }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0100
            boolean r10 = r9.j()     // Catch:{ all -> 0x010f }
            if (r10 == 0) goto L_0x0107
        L_0x0100:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.g     // Catch:{ all -> 0x010f }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.x     // Catch:{ all -> 0x010f }
            r10.submit(r11)     // Catch:{ all -> 0x010f }
        L_0x0107:
            monitor-exit(r9)
            return
        L_0x0109:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x010f }
            r10.<init>()     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x010f:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.a(com.bumptech.glide.disklrucache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean j() {
        return this.v >= 2000 && this.v >= this.u.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean c(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.k()     // Catch:{ all -> 0x0090 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r7.u     // Catch:{ all -> 0x0090 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0090 }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0090 }
            r1 = 0
            if (r0 == 0) goto L_0x008e
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = r0.g     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0017
            goto L_0x008e
        L_0x0017:
            int r2 = r7.r     // Catch:{ all -> 0x0090 }
            if (r1 >= r2) goto L_0x005a
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0090 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x0043
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x002c
            goto L_0x0043
        L_0x002c:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0090 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0090 }
            r0.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0090 }
            r0.append(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0090 }
            r8.<init>(r0)     // Catch:{ all -> 0x0090 }
            throw r8     // Catch:{ all -> 0x0090 }
        L_0x0043:
            long r2 = r7.s     // Catch:{ all -> 0x0090 }
            long[] r4 = r0.e     // Catch:{ all -> 0x0090 }
            r5 = r4[r1]     // Catch:{ all -> 0x0090 }
            r4 = 0
            long r2 = r2 - r5
            r7.s = r2     // Catch:{ all -> 0x0090 }
            long[] r2 = r0.e     // Catch:{ all -> 0x0090 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0090 }
            int r1 = r1 + 1
            goto L_0x0017
        L_0x005a:
            int r0 = r7.v     // Catch:{ all -> 0x0090 }
            r1 = 1
            int r0 = r0 + r1
            r7.v = r0     // Catch:{ all -> 0x0090 }
            java.io.Writer r0 = r7.t     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = "REMOVE"
            r0.append(r2)     // Catch:{ all -> 0x0090 }
            java.io.Writer r0 = r7.t     // Catch:{ all -> 0x0090 }
            r2 = 32
            r0.append(r2)     // Catch:{ all -> 0x0090 }
            java.io.Writer r0 = r7.t     // Catch:{ all -> 0x0090 }
            r0.append(r8)     // Catch:{ all -> 0x0090 }
            java.io.Writer r0 = r7.t     // Catch:{ all -> 0x0090 }
            r2 = 10
            r0.append(r2)     // Catch:{ all -> 0x0090 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r7.u     // Catch:{ all -> 0x0090 }
            r0.remove(r8)     // Catch:{ all -> 0x0090 }
            boolean r8 = r7.j()     // Catch:{ all -> 0x0090 }
            if (r8 == 0) goto L_0x008c
            java.util.concurrent.ThreadPoolExecutor r8 = r7.g     // Catch:{ all -> 0x0090 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.x     // Catch:{ all -> 0x0090 }
            r8.submit(r0)     // Catch:{ all -> 0x0090 }
        L_0x008c:
            monitor-exit(r7)
            return r1
        L_0x008e:
            monitor-exit(r7)
            return r1
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.c(java.lang.String):boolean");
    }

    public synchronized boolean d() {
        return this.t == null;
    }

    private void k() {
        if (this.t == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void e() throws IOException {
        k();
        l();
        this.t.flush();
    }

    public synchronized void close() throws IOException {
        if (this.t != null) {
            Iterator it = new ArrayList(this.u.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.g != null) {
                    entry.g.b();
                }
            }
            l();
            this.t.close();
            this.t = null;
        }
    }

    /* access modifiers changed from: private */
    public void l() throws IOException {
        while (this.s > this.q) {
            c((String) this.u.entrySet().iterator().next().getKey());
        }
    }

    public void f() throws IOException {
        close();
        Util.a(this.l);
    }

    /* access modifiers changed from: private */
    public static String b(InputStream inputStream) throws IOException {
        return Util.a((Reader) new InputStreamReader(inputStream, Util.b));
    }

    public final class Value {
        private final String b;
        private final long c;
        private final long[] d;
        private final File[] e;

        private Value(String str, long j, File[] fileArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.e = fileArr;
            this.d = jArr;
        }

        public Editor a() throws IOException {
            return DiskLruCache.this.a(this.b, this.c);
        }

        public File a(int i) {
            return this.e[i];
        }

        public String b(int i) throws IOException {
            return DiskLruCache.b((InputStream) new FileInputStream(this.e[i]));
        }

        public long c(int i) {
            return this.d[i];
        }
    }

    public final class Editor {
        /* access modifiers changed from: private */
        public final Entry b;
        /* access modifiers changed from: private */
        public final boolean[] c;
        private boolean d;

        private Editor(Entry entry) {
            this.b = entry;
            this.c = entry.f ? null : new boolean[DiskLruCache.this.r];
        }

        private InputStream c(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                } else if (!this.b.f) {
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

        public String a(int i) throws IOException {
            InputStream c2 = c(i);
            if (c2 != null) {
                return DiskLruCache.b(c2);
            }
            return null;
        }

        public File b(int i) throws IOException {
            File b2;
            synchronized (DiskLruCache.this) {
                if (this.b.g == this) {
                    if (!this.b.f) {
                        this.c[i] = true;
                    }
                    b2 = this.b.b(i);
                    if (!DiskLruCache.this.l.exists()) {
                        DiskLruCache.this.l.mkdirs();
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return b2;
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(b(i)), Util.b);
                try {
                    outputStreamWriter2.write(str);
                    Util.a((Closeable) outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    Util.a((Closeable) outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Util.a((Closeable) outputStreamWriter);
                throw th;
            }
        }

        public void a() throws IOException {
            DiskLruCache.this.a(this, true);
            this.d = true;
        }

        public void b() throws IOException {
            DiskLruCache.this.a(this, false);
        }

        public void c() {
            if (!this.d) {
                try {
                    b();
                } catch (IOException unused) {
                }
            }
        }
    }

    private final class Entry {

        /* renamed from: a  reason: collision with root package name */
        File[] f4823a;
        File[] b;
        /* access modifiers changed from: private */
        public final String d;
        /* access modifiers changed from: private */
        public final long[] e;
        /* access modifiers changed from: private */
        public boolean f;
        /* access modifiers changed from: private */
        public Editor g;
        /* access modifiers changed from: private */
        public long h;

        private Entry(String str) {
            this.d = str;
            this.e = new long[DiskLruCache.this.r];
            this.f4823a = new File[DiskLruCache.this.r];
            this.b = new File[DiskLruCache.this.r];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.r; i++) {
                sb.append(i);
                this.f4823a[i] = new File(DiskLruCache.this.l, sb.toString());
                sb.append(DefaultDiskStorage.FileType.TEMP);
                this.b[i] = new File(DiskLruCache.this.l, sb.toString());
                sb.setLength(length);
            }
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.e) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.r) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.e[i] = Long.parseLong(strArr[i]);
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
            return this.f4823a[i];
        }

        public File b(int i) {
            return this.b[i];
        }
    }

    private static final class DiskLruCacheThreadFactory implements ThreadFactory {
        private DiskLruCacheThreadFactory() {
        }

        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }
}
