package com.mi.global.bbs.view.praise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

public class FireworkView extends View {
    public static final Property<FireworkView, Float> FIREWORK_PROGRESS = new Property<FireworkView, Float>(Float.class, "fireworkProgress") {
        public Float get(FireworkView fireworkView) {
            return Float.valueOf(fireworkView.getCurrentProgress());
        }

        public void set(FireworkView fireworkView, Float f) {
            fireworkView.setCurrentProgress(f.floatValue());
        }
    };
    private static final int LINES_COUNT = 5;
    private static final int LINES_POSITION_ANGLE = 51;
    private int FIREWORK_COLOR = -43230;
    private int centerX;
    private int centerY;
    private float currentProgress = 0.0f;
    private float endRadius = 0.0f;
    private Paint fireworkPaint = new Paint();
    private int height = 0;
    private float maxFireworkRadius;
    private float startRadius = 0.0f;
    private int width = 0;

    public FireworkView(Context context) {
        super(context);
        init();
    }

    public FireworkView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FireworkView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.fireworkPaint.setStyle(Paint.Style.FILL);
        this.fireworkPaint.setStrokeWidth(5.0f);
        this.fireworkPaint.setStrokeCap(Paint.Cap.ROUND);
        this.fireworkPaint.setAlpha(0);
        this.fireworkPaint.setColor(this.FIREWORK_COLOR);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = i / 2;
        this.centerX = i5;
        this.centerY = i2 / 2;
        this.maxFireworkRadius = (float) i5;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            double d = (double) this.centerX;
            double d2 = (double) this.startRadius;
            double d3 = (double) (((2 - i) * 51) + 270);
            Double.isNaN(d3);
            double d4 = (d3 * 3.141592653589793d) / 180.0d;
            double cos = Math.cos(d4);
            Double.isNaN(d2);
            Double.isNaN(d);
            double d5 = (double) this.centerY;
            double d6 = (double) this.startRadius;
            double sin = Math.sin(d4);
            Double.isNaN(d6);
            Double.isNaN(d5);
            double d7 = (double) this.centerX;
            double d8 = (double) this.endRadius;
            double cos2 = Math.cos(d4);
            Double.isNaN(d8);
            Double.isNaN(d7);
            double d9 = (double) this.centerY;
            double d10 = (double) this.endRadius;
            double sin2 = Math.sin(d4);
            Double.isNaN(d10);
            Double.isNaN(d9);
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) ((int) (d + (d2 * cos))), (float) ((int) (d5 + (d6 * sin))), (float) ((int) (d7 + (d8 * cos2))), (float) ((int) (d9 + (d10 * sin2))), this.fireworkPaint);
        }
    }

    public void setCurrentProgress(float f) {
        this.currentProgress = f;
        updateLinesPosition();
        updateFireworkAlpha();
        postInvalidate();
    }

    public float getCurrentProgress() {
        return this.currentProgress;
    }

    private void updateLinesPosition() {
        this.startRadius = ((this.currentProgress * 0.8f) + 0.2f) * this.maxFireworkRadius;
        this.endRadius = ((this.currentProgress * 0.7f) + 0.3f) * this.maxFireworkRadius;
    }

    public void setColor(@ColorInt int i) {
        this.FIREWORK_COLOR = i;
        this.fireworkPaint.setColor(i);
        invalidate();
    }

    private void updateFireworkAlpha() {
        this.fireworkPaint.setAlpha((int) PraiseUtils.mapValueFromRangeToRange((double) ((float) PraiseUtils.clamp((double) this.currentProgress, 0.6000000238418579d, 1.0d)), 0.6000000238418579d, 1.0d, 255.0d, 0.0d));
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.fireworkPaint.setStrokeWidth((float) (i / 20));
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.width != 0 && this.height != 0) {
            setMeasuredDimension(this.width, this.height);
        }
    }
}
