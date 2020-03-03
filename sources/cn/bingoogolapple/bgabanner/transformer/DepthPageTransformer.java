package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class DepthPageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private float f544a = 0.8f;

    public DepthPageTransformer() {
    }

    public DepthPageTransformer(float f) {
        a(f);
    }

    public void a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void b(View view, float f) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setScaleX(view, 1.0f);
        ViewCompat.setScaleY(view, 1.0f);
    }

    public void c(View view, float f) {
        float f2 = 1.0f - f;
        ViewCompat.setAlpha(view, f2);
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        float f3 = this.f544a + ((1.0f - this.f544a) * f2);
        ViewCompat.setScaleX(view, f3);
        ViewCompat.setScaleY(view, f3);
    }

    public void a(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.f544a = f;
        }
    }
}
