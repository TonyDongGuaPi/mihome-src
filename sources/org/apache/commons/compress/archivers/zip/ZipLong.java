package org.apache.commons.compress.archivers.zip;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.Serializable;

public final class ZipLong implements Serializable, Cloneable {
    public static final ZipLong AED_SIG = new ZipLong(134630224);
    public static final ZipLong CFH_SIG = new ZipLong(33639248);
    public static final ZipLong DD_SIG = new ZipLong(134695760);
    public static final ZipLong LFH_SIG = new ZipLong(67324752);
    public static final ZipLong SINGLE_SEGMENT_SPLIT_MARKER = new ZipLong(808471376);
    static final ZipLong ZIP64_MAGIC = new ZipLong((long) MessageHead.SERIAL_MAK);

    /* renamed from: a  reason: collision with root package name */
    private static final int f3299a = 1;
    private static final int b = 65280;
    private static final int c = 8;
    private static final int d = 2;
    private static final int e = 16711680;
    private static final int f = 16;
    private static final int g = 3;
    private static final long h = 4278190080L;
    private static final int i = 24;
    private static final long serialVersionUID = 1;
    private final long value;

    public ZipLong(long j) {
        this.value = j;
    }

    public ZipLong(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipLong(byte[] bArr, int i2) {
        this.value = getValue(bArr, i2);
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public long getValue() {
        return this.value;
    }

    public static byte[] getBytes(long j) {
        byte[] bArr = new byte[4];
        putLong(j, bArr, 0);
        return bArr;
    }

    public static void putLong(long j, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (255 & j));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((65280 & j) >> 8));
        bArr[i4] = (byte) ((int) ((16711680 & j) >> 16));
        bArr[i4 + 1] = (byte) ((int) ((j & h) >> 24));
    }

    public void putLong(byte[] bArr, int i2) {
        putLong(this.value, bArr, i2);
    }

    public static long getValue(byte[] bArr, int i2) {
        return (((long) (bArr[i2 + 3] << 24)) & h) + ((long) ((bArr[i2 + 2] << 16) & e)) + ((long) ((bArr[i2 + 1] << 8) & 65280)) + ((long) (bArr[i2] & 255));
    }

    public static long getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ZipLong) || this.value != ((ZipLong) obj).getValue()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String toString() {
        return "ZipLong value: " + this.value;
    }
}
