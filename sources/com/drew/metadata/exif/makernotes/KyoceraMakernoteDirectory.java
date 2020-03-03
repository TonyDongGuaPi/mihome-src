package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class KyoceraMakernoteDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 3584;
    @NotNull
    protected static final HashMap<Integer, String> g = new HashMap<>();

    @NotNull
    public String a() {
        return "Kyocera/Contax Makernote";
    }

    static {
        g.put(1, "Proprietary Thumbnail Format Data");
        g.put(3584, "Print Image Matching (PIM) Info");
    }

    public KyoceraMakernoteDirectory() {
        a((TagDescriptor) new KyoceraMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return g;
    }
}
