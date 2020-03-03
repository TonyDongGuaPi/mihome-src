package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class FlipPageTransformer extends BGAPageTransformer {

    /* renamed from: a  reason: collision with root package name */
    private static final float f545a = 180.0f;

    public void a(View view, float f) {
    }

    public void b(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setRotationY(view, f545a * f);
        if (((double) f) > -0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    public void c(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setRotationY(view, f545a * f);
        if (((double) f) < 0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}
