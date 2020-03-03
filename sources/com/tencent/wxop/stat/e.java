package com.tencent.wxop.stat;

import com.tencent.wxop.stat.common.k;
import java.util.TimerTask;

class e extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f9326a;

    e(d dVar) {
        this.f9326a = dVar;
    }

    public void run() {
        if (StatConfig.b()) {
            k.b().b((Object) "TimerTask run");
        }
        StatServiceImpl.j(this.f9326a.c);
        cancel();
        this.f9326a.a();
    }
}
