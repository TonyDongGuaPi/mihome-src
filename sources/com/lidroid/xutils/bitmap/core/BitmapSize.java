package com.lidroid.xutils.bitmap.core;

import com.taobao.weex.annotation.JSMethod;

public class BitmapSize {

    /* renamed from: a  reason: collision with root package name */
    public static final BitmapSize f6302a = new BitmapSize(0, 0);
    private final int b;
    private final int c;

    public BitmapSize(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public BitmapSize a(int i) {
        return new BitmapSize(this.b / i, this.c / i);
    }

    public BitmapSize a(float f) {
        return new BitmapSize((int) (((float) this.b) * f), (int) (((float) this.c) * f));
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public String toString() {
        return JSMethod.NOT_SET + this.b + JSMethod.NOT_SET + this.c;
    }
}
