package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

abstract class DecompressingEntity extends HttpEntityWrapper implements UploadEntity {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f6343a;
    private long b;
    private long c = 0;
    private RequestCallBackHandler d = null;

    /* access modifiers changed from: package-private */
    public abstract InputStream a(InputStream inputStream) throws IOException;

    public long getContentLength() {
        return -1;
    }

    public DecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity);
        this.b = httpEntity.getContentLength();
    }

    private InputStream a() throws IOException {
        InputStream inputStream = null;
        try {
            InputStream content = this.wrappedEntity.getContent();
            try {
                return a(content);
            } catch (IOException e) {
                InputStream inputStream2 = content;
                e = e;
                inputStream = inputStream2;
                IOUtils.a((Closeable) inputStream);
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            IOUtils.a((Closeable) inputStream);
            throw e;
        }
    }

    public InputStream getContent() throws IOException {
        if (!this.wrappedEntity.isStreaming()) {
            return a();
        }
        if (this.f6343a == null) {
            this.f6343a = a();
        }
        return this.f6343a;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        InputStream inputStream;
        if (outputStream != null) {
            try {
                inputStream = getContent();
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            outputStream.flush();
                            if (this.d != null) {
                                this.d.a(this.b, this.c, true);
                            }
                            IOUtils.a((Closeable) inputStream);
                            return;
                        }
                        outputStream.write(bArr, 0, read);
                        this.c += (long) read;
                        if (this.d != null) {
                            if (!this.d.a(this.b, this.c, false)) {
                                throw new InterruptedIOException("cancel");
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    IOUtils.a((Closeable) inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                IOUtils.a((Closeable) inputStream);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    public void a(RequestCallBackHandler requestCallBackHandler) {
        this.d = requestCallBackHandler;
    }
}
