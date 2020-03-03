package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class AlphaInitializer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private int f20619a = 255;
    private int b = 255;

    public AlphaInitializer(int i, int i2) {
        this.f20619a = i;
        this.b = i2;
    }

    public void a(Particle particle, Random random) {
        particle.k = (int) ((((float) (this.f20619a - this.b)) * random.nextFloat()) + ((float) this.b));
    }
}
