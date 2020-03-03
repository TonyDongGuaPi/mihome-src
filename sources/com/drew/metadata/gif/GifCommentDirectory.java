package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.StringValue;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class GifCommentDirectory extends Directory {
    public static final int e = 1;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();

    @NotNull
    public String a() {
        return "GIF Comment";
    }

    static {
        f.put(1, "Comment");
    }

    public GifCommentDirectory(StringValue stringValue) {
        a((TagDescriptor) new GifCommentDescriptor(this));
        a(1, stringValue);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }
}
