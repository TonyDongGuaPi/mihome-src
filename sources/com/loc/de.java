package com.loc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class de {

    /* renamed from: a  reason: collision with root package name */
    private static final TimeUnit f6557a = TimeUnit.SECONDS;
    private static final ThreadFactory b = new df();
    private static final ExecutorService c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, f6557a, new SynchronousQueue(), b);

    static ExecutorService a() {
        return c;
    }
}
