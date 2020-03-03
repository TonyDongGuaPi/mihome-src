package com.drew.metadata.photoshop;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PsdHeaderDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    @NotNull
    protected static final HashMap<Integer, String> j = new HashMap<>();

    @NotNull
    public String a() {
        return "PSD Header";
    }

    static {
        j.put(1, "Channel Count");
        j.put(2, "Image Height");
        j.put(3, "Image Width");
        j.put(4, "Bits Per Channel");
        j.put(5, "Color Mode");
    }

    public PsdHeaderDirectory() {
        a((TagDescriptor) new PsdHeaderDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return j;
    }
}
