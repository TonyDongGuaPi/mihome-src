package com.xiaomi.ai.utils;

import android.content.Context;
import com.xiaomi.ai.mibrain.Mibrainsdk;
import com.xiaomi.ai.utils.o;

public final class Log {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9946a = 1048576;
    public static final int b = 5;
    private static boolean c = false;
    private static volatile boolean d = false;
    private static Mibrainsdk.MibrainsdkLogHook e = new d();
    private static ExternalLogHook f = null;

    public interface ExternalLogHook {
        void a(int i, String str, String str2, Throwable th);
    }

    public static void a(Context context) {
        synchronized (Log.class) {
            if (!d) {
                o.a((o.a) new i(context.getApplicationContext(), context.getPackageName(), 1048576, 5, o.b()));
                d = true;
            }
        }
    }

    public static void a(ExternalLogHook externalLogHook) {
        f = externalLogHook;
    }

    public static void a(String str, String str2) {
        if (!a(6, str, str2, (Throwable) null)) {
            android.util.Log.e(str, str2);
            if (c) {
                o.a(6, str, str2);
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (!a(6, str, str2, th)) {
            android.util.Log.e(str, str2, th);
            if (c) {
                o.a(6, str, str2, th);
            }
        }
    }

    public static void a(boolean z) {
        c = z;
    }

    private static boolean a(int i, String str, String str2, Throwable th) {
        if (f == null) {
            return false;
        }
        f.a(i, str, str2, th);
        return true;
    }

    public static void b(String str, String str2) {
        if (!a(5, str, str2, (Throwable) null)) {
            android.util.Log.w(str, str2);
            if (c) {
                o.a(5, str, str2);
            }
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (!a(5, str, str2, th)) {
            android.util.Log.w(str, str2, th);
            if (c) {
                o.a(5, str, str2, th);
            }
        }
    }

    public static void c(String str, String str2) {
        if (!a(4, str, str2, (Throwable) null)) {
            android.util.Log.i(str, str2);
            if (c) {
                o.a(4, str, str2);
            }
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (!a(4, str, str2, th)) {
            android.util.Log.i(str, str2, th);
            if (c) {
                o.a(4, str, str2, th);
            }
        }
    }

    public static void d(String str, String str2) {
        if (!a(3, str, str2, (Throwable) null)) {
            android.util.Log.d(str, str2);
            if (c) {
                o.a(3, str, str2);
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (!a(3, str, str2, th)) {
            android.util.Log.d(str, str2, th);
            if (c) {
                o.a(3, str, str2, th);
            }
        }
    }

    public static void e(String str, String str2) {
        if (!a(2, str, str2, (Throwable) null)) {
            android.util.Log.v(str, str2);
            if (c) {
                o.a(2, str, str2);
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (!a(2, str, str2, th)) {
            android.util.Log.v(str, str2, th);
            if (c) {
                o.a(2, str, str2, th);
            }
        }
    }

    public static void f(String str, String str2) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_DEBUG) {
            d(str, str2);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_DEBUG) {
            d(str, str2, th);
        }
    }

    public static void g(String str, String str2) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_WARNING) {
            b(str, str2);
        }
    }

    public static void g(String str, String str2, Throwable th) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_WARNING) {
            b(str, str2, th);
        }
    }

    public static void h(String str, String str2) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_ERROR) {
            a(str, str2);
        }
    }

    public static void h(String str, String str2, Throwable th) {
        if (Mibrainsdk.MIBRAIN_LOG_CURRENT_LEVEL <= Mibrainsdk.MIBRAIN_DEBUG_LEVEL_ERROR) {
            a(str, str2, th);
        }
    }

    public static void setJNILogHook(boolean z) {
        Mibrainsdk.setMibrainsdkLogHook(z ? e : null);
    }
}
