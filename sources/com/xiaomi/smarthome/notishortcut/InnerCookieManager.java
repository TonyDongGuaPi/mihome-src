package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

public class InnerCookieManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1560a = "cookie_setting";

    public static synchronized void a(Context context, String str) {
        synchronized (InnerCookieManager.class) {
            if (!TextUtils.isEmpty(str)) {
                SPHelper.a(context, f1560a, "server", str);
            }
        }
    }

    public static synchronized String a(Context context) {
        String b;
        synchronized (InnerCookieManager.class) {
            b = SPHelper.b(context, f1560a, "server", "");
        }
        return b;
    }

    public static synchronized void b(Context context, String str) {
        synchronized (InnerCookieManager.class) {
            if (!TextUtils.isEmpty(str)) {
                SPHelper.a(context, f1560a, "serverEnv", str);
            }
        }
    }

    public static synchronized String b(Context context) {
        String b;
        synchronized (InnerCookieManager.class) {
            b = SPHelper.b(context, f1560a, "serverEnv", "release");
        }
        return b;
    }

    public static synchronized void a(Context context, Locale locale) {
        synchronized (InnerCookieManager.class) {
            if (locale != null) {
                if (!TextUtils.isEmpty(locale.getCountry()) && !TextUtils.isEmpty(locale.getLanguage())) {
                    SPHelper.a(context, f1560a, "language", locale.getLanguage());
                    SPHelper.a(context, f1560a, "country", locale.getCountry());
                }
            }
        }
    }

    public static synchronized Locale c(Context context) {
        Locale locale;
        synchronized (InnerCookieManager.class) {
            locale = new Locale(SPHelper.b(context, f1560a, "language", ""), SPHelper.b(context, f1560a, "country", ""));
        }
        return locale;
    }
}
