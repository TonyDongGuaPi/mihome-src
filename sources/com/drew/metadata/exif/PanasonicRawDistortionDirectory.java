package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PanasonicRawDistortionDirectory extends Directory {
    public static final int e = 2;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 11;
    public static final int l = 12;
    @NotNull
    protected static final HashMap<Integer, String> m = new HashMap<>();

    @NotNull
    public String a() {
        return "PanasonicRaw DistortionInfo";
    }

    static {
        m.put(2, "Distortion Param 2");
        m.put(4, "Distortion Param 4");
        m.put(5, "Distortion Scale");
        m.put(7, "Distortion Correction");
        m.put(8, "Distortion Param 8");
        m.put(9, "Distortion Param 9");
        m.put(11, "Distortion Param 11");
        m.put(12, "Distortion N");
    }

    public PanasonicRawDistortionDirectory() {
        a((TagDescriptor) new PanasonicRawDistortionDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return m;
    }
}
