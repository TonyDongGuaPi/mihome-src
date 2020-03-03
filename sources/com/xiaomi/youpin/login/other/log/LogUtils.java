package com.xiaomi.youpin.login.other.log;

import android.util.Log;

public class LogUtils {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f23593a = false;
    public static LogInterface b;

    public static void a(boolean z) {
        f23593a = z;
    }

    public static void a(LogInterface logInterface) {
        b = logInterface;
    }

    public static int a(String str, String str2) {
        if (f23593a) {
            return Log.v(str, str2);
        }
        if (b == null) {
            return 0;
        }
        b.a(str, str2);
        return 0;
    }

    public static int a(String str, String str2, Throwable th) {
        if (f23593a) {
            return Log.v(str, str2, th);
        }
        if (b == null) {
            return 0;
        }
        b.a(str, str2, th);
        return 0;
    }

    public static int b(String str, String str2) {
        if (f23593a) {
            return Log.d(str, str2);
        }
        if (b == null) {
            return 0;
        }
        b.b(str, str2);
        return 0;
    }

    public static int b(String str, String str2, Throwable th) {
        if (f23593a) {
            return Log.d(str, str2, th);
        }
        if (b == null) {
            return 0;
        }
        b.b(str, str2, th);
        return 0;
    }

    public static int c(String str, String str2) {
        if (f23593a) {
            return Log.i(str, str2);
        }
        if (b == null) {
            return 0;
        }
        b.c(str, str2);
        return 0;
    }

    public static int c(String str, String str2, Throwable th) {
        if (f23593a) {
            return Log.i(str, str2, th);
        }
        if (b == null) {
            return 0;
        }
        b.c(str, str2, th);
        return 0;
    }

    public static int d(String str, String str2) {
        if (f23593a) {
            return Log.w(str, str2);
        }
        if (b == null) {
            return 0;
        }
        b.d(str, str2);
        return 0;
    }

    public static int d(String str, String str2, Throwable th) {
        if (f23593a) {
            return Log.w(str, str2, th);
        }
        if (b == null) {
            return 0;
        }
        b.d(str, str2, th);
        return 0;
    }

    public static int a(String str, Throwable th) {
        if (f23593a) {
            return Log.w(str, th);
        }
        if (b == null) {
            return 0;
        }
        b.a(str, th);
        return 0;
    }

    public static int e(String str, String str2) {
        if (f23593a) {
            return Log.e(str, str2);
        }
        if (b == null) {
            return 0;
        }
        b.e(str, str2);
        return 0;
    }

    public static int e(String str, String str2, Throwable th) {
        if (f23593a) {
            return Log.e(str, str2, th);
        }
        if (b == null) {
            return 0;
        }
        b.e(str, str2, th);
        return 0;
    }

    private static String a(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object append : objArr) {
            sb.append(append);
            sb.append(", ");
        }
        return sb.toString();
    }

    public static int a(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return c(str, a(objArr));
    }

    public static int b(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return a(str, a(objArr));
    }

    public static int c(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return b(str, a(objArr));
    }

    public static int d(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return d(str, a(objArr));
    }

    public static int e(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return e(str, a(objArr));
    }

    public static void a(Throwable th) {
        if (b != null) {
            b.a(th);
        }
    }

    public static String b(Throwable th) {
        return Log.getStackTraceString(th);
    }
}
