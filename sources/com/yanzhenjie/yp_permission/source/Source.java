package com.yanzhenjie.yp_permission.source;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

public abstract class Source {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2473a = "android:system_alert_window";
    private static final int b = 66;
    private PackageManager c;
    private AppOpsManager d;
    private int e;

    public abstract Context a();

    public abstract void a(Intent intent);

    public abstract void a(Intent intent, int i);

    public abstract boolean a(String str);

    private int d() {
        if (this.e < 14) {
            this.e = a().getApplicationInfo().targetSdkVersion;
        }
        return this.e;
    }

    private PackageManager e() {
        if (this.c == null) {
            this.c = a().getPackageManager();
        }
        return this.c;
    }

    private AppOpsManager f() {
        if (this.d == null) {
            this.d = (AppOpsManager) a().getSystemService("appops");
        }
        return this.d;
    }

    public final boolean b() {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT < 26) {
            return true;
        }
        if (d() >= 26) {
            return e().canRequestPackageInstalls();
        }
        Class<AppOpsManager> cls = AppOpsManager.class;
        try {
            if (((Integer) cls.getDeclaredMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(f(), new Object[]{66, Integer.valueOf(Process.myUid()), a().getPackageName()})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public final boolean c() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (f().checkOpNoThrow(f2473a, Process.myUid(), a().getPackageName()) == 0) {
            return true;
        }
        return false;
    }
}
