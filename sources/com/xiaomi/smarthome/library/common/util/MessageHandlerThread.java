package com.xiaomi.smarthome.library.common.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MessageHandlerThread extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1551a = 7200000;
    private static final int b = 1;
    /* access modifiers changed from: private */
    public Handler c;

    public MessageHandlerThread(String str) {
        super(str);
    }

    public MessageHandlerThread(String str, int i) {
        super(str, i);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c = new Handler(getLooper()) {
            public void handleMessage(Message message) {
                MessageHandlerThread.this.c.sendEmptyMessageDelayed(1, 7200000);
            }
        };
        this.c.sendEmptyMessageDelayed(1, 7200000);
    }

    public synchronized void start() {
        super.start();
        a();
    }

    public void run() {
        try {
            if (Looper.myLooper() != null) {
                Log.d("MessageHandlerThread", "looper is not when run start");
            } else {
                super.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(getName(), e);
        }
    }
}
