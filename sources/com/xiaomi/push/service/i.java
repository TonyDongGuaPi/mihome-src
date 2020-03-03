package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.hn;
import com.xiaomi.push.hs;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.stat.c.c;
import java.util.List;

public class i implements hn {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final XMPushService f12928a;

    public i(XMPushService xMPushService) {
        this.f12928a = xMPushService;
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        return c.f23036a.equals(str) ? "1000271" : this.f12928a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, (String) null);
    }

    public void a(List<hs> list, String str, String str2) {
        b.a("TinyData LongConnUploader.upload items size:" + list.size() + "  ts:" + System.currentTimeMillis());
        this.f12928a.a((XMPushService.i) new j(this, 4, str, list, str2));
    }
}
