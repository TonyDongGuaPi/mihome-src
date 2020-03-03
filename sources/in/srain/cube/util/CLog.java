package in.srain.cube.util;

import android.util.Log;

public class CLog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2620a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static int g;

    public static void a(int i) {
        g = i;
    }

    public static void a(String str, String str2) {
        if (g <= 0) {
            Log.v(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (g <= 0) {
            Log.v(str, str2, th);
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (g <= 0) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (g <= 1) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (g <= 1) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (g <= 1) {
            Log.d(str, str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (g <= 2) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (g <= 2) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (g <= 2) {
            Log.i(str, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (g <= 3) {
            Log.w(str, str2);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (g <= 3) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (g <= 3) {
            Log.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (g <= 4) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (g <= 4) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (g <= 4) {
            Log.e(str, str2, th);
        }
    }

    public static void f(String str, String str2) {
        if (g <= 5) {
            Log.wtf(str, str2);
        }
    }

    public static void f(String str, String str2, Object... objArr) {
        if (g <= 5) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.wtf(str, str2);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        if (g <= 5) {
            Log.wtf(str, str2, th);
        }
    }
}
