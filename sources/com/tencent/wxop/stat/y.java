package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.c;
import com.tencent.wxop.stat.common.StatLogger;

final class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9343a;
    final /* synthetic */ c b;
    final /* synthetic */ Context c;

    y(String str, c cVar, Context context) {
        this.f9343a = str;
        this.b = cVar;
        this.c = context;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.f9343a)) {
                StatServiceImpl.q.g("The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
                return;
            }
            if (StatConfig.b()) {
                StatLogger g = StatServiceImpl.q;
                g.b((Object) "add begin key:" + this.b);
            }
            if (StatServiceImpl.e.containsKey(this.b)) {
                StatLogger g2 = StatServiceImpl.q;
                g2.e("Duplicate CustomEvent key: " + this.b.toString() + ", trackCustomBeginKVEvent() repeated?");
            } else if (StatServiceImpl.e.size() <= StatConfig.n()) {
                StatServiceImpl.e.put(this.b, Long.valueOf(System.currentTimeMillis()));
            } else {
                StatLogger g3 = StatServiceImpl.q;
                g3.g("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.n()));
            }
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.c, th);
        }
    }
}
