package com.xiaomi.jr.web.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.daimajia.androidanimations.library.BuildConfig;
import com.facebook.internal.AnalyticsEvents;
import com.xiaomi.jr.common.os.SystemProperties;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.MiuiClient;
import com.xiaomi.jr.common.utils.NetworkUtils;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11081a = "XiaoMi/MiuiBrowser/4.3";
    private static final String b = "%s Mozilla/5.0 (Linux; U; Android %s; %s-%s; %s Build/%s; AppVersionName/%s; AppVersionCode/%s; MiuiVersion/%s; DeviceId/%s; NetworkType/%s) AppleWebKit/%s (KHTML, like Gecko) Version/%s Mobile Safari/%s";
    private static final String c = "NETWORK_TYPE_PLACEHOLDER";
    private static String d;
    private static String e;
    private static String f;

    public static String a(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(d)) {
            String str3 = TextUtils.isEmpty(Build.VERSION.RELEASE) ? BuildConfig.VERSION_NAME : Build.VERSION.RELEASE;
            Locale locale = Locale.getDefault();
            String str4 = null;
            if (locale != null) {
                str4 = locale.getLanguage();
                str2 = locale.getCountry();
            } else {
                str2 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = "en";
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = "US";
            }
            d = String.format(Locale.US, b, new Object[]{f11081a, str3, str4, str2, TextUtils.isEmpty(Build.MODEL) ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : Build.MODEL, TextUtils.isEmpty(Build.ID) ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : Build.ID, AppUtils.g(context), Integer.valueOf(AppUtils.f(context)), MiuiClient.e(), SystemProperties.a("ro.product.device"), c, a(str), b(str), a(str)});
        }
        String d2 = NetworkUtils.d(context);
        String str5 = d;
        if (d2 == null) {
            d2 = "";
        }
        return str5.replace(c, d2);
    }

    private static String a(String str) {
        if (e != null) {
            return e;
        }
        e = a(Pattern.compile("(AppleWebKit)/([\\d\\.]+)"), str);
        return e != null ? e : "534.24";
    }

    private static String b(String str) {
        if (f != null) {
            return f;
        }
        f = a(Pattern.compile("(Version|Release|Chrome)/([\\d\\.]+)"), str);
        return f != null ? f : "4.01";
    }

    private static String a(Pattern pattern, String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find() || matcher.groupCount() != 2) {
            return null;
        }
        return matcher.group(2);
    }
}
