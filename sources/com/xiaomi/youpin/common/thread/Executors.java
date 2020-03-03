package com.xiaomi.youpin.common.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executors {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23236a = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor b = new ThreadPoolExecutor(f23236a * 2, f23236a * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory(10, "ForBackgroundTasks"));
    private static final ThreadPoolExecutor c = new ThreadPoolExecutor(f23236a * 2, f23236a * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory(10, "ForLightWeightBackgroundTasks"));
    private static final Executor d = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            Executors.e.post(runnable);
        }
    };
    /* access modifiers changed from: private */
    public static final Handler e = new Handler(Looper.getMainLooper());

    public static ThreadPoolExecutor a() {
        return b;
    }

    public static void a(Runnable runnable) {
        b.execute(runnable);
    }

    public static void a(Runnable runnable, Priority priority) {
        b.execute(new PriorityRunnableProxy(runnable, priority));
    }

    public static Future<?> b(Runnable runnable) {
        return b.submit(runnable);
    }

    public static Future<?> b(Runnable runnable, Priority priority) {
        return b.submit(new PriorityRunnableProxy(runnable, priority));
    }

    public static <Params, Progress, Result> void a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        asyncTask.executeOnExecutor(b, paramsArr);
    }

    public static ThreadPoolExecutor b() {
        return c;
    }

    public static void c(Runnable runnable) {
        c.execute(runnable);
    }

    public static void c(Runnable runnable, Priority priority) {
        c.execute(new PriorityRunnableProxy(runnable, priority));
    }

    public static Future<?> d(Runnable runnable) {
        return c.submit(runnable);
    }

    public static Future<?> d(Runnable runnable, Priority priority) {
        return c.submit(new PriorityRunnableProxy(runnable, priority));
    }

    public static <Params, Progress, Result> void b(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        asyncTask.executeOnExecutor(c, paramsArr);
    }

    public static Executor c() {
        return d;
    }

    public static Handler d() {
        return e;
    }
}
