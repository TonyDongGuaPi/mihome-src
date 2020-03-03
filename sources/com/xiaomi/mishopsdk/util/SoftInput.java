package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftInput {
    public static void hide(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context != null && view != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void show(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context != null && view != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }
}
