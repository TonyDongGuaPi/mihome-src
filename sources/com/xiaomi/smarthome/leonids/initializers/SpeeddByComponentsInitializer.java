package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class SpeeddByComponentsInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f18439a;
    private float b;
    private float c;
    private float d;

    public SpeeddByComponentsInitializer(float f, float f2, float f3, float f4) {
        this.f18439a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void a(Particle particle, Random random) {
        particle.h = (random.nextFloat() * (this.b - this.f18439a)) + this.f18439a;
        particle.i = (random.nextFloat() * (this.d - this.c)) + this.c;
    }
}
