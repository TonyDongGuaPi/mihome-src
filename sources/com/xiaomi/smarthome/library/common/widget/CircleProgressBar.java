package com.xiaomi.smarthome.library.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f18786a;
    private int b;
    private float c;
    private float d = 100.0f;
    private Paint e = new Paint();
    private int f;
    private int g;
    private RectF h = new RectF();
    private int i;
    private int j;

    public CircleProgressBar(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet);
    }

    @TargetApi(21)
    public CircleProgressBar(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.e.setAntiAlias(true);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeCap(Paint.Cap.ROUND);
        this.e.setStrokeJoin(Paint.Join.ROUND);
        int i2 = (int) (getContext().getResources().getDisplayMetrics().density * 3.0f);
        this.f = i2;
        this.g = i2;
        this.i = -16777216;
        this.b = -65536;
        this.f18786a = -65536;
        this.j = this.f * 4;
    }

    public void setColor(int i2, int i3, int i4) {
        this.f18786a = i2;
        this.b = i3;
        this.i = i4;
        invalidate();
    }

    public void setWidth(int i2, int i3) {
        this.f = i2;
        this.g = i3;
        requestLayout();
    }

    public void setProgress(float f2) {
        this.c = f2;
        invalidate();
    }

    public void setMaxProgress(float f2) {
        this.d = f2;
        invalidate();
    }

    public float getProgress() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.h.set((float) (getPaddingLeft() + this.g), (float) (getPaddingTop() + this.g), (float) ((getWidth() - getPaddingRight()) - this.g), (float) ((getHeight() - getPaddingBottom()) - this.g));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.e.setStrokeWidth((float) this.g);
        this.e.setColor(this.i);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.h, 0.0f, 360.0f, false, this.e);
        this.e.setColor(this.b);
        float f2 = (this.c * 360.0f) / this.d;
        canvas2.drawArc(this.h, 270.0f, f2, false, this.e);
        int height = getHeight() / 2;
        float width = (float) (getWidth() / 2);
        float f3 = (float) height;
        canvas.rotate(f2, width, f3);
        this.e.setStrokeWidth((float) this.f);
        this.e.setColor(this.f18786a);
        if (this.c > this.d) {
            canvas.drawLine(width - (((this.c / this.d) * ((float) this.j)) / 1.5f), f3 + ((((this.c * 1.7f) / this.d) * ((float) this.j)) / 3.0f), width, (float) (height - (this.j / 2)), this.e);
        } else {
            canvas.drawLine(width - (((float) this.j) / 1.5f), (float) ((this.j / 3) + height), width, (float) (height - (this.j / 2)), this.e);
        }
        canvas.drawLine(width, (float) (height - (this.j / 2)), width + (((float) this.j) / 1.5f), (float) (height + (this.j / 3)), this.e);
    }
}
