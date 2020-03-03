package com.alibaba.android.arouter.thread;

import android.support.annotation.NonNull;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.taobao.weex.el.parse.Operators;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadFactory implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicInteger f729a = new AtomicInteger(1);
    private final AtomicInteger b = new AtomicInteger(1);
    private final ThreadGroup c;
    private final String d;

    public DefaultThreadFactory() {
        ThreadGroup threadGroup;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            threadGroup = securityManager.getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
        }
        this.c = threadGroup;
        this.d = "ARouter task pool No." + f729a.getAndIncrement() + ", thread No.";
    }

    public Thread newThread(@NonNull Runnable runnable) {
        String str = this.d + this.b.getAndIncrement();
        ARouter.c.info("ARouter::", "Thread production, name is [" + str + Operators.ARRAY_END_STR);
        Thread thread = new Thread(this.c, runnable, str, 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                ILogger iLogger = ARouter.c;
                iLogger.info("ARouter::", "Running task appeared exception! Thread [" + thread.getName() + "], because [" + th.getMessage() + Operators.ARRAY_END_STR);
            }
        });
        return thread;
    }
}
