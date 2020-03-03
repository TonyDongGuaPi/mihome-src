package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class ExifThumbnailDirectory extends ExifDirectoryBase {
    public static final int cg = 513;
    public static final int ch = 514;
    @Deprecated
    public static final int ci = 259;
    @NotNull
    protected static final HashMap<Integer, String> cj = new HashMap<>();

    @NotNull
    public String a() {
        return "Exif Thumbnail";
    }

    static {
        a(cj);
        cj.put(513, "Thumbnail Offset");
        cj.put(514, "Thumbnail Length");
    }

    public ExifThumbnailDirectory() {
        a((TagDescriptor) new ExifThumbnailDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return cj;
    }
}
