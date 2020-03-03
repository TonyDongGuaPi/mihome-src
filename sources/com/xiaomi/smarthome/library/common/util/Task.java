package com.xiaomi.smarthome.library.common.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public abstract class Task extends AsyncTask<Void, Void, Void> {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f18738a;

    public abstract void a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        a();
        return null;
    }

    private static Handler b() {
        if (f18738a == null) {
            synchronized (Task.class) {
                if (f18738a == null) {
                    f18738a = new Handler(Looper.getMainLooper());
                }
            }
        }
        return f18738a;
    }

    public void a(final Executor executor, long j) {
        b().postDelayed(new Runnable() {
            public void run() {
                Task.this.executeOnExecutor(executor != null ? executor : AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }, j);
    }

    public void a(final Executor executor) {
        b().post(new Runnable() {
            public void run() {
                Task.this.executeOnExecutor(executor != null ? executor : AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        });
    }

    public static void a(Task task, Executor executor) {
        if (task != null) {
            task.a(executor);
        }
    }

    public static void a(Task task, Executor executor, long j) {
        if (task != null) {
            task.a(executor, j);
        }
    }

    public static void a(final FutureTask futureTask, final Executor executor, long j) {
        if (futureTask != null && executor != null) {
            b().postDelayed(new Runnable() {
                public void run() {
                    executor.execute(futureTask);
                }
            }, j);
        }
    }
}
