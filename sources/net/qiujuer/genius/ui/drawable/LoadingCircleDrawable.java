package net.qiujuer.genius.ui.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class LoadingCircleDrawable extends LoadingDrawable {
    private static final int f = 5;
    private static final int g = 3;
    private static final int h = 255;
    private static int i = 56;
    private int j = i;
    private int k = i;
    private RectF l = new RectF();
    private float m = 0.0f;
    private float n = 0.0f;
    private int o = -3;

    public LoadingCircleDrawable() {
        this.c.setStrokeCap(Paint.Cap.ROUND);
    }

    public LoadingCircleDrawable(int i2, int i3) {
        this.j = i2;
        this.k = i3;
    }

    public int getIntrinsicHeight() {
        return Math.min(this.k, Math.max((int) ((Math.max(this.d.getStrokeWidth(), this.c.getStrokeWidth()) * 2.0f) + 10.0f), this.j));
    }

    public int getIntrinsicWidth() {
        return Math.min(this.k, Math.max((int) ((Math.max(this.d.getStrokeWidth(), this.c.getStrokeWidth()) * 2.0f) + 10.0f), this.j));
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect.left != 0 || rect.top != 0 || rect.right != 0 || rect.bottom != 0) {
            int centerX = rect.centerX();
            int centerY = rect.centerY();
            int min = (Math.min(rect.height(), rect.width()) >> 1) - ((((int) Math.max(c(), b())) >> 1) + 1);
            this.l.set((float) (centerX - min), (float) (centerY - min), (float) (centerX + min), (float) (centerY + min));
        }
    }

    /* access modifiers changed from: protected */
    public void a(float f2) {
        this.m = 0.0f;
        this.n = f2 * 360.0f;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.m += 5.0f;
        if (this.m > 360.0f) {
            this.m -= 360.0f;
        }
        if (this.n > 255.0f) {
            this.o = -this.o;
        } else if (this.n < 3.0f) {
            this.n = 3.0f;
            return;
        } else if (this.n == 3.0f) {
            this.o = -this.o;
            f();
        }
        this.n += (float) this.o;
    }

    /* access modifiers changed from: protected */
    public void a(Canvas canvas, Paint paint) {
        canvas.drawArc(this.l, 0.0f, 360.0f, false, paint);
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas, Paint paint) {
        canvas.drawArc(this.l, this.m, -this.n, false, paint);
    }
}
