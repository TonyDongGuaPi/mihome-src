package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.PermissionChecker;

public class SystemUtils {
    public static boolean a() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean a(Context context, String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (context.getApplicationInfo().targetSdkVersion < 23) {
            if (PermissionChecker.checkPermission(context, str, Binder.getCallingPid(), Binder.getCallingUid(), context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } else if (context.checkSelfPermission(str) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Uri a(int i) {
        return Uri.parse("res://com.xiaomi.smarthome/" + i);
    }

    public static boolean a(Activity activity) {
        if (activity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return true;
        }
        return false;
    }
}
