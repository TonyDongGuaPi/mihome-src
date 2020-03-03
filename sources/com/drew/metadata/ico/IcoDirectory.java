package com.drew.metadata.ico;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class IcoDirectory extends Directory {
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
    @NotNull
    protected static final HashMap<Integer, String> o = new HashMap<>();

    @NotNull
    public String a() {
        return "ICO";
    }

    static {
        o.put(1, "Image Type");
        o.put(2, "Image Width");
        o.put(3, "Image Height");
        o.put(4, "Colour Palette Size");
        o.put(5, "Colour Planes");
        o.put(6, "Hotspot X");
        o.put(7, "Bits Per Pixel");
        o.put(8, "Hotspot Y");
        o.put(9, "Image Size Bytes");
        o.put(10, "Image Offset Bytes");
    }

    public IcoDirectory() {
        a((TagDescriptor) new IcoDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return o;
    }
}
