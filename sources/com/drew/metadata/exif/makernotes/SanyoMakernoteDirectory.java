package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class SanyoMakernoteDirectory extends Directory {
    public static final int A = 547;
    public static final int B = 548;
    public static final int C = 549;
    public static final int D = 3584;
    public static final int E = 3840;
    @NotNull
    protected static final HashMap<Integer, String> F = new HashMap<>();
    public static final int e = 255;
    public static final int f = 256;
    public static final int g = 512;
    public static final int h = 513;
    public static final int i = 514;
    public static final int j = 516;
    public static final int k = 519;
    public static final int l = 520;
    public static final int m = 521;
    public static final int n = 526;
    public static final int o = 527;
    public static final int p = 528;
    public static final int q = 531;
    public static final int r = 532;
    public static final int s = 534;
    public static final int t = 535;
    public static final int u = 536;
    public static final int v = 537;
    public static final int w = 539;
    public static final int x = 541;
    public static final int y = 542;
    public static final int z = 543;

    @NotNull
    public String a() {
        return "Sanyo Makernote";
    }

    static {
        F.put(255, "Makernote Offset");
        F.put(256, "Sanyo Thumbnail");
        F.put(512, "Special Mode");
        F.put(513, "Sanyo Quality");
        F.put(514, "Macro");
        F.put(516, "Digital Zoom");
        F.put(519, "Software Version");
        F.put(520, "Pict Info");
        F.put(521, "Camera ID");
        F.put(Integer.valueOf(n), "Sequential Shot");
        F.put(527, "Wide Range");
        F.put(528, "Color Adjustment Node");
        F.put(531, "Quick Shot");
        F.put(532, "Self Timer");
        F.put(534, "Voice Memo");
        F.put(Integer.valueOf(t), "Record Shutter Release");
        F.put(Integer.valueOf(u), "Flicker Reduce");
        F.put(537, "Optical Zoom On");
        F.put(539, "Digital Zoom On");
        F.put(Integer.valueOf(x), "Light Source Special");
        F.put(542, "Resaved");
        F.put(Integer.valueOf(z), "Scene Select");
        F.put(547, "Manual Focus Distance or Face Info");
        F.put(Integer.valueOf(B), "Sequence Shot Interval");
        F.put(549, "Flash Mode");
        F.put(3584, "Print IM");
        F.put(3840, "Data Dump");
    }

    public SanyoMakernoteDirectory() {
        a((TagDescriptor) new SanyoMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return F;
    }
}
