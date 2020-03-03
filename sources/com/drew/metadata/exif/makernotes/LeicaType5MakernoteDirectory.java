package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class LeicaType5MakernoteDirectory extends Directory {
    public static final int e = 771;
    public static final int f = 1031;
    public static final int g = 1032;
    public static final int h = 1037;
    public static final int i = 1040;
    public static final int j = 1042;
    public static final int k = 1043;
    @NotNull
    protected static final HashMap<Integer, String> l = new HashMap<>();

    @NotNull
    public String a() {
        return "Leica Makernote";
    }

    static {
        l.put(771, "Lens Model");
        l.put(1031, "Original File Name");
        l.put(1032, "Original Directory");
        l.put(1037, "Exposure Mode");
        l.put(1040, "Shot Info");
        l.put(1042, "Film Mode");
        l.put(1043, "WB RGB Levels");
    }

    public LeicaType5MakernoteDirectory() {
        a((TagDescriptor) new LeicaType5MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return l;
    }
}
