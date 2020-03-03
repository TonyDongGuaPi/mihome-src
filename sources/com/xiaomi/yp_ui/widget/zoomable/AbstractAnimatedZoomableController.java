package com.xiaomi.yp_ui.widget.zoomable;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;

public abstract class AbstractAnimatedZoomableController extends DefaultZoomableController {
    private boolean f;
    private final float[] g = new float[9];
    private final float[] h = new float[9];
    private final float[] i = new float[9];
    private final Matrix j = new Matrix();
    private final Matrix k = new Matrix();

    public abstract void b(Matrix matrix, long j2, @Nullable Runnable runnable);

    /* access modifiers changed from: protected */
    public abstract void g();

    /* access modifiers changed from: protected */
    public abstract Class<?> h();

    public AbstractAnimatedZoomableController(TransformGestureDetector transformGestureDetector) {
        super(transformGestureDetector);
    }

    public void a() {
        FLog.v(h(), "reset");
        g();
        this.k.reset();
        this.j.reset();
        super.a();
    }

    public boolean b() {
        return !c() && super.b();
    }

    public void a(float f2, PointF pointF, PointF pointF2) {
        a(f2, pointF, pointF2, 7, 0, (Runnable) null);
    }

    public void a(float f2, PointF pointF, PointF pointF2, int i2, long j2, @Nullable Runnable runnable) {
        FLog.v(h(), "zoomToPoint: duration %d ms", (Object) Long.valueOf(j2));
        a(this.j, f2, pointF, pointF2, i2);
        a(this.j, j2, runnable);
    }

    public void a(Matrix matrix, long j2, @Nullable Runnable runnable) {
        FLog.v(h(), "setTransform: duration %d ms", (Object) Long.valueOf(j2));
        if (j2 <= 0) {
            c(matrix);
        } else {
            b(matrix, j2, runnable);
        }
    }

    private void c(Matrix matrix) {
        FLog.v(h(), "setTransformImmediate");
        g();
        this.k.set(matrix);
        super.b(matrix);
        v().c();
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: protected */
    public float[] d() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public float[] e() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public Matrix f() {
        return this.k;
    }

    public void a(TransformGestureDetector transformGestureDetector) {
        FLog.v(h(), "onGestureBegin");
        g();
        super.a(transformGestureDetector);
    }

    public void b(TransformGestureDetector transformGestureDetector) {
        FLog.v(h(), "onGestureUpdate %s", (Object) c() ? "(ignored)" : "");
        if (!c()) {
            super.b(transformGestureDetector);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Matrix matrix, float f2) {
        for (int i2 = 0; i2 < 9; i2++) {
            this.i[i2] = ((1.0f - f2) * this.g[i2]) + (this.h[i2] * f2);
        }
        matrix.setValues(this.i);
    }
}
