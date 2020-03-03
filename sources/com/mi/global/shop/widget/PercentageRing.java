package com.mi.global.shop.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.mi.global.shop.R;
import com.taobao.weex.el.parse.Operators;

public class PercentageRing extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f7162a;
    private Paint b;
    private Paint c;
    private Paint d;
    private int e;
    private int f;
    private float g;
    private RectF h;
    private float i;
    private float j;
    private float k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;

    public PercentageRing(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public PercentageRing(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PercentageRing);
        this.m = obtainStyledAttributes.getColor(R.styleable.PercentageRing_circleBackground, -5262117);
        this.n = obtainStyledAttributes.getColor(R.styleable.PercentageRing_ringColor, -9875295);
        this.o = obtainStyledAttributes.getColor(R.styleable.PercentageRing_antiRingColor, 16777215);
        this.l = obtainStyledAttributes.getInt(R.styleable.PercentageRing_radius1, 60);
        this.q = obtainStyledAttributes.getColor(R.styleable.PercentageRing_textColor, -1);
        obtainStyledAttributes.recycle();
        a(context);
    }

    public PercentageRing(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.i = -90.0f;
        this.g = 0.0f;
        this.k = 0.0f;
        this.f7162a = new Paint();
        this.f7162a.setAntiAlias(true);
        this.f7162a.setColor(this.m);
        this.f7162a.setStyle(Paint.Style.FILL);
        this.b = new Paint();
        this.b.setColor(this.q);
        this.b.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
        Paint paint = this.b;
        double d2 = (double) this.l;
        Double.isNaN(d2);
        paint.setStrokeWidth((float) (d2 * 0.025d));
        this.b.setTextSize((float) (this.l / 2));
        this.b.setTextAlign(Paint.Align.CENTER);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setColor(this.n);
        this.c.setStyle(Paint.Style.STROKE);
        Paint paint2 = this.c;
        double d3 = (double) this.l;
        Double.isNaN(d3);
        paint2.setStrokeWidth((float) (d3 * 0.075d));
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setColor(this.o);
        this.d.setStyle(Paint.Style.STROKE);
        Paint paint3 = this.d;
        double d4 = (double) this.l;
        Double.isNaN(d4);
        paint3.setStrokeWidth((float) (d4 * 0.075d));
        this.p = (int) this.b.getTextSize();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(a(i2), a(i2));
        this.e = getMeasuredWidth() / 2;
        this.f = getMeasuredHeight() / 2;
        if (this.l > this.e) {
            this.l = this.e;
            double d2 = (double) this.e;
            double d3 = (double) this.l;
            Double.isNaN(d3);
            Double.isNaN(d2);
            this.l = (int) (d2 - (d3 * 0.075d));
            Paint paint = this.b;
            double d4 = (double) this.l;
            Double.isNaN(d4);
            paint.setStrokeWidth((float) (d4 * 0.025d));
            this.b.setTextSize((float) (this.l / 2));
            Paint paint2 = this.c;
            double d5 = (double) this.l;
            Double.isNaN(d5);
            paint2.setStrokeWidth((float) (d5 * 0.075d));
            Paint paint3 = this.d;
            double d6 = (double) this.l;
            Double.isNaN(d6);
            paint3.setStrokeWidth((float) (d6 * 0.075d));
            this.p = (int) this.b.getTextSize();
        }
        this.h = new RectF((float) (this.e - this.l), (float) (this.f - this.l), (float) (this.e + this.l), (float) (this.f + this.l));
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        double d2 = (double) this.l;
        Double.isNaN(d2);
        int i3 = (int) (d2 * 1.075d * 2.0d);
        return mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) this.e, (float) this.f, (float) this.l, this.f7162a);
        canvas.drawArc(this.h, this.i, this.g, false, this.c);
        canvas.drawArc(this.h, this.g + this.i, 360.0f - this.g, false, this.d);
        canvas.drawText(this.k + Operators.MOD, (float) this.e, (float) (this.f + (this.p / 4)), this.b);
        if (this.k < this.j) {
            this.k += 1.0f;
            double d2 = (double) this.g;
            Double.isNaN(d2);
            this.g = (float) (d2 + 3.6d);
            postInvalidateDelayed(10);
        }
    }

    public void setTargetPercent(int i2) {
        this.j = (float) i2;
    }

    public void setCurrentPercent(float f2) {
        if (this.k != f2) {
            this.k = f2;
            this.g = this.k * 3.6f;
            invalidate();
        }
    }
}
