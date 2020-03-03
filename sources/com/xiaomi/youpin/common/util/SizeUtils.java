package com.xiaomi.youpin.common.util;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public final class SizeUtils {

    public interface onGetSizeListener {
        void a(View view);
    }

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int a(float f) {
        return (int) ((f * Utils.a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(float f) {
        return (int) ((f / Utils.a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int c(float f) {
        return (int) ((f * Utils.a().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int d(float f) {
        return (int) ((f / Utils.a().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static float a(float f, int i) {
        DisplayMetrics displayMetrics = Utils.a().getResources().getDisplayMetrics();
        switch (i) {
            case 0:
                return f;
            case 1:
                return f * displayMetrics.density;
            case 2:
                return f * displayMetrics.scaledDensity;
            case 3:
                return f * displayMetrics.xdpi * 0.013888889f;
            case 4:
                return f * displayMetrics.xdpi;
            case 5:
                return f * displayMetrics.xdpi * 0.03937008f;
            default:
                return 0.0f;
        }
    }

    public static void a(final View view, final onGetSizeListener ongetsizelistener) {
        view.post(new Runnable() {
            public void run() {
                if (ongetsizelistener != null) {
                    ongetsizelistener.a(view);
                }
            }
        });
    }

    public static int a(View view) {
        return c(view)[0];
    }

    public static int b(View view) {
        return c(view)[1];
    }

    public static int[] c(View view) {
        int i;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i2 = layoutParams.height;
        if (i2 > 0) {
            i = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    public static Point a() {
        Point point = new Point();
        new DisplayMetrics();
        DisplayMetrics displayMetrics = Utils.a().getResources().getDisplayMetrics();
        point.x = displayMetrics.widthPixels;
        point.y = displayMetrics.heightPixels;
        return point;
    }
}
