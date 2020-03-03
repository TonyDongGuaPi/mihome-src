package com.lidroid.xutils.bitmap.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.cache.LruDiskCache;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BitmapCache {

    /* renamed from: a  reason: collision with root package name */
    private final int f6297a = 0;
    private LruDiskCache b;
    private LruMemoryCache<MemoryCacheKey, Bitmap> c;
    private final Object d = new Object();
    private BitmapGlobalConfig e;

    public BitmapCache(BitmapGlobalConfig bitmapGlobalConfig) {
        if (bitmapGlobalConfig != null) {
            this.e = bitmapGlobalConfig;
            return;
        }
        throw new IllegalArgumentException("globalConfig may not be null");
    }

    public void a() {
        if (this.e.l()) {
            if (this.c != null) {
                try {
                    d();
                } catch (Throwable unused) {
                }
            }
            this.c = new LruMemoryCache<MemoryCacheKey, Bitmap>(this.e.g()) {
                /* access modifiers changed from: protected */
                public int a(MemoryCacheKey memoryCacheKey, Bitmap bitmap) {
                    if (bitmap == null) {
                        return 0;
                    }
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
    }

    public void b() {
        synchronized (this.d) {
            if (this.e.m() && (this.b == null || this.b.d())) {
                File file = new File(this.e.a());
                if (file.exists() || file.mkdirs()) {
                    long a2 = OtherUtils.a(file);
                    long h = (long) this.e.h();
                    if (a2 > h) {
                        a2 = h;
                    }
                    try {
                        this.b = LruDiskCache.a(file, 1, 1, a2);
                        this.b.a(this.e.n());
                        LogUtils.a("create disk cache success");
                    } catch (Throwable th) {
                        this.b = null;
                        LogUtils.b("create disk cache error", th);
                    }
                }
            }
        }
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void b(int i) {
        synchronized (this.d) {
            if (this.b != null) {
                this.b.a((long) i);
            }
        }
    }

    public void a(FileNameGenerator fileNameGenerator) {
        synchronized (this.d) {
            if (!(this.b == null || fileNameGenerator == null)) {
                this.b.a(fileNameGenerator);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v17, resolved type: com.lidroid.xutils.cache.LruDiskCache$Editor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v23, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v28, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v30, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: java.io.OutputStream} */
    /* JADX WARNING: type inference failed for: r10v13, types: [android.graphics.Bitmap] */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0083, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0084, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c8, code lost:
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00df, code lost:
        r0 = th;
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e9, code lost:
        r9 = null;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ec, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ed, code lost:
        r9 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        com.lidroid.xutils.util.LogUtils.b(r0.getMessage(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00f6, code lost:
        com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r10);
        com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00fc, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00fd, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00fe, code lost:
        r11 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[Catch:{ Throwable -> 0x0083, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0099 A[Catch:{ Throwable -> 0x00c7, all -> 0x00c5, Throwable -> 0x00df }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c5 A[Catch:{ Throwable -> 0x00c7, all -> 0x00c5, Throwable -> 0x00df }, ExcHandler: all (th java.lang.Throwable), PHI: r9 r11 
      PHI: (r9v7 com.lidroid.xutils.cache.LruDiskCache$Snapshot) = (r9v8 com.lidroid.xutils.cache.LruDiskCache$Snapshot), (r9v12 com.lidroid.xutils.cache.LruDiskCache$Snapshot), (r9v12 com.lidroid.xutils.cache.LruDiskCache$Snapshot), (r9v11 com.lidroid.xutils.cache.LruDiskCache$Snapshot), (r9v11 com.lidroid.xutils.cache.LruDiskCache$Snapshot), (r9v11 com.lidroid.xutils.cache.LruDiskCache$Snapshot) binds: [B:42:0x008c, B:33:0x0079, B:34:?, B:13:0x002b, B:17:0x0037, B:23:0x0053] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v6 java.io.OutputStream) = (r11v7 java.io.OutputStream), (r11v9 java.io.OutputStream), (r11v9 java.io.OutputStream), (r11v13 java.io.OutputStream), (r11v13 java.io.OutputStream) binds: [B:42:0x008c, B:33:0x0079, B:34:?, B:17:0x0037, B:23:0x0053] A[DONT_GENERATE, DONT_INLINE], Splitter:B:17:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ca A[Catch:{ Throwable -> 0x00c7, all -> 0x00c5, Throwable -> 0x00df }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ce A[Catch:{ Throwable -> 0x00c7, all -> 0x00c5, Throwable -> 0x00df }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e8 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000d] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap a(java.lang.String r16, com.lidroid.xutils.bitmap.BitmapDisplayConfig r17, com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?> r18) {
        /*
            r15 = this;
            r7 = r15
            r2 = r16
            r3 = r17
            r1 = r18
            com.lidroid.xutils.bitmap.core.BitmapCache$BitmapMeta r4 = new com.lidroid.xutils.bitmap.core.BitmapCache$BitmapMeta
            r8 = 0
            r4.<init>(r15, r8)
            com.lidroid.xutils.bitmap.BitmapGlobalConfig r0 = r7.e     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            boolean r0 = r0.m()     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r5 = 0
            if (r0 == 0) goto L_0x0094
            com.lidroid.xutils.cache.LruDiskCache r0 = r7.b     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            if (r0 != 0) goto L_0x001e
            r15.b()     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
        L_0x001e:
            com.lidroid.xutils.cache.LruDiskCache r0 = r7.b     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            if (r0 == 0) goto L_0x0094
            com.lidroid.xutils.cache.LruDiskCache r0 = r7.b     // Catch:{ Throwable -> 0x0088, all -> 0x00e8 }
            com.lidroid.xutils.cache.LruDiskCache$Snapshot r9 = r0.b((java.lang.String) r2)     // Catch:{ Throwable -> 0x0088, all -> 0x00e8 }
            r0 = 0
            if (r9 != 0) goto L_0x006a
            com.lidroid.xutils.cache.LruDiskCache r10 = r7.b     // Catch:{ Throwable -> 0x0067, all -> 0x0063 }
            com.lidroid.xutils.cache.LruDiskCache$Editor r10 = r10.c((java.lang.String) r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0063 }
            if (r10 == 0) goto L_0x006a
            java.io.OutputStream r11 = r10.c(r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0063 }
            com.lidroid.xutils.bitmap.BitmapGlobalConfig r12 = r7.e     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            com.lidroid.xutils.bitmap.download.Downloader r12 = r12.b()     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            long r12 = r12.a(r2, r11, r1)     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            r4.c = r12     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            long r12 = r4.c     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            int r14 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r14 >= 0) goto L_0x0053
            r10.b()     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r11)
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9)
            return r8
        L_0x0053:
            long r12 = r4.c     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            r10.a((long) r12)     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            r10.a()     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            com.lidroid.xutils.cache.LruDiskCache r10 = r7.b     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            com.lidroid.xutils.cache.LruDiskCache$Snapshot r10 = r10.b((java.lang.String) r2)     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            r9 = r10
            goto L_0x006b
        L_0x0063:
            r0 = move-exception
            r11 = r8
            goto L_0x00ff
        L_0x0067:
            r0 = move-exception
            r10 = r8
            goto L_0x008b
        L_0x006a:
            r11 = r8
        L_0x006b:
            if (r9 == 0) goto L_0x0086
            java.io.FileInputStream r0 = r9.a(r0)     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            r4.f6299a = r0     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            android.graphics.Bitmap r10 = r15.a((com.lidroid.xutils.bitmap.core.BitmapCache.BitmapMeta) r4, (com.lidroid.xutils.bitmap.BitmapDisplayConfig) r3)     // Catch:{ Throwable -> 0x0083, all -> 0x00c5 }
            if (r10 != 0) goto L_0x0097
            r4.f6299a = r8     // Catch:{ Throwable -> 0x0081, all -> 0x00c5 }
            com.lidroid.xutils.cache.LruDiskCache r0 = r7.b     // Catch:{ Throwable -> 0x0081, all -> 0x00c5 }
            r0.d((java.lang.String) r2)     // Catch:{ Throwable -> 0x0081, all -> 0x00c5 }
            goto L_0x0097
        L_0x0081:
            r0 = move-exception
            goto L_0x008c
        L_0x0083:
            r0 = move-exception
            r10 = r8
            goto L_0x008c
        L_0x0086:
            r10 = r8
            goto L_0x0097
        L_0x0088:
            r0 = move-exception
            r9 = r8
            r10 = r9
        L_0x008b:
            r11 = r10
        L_0x008c:
            java.lang.String r12 = r0.getMessage()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c5 }
            com.lidroid.xutils.util.LogUtils.b(r12, r0)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c5 }
            goto L_0x0097
        L_0x0094:
            r9 = r8
            r10 = r9
            r11 = r10
        L_0x0097:
            if (r10 != 0) goto L_0x00ca
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00c7, all -> 0x00c5 }
            r10.<init>()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c5 }
            com.lidroid.xutils.bitmap.BitmapGlobalConfig r0 = r7.e     // Catch:{ Throwable -> 0x00df }
            com.lidroid.xutils.bitmap.download.Downloader r0 = r0.b()     // Catch:{ Throwable -> 0x00df }
            long r0 = r0.a(r2, r10, r1)     // Catch:{ Throwable -> 0x00df }
            r4.c = r0     // Catch:{ Throwable -> 0x00df }
            long r0 = r4.c     // Catch:{ Throwable -> 0x00df }
            int r11 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r11 >= 0) goto L_0x00b7
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r10)
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9)
            return r8
        L_0x00b7:
            r0 = r10
            java.io.ByteArrayOutputStream r0 = (java.io.ByteArrayOutputStream) r0     // Catch:{ Throwable -> 0x00df }
            byte[] r0 = r0.toByteArray()     // Catch:{ Throwable -> 0x00df }
            r4.b = r0     // Catch:{ Throwable -> 0x00df }
            android.graphics.Bitmap r0 = r15.a((com.lidroid.xutils.bitmap.core.BitmapCache.BitmapMeta) r4, (com.lidroid.xutils.bitmap.BitmapDisplayConfig) r3)     // Catch:{ Throwable -> 0x00df }
            goto L_0x00cc
        L_0x00c5:
            r0 = move-exception
            goto L_0x00ff
        L_0x00c7:
            r0 = move-exception
            r10 = r11
            goto L_0x00ef
        L_0x00ca:
            r0 = r10
            r10 = r11
        L_0x00cc:
            if (r0 == 0) goto L_0x00e1
            android.graphics.Bitmap r0 = r15.a((java.lang.String) r2, (com.lidroid.xutils.bitmap.BitmapDisplayConfig) r3, (android.graphics.Bitmap) r0)     // Catch:{ Throwable -> 0x00df }
            long r5 = r4.c     // Catch:{ Throwable -> 0x00df }
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r0
            android.graphics.Bitmap r0 = r1.a(r2, r3, r4, r5)     // Catch:{ Throwable -> 0x00df }
            goto L_0x00e1
        L_0x00df:
            r0 = move-exception
            goto L_0x00ef
        L_0x00e1:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r10)
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9)
            return r0
        L_0x00e8:
            r0 = move-exception
            r9 = r8
            r11 = r9
            goto L_0x00ff
        L_0x00ec:
            r0 = move-exception
            r9 = r8
            r10 = r9
        L_0x00ef:
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x00fd }
            com.lidroid.xutils.util.LogUtils.b(r1, r0)     // Catch:{ all -> 0x00fd }
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r10)
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9)
            return r8
        L_0x00fd:
            r0 = move-exception
            r11 = r10
        L_0x00ff:
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r11)
            com.lidroid.xutils.util.IOUtils.a((java.io.Closeable) r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.bitmap.core.BitmapCache.a(java.lang.String, com.lidroid.xutils.bitmap.BitmapDisplayConfig, com.lidroid.xutils.BitmapUtils$BitmapLoadTask):android.graphics.Bitmap");
    }

    private Bitmap a(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap, long j) throws IOException {
        BitmapFactory h;
        if (!(bitmapDisplayConfig == null || (h = bitmapDisplayConfig.h()) == null)) {
            bitmap = h.a().a(bitmap);
        }
        if (!(str == null || bitmap == null || !this.e.l() || this.c == null)) {
            this.c.a(new MemoryCacheKey(this, str, bitmapDisplayConfig, (MemoryCacheKey) null), bitmap, j);
        }
        return bitmap;
    }

    public Bitmap a(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        if (this.c == null || !this.e.l()) {
            return null;
        }
        return this.c.a(new MemoryCacheKey(this, str, bitmapDisplayConfig, (MemoryCacheKey) null));
    }

    public File a(String str) {
        synchronized (this.d) {
            if (this.b == null) {
                return null;
            }
            File a2 = this.b.a(str, 0);
            return a2;
        }
    }

    public Bitmap b(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        LruDiskCache.Snapshot snapshot;
        Bitmap bitmap;
        if (str == null || !this.e.m()) {
            return null;
        }
        if (this.b == null) {
            b();
        }
        if (this.b != null) {
            try {
                snapshot = this.b.b(str);
                if (snapshot != null) {
                    if (bitmapDisplayConfig != null) {
                        try {
                            if (!bitmapDisplayConfig.f()) {
                                bitmap = BitmapDecoder.a(snapshot.a(0).getFD(), bitmapDisplayConfig.a(), bitmapDisplayConfig.g());
                                Bitmap a2 = a(str, bitmapDisplayConfig, a(str, bitmapDisplayConfig, bitmap), this.b.a(str));
                                IOUtils.a((Closeable) snapshot);
                                return a2;
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                LogUtils.b(th.getMessage(), th);
                                IOUtils.a((Closeable) snapshot);
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                    bitmap = BitmapDecoder.a(snapshot.a(0).getFD());
                    Bitmap a22 = a(str, bitmapDisplayConfig, a(str, bitmapDisplayConfig, bitmap), this.b.a(str));
                    IOUtils.a((Closeable) snapshot);
                    return a22;
                }
            } catch (Throwable th3) {
                th = th3;
                snapshot = null;
                IOUtils.a((Closeable) snapshot);
                throw th;
            }
            IOUtils.a((Closeable) snapshot);
        }
        return null;
    }

    public void c() {
        d();
        e();
    }

    public void d() {
        if (this.c != null) {
            this.c.a();
        }
    }

    public void e() {
        synchronized (this.d) {
            if (this.b != null && !this.b.d()) {
                try {
                    this.b.f();
                    this.b.close();
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
                this.b = null;
            }
        }
        b();
    }

    public void b(String str) {
        c(str);
        d(str);
    }

    public void c(String str) {
        MemoryCacheKey memoryCacheKey = new MemoryCacheKey(this, str, (BitmapDisplayConfig) null, (MemoryCacheKey) null);
        if (this.c != null) {
            while (this.c.c(memoryCacheKey)) {
                this.c.b(memoryCacheKey);
            }
        }
    }

    public void d(String str) {
        synchronized (this.d) {
            if (this.b != null && !this.b.d()) {
                try {
                    this.b.d(str);
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
            }
        }
    }

    public void f() {
        synchronized (this.d) {
            if (this.b != null) {
                try {
                    this.b.e();
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
            }
        }
    }

    public void g() {
        synchronized (this.d) {
            if (this.b != null) {
                try {
                    if (!this.b.d()) {
                        this.b.close();
                    }
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
                this.b = null;
            }
        }
    }

    private class BitmapMeta {

        /* renamed from: a  reason: collision with root package name */
        public FileInputStream f6299a;
        public byte[] b;
        public long c;

        private BitmapMeta() {
        }

        /* synthetic */ BitmapMeta(BitmapCache bitmapCache, BitmapMeta bitmapMeta) {
            this();
        }
    }

    private Bitmap a(BitmapMeta bitmapMeta, BitmapDisplayConfig bitmapDisplayConfig) throws IOException {
        if (bitmapMeta == null) {
            return null;
        }
        if (bitmapMeta.f6299a != null) {
            if (bitmapDisplayConfig == null || bitmapDisplayConfig.f()) {
                return BitmapDecoder.a(bitmapMeta.f6299a.getFD());
            }
            return BitmapDecoder.a(bitmapMeta.f6299a.getFD(), bitmapDisplayConfig.a(), bitmapDisplayConfig.g());
        } else if (bitmapMeta.b == null) {
            return null;
        } else {
            if (bitmapDisplayConfig == null || bitmapDisplayConfig.f()) {
                return BitmapDecoder.a(bitmapMeta.b);
            }
            return BitmapDecoder.a(bitmapMeta.b, bitmapDisplayConfig.a(), bitmapDisplayConfig.g());
        }
    }

    private synchronized Bitmap a(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap) {
        Bitmap bitmap2;
        File a2;
        if (bitmapDisplayConfig != null) {
            if (bitmapDisplayConfig.e() && (a2 = a(str)) != null && a2.exists()) {
                try {
                    int i = 0;
                    int attributeInt = new ExifInterface(a2.getPath()).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 0);
                    if (attributeInt == 3) {
                        i = 180;
                    } else if (attributeInt == 6) {
                        i = 90;
                    } else if (attributeInt == 8) {
                        i = 270;
                    }
                    if (i != 0) {
                        Matrix matrix = new Matrix();
                        matrix.postRotate((float) i);
                        bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        bitmap.recycle();
                    }
                } catch (Throwable unused) {
                    return bitmap;
                }
            }
        }
        bitmap2 = bitmap;
        return bitmap2;
    }

    public class MemoryCacheKey {
        private String b;
        private String c;

        private MemoryCacheKey(String str, BitmapDisplayConfig bitmapDisplayConfig) {
            String str2;
            this.b = str;
            if (bitmapDisplayConfig == null) {
                str2 = null;
            } else {
                str2 = bitmapDisplayConfig.toString();
            }
            this.c = str2;
        }

        /* synthetic */ MemoryCacheKey(BitmapCache bitmapCache, String str, BitmapDisplayConfig bitmapDisplayConfig, MemoryCacheKey memoryCacheKey) {
            this(str, bitmapDisplayConfig);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MemoryCacheKey)) {
                return false;
            }
            MemoryCacheKey memoryCacheKey = (MemoryCacheKey) obj;
            if (!this.b.equals(memoryCacheKey.b)) {
                return false;
            }
            if (this.c == null || memoryCacheKey.c == null) {
                return true;
            }
            return this.c.equals(memoryCacheKey.c);
        }

        public int hashCode() {
            return this.b.hashCode();
        }
    }
}
