package com.xiaomi.smarthome.core.server.internal.util;

import android.os.Build;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.Locale;

public class LocaleUtil {
    public static boolean a(Locale locale, Locale locale2) {
        if (locale == locale2) {
            return true;
        }
        if (locale == null || locale2 == null || !locale.getLanguage().equalsIgnoreCase(locale2.getLanguage()) || !locale.getCountry().equalsIgnoreCase(locale2.getCountry())) {
            return false;
        }
        return true;
    }

    public static Locale a() {
        Locale I = CoreApi.a().I();
        if (I != null) {
            return I;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return ServiceApplication.getAppContext().getResources().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    public static Locale b() {
        if (Build.VERSION.SDK_INT >= 24) {
            return ServiceApplication.getAppContext().getResources().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    public static boolean c() {
        Locale a2 = a();
        if (a2 != null && !a(a2, Locale.SIMPLIFIED_CHINESE) && !a(a2, Locale.CHINESE)) {
            return false;
        }
        return true;
    }

    public static String a(Locale locale) {
        return b(locale);
    }

    public static String b(Locale locale) {
        if (locale == null) {
            return "";
        }
        String language = locale.getLanguage();
        if (TextUtils.equals("iw", language)) {
            language = "he";
        } else if (TextUtils.equals("ji", language)) {
            language = "yi";
        } else if (TextUtils.equals("in", language)) {
            language = "id";
        }
        String country = locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return language + JSMethod.NOT_SET + country;
    }
}
