package com.xiaomi.youpin.common.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.log.LogUtils;

public abstract class AsyncCallback<R, E extends YouPinError> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23222a = 1;
    private static final int b = 2;
    private Handler c;

    public abstract void a(E e);

    public abstract void a(R r);

    public AsyncCallback() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.c = new Dispatcher(this, myLooper);
        } else if (!(this instanceof SyncCallback)) {
            LogUtils.e("AsyncCallback", "async callback must have looper");
            this.c = new Dispatcher(this, Looper.getMainLooper());
        }
    }

    public void b(R r) {
        this.c.sendMessage(this.c.obtainMessage(1, r));
    }

    public void b(E e) {
        this.c.sendMessage(this.c.obtainMessage(2, e));
    }

    private static class Dispatcher<R, E extends YouPinError> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private AsyncCallback<R, E> f23223a;

        Dispatcher(AsyncCallback asyncCallback, Looper looper) {
            super(looper);
            this.f23223a = asyncCallback;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.f23223a.a(message.obj);
                    return;
                case 2:
                    this.f23223a.a((YouPinError) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
