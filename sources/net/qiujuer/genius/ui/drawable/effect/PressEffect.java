package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PressEffect extends EaseEffect {
    protected float h;
    protected float i;
    protected float j;
    protected float k;
    protected float l;
    protected float m;
    protected float n;

    public PressEffect() {
        this(0.68f, 0.98f);
    }

    public PressEffect(float f, float f2) {
        this.m = f;
        this.n = f2;
    }

    public void c(float f) {
        this.n = f;
    }

    public void d(float f) {
        this.m = f;
    }

    public float f() {
        return this.n;
    }

    public float g() {
        return this.m;
    }

    public void a(Canvas canvas, Paint paint) {
        if (this.j > 0.0f && this.f > 0) {
            a(paint, this.f);
            canvas.drawCircle(this.k, this.l, this.j, paint);
        }
    }

    public void a(float f) {
        super.a(f);
        this.j = this.h + ((this.i - this.h) * f);
    }

    public void b(float f) {
        super.b(f);
        this.j = this.i + ((this.h - this.i) * f);
    }

    /* access modifiers changed from: protected */
    public void f(float f, float f2) {
        this.k = f / 2.0f;
        this.l = f2 / 2.0f;
        e(Math.max(this.k, this.l));
    }

    /* access modifiers changed from: protected */
    public void e(float f) {
        this.h = this.m * f;
        this.i = f * this.n;
    }
}
