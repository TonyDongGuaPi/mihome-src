package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class StackPageTransformer extends BGAPageTransformer {
    public void a(View view, float f) {
    }

    public void b(View view, float f) {
    }

    public void c(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
    }
}
