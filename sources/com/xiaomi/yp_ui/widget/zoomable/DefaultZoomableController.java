package com.xiaomi.yp_ui.widget.zoomable;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.facebook.common.logging.FLog;
import com.xiaomi.yp_ui.widget.zoomable.TransformGestureDetector;
import com.xiaomi.yp_ui.widget.zoomable.ZoomableController;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DefaultZoomableController implements TransformGestureDetector.Listener, ZoomableController {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1614a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 4;
    public static final int e = 7;
    private static final float f = 0.001f;
    private static final Class<?> g = DefaultZoomableController.class;
    private static final RectF h = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private TransformGestureDetector i;
    private ZoomableController.Listener j = null;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    private float o = 1.0f;
    private float p = 2.0f;
    private final RectF q = new RectF();
    private final RectF r = new RectF();
    private final RectF s = new RectF();
    private final Matrix t = new Matrix();
    private final Matrix u = new Matrix();
    private final Matrix v = new Matrix();
    private final float[] w = new float[9];
    private final RectF x = new RectF();
    private boolean y;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LimitFlag {
    }

    private static boolean a(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    public static DefaultZoomableController j() {
        return new DefaultZoomableController(TransformGestureDetector.a());
    }

    public DefaultZoomableController(TransformGestureDetector transformGestureDetector) {
        this.i = transformGestureDetector;
        this.i.a((TransformGestureDetector.Listener) this);
    }

    public void a() {
        FLog.v(g, "reset");
        this.i.b();
        this.t.reset();
        this.u.reset();
        d();
    }

    public void a(ZoomableController.Listener listener) {
        this.j = listener;
    }

    public void b(boolean z) {
        this.k = z;
        if (!z) {
            a();
        }
    }

    public boolean k() {
        return this.k;
    }

    public void c(boolean z) {
        this.l = z;
    }

    public boolean l() {
        return this.l;
    }

    public void d(boolean z) {
        this.m = z;
    }

    public boolean m() {
        return this.m;
    }

    public void e(boolean z) {
        this.n = z;
    }

    public boolean n() {
        return this.n;
    }

    public void a(float f2) {
        this.o = f2;
    }

    public float o() {
        return this.o;
    }

    public void b(float f2) {
        this.p = f2;
    }

    public float p() {
        return this.p;
    }

    public float q() {
        return c(this.u);
    }

    public void a(RectF rectF) {
        if (!rectF.equals(this.r)) {
            this.r.set(rectF);
            d();
        }
    }

    public RectF r() {
        return this.r;
    }

    private RectF c() {
        return this.s;
    }

    public void b(RectF rectF) {
        this.q.set(rectF);
    }

    public RectF s() {
        return this.q;
    }

    public boolean b() {
        return a(this.u, (float) f);
    }

    public boolean t() {
        return this.y;
    }

    public Matrix u() {
        return this.u;
    }

    public void a(Matrix matrix) {
        matrix.setRectToRect(h, this.s, Matrix.ScaleToFit.FILL);
    }

    public PointF a(PointF pointF) {
        float[] fArr = this.w;
        fArr[0] = pointF.x;
        fArr[1] = pointF.y;
        this.u.invert(this.v);
        this.v.mapPoints(fArr, 0, fArr, 0, 1);
        a(fArr, fArr, 1);
        return new PointF(fArr[0], fArr[1]);
    }

    public PointF b(PointF pointF) {
        float[] fArr = this.w;
        fArr[0] = pointF.x;
        fArr[1] = pointF.y;
        b(fArr, fArr, 1);
        this.u.mapPoints(fArr, 0, fArr, 0, 1);
        return new PointF(fArr[0], fArr[1]);
    }

    private void a(float[] fArr, float[] fArr2, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            int i5 = i4 + 0;
            fArr[i5] = (fArr2[i5] - this.r.left) / this.r.width();
            int i6 = i4 + 1;
            fArr[i6] = (fArr2[i6] - this.r.top) / this.r.height();
        }
    }

    private void b(float[] fArr, float[] fArr2, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            int i5 = i4 + 0;
            fArr[i5] = (fArr2[i5] * this.r.width()) + this.r.left;
            int i6 = i4 + 1;
            fArr[i6] = (fArr2[i6] * this.r.height()) + this.r.top;
        }
    }

    public void a(float f2, PointF pointF, PointF pointF2) {
        FLog.v(g, "zoomToPoint");
        a(this.u, f2, pointF, pointF2, 7);
        d();
    }

    /* access modifiers changed from: protected */
    public boolean a(Matrix matrix, float f2, PointF pointF, PointF pointF2, int i2) {
        float[] fArr = this.w;
        fArr[0] = pointF.x;
        fArr[1] = pointF.y;
        b(fArr, fArr, 1);
        float f3 = pointF2.x - fArr[0];
        float f4 = pointF2.y - fArr[1];
        matrix.setScale(f2, f2, fArr[0], fArr[1]);
        matrix.postTranslate(f3, f4);
        return b(matrix, i2) | a(matrix, fArr[0], fArr[1], i2) | false;
    }

    public void b(Matrix matrix) {
        FLog.v(g, "setTransform");
        this.u.set(matrix);
        d();
    }

    /* access modifiers changed from: protected */
    public TransformGestureDetector v() {
        return this.i;
    }

    public boolean a(MotionEvent motionEvent) {
        FLog.v(g, "onTouchEvent: action: ", (Object) Integer.valueOf(motionEvent.getAction()));
        if (this.k) {
            return this.i.a(motionEvent);
        }
        return false;
    }

    public void a(TransformGestureDetector transformGestureDetector) {
        FLog.v(g, "onGestureBegin");
        this.t.set(this.u);
        this.y = !e();
    }

    public void b(TransformGestureDetector transformGestureDetector) {
        FLog.v(g, "onGestureUpdate");
        boolean a2 = a(this.u, 7);
        d();
        if (a2) {
            this.i.c();
        }
        this.y = a2;
    }

    public void c(TransformGestureDetector transformGestureDetector) {
        FLog.v(g, "onGestureEnd");
    }

    /* access modifiers changed from: protected */
    public boolean a(Matrix matrix, int i2) {
        TransformGestureDetector transformGestureDetector = this.i;
        matrix.set(this.t);
        if (this.l) {
            matrix.postRotate(transformGestureDetector.l() * 57.29578f, transformGestureDetector.g(), transformGestureDetector.h());
        }
        if (this.m) {
            float k2 = transformGestureDetector.k();
            matrix.postScale(k2, k2, transformGestureDetector.g(), transformGestureDetector.h());
        }
        boolean a2 = a(matrix, transformGestureDetector.g(), transformGestureDetector.h(), i2) | false;
        if (this.n) {
            matrix.postTranslate(transformGestureDetector.i(), transformGestureDetector.j());
        }
        return b(matrix, i2) | a2;
    }

    private void d() {
        this.u.mapRect(this.s, this.r);
        if (this.j != null && k()) {
            this.j.a(this.u);
        }
    }

    private boolean a(Matrix matrix, float f2, float f3, int i2) {
        if (!a(i2, 4)) {
            return false;
        }
        float c2 = c(matrix);
        float a2 = a(c2, this.o, this.p);
        if (a2 == c2) {
            return false;
        }
        float f4 = a2 / c2;
        matrix.postScale(f4, f4, f2, f3);
        return true;
    }

    private boolean b(Matrix matrix, int i2) {
        float f2;
        float f3;
        if (!a(i2, 3)) {
            return false;
        }
        RectF rectF = this.x;
        rectF.set(this.r);
        matrix.mapRect(rectF);
        if (a(i2, 1)) {
            f2 = a(rectF.left, rectF.right, this.q.left, this.q.right, this.r.centerX());
        } else {
            f2 = 0.0f;
        }
        if (a(i2, 2)) {
            f3 = a(rectF.top, rectF.bottom, this.q.top, this.q.bottom, this.r.centerY());
        } else {
            f3 = 0.0f;
        }
        if (f2 == 0.0f && f3 == 0.0f) {
            return false;
        }
        matrix.postTranslate(f2, f3);
        return true;
    }

    private float a(float f2, float f3, float f4, float f5, float f6) {
        float f7 = f3 - f2;
        float f8 = f5 - f4;
        if (f7 < Math.min(f6 - f4, f5 - f6) * 2.0f) {
            return f6 - ((f3 + f2) / 2.0f);
        }
        if (f7 < f8) {
            return f6 < (f4 + f5) / 2.0f ? f4 - f2 : f5 - f3;
        }
        if (f2 > f4) {
            return f4 - f2;
        }
        if (f3 < f5) {
            return f5 - f3;
        }
        return 0.0f;
    }

    private float a(float f2, float f3, float f4) {
        return Math.min(Math.max(f3, f2), f4);
    }

    private float c(Matrix matrix) {
        matrix.getValues(this.w);
        return this.w[0];
    }

    private boolean a(Matrix matrix, float f2) {
        matrix.getValues(this.w);
        float[] fArr = this.w;
        fArr[0] = fArr[0] - 1.0f;
        float[] fArr2 = this.w;
        fArr2[4] = fArr2[4] - 1.0f;
        float[] fArr3 = this.w;
        fArr3[8] = fArr3[8] - 1.0f;
        for (int i2 = 0; i2 < 9; i2++) {
            if (Math.abs(this.w[i2]) > f2) {
                return false;
            }
        }
        return true;
    }

    private boolean e() {
        return this.s.left < this.q.left - f && this.s.top < this.q.top - f && this.s.right > this.q.right + f && this.s.bottom > this.q.bottom + f;
    }

    public int w() {
        return (int) this.s.width();
    }

    public int x() {
        return (int) (this.q.left - this.s.left);
    }

    public int y() {
        return (int) this.q.width();
    }

    public int z() {
        return (int) this.s.height();
    }

    public int A() {
        return (int) (this.q.top - this.s.top);
    }

    public int B() {
        return (int) this.q.height();
    }
}
