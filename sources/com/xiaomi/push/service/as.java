package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.en;
import com.xiaomi.push.es;
import com.xiaomi.push.ew;
import com.xiaomi.push.hm;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import java.util.HashMap;

public class as implements ew {
    public void a(Context context, HashMap<String, String> hashMap) {
        in inVar = new in();
        inVar.b(es.a(context).b());
        inVar.d(es.a(context).c());
        inVar.c(hy.AwakeAppResponse.f114a);
        inVar.a(ak.a());
        inVar.f177a = hashMap;
        byte[] a2 = iy.a(w.a(inVar.c(), inVar.b(), inVar, ho.Notification));
        if (context instanceof XMPushService) {
            b.a("MoleInfo : send data directly in pushLayer " + inVar.a());
            ((XMPushService) context).a(context.getPackageName(), a2, true);
            return;
        }
        b.a("MoleInfo : context is not correct in pushLayer " + inVar.a());
    }

    public void b(Context context, HashMap<String, String> hashMap) {
        hm a2 = hm.a(context);
        if (a2 != null) {
            a2.a("category_awake_app", "wake_up_app", 1, en.a(hashMap));
        }
    }

    public void c(Context context, HashMap<String, String> hashMap) {
        b.a("MoleInfo：　" + en.b(hashMap));
    }
}
