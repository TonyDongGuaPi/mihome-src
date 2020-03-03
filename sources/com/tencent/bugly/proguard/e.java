package com.tencent.bugly.proguard;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f9041a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2 + 1] = f9041a[b & 15];
            cArr[i2] = f9041a[((byte) (b >>> 4)) & 15];
        }
        return new String(cArr);
    }
}
