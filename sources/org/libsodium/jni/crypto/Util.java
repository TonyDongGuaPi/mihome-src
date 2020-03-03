package org.libsodium.jni.crypto;

import java.util.Arrays;

public class Util {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3723a = 32;

    public static byte[] a(int i, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length + i)];
        Arrays.fill(bArr2, (byte) 0);
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        return bArr2;
    }

    public static byte[] b(int i, byte[] bArr) {
        return Arrays.copyOfRange(bArr, i, bArr.length);
    }

    public static void a(byte[] bArr, int i) {
        if (bArr == null || bArr.length != i) {
            throw new RuntimeException("Invalid size: " + bArr.length);
        }
    }

    public static byte[] a(int i) {
        return new byte[i];
    }

    public static boolean a(int i, String str) {
        if (i == 0) {
            return true;
        }
        throw new RuntimeException(str);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        return Arrays.copyOfRange(bArr, i, i2);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
