package com.xiaomi.miot.support.monitor.leak;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import com.xiaomi.miot.support.monitor.leak.Retryable;

public final class AndroidWatchExecutor implements WatchExecutor {

    /* renamed from: a  reason: collision with root package name */
    static final String f11481a = "Miot-Leak-check";
    private final Handler c = new Handler(Looper.getMainLooper());
    private final Handler d;
    private final long e;
    private final long f;

    public AndroidWatchExecutor(long j) {
        HandlerThread handlerThread = new HandlerThread(f11481a);
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
        this.e = j;
        this.f = Long.MAX_VALUE / j;
    }

    public void a(@NonNull Retryable retryable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            b(retryable, 0);
        } else {
            a(retryable, 0);
        }
    }

    /* access modifiers changed from: private */
    public void a(final Retryable retryable, final int i) {
        this.c.post(new Runnable() {
            public void run() {
                AndroidWatchExecutor.this.b(retryable, i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final Retryable retryable, final int i) {
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            public boolean queueIdle() {
                AndroidWatchExecutor.this.c(retryable, i);
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(final Retryable retryable, final int i) {
        this.d.postDelayed(new Runnable() {
            public void run() {
                if (retryable.a() == Retryable.Result.RETRY) {
                    AndroidWatchExecutor.this.a(retryable, i + 1);
                }
            }
        }, this.e * ((long) Math.min(Math.pow(2.0d, (double) i), (double) this.f)));
    }
}
