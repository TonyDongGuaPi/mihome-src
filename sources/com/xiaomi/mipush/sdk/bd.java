package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.au;
import com.xiaomi.push.bf;
import com.xiaomi.push.g;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.i;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.l;
import com.xiaomi.push.n;
import com.xiaomi.push.service.ak;
import java.util.HashMap;
import java.util.Map;

final class bd implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11548a;
    final /* synthetic */ boolean b;

    bd(Context context, boolean z) {
        this.f11548a = context;
        this.b = z;
    }

    public void run() {
        Map<String, String> map;
        String str;
        String b2;
        b.a("do sync info");
        in inVar = new in(ak.a(), false);
        b a2 = b.a(this.f11548a);
        inVar.c(hy.SyncInfo.f114a);
        inVar.b(a2.c());
        inVar.d(this.f11548a.getPackageName());
        inVar.f177a = new HashMap();
        n.a(inVar.f177a, "app_version", g.a(this.f11548a, this.f11548a.getPackageName()));
        n.a(inVar.f177a, Constants.c, Integer.toString(g.b(this.f11548a, this.f11548a.getPackageName())));
        n.a(inVar.f177a, "push_sdk_vn", "3_7_2");
        n.a(inVar.f177a, "push_sdk_vc", Integer.toString(30702));
        n.a(inVar.f177a, "token", a2.d());
        if (!l.g()) {
            String a3 = bf.a(i.f(this.f11548a));
            String h = i.h(this.f11548a);
            if (!TextUtils.isEmpty(h)) {
                a3 = a3 + "," + h;
            }
            if (!TextUtils.isEmpty(a3)) {
                n.a(inVar.f177a, Constants.d, a3);
            }
        }
        au.a(this.f11548a).a(inVar.f177a);
        n.a(inVar.f177a, Constants.f, a2.e());
        n.a(inVar.f177a, Constants.g, a2.f());
        n.a(inVar.f177a, Constants.h, MiPushClient.y(this.f11548a).replace(",", "-"));
        if (this.b) {
            n.a(inVar.f177a, Constants.i, bc.c(MiPushClient.b(this.f11548a)));
            n.a(inVar.f177a, Constants.k, bc.c(MiPushClient.c(this.f11548a)));
            map = inVar.f177a;
            str = Constants.m;
            b2 = bc.c(MiPushClient.d(this.f11548a));
        } else {
            n.a(inVar.f177a, Constants.j, bc.d(MiPushClient.b(this.f11548a)));
            n.a(inVar.f177a, Constants.l, bc.d(MiPushClient.c(this.f11548a)));
            map = inVar.f177a;
            str = Constants.n;
            b2 = bc.d(MiPushClient.d(this.f11548a));
        }
        n.a(map, str, b2);
        aw.a(this.f11548a).a(inVar, ho.Notification, false, (ib) null);
    }
}
