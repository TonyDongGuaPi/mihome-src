package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class GifAnimationDirectory extends Directory {
    public static final int e = 1;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();

    @NotNull
    public String a() {
        return "GIF Animation";
    }

    static {
        f.put(1, "Iteration Count");
    }

    public GifAnimationDirectory() {
        a((TagDescriptor) new GifAnimationDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }
}
