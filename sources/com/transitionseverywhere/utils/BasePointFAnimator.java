package com.transitionseverywhere.utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.PointF;
import java.lang.ref.WeakReference;

@TargetApi(14)
public abstract class BasePointFAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference f9495a;
    private PointFProperty b;
    private PointF c = new PointF();

    /* access modifiers changed from: protected */
    public abstract void a(PointF pointF, float f);

    protected BasePointFAnimator(Object obj, PointFProperty pointFProperty) {
        this.f9495a = new WeakReference(obj);
        this.b = pointFProperty;
        setFloatValues(new float[]{0.0f, 1.0f});
        addUpdateListener(this);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Object obj = this.f9495a.get();
        if (obj == null) {
            cancel();
            return;
        }
        a(this.c, valueAnimator.getAnimatedFraction());
        this.b.set(obj, this.c);
    }
}
