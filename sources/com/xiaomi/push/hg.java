package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.bb;
import com.xiaomi.push.jq;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.bb;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class hg {

    /* renamed from: a  reason: collision with root package name */
    private String f12771a;
    private boolean b = false;
    private int c;
    private long d;
    private hf e;
    private bb f = bb.a();

    static class a {

        /* renamed from: a  reason: collision with root package name */
        static final hg f12772a = new hg();
    }

    private fk a(bb.a aVar) {
        if (aVar.f12645a != 0) {
            fk f2 = f();
            f2.a(fj.CHANNEL_STATS_COUNTER.a());
            f2.c(aVar.f12645a);
            f2.c(aVar.b);
            return f2;
        } else if (aVar.c instanceof fk) {
            return (fk) aVar.c;
        } else {
            return null;
        }
    }

    public static hg a() {
        return a.f12772a;
    }

    private fl b(int i) {
        ArrayList arrayList = new ArrayList();
        fl flVar = new fl(this.f12771a, arrayList);
        if (!az.e(this.e.f12770a)) {
            flVar.a(i.m(this.e.f12770a));
        }
        js jsVar = new js(i);
        jk a2 = new jq.a().a(jsVar);
        try {
            flVar.b(a2);
        } catch (je unused) {
        }
        LinkedList<bb.a> c2 = this.f.c();
        while (true) {
            try {
                if (c2.size() <= 0) {
                    break;
                }
                fk a3 = a(c2.getLast());
                if (a3 != null) {
                    a3.b(a2);
                }
                if (jsVar.I_() > i) {
                    break;
                }
                if (a3 != null) {
                    arrayList.add(a3);
                }
                c2.removeLast();
            } catch (je | NoSuchElementException unused2) {
            }
        }
        return flVar;
    }

    public static hf b() {
        hf hfVar;
        synchronized (a.f12772a) {
            hfVar = a.f12772a.e;
        }
        return hfVar;
    }

    private void g() {
        if (this.b && System.currentTimeMillis() - this.d > ((long) this.c)) {
            this.b = false;
            this.d = 0;
        }
    }

    public void a(int i) {
        if (i > 0) {
            int i2 = i * 1000;
            if (i2 > 604800000) {
                i2 = 604800000;
            }
            if (this.c != i2 || !this.b) {
                this.b = true;
                this.d = System.currentTimeMillis();
                this.c = i2;
                b.c("enable dot duration = " + i2 + " start = " + this.d);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(fk fkVar) {
        this.f.a(fkVar);
    }

    public synchronized void a(XMPushService xMPushService) {
        this.e = new hf(xMPushService);
        this.f12771a = "";
        com.xiaomi.push.service.bb.a().a((bb.a) new hh(this));
    }

    public boolean c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        g();
        return this.b && this.f.b() > 0;
    }

    /* access modifiers changed from: package-private */
    public synchronized fl e() {
        fl flVar;
        flVar = null;
        if (d()) {
            int i = 750;
            if (!az.e(this.e.f12770a)) {
                i = 375;
            }
            flVar = b(i);
        }
        return flVar;
    }

    /* access modifiers changed from: package-private */
    public synchronized fk f() {
        fk fkVar;
        fkVar = new fk();
        fkVar.a(az.k(this.e.f12770a));
        fkVar.f12736a = 0;
        fkVar.b = 1;
        fkVar.d((int) (System.currentTimeMillis() / 1000));
        return fkVar;
    }
}
