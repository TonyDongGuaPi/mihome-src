package net.qiujuer.genius.ui.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import javax.jmdns.impl.constants.DNSRecordClass;

public class LoadingLineDrawable extends LoadingDrawable {
    private float f;
    private float g;
    private float h;
    private float i = 400.0f;
    private float j;
    private float k;
    private float l;
    private float m = 0.008f;
    private int n = 1;

    public int getIntrinsicWidth() {
        return DNSRecordClass.CLASS_MASK;
    }

    public LoadingLineDrawable() {
    }

    public LoadingLineDrawable(float f2) {
        this.m = f2;
    }

    public int getIntrinsicHeight() {
        return (int) Math.max(this.d.getStrokeWidth(), this.c.getStrokeWidth());
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect.left != 0 || rect.top != 0 || rect.right != 0 || rect.bottom != 0) {
            this.g = (float) rect.left;
            this.h = (float) rect.right;
            this.f = (float) rect.centerY();
            this.i = (this.h - this.g) * 0.5f;
            if (this.e != 0.0f) {
                a(this.e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int f() {
        this.n++;
        if (this.n > 3) {
            this.n = 1;
        }
        return super.f();
    }

    /* access modifiers changed from: protected */
    public void a(float f2) {
        this.j = this.g;
        this.k = this.g + ((this.h - this.g) * f2);
    }

    /* access modifiers changed from: protected */
    public void a() {
        float f2;
        this.l += this.m;
        if (this.l > 1.0f) {
            this.l -= 1.0f;
            f();
        }
        float f3 = this.l;
        float f4 = this.i;
        float f5 = (this.h - this.g) * f3;
        float f6 = this.g + f5;
        if (this.n == 1) {
            f2 = f3 > 0.5f ? f4 * (1.0f - f3) : f4 * f3;
        } else if (this.n == 2) {
            f2 = (f4 * f3) / 2.0f;
            if (f6 + f2 > this.h) {
                f2 = this.h - f6;
            }
        } else {
            if (f5 + f5 > f4) {
                f5 = f4 / 2.0f;
            }
            f2 = f5;
            if (f6 + f2 > this.h) {
                f2 = this.h - f6;
            }
        }
        this.j = f6 - f2;
        this.k = f6 + f2;
    }

    /* access modifiers changed from: protected */
    public void a(Canvas canvas, Paint paint) {
        canvas.drawLine(this.g, this.f, this.h, this.f, paint);
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas, Paint paint) {
        canvas.drawLine(this.j, this.f, this.k, this.f, paint);
    }
}
