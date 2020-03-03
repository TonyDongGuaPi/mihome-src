package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class CubePageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private float f543a = 90.0f;

    public CubePageTransformer() {
    }

    public CubePageTransformer(float f) {
        a(f);
    }

    public void a(View view, float f) {
        ViewCompat.setPivotX(view, (float) view.getMeasuredWidth());
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, 0.0f);
    }

    public void b(View view, float f) {
        ViewCompat.setPivotX(view, (float) view.getMeasuredWidth());
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, this.f543a * f);
    }

    public void c(View view, float f) {
        ViewCompat.setPivotX(view, 0.0f);
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, this.f543a * f);
    }

    public void a(float f) {
        if (f >= 0.0f && f <= 90.0f) {
            this.f543a = f;
        }
    }
}
