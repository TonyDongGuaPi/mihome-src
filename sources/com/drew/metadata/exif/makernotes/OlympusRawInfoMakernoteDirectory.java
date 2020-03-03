package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusRawInfoMakernoteDirectory extends Directory {
    public static final int A = 4096;
    public static final int B = 4097;
    public static final int C = 4112;
    public static final int D = 4113;
    public static final int E = 4114;
    public static final int F = 4115;
    public static final int G = 8192;
    public static final int H = 8193;
    public static final int I = 8194;
    public static final int J = 8208;
    public static final int K = 8224;
    public static final int L = 8225;
    public static final int M = 8226;
    public static final int N = 8227;
    @NotNull
    protected static final HashMap<Integer, String> O = new HashMap<>();
    public static final int e = 0;
    public static final int f = 256;
    public static final int g = 272;
    public static final int h = 288;
    public static final int i = 289;
    public static final int j = 290;
    public static final int k = 291;
    public static final int l = 292;
    public static final int m = 304;
    public static final int n = 305;
    public static final int o = 306;
    public static final int p = 307;
    public static final int q = 512;
    public static final int r = 784;
    public static final int s = 785;
    public static final int t = 1536;
    public static final int u = 1537;
    public static final int v = 1553;
    public static final int w = 1554;
    public static final int x = 1555;
    public static final int y = 1556;
    public static final int z = 1557;

    @NotNull
    public String a() {
        return "Olympus Raw Info";
    }

    static {
        O.put(0, "Raw Info Version");
        O.put(256, "WB RB Levels Used");
        O.put(272, "WB RB Levels Auto");
        O.put(288, "WB RB Levels Shade");
        O.put(289, "WB RB Levels Cloudy");
        O.put(Integer.valueOf(j), "WB RB Levels Fine Weather");
        O.put(291, "WB RB Levels Tungsten");
        O.put(Integer.valueOf(l), "WB RB Levels Evening Sunlight");
        O.put(304, "WB RB Levels Daylight Fluor");
        O.put(305, "WB RB Levels Day White Fluor");
        O.put(306, "WB RB Levels Cool White Fluor");
        O.put(307, "WB RB Levels White Fluorescent");
        O.put(512, "Color Matrix 2");
        O.put(784, "Coring Filter");
        O.put(785, "Coring Values");
        O.put(1536, "Black Level 2");
        O.put(1537, ExifInterface.TAG_Y_CB_CR_COEFFICIENTS);
        O.put(1553, "Valid Pixel Depth");
        O.put(1554, "Crop Left");
        O.put(1555, "Crop Top");
        O.put(1556, "Crop Width");
        O.put(1557, "Crop Height");
        O.put(4096, "Light Source");
        O.put(4097, "White Balance Comp");
        O.put(4112, "Saturation Setting");
        O.put(4113, "Hue Setting");
        O.put(4114, "Contrast Setting");
        O.put(4115, "Sharpness Setting");
        O.put(8192, "CM Exposure Compensation");
        O.put(8193, "CM White Balance");
        O.put(8194, "CM White Balance Comp");
        O.put(8208, "CM White Balance Gray Point");
        O.put(8224, "CM Saturation");
        O.put(Integer.valueOf(L), "CM Hue");
        O.put(8226, "CM Contrast");
        O.put(Integer.valueOf(N), "CM Sharpness");
    }

    public OlympusRawInfoMakernoteDirectory() {
        a((TagDescriptor) new OlympusRawInfoMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return O;
    }
}
