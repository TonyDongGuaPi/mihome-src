package com.mi.global.shop.util;

import com.mi.global.shop.constants.UIConstant;
import com.mi.util.ScreenInfo;
import java.util.HashMap;

public class UIAdapter {
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static final int l = 10;
    public static final int m = 11;
    public static final int n = 12;
    public static final int o = 13;
    public static final int p = 14;
    public static final int q = 17;
    public static final int r = 18;
    public static final int s = 19;
    public static final int t = 20;
    public static final int u = 21;
    public static final int v = 22;
    public static final int w = 23;
    public static final int x = 24;
    public static final int y = 25;
    public static final int z = 26;
    private float A;
    private int B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private float H;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<Integer, Float> f7113a;

    public static class SingletonHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static UIAdapter f7114a = new UIAdapter();
    }

    private UIAdapter() {
        this.A = 3.0f;
        this.B = 480;
        this.C = 1080.0f;
        this.D = this.C / this.A;
    }

    public static UIAdapter a() {
        return SingletonHolder.f7114a;
    }

    public void b() {
        this.E = ScreenInfo.a().d();
        this.F = (float) ScreenInfo.a().e();
        this.G = (float) ScreenInfo.a().b();
        this.H = this.G / this.E;
        c();
    }

    private void c() {
        if (this.f7113a == null) {
            this.f7113a = new HashMap<>();
        }
        this.f7113a.put(0, Float.valueOf(e(540)));
        this.f7113a.put(3, Float.valueOf(d(370)));
        this.f7113a.put(4, Float.valueOf(d(540)));
        this.f7113a.put(5, Float.valueOf(e(720)));
        this.f7113a.put(6, Float.valueOf(e(1020)));
        this.f7113a.put(7, Float.valueOf(e(570)));
        this.f7113a.put(8, Float.valueOf(e(1020)));
        this.f7113a.put(9, Float.valueOf(e(480)));
        this.f7113a.put(10, Float.valueOf(e(UIConstant.h)));
        this.f7113a.put(11, Float.valueOf(e(540)));
        this.f7113a.put(12, Float.valueOf(e(UIConstant.k)));
        this.f7113a.put(13, Float.valueOf(e(360)));
        this.f7113a.put(14, Float.valueOf(e(360)));
        this.f7113a.put(18, Float.valueOf(e(1589)));
        this.f7113a.put(17, Float.valueOf(e(1080)));
        this.f7113a.put(20, Float.valueOf(e(UIConstant.q)));
        this.f7113a.put(19, Float.valueOf(e(1080)));
        this.f7113a.put(21, Float.valueOf(e(480)));
        this.f7113a.put(22, Float.valueOf(e(370)));
        this.f7113a.put(23, Float.valueOf(e(180)));
        this.f7113a.put(24, Float.valueOf(e(810)));
        this.f7113a.put(25, Float.valueOf(e(UIConstant.t)));
        this.f7113a.put(26, Float.valueOf(e(210)));
    }

    private int c(int i2) {
        return (int) ((((float) i2) * this.E) + 0.5f);
    }

    private float d(int i2) {
        return ((((float) i2) * this.A) * this.G) / this.C;
    }

    private float e(int i2) {
        return (((float) i2) / this.C) * this.G;
    }

    public int a(int i2) {
        return (int) (this.f7113a.get(Integer.valueOf(i2)).floatValue() + 0.5f);
    }

    public int b(int i2) {
        return (int) (this.G - (((float) i2) * this.E));
    }
}
