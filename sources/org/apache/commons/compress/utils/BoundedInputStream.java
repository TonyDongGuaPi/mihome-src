package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;

public class BoundedInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f3345a;
    private long b;

    public void close() {
    }

    public BoundedInputStream(InputStream inputStream, long j) {
        this.f3345a = inputStream;
        this.b = j;
    }

    public int read() throws IOException {
        if (this.b <= 0) {
            return -1;
        }
        this.b--;
        return this.f3345a.read();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.b == 0) {
            return -1;
        }
        if (((long) i2) > this.b) {
            i2 = (int) this.b;
        }
        int read = this.f3345a.read(bArr, i, i2);
        if (read >= 0) {
            this.b -= (long) read;
        }
        return read;
    }
}
