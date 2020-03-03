package com.xiaomi.smarthome.library.common.widget.nestscroll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Interpolator;

public class UIUtils {
    public static final int a(int i) {
        return (int) (((float) i) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int a(int i, float f) {
        float f2 = 1.0f - f;
        return Color.argb(Color.alpha(i), (int) ((((((float) Color.red(i)) * f2) / 255.0f) + f) * 255.0f), (int) ((((((float) Color.green(i)) * f2) / 255.0f) + f) * 255.0f), (int) ((((((float) Color.blue(i)) * f2) / 255.0f) + f) * 255.0f));
    }

    public static int b(int i, float f) {
        return Color.argb(Color.alpha(i), Math.max((int) (((float) Color.red(i)) * f), 0), Math.max((int) (((float) Color.green(i)) * f), 0), Math.max((int) (((float) Color.blue(i)) * f), 0));
    }

    public static int a(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.data;
        }
        return 0;
    }

    public static int a(Context context, int i, int i2) {
        int a2 = a(context, i);
        return a2 == 0 ? context.getResources().getColor(i2) : a2;
    }

    public static void a(View view) {
        if (view != null) {
            ViewCompat.setAlpha(view, 1.0f);
            ViewCompat.setScaleY(view, 1.0f);
            ViewCompat.setScaleX(view, 1.0f);
            ViewCompat.setTranslationY(view, 0.0f);
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setRotation(view, 0.0f);
            ViewCompat.setRotationY(view, 0.0f);
            ViewCompat.setRotationX(view, 0.0f);
            view.setPivotY((float) (view.getMeasuredHeight() / 2));
            ViewCompat.setPivotX(view, (float) (view.getMeasuredWidth() / 2));
            ViewCompat.animate(view).setInterpolator((Interpolator) null);
        }
    }
}
