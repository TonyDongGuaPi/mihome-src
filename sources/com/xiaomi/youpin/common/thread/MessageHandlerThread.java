package com.xiaomi.youpin.common.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class MessageHandlerThread extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23238a = 7200000;
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
}
