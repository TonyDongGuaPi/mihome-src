package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

class UnshrinkingInputStream extends LZWInputStream {
    private static final int d = 13;
    private static final int e = 8192;
    private final boolean[] f = new boolean[j()];

    public UnshrinkingInputStream(InputStream inputStream) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        b(9);
        c(13);
        for (int i = 0; i < 256; i++) {
            this.f[i] = true;
        }
        f(k() + 1);
    }

    /* access modifiers changed from: protected */
    public int a(int i, byte b) throws IOException {
        int l = l();
        while (l < 8192 && this.f[l]) {
            l++;
        }
        f(l);
        int a2 = a(i, b, 8192);
        if (a2 >= 0) {
            this.f[a2] = true;
        }
        return a2;
    }

    private void m() {
        boolean[] zArr = new boolean[8192];
        for (int i = 0; i < this.f.length; i++) {
            if (this.f[i] && e(i) != -1) {
                zArr[e(i)] = true;
            }
        }
        for (int k = k() + 1; k < zArr.length; k++) {
            if (!zArr[k]) {
                this.f[k] = false;
                a(k, -1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int a() throws IOException {
        int d2 = d();
        if (d2 < 0) {
            return -1;
        }
        boolean z = false;
        if (d2 == k()) {
            int d3 = d();
            if (d3 >= 0) {
                if (d3 == 1) {
                    if (f() < 13) {
                        h();
                    } else {
                        throw new IOException("Attempt to increase code size beyond maximum");
                    }
                } else if (d3 == 2) {
                    m();
                    f(k() + 1);
                } else {
                    throw new IOException("Invalid clear code subcode " + d3);
                }
                return 0;
            }
            throw new IOException("Unexpected EOF;");
        }
        if (!this.f[d2]) {
            d2 = e();
            z = true;
        }
        return a(d2, z);
    }
}
