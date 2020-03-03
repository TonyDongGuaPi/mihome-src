package com.hianalytics.android.v1;

import android.content.Context;
import android.content.SharedPreferences;
import com.hianalytics.android.b.a.c;

public class SessionContext {
    public static String a(Context context) {
        return c.a(context, "sessioncontext").getString("session_id", "");
    }

    public static void a(Context context, String str) {
        SharedPreferences.Editor edit = c.a(context, "sessioncontext").edit();
        edit.putString("session_id", str);
        edit.commit();
    }

    public static String b(Context context) {
        return c.a(context, "sessioncontext").getString("refer_id", "");
    }

    public static void b(Context context, String str) {
        SharedPreferences.Editor edit = c.a(context, "sessioncontext").edit();
        edit.putString("refer_id", str);
        edit.commit();
    }

    public static void c(Context context) {
        SharedPreferences.Editor edit = c.a(context, "sessioncontext").edit();
        edit.remove("session_id");
        edit.remove("refer_id");
        edit.commit();
    }

    public static void c(Context context, String str) {
        SharedPreferences a2 = c.a(context, "sessioncontext");
        String e = e(context);
        SharedPreferences.Editor edit = a2.edit();
        edit.remove("session_id");
        edit.remove("refer_id");
        edit.putString("session_id", e);
        edit.putString("refer_id", str);
        edit.commit();
    }

    public static void d(Context context) {
        SharedPreferences.Editor edit = c.a(context, "sessioncontext").edit();
        edit.remove("refer_id");
        edit.commit();
    }

    private static String e(Context context) {
        return String.valueOf(System.currentTimeMillis());
    }
}
