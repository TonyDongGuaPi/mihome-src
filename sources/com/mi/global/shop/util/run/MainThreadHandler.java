package com.mi.global.shop.util.run;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainThreadHandler {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f7136a = (!MainThreadHandler.class.desiredAssertionStatus());
    private static final Object b = new Object();
    private static Handler c = null;

    private static Handler c() {
        Handler handler;
        synchronized (b) {
            if (c == null) {
                c = new Handler(Looper.getMainLooper());
            }
            handler = c;
        }
        return handler;
    }

    public static void a(Runnable runnable) {
        if (b()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, (Object) null);
        b(futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured while waiting for runnable", e);
        }
    }

    public static <T> T a(Callable<T> callable) {
        try {
            return b(callable);
        } catch (ExecutionException e) {
            throw new RuntimeException("Error occured waiting for callable", e);
        }
    }

    public static <T> T b(Callable<T> callable) throws ExecutionException {
        FutureTask futureTask = new FutureTask(callable);
        a(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted waiting for callable", e);
        }
    }

    public static <T> FutureTask<T> a(FutureTask<T> futureTask) {
        if (b()) {
            futureTask.run();
        } else {
            b(futureTask);
        }
        return futureTask;
    }

    public static <T> FutureTask<T> c(Callable<T> callable) {
        return a(new FutureTask(callable));
    }

    public static void b(Runnable runnable) {
        if (b()) {
            runnable.run();
        } else {
            c().post(runnable);
        }
    }

    public static <T> FutureTask<T> b(FutureTask<T> futureTask) {
        c().post(futureTask);
        return futureTask;
    }

    public static void c(Runnable runnable) {
        c().post(runnable);
    }

    public static void a(Runnable runnable, long j) {
        c().postDelayed(runnable, j);
    }

    public static void d(Runnable runnable) {
        c().removeCallbacks(runnable);
    }

    public static void a() {
        if (!f7136a && !b()) {
            throw new AssertionError();
        }
    }

    public static boolean b() {
        return c().getLooper() == Looper.myLooper();
    }
}
