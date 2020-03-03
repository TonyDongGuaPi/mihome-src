package com.drew.metadata.webp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class WebpDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final String i = "VP8X";
    public static final String j = "VP8L";
    public static final String k = "VP8 ";
    public static final String l = "EXIF";
    public static final String m = "ICCP";
    public static final String n = "XMP ";
    public static final String o = "WEBP";
    @NotNull
    protected static final HashMap<Integer, String> p = new HashMap<>();

    @NotNull
    public String a() {
        return "WebP";
    }

    static {
        p.put(1, "Image Height");
        p.put(2, "Image Width");
        p.put(3, "Has Alpha");
        p.put(4, "Is Animation");
    }

    public WebpDirectory() {
        a((TagDescriptor) new WebpDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return p;
    }
}
