package com.miui.supportlite.internal.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ContextHelper {
    private ContextHelper() {
    }

    public static Activity a(View view) {
        Context context = ((ViewGroup) view.getRootView()).getChildAt(0).getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }
}
