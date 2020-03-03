package com.xiaomi.smarthome.miio.yeelight;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;

public class YeelightPrefManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f19994a = "YeelightPrefManager";
    static final String b = "yeelink_miio_scene";
    static final String c = "yeelight_user_color_count";
    static final String d = "yeelight_user_color";
    static final String e = "yeelight_user_bright";
    static final String f = "yeelight_user_color_name";

    static SharedPreferences a() {
        return PreferenceManager.getDefaultSharedPreferences(SHApplication.getAppContext());
    }

    public static boolean b() {
        return a().getBoolean("yeelink_miio_scene", true);
    }

    public static void c() {
        SharedPreferences.Editor edit = a().edit();
        edit.putBoolean("yeelink_miio_scene", false);
        edit.commit();
    }

    public static int d() {
        return a().getInt(c, 0);
    }

    public static void a(int i) {
        if (i >= 0) {
            SharedPreferences.Editor edit = a().edit();
            edit.putInt(c, i);
            edit.commit();
        }
    }

    public static int b(int i) {
        if (i >= d() || i < 0) {
            return 0;
        }
        SharedPreferences a2 = a();
        return a2.getInt(d + i, 0);
    }

    public static int c(int i) {
        if (i >= d() || i < 0) {
            return 0;
        }
        SharedPreferences a2 = a();
        int i2 = a2.getInt(e + i, 0);
        String str = f19994a;
        Log.d(str, "getBright=" + i2);
        return i2;
    }

    public static String d(int i) {
        if (i >= d() || i < 0) {
            return null;
        }
        SharedPreferences a2 = a();
        return a2.getString(f + i, (String) null);
    }

    public static void a(int i, int i2) {
        if (i < d() && i >= 0) {
            SharedPreferences.Editor edit = a().edit();
            edit.putInt(d + i, i2);
            edit.commit();
        }
    }

    public static void b(int i, int i2) {
        String str = f19994a;
        Log.d(str, "setBright=" + i2);
        if (i < d() && i >= 0) {
            SharedPreferences.Editor edit = a().edit();
            edit.putInt(e + i, i2);
            edit.commit();
        }
    }

    public static void a(int i, String str) {
        if (i < d() && i >= 0) {
            SharedPreferences.Editor edit = a().edit();
            edit.putString(f + i, str);
            edit.commit();
        }
    }

    public static void a(int i, int i2, String str) {
        int d2 = d();
        a(d2 + 1);
        a(d2, i);
        b(d2, i2);
        a(d2, str);
    }

    public static void e(int i) {
        if (i < d() && i >= 0) {
            while (i < d() - 1) {
                int i2 = i + 1;
                a(i, b(i2));
                b(i, c(i2));
                a(i, d(i2));
                i = i2;
            }
            a(d() - 1);
        }
    }
}
