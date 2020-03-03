package com.xiaomi.youpin.hawkeye.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;

public class RequestBodyHelper {
    private static final String b = "gzip";
    private static final String c = "deflate";

    /* renamed from: a  reason: collision with root package name */
    private ByteArrayOutputStream f23367a;

    public OutputStream a(String str) throws IOException {
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if ("gzip".equals(str)) {
            outputStream = GunzippingOutputStream.a((OutputStream) byteArrayOutputStream);
        } else {
            outputStream = "deflate".equals(str) ? new InflaterOutputStream(byteArrayOutputStream) : byteArrayOutputStream;
        }
        this.f23367a = byteArrayOutputStream;
        return outputStream;
    }

    public byte[] a() {
        c();
        return this.f23367a.toByteArray();
    }

    public boolean b() {
        return this.f23367a != null;
    }

    private void c() {
        if (!b()) {
            throw new IllegalStateException("No body found; has createBodySink been called?");
        }
    }
}
