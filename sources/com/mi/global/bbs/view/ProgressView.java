package com.mi.global.bbs.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private int completeColor = Color.parseColor("#ff9e00");
    private float completedDegree = 0.0f;
    private Paint mPaint;
    private int normalColor = Color.parseColor("#e4e4e4");
    private OnAnimationEndListener onAnimationEndListener;
    private float precentflag = 1.0f;
    private int process = 0;
    private int processAll = 1;
    private RectF rectF;

    public interface OnAnimationEndListener {
        void onCompleteAnimationEnd(int i, int i2);
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.FILL);
        if (this.completedDegree > 0.0f) {
            this.mPaint.setColor(this.completeColor);
        } else {
            this.mPaint.setColor(this.normalColor);
        }
        this.rectF = new RectF();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        this.rectF.left = (float) paddingLeft;
        this.rectF.top = (float) paddingTop;
        this.rectF.right = (float) (getWidth() - paddingRight);
        this.rectF.bottom = (float) (getHeight() - paddingBottom);
        this.mPaint.setColor(this.normalColor);
        canvas.drawRoundRect(this.rectF, 8.0f, 8.0f, this.mPaint);
        if (this.completedDegree > 0.0f) {
            this.mPaint.setColor(this.completeColor);
            this.rectF.right = ((float) (getWidth() - paddingRight)) * this.precentflag;
            canvas.drawRoundRect(this.rectF, 8.0f, 8.0f, this.mPaint);
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.precentflag = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void setCompleted(float f, int i, int i2) {
        this.process = i;
        this.processAll = i2;
        if (this.completedDegree != f) {
            this.completedDegree = f;
            playAnim(f);
            return;
        }
        invalidate();
    }

    private void playAnim(float f) {
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, f}).setDuration(400);
        duration.addUpdateListener(this);
        duration.addListener(this);
        duration.start();
    }

    public void onAnimationEnd(Animator animator) {
        if (this.onAnimationEndListener != null) {
            this.onAnimationEndListener.onCompleteAnimationEnd(this.process, this.processAll);
        }
    }

    public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener2) {
        this.onAnimationEndListener = onAnimationEndListener2;
    }
}
