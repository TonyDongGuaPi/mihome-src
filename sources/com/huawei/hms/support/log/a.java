package com.huawei.hms.support.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.taobao.weex.el.parse.Operators;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final b f5894a = new b();

    public static void a(Context context, int i, String str) {
        f5894a.a(context, i, str);
        f5894a.a(str, "============================================================================" + 10 + "====== " + a(context) + 10 + "============================================================================");
    }

    private static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "HMS-[unknown-version]";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return "HMS-" + packageInfo.versionName + Operators.BRACKET_START_STR + packageInfo.versionCode + Operators.BRACKET_END_STR;
        } catch (PackageManager.NameNotFoundException unused) {
            return "HMS-[unknown-version]";
        }
    }

    public static boolean a() {
        return f5894a.a(3);
    }

    public static boolean b() {
        return f5894a.a(4);
    }

    public static boolean c() {
        return f5894a.a(5);
    }

    public static boolean d() {
        return f5894a.a(6);
    }

    public static void a(String str, String str2) {
        f5894a.a(3, str, str2);
    }

    public static void b(String str, String str2) {
        f5894a.a(4, str, str2);
    }

    public static void c(String str, String str2) {
        f5894a.a(5, str, str2);
    }

    public static void d(String str, String str2) {
        f5894a.a(6, str, str2);
    }
}
