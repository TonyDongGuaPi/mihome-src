package com.xiaomi.smarthome.library.http.async;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpRangeUtil;
import com.xiaomi.smarthome.library.http.util.CommonUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.Call;
import okhttp3.Response;

public abstract class FileAsyncHandler extends AsyncHandler<File, Error> {
    protected static final int h = 4096;
    static final /* synthetic */ boolean l = (!FileAsyncHandler.class.desiredAssertionStatus());

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f19114a;
    protected final File i;
    protected final boolean j;
    protected final boolean k;

    /* renamed from: a */
    public abstract void onSuccess(File file, Response response);

    public abstract void onFailure(Error error, Exception exc, Response response);

    public FileAsyncHandler(File file) {
        this(file, false);
    }

    public FileAsyncHandler(File file, boolean z) {
        this(file, z, false);
    }

    public FileAsyncHandler(File file, boolean z, boolean z2) {
        this.f19114a = false;
        a(file, false);
        this.i = file;
        this.j = z;
        this.k = z2;
        this.f19114a = false;
    }

    public void a(boolean z) {
        this.f19114a = z;
    }

    public static boolean a(File file, boolean z) {
        if (file == null) {
            if (z) {
                CommonUtil.a(false, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
            }
            return false;
        } else if (file.isDirectory()) {
            if (z) {
                CommonUtil.a(false, "File passed into FileAsyncHttpResponseHandler constructor must not point to directory");
            }
            return false;
        } else if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            return true;
        } else {
            if (z) {
                CommonUtil.a(false, "Cannot create parent directories for requested File location");
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public File a() {
        if (l || this.i != null) {
            return this.i;
        }
        throw new AssertionError();
    }

    private void a(Response response) {
        InputStream byteStream;
        FileOutputStream fileOutputStream;
        int read;
        if (response.isSuccessful()) {
            try {
                byteStream = response.body().byteStream();
                long contentLength = response.body().contentLength();
                fileOutputStream = new FileOutputStream(a(), this.j);
                if (byteStream != null) {
                    byte[] bArr = new byte[4096];
                    int i2 = 0;
                    while (!this.f19114a && (read = byteStream.read(bArr)) != -1) {
                        i2 += read;
                        fileOutputStream.write(bArr, 0, read);
                        sendProgressMessage((long) i2, contentLength);
                    }
                    CommonUtil.a(byteStream);
                    fileOutputStream.flush();
                    CommonUtil.a((OutputStream) fileOutputStream);
                }
                sendSuccessMessage(a(), response);
            } catch (Exception e) {
                sendFailureMessage(new Error(-1, ""), e, response);
            } catch (Throwable th) {
                CommonUtil.a(byteStream);
                fileOutputStream.flush();
                CommonUtil.a((OutputStream) fileOutputStream);
                throw th;
            }
        } else {
            sendFailureMessage(new Error(response.code(), ""), (Exception) null, response);
        }
    }

    private void b(Response response) {
        InputStream byteStream;
        FileOutputStream fileOutputStream;
        int read;
        long[] a2;
        if (response.isSuccessful()) {
            try {
                byteStream = response.body().byteStream();
                long contentLength = response.body().contentLength();
                long j2 = 0;
                String header = response.header("Content-Range");
                if (!TextUtils.isEmpty(header) && (a2 = HttpRangeUtil.a(header)) != null && a2.length >= 3) {
                    j2 = a2[0];
                    contentLength = a2[2];
                }
                fileOutputStream = new FileOutputStream(a(), true);
                if (byteStream != null) {
                    byte[] bArr = new byte[4096];
                    int i2 = 0;
                    while (!this.f19114a && (read = byteStream.read(bArr)) != -1) {
                        i2 += read;
                        fileOutputStream.write(bArr, 0, read);
                        sendProgressMessage(((long) i2) + j2, contentLength);
                    }
                    CommonUtil.a(byteStream);
                    fileOutputStream.flush();
                    CommonUtil.a((OutputStream) fileOutputStream);
                }
                if (!this.f19114a) {
                    sendSuccessMessage(a(), response);
                }
            } catch (Exception e) {
                sendFailureMessage(new Error(-1, ""), e, response);
            } catch (Throwable th) {
                CommonUtil.a(byteStream);
                fileOutputStream.flush();
                CommonUtil.a((OutputStream) fileOutputStream);
                throw th;
            }
        } else {
            sendFailureMessage(new Error(response.code(), ""), (Exception) null, response);
        }
    }

    public void processResponse(Response response) {
        if (this.k) {
            b(response);
        } else {
            a(response);
        }
    }

    public void processFailure(Call call, IOException iOException) {
        sendFailureMessage(new Error(-1, ""), iOException, (Response) null);
    }
}
