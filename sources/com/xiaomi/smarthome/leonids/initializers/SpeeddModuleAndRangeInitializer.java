package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class SpeeddModuleAndRangeInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f18440a;
    private float b;
    private int c;
    private int d;

    public SpeeddModuleAndRangeInitializer(float f, float f2, int i, int i2) {
        this.f18440a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
        while (this.c < 0) {
            this.c += 360;
        }
        while (this.d < 0) {
            this.d += 360;
        }
        if (this.c > this.d) {
            int i3 = this.c;
            this.c = this.d;
            this.d = i3;
        }
    }

    public void a(Particle particle, Random random) {
        int i;
        float nextFloat = (random.nextFloat() * (this.b - this.f18440a)) + this.f18440a;
        if (this.d == this.c) {
            i = this.c;
        } else {
            i = random.nextInt(this.d - this.c) + this.c;
        }
        double d2 = (double) i;
        Double.isNaN(d2);
        double d3 = (double) nextFloat;
        double d4 = (double) ((float) ((d2 * 3.141592653589793d) / 180.0d));
        double cos = Math.cos(d4);
        Double.isNaN(d3);
        particle.h = (float) (cos * d3);
        double sin = Math.sin(d4);
        Double.isNaN(d3);
        particle.i = (float) (d3 * sin);
    }
}
