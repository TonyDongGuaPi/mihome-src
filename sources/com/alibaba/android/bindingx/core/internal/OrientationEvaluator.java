package com.alibaba.android.bindingx.core.internal;

import android.support.annotation.Nullable;

class OrientationEvaluator {

    /* renamed from: a  reason: collision with root package name */
    private Quaternion f760a = new Quaternion(0.0d, 0.0d, 0.0d, 1.0d);
    private Double b = null;
    private Double c = null;
    private Double d = null;
    private double e = 0.0d;
    private double f = 0.0d;
    private double g = 0.0d;
    private final Vector3 h = new Vector3(0.0d, 0.0d, 1.0d);
    private final Euler i = new Euler();
    private final Quaternion j = new Quaternion();
    private final Quaternion k = new Quaternion(-Math.sqrt(0.5d), 0.0d, 0.0d, Math.sqrt(0.5d));

    OrientationEvaluator(@Nullable Double d2, @Nullable Double d3, @Nullable Double d4) {
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    /* access modifiers changed from: package-private */
    public Quaternion a(double d2, double d3, double d4, double d5) {
        a(this.f760a, Math.toRadians(this.b != null ? this.b.doubleValue() : d5 + this.e), Math.toRadians(this.c != null ? this.c.doubleValue() : this.f + d3), Math.toRadians(this.d != null ? this.d.doubleValue() : d4 + this.g), 0.0d);
        return this.f760a;
    }

    private void a(Quaternion quaternion, double d2, double d3, double d4, double d5) {
        Quaternion quaternion2 = quaternion;
        this.i.a(d3, d2, -d4, "YXZ");
        quaternion.a(this.i);
        quaternion.a(this.k);
        quaternion.a(this.j.a(this.h, -d5));
    }
}
