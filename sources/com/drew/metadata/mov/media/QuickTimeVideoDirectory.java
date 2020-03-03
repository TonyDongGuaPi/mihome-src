package com.drew.metadata.mov.media;

import com.alipay.zoloz.toyger.blob.BlobManager;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class QuickTimeVideoDirectory extends QuickTimeMediaDirectory {
    public static final int A = 2;
    public static final int B = 3;
    public static final int C = 4;
    public static final int D = 5;
    public static final int E = 6;
    public static final int F = 7;
    public static final int G = 8;
    public static final int H = 9;
    public static final int I = 10;
    public static final int J = 11;
    public static final int K = 12;
    public static final int L = 13;
    public static final int M = 14;
    @NotNull
    protected static final HashMap<Integer, String> N = new HashMap<>();
    public static final int z = 1;

    @NotNull
    public String a() {
        return "QuickTime Video";
    }

    public QuickTimeVideoDirectory() {
        a((TagDescriptor) new QuickTimeVideoDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(N);
        N.put(1, "Vendor");
        N.put(2, "Temporal Quality");
        N.put(3, "Spatial Quality");
        N.put(4, "Width");
        N.put(5, "Height");
        N.put(6, "Horizontal Resolution");
        N.put(7, "Vertical Resolution");
        N.put(8, "Compressor Name");
        N.put(9, BlobManager.SUB_TYPE_DEPTH);
        N.put(10, "Compression Type");
        N.put(11, "Graphics Mode");
        N.put(12, "Opcolor");
        N.put(13, "Color Table");
        N.put(14, "Frame Rate");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return N;
    }
}
