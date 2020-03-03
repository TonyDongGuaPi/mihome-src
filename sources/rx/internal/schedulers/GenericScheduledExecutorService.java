package rx.internal.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.RxThreadFactory;

public final class GenericScheduledExecutorService implements SchedulerLifecycle {
    public static final GenericScheduledExecutorService INSTANCE = new GenericScheduledExecutorService();
    static final ScheduledExecutorService NONE = Executors.newScheduledThreadPool(0);
    private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory(THREAD_NAME_PREFIX);
    private static final String THREAD_NAME_PREFIX = "RxScheduledExecutorPool-";
    private final AtomicReference<ScheduledExecutorService> executor = new AtomicReference<>(NONE);

    static {
        NONE.shutdownNow();
    }

    private GenericScheduledExecutorService() {
        start();
    }

    public void start() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors > 8) {
            availableProcessors = 8;
        }
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(availableProcessors, THREAD_FACTORY);
        if (!this.executor.compareAndSet(NONE, newScheduledThreadPool)) {
            newScheduledThreadPool.shutdownNow();
        } else if (!NewThreadWorker.tryEnableCancelPolicy(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            NewThreadWorker.registerExecutor((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
    }

    public void shutdown() {
        ScheduledExecutorService scheduledExecutorService;
        do {
            scheduledExecutorService = this.executor.get();
            if (scheduledExecutorService == NONE) {
                return;
            }
        } while (!this.executor.compareAndSet(scheduledExecutorService, NONE));
        NewThreadWorker.deregisterExecutor(scheduledExecutorService);
        scheduledExecutorService.shutdownNow();
    }

    public static ScheduledExecutorService getInstance() {
        return INSTANCE.executor.get();
    }
}
