package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.k;
import com.tencent.wxop.stat.common.StatLogger;

final class ah implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9289a;
    final /* synthetic */ String b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    ah(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9289a = context;
        this.b = str;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        Long l;
        try {
            StatServiceImpl.i(this.f9289a);
            synchronized (StatServiceImpl.o) {
                l = (Long) StatServiceImpl.o.remove(this.b);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = 1L;
                }
                Long l2 = valueOf;
                String k = StatServiceImpl.n;
                if (k != null && k.equals(this.b)) {
                    k = "-";
                }
                k kVar = new k(this.f9289a, k, this.b, StatServiceImpl.a(this.f9289a, false, this.c), l2, this.c);
                if (!this.b.equals(StatServiceImpl.m)) {
                    StatServiceImpl.q.e("Invalid invocation since previous onResume on diff page.");
                }
                new aq(kVar).a();
                String unused = StatServiceImpl.n = this.b;
                return;
            }
            StatLogger g = StatServiceImpl.q;
            g.h("Starttime for PageID:" + this.b + " not found, lost onResume()?");
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9289a, th);
        }
    }
}
