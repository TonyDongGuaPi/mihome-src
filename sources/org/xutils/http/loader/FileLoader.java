package org.xutils.http.loader;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import org.cybergarage.http.HTTP;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.DiskCacheFile;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

public class FileLoader extends Loader<File> {
    private static final int c = 512;
    private String d;
    private String e;
    private boolean f;
    private boolean g;
    private long h;
    private String i;
    private DiskCacheFile j;

    public void b(UriRequest uriRequest) {
    }

    public Loader<File> a() {
        return new FileLoader();
    }

    public void a(RequestParams requestParams) {
        if (requestParams != null) {
            this.f10784a = requestParams;
            this.f = requestParams.z();
            this.g = requestParams.A();
        }
    }

    /* renamed from: a */
    public File b(InputStream inputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        FileInputStream fileInputStream;
        InputStream inputStream2 = inputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            File file = new File(this.d);
            if (file.isDirectory()) {
                IOUtil.a(file);
            }
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (parentFile.exists() || parentFile.mkdirs()) {
                    file.createNewFile();
                }
            }
            long length = file.length();
            if (this.f && length > 0) {
                long j2 = length - 512;
                if (j2 > 0) {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = null;
                        IOUtil.a((Closeable) fileInputStream);
                        throw th;
                    }
                    try {
                        if (Arrays.equals(IOUtil.a(inputStream2, 0, 512), IOUtil.a((InputStream) fileInputStream, j2, 512))) {
                            this.h -= 512;
                            IOUtil.a((Closeable) fileInputStream);
                        } else {
                            IOUtil.a((Closeable) fileInputStream);
                            IOUtil.a(file);
                            throw new RuntimeException("need retry");
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtil.a((Closeable) fileInputStream);
                        throw th;
                    }
                } else {
                    IOUtil.a(file);
                    throw new RuntimeException("need retry");
                }
            }
            if (this.f) {
                fileOutputStream = new FileOutputStream(file, true);
            } else {
                fileOutputStream = new FileOutputStream(file);
                length = 0;
            }
            long j3 = this.h + length;
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream2;
                IOUtil.a((Closeable) bufferedInputStream);
                IOUtil.a((Closeable) bufferedOutputStream2);
                throw th;
            }
            try {
                if (this.b != null) {
                    bufferedOutputStream2 = bufferedOutputStream;
                    if (!this.b.a(j3, length, true)) {
                        throw new Callback.CancelledException("download stopped!");
                    }
                } else {
                    bufferedOutputStream2 = bufferedOutputStream;
                }
                byte[] bArr = new byte[4096];
                long j4 = length;
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        BufferedInputStream bufferedInputStream3 = bufferedInputStream2;
                        bufferedOutputStream2.flush();
                        if (this.j != null) {
                            file = this.j.commit();
                        }
                        if (this.b != null) {
                            this.b.a(j3, j4, true);
                        }
                        IOUtil.a((Closeable) bufferedInputStream3);
                        IOUtil.a((Closeable) bufferedOutputStream2);
                        return a(file);
                    } else if (file.getParentFile().exists()) {
                        bufferedOutputStream2.write(bArr, 0, read);
                        long j5 = ((long) read) + j4;
                        if (this.b != null) {
                            bufferedInputStream = bufferedInputStream2;
                            try {
                                if (!this.b.a(j3, j5, false)) {
                                    bufferedOutputStream2.flush();
                                    throw new Callback.CancelledException("download stopped!");
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                IOUtil.a((Closeable) bufferedInputStream);
                                IOUtil.a((Closeable) bufferedOutputStream2);
                                throw th;
                            }
                        } else {
                            bufferedInputStream = bufferedInputStream2;
                        }
                        j4 = j5;
                        bufferedInputStream2 = bufferedInputStream;
                    } else {
                        BufferedInputStream bufferedInputStream4 = bufferedInputStream2;
                        file.getParentFile().mkdirs();
                        throw new IOException("parent be deleted!");
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                bufferedOutputStream2 = bufferedOutputStream;
                bufferedInputStream = bufferedInputStream2;
                IOUtil.a((Closeable) bufferedInputStream);
                IOUtil.a((Closeable) bufferedOutputStream2);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x0170 A[Catch:{ all -> 0x01c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01bf A[Catch:{ all -> 0x01c0 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File c(org.xutils.http.request.UriRequest r11) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            org.xutils.http.RequestParams r1 = r10.f10784a     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r1 = r1.B()     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r10.e = r1     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r10.j = r0     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r1 = r10.e     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x0031
            org.xutils.http.ProgressHandler r1 = r10.b     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x002d
            org.xutils.http.ProgressHandler r2 = r10.b     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r3 = 0
            r5 = 0
            r7 = 0
            boolean r1 = r2.a(r3, r5, r7)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x0025
            goto L_0x002d
        L_0x0025:
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = "download stopped!"
            r1.<init>(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            throw r1     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        L_0x002d:
            r10.d(r11)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            goto L_0x0046
        L_0x0031:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r1.<init>()     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = r10.e     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r1.append(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = ".tmp"
            r1.append(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r1 = r1.toString()     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r10.d = r1     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        L_0x0046:
            org.xutils.http.ProgressHandler r1 = r10.b     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x0060
            org.xutils.http.ProgressHandler r2 = r10.b     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r3 = 0
            r5 = 0
            r7 = 0
            boolean r1 = r2.a(r3, r5, r7)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x0058
            goto L_0x0060
        L_0x0058:
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = "download stopped!"
            r1.<init>(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            throw r1     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        L_0x0060:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r1.<init>()     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = r10.e     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r1.append(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r2 = "_lock"
            r1.append(r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            java.lang.String r1 = r1.toString()     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            r2 = 1
            org.xutils.common.util.ProcessLock r1 = org.xutils.common.util.ProcessLock.a(r1, r2)     // Catch:{ HttpException -> 0x0164, all -> 0x0161 }
            if (r1 == 0) goto L_0x0148
            boolean r0 = r1.a()     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x0148
            org.xutils.http.RequestParams r0 = r11.q()     // Catch:{ HttpException -> 0x0146 }
            r10.f10784a = r0     // Catch:{ HttpException -> 0x0146 }
            boolean r0 = r10.f     // Catch:{ HttpException -> 0x0146 }
            r2 = 0
            if (r0 == 0) goto L_0x00a4
            java.io.File r0 = new java.io.File     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r4 = r10.d     // Catch:{ HttpException -> 0x0146 }
            r0.<init>(r4)     // Catch:{ HttpException -> 0x0146 }
            long r4 = r0.length()     // Catch:{ HttpException -> 0x0146 }
            r6 = 512(0x200, double:2.53E-321)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x00a1
            org.xutils.common.util.IOUtil.a((java.io.File) r0)     // Catch:{ HttpException -> 0x0146 }
            goto L_0x00a4
        L_0x00a1:
            r0 = 0
            long r2 = r4 - r6
        L_0x00a4:
            org.xutils.http.RequestParams r0 = r10.f10784a     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r4 = "RANGE"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0146 }
            r5.<init>()     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r6 = "bytes="
            r5.append(r6)     // Catch:{ HttpException -> 0x0146 }
            r5.append(r2)     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = "-"
            r5.append(r2)     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = r5.toString()     // Catch:{ HttpException -> 0x0146 }
            r0.a((java.lang.String) r4, (java.lang.String) r2)     // Catch:{ HttpException -> 0x0146 }
            org.xutils.http.ProgressHandler r0 = r10.b     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x00db
            org.xutils.http.ProgressHandler r2 = r10.b     // Catch:{ HttpException -> 0x0146 }
            r3 = 0
            r5 = 0
            r7 = 0
            boolean r0 = r2.a(r3, r5, r7)     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x00d3
            goto L_0x00db
        L_0x00d3:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = "download stopped!"
            r0.<init>(r2)     // Catch:{ HttpException -> 0x0146 }
            throw r0     // Catch:{ HttpException -> 0x0146 }
        L_0x00db:
            r11.a()     // Catch:{ HttpException -> 0x0146 }
            long r2 = r11.h()     // Catch:{ HttpException -> 0x0146 }
            r10.h = r2     // Catch:{ HttpException -> 0x0146 }
            boolean r0 = r10.g     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x00ee
            java.lang.String r0 = e(r11)     // Catch:{ HttpException -> 0x0146 }
            r10.i = r0     // Catch:{ HttpException -> 0x0146 }
        L_0x00ee:
            boolean r0 = r10.f     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x00f8
            boolean r0 = f(r11)     // Catch:{ HttpException -> 0x0146 }
            r10.f = r0     // Catch:{ HttpException -> 0x0146 }
        L_0x00f8:
            org.xutils.http.ProgressHandler r0 = r10.b     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x0112
            org.xutils.http.ProgressHandler r2 = r10.b     // Catch:{ HttpException -> 0x0146 }
            r3 = 0
            r5 = 0
            r7 = 0
            boolean r0 = r2.a(r3, r5, r7)     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x010a
            goto L_0x0112
        L_0x010a:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = "download stopped!"
            r0.<init>(r2)     // Catch:{ HttpException -> 0x0146 }
            throw r0     // Catch:{ HttpException -> 0x0146 }
        L_0x0112:
            org.xutils.cache.DiskCacheFile r0 = r10.j     // Catch:{ HttpException -> 0x0146 }
            if (r0 == 0) goto L_0x013d
            org.xutils.cache.DiskCacheFile r0 = r10.j     // Catch:{ HttpException -> 0x0146 }
            org.xutils.cache.DiskCacheEntity r0 = r0.getCacheEntity()     // Catch:{ HttpException -> 0x0146 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ HttpException -> 0x0146 }
            r0.d((long) r2)     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = r11.m()     // Catch:{ HttpException -> 0x0146 }
            r0.d((java.lang.String) r2)     // Catch:{ HttpException -> 0x0146 }
            long r2 = r11.k()     // Catch:{ HttpException -> 0x0146 }
            r0.b((long) r2)     // Catch:{ HttpException -> 0x0146 }
            java.util.Date r2 = new java.util.Date     // Catch:{ HttpException -> 0x0146 }
            long r3 = r11.l()     // Catch:{ HttpException -> 0x0146 }
            r2.<init>(r3)     // Catch:{ HttpException -> 0x0146 }
            r0.a((java.util.Date) r2)     // Catch:{ HttpException -> 0x0146 }
        L_0x013d:
            java.io.InputStream r0 = r11.g()     // Catch:{ HttpException -> 0x0146 }
            java.io.File r0 = r10.b((java.io.InputStream) r0)     // Catch:{ HttpException -> 0x0146 }
            goto L_0x0198
        L_0x0146:
            r0 = move-exception
            goto L_0x0168
        L_0x0148:
            org.xutils.ex.FileLockedException r0 = new org.xutils.ex.FileLockedException     // Catch:{ HttpException -> 0x0146 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0146 }
            r2.<init>()     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r3 = "download exists: "
            r2.append(r3)     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r3 = r10.e     // Catch:{ HttpException -> 0x0146 }
            r2.append(r3)     // Catch:{ HttpException -> 0x0146 }
            java.lang.String r2 = r2.toString()     // Catch:{ HttpException -> 0x0146 }
            r0.<init>(r2)     // Catch:{ HttpException -> 0x0146 }
            throw r0     // Catch:{ HttpException -> 0x0146 }
        L_0x0161:
            r11 = move-exception
            r1 = r0
            goto L_0x01c1
        L_0x0164:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x0168:
            int r2 = r0.getCode()     // Catch:{ all -> 0x01c0 }
            r3 = 416(0x1a0, float:5.83E-43)
            if (r2 != r3) goto L_0x01bf
            org.xutils.cache.DiskCacheFile r0 = r10.j     // Catch:{ all -> 0x01c0 }
            if (r0 == 0) goto L_0x017b
            org.xutils.cache.DiskCacheFile r0 = r10.j     // Catch:{ all -> 0x01c0 }
            org.xutils.cache.DiskCacheFile r0 = r0.commit()     // Catch:{ all -> 0x01c0 }
            goto L_0x0182
        L_0x017b:
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x01c0 }
            java.lang.String r2 = r10.d     // Catch:{ all -> 0x01c0 }
            r0.<init>(r2)     // Catch:{ all -> 0x01c0 }
        L_0x0182:
            if (r0 == 0) goto L_0x01a1
            boolean r2 = r0.exists()     // Catch:{ all -> 0x01c0 }
            if (r2 == 0) goto L_0x01a1
            boolean r2 = r10.g     // Catch:{ all -> 0x01c0 }
            if (r2 == 0) goto L_0x0194
            java.lang.String r11 = e(r11)     // Catch:{ all -> 0x01c0 }
            r10.i = r11     // Catch:{ all -> 0x01c0 }
        L_0x0194:
            java.io.File r0 = r10.a((java.io.File) r0)     // Catch:{ all -> 0x01c0 }
        L_0x0198:
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r1)
            org.xutils.cache.DiskCacheFile r11 = r10.j
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r11)
            return r0
        L_0x01a1:
            org.xutils.common.util.IOUtil.a((java.io.File) r0)     // Catch:{ all -> 0x01c0 }
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x01c0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c0 }
            r2.<init>()     // Catch:{ all -> 0x01c0 }
            java.lang.String r3 = "cache file not found"
            r2.append(r3)     // Catch:{ all -> 0x01c0 }
            java.lang.String r11 = r11.c()     // Catch:{ all -> 0x01c0 }
            r2.append(r11)     // Catch:{ all -> 0x01c0 }
            java.lang.String r11 = r2.toString()     // Catch:{ all -> 0x01c0 }
            r0.<init>(r11)     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x01bf:
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x01c0:
            r11 = move-exception
        L_0x01c1:
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r1)
            org.xutils.cache.DiskCacheFile r0 = r10.j
            org.xutils.common.util.IOUtil.a((java.io.Closeable) r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.loader.FileLoader.c(org.xutils.http.request.UriRequest):java.io.File");
    }

    private void d(UriRequest uriRequest) throws Throwable {
        DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        diskCacheEntity.a(uriRequest.c());
        this.j = LruDiskCache.a(this.f10784a.v()).b(diskCacheEntity);
        if (this.j != null) {
            this.e = this.j.getAbsolutePath();
            this.d = this.e;
            this.g = false;
            return;
        }
        throw new IOException("create cache file error:" + uriRequest.c());
    }

    private File a(File file) {
        if (this.g && file.exists() && !TextUtils.isEmpty(this.i)) {
            File file2 = new File(file.getParent(), this.i);
            while (file2.exists()) {
                String parent = file.getParent();
                file2 = new File(parent, System.currentTimeMillis() + this.i);
            }
            return file.renameTo(file2) ? file2 : file;
        } else if (this.e.equals(this.d)) {
            return file;
        } else {
            File file3 = new File(this.e);
            return file.renameTo(file3) ? file3 : file;
        }
    }

    private static String e(UriRequest uriRequest) {
        int indexOf;
        if (uriRequest == null) {
            return null;
        }
        String a2 = uriRequest.a("Content-Disposition");
        if (!TextUtils.isEmpty(a2) && (indexOf = a2.indexOf("filename=")) > 0) {
            int i2 = indexOf + 9;
            int indexOf2 = a2.indexOf(i.b, i2);
            if (indexOf2 < 0) {
                indexOf2 = a2.length();
            }
            if (indexOf2 > i2) {
                try {
                    String decode = URLDecoder.decode(a2.substring(i2, indexOf2), uriRequest.q().a());
                    return (!decode.startsWith("\"") || !decode.endsWith("\"")) ? decode : decode.substring(1, decode.length() - 1);
                } catch (UnsupportedEncodingException e2) {
                    LogUtil.b(e2.getMessage(), e2);
                }
            }
        }
        return null;
    }

    private static boolean f(UriRequest uriRequest) {
        if (uriRequest == null) {
            return false;
        }
        String a2 = uriRequest.a(HttpHeaders.ACCEPT_RANGES);
        if (a2 != null) {
            return a2.contains(HTTP.CONTENT_RANGE_BYTES);
        }
        String a3 = uriRequest.a("Content-Range");
        if (a3 == null || !a3.contains(HTTP.CONTENT_RANGE_BYTES)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public File b(DiskCacheEntity diskCacheEntity) throws Throwable {
        return LruDiskCache.a(this.f10784a.v()).c(diskCacheEntity.b());
    }
}
