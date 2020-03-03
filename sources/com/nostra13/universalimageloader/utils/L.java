package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

public final class L {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8496a = "%1$s\n%2$s";
    private static volatile boolean b = false;
    private static volatile boolean c = true;

    private L() {
    }

    @Deprecated
    public static void a() {
        b(true);
    }

    @Deprecated
    public static void b() {
        b(false);
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void b(boolean z) {
        c = z;
    }

    public static void a(String str, Object... objArr) {
        if (b) {
            a(3, (Throwable) null, str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        a(4, (Throwable) null, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a(5, (Throwable) null, str, objArr);
    }

    public static void a(Throwable th) {
        a(6, th, (String) null, new Object[0]);
    }

    public static void d(String str, Object... objArr) {
        a(6, (Throwable) null, str, objArr);
    }

    public static void a(Throwable th, String str, Object... objArr) {
        a(6, th, str, objArr);
    }

    private static void a(int i, Throwable th, String str, Object... objArr) {
        if (c) {
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            if (th != null) {
                if (str == null) {
                    str = th.getMessage();
                }
                str = String.format(f8496a, new Object[]{str, Log.getStackTraceString(th)});
            }
            Log.println(i, ImageLoader.f8458a, str);
        }
    }
}
