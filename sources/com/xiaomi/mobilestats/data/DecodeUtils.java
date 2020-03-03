package com.xiaomi.mobilestats.data;

public class DecodeUtils {
    public static String getDenCode(byte[] bArr) {
        int[] iArr = new int[(bArr.length % 4 == 0 ? bArr.length / 4 : (bArr.length / 4) + 1)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            byte b = i >= bArr.length ? 0 : bArr[i];
            int i3 = i + 1;
            byte b2 = 32;
            byte b3 = i3 >= bArr.length ? 32 : bArr[i3];
            int i4 = i + 2;
            byte b4 = i4 >= bArr.length ? 32 : bArr[i4];
            int i5 = i + 3;
            if (i5 < bArr.length) {
                b2 = bArr[i5];
            }
            int i6 = BitConverter.toInt(new byte[]{b, b3, b4, b2});
            if (i2 % 2 == 0) {
                iArr[i2] = i6 ^ 188366965;
            } else {
                iArr[i2] = i6 ^ -1;
            }
            i2++;
            i += 4;
        }
        byte[] bArr2 = new byte[(iArr.length * 4)];
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr.length) {
            byte[] bytes = BitConverter.getBytes(iArr[i7]);
            int i9 = i8;
            for (byte b5 : bytes) {
                bArr2[i9] = b5;
                i9++;
            }
            i7++;
            i8 = i9;
        }
        return new String(bArr2);
    }

    public static byte[] getEncode(String str) {
        byte[] bytes = str.getBytes();
        int[] iArr = new int[(bytes.length % 4 == 0 ? bytes.length / 4 : (bytes.length / 4) + 1)];
        int i = 0;
        int i2 = 0;
        while (i < bytes.length) {
            byte b = i >= bytes.length ? 0 : bytes[i];
            int i3 = i + 1;
            byte b2 = 32;
            byte b3 = i3 >= bytes.length ? 32 : bytes[i3];
            int i4 = i + 2;
            byte b4 = i4 >= bytes.length ? 32 : bytes[i4];
            int i5 = i + 3;
            if (i5 < bytes.length) {
                b2 = bytes[i5];
            }
            int i6 = BitConverter.toInt(new byte[]{b, b3, b4, b2});
            if (i2 % 2 == 0) {
                iArr[i2] = i6 ^ 188366965;
            } else {
                iArr[i2] = i6 ^ -1;
            }
            i2++;
            i += 4;
        }
        byte[] bArr = new byte[(iArr.length * 4)];
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr.length) {
            byte[] bytes2 = BitConverter.getBytes(iArr[i7]);
            int i9 = i8;
            for (byte b5 : bytes2) {
                bArr[i9] = b5;
                i9++;
            }
            i7++;
            i8 = i9;
        }
        return bArr;
    }

    public static byte[] getEncode(byte[] bArr) {
        int[] iArr = new int[(bArr.length % 4 == 0 ? bArr.length / 4 : (bArr.length / 4) + 1)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            byte b = i >= bArr.length ? 0 : bArr[i];
            int i3 = i + 1;
            byte b2 = 32;
            byte b3 = i3 >= bArr.length ? 32 : bArr[i3];
            int i4 = i + 2;
            byte b4 = i4 >= bArr.length ? 32 : bArr[i4];
            int i5 = i + 3;
            if (i5 < bArr.length) {
                b2 = bArr[i5];
            }
            int i6 = BitConverter.toInt(new byte[]{b, b3, b4, b2});
            if (i2 % 2 == 0) {
                iArr[i2] = i6 ^ 188366965;
            } else {
                iArr[i2] = i6 ^ -1;
            }
            i2++;
            i += 4;
        }
        byte[] bArr2 = new byte[(iArr.length * 4)];
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr.length) {
            byte[] bytes = BitConverter.getBytes(iArr[i7]);
            int i9 = i8;
            for (byte b5 : bytes) {
                bArr2[i9] = b5;
                i9++;
            }
            i7++;
            i8 = i9;
        }
        return bArr2;
    }
}
