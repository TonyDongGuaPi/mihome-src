package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;
import com.tencent.wxop.stat.a.i;
import com.tencent.wxop.stat.common.StatLogger;

final class q implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9336a;
    final /* synthetic */ Throwable b;

    q(Context context, Throwable th) {
        this.f9336a = context;
        this.b = th;
    }

    public final void run() {
        try {
            if (StatConfig.c()) {
                new aq(new d(this.f9336a, StatServiceImpl.a(this.f9336a, false, (StatSpecifyReportedInfo) null), 99, this.b, i.f9279a)).a();
            }
        } catch (Throwable th) {
            StatLogger g = StatServiceImpl.q;
            g.h("reportSdkSelfException error: " + th);
        }
    }
}
