package com.xiaomi.smarthome.leonids.modifiers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.leonids.Particle;

public class AlphaModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private int f18442a;
    private int b;
    private long c;
    private long d;
    private float e;
    private float f;
    private Interpolator g;

    public AlphaModifier(int i, int i2, long j, long j2, Interpolator interpolator) {
        this.f18442a = i;
        this.b = i2;
        this.c = j;
        this.d = j2;
        this.e = (float) (this.d - this.c);
        this.f = (float) (this.b - this.f18442a);
        this.g = interpolator;
    }

    public AlphaModifier(int i, int i2, long j, long j2) {
        this(i, i2, j, j2, new LinearInterpolator());
    }

    public void a(Particle particle, long j) {
        if (j < this.c) {
            particle.e = this.f18442a;
        } else if (j > this.d) {
            particle.e = this.b;
        } else {
            particle.e = (int) (((float) this.f18442a) + (this.f * this.g.getInterpolation((((float) (j - this.c)) * 1.0f) / this.e)));
        }
    }
}
