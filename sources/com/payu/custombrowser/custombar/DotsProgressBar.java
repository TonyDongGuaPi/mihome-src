package com.payu.custombrowser.custombar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.payu.custombrowser.R;

public class DotsProgressBar extends View {
    private int heightSize;
    /* access modifiers changed from: private */
    public boolean isDotProgressStopped;
    /* access modifiers changed from: private */
    public int mDotCount = 5;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public int mIndex = 0;
    private float mOuterRadius;
    private final Paint mPaint = new Paint(1);
    private final Paint mPaintFill = new Paint(1);
    private float mRadius;
    /* access modifiers changed from: private */
    public final Runnable mRunnable = new Runnable() {
        public void run() {
            int unused = DotsProgressBar.this.mIndex = DotsProgressBar.this.mIndex + DotsProgressBar.this.step;
            if (DotsProgressBar.this.mIndex < 0) {
                int unused2 = DotsProgressBar.this.mIndex = 1;
                int unused3 = DotsProgressBar.this.step = 1;
            } else if (DotsProgressBar.this.mIndex > DotsProgressBar.this.mDotCount - 1) {
                int unused4 = DotsProgressBar.this.mIndex = 0;
                int unused5 = DotsProgressBar.this.step = 1;
            }
            if (!DotsProgressBar.this.isDotProgressStopped) {
                DotsProgressBar.this.invalidate();
                DotsProgressBar.this.mHandler.postDelayed(DotsProgressBar.this.mRunnable, 400);
            }
        }
    };
    private final int margin = 10;
    /* access modifiers changed from: private */
    public int step = 1;
    private int widthSize;

    public DotsProgressBar(Context context) {
        super(context);
        init(context);
    }

    public DotsProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public DotsProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mRadius = context.getResources().getDimension(R.dimen.cb_circle_indicator_radius);
        this.mOuterRadius = context.getResources().getDimension(R.dimen.cb_circle_indicator_outer_radius);
        this.mPaintFill.setStyle(Paint.Style.FILL);
        this.mPaintFill.setColor(context.getResources().getColor(R.color.cb_payu_blue));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(855638016);
        start();
    }

    public void setDotsCount(int i) {
        this.mDotCount = i;
    }

    public void start() {
        this.mIndex = -1;
        this.isDotProgressStopped = false;
        this.mHandler.removeCallbacks(this.mRunnable);
        this.mHandler.post(this.mRunnable);
    }

    public void stop() {
        if (this.mHandler != null && this.mRunnable != null) {
            this.isDotProgressStopped = true;
            this.mHandler.removeCallbacks(this.mRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.widthSize = (int) ((this.mRadius * 2.0f * ((float) this.mDotCount)) + ((float) (this.mDotCount * 10)) + 10.0f + (this.mOuterRadius - this.mRadius));
        this.heightSize = (((int) this.mRadius) * 2) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(this.widthSize, this.heightSize);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = ((((float) this.widthSize) - ((((float) this.mDotCount) * this.mRadius) * 2.0f)) - ((float) ((this.mDotCount - 1) * 10))) / 2.0f;
        float f2 = (float) (this.heightSize / 2);
        for (int i = 0; i < this.mDotCount; i++) {
            if (i == this.mIndex) {
                canvas.drawCircle(f, f2, this.mOuterRadius, this.mPaintFill);
            } else {
                canvas.drawCircle(f, f2, this.mRadius, this.mPaint);
            }
            f += (this.mRadius * 2.0f) + 10.0f;
        }
    }
}
