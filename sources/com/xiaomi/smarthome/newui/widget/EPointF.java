package com.xiaomi.smarthome.newui.widget;

class EPointF {

    /* renamed from: a  reason: collision with root package name */
    private final float f20857a;
    private final float b;

    public EPointF(float f, float f2) {
        this.f20857a = f;
        this.b = f2;
    }

    public float a() {
        return this.f20857a;
    }

    public float b() {
        return this.b;
    }

    public EPointF a(float f, EPointF ePointF) {
        return new EPointF(this.f20857a + (ePointF.f20857a * f), this.b + (f * ePointF.b));
    }

    public EPointF a(EPointF ePointF) {
        return a(1.0f, ePointF);
    }

    public EPointF b(float f, EPointF ePointF) {
        return new EPointF(this.f20857a - (ePointF.f20857a * f), this.b - (f * ePointF.b));
    }

    public EPointF b(EPointF ePointF) {
        return b(1.0f, ePointF);
    }

    public EPointF a(float f) {
        return new EPointF(this.f20857a * f, f * this.b);
    }
}
