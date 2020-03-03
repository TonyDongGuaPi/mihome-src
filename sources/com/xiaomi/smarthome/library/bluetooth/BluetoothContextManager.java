package com.xiaomi.smarthome.library.bluetooth;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BluetoothContextManager {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f1549a;
    public static boolean b;
    private static Context c;

    public static void a(Context context, boolean z) {
        c = context.getApplicationContext();
        b = z;
    }

    public static Context n() {
        return c;
    }

    public static boolean o() {
        return b;
    }

    public String p() {
        return getClass().getSimpleName();
    }

    public static void c(Runnable runnable) {
        a(runnable, 0);
    }

    public static void a(Runnable runnable, long j) {
        if (f1549a == null) {
            f1549a = new Handler(Looper.getMainLooper());
        }
        f1549a.postDelayed(runnable, j);
    }

    public static String q() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }
}
