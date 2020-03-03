package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class SigmaMakernoteDirectory extends Directory {
    public static final int A = 25;
    @NotNull
    protected static final HashMap<Integer, String> B = new HashMap<>();
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
    public static final int o = 12;
    public static final int p = 13;
    public static final int q = 14;
    public static final int r = 15;
    public static final int s = 16;
    public static final int t = 17;
    public static final int u = 18;
    public static final int v = 20;
    public static final int w = 21;
    public static final int x = 22;
    public static final int y = 23;
    public static final int z = 24;

    @NotNull
    public String a() {
        return "Sigma Makernote";
    }

    static {
        B.put(2, "Serial Number");
        B.put(3, "Drive Mode");
        B.put(4, "Resolution Mode");
        B.put(5, "Auto Focus Mode");
        B.put(6, "Focus Setting");
        B.put(7, "White Balance");
        B.put(8, "Exposure Mode");
        B.put(9, "Metering Mode");
        B.put(10, "Lens Range");
        B.put(11, "Color Space");
        B.put(12, "Exposure");
        B.put(13, ExifInterface.TAG_CONTRAST);
        B.put(14, "Shadow");
        B.put(15, "Highlight");
        B.put(16, ExifInterface.TAG_SATURATION);
        B.put(17, ExifInterface.TAG_SHARPNESS);
        B.put(18, "Fill Light");
        B.put(20, "Color Adjustment");
        B.put(21, "Adjustment Mode");
        B.put(22, "Quality");
        B.put(23, "Firmware");
        B.put(24, ExifInterface.TAG_SOFTWARE);
        B.put(25, "Auto Bracket");
    }

    public SigmaMakernoteDirectory() {
        a((TagDescriptor) new SigmaMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return B;
    }
}
