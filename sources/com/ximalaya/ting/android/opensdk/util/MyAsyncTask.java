package com.ximalaya.ting.android.opensdk.util;

import android.os.AsyncTask;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.cybergarage.http.HTTP;

public abstract class MyAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    /* renamed from: a  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f2261a = new LinkedBlockingQueue();
    private static final ThreadFactory b = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f2262a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "MyAsyncTask #" + this.f2262a.getAndIncrement());
        }
    };
    public static String c = "MyAsyncTask";
    public static final ThreadPoolExecutor d;
    private static final int e = Runtime.getRuntime().availableProcessors();
    private static final int f = Math.max(2, Math.min(e - 1, 4));
    private static final int g = ((e * 2) + 1);
    private static final int h = 30;

    private static void b() {
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(f, g, 30, TimeUnit.SECONDS, f2261a, b);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        d = threadPoolExecutor;
    }

    public final MyAsyncTask<Params, Progress, Result> a(Params... paramsArr) {
        Logger.c("MyAsyncTask", "MyAsyncTask --- myexec " + this);
        b();
        executeOnExecutor(d, paramsArr);
        return this;
    }

    public static void a(Runnable runnable) {
        b();
        d.execute(runnable);
    }

    public static void a(Runnable runnable, boolean z) {
        b();
        if (d.getQueue().size() < 10 || !z) {
            a(runnable);
        }
    }

    public static String a() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Thread next : allStackTraces.keySet()) {
            if (next != null) {
                sb.append("thread id: " + next.getId() + ", name: " + next.getName() + ", state:" + next.getState() + ", priority:" + next.getPriority());
                sb.append("\n");
                StackTraceElement[] stackTrace = next.getStackTrace();
                if (stackTrace != null) {
                    for (StackTraceElement append : stackTrace) {
                        sb.append(HTTP.TAB);
                        sb.append(append);
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}
