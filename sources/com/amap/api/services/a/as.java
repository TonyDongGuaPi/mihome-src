package com.amap.api.services.a;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class as {
    private static volatile as c;

    /* renamed from: a  reason: collision with root package name */
    private BlockingQueue<Runnable> f4294a = new LinkedBlockingQueue();
    private ExecutorService b = null;

    public static as a() {
        if (c == null) {
            synchronized (as.class) {
                if (c == null) {
                    c = new as();
                }
            }
        }
        return c;
    }

    public static void b() {
        if (c != null) {
            synchronized (as.class) {
                if (c != null) {
                    c.b.shutdownNow();
                    c.b = null;
                    c = null;
                }
            }
        }
    }

    private as() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        this.b = new ThreadPoolExecutor(availableProcessors, availableProcessors * 2, (long) 1, TimeUnit.SECONDS, this.f4294a, new ThreadPoolExecutor.AbortPolicy());
    }

    public void a(Runnable runnable) {
        if (this.b != null) {
            this.b.execute(runnable);
        }
    }
}
