package com.andview.refreshview.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public class Utils {
    public static String a(String str, int i) {
        return String.format(str, new Object[]{Integer.valueOf(i)});
    }

    public static Point a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        return new Point(windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight());
    }

    public static void a(StaggeredGridLayoutManager.LayoutParams layoutParams) {
        if (layoutParams != null && !layoutParams.isFullSpan()) {
            layoutParams.setFullSpan(true);
        }
    }

    public static void a(View view) {
        ViewGroup viewGroup;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
    }

    public static boolean a(RecyclerView recyclerView) {
        return (recyclerView.getAdapter() instanceof BaseRecyclerAdapter) && b(recyclerView) > 0;
    }

    public static int b(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof GridLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(iArr);
            return a(iArr);
        }
        throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
    }

    private static int a(int[] iArr) {
        int i = Integer.MAX_VALUE;
        for (int i2 : iArr) {
            if (i2 != -1 && i2 < i) {
                i = i2;
            }
        }
        return i;
    }

    public static int a(int i, int i2, int i3) {
        int i4;
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        boolean z = abs > abs2;
        int sqrt = (int) Math.sqrt((double) 0);
        int i5 = i3 / 2;
        float f = (float) i3;
        float f2 = (float) i5;
        float a2 = f2 + (a(Math.min(1.0f, (((float) ((int) Math.sqrt((double) ((i * i) + (i2 * i2))))) * 1.0f) / f)) * f2);
        if (sqrt > 0) {
            i4 = Math.round(Math.abs(a2 / ((float) sqrt)) * 1000.0f) * 4;
        } else {
            if (!z) {
                abs = abs2;
            }
            i4 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
        }
        return Math.min(i4, 2000);
    }

    public static int a(int i, int i2) {
        int abs = (int) (((((float) Math.abs(i)) / ((float) i2)) + 1.0f) * 200.0f);
        if (i == 0) {
            return 0;
        }
        return Math.min(abs, 500);
    }

    private static float a(float f) {
        double d = (double) (f - 0.5f);
        Double.isNaN(d);
        return (float) Math.sin((double) ((float) (d * 0.4712389167638204d)));
    }

    public static boolean b(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int d(Context context) {
        return c(context) - f(context);
    }

    public static int a(Activity activity) {
        return activity.getWindow().findViewById(16908290).getTop();
    }

    public static int e(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int f(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int g(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
