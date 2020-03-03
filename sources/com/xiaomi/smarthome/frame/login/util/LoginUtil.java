package com.xiaomi.smarthome.frame.login.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.sdk.R;

public class LoginUtil {
    public static boolean a(String str) {
        for (char c : str.toCharArray()) {
            if (c >= 19968 && c <= 40908) {
                return true;
            }
            if (c >= 12288 && c <= 12351) {
                return true;
            }
            if (c >= 65281 && c <= 65374) {
                return true;
            }
        }
        return false;
    }

    public static int b(String str) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            return R.string.set_pwd_hint;
        }
        if (a(str)) {
            return R.string.login_pwd_no_chinese;
        }
        int length = str.length();
        if (length < 8 || length > 16 || TextUtils.isDigitsOnly(str)) {
            return R.string.login_pwd_digit_letter_only;
        }
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = true;
                break;
            } else if (!Character.isLetter(str.charAt(i))) {
                z = false;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return R.string.login_pwd_digit_letter_only;
        }
        return 0;
    }

    public static void a(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 1);
        }
    }

    public static void a(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 1);
        }
    }

    public static void a(Activity activity) {
        InputMethodManager inputMethodManager;
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null && (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void b(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public static boolean a(Context context, Intent intent) {
        if (intent.getIntExtra("login_type", 1) != 3) {
            return false;
        }
        if (System.currentTimeMillis() - intent.getLongExtra(LoginHostApi.b, 0) <= 10000) {
            return false;
        }
        FrameManager.b().g().a(context);
        return true;
    }
}
