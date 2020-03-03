package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class FadePageTransformer extends BGAPageTransformer {
    public void a(View view, float f) {
    }

    public void b(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setAlpha(view, f + 1.0f);
    }

    public void c(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setAlpha(view, 1.0f - f);
    }
}
