package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class GifImageDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    @NotNull
    protected static final HashMap<Integer, String> m = new HashMap<>();

    @NotNull
    public String a() {
        return "GIF Image";
    }

    static {
        m.put(1, "Left");
        m.put(2, "Top");
        m.put(3, "Width");
        m.put(4, "Height");
        m.put(5, "Has Local Colour Table");
        m.put(6, "Is Interlaced");
        m.put(7, "Is Local Colour Table Sorted");
        m.put(8, "Local Colour Table Bits Per Pixel");
    }

    public GifImageDirectory() {
        a((TagDescriptor) new GifImageDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return m;
    }
}
