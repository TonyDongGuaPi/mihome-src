package com.xiaomi.youpin.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultExecutorSupplier implements ExecutorSupplier {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23754a = Runtime.getRuntime().availableProcessors();
    private static DefaultExecutorSupplier e;
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(f23754a * 2, f23754a * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory(10, "ForBackgroundTasks"));
    private final ThreadPoolExecutor c = new ThreadPoolExecutor(f23754a * 2, f23754a * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory(10, "ForLightWeightBackgroundTasks"));
    private final Executor d = new MainThreadExecutor();

    public static DefaultExecutorSupplier a() {
        if (e == null) {
            synchronized (DefaultExecutorSupplier.class) {
                e = new DefaultExecutorSupplier();
            }
        }
        return e;
    }

    private DefaultExecutorSupplier() {
    }

    public ThreadPoolExecutor b() {
        return this.b;
    }

    public ThreadPoolExecutor c() {
        return this.c;
    }

    public Executor d() {
        return this.d;
    }
}
