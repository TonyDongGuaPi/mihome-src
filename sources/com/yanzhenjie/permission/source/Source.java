package com.yanzhenjie.permission.source;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import java.lang.reflect.Method;

public abstract class Source {
    public abstract Context a();

    public abstract void a(Intent intent);

    public abstract void a(Intent intent, int i);

    public final boolean a(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        PackageManager packageManager = a().getPackageManager();
        Class<?> cls = packageManager.getClass();
        try {
            ActivityCompat.shouldShowRequestPermissionRationale((Activity) null, "");
            Method method = cls.getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class});
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return ((Boolean) method.invoke(packageManager, new Object[]{str})).booleanValue();
        } catch (Exception unused) {
            Context a2 = a();
            if (a2 == null || !(a2 instanceof Activity)) {
                return false;
            }
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) a2, str);
        }
    }
}
