package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ExifIFD0Directory extends ExifDirectoryBase {
    public static final int cg = 34665;
    public static final int ch = 34853;
    @NotNull
    protected static final HashMap<Integer, String> ci = new HashMap<>();

    @NotNull
    public String a() {
        return "Exif IFD0";
    }

    public ExifIFD0Directory() {
        a((TagDescriptor) new ExifIFD0Descriptor(this));
    }

    static {
        a(ci);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ci;
    }
}
