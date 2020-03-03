package com.xiaomi.smarthome.newui.card;

import com.xiaomi.smarthome.leonids.Particle;
import com.xiaomi.smarthome.leonids.initializers.ParticleInitializer;
import java.util.Random;

public class AlphaInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private int f20455a = 255;
    private int b = 255;

    public AlphaInitializer(int i, int i2) {
        this.f20455a = i;
        this.b = i2;
    }

    public void a(Particle particle, Random random) {
        particle.e = (int) ((((float) (this.f20455a - this.b)) * random.nextFloat()) + ((float) this.b));
    }
}
