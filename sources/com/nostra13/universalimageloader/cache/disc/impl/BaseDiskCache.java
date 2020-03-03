package com.nostra13.universalimageloader.cache.disc.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class BaseDiskCache implements DiskCache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8433a = 32768;
    public static final Bitmap.CompressFormat b = Bitmap.CompressFormat.PNG;
    public static final int c = 100;
    private static final String j = " argument must be not null";
    private static final String k = ".tmp";
    protected final File d;
    protected final File e;
    protected final FileNameGenerator f;
    protected int g;
    protected Bitmap.CompressFormat h;
    protected int i;

    public void b() {
    }

    public BaseDiskCache(File file) {
        this(file, (File) null);
    }

    public BaseDiskCache(File file, File file2) {
        this(file, file2, DefaultConfigurationFactory.b());
    }

    public BaseDiskCache(File file, File file2, FileNameGenerator fileNameGenerator) {
        this.g = 32768;
        this.h = b;
        this.i = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (fileNameGenerator != null) {
            this.d = file;
            this.e = file2;
            this.f = fileNameGenerator;
        } else {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        }
    }

    public File a() {
        return this.d;
    }

    public File a(String str) {
        return c(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, java.io.InputStream r7, com.nostra13.universalimageloader.utils.IoUtils.CopyListener r8) throws java.io.IOException {
        /*
            r5 = this;
            java.io.File r6 = r5.c(r6)
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r6.getAbsolutePath()
            r1.append(r2)
            java.lang.String r2 = ".tmp"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x004a }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x004a }
            r3.<init>(r0)     // Catch:{ all -> 0x004a }
            int r4 = r5.g     // Catch:{ all -> 0x004a }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x004a }
            int r3 = r5.g     // Catch:{ all -> 0x0045 }
            boolean r7 = com.nostra13.universalimageloader.utils.IoUtils.a(r7, r2, r8, r3)     // Catch:{ all -> 0x0045 }
            com.nostra13.universalimageloader.utils.IoUtils.a((java.io.Closeable) r2)     // Catch:{ all -> 0x0043 }
            if (r7 == 0) goto L_0x003d
            boolean r6 = r0.renameTo(r6)
            if (r6 != 0) goto L_0x003d
            r7 = 0
        L_0x003d:
            if (r7 != 0) goto L_0x0042
            r0.delete()
        L_0x0042:
            return r7
        L_0x0043:
            r8 = move-exception
            goto L_0x004c
        L_0x0045:
            r7 = move-exception
            com.nostra13.universalimageloader.utils.IoUtils.a((java.io.Closeable) r2)     // Catch:{ all -> 0x004a }
            throw r7     // Catch:{ all -> 0x004a }
        L_0x004a:
            r8 = move-exception
            r7 = 0
        L_0x004c:
            if (r7 == 0) goto L_0x0055
            boolean r6 = r0.renameTo(r6)
            if (r6 != 0) goto L_0x0055
            r7 = 0
        L_0x0055:
            if (r7 != 0) goto L_0x005a
            r0.delete()
        L_0x005a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.BaseDiskCache.a(java.lang.String, java.io.InputStream, com.nostra13.universalimageloader.utils.IoUtils$CopyListener):boolean");
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        File c2 = c(str);
        File file = new File(c2.getAbsolutePath() + ".tmp");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.g);
        try {
            boolean compress = bitmap.compress(this.h, this.i, bufferedOutputStream);
            IoUtils.a((Closeable) bufferedOutputStream);
            if (compress && !file.renameTo(c2)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            IoUtils.a((Closeable) bufferedOutputStream);
            file.delete();
            throw th;
        }
    }

    public boolean b(String str) {
        return c(str).delete();
    }

    public void c() {
        File[] listFiles = this.d.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public File c(String str) {
        String a2 = this.f.a(str);
        File file = this.d;
        if (!this.d.exists() && !this.d.mkdirs() && this.e != null && (this.e.exists() || this.e.mkdirs())) {
            file = this.e;
        }
        return new File(file, a2);
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(Bitmap.CompressFormat compressFormat) {
        this.h = compressFormat;
    }

    public void b(int i2) {
        this.i = i2;
    }
}
