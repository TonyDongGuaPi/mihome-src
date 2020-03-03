package com.lidroid.xutils.http.client.multipart.content;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayBody extends AbstractContentBody {
    private final byte[] b;
    private final String c;

    public String e() {
        return null;
    }

    public String f() {
        return "binary";
    }

    public ByteArrayBody(byte[] bArr, String str, String str2) {
        super(str);
        if (bArr != null) {
            this.b = bArr;
            this.c = str2;
            return;
        }
        throw new IllegalArgumentException("byte[] may not be null");
    }

    public ByteArrayBody(byte[] bArr, String str) {
        this(bArr, "application/octet-stream", str);
    }

    public String d() {
        return this.c;
    }

    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(this.b);
        this.f6353a.d += (long) this.b.length;
        this.f6353a.a(false);
    }

    public long g() {
        return (long) this.b.length;
    }
}
