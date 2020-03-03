package com.ximalaya.ting.android.sdkdownloader.task;

import android.os.Looper;
import java.util.Timer;
import java.util.TimerTask;

public final class TaskControllerImpl implements TaskController {

    /* renamed from: a  reason: collision with root package name */
    private static volatile TaskController f2371a;

    private TaskControllerImpl() {
    }

    public static TaskController a() {
        if (f2371a == null) {
            synchronized (TaskController.class) {
                if (f2371a == null) {
                    f2371a = new TaskControllerImpl();
                }
            }
        }
        return f2371a;
    }

    public <T> AbsTask<T> a(AbsTask<T> absTask) {
        TaskProxy taskProxy;
        if (absTask instanceof TaskProxy) {
            taskProxy = (TaskProxy) absTask;
        } else {
            taskProxy = new TaskProxy(absTask);
        }
        try {
            taskProxy.a();
        } catch (Throwable unused) {
        }
        return taskProxy;
    }

    public <T> AbsTask<T> b(AbsTask<T> absTask) {
        TaskProxy taskProxy;
        if (absTask instanceof TaskProxy) {
            taskProxy = (TaskProxy) absTask;
        } else {
            taskProxy = new TaskProxy(absTask);
        }
        try {
            taskProxy.m();
        } catch (Throwable unused) {
        }
        return taskProxy;
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                runnable.run();
            } else {
                TaskProxy.b.post(runnable);
            }
        }
    }

    public void b(Runnable runnable) {
        if (runnable != null) {
            TaskProxy.b.post(runnable);
        }
    }

    public void a(Runnable runnable, long j) {
        if (runnable != null) {
            TaskProxy.b.postDelayed(runnable, j);
        }
    }

    public void c(Runnable runnable) {
        if (!TaskProxy.f2373a.d()) {
            TaskProxy.f2373a.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    public void b(final Runnable runnable, long j) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                if (!TaskProxy.f2373a.d()) {
                    TaskProxy.f2373a.execute(runnable);
                } else {
                    new Thread(runnable).start();
                }
            }
        }, j);
    }

    public void d(Runnable runnable) {
        if (runnable != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                c(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public void e(Runnable runnable) {
        TaskProxy.b.removeCallbacks(runnable);
    }
}
