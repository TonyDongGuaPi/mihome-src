package com.xiaomi.jr.annotation.anr;

import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ANRProcessor {

    /* renamed from: a  reason: collision with root package name */
    public static Executor f10293a = Executors.newCachedThreadPool();

    public static Object a(final Callable<Object> callable, long j) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            try {
                return callable.call();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            FutureTask futureTask = new FutureTask(new Callable<Object>() {
                public Object call() throws Exception {
                    return callable.call();
                }
            });
            f10293a.execute(futureTask);
            try {
                return futureTask.get(j, TimeUnit.MILLISECONDS);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
