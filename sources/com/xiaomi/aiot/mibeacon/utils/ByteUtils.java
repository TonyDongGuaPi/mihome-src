package com.xiaomi.aiot.mibeacon.utils;

public class ByteUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f9987a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            cArr[i2] = f9987a[b >>> 4];
            cArr[i2 + 1] = f9987a[b & 15];
        }
        return new String(cArr);
    }
}
