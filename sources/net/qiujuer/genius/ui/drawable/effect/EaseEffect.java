package net.qiujuer.genius.ui.drawable.effect;

import android.graphics.Canvas;
import android.graphics.Paint;

public class EaseEffect extends Effect {
    static final int e = 256;
    protected int f;
    protected int g;

    public EaseEffect() {
        this.f = 0;
        this.g = 256;
    }

    public EaseEffect(int i) {
        this.f = 0;
        this.g = i;
    }

    public void a(int i) {
        this.g = i;
    }

    public int a() {
        return this.g;
    }

    public void a(Canvas canvas, Paint paint) {
        if (this.f > 0) {
            a(paint, this.f);
            canvas.drawColor(paint.getColor());
        }
    }

    public void a(float f2) {
        this.f = (int) (f2 * ((float) this.g));
    }

    public void b(float f2) {
        this.f = this.g - ((int) (((float) this.g) * f2));
    }
}
