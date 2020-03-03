package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class Mp4TextDirectory extends Mp4MediaDirectory {
    @NotNull
    protected static final HashMap<Integer, String> x = new HashMap<>();

    @NotNull
    public String a() {
        return "MP4 Text";
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return null;
    }

    public Mp4TextDirectory() {
        a((TagDescriptor) new Mp4TextDescriptor(this));
    }

    static {
        Mp4MediaDirectory.a(x);
    }
}
