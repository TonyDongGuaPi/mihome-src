package com.tencent.wxop.stat;

import android.content.Context;

final class an implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9295a;
    final /* synthetic */ StatSpecifyReportedInfo b;

    an(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9295a = context;
        this.b = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.a(this.f9295a, false, this.b);
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
        }
    }
}
