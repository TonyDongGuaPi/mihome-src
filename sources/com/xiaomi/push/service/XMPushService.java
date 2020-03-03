package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.service.local.rpc.IRpcException;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.ab;
import com.xiaomi.push.ai;
import com.xiaomi.push.ao;
import com.xiaomi.push.az;
import com.xiaomi.push.be;
import com.xiaomi.push.db;
import com.xiaomi.push.dk;
import com.xiaomi.push.es;
import com.xiaomi.push.ew;
import com.xiaomi.push.fd;
import com.xiaomi.push.fe;
import com.xiaomi.push.fn;
import com.xiaomi.push.fs;
import com.xiaomi.push.fu;
import com.xiaomi.push.fv;
import com.xiaomi.push.fx;
import com.xiaomi.push.fy;
import com.xiaomi.push.fz;
import com.xiaomi.push.ga;
import com.xiaomi.push.gf;
import com.xiaomi.push.gh;
import com.xiaomi.push.gk;
import com.xiaomi.push.gl;
import com.xiaomi.push.gz;
import com.xiaomi.push.hg;
import com.xiaomi.push.hi;
import com.xiaomi.push.hj;
import com.xiaomi.push.hm;
import com.xiaomi.push.hn;
import com.xiaomi.push.ho;
import com.xiaomi.push.ht;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.io;
import com.xiaomi.push.iy;
import com.xiaomi.push.je;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.g;
import com.xiaomi.push.service.l;
import com.xiaomi.push.t;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMPushService extends Service implements fx {

    /* renamed from: a  reason: collision with root package name */
    public static int f12846a = 1;
    private static final int d = Process.myPid();

    /* renamed from: a  reason: collision with other field name */
    private long f229a = 0;

    /* renamed from: a  reason: collision with other field name */
    private ContentObserver f230a;

    /* renamed from: a  reason: collision with other field name */
    Messenger f231a = null;

    /* renamed from: a  reason: collision with other field name */
    private fs f232a;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with other field name */
    public fu f233a;

    /* renamed from: a  reason: collision with other field name */
    private fv f234a;

    /* renamed from: a  reason: collision with other field name */
    private fz f235a = new bi(this);

    /* renamed from: a  reason: collision with other field name */
    private e f236a;

    /* renamed from: a  reason: collision with other field name */
    private p f237a;

    /* renamed from: a  reason: collision with other field name */
    private al f238a = null;

    /* renamed from: a  reason: collision with other field name */
    private aw f239a;

    /* renamed from: a  reason: collision with other field name */
    private d f240a;

    /* renamed from: a  reason: collision with other field name */
    private g f241a = null;

    /* renamed from: a  reason: collision with other field name */
    protected Class f242a = XMJobService.class;

    /* renamed from: a  reason: collision with other field name */
    private String f243a;

    /* renamed from: a  reason: collision with other field name */
    private ArrayList<l> f244a = new ArrayList<>();

    /* renamed from: a  reason: collision with other field name */
    private Collection<af> f245a = Collections.synchronizedCollection(new ArrayList());
    private int b = 0;

    /* renamed from: b  reason: collision with other field name */
    private ContentObserver f246b;
    private int c = 0;

    class a extends i {

        /* renamed from: a  reason: collision with other field name */
        am.b f247a = null;

        public a(am.b bVar) {
            super(9);
            this.f247a = bVar;
        }

        public String a() {
            return "bind the client. " + this.f247a.g;
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m273a() {
            String str;
            try {
                if (!XMPushService.this.c()) {
                    com.xiaomi.channel.commonutils.logger.b.d("trying bind while the connection is not created, quit!");
                    return;
                }
                am.b a2 = am.a().a(this.f247a.g, this.f247a.f293b);
                if (a2 == null) {
                    str = "ignore bind because the channel " + this.f247a.g + " is removed ";
                } else if (a2.f288a == am.c.unbind) {
                    a2.a(am.c.binding, 0, 0, (String) null, (String) null);
                    XMPushService.a(XMPushService.this).a(a2);
                    hi.a(XMPushService.this, a2);
                    return;
                } else {
                    str = "trying duplicate bind, ingore! " + a2.f288a;
                }
                com.xiaomi.channel.commonutils.logger.b.a(str);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                XMPushService.this.a(10, e);
            } catch (Throwable unused) {
            }
        }
    }

    static class b extends i {

        /* renamed from: a  reason: collision with root package name */
        private final am.b f12848a;

        public b(am.b bVar) {
            super(12);
            this.f12848a = bVar;
        }

        public String a() {
            return "bind time out. chid=" + this.f12848a.g;
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m274a() {
            this.f12848a.a(am.c.unbind, 1, 21, (String) null, (String) null);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            return TextUtils.equals(((b) obj).f12848a.g, this.f12848a.g);
        }

        public int hashCode() {
            return this.f12848a.g.hashCode();
        }
    }

    class c extends i {

        /* renamed from: a  reason: collision with root package name */
        private fn f12849a = null;

        public c(fn fnVar) {
            super(8);
            this.f12849a = fnVar;
        }

        public String a() {
            return "receive a message.";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m275a() {
            XMPushService.a(XMPushService.this).a(this.f12849a);
        }
    }

    public class d extends i {
        d() {
            super(1);
        }

        public String a() {
            return "do reconnect..";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m276a() {
            if (XMPushService.this.a()) {
                XMPushService.this.f();
            } else {
                com.xiaomi.channel.commonutils.logger.b.a("should not connect. quit the job.");
            }
        }
    }

    class e extends BroadcastReceiver {
        e() {
        }

        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, XMPushService.f12846a);
        }
    }

    public class f extends i {

        /* renamed from: a  reason: collision with other field name */
        public Exception f249a;
        public int b;

        f(int i, Exception exc) {
            super(2);
            this.b = i;
            this.f249a = exc;
        }

        public String a() {
            return "disconnect the connection.";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m277a() {
            XMPushService.this.a(this.b, this.f249a);
        }
    }

    class g extends i {
        g() {
            super(65535);
        }

        public String a() {
            return "Init Job";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m278a() {
            XMPushService.b(XMPushService.this);
        }
    }

    class h extends i {

        /* renamed from: a  reason: collision with root package name */
        private Intent f12854a = null;

        public h(Intent intent) {
            super(15);
            this.f12854a = intent;
        }

        public String a() {
            return "Handle intent action = " + this.f12854a.getAction();
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m279a() {
            XMPushService.this.c(this.f12854a);
        }
    }

    public static abstract class i extends g.b {
        public i(int i) {
            super(i);
        }

        public abstract String a();

        /* renamed from: a  reason: collision with other method in class */
        public abstract void m280a();

        public void run() {
            if (!(this.f12924a == 4 || this.f12924a == 8)) {
                com.xiaomi.channel.commonutils.logger.b.a("JOB: " + a());
            }
            a();
        }
    }

    class j extends i {
        public j() {
            super(5);
        }

        public String a() {
            return "ask the job queue to quit";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m281a() {
            XMPushService.a(XMPushService.this).a();
        }
    }

    class k extends i {

        /* renamed from: a  reason: collision with root package name */
        private gl f12856a = null;

        public k(gl glVar) {
            super(8);
            this.f12856a = glVar;
        }

        public String a() {
            return "receive a message.";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m282a() {
            XMPushService.a(XMPushService.this).a(this.f12856a);
        }
    }

    public interface l {
        void a();
    }

    class m extends i {

        /* renamed from: a  reason: collision with other field name */
        boolean f252a;

        public m(boolean z) {
            super(4);
            this.f252a = z;
        }

        public String a() {
            return "send ping..";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m283a() {
            if (XMPushService.this.c()) {
                try {
                    if (!this.f252a) {
                        hi.a();
                    }
                    XMPushService.a(XMPushService.this).b(this.f252a);
                } catch (gf e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    XMPushService.this.a(10, (Exception) e);
                }
            }
        }
    }

    class n extends i {

        /* renamed from: a  reason: collision with other field name */
        am.b f253a = null;

        public n(am.b bVar) {
            super(4);
            this.f253a = bVar;
        }

        public String a() {
            return "rebind the client. " + this.f253a.g;
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m284a() {
            try {
                this.f253a.a(am.c.unbind, 1, 16, (String) null, (String) null);
                XMPushService.a(XMPushService.this).a(this.f253a.g, this.f253a.f293b);
                this.f253a.a(am.c.binding, 1, 16, (String) null, (String) null);
                XMPushService.a(XMPushService.this).a(this.f253a);
            } catch (gf e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                XMPushService.this.a(10, (Exception) e);
            }
        }
    }

    class o extends i {
        o() {
            super(3);
        }

        public String a() {
            return "reset the connection.";
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m285a() {
            XMPushService.this.a(11, (Exception) null);
            if (XMPushService.this.a()) {
                XMPushService.this.f();
            }
        }
    }

    class p extends BroadcastReceiver {
        p() {
        }

        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    class q extends i {

        /* renamed from: a  reason: collision with other field name */
        am.b f254a = null;

        /* renamed from: a  reason: collision with other field name */
        String f255a;
        int b;

        /* renamed from: b  reason: collision with other field name */
        String f256b;

        public q(am.b bVar, int i, String str, String str2) {
            super(9);
            this.f254a = bVar;
            this.b = i;
            this.f255a = str;
            this.f256b = str2;
        }

        public String a() {
            return "unbind the channel. " + this.f254a.g;
        }

        /* renamed from: a  reason: collision with other method in class */
        public void m286a() {
            if (!(this.f254a.f288a == am.c.unbind || XMPushService.a(XMPushService.this) == null)) {
                try {
                    XMPushService.a(XMPushService.this).a(this.f254a.g, this.f254a.f293b);
                } catch (gf e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    XMPushService.this.a(10, (Exception) e);
                }
            }
            this.f254a.a(am.c.unbind, this.b, 0, this.f256b, this.f255a);
        }
    }

    static {
        db.a("cn.app.chat.xiaomi.net", "cn.app.chat.xiaomi.net");
    }

    @TargetApi(11)
    public static Notification a(Context context) {
        Intent intent = new Intent(context, XMPushService.class);
        if (Build.VERSION.SDK_INT >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(context.getApplicationInfo().icon);
            builder.setContentTitle("Push Service");
            builder.setContentText("Push Service");
            builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
            return builder.getNotification();
        }
        Notification notification = new Notification();
        PendingIntent service = PendingIntent.getService(context, 0, intent, 0);
        try {
            notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, "Push Service", "Push Service", service});
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
        }
        return notification;
    }

    private gl a(gl glVar, String str, String str2) {
        StringBuilder sb;
        String str3;
        am a2 = am.a();
        List a3 = a2.a(str);
        if (a3.isEmpty()) {
            sb = new StringBuilder();
            str3 = "open channel should be called first before sending a packet, pkg=";
        } else {
            glVar.o(str);
            str = glVar.l();
            if (TextUtils.isEmpty(str)) {
                str = (String) a3.get(0);
                glVar.l(str);
            }
            am.b a4 = a2.a(str, glVar.n());
            if (!c()) {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not connected, chid=";
            } else if (a4 == null || a4.f288a != am.c.binded) {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not opened, chid=";
            } else if (TextUtils.equals(str2, a4.i)) {
                return glVar;
            } else {
                sb = new StringBuilder();
                sb.append("invalid session. ");
                sb.append(str2);
                com.xiaomi.channel.commonutils.logger.b.a(sb.toString());
                return null;
            }
        }
        sb.append(str3);
        sb.append(str);
        com.xiaomi.channel.commonutils.logger.b.a(sb.toString());
        return null;
    }

    private am.b a(String str, Intent intent) {
        am.b a2 = am.a().a(str, intent.getStringExtra(aq.p));
        if (a2 == null) {
            a2 = new am.b(this);
        }
        a2.g = intent.getStringExtra(aq.r);
        a2.f293b = intent.getStringExtra(aq.p);
        a2.c = intent.getStringExtra(aq.t);
        a2.f290a = intent.getStringExtra(aq.z);
        a2.e = intent.getStringExtra(aq.x);
        a2.f = intent.getStringExtra(aq.y);
        a2.f292a = intent.getBooleanExtra(aq.w, false);
        a2.h = intent.getStringExtra(aq.v);
        a2.i = intent.getStringExtra(aq.C);
        a2.d = intent.getStringExtra(aq.u);
        a2.f289a = this.f240a;
        a2.a((Messenger) intent.getParcelableExtra(aq.G));
        a2.f282a = getApplicationContext();
        am.a().a(a2);
        return a2;
    }

    private String a() {
        String str;
        ao.a();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = new Object();
        String str2 = null;
        if (com.xiaomi.stat.c.c.f23036a.equals(getPackageName())) {
            at a2 = at.a(this);
            str = null;
            while (true) {
                if (!TextUtils.isEmpty(str) && a2.a() != 0) {
                    break;
                }
                if (TextUtils.isEmpty(str)) {
                    str = com.xiaomi.push.l.a("ro.miui.region");
                    if (TextUtils.isEmpty(str)) {
                        str = com.xiaomi.push.l.a("ro.product.locale.region");
                    }
                }
                try {
                    synchronized (obj) {
                        obj.wait(100);
                    }
                } catch (InterruptedException unused) {
                }
            }
        } else {
            str = com.xiaomi.push.l.f();
        }
        if (!TextUtils.isEmpty(str)) {
            a.a(getApplicationContext()).b(str);
            str2 = com.xiaomi.push.l.b(str).name();
        }
        com.xiaomi.channel.commonutils.logger.b.a("wait region :" + str2 + " cost = " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return str2;
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e2) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
            }
        }
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra(aq.z);
        String stringExtra2 = intent.getStringExtra(aq.C);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        am a2 = am.a();
        fn fnVar = null;
        if (bundleExtra != null) {
            gk gkVar = (gk) a((gl) new gk(bundleExtra), stringExtra, stringExtra2);
            if (gkVar != null) {
                fnVar = fn.a((gl) gkVar, a2.a(gkVar.l(), gkVar.n()).h);
            } else {
                return;
            }
        } else {
            byte[] byteArrayExtra = intent.getByteArrayExtra("ext_raw_packet");
            if (byteArrayExtra != null) {
                long longExtra = intent.getLongExtra(aq.p, 0);
                String stringExtra3 = intent.getStringExtra(aq.q);
                String stringExtra4 = intent.getStringExtra("ext_chid");
                am.b a3 = a2.a(stringExtra4, Long.toString(longExtra));
                if (a3 != null) {
                    fn fnVar2 = new fn();
                    try {
                        fnVar2.a(Integer.parseInt(stringExtra4));
                    } catch (NumberFormatException unused) {
                    }
                    fnVar2.a(MIMCConstant.p, (String) null);
                    fnVar2.a(longExtra, "xiaomi.com", stringExtra3);
                    fnVar2.a(intent.getStringExtra("ext_pkt_id"));
                    fnVar2.a(byteArrayExtra, a3.h);
                    fnVar = fnVar2;
                }
            }
        }
        if (fnVar != null) {
            c((i) new ax(this, fnVar));
        }
    }

    private void a(Intent intent, int i2) {
        byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
        boolean booleanExtra = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        in inVar = new in();
        try {
            iy.a(inVar, byteArrayExtra);
            ai.a(getApplicationContext()).a((ai.a) new b(inVar, new WeakReference(this), booleanExtra), i2);
        } catch (je unused) {
            com.xiaomi.channel.commonutils.logger.b.d("aw_ping : send help app ping  error");
        }
    }

    private void a(String str, int i2) {
        Collection<am.b> a2 = am.a().a(str);
        if (a2 != null) {
            for (am.b bVar : a2) {
                if (bVar != null) {
                    a((i) new q(bVar, i2, (String) null, (String) null));
                }
            }
        }
        am.a().a(str);
    }

    /* renamed from: a  reason: collision with other method in class */
    private boolean m256a(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return false;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    /* renamed from: a  reason: collision with other method in class */
    private boolean m257a(String str, Intent intent) {
        am.b a2 = am.a().a(str, intent.getStringExtra(aq.p));
        boolean z = false;
        if (a2 == null || str == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(aq.C);
        String stringExtra2 = intent.getStringExtra(aq.v);
        if (!TextUtils.isEmpty(a2.i) && !TextUtils.equals(stringExtra, a2.i)) {
            com.xiaomi.channel.commonutils.logger.b.a("session changed. old session=" + a2.i + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(a2.h)) {
            return z;
        }
        com.xiaomi.channel.commonutils.logger.b.a("security changed. chid = " + str + " sechash = " + be.a(stringExtra2));
        return true;
    }

    /* renamed from: a  reason: collision with other method in class */
    private int[] m258a() {
        String[] split;
        String a2 = ah.a(getApplicationContext()).a(ht.FallDownTimeRange.a(), "");
        if (!TextUtils.isEmpty(a2) && (split = a2.split(",")) != null && split.length >= 2) {
            int[] iArr = new int[2];
            try {
                iArr[0] = Integer.valueOf(split[0]).intValue();
                iArr[1] = Integer.valueOf(split[1]).intValue();
                if (iArr[0] < 0 || iArr[0] > 23 || iArr[1] < 0 || iArr[1] > 23 || iArr[0] == iArr[1]) {
                    return null;
                }
                return iArr;
            } catch (NumberFormatException e2) {
                com.xiaomi.channel.commonutils.logger.b.d("parse falldown time range failure: " + e2);
            }
        }
        return null;
    }

    private void b(Intent intent) {
        String stringExtra = intent.getStringExtra(aq.z);
        String stringExtra2 = intent.getStringExtra(aq.C);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        gk[] gkVarArr = new gk[parcelableArrayExtra.length];
        intent.getBooleanExtra("ext_encrypt", true);
        int i2 = 0;
        while (i2 < parcelableArrayExtra.length) {
            gkVarArr[i2] = new gk((Bundle) parcelableArrayExtra[i2]);
            gkVarArr[i2] = (gk) a((gl) gkVarArr[i2], stringExtra, stringExtra2);
            if (gkVarArr[i2] != null) {
                i2++;
            } else {
                return;
            }
        }
        am a2 = am.a();
        fn[] fnVarArr = new fn[gkVarArr.length];
        for (int i3 = 0; i3 < gkVarArr.length; i3++) {
            gk gkVar = gkVarArr[i3];
            fnVarArr[i3] = fn.a((gl) gkVar, a2.a(gkVar.l(), gkVar.n()).h);
        }
        c((i) new c(this, fnVarArr));
    }

    private void b(boolean z) {
        this.f229a = System.currentTimeMillis();
        if (c()) {
            if (this.f233a.n() || this.f233a.o() || az.e(this)) {
                c((i) new m(z));
                return;
            }
            c((i) new f(17, (Exception) null));
        }
        a(true);
    }

    private void c() {
        String str;
        a a2 = a.a(getApplicationContext());
        String a3 = a2.a();
        com.xiaomi.channel.commonutils.logger.b.a("region of cache is " + a3);
        if (TextUtils.isEmpty(a3)) {
            a3 = a();
        }
        if (!TextUtils.isEmpty(a3)) {
            this.f243a = a3;
            a2.a(a3);
            if (com.xiaomi.push.o.Global.name().equals(this.f243a)) {
                str = "app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.Europe.name().equals(this.f243a)) {
                str = "fr.app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.Russia.name().equals(this.f243a)) {
                str = "ru.app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.India.name().equals(this.f243a)) {
                str = "idmb.app.chat.global.xiaomi.net";
            }
            fv.a(str);
        } else {
            this.f243a = com.xiaomi.push.o.China.name();
        }
        if (com.xiaomi.push.o.China.name().equals(this.f243a)) {
            fv.a("cn.app.chat.xiaomi.net");
        }
        if (h()) {
            bs bsVar = new bs(this, 11);
            a((i) bsVar);
            l.a((l.a) new bt(this, bsVar));
        }
        try {
            if (t.c()) {
                this.f240a.a((Context) this);
            }
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: com.xiaomi.push.service.XMPushService$n} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.xiaomi.push.service.XMPushService$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: com.xiaomi.push.service.bu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v136, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: com.xiaomi.push.service.am$b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v84, resolved type: com.xiaomi.push.service.XMPushService$o} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v98, resolved type: com.xiaomi.push.service.bu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v99, resolved type: com.xiaomi.push.service.bu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: com.xiaomi.push.service.bu} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v100, resolved type: com.xiaomi.push.service.bu} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Intent r12) {
        /*
            r11 = this;
            com.xiaomi.push.service.am r0 = com.xiaomi.push.service.am.a()
            java.lang.String r1 = com.xiaomi.push.service.aq.d
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r2)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x0740
            java.lang.String r1 = com.xiaomi.push.service.aq.j
            java.lang.String r5 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r5)
            if (r1 == 0) goto L_0x0021
            goto L_0x0740
        L_0x0021:
            java.lang.String r1 = com.xiaomi.push.service.aq.i
            java.lang.String r5 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r5)
            if (r1 == 0) goto L_0x0091
            java.lang.String r1 = com.xiaomi.push.service.aq.z
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r3 = com.xiaomi.push.service.aq.r
            java.lang.String r5 = r12.getStringExtra(r3)
            java.lang.String r3 = com.xiaomi.push.service.aq.p
            java.lang.String r6 = r12.getStringExtra(r3)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r3 = "Service called close channel chid = "
            r12.append(r3)
            r12.append(r5)
            java.lang.String r3 = " res = "
            r12.append(r3)
            java.lang.String r3 = com.xiaomi.push.service.am.b.a((java.lang.String) r6)
            r12.append(r3)
            java.lang.String r12 = r12.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            boolean r12 = android.text.TextUtils.isEmpty(r5)
            if (r12 == 0) goto L_0x007d
            java.util.List r12 = r0.a((java.lang.String) r1)
            java.util.Iterator r12 = r12.iterator()
        L_0x006d:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x07b8
            java.lang.Object r0 = r12.next()
            java.lang.String r0 = (java.lang.String) r0
            r11.a((java.lang.String) r0, (int) r2)
            goto L_0x006d
        L_0x007d:
            boolean r12 = android.text.TextUtils.isEmpty(r6)
            if (r12 == 0) goto L_0x0088
            r11.a((java.lang.String) r5, (int) r2)
            goto L_0x07b8
        L_0x0088:
            r7 = 2
            r8 = 0
            r9 = 0
            r4 = r11
            r4.a(r5, r6, r7, r8, r9)
            goto L_0x07b8
        L_0x0091:
            java.lang.String r1 = com.xiaomi.push.service.aq.e
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x00a2
            r11.a((android.content.Intent) r12)
            goto L_0x07b8
        L_0x00a2:
            java.lang.String r1 = com.xiaomi.push.service.aq.g
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x00b3
            r11.b((android.content.Intent) r12)
            goto L_0x07b8
        L_0x00b3:
            java.lang.String r1 = com.xiaomi.push.service.aq.f
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x00f8
            java.lang.String r1 = com.xiaomi.push.service.aq.z
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r2 = com.xiaomi.push.service.aq.C
            java.lang.String r2 = r12.getStringExtra(r2)
            java.lang.String r3 = "ext_packet"
            android.os.Bundle r12 = r12.getBundleExtra(r3)
            com.xiaomi.push.gj r3 = new com.xiaomi.push.gj
            r3.<init>(r12)
            com.xiaomi.push.gl r12 = r11.a((com.xiaomi.push.gl) r3, (java.lang.String) r1, (java.lang.String) r2)
            if (r12 == 0) goto L_0x07b8
            java.lang.String r1 = r12.l()
            java.lang.String r2 = r12.n()
            com.xiaomi.push.service.am$b r0 = r0.a((java.lang.String) r1, (java.lang.String) r2)
            java.lang.String r0 = r0.h
            com.xiaomi.push.fn r12 = com.xiaomi.push.fn.a((com.xiaomi.push.gl) r12, (java.lang.String) r0)
            com.xiaomi.push.service.ax r0 = new com.xiaomi.push.service.ax
            r0.<init>(r11, r12)
        L_0x00f3:
            r11.c((com.xiaomi.push.service.XMPushService.i) r0)
            goto L_0x07b8
        L_0x00f8:
            java.lang.String r1 = com.xiaomi.push.service.aq.h
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x0139
            java.lang.String r1 = com.xiaomi.push.service.aq.z
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r2 = com.xiaomi.push.service.aq.C
            java.lang.String r2 = r12.getStringExtra(r2)
            java.lang.String r3 = "ext_packet"
            android.os.Bundle r12 = r12.getBundleExtra(r3)
            com.xiaomi.push.gn r3 = new com.xiaomi.push.gn
            r3.<init>((android.os.Bundle) r12)
            com.xiaomi.push.gl r12 = r11.a((com.xiaomi.push.gl) r3, (java.lang.String) r1, (java.lang.String) r2)
            if (r12 == 0) goto L_0x07b8
            java.lang.String r1 = r12.l()
            java.lang.String r2 = r12.n()
            com.xiaomi.push.service.am$b r0 = r0.a((java.lang.String) r1, (java.lang.String) r2)
            java.lang.String r0 = r0.h
            com.xiaomi.push.fn r12 = com.xiaomi.push.fn.a((com.xiaomi.push.gl) r12, (java.lang.String) r0)
            com.xiaomi.push.service.ax r0 = new com.xiaomi.push.service.ax
            r0.<init>(r11, r12)
            goto L_0x00f3
        L_0x0139:
            java.lang.String r1 = com.xiaomi.push.service.aq.k
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x01a2
            java.lang.String r0 = com.xiaomi.push.service.aq.r
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = com.xiaomi.push.service.aq.p
            java.lang.String r1 = r12.getStringExtra(r1)
            if (r0 == 0) goto L_0x07b8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "request reset connection from chid = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r2)
            com.xiaomi.push.service.am r2 = com.xiaomi.push.service.am.a()
            com.xiaomi.push.service.am$b r0 = r2.a((java.lang.String) r0, (java.lang.String) r1)
            if (r0 == 0) goto L_0x07b8
            java.lang.String r1 = r0.h
            java.lang.String r2 = com.xiaomi.push.service.aq.v
            java.lang.String r12 = r12.getStringExtra(r2)
            boolean r12 = r1.equals(r12)
            if (r12 == 0) goto L_0x07b8
            com.xiaomi.push.service.am$c r12 = r0.f288a
            com.xiaomi.push.service.am$c r0 = com.xiaomi.push.service.am.c.binded
            if (r12 != r0) goto L_0x07b8
            com.xiaomi.push.fu r12 = r11.a()
            if (r12 == 0) goto L_0x0198
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 15000(0x3a98, double:7.411E-320)
            long r0 = r0 - r2
            boolean r12 = r12.a((long) r0)
            if (r12 != 0) goto L_0x07b8
        L_0x0198:
            com.xiaomi.push.service.XMPushService$o r12 = new com.xiaomi.push.service.XMPushService$o
            r12.<init>()
        L_0x019d:
            r11.c((com.xiaomi.push.service.XMPushService.i) r12)
            goto L_0x07b8
        L_0x01a2:
            java.lang.String r1 = com.xiaomi.push.service.aq.l
            java.lang.String r2 = r12.getAction()
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 == 0) goto L_0x0232
            java.lang.String r1 = com.xiaomi.push.service.aq.z
            java.lang.String r1 = r12.getStringExtra(r1)
            java.util.List r3 = r0.a((java.lang.String) r1)
            boolean r5 = r3.isEmpty()
            if (r5 == 0) goto L_0x01d4
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "open channel should be called first before update info, pkg="
            r12.append(r0)
            r12.append(r1)
            java.lang.String r12 = r12.toString()
        L_0x01d0:
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            return
        L_0x01d4:
            java.lang.String r1 = com.xiaomi.push.service.aq.r
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r5 = com.xiaomi.push.service.aq.p
            java.lang.String r5 = r12.getStringExtra(r5)
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 == 0) goto L_0x01ec
            java.lang.Object r1 = r3.get(r4)
            java.lang.String r1 = (java.lang.String) r1
        L_0x01ec:
            boolean r3 = android.text.TextUtils.isEmpty(r5)
            if (r3 == 0) goto L_0x020a
            java.util.Collection r0 = r0.a((java.lang.String) r1)
            if (r0 == 0) goto L_0x020e
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x020e
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r0 = r0.next()
            r2 = r0
            com.xiaomi.push.service.am$b r2 = (com.xiaomi.push.service.am.b) r2
            goto L_0x020e
        L_0x020a:
            com.xiaomi.push.service.am$b r2 = r0.a((java.lang.String) r1, (java.lang.String) r5)
        L_0x020e:
            if (r2 == 0) goto L_0x07b8
            java.lang.String r0 = com.xiaomi.push.service.aq.x
            boolean r0 = r12.hasExtra(r0)
            if (r0 == 0) goto L_0x0220
            java.lang.String r0 = com.xiaomi.push.service.aq.x
            java.lang.String r0 = r12.getStringExtra(r0)
            r2.e = r0
        L_0x0220:
            java.lang.String r0 = com.xiaomi.push.service.aq.y
            boolean r0 = r12.hasExtra(r0)
            if (r0 == 0) goto L_0x07b8
            java.lang.String r0 = com.xiaomi.push.service.aq.y
            java.lang.String r12 = r12.getStringExtra(r0)
            r2.f = r12
            goto L_0x07b8
        L_0x0232:
            java.lang.String r0 = "android.intent.action.SCREEN_ON"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06f3
            java.lang.String r0 = "android.intent.action.SCREEN_OFF"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x024c
            goto L_0x06f3
        L_0x024c:
            java.lang.String r0 = "com.xiaomi.mipush.REGISTER_APP"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x02ca
            android.content.Context r0 = r11.getApplicationContext()
            com.xiaomi.push.service.at r0 = com.xiaomi.push.service.at.a(r0)
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x028d
            android.content.Context r0 = r11.getApplicationContext()
            com.xiaomi.push.service.at r0 = com.xiaomi.push.service.at.a(r0)
            int r0 = r0.a()
            if (r0 != 0) goto L_0x028d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "register without being provisioned. "
            r0.append(r1)
            java.lang.String r1 = "mipush_app_package"
            java.lang.String r12 = r12.getStringExtra(r1)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            goto L_0x01d0
        L_0x028d:
            java.lang.String r0 = "mipush_payload"
            byte[] r9 = r12.getByteArrayExtra(r0)
            java.lang.String r0 = "mipush_app_package"
            java.lang.String r10 = r12.getStringExtra(r0)
            java.lang.String r0 = "mipush_env_chanage"
            boolean r0 = r12.getBooleanExtra(r0, r4)
            java.lang.String r1 = "mipush_env_type"
            int r8 = r12.getIntExtra(r1, r3)
            com.xiaomi.push.service.m r12 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r12.d(r10)
            if (r0 == 0) goto L_0x02c5
            java.lang.String r12 = "com.xiaomi.xmsf"
            java.lang.String r0 = r11.getPackageName()
            boolean r12 = r12.equals(r0)
            if (r12 != 0) goto L_0x02c5
            com.xiaomi.push.service.bu r12 = new com.xiaomi.push.service.bu
            r7 = 14
            r5 = r12
            r6 = r11
            r5.<init>(r6, r7, r8, r9, r10)
            goto L_0x019d
        L_0x02c5:
            r11.a((byte[]) r9, (java.lang.String) r10)
            goto L_0x07b8
        L_0x02ca:
            java.lang.String r0 = "com.xiaomi.mipush.SEND_MESSAGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06c9
            java.lang.String r0 = "com.xiaomi.mipush.UNREGISTER_APP"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x02e4
            goto L_0x06c9
        L_0x02e4:
            java.lang.String r0 = com.xiaomi.push.service.au.f12891a
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x03a2
            java.lang.String r0 = "uninstall_pkg_name"
            java.lang.String r12 = r12.getStringExtra(r0)
            if (r12 == 0) goto L_0x03a1
            java.lang.String r0 = r12.trim()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0304
            goto L_0x03a1
        L_0x0304:
            android.content.pm.PackageManager r0 = r11.getPackageManager()     // Catch:{ NameNotFoundException -> 0x030c }
            r0.getPackageInfo(r12, r4)     // Catch:{ NameNotFoundException -> 0x030c }
            r3 = 0
        L_0x030c:
            java.lang.String r0 = "com.xiaomi.channel"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x032f
            com.xiaomi.push.service.am r0 = com.xiaomi.push.service.am.a()
            java.lang.String r1 = "1"
            java.util.Collection r0 = r0.a((java.lang.String) r1)
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x032f
            if (r3 == 0) goto L_0x032f
            java.lang.String r12 = "1"
            r11.a((java.lang.String) r12, (int) r4)
            java.lang.String r12 = "close the miliao channel as the app is uninstalled."
            goto L_0x01d0
        L_0x032f:
            java.lang.String r0 = "pref_registered_pkg_names"
            android.content.SharedPreferences r0 = r11.getSharedPreferences(r0, r4)
            java.lang.String r1 = r0.getString(r12, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x07b8
            if (r3 == 0) goto L_0x07b8
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r0.remove(r12)
            r0.commit()
            boolean r0 = com.xiaomi.push.service.aa.b((android.content.Context) r11, (java.lang.String) r12)
            if (r0 == 0) goto L_0x0354
            com.xiaomi.push.service.aa.b((android.content.Context) r11, (java.lang.String) r12)
        L_0x0354:
            com.xiaomi.push.service.aa.a((android.content.Context) r11, (java.lang.String) r12)
            boolean r0 = r11.c()
            if (r0 == 0) goto L_0x07b8
            if (r1 == 0) goto L_0x07b8
            com.xiaomi.push.ik r0 = com.xiaomi.push.service.w.a((java.lang.String) r12, (java.lang.String) r1)     // Catch:{ gf -> 0x0381 }
            com.xiaomi.push.service.w.a((com.xiaomi.push.service.XMPushService) r11, (com.xiaomi.push.ik) r0)     // Catch:{ gf -> 0x0381 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ gf -> 0x0381 }
            r0.<init>()     // Catch:{ gf -> 0x0381 }
            java.lang.String r1 = "uninstall "
            r0.append(r1)     // Catch:{ gf -> 0x0381 }
            r0.append(r12)     // Catch:{ gf -> 0x0381 }
            java.lang.String r12 = " msg sent"
            r0.append(r12)     // Catch:{ gf -> 0x0381 }
            java.lang.String r12 = r0.toString()     // Catch:{ gf -> 0x0381 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)     // Catch:{ gf -> 0x0381 }
            goto L_0x07b8
        L_0x0381:
            r12 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Fail to send Message: "
            r0.append(r1)
            java.lang.String r1 = r12.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r0)
            r0 = 10
            r11.a((int) r0, (java.lang.Exception) r12)
            goto L_0x07b8
        L_0x03a1:
            return
        L_0x03a2:
            java.lang.String r0 = "com.xiaomi.mipush.CLEAR_NOTIFICATION"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x03da
            java.lang.String r0 = com.xiaomi.push.service.aq.z
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = com.xiaomi.push.service.aq.A
            r2 = -2
            int r1 = r12.getIntExtra(r1, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x07b8
            r2 = -1
            if (r1 < r2) goto L_0x03c9
            com.xiaomi.push.service.aa.a((android.content.Context) r11, (java.lang.String) r0, (int) r1)
            goto L_0x07b8
        L_0x03c9:
            java.lang.String r1 = com.xiaomi.push.service.aq.E
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r2 = com.xiaomi.push.service.aq.F
            java.lang.String r12 = r12.getStringExtra(r2)
            com.xiaomi.push.service.aa.a((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r12)
            goto L_0x07b8
        L_0x03da:
            java.lang.String r0 = "com.xiaomi.mipush.SET_NOTIFICATION_TYPE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0449
            java.lang.String r0 = com.xiaomi.push.service.aq.z
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = com.xiaomi.push.service.aq.D
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r2 = com.xiaomi.push.service.aq.B
            boolean r2 = r12.hasExtra(r2)
            if (r2 == 0) goto L_0x0415
            java.lang.String r2 = com.xiaomi.push.service.aq.B
            int r12 = r12.getIntExtra(r2, r4)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = com.xiaomi.push.be.b(r2)
            r3 = 0
            goto L_0x041a
        L_0x0415:
            java.lang.String r2 = com.xiaomi.push.be.b(r0)
            r12 = 0
        L_0x041a:
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x0433
            boolean r1 = android.text.TextUtils.equals(r1, r2)
            if (r1 != 0) goto L_0x0427
            goto L_0x0433
        L_0x0427:
            if (r3 == 0) goto L_0x042e
            com.xiaomi.push.service.aa.b((android.content.Context) r11, (java.lang.String) r0)
            goto L_0x07b8
        L_0x042e:
            com.xiaomi.push.service.aa.b(r11, r0, r12)
            goto L_0x07b8
        L_0x0433:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r1 = "invalid notification for "
            r12.append(r1)
            r12.append(r0)
            java.lang.String r12 = r12.toString()
        L_0x0444:
            com.xiaomi.channel.commonutils.logger.b.d(r12)
            goto L_0x07b8
        L_0x0449:
            java.lang.String r0 = "com.xiaomi.mipush.DISABLE_PUSH"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0481
            java.lang.String r0 = "mipush_app_package"
            java.lang.String r12 = r12.getStringExtra(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L_0x0468
            com.xiaomi.push.service.m r0 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r0.b((java.lang.String) r12)
        L_0x0468:
            java.lang.String r12 = "com.xiaomi.xmsf"
            java.lang.String r0 = r11.getPackageName()
            boolean r12 = r12.equals(r0)
            if (r12 != 0) goto L_0x07b8
            r12 = 19
            r11.a((int) r12, (java.lang.Exception) r2)
            r11.e()
            r11.stopSelf()
            goto L_0x07b8
        L_0x0481:
            java.lang.String r0 = "com.xiaomi.mipush.DISABLE_PUSH_MESSAGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0646
            java.lang.String r0 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x049b
            goto L_0x0646
        L_0x049b:
            java.lang.String r0 = "com.xiaomi.mipush.SEND_TINYDATA"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x04ca
            java.lang.String r0 = "mipush_app_package"
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = "mipush_payload"
            byte[] r12 = r12.getByteArrayExtra(r1)
            com.xiaomi.push.hs r1 = new com.xiaomi.push.hs
            r1.<init>()
            com.xiaomi.push.iy.a(r1, (byte[]) r12)     // Catch:{ je -> 0x04c4 }
            com.xiaomi.push.hm r12 = com.xiaomi.push.hm.a(r11)     // Catch:{ je -> 0x04c4 }
            r12.a((com.xiaomi.push.hs) r1, (java.lang.String) r0)     // Catch:{ je -> 0x04c4 }
            goto L_0x07b8
        L_0x04c4:
            r12 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r12)
            goto L_0x07b8
        L_0x04ca:
            java.lang.String r0 = "com.xiaomi.push.timer"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x04f9
            java.lang.String r12 = "Service called on timer"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            boolean r12 = r11.j()
            if (r12 == 0) goto L_0x04eb
            boolean r12 = com.xiaomi.push.fe.b()
            if (r12 == 0) goto L_0x07b8
            java.lang.String r12 = "enter falldown mode, stop alarm"
            goto L_0x0738
        L_0x04eb:
            com.xiaomi.push.fe.a((boolean) r4)
            boolean r12 = r11.e()
            if (r12 == 0) goto L_0x07b8
        L_0x04f4:
            r11.b((boolean) r4)
            goto L_0x07b8
        L_0x04f9:
            java.lang.String r0 = "com.xiaomi.push.check_alive"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x0511
            java.lang.String r12 = "Service called on check alive."
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            boolean r12 = r11.e()
            if (r12 == 0) goto L_0x07b8
            goto L_0x04f4
        L_0x0511:
            java.lang.String r0 = "com.xiaomi.mipush.thirdparty"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0542
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "on thirdpart push :"
            r0.append(r1)
            java.lang.String r1 = "com.xiaomi.mipush.thirdparty_DESC"
            java.lang.String r1 = r12.getStringExtra(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            java.lang.String r0 = "com.xiaomi.mipush.thirdparty_LEVEL"
            int r12 = r12.getIntExtra(r0, r4)
            com.xiaomi.push.fe.a(r11, r12)
            goto L_0x07b8
        L_0x0542:
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0553
            r11.d()
            goto L_0x07b8
        L_0x0553:
            java.lang.String r0 = "action_cr_config"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x05dc
            java.lang.String r0 = "action_cr_event_switch"
            boolean r0 = r12.getBooleanExtra(r0, r4)
            java.lang.String r1 = "action_cr_event_frequency"
            r5 = 86400(0x15180, double:4.26873E-319)
            long r1 = r12.getLongExtra(r1, r5)
            java.lang.String r7 = "action_cr_perf_switch"
            boolean r4 = r12.getBooleanExtra(r7, r4)
            java.lang.String r7 = "action_cr_perf_frequency"
            long r5 = r12.getLongExtra(r7, r5)
            java.lang.String r7 = "action_cr_event_en"
            boolean r3 = r12.getBooleanExtra(r7, r3)
            java.lang.String r7 = "action_cr_max_file_size"
            r8 = 1048576(0x100000, double:5.180654E-318)
            long r7 = r12.getLongExtra(r7, r8)
            com.xiaomi.clientreport.data.Config$Builder r12 = com.xiaomi.clientreport.data.Config.a()
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.b((boolean) r0)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.b((long) r1)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.c((boolean) r4)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.c((long) r5)
            android.content.Context r0 = r11.getApplicationContext()
            java.lang.String r0 = com.xiaomi.push.bl.a((android.content.Context) r0)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.a((java.lang.String) r0)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.a((boolean) r3)
            com.xiaomi.clientreport.data.Config$Builder r12 = r12.a((long) r7)
            android.content.Context r0 = r11.getApplicationContext()
            com.xiaomi.clientreport.data.Config r12 = r12.a((android.content.Context) r0)
            java.lang.String r0 = "com.xiaomi.xmsf"
            java.lang.String r3 = r11.getPackageName()
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x07b8
            r3 = 0
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x07b8
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x07b8
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x07b8
            android.content.Context r0 = r11.getApplicationContext()
            com.xiaomi.push.fc.a((android.content.Context) r0, (com.xiaomi.clientreport.data.Config) r12)
            goto L_0x07b8
        L_0x05dc:
            java.lang.String r0 = "action_help_ping"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0635
            java.lang.String r0 = "extra_help_ping_switch"
            boolean r0 = r12.getBooleanExtra(r0, r4)
            java.lang.String r1 = "extra_help_ping_frequency"
            int r1 = r12.getIntExtra(r1, r4)
            r2 = 30
            if (r1 < 0) goto L_0x0601
            if (r1 >= r2) goto L_0x0601
            java.lang.String r1 = "aw_ping: frquency need > 30s."
            com.xiaomi.channel.commonutils.logger.b.c(r1)
            r1 = 30
        L_0x0601:
            if (r1 >= 0) goto L_0x0604
            r0 = 0
        L_0x0604:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "aw_ping: receive a aw_ping message. switch: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = " frequency: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r2)
            if (r0 == 0) goto L_0x07b8
            if (r1 <= 0) goto L_0x07b8
            java.lang.String r0 = "com.xiaomi.xmsf"
            java.lang.String r2 = r11.getPackageName()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x07b8
            r11.a((android.content.Intent) r12, (int) r1)
            goto L_0x07b8
        L_0x0635:
            java.lang.String r0 = "action_aw_app_logic"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x07b8
            r11.d(r12)
            goto L_0x07b8
        L_0x0646:
            java.lang.String r0 = "mipush_app_package"
            java.lang.String r3 = r12.getStringExtra(r0)
            java.lang.String r0 = "mipush_payload"
            byte[] r6 = r12.getByteArrayExtra(r0)
            java.lang.String r0 = "mipush_app_id"
            java.lang.String r4 = r12.getStringExtra(r0)
            java.lang.String r0 = "mipush_app_token"
            java.lang.String r5 = r12.getStringExtra(r0)
            java.lang.String r0 = "com.xiaomi.mipush.DISABLE_PUSH_MESSAGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0671
            com.xiaomi.push.service.m r0 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r0.c((java.lang.String) r3)
        L_0x0671:
            java.lang.String r0 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x068b
            com.xiaomi.push.service.m r0 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r0.e(r3)
            com.xiaomi.push.service.m r0 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r0.f(r3)
        L_0x068b:
            if (r6 != 0) goto L_0x0697
            r12 = 70000003(0x42c1d83, float:2.0232054E-36)
            java.lang.String r0 = "null payload"
            com.xiaomi.push.service.o.a(r11, r3, r6, r12, r0)
            goto L_0x07b8
        L_0x0697:
            com.xiaomi.push.service.o.b(r3, r6)
            com.xiaomi.push.service.n r0 = new com.xiaomi.push.service.n
            r1 = r0
            r2 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r11.a((com.xiaomi.push.service.XMPushService.i) r0)
            java.lang.String r0 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE"
            java.lang.String r12 = r12.getAction()
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x07b8
            com.xiaomi.push.service.XMPushService$e r12 = r11.f236a
            if (r12 != 0) goto L_0x07b8
            com.xiaomi.push.service.XMPushService$e r12 = new com.xiaomi.push.service.XMPushService$e
            r12.<init>()
            r11.f236a = r12
            android.content.IntentFilter r12 = new android.content.IntentFilter
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            r12.<init>(r0)
            com.xiaomi.push.service.XMPushService$e r0 = r11.f236a
            r11.registerReceiver(r0, r12)
            goto L_0x07b8
        L_0x06c9:
            java.lang.String r0 = "mipush_app_package"
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = "mipush_payload"
            byte[] r1 = r12.getByteArrayExtra(r1)
            java.lang.String r2 = "com.xiaomi.mipush.MESSAGE_CACHE"
            boolean r2 = r12.getBooleanExtra(r2, r3)
            java.lang.String r3 = "com.xiaomi.mipush.UNREGISTER_APP"
            java.lang.String r12 = r12.getAction()
            boolean r12 = r3.equals(r12)
            if (r12 == 0) goto L_0x06ee
            com.xiaomi.push.service.m r12 = com.xiaomi.push.service.m.a((android.content.Context) r11)
            r12.a((java.lang.String) r0)
        L_0x06ee:
            r11.a((java.lang.String) r0, (byte[]) r1, (boolean) r2)
            goto L_0x07b8
        L_0x06f3:
            java.lang.String r0 = "android.intent.action.SCREEN_ON"
            java.lang.String r1 = r12.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x071e
            boolean r12 = r11.j()
            if (r12 != 0) goto L_0x07b8
            java.lang.String r12 = "exit falldown mode, activate alarm."
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            r11.e()
            boolean r12 = r11.c()
            if (r12 != 0) goto L_0x07b8
            boolean r12 = r11.d()
            if (r12 != 0) goto L_0x07b8
        L_0x0719:
            r11.a((boolean) r3)
            goto L_0x07b8
        L_0x071e:
            java.lang.String r0 = "android.intent.action.SCREEN_OFF"
            java.lang.String r12 = r12.getAction()
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x07b8
            boolean r12 = r11.j()
            if (r12 == 0) goto L_0x07b8
            boolean r12 = com.xiaomi.push.fe.b()
            if (r12 == 0) goto L_0x07b8
            java.lang.String r12 = "enter falldown mode, stop alarm."
        L_0x0738:
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            com.xiaomi.push.fe.a()
            goto L_0x07b8
        L_0x0740:
            java.lang.String r0 = com.xiaomi.push.service.aq.r
            java.lang.String r0 = r12.getStringExtra(r0)
            java.lang.String r1 = com.xiaomi.push.service.aq.v
            java.lang.String r1 = r12.getStringExtra(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0755
            java.lang.String r12 = "security is empty. ignore."
            goto L_0x07a5
        L_0x0755:
            if (r0 == 0) goto L_0x07b4
            boolean r1 = r11.a((java.lang.String) r0, (android.content.Intent) r12)
            com.xiaomi.push.service.am$b r7 = r11.a((java.lang.String) r0, (android.content.Intent) r12)
            boolean r12 = com.xiaomi.push.az.c(r11)
            if (r12 != 0) goto L_0x076f
            com.xiaomi.push.service.d r5 = r11.f240a
            r8 = 0
            r9 = 2
        L_0x0769:
            r10 = 0
            r6 = r11
            r5.a(r6, r7, r8, r9, r10)
            goto L_0x07b8
        L_0x076f:
            boolean r12 = r11.c()
            if (r12 == 0) goto L_0x0719
            com.xiaomi.push.service.am$c r12 = r7.f288a
            com.xiaomi.push.service.am$c r0 = com.xiaomi.push.service.am.c.unbind
            if (r12 != r0) goto L_0x0782
            com.xiaomi.push.service.XMPushService$a r12 = new com.xiaomi.push.service.XMPushService$a
            r12.<init>(r7)
            goto L_0x019d
        L_0x0782:
            if (r1 == 0) goto L_0x078b
            com.xiaomi.push.service.XMPushService$n r12 = new com.xiaomi.push.service.XMPushService$n
            r12.<init>(r7)
            goto L_0x019d
        L_0x078b:
            com.xiaomi.push.service.am$c r12 = r7.f288a
            com.xiaomi.push.service.am$c r0 = com.xiaomi.push.service.am.c.binding
            if (r12 != r0) goto L_0x07a9
            java.lang.String r12 = "the client is binding. %1$s %2$s."
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r1 = r7.g
            r0[r4] = r1
            java.lang.String r1 = r7.f293b
            java.lang.String r1 = com.xiaomi.push.service.am.b.a((java.lang.String) r1)
            r0[r3] = r1
            java.lang.String r12 = java.lang.String.format(r12, r0)
        L_0x07a5:
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)
            goto L_0x07b8
        L_0x07a9:
            com.xiaomi.push.service.am$c r12 = r7.f288a
            com.xiaomi.push.service.am$c r0 = com.xiaomi.push.service.am.c.binded
            if (r12 != r0) goto L_0x07b8
            com.xiaomi.push.service.d r5 = r11.f240a
            r8 = 1
            r9 = 0
            goto L_0x0769
        L_0x07b4:
            java.lang.String r12 = "channel id is empty, do nothing!"
            goto L_0x0444
        L_0x07b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.XMPushService.c(android.content.Intent):void");
    }

    private void c(i iVar) {
        this.f241a.a((g.b) iVar);
    }

    private void c(boolean z) {
        try {
            if (!t.c()) {
                return;
            }
            if (z) {
                sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
                for (af a2 : (af[]) this.f245a.toArray(new af[0])) {
                    a2.a();
                }
                return;
            }
            sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
        }
    }

    private void d() {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
            networkInfo = null;
        }
        if (networkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network changed,");
            sb.append(Operators.ARRAY_START_STR + "type: " + networkInfo.getTypeName() + Operators.ARRAY_START_STR + networkInfo.getSubtypeName() + "], state: " + networkInfo.getState() + "/" + networkInfo.getDetailedState());
            com.xiaomi.channel.commonutils.logger.b.a(sb.toString());
            NetworkInfo.State state = networkInfo.getState();
            if (state == NetworkInfo.State.SUSPENDED || state == NetworkInfo.State.UNKNOWN) {
                return;
            }
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("network changed, no active network");
        }
        if (hg.b() != null) {
            hg.b().b();
        }
        gz.a((Context) this);
        this.f232a.p();
        if (az.c(this)) {
            if (c() && e()) {
                b(false);
            }
            if (!c() && !d()) {
                this.f241a.a(1);
                a((i) new d());
            }
            dk.a((Context) this).a();
        } else {
            a((i) new f(2, (Exception) null));
        }
        e();
    }

    private void d(Intent intent) {
        int i2;
        try {
            es.a(getApplicationContext()).a((ew) new as());
            String stringExtra = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra != null) {
                in inVar = new in();
                iy.a(inVar, byteArrayExtra);
                String b2 = inVar.b();
                Map a2 = inVar.a();
                if (a2 != null) {
                    String str = (String) a2.get("extra_help_aw_info");
                    String str2 = (String) a2.get("extra_aw_app_online_cmd");
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            i2 = Integer.parseInt(str2);
                        } catch (NumberFormatException unused) {
                            i2 = 0;
                        }
                        if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(b2) && !TextUtils.isEmpty(str)) {
                            es.a(getApplicationContext()).a(this, str, i2, stringExtra, b2);
                        }
                    }
                }
            }
        } catch (je e2) {
            com.xiaomi.channel.commonutils.logger.b.d("aw_logic: translate fail. " + e2.getMessage());
        }
    }

    private void e() {
        if (!a()) {
            fe.a();
        } else if (!fe.b()) {
            fe.a(true);
        }
    }

    /* renamed from: e  reason: collision with other method in class */
    private boolean m259e() {
        if (System.currentTimeMillis() - this.f229a < 30000) {
            return false;
        }
        return az.d(this);
    }

    /* access modifiers changed from: private */
    public void f() {
        String str;
        if (this.f233a != null && this.f233a.i()) {
            str = "try to connect while connecting.";
        } else if (this.f233a == null || !this.f233a.j()) {
            this.f234a.b(az.k(this));
            g();
            if (this.f233a == null) {
                am.a().a((Context) this);
                c(false);
                return;
            }
            return;
        } else {
            str = "try to connect while is connected.";
        }
        com.xiaomi.channel.commonutils.logger.b.d(str);
    }

    /* renamed from: f  reason: collision with other method in class */
    private boolean m260f() {
        return com.xiaomi.stat.c.c.f23036a.equals(getPackageName()) && Settings.Secure.getInt(getContentResolver(), "EXTREME_POWER_MODE_ENABLE", 0) == 1;
    }

    private void g() {
        try {
            this.f232a.a(this.f235a, (gh) new bl(this));
            this.f232a.r();
            this.f233a = this.f232a;
        } catch (gf e2) {
            com.xiaomi.channel.commonutils.logger.b.a("fail to create Slim connection", (Throwable) e2);
            this.f232a.b(3, e2);
        }
    }

    /* renamed from: g  reason: collision with other method in class */
    private boolean m261g() {
        return com.xiaomi.stat.c.c.f23036a.equals(getPackageName()) && Settings.System.getInt(getContentResolver(), "power_supersave_mode_open", 0) == 1;
    }

    private void h() {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(d, new Notification());
        } else {
            bindService(new Intent(this, this.f242a), new bm(this), 1);
        }
    }

    /* renamed from: h  reason: collision with other method in class */
    private boolean m262h() {
        return com.xiaomi.stat.c.c.f23036a.equals(getPackageName()) || !m.a((Context) this).b(getPackageName());
    }

    private void i() {
        synchronized (this.f244a) {
            this.f244a.clear();
        }
    }

    /* renamed from: i  reason: collision with other method in class */
    private boolean m263i() {
        return ((PowerManager) getSystemService(CameraPropertyBase.l)).isScreenOn();
    }

    private boolean j() {
        return getApplicationContext().getPackageName().equals(com.xiaomi.stat.c.c.f23036a) && k() && !i() && !a(getApplicationContext());
    }

    private boolean k() {
        int intValue = Integer.valueOf(String.format("%tH", new Object[]{new Date()})).intValue();
        if (this.b <= this.c) {
            return this.b < this.c && intValue >= this.b && intValue < this.c;
        }
        if (intValue >= this.b || intValue < this.c) {
            return true;
        }
    }

    private boolean l() {
        if (TextUtils.equals(getPackageName(), com.xiaomi.stat.c.c.f23036a)) {
            return false;
        }
        return ah.a((Context) this).a(ht.ForegroundServiceSwitch.a(), false);
    }

    /* renamed from: a  reason: collision with other method in class */
    public fu m264a() {
        return this.f233a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public d m265a() {
        return new d();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m266a() {
        if (System.currentTimeMillis() - this.f229a >= ((long) ga.b()) && az.d(this)) {
            b(true);
        }
    }

    public void a(int i2) {
        this.f241a.a(i2);
    }

    public void a(int i2, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("disconnect ");
        sb.append(hashCode());
        sb.append(", ");
        sb.append(this.f233a == null ? null : Integer.valueOf(this.f233a.hashCode()));
        com.xiaomi.channel.commonutils.logger.b.a(sb.toString());
        if (this.f233a != null) {
            this.f233a.b(i2, exc);
            this.f233a = null;
        }
        a(7);
        a(4);
        am.a().a((Context) this, i2);
    }

    public void a(fn fnVar) {
        if (this.f233a != null) {
            this.f233a.b(fnVar);
            return;
        }
        throw new gf("try send msg while connection is null.");
    }

    public void a(fu fuVar) {
        hg.b().a(fuVar);
        c(true);
        this.f239a.a();
        if (!fe.b() && !j()) {
            com.xiaomi.channel.commonutils.logger.b.a("reconnection successful, reactivate alarm.");
            fe.a(true);
        }
        Iterator it = am.a().a().iterator();
        while (it.hasNext()) {
            a((i) new a((am.b) it.next()));
        }
    }

    public void a(fu fuVar, int i2, Exception exc) {
        hg.b().a(fuVar, i2, exc);
        if (!j()) {
            a(false);
        }
    }

    public void a(fu fuVar, Exception exc) {
        hg.b().a(fuVar, exc);
        c(false);
        if (!j()) {
            a(false);
        }
    }

    public void a(i iVar) {
        a(iVar, 0);
    }

    public void a(i iVar, long j2) {
        try {
            this.f241a.a((g.b) iVar, j2);
        } catch (IllegalStateException e2) {
            com.xiaomi.channel.commonutils.logger.b.a("can't execute job err = " + e2.getMessage());
        }
    }

    public void a(l lVar) {
        synchronized (this.f244a) {
            this.f244a.add(lVar);
        }
    }

    public void a(am.b bVar) {
        if (bVar != null) {
            long a2 = bVar.a();
            com.xiaomi.channel.commonutils.logger.b.a("schedule rebind job in " + (a2 / 1000));
            a((i) new a(bVar), a2);
        }
    }

    public void a(String str, String str2, int i2, String str3, String str4) {
        am.b a2 = am.a().a(str, str2);
        if (a2 != null) {
            a((i) new q(a2, i2, str4, str3));
        }
        am.a().a(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, byte[] bArr, boolean z) {
        Collection a2 = am.a().a("5");
        if (a2.isEmpty()) {
            if (!z) {
                return;
            }
        } else if (((am.b) a2.iterator().next()).f288a == am.c.binded) {
            a((i) new bj(this, 4, str, bArr));
            return;
        } else if (!z) {
            return;
        }
        o.b(str, bArr);
    }

    public void a(boolean z) {
        this.f239a.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            o.a(this, str, bArr, ErrorCode.e, "null payload");
            com.xiaomi.channel.commonutils.logger.b.a("register request without payload");
            return;
        }
        ik ikVar = new ik();
        try {
            iy.a(ikVar, bArr);
            if (ikVar.f12803a == ho.Registration) {
                io ioVar = new io();
                try {
                    iy.a(ioVar, ikVar.a());
                    o.a(ikVar.b(), bArr);
                    a((i) new n(this, ikVar.b(), ioVar.b(), ioVar.c(), bArr));
                    fd.a(getApplicationContext()).a(ikVar.b(), "E100003", ioVar.a(), IRpcException.ErrorCode.SERVER_PARAMMISSING, "send a register message to server");
                } catch (je e2) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
                    o.a(this, str, bArr, ErrorCode.e, " data action error.");
                }
            } else {
                o.a(this, str, bArr, ErrorCode.e, " registration action required.");
                com.xiaomi.channel.commonutils.logger.b.a("register request with invalid payload");
            }
        } catch (je e3) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e3);
            o.a(this, str, bArr, ErrorCode.e, " data container error.");
        }
    }

    public void a(fn[] fnVarArr) {
        if (this.f233a != null) {
            this.f233a.a(fnVarArr);
            return;
        }
        throw new gf("try send msg while connection is null.");
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m267a() {
        return az.c(this) && am.a().a() > 0 && !b() && h() && !g() && !f();
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m268a(int i2) {
        return this.f241a.a(i2);
    }

    public d b() {
        return this.f240a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b  reason: collision with other method in class */
    public void m269b() {
        Iterator it = new ArrayList(this.f244a).iterator();
        while (it.hasNext()) {
            ((l) it.next()).a();
        }
    }

    public void b(fu fuVar) {
        com.xiaomi.channel.commonutils.logger.b.c("begin to connect...");
        hg.b().b(fuVar);
    }

    public void b(i iVar) {
        this.f241a.a(iVar.f12924a, (g.b) iVar);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m270b() {
        try {
            Class<?> a2 = t.a(this, "miui.os.Build");
            return a2.getField("IS_CM_CUSTOMIZATION_TEST").getBoolean((Object) null) || a2.getField("IS_CU_CUSTOMIZATION_TEST").getBoolean((Object) null) || a2.getField("IS_CT_CUSTOMIZATION_TEST").getBoolean((Object) null);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m271c() {
        return this.f233a != null && this.f233a.j();
    }

    /* renamed from: d  reason: collision with other method in class */
    public boolean m272d() {
        return this.f233a != null && this.f233a.i();
    }

    public IBinder onBind(Intent intent) {
        return this.f231a.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        t.a(this);
        k a2 = l.a((Context) this);
        if (a2 != null) {
            ab.a(a2.f12930a);
        }
        this.f231a = new Messenger(new bn(this));
        ar.a(this);
        this.f234a = new bo(this, (Map) null, 5222, "xiaomi.com", (fy) null);
        this.f234a.a(true);
        this.f232a = new fs(this, this.f234a);
        this.f240a = a();
        fe.a((Context) this);
        this.f232a.a((fx) this);
        this.f238a = new al(this);
        this.f239a = new aw(this);
        new e().a();
        hg.a().a(this);
        this.f241a = new g("Connection Controller Thread");
        am a3 = am.a();
        a3.b();
        a3.a((am.a) new bp(this));
        if (l()) {
            h();
        }
        hm.a(this).a((hn) new i(this), "UPLOADER_PUSH_CHANNEL");
        a((l) new hj(this));
        a((i) new g());
        this.f245a.add(bd.a((Context) this));
        if (h()) {
            this.f236a = new e();
            registerReceiver(this.f236a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (com.xiaomi.stat.c.c.f23036a.equals(getPackageName())) {
            Uri uriFor = Settings.Secure.getUriFor("EXTREME_POWER_MODE_ENABLE");
            if (uriFor != null) {
                this.f230a = new bq(this, new Handler(Looper.getMainLooper()));
                try {
                    getContentResolver().registerContentObserver(uriFor, false, this.f230a);
                } catch (Throwable th) {
                    com.xiaomi.channel.commonutils.logger.b.a("register observer err:" + th.getMessage());
                }
            }
            Uri uriFor2 = Settings.System.getUriFor("power_supersave_mode_open");
            if (uriFor2 != null) {
                this.f246b = new br(this, new Handler(Looper.getMainLooper()));
                try {
                    getContentResolver().registerContentObserver(uriFor2, false, this.f246b);
                } catch (Throwable th2) {
                    com.xiaomi.channel.commonutils.logger.b.d("register super-power-mode observer err:" + th2.getMessage());
                }
            }
            int[] a4 = a();
            if (a4 != null) {
                this.f237a = new p();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                registerReceiver(this.f237a, intentFilter);
                this.b = a4[0];
                this.c = a4[1];
                com.xiaomi.channel.commonutils.logger.b.a("falldown initialized: " + this.b + "," + this.c);
            }
        }
        com.xiaomi.channel.commonutils.logger.b.a("XMPushService created pid = " + d);
    }

    public void onDestroy() {
        if (this.f236a != null) {
            a((BroadcastReceiver) this.f236a);
            this.f236a = null;
        }
        if (this.f237a != null) {
            a((BroadcastReceiver) this.f237a);
            this.f237a = null;
        }
        if (com.xiaomi.stat.c.c.f23036a.equals(getPackageName()) && this.f230a != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f230a);
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a("unregister observer err:" + th.getMessage());
            }
        }
        if (com.xiaomi.stat.c.c.f23036a.equals(getPackageName()) && this.f246b != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f246b);
            } catch (Throwable th2) {
                com.xiaomi.channel.commonutils.logger.b.d("unregister super-power-mode err:" + th2.getMessage());
            }
        }
        this.f245a.clear();
        this.f241a.b();
        a((i) new bk(this, 2));
        a((i) new j());
        am.a().b();
        am.a().a((Context) this, 15);
        am.a().a();
        this.f232a.b((fx) this);
        bb.a().a();
        fe.a();
        i();
        super.onDestroy();
        com.xiaomi.channel.commonutils.logger.b.a("Service destroyed");
    }

    public void onStart(Intent intent, int i2) {
        h hVar;
        long currentTimeMillis = System.currentTimeMillis();
        if (intent == null) {
            com.xiaomi.channel.commonutils.logger.b.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.logger.b.c(String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s, from-bridge = %s", new Object[]{intent.getAction(), intent.getStringExtra(aq.r), intent.getStringExtra(aq.z), intent.getStringExtra("mipush_app_package"), intent.getStringExtra("ext_is_xmpushservice_bridge")}));
        }
        if (!(intent == null || intent.getAction() == null)) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.f241a.a()) {
                    com.xiaomi.channel.commonutils.logger.b.d("ERROR, the job controller is blocked.");
                    am.a().a((Context) this, 14);
                    stopSelf();
                } else {
                    hVar = new h(intent);
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                hVar = new h(intent);
            }
            a((i) hVar);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 50) {
            com.xiaomi.channel.commonutils.logger.b.c("[Prefs] spend " + currentTimeMillis2 + " ms, too more times.");
        }
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        onStart(intent, i3);
        return f12846a;
    }
}
