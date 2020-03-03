package com.xiaomi.mobilestats.data;

public class BitConverter {
    public static final int FLAG_JAVA = 0;
    public static final int FLAG_REVERSE = -1;

    public static byte[] getBytes(int i) {
        return getBytes(i, 0);
    }

    public static byte[] getBytes(int i, int i2) {
        byte[] bArr = new byte[4];
        switch (i2) {
            case -1:
                bArr[3] = (byte) ((i >> 24) & 255);
                bArr[2] = (byte) ((i >> 16) & 255);
                bArr[1] = (byte) ((i >> 8) & 255);
                bArr[0] = (byte) (i & 255);
                break;
            case 0:
                bArr[0] = (byte) ((i >> 24) & 255);
                bArr[1] = (byte) ((i >> 16) & 255);
                bArr[2] = (byte) ((i >> 8) & 255);
                bArr[3] = (byte) (i & 255);
                break;
        }
        return bArr;
    }

    public static byte[] getBytes(long j) {
        return getBytes(j, 0);
    }

    public static byte[] getBytes(long j, int i) {
        byte[] bArr = new byte[8];
        switch (i) {
            case -1:
                bArr[7] = (byte) ((int) ((j >> 56) & 255));
                bArr[6] = (byte) ((int) ((j >> 48) & 255));
                bArr[5] = (byte) ((int) ((j >> 40) & 255));
                bArr[4] = (byte) ((int) ((j >> 32) & 255));
                bArr[3] = (byte) ((int) ((j >> 24) & 255));
                bArr[2] = (byte) ((int) ((j >> 16) & 255));
                bArr[1] = (byte) ((int) ((j >> 8) & 255));
                bArr[0] = (byte) ((int) ((j >> 0) & 255));
                break;
            case 0:
                bArr[0] = (byte) ((int) ((j >> 56) & 255));
                bArr[1] = (byte) ((int) ((j >> 48) & 255));
                bArr[2] = (byte) ((int) ((j >> 40) & 255));
                bArr[3] = (byte) ((int) ((j >> 32) & 255));
                bArr[4] = (byte) ((int) ((j >> 24) & 255));
                bArr[5] = (byte) ((int) ((j >> 16) & 255));
                bArr[6] = (byte) ((int) ((j >> 8) & 255));
                bArr[7] = (byte) ((int) ((j >> 0) & 255));
                break;
        }
        return bArr;
    }

    public static byte[] getBytes(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length() * 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i += 2) {
            short charAt = (short) str.charAt(i / 2);
            bArr[i] = (byte) (charAt & 255);
            bArr[i + 1] = (byte) ((charAt >> 8) & 255);
        }
        return bArr;
    }

    public static byte[] getBytes(short s) {
        return getBytes(s, 0);
    }

    public static byte[] getBytes(short s, int i) {
        byte[] bArr = new byte[2];
        switch (i) {
            case -1:
                bArr[1] = (byte) ((s >> 8) & 255);
                bArr[0] = (byte) (s & 255);
                break;
            case 0:
                bArr[0] = (byte) ((s >> 8) & 255);
                bArr[1] = (byte) (s & 255);
                break;
        }
        return bArr;
    }

    public static String getString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0 || bArr.length % 2 != 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(length / 2);
        for (int i = 0; i < length; i += 2) {
            stringBuffer.append((char) ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)));
        }
        return stringBuffer.toString();
    }

    public static int toInt(byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static int toInt(byte[] bArr, int i) {
        byte b;
        byte b2;
        switch (i) {
            case -1:
                b = ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8);
                b2 = bArr[0];
                break;
            case 0:
                b = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
                b2 = bArr[3];
                break;
            default:
                throw new IllegalArgumentException("BitConverter:toInt");
        }
        return (b2 & 255) | b;
    }

    public static long toLong(byte[] bArr) {
        return toLong(bArr, 0);
    }

    public static long toLong(byte[] bArr, int i) {
        long j;
        byte b;
        switch (i) {
            case -1:
                j = (((long) (bArr[6] & 255)) << 48) | (((long) (bArr[7] & 255)) << 56) | (((long) (bArr[5] & 255)) << 40) | (((long) (bArr[4] & 255)) << 32) | (((long) (bArr[3] & 255)) << 24) | (((long) (bArr[2] & 255)) << 16) | (((long) (bArr[1] & 255)) << 8);
                b = bArr[0];
                break;
            case 0:
                j = (((long) (bArr[1] & 255)) << 48) | (((long) (bArr[0] & 255)) << 56) | (((long) (bArr[2] & 255)) << 40) | (((long) (bArr[3] & 255)) << 32) | (((long) (bArr[4] & 255)) << 24) | (((long) (bArr[5] & 255)) << 16) | (((long) (bArr[6] & 255)) << 8);
                b = bArr[7];
                break;
            default:
                throw new IllegalArgumentException("BitConverter:toLong");
        }
        return j | ((long) (b & 255));
    }

    public static short toShort(byte[] bArr) {
        return toShort(bArr, 0);
    }

    public static short toShort(byte[] bArr, int i) {
        int i2;
        byte b;
        switch (i) {
            case -1:
                i2 = (bArr[1] & 255) << 8;
                b = bArr[0];
                break;
            case 0:
                i2 = (bArr[0] & 255) << 8;
                b = bArr[1];
                break;
            default:
                throw new IllegalArgumentException("BitConverter:toShort");
        }
        return (short) ((b & 255) | i2);
    }
}
