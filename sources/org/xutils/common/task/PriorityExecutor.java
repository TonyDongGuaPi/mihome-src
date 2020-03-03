package org.xutils.common.task;

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
    private static final int f4216a = 5;
    private static final int b = 256;
    private static final int c = 1;
    private static final AtomicLong d = new AtomicLong(0);
    private static final ThreadFactory e = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f4217a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "xTID#" + this.f4217a.getAndIncrement());
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
            return ordinal == 0 ? (int) (priorityRunnable.f4218a - priorityRunnable2.f4218a) : ordinal;
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
            return ordinal == 0 ? (int) (priorityRunnable2.f4218a - priorityRunnable.f4218a) : ordinal;
        }
    };
    private final ThreadPoolExecutor h;

    public PriorityExecutor(boolean z) {
        this(5, z);
    }

    public PriorityExecutor(int i, boolean z) {
        this.h = new ThreadPoolExecutor(i, 256, 1, TimeUnit.SECONDS, new PriorityBlockingQueue(256, z ? f : g), e);
    }

    public int a() {
        return this.h.getCorePoolSize();
    }

    public void a(int i) {
        if (i > 0) {
            this.h.setCorePoolSize(i);
        }
    }

    public ThreadPoolExecutor b() {
        return this.h;
    }

    public boolean c() {
        return this.h.getActiveCount() >= this.h.getCorePoolSize();
    }

    public void execute(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).f4218a = d.getAndIncrement();
        }
        this.h.execute(runnable);
    }
}
