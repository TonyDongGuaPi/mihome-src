package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private long f3349a;

    public CountingInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            a(1);
        }
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read >= 0) {
            a((long) read);
        }
        return read;
    }

    /* access modifiers changed from: protected */
    public final void a(long j) {
        if (j != -1) {
            this.f3349a += j;
        }
    }

    public long a() {
        return this.f3349a;
    }
}
