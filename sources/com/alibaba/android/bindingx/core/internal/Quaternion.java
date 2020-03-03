package com.alibaba.android.bindingx.core.internal;

import android.support.annotation.Nullable;
import com.taobao.weex.el.parse.Operators;

class Quaternion {

    /* renamed from: a  reason: collision with root package name */
    double f761a;
    double b;
    double c;
    double d;

    Quaternion() {
    }

    Quaternion(double d2, double d3, double d4, double d5) {
        this.f761a = d2;
        this.b = d3;
        this.c = d4;
        this.d = d5;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Quaternion a(Euler euler) {
        Euler euler2 = euler;
        if (euler2 == null || !euler2.e) {
            return null;
        }
        double cos = Math.cos(euler2.b / 2.0d);
        double cos2 = Math.cos(euler2.c / 2.0d);
        double cos3 = Math.cos(euler2.d / 2.0d);
        double sin = Math.sin(euler2.b / 2.0d);
        double sin2 = Math.sin(euler2.c / 2.0d);
        double sin3 = Math.sin(euler2.d / 2.0d);
        String str = euler2.f754a;
        if ("XYZ".equals(str)) {
            double d2 = sin * cos2;
            double d3 = cos * sin2;
            this.f761a = (d2 * cos3) + (d3 * sin3);
            this.b = (d3 * cos3) - (d2 * sin3);
            double d4 = cos * cos2;
            double d5 = sin * sin2;
            this.c = (d4 * sin3) + (d5 * cos3);
            this.d = (d4 * cos3) - (d5 * sin3);
        } else {
            double d6 = sin;
            if ("YXZ".equals(str)) {
                double d7 = d6 * cos2;
                double d8 = cos * sin2;
                this.f761a = (d7 * cos3) + (d8 * sin3);
                this.b = (d8 * cos3) - (d7 * sin3);
                double d9 = cos * cos2;
                double d10 = d6 * sin2;
                this.c = (d9 * sin3) - (d10 * cos3);
                this.d = (d9 * cos3) + (d10 * sin3);
            } else if ("ZXY".equals(str)) {
                double d11 = d6 * cos2;
                double d12 = cos * sin2;
                this.f761a = (d11 * cos3) - (d12 * sin3);
                this.b = (d12 * cos3) + (d11 * sin3);
                double d13 = cos * cos2;
                double d14 = d6 * sin2;
                this.c = (d13 * sin3) + (d14 * cos3);
                this.d = (d13 * cos3) - (d14 * sin3);
            } else if ("ZYX".equals(str)) {
                double d15 = d6 * cos2;
                double d16 = cos * sin2;
                this.f761a = (d15 * cos3) - (d16 * sin3);
                this.b = (d16 * cos3) + (d15 * sin3);
                double d17 = cos * cos2;
                double d18 = d6 * sin2;
                this.c = (d17 * sin3) - (d18 * cos3);
                this.d = (d17 * cos3) + (d18 * sin3);
            } else if ("YZX".equals(str)) {
                double d19 = d6 * cos2;
                double d20 = cos * sin2;
                this.f761a = (d19 * cos3) + (d20 * sin3);
                this.b = (d20 * cos3) + (d19 * sin3);
                double d21 = cos * cos2;
                double d22 = d6 * sin2;
                this.c = (d21 * sin3) - (d22 * cos3);
                this.d = (d21 * cos3) - (d22 * sin3);
            } else if ("XZY".equals(str)) {
                double d23 = d6 * cos2;
                double d24 = cos * sin2;
                this.f761a = (d23 * cos3) - (d24 * sin3);
                this.b = (d24 * cos3) - (d23 * sin3);
                double d25 = cos * cos2;
                double d26 = d6 * sin2;
                this.c = (d25 * sin3) + (d26 * cos3);
                this.d = (d25 * cos3) + (d26 * sin3);
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public Quaternion a(Vector3 vector3, double d2) {
        double d3 = d2 / 2.0d;
        double sin = Math.sin(d3);
        this.f761a = vector3.f766a * sin;
        this.b = vector3.b * sin;
        this.c = vector3.c * sin;
        this.d = Math.cos(d3);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Quaternion a(Quaternion quaternion) {
        return a(this, quaternion);
    }

    /* access modifiers changed from: package-private */
    public Quaternion a(Quaternion quaternion, Quaternion quaternion2) {
        Quaternion quaternion3 = quaternion;
        Quaternion quaternion4 = quaternion2;
        double d2 = quaternion3.f761a;
        double d3 = quaternion3.b;
        double d4 = quaternion3.c;
        double d5 = quaternion3.d;
        double d6 = quaternion4.f761a;
        double d7 = quaternion4.b;
        double d8 = quaternion4.c;
        double d9 = d4;
        double d10 = quaternion4.d;
        double d11 = d8;
        double d12 = d11;
        this.f761a = (((d2 * d10) + (d5 * d6)) + (d3 * d8)) - (d9 * d7);
        this.b = (((d3 * d10) + (d5 * d7)) + (d9 * d6)) - (d2 * d12);
        this.c = (((d9 * d10) + (d5 * d12)) + (d2 * d7)) - (d3 * d6);
        this.d = (((d5 * d10) - (d2 * d6)) - (d3 * d7)) - (d9 * d12);
        return this;
    }

    public String toString() {
        return "Quaternion{x=" + this.f761a + ", y=" + this.b + ", z=" + this.c + ", w=" + this.d + Operators.BLOCK_END;
    }
}
