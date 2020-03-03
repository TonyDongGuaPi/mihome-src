package com.bumptech.glide.gifdecoder;

import android.support.annotation.ColorInt;
import java.util.ArrayList;
import java.util.List;

public class GifHeader {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4831a = 0;
    public static final int b = -1;
    @ColorInt
    int[] c = null;
    int d = 0;
    int e = 0;
    GifFrame f;
    final List<GifFrame> g = new ArrayList();
    int h;
    int i;
    boolean j;
    int k;
    int l;
    int m;
    @ColorInt
    int n;
    int o = -1;

    public int a() {
        return this.i;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.d;
    }
}
