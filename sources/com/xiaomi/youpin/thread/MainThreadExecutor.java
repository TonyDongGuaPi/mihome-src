package com.xiaomi.youpin.thread;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f23755a = new Handler(Looper.getMainLooper());

    public void execute(Runnable runnable) {
        this.f23755a.post(runnable);
    }
}
