package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusImageProcessingMakernoteDirectory extends Directory {
    public static final int A = 279;
    public static final int B = 280;
    public static final int C = 281;
    public static final int D = 282;
    public static final int E = 283;
    public static final int F = 284;
    public static final int G = 285;
    public static final int H = 286;
    public static final int I = 287;
    public static final int J = 512;
    public static final int K = 768;
    public static final int L = 769;
    public static final int M = 784;
    public static final int N = 785;
    public static final int O = 1536;
    public static final int P = 1552;
    public static final int Q = 1553;
    public static final int R = 1554;
    public static final int S = 1555;
    public static final int T = 1556;
    public static final int U = 1557;
    public static final int V = 1589;
    public static final int W = 1590;
    public static final int X = 2053;
    public static final int Y = 4112;
    public static final int Z = 4113;
    public static final int aa = 4114;
    public static final int ab = 4124;
    public static final int ac = 4355;
    public static final int ad = 4356;
    public static final int ae = 4370;
    public static final int af = 4371;
    public static final int ag = 4608;
    public static final int ah = 4609;
    public static final int ai = 4610;
    public static final int aj = 4611;
    public static final int ak = 4615;
    public static final int al = 4870;
    public static final int am = 6400;
    public static final int an = 6401;
    public static final int ao = 6406;
    @NotNull
    protected static final HashMap<Integer, String> ap = new HashMap<>();
    public static final int e = 0;
    public static final int f = 256;
    public static final int g = 258;
    public static final int h = 259;
    public static final int i = 260;
    public static final int j = 261;
    public static final int k = 262;
    public static final int l = 263;
    public static final int m = 264;
    public static final int n = 265;
    public static final int o = 266;
    public static final int p = 267;
    public static final int q = 268;
    public static final int r = 269;
    public static final int s = 270;
    public static final int t = 271;
    public static final int u = 272;
    public static final int v = 273;
    public static final int w = 275;
    public static final int x = 276;
    public static final int y = 277;
    public static final int z = 278;

    @NotNull
    public String a() {
        return "Olympus Image Processing";
    }

    static {
        ap.put(0, "Image Processing Version");
        ap.put(256, "WB RB Levels");
        ap.put(258, "WB RB Levels 3000K");
        ap.put(259, "WB RB Levels 3300K");
        ap.put(260, "WB RB Levels 3600K");
        ap.put(261, "WB RB Levels 3900K");
        ap.put(262, "WB RB Levels 4000K");
        ap.put(263, "WB RB Levels 4300K");
        ap.put(264, "WB RB Levels 4500K");
        ap.put(265, "WB RB Levels 4800K");
        ap.put(266, "WB RB Levels 5300K");
        ap.put(267, "WB RB Levels 6000K");
        ap.put(268, "WB RB Levels 6600K");
        ap.put(269, "WB RB Levels 7500K");
        ap.put(270, "WB RB Levels CWB1");
        ap.put(271, "WB RB Levels CWB2");
        ap.put(272, "WB RB Levels CWB3");
        ap.put(273, "WB RB Levels CWB4");
        ap.put(275, "WB G Level 3000K");
        ap.put(276, "WB G Level 3300K");
        ap.put(277, "WB G Level 3600K");
        ap.put(278, "WB G Level 3900K");
        ap.put(279, "WB G Level 4000K");
        ap.put(280, "WB G Level 4300K");
        ap.put(281, "WB G Level 4500K");
        ap.put(282, "WB G Level 4800K");
        ap.put(283, "WB G Level 5300K");
        ap.put(284, "WB G Level 6000K");
        ap.put(285, "WB G Level 6600K");
        ap.put(286, "WB G Level 7500K");
        ap.put(Integer.valueOf(I), "WB G Level");
        ap.put(512, "Color Matrix");
        ap.put(768, "Enhancer");
        ap.put(769, "Enhancer Values");
        ap.put(784, "Coring Filter");
        ap.put(785, "Coring Values");
        ap.put(1536, "Black Level 2");
        ap.put(Integer.valueOf(P), "Gain Base");
        ap.put(1553, "Valid Bits");
        ap.put(1554, "Crop Left");
        ap.put(1555, "Crop Top");
        ap.put(1556, "Crop Width");
        ap.put(1557, "Crop Height");
        ap.put(1589, "Unknown Block 1");
        ap.put(Integer.valueOf(W), "Unknown Block 2");
        ap.put(Integer.valueOf(X), "Sensor Calibration");
        ap.put(4112, "Noise Reduction 2");
        ap.put(4113, "Distortion Correction 2");
        ap.put(4114, "Shading Compensation 2");
        ap.put(4124, "Multiple Exposure Mode");
        ap.put(Integer.valueOf(ac), "Unknown Block 3");
        ap.put(Integer.valueOf(ad), "Unknown Block 4");
        ap.put(Integer.valueOf(ae), "Aspect Ratio");
        ap.put(Integer.valueOf(af), "Aspect Frame");
        ap.put(Integer.valueOf(ag), "Faces Detected");
        ap.put(4609, "Face Detect Area");
        ap.put(Integer.valueOf(ai), "Max Faces");
        ap.put(4611, "Face Detect Frame Size");
        ap.put(Integer.valueOf(ak), "Face Detect Frame Crop");
        ap.put(Integer.valueOf(al), "Camera Temperature");
        ap.put(Integer.valueOf(am), "Keystone Compensation");
        ap.put(Integer.valueOf(an), "Keystone Direction");
        ap.put(Integer.valueOf(ao), "Keystone Value");
    }

    public OlympusImageProcessingMakernoteDirectory() {
        a((TagDescriptor) new OlympusImageProcessingMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return ap;
    }
}
