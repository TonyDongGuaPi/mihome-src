package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RippleEffect extends PressEffect {

    /* renamed from: a  reason: collision with root package name */
    protected float f3139a;
    protected float b;

    public RippleEffect() {
        this.m = 0.0f;
        this.n = 1.0f;
    }

    public void a(Canvas canvas, Paint paint) {
        if (this.j > 0.0f) {
            canvas.drawCircle(this.f3139a, this.b, this.j, paint);
        } else if (this.f > 0) {
            a(paint, this.f);
            canvas.drawColor(paint.getColor());
        }
    }

    public void b(float f) {
        super.b(f);
        this.j = 0.0f;
    }

    public void a(float f, float f2) {
        this.f3139a = f;
        this.b = f2;
        float f3 = 0.0f;
        float b2 = f < this.k ? b() : 0.0f;
        if (f2 < this.l) {
            f3 = c();
        }
        float f4 = b2 - f;
        float f5 = f3 - f2;
        e((float) Math.sqrt((double) ((f4 * f4) + (f5 * f5))));
    }
}
