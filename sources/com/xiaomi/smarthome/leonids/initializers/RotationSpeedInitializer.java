package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class RotationSpeedInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f18437a;
    private float b;

    public RotationSpeedInitializer(float f, float f2) {
        this.f18437a = f;
        this.b = f2;
    }

    public void a(Particle particle, Random random) {
        particle.g = (random.nextFloat() * (this.b - this.f18437a)) + this.f18437a;
    }
}
