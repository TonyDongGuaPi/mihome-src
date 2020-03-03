package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class NikonType1MakernoteDirectory extends Directory {
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 9;
    public static final int m = 10;
    public static final int n = 11;
    public static final int o = 3840;
    @NotNull
    protected static final HashMap<Integer, String> p = new HashMap<>();

    @NotNull
    public String a() {
        return "Nikon Makernote";
    }

    static {
        p.put(6, "CCD Sensitivity");
        p.put(4, "Color Mode");
        p.put(10, "Digital Zoom");
        p.put(11, "Fisheye Converter");
        p.put(8, "Focus");
        p.put(5, "Image Adjustment");
        p.put(3, "Quality");
        p.put(2, "Makernote Unknown 1");
        p.put(9, "Makernote Unknown 2");
        p.put(3840, "Makernote Unknown 3");
        p.put(7, "White Balance");
    }

    public NikonType1MakernoteDirectory() {
        a((TagDescriptor) new NikonType1MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return p;
    }
}
