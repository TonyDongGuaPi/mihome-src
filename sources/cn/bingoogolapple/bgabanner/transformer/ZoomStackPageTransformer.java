package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class ZoomStackPageTransformer extends BGAPageTransformer {
    public void a(View view, float f) {
    }

    public void b(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setPivotX(view, ((float) view.getWidth()) * 0.5f);
        ViewCompat.setPivotY(view, ((float) view.getHeight()) * 0.5f);
        float f2 = f + 1.0f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        if (f < -0.95f) {
            ViewCompat.setAlpha(view, 0.0f);
        } else {
            ViewCompat.setAlpha(view, 1.0f);
        }
    }

    public void c(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setPivotX(view, ((float) view.getWidth()) * 0.5f);
        ViewCompat.setPivotY(view, ((float) view.getHeight()) * 0.5f);
        float f2 = f + 1.0f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        if (f > 0.95f) {
            ViewCompat.setAlpha(view, 0.0f);
        } else {
            ViewCompat.setAlpha(view, 1.0f);
        }
    }
}
