package rx.schedulers;

import java.util.concurrent.Executor;
import rx.Scheduler;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.RxRingBuffer;
import rx.plugins.RxJavaPlugins;

public final class Schedulers {
    private static final Schedulers INSTANCE = new Schedulers();
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;

    private Schedulers() {
        Scheduler computationScheduler2 = RxJavaPlugins.getInstance().getSchedulersHook().getComputationScheduler();
        if (computationScheduler2 != null) {
            this.computationScheduler = computationScheduler2;
        } else {
            this.computationScheduler = new EventLoopsScheduler();
        }
        Scheduler iOScheduler = RxJavaPlugins.getInstance().getSchedulersHook().getIOScheduler();
        if (iOScheduler != null) {
            this.ioScheduler = iOScheduler;
        } else {
            this.ioScheduler = new CachedThreadScheduler();
        }
        Scheduler newThreadScheduler2 = RxJavaPlugins.getInstance().getSchedulersHook().getNewThreadScheduler();
        if (newThreadScheduler2 != null) {
            this.newThreadScheduler = newThreadScheduler2;
        } else {
            this.newThreadScheduler = NewThreadScheduler.instance();
        }
    }

    public static Scheduler immediate() {
        return ImmediateScheduler.instance();
    }

    public static Scheduler trampoline() {
        return TrampolineScheduler.instance();
    }

    public static Scheduler newThread() {
        return INSTANCE.newThreadScheduler;
    }

    public static Scheduler computation() {
        return INSTANCE.computationScheduler;
    }

    public static Scheduler io() {
        return INSTANCE.ioScheduler;
    }

    public static TestScheduler test() {
        return new TestScheduler();
    }

    public static Scheduler from(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    static void start() {
        Schedulers schedulers = INSTANCE;
        synchronized (schedulers) {
            if (schedulers.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.computationScheduler).start();
            }
            if (schedulers.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.ioScheduler).start();
            }
            if (schedulers.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.newThreadScheduler).start();
            }
            GenericScheduledExecutorService.INSTANCE.start();
            RxRingBuffer.SPSC_POOL.start();
            RxRingBuffer.SPMC_POOL.start();
        }
    }

    public static void shutdown() {
        Schedulers schedulers = INSTANCE;
        synchronized (schedulers) {
            if (schedulers.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.computationScheduler).shutdown();
            }
            if (schedulers.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.ioScheduler).shutdown();
            }
            if (schedulers.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.newThreadScheduler).shutdown();
            }
            GenericScheduledExecutorService.INSTANCE.shutdown();
            RxRingBuffer.SPSC_POOL.shutdown();
            RxRingBuffer.SPMC_POOL.shutdown();
        }
    }
}
