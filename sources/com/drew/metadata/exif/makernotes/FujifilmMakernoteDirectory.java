package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class FujifilmMakernoteDirectory extends Directory {
    public static final int A = 4353;
    public static final int B = 4624;
    public static final int C = 4864;
    public static final int D = 4865;
    public static final int E = 4866;
    public static final int F = 4868;
    public static final int G = 5120;
    public static final int H = 5121;
    public static final int I = 5122;
    public static final int J = 5123;
    public static final int K = 5124;
    public static final int L = 5125;
    public static final int M = 5126;
    public static final int N = 5127;
    public static final int O = 5131;
    public static final int P = 16640;
    public static final int Q = 16643;
    public static final int R = 17026;
    public static final int S = 32768;
    public static final int T = 32770;
    public static final int U = 32771;
    public static final int V = 45585;
    @NotNull
    protected static final HashMap<Integer, String> W = new HashMap<>();
    public static final int e = 0;
    public static final int f = 16;
    public static final int g = 4096;
    public static final int h = 4097;
    public static final int i = 4098;
    public static final int j = 4099;
    public static final int k = 4100;
    public static final int l = 4101;
    public static final int m = 4102;
    public static final int n = 4106;
    public static final int o = 4107;
    public static final int p = 4110;
    public static final int q = 4112;
    public static final int r = 4113;
    public static final int s = 4128;
    public static final int t = 4129;
    public static final int u = 4131;
    public static final int v = 4144;
    public static final int w = 4145;
    public static final int x = 4147;
    public static final int y = 4148;
    public static final int z = 4352;

    @NotNull
    public String a() {
        return "Fujifilm Makernote";
    }

    static {
        W.put(0, "Makernote Version");
        W.put(16, "Serial Number");
        W.put(4096, "Quality");
        W.put(4097, ExifInterface.TAG_SHARPNESS);
        W.put(4098, "White Balance");
        W.put(4099, "Color Saturation");
        W.put(4100, "Tone (Contrast)");
        W.put(4101, "Color Temperature");
        W.put(4102, ExifInterface.TAG_CONTRAST);
        W.put(4106, "White Balance Fine Tune");
        W.put(4107, "Noise Reduction");
        W.put(4110, "High ISO Noise Reduction");
        W.put(4112, "Flash Mode");
        W.put(4113, "Flash Strength");
        W.put(4128, "Macro");
        W.put(4129, "Focus Mode");
        W.put(4131, "Focus Pixel");
        W.put(4144, "Slow Sync");
        W.put(4145, "Picture Mode");
        W.put(4147, "EXR Auto");
        W.put(4148, "EXR Mode");
        W.put(Integer.valueOf(z), "Auto Bracketing");
        W.put(Integer.valueOf(A), "Sequence Number");
        W.put(Integer.valueOf(B), "FinePix Color Setting");
        W.put(Integer.valueOf(C), "Blur Warning");
        W.put(Integer.valueOf(D), "Focus Warning");
        W.put(Integer.valueOf(E), "AE Warning");
        W.put(Integer.valueOf(F), "GE Image Size");
        W.put(5120, "Dynamic Range");
        W.put(Integer.valueOf(H), "Film Mode");
        W.put(Integer.valueOf(I), "Dynamic Range Setting");
        W.put(Integer.valueOf(J), "Development Dynamic Range");
        W.put(Integer.valueOf(K), "Minimum Focal Length");
        W.put(Integer.valueOf(L), "Maximum Focal Length");
        W.put(Integer.valueOf(M), "Maximum Aperture at Minimum Focal Length");
        W.put(Integer.valueOf(N), "Maximum Aperture at Maximum Focal Length");
        W.put(Integer.valueOf(O), "Auto Dynamic Range");
        W.put(Integer.valueOf(P), "Faces Detected");
        W.put(Integer.valueOf(Q), "Face Positions");
        W.put(17026, "Face Detection Data");
        W.put(32768, "File Source");
        W.put(Integer.valueOf(T), "Order Number");
        W.put(Integer.valueOf(U), "Frame Number");
        W.put(Integer.valueOf(V), "Parallax");
    }

    public FujifilmMakernoteDirectory() {
        a((TagDescriptor) new FujifilmMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return W;
    }
}
