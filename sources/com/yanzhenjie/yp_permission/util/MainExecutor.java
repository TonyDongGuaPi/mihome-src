package com.yanzhenjie.yp_permission.util;

import android.os.Handler;
import android.os.Looper;

public class MainExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f2475a = new Handler(Looper.getMainLooper());

    public void a(Runnable runnable) {
        f2475a.post(runnable);
    }

    public void a(Runnable runnable, long j) {
        f2475a.postDelayed(runnable, j);
    }
}
