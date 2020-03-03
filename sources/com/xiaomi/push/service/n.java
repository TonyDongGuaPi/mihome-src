package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.gf;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import java.io.IOException;
import java.util.Collection;
import org.json.JSONException;

public class n extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    private XMPushService f12933a;

    /* renamed from: a  reason: collision with other field name */
    private String f350a;

    /* renamed from: a  reason: collision with other field name */
    private byte[] f351a;
    private String b;
    private String c;

    public n(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.f12933a = xMPushService;
        this.f350a = str;
        this.f351a = bArr;
        this.b = str2;
        this.c = str3;
    }

    public String a() {
        return "register app";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m341a() {
        am.b bVar;
        k a2 = l.a((Context) this.f12933a);
        if (a2 == null) {
            try {
                a2 = l.a(this.f12933a, this.f350a, this.b, this.c);
            } catch (IOException | JSONException e) {
                b.a(e);
            }
        }
        if (a2 == null) {
            b.d("no account for mipush");
            o.a(this.f12933a, ErrorCode.d, "no account.");
            return;
        }
        Collection a3 = am.a().a("5");
        if (a3.isEmpty()) {
            bVar = a2.a(this.f12933a);
            w.a(this.f12933a, bVar);
            am.a().a(bVar);
        } else {
            bVar = (am.b) a3.iterator().next();
        }
        if (this.f12933a.c()) {
            try {
                if (bVar.f288a == am.c.binded) {
                    w.a(this.f12933a, this.f350a, this.f351a);
                } else if (bVar.f288a == am.c.unbind) {
                    XMPushService xMPushService = this.f12933a;
                    XMPushService xMPushService2 = this.f12933a;
                    xMPushService2.getClass();
                    xMPushService.a((XMPushService.i) new XMPushService.a(bVar));
                }
            } catch (gf e2) {
                b.a((Throwable) e2);
                this.f12933a.a(10, (Exception) e2);
            }
        } else {
            this.f12933a.a(true);
        }
    }
}
