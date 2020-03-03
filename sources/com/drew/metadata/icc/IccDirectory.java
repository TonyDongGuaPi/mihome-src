package com.drew.metadata.icc;

import android.support.media.ExifInterface;
import com.alipay.sdk.packet.e;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class IccDirectory extends Directory {
    public static final int A = 1110589744;
    public static final int B = 1110589745;
    public static final int C = 1110589746;
    public static final int D = 1667329140;
    public static final int E = 1952543335;
    public static final int F = 1667785060;
    public static final int G = 1667789421;
    public static final int H = 1668313716;
    public static final int I = 1668441193;
    public static final int J = 1684893284;
    public static final int K = 1684890724;
    public static final int L = 1684371059;
    public static final int M = 1734438260;
    public static final int N = 1800688195;
    public static final int O = 1733843290;
    public static final int P = 1733579331;
    public static final int Q = 1819635049;
    public static final int R = 1835360627;
    public static final int S = 1651208308;
    public static final int T = 2004119668;
    public static final int U = 1852010348;
    public static final int V = 1852009522;
    public static final int W = 1919251312;
    public static final int X = 1886545200;
    public static final int Y = 1886545201;
    public static final int Z = 1886545202;
    public static final int aa = 1684370275;
    public static final int ab = 1886610801;
    public static final int ac = 1886610480;
    public static final int ad = 1886610481;
    public static final int ae = 1886610482;
    public static final int af = 1886610483;
    public static final int ag = 1886597747;
    public static final int ah = 1886597737;
    public static final int ai = 1918392666;
    public static final int aj = 1918128707;
    public static final int ak = 1935897188;
    public static final int al = 1935897198;
    public static final int am = 1952801640;
    public static final int an = 1650877472;
    public static final int ao = 1987405156;
    public static final int ap = 1986618743;
    public static final int aq = 1685283693;
    @NotNull
    protected static final HashMap<Integer, String> ar = new HashMap<>();
    public static final int e = 0;
    public static final int f = 4;
    public static final int g = 8;
    public static final int h = 12;
    public static final int i = 16;
    public static final int j = 20;
    public static final int k = 24;
    public static final int l = 36;
    public static final int m = 40;
    public static final int n = 44;
    public static final int o = 48;
    public static final int p = 52;
    public static final int q = 56;
    public static final int r = 64;
    public static final int s = 68;
    public static final int t = 80;
    public static final int u = 128;
    public static final int v = 1093812784;
    public static final int w = 1093812785;
    public static final int x = 1093812786;
    public static final int y = 1649957210;
    public static final int z = 1649693251;

    @NotNull
    public String a() {
        return "ICC Profile";
    }

    static {
        ar.put(0, "Profile Size");
        ar.put(4, "CMM Type");
        ar.put(8, e.e);
        ar.put(12, "Class");
        ar.put(16, "Color space");
        ar.put(20, "Profile Connection Space");
        ar.put(24, "Profile Date/Time");
        ar.put(36, "Signature");
        ar.put(40, "Primary Platform");
        ar.put(44, "CMM Flags");
        ar.put(48, "Device manufacturer");
        ar.put(52, "Device model");
        ar.put(56, "Device attributes");
        ar.put(64, "Rendering Intent");
        ar.put(68, "XYZ values");
        ar.put(80, "Profile Creator");
        ar.put(128, "Tag Count");
        ar.put(Integer.valueOf(v), "AToB 0");
        ar.put(Integer.valueOf(w), "AToB 1");
        ar.put(Integer.valueOf(x), "AToB 2");
        ar.put(Integer.valueOf(y), "Blue Colorant");
        ar.put(Integer.valueOf(z), "Blue TRC");
        ar.put(Integer.valueOf(A), "BToA 0");
        ar.put(Integer.valueOf(B), "BToA 1");
        ar.put(Integer.valueOf(C), "BToA 2");
        ar.put(Integer.valueOf(D), "Calibration Date/Time");
        ar.put(Integer.valueOf(E), "Char Target");
        ar.put(Integer.valueOf(F), "Chromatic Adaptation");
        ar.put(Integer.valueOf(G), "Chromaticity");
        ar.put(Integer.valueOf(H), ExifInterface.TAG_COPYRIGHT);
        ar.put(Integer.valueOf(I), "CrdInfo");
        ar.put(Integer.valueOf(J), "Device Mfg Description");
        ar.put(Integer.valueOf(K), "Device Model Description");
        ar.put(Integer.valueOf(L), "Device Settings");
        ar.put(Integer.valueOf(M), "Gamut");
        ar.put(Integer.valueOf(N), "Gray TRC");
        ar.put(Integer.valueOf(O), "Green Colorant");
        ar.put(Integer.valueOf(P), "Green TRC");
        ar.put(Integer.valueOf(Q), "Luminance");
        ar.put(Integer.valueOf(R), "Measurement");
        ar.put(Integer.valueOf(S), "Media Black Point");
        ar.put(Integer.valueOf(T), "Media White Point");
        ar.put(Integer.valueOf(U), "Named Color");
        ar.put(Integer.valueOf(V), "Named Color 2");
        ar.put(Integer.valueOf(W), "Output Response");
        ar.put(Integer.valueOf(X), "Preview 0");
        ar.put(Integer.valueOf(Y), "Preview 1");
        ar.put(Integer.valueOf(Z), "Preview 2");
        ar.put(Integer.valueOf(aa), "Profile Description");
        ar.put(Integer.valueOf(ab), "Profile Sequence Description");
        ar.put(Integer.valueOf(ac), "Ps2 CRD 0");
        ar.put(Integer.valueOf(ad), "Ps2 CRD 1");
        ar.put(Integer.valueOf(ae), "Ps2 CRD 2");
        ar.put(Integer.valueOf(af), "Ps2 CRD 3");
        ar.put(Integer.valueOf(ag), "Ps2 CSA");
        ar.put(Integer.valueOf(ah), "Ps2 Rendering Intent");
        ar.put(Integer.valueOf(ai), "Red Colorant");
        ar.put(Integer.valueOf(aj), "Red TRC");
        ar.put(Integer.valueOf(ak), "Screening Desc");
        ar.put(Integer.valueOf(al), "Screening");
        ar.put(Integer.valueOf(am), "Technology");
        ar.put(Integer.valueOf(an), "Ucrbg");
        ar.put(Integer.valueOf(ao), "Viewing Conditions Description");
        ar.put(Integer.valueOf(ap), "Viewing Conditions");
        ar.put(Integer.valueOf(aq), "Apple Multi-language Profile Name");
    }

    public IccDirectory() {
        a((TagDescriptor) new IccDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ar;
    }
}
