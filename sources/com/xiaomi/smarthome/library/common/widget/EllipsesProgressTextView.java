package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;

public class EllipsesProgressTextView extends AppCompatTextView {
    private static final int ANIM_STEP_FORWARD = 1;
    private boolean mAnimating = false;
    private int mCurrentEllipseCount = 0;
    private int mEllipseCount = 3;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                EllipsesProgressTextView.this.stepForward();
            }
        }
    };
    private int mSpeed = 500;
    private StringBuilder stringBuilder = new StringBuilder();

    public EllipsesProgressTextView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public EllipsesProgressTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public EllipsesProgressTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EllipsesProgressTextView);
        this.mEllipseCount = obtainStyledAttributes.getDimensionPixelSize(0, 3);
        if (this.mEllipseCount <= 1) {
            this.mEllipseCount = 2;
        }
        this.mSpeed = obtainStyledAttributes.getDimensionPixelSize(1, 500);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopProgressAnim();
    }

    public boolean isAnimating() {
        return this.mAnimating;
    }

    public void startProgressAnim() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mCurrentEllipseCount = this.mEllipseCount;
        this.mHandler.sendEmptyMessage(1);
        this.mAnimating = true;
    }

    public void stopProgressAnim() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mCurrentEllipseCount = this.mEllipseCount;
        this.mAnimating = false;
    }

    private void updateUI() {
        int i = this.mCurrentEllipseCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.stringBuilder.append(".");
        }
        setText(this.stringBuilder.toString());
        this.stringBuilder.delete(0, this.stringBuilder.length());
    }

    /* access modifiers changed from: private */
    public void stepForward() {
        this.mCurrentEllipseCount = (this.mCurrentEllipseCount % this.mEllipseCount) + 1;
        updateUI();
        this.mHandler.sendEmptyMessageDelayed(1, (long) this.mSpeed);
    }
}
