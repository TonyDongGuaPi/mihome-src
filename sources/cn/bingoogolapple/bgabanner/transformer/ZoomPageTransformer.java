package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class ZoomPageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private float f547a = 0.85f;
    private float b = 0.65f;

    public ZoomPageTransformer() {
    }

    public ZoomPageTransformer(float f, float f2) {
        a(f);
        b(f2);
    }

    public void a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void b(View view, float f) {
        float max = Math.max(this.f547a, f + 1.0f);
        float f2 = 1.0f - max;
        ViewCompat.setTranslationX(view, ((((float) view.getWidth()) * f2) / 2.0f) - (((((float) view.getHeight()) * f2) / 2.0f) / 2.0f));
        ViewCompat.setScaleX(view, max);
        ViewCompat.setScaleY(view, max);
        ViewCompat.setAlpha(view, this.b + (((max - this.f547a) / (1.0f - this.f547a)) * (1.0f - this.b)));
    }

    public void c(View view, float f) {
        float max = Math.max(this.f547a, 1.0f - f);
        float f2 = 1.0f - max;
        ViewCompat.setTranslationX(view, (-((((float) view.getWidth()) * f2) / 2.0f)) + (((((float) view.getHeight()) * f2) / 2.0f) / 2.0f));
        ViewCompat.setScaleX(view, max);
        ViewCompat.setScaleY(view, max);
        ViewCompat.setAlpha(view, this.b + (((max - this.f547a) / (1.0f - this.f547a)) * (1.0f - this.b)));
    }

    public void a(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.b = f;
        }
    }

    public void b(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.f547a = f;
        }
    }
}
