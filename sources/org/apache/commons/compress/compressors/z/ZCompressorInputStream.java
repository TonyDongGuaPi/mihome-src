package org.apache.commons.compress.compressors.z;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

public class ZCompressorInputStream extends LZWInputStream {
    private static final int d = 31;
    private static final int e = 157;
    private static final int f = 128;
    private static final int g = 31;
    private final boolean h;
    private final int i;
    private long j = 0;

    public ZCompressorInputStream(InputStream inputStream) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        int b = (int) this.c.b(8);
        int b2 = (int) this.c.b(8);
        int b3 = (int) this.c.b(8);
        if (b == 31 && b2 == 157 && b3 >= 0) {
            this.h = (b3 & 128) != 0;
            this.i = b3 & 31;
            if (this.h) {
                b(9);
            }
            c(this.i);
            m();
            return;
        }
        throw new IOException("Input is not in .Z format");
    }

    private void m() {
        f((this.h ? 1 : 0) + true);
    }

    /* access modifiers changed from: protected */
    public int d() throws IOException {
        int d2 = super.d();
        if (d2 >= 0) {
            this.j++;
        }
        return d2;
    }

    private void n() throws IOException {
        long j2 = 8 - (this.j % 8);
        if (j2 == 8) {
            j2 = 0;
        }
        for (long j3 = 0; j3 < j2; j3++) {
            d();
        }
        this.c.c();
    }

    /* access modifiers changed from: protected */
    public int a(int i2, byte b) throws IOException {
        int f2 = 1 << f();
        int a2 = a(i2, b, f2);
        if (l() == f2 && f() < this.i) {
            n();
            h();
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int a() throws IOException {
        int d2 = d();
        if (d2 < 0) {
            return -1;
        }
        boolean z = false;
        if (!this.h || d2 != k()) {
            if (d2 == l()) {
                e();
                z = true;
            } else if (d2 > l()) {
                throw new IOException(String.format("Invalid %d bit code 0x%x", new Object[]{Integer.valueOf(f()), Integer.valueOf(d2)}));
            }
            return a(d2, z);
        }
        m();
        n();
        g();
        i();
        return 0;
    }

    public static boolean a(byte[] bArr, int i2) {
        return i2 > 3 && bArr[0] == 31 && bArr[1] == -99;
    }
}
