package com.xiaomi.pluginhost;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class AppInitialApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12581a = "app_initial_finally_broadcast_action";
    private static AppInitialApi c;
    Future b = null;
    /* access modifiers changed from: private */
    public volatile boolean d = false;

    public interface IsInitialedCallback {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    protected AppInitialApi() {
    }

    protected static synchronized void a(AppInitialApi appInitialApi) {
        synchronized (AppInitialApi.class) {
            c = appInitialApi;
        }
    }

    public static synchronized AppInitialApi a() {
        AppInitialApi appInitialApi;
        synchronized (AppInitialApi.class) {
            appInitialApi = c;
        }
        return appInitialApi;
    }

    public synchronized void a(final Context context) {
        if (this.b == null) {
            this.b = Executors.newSingleThreadExecutor().submit(new Callable<Void>() {
                /* renamed from: a */
                public Void call() throws Exception {
                    AppInitialApi.this.b();
                    boolean unused = AppInitialApi.this.d = true;
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(AppInitialApi.f12581a));
                    return null;
                }
            });
        }
    }

    public synchronized void b(Context context) {
        if (this.b == null) {
            a(context);
        }
        try {
            this.b.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public synchronized boolean c() {
        return this.d;
    }

    public void a(final Context context, final IsInitialedCallback isInitialedCallback) {
        if (context == null) {
            if (isInitialedCallback != null) {
                isInitialedCallback.a();
            }
        } else if (!c()) {
            IntentFilter intentFilter = new IntentFilter(f12581a);
            LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                    if (isInitialedCallback != null) {
                        isInitialedCallback.a();
                    }
                }
            }, intentFilter);
        } else if (isInitialedCallback == null) {
        } else {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        if (isInitialedCallback != null) {
                            isInitialedCallback.a();
                        }
                    }
                });
            } else if (isInitialedCallback != null) {
                isInitialedCallback.a();
            }
        }
    }
}
