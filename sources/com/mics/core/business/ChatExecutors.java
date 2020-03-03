package com.mics.core.business;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatExecutors {
    private static ChatExecutors f;

    /* renamed from: a  reason: collision with root package name */
    private final Executor f7630a = Executors.newSingleThreadExecutor();
    private final Executor b = Executors.newSingleThreadExecutor();
    private final Executor c = Executors.newSingleThreadExecutor();
    private final MainThreadExecutor d = new MainThreadExecutor();
    private final Executor e = Executors.newSingleThreadExecutor();

    private ChatExecutors() {
    }

    private static ChatExecutors f() {
        if (f == null) {
            synchronized (ChatExecutors.class) {
                if (f == null) {
                    f = new ChatExecutors();
                }
            }
        }
        return f;
    }

    public static Executor a() {
        return f().f7630a;
    }

    public static Executor b() {
        return f().b;
    }

    public static Executor c() {
        return f().c;
    }

    public static MainThreadExecutor d() {
        return f().d;
    }

    public static Executor e() {
        return f().e;
    }

    public static class MainThreadExecutor implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private Handler f7631a = new Handler(Looper.getMainLooper());

        public void execute(@NonNull Runnable runnable) {
            this.f7631a.post(runnable);
        }

        public void a(@NonNull Runnable runnable, long j) {
            this.f7631a.postDelayed(runnable, j);
        }
    }
}
