package com.xiaomi.smarthome.library.common.util;

import android.os.Handler;
import android.os.Message;

public class CheckStatusHandlerTask {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18651a = 10;
    public static final int b = 11;
    boolean c = false;
    Handler d;
    long e;
    MyRunnable f;
    long g;
    Runnable h;
    boolean i = false;

    public interface MyRunnable {
        void a(Handler handler);
    }

    public CheckStatusHandlerTask(boolean z) {
        this.c = z;
    }

    public void a() {
        this.d = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10:
                        if (CheckStatusHandlerTask.this.i && CheckStatusHandlerTask.this.d == this) {
                            CheckStatusHandlerTask.this.f.a(this);
                            if (CheckStatusHandlerTask.this.c && CheckStatusHandlerTask.this.e > 0) {
                                sendEmptyMessageDelayed(10, CheckStatusHandlerTask.this.e);
                                return;
                            }
                            return;
                        }
                        return;
                    case 11:
                        if (CheckStatusHandlerTask.this.i && CheckStatusHandlerTask.this.d == this) {
                            CheckStatusHandlerTask.this.d.removeMessages(10);
                            CheckStatusHandlerTask.this.i = false;
                            CheckStatusHandlerTask.this.d = null;
                            if (CheckStatusHandlerTask.this.h != null) {
                                CheckStatusHandlerTask.this.h.run();
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.i = true;
        this.d.sendEmptyMessage(10);
        if (this.g > 0 && this.h != null) {
            this.d.sendEmptyMessageDelayed(11, this.g);
        }
    }

    public void b() {
        this.i = false;
        if (this.d != null) {
            this.d.removeMessages(10);
            this.d.removeMessages(11);
            this.d = null;
        }
    }

    public void c() {
        this.d.sendEmptyMessageDelayed(10, this.e);
    }

    public boolean a(Handler handler) {
        return this.i && this.d == handler;
    }

    public void a(MyRunnable myRunnable, long j) {
        this.f = myRunnable;
        this.e = j;
    }

    public void a(Runnable runnable, long j) {
        this.h = runnable;
        this.g = j;
    }
}
