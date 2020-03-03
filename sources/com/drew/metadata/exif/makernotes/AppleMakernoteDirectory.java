package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class AppleMakernoteDirectory extends Directory {
    public static final int e = 3;
    public static final int f = 10;
    public static final int g = 11;
    @NotNull
    protected static final HashMap<Integer, String> h = new HashMap<>();

    @NotNull
    public String a() {
        return "Apple Makernote";
    }

    static {
        h.put(3, "Run Time");
        h.put(10, "HDR Image Type");
        h.put(11, "Burst UUID");
    }

    public AppleMakernoteDirectory() {
        a((TagDescriptor) new AppleMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return h;
    }
}
