package com.xiaomi.mipush.sdk;

import android.text.TextUtils;
import com.xiaomi.push.au;
import com.xiaomi.push.bf;
import com.xiaomi.push.ho;
import com.xiaomi.push.i;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.l;
import com.xiaomi.push.service.ak;
import java.util.HashMap;
import java.util.Map;

final class ae implements Runnable {
    ae() {
    }

    public void run() {
        if (l.g()) {
            return;
        }
        if (i.f(MiPushClient.l) != null || au.a(MiPushClient.l).a()) {
            in inVar = new in();
            inVar.b(b.a(MiPushClient.l).c());
            inVar.c("client_info_update");
            inVar.a(ak.a());
            inVar.a((Map<String, String>) new HashMap());
            String str = "";
            String f = i.f(MiPushClient.l);
            if (!TextUtils.isEmpty(f)) {
                str = str + bf.a(f);
            }
            String h = i.h(MiPushClient.l);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(h)) {
                str = str + "," + h;
            }
            if (!TextUtils.isEmpty(str)) {
                inVar.a().put(Constants.d, str);
            }
            au.a(MiPushClient.l).a((Map<String, String>) inVar.a());
            int b = i.b();
            if (b >= 0) {
                inVar.a().put("space_id", Integer.toString(b));
            }
            aw.a(MiPushClient.l).a(inVar, ho.Notification, false, (ib) null);
        }
    }
}
