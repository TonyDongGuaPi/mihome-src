package com.mi.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f7422a = Executors.newFixedThreadPool(b);
    private static int b = 10;

    public static void a(Runnable runnable) {
        f7422a.execute(runnable);
    }
}
