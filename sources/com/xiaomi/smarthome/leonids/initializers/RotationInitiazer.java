package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public class RotationInitiazer implements ParticleInitializer {

    /* renamed from: a  reason: collision with root package name */
    private int f18436a;
    private int b;

    public RotationInitiazer(int i, int i2) {
        this.f18436a = i;
        this.b = i2;
    }

    public void a(Particle particle, Random random) {
        particle.f = (float) (this.f18436a == this.b ? this.f18436a : random.nextInt(this.b - this.f18436a) + this.f18436a);
    }
}
