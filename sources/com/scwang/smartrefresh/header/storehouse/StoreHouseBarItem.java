package com.scwang.smartrefresh.header.storehouse;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.Random;

public class StoreHouseBarItem extends Animation {

    /* renamed from: a  reason: collision with root package name */
    public PointF f8759a;
    public float b;
    public int c;
    protected final Paint d = new Paint();
    protected float e = 1.0f;
    protected float f = 0.4f;
    protected PointF g;
    protected PointF h;

    public StoreHouseBarItem(int i, PointF pointF, PointF pointF2, int i2, int i3) {
        this.c = i;
        this.f8759a = new PointF((pointF.x + pointF2.x) / 2.0f, (pointF.y + pointF2.y) / 2.0f);
        this.g = new PointF(pointF.x - this.f8759a.x, pointF.y - this.f8759a.y);
        this.h = new PointF(pointF2.x - this.f8759a.x, pointF2.y - this.f8759a.y);
        b(i2);
        a(i3);
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
    }

    public void a(int i) {
        this.d.setStrokeWidth((float) i);
    }

    public void b(int i) {
        this.d.setColor(i);
    }

    public void c(int i) {
        this.b = (float) ((-new Random().nextInt(i)) + i);
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f2, Transformation transformation) {
        float f3 = this.e;
        a(f3 + ((this.f - f3) * f2));
    }

    public void a(float f2, float f3) {
        this.e = f2;
        this.f = f3;
        super.start();
    }

    public void a(float f2) {
        this.d.setAlpha((int) (f2 * 255.0f));
    }

    public void a(Canvas canvas) {
        canvas.drawLine(this.g.x, this.g.y, this.h.x, this.h.y, this.d);
    }
}
