package com.xiaomi.miot.support.monitor.core.activity;

import android.app.Activity;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.core.tasks.ITask;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import com.xiaomi.miot.support.monitor.utils.AsyncThreadTask;

public class ActivityCore {

    /* renamed from: a  reason: collision with root package name */
    public static long f1480a = 0;
    public static long b = 0;
    public static int c = 0;
    private static final String d = "traceactivity";

    public static void a(Activity activity, int i, long j, int i2) {
        if (activity != null && activity.getClass() != null && !TextUtils.isEmpty(activity.getClass().getCanonicalName())) {
            final Activity activity2 = activity;
            final int i3 = i2;
            final int i4 = i;
            final long j2 = j;
            AsyncThreadTask.a((Runnable) new Runnable() {
                public void run() {
                    String canonicalName = activity2.getClass().getCanonicalName();
                    if (i3 == 1) {
                        Activity a2 = AndroidUtils.a();
                        if (a2 != null && a2.getClass() != null) {
                            String canonicalName2 = a2.getClass().getCanonicalName();
                            if (TextUtils.isEmpty(canonicalName2) || !TextUtils.equals(canonicalName, canonicalName2)) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    ActivityInfo activityInfo = new ActivityInfo();
                    activityInfo.resetData();
                    activityInfo.activityName = canonicalName;
                    activityInfo.startType = i4;
                    activityInfo.time = j2;
                    activityInfo.lifeCycle = i3;
                    MiotDataStorage.a().a(activityInfo);
                }
            });
        }
    }

    public static void a(Activity activity, long j) {
        c = 1;
        activity.getWindow().getDecorView().post(new FirstFrameRunnable(activity, c, j));
        a(activity, c, System.currentTimeMillis() - j, 2);
    }

    private static class FirstFrameRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private Activity f1482a;
        private int b;
        private long c;

        public FirstFrameRunnable(Activity activity, int i, long j) {
            this.c = j;
            this.f1482a = activity;
            this.b = i;
        }

        public void run() {
            ActivityCore.a(this.f1482a, this.b, System.currentTimeMillis() - this.c, 1);
        }
    }

    public static boolean a() {
        ITask a2;
        TaskManager a3 = TaskManager.a();
        if (a3 == null || (a2 = a3.a("activity")) == null || !a2.f()) {
            return false;
        }
        return true;
    }
}
