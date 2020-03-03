package com.xiaomi.stat.d;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class n {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23061a = 64;
    private static final int b = 256;
    private static final int c = 10240;
    private static final String d = "mistat_";
    private static final String e = "mi_";
    private static final String f = "abtest_";
    private static final String g = "null";
    private static Pattern h = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*");

    public static String c(String str) {
        return str == null ? "null" : str;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.length() > 64 || str.startsWith(d) || str.startsWith(e) || str.startsWith(f)) {
            return false;
        }
        return h.matcher(str).matches();
    }

    public static boolean b(String str) {
        return str == null || str.length() <= 256;
    }

    public static boolean d(String str) {
        return str == null || str.length() <= 10240;
    }

    public static void e(String str) {
        k.e("invalid parameter name: " + str);
    }

    public static void f(String str) {
        k.e("parameter value is too long: " + str);
    }

    public static void a() {
        k.e("parameter number exceed limits");
    }
}
