package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.Schedulers;

public abstract class ObjectPool<T> implements SchedulerLifecycle {
    /* access modifiers changed from: private */
    public final int maxSize;
    /* access modifiers changed from: private */
    public final int minSize;
    /* access modifiers changed from: private */
    public Queue<T> pool;
    private final AtomicReference<Scheduler.Worker> schedulerWorker;
    private final long validationInterval;

    /* access modifiers changed from: protected */
    public abstract T createObject();

    public ObjectPool() {
        this(0, 0, 67);
    }

    private ObjectPool(int i, int i2, long j) {
        this.minSize = i;
        this.maxSize = i2;
        this.validationInterval = j;
        this.schedulerWorker = new AtomicReference<>();
        initialize(i);
        start();
    }

    public T borrowObject() {
        T poll = this.pool.poll();
        return poll == null ? createObject() : poll;
    }

    public void returnObject(T t) {
        if (t != null) {
            this.pool.offer(t);
        }
    }

    public void shutdown() {
        Scheduler.Worker andSet = this.schedulerWorker.getAndSet((Object) null);
        if (andSet != null) {
            andSet.unsubscribe();
        }
    }

    public void start() {
        Scheduler.Worker createWorker = Schedulers.computation().createWorker();
        if (this.schedulerWorker.compareAndSet((Object) null, createWorker)) {
            createWorker.schedulePeriodically(new Action0() {
                public void call() {
                    int size = ObjectPool.this.pool.size();
                    int i = 0;
                    if (size < ObjectPool.this.minSize) {
                        int access$200 = ObjectPool.this.maxSize - size;
                        while (i < access$200) {
                            ObjectPool.this.pool.add(ObjectPool.this.createObject());
                            i++;
                        }
                    } else if (size > ObjectPool.this.maxSize) {
                        int access$2002 = size - ObjectPool.this.maxSize;
                        while (i < access$2002) {
                            ObjectPool.this.pool.poll();
                            i++;
                        }
                    }
                }
            }, this.validationInterval, this.validationInterval, TimeUnit.SECONDS);
        } else {
            createWorker.unsubscribe();
        }
    }

    private void initialize(int i) {
        if (UnsafeAccess.isUnsafeAvailable()) {
            this.pool = new MpmcArrayQueue(Math.max(this.maxSize, 1024));
        } else {
            this.pool = new ConcurrentLinkedQueue();
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.pool.add(createObject());
        }
    }
}
