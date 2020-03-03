package com.tencent.wxop.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class p {

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences f9323a;

    public static int a(Context context, String str, int i) {
        return a(context).getInt(k.a(context, StatConstants.g + str), i);
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(k.a(context, StatConstants.g + str), j);
    }

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (p.class) {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(".mta-wxop", 0);
            f9323a = sharedPreferences2;
            if (sharedPreferences2 == null) {
                f9323a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = f9323a;
        }
        return sharedPreferences;
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(k.a(context, StatConstants.g + str), str2);
    }

    public static void b(Context context, String str, int i) {
        String a2 = k.a(context, StatConstants.g + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putInt(a2, i);
        edit.commit();
    }

    public static void b(Context context, String str, long j) {
        String a2 = k.a(context, StatConstants.g + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putLong(a2, j);
        edit.commit();
    }

    public static void b(Context context, String str, String str2) {
        String a2 = k.a(context, StatConstants.g + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putString(a2, str2);
        edit.commit();
    }
}
