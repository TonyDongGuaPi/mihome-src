package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;

final class af implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9287a;
    final /* synthetic */ Map b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    af(Context context, Map map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9287a = context;
        this.b = map;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            new Thread(new ap(this.f9287a, this.b, this.c), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9287a, th);
        }
    }
}
