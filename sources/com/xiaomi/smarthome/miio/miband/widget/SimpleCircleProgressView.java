package com.xiaomi.smarthome.miio.miband.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SimpleCircleProgressView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f19484a = 250;
    private static final int b = 50;
    private static int c = 122;
    private static int d = 255;
    private float e = 0.0f;
    private Paint f;
    private int g;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private float k = 0.0f;

    public SimpleCircleProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public SimpleCircleProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SimpleCircleProgressView(Context context) {
        super(context);
        a();
    }

    public void setProgress(float f2) {
        if (f2 >= 0.0f && f2 <= 360.0f) {
            this.e = f2;
            invalidate();
        }
    }

    private void a() {
        this.k = getContext().getResources().getDisplayMetrics().density;
        this.g = -1;
        this.h = (getWidth() < getHeight() ? getWidth() : getHeight()) / 2;
        this.f = new Paint();
        this.f.setColor(this.g);
        this.f.setAntiAlias(true);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setStrokeWidth(this.k * 1.2f);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.i = (getRight() - getLeft()) / 2;
        this.j = (getBottom() - getTop()) / 2;
        this.h = (getWidth() > getHeight() ? getHeight() : getWidth()) / 2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2 = this.h - 50;
        for (int i3 = 0; i3 < 250; i3++) {
            float f2 = (float) i3;
            int i4 = this.i;
            double d2 = (double) this.h;
            double d3 = (double) ((((2.0f * f2) / 250.0f) - 0.5f) * 3.1415927f);
            double cos = Math.cos(d3);
            Double.isNaN(d2);
            int i5 = i4 + ((int) (d2 * cos));
            int i6 = this.j;
            double d4 = (double) this.h;
            double sin = Math.sin(d3);
            Double.isNaN(d4);
            int i7 = i6 + ((int) (d4 * sin));
            int i8 = this.i;
            double d5 = (double) i2;
            double cos2 = Math.cos(d3);
            Double.isNaN(d5);
            int i9 = i8 + ((int) (cos2 * d5));
            int i10 = this.j;
            double sin2 = Math.sin(d3);
            Double.isNaN(d5);
            int i11 = i10 + ((int) (d5 * sin2));
            if ((f2 * 1.0f) / 250.0f < this.e) {
                this.f.setAlpha(d);
            } else {
                this.f.setAlpha(c);
            }
            canvas.drawLine((float) i5, (float) i7, (float) i9, (float) i11, this.f);
        }
    }
}
