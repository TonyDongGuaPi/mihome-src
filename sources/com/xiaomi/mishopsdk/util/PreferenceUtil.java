package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;

public class PreferenceUtil {
    public static void setIntPref(Context context, String str, int i) {
        SharedPreferences defaultSharedPreferences;
        SharedPreferences.Editor edit;
        if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.putInt(str, i);
            edit.apply();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getIntPref(android.content.Context r0, java.lang.String r1, int r2) {
        /*
            if (r0 != 0) goto L_0x0003
            return r2
        L_0x0003:
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x000e
            int r0 = r0.getInt(r1, r2)
            return r0
        L_0x000e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.PreferenceUtil.getIntPref(android.content.Context, java.lang.String, int):int");
    }

    public static void setLongPref(Context context, String str, Long l) {
        SharedPreferences defaultSharedPreferences;
        SharedPreferences.Editor edit;
        if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.putLong(str, l.longValue());
            edit.apply();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getLongPref(android.content.Context r0, java.lang.String r1, long r2) {
        /*
            if (r0 != 0) goto L_0x0003
            return r2
        L_0x0003:
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x000e
            long r0 = r0.getLong(r1, r2)
            return r0
        L_0x000e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.PreferenceUtil.getLongPref(android.content.Context, java.lang.String, long):long");
    }

    public static void setStringPref(Context context, String str, String str2) {
        SharedPreferences defaultSharedPreferences;
        SharedPreferences.Editor edit;
        if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.putString(str, str2);
            edit.apply();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getStringPref(android.content.Context r0, java.lang.String r1, java.lang.String r2) {
        /*
            if (r0 != 0) goto L_0x0003
            return r2
        L_0x0003:
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r0.getString(r1, r2)
            return r0
        L_0x000e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.PreferenceUtil.getStringPref(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean getBooleanPref(android.content.Context r0, java.lang.String r1, boolean r2) {
        /*
            if (r0 != 0) goto L_0x0003
            return r2
        L_0x0003:
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x000e
            boolean r0 = r0.getBoolean(r1, r2)
            return r0
        L_0x000e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.PreferenceUtil.getBooleanPref(android.content.Context, java.lang.String, boolean):boolean");
    }

    public static void setBooleanPref(Context context, String str, boolean z) {
        SharedPreferences defaultSharedPreferences;
        SharedPreferences.Editor edit;
        if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.putBoolean(str, z);
            edit.apply();
        }
    }

    public static void removePref(Context context, String str) {
        SharedPreferences defaultSharedPreferences;
        SharedPreferences.Editor edit;
        if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.remove(str);
            edit.apply();
        }
    }

    public static ArrayList<String> getAllPreferenceKey(Context context) {
        SharedPreferences defaultSharedPreferences;
        if (context == null || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String obj : defaultSharedPreferences.getAll().keySet()) {
            arrayList.add(obj.toString());
        }
        return arrayList;
    }
}
