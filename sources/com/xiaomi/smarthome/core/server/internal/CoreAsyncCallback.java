package com.xiaomi.smarthome.core.server.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.core.server.internal.CoreError;

public abstract class CoreAsyncCallback<R, E extends CoreError> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f14058a = 0;
    private static final int b = 1;
    private Handler c;

    public abstract void a(E e);

    public abstract void a(R r);

    public CoreAsyncCallback() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.c = new Dispatcher(this, myLooper);
            return;
        }
        throw new RuntimeException("Async Callback must have Looper");
    }

    public void b(R r) {
        this.c.sendMessage(this.c.obtainMessage(0, r));
    }

    public void b(E e) {
        this.c.sendMessage(this.c.obtainMessage(1, e));
    }

    private static class Dispatcher<R, E extends CoreError> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private CoreAsyncCallback<R, E> f14059a;

        Dispatcher(CoreAsyncCallback coreAsyncCallback, Looper looper) {
            super(looper);
            this.f14059a = coreAsyncCallback;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.f14059a.a(message.obj);
                    return;
                case 1:
                    this.f14059a.a((CoreError) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
