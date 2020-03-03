package com.drew.metadata.mov;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class QuickTimeDirectory extends Directory {
    public static final int e = 256;
    public static final int f = 257;
    public static final int g = 258;
    public static final int h = 259;
    public static final int i = 260;
    public static final int j = 261;
    public static final int k = 264;
    public static final int l = 265;
    public static final int m = 266;
    public static final int n = 267;
    public static final int o = 268;
    public static final int p = 269;
    public static final int q = 270;
    public static final int r = 774;
    public static final int s = 4096;
    public static final int t = 4097;
    public static final int u = 4098;
    @NotNull
    protected static final HashMap<Integer, String> v = new HashMap<>();

    @NotNull
    public String a() {
        return "QuickTime";
    }

    static {
        v.put(4096, "Major Brand");
        v.put(4097, "Minor Version");
        v.put(4098, "Compatible Brands");
        v.put(256, "Creation Time");
        v.put(257, "Modification Time");
        v.put(258, "Media Time Scale");
        v.put(259, "Duration");
        v.put(260, "Preferred Rate");
        v.put(261, "Preferred Volume");
        v.put(264, "Preview Time");
        v.put(265, "Preview Duration");
        v.put(266, "Poster Time");
        v.put(267, "Selection Time");
        v.put(268, "Selection Duration");
        v.put(269, "Current Time");
        v.put(270, "Next Track ID");
        v.put(774, "Media Time Scale");
    }

    public QuickTimeDirectory() {
        a((TagDescriptor) new QuickTimeDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return v;
    }
}
