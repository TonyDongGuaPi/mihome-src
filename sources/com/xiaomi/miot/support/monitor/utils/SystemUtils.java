package com.xiaomi.miot.support.monitor.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.Env;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SystemUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11500a = "SystemUtils";

    public static Integer a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (ApplicationInfo next : a(context.getApplicationContext().getPackageManager().getInstalledApplications(128))) {
            if (!TextUtils.isEmpty(next.packageName) && str.equalsIgnoreCase(next.packageName)) {
                return Integer.valueOf(next.uid);
            }
        }
        return null;
    }

    private static List<ApplicationInfo> a(List<ApplicationInfo> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list == null) {
            return arrayList2;
        }
        for (ApplicationInfo next : list) {
            int i = next.uid;
            if (!arrayList.contains(Integer.valueOf(i))) {
                arrayList.add(Integer.valueOf(i));
                arrayList2.add(next);
            }
        }
        list.clear();
        arrayList.clear();
        return arrayList2;
    }

    public static boolean a() {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) MiotMonitorManager.a().h().getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static final boolean c() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static final boolean d() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static final boolean e() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static final boolean f() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static final boolean g() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static final boolean h() {
        return Build.VERSION.SDK_INT <= 24;
    }

    public static boolean i() {
        File file = new File("/system/xbin/su");
        if (!file.exists()) {
            file = new File("/system/bin/su");
        }
        if (!file.exists()) {
            return false;
        }
        try {
            Runtime.getRuntime().exec("su");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean j() {
        String a2 = a("ro.build.uiversion");
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return a2.contains("360UI");
    }

    public static String a(String str) {
        try {
            Object a2 = a("android.os.SystemProperties", "get", new Class[]{String.class}, str);
            if (a2 == null || !(a2 instanceof String)) {
                return null;
            }
            return (String) a2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object a(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        Method method;
        try {
            Class<?> cls = Class.forName(str);
            if (!(cls == null || (method = cls.getMethod(str2, clsArr)) == null)) {
                method.setAccessible(true);
                return method.invoke((Object) null, objArr);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static final String k() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean b(String str) {
        Context h;
        PackageManager packageManager;
        int i;
        if (TextUtils.isEmpty(str) || (h = MiotMonitorManager.a().h()) == null) {
            return false;
        }
        String packageName = h.getPackageName();
        if (TextUtils.isEmpty(packageName) || (packageManager = h.getPackageManager()) == null) {
            return false;
        }
        try {
            i = packageManager.checkPermission(str, packageName);
        } catch (Exception e) {
            LogX.d(Env.c, f11500a, e.toString());
            i = -1;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }
}
