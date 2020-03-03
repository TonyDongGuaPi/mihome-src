package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import net.qiujuer.genius.ui.Ui;

public abstract class Effect {

    /* renamed from: a  reason: collision with root package name */
    private float f3136a;
    private float b;

    public abstract void a(float f);

    public void a(float f, float f2) {
    }

    public abstract void a(Canvas canvas, Paint paint);

    public void a(Outline outline) {
    }

    public abstract void b(float f);

    public void c(float f, float f2) {
    }

    public void d(float f, float f2) {
    }

    public boolean d() {
        return true;
    }

    public void e(float f, float f2) {
    }

    /* access modifiers changed from: protected */
    public void f(float f, float f2) {
    }

    public final float b() {
        return this.f3136a;
    }

    public final float c() {
        return this.b;
    }

    public final void b(float f, float f2) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (this.f3136a != f || this.b != f2) {
            this.f3136a = f;
            this.b = f2;
            f(f, f2);
        }
    }

    /* access modifiers changed from: protected */
    public int a(Paint paint, int i) {
        int alpha = paint.getAlpha();
        paint.setAlpha(Ui.a(alpha, i));
        return alpha;
    }

    /* renamed from: e */
    public Effect clone() throws CloneNotSupportedException {
        return (Effect) super.clone();
    }
}
