package com.xiaomi.mishopsdk.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class DispatchQueue extends Thread {
    public volatile Handler handler = null;
    private final Object handlerSyncObject = new Object();

    public DispatchQueue(String str) {
        setName(str);
        start();
    }

    private void sendMessage(Message message, int i) {
        if (this.handler == null) {
            try {
                synchronized (this.handlerSyncObject) {
                    this.handlerSyncObject.wait();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (this.handler == null) {
            return;
        }
        if (i <= 0) {
            this.handler.sendMessage(message);
        } else {
            this.handler.sendMessageDelayed(message, (long) i);
        }
    }

    public void cancelRunnable(Runnable runnable) {
        if (this.handler == null) {
            synchronized (this.handlerSyncObject) {
                if (this.handler == null) {
                    try {
                        this.handlerSyncObject.wait();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
        if (this.handler != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    public void postRunnable(Runnable runnable) {
        postRunnable(runnable, 0);
    }

    public void postRunnable(Runnable runnable, long j) {
        if (this.handler == null) {
            synchronized (this.handlerSyncObject) {
                if (this.handler == null) {
                    try {
                        this.handlerSyncObject.wait();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
        if (this.handler == null) {
            return;
        }
        if (j <= 0) {
            this.handler.post(runnable);
        } else {
            this.handler.postDelayed(runnable, j);
        }
    }

    public void cleanupQueue() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages((Object) null);
        }
    }

    public void run() {
        Looper.prepare();
        synchronized (this.handlerSyncObject) {
            this.handler = new Handler();
            this.handlerSyncObject.notify();
        }
        Looper.loop();
    }
}
