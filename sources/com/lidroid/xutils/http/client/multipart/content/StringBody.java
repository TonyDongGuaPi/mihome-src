package com.lidroid.xutils.http.client.multipart.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringBody extends AbstractContentBody {
    private final byte[] b;
    private final Charset c;

    public String d() {
        return null;
    }

    public String f() {
        return "8bit";
    }

    public static StringBody a(String str, String str2, Charset charset) throws IllegalArgumentException {
        try {
            return new StringBody(str, str2, charset);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Charset " + charset + " is not supported", e);
        }
    }

    public static StringBody a(String str, Charset charset) throws IllegalArgumentException {
        return a(str, (String) null, charset);
    }

    public static StringBody a(String str) throws IllegalArgumentException {
        return a(str, (String) null, (Charset) null);
    }

    public StringBody(String str, String str2, Charset charset) throws UnsupportedEncodingException {
        super(str2);
        if (str != null) {
            charset = charset == null ? Charset.forName("UTF-8") : charset;
            this.b = str.getBytes(charset.name());
            this.c = charset;
            return;
        }
        throw new IllegalArgumentException("Text may not be null");
    }

    public StringBody(String str, Charset charset) throws UnsupportedEncodingException {
        this(str, "text/plain", charset);
    }

    public StringBody(String str) throws UnsupportedEncodingException {
        this(str, "text/plain", (Charset) null);
    }

    public Reader h() {
        return new InputStreamReader(new ByteArrayInputStream(this.b), this.c);
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.b);
            byte[] bArr = new byte[4096];
            do {
                int read = byteArrayInputStream.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
                this.f6353a.d += (long) read;
            } while (this.f6353a.a(false));
            throw new InterruptedIOException("cancel");
        }
        throw new IllegalArgumentException("Output stream may not be null");
    }

    public String e() {
        return this.c.name();
    }

    public long g() {
        return (long) this.b.length;
    }
}
