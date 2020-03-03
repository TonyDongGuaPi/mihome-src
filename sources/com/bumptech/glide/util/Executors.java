package com.bumptech.glide.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class Executors {

    /* renamed from: a  reason: collision with root package name */
    private static final Executor f5098a = new Executor() {

        /* renamed from: a  reason: collision with root package name */
        private final Handler f5099a = new Handler(Looper.getMainLooper());

        public void execute(@NonNull Runnable runnable) {
            this.f5099a.post(runnable);
        }
    };
    private static final Executor b = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    };

    private Executors() {
    }

    public static Executor a() {
        return f5098a;
    }

    public static Executor b() {
        return b;
    }

    @VisibleForTesting
    public static void a(ExecutorService executorService) {
        executorService.shutdownNow();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    throw new RuntimeException("Failed to shutdown");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
