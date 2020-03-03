package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class AccelerationInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f18435a;
    private float b;
    private int c;
    private int d;
    private boolean e = true;

    public AccelerationInitializer(float f, float f2, int i, int i2) {
        this.f18435a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
    }

    public AccelerationInitializer(float f, float f2) {
        this.f18435a = f;
        this.b = f2;
        this.e = false;
    }

    public void a(Particle particle, Random random) {
        float f = (float) this.c;
        if (this.d != this.c) {
            f = (float) (random.nextInt(this.d - this.c) + this.c);
        }
        double d2 = (double) f;
        Double.isNaN(d2);
        float f2 = (float) ((d2 * 3.141592653589793d) / 180.0d);
        float nextFloat = (random.nextFloat() * (this.b - this.f18435a)) + this.f18435a;
        if (this.e) {
            double d3 = (double) nextFloat;
            double d4 = (double) f2;
            double cos = Math.cos(d4);
            Double.isNaN(d3);
            particle.j = (float) (cos * d3);
            double sin = Math.sin(d4);
            Double.isNaN(d3);
            particle.k = (float) (d3 * sin);
            return;
        }
        particle.j = nextFloat;
        particle.k = nextFloat;
    }
}
