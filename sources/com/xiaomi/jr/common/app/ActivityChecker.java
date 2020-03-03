package com.xiaomi.jr.common.app;

import android.app.Activity;
import android.os.Build;

public class ActivityChecker {

    /* renamed from: a  reason: collision with root package name */
    private static Checker f1408a = new DefaultChecker();

    public interface Checker {
        boolean a(Activity activity);
    }

    public static void a(Class<? extends Checker> cls) {
        try {
            f1408a = (Checker) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a(Activity activity) {
        if (f1408a != null) {
            return f1408a.a(activity);
        }
        throw new IllegalStateException("hasn't set checker yet");
    }

    private static class DefaultChecker implements Checker {
        private DefaultChecker() {
        }

        public boolean a(Activity activity) {
            if (activity == null || activity.isFinishing()) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                return true;
            }
            return false;
        }
    }
}
