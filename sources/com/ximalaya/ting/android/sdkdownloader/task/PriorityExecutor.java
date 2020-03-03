package com.ximalaya.ting.android.sdkdownloader.task;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PriorityExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static final int f2368a = 3;
    private static final int b = 500;
    private static final int c = 1;
    private static final AtomicLong d = new AtomicLong(Long.MAX_VALUE);
    private static final ThreadFactory e = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f2369a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "xTID#" + this.f2369a.getAndIncrement());
        }
    };
    private static final Comparator<Runnable> f = new Comparator<Runnable>() {
        /* renamed from: a */
        public int compare(Runnable runnable, Runnable runnable2) {
            if (!(runnable instanceof PriorityRunnable) || !(runnable2 instanceof PriorityRunnable)) {
                return 0;
            }
            PriorityRunnable priorityRunnable = (PriorityRunnable) runnable;
            PriorityRunnable priorityRunnable2 = (PriorityRunnable) runnable2;
            int ordinal = priorityRunnable.b.ordinal() - priorityRunnable2.b.ordinal();
            return ordinal == 0 ? (int) (priorityRunnable.f2370a - priorityRunnable2.f2370a) : ordinal;
        }
    };
    private static final Comparator<Runnable> g = new Comparator<Runnable>() {
        /* renamed from: a */
        public int compare(Runnable runnable, Runnable runnable2) {
            if (!(runnable instanceof PriorityRunnable) || !(runnable2 instanceof PriorityRunnable)) {
                return 0;
            }
            PriorityRunnable priorityRunnable = (PriorityRunnable) runnable;
            PriorityRunnable priorityRunnable2 = (PriorityRunnable) runnable2;
            int ordinal = priorityRunnable.b.ordinal() - priorityRunnable2.b.ordinal();
            return ordinal == 0 ? (int) (priorityRunnable2.f2370a - priorityRunnable.f2370a) : ordinal;
        }
    };
    private final ThreadPoolExecutor h;

    public PriorityExecutor(boolean z) {
        this(3, z);
    }

    public PriorityExecutor(int i, boolean z) {
        this.h = new ThreadPoolExecutor(i, 500, 1, TimeUnit.SECONDS, new PriorityBlockingQueue(50, z ? f : g), e);
    }

    public int a() {
        return this.h.getCorePoolSize();
    }

    public void a(int i) {
        if (i > 0) {
            this.h.setCorePoolSize(i);
        }
    }

    public void b() {
        if (this.h != null) {
            this.h.getQueue().clear();
        }
    }

    public ThreadPoolExecutor c() {
        return this.h;
    }

    public boolean d() {
        return this.h.getActiveCount() >= this.h.getCorePoolSize();
    }

    public void execute(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).f2370a = d.getAndDecrement();
        }
        this.h.execute(runnable);
    }

    public boolean a(Runnable runnable) {
        return this.h.remove(runnable);
    }
}
