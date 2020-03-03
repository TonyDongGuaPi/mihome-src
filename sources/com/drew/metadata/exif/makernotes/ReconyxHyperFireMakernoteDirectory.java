package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ReconyxHyperFireMakernoteDirectory extends Directory {
    public static final int e = 61697;
    public static final int f = 0;
    public static final int g = 2;
    public static final int h = 12;
    public static final int i = 14;
    public static final int j = 18;
    public static final int k = 22;
    public static final int l = 36;
    public static final int m = 38;
    public static final int n = 40;
    public static final int o = 42;
    public static final int p = 72;
    public static final int q = 74;
    public static final int r = 76;
    public static final int s = 78;
    public static final int t = 80;
    public static final int u = 82;
    public static final int v = 84;
    public static final int w = 86;
    @NotNull
    protected static final HashMap<Integer, String> x = new HashMap<>();

    @NotNull
    public String a() {
        return "Reconyx HyperFire Makernote";
    }

    static {
        x.put(0, "Makernote Version");
        x.put(2, "Firmware Version");
        x.put(12, "Trigger Mode");
        x.put(14, "Sequence");
        x.put(18, "Event Number");
        x.put(22, "Date/Time Original");
        x.put(36, "Moon Phase");
        x.put(38, "Ambient Temperature Fahrenheit");
        x.put(40, "Ambient Temperature");
        x.put(42, "Serial Number");
        x.put(72, ExifInterface.TAG_CONTRAST);
        x.put(74, "Brightness");
        x.put(76, ExifInterface.TAG_SHARPNESS);
        x.put(78, ExifInterface.TAG_SATURATION);
        x.put(80, "Infrared Illuminator");
        x.put(82, "Motion Sensitivity");
        x.put(84, "Battery Voltage");
        x.put(86, "User Label");
    }

    public ReconyxHyperFireMakernoteDirectory() {
        a((TagDescriptor) new ReconyxHyperFireMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return x;
    }
}
