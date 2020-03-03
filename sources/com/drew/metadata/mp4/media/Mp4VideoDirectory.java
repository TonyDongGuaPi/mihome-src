package com.drew.metadata.mp4.media;

import com.alipay.zoloz.toyger.blob.BlobManager;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class Mp4VideoDirectory extends Mp4MediaDirectory {
    public static final int A = 104;
    public static final int B = 105;
    public static final int G = 106;
    public static final int H = 107;
    public static final int I = 108;
    public static final int J = 109;
    public static final int K = 110;
    public static final int L = 111;
    public static final int M = 112;
    public static final int N = 113;
    public static final int O = 114;
    @NotNull
    protected static final HashMap<Integer, String> P = new HashMap<>();
    public static final int x = 101;
    public static final int y = 102;
    public static final int z = 103;

    @NotNull
    public String a() {
        return "MP4 Video";
    }

    public Mp4VideoDirectory() {
        a((TagDescriptor) new Mp4VideoDescriptor(this));
    }

    static {
        Mp4MediaDirectory.a(P);
        P.put(101, "Vendor");
        P.put(102, "Temporal Quality");
        P.put(103, "Spatial Quality");
        P.put(104, "Width");
        P.put(105, "Height");
        P.put(106, "Horizontal Resolution");
        P.put(107, "Vertical Resolution");
        P.put(108, "Compressor Name");
        P.put(109, BlobManager.SUB_TYPE_DEPTH);
        P.put(110, "Compression Type");
        P.put(111, "Graphics Mode");
        P.put(112, "Opcolor");
        P.put(113, "Color Table");
        P.put(114, "Frame Rate");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return P;
    }
}
