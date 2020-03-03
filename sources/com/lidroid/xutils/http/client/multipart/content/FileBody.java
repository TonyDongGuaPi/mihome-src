package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

public class FileBody extends AbstractContentBody {
    private final File b;
    private final String c;
    private final String d;

    public String f() {
        return "binary";
    }

    public FileBody(File file, String str, String str2, String str3) {
        super(str2);
        if (file != null) {
            this.b = file;
            if (str != null) {
                this.c = str;
            } else {
                this.c = file.getName();
            }
            this.d = str3;
            return;
        }
        throw new IllegalArgumentException("File may not be null");
    }

    public FileBody(File file, String str, String str2) {
        this(file, (String) null, str, str2);
    }

    public FileBody(File file, String str) {
        this(file, (String) null, str, (String) null);
    }

    public FileBody(File file) {
        this(file, (String) null, "application/octet-stream", (String) null);
    }

    public InputStream h() throws IOException {
        return new FileInputStream(this.b);
    }

    public void a(OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (outputStream != null) {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(this.b));
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            outputStream.flush();
                            IOUtils.a((Closeable) bufferedInputStream);
                            return;
                        }
                        outputStream.write(bArr, 0, read);
                        this.f6353a.d += (long) read;
                        if (!this.f6353a.a(false)) {
                            throw new InterruptedIOException("cancel");
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    IOUtils.a((Closeable) bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                IOUtils.a((Closeable) bufferedInputStream);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    public String e() {
        return this.d;
    }

    public long g() {
        return this.b.length();
    }

    public String d() {
        return this.c;
    }

    public File i() {
        return this.b;
    }
}
