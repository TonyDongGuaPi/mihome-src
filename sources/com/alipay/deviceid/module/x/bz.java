package com.alipay.deviceid.module.x;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public final class bz {

    /* renamed from: a  reason: collision with root package name */
    private static String f901a = "";

    public static void a(Context context, String str) {
        a(context, "webrtcurl", str);
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : "0");
    }

    public static long b(Context context, String str) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("alipay_device_id_settings", 0);
            String string = sharedPreferences.getString("vkey_valid" + str, "");
            if (e.a(string)) {
                return 0;
            }
            String b = i.b(i.a(), string);
            if (e.a(b)) {
                return 0;
            }
            return Long.parseLong(b);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static void a(Context context, String str, long j) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("alipay_device_id_settings", 0).edit();
            if (edit != null) {
                String a2 = i.a(i.a(), String.valueOf(j));
                edit.putString("vkey_valid" + str, a2);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, String str, String str2) {
        cb.a(context, "alipay_device_id_settings", str, str2);
    }

    public static synchronized String b(Context context) {
        String str;
        SharedPreferences.Editor edit;
        synchronized (bz.class) {
            if (e.a(f901a)) {
                String a2 = v.a(context, "alipay_device_id_tags", "random", "");
                f901a = a2;
                if (e.a(a2)) {
                    f901a = h.a(UUID.randomUUID().toString());
                    String str2 = f901a;
                    if (!(str2 == null || (edit = context.getSharedPreferences("alipay_device_id_tags", 0).edit()) == null)) {
                        edit.putString("random", str2);
                        edit.commit();
                    }
                }
            }
            str = f901a;
        }
        return str;
    }

    public static boolean a(Context context) {
        String a2 = cb.a(context, "alipay_device_id_settings", "log_switch");
        return a2 != null && "1".equals(a2);
    }
}
