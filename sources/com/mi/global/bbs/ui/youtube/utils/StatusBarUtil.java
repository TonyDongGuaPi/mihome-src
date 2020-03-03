package com.mi.global.bbs.ui.youtube.utils;

import android.app.Activity;
import android.os.Build;

public class StatusBarUtil {
    private StatusBarUtil() {
    }

    public static void hide(Activity activity) {
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(1024, 1024);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(4);
        }
    }
}
