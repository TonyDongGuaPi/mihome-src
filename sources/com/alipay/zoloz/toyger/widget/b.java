package com.alipay.zoloz.toyger.widget;

import android.animation.ValueAnimator;

class b implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CircleUploadPattern f1207a;

    b(CircleUploadPattern circleUploadPattern) {
        this.f1207a = circleUploadPattern;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int parseInt = Integer.parseInt(this.f1207a.mValueAnimator.getAnimatedValue() + "");
        int unused = this.f1207a.processsAngle = this.f1207a.processsAngle + 4;
        this.f1207a.mUploadProgressBar.setProgressAngle(this.f1207a.processsAngle);
        if (30 == parseInt) {
            this.f1207a.mValueAnimator.cancel();
            this.f1207a.mValueAnimator = null;
        }
    }
}
