package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class GlideExecutor implements ExecutorService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4921a = "source";
    private static final String b = "disk-cache";
    private static final int c = 1;
    private static final String d = "GlideExecutor";
    private static final String e = "source-unlimited";
    private static final String f = "animation";
    private static final long g = TimeUnit.SECONDS.toMillis(10);
    private static final int h = 4;
    private static volatile int i;
    private final ExecutorService j;

    public interface UncaughtThrowableStrategy {

        /* renamed from: a  reason: collision with root package name */
        public static final UncaughtThrowableStrategy f4924a = new UncaughtThrowableStrategy() {
            public void a(Throwable th) {
            }
        };
        public static final UncaughtThrowableStrategy b = new UncaughtThrowableStrategy() {
            public void a(Throwable th) {
                if (th != null && Log.isLoggable(GlideExecutor.d, 6)) {
                    Log.e(GlideExecutor.d, "Request threw uncaught throwable", th);
                }
            }
        };
        public static final UncaughtThrowableStrategy c = new UncaughtThrowableStrategy() {
            public void a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        };
        public static final UncaughtThrowableStrategy d = b;

        void a(Throwable th);
    }

    public static GlideExecutor a() {
        return a(1, b, UncaughtThrowableStrategy.d);
    }

    public static GlideExecutor a(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return a(1, b, uncaughtThrowableStrategy);
    }

    public static GlideExecutor a(int i2, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i2, i2, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(str, uncaughtThrowableStrategy, true)));
    }

    public static GlideExecutor b() {
        return b(e(), "source", UncaughtThrowableStrategy.d);
    }

    public static GlideExecutor b(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return b(e(), "source", uncaughtThrowableStrategy);
    }

    public static GlideExecutor b(int i2, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i2, i2, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(str, uncaughtThrowableStrategy, false)));
    }

    public static GlideExecutor c() {
        return new GlideExecutor(new ThreadPoolExecutor(0, Integer.MAX_VALUE, g, TimeUnit.MILLISECONDS, new SynchronousQueue(), new DefaultThreadFactory(e, UncaughtThrowableStrategy.d, false)));
    }

    public static GlideExecutor d() {
        return a(e() >= 4 ? 2 : 1, UncaughtThrowableStrategy.d);
    }

    public static GlideExecutor a(int i2, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(0, i2, g, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(f, uncaughtThrowableStrategy, true)));
    }

    @VisibleForTesting
    GlideExecutor(ExecutorService executorService) {
        this.j = executorService;
    }

    public void execute(@NonNull Runnable runnable) {
        this.j.execute(runnable);
    }

    @NonNull
    public Future<?> submit(@NonNull Runnable runnable) {
        return this.j.submit(runnable);
    }

    @NonNull
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.j.invokeAll(collection);
    }

    @NonNull
    public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection, long j2, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.j.invokeAll(collection, j2, timeUnit);
    }

    @NonNull
    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.j.invokeAny(collection);
    }

    public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection, long j2, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.j.invokeAny(collection, j2, timeUnit);
    }

    @NonNull
    public <T> Future<T> submit(@NonNull Runnable runnable, T t) {
        return this.j.submit(runnable, t);
    }

    public <T> Future<T> submit(@NonNull Callable<T> callable) {
        return this.j.submit(callable);
    }

    public void shutdown() {
        this.j.shutdown();
    }

    @NonNull
    public List<Runnable> shutdownNow() {
        return this.j.shutdownNow();
    }

    public boolean isShutdown() {
        return this.j.isShutdown();
    }

    public boolean isTerminated() {
        return this.j.isTerminated();
    }

    public boolean awaitTermination(long j2, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.j.awaitTermination(j2, timeUnit);
    }

    public String toString() {
        return this.j.toString();
    }

    public static int e() {
        if (i == 0) {
            i = Math.min(4, RuntimeCompat.a());
        }
        return i;
    }

    private static final class DefaultThreadFactory implements ThreadFactory {
        private static final int c = 9;

        /* renamed from: a  reason: collision with root package name */
        final UncaughtThrowableStrategy f4922a;
        final boolean b;
        private final String d;
        private int e;

        DefaultThreadFactory(String str, UncaughtThrowableStrategy uncaughtThrowableStrategy, boolean z) {
            this.d = str;
            this.f4922a = uncaughtThrowableStrategy;
            this.b = z;
        }

        public synchronized Thread newThread(@NonNull Runnable runnable) {
            AnonymousClass1 r0;
            r0 = new Thread(runnable, "glide-" + this.d + "-thread-" + this.e) {
                public void run() {
                    Process.setThreadPriority(9);
                    if (DefaultThreadFactory.this.b) {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                    }
                    try {
                        super.run();
                    } catch (Throwable th) {
                        DefaultThreadFactory.this.f4922a.a(th);
                    }
                }
            };
            this.e = this.e + 1;
            return r0;
        }
    }
}
