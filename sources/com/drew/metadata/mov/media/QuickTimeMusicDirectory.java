package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public class QuickTimeMusicDirectory extends QuickTimeDirectory {
    @NotNull
    protected static final HashMap<Integer, String> w = new HashMap<>();

    @NotNull
    public String a() {
        return "QuickTime Music";
    }

    public QuickTimeMusicDirectory() {
        a((TagDescriptor) new QuickTimeMusicDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(w);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return w;
    }
}
