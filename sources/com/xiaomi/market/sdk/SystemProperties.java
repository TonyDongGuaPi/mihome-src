package com.xiaomi.market.sdk;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.lang.reflect.Method;

class SystemProperties {

    /* renamed from: a  reason: collision with root package name */
    private static Class<?> f11114a;
    private static Method b;

    SystemProperties() {
    }

    static {
        try {
            f11114a = Class.forName("android.os.SystemProperties");
            b = f11114a.getDeclaredMethod("get", new Class[]{String.class, String.class});
        } catch (Exception e) {
            Log.b("MarketSdkUtils", e.getMessage(), (Throwable) e);
        }
    }

    @NonNull
    public static String a(String str, String str2) {
        try {
            String str3 = (String) b.invoke(f11114a, new Object[]{str, str2});
            return !TextUtils.isEmpty(str3) ? str3 : str2;
        } catch (Exception e) {
            Log.b("MarketSdkUtils", e.getMessage(), (Throwable) e);
            return str2;
        }
    }
}
