package com.alibaba.android.bindingx.core;

import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

public class WeakRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Runnable> f748a;

    public WeakRunnable(@NonNull Runnable runnable) {
        this.f748a = new WeakReference<>(runnable);
    }

    public void run() {
        Runnable runnable = (Runnable) this.f748a.get();
        if (runnable != null) {
            runnable.run();
        }
    }
}
