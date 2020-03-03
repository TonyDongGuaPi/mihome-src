package com.lidroid.xutils.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6361a = 5;
    private static final int b = 256;
    private static final int c = 1;
    private static final ThreadFactory d = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f6362a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "PriorityExecutor #" + this.f6362a.getAndIncrement());
        }
    };
    private final BlockingQueue<Runnable> e;
    private final ThreadPoolExecutor f;

    public PriorityExecutor() {
        this(5);
    }

    public PriorityExecutor(int i) {
        this.e = new PriorityObjectBlockingQueue();
        this.f = new ThreadPoolExecutor(i, 256, 1, TimeUnit.SECONDS, this.e, d);
    }

    public int a() {
        return this.f.getCorePoolSize();
    }

    public void a(int i) {
        if (i > 0) {
            this.f.setCorePoolSize(i);
        }
    }

    public boolean b() {
        return this.f.getActiveCount() >= this.f.getCorePoolSize();
    }

    public void execute(Runnable runnable) {
        this.f.execute(runnable);
    }
}
