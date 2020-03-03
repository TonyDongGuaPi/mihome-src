package com.transitionseverywhere.utils;

import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.lang.reflect.Field;

public class MatrixUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Matrix f9497a = new Matrix() {
        /* access modifiers changed from: package-private */
        public void a() {
            throw new IllegalStateException("Matrix can not be modified");
        }

        public void set(Matrix matrix) {
            a();
        }

        public void reset() {
            a();
        }

        public void setTranslate(float f, float f2) {
            a();
        }

        public void setScale(float f, float f2, float f3, float f4) {
            a();
        }

        public void setScale(float f, float f2) {
            a();
        }

        public void setRotate(float f, float f2, float f3) {
            a();
        }

        public void setRotate(float f) {
            a();
        }

        public void setSinCos(float f, float f2, float f3, float f4) {
            a();
        }

        public void setSinCos(float f, float f2) {
            a();
        }

        public void setSkew(float f, float f2, float f3, float f4) {
            a();
        }

        public void setSkew(float f, float f2) {
            a();
        }

        public boolean setConcat(Matrix matrix, Matrix matrix2) {
            a();
            return false;
        }

        public boolean preTranslate(float f, float f2) {
            a();
            return false;
        }

        public boolean preScale(float f, float f2, float f3, float f4) {
            a();
            return false;
        }

        public boolean preScale(float f, float f2) {
            a();
            return false;
        }

        public boolean preRotate(float f, float f2, float f3) {
            a();
            return false;
        }

        public boolean preRotate(float f) {
            a();
            return false;
        }

        public boolean preSkew(float f, float f2, float f3, float f4) {
            a();
            return false;
        }

        public boolean preSkew(float f, float f2) {
            a();
            return false;
        }

        public boolean preConcat(Matrix matrix) {
            a();
            return false;
        }

        public boolean postTranslate(float f, float f2) {
            a();
            return false;
        }

        public boolean postScale(float f, float f2, float f3, float f4) {
            a();
            return false;
        }

        public boolean postScale(float f, float f2) {
            a();
            return false;
        }

        public boolean postRotate(float f, float f2, float f3) {
            a();
            return false;
        }

        public boolean postRotate(float f) {
            a();
            return false;
        }

        public boolean postSkew(float f, float f2, float f3, float f4) {
            a();
            return false;
        }

        public boolean postSkew(float f, float f2) {
            a();
            return false;
        }

        public boolean postConcat(Matrix matrix) {
            a();
            return false;
        }

        public boolean setRectToRect(RectF rectF, RectF rectF2, Matrix.ScaleToFit scaleToFit) {
            a();
            return false;
        }

        public boolean setPolyToPoly(float[] fArr, int i, float[] fArr2, int i2, int i3) {
            a();
            return false;
        }

        public void setValues(float[] fArr) {
            a();
        }
    };
    private static final Field b = ReflectionUtils.a(ImageView.class, "mDrawMatrix");

    @TargetApi(14)
    public static class NullMatrixEvaluator implements TypeEvaluator<Matrix> {
        /* renamed from: a */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }
    }

    public static void a(ImageView imageView, Matrix matrix) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            if (matrix == null || drawable.getIntrinsicWidth() == -1 || drawable.getIntrinsicHeight() == -1) {
                drawable.setBounds(0, 0, imageView.getWidth(), imageView.getHeight());
            } else {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                Matrix imageMatrix = imageView.getImageMatrix();
                if (imageMatrix.isIdentity()) {
                    imageMatrix = new Matrix();
                    ReflectionUtils.a((Object) imageView, b, (Object) imageMatrix);
                }
                imageMatrix.set(matrix);
            }
            imageView.invalidate();
        }
    }

    @TargetApi(14)
    public static class MatrixEvaluator implements TypeEvaluator<Matrix> {

        /* renamed from: a  reason: collision with root package name */
        float[] f9498a = new float[9];
        float[] b = new float[9];
        Matrix c = new Matrix();

        /* renamed from: a */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            matrix.getValues(this.f9498a);
            matrix2.getValues(this.b);
            for (int i = 0; i < 9; i++) {
                this.b[i] = this.f9498a[i] + ((this.b[i] - this.f9498a[i]) * f);
            }
            this.c.setValues(this.b);
            return this.c;
        }
    }
}
