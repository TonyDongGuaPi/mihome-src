package com.xiaomi.stat;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.stat.d.r;

public class ah {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23018a = 1;
    private static final int b = 10000;
    private static final int c = 3;
    private Handler d;
    /* access modifiers changed from: private */
    public Runnable e;
    private HandlerThread f;
    /* access modifiers changed from: private */
    public int g = 3;
    /* access modifiers changed from: private */
    public int h = 10000;
    private int i = 0;
    /* access modifiers changed from: private */
    public boolean j = false;

    public ah(Runnable runnable) {
        this.e = runnable;
    }

    private void d() {
        a aVar = new a();
        this.f = new HandlerThread("".concat(JSMethod.NOT_SET).concat(String.valueOf(r.b())));
        this.f.start();
        this.d = new Handler(this.f.getLooper(), aVar);
        aVar.a(this.d);
    }

    public void a() {
        if (this.d == null || !this.d.hasMessages(1)) {
            d();
            Message obtainMessage = this.d.obtainMessage(1);
            obtainMessage.obj = 0;
            this.j = true;
            this.d.sendMessageDelayed(obtainMessage, (long) this.i);
        }
    }

    public void b() {
        this.d.removeMessages(1);
        this.d.getLooper().quit();
        this.j = false;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public boolean c() {
        return this.j;
    }

    class a implements Handler.Callback {
        private Handler b;

        private a() {
            this.b = null;
        }

        /* access modifiers changed from: private */
        public void a(Handler handler) {
            this.b = handler;
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                int intValue = ((Integer) message.obj).intValue();
                if (intValue < ah.this.g) {
                    ah.this.e.run();
                    if (ah.this.j) {
                        Message obtainMessage = this.b.obtainMessage(1);
                        obtainMessage.obj = Integer.valueOf(intValue + 1);
                        this.b.sendMessageDelayed(obtainMessage, (long) ah.this.h);
                    }
                } else {
                    ah.this.b();
                }
            }
            return true;
        }
    }
}
