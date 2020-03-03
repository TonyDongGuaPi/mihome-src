package com.seek.biscuit;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class HandlerExecutor implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8809a = "HandlerExecutor";
    private final CompressHandler b;

    public HandlerExecutor() {
        HandlerThread handlerThread = new HandlerThread(f8809a);
        handlerThread.setPriority(10);
        handlerThread.start();
        this.b = new CompressHandler(handlerThread.getLooper());
    }

    public void a(Runnable runnable) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.obj = runnable;
        obtainMessage.sendToTarget();
    }

    private static class CompressHandler extends Handler {
        public CompressHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            ((Runnable) message.obj).run();
        }
    }
}
