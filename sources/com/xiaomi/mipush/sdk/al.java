package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ai;
import com.xiaomi.push.ho;
import com.xiaomi.push.hu;
import com.xiaomi.push.hy;
import com.xiaomi.push.ib;
import com.xiaomi.push.ig;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.service.ah;

public class al extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f11531a;

    public al(Context context) {
        this.f11531a = context;
    }

    public int a() {
        return 2;
    }

    public void run() {
        ah a2 = ah.a(this.f11531a);
        ig igVar = new ig();
        igVar.a(com.xiaomi.push.service.ai.a(a2, hu.MISC_CONFIG));
        igVar.b(com.xiaomi.push.service.ai.a(a2, hu.PLUGIN_CONFIG));
        in inVar = new in("-1", false);
        inVar.c(hy.DailyCheckClientConfig.f114a);
        inVar.a(iy.a(igVar));
        aw.a(this.f11531a).a(inVar, ho.Notification, (ib) null);
    }
}
