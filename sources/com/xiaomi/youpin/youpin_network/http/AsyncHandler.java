package com.xiaomi.youpin.youpin_network.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.network.bean.NetError;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public abstract class AsyncHandler<R, E extends NetError> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23854a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private Handler d;

    public void a(long j, long j2) {
    }

    public abstract void a(NetError netError, Exception exc, Response response);

    public abstract void a(R r, Response response);

    public abstract void a(Call call, IOException iOException);

    public abstract void a(Response response);

    public AsyncHandler() {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            LogUtils.e("AsyncHandler", "Async Callback must have Looper");
            myLooper = Looper.getMainLooper();
        }
        this.d = new DispatchHandler(this, myLooper);
    }

    public final void b(R r, Response response) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.f23858a = r;
        successMessage.b = response;
        this.d.sendMessage(this.d.obtainMessage(0, successMessage));
    }

    public final void b(long j, long j2) {
        ProgressMessage progressMessage = new ProgressMessage();
        progressMessage.f23857a = j;
        progressMessage.b = j2;
        this.d.sendMessage(this.d.obtainMessage(1, progressMessage));
    }

    public final void b(E e, Exception exc, Response response) {
        FailureMessage failureMessage = new FailureMessage();
        failureMessage.f23856a = e;
        failureMessage.b = exc;
        failureMessage.c = response;
        this.d.sendMessage(this.d.obtainMessage(2, failureMessage));
    }

    private static class SuccessMessage<R> {

        /* renamed from: a  reason: collision with root package name */
        R f23858a;
        Response b;

        private SuccessMessage() {
        }
    }

    private static class ProgressMessage {

        /* renamed from: a  reason: collision with root package name */
        long f23857a;
        long b;

        private ProgressMessage() {
        }
    }

    private static class FailureMessage<E extends NetError> {

        /* renamed from: a  reason: collision with root package name */
        E f23856a;
        Exception b;
        Response c;

        private FailureMessage() {
        }
    }

    private static class DispatchHandler<R, E extends NetError> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private AsyncHandler<R, E> f23855a;

        DispatchHandler(AsyncHandler<R, E> asyncHandler, Looper looper) {
            super(looper);
            this.f23855a = asyncHandler;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    SuccessMessage successMessage = (SuccessMessage) message.obj;
                    this.f23855a.a(successMessage.f23858a, successMessage.b);
                    return;
                case 1:
                    ProgressMessage progressMessage = (ProgressMessage) message.obj;
                    this.f23855a.a(progressMessage.f23857a, progressMessage.b);
                    return;
                case 2:
                    FailureMessage failureMessage = (FailureMessage) message.obj;
                    this.f23855a.a(failureMessage.f23856a, failureMessage.b, failureMessage.c);
                    return;
                default:
                    return;
            }
        }
    }
}
