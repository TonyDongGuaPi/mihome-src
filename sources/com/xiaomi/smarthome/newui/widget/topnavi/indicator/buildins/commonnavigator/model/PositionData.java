package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model;

public class PositionData {

    /* renamed from: a  reason: collision with root package name */
    public int f20936a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public int a() {
        return this.c - this.f20936a;
    }

    public int b() {
        return this.d - this.b;
    }

    public int c() {
        return this.g - this.e;
    }

    public int d() {
        return this.h - this.f;
    }

    public int e() {
        return this.f20936a + (a() / 2);
    }

    public int f() {
        return this.b + (b() / 2);
    }
}
