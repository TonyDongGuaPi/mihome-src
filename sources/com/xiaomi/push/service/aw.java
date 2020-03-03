package com.xiaomi.push.service;

import com.alipay.security.mobile.module.http.constant.a;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.hg;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.stat.d;

class aw {
    private static int d = 300000;

    /* renamed from: a  reason: collision with root package name */
    private int f12893a;

    /* renamed from: a  reason: collision with other field name */
    private long f303a;

    /* renamed from: a  reason: collision with other field name */
    private XMPushService f304a;
    private int b = 0;
    private int c = 0;

    public aw(XMPushService xMPushService) {
        this.f304a = xMPushService;
        this.f12893a = 500;
        this.f303a = 0;
    }

    private int a() {
        double d2;
        if (this.b > 8) {
            return a.f1173a;
        }
        double random = (Math.random() * 2.0d) + 1.0d;
        if (this.b > 4) {
            d2 = 60000.0d;
        } else if (this.b > 1) {
            d2 = 10000.0d;
        } else if (this.f303a == 0) {
            return 0;
        } else {
            if (System.currentTimeMillis() - this.f303a >= 310000) {
                this.f12893a = 1000;
                this.c = 0;
                return 0;
            } else if (this.f12893a >= d) {
                return this.f12893a;
            } else {
                int i = this.f12893a;
                this.c++;
                if (this.c >= 4) {
                    return d;
                }
                double d3 = (double) this.f12893a;
                Double.isNaN(d3);
                this.f12893a = (int) (d3 * 1.5d);
                return i;
            }
        }
        return (int) (random * d2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m312a() {
        this.f303a = System.currentTimeMillis();
        this.f304a.a(1);
        this.b = 0;
    }

    public void a(boolean z) {
        if (!this.f304a.a()) {
            b.c("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.f304a.a(1)) {
                this.b++;
            }
            this.f304a.a(1);
            XMPushService xMPushService = this.f304a;
            XMPushService xMPushService2 = this.f304a;
            xMPushService2.getClass();
            xMPushService.a((XMPushService.i) new XMPushService.d());
        } else if (!this.f304a.a(1)) {
            int a2 = a();
            this.b++;
            b.a("schedule reconnect in " + a2 + d.H);
            XMPushService xMPushService3 = this.f304a;
            XMPushService xMPushService4 = this.f304a;
            xMPushService4.getClass();
            xMPushService3.a((XMPushService.i) new XMPushService.d(), (long) a2);
            if (this.b == 2 && hg.a().c()) {
                ad.b();
            }
            if (this.b == 3) {
                ad.a();
            }
        }
    }
}
