package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.em;
import com.xiaomi.push.fv;
import com.xiaomi.push.fy;
import java.util.Map;

class bo extends fv {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12913a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bo(XMPushService xMPushService, Map map, int i, String str, fy fyVar) {
        super(map, i, str, fyVar);
        this.f12913a = xMPushService;
    }

    public byte[] a() {
        try {
            em.b bVar = new em.b();
            bVar.a(bb.a().a());
            return bVar.c();
        } catch (Exception e) {
            b.a("getOBBString err: " + e.toString());
            return null;
        }
    }
}
