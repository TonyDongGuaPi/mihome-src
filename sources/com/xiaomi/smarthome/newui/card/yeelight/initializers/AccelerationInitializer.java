package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class AccelerationInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f20618a;
    private float b;
    private int c;
    private int d;

    public AccelerationInitializer(float f, float f2, int i, int i2) {
        this.f20618a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
    }

    public void a(Particle particle, Random random) {
        float f = (float) this.c;
        if (this.d != this.c) {
            f = (float) (random.nextInt(this.d - this.c) + this.c);
        }
        double d2 = (double) f;
        Double.isNaN(d2);
        double nextFloat = (double) ((random.nextFloat() * (this.b - this.f20618a)) + this.f20618a);
        double d3 = (double) ((float) ((d2 * 3.141592653589793d) / 180.0d));
        double cos = Math.cos(d3);
        Double.isNaN(nextFloat);
        particle.r = (float) (cos * nextFloat);
        double sin = Math.sin(d3);
        Double.isNaN(nextFloat);
        particle.s = (float) (nextFloat * sin);
    }
}
