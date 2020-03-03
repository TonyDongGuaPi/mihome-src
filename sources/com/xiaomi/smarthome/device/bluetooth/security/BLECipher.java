package com.xiaomi.smarthome.device.bluetooth.security;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public class BLECipher {
    private static byte b(int i) {
        return (byte) (i & 255);
    }

    private static native int nativeEncrypt(byte[] bArr, byte[] bArr2, byte[] bArr3);

    private static native int nativeMixA(byte[] bArr, byte[] bArr2, byte[] bArr3);

    private static native int nativeMixB(byte[] bArr, byte[] bArr2, byte[] bArr3);

    static {
        System.loadLibrary("blecipher");
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (ByteUtils.e(bArr) || ByteUtils.e(bArr2)) {
            return ByteUtils.b;
        }
        byte[] bArr3 = new byte[bArr2.length];
        if (nativeEncrypt(bArr, bArr2, bArr3) != 0) {
            return ByteUtils.b;
        }
        return bArr3;
    }

    public static byte[] a(String str, int i) {
        if (TextUtils.isEmpty(str) || i < 0) {
            return ByteUtils.b;
        }
        byte[] bArr = new byte[8];
        if (nativeMixA(a(str), a(i), bArr) != 0) {
            return ByteUtils.b;
        }
        return bArr;
    }

    public static byte[] b(String str, int i) {
        if (TextUtils.isEmpty(str) || i < 0) {
            return ByteUtils.b;
        }
        byte[] bArr = new byte[8];
        if (nativeMixB(a(str), a(i), bArr) != 0) {
            return ByteUtils.b;
        }
        return bArr;
    }

    private static byte[] a(String str) {
        String[] split = str.split(":");
        int length = split.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[(length - i) - 1] = b(Integer.parseInt(split[i], 16));
        }
        return bArr;
    }

    private static byte[] a(int i) {
        return new byte[]{b(i), b(i >>> 8)};
    }
}
