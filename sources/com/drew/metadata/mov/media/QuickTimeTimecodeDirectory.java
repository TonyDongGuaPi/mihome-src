package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public class QuickTimeTimecodeDirectory extends QuickTimeDirectory {
    public static final int A = 5;
    public static final int B = 6;
    public static final int C = 7;
    public static final int D = 8;
    public static final int E = 9;
    public static final int F = 10;
    @NotNull
    protected static final HashMap<Integer, String> G = new HashMap<>();
    public static final int w = 1;
    public static final int x = 2;
    public static final int y = 3;
    public static final int z = 4;

    @NotNull
    public String a() {
        return "QuickTime Timecode";
    }

    public QuickTimeTimecodeDirectory() {
        a((TagDescriptor) new QuickTimeTimecodeDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(G);
        G.put(1, "Drop Frame");
        G.put(2, "24 Hour Max");
        G.put(3, "Negative Times OK");
        G.put(4, "Counter");
        G.put(5, "Text Font");
        G.put(6, "Text Face");
        G.put(7, "Text Size");
        G.put(8, "Text Color");
        G.put(9, "Background Color");
        G.put(10, "Font Name");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return G;
    }
}
