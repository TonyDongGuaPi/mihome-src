package com.xiaomi.smarthome.library.common.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;

public abstract class CornerDrawable extends ColorDrawable {

    /* renamed from: a  reason: collision with root package name */
    private Path f18794a = new Path();
    private RectF b = new RectF();
    private int c;
    private int d;
    private Paint e;

    /* access modifiers changed from: protected */
    public abstract float[] a(Rect rect);

    public CornerDrawable(int i) {
        super(i);
    }

    public void a(int i, int i2) {
        this.d = i;
        this.c = i2;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.b.set(bounds);
        if (this.d > 1) {
            float f = (float) (this.d / 2);
            this.b.inset(f, f);
        }
        this.f18794a.addRoundRect(this.b, a(bounds), Path.Direction.CW);
        canvas.clipPath(this.f18794a);
        super.draw(canvas);
        if (this.c != 0) {
            if (this.e == null) {
                this.e = new Paint();
            }
            this.e.setColor(this.c);
            this.e.setStrokeWidth((float) this.d);
            canvas.drawPath(this.f18794a, this.e);
        }
    }

    public static class RoundSideDrawable extends CornerDrawable {
        public RoundSideDrawable(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        public float[] a(Rect rect) {
            float[] fArr = new float[8];
            int min = Math.min(rect.height(), rect.width()) / 2;
            for (int i = 0; i < fArr.length; i++) {
                fArr[i] = (float) min;
            }
            return fArr;
        }
    }
}
