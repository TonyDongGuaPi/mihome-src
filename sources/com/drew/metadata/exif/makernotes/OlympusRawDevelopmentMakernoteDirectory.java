package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusRawDevelopmentMakernoteDirectory extends Directory {
    public static final int e = 0;
    public static final int f = 256;
    public static final int g = 257;
    public static final int h = 258;
    public static final int i = 259;
    public static final int j = 260;
    public static final int k = 261;
    public static final int l = 262;
    public static final int m = 263;
    public static final int n = 264;
    public static final int o = 265;
    public static final int p = 266;
    public static final int q = 267;
    public static final int r = 268;
    @NotNull
    protected static final HashMap<Integer, String> s = new HashMap<>();

    @NotNull
    public String a() {
        return "Olympus Raw Development";
    }

    static {
        s.put(0, "Raw Dev Version");
        s.put(256, "Raw Dev Exposure Bias Value");
        s.put(257, "Raw Dev White Balance Value");
        s.put(258, "Raw Dev WB Fine Adjustment");
        s.put(259, "Raw Dev Gray Point");
        s.put(260, "Raw Dev Saturation Emphasis");
        s.put(261, "Raw Dev Memory Color Emphasis");
        s.put(262, "Raw Dev Contrast Value");
        s.put(263, "Raw Dev Sharpness Value");
        s.put(264, "Raw Dev Color Space");
        s.put(265, "Raw Dev Engine");
        s.put(266, "Raw Dev Noise Reduction");
        s.put(267, "Raw Dev Edit Status");
        s.put(268, "Raw Dev Settings");
    }

    public OlympusRawDevelopmentMakernoteDirectory() {
        a((TagDescriptor) new OlympusRawDevelopmentMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return s;
    }
}
