package com.xiaomi.miot.support.monitor.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncThreadTask {
    private static final ThreadFactory e = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f11491a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AsyncThreadTask #" + this.f11491a.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    };
    private static AsyncThreadTask f;

    /* renamed from: a  reason: collision with root package name */
    private final int f1488a = Runtime.getRuntime().availableProcessors();
    private final int b = (this.f1488a + 3);
    private final int c = 3;
    /* access modifiers changed from: private */
    public ExecutorService d = new ThreadPoolExecutor(this.b, this.b, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(10000), e, new ThreadPoolExecutor.DiscardOldestPolicy());
    private InternalHandler g;

    public static AsyncThreadTask a() {
        if (f == null) {
            synchronized (AsyncThreadTask.class) {
                if (f == null) {
                    f = new AsyncThreadTask();
                }
            }
        }
        return f;
    }

    private AsyncThreadTask() {
    }

    private void b(Runnable runnable) {
        this.d.execute(runnable);
    }

    private void c(final Runnable runnable, long j) {
        b().postDelayed(new Runnable() {
            public void run() {
                AsyncThreadTask.this.d.execute(runnable);
            }
        }, j);
    }

    public static void a(Runnable runnable) {
        a().b(runnable);
    }

    public static void a(Runnable runnable, long j) {
        a().c(runnable, j);
    }

    public static void b(Runnable runnable, long j) {
        a().d(runnable, j);
    }

    private void d(Runnable runnable, long j) {
        b().postDelayed(runnable, j);
    }

    private Handler b() {
        InternalHandler internalHandler;
        synchronized (this) {
            if (this.g == null) {
                this.g = new InternalHandler();
            }
            internalHandler = this.g;
        }
        return internalHandler;
    }

    private static class InternalHandler extends Handler {
        public void handleMessage(Message message) {
        }

        public InternalHandler() {
            super(Looper.getMainLooper());
        }
    }
}
