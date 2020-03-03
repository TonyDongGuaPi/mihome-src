package com.xiaomi.jr.common.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadUtils {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f1421a = new Handler(Looper.getMainLooper());
    private static Executor b = Executors.newCachedThreadPool();

    public static void a(Runnable runnable) {
        a(runnable, 0);
    }

    public static void a(Runnable runnable, long j) {
        f1421a.postDelayed(runnable, j);
    }

    public static void b(Runnable runnable) {
        b.execute(runnable);
    }
}
