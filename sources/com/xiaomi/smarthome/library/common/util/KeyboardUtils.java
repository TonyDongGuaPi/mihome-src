package com.xiaomi.smarthome.library.common.util;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;

public class KeyboardUtils {
    public static void a(final EditText editText) {
        if (editText != null) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 2);
                }
            }, 300);
        }
    }

    public static void b(EditText editText) {
        if (editText != null) {
            ((InputMethodManager) editText.getContext().getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
