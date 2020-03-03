package com.ximalaya.ting.android.opensdk.httputil;

import android.os.Handler;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import java.util.concurrent.Executor;

public class ExecutorDelivery {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f1988a;

    public ExecutorDelivery(final Handler handler) {
        this.f1988a = new Executor() {
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public <T> void a(IDataCallBack<T> iDataCallBack, T t) {
        this.f1988a.execute(new ResponseDeliveryRunnable(0, t, iDataCallBack));
    }

    public <T> void a(int i, String str, IDataCallBack<T> iDataCallBack) {
        this.f1988a.execute(new ResponseDeliveryRunnable(1, i, str, null, iDataCallBack));
    }

    private class ResponseDeliveryRunnable<T> implements Runnable {
        private int b;
        private String c;
        private IDataCallBack<T> d;
        private T e;
        private int f;

        public ResponseDeliveryRunnable(int i, int i2, String str, T t, IDataCallBack<T> iDataCallBack) {
            this.f = i;
            this.b = i2;
            this.c = str;
            this.d = iDataCallBack;
            this.e = t;
        }

        public ResponseDeliveryRunnable(int i, T t, IDataCallBack<T> iDataCallBack) {
            this.f = i;
            this.d = iDataCallBack;
            this.e = t;
        }

        public void run() {
            if (this.f == 0) {
                this.d.a(this.e);
            } else if (this.f == 1) {
                this.d.a(this.b, this.c);
            }
        }
    }
}
