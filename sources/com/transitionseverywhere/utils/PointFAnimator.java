package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.PointF;

@TargetApi(14)
public class PointFAnimator extends BasePointFAnimator {

    /* renamed from: a  reason: collision with root package name */
    private float f9500a;
    private float b;
    private float c;
    private float d;

    protected static float a(float f, float f2, float f3) {
        return f2 + ((f3 - f2) * f);
    }

    protected PointFAnimator(Object obj, PointFProperty pointFProperty) {
        super(obj, pointFProperty);
    }

    public static <T> PointFAnimator a(T t, PointFProperty<T> pointFProperty, float f, float f2, float f3, float f4) {
        if (t == null || pointFProperty == null) {
            return null;
        }
        PointFAnimator pointFAnimator = new PointFAnimator(t, pointFProperty);
        pointFAnimator.b = f;
        pointFAnimator.f9500a = f2;
        pointFAnimator.d = f3;
        pointFAnimator.c = f4;
        return pointFAnimator;
    }

    /* access modifiers changed from: protected */
    public void a(PointF pointF, float f) {
        pointF.x = a(f, this.b, this.d);
        pointF.y = a(f, this.f9500a, this.c);
    }
}
