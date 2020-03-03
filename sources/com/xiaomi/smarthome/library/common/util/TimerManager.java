package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {

    /* renamed from: a  reason: collision with root package name */
    private final Timer f18742a = new Timer(false);
    /* access modifiers changed from: private */
    public final Handler b;

    public TimerManager(Handler handler) {
        this.b = handler;
    }

    public void a(Context context, final Runnable runnable, long j, long j2) {
        this.f18742a.schedule(new TimerTask() {
            public void run() {
                TimerManager.this.b.post(runnable);
            }
        }, j, j2);
    }

    public void a(TimerTask timerTask, long j, long j2) {
        this.f18742a.schedule(timerTask, j, j2);
    }

    public void a() {
        this.f18742a.cancel();
    }
}
