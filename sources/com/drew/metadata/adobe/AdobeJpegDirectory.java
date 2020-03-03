package com.drew.metadata.adobe;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class AdobeJpegDirectory extends Directory {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    private static final HashMap<Integer, String> i = new HashMap<>();

    @NotNull
    public String a() {
        return "Adobe JPEG";
    }

    static {
        i.put(0, "DCT Encode Version");
        i.put(1, "Flags 0");
        i.put(2, "Flags 1");
        i.put(3, "Color Transform");
    }

    public AdobeJpegDirectory() {
        a((TagDescriptor) new AdobeJpegDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return i;
    }
}
