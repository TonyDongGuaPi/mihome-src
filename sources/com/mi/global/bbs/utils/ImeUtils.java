package com.mi.global.bbs.utils;

import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Method;

public class ImeUtils {
    private ImeUtils() {
    }

    public static void showIme(@NonNull View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        Class<InputMethodManager> cls = InputMethodManager.class;
        try {
            Method method = cls.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
            method.setAccessible(true);
            method.invoke(inputMethodManager, new Object[]{0, null});
        } catch (Exception unused) {
        }
    }

    public static void hideIme(@NonNull View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
