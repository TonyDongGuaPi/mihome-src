package com.wx.wheelview.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collection;

public class WheelUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9876a = "WheelView";

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(f9876a, str);
        }
    }

    public static <V> boolean a(Collection<V> collection) {
        return collection == null || collection.size() == 0;
    }

    public static TextView a(View view) {
        if (view instanceof TextView) {
            return (TextView) view;
        }
        if (view instanceof ViewGroup) {
            return a(((ViewGroup) view).getChildAt(0));
        }
        return null;
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
