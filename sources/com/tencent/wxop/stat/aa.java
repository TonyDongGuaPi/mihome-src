package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.h;

final class aa implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9283a;
    final /* synthetic */ StatSpecifyReportedInfo b;
    final /* synthetic */ StatAppMonitor c;

    aa(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, StatAppMonitor statAppMonitor) {
        this.f9283a = context;
        this.b = statSpecifyReportedInfo;
        this.c = statAppMonitor;
    }

    public final void run() {
        try {
            new aq(new h(this.f9283a, StatServiceImpl.a(this.f9283a, false, this.b), this.c, this.b)).a();
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9283a, th);
        }
    }
}
