package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import org.apache.http.HttpResponse;

public class ResponseStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private HttpResponse f6336a;
    private InputStream b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;

    public ResponseStream(HttpResponse httpResponse, String str, long j) throws IOException {
        this(httpResponse, "UTF-8", str, j);
    }

    public ResponseStream(HttpResponse httpResponse, String str, String str2, long j) throws IOException {
        if (httpResponse != null) {
            this.f6336a = httpResponse;
            this.b = httpResponse.getEntity().getContent();
            this.c = str;
            this.d = str2;
            this.f = j;
            return;
        }
        throw new IllegalArgumentException("baseResponse may not be null");
    }

    public ResponseStream(String str) throws IOException {
        if (str != null) {
            this.g = str;
            return;
        }
        throw new IllegalArgumentException("result may not be null");
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.e = str;
    }

    public InputStream c() {
        return this.b;
    }

    public HttpResponse d() {
        return this.f6336a;
    }

    public int e() {
        if (this.g != null) {
            return 200;
        }
        return this.f6336a.getStatusLine().getStatusCode();
    }

    public Locale f() {
        if (this.g != null) {
            return Locale.getDefault();
        }
        return this.f6336a.getLocale();
    }

    public String g() {
        if (this.g != null) {
            return "";
        }
        return this.f6336a.getStatusLine().getReasonPhrase();
    }

    public String h() throws IOException {
        if (this.g != null) {
            return this.g;
        }
        if (this.b == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.b, this.c));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            this.g = sb.toString();
            if (this.d != null && HttpUtils.f6287a.b(this.e)) {
                HttpUtils.f6287a.a(this.d, this.g, this.f);
            }
            return this.g;
        } finally {
            IOUtils.a((Closeable) this.b);
        }
    }

    public void b(String str) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        if (this.g == null && this.b != null) {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(this.b);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            bufferedOutputStream.flush();
                            IOUtils.a((Closeable) bufferedOutputStream);
                            IOUtils.a((Closeable) this.b);
                            return;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
                IOUtils.a((Closeable) bufferedOutputStream);
                IOUtils.a((Closeable) this.b);
                throw th;
            }
        }
    }

    public int read() throws IOException {
        if (this.b == null) {
            return -1;
        }
        return this.b.read();
    }

    public int available() throws IOException {
        if (this.b == null) {
            return 0;
        }
        return this.b.available();
    }

    public void close() throws IOException {
        if (this.b != null) {
            this.b.close();
        }
    }

    public void mark(int i) {
        if (this.b != null) {
            this.b.mark(i);
        }
    }

    public boolean markSupported() {
        if (this.b == null) {
            return false;
        }
        return this.b.markSupported();
    }

    public int read(byte[] bArr) throws IOException {
        if (this.b == null) {
            return -1;
        }
        return this.b.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.b == null) {
            return -1;
        }
        return this.b.read(bArr, i, i2);
    }

    public synchronized void reset() throws IOException {
        if (this.b != null) {
            this.b.reset();
        }
    }

    public long skip(long j) throws IOException {
        if (this.b == null) {
            return 0;
        }
        return this.b.skip(j);
    }

    public long i() {
        if (this.b == null) {
            return 0;
        }
        return this.f6336a.getEntity().getContentLength();
    }
}
