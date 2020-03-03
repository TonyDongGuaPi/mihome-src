package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.service.ak;

public class MiPushClient4VR {
    public static void a(Context context, String str) {
        in inVar = new in();
        inVar.c(hy.VRUpload.f114a);
        inVar.b(b.a(context).c());
        inVar.d(context.getPackageName());
        inVar.a("data", str);
        inVar.a(ak.a());
        aw.a(context).a(inVar, ho.Notification, (ib) null);
    }
}
