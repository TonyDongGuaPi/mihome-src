package com.scwang.smartrefresh.layout.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.NonNull;

public class ArrowDrawable extends PaintDrawable {

    /* renamed from: a  reason: collision with root package name */
    private int f8798a = 0;
    private int b = 0;
    private Path c = new Path();

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (!(this.f8798a == width && this.b == height)) {
            int i = (width * 30) / 225;
            this.c.reset();
            double d = (double) i;
            double sin = Math.sin(0.7853981633974483d);
            Double.isNaN(d);
            float f = (float) (sin * d);
            double sin2 = Math.sin(0.7853981633974483d);
            Double.isNaN(d);
            int i2 = width / 2;
            float f2 = (float) height;
            this.c.moveTo((float) i2, f2);
            float f3 = (float) (height / 2);
            this.c.lineTo(0.0f, f3);
            float f4 = f3 - f;
            this.c.lineTo(f, f4);
            int i3 = i / 2;
            float f5 = (float) (i2 - i3);
            float f6 = (f2 - ((float) (d / sin2))) - ((float) i3);
            this.c.lineTo(f5, f6);
            this.c.lineTo(f5, 0.0f);
            float f7 = (float) (i2 + i3);
            this.c.lineTo(f7, 0.0f);
            this.c.lineTo(f7, f6);
            float f8 = (float) width;
            this.c.lineTo(f8 - f, f4);
            this.c.lineTo(f8, f3);
            this.c.close();
            this.f8798a = width;
            this.b = height;
        }
        canvas.drawPath(this.c, this.m);
    }
}
