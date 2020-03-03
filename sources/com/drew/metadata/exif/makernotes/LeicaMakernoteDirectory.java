package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class LeicaMakernoteDirectory extends Directory {
    public static final int e = 768;
    public static final int f = 770;
    public static final int g = 771;
    public static final int h = 772;
    public static final int i = 784;
    public static final int j = 785;
    public static final int k = 786;
    public static final int l = 787;
    public static final int m = 800;
    public static final int n = 801;
    public static final int o = 802;
    public static final int p = 803;
    public static final int q = 804;
    public static final int r = 816;
    public static final int s = 817;
    public static final int t = 818;
    public static final int u = 819;
    public static final int v = 832;
    @NotNull
    protected static final HashMap<Integer, String> w = new HashMap<>();

    @NotNull
    public String a() {
        return "Leica Makernote";
    }

    static {
        w.put(768, "Quality");
        w.put(770, "User Profile");
        w.put(771, "Serial Number");
        w.put(772, "White Balance");
        w.put(784, "Lens Type");
        w.put(785, "External Sensor Brightness Value");
        w.put(Integer.valueOf(k), "Measured LV");
        w.put(Integer.valueOf(l), "Approximate F Number");
        w.put(800, "Camera Temperature");
        w.put(801, "Color Temperature");
        w.put(802, "WB Red Level");
        w.put(803, "WB Green Level");
        w.put(804, "WB Blue Level");
        w.put(816, "CCD Version");
        w.put(817, "CCD Board Version");
        w.put(818, "Controller Board Version");
        w.put(819, "M16 C Version");
        w.put(832, "Image ID Number");
    }

    public LeicaMakernoteDirectory() {
        a((TagDescriptor) new LeicaMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return w;
    }
}
