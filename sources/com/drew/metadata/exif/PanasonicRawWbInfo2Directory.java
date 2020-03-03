package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PanasonicRawWbInfo2Directory extends Directory {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 13;
    public static final int m = 14;
    public static final int n = 17;
    public static final int o = 18;
    public static final int p = 21;
    public static final int q = 22;
    public static final int r = 25;
    public static final int s = 26;
    @NotNull
    protected static final HashMap<Integer, String> t = new HashMap<>();

    @NotNull
    public String a() {
        return "PanasonicRaw WbInfo2";
    }

    static {
        t.put(0, "Num WB Entries");
        t.put(0, "Num WB Entries");
        t.put(1, "WB Type 1");
        t.put(2, "WB RGB Levels 1");
        t.put(5, "WB Type 2");
        t.put(6, "WB RGB Levels 2");
        t.put(9, "WB Type 3");
        t.put(10, "WB RGB Levels 3");
        t.put(13, "WB Type 4");
        t.put(14, "WB RGB Levels 4");
        t.put(17, "WB Type 5");
        t.put(18, "WB RGB Levels 5");
        t.put(21, "WB Type 6");
        t.put(22, "WB RGB Levels 6");
        t.put(25, "WB Type 7");
        t.put(26, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfo2Directory() {
        a((TagDescriptor) new PanasonicRawWbInfo2Descriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return t;
    }
}
