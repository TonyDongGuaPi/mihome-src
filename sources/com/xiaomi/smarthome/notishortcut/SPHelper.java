package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.content.SharedPreferences;

public class SPHelper {
    public static boolean a(Context context, String str) {
        return context.getSharedPreferences(str, 0).edit().clear().commit();
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, str3);
            return edit.commit();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String b(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Exception unused) {
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
    }
}
