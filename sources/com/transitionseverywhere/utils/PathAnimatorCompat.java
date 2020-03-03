package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;

@TargetApi(14)
public class PathAnimatorCompat extends BasePointFAnimator {

    /* renamed from: a  reason: collision with root package name */
    private PathMeasure f9499a;
    private float b;
    private float[] c = new float[2];

    private PathAnimatorCompat(Object obj, PointFProperty pointFProperty) {
        super(obj, pointFProperty);
    }

    public static <T> PathAnimatorCompat a(T t, PointFProperty<T> pointFProperty, Path path) {
        if (t == null || pointFProperty == null || path == null) {
            return null;
        }
        PathAnimatorCompat pathAnimatorCompat = new PathAnimatorCompat(t, pointFProperty);
        pathAnimatorCompat.f9499a = new PathMeasure(path, false);
        pathAnimatorCompat.b = pathAnimatorCompat.f9499a.getLength();
        return pathAnimatorCompat;
    }

    /* access modifiers changed from: protected */
    public void a(PointF pointF, float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.f9499a.getPosTan(f * this.b, this.c, (float[]) null);
        pointF.set(this.c[0], this.c[1]);
    }
}
