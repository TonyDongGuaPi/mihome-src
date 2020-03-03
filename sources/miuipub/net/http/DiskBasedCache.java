package miuipub.net.http;

import android.util.Log;
import com.miuipub.internal.util.PackageConstants;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.net.http.Cache;
import miuipub.security.DigestUtils;
import miuipub.text.ExtraTextUtils;
import miuipub.util.IOUtils;
import miuipub.util.SoftReferenceSingleton;

public class DiskBasedCache implements Cache {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2975a = "DisBasedCache";
    private static final int f = 10485760;
    private static final float g = 0.9f;
    private static final int h = 538182184;
    private static final SoftReferenceSingleton<DiskBasedCache> i = new SoftReferenceSingleton<DiskBasedCache>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public DiskBasedCache createInstance() {
            DiskBasedCache diskBasedCache = new DiskBasedCache();
            diskBasedCache.a();
            return diskBasedCache;
        }
    };
    private final Map<String, DiskCacheEntry> b;
    private long c;
    private final File d;
    private final int e;

    public static DiskBasedCache c() {
        return i.get();
    }

    public DiskBasedCache() {
        this((File) null, 10485760);
    }

    public DiskBasedCache(File file) {
        this(file, 10485760);
    }

    public DiskBasedCache(File file, int i2) {
        this.b = new ConcurrentHashMap(16, 0.75f);
        this.c = 0;
        this.d = file == null ? new File(PackageConstants.a().getCacheDir(), "miui.net.http") : file;
        this.e = i2;
    }

    public Cache.Entry a(String str) {
        DiskCacheEntry diskCacheEntry = this.b.get(str);
        if (diskCacheEntry == null) {
            return null;
        }
        return diskCacheEntry.a();
    }

    public boolean a(String str, Cache.Entry entry) {
        DiskCacheEntry a2 = DiskCacheEntry.a(c(str), str, entry);
        if (a2 == null) {
            return false;
        }
        a(a2.b);
        a(a2);
        return true;
    }

    public void a() {
        this.b.clear();
        this.c = 0;
        if (this.d.exists()) {
            File[] listFiles = this.d.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    DiskCacheEntry a2 = DiskCacheEntry.a(file);
                    if (a2 != null) {
                        a(a2);
                    } else if (!file.delete()) {
                        Log.e(f2975a, "Cannot delete file " + file);
                    }
                }
            }
        } else if (!this.d.mkdirs()) {
            Log.e(f2975a, "Cannot create directory " + this.d);
        }
    }

    public void a(String str, boolean z) {
        DiskCacheEntry diskCacheEntry = this.b.get(str);
        if (diskCacheEntry != null) {
            diskCacheEntry.j = 0;
            if (z) {
                diskCacheEntry.i = 0;
            }
        }
    }

    public void b(String str) {
        DiskCacheEntry diskCacheEntry = this.b.get(str);
        if (diskCacheEntry != null) {
            diskCacheEntry.e();
            b(diskCacheEntry);
        }
    }

    public void b() {
        for (Map.Entry<String, DiskCacheEntry> value : this.b.entrySet()) {
            ((DiskCacheEntry) value.getValue()).e();
        }
        this.b.clear();
        this.c = 0;
    }

    private File c(String str) {
        return new File(this.d, ExtraTextUtils.a(DigestUtils.a((CharSequence) str, "MD5")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e3, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(long r11) {
        /*
            r10 = this;
            long r0 = r10.c
            long r0 = r0 + r11
            int r2 = r10.e
            long r2 = (long) r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x000b
            return
        L_0x000b:
            long r0 = java.lang.System.currentTimeMillis()
            java.util.Map<java.lang.String, miuipub.net.http.DiskBasedCache$DiskCacheEntry> r2 = r10.b
            monitor-enter(r2)
            long r3 = r10.c     // Catch:{ all -> 0x00e4 }
            r5 = 0
            long r3 = r3 + r11
            int r5 = r10.e     // Catch:{ all -> 0x00e4 }
            long r5 = (long) r5     // Catch:{ all -> 0x00e4 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x001f
            monitor-exit(r2)     // Catch:{ all -> 0x00e4 }
            return
        L_0x001f:
            java.util.Map<java.lang.String, miuipub.net.http.DiskBasedCache$DiskCacheEntry> r3 = r10.b     // Catch:{ all -> 0x00e4 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x00e4 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00e4 }
        L_0x0029:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00e4 }
            if (r4 == 0) goto L_0x0050
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00e4 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x00e4 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x00e4 }
            miuipub.net.http.DiskBasedCache$DiskCacheEntry r4 = (miuipub.net.http.DiskBasedCache.DiskCacheEntry) r4     // Catch:{ all -> 0x00e4 }
            long r5 = r4.i     // Catch:{ all -> 0x00e4 }
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 >= 0) goto L_0x0029
            r4.e()     // Catch:{ all -> 0x00e4 }
            long r5 = r10.c     // Catch:{ all -> 0x00e4 }
            long r7 = r4.b     // Catch:{ all -> 0x00e4 }
            r4 = 0
            long r5 = r5 - r7
            r10.c = r5     // Catch:{ all -> 0x00e4 }
            r3.remove()     // Catch:{ all -> 0x00e4 }
            goto L_0x0029
        L_0x0050:
            long r3 = r10.c     // Catch:{ all -> 0x00e4 }
            r5 = 0
            long r3 = r3 + r11
            int r5 = r10.e     // Catch:{ all -> 0x00e4 }
            long r5 = (long) r5     // Catch:{ all -> 0x00e4 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x005d
            monitor-exit(r2)     // Catch:{ all -> 0x00e4 }
            return
        L_0x005d:
            java.util.Map<java.lang.String, miuipub.net.http.DiskBasedCache$DiskCacheEntry> r3 = r10.b     // Catch:{ all -> 0x00e4 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x00e4 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00e4 }
        L_0x0067:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00e4 }
            r5 = 1063675494(0x3f666666, float:0.9)
            if (r4 == 0) goto L_0x009e
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00e4 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x00e4 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x00e4 }
            miuipub.net.http.DiskBasedCache$DiskCacheEntry r4 = (miuipub.net.http.DiskBasedCache.DiskCacheEntry) r4     // Catch:{ all -> 0x00e4 }
            long r6 = r4.j     // Catch:{ all -> 0x00e4 }
            int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x0067
            r4.e()     // Catch:{ all -> 0x00e4 }
            long r6 = r10.c     // Catch:{ all -> 0x00e4 }
            long r8 = r4.b     // Catch:{ all -> 0x00e4 }
            r4 = 0
            long r6 = r6 - r8
            r10.c = r6     // Catch:{ all -> 0x00e4 }
            r3.remove()     // Catch:{ all -> 0x00e4 }
            long r6 = r10.c     // Catch:{ all -> 0x00e4 }
            r4 = 0
            long r6 = r6 + r11
            float r4 = (float) r6     // Catch:{ all -> 0x00e4 }
            int r6 = r10.e     // Catch:{ all -> 0x00e4 }
            float r6 = (float) r6     // Catch:{ all -> 0x00e4 }
            float r6 = r6 * r5
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0067
        L_0x009e:
            long r0 = r10.c     // Catch:{ all -> 0x00e4 }
            r3 = 0
            long r0 = r0 + r11
            int r3 = r10.e     // Catch:{ all -> 0x00e4 }
            long r3 = (long) r3     // Catch:{ all -> 0x00e4 }
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 >= 0) goto L_0x00ab
            monitor-exit(r2)     // Catch:{ all -> 0x00e4 }
            return
        L_0x00ab:
            java.util.Map<java.lang.String, miuipub.net.http.DiskBasedCache$DiskCacheEntry> r0 = r10.b     // Catch:{ all -> 0x00e4 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x00e4 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e4 }
        L_0x00b5:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00e4 }
            if (r1 == 0) goto L_0x00e2
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00e4 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x00e4 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x00e4 }
            miuipub.net.http.DiskBasedCache$DiskCacheEntry r1 = (miuipub.net.http.DiskBasedCache.DiskCacheEntry) r1     // Catch:{ all -> 0x00e4 }
            boolean r3 = r1.c()     // Catch:{ all -> 0x00e4 }
            if (r3 == 0) goto L_0x00ce
            goto L_0x00b5
        L_0x00ce:
            r1.e()     // Catch:{ all -> 0x00e4 }
            r0.remove()     // Catch:{ all -> 0x00e4 }
            long r3 = r10.c     // Catch:{ all -> 0x00e4 }
            r1 = 0
            long r3 = r3 + r11
            float r1 = (float) r3     // Catch:{ all -> 0x00e4 }
            int r3 = r10.e     // Catch:{ all -> 0x00e4 }
            float r3 = (float) r3     // Catch:{ all -> 0x00e4 }
            float r3 = r3 * r5
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b5
        L_0x00e2:
            monitor-exit(r2)     // Catch:{ all -> 0x00e4 }
            return
        L_0x00e4:
            r11 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00e4 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.net.http.DiskBasedCache.a(long):void");
    }

    private void a(DiskCacheEntry diskCacheEntry) {
        synchronized (this.b) {
            DiskCacheEntry diskCacheEntry2 = this.b.get(diskCacheEntry.d);
            if (diskCacheEntry2 == null) {
                this.c += diskCacheEntry.b;
            } else {
                this.c += diskCacheEntry.b - diskCacheEntry2.b;
            }
            this.b.put(diskCacheEntry.d, diskCacheEntry);
        }
    }

    private void b(DiskCacheEntry diskCacheEntry) {
        synchronized (this.b) {
            this.c -= diskCacheEntry.b;
            this.b.remove(diskCacheEntry.d);
        }
    }

    private static class DiskCacheEntry {

        /* renamed from: a  reason: collision with root package name */
        public File f2977a;
        public long b;
        public long c;
        public String d;
        public String e;
        public String f;
        public String g;
        public long h;
        public long i;
        public long j;
        public Map<String, String> k;
        private volatile boolean l;
        private volatile int m;

        private DiskCacheEntry() {
        }

        public static DiskCacheEntry a(File file) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    if (a((InputStream) fileInputStream) != DiskBasedCache.h) {
                        IOUtils.a((InputStream) fileInputStream);
                        return null;
                    }
                    DiskCacheEntry diskCacheEntry = new DiskCacheEntry();
                    diskCacheEntry.d = c(fileInputStream);
                    diskCacheEntry.e = c(fileInputStream);
                    if ("".equals(diskCacheEntry.e)) {
                        diskCacheEntry.e = null;
                    }
                    diskCacheEntry.f = c(fileInputStream);
                    if ("".equals(diskCacheEntry.f)) {
                        diskCacheEntry.f = null;
                    }
                    diskCacheEntry.g = c(fileInputStream);
                    if ("".equals(diskCacheEntry.g)) {
                        diskCacheEntry.g = null;
                    }
                    diskCacheEntry.h = b(fileInputStream);
                    diskCacheEntry.i = b(fileInputStream);
                    diskCacheEntry.j = b(fileInputStream);
                    diskCacheEntry.k = d(fileInputStream);
                    diskCacheEntry.c = fileInputStream.getChannel().position();
                    diskCacheEntry.f2977a = file;
                    diskCacheEntry.b = file.length();
                    IOUtils.a((InputStream) fileInputStream);
                    return diskCacheEntry;
                } catch (IOException unused) {
                    IOUtils.a((InputStream) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.a((InputStream) fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
                fileInputStream = null;
                IOUtils.a((InputStream) fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                IOUtils.a((InputStream) fileInputStream);
                throw th;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x010c  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0130  */
        /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static miuipub.net.http.DiskBasedCache.DiskCacheEntry a(java.io.File r10, java.lang.String r11, miuipub.net.http.Cache.Entry r12) {
            /*
                java.io.InputStream r0 = r12.f2973a
                r1 = 0
                r12.f2973a = r1     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                r2.<init>(r10)     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                r3 = 538182184(0x20140228, float:1.2536801E-19)
                a((java.io.OutputStream) r2, (int) r3)     // Catch:{ IOException -> 0x00e3 }
                a((java.io.OutputStream) r2, (java.lang.String) r11)     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r3 = r12.c     // Catch:{ IOException -> 0x00e3 }
                if (r3 != 0) goto L_0x001a
                java.lang.String r3 = ""
                goto L_0x001c
            L_0x001a:
                java.lang.String r3 = r12.c     // Catch:{ IOException -> 0x00e3 }
            L_0x001c:
                a((java.io.OutputStream) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r3 = r12.d     // Catch:{ IOException -> 0x00e3 }
                if (r3 != 0) goto L_0x0026
                java.lang.String r3 = ""
                goto L_0x0028
            L_0x0026:
                java.lang.String r3 = r12.d     // Catch:{ IOException -> 0x00e3 }
            L_0x0028:
                a((java.io.OutputStream) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r3 = r12.e     // Catch:{ IOException -> 0x00e3 }
                if (r3 != 0) goto L_0x0032
                java.lang.String r3 = ""
                goto L_0x0034
            L_0x0032:
                java.lang.String r3 = r12.e     // Catch:{ IOException -> 0x00e3 }
            L_0x0034:
                a((java.io.OutputStream) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x00e3 }
                long r3 = r12.f     // Catch:{ IOException -> 0x00e3 }
                a((java.io.OutputStream) r2, (long) r3)     // Catch:{ IOException -> 0x00e3 }
                long r3 = r12.g     // Catch:{ IOException -> 0x00e3 }
                a((java.io.OutputStream) r2, (long) r3)     // Catch:{ IOException -> 0x00e3 }
                long r3 = r12.h     // Catch:{ IOException -> 0x00e3 }
                a((java.io.OutputStream) r2, (long) r3)     // Catch:{ IOException -> 0x00e3 }
                java.util.Map<java.lang.String, java.lang.String> r3 = r12.i     // Catch:{ IOException -> 0x00e3 }
                a((java.util.Map<java.lang.String, java.lang.String>) r3, (java.io.OutputStream) r2)     // Catch:{ IOException -> 0x00e3 }
                r2.flush()     // Catch:{ IOException -> 0x00e3 }
                miuipub.net.http.DiskBasedCache$DiskCacheEntry r3 = new miuipub.net.http.DiskBasedCache$DiskCacheEntry     // Catch:{ IOException -> 0x00e3 }
                r3.<init>()     // Catch:{ IOException -> 0x00e3 }
                r3.d = r11     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r11 = r12.c     // Catch:{ IOException -> 0x00e3 }
                r3.e = r11     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r11 = r12.d     // Catch:{ IOException -> 0x00e3 }
                r3.f = r11     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r11 = r12.e     // Catch:{ IOException -> 0x00e3 }
                r3.g = r11     // Catch:{ IOException -> 0x00e3 }
                long r4 = r12.f     // Catch:{ IOException -> 0x00e3 }
                r3.h = r4     // Catch:{ IOException -> 0x00e3 }
                long r4 = r12.g     // Catch:{ IOException -> 0x00e3 }
                r3.i = r4     // Catch:{ IOException -> 0x00e3 }
                long r4 = r12.h     // Catch:{ IOException -> 0x00e3 }
                r3.j = r4     // Catch:{ IOException -> 0x00e3 }
                java.util.Map<java.lang.String, java.lang.String> r11 = r12.i     // Catch:{ IOException -> 0x00e3 }
                r3.k = r11     // Catch:{ IOException -> 0x00e3 }
                r3.f2977a = r10     // Catch:{ IOException -> 0x00e3 }
                java.nio.channels.FileChannel r11 = r2.getChannel()     // Catch:{ IOException -> 0x00e3 }
                long r4 = r11.position()     // Catch:{ IOException -> 0x00e3 }
                r3.c = r4     // Catch:{ IOException -> 0x00e3 }
                miuipub.util.IOUtils.a((java.io.InputStream) r0, (java.io.OutputStream) r2)     // Catch:{ IOException -> 0x00e3 }
                r2.flush()     // Catch:{ IOException -> 0x00e3 }
                java.nio.channels.FileChannel r11 = r2.getChannel()     // Catch:{ IOException -> 0x00e3 }
                long r4 = r11.position()     // Catch:{ IOException -> 0x00e3 }
                r3.b = r4     // Catch:{ IOException -> 0x00e3 }
                long r4 = r12.b     // Catch:{ IOException -> 0x00e3 }
                r6 = 0
                int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r11 > 0) goto L_0x009e
                long r4 = r3.b     // Catch:{ IOException -> 0x00e3 }
                long r6 = r3.c     // Catch:{ IOException -> 0x00e3 }
                r11 = 0
                long r4 = r4 - r6
                r12.b = r4     // Catch:{ IOException -> 0x00e3 }
                goto L_0x00aa
            L_0x009e:
                long r4 = r12.b     // Catch:{ IOException -> 0x00e3 }
                long r6 = r3.b     // Catch:{ IOException -> 0x00e3 }
                long r8 = r3.c     // Catch:{ IOException -> 0x00e3 }
                r11 = 0
                long r6 = r6 - r8
                int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r11 != 0) goto L_0x00bc
            L_0x00aa:
                r2.close()     // Catch:{ IOException -> 0x00e3 }
                miuipub.net.http.DiskBasedCache$CacheFileContentInputStream r11 = new miuipub.net.http.DiskBasedCache$CacheFileContentInputStream     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                r11.<init>(r3)     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                r12.f2973a = r11     // Catch:{ IOException -> 0x00e8, all -> 0x00e5 }
                miuipub.util.IOUtils.a((java.io.InputStream) r0)
                miuipub.util.IOUtils.a((java.io.OutputStream) r1)
                r1 = r3
                goto L_0x0122
            L_0x00bc:
                java.io.IOException r11 = new java.io.IOException     // Catch:{ IOException -> 0x00e3 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e3 }
                r4.<init>()     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r5 = "length not match "
                r4.append(r5)     // Catch:{ IOException -> 0x00e3 }
                long r5 = r12.b     // Catch:{ IOException -> 0x00e3 }
                r4.append(r5)     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r12 = ":"
                r4.append(r12)     // Catch:{ IOException -> 0x00e3 }
                long r5 = r3.b     // Catch:{ IOException -> 0x00e3 }
                long r7 = r3.c     // Catch:{ IOException -> 0x00e3 }
                r12 = 0
                long r5 = r5 - r7
                r4.append(r5)     // Catch:{ IOException -> 0x00e3 }
                java.lang.String r12 = r4.toString()     // Catch:{ IOException -> 0x00e3 }
                r11.<init>(r12)     // Catch:{ IOException -> 0x00e3 }
                throw r11     // Catch:{ IOException -> 0x00e3 }
            L_0x00e3:
                r11 = move-exception
                goto L_0x00ea
            L_0x00e5:
                r11 = move-exception
                r2 = r1
                goto L_0x0124
            L_0x00e8:
                r11 = move-exception
                r2 = r1
            L_0x00ea:
                java.lang.String r12 = "DisBasedCache"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
                r3.<init>()     // Catch:{ all -> 0x0123 }
                java.lang.String r4 = "Cannot save cache to disk file "
                r3.append(r4)     // Catch:{ all -> 0x0123 }
                r3.append(r10)     // Catch:{ all -> 0x0123 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0123 }
                android.util.Log.w(r12, r3, r11)     // Catch:{ all -> 0x0123 }
                miuipub.util.IOUtils.a((java.io.InputStream) r0)
                miuipub.util.IOUtils.a((java.io.OutputStream) r2)
                boolean r11 = r10.delete()
                if (r11 != 0) goto L_0x0122
                java.lang.String r11 = "DisBasedCache"
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                r12.<init>()
                java.lang.String r0 = "Cannot delete file "
                r12.append(r0)
                r12.append(r10)
                java.lang.String r10 = r12.toString()
                android.util.Log.e(r11, r10)
            L_0x0122:
                return r1
            L_0x0123:
                r11 = move-exception
            L_0x0124:
                miuipub.util.IOUtils.a((java.io.InputStream) r0)
                miuipub.util.IOUtils.a((java.io.OutputStream) r2)
                boolean r12 = r10.delete()
                if (r12 != 0) goto L_0x0146
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                r12.<init>()
                java.lang.String r0 = "Cannot delete file "
                r12.append(r0)
                r12.append(r10)
                java.lang.String r10 = r12.toString()
                java.lang.String r12 = "DisBasedCache"
                android.util.Log.e(r12, r10)
            L_0x0146:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.net.http.DiskBasedCache.DiskCacheEntry.a(java.io.File, java.lang.String, miuipub.net.http.Cache$Entry):miuipub.net.http.DiskBasedCache$DiskCacheEntry");
        }

        public Cache.Entry a() {
            try {
                Cache.Entry entry = new Cache.Entry();
                entry.f2973a = new FileInputStream(this.f2977a);
                if (entry.f2973a.skip(this.c) != this.c) {
                    return null;
                }
                entry.b = this.b - this.c;
                entry.c = this.e;
                entry.d = this.f;
                entry.e = this.g;
                entry.f = this.h;
                entry.g = this.i;
                entry.h = this.j;
                entry.i = this.k;
                return entry;
            } catch (IOException unused) {
                return null;
            }
        }

        public synchronized void b() {
            this.m++;
        }

        public synchronized boolean c() {
            return this.m > 0;
        }

        public synchronized void d() {
            this.m--;
            if (this.m <= 0 && this.l && !this.f2977a.delete()) {
                Log.e(DiskBasedCache.f2975a, "Cannot delete file " + this.f2977a);
            }
        }

        public synchronized void e() {
            if (this.m > 0) {
                this.l = true;
            } else if (!this.f2977a.delete()) {
                Log.e(DiskBasedCache.f2975a, "Cannot delete file " + this.f2977a);
            }
        }

        private static int e(InputStream inputStream) throws IOException {
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            throw new EOFException();
        }

        static void a(OutputStream outputStream, int i2) throws IOException {
            outputStream.write(i2 & 255);
            outputStream.write((i2 >> 8) & 255);
            outputStream.write((i2 >> 16) & 255);
            outputStream.write((i2 >> 24) & 255);
        }

        static int a(InputStream inputStream) throws IOException {
            return (e(inputStream) << 24) | e(inputStream) | 0 | (e(inputStream) << 8) | (e(inputStream) << 16);
        }

        static void a(OutputStream outputStream, long j2) throws IOException {
            outputStream.write((byte) ((int) j2));
            outputStream.write((byte) ((int) (j2 >>> 8)));
            outputStream.write((byte) ((int) (j2 >>> 16)));
            outputStream.write((byte) ((int) (j2 >>> 24)));
            outputStream.write((byte) ((int) (j2 >>> 32)));
            outputStream.write((byte) ((int) (j2 >>> 40)));
            outputStream.write((byte) ((int) (j2 >>> 48)));
            outputStream.write((byte) ((int) (j2 >>> 56)));
        }

        static long b(InputStream inputStream) throws IOException {
            return (((long) e(inputStream)) & 255) | 0 | ((((long) e(inputStream)) & 255) << 8) | ((((long) e(inputStream)) & 255) << 16) | ((((long) e(inputStream)) & 255) << 24) | ((((long) e(inputStream)) & 255) << 32) | ((((long) e(inputStream)) & 255) << 40) | ((((long) e(inputStream)) & 255) << 48) | ((255 & ((long) e(inputStream))) << 56);
        }

        static void a(OutputStream outputStream, String str) throws IOException {
            byte[] bytes = str.getBytes("UTF-8");
            a(outputStream, (long) bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }

        static String c(InputStream inputStream) throws IOException {
            int b2 = (int) b(inputStream);
            if (b2 < 0 || b2 > 8192) {
                throw new IOException("Malformed data structure encountered");
            }
            byte[] bArr = new byte[b2];
            int i2 = 0;
            while (i2 < b2) {
                int read = inputStream.read(bArr, i2, b2 - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == b2) {
                return new String(bArr, "UTF-8");
            }
            throw new IOException("Expected " + b2 + " bytes but read " + i2 + " bytes");
        }

        static void a(Map<String, String> map, OutputStream outputStream) throws IOException {
            if (map == null || map.size() == 0) {
                a(outputStream, 0);
                return;
            }
            a(outputStream, map.size());
            for (Map.Entry next : map.entrySet()) {
                a(outputStream, (String) next.getKey());
                a(outputStream, (String) next.getValue());
            }
        }

        static Map<String, String> d(InputStream inputStream) throws IOException {
            int a2 = a(inputStream);
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < a2; i2++) {
                hashMap.put(c(inputStream).intern(), c(inputStream).intern());
            }
            return hashMap;
        }
    }

    private static class CacheFileContentInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private DiskCacheEntry f2976a;

        protected CacheFileContentInputStream(DiskCacheEntry diskCacheEntry) throws IOException {
            super(a(diskCacheEntry));
            if (this.in != null) {
                this.f2976a = diskCacheEntry;
            }
        }

        private static InputStream a(DiskCacheEntry diskCacheEntry) throws IOException {
            diskCacheEntry.b();
            try {
                FileInputStream fileInputStream = new FileInputStream(diskCacheEntry.f2977a);
                if (fileInputStream.skip(diskCacheEntry.c) == diskCacheEntry.c) {
                    return fileInputStream;
                }
                throw new IOException("skip failed for file " + diskCacheEntry.f2977a.getName());
            } catch (Throwable th) {
                diskCacheEntry.d();
                throw th;
            }
        }

        public void close() throws IOException {
            this.f2976a.d();
            super.close();
        }
    }
}
