package com.xiaomi.push.service;

import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.dh;
import com.xiaomi.push.fn;
import com.xiaomi.push.fu;
import com.xiaomi.push.gf;
import com.xiaomi.push.gl;
import com.xiaomi.push.ho;
import com.xiaomi.push.id;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import com.xiaomi.push.je;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.bd;
import java.nio.ByteBuffer;

final class w {
    static fn a(XMPushService xMPushService, byte[] bArr) {
        ik ikVar = new ik();
        try {
            iy.a(ikVar, bArr);
            return a(l.a((Context) xMPushService), (Context) xMPushService, ikVar);
        } catch (je e) {
            b.a((Throwable) e);
            return null;
        }
    }

    static fn a(k kVar, Context context, ik ikVar) {
        try {
            fn fnVar = new fn();
            fnVar.a(5);
            fnVar.c(kVar.f346a);
            fnVar.b(a(ikVar));
            fnVar.a(MIMCConstant.p, "message");
            String str = kVar.f346a;
            ikVar.f167a.f138a = str.substring(0, str.indexOf("@"));
            ikVar.f167a.c = str.substring(str.indexOf("/") + 1);
            fnVar.a(iy.a(ikVar), kVar.c);
            fnVar.a(1);
            b.a("try send mi push message. packagename:" + ikVar.b + " action:" + ikVar.f12803a);
            return fnVar;
        } catch (NullPointerException e) {
            b.a((Throwable) e);
            return null;
        }
    }

    static ik a(String str, String str2) {
        in inVar = new in();
        inVar.b(str2);
        inVar.c("package uninstalled");
        inVar.a(gl.j());
        inVar.a(false);
        return a(str, str2, inVar, ho.Notification);
    }

    static <T extends iz<T, ?>> ik a(String str, String str2, T t, ho hoVar) {
        byte[] a2 = iy.a(t);
        ik ikVar = new ik();
        id idVar = new id();
        idVar.f12796a = 5;
        idVar.f138a = "fakeid";
        ikVar.a(idVar);
        ikVar.a(ByteBuffer.wrap(a2));
        ikVar.a(hoVar);
        ikVar.b(true);
        ikVar.b(str);
        ikVar.a(false);
        ikVar.a(str2);
        return ikVar;
    }

    private static String a(ik ikVar) {
        if (!(ikVar.f166a == null || ikVar.f166a.f133b == null)) {
            String str = ikVar.f166a.f133b.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return ikVar.b;
    }

    static String a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    static void a(XMPushService xMPushService) {
        k a2 = l.a(xMPushService.getApplicationContext());
        if (a2 != null) {
            am.b a3 = l.a(xMPushService.getApplicationContext()).a(xMPushService);
            a(xMPushService, a3);
            am.a().a(a3);
            bd.a((Context) xMPushService).a((bd.a) new x("GAID", 172800, xMPushService, a2));
            a(xMPushService, a2, 172800);
        }
    }

    static void a(XMPushService xMPushService, ik ikVar) {
        dh.a(ikVar.b(), xMPushService.getApplicationContext(), ikVar, -1);
        fu a2 = xMPushService.a();
        if (a2 == null) {
            throw new gf("try send msg while connection is null.");
        } else if (a2.b()) {
            fn a3 = a(l.a((Context) xMPushService), (Context) xMPushService, ikVar);
            if (a3 != null) {
                a2.b(a3);
            }
        } else {
            throw new gf("Don't support XMPP connection.");
        }
    }

    static void a(XMPushService xMPushService, am.b bVar) {
        bVar.a((Messenger) null);
        bVar.a((am.b.a) new z(xMPushService));
    }

    private static void a(XMPushService xMPushService, k kVar, int i) {
        bd.a((Context) xMPushService).a((bd.a) new y("MSAID", (long) i, xMPushService, kVar));
    }

    static void a(XMPushService xMPushService, String str, byte[] bArr) {
        dh.a(str, xMPushService.getApplicationContext(), bArr);
        fu a2 = xMPushService.a();
        if (a2 == null) {
            throw new gf("try send msg while connection is null.");
        } else if (a2.b()) {
            fn a3 = a(xMPushService, bArr);
            if (a3 != null) {
                a2.b(a3);
            } else {
                o.a(xMPushService, str, bArr, ErrorCode.e, "not a valid message");
            }
        } else {
            throw new gf("Don't support XMPP connection.");
        }
    }
}
