package org.apache.commons.compress.compressors.lzw;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;

public abstract class LZWInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f3329a = 9;
    protected static final int b = -1;
    protected final BitInputStream c;
    private final byte[] d = new byte[1];
    private int e = -1;
    private int f = 9;
    private byte g;
    private int h = -1;
    private int i;
    private int[] j;
    private byte[] k;
    private byte[] l;
    private int m;

    /* access modifiers changed from: protected */
    public abstract int a() throws IOException;

    /* access modifiers changed from: protected */
    public abstract int a(int i2, byte b2) throws IOException;

    protected LZWInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.c = new BitInputStream(inputStream, byteOrder);
    }

    public void close() throws IOException {
        this.c.close();
    }

    public int read() throws IOException {
        int read = read(this.d);
        if (read < 0) {
            return read;
        }
        return this.d[0] & 255;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int a2 = a(bArr, i2, i3);
        while (true) {
            int i4 = i3 - a2;
            if (i4 > 0) {
                int a3 = a();
                if (a3 >= 0) {
                    a2 += a(bArr, i2 + a2, i4);
                } else if (a2 <= 0) {
                    return a3;
                } else {
                    a(a2);
                    return a2;
                }
            } else {
                a(a2);
                return a2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2) {
        this.e = 1 << (i2 - 1);
    }

    /* access modifiers changed from: protected */
    public void c(int i2) {
        int i3 = 1 << i2;
        this.j = new int[i3];
        this.k = new byte[i3];
        this.l = new byte[i3];
        this.m = i3;
        for (int i4 = 0; i4 < 256; i4++) {
            this.j[i4] = -1;
            this.k[i4] = (byte) i4;
        }
    }

    /* access modifiers changed from: protected */
    public int d() throws IOException {
        if (this.f <= 31) {
            return (int) this.c.b(this.f);
        }
        throw new IllegalArgumentException("code size must not be bigger than 31");
    }

    /* access modifiers changed from: protected */
    public int a(int i2, byte b2, int i3) {
        if (this.i >= i3) {
            return -1;
        }
        this.j[this.i] = i2;
        this.k[this.i] = b2;
        int i4 = this.i;
        this.i = i4 + 1;
        return i4;
    }

    /* access modifiers changed from: protected */
    public int e() throws IOException {
        if (this.h != -1) {
            return a(this.h, this.g);
        }
        throw new IOException("The first code can't be a reference to its preceding code");
    }

    /* access modifiers changed from: protected */
    public int a(int i2, boolean z) throws IOException {
        int i3 = i2;
        while (i3 >= 0) {
            byte[] bArr = this.l;
            int i4 = this.m - 1;
            this.m = i4;
            bArr[i4] = this.k[i3];
            i3 = this.j[i3];
        }
        if (this.h != -1 && !z) {
            a(this.h, this.l[this.m]);
        }
        this.h = i2;
        this.g = this.l[this.m];
        return this.m;
    }

    private int a(byte[] bArr, int i2, int i3) {
        int length = this.l.length - this.m;
        if (length <= 0) {
            return 0;
        }
        int min = Math.min(length, i3);
        System.arraycopy(this.l, this.m, bArr, i2, min);
        this.m += min;
        return min;
    }

    /* access modifiers changed from: protected */
    public int f() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void g() {
        d(9);
    }

    /* access modifiers changed from: protected */
    public void d(int i2) {
        this.f = i2;
    }

    /* access modifiers changed from: protected */
    public void h() {
        this.f++;
    }

    /* access modifiers changed from: protected */
    public void i() {
        this.h = -1;
    }

    /* access modifiers changed from: protected */
    public int e(int i2) {
        return this.j[i2];
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3) {
        this.j[i2] = i3;
    }

    /* access modifiers changed from: protected */
    public int j() {
        return this.j.length;
    }

    /* access modifiers changed from: protected */
    public int k() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public int l() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public void f(int i2) {
        this.i = i2;
    }
}
