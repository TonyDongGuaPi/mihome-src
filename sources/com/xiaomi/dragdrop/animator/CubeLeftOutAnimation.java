package com.xiaomi.dragdrop.animator;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CubeLeftOutAnimation extends Animation {
    private static final int e = 90;

    /* renamed from: a  reason: collision with root package name */
    private Camera f10112a;
    private Matrix b;
    private int c;
    private int d;

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f10112a = new Camera();
        this.b = new Matrix();
        this.c = i;
        this.d = i2;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        this.f10112a.save();
        this.f10112a.translate(((float) this.c) - (f * ((float) this.c)), 0.0f, 0.0f);
        this.f10112a.rotateY(-90.0f * f);
        this.f10112a.getMatrix(this.b);
        this.f10112a.restore();
        this.b.postTranslate(0.0f, (float) (this.d / 2));
        this.b.preTranslate((float) (-this.c), (float) ((-this.d) / 2));
        transformation.getMatrix().postConcat(this.b);
    }
}