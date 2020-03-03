package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public class QuickTimeTextDirectory extends QuickTimeDirectory {
    public static final int A = 5;
    public static final int B = 6;
    public static final int C = 7;
    public static final int D = 8;
    public static final int E = 9;
    public static final int F = 10;
    public static final int G = 11;
    public static final int H = 12;
    public static final int I = 13;
    public static final int J = 14;
    public static final int K = 15;
    public static final int L = 16;
    public static final int M = 17;
    @NotNull
    protected static final HashMap<Integer, String> N = new HashMap<>();
    public static final int w = 1;
    public static final int x = 2;
    public static final int y = 3;
    public static final int z = 4;

    @NotNull
    public String a() {
        return "QuickTime Text";
    }

    public QuickTimeTextDirectory() {
        a((TagDescriptor) new QuickTimeTextDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(N);
        N.put(1, "Auto Scale");
        N.put(2, "Use Background Color");
        N.put(3, "Scroll In");
        N.put(4, "Scroll Out");
        N.put(5, "Scroll Orientation");
        N.put(6, "Scroll Direction");
        N.put(7, "Continuous Scroll");
        N.put(8, "Drop Shadow");
        N.put(9, "Anti-aliasing");
        N.put(10, "Display Text Background Color");
        N.put(11, "Alignment");
        N.put(12, "Background Color");
        N.put(13, "Default Text Box");
        N.put(14, "Font Number");
        N.put(15, "Font Face");
        N.put(16, "Foreground Color");
        N.put(17, "Font Name");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return N;
    }
}
