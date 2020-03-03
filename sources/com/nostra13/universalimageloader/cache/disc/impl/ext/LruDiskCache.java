package com.nostra13.universalimageloader.cache.disc.impl.ext;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LruDiskCache implements DiskCache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8440a = 32768;
    public static final Bitmap.CompressFormat b = Bitmap.CompressFormat.PNG;
    public static final int c = 100;
    private static final String i = " argument must be not null";
    private static final String j = " argument must be positive number";
    protected DiskLruCache d;
    protected final FileNameGenerator e;
    protected int f;
    protected Bitmap.CompressFormat g;
    protected int h;
    private File k;

    public LruDiskCache(File file, FileNameGenerator fileNameGenerator, long j2) throws IOException {
        this(file, (File) null, fileNameGenerator, j2, 0);
    }

    public LruDiskCache(File file, File file2, FileNameGenerator fileNameGenerator, long j2, int i2) throws IOException {
        this.f = 32768;
        this.g = b;
        this.h = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j2 < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (fileNameGenerator != null) {
            long j3 = j2 == 0 ? Long.MAX_VALUE : j2;
            int i3 = i2 == 0 ? Integer.MAX_VALUE : i2;
            this.k = file2;
            this.e = fileNameGenerator;
            a(file, file2, j3, i3);
        } else {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        }
    }

    private void a(File file, File file2, long j2, int i2) throws IOException {
        try {
            this.d = DiskLruCache.a(file, 1, 1, j2, i2);
        } catch (IOException e2) {
            L.a((Throwable) e2);
            if (file2 != null) {
                a(file2, (File) null, j2, i2);
            }
            if (this.d == null) {
                throw e2;
            }
        }
    }

    public File a() {
        return this.d.a();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File a(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache r1 = r3.d     // Catch:{ IOException -> 0x001e, all -> 0x001c }
            java.lang.String r4 = r3.c(r4)     // Catch:{ IOException -> 0x001e, all -> 0x001c }
            com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot r4 = r1.a((java.lang.String) r4)     // Catch:{ IOException -> 0x001e, all -> 0x001c }
            if (r4 != 0) goto L_0x000e
            goto L_0x0014
        L_0x000e:
            r1 = 0
            java.io.File r1 = r4.a(r1)     // Catch:{ IOException -> 0x001a }
            r0 = r1
        L_0x0014:
            if (r4 == 0) goto L_0x0019
            r4.close()
        L_0x0019:
            return r0
        L_0x001a:
            r1 = move-exception
            goto L_0x0020
        L_0x001c:
            r4 = move-exception
            goto L_0x002d
        L_0x001e:
            r1 = move-exception
            r4 = r0
        L_0x0020:
            com.nostra13.universalimageloader.utils.L.a((java.lang.Throwable) r1)     // Catch:{ all -> 0x0029 }
            if (r4 == 0) goto L_0x0028
            r4.close()
        L_0x0028:
            return r0
        L_0x0029:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L_0x002d:
            if (r0 == 0) goto L_0x0032
            r0.close()
        L_0x0032:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache.a(java.lang.String):java.io.File");
    }

    public boolean a(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException {
        DiskLruCache.Editor b2 = this.d.b(c(str));
        if (b2 == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b2.c(0), this.f);
        try {
            boolean a2 = IoUtils.a(inputStream, bufferedOutputStream, copyListener, this.f);
            IoUtils.a((Closeable) bufferedOutputStream);
            if (a2) {
                b2.a();
            } else {
                b2.b();
            }
            return a2;
        } catch (Throwable th) {
            IoUtils.a((Closeable) bufferedOutputStream);
            b2.b();
            throw th;
        }
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        DiskLruCache.Editor b2 = this.d.b(c(str));
        if (b2 == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b2.c(0), this.f);
        try {
            boolean compress = bitmap.compress(this.g, this.h, bufferedOutputStream);
            if (compress) {
                b2.a();
            } else {
                b2.b();
            }
            return compress;
        } finally {
            IoUtils.a((Closeable) bufferedOutputStream);
        }
    }

    public boolean b(String str) {
        try {
            return this.d.c(c(str));
        } catch (IOException e2) {
            L.a((Throwable) e2);
            return false;
        }
    }

    public void b() {
        try {
            this.d.close();
        } catch (IOException e2) {
            L.a((Throwable) e2);
        }
        this.d = null;
    }

    public void c() {
        try {
            this.d.h();
        } catch (IOException e2) {
            L.a((Throwable) e2);
        }
        try {
            a(this.d.a(), this.k, this.d.b(), this.d.c());
        } catch (IOException e3) {
            L.a((Throwable) e3);
        }
    }

    private String c(String str) {
        return this.e.a(str);
    }

    public void a(int i2) {
        this.f = i2;
    }

    public void a(Bitmap.CompressFormat compressFormat) {
        this.g = compressFormat;
    }

    public void b(int i2) {
        this.h = i2;
    }
}
