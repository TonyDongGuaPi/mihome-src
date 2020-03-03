package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util;

import cn.com.fmsh.communication.core.MessageHead;

public abstract class Pack {
    public static short a(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public static int b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    public static void a(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = b(bArr, i);
            i += 4;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        a(i, bArr, 0);
        return bArr;
    }

    public static void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public static byte[] a(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        a(iArr, bArr, 0);
        return bArr;
    }

    public static void a(int[] iArr, byte[] bArr, int i) {
        for (int a2 : iArr) {
            a(a2, bArr, i);
            i += 4;
        }
    }

    public static long c(byte[] bArr, int i) {
        int b = b(bArr, i);
        return (((long) b(bArr, i + 4)) & MessageHead.SERIAL_MAK) | ((((long) b) & MessageHead.SERIAL_MAK) << 32);
    }

    public static void a(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = c(bArr, i);
            i += 8;
        }
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        a(j, bArr, 0);
        return bArr;
    }

    public static void a(long j, byte[] bArr, int i) {
        a((int) (j >>> 32), bArr, i);
        a((int) (j & MessageHead.SERIAL_MAK), bArr, i + 4);
    }

    public static byte[] a(long[] jArr) {
        byte[] bArr = new byte[(jArr.length * 8)];
        a(jArr, bArr, 0);
        return bArr;
    }

    public static void a(long[] jArr, byte[] bArr, int i) {
        for (long a2 : jArr) {
            a(a2, bArr, i);
            i += 8;
        }
    }

    public static short d(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & 255) << 8) | (bArr[i] & 255));
    }

    public static int e(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    public static void b(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = e(bArr, i);
            i += 4;
        }
    }

    public static void a(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = e(bArr, i);
            i += 4;
        }
    }

    public static int[] a(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr[i3] = e(bArr, i);
            i += 4;
        }
        return iArr;
    }

    public static byte[] a(short s) {
        byte[] bArr = new byte[2];
        a(s, bArr, 0);
        return bArr;
    }

    public static void a(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
        bArr[i + 1] = (byte) (s >>> 8);
    }

    public static byte[] b(int i) {
        byte[] bArr = new byte[4];
        b(i, bArr, 0);
        return bArr;
    }

    public static void b(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    public static byte[] b(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        b(iArr, bArr, 0);
        return bArr;
    }

    public static void b(int[] iArr, byte[] bArr, int i) {
        for (int b : iArr) {
            b(b, bArr, i);
            i += 4;
        }
    }

    public static long f(byte[] bArr, int i) {
        return ((((long) e(bArr, i + 4)) & MessageHead.SERIAL_MAK) << 32) | (MessageHead.SERIAL_MAK & ((long) e(bArr, i)));
    }

    public static void b(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = f(bArr, i);
            i += 8;
        }
    }

    public static byte[] b(long j) {
        byte[] bArr = new byte[8];
        b(j, bArr, 0);
        return bArr;
    }

    public static void b(long j, byte[] bArr, int i) {
        b((int) (MessageHead.SERIAL_MAK & j), bArr, i);
        b((int) (j >>> 32), bArr, i + 4);
    }

    public static byte[] b(long[] jArr) {
        byte[] bArr = new byte[(jArr.length * 8)];
        b(jArr, bArr, 0);
        return bArr;
    }

    public static void b(long[] jArr, byte[] bArr, int i) {
        for (long b : jArr) {
            b(b, bArr, i);
            i += 8;
        }
    }
}
