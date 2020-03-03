package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.apache.commons.compress.compressors.CompressorInputStream;

public class BZip2CompressorInputStream extends CompressorInputStream implements BZip2Constants {
    private static final int A = 6;
    private static final int B = 7;
    private static final int u = 0;
    private static final int v = 1;
    private static final int w = 2;
    private static final int x = 3;
    private static final int y = 4;
    private static final int z = 5;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private char P;
    private Data Q;

    /* renamed from: a  reason: collision with root package name */
    private int f3311a;
    private int b;
    private int m;
    private boolean n;
    private int o;
    private int p;
    private final CRC q;
    private int r;
    private InputStream s;
    private final boolean t;

    public BZip2CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public BZip2CompressorInputStream(InputStream inputStream, boolean z2) throws IOException {
        this.q = new CRC();
        this.C = 1;
        this.s = inputStream;
        this.t = z2;
        a(true);
        e();
    }

    public int read() throws IOException {
        if (this.s != null) {
            int d = d();
            a(d < 0 ? -1 : 1);
            return d;
        }
        throw new IOException("stream closed");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > dest.length(" + bArr.length + ").");
            } else if (this.s == null) {
                throw new IOException("stream closed");
            } else if (i2 == 0) {
                return 0;
            } else {
                int i4 = i;
                while (i4 < i3) {
                    int d = d();
                    if (d < 0) {
                        break;
                    }
                    bArr[i4] = (byte) d;
                    a(1);
                    i4++;
                }
                if (i4 == i) {
                    return -1;
                }
                return i4 - i;
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void a() {
        boolean[] zArr = this.Q.f3312a;
        byte[] bArr = this.Q.b;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                bArr[i] = (byte) i2;
                i++;
            }
        }
        this.r = i;
    }

    private int d() throws IOException {
        switch (this.C) {
            case 0:
                return -1;
            case 1:
                return m();
            case 2:
                throw new IllegalStateException();
            case 3:
                return p();
            case 4:
                return q();
            case 5:
                throw new IllegalStateException();
            case 6:
                return r();
            case 7:
                return s();
            default:
                throw new IllegalStateException();
        }
    }

    private boolean a(boolean z2) throws IOException {
        if (this.s != null) {
            int read = this.s.read();
            if (read == -1 && !z2) {
                return false;
            }
            int read2 = this.s.read();
            int read3 = this.s.read();
            if (read == 66 && read2 == 90 && read3 == 104) {
                int read4 = this.s.read();
                if (read4 < 49 || read4 > 57) {
                    throw new IOException("BZip2 block size is invalid");
                }
                this.m = read4 - 48;
                this.p = 0;
                this.G = 0;
                return true;
            }
            throw new IOException(z2 ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
        }
        throw new IOException("No InputStream");
    }

    private void e() throws IOException {
        do {
            char i = i();
            char i2 = i();
            char i3 = i();
            char i4 = i();
            char i5 = i();
            char i6 = i();
            if (i != 23 || i2 != 'r' || i3 != 'E' || i4 != '8' || i5 != 'P' || i6 != 144) {
                boolean z2 = false;
                if (i == '1' && i2 == 'A' && i3 == 'Y' && i4 == '&' && i5 == 'S' && i6 == 'Y') {
                    this.D = j();
                    if (b(1) == 1) {
                        z2 = true;
                    }
                    this.n = z2;
                    if (this.Q == null) {
                        this.Q = new Data(this.m);
                    }
                    l();
                    this.q.a();
                    this.C = 1;
                    return;
                }
                this.C = 0;
                throw new IOException("bad block header");
            }
        } while (!g());
    }

    private void f() throws IOException {
        this.F = this.q.b();
        if (this.D == this.F) {
            this.G = (this.G << 1) | (this.G >>> 31);
            this.G ^= this.F;
            return;
        }
        this.G = (this.E << 1) | (this.E >>> 31);
        this.G ^= this.D;
        throw new IOException("BZip2 CRC error");
    }

    private boolean g() throws IOException {
        this.E = j();
        this.C = 0;
        this.Q = null;
        if (this.E != this.G) {
            throw new IOException("BZip2 CRC error");
        } else if (!this.t || !a(false)) {
            return true;
        } else {
            return false;
        }
    }

    public void close() throws IOException {
        InputStream inputStream = this.s;
        if (inputStream != null) {
            try {
                if (inputStream != System.in) {
                    inputStream.close();
                }
            } finally {
                this.Q = null;
                this.s = null;
            }
        }
    }

    private int b(int i) throws IOException {
        int i2 = this.p;
        int i3 = this.o;
        if (i2 < i) {
            InputStream inputStream = this.s;
            do {
                int read = inputStream.read();
                if (read >= 0) {
                    i3 = (i3 << 8) | read;
                    i2 += 8;
                } else {
                    throw new IOException("unexpected end of stream");
                }
            } while (i2 < i);
            this.o = i3;
        }
        int i4 = i2 - i;
        this.p = i4;
        return ((1 << i) - 1) & (i3 >> i4);
    }

    private boolean h() throws IOException {
        return b(1) != 0;
    }

    private char i() throws IOException {
        return (char) b(8);
    }

    private int j() throws IOException {
        return b(8) | (((((b(8) << 8) | b(8)) << 8) | b(8)) << 8);
    }

    private static void a(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        while (i5 <= i2) {
            int i7 = i6;
            for (int i8 = 0; i8 < i3; i8++) {
                if (cArr[i8] == i5) {
                    iArr3[i7] = i8;
                    i7++;
                }
            }
            i5++;
            i6 = i7;
        }
        int i9 = 23;
        while (true) {
            i9--;
            if (i9 <= 0) {
                break;
            }
            iArr2[i9] = 0;
            iArr[i9] = 0;
        }
        for (int i10 = 0; i10 < i3; i10++) {
            int i11 = cArr[i10] + 1;
            iArr2[i11] = iArr2[i11] + 1;
        }
        int i12 = iArr2[0];
        for (int i13 = 1; i13 < 23; i13++) {
            i12 += iArr2[i13];
            iArr2[i13] = i12;
        }
        int i14 = iArr2[i];
        int i15 = i;
        while (i15 <= i2) {
            int i16 = i15 + 1;
            int i17 = iArr2[i16];
            int i18 = i4 + (i17 - i14);
            iArr[i15] = i18 - 1;
            i4 = i18 << 1;
            i15 = i16;
            i14 = i17;
        }
        for (int i19 = i + 1; i19 <= i2; i19++) {
            iArr2[i19] = ((iArr[i19 - 1] + 1) << 1) - iArr2[i19];
        }
    }

    private void k() throws IOException {
        Data data = this.Q;
        boolean[] zArr = data.f3312a;
        byte[] bArr = data.m;
        byte[] bArr2 = data.c;
        byte[] bArr3 = data.d;
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            if (h()) {
                i |= 1 << i2;
            }
        }
        int i3 = 256;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            zArr[i3] = false;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            if (((1 << i4) & i) != 0) {
                int i5 = i4 << 4;
                for (int i6 = 0; i6 < 16; i6++) {
                    if (h()) {
                        zArr[i5 + i6] = true;
                    }
                }
            }
        }
        a();
        int i7 = this.r + 2;
        int b2 = b(3);
        int b3 = b(15);
        for (int i8 = 0; i8 < b3; i8++) {
            int i9 = 0;
            while (h()) {
                i9++;
            }
            bArr3[i8] = (byte) i9;
        }
        int i10 = b2;
        while (true) {
            i10--;
            if (i10 < 0) {
                break;
            }
            bArr[i10] = (byte) i10;
        }
        for (int i11 = 0; i11 < b3; i11++) {
            int i12 = bArr3[i11] & 255;
            byte b4 = bArr[i12];
            while (i12 > 0) {
                bArr[i12] = bArr[i12 - 1];
                i12--;
            }
            bArr[0] = b4;
            bArr2[i11] = b4;
        }
        char[][] cArr = data.l;
        for (int i13 = 0; i13 < b2; i13++) {
            int b5 = b(5);
            char[] cArr2 = cArr[i13];
            int i14 = b5;
            for (int i15 = 0; i15 < i7; i15++) {
                while (h()) {
                    i14 += h() ? -1 : 1;
                }
                cArr2[i15] = (char) i14;
            }
        }
        a(i7, b2);
    }

    private void a(int i, int i2) {
        Data data = this.Q;
        char[][] cArr = data.l;
        int[] iArr = data.i;
        int[][] iArr2 = data.f;
        int[][] iArr3 = data.g;
        int[][] iArr4 = data.h;
        int i3 = i2;
        for (int i4 = 0; i4 < i3; i4++) {
            char[] cArr2 = cArr[i4];
            int i5 = i;
            char c = ' ';
            char c2 = 0;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                char c3 = cArr2[i5];
                if (c3 > c2) {
                    c2 = c3;
                }
                if (c3 < c) {
                    c = c3;
                }
            }
            int i6 = c;
            a(iArr2[i4], iArr3[i4], iArr4[i4], cArr[i4], i6, c2, i);
            iArr[i4] = i6;
        }
    }

    private void l() throws IOException {
        int i;
        int i2;
        char c;
        int i3;
        BZip2CompressorInputStream bZip2CompressorInputStream = this;
        bZip2CompressorInputStream.b = bZip2CompressorInputStream.b(24);
        k();
        InputStream inputStream = bZip2CompressorInputStream.s;
        Data data = bZip2CompressorInputStream.Q;
        byte[] bArr = data.o;
        int[] iArr = data.e;
        byte[] bArr2 = data.c;
        byte[] bArr3 = data.b;
        char[] cArr = data.k;
        int[] iArr2 = data.i;
        int[][] iArr3 = data.f;
        int[][] iArr4 = data.g;
        int[][] iArr5 = data.h;
        int i4 = bZip2CompressorInputStream.m * 100000;
        int i5 = 256;
        while (true) {
            i5--;
            if (i5 < 0) {
                break;
            }
            cArr[i5] = (char) i5;
            iArr[i5] = 0;
        }
        int i6 = bZip2CompressorInputStream.r + 1;
        int c2 = bZip2CompressorInputStream.c(0);
        int i7 = bZip2CompressorInputStream.o;
        int i8 = bZip2CompressorInputStream.p;
        int i9 = i7;
        byte b2 = bArr2[0] & 255;
        int[] iArr6 = iArr4[b2];
        int[] iArr7 = iArr3[b2];
        int i10 = i8;
        int i11 = i9;
        int[] iArr8 = iArr5[b2];
        int i12 = -1;
        int i13 = 0;
        int[] iArr9 = iArr7;
        int[] iArr10 = iArr6;
        int i14 = iArr2[b2];
        int i15 = c2;
        int i16 = 49;
        while (i15 != i6) {
            if (i15 == 0 || i15 == 1) {
                int i17 = i6;
                int i18 = -1;
                int i19 = 1;
                while (true) {
                    if (i15 == 0) {
                        i18 += i19;
                    } else if (i15 == 1) {
                        i18 += i19 << 1;
                    } else {
                        int[][] iArr11 = iArr5;
                        byte b3 = bArr3[cArr[0]];
                        byte b4 = b3 & 255;
                        iArr[b4] = iArr[b4] + i18 + 1;
                        while (true) {
                            int i20 = i18 - 1;
                            if (i18 < 0) {
                                break;
                            }
                            i12++;
                            bArr[i12] = b3;
                            i18 = i20;
                        }
                        if (i12 < i4) {
                            i6 = i17;
                            iArr5 = iArr11;
                            bZip2CompressorInputStream = this;
                        } else {
                            throw new IOException("block overrun");
                        }
                    }
                    if (i16 == 0) {
                        i13++;
                        byte b5 = bArr2[i13] & 255;
                        iArr10 = iArr4[b5];
                        iArr9 = iArr3[b5];
                        iArr8 = iArr5[b5];
                        i = iArr2[b5];
                        i2 = 49;
                    } else {
                        i2 = i16 - 1;
                        i = i14;
                    }
                    int i21 = i10;
                    while (i21 < i) {
                        int read = inputStream.read();
                        if (read >= 0) {
                            i11 = (i11 << 8) | read;
                            i21 += 8;
                        } else {
                            throw new IOException("unexpected end of stream");
                        }
                    }
                    int i22 = i21 - i;
                    int[][] iArr12 = iArr5;
                    int i23 = i22;
                    int i24 = (i11 >> i22) & ((1 << i) - 1);
                    int i25 = i;
                    while (i24 > iArr9[i25]) {
                        int i26 = i;
                        int i27 = i25 + 1;
                        int i28 = i10;
                        while (i28 < 1) {
                            int read2 = inputStream.read();
                            if (read2 >= 0) {
                                i11 = (i11 << 8) | read2;
                                i28 += 8;
                            } else {
                                throw new IOException("unexpected end of stream");
                            }
                        }
                        i23 = i28 - 1;
                        i24 = (i24 << 1) | ((i11 >> i23) & 1);
                        i = i26;
                        i25 = i27;
                    }
                    i15 = iArr8[i24 - iArr10[i25]];
                    i19 <<= 1;
                    iArr5 = iArr12;
                    i14 = i;
                }
            } else {
                i12++;
                if (i12 < i4) {
                    int i29 = i15 - 1;
                    char c3 = cArr[i29];
                    int i30 = i6;
                    byte b6 = bArr3[c3] & 255;
                    iArr[b6] = iArr[b6] + 1;
                    bArr[i12] = bArr3[c3];
                    if (i15 <= 16) {
                        while (i29 > 0) {
                            int i31 = i29 - 1;
                            cArr[i29] = cArr[i31];
                            i29 = i31;
                        }
                        c = 0;
                    } else {
                        c = 0;
                        System.arraycopy(cArr, 0, cArr, 1, i29);
                    }
                    cArr[c] = c3;
                    if (i16 == 0) {
                        i13++;
                        byte b7 = bArr2[i13] & 255;
                        int[] iArr13 = iArr4[b7];
                        int[] iArr14 = iArr3[b7];
                        int[] iArr15 = iArr5[b7];
                        i3 = iArr2[b7];
                        iArr10 = iArr13;
                        iArr9 = iArr14;
                        iArr8 = iArr15;
                        i16 = 49;
                    } else {
                        i16--;
                        i3 = i14;
                    }
                    int i32 = i10;
                    while (i32 < i3) {
                        int read3 = inputStream.read();
                        if (read3 >= 0) {
                            i11 = (i11 << 8) | read3;
                            i32 += 8;
                        } else {
                            throw new IOException("unexpected end of stream");
                        }
                    }
                    int i33 = i32 - i3;
                    int i34 = (i11 >> i33) & ((1 << i3) - 1);
                    int i35 = i3;
                    i10 = i33;
                    while (i34 > iArr9[i35]) {
                        i35++;
                        int i36 = i3;
                        int i37 = i10;
                        while (i37 < 1) {
                            int read4 = inputStream.read();
                            if (read4 >= 0) {
                                i11 = (i11 << 8) | read4;
                                i37 += 8;
                            } else {
                                throw new IOException("unexpected end of stream");
                            }
                        }
                        i10 = i37 - 1;
                        i34 = (i34 << 1) | ((i11 >> i10) & 1);
                        i3 = i36;
                    }
                    int i38 = i3;
                    i15 = iArr8[i34 - iArr10[i35]];
                    i6 = i30;
                    i14 = i38;
                } else {
                    throw new IOException("block overrun");
                }
            }
        }
        bZip2CompressorInputStream.f3311a = i12;
        bZip2CompressorInputStream.p = i10;
        bZip2CompressorInputStream.o = i11;
    }

    private int c(int i) throws IOException {
        InputStream inputStream = this.s;
        Data data = this.Q;
        byte b2 = data.c[i] & 255;
        int[] iArr = data.f[b2];
        int i2 = data.i[b2];
        int b3 = b(i2);
        int i3 = this.p;
        int i4 = this.o;
        while (b3 > iArr[i2]) {
            i2++;
            while (i3 < 1) {
                int read = inputStream.read();
                if (read >= 0) {
                    i4 = (i4 << 8) | read;
                    i3 += 8;
                } else {
                    throw new IOException("unexpected end of stream");
                }
            }
            i3--;
            b3 = (b3 << 1) | (1 & (i4 >> i3));
        }
        this.p = i3;
        this.o = i4;
        return data.h[b2][b3 - data.g[b2][i2]];
    }

    private int m() throws IOException {
        if (this.C == 0 || this.Q == null) {
            return -1;
        }
        int[] iArr = this.Q.j;
        int[] a2 = this.Q.a(this.f3311a + 1);
        byte[] bArr = this.Q.o;
        iArr[0] = 0;
        System.arraycopy(this.Q.e, 0, iArr, 1, 256);
        int i = iArr[0];
        for (int i2 = 1; i2 <= 256; i2++) {
            i += iArr[i2];
            iArr[i2] = i;
        }
        int i3 = this.f3311a;
        for (int i4 = 0; i4 <= i3; i4++) {
            byte b2 = bArr[i4] & 255;
            int i5 = iArr[b2];
            iArr[b2] = i5 + 1;
            a2[i5] = i4;
        }
        if (this.b < 0 || this.b >= a2.length) {
            throw new IOException("stream corrupted");
        }
        this.O = a2[this.b];
        this.H = 0;
        this.K = 0;
        this.I = 256;
        if (!this.n) {
            return o();
        }
        this.M = 0;
        this.N = 0;
        return n();
    }

    private int n() throws IOException {
        if (this.K <= this.f3311a) {
            this.J = this.I;
            byte b2 = this.Q.o[this.O] & 255;
            this.O = this.Q.n[this.O];
            byte b3 = 0;
            if (this.M == 0) {
                this.M = Rand.a(this.N) - 1;
                int i = this.N + 1;
                this.N = i;
                if (i == 512) {
                    this.N = 0;
                }
            } else {
                this.M--;
            }
            if (this.M == 1) {
                b3 = 1;
            }
            byte b4 = b2 ^ b3;
            this.I = b4;
            this.K++;
            this.C = 3;
            this.q.b(b4);
            return b4;
        }
        f();
        e();
        return m();
    }

    private int o() throws IOException {
        if (this.K <= this.f3311a) {
            this.J = this.I;
            byte b2 = this.Q.o[this.O] & 255;
            this.I = b2;
            this.O = this.Q.n[this.O];
            this.K++;
            this.C = 6;
            this.q.b(b2);
            return b2;
        }
        this.C = 5;
        f();
        e();
        return m();
    }

    private int p() throws IOException {
        if (this.I != this.J) {
            this.C = 2;
            this.H = 1;
            return n();
        }
        int i = this.H + 1;
        this.H = i;
        if (i >= 4) {
            this.P = (char) (this.Q.o[this.O] & 255);
            this.O = this.Q.n[this.O];
            if (this.M == 0) {
                this.M = Rand.a(this.N) - 1;
                int i2 = this.N + 1;
                this.N = i2;
                if (i2 == 512) {
                    this.N = 0;
                }
            } else {
                this.M--;
            }
            this.L = 0;
            this.C = 4;
            if (this.M == 1) {
                this.P = (char) (this.P ^ 1);
            }
            return q();
        }
        this.C = 2;
        return n();
    }

    private int q() throws IOException {
        if (this.L < this.P) {
            this.q.b(this.I);
            this.L++;
            return this.I;
        }
        this.C = 2;
        this.K++;
        this.H = 0;
        return n();
    }

    private int r() throws IOException {
        if (this.I != this.J) {
            this.H = 1;
            return o();
        }
        int i = this.H + 1;
        this.H = i;
        if (i < 4) {
            return o();
        }
        this.P = (char) (this.Q.o[this.O] & 255);
        this.O = this.Q.n[this.O];
        this.L = 0;
        return s();
    }

    private int s() throws IOException {
        if (this.L < this.P) {
            int i = this.I;
            this.q.b(i);
            this.L++;
            this.C = 7;
            return i;
        }
        this.K++;
        this.H = 0;
        return o();
    }

    private static final class Data {

        /* renamed from: a  reason: collision with root package name */
        final boolean[] f3312a = new boolean[256];
        final byte[] b = new byte[256];
        final byte[] c = new byte[BZip2Constants.k];
        final byte[] d = new byte[BZip2Constants.k];
        final int[] e = new int[256];
        final int[][] f = ((int[][]) Array.newInstance(int.class, new int[]{6, 258}));
        final int[][] g = ((int[][]) Array.newInstance(int.class, new int[]{6, 258}));
        final int[][] h = ((int[][]) Array.newInstance(int.class, new int[]{6, 258}));
        final int[] i = new int[6];
        final int[] j = new int[257];
        final char[] k = new char[256];
        final char[][] l = ((char[][]) Array.newInstance(char.class, new int[]{6, 258}));
        final byte[] m = new byte[6];
        int[] n;
        byte[] o;

        Data(int i2) {
            this.o = new byte[(i2 * 100000)];
        }

        /* access modifiers changed from: package-private */
        public int[] a(int i2) {
            int[] iArr = this.n;
            if (iArr != null && iArr.length >= i2) {
                return iArr;
            }
            int[] iArr2 = new int[i2];
            this.n = iArr2;
            return iArr2;
        }
    }

    public static boolean a(byte[] bArr, int i) {
        return i >= 3 && bArr[0] == 66 && bArr[1] == 90 && bArr[2] == 104;
    }
}
