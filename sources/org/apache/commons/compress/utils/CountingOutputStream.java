package org.apache.commons.compress.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private long f3350a = 0;

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        a(1);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        a((long) i2);
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j != -1) {
            this.f3350a += j;
        }
    }

    public long a() {
        return this.f3350a;
    }
}
