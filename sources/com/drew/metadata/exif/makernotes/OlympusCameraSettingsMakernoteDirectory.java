package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusCameraSettingsMakernoteDirectory extends Directory {
    public static final int A = 1030;
    public static final int B = 1280;
    public static final int C = 1281;
    public static final int D = 1282;
    public static final int E = 1283;
    public static final int F = 1284;
    public static final int G = 1285;
    public static final int H = 1286;
    public static final int I = 1287;
    public static final int J = 1289;
    public static final int K = 1290;
    public static final int L = 1291;
    public static final int M = 1292;
    public static final int N = 1293;
    public static final int O = 1295;
    public static final int P = 1312;
    public static final int Q = 1313;
    public static final int R = 1314;
    public static final int S = 1315;
    public static final int T = 1316;
    public static final int U = 1317;
    public static final int V = 1318;
    public static final int W = 1319;
    public static final int X = 1321;
    public static final int Y = 1324;
    public static final int Z = 1325;
    public static final int aa = 1326;
    public static final int ab = 1327;
    public static final int ac = 1330;
    public static final int ad = 1536;
    public static final int ae = 1537;
    public static final int af = 1539;
    public static final int ag = 1540;
    public static final int ah = 2052;
    public static final int ai = 2304;
    public static final int aj = 2305;
    public static final int ak = 2306;
    public static final int al = 2307;
    public static final int am = 2308;
    public static final int an = 2312;
    @NotNull
    protected static final HashMap<Integer, String> ao = new HashMap<>();
    public static final int e = 0;
    public static final int f = 256;
    public static final int g = 257;
    public static final int h = 258;
    public static final int i = 512;
    public static final int j = 513;
    public static final int k = 514;
    public static final int l = 515;
    public static final int m = 516;
    public static final int n = 768;
    public static final int o = 769;
    public static final int p = 770;
    public static final int q = 771;
    public static final int r = 772;
    public static final int s = 773;
    public static final int t = 774;
    public static final int u = 775;
    public static final int v = 1024;
    public static final int w = 1025;
    public static final int x = 1027;
    public static final int y = 1028;
    public static final int z = 1029;

    @NotNull
    public String a() {
        return "Olympus Camera Settings";
    }

    static {
        ao.put(0, "Camera Settings Version");
        ao.put(256, "Preview Image Valid");
        ao.put(257, "Preview Image Start");
        ao.put(258, "Preview Image Length");
        ao.put(512, "Exposure Mode");
        ao.put(513, "AE Lock");
        ao.put(514, "Metering Mode");
        ao.put(515, "Exposure Shift");
        ao.put(516, "ND Filter");
        ao.put(768, "Macro Mode");
        ao.put(769, "Focus Mode");
        ao.put(770, "Focus Process");
        ao.put(771, "AF Search");
        ao.put(772, "AF Areas");
        ao.put(773, "AF Point Selected");
        ao.put(774, "AF Fine Tune");
        ao.put(Integer.valueOf(u), "AF Fine Tune Adj");
        ao.put(1024, "Flash Mode");
        ao.put(1025, "Flash Exposure Comp");
        ao.put(1027, "Flash Remote Control");
        ao.put(1028, "Flash Control Mode");
        ao.put(1029, "Flash Intensity");
        ao.put(1030, "Manual Flash Strength");
        ao.put(1280, "White Balance 2");
        ao.put(Integer.valueOf(C), "White Balance Temperature");
        ao.put(Integer.valueOf(D), "White Balance Bracket");
        ao.put(Integer.valueOf(E), "Custom Saturation");
        ao.put(Integer.valueOf(F), "Modified Saturation");
        ao.put(Integer.valueOf(G), "Contrast Setting");
        ao.put(Integer.valueOf(H), "Sharpness Setting");
        ao.put(Integer.valueOf(I), "Color Space");
        ao.put(Integer.valueOf(J), "Scene Mode");
        ao.put(Integer.valueOf(K), "Noise Reduction");
        ao.put(Integer.valueOf(L), "Distortion Correction");
        ao.put(Integer.valueOf(M), "Shading Compensation");
        ao.put(Integer.valueOf(N), "Compression Factor");
        ao.put(Integer.valueOf(O), "Gradation");
        ao.put(Integer.valueOf(P), "Picture Mode");
        ao.put(Integer.valueOf(Q), "Picture Mode Saturation");
        ao.put(Integer.valueOf(R), "Picture Mode Hue");
        ao.put(Integer.valueOf(S), "Picture Mode Contrast");
        ao.put(Integer.valueOf(T), "Picture Mode Sharpness");
        ao.put(Integer.valueOf(U), "Picture Mode BW Filter");
        ao.put(Integer.valueOf(V), "Picture Mode Tone");
        ao.put(Integer.valueOf(W), "Noise Filter");
        ao.put(Integer.valueOf(X), "Art Filter");
        ao.put(Integer.valueOf(Y), "Magic Filter");
        ao.put(Integer.valueOf(Z), "Picture Mode Effect");
        ao.put(Integer.valueOf(aa), "Tone Level");
        ao.put(Integer.valueOf(ab), "Art Filter Effect");
        ao.put(Integer.valueOf(ac), "Color Creator Effect");
        ao.put(1536, "Drive Mode");
        ao.put(1537, "Panorama Mode");
        ao.put(1539, "Image Quality 2");
        ao.put(1540, "Image Stabilization");
        ao.put(Integer.valueOf(ah), "Stacked Image");
        ao.put(Integer.valueOf(ai), "Manometer Pressure");
        ao.put(Integer.valueOf(aj), "Manometer Reading");
        ao.put(Integer.valueOf(ak), "Extended WB Detect");
        ao.put(Integer.valueOf(al), "Roll Angle");
        ao.put(Integer.valueOf(am), "Pitch Angle");
        ao.put(Integer.valueOf(an), "Date Time UTC");
    }

    public OlympusCameraSettingsMakernoteDirectory() {
        a((TagDescriptor) new OlympusCameraSettingsMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ao;
    }
}
