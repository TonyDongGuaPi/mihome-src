package com.xiaomi.smarthome.library.common.util;

import java.util.Arrays;

public class ByteUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18649a = 4;
    public static final byte[] b = new byte[0];
    public static final int c = 255;

    public static int a(byte b2) {
        return b2 & 255;
    }

    public static boolean a(byte b2, int i, boolean z) {
        return z ? (b2 & (1 << i)) != 0 : (b2 & (1 << (7 - i))) != 0;
    }

    public static byte[] a(byte[] bArr) {
        return bArr != null ? bArr : b;
    }

    public static String b(byte[] bArr) {
        return new String(bArr);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        if (bArr == null || i < 0 || i >= bArr.length || i2 < 0 || i2 >= bArr.length || i > i2) {
            return null;
        }
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        for (int i3 = i; i3 <= i2; i3++) {
            bArr2[i3 - i] = bArr[i3];
        }
        return bArr2;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        int length;
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || (length = bArr.length) != bArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(byte[] bArr, int i, byte[] bArr2) {
        int length;
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || i < 0 || i >= bArr.length || (length = (bArr2.length + i) - 1) < 0 || length >= bArr.length) {
            return false;
        }
        for (int i2 = i; i2 <= length; i2++) {
            if (bArr[i2] != bArr2[i2 - i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(byte b2, int i) {
        return a(b2, i, true);
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (!e(bArr)) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[length])}));
            }
        }
        return sb.toString();
    }

    public static String d(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (!e(bArr)) {
            for (int i = 0; i < bArr.length; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            }
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[((length + 1) / 2)];
        int i = 0;
        while (i < length) {
            try {
                bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, Math.min(2, length - i) + i), 16);
                i += 2;
            } catch (Exception e) {
                e.printStackTrace();
                return b;
            }
        }
        return bArr;
    }

    public static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean f(byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        for (int i = 0; i < length; i++) {
            if (a(bArr[i]) != 255) {
                return false;
            }
        }
        return true;
    }

    public static byte[] a(byte[] bArr, int i, byte b2) {
        int length = bArr != null ? bArr.length : 0;
        if (length >= i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        int i2 = i - 1;
        int i3 = length - 1;
        while (i2 >= 0) {
            if (i3 >= 0) {
                bArr2[i2] = bArr[i3];
            } else {
                bArr2[i2] = b2;
            }
            i2--;
            i3--;
        }
        return bArr2;
    }

    public static byte[] a(byte[] bArr, byte b2) {
        if (e(bArr)) {
            return bArr;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != b2) {
                return Arrays.copyOfRange(bArr, i, bArr.length);
            }
        }
        return b;
    }

    public static byte[] b(byte[] bArr, byte b2) {
        if (e(bArr)) {
            return bArr;
        }
        for (int length = bArr.length - 1; length >= 0; length--) {
            if (bArr[length] != b2) {
                return Arrays.copyOfRange(bArr, 0, length + 1);
            }
        }
        return b;
    }

    public static boolean g(byte[] bArr) {
        return !e(bArr) && !f(bArr);
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >>> (i2 * 8));
        }
        return bArr;
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) (j >>> (i * 8)));
        }
        return bArr;
    }

    public static byte[] a(short s) {
        return new byte[]{(byte) s, (byte) (s >>> 8)};
    }

    public static int h(byte[] bArr) {
        int i = 0;
        if (bArr.length != 4) {
            return 0;
        }
        int i2 = 0;
        while (i < 4) {
            int i3 = i + 1;
            i2 = (int) (((long) i2) + (((1 << (i3 * 8)) - 1) & ((long) (bArr[i] << (i * 8)))));
            i = i3;
        }
        return i2;
    }

    public static boolean c(byte[] bArr, byte b2) {
        if (bArr == null) {
            return false;
        }
        for (byte b3 : bArr) {
            if (b3 != b2) {
                return false;
            }
        }
        return true;
    }

    public static void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        if (bArr != null && bArr2 != null && i >= 0) {
            while (i2 < bArr2.length && i < bArr.length) {
                bArr[i] = bArr2[i2];
                i++;
                i2++;
            }
        }
    }

    public static boolean b(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, Math.min(bArr.length, bArr2.length));
    }

    public static boolean a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] a(byte[] bArr, int i) {
        return b(bArr, i, bArr.length - i);
    }

    public static byte[] b(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
