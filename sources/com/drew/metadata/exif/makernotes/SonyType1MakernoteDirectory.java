package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class SonyType1MakernoteDirectory extends Directory {
    public static final int A = 8202;
    public static final int B = 8203;
    public static final int C = 8206;
    public static final int D = 8207;
    public static final int E = 8209;
    public static final int F = 8210;
    public static final int G = 8211;
    public static final int H = 8212;
    public static final int I = 8214;
    public static final int J = 8219;
    public static final int K = 8222;
    public static final int L = 12288;
    public static final int M = 45056;
    public static final int N = 45057;
    public static final int O = 45088;
    public static final int P = 45089;
    public static final int Q = 45090;
    public static final int R = 45091;
    public static final int S = 45092;
    public static final int T = 45093;
    public static final int U = 45094;
    public static final int V = 45095;
    public static final int W = 45096;
    public static final int X = 45097;
    public static final int Y = 45098;
    public static final int Z = 45099;
    public static final int aa = 45100;
    public static final int ab = 45120;
    public static final int ac = 45121;
    public static final int ad = 45122;
    public static final int ae = 45123;
    public static final int af = 45124;
    public static final int ag = 45127;
    public static final int ah = 45128;
    public static final int ai = 45129;
    public static final int aj = 45130;
    public static final int ak = 45131;
    public static final int al = 45134;
    public static final int am = 45135;
    public static final int an = 45136;
    public static final int ao = 45138;
    public static final int ap = 45140;
    public static final int aq = 65535;
    @NotNull
    protected static final HashMap<Integer, String> ar = new HashMap<>();
    public static final int e = 16;
    public static final int f = 32;
    public static final int g = 258;
    public static final int h = 260;
    public static final int i = 261;
    public static final int j = 274;
    public static final int k = 276;
    public static final int l = 277;
    public static final int m = 278;
    public static final int n = 3584;
    public static final int o = 4096;
    public static final int p = 4097;
    public static final int q = 4098;
    public static final int r = 4099;
    public static final int s = 8193;
    public static final int t = 8194;
    public static final int u = 8196;
    public static final int v = 8197;
    public static final int w = 8198;
    public static final int x = 8199;
    public static final int y = 8200;
    public static final int z = 8201;

    @NotNull
    public String a() {
        return "Sony Makernote";
    }

    static {
        ar.put(16, "Camera Info");
        ar.put(32, "Focus Info");
        ar.put(258, "Image Quality");
        ar.put(260, "Flash Exposure Compensation");
        ar.put(261, "Teleconverter Model");
        ar.put(274, "White Balance Fine Tune Value");
        ar.put(276, "Camera Settings");
        ar.put(277, "White Balance");
        ar.put(278, "Extra Info");
        ar.put(3584, "Print Image Matching (PIM) Info");
        ar.put(4096, "Multi Burst Mode");
        ar.put(4097, "Multi Burst Image Width");
        ar.put(4098, "Multi Burst Image Height");
        ar.put(4099, "Panorama");
        ar.put(8193, "Preview Image");
        ar.put(8194, "Rating");
        ar.put(Integer.valueOf(u), ExifInterface.TAG_CONTRAST);
        ar.put(Integer.valueOf(v), ExifInterface.TAG_SATURATION);
        ar.put(Integer.valueOf(w), ExifInterface.TAG_SHARPNESS);
        ar.put(Integer.valueOf(x), "Brightness");
        ar.put(Integer.valueOf(y), "Long Exposure Noise Reduction");
        ar.put(Integer.valueOf(z), "High ISO Noise Reduction");
        ar.put(Integer.valueOf(A), "HDR");
        ar.put(Integer.valueOf(B), "Multi Frame Noise Reduction");
        ar.put(Integer.valueOf(C), "Picture Effect");
        ar.put(Integer.valueOf(D), "Soft Skin Effect");
        ar.put(8209, "Vignetting Correction");
        ar.put(8210, "Lateral Chromatic Aberration");
        ar.put(Integer.valueOf(G), "Distortion Correction");
        ar.put(Integer.valueOf(H), "WB Shift Amber/Magenta");
        ar.put(Integer.valueOf(I), "Auto Portrait Framing");
        ar.put(Integer.valueOf(J), "Focus Mode");
        ar.put(Integer.valueOf(K), "AF Point Selected");
        ar.put(12288, "Shot Info");
        ar.put(Integer.valueOf(M), "File Format");
        ar.put(Integer.valueOf(N), "Sony Model ID");
        ar.put(Integer.valueOf(O), "Color Mode Setting");
        ar.put(Integer.valueOf(P), "Color Temperature");
        ar.put(Integer.valueOf(Q), "Color Compensation Filter");
        ar.put(Integer.valueOf(R), "Scene Mode");
        ar.put(Integer.valueOf(S), "Zone Matching");
        ar.put(Integer.valueOf(T), "Dynamic Range Optimizer");
        ar.put(Integer.valueOf(U), "Image Stabilisation");
        ar.put(Integer.valueOf(V), "Lens ID");
        ar.put(Integer.valueOf(W), "Minolta Makernote");
        ar.put(Integer.valueOf(X), "Color Mode");
        ar.put(Integer.valueOf(Y), "Lens Spec");
        ar.put(Integer.valueOf(Z), "Full Image Size");
        ar.put(Integer.valueOf(aa), "Preview Image Size");
        ar.put(Integer.valueOf(ab), "Macro");
        ar.put(Integer.valueOf(ac), "Exposure Mode");
        ar.put(Integer.valueOf(ad), "Focus Mode");
        ar.put(Integer.valueOf(ae), "AF Mode");
        ar.put(Integer.valueOf(af), "AF Illuminator");
        ar.put(Integer.valueOf(ag), "Quality");
        ar.put(Integer.valueOf(ah), "Flash Level");
        ar.put(Integer.valueOf(ai), "Release Mode");
        ar.put(Integer.valueOf(aj), "Sequence Number");
        ar.put(Integer.valueOf(ak), "Anti Blur");
        ar.put(Integer.valueOf(al), "Long Exposure Noise Reduction");
        ar.put(Integer.valueOf(am), "Dynamic Range Optimizer");
        ar.put(Integer.valueOf(an), "High ISO Noise Reduction");
        ar.put(Integer.valueOf(ao), "Intelligent Auto");
        ar.put(Integer.valueOf(ap), "White Balance 2");
        ar.put(65535, "No Print");
    }

    public SonyType1MakernoteDirectory() {
        a((TagDescriptor) new SonyType1MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ar;
    }
}
