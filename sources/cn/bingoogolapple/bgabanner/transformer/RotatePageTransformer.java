package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class RotatePageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private float f546a = 15.0f;

    public RotatePageTransformer() {
    }

    public RotatePageTransformer(float f) {
        a(f);
    }

    public void a(View view, float f) {
        ViewCompat.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
        ViewCompat.setPivotY(view, (float) view.getMeasuredHeight());
        ViewCompat.setRotation(view, 0.0f);
    }

    public void b(View view, float f) {
        float f2 = this.f546a * f;
        ViewCompat.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
        ViewCompat.setPivotY(view, (float) view.getMeasuredHeight());
        ViewCompat.setRotation(view, f2);
    }

    public void c(View view, float f) {
        b(view, f);
    }

    public void a(float f) {
        if (f >= 0.0f && f <= 40.0f) {
            this.f546a = f;
        }
    }
}
