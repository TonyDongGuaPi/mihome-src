package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.FileEntity;

public class FileUploadEntity extends FileEntity implements UploadEntity {

    /* renamed from: a  reason: collision with root package name */
    private long f6344a;
    private long b = 0;
    private RequestCallBackHandler c = null;

    public FileUploadEntity(File file, String str) {
        super(file, str);
        this.f6344a = file.length();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (outputStream != null) {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            outputStream.flush();
                            if (this.c != null) {
                                this.c.a(this.f6344a, this.b, true);
                            }
                            IOUtils.a((Closeable) bufferedInputStream);
                            return;
                        }
                        outputStream.write(bArr, 0, read);
                        this.b += (long) read;
                        if (this.c != null) {
                            if (!this.c.a(this.f6344a, this.b, false)) {
                                throw new InterruptedIOException("cancel");
                            }
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

    public void a(RequestCallBackHandler requestCallBackHandler) {
        this.c = requestCallBackHandler;
    }
}
