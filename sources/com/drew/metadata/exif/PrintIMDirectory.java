package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PrintIMDirectory extends Directory {
    public static final int e = 0;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();

    @NotNull
    public String a() {
        return "PrintIM";
    }

    static {
        f.put(0, "PrintIM Version");
    }

    public PrintIMDirectory() {
        a((TagDescriptor) new PrintIMDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }
}
