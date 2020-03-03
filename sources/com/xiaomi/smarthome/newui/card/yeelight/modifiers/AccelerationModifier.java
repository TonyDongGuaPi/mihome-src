package com.xiaomi.smarthome.newui.card.yeelight.modifiers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;

public class AccelerationModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private float f20625a;
    private float b;

    public AccelerationModifier(float f, float f2) {
        double d = (double) f2;
        Double.isNaN(d);
        float f3 = (float) ((d * 3.141592653589793d) / 180.0d);
        double d2 = (double) f;
        double d3 = (double) f3;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        this.f20625a = (float) (cos * d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        this.b = (float) (d2 * sin);
    }

    public void a(Particle particle, long j) {
        float f = (float) j;
        particle.g += this.f20625a * f * f;
        particle.h += this.b * f * f;
    }
}
