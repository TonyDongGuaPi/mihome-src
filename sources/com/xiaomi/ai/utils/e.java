package com.xiaomi.ai.utils;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9951a = "MemoryUtils";

    public static byte[] a(short[] sArr, int i) {
        byte[] bArr = new byte[(i * 2)];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            bArr[i3 + 1] = (byte) (sArr[i2] >> 8);
            bArr[i3] = (byte) (sArr[i2] >> 0);
        }
        return bArr;
    }

    public static short[] a(byte[] bArr, int i) {
        if (i % 2 != 0) {
            Log.a(f9951a, "toShortArray error ,len % 2 != 0 !!!");
            return null;
        }
        int i2 = i / 2;
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            sArr[i3] = (short) (((short) bArr[i4]) + (((short) bArr[i4 + 1]) << 8));
        }
        return sArr;
    }
}
