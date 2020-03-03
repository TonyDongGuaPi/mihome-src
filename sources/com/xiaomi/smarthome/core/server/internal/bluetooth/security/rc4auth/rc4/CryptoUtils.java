package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.rc4;

import com.taobao.weex.el.parse.Operators;

public class CryptoUtils {
    public static void b(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr[i3] = 0;
        }
    }

    public static void b(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    public static void c(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr[i3] = (byte) ((int) (Math.random() * 256.0d));
        }
    }

    public static void c(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public static void a(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            bArr3[i3 + i5] = (byte) (bArr[i + i5] ^ bArr2[i2 + i5]);
        }
    }

    public static void a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        a(bArr, 0, bArr2, 0, bArr3, 0, bArr.length);
    }

    public static void a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i2 + i4] = bArr[i + i4];
        }
    }

    public static void a(byte[] bArr, byte[] bArr2) {
        a(bArr, 0, bArr2, 0, bArr.length);
    }

    public static boolean b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(byte[] bArr, byte[] bArr2) {
        return b(bArr, 0, bArr2, 0, bArr.length);
    }

    public static void a(byte[] bArr, int i, byte b, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr[i3] = b;
        }
    }

    public static void a(byte[] bArr, byte b) {
        a(bArr, 0, b, bArr.length);
    }

    public static void a(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i4 * 4) + i;
            iArr[i2 + i4] = (bArr[i5 + 3] & 255) | ((bArr[i5] & 255) << 24) | ((bArr[i5 + 1] & 255) << 16) | ((bArr[i5 + 2] & 255) << 8);
        }
    }

    public static void a(int[] iArr, int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i4 * 4) + i2;
            int i6 = i + i4;
            bArr[i5] = (byte) (iArr[i6] >>> 24);
            bArr[i5 + 1] = (byte) (iArr[i6] >>> 16);
            bArr[i5 + 2] = (byte) (iArr[i6] >>> 8);
            bArr[i5 + 3] = (byte) iArr[i6];
        }
    }

    public static void b(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i4 * 2) + i;
            iArr[i2 + i4] = (bArr[i5 + 1] & 255) | ((bArr[i5] & 255) << 8);
        }
    }

    public static void b(int[] iArr, int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i4 * 2) + i2;
            int i6 = i + i4;
            bArr[i5] = (byte) (iArr[i6] >>> 8);
            bArr[i5 + 1] = (byte) iArr[i6];
        }
    }

    public static String d(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < i + i2; i3++) {
            stringBuffer.append("0123456789abcdef".charAt((bArr[i3] >>> 4) & 15));
            stringBuffer.append("0123456789abcdef".charAt(bArr[i3] & 15));
        }
        return Operators.ARRAY_START_STR + stringBuffer + Operators.ARRAY_END_STR;
    }

    public static String d(byte[] bArr) {
        return d(bArr, 0, bArr.length);
    }
}
