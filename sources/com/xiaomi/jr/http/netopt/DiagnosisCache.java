package com.xiaomi.jr.http.netopt;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.PreferenceUtils;

class DiagnosisCache {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10832a = "DiagnosisCache";
    private static final String b = "network_diagnosis";
    private static final String c = "diagnosis_";
    private static final String d = "diagnosis_reason_";
    private static final String e = "a";
    private static final String f = "b";
    private static final String g = "diagnosis_timestamp";
    private static String h = null;
    private static String i = null;
    private static boolean j = true;

    DiagnosisCache() {
    }

    static void a(Context context, String str) {
        PreferenceUtils.a(context, b, c + h, str);
        d(context);
    }

    static void b(Context context, String str) {
        String str2 = d + h;
        PreferenceUtils.a(context, b, str2, a(PreferenceUtils.d(context, b, str2), str));
        d(context);
    }

    private static String a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        int length = str.length();
        for (int i2 = 0; i2 < 10 && (length = str.lastIndexOf(59, length - 1)) >= 0; i2++) {
        }
        if (length >= 0 && "; ".length() + length < str.length()) {
            str = str.substring(length + "; ".length());
        }
        return str + str2 + "; ";
    }

    static void a(Context context) {
        if (h == null || i == null) {
            MifiLog.b(f10832a, "cache done, init buffer id");
            long[] f2 = f(context);
            h = f2[0] < f2[1] ? "a" : f;
            i = f2[0] < f2[1] ? f : "a";
            return;
        }
        MifiLog.b(f10832a, "cache done, swap buffer");
        String str = h;
        h = i;
        i = str;
        j = true;
    }

    static String[] b(Context context) {
        String d2 = PreferenceUtils.d(context, b, c + i);
        MifiLog.b(f10832a, "loadReport sLoadId: " + i + ", diagnosis: " + d2);
        if (TextUtils.isEmpty(d2)) {
            PreferenceUtils.a(context, b, d + i, (String) null);
            return null;
        }
        return new String[]{d2, PreferenceUtils.d(context, b, d + i)};
    }

    static void c(Context context) {
        PreferenceUtils.a(context, b, c + i, (String) null);
        PreferenceUtils.a(context, b, d + i, (String) null);
        e(context);
    }

    private static void d(Context context) {
        if (j) {
            j = false;
            long[] f2 = f(context);
            if (TextUtils.equals(h, "a")) {
                f2[0] = System.currentTimeMillis();
            } else {
                f2[1] = System.currentTimeMillis();
            }
            PreferenceUtils.a(context, b, g, String.format("a:%s,b:%s", new Object[]{Long.valueOf(f2[0]), Long.valueOf(f2[1])}));
        }
    }

    private static void e(Context context) {
        long[] f2 = f(context);
        if (TextUtils.equals(i, "a")) {
            f2[0] = 0;
        } else {
            f2[1] = 0;
        }
        PreferenceUtils.a(context, b, g, String.format("a:%s,b:%s", new Object[]{Long.valueOf(f2[0]), Long.valueOf(f2[1])}));
    }

    private static long[] f(Context context) {
        long[] jArr = {0, 0};
        String d2 = PreferenceUtils.d(context, b, g);
        try {
            if (!TextUtils.isEmpty(d2)) {
                String[] split = d2.split(",");
                if (split.length == 2) {
                    jArr[0] = Long.parseLong(split[0].split(":")[1]);
                    jArr[1] = Long.parseLong(split[1].split(":")[1]);
                }
            }
        } catch (Exception unused) {
        }
        return jArr;
    }
}
