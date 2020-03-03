package com.xiaomi.jr.mipay.safekeyboard.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.lang.reflect.Method;

public class Utils {
    public static void a(Context context, View view, boolean z) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (z) {
                inputMethodManager.showSoftInput(view, 1);
            } else {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void a(EditText editText, boolean z) {
        if (Build.VERSION.SDK_INT > 21) {
            editText.setShowSoftInputOnFocus(z);
            return;
        }
        Class<EditText> cls = EditText.class;
        try {
            Method method = cls.getMethod("setShowSoftInputOnFocus", new Class[]{Boolean.TYPE});
            method.setAccessible(true);
            method.invoke(editText, new Object[]{Boolean.valueOf(z)});
        } catch (Exception unused) {
        }
    }
}
