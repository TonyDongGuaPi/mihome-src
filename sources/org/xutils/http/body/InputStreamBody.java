package org.xutils.http.body;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.xutils.common.Callback;
import org.xutils.common.util.IOUtil;
import org.xutils.http.ProgressHandler;

public class InputStreamBody implements ProgressBody {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f10777a;
    private String b;
    private final long c;
    private long d;
    private ProgressHandler e;

    public InputStreamBody(InputStream inputStream) {
        this(inputStream, (String) null);
    }

    public InputStreamBody(InputStream inputStream, String str) {
        this.d = 0;
        this.f10777a = inputStream;
        this.b = str;
        this.c = a(inputStream);
    }

    public void a(ProgressHandler progressHandler) {
        this.e = progressHandler;
    }

    public long b() {
        return this.c;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return TextUtils.isEmpty(this.b) ? "application/octet-stream" : this.b;
    }

    public void a(OutputStream outputStream) throws IOException {
        if (this.e == null || this.e.a(this.c, this.d, true)) {
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int read = this.f10777a.read(bArr);
                    if (read != -1) {
                        outputStream.write(bArr, 0, read);
                        this.d += (long) read;
                        if (this.e != null) {
                            if (!this.e.a(this.c, this.d, false)) {
                                throw new Callback.CancelledException("upload stopped!");
                            }
                        }
                    } else {
                        outputStream.flush();
                        if (this.e != null) {
                            this.e.a(this.c, this.c, true);
                        }
                        return;
                    }
                } finally {
                    IOUtil.a((Closeable) this.f10777a);
                }
            }
        } else {
            throw new Callback.CancelledException("upload stopped!");
        }
    }

    public static long a(InputStream inputStream) {
        try {
            if ((inputStream instanceof FileInputStream) || (inputStream instanceof ByteArrayInputStream)) {
                return (long) inputStream.available();
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
