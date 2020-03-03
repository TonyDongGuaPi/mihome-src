package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle {

    /* renamed from: a  reason: collision with root package name */
    public int f10749a;
    public int b;
    public Paint c;
    public int d;

    public void a(int i, int i2, boolean z) {
        this.d += i;
        this.c.setAlpha(this.c.getAlpha() - i2);
        if (z) {
            this.c.setAlpha(0);
        }
    }

    public Circle(int i, int i2, Paint paint, int i3) {
        this.f10749a = i;
        this.b = i2;
        this.c = new Paint(paint);
        this.d = i3;
    }

    public void a(Canvas canvas) {
        canvas.drawCircle((float) this.f10749a, (float) this.b, (float) this.d, this.c);
    }
}
