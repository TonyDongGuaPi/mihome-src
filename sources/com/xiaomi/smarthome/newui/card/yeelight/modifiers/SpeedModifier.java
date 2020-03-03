package com.xiaomi.smarthome.newui.card.yeelight.modifiers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;

public class SpeedModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private float f20628a = 0.0f;
    private long b;

    public SpeedModifier(float f, long j) {
        this.f20628a = f;
        this.b = j;
    }

    public void a(Particle particle, long j) {
        if (j >= this.b) {
            particle.r = this.f20628a;
            particle.s = this.f20628a;
        }
    }
}
