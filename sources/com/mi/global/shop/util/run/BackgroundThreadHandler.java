package com.mi.global.shop.util.run;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundThreadHandler {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f7135a = Executors.newFixedThreadPool(3);

    private BackgroundThreadHandler() {
    }

    public static void a(Runnable runnable) {
        if (runnable != null) {
            f7135a.execute(runnable);
        }
    }
}
