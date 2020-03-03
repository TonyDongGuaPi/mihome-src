package com.xiaomi.smarthome.frame;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.frame.Error;

public abstract class AsyncCallback<R, E extends Error> {
    private static final int MSG_CACHE = 0;
    private static final int MSG_FAILURE = 2;
    private static final int MSG_SUCCESS = 1;
    private Handler mDispatcher;

    public void onCache(R r) {
    }

    public abstract void onFailure(E e);

    public abstract void onSuccess(R r);

    public AsyncCallback() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mDispatcher = new Dispatcher(this, myLooper);
        } else if (!(this instanceof SyncCallback)) {
            Log.e("AsyncCallback", "async callback must have looper");
            this.mDispatcher = new Dispatcher(this, Looper.getMainLooper());
        }
    }

    public void sendCacheMessage(R r) {
        this.mDispatcher.sendMessage(this.mDispatcher.obtainMessage(0, r));
    }

    public void sendSuccessMessage(R r) {
        this.mDispatcher.sendMessage(this.mDispatcher.obtainMessage(1, r));
    }

    public void sendFailureMessage(E e) {
        this.mDispatcher.sendMessage(this.mDispatcher.obtainMessage(2, e));
    }

    private static class Dispatcher<R, E extends Error> extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private AsyncCallback<R, E> f15988a;

        Dispatcher(AsyncCallback asyncCallback, Looper looper) {
            super(looper);
            this.f15988a = asyncCallback;
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        this.f15988a.onCache(message.obj);
                        return;
                    case 1:
                        this.f15988a.onSuccess(message.obj);
                        return;
                    case 2:
                        this.f15988a.onFailure((Error) message.obj);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
