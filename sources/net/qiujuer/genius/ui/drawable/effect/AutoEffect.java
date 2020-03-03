package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Paint;

public class AutoEffect extends PressEffect {

    /* renamed from: a  reason: collision with root package name */
    protected float f3135a;
    protected float b;
    protected float c;
    protected float d;
    private int o;

    public AutoEffect() {
        this.g = 172;
        this.m = 0.0f;
        this.n = 0.78f;
    }

    public void a(Canvas canvas, Paint paint) {
        int a2 = a(paint, this.f);
        if (paint.getAlpha() > 0) {
            canvas.drawColor(paint.getColor());
        }
        if (this.j > 0.0f) {
            if (a2 < 255) {
                a2 = a(a2, paint.getAlpha());
            }
            paint.setAlpha(a2);
            a(paint, this.o);
            if (paint.getAlpha() > 0) {
                canvas.drawCircle(this.c, this.d, this.j, paint);
            }
        }
    }

    public void a(float f) {
        super.a(f);
        this.c = this.f3135a + ((this.k - this.f3135a) * f);
        this.d = this.b + ((this.l - this.b) * f);
    }

    public void b(float f) {
        super.b(f);
        this.j = this.i;
        this.o = 255 - ((int) (f * 255.0f));
    }

    public void a(float f, float f2) {
        this.f3135a = f;
        this.c = f;
        this.b = f2;
        this.d = f2;
        this.o = 255;
    }

    private int a(int i, int i2) {
        if (i2 > i) {
            return 0;
        }
        return ((i - i2) * 255) / (255 - i2);
    }
}
