package com.xiaomi.smarthome.newui.card.yeelight.modifiers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.card.yeelight.Particle;

public class ScaleModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private float f20627a;
    private float b;
    private long c;
    private long d;
    private long e;
    private float f;
    private Interpolator g;

    public ScaleModifier(float f2, float f3, long j, long j2, Interpolator interpolator) {
        this.f20627a = f2;
        this.b = f3;
        this.d = j;
        this.c = j2;
        this.e = this.c - this.d;
        this.f = this.b - this.f20627a;
        this.g = interpolator;
    }

    public ScaleModifier(float f2, float f3, long j, long j2) {
        this(f2, f3, j, j2, new LinearInterpolator());
    }

    public void a(Particle particle, long j) {
        if (j < this.d) {
            particle.i = this.f20627a;
        } else if (j > this.c) {
            particle.i = this.b;
        } else {
            particle.i = this.f20627a + (this.f * this.g.getInterpolation((((float) (j - this.d)) * 1.0f) / ((float) this.e)));
        }
    }
}
