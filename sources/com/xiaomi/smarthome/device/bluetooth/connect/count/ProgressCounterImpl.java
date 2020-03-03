package com.xiaomi.smarthome.device.bluetooth.connect.count;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

public class ProgressCounterImpl implements ProgressCounter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15138a = 1;
    private static final int b = 10;
    private static final int c = 1;
    private ProgressNotifier d;
    private int e;
    private CounterTask f;
    private int g;
    private boolean h;
    private List<CounterTask> i = new ArrayList();
    private Handler j = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            ProgressCounterImpl.this.a(message);
        }
    };

    public ProgressCounterImpl(ProgressNotifier progressNotifier) {
        this.d = progressNotifier;
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (message.what == 1) {
            d();
        }
    }

    public void a(int i2, int i3, BoostCallback boostCallback) {
        g();
        if (b(i2, i3, boostCallback)) {
            this.h = false;
            if (!this.j.hasMessages(1)) {
                d();
            }
        }
    }

    public void a() {
        g();
        if (this.f != null) {
            if (this.f.f15137a == f()) {
                this.j.removeCallbacksAndMessages((Object) null);
                return;
            }
            this.i.remove(this.i.size() - 1);
        }
    }

    public void a(int i2) {
        g();
        this.h = true;
        this.f = null;
        this.g = 0;
        this.i.clear();
        b(i2);
        this.j.removeCallbacksAndMessages((Object) null);
    }

    public void b() {
        this.j.removeCallbacksAndMessages((Object) null);
        this.d = null;
        this.f = null;
        this.i.clear();
    }

    private void c() {
        if (this.f == null) {
            if (this.i.size() > 0) {
                this.f = this.i.remove(0);
            }
            if (this.f == null) {
                return;
            }
            if (this.f.f15137a > this.e) {
                this.g = (this.f.b * 1) / (this.f.f15137a - this.e);
                d();
                return;
            }
            throw new IllegalStateException("the scheduled task's target should be larger than current progress");
        }
    }

    private void b(int i2) {
        if (i2 <= 100) {
            this.e = i2;
            if (this.d != null) {
                this.d.a(this.e);
            }
        }
    }

    private void d() {
        int i2;
        if (!this.h) {
            if (this.f == null) {
                c();
                return;
            }
            boolean e2 = e();
            if (this.e == this.f.f15137a) {
                if (e2) {
                    if (this.f.c != null) {
                        this.f.c.a();
                    }
                    this.f = null;
                    c();
                }
            } else if (this.e <= this.f.f15137a) {
                int i3 = this.e + 1;
                this.e = i3;
                b(i3);
                if (e2) {
                    i2 = 10;
                } else {
                    i2 = this.g;
                }
                this.j.sendEmptyMessageDelayed(1, (long) i2);
            } else {
                throw new IllegalStateException("current progress should never be larger than current task's target");
            }
        }
    }

    private boolean e() {
        if (this.f != null) {
            return this.f.f15137a < f();
        }
        throw new IllegalStateException("current task should not be null");
    }

    private boolean b(int i2, int i3, BoostCallback boostCallback) {
        if (i2 <= f()) {
            return false;
        }
        this.i.add(new CounterTask(i2, i3, boostCallback));
        return true;
    }

    private int f() {
        int size = this.i.size();
        if (size > 0) {
            return this.i.get(size - 1).f15137a;
        }
        return 0;
    }

    private void g() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ProgressCounter should run in UI thread");
        }
    }
}
