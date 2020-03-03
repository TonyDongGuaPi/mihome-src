package com.megvii.livenessdetection.obf;

import android.util.Log;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f6689a = false;
    private static String b = "MegviiSDK";

    public static void a() {
        f6689a = true;
    }

    public static void b() {
        f6689a = false;
    }

    public static void a(String str) {
        b(b, str);
    }

    public static void b(String str) {
        a(b, str);
    }

    public static void a(String str, String str2) {
        if (f6689a) {
            if (str == null) {
                str = b;
            }
            if (str2 == null) {
                str2 = "";
            }
            Log.e(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (f6689a) {
            if (str == null) {
                str = b;
            }
            if (str2 == null) {
                str2 = "";
            }
            Log.d(str, str2);
        }
    }
}
