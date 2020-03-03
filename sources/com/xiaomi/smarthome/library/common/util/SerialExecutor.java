package com.xiaomi.smarthome.library.common.util;

import android.os.AsyncTask;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    final ArrayDeque<Runnable> f18703a = new ArrayDeque<>();
    Runnable b;

    public synchronized void execute(final Runnable runnable) {
        this.f18703a.offer(new Runnable() {
            public void run() {
                try {
                    runnable.run();
                } finally {
                    SerialExecutor.this.a();
                }
            }
        });
        if (this.b == null) {
            a();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void a() {
        Runnable poll = this.f18703a.poll();
        this.b = poll;
        if (poll != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(this.b);
        }
    }
}
