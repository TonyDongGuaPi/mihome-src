package com.drew.metadata.photoshop;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class DuckyDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    @NotNull
    protected static final HashMap<Integer, String> h = new HashMap<>();

    @NotNull
    public String a() {
        return "Ducky";
    }

    static {
        h.put(1, "Quality");
        h.put(2, "Comment");
        h.put(3, ExifInterface.TAG_COPYRIGHT);
    }

    public DuckyDirectory() {
        a(new TagDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return h;
    }
}
