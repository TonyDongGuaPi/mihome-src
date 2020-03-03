package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.e;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.p;

class aq {
    private static volatile long f;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public e f9297a;
    private StatReportStrategy b = null;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public Context d = null;
    private long e = System.currentTimeMillis();

    public aq(e eVar) {
        this.f9297a = eVar;
        this.b = StatConfig.a();
        this.c = eVar.f();
        this.d = eVar.e();
    }

    private void a(h hVar) {
        i.b(StatServiceImpl.t).a(this.f9297a, hVar);
    }

    private void b() {
        if (this.f9297a.d() != null && this.f9297a.d().a()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.j && a.a(StatServiceImpl.t).e()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.b()) {
            StatLogger g = StatServiceImpl.q;
            g.b((Object) "strategy=" + this.b.name());
        }
        switch (ag.f9288a[this.b.ordinal()]) {
            case 1:
                c();
                return;
            case 2:
                au.a(this.d).a(this.f9297a, (h) null, this.c, false);
                if (StatConfig.b()) {
                    StatLogger g2 = StatServiceImpl.q;
                    g2.b((Object) "PERIOD currTime=" + this.e + ",nextPeriodSendTs=" + StatServiceImpl.c + ",difftime=" + (StatServiceImpl.c - this.e));
                }
                if (StatServiceImpl.c == 0) {
                    StatServiceImpl.c = p.a(this.d, "last_period_ts", 0);
                    if (this.e > StatServiceImpl.c) {
                        StatServiceImpl.j(this.d);
                    }
                    long m = this.e + ((long) (StatConfig.m() * 60 * 1000));
                    if (StatServiceImpl.c > m) {
                        StatServiceImpl.c = m;
                    }
                    d.a(this.d).a();
                }
                if (StatConfig.b()) {
                    StatLogger g3 = StatServiceImpl.q;
                    g3.b((Object) "PERIOD currTime=" + this.e + ",nextPeriodSendTs=" + StatServiceImpl.c + ",difftime=" + (StatServiceImpl.c - this.e));
                }
                if (this.e > StatServiceImpl.c) {
                    StatServiceImpl.j(this.d);
                    return;
                }
                return;
            case 3:
            case 4:
                au.a(this.d).a(this.f9297a, (h) null, this.c, false);
                return;
            case 5:
                au.a(this.d).a(this.f9297a, (h) new ar(this), this.c, true);
                return;
            case 6:
                if (a.a(StatServiceImpl.t).c() == 1) {
                    c();
                    return;
                } else {
                    au.a(this.d).a(this.f9297a, (h) null, this.c, false);
                    return;
                }
            case 7:
                if (k.e(this.d)) {
                    a((h) new as(this));
                    return;
                }
                return;
            default:
                StatLogger g4 = StatServiceImpl.q;
                g4.g("Invalid stat strategy:" + StatConfig.a());
                return;
        }
    }

    private void c() {
        if (au.b().f9301a <= 0 || !StatConfig.m) {
            a((h) new at(this));
            return;
        }
        au.b().a(this.f9297a, (h) null, this.c, true);
        au.b().a(-1);
    }

    private boolean d() {
        if (StatConfig.h <= 0) {
            return false;
        }
        if (this.e > StatServiceImpl.h) {
            StatServiceImpl.g.clear();
            long unused = StatServiceImpl.h = this.e + StatConfig.i;
            if (StatConfig.b()) {
                StatLogger g = StatServiceImpl.q;
                g.b((Object) "clear methodsCalledLimitMap, nextLimitCallClearTime=" + StatServiceImpl.h);
            }
        }
        Integer valueOf = Integer.valueOf(this.f9297a.a().a());
        Integer num = (Integer) StatServiceImpl.g.get(valueOf);
        if (num != null) {
            StatServiceImpl.g.put(valueOf, Integer.valueOf(num.intValue() + 1));
            if (num.intValue() <= StatConfig.h) {
                return false;
            }
            if (StatConfig.b()) {
                StatLogger g2 = StatServiceImpl.q;
                g2.h("event " + this.f9297a.g() + " was discard, cause of called limit, current:" + num + ", limit:" + StatConfig.h + ", period:" + StatConfig.i + " ms");
            }
            return true;
        }
        StatServiceImpl.g.put(valueOf, 1);
        return false;
    }

    public void a() {
        if (!d()) {
            if (StatConfig.n > 0 && this.e >= f) {
                StatServiceImpl.i(this.d);
                f = this.e + StatConfig.o;
                if (StatConfig.b()) {
                    StatLogger g = StatServiceImpl.q;
                    g.b((Object) "nextFlushTime=" + f);
                }
            }
            if (a.a(this.d).f()) {
                if (StatConfig.b()) {
                    StatLogger g2 = StatServiceImpl.q;
                    g2.b((Object) "sendFailedCount=" + StatServiceImpl.f9268a);
                }
                if (!StatServiceImpl.a()) {
                    b();
                    return;
                }
                au.a(this.d).a(this.f9297a, (h) null, this.c, false);
                if (this.e - StatServiceImpl.b > 1800000) {
                    StatServiceImpl.g(this.d);
                    return;
                }
                return;
            }
            au.a(this.d).a(this.f9297a, (h) null, this.c, false);
        }
    }
}
