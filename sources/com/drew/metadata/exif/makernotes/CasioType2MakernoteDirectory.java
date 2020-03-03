package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class CasioType2MakernoteDirectory extends Directory {
    public static final int A = 12294;
    public static final int B = 12295;
    public static final int C = 12308;
    public static final int D = 12309;
    public static final int E = 12310;
    public static final int F = 12311;
    @NotNull
    protected static final HashMap<Integer, String> G = new HashMap<>();
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 13;
    public static final int k = 20;
    public static final int l = 25;
    public static final int m = 29;
    public static final int n = 31;
    public static final int o = 32;
    public static final int p = 33;
    public static final int q = 3584;
    public static final int r = 8192;
    public static final int s = 8209;
    public static final int t = 8210;
    public static final int u = 8226;
    public static final int v = 8244;
    public static final int w = 12288;
    public static final int x = 12289;
    public static final int y = 12290;
    public static final int z = 12291;

    @NotNull
    public String a() {
        return "Casio Makernote";
    }

    static {
        G.put(2, "Thumbnail Dimensions");
        G.put(3, "Thumbnail Size");
        G.put(4, "Thumbnail Offset");
        G.put(8, "Quality Mode");
        G.put(9, "Image Size");
        G.put(13, "Focus Mode");
        G.put(20, "ISO Sensitivity");
        G.put(25, "White Balance");
        G.put(29, "Focal Length");
        G.put(31, ExifInterface.TAG_SATURATION);
        G.put(32, ExifInterface.TAG_CONTRAST);
        G.put(33, ExifInterface.TAG_SHARPNESS);
        G.put(3584, "Print Image Matching (PIM) Info");
        G.put(8192, "Casio Preview Thumbnail");
        G.put(8209, "White Balance Bias");
        G.put(8210, "White Balance");
        G.put(8226, "Object Distance");
        G.put(Integer.valueOf(v), "Flash Distance");
        G.put(12288, "Record Mode");
        G.put(12289, "Self Timer");
        G.put(12290, "Quality");
        G.put(12291, "Focus Mode");
        G.put(12294, "Time Zone");
        G.put(12295, "BestShot Mode");
        G.put(12308, "CCD ISO Sensitivity");
        G.put(12309, "Colour Mode");
        G.put(12310, "Enhancement");
        G.put(Integer.valueOf(F), "Filter");
    }

    public CasioType2MakernoteDirectory() {
        a((TagDescriptor) new CasioType2MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return G;
    }
}
