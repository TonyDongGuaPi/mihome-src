package com.xiaomi.jr.common.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ObjectMonitor {
    public static void a(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityDestroyed(Activity activity) {
                LifecycledObjects.a((Object) activity);
            }
        });
    }
}
