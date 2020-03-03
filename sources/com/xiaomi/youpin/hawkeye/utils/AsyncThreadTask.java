package com.xiaomi.youpin.hawkeye.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncThreadTask {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23386a = "AsyncThreadTask";
    private static final ThreadFactory g = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f23387a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AsyncThreadTask #" + this.f23387a.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    };
    private static AsyncThreadTask h;
    private final int b = Runtime.getRuntime().availableProcessors();
    private final int c = (this.b + 3);
    private final int d = 3;
    /* access modifiers changed from: private */
    public ExecutorService e = new ThreadPoolExecutor(this.c, this.c, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(10000), g, new ThreadPoolExecutor.DiscardOldestPolicy());
    private ScheduledExecutorService f;
    private Handler i;

    public static AsyncThreadTask a() {
        if (h == null) {
            synchronized (AsyncThreadTask.class) {
                if (h == null) {
                    h = new AsyncThreadTask();
                }
            }
        }
        return h;
    }

    private AsyncThreadTask() {
    }

    private void b(Runnable runnable) {
        this.e.execute(runnable);
    }

    private void e(final Runnable runnable, long j) {
        b().postDelayed(new Runnable() {
            public void run() {
                AsyncThreadTask.this.e.execute(runnable);
            }
        }, j);
    }

    public static void a(Runnable runnable) {
        a().b(runnable);
    }

    public static ScheduledFuture<?> a(Runnable runnable, long j) {
        return a().b(runnable, j);
    }

    public ScheduledFuture<?> b(Runnable runnable, long j) {
        if (this.f == null) {
            this.f = Executors.newSingleThreadScheduledExecutor();
        }
        return this.f.scheduleWithFixedDelay(runnable, 0, j, TimeUnit.MILLISECONDS);
    }

    public static void c(Runnable runnable, long j) {
        a().e(runnable, j);
    }

    public static void d(Runnable runnable, long j) {
        a().f(runnable, j);
    }

    private void f(Runnable runnable, long j) {
        b().postDelayed(runnable, j);
    }

    private Handler b() {
        Handler handler;
        synchronized (this) {
            if (this.i == null) {
                this.i = new Handler(Looper.getMainLooper());
            }
            handler = this.i;
        }
        return handler;
    }
}
