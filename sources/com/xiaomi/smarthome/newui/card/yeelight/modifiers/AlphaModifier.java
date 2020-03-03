package com.xiaomi.smarthome.newui.card.yeelight.modifiers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.card.yeelight.Particle;
import java.util.Random;

public class AlphaModifier implements ParticleModifier {

    /* renamed from: a  reason: collision with root package name */
    private int f20626a;
    private int b;
    private long c;
    private long d;
    private float e;
    private float f;
    private Interpolator g;

    public AlphaModifier(int i, int i2, long j, long j2, Interpolator interpolator) {
        this.f20626a = -1;
        this.b = i2;
        new Random();
        this.c = j;
        this.d = j2;
        this.e = (float) (this.d - this.c);
        this.f = (float) (this.b - this.f20626a);
        this.g = interpolator;
    }

    public AlphaModifier(int i, int i2, long j, long j2) {
        this(i, i2, j, j2, new LinearInterpolator());
    }

    public void a(Particle particle, long j) {
        if (this.f20626a == -1) {
            this.f20626a = particle.k;
            this.f = (float) (this.b - this.f20626a);
        }
        if (this.b == this.f20626a) {
            this.f = (float) this.f20626a;
        }
        if (j >= this.c) {
            if (j > this.d) {
                particle.k = this.b;
                return;
            }
            particle.k = (int) (((float) this.f20626a) + (this.f * this.g.getInterpolation((((float) (j - this.c)) * 1.0f) / this.e)));
        }
    }
}
