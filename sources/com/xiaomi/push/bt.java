package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ai;
import java.lang.ref.WeakReference;

class bt extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ br f12658a;

    bt(br brVar) {
        this.f12658a = brVar;
    }

    public int a() {
        return 10054;
    }

    public void run() {
        b.c("exec== DbSizeControlJob");
        cd.a(this.f12658a.f).a((Runnable) new bw(this.f12658a.d(), new WeakReference(this.f12658a.f)));
        this.f12658a.b("check_time");
    }
}
