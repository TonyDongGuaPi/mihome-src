package com.mi.global.bbs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.mi.global.bbs.R;
import com.taobao.weex.el.parse.Operators;

public class PercentageRing extends View {
    private Paint mAntiArcPaint;
    private int mAntiRingColor;
    private Paint mArcPaint;
    private RectF mArcRectF;
    private int mCircleBackground;
    private Paint mCirclePaint;
    private int mCircleX;
    private int mCircleY;
    private float mCurrentAngle;
    private float mCurrentPercent;
    private int mRadius;
    private int mRingColor;
    private float mStartSweepValue;
    private float mTargetPercent;
    private int mTextColor;
    private Paint mTextPaint;
    private int mTextSize;

    public PercentageRing(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public PercentageRing(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PercentageRing);
        this.mCircleBackground = obtainStyledAttributes.getColor(R.styleable.PercentageRing_circleBackground, -5262117);
        this.mRingColor = obtainStyledAttributes.getColor(R.styleable.PercentageRing_ringColor, -9875295);
        this.mAntiRingColor = obtainStyledAttributes.getColor(R.styleable.PercentageRing_antiRingColor, 16777215);
        this.mRadius = obtainStyledAttributes.getInt(R.styleable.PercentageRing_radius1, 60);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.PercentageRing_textColor, -1);
        obtainStyledAttributes.recycle();
        init(context);
    }

    public PercentageRing(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mStartSweepValue = -90.0f;
        this.mCurrentAngle = 0.0f;
        this.mCurrentPercent = 0.0f;
        this.mCirclePaint = new Paint();
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setColor(this.mCircleBackground);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setStyle(Paint.Style.FILL);
        Paint paint = this.mTextPaint;
        double d = (double) this.mRadius;
        Double.isNaN(d);
        paint.setStrokeWidth((float) (d * 0.025d));
        this.mTextPaint.setTextSize((float) (this.mRadius / 2));
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mArcPaint = new Paint();
        this.mArcPaint.setAntiAlias(true);
        this.mArcPaint.setColor(this.mRingColor);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = this.mArcPaint;
        double d2 = (double) this.mRadius;
        Double.isNaN(d2);
        paint2.setStrokeWidth((float) (d2 * 0.075d));
        this.mAntiArcPaint = new Paint();
        this.mAntiArcPaint.setAntiAlias(true);
        this.mAntiArcPaint.setColor(this.mAntiRingColor);
        this.mAntiArcPaint.setStyle(Paint.Style.STROKE);
        Paint paint3 = this.mAntiArcPaint;
        double d3 = (double) this.mRadius;
        Double.isNaN(d3);
        paint3.setStrokeWidth((float) (d3 * 0.075d));
        this.mTextSize = (int) this.mTextPaint.getTextSize();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(measure(i), measure(i));
        this.mCircleX = getMeasuredWidth() / 2;
        this.mCircleY = getMeasuredHeight() / 2;
        if (this.mRadius > this.mCircleX) {
            this.mRadius = this.mCircleX;
            double d = (double) this.mCircleX;
            double d2 = (double) this.mRadius;
            Double.isNaN(d2);
            Double.isNaN(d);
            this.mRadius = (int) (d - (d2 * 0.075d));
            Paint paint = this.mTextPaint;
            double d3 = (double) this.mRadius;
            Double.isNaN(d3);
            paint.setStrokeWidth((float) (d3 * 0.025d));
            this.mTextPaint.setTextSize((float) (this.mRadius / 2));
            Paint paint2 = this.mArcPaint;
            double d4 = (double) this.mRadius;
            Double.isNaN(d4);
            paint2.setStrokeWidth((float) (d4 * 0.075d));
            Paint paint3 = this.mAntiArcPaint;
            double d5 = (double) this.mRadius;
            Double.isNaN(d5);
            paint3.setStrokeWidth((float) (d5 * 0.075d));
            this.mTextSize = (int) this.mTextPaint.getTextSize();
        }
        this.mArcRectF = new RectF((float) (this.mCircleX - this.mRadius), (float) (this.mCircleY - this.mRadius), (float) (this.mCircleX + this.mRadius), (float) (this.mCircleY + this.mRadius));
    }

    private int measure(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        double d = (double) this.mRadius;
        Double.isNaN(d);
        int i2 = (int) (d * 1.075d * 2.0d);
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) this.mCircleX, (float) this.mCircleY, (float) this.mRadius, this.mCirclePaint);
        canvas.drawArc(this.mArcRectF, this.mStartSweepValue, this.mCurrentAngle, false, this.mArcPaint);
        canvas.drawArc(this.mArcRectF, this.mCurrentAngle + this.mStartSweepValue, 360.0f - this.mCurrentAngle, false, this.mAntiArcPaint);
        canvas.drawText(String.valueOf(this.mCurrentPercent) + Operators.MOD, (float) this.mCircleX, (float) (this.mCircleY + (this.mTextSize / 4)), this.mTextPaint);
        if (this.mCurrentPercent < this.mTargetPercent) {
            this.mCurrentPercent += 1.0f;
            double d = (double) this.mCurrentAngle;
            Double.isNaN(d);
            this.mCurrentAngle = (float) (d + 3.6d);
            postInvalidateDelayed(10);
        }
    }

    public void setTargetPercent(int i) {
        this.mTargetPercent = (float) i;
    }

    public void setCurrentPercent(float f) {
        if (this.mCurrentPercent != f) {
            this.mCurrentPercent = f;
            this.mCurrentAngle = this.mCurrentPercent * 3.6f;
            invalidate();
        }
    }
}
