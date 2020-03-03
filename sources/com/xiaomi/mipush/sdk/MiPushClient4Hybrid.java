package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.push.bf;
import com.xiaomi.push.fi;
import com.xiaomi.push.g;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.i;
import com.xiaomi.push.ib;
import com.xiaomi.push.ic;
import com.xiaomi.push.ie;
import com.xiaomi.push.in;
import com.xiaomi.push.io;
import com.xiaomi.push.ip;
import com.xiaomi.push.iu;
import com.xiaomi.push.iv;
import com.xiaomi.push.iy;
import com.xiaomi.push.l;
import com.xiaomi.push.service.aa;
import com.xiaomi.push.service.ak;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MiPushClient4Hybrid {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, b.a> f11513a = new HashMap();
    private static Map<String, Long> b = new HashMap();
    private static MiPushCallback c;

    public static class MiPushCallback {
        public void a(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void b(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void c(String str, MiPushCommandMessage miPushCommandMessage) {
        }
    }

    private static short a(MiPushMessage miPushMessage, boolean z) {
        String str = miPushMessage.getExtra() == null ? "" : miPushMessage.getExtra().get(Constants.B);
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            i = Integer.valueOf(str).intValue();
        }
        if (!z) {
            i = (i & -4) + g.a.NOT_ALLOWED.a();
        }
        return (short) i;
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        MiPushClient.a(context, miPushMessage);
    }

    public static void a(Context context, MiPushMessage miPushMessage, boolean z) {
        if (miPushMessage == null || miPushMessage.getExtra() == null) {
            com.xiaomi.channel.commonutils.logger.b.a("do not ack message, message is null");
            return;
        }
        try {
            ie ieVar = new ie();
            ieVar.b(b.a(context).c());
            ieVar.a(miPushMessage.getMessageId());
            ieVar.a(Long.valueOf(miPushMessage.getExtra().get(Constants.A)).longValue());
            ieVar.a(a(miPushMessage, z));
            if (!TextUtils.isEmpty(miPushMessage.getTopic())) {
                ieVar.c(miPushMessage.getTopic());
            }
            aw.a(context).a(ieVar, ho.AckMessage, false, PushMessageHelper.a(miPushMessage));
            com.xiaomi.channel.commonutils.logger.b.b("MiPushClient4Hybrid ack mina message, messageId is " + miPushMessage.getMessageId());
        } catch (Throwable th) {
            miPushMessage.getExtra().remove(Constants.A);
            miPushMessage.getExtra().remove(Constants.B);
            throw th;
        }
        miPushMessage.getExtra().remove(Constants.A);
        miPushMessage.getExtra().remove(Constants.B);
    }

    public static void a(Context context, ip ipVar) {
        b.a aVar;
        String b2 = ipVar.b();
        if (ipVar.a() == 0 && (aVar = f11513a.get(b2)) != null) {
            aVar.a(ipVar.e, ipVar.f);
            b.a(context).a(b2, aVar);
        }
        ArrayList arrayList = null;
        if (!TextUtils.isEmpty(ipVar.e)) {
            arrayList = new ArrayList();
            arrayList.add(ipVar.e);
        }
        MiPushCommandMessage a2 = PushMessageHelper.a(fi.COMMAND_REGISTER.f70a, arrayList, ipVar.f191a, ipVar.d, (String) null);
        if (c != null) {
            c.a(b2, a2);
        }
    }

    public static void a(Context context, iv ivVar) {
        MiPushCommandMessage a2 = PushMessageHelper.a(fi.COMMAND_UNREGISTER.f70a, (List<String>) null, ivVar.f12814a, ivVar.d, (String) null);
        String a3 = ivVar.a();
        if (c != null) {
            c.b(a3, a2);
        }
    }

    public static void a(Context context, String str) {
        b.remove(str);
        b.a b2 = b.a(context).b(str);
        if (b2 != null) {
            iu iuVar = new iu();
            iuVar.a(ak.a());
            iuVar.d(str);
            iuVar.b(b2.f11545a);
            iuVar.c(b2.c);
            iuVar.e(b2.b);
            in inVar = new in();
            inVar.c(hy.HybridUnregister.f114a);
            inVar.b(b.a(context).c());
            inVar.d(context.getPackageName());
            inVar.a(iy.a(iuVar));
            inVar.a(ak.a());
            aw.a(context).a(inVar, ho.Notification, (ib) null);
            b.a(context).c(str);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (b.a(context).c(str2, str3, str)) {
            ArrayList arrayList = new ArrayList();
            b.a b2 = b.a(context).b(str);
            if (b2 != null) {
                arrayList.add(b2.c);
                MiPushCommandMessage a2 = PushMessageHelper.a(fi.COMMAND_REGISTER.f70a, arrayList, 0, (String) null, (String) null);
                if (c != null) {
                    c.a(str, a2);
                }
            }
            if (d(context, str)) {
                in inVar = new in();
                inVar.b(str2);
                inVar.c(hy.PullOfflineMessage.f114a);
                inVar.a(ak.a());
                inVar.a(false);
                aw.a(context).a(inVar, ho.Notification, false, true, (ib) null, false, str, str2);
                com.xiaomi.channel.commonutils.logger.b.b("MiPushClient4Hybrid pull offline pass through message");
                c(context, str);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - (b.get(str) != null ? b.get(str).longValue() : 0)) < 5000) {
            com.xiaomi.channel.commonutils.logger.b.a("MiPushClient4Hybrid  Could not send register message within 5s repeatedly.");
            return;
        }
        b.put(str, Long.valueOf(currentTimeMillis));
        String a3 = bf.a(6);
        b.a aVar = new b.a(context);
        aVar.c(str2, str3, a3);
        f11513a.put(str, aVar);
        io ioVar = new io();
        ioVar.a(ak.a());
        ioVar.b(str2);
        ioVar.e(str3);
        ioVar.d(str);
        ioVar.f(a3);
        ioVar.c(g.a(context, context.getPackageName()));
        ioVar.b(g.b(context, context.getPackageName()));
        ioVar.h("3_7_2");
        ioVar.a(30702);
        ioVar.i(i.e(context));
        ioVar.a(ic.Init);
        if (!l.g()) {
            String g = i.g(context);
            if (!TextUtils.isEmpty(g)) {
                ioVar.k(bf.a(g));
            }
        }
        ioVar.j(i.a());
        int b3 = i.b();
        if (b3 >= 0) {
            ioVar.c(b3);
        }
        in inVar2 = new in();
        inVar2.c(hy.HybridRegister.f114a);
        inVar2.b(b.a(context).c());
        inVar2.d(context.getPackageName());
        inVar2.a(iy.a(ioVar));
        inVar2.a(ak.a());
        aw.a(context).a(inVar2, ho.Notification, (ib) null);
    }

    public static void a(Context context, LinkedList<? extends Object> linkedList) {
        aa.a(context, linkedList);
    }

    public static void a(MiPushCallback miPushCallback) {
        c = miPushCallback;
    }

    public static void b(Context context, MiPushMessage miPushMessage) {
        String str = miPushMessage.getExtra() != null ? miPushMessage.getExtra().get("jobkey") : null;
        if (TextUtils.isEmpty(str)) {
            str = miPushMessage.getMessageId();
        }
        at.a(context, str);
    }

    public static boolean b(Context context, String str) {
        return b.a(context).b(str) != null;
    }

    private static void c(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putLong("last_pull_notification_" + str, System.currentTimeMillis()).commit();
    }

    private static boolean d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("last_pull_notification_");
        sb.append(str);
        return Math.abs(System.currentTimeMillis() - sharedPreferences.getLong(sb.toString(), -1)) > 300000;
    }
}
