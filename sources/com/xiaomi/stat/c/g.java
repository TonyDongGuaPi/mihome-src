package com.xiaomi.stat.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.stat.a.c;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.b;
import com.xiaomi.stat.d.k;
import com.xiaomi.stat.d.l;
import com.xiaomi.stat.d.p;
import com.xiaomi.stat.d.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class g extends Handler {
    private static final String c = "UploadTimer";
    private static final int d = 15000;
    private static final int e = 5;
    private static final int f = 86400;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;

    /* renamed from: a  reason: collision with root package name */
    public AtomicBoolean f23040a;
    BroadcastReceiver b;
    private long j;
    private AtomicInteger k;
    private int l;
    private boolean m;
    private long n;
    private long o;
    private boolean p;

    private int a(int i2) {
        if (i2 < 0) {
            return 0;
        }
        if (i2 > 0 && i2 < 5) {
            return 5;
        }
        if (i2 > 86400) {
            return 86400;
        }
        return i2;
    }

    public g(Looper looper) {
        super(looper);
        this.j = 300000;
        this.k = new AtomicInteger();
        this.f23040a = new AtomicBoolean(true);
        this.l = 15000;
        this.m = true;
        this.p = true;
        this.b = new h(this);
        this.l = 60000;
        this.k.set(this.l);
        sendEmptyMessageDelayed(1, (long) this.l);
        a(ak.a());
        k.b(c, " UploadTimer: " + this.l);
    }

    private int e() {
        int a2 = a(b.m());
        if (a2 > 0) {
            return a2 * 1000;
        }
        int a3 = a(b.j());
        if (a3 > 0) {
            return a3 * 1000;
        }
        return 15000;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1) {
            f();
            this.n = (long) (this.m ? this.k.get() : this.l);
            sendEmptyMessageDelayed(1, this.n);
            k.b(c, " handleMessage: " + this.m);
        } else if (message.what == 2) {
            h();
        } else if (message.what == 3) {
            g();
        }
    }

    private void f() {
        i.a().c();
        d();
    }

    public long a() {
        return this.n;
    }

    public void a(boolean z) {
        if (!z && !this.p) {
            b();
        }
        this.m = z;
        this.p = false;
    }

    public void b() {
        this.l = e();
        this.k.set(this.l);
        removeMessages(1);
        sendEmptyMessageDelayed(1, (long) this.l);
        k.b(c, " resetBackgroundInterval: " + this.l);
    }

    private void a(Context context) {
        if (context != null) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(this.b, intentFilter);
            } catch (Exception e2) {
                k.b(c, "registerNetReceiver: " + e2);
            }
        }
    }

    private void g() {
        boolean z = r.b() - this.o > ((long) this.l);
        if (l.a() && this.m && z) {
            b();
            this.o = r.b();
        }
    }

    public void b(boolean z) {
        long c2 = c.a().c();
        if (c2 == 0) {
            this.f23040a.set(true);
        }
        k.b(c, " totalCount=" + c2 + " deleteData=" + z);
        if (((long) this.k.get()) < this.j && this.m) {
            if (c2 == 0 || !z) {
                this.k.addAndGet(this.l);
            }
        }
    }

    public void c() {
        if (this.m && this.f23040a.get()) {
            sendEmptyMessage(2);
        }
    }

    private void h() {
        long c2 = c.a().c();
        if (c2 >= 0) {
            if (c2 > 0) {
                b();
                this.f23040a.set(false);
            } else {
                this.f23040a.set(true);
            }
            k.b(c, " checkDatabase mIsDatabaseEmpty=" + this.f23040a.get());
        }
    }

    public void d() {
        Context a2 = ak.a();
        long n2 = p.n(a2);
        long m2 = p.m(a2);
        long totalRxBytes = TrafficStats.getTotalRxBytes() == -1 ? 0 : TrafficStats.getTotalRxBytes() / 1024;
        long b2 = r.b();
        p.e(a2, b2);
        p.d(a2, totalRxBytes);
        p.a(a2, ((float) ((totalRxBytes - m2) * 1000)) / ((float) (b2 - n2)));
    }
}
