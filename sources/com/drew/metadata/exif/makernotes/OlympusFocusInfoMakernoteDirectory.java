package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusFocusInfoMakernoteDirectory extends Directory {
    public static final int e = 0;
    public static final int f = 521;
    public static final int g = 528;
    public static final int h = 529;
    public static final int i = 530;
    public static final int j = 768;
    public static final int k = 769;
    public static final int l = 771;
    public static final int m = 772;
    public static final int n = 773;
    public static final int o = 776;
    public static final int p = 808;
    public static final int q = 4609;
    public static final int r = 4611;
    public static final int s = 4612;
    public static final int t = 4613;
    public static final int u = 4616;
    public static final int v = 4617;
    public static final int w = 4618;
    public static final int x = 5376;
    public static final int y = 5632;
    @NotNull
    protected static final HashMap<Integer, String> z = new HashMap<>();

    @NotNull
    public String a() {
        return "Olympus Focus Info";
    }

    static {
        z.put(0, "Focus Info Version");
        z.put(521, "Auto Focus");
        z.put(528, "Scene Detect");
        z.put(529, "Scene Area");
        z.put(530, "Scene Detect Data");
        z.put(768, "Zoom Step Count");
        z.put(769, "Focus Step Count");
        z.put(771, "Focus Step Infinity");
        z.put(772, "Focus Step Near");
        z.put(773, "Focus Distance");
        z.put(Integer.valueOf(o), "AF Point");
        z.put(808, "AF Info");
        z.put(4609, "External Flash");
        z.put(4611, "External Flash Guide Number");
        z.put(Integer.valueOf(s), "External Flash Bounce");
        z.put(Integer.valueOf(t), "External Flash Zoom");
        z.put(Integer.valueOf(u), "Internal Flash");
        z.put(Integer.valueOf(v), "Manual Flash");
        z.put(Integer.valueOf(w), "Macro LED");
        z.put(Integer.valueOf(x), "Sensor Temperature");
        z.put(Integer.valueOf(y), "Image Stabilization");
    }

    public OlympusFocusInfoMakernoteDirectory() {
        a((TagDescriptor) new OlympusFocusInfoMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return z;
    }
}
