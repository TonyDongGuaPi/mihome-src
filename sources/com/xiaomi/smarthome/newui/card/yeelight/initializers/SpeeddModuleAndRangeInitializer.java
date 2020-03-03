package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class SpeeddModuleAndRangeInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private float f20624a;
    private float b;
    private int c;
    private int d;
    private boolean e;

    public SpeeddModuleAndRangeInitializer(float f, float f2, int i, int i2) {
        this(f, f2, i, i2, true);
    }

    public SpeeddModuleAndRangeInitializer(float f, float f2, int i, int i2, boolean z) {
        this.f20624a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
        this.e = z;
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
        float nextFloat = (random.nextFloat() * (this.b - this.f20624a)) + this.f20624a;
        if (this.d == this.c) {
            i = this.c;
        } else if (this.e) {
            i = random.nextInt(this.d - this.c) + this.c;
        } else {
            i = random.nextInt(360 - (this.d - this.c)) + this.d;
            while (i < 0) {
                i += 360;
            }
            while (i > 360) {
                i -= 360;
            }
        }
        a(particle, nextFloat, (float) i);
    }

    private void a(Particle particle, float f, float f2) {
        double d2 = (double) f2;
        Double.isNaN(d2);
        float f3 = (float) ((d2 * 3.141592653589793d) / 180.0d);
        if (f2 < 90.0f) {
            double d3 = (double) f3;
            particle.p = ((float) Math.cos(d3)) * f;
            particle.q = ((float) Math.sin(d3)) * f;
        } else if (f2 == 90.0f) {
            particle.p = 0.0f;
            particle.q = f;
        } else if (f2 > 90.0f && f2 < 180.0f) {
            double d4 = (double) (180.0f - f2);
            Double.isNaN(d4);
            double d5 = (double) ((float) ((d4 * 3.141592653589793d) / 180.0d));
            particle.p = (-((float) Math.cos(d5))) * f;
            particle.q = ((float) Math.sin(d5)) * f;
        } else if (f2 == 180.0f) {
            particle.p = f;
            particle.q = 0.0f;
        } else if (f2 > 180.0f && f2 < 270.0f) {
            double d6 = (double) (f2 - 180.0f);
            Double.isNaN(d6);
            double d7 = (double) ((float) ((d6 * 3.141592653589793d) / 180.0d));
            particle.p = (-((float) Math.cos(d7))) * f;
            particle.q = (-((float) Math.sin(d7))) * f;
        } else if (f2 == 270.0f) {
            particle.p = 0.0f;
            particle.q = -f;
        } else {
            double d8 = (double) (360.0f - f2);
            Double.isNaN(d8);
            double d9 = (double) ((float) ((d8 * 3.141592653589793d) / 180.0d));
            particle.p = ((float) Math.cos(d9)) * f;
            particle.q = (-((float) Math.sin(d9))) * f;
        }
    }
}
