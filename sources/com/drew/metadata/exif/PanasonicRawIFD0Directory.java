package com.drew.metadata.exif;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class PanasonicRawIFD0Directory extends Directory {
    public static final int A = 30;
    public static final int B = 36;
    public static final int C = 37;
    public static final int D = 38;
    public static final int E = 39;
    public static final int F = 46;
    public static final int G = 47;
    public static final int H = 48;
    public static final int I = 49;
    public static final int J = 50;
    public static final int K = 271;
    public static final int L = 272;
    public static final int M = 273;
    public static final int N = 274;
    public static final int O = 278;
    public static final int P = 279;
    public static final int Q = 280;
    public static final int R = 281;
    @NotNull
    protected static final HashMap<Integer, String> S = new HashMap<>();
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 14;
    public static final int p = 15;
    public static final int q = 16;
    public static final int r = 17;
    public static final int s = 18;
    public static final int t = 19;
    public static final int u = 23;
    public static final int v = 24;
    public static final int w = 25;
    public static final int x = 26;
    public static final int y = 28;
    public static final int z = 29;

    @NotNull
    public String a() {
        return "PanasonicRaw Exif IFD0";
    }

    public PanasonicRawIFD0Directory() {
        a((TagDescriptor) new PanasonicRawIFD0Descriptor(this));
    }

    static {
        S.put(1, "Panasonic Raw Version");
        S.put(2, "Sensor Width");
        S.put(3, "Sensor Height");
        S.put(4, "Sensor Top Border");
        S.put(5, "Sensor Left Border");
        S.put(6, "Sensor Bottom Border");
        S.put(7, "Sensor Right Border");
        S.put(8, "Black Level 1");
        S.put(9, "Black Level 2");
        S.put(10, "Black Level 3");
        S.put(14, "Linearity Limit Red");
        S.put(15, "Linearity Limit Green");
        S.put(16, "Linearity Limit Blue");
        S.put(17, "Red Balance");
        S.put(18, "Blue Balance");
        S.put(23, ExifInterface.TAG_RW2_ISO);
        S.put(24, "High ISO Multiplier Red");
        S.put(25, "High ISO Multiplier Green");
        S.put(26, "High ISO Multiplier Blue");
        S.put(28, "Black Level Red");
        S.put(29, "Black Level Green");
        S.put(30, "Black Level Blue");
        S.put(36, "WB Red Level");
        S.put(37, "WB Green Level");
        S.put(38, "WB Blue Level");
        S.put(46, "Jpg From Raw");
        S.put(47, "Crop Top");
        S.put(48, "Crop Left");
        S.put(49, "Crop Bottom");
        S.put(50, "Crop Right");
        S.put(271, ExifInterface.TAG_MAKE);
        S.put(272, ExifInterface.TAG_MODEL);
        S.put(273, "Strip Offsets");
        S.put(274, ExifInterface.TAG_ORIENTATION);
        S.put(278, "Rows Per Strip");
        S.put(279, "Strip Byte Counts");
        S.put(280, "Raw Data Offset");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return S;
    }
}
