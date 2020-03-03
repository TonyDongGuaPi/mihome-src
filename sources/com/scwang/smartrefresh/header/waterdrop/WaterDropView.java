package com.scwang.smartrefresh.header.waterdrop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.libra.Color;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xiaomi.mishopsdk.util.SystemBarTintManager;

public class WaterDropView extends View {
    protected static final int BACK_ANIM_DURATION = 180;
    protected static int STROKE_WIDTH = 2;
    protected Circle bottomCircle = new Circle();
    protected int mMaxCircleRadius;
    protected int mMinCircleRadius;
    protected Paint mPaint = new Paint();
    protected Path mPath = new Path();
    protected Circle topCircle = new Circle();

    public void updateCompleteState(int i, int i2) {
    }

    public WaterDropView(Context context) {
        super(context);
        this.mPaint.setColor(Color.c);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint = this.mPaint;
        int a2 = DensityUtil.a(1.0f);
        STROKE_WIDTH = a2;
        paint.setStrokeWidth((float) a2);
        this.mPaint.setShadowLayer((float) STROKE_WIDTH, (float) (STROKE_WIDTH / 2), (float) STROKE_WIDTH, SystemBarTintManager.DEFAULT_TINT_COLOR);
        setLayerType(1, (Paint) null);
        int i = STROKE_WIDTH * 4;
        setPadding(i, i, i, i);
        this.mPaint.setColor(Color.c);
        this.mMaxCircleRadius = DensityUtil.a(20.0f);
        this.mMinCircleRadius = this.mMaxCircleRadius / 5;
        this.topCircle.c = (float) this.mMaxCircleRadius;
        this.bottomCircle.c = (float) this.mMaxCircleRadius;
        this.topCircle.f8761a = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.topCircle.b = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.bottomCircle.f8761a = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.bottomCircle.b = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
    }

    public int getMaxCircleRadius() {
        return this.mMaxCircleRadius;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(((this.mMaxCircleRadius + STROKE_WIDTH) * 2) + getPaddingLeft() + getPaddingRight(), View.resolveSize(((int) Math.ceil((double) (this.bottomCircle.b + this.bottomCircle.c + ((float) (STROKE_WIDTH * 2))))) + getPaddingTop() + getPaddingBottom(), i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateCompleteState(getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        canvas.save();
        float f = (float) height;
        float f2 = (float) paddingTop;
        float f3 = (float) paddingBottom;
        if (f <= (this.topCircle.c * 2.0f) + f2 + f3) {
            canvas.translate((float) paddingLeft, (f - (this.topCircle.c * 2.0f)) - f3);
            canvas.drawCircle(this.topCircle.f8761a, this.topCircle.b, this.topCircle.c, this.mPaint);
        } else {
            canvas.translate((float) paddingLeft, f2);
            a();
            canvas.drawPath(this.mPath, this.mPaint);
        }
        canvas.restore();
    }

    private void a() {
        this.mPath.reset();
        this.mPath.addCircle(this.topCircle.f8761a, this.topCircle.b, this.topCircle.c, Path.Direction.CCW);
        if (this.bottomCircle.b > this.topCircle.b + ((float) DensityUtil.a(1.0f))) {
            this.mPath.addCircle(this.bottomCircle.f8761a, this.bottomCircle.b, this.bottomCircle.c, Path.Direction.CCW);
            double angle = getAngle();
            double d = (double) this.topCircle.f8761a;
            double d2 = (double) this.topCircle.c;
            double cos = Math.cos(angle);
            Double.isNaN(d2);
            Double.isNaN(d);
            double d3 = (double) this.topCircle.b;
            double d4 = (double) this.topCircle.c;
            double sin = Math.sin(angle);
            Double.isNaN(d4);
            Double.isNaN(d3);
            float f = (float) (d3 + (d4 * sin));
            double d5 = (double) this.topCircle.f8761a;
            double d6 = (double) this.topCircle.c;
            double cos2 = Math.cos(angle);
            Double.isNaN(d6);
            Double.isNaN(d5);
            double d7 = (double) this.bottomCircle.f8761a;
            double d8 = (double) this.bottomCircle.c;
            double cos3 = Math.cos(angle);
            Double.isNaN(d8);
            Double.isNaN(d7);
            double d9 = (double) this.bottomCircle.b;
            double d10 = (double) this.bottomCircle.c;
            double sin2 = Math.sin(angle);
            Double.isNaN(d10);
            Double.isNaN(d9);
            float f2 = (float) (d9 + (d10 * sin2));
            double d11 = (double) this.bottomCircle.f8761a;
            double d12 = (double) this.bottomCircle.c;
            double cos4 = Math.cos(angle);
            Double.isNaN(d12);
            Double.isNaN(d11);
            float f3 = (float) (d11 + (d12 * cos4));
            this.mPath.moveTo(this.topCircle.f8761a, this.topCircle.b);
            this.mPath.lineTo((float) (d - (d2 * cos)), f);
            this.mPath.quadTo(this.bottomCircle.f8761a - this.bottomCircle.c, (this.bottomCircle.b + this.topCircle.b) / 2.0f, (float) (d7 - (d8 * cos3)), f2);
            this.mPath.lineTo(f3, f2);
            this.mPath.quadTo(this.bottomCircle.f8761a + this.bottomCircle.c, (this.bottomCircle.b + f) / 2.0f, (float) (d5 + (d6 * cos2)), f);
        }
        this.mPath.close();
    }

    private double getAngle() {
        if (this.bottomCircle.c > this.topCircle.c) {
            return 0.0d;
        }
        return Math.asin((double) ((this.topCircle.c - this.bottomCircle.c) / (this.bottomCircle.b - this.topCircle.b)));
    }

    public ValueAnimator createAnimator() {
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{1.0f, 0.001f}).setDuration(180);
        duration.setInterpolator(new DecelerateInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WaterDropView.this.updateCompleteState(((Float) valueAnimator.getAnimatedValue()).floatValue());
                WaterDropView.this.postInvalidate();
            }
        });
        return duration;
    }

    public void updateCompleteState(float f) {
        double d = (double) this.mMaxCircleRadius;
        double d2 = (double) f;
        Double.isNaN(d2);
        double d3 = (double) this.mMaxCircleRadius;
        Double.isNaN(d3);
        Double.isNaN(d);
        float f2 = (((float) (this.mMinCircleRadius - this.mMaxCircleRadius)) * f) + ((float) this.mMaxCircleRadius);
        float f3 = f * 4.0f * ((float) this.mMaxCircleRadius);
        this.topCircle.c = (float) (d - ((d2 * 0.25d) * d3));
        this.bottomCircle.c = f2;
        this.bottomCircle.b = this.topCircle.b + f3;
    }

    public void updateCompleteState(int i) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        float f = (float) ((this.mMaxCircleRadius * 2) + paddingTop + paddingBottom);
        float f2 = (float) i;
        if (f2 < f) {
            this.topCircle.c = (float) this.mMaxCircleRadius;
            this.bottomCircle.c = (float) this.mMaxCircleRadius;
            this.bottomCircle.b = this.topCircle.b;
            return;
        }
        float max = Math.max(0.0f, f2 - f);
        double d = (double) ((float) (this.mMaxCircleRadius - this.mMinCircleRadius));
        Double.isNaN(d);
        float pow = (float) (d * (1.0d - Math.pow(100.0d, (double) ((-max) / ((float) DensityUtil.a(200.0f))))));
        this.topCircle.c = ((float) this.mMaxCircleRadius) - (pow / 4.0f);
        this.bottomCircle.c = ((float) this.mMaxCircleRadius) - pow;
        this.bottomCircle.b = ((float) ((i - paddingTop) - paddingBottom)) - this.bottomCircle.c;
    }

    public Circle getTopCircle() {
        return this.topCircle;
    }

    public Circle getBottomCircle() {
        return this.bottomCircle;
    }

    public void setIndicatorColor(@ColorInt int i) {
        this.mPaint.setColor(i);
    }

    public int getIndicatorColor() {
        return this.mPaint.getColor();
    }
}
