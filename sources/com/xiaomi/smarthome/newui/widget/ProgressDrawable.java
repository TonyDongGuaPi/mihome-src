package com.xiaomi.smarthome.newui.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ProgressDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private int f20877a;
    private int b;
    private int c;
    private int d = 0;
    private int e = 100;
    private int f;
    private Paint g;
    private Paint h;

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    private void a() {
        if (this.f20877a == 0 || this.b == 0) {
            Rect bounds = getBounds();
            this.f20877a = bounds.width();
            this.b = bounds.height();
            this.f = this.b / 2;
        }
    }

    public void draw(@NonNull Canvas canvas) {
        a();
        RectF rectF = new RectF(0.0f, 0.0f, (float) (this.f * 2), (float) (this.f * 2));
        RectF rectF2 = new RectF((float) (this.f20877a - (this.f * 2)), 0.0f, (float) this.f20877a, (float) this.b);
        canvas.drawArc(rectF, 90.0f, 180.0f, false, this.g);
        canvas.drawRect(new RectF((float) this.f, 0.0f, (float) (this.f20877a - this.f), (float) this.b), this.g);
        canvas.drawArc(rectF2, -90.0f, 180.0f, false, this.g);
        int i = (int) (((float) (this.f20877a * this.c)) / ((float) (this.e - this.d)));
        if (i < this.f) {
            int degrees = (int) Math.toDegrees(Math.acos((double) (((float) (this.f - i)) / ((float) this.f))));
            int i2 = 180 - degrees;
            canvas.drawArc(rectF, (float) i2, (float) (degrees * 2), false, this.h);
            return;
        }
        canvas.drawArc(rectF, 90.0f, 180.0f, false, this.h);
        if (i < this.f20877a - this.f) {
            canvas.drawRect(new RectF((float) this.f, 0.0f, (float) i, (float) this.b), this.h);
            return;
        }
        canvas.drawRect(new RectF((float) this.f, 0.0f, (float) (this.f20877a - this.f), (float) this.b), this.h);
        int degrees2 = (int) Math.toDegrees(Math.acos((double) (((float) ((this.f - this.f20877a) + i)) / ((float) this.f))));
        Path path = new Path();
        path.addArc(rectF2, -90.0f, 180.0f);
        Path path2 = new Path();
        path2.addArc(rectF2, (float) degrees2, (float) (degrees2 * -2));
        path.op(path2, Path.Op.DIFFERENCE);
        canvas.drawPath(path, this.h);
    }

    public void a(int i) {
        if (this.g == null) {
            this.g = new Paint(1);
            this.g.setStyle(Paint.Style.FILL);
        }
        this.g.setColor(i);
    }

    public void b(int i) {
        if (this.h == null) {
            this.h = new Paint(1);
            this.h.setStyle(Paint.Style.FILL);
        }
        this.h.setColor(i);
    }

    public void a(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public void c(int i) {
        this.c = i;
    }
}
