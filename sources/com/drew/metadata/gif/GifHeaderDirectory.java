package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class GifHeaderDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    @Deprecated
    public static final int l = 8;
    public static final int m = 8;
    public static final int n = 9;
    @NotNull
    protected static final HashMap<Integer, String> o = new HashMap<>();

    @NotNull
    public String a() {
        return "GIF Header";
    }

    static {
        o.put(1, "GIF Format Version");
        o.put(3, "Image Height");
        o.put(2, "Image Width");
        o.put(4, "Color Table Size");
        o.put(5, "Is Color Table Sorted");
        o.put(6, "Bits per Pixel");
        o.put(7, "Has Global Color Table");
        o.put(8, "Background Color Index");
        o.put(9, "Pixel Aspect Ratio");
    }

    public GifHeaderDirectory() {
        a((TagDescriptor) new GifHeaderDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return o;
    }
}
