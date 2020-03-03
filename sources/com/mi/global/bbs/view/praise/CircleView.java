package com.mi.global.bbs.view.praise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

public class CircleView extends View {
    public static final Property<CircleView, Float> CIRCLE_RADIUS_PROGRESS = new Property<CircleView, Float>(Float.class, "circleRadiusProgress") {
        public Float get(CircleView circleView) {
            return Float.valueOf(circleView.getCircleRadiusProgress());
        }

        public void set(CircleView circleView, Float f) {
            circleView.setCircleRadiusProgress(f.floatValue());
        }
    };
    private int CIRCLE_COLOR = -16121;
    private Paint circlePaint = new Paint();
    private float circleRadiusProgress = 0.0f;
    private int height = 0;
    private Paint maskPaint = new Paint();
    private int maxCircleSize;
    private Bitmap tempBitmap;
    private Canvas tempCanvas;
    private int width = 0;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.circlePaint.setAlpha(0);
        this.circlePaint.setColor(this.CIRCLE_COLOR);
        this.maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.width != 0 && this.height != 0) {
            setMeasuredDimension(this.width, this.height);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.maxCircleSize = i / 2;
        this.tempBitmap = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
        this.tempCanvas = new Canvas(this.tempBitmap);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.tempCanvas.drawColor(16777215, PorterDuff.Mode.CLEAR);
        this.tempCanvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.circleRadiusProgress * ((float) this.maxCircleSize), this.circlePaint);
        this.tempCanvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (this.circleRadiusProgress * ((float) this.maxCircleSize)) / 3.0f, this.maskPaint);
        canvas.drawBitmap(this.tempBitmap, 0.0f, 0.0f, (Paint) null);
    }

    public void setCircleRadiusProgress(float f) {
        this.circleRadiusProgress = f;
        updateCircleAlpha();
        postInvalidate();
    }

    private void updateCircleAlpha() {
        this.circlePaint.setAlpha((int) PraiseUtils.mapValueFromRangeToRange((double) ((float) PraiseUtils.clamp((double) this.circleRadiusProgress, 0.8999999761581421d, 1.0d)), 0.6000000238418579d, 1.0d, 255.0d, 0.0d));
    }

    public float getCircleRadiusProgress() {
        return this.circleRadiusProgress;
    }

    public void setColor(@ColorInt int i) {
        this.CIRCLE_COLOR = i;
        this.circlePaint.setColor(this.CIRCLE_COLOR);
        invalidate();
    }
}
