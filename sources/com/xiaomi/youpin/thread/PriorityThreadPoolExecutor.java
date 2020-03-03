package com.xiaomi.youpin.thread;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    public PriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, new PriorityBlockingQueue(), threadFactory);
    }

    public Future<?> submit(Runnable runnable) {
        PriorityFutureTask priorityFutureTask = new PriorityFutureTask((PriorityRunnable) runnable);
        execute(priorityFutureTask);
        return priorityFutureTask;
    }

    private static final class PriorityFutureTask extends FutureTask<PriorityRunnable> implements Comparable<PriorityFutureTask> {

        /* renamed from: a  reason: collision with root package name */
        private final PriorityRunnable f23759a;

        public PriorityFutureTask(PriorityRunnable priorityRunnable) {
            super(priorityRunnable, (Object) null);
            this.f23759a = priorityRunnable;
        }

        /* renamed from: a */
        public int compareTo(PriorityFutureTask priorityFutureTask) {
            return priorityFutureTask.f23759a.a().ordinal() - this.f23759a.a().ordinal();
        }
    }
}
