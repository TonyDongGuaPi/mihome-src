package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class ScaleInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f18438a;
    private float b;

    public ScaleInitializer(float f, float f2) {
        this.b = f;
        this.f18438a = f2;
    }

    public void a(Particle particle, Random random) {
        particle.d = (random.nextFloat() * (this.f18438a - this.b)) + this.b;
    }
}
