package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;

public final class ZipEightByteInteger implements Serializable {
    public static final ZipEightByteInteger ZERO = new ZipEightByteInteger(0);

    /* renamed from: a  reason: collision with root package name */
    private static final int f3289a = 1;
    private static final int b = 65280;
    private static final int c = 8;
    private static final int d = 2;
    private static final int e = 16711680;
    private static final int f = 16;
    private static final int g = 3;
    private static final long h = 4278190080L;
    private static final int i = 24;
    private static final int j = 4;
    private static final long k = 1095216660480L;
    private static final int l = 32;
    private static final int m = 5;
    private static final long n = 280375465082880L;
    private static final int o = 40;
    private static final int p = 6;
    private static final long q = 71776119061217280L;
    private static final int r = 48;
    private static final int s = 7;
    private static final long serialVersionUID = 1;
    private static final long t = 9151314442816847872L;
    private static final int u = 56;
    private static final int v = 63;
    private static final byte w = Byte.MIN_VALUE;
    private final BigInteger value;

    public ZipEightByteInteger(long j2) {
        this(BigInteger.valueOf(j2));
    }

    public ZipEightByteInteger(BigInteger bigInteger) {
        this.value = bigInteger;
    }

    public ZipEightByteInteger(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipEightByteInteger(byte[] bArr, int i2) {
        this.value = getValue(bArr, i2);
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public long getLongValue() {
        return this.value.longValue();
    }

    public BigInteger getValue() {
        return this.value;
    }

    public static byte[] getBytes(long j2) {
        return getBytes(BigInteger.valueOf(j2));
    }

    public static byte[] getBytes(BigInteger bigInteger) {
        long longValue = bigInteger.longValue();
        byte[] bArr = {(byte) ((int) (255 & longValue)), (byte) ((int) ((65280 & longValue) >> 8)), (byte) ((int) ((16711680 & longValue) >> 16)), (byte) ((int) ((h & longValue) >> 24)), (byte) ((int) ((k & longValue) >> 32)), (byte) ((int) ((n & longValue) >> 40)), (byte) ((int) ((q & longValue) >> 48)), (byte) ((int) ((longValue & t) >> 56))};
        if (bigInteger.testBit(63)) {
            bArr[7] = (byte) (bArr[7] | Byte.MIN_VALUE);
        }
        return bArr;
    }

    public static long getLongValue(byte[] bArr, int i2) {
        return getValue(bArr, i2).longValue();
    }

    public static BigInteger getValue(byte[] bArr, int i2) {
        int i3 = i2 + 7;
        BigInteger valueOf = BigInteger.valueOf(((((long) bArr[i3]) << 56) & t) + ((((long) bArr[i2 + 6]) << 48) & q) + ((((long) bArr[i2 + 5]) << 40) & n) + ((((long) bArr[i2 + 4]) << 32) & k) + ((((long) bArr[i2 + 3]) << 24) & h) + ((((long) bArr[i2 + 2]) << 16) & 16711680) + ((((long) bArr[i2 + 1]) << 8) & 65280) + (((long) bArr[i2]) & 255));
        return (bArr[i3] & Byte.MIN_VALUE) == Byte.MIN_VALUE ? valueOf.setBit(63) : valueOf;
    }

    public static long getLongValue(byte[] bArr) {
        return getLongValue(bArr, 0);
    }

    public static BigInteger getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ZipEightByteInteger)) {
            return false;
        }
        return this.value.equals(((ZipEightByteInteger) obj).getValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "ZipEightByteInteger value: " + this.value;
    }
}
