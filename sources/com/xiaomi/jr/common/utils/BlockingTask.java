package com.xiaomi.jr.common.utils;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class BlockingTask<V> extends FutureTask<V> {
    public BlockingTask(Handler handler, Callable<V> callable) {
        super(callable);
        handler.post(new Runnable(callable) {
            private final /* synthetic */ Callable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                BlockingTask.this.a(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Callable callable) {
        try {
            set(callable.call());
        } catch (Exception e) {
            setException(e);
        }
    }
}
