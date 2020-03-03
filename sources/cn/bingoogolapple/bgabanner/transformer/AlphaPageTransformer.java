package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class AlphaPageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private float f541a = 0.4f;

    public AlphaPageTransformer() {
    }

    public AlphaPageTransformer(float f) {
        a(f);
    }

    public void a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void b(View view, float f) {
        ViewCompat.setAlpha(view, this.f541a + ((1.0f - this.f541a) * (f + 1.0f)));
    }

    public void c(View view, float f) {
        ViewCompat.setAlpha(view, this.f541a + ((1.0f - this.f541a) * (1.0f - f)));
    }

    public void a(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.f541a = f;
        }
    }
}
