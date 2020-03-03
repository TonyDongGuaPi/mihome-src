package com.xiaomi.youpin.common.thread;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f23237a = new Handler(Looper.getMainLooper());

    public void execute(Runnable runnable) {
        this.f23237a.post(runnable);
    }
}
