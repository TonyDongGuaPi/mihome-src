package com.mi.global.shop.util;

import android.view.View;
import android.view.ViewGroup;

public class LayoutParamsUtil {
    public static void a(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }
}
