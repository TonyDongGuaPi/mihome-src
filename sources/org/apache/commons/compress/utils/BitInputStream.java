package org.apache.commons.compress.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

public class BitInputStream implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3344a = 63;
    private static final long[] b = new long[64];
    private final InputStream c;
    private final ByteOrder d;
    private long e = 0;
    private int f = 0;

    static {
        for (int i = 1; i <= 63; i++) {
            b[i] = (b[i - 1] << 1) + 1;
        }
    }

    public BitInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.c = inputStream;
        this.d = byteOrder;
    }

    public void close() throws IOException {
        this.c.close();
    }

    public void c() {
        this.e = 0;
        this.f = 0;
    }

    public long b(int i) throws IOException {
        long j;
        long j2;
        if (i < 0 || i > 63) {
            throw new IllegalArgumentException("count must not be negative or greater than 63");
        }
        while (true) {
            j = 0;
            if (this.f >= i || this.f >= 57) {
                int i2 = 0;
            } else {
                long read = (long) this.c.read();
                if (read < 0) {
                    return read;
                }
                if (this.d == ByteOrder.LITTLE_ENDIAN) {
                    this.e |= read << this.f;
                } else {
                    this.e <<= 8;
                    this.e |= read;
                }
                this.f += 8;
            }
        }
        int i22 = 0;
        if (this.f < i) {
            int i3 = i - this.f;
            int i4 = 8 - i3;
            long read2 = (long) this.c.read();
            if (read2 < 0) {
                return read2;
            }
            if (this.d == ByteOrder.LITTLE_ENDIAN) {
                this.e = ((read2 & b[i3]) << this.f) | this.e;
                j = (read2 >>> i3) & b[i4];
            } else {
                this.e <<= i3;
                this.e = ((read2 >>> i4) & b[i3]) | this.e;
                j = b[i4] & read2;
            }
            this.f = i;
            i22 = i4;
        }
        if (i22 == 0) {
            if (this.d == ByteOrder.LITTLE_ENDIAN) {
                j2 = this.e & b[i];
                this.e >>>= i;
            } else {
                j2 = (this.e >> (this.f - i)) & b[i];
            }
            this.f -= i;
            return j2;
        }
        this.e = j;
        this.f = i22;
        return this.e & b[i];
    }
}
