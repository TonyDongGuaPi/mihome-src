package com.xiaomi.smarthome.application;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.Util;

public abstract class CommonApplication extends MultiDexApplication {
    protected static AtomicInteger activeActivityCount = new AtomicInteger(0);
    protected static boolean sApplicationStart = false;
    protected static Handler sGlobalHandler;
    protected static Handler sGlobalWorkerHandler;
    protected static final SHApplicationWrapper<CommonApplication> sInstance = new SHApplicationWrapper<>();
    private static boolean sIsInSplitScreenMode = false;
    public static ThreadPoolExecutor sNetworkThreadPool = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false));
    public static volatile long sOnCreateTime;
    private static ExecutorService sStaticExecutor = Executors.newFixedThreadPool(5);
    public Runnable mResetRepeatedCrashRunnable;

    public abstract String getPushId();

    public abstract void onApplicationLifeCycleStart();

    public static Handler getGlobalHandler() {
        return sGlobalHandler;
    }

    public static Handler getGlobalWorkerHandler() {
        return sGlobalWorkerHandler;
    }

    public static ThreadPoolExecutor getNetworkThreadPool() {
        return sNetworkThreadPool;
    }

    public static CommonApplication getApplication() {
        return sInstance.a();
    }

    public static boolean isApplicationStart() {
        return sApplicationStart;
    }

    public static ExecutorService getThreadExecutor() {
        return sStaticExecutor;
    }

    public boolean isAppForeground() {
        return activeActivityCount.get() > 0;
    }

    public static long getOnCreateTime() {
        return sOnCreateTime;
    }

    public static boolean isCurrentHotStart() {
        return System.currentTimeMillis() - getOnCreateTime() > 1000;
    }

    public void onCreate() {
        super.onCreate();
        sInstance.a(this);
        if (sGlobalHandler == null) {
            sGlobalHandler = new Handler();
        }
    }

    public static Context getAppContext() {
        return sInstance.a();
    }

    protected static class SHApplicationWrapper<T> {

        /* renamed from: a  reason: collision with root package name */
        private T f1501a;

        protected SHApplicationWrapper() {
        }

        public void a(T t) {
            if (t != null) {
                this.f1501a = t;
            }
        }

        public final T a() {
            return this.f1501a;
        }
    }

    public static void addApplicationLifeCycle(ApplicationLifeCycle applicationLifeCycle) {
        if (!LifeCycleManager.a().b().contains(applicationLifeCycle)) {
            LifeCycleManager.a().b().add(applicationLifeCycle);
        }
    }

    public static boolean isInSplitScreenMode() {
        return sIsInSplitScreenMode;
    }

    public static void setInSplitScreenMode(boolean z) {
        sIsInSplitScreenMode = z;
    }
}
