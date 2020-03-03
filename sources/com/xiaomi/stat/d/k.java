package com.xiaomi.stat.d;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.stat.ak;

public class k {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23057a = "http://test.data.mistat.xiaomi.srv/get_all_config";
    public static final String b = "http://test-idservice.data.mistat.xiaomi.com/deviceid_get";
    public static final String c = "http://test.data.mistat.xiaomi.srv/mistats/v3";
    private static final String d = "MI_STAT";
    private static final String e = "com.xiaomi.stat.demo";
    private static boolean f = false;
    private static final int g = 4000;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f23058a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
    }

    private static void a(int i, String str, String str2, Throwable th) {
        String concat = (TextUtils.isEmpty(str) || TextUtils.equals(str, d)) ? d : d.concat(JSMethod.NOT_SET).concat(str);
        if (i == 0) {
            Log.v(concat, str2, th);
        } else if (i == 1) {
            Log.i(concat, str2, th);
        } else if (i == 2) {
            Log.d(concat, str2, th);
        } else if (i == 3) {
            Log.w(concat, str2, th);
        } else if (i == 4) {
            Log.e(concat, str2, th);
        }
    }

    private static void b(int i, String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.length() > 4000) {
            a(i, str, str2.substring(0, 4000), (Throwable) null);
            b(i, str, str2.substring(4000, str2.length()), (Throwable) null);
            return;
        }
        a(i, str, str2, th);
    }

    public static void a(boolean z) {
        f = z;
    }

    public static boolean a() {
        return f;
    }

    public static void a(String str) {
        a(d, str);
    }

    public static void a(String str, String str2) {
        if (f) {
            b(0, str, str2, (Throwable) null);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f) {
            b(0, str, str2, th);
        }
    }

    public static void b(String str) {
        b(d, str);
    }

    public static void b(String str, String str2) {
        if (f) {
            b(2, str, str2, (Throwable) null);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (f) {
            b(str, str2 + " " + str3);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f) {
            b(2, str, str2, th);
        }
    }

    public static void c(String str) {
        c(d, str);
    }

    public static void c(String str, String str2) {
        if (f) {
            b(1, str, str2, (Throwable) null);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (f) {
            b(1, str, str2, th);
        }
    }

    public static void d(String str) {
        d(d, str);
    }

    public static void d(String str, String str2) {
        if (f) {
            b(3, str, str2, (Throwable) null);
        }
    }

    public static void a(String str, Throwable th) {
        if (f) {
            b(3, str, (String) null, th);
        }
    }

    public static void e(String str) {
        e(d, str);
    }

    public static void e(String str, String str2) {
        if (f) {
            b(4, str, str2, (Throwable) null);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (f) {
            b(4, str, str2, th);
        }
    }

    public static String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static boolean b() {
        Context a2;
        try {
            if (a() && (a2 = ak.a()) != null && TextUtils.equals(e, a2.getPackageName())) {
                return a2.getSharedPreferences("demo_config", 0).getBoolean("mistat_test_url", false);
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
