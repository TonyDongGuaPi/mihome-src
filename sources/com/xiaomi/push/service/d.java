package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.fn;
import com.xiaomi.push.gj;
import com.xiaomi.push.gk;
import com.xiaomi.push.gl;
import com.xiaomi.push.gn;
import com.xiaomi.push.l;
import com.xiaomi.push.service.am;
import com.xiaomi.stat.c.c;
import java.util.Collection;
import java.util.Iterator;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private p f12921a = new p();

    public static String a(am.b bVar) {
        StringBuilder sb;
        String str;
        if (!"9".equals(bVar.g)) {
            sb = new StringBuilder();
            sb.append(bVar.f290a);
            str = ".permission.MIPUSH_RECEIVE";
        } else {
            sb = new StringBuilder();
            sb.append(bVar.f290a);
            str = ".permission.MIMC_RECEIVE";
        }
        sb.append(str);
        return sb.toString();
    }

    private static void a(Context context, Intent intent, am.b bVar) {
        if (c.f23036a.equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, a(bVar));
        }
    }

    /* access modifiers changed from: package-private */
    public am.b a(fn fnVar) {
        Collection a2 = am.a().a(Integer.toString(fnVar.c()));
        if (a2.isEmpty()) {
            return null;
        }
        Iterator it = a2.iterator();
        if (a2.size() == 1) {
            return (am.b) it.next();
        }
        String j = fnVar.j();
        while (it.hasNext()) {
            am.b bVar = (am.b) it.next();
            if (TextUtils.equals(j, bVar.f293b)) {
                return bVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.push.service.am.b a(com.xiaomi.push.gl r6) {
        /*
            r5 = this;
            com.xiaomi.push.service.am r0 = com.xiaomi.push.service.am.a()
            java.lang.String r1 = r6.l()
            java.util.Collection r0 = r0.a((java.lang.String) r1)
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L_0x0014
            return r2
        L_0x0014:
            java.util.Iterator r1 = r0.iterator()
            int r0 = r0.size()
            r3 = 1
            if (r0 != r3) goto L_0x0026
            java.lang.Object r6 = r1.next()
            com.xiaomi.push.service.am$b r6 = (com.xiaomi.push.service.am.b) r6
            return r6
        L_0x0026:
            java.lang.String r0 = r6.n()
            java.lang.String r6 = r6.m()
        L_0x002e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x004b
            java.lang.Object r3 = r1.next()
            com.xiaomi.push.service.am$b r3 = (com.xiaomi.push.service.am.b) r3
            java.lang.String r4 = r3.f293b
            boolean r4 = android.text.TextUtils.equals(r0, r4)
            if (r4 != 0) goto L_0x004a
            java.lang.String r4 = r3.f293b
            boolean r4 = android.text.TextUtils.equals(r6, r4)
            if (r4 == 0) goto L_0x002e
        L_0x004a:
            return r3
        L_0x004b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.d.a(com.xiaomi.push.gl):com.xiaomi.push.service.am$b");
    }

    @SuppressLint({"WrongConstant"})
    public void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        if (l.e()) {
            intent.addFlags(16777216);
        }
        context.sendBroadcast(intent);
    }

    public void a(Context context, am.b bVar, int i) {
        if (!"5".equalsIgnoreCase(bVar.g)) {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.channel_closed");
            intent.setPackage(bVar.f290a);
            intent.putExtra(aq.r, bVar.g);
            intent.putExtra("ext_reason", i);
            intent.putExtra(aq.p, bVar.f293b);
            intent.putExtra(aq.C, bVar.i);
            if (bVar.f284a == null || !"9".equals(bVar.g)) {
                a(context, intent, bVar);
                return;
            }
            try {
                bVar.f284a.send(Message.obtain((Handler) null, 17, intent));
            } catch (RemoteException unused) {
                bVar.f284a = null;
                b.a("peer may died: " + bVar.f293b.substring(bVar.f293b.lastIndexOf(64)));
            }
        }
    }

    public void a(Context context, am.b bVar, String str, String str2) {
        if ("5".equalsIgnoreCase(bVar.g)) {
            b.d("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(bVar.f290a);
        intent.putExtra("ext_kick_type", str);
        intent.putExtra("ext_kick_reason", str2);
        intent.putExtra("ext_chid", bVar.g);
        intent.putExtra(aq.p, bVar.f293b);
        intent.putExtra(aq.C, bVar.i);
        a(context, intent, bVar);
    }

    public void a(Context context, am.b bVar, boolean z, int i, String str) {
        if ("5".equalsIgnoreCase(bVar.g)) {
            this.f12921a.a(context, bVar, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(bVar.f290a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", bVar.g);
        intent.putExtra(aq.p, bVar.f293b);
        intent.putExtra(aq.C, bVar.i);
        a(context, intent, bVar);
    }

    public void a(XMPushService xMPushService, String str, fn fnVar) {
        am.b a2 = a(fnVar);
        if (a2 == null) {
            b.d("error while notify channel closed! channel " + str + " not registered");
        } else if ("5".equalsIgnoreCase(str)) {
            this.f12921a.a(xMPushService, fnVar, a2);
        } else {
            String str2 = a2.f290a;
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.new_msg");
            intent.setPackage(str2);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_raw_packet", fnVar.d(a2.h));
            intent.putExtra(aq.C, a2.i);
            intent.putExtra(aq.v, a2.h);
            if (a2.f284a != null) {
                try {
                    a2.f284a.send(Message.obtain((Handler) null, 17, intent));
                    return;
                } catch (RemoteException unused) {
                    a2.f284a = null;
                    b.a("peer may died: " + a2.f293b.substring(a2.f293b.lastIndexOf(64)));
                }
            }
            if (!c.f23036a.equals(str2)) {
                a((Context) xMPushService, intent, a2);
            }
        }
    }

    public void a(XMPushService xMPushService, String str, gl glVar) {
        String str2;
        String str3;
        am.b a2 = a(glVar);
        if (a2 == null) {
            str3 = "error while notify channel closed! channel " + str + " not registered";
        } else if ("5".equalsIgnoreCase(str)) {
            this.f12921a.a(xMPushService, glVar, a2);
            return;
        } else {
            String str4 = a2.f290a;
            if (glVar instanceof gk) {
                str2 = "com.xiaomi.push.new_msg";
            } else if (glVar instanceof gj) {
                str2 = "com.xiaomi.push.new_iq";
            } else if (glVar instanceof gn) {
                str2 = "com.xiaomi.push.new_pres";
            } else {
                str3 = "unknown packet type, drop it";
            }
            Intent intent = new Intent();
            intent.setAction(str2);
            intent.setPackage(str4);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", glVar.b());
            intent.putExtra(aq.C, a2.i);
            intent.putExtra(aq.v, a2.h);
            a((Context) xMPushService, intent, a2);
            return;
        }
        b.d(str3);
    }
}
