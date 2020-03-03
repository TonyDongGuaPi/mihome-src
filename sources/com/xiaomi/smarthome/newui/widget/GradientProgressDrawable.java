package com.xiaomi.smarthome.newui.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.libra.Color;

public class GradientProgressDrawable extends GradientDrawable {

    /* renamed from: a  reason: collision with root package name */
    private Paint f20865a;
    private int b;
    private int c;
    private int d = 0;
    private int e = 100;
    private int f;
    private int g;
    private Path h;
    private Paint i;
    private int j;
    private int k;
    private int l = 10;
    private int m = 0;

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i2) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public GradientProgressDrawable() {
        setGradientType(0);
        setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
    }

    private void b() {
        if (this.b == 0 || this.c == 0) {
            Rect bounds = getBounds();
            this.b = bounds.width();
            this.c = bounds.height();
            this.f = this.c / 2;
            RectF rectF = new RectF(0.0f, 0.0f, (float) (this.f * 2), (float) (this.f * 2));
            RectF rectF2 = new RectF((float) (this.b - (this.f * 2)), 0.0f, (float) this.b, (float) this.c);
            RectF rectF3 = new RectF((float) this.f, 0.0f, (float) (this.b - this.f), (float) this.c);
            this.h = new Path();
            this.h.addArc(rectF, 90.0f, 180.0f);
            this.h.addRect(rectF3, Path.Direction.CCW);
            this.h.addArc(rectF2, -90.0f, 180.0f);
            this.i = new Paint(1);
            this.f20865a = new Paint(1);
            this.f20865a.setColor(Color.d);
            this.f20865a.setStyle(Paint.Style.STROKE);
            this.f20865a.setStrokeWidth(3.0f);
            this.f20865a.setAlpha(150);
        }
    }

    public void draw(@NonNull Canvas canvas) {
        b();
        canvas.clipPath(this.h);
        super.draw(canvas);
        canvas.save();
        a(canvas);
        canvas.restore();
    }

    private void a(@NonNull Canvas canvas) {
        int a2 = a();
        if (a2 < this.f) {
            a2 = this.f;
        }
        if (a2 > this.b - this.f) {
            a2 = this.b - this.f;
        }
        this.i.setShadowLayer(24.0f, 2.0f, 2.0f, 1145324612);
        this.i.setAntiAlias(true);
        this.i.setColor(this.j);
        float f2 = (float) a2;
        canvas.drawCircle(f2, (float) this.f, (float) ((this.m == 0 ? this.f : this.m) - 3), this.i);
        canvas.drawCircle(f2, (float) this.f, (float) ((this.m == 0 ? this.f : this.m) - 3), this.f20865a);
        this.i.clearShadowLayer();
        this.i.setColor(this.k);
        canvas.drawCircle(f2, (float) this.f, (float) this.l, this.i);
    }

    public int a() {
        return (int) (((float) (this.b * this.g)) / ((float) (this.e - this.d)));
    }

    public void a(int i2, int i3) {
        this.j = i2;
        this.k = i3;
    }

    public void b(@ColorInt int i2, @ColorInt int i3) {
        this.d = i2;
        this.e = i3;
    }

    public void a(int i2) {
        this.m = i2;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public void c(int i2) {
        this.g = i2;
    }
}
