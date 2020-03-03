package com.xiaomi.dragdrop.animator;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CubeRotationAnimation extends Animation {

    /* renamed from: a  reason: collision with root package name */
    float f10115a;
    float b;
    private Camera c;
    private Matrix d;
    private int e;
    private int f;

    public void a(float f2, float f3) {
        this.f10115a = f2;
        this.b = f3;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.c = new Camera();
        this.d = new Matrix();
        this.e = i;
        this.f = i2;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f2, Transformation transformation) {
        super.applyTransformation(f2, transformation);
        this.c.save();
        this.c.rotateX(this.f10115a + ((this.b - this.f10115a) * f2));
        this.c.getMatrix(this.d);
        this.c.restore();
        this.d.postTranslate(0.0f, (float) (this.f / 2));
        this.d.preTranslate(0.0f, (float) ((-this.f) / 2));
        transformation.getMatrix().postConcat(this.d);
    }
}
