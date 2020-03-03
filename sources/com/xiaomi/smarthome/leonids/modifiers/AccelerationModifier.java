package com.xiaomi.smarthome.leonids.modifiers;

import com.xiaomi.smarthome.leonids.Particle;

public class AccelerationModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private float f18441a;
    private float b;

    public AccelerationModifier(float f, float f2) {
        double d = (double) f2;
        Double.isNaN(d);
        float f3 = (float) ((d * 3.141592653589793d) / 180.0d);
        double d2 = (double) f;
        double d3 = (double) f3;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        this.f18441a = (float) (cos * d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        this.b = (float) (d2 * sin);
    }

    public void a(Particle particle, long j) {
        float f = (float) j;
        particle.b += this.f18441a * f * f;
        particle.c += this.b * f * f;
    }
}
