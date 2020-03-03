package com.xiaomi.smarthome.frame;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.frame.process.ProcessUtil;

public class AppUsrExpPlanUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15987a = "com.xiaomi.smarthome.usr_exp_plan_change";
    public static final String b = "usr_exp_plan_value";
    public static final int c = -1;
    public static final int d = 1;
    public static final int e = 0;
    private static volatile boolean f = false;
    private static volatile boolean g = true;

    public static boolean a(Context context) {
        if (context == null) {
            return true;
        }
        if (f && ProcessUtil.h(context)) {
            return g;
        }
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.xiaomi.smarthome.core_auth"), CoreContentProvider.METHOD_KEY_isUsrExpPlanEnabled, (String) null, (Bundle) null);
            if (call == null) {
                g = true;
                f = true;
                return true;
            }
            f = true;
            boolean z = call.getBoolean("enabled", true);
            g = z;
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            return true;
        }
        try {
            boolean h = ProcessUtil.h(context);
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.xiaomi.smarthome.core_auth"), CoreContentProvider.METHOD_KEY_isUsrExpPlanEnabled, (String) null, (Bundle) null);
            if (call == null) {
                if (h) {
                    g = true;
                    f = true;
                }
                return true;
            }
            if (h) {
                g = call.getBoolean("enabled", true);
                f = true;
            }
            return call.getBoolean("enabled", true);
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static void a(Context context, boolean z) {
        boolean z2;
        if (context != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean("enabled", z);
                Bundle call = context.getContentResolver().call(Uri.parse("content://com.xiaomi.smarthome.core_auth"), CoreContentProvider.METHOD_KEY_setUsrExpPlanEnabled, (String) null, bundle);
                if (call == null) {
                    z2 = true;
                } else {
                    z2 = call.getBoolean("enabled", true);
                }
                if (ProcessUtil.h(context)) {
                    g = z;
                    f = true;
                }
                if (z2) {
                    b(context, z);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("device_id", str);
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.xiaomi.smarthome.core_auth"), CoreContentProvider.METHOD_KEY_isDeviceUsrExpPlanEnabled, (String) null, bundle);
            if (call == null) {
                return true;
            }
            return call.getBoolean("enabled", true);
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static void a(Context context, String str, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("device_id", str);
                bundle.putBoolean("enabled", z);
                context.getContentResolver().call(Uri.parse("content://com.xiaomi.smarthome.core_auth"), CoreContentProvider.METHOD_KEY_setDeviceUsrExpPlanEnabled, (String) null, bundle);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void b(Context context, boolean z) {
        Intent intent = new Intent("com.xiaomi.smarthome.usr_exp_plan_change");
        intent.putExtra("usr_exp_plan_value", z);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        a(z);
    }

    private static void a(boolean z) {
        if (z) {
            CrashReport.enableBugly(false);
        } else {
            CrashReport.enableBugly(true);
        }
    }
}
