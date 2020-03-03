package com.drew.metadata.avi;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class AviDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final String m = "strh";
    public static final String n = "avih";
    public static final String o = "hdrl";
    public static final String p = "strl";
    public static final String q = "AVI ";
    @NotNull
    protected static final HashMap<Integer, String> r = new HashMap<>();

    @NotNull
    public String a() {
        return "AVI";
    }

    static {
        r.put(1, "Frames Per Second");
        r.put(2, "Samples Per Second");
        r.put(3, "Duration");
        r.put(4, "Video Codec");
        r.put(5, "Audio Codec");
        r.put(6, "Width");
        r.put(7, "Height");
        r.put(8, "Stream Count");
    }

    public AviDirectory() {
        a((TagDescriptor) new AviDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return r;
    }
}
