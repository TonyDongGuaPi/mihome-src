package com.xiaomi.smarthome.library.http.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.library.http.Error;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public abstract class AsyncHandler<R, E extends Error> {
    private static final int MSG_FAILURE = 2;
    private static final int MSG_PROGRESS = 1;
    private static final int MSG_SUCCESS = 0;
    private Handler mDispatchHandler;

    public abstract void onFailure(Error error, Exception exc, Response response);

    public void onProgress(long j, long j2) {
    }

    public abstract void onSuccess(R r, Response response);

    public abstract void processFailure(Call call, IOException iOException);

    public abstract void processResponse(Response response);

    public AsyncHandler() {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            Log.e("AsyncHandler", "Async Callback must have Looper");
            myLooper = Looper.getMainLooper();
        }
        this.mDispatchHandler = new DispatchHandler(this, myLooper);
    }

    public final void sendSuccessMessage(R r, Response response) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.f19113a = r;
        successMessage.b = response;
        this.mDispatchHandler.sendMessage(this.mDispatchHandler.obtainMessage(0, successMessage));
    }

    public final void sendProgressMessage(long j, long j2) {
        ProgressMessage progressMessage = new ProgressMessage();
        progressMessage.f19112a = j;
        progressMessage.b = j2;
        this.mDispatchHandler.sendMessage(this.mDispatchHandler.obtainMessage(1, progressMessage));
    }

    public final void sendFailureMessage(E e, Exception exc, Response response) {
        FailureMessage failureMessage = new FailureMessage();
        failureMessage.f19111a = e;
        failureMessage.b = exc;
        failureMessage.c = response;
        this.mDispatchHandler.sendMessage(this.mDispatchHandler.obtainMessage(2, failureMessage));
    }

    private static class SuccessMessage<R> {

        /* renamed from: a  reason: collision with root package name */
        R f19113a;
        Response b;

        private SuccessMessage() {
        }
    }

    private static class ProgressMessage {

        /* renamed from: a  reason: collision with root package name */
        long f19112a;
        long b;

        private ProgressMessage() {
        }
    }

    private static class FailureMessage<E extends Error> {

        /* renamed from: a  reason: collision with root package name */
        E f19111a;
        Exception b;
        Response c;

        private FailureMessage() {
        }
    }

    private static class DispatchHandler<R, E extends Error> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private AsyncHandler<R, E> f19110a;

        DispatchHandler(AsyncHandler asyncHandler, Looper looper) {
            super(looper);
            this.f19110a = asyncHandler;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    SuccessMessage successMessage = (SuccessMessage) message.obj;
                    this.f19110a.onSuccess(successMessage.f19113a, successMessage.b);
                    return;
                case 1:
                    ProgressMessage progressMessage = (ProgressMessage) message.obj;
                    this.f19110a.onProgress(progressMessage.f19112a, progressMessage.b);
                    return;
                case 2:
                    FailureMessage failureMessage = (FailureMessage) message.obj;
                    this.f19110a.onFailure(failureMessage.f19111a, failureMessage.b, failureMessage.c);
                    return;
                default:
                    return;
            }
        }
    }
}
