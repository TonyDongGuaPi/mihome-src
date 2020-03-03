package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.b;
import com.tencent.wxop.stat.a.c;
import com.tencent.wxop.stat.common.StatLogger;

final class x implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9342a;
    final /* synthetic */ c b;
    final /* synthetic */ Context c;
    final /* synthetic */ StatSpecifyReportedInfo d;

    x(String str, c cVar, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9342a = str;
        this.b = cVar;
        this.c = context;
        this.d = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.f9342a)) {
                StatServiceImpl.q.g("The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
                return;
            }
            Long l = (Long) StatServiceImpl.e.remove(this.b);
            if (l != null) {
                b bVar = new b(this.c, StatServiceImpl.a(this.c, false, this.d), this.b.f9273a, this.d);
                bVar.b().b = this.b.b;
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                bVar.a(Long.valueOf(valueOf.longValue() == 0 ? 1 : valueOf.longValue()).longValue());
                new aq(bVar).a();
                return;
            }
            StatLogger g = StatServiceImpl.q;
            g.g("No start time found for custom event: " + this.b.toString() + ", lost trackCustomBeginEvent()?");
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.c, th);
        }
    }
}
