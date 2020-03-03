package com.libra.virtualview.common;

import com.libra.Color;
import com.libra.Log;
import com.libra.TextUtils;

public class Common {
    public static final int A = 14;
    public static final int B = 15;
    public static final int C = 16;
    public static final int D = 17;
    public static final int E = 18;
    public static final int F = 19;
    public static final int G = 20;
    public static final int H = 21;
    public static final int I = 22;
    public static final int J = 23;
    public static final int K = 24;
    public static final int L = 25;
    public static final int M = 26;
    public static final int N = 27;
    public static final int O = 28;
    public static final int P = 29;

    /* renamed from: a  reason: collision with root package name */
    public static final String f6266a = "ALIVV";
    public static final byte b = 0;
    public static final byte c = 1;
    public static final int d = 1;
    public static final int e = 0;
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 3;
    public static final int j = 20;
    public static final int k = 1024;
    public static final int l = 1023;
    public static final int m = 1000;
    public static final int n = 1;
    public static final int o = 2;
    public static final int p = 3;
    public static final int q = 4;
    public static final int r = 5;
    public static final int s = 6;
    public static final int t = 7;
    public static final int u = 8;
    public static final int v = 9;
    public static final int w = 10;
    public static final int x = 11;
    public static final int y = 12;
    public static final int z = 13;

    public static boolean a(DataItem dataItem) {
        if (dataItem == null || TextUtils.a(dataItem.f6267a)) {
            Log.e(f6266a, "parseInteger value invalidate:" + dataItem);
        } else {
            try {
                dataItem.a(Integer.parseInt(dataItem.f6267a));
                return true;
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean b(DataItem dataItem) {
        int i2;
        if (TextUtils.a(dataItem.f6267a)) {
            return false;
        }
        try {
            if (TextUtils.a("black", dataItem.f6267a)) {
                i2 = -16777216;
            } else if (TextUtils.a("blue", dataItem.f6267a)) {
                i2 = Color.h;
            } else if (TextUtils.a("cyan", dataItem.f6267a)) {
                i2 = Color.j;
            } else if (TextUtils.a("dkgray", dataItem.f6267a)) {
                i2 = Color.b;
            } else if (TextUtils.a("gray", dataItem.f6267a)) {
                i2 = Color.c;
            } else if (TextUtils.a("green", dataItem.f6267a)) {
                i2 = Color.g;
            } else if (TextUtils.a("ltgray", dataItem.f6267a)) {
                i2 = Color.d;
            } else {
                if (!TextUtils.a("magenta", dataItem.f6267a)) {
                    if (!TextUtils.a("magenta", dataItem.f6267a)) {
                        if (TextUtils.a("red", dataItem.f6267a)) {
                            i2 = -65536;
                        } else if (TextUtils.a("transparent", dataItem.f6267a)) {
                            i2 = 0;
                        } else if (TextUtils.a("yellow", dataItem.f6267a)) {
                            i2 = -256;
                        } else {
                            i2 = Color.a(dataItem.f6267a);
                        }
                    }
                }
                i2 = Color.k;
            }
            dataItem.a(i2);
            return true;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
