package com.xiaomi.jr.common.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public class UrlUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1422a = "UrlUtils";

    public static String a(@NonNull String str) {
        Uri parse = Uri.parse(str);
        return parse.getScheme() + "://" + parse.getHost() + "/";
    }

    public static String a(@NonNull String str, @NonNull String str2) {
        return str.replace(a(str), str2);
    }

    public static String b(String str, String str2) {
        try {
            return Uri.parse(str).getQueryParameter(str2);
        } catch (Exception e) {
            MifiLog.e(f1422a, e.getMessage());
            return null;
        }
    }

    public static boolean a(String str, String str2, boolean z) {
        try {
            return Uri.parse(str).getBooleanQueryParameter(str2, z);
        } catch (Exception e) {
            MifiLog.e(f1422a, e.getMessage());
            return z;
        }
    }

    public static String a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse.getQueryParameter(str2) == null) {
                parse = parse.buildUpon().appendQueryParameter(str2, str3).build();
            }
            return parse.toString();
        } catch (Exception e) {
            MifiLog.e(f1422a, "Exception throws " + e + ", url = " + str);
            return str;
        }
    }

    public static String c(@NonNull String str, @NonNull String str2) {
        Uri parse = Uri.parse(str);
        try {
            for (String next : parse.getQueryParameterNames()) {
                str2 = a(str2, next, parse.getQueryParameter(next));
            }
            return str2;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage() + " - origUrl: " + str);
        }
    }

    public static String b(@NonNull String str) {
        return Uri.parse(str).buildUpon().clearQuery().build().toString();
    }
}
