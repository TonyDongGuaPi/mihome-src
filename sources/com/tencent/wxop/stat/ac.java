package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.b;
import com.tencent.wxop.stat.a.c;

final class ac implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9284a;
    final /* synthetic */ StatSpecifyReportedInfo b;
    final /* synthetic */ c c;
    final /* synthetic */ int d;

    ac(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, c cVar, int i) {
        this.f9284a = context;
        this.b = statSpecifyReportedInfo;
        this.c = cVar;
        this.d = i;
    }

    public final void run() {
        try {
            b bVar = new b(this.f9284a, StatServiceImpl.a(this.f9284a, false, this.b), this.c.f9273a, this.b);
            bVar.b().c = this.c.c;
            Long valueOf = Long.valueOf((long) this.d);
            bVar.a(Long.valueOf(valueOf.longValue() <= 0 ? 1 : valueOf.longValue()).longValue());
            new aq(bVar).a();
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9284a, th);
        }
    }
}
