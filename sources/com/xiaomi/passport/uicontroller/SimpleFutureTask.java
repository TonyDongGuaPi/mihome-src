package com.xiaomi.passport.uicontroller;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class SimpleFutureTask<T> extends FutureTask<T> {
    /* access modifiers changed from: private */
    public Callback<T> mCallback;

    public static abstract class Callback<T> {
        public abstract void call(SimpleFutureTask<T> simpleFutureTask);
    }

    public SimpleFutureTask(Callable<T> callable, Callback<T> callback) {
        super(callable);
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void done() {
        if (!isCancelled() && this.mCallback != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    SimpleFutureTask.this.mCallback.call(SimpleFutureTask.this);
                }
            });
        }
        super.done();
    }
}
