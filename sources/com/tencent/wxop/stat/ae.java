package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;

final class ae implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9286a;

    ae(Context context) {
        this.f9286a = context;
    }

    public final void run() {
        try {
            new Thread(new ap(this.f9286a, (Map<String, Integer>) null, (StatSpecifyReportedInfo) null), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9286a, th);
        }
    }
}
