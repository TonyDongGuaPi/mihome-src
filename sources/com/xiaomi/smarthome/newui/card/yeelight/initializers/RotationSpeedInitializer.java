package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class RotationSpeedInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f20621a;
    private float b;

    public RotationSpeedInitializer(float f, float f2) {
        this.f20621a = f;
        this.b = f2;
    }

    public void a(Particle particle, Random random) {
        particle.o = (random.nextFloat() * (this.b - this.f20621a)) + this.f20621a;
    }
}
