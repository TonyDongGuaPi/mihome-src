package com.payu.custombrowser.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.payu.custombrowser.R;
import java.util.Timer;
import java.util.TimerTask;

public class SnoozeLoaderView extends View {
    private Paint activeBarPaint;
    /* access modifiers changed from: private */
    public int blinkPosition = 0;
    private Rect firstBar;
    private Paint firstBarPaint;
    private Paint inActiveBarPaint;
    private int mActiveColor = Color.parseColor("#00adf2");
    Activity mActivity;
    private int mAnimationSpeed = 200;
    private int mBarHeight = 120;
    private int mBarHeightRatio = (this.mBarHeight / 3);
    private int mBarSpace = 70;
    private int mBarWidth = 40;
    private int mInActiveColor = Color.parseColor("#b0eafc");
    private Rect secondBar;
    private Paint secondBarPaint;
    /* access modifiers changed from: private */
    public boolean shouldStartAnimation = false;
    private Rect thirdBar;
    private Paint thirdBarPaint;
    private Timer timer;

    public SnoozeLoaderView(Context context) {
        super(context);
        this.mActivity = (Activity) context;
        init();
    }

    public SnoozeLoaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActivity = (Activity) context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SnoozeLoaderView, 0, 0);
        try {
            this.shouldStartAnimation = obtainStyledAttributes.getBoolean(R.styleable.SnoozeLoaderView_startAnimate, this.shouldStartAnimation);
            this.mActiveColor = obtainStyledAttributes.getColor(R.styleable.SnoozeLoaderView_activeBarColor, this.mActiveColor);
            this.mInActiveColor = obtainStyledAttributes.getColor(R.styleable.SnoozeLoaderView_inActiveBarColor, this.mInActiveColor);
            this.mBarWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barWidth, this.mBarWidth);
            this.mBarHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barHeight, this.mBarHeight);
            this.mBarHeightRatio = this.mBarHeight / 3;
            this.mBarSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barSpace, this.mBarSpace);
            this.mAnimationSpeed = obtainStyledAttributes.getInt(R.styleable.SnoozeLoaderView_animationSpeed, this.mAnimationSpeed);
            init();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public SnoozeLoaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mActivity = (Activity) context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SnoozeLoaderView, 0, 0);
        try {
            this.shouldStartAnimation = obtainStyledAttributes.getBoolean(R.styleable.SnoozeLoaderView_startAnimate, this.shouldStartAnimation);
            this.mActiveColor = obtainStyledAttributes.getColor(R.styleable.SnoozeLoaderView_activeBarColor, this.mActiveColor);
            this.mInActiveColor = obtainStyledAttributes.getColor(R.styleable.SnoozeLoaderView_inActiveBarColor, this.mInActiveColor);
            this.mBarWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barWidth, this.mBarWidth);
            this.mBarHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barHeight, this.mBarHeight);
            this.mBarHeightRatio = this.mBarHeight / 3;
            this.mBarSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnoozeLoaderView_barSpace, this.mBarSpace);
            init();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(this.firstBar, this.firstBarPaint);
        canvas.drawRect(this.secondBar, this.secondBarPaint);
        canvas.drawRect(this.thirdBar, this.thirdBarPaint);
    }

    public void updateBar(int i) {
        switch (i) {
            case 0:
                this.firstBarPaint = this.inActiveBarPaint;
                this.secondBarPaint = this.inActiveBarPaint;
                this.thirdBarPaint = this.inActiveBarPaint;
                break;
            case 1:
                this.firstBarPaint = this.activeBarPaint;
                this.secondBarPaint = this.inActiveBarPaint;
                this.thirdBarPaint = this.inActiveBarPaint;
                break;
            case 2:
                this.firstBarPaint = this.activeBarPaint;
                this.secondBarPaint = this.activeBarPaint;
                this.thirdBarPaint = this.inActiveBarPaint;
                break;
            case 3:
                this.firstBarPaint = this.activeBarPaint;
                this.secondBarPaint = this.activeBarPaint;
                this.thirdBarPaint = this.activeBarPaint;
                break;
            default:
                this.firstBarPaint = this.inActiveBarPaint;
                this.secondBarPaint = this.inActiveBarPaint;
                this.thirdBarPaint = this.inActiveBarPaint;
                break;
        }
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                SnoozeLoaderView.this.invalidate();
            }
        });
    }

    private void init() {
        this.activeBarPaint = new Paint();
        this.activeBarPaint.setColor(this.mActiveColor);
        this.activeBarPaint.setStyle(Paint.Style.FILL);
        this.inActiveBarPaint = new Paint();
        this.inActiveBarPaint.setColor(this.mInActiveColor);
        this.inActiveBarPaint.setStyle(Paint.Style.FILL);
        this.firstBarPaint = this.inActiveBarPaint;
        this.secondBarPaint = this.inActiveBarPaint;
        this.thirdBarPaint = this.inActiveBarPaint;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = i / 2;
        int i6 = i2 / 2;
        int i7 = i5 - (this.mBarWidth / 2);
        int i8 = i6 - (this.mBarHeight / 2);
        int i9 = ((i5 - this.mBarWidth) - this.mBarSpace) - (this.mBarWidth / 2);
        int i10 = (i6 - (this.mBarHeight / 2)) - this.mBarHeightRatio;
        int i11 = this.mBarHeight + i10 + this.mBarHeightRatio + this.mBarHeightRatio;
        int i12 = i5 + (this.mBarWidth / 2) + this.mBarSpace;
        int i13 = (i6 - (this.mBarHeight / 2)) + this.mBarHeightRatio;
        int i14 = ((this.mBarHeight + i13) - this.mBarHeightRatio) - this.mBarHeightRatio;
        this.secondBar = new Rect(i7, i8, this.mBarWidth + i7, this.mBarHeight + i8);
        this.firstBar = new Rect(i9, i10, this.mBarWidth + i9, i11);
        this.thirdBar = new Rect(i12, i13, this.mBarWidth + i12, i14);
        if (this.shouldStartAnimation) {
            startAnimation();
        }
    }

    public void startAnimation() {
        this.shouldStartAnimation = true;
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            public void run() {
                if (SnoozeLoaderView.this.blinkPosition == 4) {
                    int unused = SnoozeLoaderView.this.blinkPosition = 0;
                } else {
                    int unused2 = SnoozeLoaderView.this.blinkPosition = SnoozeLoaderView.this.blinkPosition + 1;
                }
                if (SnoozeLoaderView.this.shouldStartAnimation) {
                    SnoozeLoaderView.this.updateBar(SnoozeLoaderView.this.blinkPosition);
                } else {
                    cancel();
                }
            }
        }, 0, (long) this.mAnimationSpeed);
    }

    public void cancelAnimation() {
        this.shouldStartAnimation = false;
        if (this.timer != null) {
            this.timer.cancel();
            this.timer.purge();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension((this.mBarWidth * 3) + (this.mBarSpace * 2) + getPaddingLeft() + getPaddingRight(), this.mBarHeight + (this.mBarHeightRatio * 2) + getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.shouldStartAnimation = false;
    }
}
