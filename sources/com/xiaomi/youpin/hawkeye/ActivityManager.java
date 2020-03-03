package com.xiaomi.youpin.hawkeye;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.xiaomi.youpin.hawkeye.appstart.AppStartTask;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.ITask;
import com.xiaomi.youpin.hawkeye.task.TaskManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static WeakReference<Activity> f23345a;
    /* access modifiers changed from: private */
    public static boolean b;
    /* access modifiers changed from: private */
    public static List<AppStatusListener> c = new ArrayList();

    public interface AppStatusListener {
        void ab_();

        void b();
    }

    public static void a(AppStatusListener appStatusListener) {
        c.add(appStatusListener);
    }

    public static void b(AppStatusListener appStatusListener) {
        c.remove(appStatusListener);
    }

    public static Activity a() {
        if (f23345a == null || f23345a.get() == null) {
            return null;
        }
        return (Activity) f23345a.get();
    }

    public static void a(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            /* renamed from: a  reason: collision with root package name */
            int f23346a;

            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
                if (this.f23346a == 0 && !ActivityManager.c.isEmpty()) {
                    for (AppStatusListener appStatusListener : ActivityManager.c) {
                        if (appStatusListener != null) {
                            appStatusListener.ab_();
                        }
                    }
                }
                this.f23346a++;
            }

            public void onActivityResumed(Activity activity) {
                WeakReference unused = ActivityManager.f23345a = new WeakReference(activity);
                if (!ActivityManager.b && ActivityManager.a() != null) {
                    boolean unused2 = ActivityManager.b = true;
                    ActivityManager.a().getWindow().getDecorView().post(new Runnable() {
                        public void run() {
                            ITask a2 = TaskManager.a().a(ApmTaskConstants.f23371a);
                            if (a2 != null && (a2 instanceof AppStartTask)) {
                                ((AppStartTask) a2).c();
                            }
                        }
                    });
                }
            }

            public void onActivityPaused(Activity activity) {
                WeakReference unused = ActivityManager.f23345a = null;
            }

            public void onActivityStopped(Activity activity) {
                this.f23346a--;
                if (this.f23346a == 0 && !ActivityManager.c.isEmpty()) {
                    for (AppStatusListener appStatusListener : ActivityManager.c) {
                        if (appStatusListener != null) {
                            appStatusListener.b();
                        }
                    }
                }
            }
        });
    }
}
