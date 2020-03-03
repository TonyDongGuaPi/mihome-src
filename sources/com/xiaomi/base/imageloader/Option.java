package com.xiaomi.base.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.taobao.weex.el.parse.Operators;

public class Option {

    /* renamed from: a  reason: collision with root package name */
    public static Option f10012a = new Option().a(Strategy.ALL);
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public boolean e = false;
    public boolean f = false;
    public ImageView.ScaleType g;
    private int h;
    private Drawable i;
    private int j;
    private Drawable k;
    private Strategy l;
    private int m;
    private boolean n;

    public enum Strategy {
        ALL,
        NONE,
        AUTOMATIC
    }

    public Option a(ImageView.ScaleType scaleType) {
        this.g = scaleType;
        return this;
    }

    public Option a(boolean z) {
        this.f = z;
        return this;
    }

    public Option a(int i2) {
        this.m = i2;
        return this;
    }

    public int a() {
        return this.m;
    }

    public Option b(boolean z) {
        this.n = z;
        return this;
    }

    public boolean b() {
        return this.n;
    }

    public Option b(int i2) {
        this.h = i2;
        return this;
    }

    public Option a(Drawable drawable) {
        this.i = drawable;
        return this;
    }

    public Option c(int i2) {
        this.j = i2;
        return this;
    }

    public Option b(Drawable drawable) {
        this.k = drawable;
        return this;
    }

    public Option a(Strategy strategy) {
        this.l = strategy;
        return this;
    }

    public int c() {
        return this.h;
    }

    public Drawable d() {
        return this.i;
    }

    public int e() {
        return this.j;
    }

    public Drawable f() {
        return this.k;
    }

    public Strategy g() {
        return this.l;
    }

    public String toString() {
        return "Option{placeholderRes=" + this.h + ", errorRes=" + this.j + ", strategy=" + this.l + Operators.BLOCK_END;
    }
}
