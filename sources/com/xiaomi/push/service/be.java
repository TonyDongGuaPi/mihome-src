package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.bd;

class be implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bd f12903a;

    be(bd bdVar) {
        this.f12903a = bdVar;
    }

    public void run() {
        try {
            for (bd.a run : bd.a(this.f12903a).values()) {
                run.run();
            }
        } catch (Exception e) {
            b.a("Sync job exception :" + e.getMessage());
        }
        boolean unused = this.f12903a.f323a = false;
    }
}
