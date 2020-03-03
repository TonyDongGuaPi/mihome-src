package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.b;
import com.tencent.wxop.stat.a.c;

final class u implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9339a;
    final /* synthetic */ StatSpecifyReportedInfo b;
    final /* synthetic */ c c;

    u(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, c cVar) {
        this.f9339a = context;
        this.b = statSpecifyReportedInfo;
        this.c = cVar;
    }

    public final void run() {
        try {
            b bVar = new b(this.f9339a, StatServiceImpl.a(this.f9339a, false, this.b), this.c.f9273a, this.b);
            bVar.b().c = this.c.c;
            new aq(bVar).a();
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9339a, th);
        }
    }
}
