package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ExifImageDirectory extends ExifDirectoryBase {
    @NotNull
    protected static final HashMap<Integer, String> cg = new HashMap<>();

    @NotNull
    public String a() {
        return "Exif Image";
    }

    static {
        a(cg);
    }

    public ExifImageDirectory() {
        a((TagDescriptor) new ExifImageDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return cg;
    }
}
