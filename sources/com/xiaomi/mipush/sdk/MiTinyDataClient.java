package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ho;
import com.xiaomi.push.hs;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.service.ak;
import com.xiaomi.push.service.bf;
import com.xiaomi.stat.c.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MiTinyDataClient {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11516a = "com.xiaomi.xmpushsdk.tinydataPending.appId";
    public static final String b = "com.xiaomi.xmpushsdk.tinydataPending.init";
    public static final String c = "com.xiaomi.xmpushsdk.tinydataPending.channel";

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static a f11517a;
        /* access modifiers changed from: private */
        public Context b;
        private String c;
        private Boolean d;
        private C0084a e = new C0084a();
        private final ArrayList<hs> f = new ArrayList<>();

        /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a$a  reason: collision with other inner class name */
        public class C0084a {

            /* renamed from: a  reason: collision with root package name */
            public final ArrayList<hs> f11518a = new ArrayList<>();
            private ScheduledThreadPoolExecutor c = new ScheduledThreadPoolExecutor(1);
            /* access modifiers changed from: private */
            public ScheduledFuture<?> d;
            private final Runnable e = new ak(this);

            public C0084a() {
            }

            /* access modifiers changed from: private */
            public void a() {
                if (this.d == null) {
                    this.d = this.c.scheduleAtFixedRate(this.e, 1000, 1000, TimeUnit.MILLISECONDS);
                }
            }

            /* access modifiers changed from: private */
            public void b() {
                hs remove = this.f11518a.remove(0);
                for (in a2 : bf.a(Arrays.asList(new hs[]{remove}), a.this.b.getPackageName(), b.a(a.this.b).c(), 30720)) {
                    b.c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + remove.d());
                    aw.a(a.this.b).a(a2, ho.Notification, true, (ib) null);
                }
            }

            public void a(hs hsVar) {
                this.c.execute(new aj(this, hsVar));
            }
        }

        public static a a() {
            if (f11517a == null) {
                synchronized (a.class) {
                    if (f11517a == null) {
                        f11517a = new a();
                    }
                }
            }
            return f11517a;
        }

        private void b(hs hsVar) {
            synchronized (this.f) {
                if (!this.f.contains(hsVar)) {
                    this.f.add(hsVar);
                    if (this.f.size() > 100) {
                        this.f.remove(0);
                    }
                }
            }
        }

        private boolean b(Context context) {
            if (!aw.a(context).c()) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(c.f23036a, 4);
                return packageInfo != null && packageInfo.versionCode >= 108;
            } catch (Exception unused) {
                return false;
            }
        }

        private boolean c(Context context) {
            return b.a(context).c() == null && !b(this.b);
        }

        private boolean c(hs hsVar) {
            if (bf.a(hsVar, false)) {
                return false;
            }
            if (this.d.booleanValue()) {
                b.c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + hsVar.d());
                aw.a(this.b).a(hsVar);
                return true;
            }
            this.e.a(hsVar);
            return true;
        }

        public void a(Context context) {
            if (context == null) {
                b.a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.b = context;
            this.d = Boolean.valueOf(b(context));
            b(MiTinyDataClient.b);
        }

        public synchronized void a(String str) {
            if (TextUtils.isEmpty(str)) {
                b.a("channel is null, MiTinyDataClientImp.setChannel(String) failed.");
                return;
            }
            this.c = str;
            b(MiTinyDataClient.c);
        }

        public synchronized boolean a(hs hsVar) {
            String str;
            boolean z = false;
            if (hsVar == null) {
                return false;
            }
            if (bf.a(hsVar, true)) {
                return false;
            }
            boolean z2 = TextUtils.isEmpty(hsVar.a()) && TextUtils.isEmpty(this.c);
            boolean z3 = !b();
            if (this.b == null || c(this.b)) {
                z = true;
            }
            if (!z3 && !z2) {
                if (!z) {
                    b.c("MiTinyDataClient Send item immediately." + hsVar.d());
                    if (TextUtils.isEmpty(hsVar.d())) {
                        hsVar.f(ak.a());
                    }
                    if (TextUtils.isEmpty(hsVar.a())) {
                        hsVar.a(this.c);
                    }
                    if (TextUtils.isEmpty(hsVar.c())) {
                        hsVar.e(this.b.getPackageName());
                    }
                    if (hsVar.a() <= 0) {
                        hsVar.b(System.currentTimeMillis());
                    }
                    return c(hsVar);
                }
            }
            if (z2) {
                str = "MiTinyDataClient Pending " + hsVar.b() + " reason is " + MiTinyDataClient.c;
            } else if (z3) {
                str = "MiTinyDataClient Pending " + hsVar.b() + " reason is " + MiTinyDataClient.b;
            } else {
                if (z) {
                    str = "MiTinyDataClient Pending " + hsVar.b() + " reason is " + MiTinyDataClient.f11516a;
                }
                b(hsVar);
                return true;
            }
            b.c(str);
            b(hsVar);
            return true;
        }

        public void b(String str) {
            b.c("MiTinyDataClient.processPendingList(" + str + Operators.BRACKET_END_STR);
            ArrayList arrayList = new ArrayList();
            synchronized (this.f) {
                arrayList.addAll(this.f);
                this.f.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a((hs) it.next());
            }
        }

        public boolean b() {
            return this.b != null;
        }
    }

    public static void a(Context context, String str) {
        if (context == null) {
            b.a("context is null, MiTinyDataClient.init(Context, String) failed.");
            return;
        }
        a.a().a(context);
        if (TextUtils.isEmpty(str)) {
            b.a("channel is null or empty, MiTinyDataClient.init(Context, String) failed.");
        } else {
            a.a().a(str);
        }
    }

    public static boolean a(Context context, hs hsVar) {
        b.c("MiTinyDataClient.upload " + hsVar.d());
        if (!a.a().b()) {
            a.a().a(context);
        }
        return a.a().a(hsVar);
    }

    public static boolean a(Context context, String str, String str2, long j, String str3) {
        hs hsVar = new hs();
        hsVar.d(str);
        hsVar.c(str2);
        hsVar.a(j);
        hsVar.b(str3);
        hsVar.a(true);
        hsVar.a("push_sdk_channel");
        return a(context, hsVar);
    }

    public static boolean a(String str, String str2, long j, String str3) {
        hs hsVar = new hs();
        hsVar.d(str);
        hsVar.c(str2);
        hsVar.a(j);
        hsVar.b(str3);
        return a.a().a(hsVar);
    }
}
