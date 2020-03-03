package com.mi.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.mi.log.LogUtil;

public class ScreenInfo {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1352a = "ScreenInfo";
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;

    public static class SingletonHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static ScreenInfo f7420a = new ScreenInfo();
    }

    private ScreenInfo() {
    }

    public static ScreenInfo a() {
        return SingletonHolder.f7420a;
    }

    public void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.b = displayMetrics.widthPixels;
        this.c = displayMetrics.heightPixels;
        this.f = displayMetrics.density;
        this.g = displayMetrics.densityDpi;
        this.d = (int) ((((float) displayMetrics.widthPixels) / this.f) + 0.5f);
        this.e = (int) ((((float) displayMetrics.heightPixels) / this.f) + 0.5f);
        String str = f1352a;
        LogUtil.b(str, "屏幕高度px:" + this.c + ",屏幕宽度px:" + this.b + ",Density:" + this.f + ",dpi:" + this.g + ",屏幕高度dip:" + this.e + ",屏幕宽度dip:" + this.d);
        LogUtil.b(f1352a, displayMetrics.toString());
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public float d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }
}
