package a.a.a.h.d;

import android.support.v4.view.ViewPager;
import android.view.View;

public class a implements ViewPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setScaleX(1.0f - (Math.abs(f) * 0.33f));
        view.setScaleY(1.0f - (Math.abs(f) * 0.33f));
        view.setPivotX(((float) view.getWidth()) / 2.0f);
        view.setPivotY(((float) view.getHeight()) / 2.0f);
    }
}
