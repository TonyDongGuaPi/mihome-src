package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class SmartBarView extends View {
    int mAnimationTime = 3000;
    int mColor;
    Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    Paint mPaint = new Paint();
    int mScore;
    long mStartTime;

    public SmartBarView(Context context) {
        super(context);
    }

    public SmartBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SmartBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setColorAndScore(int i, int i2) {
        this.mColor = i;
        this.mScore = i2;
    }

    public void start() {
        this.mStartTime = System.currentTimeMillis();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-6513508);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setStyle(Paint.Style.STROKE);
        int height = (getHeight() * 2) / 3;
        float f = (float) height;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), f, f, this.mPaint);
        this.mPaint.setColor(this.mColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        float f2 = (float) (height - 4);
        canvas.drawRoundRect(new RectF(2.0f, 2.0f, (float) ((((getWidth() - 4) * this.mScore) / 100) + 2), (float) (getHeight() - 2)), f2, f2, this.mPaint);
    }
}
