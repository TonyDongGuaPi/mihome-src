package com.xiaomi.smarthome.newui.card.yeelight.initializers;

import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class RotationInitiazer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private int f20620a;
    private int b;

    public RotationInitiazer(int i, int i2) {
        this.f20620a = i;
        this.b = i2;
    }

    public void a(Particle particle, Random random) {
        particle.n = (float) (random.nextInt(this.b - this.f20620a) + this.f20620a);
    }
}
