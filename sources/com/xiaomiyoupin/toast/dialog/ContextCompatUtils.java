package com.xiaomiyoupin.toast.dialog;

import android.app.Activity;
import android.content.Context;
import com.xiaomiyoupin.toast.YPDToast;

class ContextCompatUtils {
    ContextCompatUtils() {
    }

    static Context loadRealContext(Context context) {
        if (!(context instanceof Activity)) {
            return !isPluginContext(context) ? context : YPDToast.getInstance().getApplicationContext();
        }
        Context context2 = null;
        if (!isPluginContext(context)) {
            context2 = context;
        } else {
            Context baseContext = ((Activity) context).getBaseContext();
            if (baseContext instanceof Activity) {
                context2 = baseContext;
            }
        }
        if (context2 != null) {
            return context2;
        }
        return context;
    }

    private static boolean isPluginContext(Context context) {
        if (context == null || YPDToast.getInstance().getApplicationContext() == null || context.getClassLoader() == YPDToast.getInstance().getApplicationContext().getClassLoader()) {
            return false;
        }
        return true;
    }
}
