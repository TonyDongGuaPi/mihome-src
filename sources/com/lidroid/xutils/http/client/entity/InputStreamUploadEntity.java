package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;

public class InputStreamUploadEntity extends AbstractHttpEntity implements UploadEntity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6345a = 2048;
    private final InputStream b;
    private final long c;
    private long d = 0;
    private RequestCallBackHandler e = null;

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public InputStreamUploadEntity(InputStream inputStream, long j) {
        if (inputStream != null) {
            this.b = inputStream;
            this.c = j;
            return;
        }
        throw new IllegalArgumentException("Source input stream may not be null");
    }

    public long getContentLength() {
        return this.c;
    }

    public InputStream getContent() throws IOException {
        return this.b;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        OutputStream outputStream2 = outputStream;
        if (outputStream2 != null) {
            InputStream inputStream = this.b;
            try {
                byte[] bArr = new byte[2048];
                if (this.c < 0) {
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        outputStream2.write(bArr, 0, read);
                        this.d += (long) read;
                        if (this.e != null) {
                            if (!this.e.a(this.d + 1, this.d, false)) {
                                throw new InterruptedIOException("cancel");
                            }
                        }
                    }
                } else {
                    long j = this.c;
                    while (true) {
                        if (j <= 0) {
                            break;
                        }
                        int read2 = inputStream.read(bArr, 0, (int) Math.min(2048, j));
                        if (read2 == -1) {
                            break;
                        }
                        outputStream2.write(bArr, 0, read2);
                        long j2 = (long) read2;
                        j -= j2;
                        this.d += j2;
                        if (this.e != null) {
                            if (!this.e.a(this.c, this.d, false)) {
                                throw new InterruptedIOException("cancel");
                            }
                        }
                    }
                }
                outputStream.flush();
                if (this.e != null) {
                    this.e.a(this.c, this.d, true);
                }
            } finally {
                IOUtils.a((Closeable) inputStream);
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    public void consumeContent() throws IOException {
        this.b.close();
    }

    public void a(RequestCallBackHandler requestCallBackHandler) {
        this.e = requestCallBackHandler;
    }
}
