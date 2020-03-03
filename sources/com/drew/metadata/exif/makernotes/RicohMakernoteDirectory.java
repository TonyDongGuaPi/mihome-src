package com.drew.metadata.exif.makernotes;

import com.alipay.sdk.packet.e;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class RicohMakernoteDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3584;
    public static final int h = 8193;
    @NotNull
    protected static final HashMap<Integer, String> i = new HashMap<>();

    @NotNull
    public String a() {
        return "Ricoh Makernote";
    }

    static {
        i.put(1, "Makernote Data Type");
        i.put(2, e.e);
        i.put(3584, "Print Image Matching (PIM) Info");
        i.put(8193, "Ricoh Camera Info Makernote Sub-IFD");
    }

    public RicohMakernoteDirectory() {
        a((TagDescriptor) new RicohMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return i;
    }
}
