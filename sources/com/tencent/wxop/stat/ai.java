package com.tencent.wxop.stat;

import android.content.Context;

final class ai implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9290a;
    final /* synthetic */ StatSpecifyReportedInfo b;

    ai(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9290a = context;
        this.b = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.c();
            StatServiceImpl.a(this.f9290a, true, this.b);
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9290a, th);
        }
    }
}
