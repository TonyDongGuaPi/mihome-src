package com.tencent.wxop.stat;

import android.content.Context;

final class al implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StatAccount f9293a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    al(StatAccount statAccount, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9293a = statAccount;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9293a == null || this.f9293a.b().trim().length() == 0) {
            StatServiceImpl.q.f("account is null or empty.");
            return;
        }
        StatConfig.d(this.b, this.f9293a.b());
        StatServiceImpl.c(this.b, this.f9293a, this.c);
    }
}
