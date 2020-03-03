package com.drew.metadata.mp4;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class Mp4Directory extends Directory {
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
    public static final int r = 271;
    public static final int s = 774;
    public static final int t = 1;
    public static final int u = 2;
    public static final int v = 3;
    @NotNull
    protected static final HashMap<Integer, String> w = new HashMap<>();

    @NotNull
    public String a() {
        return "MP4";
    }

    static {
        w.put(1, "Major Brand");
        w.put(2, "Minor Version");
        w.put(3, "Compatible Brands");
        w.put(256, "Creation Time");
        w.put(257, "Modification Time");
        w.put(258, "Media Time Scale");
        w.put(259, "Duration");
        w.put(260, "Preferred Rate");
        w.put(261, "Preferred Volume");
        w.put(264, "Preview Time");
        w.put(265, "Preview Duration");
        w.put(266, "Poster Time");
        w.put(267, "Selection Time");
        w.put(268, "Selection Duration");
        w.put(269, "Current Time");
        w.put(270, "Next Track ID");
        w.put(271, "Transformation Matrix");
        w.put(774, "Media Time Scale");
    }

    public Mp4Directory() {
        a((TagDescriptor) new Mp4Descriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return w;
    }
}
