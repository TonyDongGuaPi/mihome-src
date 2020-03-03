package com.xiaomi.zxing.qrcode.decoder;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitMatrix;

final class BitMatrixParser {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1757a;
    private Version b;
    private FormatInformation c;
    private boolean d;

    BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int g = bitMatrix.g();
        if (g < 21 || (g & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.f1757a = bitMatrix;
    }

    /* access modifiers changed from: package-private */
    public FormatInformation a() throws FormatException {
        if (this.c != null) {
            return this.c;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 = a(i3, 8, i2);
        }
        int a2 = a(8, 7, a(8, 8, a(7, 8, i2)));
        for (int i4 = 5; i4 >= 0; i4--) {
            a2 = a(8, i4, a2);
        }
        int g = this.f1757a.g();
        int i5 = g - 7;
        for (int i6 = g - 1; i6 >= i5; i6--) {
            i = a(8, i6, i);
        }
        for (int i7 = g - 8; i7 < g; i7++) {
            i = a(i7, 8, i);
        }
        this.c = FormatInformation.b(a2, i);
        if (this.c != null) {
            return this.c;
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: package-private */
    public Version b() throws FormatException {
        if (this.b != null) {
            return this.b;
        }
        int g = this.f1757a.g();
        int i = (g - 17) / 4;
        if (i <= 6) {
            return Version.b(i);
        }
        int i2 = g - 11;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = g - 9; i6 >= i2; i6--) {
                i4 = a(i6, i5, i4);
            }
        }
        Version c2 = Version.c(i4);
        if (c2 == null || c2.d() != g) {
            for (int i7 = 5; i7 >= 0; i7--) {
                for (int i8 = g - 9; i8 >= i2; i8--) {
                    i3 = a(i7, i8, i3);
                }
            }
            Version c3 = Version.c(i3);
            if (c3 == null || c3.d() != g) {
                throw FormatException.getFormatInstance();
            }
            this.b = c3;
            return c3;
        }
        this.b = c2;
        return c2;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.f1757a.a(i2, i) : this.f1757a.a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    /* access modifiers changed from: package-private */
    public byte[] c() throws FormatException {
        FormatInformation a2 = a();
        Version b2 = b();
        DataMask dataMask = DataMask.values()[a2.b()];
        int g = this.f1757a.g();
        dataMask.unmaskBitMatrix(this.f1757a, g);
        BitMatrix e = b2.e();
        byte[] bArr = new byte[b2.c()];
        int i = g - 1;
        int i2 = i;
        int i3 = 0;
        boolean z = true;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            int i6 = i5;
            int i7 = i4;
            int i8 = i3;
            int i9 = 0;
            while (i9 < g) {
                int i10 = z ? i - i9 : i9;
                int i11 = i7;
                int i12 = i8;
                for (int i13 = 0; i13 < 2; i13++) {
                    int i14 = i2 - i13;
                    if (!e.a(i14, i10)) {
                        i6++;
                        int i15 = i11 << 1;
                        int i16 = this.f1757a.a(i14, i10) ? i15 | 1 : i15;
                        if (i6 == 8) {
                            bArr[i12] = (byte) i16;
                            i12++;
                            i6 = 0;
                            i11 = 0;
                        } else {
                            i11 = i16;
                        }
                    }
                }
                i9++;
                i8 = i12;
                i7 = i11;
            }
            z = !z;
            i2 -= 2;
            i3 = i8;
            i4 = i7;
            i5 = i6;
        }
        if (i3 == b2.c()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (this.c != null) {
            DataMask.values()[this.c.b()].unmaskBitMatrix(this.f1757a, this.f1757a.g());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        int i = 0;
        while (i < this.f1757a.f()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f1757a.g(); i3++) {
                if (this.f1757a.a(i, i3) != this.f1757a.a(i3, i)) {
                    this.f1757a.d(i3, i);
                    this.f1757a.d(i, i3);
                }
            }
            i = i2;
        }
    }
}
