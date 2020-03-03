package com.xiaomi.jr.common.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Locale;

public class MiuiImageUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10369a = "http://t1.market.xiaomi.com/thumbnail/";
    private static final String b = "http://t1.market.xiaomi.com/download/";
    private static final String c = "webp/w%dq70/";
    private static final String d = "webp/h%dq70/";

    private static String a(String str, String str2) {
        return f10369a + str2 + str;
    }

    public static String a(String str, int i) {
        return a(str, String.format(Locale.getDefault(), c, new Object[]{Integer.valueOf(i)}));
    }

    public static String b(String str, int i) {
        return a(str, String.format(Locale.getDefault(), d, new Object[]{Integer.valueOf(i)}));
    }

    public static String a(String str) {
        return b + str;
    }

    public static String c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!TextUtils.equals(Uri.parse(str).getScheme(), Constants.i)) {
            return str;
        }
        String substring = str.substring("miuifile://".length());
        if (i == 0) {
            return a(substring);
        }
        return a(substring, i);
    }
}
