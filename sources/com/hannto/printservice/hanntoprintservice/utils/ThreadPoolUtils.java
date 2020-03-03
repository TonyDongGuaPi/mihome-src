package com.hannto.printservice.hanntoprintservice.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

    /* renamed from: a  reason: collision with root package name */
    private static ThreadPoolUtils f5789a;
    private static ThreadPoolExecutor b;

    private ThreadPoolUtils() {
        b();
    }

    public static ThreadPoolUtils a() {
        if (f5789a == null) {
            synchronized (ThreadPoolUtils.class) {
                if (f5789a == null) {
                    f5789a = new ThreadPoolUtils();
                }
            }
        }
        return f5789a;
    }

    private static void b() {
        b = new ThreadPoolExecutor(0, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public void a(Runnable runnable) {
        b.execute(runnable);
    }
}
