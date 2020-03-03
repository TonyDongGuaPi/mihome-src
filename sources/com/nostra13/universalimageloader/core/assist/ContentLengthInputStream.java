package com.nostra13.universalimageloader.core.assist;

import java.io.IOException;
import java.io.InputStream;

public class ContentLengthInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f8473a;
    private final int b;

    public ContentLengthInputStream(InputStream inputStream, int i) {
        this.f8473a = inputStream;
        this.b = i;
    }

    public int available() {
        return this.b;
    }

    public void close() throws IOException {
        this.f8473a.close();
    }

    public void mark(int i) {
        this.f8473a.mark(i);
    }

    public int read() throws IOException {
        return this.f8473a.read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.f8473a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.f8473a.read(bArr, i, i2);
    }

    public void reset() throws IOException {
        this.f8473a.reset();
    }

    public long skip(long j) throws IOException {
        return this.f8473a.skip(j);
    }

    public boolean markSupported() {
        return this.f8473a.markSupported();
    }
}
