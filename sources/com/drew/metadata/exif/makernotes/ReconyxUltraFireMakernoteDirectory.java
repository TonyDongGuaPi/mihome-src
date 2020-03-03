package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ReconyxUltraFireMakernoteDirectory extends Directory {
    public static final int A = 80;
    @NotNull
    protected static final HashMap<Integer, String> B = new HashMap<>();
    public static final int e = 65536;
    public static final int f = 133234689;
    public static final int g = 0;
    public static final int h = 10;
    public static final int i = 14;
    public static final int j = 18;
    public static final int k = 22;
    public static final int l = 24;
    public static final int m = 31;
    public static final int n = 38;
    public static final int o = 45;
    public static final int p = 52;
    public static final int q = 53;
    public static final int r = 55;
    public static final int s = 59;
    public static final int t = 66;
    public static final int u = 67;
    public static final int v = 68;
    public static final int w = 70;
    public static final int x = 72;
    public static final int y = 73;
    public static final int z = 75;

    @NotNull
    public String a() {
        return "Reconyx UltraFire Makernote";
    }

    static {
        B.put(0, "Makernote Label");
        B.put(10, "Makernote ID");
        B.put(14, "Makernote Size");
        B.put(18, "Makernote Public ID");
        B.put(22, "Makernote Public Size");
        B.put(24, "Camera Version");
        B.put(31, "Uib Version");
        B.put(38, "Btl Version");
        B.put(45, "Pex Version");
        B.put(52, "Event Type");
        B.put(53, "Sequence");
        B.put(55, "Event Number");
        B.put(59, "Date/Time Original");
        B.put(66, "Day of Week");
        B.put(67, "Moon Phase");
        B.put(68, "Ambient Temperature Fahrenheit");
        B.put(70, "Ambient Temperature");
        B.put(72, ExifInterface.TAG_FLASH);
        B.put(73, "Battery Voltage");
        B.put(75, "Serial Number");
        B.put(80, "User Label");
    }

    public ReconyxUltraFireMakernoteDirectory() {
        a((TagDescriptor) new ReconyxUltraFireMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return B;
    }
}
