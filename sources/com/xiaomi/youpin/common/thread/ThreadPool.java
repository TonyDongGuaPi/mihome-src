package com.xiaomi.youpin.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadPool {

    /* renamed from: a  reason: collision with root package name */
    public static final Executor f23245a = new ThreadPoolExecutor(c, 30, 60, TimeUnit.SECONDS, g, f);
    private static final int b = Runtime.getRuntime().availableProcessors();
    private static final int c = (b + 1);
    private static final int d = 30;
    private static final int e = 60;
    private static final ThreadFactory f = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f23246a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f23246a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> g = new LinkedBlockingQueue(128);

    public static void a(Runnable runnable) {
        f23245a.execute(runnable);
    }
}
