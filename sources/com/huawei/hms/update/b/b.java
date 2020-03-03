package com.huawei.hms.update.b;

import com.huawei.hms.c.c;
import com.huawei.hms.support.log.a;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class b implements d {

    /* renamed from: a  reason: collision with root package name */
    private HttpURLConnection f5917a;
    private volatile int b = -1;

    public void a() {
        this.b = -1;
        if (this.f5917a != null) {
            this.f5917a.disconnect();
        }
    }

    public void b() {
        this.b = 1;
    }

    public int a(String str, InputStream inputStream, OutputStream outputStream) throws IOException, a {
        OutputStream outputStream2;
        InputStream inputStream2 = null;
        try {
            a(str);
            this.f5917a.setRequestMethod("POST");
            outputStream2 = this.f5917a.getOutputStream();
            try {
                a(inputStream, outputStream2);
                outputStream2.flush();
                int responseCode = this.f5917a.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream3 = this.f5917a.getInputStream();
                    try {
                        a((InputStream) new BufferedInputStream(inputStream3, 4096), outputStream);
                        outputStream.flush();
                        inputStream2 = inputStream3;
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream3;
                        c.a(inputStream2);
                        c.a(outputStream2);
                        throw th;
                    }
                }
                c.a(inputStream2);
                c.a(outputStream2);
                return responseCode;
            } catch (Throwable th2) {
                th = th2;
                c.a(inputStream2);
                c.a(outputStream2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream2 = null;
            c.a(inputStream2);
            c.a(outputStream2);
            throw th;
        }
    }

    public int a(String str, OutputStream outputStream) throws IOException, a {
        return a(str, outputStream, 0, 0);
    }

    public int a(String str, OutputStream outputStream, int i, int i2) throws IOException, a {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            a(str);
            this.f5917a.setRequestMethod("GET");
            if (i > 0) {
                this.f5917a.addRequestProperty("Range", "bytes=" + i + "-" + i2);
            }
            int responseCode = this.f5917a.getResponseCode();
            if ((i <= 0 || responseCode != 206) && (i > 0 || responseCode != 200)) {
                inputStream = null;
            } else {
                inputStream = this.f5917a.getInputStream();
                try {
                    a((InputStream) new BufferedInputStream(inputStream, 4096), outputStream);
                    outputStream.flush();
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                }
            }
            c.a(inputStream);
            return responseCode;
        } catch (Throwable th2) {
            th = th2;
            c.a(inputStream2);
            throw th;
        }
    }

    private void a(String str) throws IOException {
        if (this.b == 0) {
            a.d("HttpRequestHelper", "Not allowed to repeat open http(s) connection.");
        }
        this.f5917a = (HttpURLConnection) new URL(str).openConnection();
        if (this.f5917a instanceof HttpsURLConnection) {
            c.a((HttpsURLConnection) this.f5917a);
        }
        this.f5917a.setConnectTimeout(30000);
        this.f5917a.setReadTimeout(30000);
        this.f5917a.setDoInput(true);
        this.f5917a.setDoOutput(true);
        this.f5917a.setUseCaches(false);
        this.b = 0;
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException, a {
        byte[] bArr = new byte[4096];
        do {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        } while (this.b != 1);
        throw new a("HTTP(s) request was canceled.");
    }
}
