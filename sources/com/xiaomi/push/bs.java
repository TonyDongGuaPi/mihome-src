package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ai;

class bs extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ br f12657a;

    bs(br brVar) {
        this.f12657a = brVar;
    }

    public int a() {
        return 10052;
    }

    public void run() {
        b.c("exec== mUploadJob");
        if (this.f12657a.j != null) {
            this.f12657a.j.a(this.f12657a.f);
            this.f12657a.b("upload_time");
        }
    }
}
