package com.mi.global.shop.widget.gallery;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class RotateBitmap {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7238a = "RotateBitmap";
    private Bitmap b;
    private int c;

    public RotateBitmap(Bitmap bitmap) {
        this.b = bitmap;
        this.c = 0;
    }

    public RotateBitmap(Bitmap bitmap, int i) {
        this.b = bitmap;
        this.c = i % 360;
    }

    public void a(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public Bitmap b() {
        return this.b;
    }

    public void a(Bitmap bitmap) {
        this.b = bitmap;
    }

    public Matrix c() {
        Matrix matrix = new Matrix();
        if (this.c != 0) {
            matrix.preTranslate((float) (-(this.b.getWidth() / 2)), (float) (-(this.b.getHeight() / 2)));
            matrix.postRotate((float) this.c);
            matrix.postTranslate((float) (f() / 2), (float) (e() / 2));
        }
        return matrix;
    }

    public boolean d() {
        return (this.c / 90) % 2 != 0;
    }

    public int e() {
        if (d()) {
            return this.b.getWidth();
        }
        return this.b.getHeight();
    }

    public int f() {
        if (d()) {
            return this.b.getHeight();
        }
        return this.b.getWidth();
    }

    public void g() {
        if (this.b != null) {
            this.b.recycle();
            this.b = null;
        }
    }
}
