package com.nostra13.universalimageloader.cache.disc.impl.ext;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedWriter;
import java.io.Closeable;
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
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class DiskLruCache implements Closeable {
    /* access modifiers changed from: private */
    public static final OutputStream B = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };

    /* renamed from: a  reason: collision with root package name */
    static final String f8434a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    static final Pattern g = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final String i = "CLEAN";
    private static final String j = "DIRTY";
    private static final String k = "REMOVE";
    private static final String l = "READ";
    private final Callable<Void> A = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r0 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this
                monitor-enter(r0)
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                java.io.Writer r1 = r1.w     // Catch:{ all -> 0x002d }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return r2
            L_0x000e:
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                r1.o()     // Catch:{ all -> 0x002d }
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                r1.p()     // Catch:{ all -> 0x002d }
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                boolean r1 = r1.m()     // Catch:{ all -> 0x002d }
                if (r1 == 0) goto L_0x002b
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                r1.l()     // Catch:{ all -> 0x002d }
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x002d }
                r3 = 0
                int unused = r1.y = r3     // Catch:{ all -> 0x002d }
            L_0x002b:
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return r2
            L_0x002d:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.AnonymousClass1.call():java.lang.Void");
        }
    };
    final ThreadPoolExecutor h = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */
    public final File m;
    private final File n;
    private final File o;
    private final File p;
    private final int q;
    private long r;
    private int s;
    /* access modifiers changed from: private */
    public final int t;
    private long u = 0;
    private int v = 0;
    /* access modifiers changed from: private */
    public Writer w;
    private final LinkedHashMap<String, Entry> x = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int y;
    private long z = 0;

    private DiskLruCache(File file, int i2, int i3, long j2, int i4) {
        File file2 = file;
        this.m = file2;
        this.q = i2;
        this.n = new File(file2, f8434a);
        this.o = new File(file2, b);
        this.p = new File(file2, c);
        this.t = i3;
        this.r = j2;
        this.s = i4;
    }

    public static DiskLruCache a(File file, int i2, int i3, long j2, int i4) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i4 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, c);
            if (file2.exists()) {
                File file3 = new File(file, f8434a);
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i2, i3, j2, i4);
            if (diskLruCache.n.exists()) {
                try {
                    diskLruCache.j();
                    diskLruCache.k();
                    diskLruCache.w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(diskLruCache.n, true), Util.f8443a));
                    return diskLruCache;
                } catch (IOException e2) {
                    PrintStream printStream = System.out;
                    printStream.println("DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                    diskLruCache.h();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i2, i3, j2, i4);
            diskLruCache2.l();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.y = r1 - r8.x.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005d */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x006a=Splitter:B:20:0x006a, B:16:0x005d=Splitter:B:16:0x005d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void j() throws java.io.IOException {
        /*
            r8 = this;
            com.nostra13.universalimageloader.cache.disc.impl.ext.StrictLineReader r0 = new com.nostra13.universalimageloader.cache.disc.impl.ext.StrictLineReader
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.io.File r2 = r8.n
            r1.<init>(r2)
            java.nio.charset.Charset r2 = com.nostra13.universalimageloader.cache.disc.impl.ext.Util.f8443a
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x009e }
            java.lang.String r2 = r0.a()     // Catch:{ all -> 0x009e }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r0.a()     // Catch:{ all -> 0x009e }
            java.lang.String r5 = r0.a()     // Catch:{ all -> 0x009e }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x009e }
            if (r6 == 0) goto L_0x006a
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x009e }
            if (r6 == 0) goto L_0x006a
            int r6 = r8.q     // Catch:{ all -> 0x009e }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x009e }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            int r3 = r8.t     // Catch:{ all -> 0x009e }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x009e }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            r1 = 0
        L_0x0053:
            java.lang.String r2 = r0.a()     // Catch:{ EOFException -> 0x005d }
            r8.d((java.lang.String) r2)     // Catch:{ EOFException -> 0x005d }
            int r1 = r1 + 1
            goto L_0x0053
        L_0x005d:
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r2 = r8.x     // Catch:{ all -> 0x009e }
            int r2 = r2.size()     // Catch:{ all -> 0x009e }
            int r1 = r1 - r2
            r8.y = r1     // Catch:{ all -> 0x009e }
            com.nostra13.universalimageloader.cache.disc.impl.ext.Util.a((java.io.Closeable) r0)
            return
        L_0x006a:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r6.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x009e }
            r6.append(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r4)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x009e }
            r3.<init>(r1)     // Catch:{ all -> 0x009e }
            throw r3     // Catch:{ all -> 0x009e }
        L_0x009e:
            r1 = move-exception
            com.nostra13.universalimageloader.cache.disc.impl.ext.Util.a((java.io.Closeable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.j():void");
    }

    private void d(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (indexOf == k.length() && str.startsWith(k)) {
                    this.x.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            Entry entry = this.x.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.x.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == i.length() && str.startsWith(i)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                boolean unused = entry.d = true;
                Editor unused2 = entry.e = null;
                entry.a(split);
            } else if (indexOf2 == -1 && indexOf == j.length() && str.startsWith(j)) {
                Editor unused3 = entry.e = new Editor(entry);
            } else if (indexOf2 != -1 || indexOf != l.length() || !str.startsWith(l)) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void k() throws IOException {
        a(this.o);
        Iterator<Entry> it = this.x.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i2 = 0;
            if (next.e == null) {
                while (i2 < this.t) {
                    this.u += next.c[i2];
                    this.v++;
                    i2++;
                }
            } else {
                Editor unused = next.e = null;
                while (i2 < this.t) {
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
    public synchronized void l() throws IOException {
        if (this.w != null) {
            this.w.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.o), Util.f8443a));
        try {
            bufferedWriter.write(d);
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.q));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.t));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry next : this.x.values()) {
                if (next.e != null) {
                    bufferedWriter.write("DIRTY " + next.b + 10);
                } else {
                    bufferedWriter.write("CLEAN " + next.b + next.a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.n.exists()) {
                a(this.n, this.p, true);
            }
            a(this.o, this.n, false);
            this.p.delete();
            this.w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.n, true), Util.f8443a));
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

    private static void a(File file, File file2, boolean z2) throws IOException {
        if (z2) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r12.y++;
        r12.w.append("READ " + r13 + 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        if (m() == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        r12.h.submit(r12.A);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        return new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Snapshot(r12, r13, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry.e(r0), r8, r9, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry.b(r0), (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007b */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Snapshot a(java.lang.String r13) throws java.io.IOException {
        /*
            r12 = this;
            monitor-enter(r12)
            r12.n()     // Catch:{ all -> 0x008d }
            r12.e((java.lang.String) r13)     // Catch:{ all -> 0x008d }
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r0 = r12.x     // Catch:{ all -> 0x008d }
            java.lang.Object r0 = r0.get(r13)     // Catch:{ all -> 0x008d }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r0 = (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry) r0     // Catch:{ all -> 0x008d }
            r1 = 0
            if (r0 != 0) goto L_0x0014
            monitor-exit(r12)
            return r1
        L_0x0014:
            boolean r2 = r0.d     // Catch:{ all -> 0x008d }
            if (r2 != 0) goto L_0x001c
            monitor-exit(r12)
            return r1
        L_0x001c:
            int r2 = r12.t     // Catch:{ all -> 0x008d }
            java.io.File[] r8 = new java.io.File[r2]     // Catch:{ all -> 0x008d }
            int r2 = r12.t     // Catch:{ all -> 0x008d }
            java.io.InputStream[] r9 = new java.io.InputStream[r2]     // Catch:{ all -> 0x008d }
            r2 = 0
            r3 = 0
        L_0x0026:
            int r4 = r12.t     // Catch:{ FileNotFoundException -> 0x007b }
            if (r3 >= r4) goto L_0x003a
            java.io.File r4 = r0.a((int) r3)     // Catch:{ FileNotFoundException -> 0x007b }
            r8[r3] = r4     // Catch:{ FileNotFoundException -> 0x007b }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x007b }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x007b }
            r9[r3] = r5     // Catch:{ FileNotFoundException -> 0x007b }
            int r3 = r3 + 1
            goto L_0x0026
        L_0x003a:
            int r1 = r12.y     // Catch:{ all -> 0x008d }
            int r1 = r1 + 1
            r12.y = r1     // Catch:{ all -> 0x008d }
            java.io.Writer r1 = r12.w     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r2.<init>()     // Catch:{ all -> 0x008d }
            java.lang.String r3 = "READ "
            r2.append(r3)     // Catch:{ all -> 0x008d }
            r2.append(r13)     // Catch:{ all -> 0x008d }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x008d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008d }
            r1.append(r2)     // Catch:{ all -> 0x008d }
            boolean r1 = r12.m()     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x0068
            java.util.concurrent.ThreadPoolExecutor r1 = r12.h     // Catch:{ all -> 0x008d }
            java.util.concurrent.Callable<java.lang.Void> r2 = r12.A     // Catch:{ all -> 0x008d }
            r1.submit(r2)     // Catch:{ all -> 0x008d }
        L_0x0068:
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot r1 = new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot     // Catch:{ all -> 0x008d }
            long r6 = r0.f     // Catch:{ all -> 0x008d }
            long[] r10 = r0.c     // Catch:{ all -> 0x008d }
            r11 = 0
            r3 = r1
            r4 = r12
            r5 = r13
            r3.<init>(r5, r6, r8, r9, r10)     // Catch:{ all -> 0x008d }
            monitor-exit(r12)
            return r1
        L_0x007b:
            int r13 = r12.t     // Catch:{ all -> 0x008d }
            if (r2 >= r13) goto L_0x008b
            r13 = r9[r2]     // Catch:{ all -> 0x008d }
            if (r13 == 0) goto L_0x008b
            r13 = r9[r2]     // Catch:{ all -> 0x008d }
            com.nostra13.universalimageloader.cache.disc.impl.ext.Util.a((java.io.Closeable) r13)     // Catch:{ all -> 0x008d }
            int r2 = r2 + 1
            goto L_0x007b
        L_0x008b:
            monitor-exit(r12)
            return r1
        L_0x008d:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.a(java.lang.String):com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot");
    }

    public Editor b(String str) throws IOException {
        return a(str, -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.n()     // Catch:{ all -> 0x0061 }
            r5.e((java.lang.String) r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r0 = r5.x     // Catch:{ all -> 0x0061 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0061 }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r0 = (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0061 }
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
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r0 = new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry     // Catch:{ all -> 0x0061 }
            r0.<init>(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r7 = r5.x     // Catch:{ all -> 0x0061 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0037
        L_0x002f:
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor r7 = r0.e     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r1
        L_0x0037:
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor r7 = new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor     // Catch:{ all -> 0x0061 }
            r7.<init>(r0)     // Catch:{ all -> 0x0061 }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor unused = r0.e = r7     // Catch:{ all -> 0x0061 }
            java.io.Writer r8 = r5.w     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0061 }
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0061 }
            r8.write(r6)     // Catch:{ all -> 0x0061 }
            java.io.Writer r6 = r5.w     // Catch:{ all -> 0x0061 }
            r6.flush()     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return r7
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.a(java.lang.String, long):com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor");
    }

    public File a() {
        return this.m;
    }

    public synchronized long b() {
        return this.r;
    }

    public synchronized int c() {
        return this.s;
    }

    public synchronized void a(long j2) {
        this.r = j2;
        this.h.submit(this.A);
    }

    public synchronized long d() {
        return this.u;
    }

    public synchronized long e() {
        return (long) this.v;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0115, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor r11, boolean r12) throws java.io.IOException {
        /*
            r10 = this;
            monitor-enter(r10)
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r0 = r11.b     // Catch:{ all -> 0x011c }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor r1 = r0.e     // Catch:{ all -> 0x011c }
            if (r1 != r11) goto L_0x0116
            r1 = 0
            if (r12 == 0) goto L_0x004d
            boolean r2 = r0.d     // Catch:{ all -> 0x011c }
            if (r2 != 0) goto L_0x004d
            r2 = 0
        L_0x0015:
            int r3 = r10.t     // Catch:{ all -> 0x011c }
            if (r2 >= r3) goto L_0x004d
            boolean[] r3 = r11.c     // Catch:{ all -> 0x011c }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x011c }
            if (r3 == 0) goto L_0x0033
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x011c }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x011c }
            if (r3 != 0) goto L_0x0030
            r11.b()     // Catch:{ all -> 0x011c }
            monitor-exit(r10)
            return
        L_0x0030:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0033:
            r11.b()     // Catch:{ all -> 0x011c }
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r12.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r12.append(r0)     // Catch:{ all -> 0x011c }
            r12.append(r2)     // Catch:{ all -> 0x011c }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x011c }
            r11.<init>(r12)     // Catch:{ all -> 0x011c }
            throw r11     // Catch:{ all -> 0x011c }
        L_0x004d:
            int r11 = r10.t     // Catch:{ all -> 0x011c }
            r2 = 1
            if (r1 >= r11) goto L_0x0088
            java.io.File r11 = r0.b((int) r1)     // Catch:{ all -> 0x011c }
            if (r12 == 0) goto L_0x0082
            boolean r3 = r11.exists()     // Catch:{ all -> 0x011c }
            if (r3 == 0) goto L_0x0085
            java.io.File r3 = r0.a((int) r1)     // Catch:{ all -> 0x011c }
            r11.renameTo(r3)     // Catch:{ all -> 0x011c }
            long[] r11 = r0.c     // Catch:{ all -> 0x011c }
            r4 = r11[r1]     // Catch:{ all -> 0x011c }
            long r6 = r3.length()     // Catch:{ all -> 0x011c }
            long[] r11 = r0.c     // Catch:{ all -> 0x011c }
            r11[r1] = r6     // Catch:{ all -> 0x011c }
            long r8 = r10.u     // Catch:{ all -> 0x011c }
            r11 = 0
            long r8 = r8 - r4
            long r8 = r8 + r6
            r10.u = r8     // Catch:{ all -> 0x011c }
            int r11 = r10.v     // Catch:{ all -> 0x011c }
            int r11 = r11 + r2
            r10.v = r11     // Catch:{ all -> 0x011c }
            goto L_0x0085
        L_0x0082:
            a((java.io.File) r11)     // Catch:{ all -> 0x011c }
        L_0x0085:
            int r1 = r1 + 1
            goto L_0x004d
        L_0x0088:
            int r11 = r10.y     // Catch:{ all -> 0x011c }
            int r11 = r11 + r2
            r10.y = r11     // Catch:{ all -> 0x011c }
            r11 = 0
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor unused = r0.e = r11     // Catch:{ all -> 0x011c }
            boolean r11 = r0.d     // Catch:{ all -> 0x011c }
            r11 = r11 | r12
            r1 = 10
            if (r11 == 0) goto L_0x00ce
            boolean unused = r0.d = r2     // Catch:{ all -> 0x011c }
            java.io.Writer r11 = r10.w     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r2.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r3 = "CLEAN "
            r2.append(r3)     // Catch:{ all -> 0x011c }
            java.lang.String r3 = r0.b     // Catch:{ all -> 0x011c }
            r2.append(r3)     // Catch:{ all -> 0x011c }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x011c }
            r2.append(r3)     // Catch:{ all -> 0x011c }
            r2.append(r1)     // Catch:{ all -> 0x011c }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x011c }
            r11.write(r1)     // Catch:{ all -> 0x011c }
            if (r12 == 0) goto L_0x00f4
            long r11 = r10.z     // Catch:{ all -> 0x011c }
            r1 = 1
            long r1 = r1 + r11
            r10.z = r1     // Catch:{ all -> 0x011c }
            long unused = r0.f = r11     // Catch:{ all -> 0x011c }
            goto L_0x00f4
        L_0x00ce:
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r11 = r10.x     // Catch:{ all -> 0x011c }
            java.lang.String r12 = r0.b     // Catch:{ all -> 0x011c }
            r11.remove(r12)     // Catch:{ all -> 0x011c }
            java.io.Writer r11 = r10.w     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r12.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r2 = "REMOVE "
            r12.append(r2)     // Catch:{ all -> 0x011c }
            java.lang.String r0 = r0.b     // Catch:{ all -> 0x011c }
            r12.append(r0)     // Catch:{ all -> 0x011c }
            r12.append(r1)     // Catch:{ all -> 0x011c }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x011c }
            r11.write(r12)     // Catch:{ all -> 0x011c }
        L_0x00f4:
            java.io.Writer r11 = r10.w     // Catch:{ all -> 0x011c }
            r11.flush()     // Catch:{ all -> 0x011c }
            long r11 = r10.u     // Catch:{ all -> 0x011c }
            long r0 = r10.r     // Catch:{ all -> 0x011c }
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x010d
            int r11 = r10.v     // Catch:{ all -> 0x011c }
            int r12 = r10.s     // Catch:{ all -> 0x011c }
            if (r11 > r12) goto L_0x010d
            boolean r11 = r10.m()     // Catch:{ all -> 0x011c }
            if (r11 == 0) goto L_0x0114
        L_0x010d:
            java.util.concurrent.ThreadPoolExecutor r11 = r10.h     // Catch:{ all -> 0x011c }
            java.util.concurrent.Callable<java.lang.Void> r12 = r10.A     // Catch:{ all -> 0x011c }
            r11.submit(r12)     // Catch:{ all -> 0x011c }
        L_0x0114:
            monitor-exit(r10)
            return
        L_0x0116:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x011c }
            r11.<init>()     // Catch:{ all -> 0x011c }
            throw r11     // Catch:{ all -> 0x011c }
        L_0x011c:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.a(com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean m() {
        return this.y >= 2000 && this.y >= this.x.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0096, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0098, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean c(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.n()     // Catch:{ all -> 0x0099 }
            r8.e((java.lang.String) r9)     // Catch:{ all -> 0x0099 }
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r0 = r8.x     // Catch:{ all -> 0x0099 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x0099 }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r0 = (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0099 }
            r1 = 0
            if (r0 == 0) goto L_0x0097
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor r2 = r0.e     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x001a
            goto L_0x0097
        L_0x001a:
            int r2 = r8.t     // Catch:{ all -> 0x0099 }
            r3 = 1
            if (r1 >= r2) goto L_0x0063
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0099 }
            boolean r4 = r2.exists()     // Catch:{ all -> 0x0099 }
            if (r4 == 0) goto L_0x0047
            boolean r4 = r2.delete()     // Catch:{ all -> 0x0099 }
            if (r4 == 0) goto L_0x0030
            goto L_0x0047
        L_0x0030:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r0.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0099 }
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0099 }
            r9.<init>(r0)     // Catch:{ all -> 0x0099 }
            throw r9     // Catch:{ all -> 0x0099 }
        L_0x0047:
            long r4 = r8.u     // Catch:{ all -> 0x0099 }
            long[] r2 = r0.c     // Catch:{ all -> 0x0099 }
            r6 = r2[r1]     // Catch:{ all -> 0x0099 }
            r2 = 0
            long r4 = r4 - r6
            r8.u = r4     // Catch:{ all -> 0x0099 }
            int r2 = r8.v     // Catch:{ all -> 0x0099 }
            int r2 = r2 - r3
            r8.v = r2     // Catch:{ all -> 0x0099 }
            long[] r2 = r0.c     // Catch:{ all -> 0x0099 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0099 }
            int r1 = r1 + 1
            goto L_0x001a
        L_0x0063:
            int r0 = r8.y     // Catch:{ all -> 0x0099 }
            int r0 = r0 + r3
            r8.y = r0     // Catch:{ all -> 0x0099 }
            java.io.Writer r0 = r8.w     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r1.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "REMOVE "
            r1.append(r2)     // Catch:{ all -> 0x0099 }
            r1.append(r9)     // Catch:{ all -> 0x0099 }
            r2 = 10
            r1.append(r2)     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0099 }
            r0.append(r1)     // Catch:{ all -> 0x0099 }
            java.util.LinkedHashMap<java.lang.String, com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry> r0 = r8.x     // Catch:{ all -> 0x0099 }
            r0.remove(r9)     // Catch:{ all -> 0x0099 }
            boolean r9 = r8.m()     // Catch:{ all -> 0x0099 }
            if (r9 == 0) goto L_0x0095
            java.util.concurrent.ThreadPoolExecutor r9 = r8.h     // Catch:{ all -> 0x0099 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r8.A     // Catch:{ all -> 0x0099 }
            r9.submit(r0)     // Catch:{ all -> 0x0099 }
        L_0x0095:
            monitor-exit(r8)
            return r3
        L_0x0097:
            monitor-exit(r8)
            return r1
        L_0x0099:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.c(java.lang.String):boolean");
    }

    public synchronized boolean f() {
        return this.w == null;
    }

    private void n() {
        if (this.w == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void g() throws IOException {
        n();
        o();
        p();
        this.w.flush();
    }

    public synchronized void close() throws IOException {
        if (this.w != null) {
            Iterator it = new ArrayList(this.x.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.e != null) {
                    entry.e.b();
                }
            }
            o();
            p();
            this.w.close();
            this.w = null;
        }
    }

    /* access modifiers changed from: private */
    public void o() throws IOException {
        while (this.u > this.r) {
            c((String) this.x.entrySet().iterator().next().getKey());
        }
    }

    /* access modifiers changed from: private */
    public void p() throws IOException {
        while (this.v > this.s) {
            c((String) this.x.entrySet().iterator().next().getKey());
        }
    }

    public void h() throws IOException {
        close();
        Util.a(this.m);
    }

    private void e(String str) {
        if (!g.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }

    /* access modifiers changed from: private */
    public static String b(InputStream inputStream) throws IOException {
        return Util.a((Reader) new InputStreamReader(inputStream, Util.b));
    }

    public final class Snapshot implements Closeable {
        private final String b;
        private final long c;
        private File[] d;
        private final InputStream[] e;
        private final long[] f;

        private Snapshot(String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = fileArr;
            this.e = inputStreamArr;
            this.f = jArr;
        }

        public Editor a() throws IOException {
            return DiskLruCache.this.a(this.b, this.c);
        }

        public File a(int i) {
            return this.d[i];
        }

        public InputStream b(int i) {
            return this.e[i];
        }

        public String c(int i) throws IOException {
            return DiskLruCache.b(b(i));
        }

        public long d(int i) {
            return this.f[i];
        }

        public void close() {
            for (InputStream a2 : this.e) {
                Util.a((Closeable) a2);
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
            this.c = entry.d ? null : new boolean[DiskLruCache.this.t];
        }

        public InputStream a(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                } else if (!this.b.d) {
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
                return DiskLruCache.b(a2);
            }
            return null;
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.OutputStream c(int r4) throws java.io.IOException {
            /*
                r3 = this;
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r0 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this
                monitor-enter(r0)
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor r1 = r1.e     // Catch:{ all -> 0x0046 }
                if (r1 != r3) goto L_0x0040
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                boolean r1 = r1.d     // Catch:{ all -> 0x0046 }
                if (r1 != 0) goto L_0x0018
                boolean[] r1 = r3.c     // Catch:{ all -> 0x0046 }
                r2 = 1
                r1[r4] = r2     // Catch:{ all -> 0x0046 }
            L_0x0018:
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Entry r1 = r3.b     // Catch:{ all -> 0x0046 }
                java.io.File r4 = r1.b((int) r4)     // Catch:{ all -> 0x0046 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0024 }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0024 }
                goto L_0x0032
            L_0x0024:
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.this     // Catch:{ all -> 0x0046 }
                java.io.File r1 = r1.m     // Catch:{ all -> 0x0046 }
                r1.mkdirs()     // Catch:{ all -> 0x0046 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x003a }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x003a }
            L_0x0032:
                com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor$FaultHidingOutputStream r4 = new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor$FaultHidingOutputStream     // Catch:{ all -> 0x0046 }
                r2 = 0
                r4.<init>(r1)     // Catch:{ all -> 0x0046 }
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                return r4
            L_0x003a:
                java.io.OutputStream r4 = com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.B     // Catch:{ all -> 0x0046 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor.c(int):java.io.OutputStream");
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(i), Util.b);
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
            if (this.d) {
                DiskLruCache.this.a(this, false);
                DiskLruCache.this.c(this.b.b);
            } else {
                DiskLruCache.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            DiskLruCache.this.a(this, false);
        }

        public void c() {
            if (!this.e) {
                try {
                    b();
                } catch (IOException unused) {
                }
            }
        }

        private class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.d = true;
                }
            }
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public final long[] c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public Editor e;
        /* access modifiers changed from: private */
        public long f;

        private Entry(String str) {
            this.b = str;
            this.c = new long[DiskLruCache.this.t];
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
            if (strArr.length == DiskLruCache.this.t) {
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
            File g = DiskLruCache.this.m;
            return new File(g, this.b + "" + i);
        }

        public File b(int i) {
            File g = DiskLruCache.this.m;
            return new File(g, this.b + "" + i + DefaultDiskStorage.FileType.TEMP);
        }
    }
}
