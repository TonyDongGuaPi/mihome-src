package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class SpeeddByComponentsInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f20623a;
    private float b;
    private float c;
    private float d;

    public SpeeddByComponentsInitializer(float f, float f2, float f3, float f4) {
        this.f20623a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void a(Particle particle, Random random) {
        particle.p = (random.nextFloat() * (this.b - this.f20623a)) + this.f20623a;
        particle.q = (random.nextFloat() * (this.d - this.c)) + this.c;
    }
}
