package com.xiaomi.jr.idcardverifier.utils;

public class RotatorUtil {
    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        if (i3 == 0) {
            return bArr;
        }
        if (i3 == 90) {
            return a(bArr, i, i2);
        }
        if (i3 != 180) {
            return i3 != 270 ? bArr : c(bArr, i, i2);
        }
        return b(bArr, i, i2);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                bArr2[i5] = bArr[(i7 * i) + i6];
                i5++;
            }
        }
        int i8 = i4 - 1;
        int i9 = i - 1;
        while (i9 > 0) {
            int i10 = i8;
            for (int i11 = 0; i11 < i2 / 2; i11++) {
                int i12 = (i11 * i) + i3;
                bArr2[i10] = bArr[i12 + i9];
                int i13 = i10 - 1;
                bArr2[i13] = bArr[i12 + (i9 - 1)];
                i10 = i13 - 1;
            }
            i9 -= 2;
            i8 = i10;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            bArr2[i5] = bArr[i6];
            i5++;
        }
        for (int i7 = i4 - 1; i7 >= i3; i7 -= 2) {
            int i8 = i5 + 1;
            bArr2[i5] = bArr[i7 - 1];
            i5 = i8 + 1;
            bArr2[i8] = bArr[i7];
        }
        return bArr2;
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[((i3 * 3) / 2)];
        int i4 = i - 1;
        int i5 = i4;
        int i6 = 0;
        while (i5 >= 0) {
            int i7 = i6;
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                bArr2[i7] = bArr[i8 + i5];
                i7++;
                i8 += i;
            }
            i5--;
            i6 = i7;
        }
        int i10 = i3;
        while (i4 > 0) {
            int i11 = i3;
            int i12 = i10;
            for (int i13 = 0; i13 < i2 / 2; i13++) {
                bArr2[i12] = bArr[(i4 - 1) + i11];
                int i14 = i12 + 1;
                bArr2[i14] = bArr[i11 + i4];
                i12 = i14 + 1;
                i11 += i;
            }
            i4 -= 2;
            i10 = i12;
        }
        return bArr2;
    }
}
