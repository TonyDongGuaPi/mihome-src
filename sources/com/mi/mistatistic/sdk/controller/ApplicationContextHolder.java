package com.mi.mistatistic.sdk.controller;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.mistatistic.sdk.StaticConstants;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ApplicationContextHolder {

    /* renamed from: a  reason: collision with root package name */
    private static Context f7323a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static String h = null;
    private static String i = "";
    private static String j = "";
    private static ArrayList<String> k;

    public static void a(Application application, String str, String str2) {
        f7323a = application;
        b = str;
        c = str2;
        f = String.valueOf(TimeUtil.a().b());
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
                ApplicationContextHolder.b(activity, true);
            }

            public void onActivityPaused(Activity activity) {
                ApplicationContextHolder.b(activity, false);
            }
        });
    }

    public static Context a() {
        return f7323a;
    }

    public static String b() {
        return b;
    }

    public static String c() {
        return c;
    }

    public static String d() {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        try {
            PackageInfo packageInfo = f7323a.getPackageManager().getPackageInfo(f7323a.getPackageName(), 16384);
            if (packageInfo != null) {
                d = String.valueOf(packageInfo.versionCode);
            }
        } catch (Exception unused) {
        }
        return d;
    }

    public static String e() {
        return PrefPersistUtils.a(f7323a, StaticConstants.f7320a, "");
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, boolean z) {
        if (k != null && k.size() > 0) {
            Iterator<String> it = k.iterator();
            while (it.hasNext()) {
                if (activity.getClass().getName().contains(it.next())) {
                    if (z) {
                        SessionManager.a().d();
                        Context applicationContext = activity.getApplicationContext();
                        a(applicationContext, j + activity.getClass().getSimpleName());
                        return;
                    }
                    SessionManager.a().e();
                    o();
                    return;
                }
            }
        } else if (z) {
            SessionManager.a().d();
            Context applicationContext2 = activity.getApplicationContext();
            a(applicationContext2, j + activity.getClass().getSimpleName());
        } else {
            SessionManager.a().e();
            o();
        }
    }

    private static void a(Context context, String str) {
        if (context != null && SessionManager.a() != null && !MiStatInterface.a().isDisableStat()) {
            SessionManager.a().a(context, str);
        }
    }

    private static void o() {
        if (SessionManager.a() != null && !MiStatInterface.a().isDisableStat()) {
            SessionManager.a().f();
        }
    }

    public static String f() {
        return g;
    }

    public static void a(String str) {
        g = str;
    }

    public static String g() {
        return h;
    }

    public static void b(String str) {
        h = str;
    }

    public static ArrayList<String> h() {
        return k;
    }

    public static void a(ArrayList<String> arrayList) {
        k = arrayList;
    }

    public static String i() {
        return f;
    }

    public static void c(String str) {
        d = str;
    }

    public static String j() {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        e = f7323a.getPackageName();
        return e;
    }

    public static void d(String str) {
        e = str;
    }

    public static String k() {
        return i;
    }

    public static void e(String str) {
        i = str;
    }

    public static void l() {
        i = "";
    }

    public static String m() {
        return j;
    }

    public static void f(String str) {
        j = str;
    }

    public static void n() {
        j = "";
    }
}
