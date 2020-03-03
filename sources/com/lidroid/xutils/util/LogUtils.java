package com.lidroid.xutils.util;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {

    /* renamed from: a  reason: collision with root package name */
    public static String f6368a = "";
    public static boolean b = true;
    public static boolean c = true;
    public static boolean d = true;
    public static boolean e = true;
    public static boolean f = true;
    public static boolean g = true;
    public static CustomLogger h;

    public interface CustomLogger {
        void a(String str, String str2);

        void a(String str, String str2, Throwable th);

        void a(String str, Throwable th);

        void b(String str, String str2);

        void b(String str, String str2, Throwable th);

        void b(String str, Throwable th);

        void c(String str, String str2);

        void c(String str, String str2, Throwable th);

        void d(String str, String str2);

        void d(String str, String str2, Throwable th);

        void e(String str, String str2);

        void e(String str, String str2, Throwable th);

        void f(String str, String str2);

        void f(String str, String str2, Throwable th);
    }

    private LogUtils() {
    }

    private static String a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(L:%d)", new Object[]{className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        if (TextUtils.isEmpty(f6368a)) {
            return format;
        }
        return String.valueOf(f6368a) + ":" + format;
    }

    public static void a(String str) {
        if (b) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.a(a2, str);
            } else {
                Log.d(a2, str);
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (b) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.a(a2, str, th);
            } else {
                Log.d(a2, str, th);
            }
        }
    }

    public static void b(String str) {
        if (c) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.b(a2, str);
            } else {
                Log.e(a2, str);
            }
        }
    }

    public static void b(String str, Throwable th) {
        if (c) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.b(a2, str, th);
            } else {
                Log.e(a2, str, th);
            }
        }
    }

    public static void c(String str) {
        if (d) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.c(a2, str);
            } else {
                Log.i(a2, str);
            }
        }
    }

    public static void c(String str, Throwable th) {
        if (d) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.c(a2, str, th);
            } else {
                Log.i(a2, str, th);
            }
        }
    }

    public static void d(String str) {
        if (e) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.d(a2, str);
            } else {
                Log.v(a2, str);
            }
        }
    }

    public static void d(String str, Throwable th) {
        if (e) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.d(a2, str, th);
            } else {
                Log.v(a2, str, th);
            }
        }
    }

    public static void e(String str) {
        if (f) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.e(a2, str);
            } else {
                Log.w(a2, str);
            }
        }
    }

    public static void e(String str, Throwable th) {
        if (f) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.e(a2, str, th);
            } else {
                Log.w(a2, str, th);
            }
        }
    }

    public static void a(Throwable th) {
        if (f) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.a(a2, th);
            } else {
                Log.w(a2, th);
            }
        }
    }

    public static void f(String str) {
        if (g) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.f(a2, str);
            } else {
                Log.wtf(a2, str);
            }
        }
    }

    public static void f(String str, Throwable th) {
        if (g) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.f(a2, str, th);
            } else {
                Log.wtf(a2, str, th);
            }
        }
    }

    public static void b(Throwable th) {
        if (g) {
            String a2 = a(OtherUtils.b());
            if (h != null) {
                h.b(a2, th);
            } else {
                Log.wtf(a2, th);
            }
        }
    }
}
