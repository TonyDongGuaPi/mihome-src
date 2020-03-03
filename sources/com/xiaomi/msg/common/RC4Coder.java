package com.xiaomi.msg.common;

public class RC4Coder {
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] a2 = a(bArr2);
        byte[] bArr3 = new byte[bArr.length];
        int i = 0;
        byte b = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i = (i + 1) & 255;
            b = ((a2[i] & 255) + b) & 255;
            byte b2 = a2[i];
            a2[i] = a2[b];
            a2[b] = b2;
            bArr3[i2] = (byte) (a2[((a2[i] & 255) + (a2[b] & 255)) & 255] ^ bArr[i2]);
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = (byte) i;
        }
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            i3 = ((bArr[i2] & 255) + (bArr2[i4] & 255) + i3) & 255;
            byte b = bArr2[i4];
            bArr2[i4] = bArr2[i3];
            bArr2[i3] = b;
            i2 = (i2 + 1) % bArr.length;
        }
        return bArr2;
    }
}
