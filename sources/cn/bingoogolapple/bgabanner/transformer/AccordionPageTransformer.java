package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class AccordionPageTransformer extends BGAPageTransformer {
    public void a(View view, float f) {
    }

    public void b(View view, float f) {
        ViewCompat.setPivotX(view, (float) view.getWidth());
        ViewCompat.setScaleX(view, f + 1.0f);
    }

    public void c(View view, float f) {
        ViewCompat.setPivotX(view, 0.0f);
        ViewCompat.setScaleX(view, 1.0f - f);
        ViewCompat.setAlpha(view, 1.0f);
    }
}
