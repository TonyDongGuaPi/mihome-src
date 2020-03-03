package com.xiaomi.smarthome.newui.widget.guide.drawable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BubbleDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private Path f20906a;
    private RectF b;
    private Paint c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private int h;
    private RectF i;
    private PointF j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private float s;

    public int getOpacity() {
        return -3;
    }

    public BubbleDrawable(Activity activity) {
        this(activity, 3);
    }

    public BubbleDrawable(Activity activity, int i2) {
        this(activity, Color.parseColor("#000000"), i2);
    }

    public BubbleDrawable(Activity activity, int i2, int i3) {
        this.k = 3;
        this.l = 10;
        this.s = 0.0f;
        this.e = a(activity, ((float) (this.l * 2)) + 19.2f);
        this.d = (int) ((((float) this.e) * 4.0f) / 3.0f);
        this.f = a(activity, 6.0f);
        this.g = this.f * 2;
        this.n = a(activity, (float) this.l);
        this.m = a(activity, (float) this.k);
        a(i3);
        this.i = new RectF();
        this.f20906a = new Path();
        this.b = new RectF();
        this.c = new Paint(1);
        this.c.setColor(i2);
        this.c.setStrokeCap(Paint.Cap.SQUARE);
        this.c.setStrokeWidth(1.0f);
        this.j = new PointF();
    }

    public PointF a() {
        return this.j;
    }

    public void a(int i2) {
        if (i2 == 80 || i2 == 85) {
            d();
            this.h = i2;
            this.r = this.f;
        } else if (i2 == 48 || i2 == 51) {
            d();
            this.h = i2;
            this.p = this.f;
        } else if (i2 == 3) {
            d();
            this.h = i2;
            this.o = this.f;
        } else if (i2 == 5) {
            d();
            this.h = i2;
            this.q = this.f;
        } else {
            throw new RuntimeException("Use only the following: BOTTOM, TOP, LEFT, RIGHT.");
        }
    }

    public int b() {
        return this.h;
    }

    private void d() {
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
    }

    public void b(int i2) {
        this.c.setColor(i2);
        invalidateSelf();
    }

    public void c(int i2) {
        this.n = i2;
        invalidateSelf();
    }

    public int c() {
        return this.f;
    }

    public void a(float f2) {
        this.s = f2;
        invalidateSelf();
    }

    public void draw(@NonNull Canvas canvas) {
        this.f20906a.reset();
        canvas.drawRoundRect(this.b, (float) this.n, (float) this.n, this.c);
        float f2 = (this.b.bottom - this.b.top) - ((float) (this.n * 2));
        float f3 = (this.b.right - this.b.left) - ((float) (this.n * 2));
        double d2 = (double) this.m;
        Double.isNaN(d2);
        float sqrt = (float) ((Math.sqrt(2.0d) / 2.0d) * d2);
        if (this.h == 3) {
            float f4 = this.b.left;
            float f5 = this.b.top + ((float) this.n) + (f2 / 7.0f);
            this.f20906a.moveTo(f4, f5);
            this.f20906a.lineTo(sqrt, ((((float) this.g) / 2.0f) + f5) - sqrt);
            double sqrt2 = Math.sqrt(2.0d);
            double d3 = (double) this.m;
            Double.isNaN(d3);
            float f6 = (float) (sqrt2 * d3);
            float f7 = (((float) this.g) / 2.0f) + f5;
            this.i.set(f6 - ((float) this.m), f7 - ((float) this.m), f6 + ((float) this.m), f7 + ((float) this.m));
            this.f20906a.arcTo(this.i, -135.0f, -90.0f);
            this.f20906a.lineTo(sqrt, (((float) this.g) / 2.0f) + f5 + sqrt);
            this.f20906a.lineTo(f4, ((float) this.g) + f5);
            this.j.set(0.0f, f5 + (((float) this.g) / 2.0f));
        } else if (this.h == 5) {
            float f8 = this.b.right;
            float f9 = this.b.top + ((float) this.n) + (f2 / 7.0f);
            this.f20906a.moveTo(f8, f9);
            this.f20906a.lineTo((this.b.right + ((float) this.q)) - sqrt, ((((float) this.g) / 2.0f) + f9) - sqrt);
            float f10 = this.b.right + ((float) this.q);
            double sqrt3 = Math.sqrt(2.0d);
            double d4 = (double) this.m;
            Double.isNaN(d4);
            float f11 = f10 - ((float) (sqrt3 * d4));
            float f12 = (((float) this.g) / 2.0f) + f9;
            this.i.set(f11 - ((float) this.m), f12 - ((float) this.m), f11 + ((float) this.m), f12 + ((float) this.m));
            this.f20906a.arcTo(this.i, -45.0f, 90.0f);
            this.f20906a.lineTo((this.b.right + ((float) this.q)) - sqrt, (((float) this.g) / 2.0f) + f9 + sqrt);
            this.f20906a.lineTo(f8, ((float) this.g) + f9);
            this.j.set(this.b.right + ((float) this.q), f9 + (((float) this.g) / 2.0f));
        } else if (this.h == 48) {
            float f13 = ((this.b.left + ((float) this.n)) + (f3 / 2.0f)) - (((float) this.g) / 2.0f);
            float f14 = this.b.top;
            this.f20906a.moveTo(f13, f14);
            this.f20906a.lineTo(((((float) this.g) / 2.0f) + f13) - sqrt, sqrt);
            float f15 = (((float) this.g) / 2.0f) + f13;
            double sqrt4 = Math.sqrt(2.0d);
            double d5 = (double) this.m;
            Double.isNaN(d5);
            float f16 = (float) (sqrt4 * d5);
            this.i.set(f15 - ((float) this.m), f16 - ((float) this.m), f15 + ((float) this.m), f16 + ((float) this.m));
            this.f20906a.arcTo(this.i, -135.0f, 90.0f);
            this.f20906a.lineTo((((float) this.g) / 2.0f) + f13 + sqrt, sqrt);
            this.f20906a.lineTo(((float) this.g) + f13, f14);
            this.j.set(f13 + (((float) this.g) / 2.0f), 0.0f);
        } else if (this.h == 80) {
            float f17 = ((this.b.left + ((float) this.n)) + (f3 / 2.0f)) - (((float) this.g) / 2.0f);
            float f18 = this.b.bottom;
            this.f20906a.moveTo(f17, f18);
            this.f20906a.lineTo(((((float) this.g) / 2.0f) + f17) - sqrt, (((float) this.r) + f18) - sqrt);
            float f19 = (((float) this.g) / 2.0f) + f17;
            float f20 = this.b.bottom + ((float) this.r);
            double sqrt5 = Math.sqrt(2.0d);
            double d6 = (double) this.m;
            Double.isNaN(d6);
            float f21 = f20 - ((float) (sqrt5 * d6));
            this.i.set(f19 - ((float) this.m), f21 - ((float) this.m), f19 + ((float) this.m), f21 + ((float) this.m));
            this.f20906a.arcTo(this.i, 45.0f, 90.0f);
            this.f20906a.lineTo((((float) this.g) / 2.0f) + f17 + sqrt, (((float) this.r) + f18) - sqrt);
            this.f20906a.lineTo(((float) this.g) + f17, f18);
            this.j.set(f17 + (((float) this.g) / 2.0f), f18 + ((float) this.r));
        } else if (this.h == 85) {
            float f22 = ((this.b.left + ((float) this.n)) + ((f3 * 3.0f) / 4.0f)) - (((float) this.g) / 2.0f);
            float f23 = this.b.bottom;
            this.f20906a.moveTo(f22, f23);
            this.f20906a.lineTo(((((float) this.g) / 2.0f) + f22) - sqrt, (((float) this.r) + f23) - sqrt);
            float f24 = (((float) this.g) / 2.0f) + f22;
            float f25 = this.b.bottom + ((float) this.r);
            double sqrt6 = Math.sqrt(2.0d);
            double d7 = (double) this.m;
            Double.isNaN(d7);
            float f26 = f25 - ((float) (sqrt6 * d7));
            this.i.set(f24 - ((float) this.m), f26 - ((float) this.m), f24 + ((float) this.m), f26 + ((float) this.m));
            this.f20906a.arcTo(this.i, 45.0f, 90.0f);
            this.f20906a.lineTo((((float) this.g) / 2.0f) + f22 + sqrt, (((float) this.r) + f23) - sqrt);
            this.f20906a.lineTo(((float) this.g) + f22, f23);
            this.j.set(f22 + (((float) this.g) / 2.0f), f23 + ((float) this.r));
        } else if (this.h == 51) {
            float f27 = ((this.b.left + ((float) this.n)) + ((f3 * 1.0f) / 8.0f)) - (((float) this.g) / 2.0f);
            if (this.s > this.b.left && this.s < this.b.right) {
                f27 = this.s;
            }
            float f28 = this.b.top;
            this.f20906a.moveTo(f27, f28);
            this.f20906a.lineTo(((((float) this.g) / 2.0f) + f27) - sqrt, sqrt);
            float f29 = (((float) this.g) / 2.0f) + f27;
            double sqrt7 = Math.sqrt(2.0d);
            double d8 = (double) this.m;
            Double.isNaN(d8);
            float f30 = (float) (sqrt7 * d8);
            this.i.set(f29 - ((float) this.m), f30 - ((float) this.m), f29 + ((float) this.m), f30 + ((float) this.m));
            this.f20906a.arcTo(this.i, -135.0f, 90.0f);
            this.f20906a.lineTo((((float) this.g) / 2.0f) + f27 + sqrt, sqrt);
            this.f20906a.lineTo(((float) this.g) + f27, f28);
            this.j.set(f27 + (((float) this.g) / 2.0f), 0.0f);
        }
        this.f20906a.close();
        canvas.drawPath(this.f20906a, this.c);
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.c.setAlpha(i2);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }

    public int getIntrinsicHeight() {
        return getBounds().height() < this.e ? this.e : getBounds().height();
    }

    public int getIntrinsicWidth() {
        int width = getBounds().width();
        return width < this.d ? this.d : width;
    }

    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        this.b.set((float) (i2 + this.o), (float) (i3 + this.p), (float) (i4 - this.q), (float) (i5 - this.r));
    }

    private int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
