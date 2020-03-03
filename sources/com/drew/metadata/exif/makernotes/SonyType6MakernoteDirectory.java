package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class SonyType6MakernoteDirectory extends Directory {
    public static final int e = 1299;
    public static final int f = 1300;
    public static final int g = 8192;
    @NotNull
    protected static final HashMap<Integer, String> h = new HashMap<>();

    @NotNull
    public String a() {
        return "Sony Makernote";
    }

    static {
        h.put(Integer.valueOf(e), "Makernote Thumb Offset");
        h.put(Integer.valueOf(f), "Makernote Thumb Length");
        h.put(8192, "Makernote Thumb Version");
    }

    public SonyType6MakernoteDirectory() {
        a((TagDescriptor) new SonyType6MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return h;
    }
}
