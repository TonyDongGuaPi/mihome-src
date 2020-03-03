package com.xiaomi.smarthome.library;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;

public class DarkModeCompat {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18445a = "action_on_dark_mode_changed";
    private static final String b = "DarkModeCompat";
    private static final String c = "spfs_plugin_theme";
    private static final String d = "key_dark_enable";
    private static final String e = "key_app_dark_enable";

    public static boolean b(Context context) {
        return false;
    }

    public static boolean c(Context context) {
        return false;
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        try {
            if (!((context.getResources().getConfiguration().uiMode & 48) == 32) || !a(16844176, context, false) || !a(16844172, context, false)) {
                return false;
            }
            return true;
        } catch (Exception e2) {
            Log.e(b, "isForceDarkMode: " + Log.getStackTraceString(e2));
            return false;
        }
    }

    public static void a(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            view.setForceDarkAllowed(z);
        }
    }

    private static boolean a(int i, Context context, boolean z) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return z;
        }
        if (typedValue.type != 18 || typedValue.data == 0) {
            return false;
        }
        return true;
    }

    public static void a(Context context, boolean z) {
        SharePrefsManager.a(context, c, d, z);
    }

    public static void a(Activity activity) {
        if (a((Context) activity) && !b((Context) activity)) {
            c(activity);
        }
    }

    public static void b(Context context, boolean z) {
        SharePrefsManager.a(context, c, e, z);
    }

    public static void b(Activity activity) {
        if (a((Context) activity) && !c((Context) activity)) {
            c(activity);
        }
    }

    private static void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                activity.getWindow().getDecorView().setForceDarkAllowed(false);
                activity.getResources().getConfiguration().uiMode = 16;
            } catch (Exception e2) {
                Log.d(b, "disableForceDarkMode: " + Log.getStackTraceString(e2));
            }
        }
    }
}
