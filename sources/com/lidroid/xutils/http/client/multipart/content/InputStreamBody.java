package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

public class InputStreamBody extends AbstractContentBody {
    private final InputStream b;
    private final String c;
    private long d;

    public String e() {
        return null;
    }

    public String f() {
        return "binary";
    }

    public InputStreamBody(InputStream inputStream, long j, String str, String str2) {
        super(str2);
        if (inputStream != null) {
            this.b = inputStream;
            this.c = str;
            this.d = j;
            return;
        }
        throw new IllegalArgumentException("Input stream may not be null");
    }

    public InputStreamBody(InputStream inputStream, long j, String str) {
        this(inputStream, j, str, "application/octet-stream");
    }

    public InputStreamBody(InputStream inputStream, long j) {
        this(inputStream, j, "no_name", "application/octet-stream");
    }

    public InputStream h() {
        return this.b;
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = this.b.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.f6353a.d += (long) read;
                    if (!this.f6353a.a(false)) {
                        throw new InterruptedIOException("cancel");
                    }
                }
            } finally {
                IOUtils.a((Closeable) this.b);
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    public long g() {
        return this.d;
    }

    public String d() {
        return this.c;
    }
}
