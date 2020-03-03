package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.StatLogger;

final class w implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9341a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    w(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9341a = str;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            synchronized (StatServiceImpl.o) {
                if (StatServiceImpl.o.size() >= StatConfig.n()) {
                    StatLogger g = StatServiceImpl.q;
                    g.g("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.n()));
                    return;
                }
                String unused = StatServiceImpl.m = this.f9341a;
                if (StatServiceImpl.o.containsKey(StatServiceImpl.m)) {
                    StatLogger g2 = StatServiceImpl.q;
                    g2.h("Duplicate PageID : " + StatServiceImpl.m + ", onResume() repeated?");
                    return;
                }
                StatServiceImpl.o.put(StatServiceImpl.m, Long.valueOf(System.currentTimeMillis()));
                StatServiceImpl.a(this.b, true, this.c);
            }
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.b, th);
        }
    }
}
