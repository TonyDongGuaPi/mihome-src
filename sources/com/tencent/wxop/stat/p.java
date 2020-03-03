package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;

final class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9335a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    p(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9335a = str;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.f9335a)) {
                StatServiceImpl.q.g("Error message in StatService.reportError() is empty.");
            } else {
                new aq(new d(this.b, StatServiceImpl.a(this.b, false, this.c), this.f9335a, 0, StatConfig.x(), (Thread) null, this.c)).a();
            }
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.b, th);
        }
    }
}
