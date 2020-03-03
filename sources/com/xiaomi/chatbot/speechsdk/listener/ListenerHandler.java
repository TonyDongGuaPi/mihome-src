package com.xiaomi.chatbot.speechsdk.listener;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public abstract class ListenerHandler {
    private Handler handler = null;
    protected Listener listener;

    /* access modifiers changed from: protected */
    public abstract void workMessage(Message message);

    /* access modifiers changed from: protected */
    public void AddMessage(Message message) {
        if (this.listener != null) {
            this.handler.sendMessage(message);
        }
    }

    public ListenerHandler(Listener listener2) {
        if (listener2 != null) {
            this.listener = listener2;
            if (this.listener.isRunning()) {
                this.handler = new WorkHandler(this);
            }
        }
    }

    private static class WorkHandler extends Handler {
        WeakReference<ListenerHandler> mWorker;

        public WorkHandler(ListenerHandler listenerHandler) {
            this.mWorker = new WeakReference<>(listenerHandler);
        }

        public void handleMessage(Message message) {
            ListenerHandler listenerHandler = (ListenerHandler) this.mWorker.get();
            if (listenerHandler != null) {
                try {
                    listenerHandler.workMessage(message);
                } catch (NullPointerException unused) {
                }
            }
        }
    }
}
