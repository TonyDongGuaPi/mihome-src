package com.xiaomi.youpin.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.xiaomi.youpin.common.util.AppInfo;
import java.util.UUID;

public class AppIdManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23220a = "com.xiaomi.youpin.appid";
    private static final String b = "youpin_imei_20190426";
    private static final String c = "youpin_deviceid_20190426";
    private static final String d = "uuid-";
    private static AppIdManager e;
    private static String f;
    private static String g;
    private SharedPreferences h = AppInfo.a().getSharedPreferences(f23220a, 0);

    private AppIdManager() {
        e();
    }

    public static AppIdManager a() {
        if (e == null) {
            synchronized (AppIdManager.class) {
                if (e == null) {
                    e = new AppIdManager();
                }
            }
        }
        return e;
    }

    private static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    private static String d() {
        return d + UUID.randomUUID().toString();
    }

    private static boolean a(String str) {
        return str.startsWith(d);
    }

    private void e() {
        Application a2 = AppInfo.a();
        String f2 = f();
        String g2 = g();
        if (TextUtils.isEmpty(f2) || TextUtils.isEmpty(g2)) {
            if (a((Context) a2, "android.permission.READ_PHONE_STATE")) {
                f2 = AppInfo.j();
                g2 = AppInfo.k();
            }
            String str = "";
            if (TextUtils.isEmpty(f2) || TextUtils.isEmpty(g2)) {
                str = d();
            }
            if (TextUtils.isEmpty(f2) || a(f2)) {
                f2 = str;
            }
            if (!TextUtils.isEmpty(g2) && !a(g2)) {
                str = g2;
            }
            a(f2, str);
            return;
        }
        f = f2;
        g = g2;
    }

    public String b() {
        if (TextUtils.isEmpty(f)) {
            f = f();
        }
        if (TextUtils.isEmpty(f)) {
            e();
        }
        return f;
    }

    public String c() {
        if (TextUtils.isEmpty(g)) {
            g = g();
        }
        if (TextUtils.isEmpty(g)) {
            e();
        }
        return g;
    }

    private String f() {
        return this.h.getString(b, "");
    }

    private String g() {
        return this.h.getString(c, "");
    }

    private void a(String str, String str2) {
        f = str;
        g = str2;
        SharedPreferences.Editor edit = this.h.edit();
        edit.putString(b, str);
        edit.putString(c, str2);
        edit.apply();
    }
}
