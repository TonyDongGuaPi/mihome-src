package com.mi.global.shop.widget.ptr.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class PtrLocalDisplay {

    /* renamed from: a  reason: collision with root package name */
    public static int f7270a;
    public static int b;
    public static float c;
    public static int d;
    public static int e;
    private static boolean f;

    public static void a(Context context) {
        if (!f && context != null) {
            f = true;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            f7270a = displayMetrics.widthPixels;
            b = displayMetrics.heightPixels;
            c = displayMetrics.density;
            d = (int) (((float) f7270a) / displayMetrics.density);
            e = (int) (((float) b) / displayMetrics.density);
        }
    }

    public static int a(float f2) {
        return (int) ((f2 * c) + 0.5f);
    }

    public static int b(float f2) {
        if (d != 320) {
            f2 = (f2 * ((float) d)) / 320.0f;
        }
        return a(f2);
    }

    public static void a(View view, float f2, float f3, float f4, float f5) {
        view.setPadding(b(f2), a(f3), b(f4), a(f5));
    }
}
