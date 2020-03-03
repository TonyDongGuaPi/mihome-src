package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ExifInteropDirectory extends ExifDirectoryBase {
    @NotNull
    protected static final HashMap<Integer, String> cg = new HashMap<>();

    @NotNull
    public String a() {
        return "Interoperability";
    }

    static {
        a(cg);
    }

    public ExifInteropDirectory() {
        a((TagDescriptor) new ExifInteropDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return cg;
    }
}
