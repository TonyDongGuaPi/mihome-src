package com.xiaomi.base.utils;

public class SpringConfig {

    /* renamed from: a  reason: collision with root package name */
    public static SpringConfig f10043a = b(40.0d, 7.0d);
    public double b;
    public double c;

    public SpringConfig(double d, double d2) {
        this.c = d;
        this.b = d2;
    }

    public static SpringConfig a(double d, double d2) {
        BouncyConversion bouncyConversion = new BouncyConversion(d2, d);
        return b(bouncyConversion.c(), bouncyConversion.b());
    }

    public static SpringConfig b(double d, double d2) {
        return new SpringConfig(OrigamiValueConverter.d(d), OrigamiValueConverter.a(d2));
    }
}
