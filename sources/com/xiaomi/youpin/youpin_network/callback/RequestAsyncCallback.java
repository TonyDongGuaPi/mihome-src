package com.xiaomi.youpin.youpin_network.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.network.bean.NetError;

public abstract class RequestAsyncCallback<R, E extends NetError> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23850a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private Handler e = new Dispatcher(this, Looper.getMainLooper());

    public void a(long j, long j2) {
    }

    public abstract void a(E e2);

    public void a(R r) {
    }

    public abstract void a(R r, boolean z);

    public void b(long j, long j2) {
    }

    public void a(long j, long j2, boolean z) {
        ProgressMessage progressMessage = new ProgressMessage();
        progressMessage.f23852a = j;
        progressMessage.b = j2;
        this.e.sendMessage(this.e.obtainMessage(3, progressMessage));
    }

    public void b(long j, long j2, boolean z) {
        ProgressMessage progressMessage = new ProgressMessage();
        progressMessage.f23852a = j;
        progressMessage.b = j2;
        this.e.sendMessage(this.e.obtainMessage(3, progressMessage));
    }

    public void b(R r) {
        this.e.sendMessage(this.e.obtainMessage(0, r));
    }

    public void b(R r, boolean z) {
        SuccessObj successObj = new SuccessObj();
        successObj.f23853a = r;
        successObj.b = z;
        this.e.sendMessage(this.e.obtainMessage(1, successObj));
    }

    public void b(NetError netError) {
        this.e.sendMessage(this.e.obtainMessage(2, netError));
    }

    private static class Dispatcher<R, E extends NetError> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final String f23851a = "RequestAsyncCallback.Dispatcher";
        boolean b = false;
        boolean c = false;
        private RequestAsyncCallback<R, E> d;

        Dispatcher(RequestAsyncCallback<R, E> requestAsyncCallback, Looper looper) {
            super(looper);
            this.d = requestAsyncCallback;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.b) {
                        LogUtils.e(f23851a, "onCache after success");
                    }
                    if (this.c) {
                        LogUtils.e(f23851a, "onCache after failed");
                    }
                    this.d.a(message.obj);
                    return;
                case 1:
                    this.b = true;
                    SuccessObj successObj = (SuccessObj) message.obj;
                    this.d.a(successObj.f23853a, successObj.b);
                    return;
                case 2:
                    this.c = true;
                    this.d.a((NetError) message.obj);
                    return;
                case 3:
                    ProgressMessage progressMessage = (ProgressMessage) message.obj;
                    this.d.b(progressMessage.f23852a, progressMessage.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class ProgressMessage {

        /* renamed from: a  reason: collision with root package name */
        long f23852a;
        long b;

        private ProgressMessage() {
        }
    }

    static class SuccessObj<R> {

        /* renamed from: a  reason: collision with root package name */
        R f23853a;
        boolean b;

        SuccessObj() {
        }
    }
}
