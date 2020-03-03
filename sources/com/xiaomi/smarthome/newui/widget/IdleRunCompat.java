package com.xiaomi.smarthome.newui.widget;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.RequiresApi;

public class IdleRunCompat {
    private IdleRunCompat() {
    }

    public static void a(Looper looper, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 23) {
            b(looper, runnable);
        } else {
            new Handler(looper).post(runnable);
        }
    }

    @RequiresApi(api = 23)
    private static void b(Looper looper, Runnable runnable) {
        looper.getQueue().addIdleHandler(new MessageQueue.IdleHandler(runnable) {
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final boolean queueIdle() {
                return this.f$0.run();
            }
        });
    }
}
