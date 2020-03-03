package com.xiaomi.jr.mipay.common.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static Executor f10951a = Executors.newCachedThreadPool();

    public static void a(Runnable runnable) {
        f10951a.execute(runnable);
    }
}
