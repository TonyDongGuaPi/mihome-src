package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

public class SmartCircleView extends View {
    int count = 0;
    int mAnimationTime = 3000;
    int mColor;
    Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    Paint mPaint = new Paint();
    int mScore;
    TextView mScoreView;
    long mStartTime;

    /* access modifiers changed from: package-private */
    public void initial() {
    }

    public SmartCircleView(Context context) {
        super(context);
        initial();
    }

    public SmartCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initial();
    }

    public SmartCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initial();
    }

    public void setScore(int i, TextView textView) {
        this.mScore = i;
        this.mScoreView = textView;
    }

    public void start() {
        this.mStartTime = System.currentTimeMillis();
        invalidate();
    }

    public void resetStartAnimation() {
        this.mStartTime = 0;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.mScore != 0) {
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(-2040355);
            this.mPaint.setStrokeWidth(3.0f);
            this.mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) ((getWidth() - 20) / 2), this.mPaint);
            this.mPaint.setColor(-750049);
            this.mPaint.setStrokeWidth((float) 20);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float currentTimeMillis = (float) (System.currentTimeMillis() - this.mStartTime);
            if (currentTimeMillis > ((float) this.mAnimationTime)) {
                f = 1.0f;
            } else {
                f = currentTimeMillis / ((float) this.mAnimationTime);
                postInvalidate();
            }
            float interpolation = this.mInterpolator.getInterpolation(f);
            float f2 = (float) 10;
            canvas.drawArc(new RectF(f2, f2, (float) (getWidth() - 10), (float) (getHeight() - 10)), 135.0f, (((float) (this.mScore * 360)) * interpolation) / 100.0f, false, this.mPaint);
            this.count++;
            if (this.mScoreView == null) {
                return;
            }
            if (this.count % 5 == 0 || ((double) interpolation) == 1.0d) {
                this.mScoreView.setText(String.valueOf((int) (((float) this.mScore) * interpolation)));
            }
        }
    }
}
