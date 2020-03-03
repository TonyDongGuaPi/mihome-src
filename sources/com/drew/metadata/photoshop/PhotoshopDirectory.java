package com.drew.metadata.photoshop;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.HashMap;

public class PhotoshopDirectory extends Directory {
    public static final int A = 1026;
    public static final int B = 1028;
    public static final int C = 1029;
    public static final int D = 1030;
    public static final int E = 1032;
    public static final int F = 1033;
    public static final int G = 1034;
    public static final int H = 1035;
    public static final int I = 1036;
    public static final int J = 1037;
    public static final int K = 1039;
    public static final int L = 1040;
    public static final int M = 1041;
    public static final int N = 1042;
    public static final int O = 1043;
    public static final int P = 1044;
    public static final int Q = 1045;
    public static final int R = 1046;
    public static final int S = 1047;
    public static final int T = 1049;
    public static final int U = 1050;
    public static final int V = 1051;
    public static final int W = 1052;
    public static final int X = 1053;
    public static final int Y = 1054;
    public static final int Z = 1057;
    public static final int aA = 1088;
    public static final int aB = 2999;
    public static final int aC = 3000;
    public static final int aD = 7000;
    public static final int aE = 7001;
    public static final int aF = 7002;
    public static final int aG = 7003;
    public static final int aH = 7004;
    public static final int aI = 7005;
    public static final int aJ = 7006;
    public static final int aK = 8000;
    public static final int aL = 10000;
    @NotNull
    protected static final HashMap<Integer, String> aM = new HashMap<>();
    public static final int aa = 1058;
    public static final int ab = 1059;
    public static final int ac = 1060;
    public static final int ad = 1061;
    public static final int ae = 1062;
    public static final int af = 1064;
    public static final int ag = 1065;
    public static final int ah = 1066;
    public static final int ai = 1067;
    public static final int aj = 1069;
    public static final int ak = 1070;
    public static final int al = 1071;
    public static final int am = 1072;
    public static final int an = 1073;
    public static final int ao = 1074;
    public static final int ap = 1075;
    public static final int aq = 1076;
    public static final int ar = 1077;
    public static final int as = 1078;
    public static final int at = 1080;
    public static final int au = 1082;
    public static final int av = 1083;
    public static final int aw = 1084;
    public static final int ax = 1085;
    public static final int ay = 1086;
    public static final int az = 1087;
    public static final int e = 1000;
    public static final int f = 1001;
    public static final int g = 1002;
    public static final int h = 1003;
    public static final int i = 1005;
    public static final int j = 1006;
    public static final int k = 1007;
    public static final int l = 1008;
    public static final int m = 1009;
    public static final int n = 1010;
    public static final int o = 1011;
    public static final int p = 1012;
    public static final int q = 1013;
    public static final int r = 1014;
    public static final int s = 1015;
    public static final int t = 1016;
    public static final int u = 1017;
    public static final int v = 1018;
    public static final int w = 1019;
    public static final int x = 1021;
    public static final int y = 1022;
    public static final int z = 1024;

    @NotNull
    public String a() {
        return "Photoshop";
    }

    static {
        aM.put(1000, "Channels, Rows, Columns, Depth, Mode");
        aM.put(1001, "Mac Print Info");
        aM.put(1002, "XML Data");
        aM.put(1003, "Indexed Color Table");
        aM.put(1005, "Resolution Info");
        aM.put(1006, "Alpha Channels");
        aM.put(1007, "Display Info (Obsolete)");
        aM.put(1008, "Caption");
        aM.put(1009, "Border Information");
        aM.put(1010, "Background Color");
        aM.put(1011, "Print Flags");
        aM.put(1012, "Grayscale and Multichannel Halftoning Information");
        aM.put(1013, "Color Halftoning Information");
        aM.put(1014, "Duotone Halftoning Information");
        aM.put(1015, "Grayscale and Multichannel Transfer Function");
        aM.put(1016, "Color Transfer Functions");
        aM.put(1017, "Duotone Transfer Functions");
        aM.put(1018, "Duotone Image Information");
        aM.put(1019, "Effective Black and White Values");
        aM.put(1021, "EPS Options");
        aM.put(1022, "Quick Mask Information");
        aM.put(1024, "Layer State Information");
        aM.put(1026, "Layers Group Information");
        aM.put(1028, "IPTC-NAA Record");
        aM.put(1029, "Image Mode for Raw Format Files");
        aM.put(1030, "JPEG Quality");
        aM.put(1032, "Grid and Guides Information");
        aM.put(Integer.valueOf(F), "Photoshop 4.0 Thumbnail");
        aM.put(Integer.valueOf(G), "Copyright Flag");
        aM.put(1035, DTransferConstants.f);
        aM.put(Integer.valueOf(I), "Thumbnail Data");
        aM.put(1037, "Global Angle");
        aM.put(Integer.valueOf(K), "ICC Profile Bytes");
        aM.put(1040, "Watermark");
        aM.put(1041, "ICC Untagged Profile");
        aM.put(1042, "Effects Visible");
        aM.put(1043, "Spot Halftone");
        aM.put(Integer.valueOf(P), "Seed Number");
        aM.put(Integer.valueOf(Q), "Unicode Alpha Names");
        aM.put(Integer.valueOf(R), "Indexed Color Table Count");
        aM.put(Integer.valueOf(S), "Transparency Index");
        aM.put(Integer.valueOf(T), "Global Altitude");
        aM.put(Integer.valueOf(U), "Slices");
        aM.put(1051, "Workflow URL");
        aM.put(1052, "Jump To XPEP");
        aM.put(Integer.valueOf(X), "Alpha Identifiers");
        aM.put(Integer.valueOf(Y), "URL List");
        aM.put(Integer.valueOf(Z), "Version Info");
        aM.put(Integer.valueOf(aa), "EXIF Data 1");
        aM.put(Integer.valueOf(ab), "EXIF Data 3");
        aM.put(Integer.valueOf(ac), "XMP Data");
        aM.put(1061, "Caption Digest");
        aM.put(Integer.valueOf(ae), "Print Scale");
        aM.put(Integer.valueOf(af), "Pixel Aspect Ratio");
        aM.put(Integer.valueOf(ag), "Layer Comps");
        aM.put(Integer.valueOf(ah), "Alternate Duotone Colors");
        aM.put(Integer.valueOf(ai), "Alternate Spot Colors");
        aM.put(Integer.valueOf(aj), "Layer Selection IDs");
        aM.put(Integer.valueOf(ak), "HDR Toning Info");
        aM.put(1071, "Print Info");
        aM.put(Integer.valueOf(am), "Layer Groups Enabled ID");
        aM.put(Integer.valueOf(an), "Color Samplers");
        aM.put(Integer.valueOf(ao), "Measurement Scale");
        aM.put(Integer.valueOf(ap), "Timeline Information");
        aM.put(Integer.valueOf(aq), "Sheet Disclosure");
        aM.put(Integer.valueOf(ar), "Display Info");
        aM.put(Integer.valueOf(as), "Onion Skins");
        aM.put(1080, "Count information");
        aM.put(Integer.valueOf(au), "Print Info 2");
        aM.put(Integer.valueOf(av), "Print Style");
        aM.put(Integer.valueOf(aw), "Mac NSPrintInfo");
        aM.put(Integer.valueOf(ax), "Win DEVMODE");
        aM.put(Integer.valueOf(ay), "Auto Save File Subpath");
        aM.put(1087, "Auto Save Format");
        aM.put(Integer.valueOf(aA), "Subpath Selection State");
        aM.put(Integer.valueOf(aB), "Clipping Path Name");
        aM.put(3000, "Origin Subpath Info");
        aM.put(7000, "Image Ready Variables XML");
        aM.put(7001, "Image Ready Data Sets");
        aM.put(7002, "Image Ready Selected State");
        aM.put(7003, "Image Ready 7 Rollover Expanded State");
        aM.put(7004, "Image Ready Rollover Expanded State");
        aM.put(7005, "Image Ready Save Layer Settings");
        aM.put(7006, "Image Ready Version");
        aM.put(8000, "Lightroom Workflow");
        aM.put(10000, "Print Flags Information");
    }

    public PhotoshopDirectory() {
        a((TagDescriptor) new PhotoshopDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return aM;
    }

    @Nullable
    public byte[] j() {
        byte[] g2 = g(I);
        if (g2 == null) {
            g2 = g(F);
        }
        if (g2 == null || g2.length <= 28) {
            return null;
        }
        int length = g2.length - 28;
        byte[] bArr = new byte[length];
        System.arraycopy(g2, 28, bArr, 0, length);
        return bArr;
    }
}
