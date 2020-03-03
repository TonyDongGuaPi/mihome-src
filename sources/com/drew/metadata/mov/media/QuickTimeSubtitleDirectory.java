package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public class QuickTimeSubtitleDirectory extends QuickTimeDirectory {
    public static final int A = 5;
    public static final int B = 6;
    public static final int C = 7;
    public static final int D = 8;
    @NotNull
    protected static final HashMap<Integer, String> E = new HashMap<>();
    public static final int w = 1;
    public static final int x = 2;
    public static final int y = 3;
    public static final int z = 4;

    @NotNull
    public String a() {
        return "QuickTime Subtitle";
    }

    public QuickTimeSubtitleDirectory() {
        a((TagDescriptor) new QuickTimeSubtitleDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(E);
        E.put(1, "Vertical Placement");
        E.put(2, "Some Samples Forced");
        E.put(3, "All Samples Forced");
        E.put(4, "Default Text Box");
        E.put(5, "Font Identifier");
        E.put(6, "Font Face");
        E.put(7, "Font Size");
        E.put(8, "Foreground Color");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return E;
    }
}
