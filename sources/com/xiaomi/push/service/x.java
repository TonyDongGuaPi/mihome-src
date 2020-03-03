package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.i;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.service.bd;
import java.util.HashMap;
import java.util.Map;

final class x extends bd.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12943a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ k f366a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    x(String str, long j, XMPushService xMPushService, k kVar) {
        super(str, j);
        this.f12943a = xMPushService;
        this.f366a = kVar;
    }

    /* access modifiers changed from: package-private */
    public void a(bd bdVar) {
        String a2 = bdVar.a("GAID", "gaid");
        String b = i.b((Context) this.f12943a);
        b.c("gaid :" + b);
        if (!TextUtils.isEmpty(b) && !TextUtils.equals(a2, b)) {
            bdVar.a("GAID", "gaid", b);
            in inVar = new in();
            inVar.b(this.f366a.d);
            inVar.c(hy.ClientInfoUpdate.f114a);
            inVar.a(ak.a());
            inVar.a((Map<String, String>) new HashMap());
            inVar.a().put("gaid", b);
            this.f12943a.a(this.f12943a.getPackageName(), iy.a(w.a(this.f12943a.getPackageName(), this.f366a.d, inVar, ho.Notification)), true);
        }
    }
}
