package com.github.ksoichiro.android.observablescrollview;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public final class ScrollUtils {
    private ScrollUtils() {
    }

    public static float a(float f, float f2, float f3) {
        return Math.min(f3, Math.max(f2, f));
    }

    public static int a(float f, int i) {
        return (Math.min(255, Math.max(0, (int) (f * 255.0f))) << 24) + (i & 16777215);
    }

    public static void a(final View view, final Runnable runnable) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                runnable.run();
            }
        });
    }

    public static int a(int i, int i2, float f) {
        float[] a2 = a(i);
        float[] a3 = a(i2);
        float[] fArr = new float[4];
        for (int i3 = 0; i3 < 4; i3++) {
            fArr[i3] = Math.min(1.0f, (a2[i3] * (1.0f - f)) + (a3[i3] * f));
        }
        return (16777215 & a(fArr)) - 16777216;
    }

    public static float[] a(int i) {
        float f;
        float f2;
        float f3 = 1.0f;
        float f4 = 1.0f - (((float) ((16711680 & i) >> 16)) / 255.0f);
        float f5 = 1.0f - (((float) ((65280 & i) >> 8)) / 255.0f);
        float f6 = 1.0f - (((float) (i & 255)) / 255.0f);
        float min = Math.min(f4, Math.min(f5, f6));
        if (min != 1.0f) {
            float f7 = 1.0f - min;
            f2 = (f4 - min) / f7;
            f = (f5 - min) / f7;
            f3 = (f6 - min) / f7;
        } else {
            f2 = 1.0f;
            f = 1.0f;
        }
        return new float[]{f2, f, f3, min};
    }

    public static int a(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = 1.0f - f4;
        return ((((int) ((1.0f - Math.min(1.0f, (f * f5) + f4)) * 255.0f)) & 255) << 16) + ((((int) ((1.0f - Math.min(1.0f, (f2 * f5) + f4)) * 255.0f)) & 255) << 8) + (((int) ((1.0f - Math.min(1.0f, (f3 * f5) + f4)) * 255.0f)) & 255);
    }
}
