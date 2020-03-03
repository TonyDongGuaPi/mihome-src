package com.ximalaya.ting.android.sdkdownloader.http.loader;

import com.google.common.net.HttpHeaders;
import com.ximalaya.ting.android.player.MediadataCrytoUtil;
import com.ximalaya.ting.android.sdkdownloader.exception.FileLockedException;
import com.ximalaya.ting.android.sdkdownloader.exception.HttpException;
import com.ximalaya.ting.android.sdkdownloader.http.ProgressHandler;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import com.ximalaya.ting.android.sdkdownloader.util.IOUtil;
import com.ximalaya.ting.android.sdkdownloader.util.ProcessLock;
import com.ximalaya.ting.android.sdkdownloader.util.XmUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import org.cybergarage.http.HTTP;

public class FileLoader {
    public static int c = 0;
    public static int d = 1;
    private static final int e = 512;

    /* renamed from: a  reason: collision with root package name */
    protected RequestParams f2363a;
    protected ProgressHandler b;
    private String f;
    private String g;
    private boolean h;
    private long i;
    private int j;
    private AtomicBoolean k = new AtomicBoolean(false);
    private AtomicBoolean l = new AtomicBoolean(false);
    private int m;
    private byte[] n;

    public FileLoader() {
        this.m = MediadataCrytoUtil.f2276a >= 4096 ? MediadataCrytoUtil.f2276a : 4096;
        this.n = new byte[MediadataCrytoUtil.f2276a];
    }

    public void a(ProgressHandler progressHandler) {
        this.b = progressHandler;
    }

    public void a(RequestParams requestParams) {
        if (requestParams != null) {
            this.f2363a = requestParams;
            this.h = requestParams.j();
            this.j = requestParams.s();
        }
    }

    public File a(InputStream inputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        InputStream inputStream2 = inputStream;
        if (this.b == null || this.b.a(0, 0, false)) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                File file = new File(this.f);
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
                if (this.h && length > 0) {
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
                                this.i -= 512;
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
                if (this.h) {
                    fileOutputStream = new FileOutputStream(file, true);
                } else {
                    fileOutputStream = new FileOutputStream(file);
                    length = 0;
                }
                long j3 = this.i + length;
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        if (this.b != null) {
                            bufferedOutputStream = bufferedOutputStream2;
                            if (!this.b.a(j3, length, true)) {
                                throw new Callback.CancelledException("download stopped!");
                            }
                        } else {
                            bufferedOutputStream = bufferedOutputStream2;
                        }
                        byte[] bArr = new byte[this.m];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read == -1) {
                                BufferedInputStream bufferedInputStream3 = bufferedInputStream2;
                                bufferedOutputStream.flush();
                                if (this.b != null) {
                                    this.b.a(j3, length, true);
                                }
                                IOUtil.a((Closeable) bufferedInputStream3);
                                IOUtil.a((Closeable) bufferedOutputStream);
                                return a(file);
                            } else if (this.k.get()) {
                                BufferedInputStream bufferedInputStream4 = bufferedInputStream2;
                                bufferedOutputStream.flush();
                                throw new Callback.CancelledException("download stopped!");
                            } else if (this.l.get()) {
                                BufferedInputStream bufferedInputStream5 = bufferedInputStream2;
                                bufferedOutputStream.flush();
                                throw new Callback.RemovedException("download removed!");
                            } else if (file.getParentFile().exists()) {
                                if ((length != 0 && length >= ((long) MediadataCrytoUtil.f2276a)) || this.j != d) {
                                    bufferedOutputStream.write(bArr, 0, read);
                                } else if (length == 0 && read > MediadataCrytoUtil.f2276a) {
                                    bufferedOutputStream.write(MediadataCrytoUtil.a().a(Arrays.copyOf(bArr, MediadataCrytoUtil.f2276a)), 0, MediadataCrytoUtil.f2276a);
                                    byte[] copyOfRange = Arrays.copyOfRange(bArr, MediadataCrytoUtil.f2276a, read);
                                    bufferedOutputStream.write(copyOfRange, 0, copyOfRange.length);
                                } else if (length >= 0) {
                                    int i2 = (int) length;
                                    int i3 = MediadataCrytoUtil.f2276a - i2;
                                    if (i3 > bArr.length) {
                                        System.arraycopy(bArr, 0, this.n, i2, bArr.length);
                                    } else {
                                        System.arraycopy(bArr, 0, this.n, i2, i3);
                                    }
                                    long j4 = ((long) read) + length;
                                    if (j4 > ((long) MediadataCrytoUtil.f2276a)) {
                                        bufferedOutputStream.write(MediadataCrytoUtil.a().a(this.n), 0, MediadataCrytoUtil.f2276a);
                                        if (i3 < bArr.length) {
                                            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i3, bArr.length);
                                            bufferedOutputStream.write(copyOfRange2, 0, copyOfRange2.length);
                                        }
                                    } else if (j4 == ((long) MediadataCrytoUtil.f2276a)) {
                                        bufferedOutputStream.write(MediadataCrytoUtil.a().a(this.n), 0, read);
                                    }
                                }
                                length += (long) read;
                                if (this.b != null) {
                                    bufferedInputStream = bufferedInputStream2;
                                    try {
                                        if (!this.b.a(j3, length, false)) {
                                            bufferedOutputStream.flush();
                                            if (this.l.get()) {
                                                throw new Callback.RemovedException("download removed");
                                            }
                                            throw new Callback.CancelledException("download stopped!");
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        IOUtil.a((Closeable) bufferedInputStream);
                                        IOUtil.a((Closeable) bufferedOutputStream);
                                        throw th;
                                    }
                                } else {
                                    bufferedInputStream = bufferedInputStream2;
                                }
                                bufferedInputStream2 = bufferedInputStream;
                            } else {
                                BufferedInputStream bufferedInputStream6 = bufferedInputStream2;
                                file.getParentFile().mkdirs();
                                throw new IOException("parent be deleted!");
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedOutputStream = bufferedOutputStream2;
                        bufferedInputStream = bufferedInputStream2;
                        IOUtil.a((Closeable) bufferedInputStream);
                        IOUtil.a((Closeable) bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    bufferedInputStream = bufferedInputStream2;
                    IOUtil.a((Closeable) bufferedInputStream);
                    IOUtil.a((Closeable) bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                bufferedInputStream = null;
            }
        } else {
            throw new Callback.CancelledException("download stopped!");
        }
    }

    public File a(UriRequest uriRequest) throws Throwable {
        this.k.set(false);
        ProcessLock processLock = null;
        try {
            this.g = this.f2363a.k();
            this.f = XmUtils.a(this.g);
            if (this.b != null) {
                if (!this.b.a(0, 0, false)) {
                    throw new Callback.CancelledException("download stopped!");
                }
            }
            ProcessLock processLock2 = ProcessLock.a(this.g + "_lock", true);
            if (processLock2 != null) {
                try {
                    if (processLock2.a()) {
                        this.f2363a = uriRequest.j();
                        long j2 = 0;
                        if (this.h) {
                            File file = new File(this.f);
                            long length = file.length();
                            if (length <= 512) {
                                IOUtil.a(file);
                            } else {
                                j2 = length - 512;
                            }
                        }
                        RequestParams requestParams = this.f2363a;
                        requestParams.a("RANGE", "bytes=" + j2 + "-");
                        if (this.b != null) {
                            if (!this.b.a(0, 0, false)) {
                                throw new Callback.CancelledException("download stopped!");
                            }
                        }
                        uriRequest.b();
                        this.i = uriRequest.g();
                        if (this.h) {
                            this.h = b(uriRequest);
                        }
                        if (this.b != null) {
                            if (!this.b.a(0, 0, false)) {
                                throw new Callback.CancelledException("download stopped!");
                            }
                        }
                        File a2 = a(uriRequest.e());
                        IOUtil.a((Closeable) processLock2);
                        return a2;
                    }
                } catch (HttpException e2) {
                    e = e2;
                    processLock = processLock2;
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        processLock2 = processLock;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtil.a((Closeable) processLock2);
                    throw th;
                }
            }
            throw new FileLockedException("download exists: " + this.g);
        } catch (HttpException e3) {
            e = e3;
            throw e;
        }
    }

    public void a() {
        this.k.set(true);
    }

    public void b() {
        this.l.set(true);
        IOUtil.a(this.f);
    }

    private static boolean b(UriRequest uriRequest) {
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

    private File a(File file) {
        if (this.g.equals(this.f)) {
            return file;
        }
        File file2 = new File(this.g);
        return file.renameTo(file2) ? file2 : file;
    }
}
