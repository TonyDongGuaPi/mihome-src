package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class ScaleInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f20622a;
    private float b;

    public ScaleInitializer(float f, float f2) {
        this.b = f;
        this.f20622a = f2;
    }

    public void a(Particle particle, Random random) {
        float nextFloat = (random.nextFloat() * (this.f20622a - this.b)) + this.b;
        if (nextFloat < 0.2f) {
            particle.i = 0.0f;
        } else if (nextFloat > 0.4f) {
            particle.i = nextFloat;
        } else {
            particle.i = (random.nextFloat() * 0.7f) + 0.5f;
        }
    }
}
