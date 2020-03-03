package com.xiaomi.jr.account;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class SimpleFutureTask<T> extends FutureTask<T> {

    /* renamed from: a  reason: collision with root package name */
    private Callback<T> f10276a;

    public static abstract class Callback<T> {
        public abstract void a(SimpleFutureTask<T> simpleFutureTask);
    }

    public SimpleFutureTask(Callable<T> callable, Callback<T> callback) {
        super(callable);
        this.f10276a = callback;
    }

    /* access modifiers changed from: protected */
    public void done() {
        if (!isCancelled() && this.f10276a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    SimpleFutureTask.this.a();
                }
            });
        }
        super.done();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.f10276a.a(this);
    }
}
