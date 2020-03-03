package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;

final class r implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Throwable f9337a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    r(Throwable th, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9337a = th;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f9337a == null) {
            StatServiceImpl.q.g("The Throwable error message of StatService.reportException() can not be null!");
        } else {
            new aq(new d(this.b, StatServiceImpl.a(this.b, false, this.c), 1, this.f9337a, this.c)).a();
        }
    }
}
