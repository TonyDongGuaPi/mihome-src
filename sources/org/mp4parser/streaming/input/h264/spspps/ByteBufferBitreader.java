package org.mp4parser.streaming.input.h264.spspps;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class ByteBufferBitreader {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f4077a;
    int b;
    private int c = a();
    private int d = a();

    public ByteBufferBitreader(ByteBuffer byteBuffer) {
        this.f4077a = byteBuffer;
    }

    public int a() {
        try {
            byte b2 = this.f4077a.get();
            return b2 < 0 ? b2 + 256 : b2;
        } catch (BufferUnderflowException unused) {
            return -1;
        }
    }

    public int b() throws IOException {
        if (this.b == 8) {
            g();
            if (this.c == -1) {
                return -1;
            }
        }
        int i = (this.c >> (7 - this.b)) & 1;
        this.b++;
        return i;
    }

    private void g() throws IOException {
        this.c = this.d;
        this.d = a();
        this.b = 0;
    }

    public int c() throws IOException {
        int i = 0;
        while (b() == 0) {
            i++;
        }
        if (i > 0) {
            return (int) (((long) ((1 << i) - 1)) + a(i));
        }
        return 0;
    }

    public long a(int i) throws IOException {
        if (i <= 64) {
            long j = 0;
            for (int i2 = 0; i2 < i; i2++) {
                j = (j << 1) | ((long) b());
            }
            return j;
        }
        throw new IllegalArgumentException("Can not readByte more then 64 bit");
    }

    public boolean d() throws IOException {
        return b() != 0;
    }

    public int e() throws IOException {
        int c2 = c();
        int i = c2 & 1;
        return ((c2 >> 1) + i) * ((i << 1) - 1);
    }

    public boolean f() throws IOException {
        if (this.b == 8) {
            g();
        }
        int i = 1 << ((8 - this.b) - 1);
        boolean z = (((i << 1) - 1) & this.c) == i;
        if (this.c == -1 || (this.d == -1 && z)) {
            return false;
        }
        return true;
    }
}
