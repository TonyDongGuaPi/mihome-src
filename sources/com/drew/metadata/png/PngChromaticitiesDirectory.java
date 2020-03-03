package com.drew.metadata.png;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PngChromaticitiesDirectory extends Directory {
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
        return "PNG Chromaticities";
    }

    static {
        m.put(1, "White Point X");
        m.put(2, "White Point Y");
        m.put(3, "Red X");
        m.put(4, "Red Y");
        m.put(5, "Green X");
        m.put(6, "Green Y");
        m.put(7, "Blue X");
        m.put(8, "Blue Y");
    }

    public PngChromaticitiesDirectory() {
        a(new TagDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return m;
    }
}
