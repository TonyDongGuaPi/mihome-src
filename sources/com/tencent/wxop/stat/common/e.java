package com.tencent.wxop.stat.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class e {

    /* renamed from: a  reason: collision with root package name */
    ExecutorService f9318a;

    public e() {
        this.f9318a = null;
        this.f9318a = Executors.newSingleThreadExecutor();
    }

    public void a(Runnable runnable) {
        this.f9318a.execute(runnable);
    }
}
