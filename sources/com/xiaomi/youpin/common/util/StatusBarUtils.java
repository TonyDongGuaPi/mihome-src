package com.xiaomi.youpin.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Window;

public class StatusBarUtils {
    public static void a(Activity activity, int i) {
        Window window;
        try {
            if (Build.VERSION.SDK_INT >= 21 && (window = activity.getWindow()) != null) {
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(activity.getResources().getColor(i));
                window.setNavigationBarColor(activity.getResources().getColor(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Dialog dialog, int i) {
        Window window;
        try {
            if (Build.VERSION.SDK_INT >= 21 && (window = dialog.getWindow()) != null) {
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(dialog.getContext().getResources().getColor(i));
                window.setNavigationBarColor(dialog.getContext().getResources().getColor(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
