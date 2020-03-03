package com.alibaba.android.bindingx.core;

import android.util.Log;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import java.util.Map;

public final class LogProxy {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f745a = false;
    private static final String b = "debug";

    public static void a(Map<String, Object> map) {
        Object obj;
        if (map != null && (obj = map.get("debug")) != null) {
            boolean z = false;
            if (obj instanceof Boolean) {
                z = ((Boolean) obj).booleanValue();
            } else if (obj instanceof String) {
                z = "true".equals((String) obj);
            }
            f745a = z;
        }
    }

    public static void a(String str) {
        if (f745a) {
            Log.i(BindingXConstants.f752a, str);
        }
    }

    public static void a(String str, Throwable th) {
        if (f745a) {
            Log.i(BindingXConstants.f752a, str, th);
        }
    }

    public static void b(String str) {
        if (f745a) {
            Log.v(BindingXConstants.f752a, str);
        }
    }

    public static void b(String str, Throwable th) {
        if (f745a) {
            Log.v(BindingXConstants.f752a, str, th);
        }
    }

    public static void c(String str) {
        if (f745a) {
            Log.d(BindingXConstants.f752a, str);
        }
    }

    public static void c(String str, Throwable th) {
        if (f745a) {
            Log.d(BindingXConstants.f752a, str, th);
        }
    }

    public static void d(String str) {
        if (f745a) {
            Log.w(BindingXConstants.f752a, str);
        }
    }

    public static void d(String str, Throwable th) {
        if (f745a) {
            Log.w(BindingXConstants.f752a, str, th);
        }
    }

    public static void e(String str) {
        if (f745a) {
            Log.e(BindingXConstants.f752a, str);
        }
    }

    public static void e(String str, Throwable th) {
        if (f745a) {
            Log.e(BindingXConstants.f752a, str, th);
        }
    }
}
