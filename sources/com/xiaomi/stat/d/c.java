package com.xiaomi.stat.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.xiaomi.stat.ak;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f23047a;
    private static int b;
    private static String c;

    public static int a() {
        if (!f23047a) {
            c();
        }
        return b;
    }

    public static String b() {
        if (!f23047a) {
            c();
        }
        return c;
    }

    private static void c() {
        if (!f23047a) {
            f23047a = true;
            Context a2 = ak.a();
            try {
                PackageInfo packageInfo = a2.getPackageManager().getPackageInfo(a2.getPackageName(), 0);
                b = packageInfo.versionCode;
                c = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
    }
}
