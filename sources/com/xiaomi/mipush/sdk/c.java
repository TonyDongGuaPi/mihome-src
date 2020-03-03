package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.en;
import com.xiaomi.push.es;
import com.xiaomi.push.ew;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.service.ak;
import java.util.HashMap;

public class c implements ew {
    public void a(Context context, HashMap<String, String> hashMap) {
        in inVar = new in();
        inVar.b(es.a(context).b());
        inVar.d(es.a(context).c());
        inVar.c(hy.AwakeAppResponse.f114a);
        inVar.a(ak.a());
        inVar.f177a = hashMap;
        aw.a(context).a(inVar, ho.Notification, true, (ib) null, true);
        b.a("MoleInfo：　send data in app layer");
    }

    public void b(Context context, HashMap<String, String> hashMap) {
        MiTinyDataClient.a("category_awake_app", "wake_up_app", 1, en.a(hashMap));
        b.a("MoleInfo：　send data in app layer");
    }

    public void c(Context context, HashMap<String, String> hashMap) {
        b.a("MoleInfo：　" + en.b(hashMap));
        String str = hashMap.get("awake_info");
        if (String.valueOf(1007).equals(hashMap.get("event_type"))) {
            n.a(context, str);
        }
    }
}
