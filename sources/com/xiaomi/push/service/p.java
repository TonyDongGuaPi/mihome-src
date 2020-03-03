package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.service.local.rpc.IRpcException;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.dh;
import com.xiaomi.push.fd;
import com.xiaomi.push.fn;
import com.xiaomi.push.g;
import com.xiaomi.push.gf;
import com.xiaomi.push.gi;
import com.xiaomi.push.gk;
import com.xiaomi.push.gl;
import com.xiaomi.push.gz;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.ib;
import com.xiaomi.push.ie;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.aa;
import com.xiaomi.push.service.am;
import com.xiaomi.push.t;
import com.xiaomi.stat.c.c;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class p {
    public static Intent a(byte[] bArr, long j) {
        ik a2 = a(bArr);
        if (a2 == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(a2.b);
        return intent;
    }

    public static ik a(Context context, ik ikVar) {
        ie ieVar = new ie();
        ieVar.b(ikVar.a());
        ib a2 = ikVar.a();
        if (a2 != null) {
            ieVar.a(a2.a());
            ieVar.a(a2.a());
            if (!TextUtils.isEmpty(a2.b())) {
                ieVar.c(a2.b());
            }
        }
        ieVar.a(iy.a(context, ikVar));
        ik a3 = w.a(ikVar.b(), ikVar.a(), ieVar, ho.AckMessage);
        ib a4 = ikVar.a().a();
        a4.a("mat", Long.toString(System.currentTimeMillis()));
        a3.a(a4);
        return a3;
    }

    public static ik a(byte[] bArr) {
        ik ikVar = new ik();
        try {
            iy.a(ikVar, bArr);
            return ikVar;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    private static void a(XMPushService xMPushService, ik ikVar) {
        xMPushService.a((XMPushService.i) new q(4, xMPushService, ikVar));
    }

    private static void a(XMPushService xMPushService, ik ikVar, String str) {
        xMPushService.a((XMPushService.i) new u(4, xMPushService, ikVar, str));
    }

    private static void a(XMPushService xMPushService, ik ikVar, String str, String str2) {
        xMPushService.a((XMPushService.i) new v(4, xMPushService, ikVar, str, str2));
    }

    public static void a(XMPushService xMPushService, String str, byte[] bArr, Intent intent) {
        fd a2;
        String b;
        String str2;
        String a3;
        int i;
        String str3;
        fd a4;
        String b2;
        String b3;
        String a5;
        String str4;
        boolean z;
        XMPushService xMPushService2 = xMPushService;
        byte[] bArr2 = bArr;
        Intent intent2 = intent;
        ik a6 = a(bArr);
        ib a7 = a6.a();
        String str5 = null;
        if (bArr2 != null) {
            dh.a(a6.b(), xMPushService.getApplicationContext(), (iz) null, a6.a(), bArr2.length);
        }
        if (c(a6) && a((Context) xMPushService, str)) {
            if (aa.e(a6)) {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), "old message received by new SDK.");
            }
            c(xMPushService2, a6);
        } else if (a(a6) && !a((Context) xMPushService, str) && !b(a6)) {
            if (aa.e(a6)) {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), "new message received by old SDK.");
            }
            d(xMPushService2, a6);
        } else if ((aa.a(a6) && g.e(xMPushService2, a6.b)) || a((Context) xMPushService2, intent2)) {
            if (ho.Registration == a6.a()) {
                String b4 = a6.b();
                SharedPreferences.Editor edit = xMPushService2.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                edit.putString(b4, a6.f168a);
                edit.commit();
                fd.a(xMPushService.getApplicationContext()).a(b4, "E100003", a7.a(), IRpcException.ErrorCode.SERVER_ILLEGALACCESS, "receive a register message");
                if (!TextUtils.isEmpty(a7.a())) {
                    intent2.putExtra("messageId", a7.a());
                    intent2.putExtra("eventMessageType", 6000);
                }
            }
            if (aa.c(a6)) {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), 1001, System.currentTimeMillis(), "receive notification message ");
                if (!TextUtils.isEmpty(a7.a())) {
                    intent2.putExtra("messageId", a7.a());
                    intent2.putExtra("eventMessageType", 1000);
                }
            }
            if (aa.b(a6)) {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), 2001, System.currentTimeMillis(), "receive passThrough message");
                if (!TextUtils.isEmpty(a7.a())) {
                    intent2.putExtra("messageId", a7.a());
                    intent2.putExtra("eventMessageType", 2000);
                }
            }
            if (aa.a(a6)) {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), 3001, System.currentTimeMillis(), "receive business message");
                if (!TextUtils.isEmpty(a7.a())) {
                    intent2.putExtra("messageId", a7.a());
                    intent2.putExtra("eventMessageType", 3000);
                }
            }
            if (a7 != null && !TextUtils.isEmpty(a7.c()) && !TextUtils.isEmpty(a7.d()) && a7.b != 1 && (aa.a((Map<String, String>) a7.a()) || !aa.a((Context) xMPushService2, a6.b))) {
                if (a7 != null) {
                    if (a7.f130a != null) {
                        str5 = a7.f130a.get("jobkey");
                    }
                    if (TextUtils.isEmpty(str5)) {
                        str5 = a7.a();
                    }
                    z = ac.a(xMPushService2, a6.b, str5);
                } else {
                    z = false;
                }
                if (z) {
                    fd.a(xMPushService.getApplicationContext()).c(a6.b(), aa.b(a6), a7.a(), "drop a duplicate message");
                    b.a("drop a duplicate message, key=" + str5);
                } else {
                    aa.c a8 = aa.a((Context) xMPushService2, a6, bArr2);
                    if (a8.f12866a > 0 && !TextUtils.isEmpty(a8.f266a)) {
                        gz.a(xMPushService, a8.f266a, a8.f12866a, true, false, System.currentTimeMillis());
                    }
                    if (!aa.a(a6)) {
                        Intent intent3 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                        intent3.putExtra("mipush_payload", bArr2);
                        intent3.setPackage(a6.b);
                        try {
                            List<ResolveInfo> queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent3, 0);
                            if (queryBroadcastReceivers != null && !queryBroadcastReceivers.isEmpty()) {
                                xMPushService2.sendBroadcast(intent3, w.a(a6.b));
                            }
                        } catch (Exception e) {
                            xMPushService2.sendBroadcast(intent3, w.a(a6.b));
                            fd.a(xMPushService.getApplicationContext()).b(a6.b(), aa.b(a6), a7.a(), e.getMessage());
                        }
                    }
                }
                b(xMPushService2, a6);
            } else if (c.f23036a.contains(a6.b) && !a6.b() && a7 != null && a7.a() != null && a7.a().containsKey("ab")) {
                b(xMPushService2, a6);
                b.c("receive abtest message. ack it." + a7.a());
            } else if (a(xMPushService2, str, a6, a7)) {
                if (a7 != null && !TextUtils.isEmpty(a7.a())) {
                    if (aa.b(a6)) {
                        a2 = fd.a(xMPushService.getApplicationContext());
                        b = a6.b();
                        str2 = aa.b(a6);
                        a3 = a7.a();
                        i = 2002;
                        str3 = "try send passThrough message Broadcast";
                    } else {
                        if (aa.a(a6)) {
                            a4 = fd.a(xMPushService.getApplicationContext());
                            b2 = a6.b();
                            b3 = aa.b(a6);
                            a5 = a7.a();
                            str4 = "try show awake message , but it don't show in foreground";
                        } else if (aa.c(a6)) {
                            a4 = fd.a(xMPushService.getApplicationContext());
                            b2 = a6.b();
                            b3 = aa.b(a6);
                            a5 = a7.a();
                            str4 = "try show notification message , but it don't show in foreground";
                        } else if (aa.d(a6)) {
                            a2 = fd.a(xMPushService.getApplicationContext());
                            b = a6.b();
                            str2 = "E100003";
                            a3 = a7.a();
                            i = IRpcException.ErrorCode.SERVER_JSONPARSEREXCEPTION;
                            str3 = "try send register broadcast";
                        }
                        a4.a(b2, b3, a5, str4);
                    }
                    a2.a(b, str2, a3, i, str3);
                }
                xMPushService2.sendBroadcast(intent2, w.a(a6.b));
            } else {
                fd.a(xMPushService.getApplicationContext()).a(a6.b(), aa.b(a6), a7.a(), "passThough message: not permit to send broadcast ");
            }
            if (a6.a() == ho.UnRegistration && !c.f23036a.equals(xMPushService.getPackageName())) {
                xMPushService.stopSelf();
            }
        } else if (!g.e(xMPushService2, a6.b)) {
            if (aa.e(a6)) {
                fd.a(xMPushService.getApplicationContext()).b(a6.b(), aa.b(a6), a7.a(), "receive a message, but the package is removed.");
            }
            a(xMPushService2, a6);
        } else {
            b.a("receive a mipush message, we can see the app, but we can't see the receiver.");
            if (aa.e(a6)) {
                fd.a(xMPushService.getApplicationContext()).b(a6.b(), aa.b(a6), a7.a(), "receive a mipush message, we can see the app, but we can't see the receiver.");
            }
        }
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) {
        Map a2;
        ik a3 = a(bArr);
        if (a3 != null) {
            if (TextUtils.isEmpty(a3.b)) {
                b.a("receive a mipush message without package name");
                return;
            }
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            Intent a4 = a(bArr, valueOf.longValue());
            String a5 = aa.a(a3);
            gz.a(xMPushService, a5, j, true, true, System.currentTimeMillis());
            ib a6 = a3.a();
            if (a6 != null) {
                a6.a("mrt", Long.toString(valueOf.longValue()));
            }
            if (ho.SendMessage == a3.a() && m.a((Context) xMPushService).a(a3.b) && !aa.a(a3)) {
                String str = "";
                if (a6 != null) {
                    str = a6.a();
                    if (aa.e(a3)) {
                        fd.a(xMPushService.getApplicationContext()).a(a3.b(), aa.b(a3), str, "Drop a message for unregistered");
                    }
                }
                b.a("Drop a message for unregistered, msgid=" + str);
                a(xMPushService, a3, a3.b);
            } else if (ho.SendMessage == a3.a() && m.a((Context) xMPushService).c(a3.b) && !aa.a(a3)) {
                String str2 = "";
                if (a6 != null) {
                    str2 = a6.a();
                    if (aa.e(a3)) {
                        fd.a(xMPushService.getApplicationContext()).a(a3.b(), aa.b(a3), str2, "Drop a message for push closed");
                    }
                }
                b.a("Drop a message for push closed, msgid=" + str2);
                a(xMPushService, a3, a3.b);
            } else if (ho.SendMessage != a3.a() || TextUtils.equals(xMPushService.getPackageName(), c.f23036a) || TextUtils.equals(xMPushService.getPackageName(), a3.b)) {
                if (!(a6 == null || a6.a() == null)) {
                    b.a(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[]{a3.a(), a6.a()}));
                }
                if (a6 == null || (a2 = a6.a()) == null || !a2.containsKey("hide") || !"true".equalsIgnoreCase((String) a2.get("hide"))) {
                    if (!(a6 == null || a6.a() == null || !a6.a().containsKey("__miid"))) {
                        String str3 = (String) a6.a().get("__miid");
                        String c = t.c(xMPushService.getApplicationContext());
                        if (TextUtils.isEmpty(c) || !TextUtils.equals(str3, c)) {
                            if (aa.e(a3)) {
                                fd.a(xMPushService.getApplicationContext()).a(a3.b(), aa.b(a3), a6.a(), "miid already logout or anther already login");
                            }
                            b.a(str3 + " should be login, but got " + c);
                            a(xMPushService, a3, "miid already logout or anther already login", str3 + " should be login, but got " + c);
                            return;
                        }
                    }
                    a(xMPushService, a5, bArr, a4);
                    return;
                }
                b(xMPushService, a3);
            } else {
                b.a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + a3.b);
                a(xMPushService, a3, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + a3.b);
                if (a6 != null && aa.e(a3)) {
                    fd.a(xMPushService.getApplicationContext()).a(a3.b(), aa.b(a3), a6.a(), "Receive a message with wrong package name");
                }
            }
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return queryBroadcastReceivers != null && !queryBroadcastReceivers.isEmpty();
        } catch (Exception unused) {
            return true;
        }
    }

    private static boolean a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            return !packageManager.queryBroadcastReceivers(intent2, 32).isEmpty() || !packageManager.queryIntentServices(intent, 32).isEmpty();
        } catch (Exception e) {
            b.a((Throwable) e);
            return false;
        }
    }

    private static boolean a(ik ikVar) {
        return c.f23036a.equals(ikVar.b) && ikVar.a() != null && ikVar.a().a() != null && ikVar.a().a().containsKey("miui_package_name");
    }

    private static boolean a(XMPushService xMPushService, String str, ik ikVar, ib ibVar) {
        boolean z = true;
        if (ibVar != null && ibVar.a() != null && ibVar.a().containsKey("__check_alive") && ibVar.a().containsKey("__awake")) {
            in inVar = new in();
            inVar.b(ikVar.a());
            inVar.d(str);
            inVar.c(hy.AwakeSystemApp.f114a);
            inVar.a(ibVar.a());
            inVar.f177a = new HashMap();
            boolean d = g.d(xMPushService.getApplicationContext(), str);
            inVar.f177a.put("app_running", Boolean.toString(d));
            if (!d) {
                boolean parseBoolean = Boolean.parseBoolean((String) ibVar.a().get("__awake"));
                inVar.f177a.put("awaked", Boolean.toString(parseBoolean));
                if (!parseBoolean) {
                    z = false;
                }
            }
            try {
                w.a(xMPushService, w.a(ikVar.b(), ikVar.a(), inVar, ho.Notification));
            } catch (gf e) {
                b.a((Throwable) e);
            }
        }
        return z;
    }

    private static void b(XMPushService xMPushService, ik ikVar) {
        xMPushService.a((XMPushService.i) new r(4, xMPushService, ikVar));
    }

    private static boolean b(ik ikVar) {
        Map a2 = ikVar.a().a();
        return a2 != null && a2.containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, ik ikVar) {
        xMPushService.a((XMPushService.i) new s(4, xMPushService, ikVar));
    }

    private static boolean c(ik ikVar) {
        if (ikVar.a() == null || ikVar.a().a() == null) {
            return false;
        }
        return "1".equals(ikVar.a().a().get("obslete_ads_message"));
    }

    private static void d(XMPushService xMPushService, ik ikVar) {
        xMPushService.a((XMPushService.i) new t(4, xMPushService, ikVar));
    }

    public void a(Context context, am.b bVar, boolean z, int i, String str) {
        k a2;
        if (!z && (a2 = l.a(context)) != null && "token-expired".equals(str)) {
            try {
                l.a(context, a2.f, a2.d, a2.e);
            } catch (IOException | JSONException e) {
                b.a(e);
            }
        }
    }

    public void a(XMPushService xMPushService, fn fnVar, am.b bVar) {
        try {
            a(xMPushService, fnVar.d(bVar.h), (long) fnVar.l());
        } catch (IllegalArgumentException e) {
            b.a((Throwable) e);
        }
    }

    public void a(XMPushService xMPushService, gl glVar, am.b bVar) {
        if (glVar instanceof gk) {
            gk gkVar = (gk) glVar;
            gi p = gkVar.p("s");
            if (p != null) {
                try {
                    a(xMPushService, av.a(av.a(bVar.h, gkVar.k()), p.c()), (long) gz.b(glVar.c()));
                } catch (IllegalArgumentException e) {
                    b.a((Throwable) e);
                }
            }
        } else {
            b.a("not a mipush message");
        }
    }
}
