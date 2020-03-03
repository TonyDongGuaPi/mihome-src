package com.xiaomi.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class ScreenInfo {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f10035a = true;
    private static final String b = "ScreenInfo";
    private float c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public static ScreenInfo a() {
        return SingletonHolder.f10036a;
    }

    public float b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.g;
    }

    public void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            this.g = i;
            this.e = i2;
        } else {
            this.g = i2;
            this.e = i;
        }
        this.c = displayMetrics.density;
        this.d = displayMetrics.densityDpi;
        this.h = (int) ((((float) displayMetrics.widthPixels) / this.c) + 0.5f);
        this.f = (int) ((((float) displayMetrics.heightPixels) / this.c) + 0.5f);
        String str = b;
        Log.d(str, "屏幕高度px:" + this.e + ",屏幕宽度px:" + this.g + ",Density:" + this.c + ",dpi:" + this.d + ",屏幕高度dip:" + this.f + ",屏幕宽度dip:" + this.h);
        Log.d(b, displayMetrics.toString());
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static ScreenInfo f10036a = new ScreenInfo();

        private SingletonHolder() {
        }
    }
}
