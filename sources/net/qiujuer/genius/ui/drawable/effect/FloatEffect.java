package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Paint;

public class FloatEffect extends Effect {

    /* renamed from: a  reason: collision with root package name */
    private float f3138a;
    private float b = 0.0f;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i = 255;
    private int j = 255;

    public void a(Canvas canvas, Paint paint) {
        if (this.b != 0.0f) {
            a(paint, this.i);
            canvas.drawCircle(this.g, this.h, this.b, paint);
        }
    }

    public void a(float f2, float f3) {
        g(f2, f3);
        this.i = this.j;
    }

    public void a(float f2) {
        this.b = this.f3138a * f2;
        this.g = this.c + ((this.e - this.c) * f2);
        this.h = this.d + ((this.f - this.d) * f2);
    }

    public void b(float f2) {
        this.i = this.j - ((int) (((float) this.j) * f2));
    }

    /* access modifiers changed from: protected */
    public void f(float f2, float f3) {
        this.e = f2 / 2.0f;
        this.f = f3 / 2.0f;
        this.f3138a = Math.min(f2, f3) / 2.0f;
    }

    private void g(float f2, float f3) {
        float f4 = f2 - this.e;
        float f5 = f3 - this.f;
        float sqrt = this.f3138a / ((float) Math.sqrt((double) ((f4 * f4) + (f5 * f5))));
        if (sqrt < 1.0f) {
            float f6 = this.e + (f4 * sqrt);
            this.c = f6;
            this.g = f6;
            float f7 = this.f + (f5 * sqrt);
            this.d = f7;
            this.h = f7;
            return;
        }
        this.c = f2;
        this.g = f2;
        this.d = f3;
        this.h = f3;
    }
}
