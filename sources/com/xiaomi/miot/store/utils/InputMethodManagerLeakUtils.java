package com.xiaomi.miot.store.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;

public class InputMethodManagerLeakUtils {
    public static void a(Context context) {
        InputMethodManager inputMethodManager;
        if (context != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
            for (String declaredField : strArr) {
                try {
                    Field declaredField2 = inputMethodManager.getClass().getDeclaredField(declaredField);
                    if (!declaredField2.isAccessible()) {
                        declaredField2.setAccessible(true);
                    }
                    Object obj = declaredField2.get(inputMethodManager);
                    if (obj != null && (obj instanceof View)) {
                        if (((View) obj).getContext() == context) {
                            declaredField2.set(inputMethodManager, (Object) null);
                        } else {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
