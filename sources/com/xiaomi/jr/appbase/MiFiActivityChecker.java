package com.xiaomi.jr.appbase;

import android.app.Activity;
import android.os.Build;
import com.xiaomi.jr.common.app.ActivityChecker;

public class MiFiActivityChecker implements ActivityChecker.Checker {
    public boolean a(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity.isDestroyed()) {
                return false;
            }
            return true;
        } else if (!(activity instanceof BaseActivity) || !((BaseActivity) activity).isOnDestroyCalled()) {
            return true;
        } else {
            return false;
        }
    }
}
