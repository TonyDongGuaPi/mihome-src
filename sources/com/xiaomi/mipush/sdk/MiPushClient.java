package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.ai;
import com.xiaomi.push.bf;
import com.xiaomi.push.ds;
import com.xiaomi.push.fa;
import com.xiaomi.push.fb;
import com.xiaomi.push.fc;
import com.xiaomi.push.fi;
import com.xiaomi.push.g;
import com.xiaomi.push.ho;
import com.xiaomi.push.ht;
import com.xiaomi.push.hy;
import com.xiaomi.push.i;
import com.xiaomi.push.ib;
import com.xiaomi.push.ic;
import com.xiaomi.push.ii;
import com.xiaomi.push.in;
import com.xiaomi.push.io;
import com.xiaomi.push.is;
import com.xiaomi.push.iu;
import com.xiaomi.push.iw;
import com.xiaomi.push.l;
import com.xiaomi.push.n;
import com.xiaomi.push.r;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.ak;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.t;
import com.xiaomi.smarthome.library.common.util.DateTimeHelper;
import com.xiaomi.stat.c.c;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public abstract class MiPushClient {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11511a = "register";
    public static final String b = "unregister";
    public static final String c = "set-alias";
    public static final String d = "unset-alias";
    public static final String e = "set-account";
    public static final String f = "unset-account";
    public static final String g = "subscribe-topic";
    public static final String h = "unsubscibe-topic";
    public static final String i = "accept-time";
    public static final String j = "mipush_extra";
    private static boolean k = false;
    /* access modifiers changed from: private */
    public static Context l;
    private static long m = System.currentTimeMillis();

    @Deprecated
    public static abstract class MiPushClientCallback {

        /* renamed from: a  reason: collision with root package name */
        private String f11512a;

        /* access modifiers changed from: protected */
        public String a() {
            return this.f11512a;
        }

        public void a(long j, String str, String str2) {
        }

        public void a(MiPushMessage miPushMessage) {
        }

        /* access modifiers changed from: protected */
        public void a(String str) {
            this.f11512a = str;
        }

        public void a(String str, long j, String str2, List<String> list) {
        }

        public void a(String str, String str2, String str3, boolean z) {
        }

        public void b(long j, String str, String str2) {
        }

        public void c(long j, String str, String str2) {
        }
    }

    private static void A(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            context.getApplicationContext().registerReceiver(new NetworkStatusReceiver((Object) null), intentFilter);
        } catch (Throwable th) {
            b.a(th);
        }
    }

    private static void B(Context context) {
        if (ah.a(l).a(ht.DataCollectionSwitch.a(), c())) {
            ds.a().a(new q(context));
            ai.a(l).a((Runnable) new ad(), 10);
        }
    }

    private static void C(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        r.a(edit);
    }

    private static boolean D(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1)) > 300000;
    }

    private static void E(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        r.a(edit);
    }

    private static boolean F(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1)) > 5000;
    }

    private static void G(Context context) {
        fc.a((fc.a) new ag());
        Config c2 = fc.c(context);
        ClientReportClient.a(context, c2, new fa(context), new fb(context));
        a.a(context);
        r.a(context, c2);
        ah.a(context).a((ah.a) new ah(100, "perf event job update", context));
    }

    private static void H(Context context) {
        if ("syncing".equals(am.a(l).a(bb.DISABLE_PUSH))) {
            h(l);
        }
        if ("syncing".equals(am.a(l).a(bb.ENABLE_PUSH))) {
            i(l);
        }
        if ("syncing".equals(am.a(l).a(bb.UPLOAD_HUAWEI_TOKEN))) {
            j(l);
        }
        if ("syncing".equals(am.a(l).a(bb.UPLOAD_FCM_TOKEN))) {
            k(l);
        }
        if ("syncing".equals(am.a(l).a(bb.UPLOAD_COS_TOKEN))) {
            l(context);
        }
        if ("syncing".equals(am.a(l).a(bb.UPLOAD_FTOS_TOKEN))) {
            m(context);
        }
    }

    public static void a(Context context, int i2) {
        aw.a(context).b(i2 & -1);
    }

    public static void a(Context context, int i2, int i3, int i4, int i5, String str) {
        Context context2 = context;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        if (i6 < 0 || i6 >= 24 || i8 < 0 || i8 >= 24 || i7 < 0 || i7 >= 60 || i9 < 0 || i9 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = (long) (((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60);
        long j2 = ((((long) ((i6 * 60) + i7)) + rawOffset) + DateTimeHelper.e) % DateTimeHelper.e;
        long j3 = ((((long) ((i8 * 60) + i9)) + rawOffset) + DateTimeHelper.e) % DateTimeHelper.e;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j3 / 60), Long.valueOf(j3 % 60)}));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5)}));
        if (!j(context2, (String) arrayList.get(0), (String) arrayList.get(1))) {
            a(context2, fi.COMMAND_SET_ACCEPT_TIME.f70a, (ArrayList<String>) arrayList, str);
        } else if (1 == PushMessageHelper.a(context)) {
            PushMessageHandler.a(context, str, fi.COMMAND_SET_ACCEPT_TIME.f70a, 0, (String) null, arrayList2);
        } else {
            PushMessageHelper.a(context2, PushMessageHelper.a(fi.COMMAND_SET_ACCEPT_TIME.f70a, arrayList2, 0, (String) null, (String) null));
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        ib ibVar = new ib();
        ibVar.a(miPushMessage.getMessageId());
        ibVar.b(miPushMessage.getTopic());
        ibVar.d(miPushMessage.getDescription());
        ibVar.c(miPushMessage.getTitle());
        ibVar.c(miPushMessage.getNotifyId());
        ibVar.a(miPushMessage.getNotifyType());
        ibVar.b(miPushMessage.getPassThrough());
        ibVar.a(miPushMessage.getExtra());
        a(context, miPushMessage.getMessageId(), ibVar, (String) null);
    }

    static void a(Context context, ic icVar) {
        if (b.a(context).j()) {
            String a2 = bf.a(6);
            String c2 = b.a(context).c();
            String d2 = b.a(context).d();
            b.a(context).i();
            b.a(context).a(Constants.a());
            b.a(context).a(c2, d2, a2);
            io ioVar = new io();
            ioVar.a(ak.a());
            ioVar.b(c2);
            ioVar.e(d2);
            ioVar.f(a2);
            ioVar.d(context.getPackageName());
            ioVar.c(g.a(context, context.getPackageName()));
            ioVar.a(icVar);
            aw.a(context).a(ioVar, false);
        }
    }

    @Deprecated
    public static void a(Context context, String str) {
        a(context, str, (ib) null, (String) null);
    }

    static void a(Context context, String str, ib ibVar, String str2) {
        in inVar = new in();
        if (TextUtils.isEmpty(str2)) {
            if (b.a(context).b()) {
                str2 = b.a(context).c();
            } else {
                b.d("do not report clicked message");
                return;
            }
        }
        inVar.b(str2);
        inVar.c("bar:click");
        inVar.a(str);
        inVar.a(false);
        aw.a(context).a(inVar, ho.Notification, false, ibVar);
    }

    static void a(Context context, String str, ib ibVar, String str2, String str3) {
        in inVar = new in();
        if (TextUtils.isEmpty(str3)) {
            b.d("do not report clicked message");
            return;
        }
        inVar.b(str3);
        inVar.c("bar:click");
        inVar.a(str);
        inVar.a(false);
        aw.a(context).a(inVar, ho.Notification, false, true, ibVar, true, str2, str3);
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, new PushConfiguration());
    }

    @Deprecated
    public static void a(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        b(context, str, str2, miPushClientCallback, (String) null);
    }

    public static void a(Context context, String str, String str2, PushConfiguration pushConfiguration) {
        a(context, str, str2, pushConfiguration, (String) null);
    }

    private static void a(Context context, String str, String str2, PushConfiguration pushConfiguration, String str3) {
        a((Object) context, "context");
        a((Object) str, "appID");
        a((Object) str2, "appToken");
        l = context.getApplicationContext();
        if (l == null) {
            l = context;
        }
        Context context2 = l;
        t.a(context2);
        if (!NetworkStatusReceiver.a()) {
            A(l);
        }
        e.a(l).a(pushConfiguration);
        ai.a(context2).a((Runnable) new ac(str, str2, str3));
    }

    public static void a(Context context, String str, String str2, String str3) {
        a(context, str, str2, new PushConfiguration(), str3);
    }

    protected static void a(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(b.a(context).c())) {
            ii iiVar = new ii();
            iiVar.a(ak.a());
            iiVar.b(b.a(context).c());
            iiVar.c(str);
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                iiVar.a(it.next());
            }
            iiVar.e(str2);
            iiVar.d(context.getPackageName());
            aw.a(context).a(iiVar, ho.Command, (ib) null);
        }
    }

    public static void a(Context context, boolean z) {
        if (b.a(context).b()) {
            hy hyVar = z ? hy.APP_SLEEP : hy.APP_WAKEUP;
            in inVar = new in();
            inVar.b(b.a(context).c());
            inVar.c(hyVar.f114a);
            inVar.d(context.getPackageName());
            inVar.a(ak.a());
            inVar.a(false);
            aw.a(context).a(inVar, ho.Notification, false, (ib) null, false);
        }
    }

    public static void a(Context context, String[] strArr) {
        ai.a(context).a((Runnable) new af(strArr, context));
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    public static void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Thread.setDefaultUncaughtExceptionHandler(new v(l, uncaughtExceptionHandler));
        k = true;
    }

    public static boolean a(Context context) {
        return aw.a(context).c();
    }

    public static List<String> b(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("alias_")) {
                arrayList.add(next.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    private static void b() {
        ai.a(l).a(new al(l), ah.a(l).a(ht.OcVersionCheckFrequency.a(), 86400), 5);
    }

    public static void b(Context context, int i2) {
        aw.a(context).a(i2);
    }

    /* access modifiers changed from: private */
    public static void b(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            int length = serviceInfoArr.length;
            int i2 = 0;
            while (i2 < length) {
                ServiceInfo serviceInfo = serviceInfoArr[i2];
                if (!serviceInfo.exported || !serviceInfo.enabled || !"com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) || context.getPackageName().equals(serviceInfo.packageName)) {
                    i2++;
                } else {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        intent.putExtra("waker_pkgname", context.getPackageName());
                        PushMessageHandler.a(context, intent);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
        }
    }

    public static void b(Context context, String str) {
        a(context, 0, 0, 0, 0, str);
    }

    public static void b(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            b(context, fi.COMMAND_SET_ALIAS.f70a, str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str, String str2, MiPushClientCallback miPushClientCallback, String str3) {
        try {
            b.a("sdk_version = 3_7_2");
            if (miPushClientCallback != null) {
                PushMessageHandler.a(miPushClientCallback);
            }
            if (t.b(l)) {
                x.a(l);
            }
            if (b.a(l).a(str, str2) || z(l)) {
                boolean z = b.a(l).m() != Constants.a();
                if (z || F(l)) {
                    if (z || !b.a(l).a(str, str2) || b.a(l).n()) {
                        String a2 = bf.a(6);
                        b.a(l).i();
                        b.a(l).a(Constants.a());
                        b.a(l).a(str, str2, a2);
                        MiTinyDataClient.a.a().b(MiTinyDataClient.f11516a);
                        e(l);
                        io ioVar = new io();
                        ioVar.a(ak.a());
                        ioVar.b(str);
                        ioVar.e(str2);
                        ioVar.d(l.getPackageName());
                        ioVar.f(a2);
                        ioVar.c(g.a(l, l.getPackageName()));
                        ioVar.b(g.b(l, l.getPackageName()));
                        ioVar.h("3_7_2");
                        ioVar.a(30702);
                        ioVar.i(i.e(l));
                        ioVar.a(ic.Init);
                        if (!TextUtils.isEmpty(str3)) {
                            ioVar.g(str3);
                        }
                        if (!l.g()) {
                            String g2 = i.g(l);
                            if (!TextUtils.isEmpty(g2)) {
                                ioVar.k(bf.a(g2) + "," + i.j(l));
                            }
                        }
                        ioVar.j(i.a());
                        int b2 = i.b();
                        if (b2 >= 0) {
                            ioVar.c(b2);
                        }
                        aw.a(l).a(ioVar, z);
                        l.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
                    } else {
                        if (1 == PushMessageHelper.a(l)) {
                            a((Object) miPushClientCallback, "callback");
                            miPushClientCallback.a(0, (String) null, b.a(l).e());
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(b.a(l).e());
                            PushMessageHelper.a(l, PushMessageHelper.a(fi.COMMAND_REGISTER.f70a, arrayList, 0, (String) null, (String) null));
                        }
                        aw.a(l).a();
                        if (b.a(l).a()) {
                            in inVar = new in();
                            inVar.b(b.a(l).c());
                            inVar.c("client_info_update");
                            inVar.a(ak.a());
                            inVar.f177a = new HashMap();
                            inVar.f177a.put("app_version", g.a(l, l.getPackageName()));
                            inVar.f177a.put(Constants.c, Integer.toString(g.b(l, l.getPackageName())));
                            inVar.f177a.put("push_sdk_vn", "3_7_2");
                            inVar.f177a.put("push_sdk_vc", Integer.toString(30702));
                            String g3 = b.a(l).g();
                            if (!TextUtils.isEmpty(g3)) {
                                inVar.f177a.put("deviceid", g3);
                            }
                            aw.a(l).a(inVar, ho.Notification, false, (ib) null);
                        }
                        if (!n.a(l, "update_devId", false)) {
                            d();
                            n.b(l, "update_devId", true);
                        }
                        String d2 = i.d(l);
                        if (!TextUtils.isEmpty(d2)) {
                            ii iiVar = new ii();
                            iiVar.a(ak.a());
                            iiVar.b(str);
                            iiVar.c(fi.COMMAND_CHK_VDEVID.f70a);
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(i.c(l));
                            arrayList2.add(d2);
                            arrayList2.add(Build.MODEL != null ? Build.MODEL : "");
                            arrayList2.add(Build.BOARD != null ? Build.BOARD : "");
                            iiVar.a((List<String>) arrayList2);
                            aw.a(l).a(iiVar, ho.Command, false, (ib) null);
                        }
                        if (a(l) && D(l)) {
                            in inVar2 = new in();
                            inVar2.b(b.a(l).c());
                            inVar2.c(hy.PullOfflineMessage.f114a);
                            inVar2.a(ak.a());
                            inVar2.a(false);
                            aw.a(l).a(inVar2, ho.Notification, false, (ib) null, false);
                            C(l);
                        }
                    }
                    E(l);
                    b();
                    B(l);
                    G(l);
                    bc.a(l);
                    e();
                    if (!l.getPackageName().equals(c.f23036a)) {
                        if (Logger.a() != null) {
                            Logger.a(l, Logger.a());
                        }
                        b.a(2);
                    }
                    H(context);
                    return;
                }
                aw.a(l).a();
                b.a("Could not send  register message within 5s repeatly .");
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }

    protected static void b(Context context, String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        fi fiVar;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!fi.COMMAND_SET_ALIAS.f70a.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - l(context, str2)) >= 86400000) {
            if (fi.COMMAND_UNSET_ALIAS.f70a.equalsIgnoreCase(str) && l(context, str2) < 0) {
                sb = new StringBuilder();
                str4 = "Don't cancel alias for ";
            } else if (!fi.COMMAND_SET_ACCOUNT.f70a.equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - k(context, str2)) >= 3600000) {
                if (!fi.COMMAND_UNSET_ACCOUNT.f70a.equalsIgnoreCase(str) || k(context, str2) >= 0) {
                    a(context, str, (ArrayList<String>) arrayList, str3);
                    return;
                } else {
                    sb = new StringBuilder();
                    str4 = "Don't cancel account for ";
                }
            } else if (1 != PushMessageHelper.a(context)) {
                fiVar = fi.COMMAND_SET_ACCOUNT;
                PushMessageHelper.a(context, PushMessageHelper.a(fiVar.f70a, arrayList, 0, (String) null, str3));
                return;
            }
            sb.append(str4);
            sb.append(bf.a(arrayList.toString(), 3));
            sb.append(" is unseted");
            b.a(sb.toString());
            return;
        } else if (1 != PushMessageHelper.a(context)) {
            fiVar = fi.COMMAND_SET_ALIAS;
            PushMessageHelper.a(context, PushMessageHelper.a(fiVar.f70a, arrayList, 0, (String) null, str3));
            return;
        }
        PushMessageHandler.a(context, str3, str, 0, (String) null, arrayList);
    }

    public static List<String> c(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("topic_") && !next.contains("**ALL**")) {
                arrayList.add(next.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    public static void c(Context context, String str) {
        a(context, 0, 0, 23, 59, str);
    }

    public static void c(Context context, String str, String str2) {
        b(context, fi.COMMAND_UNSET_ALIAS.f70a, str, str2);
    }

    private static boolean c() {
        return l.b();
    }

    public static List<String> d(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String next : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (next.startsWith("account_")) {
                arrayList.add(next.substring("account_".length()));
            }
        }
        return arrayList;
    }

    private static void d() {
        new Thread(new ae()).start();
    }

    static synchronized void d(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void d(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            b(context, fi.COMMAND_SET_ACCOUNT.f70a, str, str2);
        }
    }

    private static void e() {
        boolean a2 = ah.a(l).a(ht.ForceHandleCrashSwitch.a(), false);
        if (!k && a2) {
            Thread.setDefaultUncaughtExceptionHandler(new v(l));
        }
    }

    protected static void e(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    static synchronized void e(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("alias_" + str).commit();
        }
    }

    public static void e(Context context, String str, String str2) {
        b(context, fi.COMMAND_UNSET_ACCOUNT.f70a, str, str2);
    }

    public static void f(Context context) {
        aw.a(context).f();
    }

    static synchronized void f(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void f(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(b.a(context).c()) && !TextUtils.isEmpty(str)) {
            if (Math.abs(System.currentTimeMillis() - j(context, str)) > 86400000) {
                is isVar = new is();
                isVar.a(ak.a());
                isVar.b(b.a(context).c());
                isVar.c(str);
                isVar.d(context.getPackageName());
                isVar.e(str2);
                aw.a(context).a(isVar, ho.Subscription, (ib) null);
            } else if (1 == PushMessageHelper.a(context)) {
                PushMessageHandler.a(context, str2, 0, (String) null, str);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.a(context, PushMessageHelper.a(fi.COMMAND_SUBSCRIBE_TOPIC.f70a, arrayList, 0, (String) null, (String) null));
            }
        }
    }

    public static void g(Context context) {
        h.e(context);
        ah.a(context).a();
        if (b.a(context).b()) {
            iu iuVar = new iu();
            iuVar.a(ak.a());
            iuVar.b(b.a(context).c());
            iuVar.c(b.a(context).e());
            iuVar.e(b.a(context).d());
            iuVar.d(context.getPackageName());
            aw.a(context).a(iuVar);
            PushMessageHandler.a();
            b.a(context).k();
            f(context);
            n(context);
            e(context);
        }
    }

    static synchronized void g(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("account_" + str).commit();
        }
    }

    public static void g(Context context, String str, String str2) {
        if (b.a(context).b()) {
            if (j(context, str) < 0) {
                b.a("Don't cancel subscribe for " + str + " is unsubscribed");
                return;
            }
            iw iwVar = new iw();
            iwVar.a(ak.a());
            iwVar.b(b.a(context).c());
            iwVar.c(str);
            iwVar.d(context.getPackageName());
            iwVar.e(str2);
            aw.a(context).a(iwVar, ho.UnSubscription, (ib) null);
        }
    }

    public static void h(Context context) {
        aw.a(context).a(true);
    }

    static synchronized void h(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void h(Context context, String str, String str2) {
        aw.a(context).a(str, str2);
    }

    public static void i(Context context) {
        aw.a(context).a(false);
    }

    static synchronized void i(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("topic_" + str).commit();
        }
    }

    static synchronized void i(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString(Constants.h, str + "," + str2);
            r.a(edit);
        }
    }

    public static long j(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("topic_" + str, -1);
    }

    public static void j(Context context) {
        aw.a(context).a((String) null, bb.UPLOAD_HUAWEI_TOKEN, d.ASSEMBLE_PUSH_HUAWEI);
    }

    private static boolean j(Context context, String str, String str2) {
        String y = y(context);
        return TextUtils.equals(y, str + "," + str2);
    }

    public static long k(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("account_" + str, -1);
    }

    public static void k(Context context) {
        aw.a(context).a((String) null, bb.UPLOAD_FCM_TOKEN, d.ASSEMBLE_PUSH_FCM);
    }

    public static long l(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("alias_" + str, -1);
    }

    public static void l(Context context) {
        aw.a(context).a((String) null, bb.UPLOAD_COS_TOKEN, d.ASSEMBLE_PUSH_COS);
    }

    public static void m(Context context) {
        aw.a(context).a((String) null, bb.UPLOAD_FTOS_TOKEN, d.ASSEMBLE_PUSH_FTOS);
    }

    public static void n(Context context) {
        aw.a(context).a(-1);
    }

    public static String o(Context context) {
        if (b.a(context).j()) {
            return b.a(context).e();
        }
        return null;
    }

    public static String p(Context context) {
        if (b.a(context).j()) {
            return b.a(context).h();
        }
        return null;
    }

    protected static boolean q(Context context) {
        a((Object) context, "context");
        return e.a(context).d(d.ASSEMBLE_PUSH_HUAWEI);
    }

    protected static boolean r(Context context) {
        a((Object) context, "context");
        return e.a(context).d(d.ASSEMBLE_PUSH_FCM);
    }

    protected static boolean s(Context context) {
        a((Object) context, "context");
        return e.a(context).d(d.ASSEMBLE_PUSH_COS);
    }

    protected static boolean t(Context context) {
        return e.a(context).d(d.ASSEMBLE_PUSH_FTOS);
    }

    static synchronized void u(Context context) {
        synchronized (MiPushClient.class) {
            for (String e2 : b(context)) {
                e(context, e2);
            }
        }
    }

    static synchronized void v(Context context) {
        synchronized (MiPushClient.class) {
            for (String g2 : d(context)) {
                g(context, g2);
            }
        }
    }

    static synchronized void w(Context context) {
        synchronized (MiPushClient.class) {
            for (String i2 : c(context)) {
                i(context, i2);
            }
        }
    }

    static synchronized void x(Context context) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove(Constants.h);
            r.a(edit);
        }
    }

    protected static String y(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString(Constants.h, "00:00-23:59");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (com.xiaomi.push.au.a(r5).a() == false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005c, code lost:
        if (android.text.TextUtils.isEmpty(r2) != false) goto L_0x005f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean z(android.content.Context r5) {
        /*
            r0 = 1
            if (r5 == 0) goto L_0x005f
            boolean r1 = com.xiaomi.push.l.a()
            if (r1 != 0) goto L_0x0060
            java.lang.String r1 = "com.xiaomi.xmsf"
            java.lang.String r2 = r5.getPackageName()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0016
            goto L_0x0060
        L_0x0016:
            java.lang.String r1 = com.xiaomi.push.i.b((android.content.Context) r5)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0021
            goto L_0x0060
        L_0x0021:
            android.content.pm.ApplicationInfo r1 = r5.getApplicationInfo()
            int r1 = r1.targetSdkVersion
            r2 = 23
            if (r1 < r2) goto L_0x004a
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r2) goto L_0x004a
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            boolean r1 = com.xiaomi.push.m.a(r5, r1)
            if (r1 != 0) goto L_0x0060
            java.lang.String r1 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r1 = com.xiaomi.push.m.a(r5, r1)
            if (r1 != 0) goto L_0x0060
            com.xiaomi.push.au r1 = com.xiaomi.push.au.a((android.content.Context) r5)
            boolean r1 = r1.a()
            if (r1 == 0) goto L_0x005f
            goto L_0x0060
        L_0x004a:
            java.lang.String r1 = com.xiaomi.push.i.f(r5)
            java.lang.String r2 = com.xiaomi.push.i.a()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0060
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r0 = 0
        L_0x0060:
            if (r0 != 0) goto L_0x00bb
            java.lang.String r1 = "Because of lack of necessary information, mi push can't be initialized"
            com.xiaomi.channel.commonutils.logger.b.d(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = com.xiaomi.push.m.a(r5, r2)
            if (r2 != 0) goto L_0x0079
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            r1.add(r2)
        L_0x0079:
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r2 = com.xiaomi.push.m.a(r5, r2)
            if (r2 != 0) goto L_0x0086
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            r1.add(r2)
        L_0x0086:
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x00bb
            int r2 = r1.size()
            java.lang.String[] r2 = new java.lang.String[r2]
            r1.toArray(r2)
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            java.lang.String r3 = "com.xiaomi.mipush.ERROR"
            r1.setAction(r3)
            java.lang.String r3 = r5.getPackageName()
            r1.setPackage(r3)
            java.lang.String r3 = "message_type"
            r4 = 5
            r1.putExtra(r3, r4)
            java.lang.String r3 = "error_type"
            java.lang.String r4 = "error_lack_of_permission"
            r1.putExtra(r3, r4)
            java.lang.String r3 = "error_message"
            r1.putExtra(r3, r2)
            r5.sendBroadcast(r1)
        L_0x00bb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.MiPushClient.z(android.content.Context):boolean");
    }
}
