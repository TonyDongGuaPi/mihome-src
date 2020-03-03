package com.xiaomi.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundThreadHandler {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f10014a = Executors.newFixedThreadPool(3);

    private BackgroundThreadHandler() {
    }

    public static void a(Runnable runnable) {
        if (runnable != null) {
            f10014a.execute(runnable);
        }
    }
}
