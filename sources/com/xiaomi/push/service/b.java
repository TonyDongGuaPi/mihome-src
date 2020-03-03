package com.xiaomi.push.service;

import com.xiaomi.push.ai;
import com.xiaomi.push.ho;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import java.lang.ref.WeakReference;

public class b extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    private in f12897a;

    /* renamed from: a  reason: collision with other field name */
    private WeakReference<XMPushService> f312a;

    /* renamed from: a  reason: collision with other field name */
    private boolean f313a = false;

    public b(in inVar, WeakReference<XMPushService> weakReference, boolean z) {
        this.f12897a = inVar;
        this.f312a = weakReference;
        this.f313a = z;
    }

    public int a() {
        return 22;
    }

    public void run() {
        XMPushService xMPushService;
        if (this.f312a != null && this.f12897a != null && (xMPushService = (XMPushService) this.f312a.get()) != null) {
            this.f12897a.a(ak.a());
            this.f12897a.a(false);
            com.xiaomi.channel.commonutils.logger.b.c("MoleInfo aw_ping : send aw_Ping msg " + this.f12897a.a());
            try {
                String c = this.f12897a.c();
                xMPushService.a(c, iy.a(w.a(c, this.f12897a.b(), this.f12897a, ho.Notification)), this.f313a);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.d("MoleInfo aw_ping : send help app ping error" + e.toString());
            }
        }
    }
}
