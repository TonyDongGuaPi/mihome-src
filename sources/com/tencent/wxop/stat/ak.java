package com.tencent.wxop.stat;

import android.content.Context;

final class ak implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9292a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    ak(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9292a = str;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9292a == null || this.f9292a.trim().length() == 0) {
            StatServiceImpl.q.f("qq num is null or empty.");
            return;
        }
        StatConfig.f = this.f9292a;
        StatServiceImpl.c(this.b, new StatAccount(this.f9292a), this.c);
    }
}
