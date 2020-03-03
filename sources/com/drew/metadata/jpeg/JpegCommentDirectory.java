package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class JpegCommentDirectory extends Directory {
    public static final int e = 0;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();

    @NotNull
    public String a() {
        return "JpegComment";
    }

    static {
        f.put(0, "JPEG Comment");
    }

    public JpegCommentDirectory() {
        a((TagDescriptor) new JpegCommentDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }
}
