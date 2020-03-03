package com.xiaomi.zxing.qrcode.decoder;

import com.xiaomi.zxing.qrcode.decoder.Version;

final class DataBlock {

    /* renamed from: a  reason: collision with root package name */
    private final int f1758a;
    private final byte[] b;

    private DataBlock(int i, byte[] bArr) {
        this.f1758a = i;
        this.b = bArr;
    }

    static DataBlock[] a(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length == version.c()) {
            Version.ECBlocks a2 = version.a(errorCorrectionLevel);
            Version.ECB[] d = a2.d();
            int i = 0;
            for (Version.ECB a3 : d) {
                i += a3.a();
            }
            DataBlock[] dataBlockArr = new DataBlock[i];
            int length = d.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                Version.ECB ecb = d[i2];
                int i4 = i3;
                int i5 = 0;
                while (i5 < ecb.a()) {
                    int b2 = ecb.b();
                    dataBlockArr[i4] = new DataBlock(b2, new byte[(a2.a() + b2)]);
                    i5++;
                    i4++;
                }
                i2++;
                i3 = i4;
            }
            int length2 = dataBlockArr[0].b.length;
            int length3 = dataBlockArr.length - 1;
            while (length3 >= 0 && dataBlockArr[length3].b.length != length2) {
                length3--;
            }
            int i6 = length3 + 1;
            int a4 = length2 - a2.a();
            int i7 = 0;
            int i8 = 0;
            while (i7 < a4) {
                int i9 = i8;
                int i10 = 0;
                while (i10 < i3) {
                    dataBlockArr[i10].b[i7] = bArr[i9];
                    i10++;
                    i9++;
                }
                i7++;
                i8 = i9;
            }
            int i11 = i6;
            while (i11 < i3) {
                dataBlockArr[i11].b[a4] = bArr[i8];
                i11++;
                i8++;
            }
            int length4 = dataBlockArr[0].b.length;
            while (a4 < length4) {
                int i12 = i8;
                int i13 = 0;
                while (i13 < i3) {
                    dataBlockArr[i13].b[i13 < i6 ? a4 : a4 + 1] = bArr[i12];
                    i13++;
                    i12++;
                }
                a4++;
                i8 = i12;
            }
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f1758a;
    }

    /* access modifiers changed from: package-private */
    public byte[] b() {
        return this.b;
    }
}
