package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.c.d;
import com.huawei.hms.c.g;
import java.util.HashMap;

public abstract class a {
    /* access modifiers changed from: package-private */
    public abstract void a(b bVar);

    /* access modifiers changed from: package-private */
    public abstract void b(b bVar);

    /* access modifiers changed from: package-private */
    public abstract Activity c();

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
        Activity c = c();
        if (c != null && !c.isFinishing()) {
            HashMap hashMap = new HashMap();
            hashMap.put("package", c.getPackageName());
            hashMap.put("sdk_ver", String.valueOf(20502300));
            hashMap.put("app_id", g.a((Context) c));
            hashMap.put("trigger_api", com.huawei.hms.update.c.a.b());
            hashMap.put("hms_ver", String.valueOf(com.huawei.hms.update.c.a.a()));
            hashMap.put("update_type", String.valueOf(i2));
            hashMap.put("net_type", String.valueOf(d.a((Context) c)));
            hashMap.put("result", String.valueOf(i));
            com.huawei.hms.support.b.a.a().a(c, "HMS_SDK_UPDATE", hashMap);
        }
    }
}
