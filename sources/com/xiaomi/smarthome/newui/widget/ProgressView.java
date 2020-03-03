package com.xiaomi.smarthome.newui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final float f20878a = 0.8f;
    private static final float b = 0.5f;
    private static final String c = "ProgressView";
    private int d;
    private int e;
    private float f;
    private float g;
    private float h = 100.0f;
    private Paint i = new Paint();
    private int j;
    private int k;
    private RectF l = new RectF();
    private int m;
    private float n = 0.55f;
    private PathMeasure o = new PathMeasure();
    private Path p = new Path();
    private Path q = new Path();
    private float r;
    private long s;
    private long t = 500;
    private float u;

    public ProgressView(Context context) {
        super(context);
        a();
    }

    public ProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ProgressView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    @TargetApi(21)
    public ProgressView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a();
    }

    private void a() {
        this.i.setAntiAlias(true);
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setStrokeCap(Paint.Cap.ROUND);
        this.i.setStrokeJoin(Paint.Join.ROUND);
        int i2 = (int) (getContext().getResources().getDisplayMetrics().density * 3.0f);
        this.j = i2;
        this.k = i2;
        this.m = -16777216;
        this.e = -65536;
        this.d = -65536;
    }

    public void setColor(int i2, int i3, int i4) {
        this.d = i2;
        this.e = i3;
        this.m = i4;
        invalidate();
    }

    public void setSuccessTime(long j2) {
        this.t = j2;
    }

    public void setWidth(int i2, int i3) {
        this.j = i2;
        this.k = i3;
        requestLayout();
    }

    public void setWidth(int i2, int i3, float f2) {
        this.j = i2;
        this.k = i3;
        this.n = f2;
        requestLayout();
    }

    public void setProgress(float f2) {
        if (this.f < 0.0f) {
            this.g = f2;
        }
        if (f2 != this.h) {
            this.r = 0.0f;
            this.s = 0;
        }
        this.f = f2;
    }

    public float getProgress() {
        return Math.min(this.f, this.h);
    }

    public void setMaxProgress(float f2) {
        this.h = f2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.l.set(((float) getPaddingLeft()) + (((float) this.k) / 2.0f), ((float) getPaddingTop()) + (((float) this.k) / 2.0f), ((float) (getWidth() - getPaddingRight())) - (((float) this.k) / 2.0f), ((float) (getHeight() - getPaddingBottom())) - (((float) this.k) / 2.0f));
        int width = getWidth() / 2;
        int i6 = (int) (this.n * ((float) width));
        this.p.reset();
        double height = (double) (getHeight() / 2);
        double d2 = (double) i6;
        Double.isNaN(d2);
        Double.isNaN(height);
        int i7 = (int) (height + (d2 / 1.7d));
        float f2 = (float) (width - (i6 / 10));
        float f3 = (float) i6;
        float f4 = 0.5f * f3;
        float f5 = f2 - (1.0f * f4);
        float f6 = (float) i7;
        float f7 = f6 - f4;
        this.q.moveTo(f5, f7);
        this.p.moveTo(f5, f7);
        this.p.lineTo(f2, f6);
        float f8 = f3 * 0.8f;
        this.p.lineTo(f2 + f8, f6 - (f8 * 1.4f));
        this.o.setPath(this.p, false);
        this.u = this.o.getLength();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        long currentTimeMillis = System.currentTimeMillis();
        this.i.setColor(this.d);
        this.i.setStrokeWidth((float) this.j);
        this.i.setAntiAlias(true);
        this.i.setStrokeCap(Paint.Cap.ROUND);
        if (this.f >= this.h) {
            if (this.s != 0) {
                this.r += (this.u * ((float) (currentTimeMillis - this.s))) / ((float) this.t);
            }
            this.s = currentTimeMillis;
            if (this.r < this.u) {
                postInvalidate();
            } else {
                this.r = this.u;
            }
            this.o.getSegment(0.0f, this.r, this.q, false);
            canvas.drawPath(this.q, this.i);
        }
        this.i.setColor(this.m);
        this.i.setStrokeWidth((float) this.k);
        canvas.drawArc(this.l, 0.0f, 360.0f, false, this.i);
        this.i.setColor(this.e);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        if (this.f > 0.0f) {
            if (this.g != this.f) {
                this.g += Math.max(1.0f, (this.f - this.g) / 10.0f);
                postInvalidate();
            }
            canvas.drawArc(this.l, 270.0f, (this.g * 360.0f) / this.h, false, this.i);
            return;
        }
        canvas.rotate((float) ((currentTimeMillis / 4) % 360), (float) width, (float) height);
        canvas.drawArc(this.l, 270.0f, 340.0f, false, this.i);
        postInvalidate();
    }
}
