package com.xiaomi.zxing.qrcode.decoder;

import com.drew.metadata.iptc.IptcDirectory;

final class FormatInformation {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1761a = 21522;
    private static final int[][] b = {new int[]{f1761a, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{IptcDirectory.X, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    private final ErrorCorrectionLevel c;
    private final byte d;

    private FormatInformation(int i) {
        this.c = ErrorCorrectionLevel.forBits((i >> 3) & 3);
        this.d = (byte) (i & 7);
    }

    static int a(int i, int i2) {
        return Integer.bitCount(i ^ i2);
    }

    static FormatInformation b(int i, int i2) {
        FormatInformation c2 = c(i, i2);
        if (c2 != null) {
            return c2;
        }
        return c(i ^ f1761a, i2 ^ f1761a);
    }

    private static FormatInformation c(int i, int i2) {
        int a2;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (int[] iArr : b) {
            int i5 = iArr[0];
            if (i5 == i || i5 == i2) {
                return new FormatInformation(iArr[1]);
            }
            int a3 = a(i, i5);
            if (a3 < i3) {
                i4 = iArr[1];
                i3 = a3;
            }
            if (i != i2 && (a2 = a(i2, i5)) < i3) {
                i4 = iArr[1];
                i3 = a2;
            }
        }
        if (i3 <= 3) {
            return new FormatInformation(i4);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ErrorCorrectionLevel a() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public byte b() {
        return this.d;
    }

    public int hashCode() {
        return (this.c.ordinal() << 3) | this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FormatInformation)) {
            return false;
        }
        FormatInformation formatInformation = (FormatInformation) obj;
        if (this.c == formatInformation.c && this.d == formatInformation.d) {
            return true;
        }
        return false;
    }
}
