package com.drew.metadata.pcx;

import com.alipay.sdk.packet.e;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PcxDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 11;
    public static final int p = 12;
    public static final int q = 13;
    public static final int r = 14;
    @NotNull
    protected static final HashMap<Integer, String> s = new HashMap<>();

    @NotNull
    public String a() {
        return "PCX";
    }

    static {
        s.put(1, e.e);
        s.put(2, "Bits Per Pixel");
        s.put(3, "X Min");
        s.put(4, "Y Min");
        s.put(5, "X Max");
        s.put(6, "Y Max");
        s.put(7, "Horizontal DPI");
        s.put(8, "Vertical DPI");
        s.put(9, "Palette");
        s.put(10, "Color Planes");
        s.put(11, "Bytes Per Line");
        s.put(12, "Palette Type");
        s.put(13, "H Scr Size");
        s.put(14, "V Scr Size");
    }

    public PcxDirectory() {
        a((TagDescriptor) new PcxDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return s;
    }
}
