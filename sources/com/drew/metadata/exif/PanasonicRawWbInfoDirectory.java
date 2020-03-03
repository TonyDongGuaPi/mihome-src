package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PanasonicRawWbInfoDirectory extends Directory {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 10;
    public static final int m = 11;
    public static final int n = 13;
    public static final int o = 14;
    public static final int p = 16;
    public static final int q = 17;
    public static final int r = 19;
    public static final int s = 20;
    @NotNull
    protected static final HashMap<Integer, String> t = new HashMap<>();

    @NotNull
    public String a() {
        return "PanasonicRaw WbInfo";
    }

    static {
        t.put(0, "Num WB Entries");
        t.put(1, "WB Type 1");
        t.put(2, "WB RGB Levels 1");
        t.put(4, "WB Type 2");
        t.put(5, "WB RGB Levels 2");
        t.put(7, "WB Type 3");
        t.put(8, "WB RGB Levels 3");
        t.put(10, "WB Type 4");
        t.put(11, "WB RGB Levels 4");
        t.put(13, "WB Type 5");
        t.put(14, "WB RGB Levels 5");
        t.put(16, "WB Type 6");
        t.put(17, "WB RGB Levels 6");
        t.put(19, "WB Type 7");
        t.put(20, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfoDirectory() {
        a((TagDescriptor) new PanasonicRawWbInfoDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return t;
    }
}
