package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class CasioType1MakernoteDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 11;
    public static final int p = 12;
    public static final int q = 13;
    public static final int r = 14;
    public static final int s = 15;
    public static final int t = 16;
    public static final int u = 17;
    public static final int v = 18;
    public static final int w = 19;
    public static final int x = 20;
    @NotNull
    protected static final HashMap<Integer, String> y = new HashMap<>();

    @NotNull
    public String a() {
        return "Casio Makernote";
    }

    static {
        y.put(20, "CCD Sensitivity");
        y.put(12, ExifInterface.TAG_CONTRAST);
        y.put(10, "Digital Zoom");
        y.put(5, "Flash Intensity");
        y.put(4, "Flash Mode");
        y.put(3, "Focusing Mode");
        y.put(6, "Object Distance");
        y.put(2, "Quality");
        y.put(1, "Recording Mode");
        y.put(13, ExifInterface.TAG_SATURATION);
        y.put(11, ExifInterface.TAG_SHARPNESS);
        y.put(8, "Makernote Unknown 1");
        y.put(9, "Makernote Unknown 2");
        y.put(14, "Makernote Unknown 3");
        y.put(15, "Makernote Unknown 4");
        y.put(16, "Makernote Unknown 5");
        y.put(17, "Makernote Unknown 6");
        y.put(18, "Makernote Unknown 7");
        y.put(19, "Makernote Unknown 8");
        y.put(7, "White Balance");
    }

    public CasioType1MakernoteDirectory() {
        a((TagDescriptor) new CasioType1MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return y;
    }
}
