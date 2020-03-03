package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;

public final class CountOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final OutputStream f683a;
    private int b = 0;

    CountOutputStream(OutputStream outputStream) {
        this.f683a = outputStream;
    }

    public int a() {
        return this.b;
    }

    public void write(int i) throws IOException {
        this.f683a.write(i);
        this.b++;
    }

    public void write(byte[] bArr) throws IOException {
        this.f683a.write(bArr);
        this.b += bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f683a.write(bArr, i, i2);
        this.b += i2;
    }
}
