package com.xiaomi.plugin;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.plugin.Error;

public abstract class AsyncCallback<R, E extends Error> {
    private static final int MSG_FAILURE = 2;
    private static final int MSG_SUCCESS = 1;
    private Handler mDispatcher;

    public abstract void onFailure(E e);

    public abstract void onSuccess(R r);

    public AsyncCallback() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mDispatcher = new Dispatcher(this, myLooper);
            return;
        }
        throw new RuntimeException("async callback must have looper");
    }

    public void sendSuccessMessage(R r) {
        this.mDispatcher.sendMessage(this.mDispatcher.obtainMessage(1, r));
    }

    public void sendFailureMessage(E e) {
        this.mDispatcher.sendMessage(this.mDispatcher.obtainMessage(2, e));
    }

    private static class Dispatcher<R, E extends Error> extends Handler {
        private AsyncCallback<R, E> mCallback;

        Dispatcher(AsyncCallback asyncCallback, Looper looper) {
            super(looper);
            this.mCallback = asyncCallback;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.mCallback.onSuccess(message.obj);
                    return;
                case 2:
                    this.mCallback.onFailure((Error) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
