package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.au;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.service.bd;
import java.util.HashMap;
import java.util.Map;

final class y extends bd.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12944a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ k f367a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    y(String str, long j, XMPushService xMPushService, k kVar) {
        super(str, j);
        this.f12944a = xMPushService;
        this.f367a = kVar;
    }

    /* access modifiers changed from: package-private */
    public void a(bd bdVar) {
        au a2 = au.a((Context) this.f12944a);
        String a3 = bdVar.a("MSAID", "msaid");
        String str = a2.b() + a2.c() + a2.d() + a2.e();
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(a3, str)) {
            bdVar.a("MSAID", "msaid", str);
            in inVar = new in();
            inVar.b(this.f367a.d);
            inVar.c(hy.ClientInfoUpdate.f114a);
            inVar.a(ak.a());
            inVar.a((Map<String, String>) new HashMap());
            a2.a((Map<String, String>) inVar.a());
            this.f12944a.a(this.f12944a.getPackageName(), iy.a(w.a(this.f12944a.getPackageName(), this.f367a.d, inVar, ho.Notification)), true);
        }
    }
}
