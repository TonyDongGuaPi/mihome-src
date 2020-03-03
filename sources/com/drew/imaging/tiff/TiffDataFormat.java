package com.drew.imaging.tiff;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;

public class TiffDataFormat {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5190a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    @NotNull
    public static final TiffDataFormat m = new TiffDataFormat("BYTE", 1, 1);
    @NotNull
    public static final TiffDataFormat n = new TiffDataFormat("STRING", 2, 1);
    @NotNull
    public static final TiffDataFormat o = new TiffDataFormat("USHORT", 3, 2);
    @NotNull
    public static final TiffDataFormat p = new TiffDataFormat("ULONG", 4, 4);
    @NotNull
    public static final TiffDataFormat q = new TiffDataFormat("URATIONAL", 5, 8);
    @NotNull
    public static final TiffDataFormat r = new TiffDataFormat("SBYTE", 6, 1);
    @NotNull
    public static final TiffDataFormat s = new TiffDataFormat("UNDEFINED", 7, 1);
    @NotNull
    public static final TiffDataFormat t = new TiffDataFormat("SSHORT", 8, 2);
    @NotNull
    public static final TiffDataFormat u = new TiffDataFormat("SLONG", 9, 4);
    @NotNull
    public static final TiffDataFormat v = new TiffDataFormat("SRATIONAL", 10, 8);
    @NotNull
    public static final TiffDataFormat w = new TiffDataFormat("SINGLE", 11, 4);
    @NotNull
    public static final TiffDataFormat x = new TiffDataFormat("DOUBLE", 12, 8);
    private final int A;
    @NotNull
    private final String y;
    private final int z;

    @Nullable
    public static TiffDataFormat a(int i2) {
        switch (i2) {
            case 1:
                return m;
            case 2:
                return n;
            case 3:
                return o;
            case 4:
                return p;
            case 5:
                return q;
            case 6:
                return r;
            case 7:
                return s;
            case 8:
                return t;
            case 9:
                return u;
            case 10:
                return v;
            case 11:
                return w;
            case 12:
                return x;
            default:
                return null;
        }
    }

    private TiffDataFormat(@NotNull String str, int i2, int i3) {
        this.y = str;
        this.z = i2;
        this.A = i3;
    }

    public int a() {
        return this.A;
    }

    public int b() {
        return this.z;
    }

    @NotNull
    public String toString() {
        return this.y;
    }
}
