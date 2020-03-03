package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class BZip2CompressorOutputStream extends CompressorOutputStream implements BZip2Constants {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3313a = 1;
    public static final int b = 9;
    private static final int m = 15;
    private static final int n = 0;
    private Data A;
    private BlockSort B;
    private OutputStream C;
    private volatile boolean D;
    private int o;
    private final int p;
    private int q;
    private int r;
    private final CRC s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private final int z;

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0093, code lost:
        if (r4[r3[r11]] < r4[r3[r10]]) goto L_0x0097;
     */
    private static void a(byte[] r17, int[] r18, org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.Data r19, int r20, int r21) {
        /*
            r1 = r19
            r2 = r20
            int[] r3 = r1.n
            int[] r4 = r1.o
            int[] r1 = r1.p
            r5 = r2
        L_0x000b:
            r6 = -1
            int r5 = r5 + r6
            r7 = 1
            if (r5 < 0) goto L_0x001e
            int r6 = r5 + 1
            r8 = r18[r5]
            if (r8 != 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r7 = r18[r5]
        L_0x0019:
            int r7 = r7 << 8
            r4[r6] = r7
            goto L_0x000b
        L_0x001e:
            r0 = 1
        L_0x001f:
            if (r0 == 0) goto L_0x0119
            r0 = 0
            r3[r0] = r0
            r4[r0] = r0
            r5 = -2
            r1[r0] = r5
            r5 = 1
            r8 = 0
        L_0x002b:
            if (r5 > r2) goto L_0x004b
            r1[r5] = r6
            int r8 = r8 + 1
            r3[r8] = r5
            r9 = r3[r8]
            r10 = r8
        L_0x0036:
            r11 = r4[r9]
            int r12 = r10 >> 1
            r13 = r3[r12]
            r13 = r4[r13]
            if (r11 >= r13) goto L_0x0046
            r11 = r3[r12]
            r3[r10] = r11
            r10 = r12
            goto L_0x0036
        L_0x0046:
            r3[r10] = r9
            int r5 = r5 + 1
            goto L_0x002b
        L_0x004b:
            r5 = r2
        L_0x004c:
            if (r8 <= r7) goto L_0x00e8
            r9 = r3[r7]
            r10 = r3[r8]
            r3[r7] = r10
            int r8 = r8 + -1
            r10 = r3[r7]
            r11 = 1
        L_0x0059:
            int r12 = r11 << 1
            if (r12 <= r8) goto L_0x005e
            goto L_0x0075
        L_0x005e:
            if (r12 >= r8) goto L_0x006d
            int r13 = r12 + 1
            r14 = r3[r13]
            r14 = r4[r14]
            r15 = r3[r12]
            r15 = r4[r15]
            if (r14 >= r15) goto L_0x006d
            r12 = r13
        L_0x006d:
            r13 = r4[r10]
            r14 = r3[r12]
            r14 = r4[r14]
            if (r13 >= r14) goto L_0x00e0
        L_0x0075:
            r3[r11] = r10
            r13 = r3[r7]
            r10 = r3[r8]
            r3[r7] = r10
            int r14 = r8 + -1
            r15 = r3[r7]
            r8 = 1
        L_0x0082:
            int r10 = r8 << 1
            if (r10 <= r14) goto L_0x0087
            goto L_0x009f
        L_0x0087:
            if (r10 >= r14) goto L_0x0096
            int r11 = r10 + 1
            r12 = r3[r11]
            r12 = r4[r12]
            r16 = r3[r10]
            r0 = r4[r16]
            if (r12 >= r0) goto L_0x0096
            goto L_0x0097
        L_0x0096:
            r11 = r10
        L_0x0097:
            r0 = r4[r15]
            r10 = r3[r11]
            r10 = r4[r10]
            if (r0 >= r10) goto L_0x00d9
        L_0x009f:
            r3[r8] = r15
            int r5 = r5 + r7
            r1[r13] = r5
            r1[r9] = r5
            r0 = r4[r9]
            r8 = r4[r13]
            r9 = r0 & -256(0xffffffffffffff00, float:NaN)
            r10 = r8 & -256(0xffffffffffffff00, float:NaN)
            int r9 = r9 + r10
            r0 = r0 & 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r0 <= r8) goto L_0x00b6
            goto L_0x00b7
        L_0x00b6:
            r0 = r8
        L_0x00b7:
            int r0 = r0 + r7
            r0 = r0 | r9
            r4[r5] = r0
            r1[r5] = r6
            int r8 = r14 + 1
            r3[r8] = r5
            r0 = r3[r8]
            r9 = r4[r0]
            r10 = r8
        L_0x00c6:
            int r11 = r10 >> 1
            r12 = r3[r11]
            r12 = r4[r12]
            if (r9 >= r12) goto L_0x00d4
            r12 = r3[r11]
            r3[r10] = r12
            r10 = r11
            goto L_0x00c6
        L_0x00d4:
            r3[r10] = r0
            r0 = 0
            goto L_0x004c
        L_0x00d9:
            r0 = r3[r11]
            r3[r8] = r0
            r8 = r11
            r0 = 0
            goto L_0x0082
        L_0x00e0:
            r0 = r3[r12]
            r3[r11] = r0
            r11 = r12
            r0 = 0
            goto L_0x0059
        L_0x00e8:
            r0 = 1
            r5 = 0
        L_0x00ea:
            if (r0 > r2) goto L_0x0102
            r8 = r0
            r9 = 0
        L_0x00ee:
            r8 = r1[r8]
            if (r8 < 0) goto L_0x00f5
            int r9 = r9 + 1
            goto L_0x00ee
        L_0x00f5:
            int r8 = r0 + -1
            byte r10 = (byte) r9
            r17[r8] = r10
            r8 = r21
            if (r9 <= r8) goto L_0x00ff
            r5 = 1
        L_0x00ff:
            int r0 = r0 + 1
            goto L_0x00ea
        L_0x0102:
            r8 = r21
            if (r5 == 0) goto L_0x0116
            r0 = 1
        L_0x0107:
            if (r0 >= r2) goto L_0x0116
            r9 = r4[r0]
            int r9 = r9 >> 8
            int r9 = r9 >> r7
            int r9 = r9 + r7
            int r9 = r9 << 8
            r4[r0] = r9
            int r0 = r0 + 1
            goto L_0x0107
        L_0x0116:
            r0 = r5
            goto L_0x001f
        L_0x0119:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.a(byte[], int[], org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data, int, int):void");
    }

    public static int a(long j) {
        if (j > 0) {
            return (int) Math.min((j / 132000) + 1, 9);
        }
        return 9;
    }

    public BZip2CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }

    public BZip2CompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.s = new CRC();
        this.v = -1;
        this.w = 0;
        if (i < 1) {
            throw new IllegalArgumentException("blockSize(" + i + ") < 1");
        } else if (i <= 9) {
            this.p = i;
            this.C = outputStream;
            this.z = (this.p * 100000) - 20;
            d();
        } else {
            throw new IllegalArgumentException("blockSize(" + i + ") > 9");
        }
    }

    public void write(int i) throws IOException {
        if (!this.D) {
            a(i);
            return;
        }
        throw new IOException("closed");
    }

    private void c() throws IOException {
        int i = this.o;
        if (i < this.z) {
            int i2 = this.v;
            Data data = this.A;
            data.f3314a[i2] = true;
            byte b2 = (byte) i2;
            int i3 = this.w;
            this.s.a(i2, i3);
            switch (i3) {
                case 1:
                    data.q[i + 2] = b2;
                    this.o = i + 1;
                    return;
                case 2:
                    int i4 = i + 2;
                    data.q[i4] = b2;
                    data.q[i + 3] = b2;
                    this.o = i4;
                    return;
                case 3:
                    byte[] bArr = data.q;
                    bArr[i + 2] = b2;
                    int i5 = i + 3;
                    bArr[i5] = b2;
                    bArr[i + 4] = b2;
                    this.o = i5;
                    return;
                default:
                    int i6 = i3 - 4;
                    data.f3314a[i6] = true;
                    byte[] bArr2 = data.q;
                    bArr2[i + 2] = b2;
                    bArr2[i + 3] = b2;
                    bArr2[i + 4] = b2;
                    int i7 = i + 5;
                    bArr2[i7] = b2;
                    bArr2[i + 6] = (byte) i6;
                    this.o = i7;
                    return;
            }
        } else {
            f();
            e();
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (!this.D) {
            System.err.println("Unclosed BZip2CompressorOutputStream detected, will *not* close it");
        }
        super.finalize();
    }

    public void a() throws IOException {
        if (!this.D) {
            this.D = true;
            try {
                if (this.w > 0) {
                    c();
                }
                this.v = -1;
                f();
                g();
            } finally {
                this.C = null;
                this.B = null;
                this.A = null;
            }
        }
    }

    public void close() throws IOException {
        if (!this.D) {
            OutputStream outputStream = this.C;
            a();
            outputStream.close();
        }
    }

    public void flush() throws IOException {
        OutputStream outputStream = this.C;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    private void d() throws IOException {
        b(66);
        b(90);
        this.A = new Data(this.p);
        this.B = new BlockSort(this.A);
        b(104);
        b(this.p + 48);
        this.y = 0;
        e();
    }

    private void e() {
        this.s.a();
        this.o = -1;
        boolean[] zArr = this.A.f3314a;
        int i = 256;
        while (true) {
            i--;
            if (i >= 0) {
                zArr[i] = false;
            } else {
                return;
            }
        }
    }

    private void f() throws IOException {
        this.x = this.s.b();
        this.y = (this.y << 1) | (this.y >>> 31);
        this.y ^= this.x;
        if (this.o != -1) {
            m();
            b(49);
            b(65);
            b(89);
            b(38);
            b(83);
            b(89);
            c(this.x);
            a(1, 0);
            l();
        }
    }

    private void g() throws IOException {
        b(23);
        b(114);
        b(69);
        b(56);
        b(80);
        b(144);
        c(this.y);
        h();
    }

    public final int b() {
        return this.p;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > buf.length(" + bArr.length + ").");
            } else if (!this.D) {
                while (i < i3) {
                    a((int) bArr[i]);
                    i++;
                }
            } else {
                throw new IOException("stream closed");
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void a(int i) throws IOException {
        if (this.v != -1) {
            int i2 = i & 255;
            if (this.v == i2) {
                int i3 = this.w + 1;
                this.w = i3;
                if (i3 > 254) {
                    c();
                    this.v = -1;
                    this.w = 0;
                    return;
                }
                return;
            }
            c();
            this.w = 1;
            this.v = i2;
            return;
        }
        this.v = i & 255;
        this.w++;
    }

    private static void a(int[] iArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            int i5 = i4;
            for (int i6 = 0; i6 < i3; i6++) {
                if ((bArr[i6] & 255) == i) {
                    iArr[i6] = i5;
                    i5++;
                }
            }
            i4 = i5 << 1;
            i++;
        }
    }

    private void h() throws IOException {
        while (this.r > 0) {
            this.C.write(this.q >> 24);
            this.q <<= 8;
            this.r -= 8;
        }
    }

    private void a(int i, int i2) throws IOException {
        OutputStream outputStream = this.C;
        int i3 = this.r;
        int i4 = this.q;
        while (i3 >= 8) {
            outputStream.write(i4 >> 24);
            i4 <<= 8;
            i3 -= 8;
        }
        this.q = (i2 << ((32 - i3) - i)) | i4;
        this.r = i3 + i;
    }

    private void b(int i) throws IOException {
        a(8, i);
    }

    private void c(int i) throws IOException {
        a(8, (i >> 24) & 255);
        a(8, (i >> 16) & 255);
        a(8, (i >> 8) & 255);
        a(8, i & 255);
    }

    private void i() throws IOException {
        byte[][] bArr = this.A.g;
        int i = 2;
        int i2 = this.t + 2;
        int i3 = 6;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    bArr2[i4] = 15;
                }
            }
        }
        if (this.u >= 200) {
            i = this.u < 600 ? 3 : this.u < 1200 ? 4 : this.u < 2400 ? 5 : 6;
        }
        b(i, i2);
        int c = c(i, i2);
        d(i, c);
        e(i, i2);
        j();
        f(i, c);
        g(i, i2);
        k();
    }

    private void b(int i, int i2) {
        int i3;
        byte[][] bArr = this.A.g;
        int[] iArr = this.A.c;
        int i4 = this.u;
        int i5 = 0;
        for (int i6 = i; i6 > 0; i6--) {
            int i7 = i4 / i6;
            int i8 = i2 - 1;
            int i9 = i5 - 1;
            int i10 = 0;
            while (i10 < i7 && i9 < i8) {
                i9++;
                i10 += iArr[i9];
            }
            if (i9 <= i5 || i6 == i || i6 == 1 || (1 & (i - i6)) == 0) {
                i3 = i9;
            } else {
                i3 = i9 - 1;
                i10 -= iArr[i9];
            }
            byte[] bArr2 = bArr[i6 - 1];
            int i11 = i2;
            while (true) {
                i11--;
                if (i11 < 0) {
                    break;
                } else if (i11 < i5 || i11 > i3) {
                    bArr2[i11] = 15;
                } else {
                    bArr2[i11] = 0;
                }
            }
            i5 = i3 + 1;
            i4 -= i10;
        }
    }

    private int c(int i, int i2) {
        byte[] bArr;
        int i3;
        BZip2CompressorOutputStream bZip2CompressorOutputStream = this;
        int i4 = i;
        Data data = bZip2CompressorOutputStream.A;
        int[][] iArr = data.h;
        int[] iArr2 = data.i;
        short[] sArr = data.j;
        char[] cArr = data.s;
        byte[] bArr2 = data.d;
        byte[][] bArr3 = data.g;
        int i5 = 0;
        byte[] bArr4 = bArr3[0];
        byte[] bArr5 = bArr3[1];
        byte[] bArr6 = bArr3[2];
        byte[] bArr7 = bArr3[3];
        byte[] bArr8 = bArr3[4];
        byte[] bArr9 = bArr3[5];
        int i6 = bZip2CompressorOutputStream.u;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 4; i7 < i9; i9 = 4) {
            int i10 = i4;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                }
                iArr2[i10] = i5;
                int[] iArr3 = iArr[i10];
                int i11 = i2;
                while (true) {
                    i11--;
                    if (i11 >= 0) {
                        iArr3[i11] = i5;
                    }
                }
            }
            int i12 = 0;
            i8 = 0;
            while (i12 < bZip2CompressorOutputStream.u) {
                int i13 = i12;
                int min = Math.min((i12 + 50) - 1, i6 - 1);
                if (i4 == 6) {
                    int i14 = i13;
                    short s2 = 0;
                    short s3 = 0;
                    short s4 = 0;
                    short s5 = 0;
                    short s6 = 0;
                    short s7 = 0;
                    while (i14 <= min) {
                        char c = cArr[i14];
                        short s8 = (short) (s3 + (bArr5[c] & 255));
                        short s9 = (short) (s4 + (bArr6[c] & 255));
                        short s10 = (short) (s5 + (bArr7[c] & 255));
                        short s11 = (short) (s6 + (bArr8[c] & 255));
                        i14++;
                        s7 = (short) (s7 + (bArr9[c] & 255));
                        s2 = (short) (s2 + (bArr4[c] & 255));
                        i6 = i6;
                        bArr4 = bArr4;
                        s3 = s8;
                        s4 = s9;
                        s5 = s10;
                        s6 = s11;
                    }
                    bArr = bArr4;
                    i3 = i6;
                    sArr[0] = s2;
                    sArr[1] = s3;
                    sArr[2] = s4;
                    sArr[3] = s5;
                    sArr[4] = s6;
                    sArr[5] = s7;
                } else {
                    bArr = bArr4;
                    i3 = i6;
                    int i15 = i4;
                    while (true) {
                        i15--;
                        if (i15 < 0) {
                            break;
                        }
                        sArr[i15] = 0;
                    }
                    for (int i16 = i13; i16 <= min; i16++) {
                        char c2 = cArr[i16];
                        int i17 = i4;
                        while (true) {
                            i17--;
                            if (i17 < 0) {
                                break;
                            }
                            sArr[i17] = (short) (sArr[i17] + (bArr3[i17][c2] & 255));
                        }
                    }
                }
                int i18 = i4;
                short s12 = 999999999;
                int i19 = -1;
                while (true) {
                    i18--;
                    if (i18 < 0) {
                        break;
                    }
                    byte[] bArr10 = bArr5;
                    short s13 = sArr[i18];
                    if (s13 < s12) {
                        i19 = i18;
                        s12 = s13;
                    }
                    bArr5 = bArr10;
                }
                byte[] bArr11 = bArr5;
                iArr2[i19] = iArr2[i19] + 1;
                bArr2[i8] = (byte) i19;
                i8++;
                int[] iArr4 = iArr[i19];
                for (int i20 = i13; i20 <= min; i20++) {
                    char c3 = cArr[i20];
                    iArr4[c3] = iArr4[c3] + 1;
                }
                i12 = min + 1;
                i6 = i3;
                bArr4 = bArr;
                bArr5 = bArr11;
            }
            byte[] bArr12 = bArr4;
            byte[] bArr13 = bArr5;
            int i21 = i6;
            int i22 = 0;
            while (i22 < i4) {
                a(bArr3[i22], iArr[i22], bZip2CompressorOutputStream.A, i2, 20);
                i22++;
                bZip2CompressorOutputStream = this;
            }
            int i23 = i2;
            i7++;
            i6 = i21;
            bArr4 = bArr12;
            bArr5 = bArr13;
            bZip2CompressorOutputStream = this;
            i5 = 0;
        }
        return i8;
    }

    private void d(int i, int i2) {
        Data data = this.A;
        byte[] bArr = data.l;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            bArr[i] = (byte) i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b2 = data.d[i3];
            byte b3 = bArr[0];
            int i4 = 0;
            while (b2 != b3) {
                i4++;
                byte b4 = bArr[i4];
                bArr[i4] = b3;
                b3 = b4;
            }
            bArr[0] = b3;
            data.e[i3] = (byte) i4;
        }
    }

    private void e(int i, int i2) {
        int[][] iArr = this.A.k;
        byte[][] bArr = this.A.g;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            byte b2 = 0;
            byte b3 = 32;
            while (true) {
                i4--;
                if (i4 < 0) {
                    break;
                }
                byte b4 = bArr2[i4] & 255;
                if (b4 > b2) {
                    b2 = b4;
                }
                if (b4 < b3) {
                    b3 = b4;
                }
            }
            a(iArr[i3], bArr[i3], (int) b3, (int) b2, i2);
        }
    }

    private void j() throws IOException {
        boolean[] zArr = this.A.f3314a;
        boolean[] zArr2 = this.A.m;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                    }
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            a(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.C;
        int i5 = this.r;
        int i6 = this.q;
        int i7 = i5;
        for (int i8 = 0; i8 < 16; i8++) {
            if (zArr2[i8]) {
                int i9 = i8 * 16;
                int i10 = i6;
                for (int i11 = 0; i11 < 16; i11++) {
                    while (i7 >= 8) {
                        outputStream.write(i10 >> 24);
                        i10 <<= 8;
                        i7 -= 8;
                    }
                    if (zArr[i9 + i11]) {
                        i10 |= 1 << ((32 - i7) - 1);
                    }
                    i7++;
                }
                i6 = i10;
            }
        }
        this.q = i6;
        this.r = i7;
    }

    private void f(int i, int i2) throws IOException {
        a(3, i);
        a(15, i2);
        OutputStream outputStream = this.C;
        byte[] bArr = this.A.e;
        int i3 = this.r;
        int i4 = this.q;
        int i5 = i3;
        for (int i6 = 0; i6 < i2; i6++) {
            byte b2 = bArr[i6] & 255;
            int i7 = i5;
            int i8 = i4;
            for (int i9 = 0; i9 < b2; i9++) {
                while (i7 >= 8) {
                    outputStream.write(i8 >> 24);
                    i8 <<= 8;
                    i7 -= 8;
                }
                i8 |= 1 << ((32 - i7) - 1);
                i7++;
            }
            i4 = i8;
            while (i7 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i7 -= 8;
            }
            i5 = i7 + 1;
        }
        this.q = i4;
        this.r = i5;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g(int r18, int r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data r1 = r0.A
            byte[][] r1 = r1.g
            java.io.OutputStream r2 = r0.C
            int r3 = r0.r
            int r4 = r0.q
            r5 = 0
            r7 = r3
            r6 = r4
            r4 = 0
            r3 = r18
        L_0x0012:
            if (r4 >= r3) goto L_0x0085
            r8 = r1[r4]
            byte r9 = r8[r5]
            r9 = r9 & 255(0xff, float:3.57E-43)
        L_0x001a:
            r10 = 8
            if (r7 < r10) goto L_0x0028
            int r10 = r6 >> 24
            r2.write(r10)
            int r6 = r6 << 8
            int r7 = r7 + -8
            goto L_0x001a
        L_0x0028:
            int r11 = 32 - r7
            int r11 = r11 + -5
            int r11 = r9 << r11
            r6 = r6 | r11
            int r7 = r7 + 5
            r11 = r6
            r12 = r9
            r6 = 0
            r9 = r19
        L_0x0036:
            if (r6 >= r9) goto L_0x0081
            byte r13 = r8[r6]
            r13 = r13 & 255(0xff, float:3.57E-43)
        L_0x003c:
            r14 = 2
            if (r12 >= r13) goto L_0x0055
        L_0x003f:
            if (r7 < r10) goto L_0x004b
            int r15 = r11 >> 24
            r2.write(r15)
            int r11 = r11 << 8
            int r7 = r7 + -8
            goto L_0x003f
        L_0x004b:
            int r15 = 32 - r7
            int r15 = r15 - r14
            int r14 = r14 << r15
            r11 = r11 | r14
            int r7 = r7 + 2
            int r12 = r12 + 1
            goto L_0x003c
        L_0x0055:
            if (r12 <= r13) goto L_0x0070
        L_0x0057:
            if (r7 < r10) goto L_0x0063
            int r15 = r11 >> 24
            r2.write(r15)
            int r11 = r11 << 8
            int r7 = r7 + -8
            goto L_0x0057
        L_0x0063:
            r15 = 3
            int r16 = 32 - r7
            int r16 = r16 + -2
            int r15 = r15 << r16
            r11 = r11 | r15
            int r7 = r7 + 2
            int r12 = r12 + -1
            goto L_0x0055
        L_0x0070:
            if (r7 < r10) goto L_0x007c
            int r13 = r11 >> 24
            r2.write(r13)
            int r11 = r11 << 8
            int r7 = r7 + -8
            goto L_0x0070
        L_0x007c:
            int r7 = r7 + 1
            int r6 = r6 + 1
            goto L_0x0036
        L_0x0081:
            int r4 = r4 + 1
            r6 = r11
            goto L_0x0012
        L_0x0085:
            r0.q = r6
            r0.r = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.g(int, int):void");
    }

    private void k() throws IOException {
        Data data = this.A;
        byte[][] bArr = data.g;
        int[][] iArr = data.k;
        OutputStream outputStream = this.C;
        byte[] bArr2 = data.d;
        char[] cArr = data.s;
        int i = this.u;
        int i2 = this.r;
        int i3 = this.q;
        int i4 = 0;
        int i5 = i2;
        int i6 = 0;
        while (i4 < i) {
            int min = Math.min((i4 + 50) - 1, i - 1);
            byte b2 = bArr2[i6] & 255;
            int[] iArr2 = iArr[b2];
            byte[] bArr3 = bArr[b2];
            while (i4 <= min) {
                char c = cArr[i4];
                while (i5 >= 8) {
                    outputStream.write(i3 >> 24);
                    i3 <<= 8;
                    i5 -= 8;
                }
                byte b3 = bArr3[c] & 255;
                i3 |= iArr2[c] << ((32 - i5) - b3);
                i5 += b3;
                i4++;
            }
            i4 = min + 1;
            i6++;
        }
        this.q = i3;
        this.r = i5;
    }

    private void l() throws IOException {
        a(24, this.A.t);
        n();
        i();
    }

    private void m() {
        this.B.a(this.A, this.o);
    }

    private void n() {
        int i;
        int i2 = this.o;
        Data data = this.A;
        boolean[] zArr = data.f3314a;
        byte[] bArr = data.q;
        int[] iArr = data.r;
        char[] cArr = data.s;
        int[] iArr2 = data.c;
        byte[] bArr2 = data.b;
        byte[] bArr3 = data.f;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            if (zArr[i4]) {
                bArr2[i4] = (byte) i3;
                i3++;
            }
        }
        this.t = i3;
        int i5 = i3 + 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            iArr2[i6] = 0;
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            bArr3[i3] = (byte) i3;
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 <= i2; i9++) {
            byte b2 = bArr2[bArr[iArr[i9]] & 255];
            byte b3 = bArr3[0];
            int i10 = 0;
            while (b2 != b3) {
                i10++;
                byte b4 = bArr3[i10];
                bArr3[i10] = b3;
                b3 = b4;
            }
            bArr3[0] = b3;
            if (i10 == 0) {
                i7++;
            } else {
                if (i7 > 0) {
                    int i11 = i7 - 1;
                    while (true) {
                        if ((i11 & 1) == 0) {
                            cArr[i8] = 0;
                            i8++;
                            iArr2[0] = iArr2[0] + 1;
                        } else {
                            cArr[i8] = 1;
                            i8++;
                            iArr2[1] = iArr2[1] + 1;
                        }
                        if (i11 < 2) {
                            break;
                        }
                        i11 = (i11 - 2) >> 1;
                    }
                    i7 = 0;
                }
                int i12 = i10 + 1;
                cArr[i8] = (char) i12;
                i8++;
                iArr2[i12] = iArr2[i12] + 1;
            }
        }
        if (i7 > 0) {
            int i13 = i7 - 1;
            while (true) {
                if ((i13 & 1) == 0) {
                    cArr[i8] = 0;
                    i = i8 + 1;
                    iArr2[0] = iArr2[0] + 1;
                } else {
                    cArr[i8] = 1;
                    i = i8 + 1;
                    iArr2[1] = iArr2[1] + 1;
                }
                if (i13 < 2) {
                    break;
                }
                i13 = (i13 - 2) >> 1;
            }
        }
        cArr[i8] = (char) i5;
        iArr2[i5] = iArr2[i5] + 1;
        this.u = i8 + 1;
    }

    static final class Data {

        /* renamed from: a  reason: collision with root package name */
        final boolean[] f3314a = new boolean[256];
        final byte[] b = new byte[256];
        final int[] c = new int[258];
        final byte[] d = new byte[BZip2Constants.k];
        final byte[] e = new byte[BZip2Constants.k];
        final byte[] f = new byte[256];
        final byte[][] g = ((byte[][]) Array.newInstance(byte.class, new int[]{6, 258}));
        final int[][] h = ((int[][]) Array.newInstance(int.class, new int[]{6, 258}));
        final int[] i = new int[6];
        final short[] j = new short[6];
        final int[][] k = ((int[][]) Array.newInstance(int.class, new int[]{6, 258}));
        final byte[] l = new byte[6];
        final boolean[] m = new boolean[16];
        final int[] n = new int[260];
        final int[] o = new int[516];
        final int[] p = new int[516];
        final byte[] q;
        final int[] r;
        final char[] s;
        int t;

        Data(int i2) {
            int i3 = i2 * 100000;
            this.q = new byte[(i3 + 1 + 20)];
            this.r = new int[i3];
            this.s = new char[(i3 * 2)];
        }
    }
}
