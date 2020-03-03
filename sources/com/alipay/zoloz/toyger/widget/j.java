package com.alipay.zoloz.toyger.widget;

import android.animation.ValueAnimator;

class j implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f1215a;
    final /* synthetic */ ToygerCirclePattern b;

    j(ToygerCirclePattern toygerCirclePattern, int i) {
        this.b = toygerCirclePattern;
        this.f1215a = i;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ToygerCirclePattern toygerCirclePattern = this.b;
        int unused = toygerCirclePattern.mAnimateValue = Integer.parseInt(this.b.mValueAnimator.getAnimatedValue() + "");
        this.b.mRoundProgressBar.setProgress(this.b.mAnimateValue);
        if (this.f1215a == this.b.mAnimateValue) {
            this.b.mIsShowProcess = false;
            this.b.mValueAnimator.cancel();
            this.b.mValueAnimator = null;
        }
    }
}
