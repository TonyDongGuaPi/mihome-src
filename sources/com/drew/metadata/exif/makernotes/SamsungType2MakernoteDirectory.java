package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class SamsungType2MakernoteDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 67;
    public static final int i = 256;
    public static final int j = 288;
    public static final int k = 291;
    public static final int l = 40961;
    @NotNull
    protected static final HashMap<Integer, String> m = new HashMap<>();

    @NotNull
    public String a() {
        return "Samsung Makernote";
    }

    static {
        m.put(1, "Maker Note Version");
        m.put(2, "Device Type");
        m.put(3, "Model Id");
        m.put(67, "Camera Temperature");
        m.put(256, "Face Detect");
        m.put(288, "Face Recognition");
        m.put(291, "Face Name");
        m.put(40961, "Firmware Name");
    }

    public SamsungType2MakernoteDirectory() {
        a((TagDescriptor) new SamsungType2MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return m;
    }
}
