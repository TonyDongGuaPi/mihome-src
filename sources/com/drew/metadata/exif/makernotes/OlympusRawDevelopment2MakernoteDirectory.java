package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusRawDevelopment2MakernoteDirectory extends Directory {
    public static final int A = 288;
    public static final int B = 289;
    @NotNull
    protected static final HashMap<Integer, String> C = new HashMap<>();
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
    public static final int s = 269;
    public static final int t = 270;
    public static final int u = 271;
    public static final int v = 272;
    public static final int w = 273;
    public static final int x = 274;
    public static final int y = 275;
    public static final int z = 281;

    @NotNull
    public String a() {
        return "Olympus Raw Development 2";
    }

    static {
        C.put(0, "Raw Dev Version");
        C.put(256, "Raw Dev Exposure Bias Value");
        C.put(257, "Raw Dev White Balance");
        C.put(258, "Raw Dev White Balance Value");
        C.put(259, "Raw Dev WB Fine Adjustment");
        C.put(260, "Raw Dev Gray Point");
        C.put(261, "Raw Dev Contrast Value");
        C.put(262, "Raw Dev Sharpness Value");
        C.put(263, "Raw Dev Saturation Emphasis");
        C.put(264, "Raw Dev Memory Color Emphasis");
        C.put(265, "Raw Dev Color Space");
        C.put(266, "Raw Dev Noise Reduction");
        C.put(267, "Raw Dev Engine");
        C.put(268, "Raw Dev Picture Mode");
        C.put(269, "Raw Dev PM Saturation");
        C.put(270, "Raw Dev PM Contrast");
        C.put(271, "Raw Dev PM Sharpness");
        C.put(272, "Raw Dev PM BW Filter");
        C.put(273, "Raw Dev PM Picture Tone");
        C.put(274, "Raw Dev Gradation");
        C.put(275, "Raw Dev Saturation 3");
        C.put(281, "Raw Dev Auto Gradation");
        C.put(288, "Raw Dev PM Noise Filter");
        C.put(289, "Raw Dev Art Filter");
    }

    public OlympusRawDevelopment2MakernoteDirectory() {
        a((TagDescriptor) new OlympusRawDevelopment2MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return C;
    }
}
