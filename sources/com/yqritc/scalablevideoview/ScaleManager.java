package com.yqritc.scalablevideoview;

import android.graphics.Matrix;

public class ScaleManager {

    /* renamed from: a  reason: collision with root package name */
    private Size f2554a;
    private Size b;

    public ScaleManager(Size size, Size size2) {
        this.f2554a = size;
        this.b = size2;
    }

    public Matrix a(ScalableType scalableType) {
        switch (scalableType) {
            case NONE:
                return a();
            case FIT_XY:
                return b();
            case FIT_CENTER:
                return d();
            case FIT_START:
                return c();
            case FIT_END:
                return e();
            case LEFT_TOP:
                return b(PivotPoint.LEFT_TOP);
            case LEFT_CENTER:
                return b(PivotPoint.LEFT_CENTER);
            case LEFT_BOTTOM:
                return b(PivotPoint.LEFT_BOTTOM);
            case CENTER_TOP:
                return b(PivotPoint.CENTER_TOP);
            case CENTER:
                return b(PivotPoint.CENTER);
            case CENTER_BOTTOM:
                return b(PivotPoint.CENTER_BOTTOM);
            case RIGHT_TOP:
                return b(PivotPoint.RIGHT_TOP);
            case RIGHT_CENTER:
                return b(PivotPoint.RIGHT_CENTER);
            case RIGHT_BOTTOM:
                return b(PivotPoint.RIGHT_BOTTOM);
            case LEFT_TOP_CROP:
                return c(PivotPoint.LEFT_TOP);
            case LEFT_CENTER_CROP:
                return c(PivotPoint.LEFT_CENTER);
            case LEFT_BOTTOM_CROP:
                return c(PivotPoint.LEFT_BOTTOM);
            case CENTER_TOP_CROP:
                return c(PivotPoint.CENTER_TOP);
            case CENTER_CROP:
                return c(PivotPoint.CENTER);
            case CENTER_BOTTOM_CROP:
                return c(PivotPoint.CENTER_BOTTOM);
            case RIGHT_TOP_CROP:
                return c(PivotPoint.RIGHT_TOP);
            case RIGHT_CENTER_CROP:
                return c(PivotPoint.RIGHT_CENTER);
            case RIGHT_BOTTOM_CROP:
                return c(PivotPoint.RIGHT_BOTTOM);
            case START_INSIDE:
                return f();
            case CENTER_INSIDE:
                return g();
            case END_INSIDE:
                return h();
            default:
                return null;
        }
    }

    private Matrix a(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2, f3, f4);
        return matrix;
    }

    private Matrix a(float f, float f2, PivotPoint pivotPoint) {
        switch (pivotPoint) {
            case LEFT_TOP:
                return a(f, f2, 0.0f, 0.0f);
            case LEFT_CENTER:
                return a(f, f2, 0.0f, ((float) this.f2554a.b()) / 2.0f);
            case LEFT_BOTTOM:
                return a(f, f2, 0.0f, (float) this.f2554a.b());
            case CENTER_TOP:
                return a(f, f2, ((float) this.f2554a.a()) / 2.0f, 0.0f);
            case CENTER:
                return a(f, f2, ((float) this.f2554a.a()) / 2.0f, ((float) this.f2554a.b()) / 2.0f);
            case CENTER_BOTTOM:
                return a(f, f2, ((float) this.f2554a.a()) / 2.0f, (float) this.f2554a.b());
            case RIGHT_TOP:
                return a(f, f2, (float) this.f2554a.a(), 0.0f);
            case RIGHT_CENTER:
                return a(f, f2, (float) this.f2554a.a(), ((float) this.f2554a.b()) / 2.0f);
            case RIGHT_BOTTOM:
                return a(f, f2, (float) this.f2554a.a(), (float) this.f2554a.b());
            default:
                throw new IllegalArgumentException("Illegal PivotPoint");
        }
    }

    private Matrix a() {
        return a(((float) this.b.a()) / ((float) this.f2554a.a()), ((float) this.b.b()) / ((float) this.f2554a.b()), PivotPoint.LEFT_TOP);
    }

    private Matrix a(PivotPoint pivotPoint) {
        float a2 = ((float) this.f2554a.a()) / ((float) this.b.a());
        float b2 = ((float) this.f2554a.b()) / ((float) this.b.b());
        float min = Math.min(a2, b2);
        return a(min / a2, min / b2, pivotPoint);
    }

    private Matrix b() {
        return a(1.0f, 1.0f, PivotPoint.LEFT_TOP);
    }

    private Matrix c() {
        return a(PivotPoint.LEFT_TOP);
    }

    private Matrix d() {
        return a(PivotPoint.CENTER);
    }

    private Matrix e() {
        return a(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix b(PivotPoint pivotPoint) {
        return a(((float) this.b.a()) / ((float) this.f2554a.a()), ((float) this.b.b()) / ((float) this.f2554a.b()), pivotPoint);
    }

    private Matrix c(PivotPoint pivotPoint) {
        float a2 = ((float) this.f2554a.a()) / ((float) this.b.a());
        float b2 = ((float) this.f2554a.b()) / ((float) this.b.b());
        float max = Math.max(a2, b2);
        return a(max / a2, max / b2, pivotPoint);
    }

    private Matrix f() {
        if (this.b.b() > this.f2554a.a() || this.b.b() > this.f2554a.b()) {
            return c();
        }
        return b(PivotPoint.LEFT_TOP);
    }

    private Matrix g() {
        if (this.b.b() > this.f2554a.a() || this.b.b() > this.f2554a.b()) {
            return d();
        }
        return b(PivotPoint.CENTER);
    }

    private Matrix h() {
        if (this.b.b() > this.f2554a.a() || this.b.b() > this.f2554a.b()) {
            return e();
        }
        return b(PivotPoint.RIGHT_BOTTOM);
    }
}
