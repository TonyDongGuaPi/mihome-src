package com.huawei.hms.update.f;

import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public abstract class a {
    public static String a(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.locale == null) {
            return "";
        }
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        if (language == null || country == null) {
            return "";
        }
        return language.toLowerCase(Locale.getDefault()) + '_' + country.toUpperCase(Locale.getDefault());
    }

    public static String b(Context context) {
        String a2 = a("ro.product.locale.region");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String d = d(context);
        return !TextUtils.isEmpty(d) ? d : "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = r1.locale.getCountry();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d(android.content.Context r1) {
        /*
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            java.util.Locale r0 = r1.locale
            if (r0 == 0) goto L_0x0015
            java.util.Locale r1 = r1.locale
            java.lang.String r1 = r1.getCountry()
            if (r1 == 0) goto L_0x0015
            return r1
        L_0x0015:
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.update.f.a.d(android.content.Context):java.lang.String");
    }

    private static String a(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            com.huawei.hms.support.log.a.d("UpdateUtils", "An exception occurred while reading: " + str);
            return "";
        }
    }

    public static boolean c(Context context) {
        String a2 = a("ro.product.locale.region");
        if (!TextUtils.isEmpty(a2)) {
            return "cn".equalsIgnoreCase(a2);
        }
        String d = d(context);
        return !TextUtils.isEmpty(d) ? "cn".equalsIgnoreCase(d) : e(context).startsWith("460") ? true : true;
    }

    private static String e(Context context) {
        String str = "";
        com.huawei.hms.update.f.a.a a2 = com.huawei.hms.update.f.a.a.a();
        if (a2 != null) {
            int b = a2.b();
            if (b == -1 || 5 == a2.b(b)) {
                str = a2.a(b);
                if (TextUtils.isEmpty(str)) {
                    str = a2.c(b);
                }
            }
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && 5 == telephonyManager.getSimState()) {
                str = telephonyManager.getSimOperator();
                if (TextUtils.isEmpty(str)) {
                    str = telephonyManager.getSubscriberId();
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            return "00000";
        }
        return str.substring(0, 5);
    }
}
