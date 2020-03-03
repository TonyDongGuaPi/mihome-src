package com.xiaomi.miot.support.monitor.core.activity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

public class AH {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11456a = "traceactivity";

    public static void b(Context context) {
    }

    public static void a(Activity activity, long j, String str, Object... objArr) {
        if (a()) {
            if (TextUtils.equals(str, "onCreate")) {
                ActivityCore.a(activity, j);
                return;
            }
            int ofLifeCycleString = ActivityInfo.ofLifeCycleString(str);
            if (ofLifeCycleString > 0 && ofLifeCycleString <= 7) {
                ActivityCore.a(activity, 2, System.currentTimeMillis() - j, ofLifeCycleString);
            }
        }
    }

    public static void a(Context context) {
        ActivityCore.f1480a = System.currentTimeMillis();
    }

    public static boolean a() {
        return ActivityCore.a();
    }
}
