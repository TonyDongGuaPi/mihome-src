package com.jstun.core.util;

public class Utility {
    public static final int a(byte b) throws UtilityException {
        return b & 255;
    }

    public static final byte a(int i) throws UtilityException {
        if (((double) i) <= Math.pow(2.0d, 15.0d) && i >= 0) {
            return (byte) (i & 255);
        }
        throw new UtilityException("Integer value " + i + " is larger than 2^15");
    }

    public static final byte[] b(int i) throws UtilityException {
        byte[] bArr = new byte[2];
        if (((double) i) > Math.pow(2.0d, 31.0d) || i < 0) {
            throw new UtilityException("Integer value " + i + " is larger than 2^31");
        }
        bArr[0] = (byte) ((i >>> 8) & 255);
        bArr[1] = (byte) (i & 255);
        return bArr;
    }

    public static final byte[] c(int i) throws UtilityException {
        byte[] bArr = new byte[4];
        if (((double) i) > Math.pow(2.0d, 63.0d) || i < 0) {
            throw new UtilityException("Integer value " + i + " is larger than 2^63");
        }
        bArr[0] = (byte) ((i >>> 24) & 255);
        bArr[1] = (byte) ((i >>> 16) & 255);
        bArr[2] = (byte) ((i >>> 8) & 255);
        bArr[3] = (byte) (i & 255);
        return bArr;
    }

    public static final int a(byte[] bArr) throws UtilityException {
        if (bArr.length >= 2) {
            return ((bArr[0] & 255) << 8) + (bArr[1] & 255);
        }
        throw new UtilityException("Byte array too short!");
    }

    public static final long b(byte[] bArr) throws UtilityException {
        if (bArr.length >= 4) {
            return (((long) (bArr[0] & 255)) << 24) + ((long) ((bArr[1] & 255) << 16)) + ((long) ((bArr[2] & 255) << 8)) + ((long) (bArr[3] & 255));
        }
        throw new UtilityException("Byte array too short!");
    }
}
