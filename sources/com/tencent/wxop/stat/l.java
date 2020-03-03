package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;
import java.lang.Thread;

final class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9331a;

    l(Context context) {
        this.f9331a = context;
    }

    public final void run() {
        a.a(StatServiceImpl.t).h();
        k.a(this.f9331a, true);
        au.a(this.f9331a);
        i.b(this.f9331a);
        Thread.UncaughtExceptionHandler unused = StatServiceImpl.r = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new ao());
        if (StatConfig.a() == StatReportStrategy.APP_LAUNCH) {
            StatServiceImpl.a(this.f9331a, -1);
        }
        if (StatConfig.b()) {
            StatServiceImpl.q.j("Init MTA StatService success.");
        }
    }
}
