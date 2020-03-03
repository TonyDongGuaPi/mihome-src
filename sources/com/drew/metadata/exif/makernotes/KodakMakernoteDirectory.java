package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class KodakMakernoteDirectory extends Directory {
    public static final int A = 100;
    public static final int B = 102;
    public static final int C = 104;
    public static final int D = 107;
    @NotNull
    protected static final HashMap<Integer, String> E = new HashMap<>();
    public static final int e = 0;
    public static final int f = 9;
    public static final int g = 10;
    public static final int h = 12;
    public static final int i = 14;
    public static final int j = 16;
    public static final int k = 18;
    public static final int l = 20;
    public static final int m = 24;
    public static final int n = 27;
    public static final int o = 28;
    public static final int p = 29;
    public static final int q = 30;
    public static final int r = 32;
    public static final int s = 36;
    public static final int t = 56;
    public static final int u = 64;
    public static final int v = 92;
    public static final int w = 93;
    public static final int x = 94;
    public static final int y = 96;
    public static final int z = 98;

    @NotNull
    public String a() {
        return "Kodak Makernote";
    }

    static {
        E.put(0, "Kodak Model");
        E.put(9, "Quality");
        E.put(10, "Burst Mode");
        E.put(12, "Image Width");
        E.put(14, "Image Height");
        E.put(16, "Year Created");
        E.put(18, "Month/Day Created");
        E.put(20, "Time Created");
        E.put(24, "Burst Mode 2");
        E.put(27, "Shutter Speed");
        E.put(28, "Metering Mode");
        E.put(29, "Sequence Number");
        E.put(30, "F Number");
        E.put(32, "Exposure Time");
        E.put(36, "Exposure Compensation");
        E.put(56, "Focus Mode");
        E.put(64, "White Balance");
        E.put(92, "Flash Mode");
        E.put(93, "Flash Fired");
        E.put(94, "ISO Setting");
        E.put(96, ExifInterface.TAG_RW2_ISO);
        E.put(98, "Total Zoom");
        E.put(100, "Date/Time Stamp");
        E.put(102, "Color Mode");
        E.put(104, "Digital Zoom");
        E.put(107, ExifInterface.TAG_SHARPNESS);
    }

    public KodakMakernoteDirectory() {
        a((TagDescriptor) new KodakMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return E;
    }
}
