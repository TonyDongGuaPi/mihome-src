package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PentaxMakernoteDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 7;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 20;
    public static final int o = 23;
    public static final int p = 3584;
    public static final int q = 4096;
    public static final int r = 4097;
    @NotNull
    protected static final HashMap<Integer, String> s = new HashMap<>();

    @NotNull
    public String a() {
        return "Pentax Makernote";
    }

    static {
        s.put(1, "Capture Mode");
        s.put(2, "Quality Level");
        s.put(3, "Focus Mode");
        s.put(4, "Flash Mode");
        s.put(7, "White Balance");
        s.put(10, "Digital Zoom");
        s.put(11, ExifInterface.TAG_SHARPNESS);
        s.put(12, ExifInterface.TAG_CONTRAST);
        s.put(13, ExifInterface.TAG_SATURATION);
        s.put(20, "ISO Speed");
        s.put(23, "Colour");
        s.put(3584, "Print Image Matching (PIM) Info");
        s.put(4096, "Time Zone");
        s.put(4097, "Daylight Savings");
    }

    public PentaxMakernoteDirectory() {
        a((TagDescriptor) new PentaxMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return s;
    }
}
