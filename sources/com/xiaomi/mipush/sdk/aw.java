package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.az;
import com.xiaomi.push.be;
import com.xiaomi.push.dh;
import com.xiaomi.push.fd;
import com.xiaomi.push.ho;
import com.xiaomi.push.hp;
import com.xiaomi.push.hs;
import com.xiaomi.push.ht;
import com.xiaomi.push.hy;
import com.xiaomi.push.ib;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.io;
import com.xiaomi.push.iu;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import com.xiaomi.push.l;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.ak;
import com.xiaomi.push.service.aq;
import com.xiaomi.push.service.at;
import com.xiaomi.stat.c.c;
import com.xiaomi.youpin.UserMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class aw {
    private static aw b = null;
    private static boolean f = false;
    private static final ArrayList<a> g = new ArrayList<>();

    /* renamed from: a  reason: collision with root package name */
    private boolean f11539a = false;
    /* access modifiers changed from: private */
    public Context c;
    private String d;
    /* access modifiers changed from: private */
    public Messenger e;
    private Handler h = null;
    /* access modifiers changed from: private */
    public List<Message> i = new ArrayList();
    /* access modifiers changed from: private */
    public boolean j = false;
    private Intent k = null;
    /* access modifiers changed from: private */
    public Integer l = null;

    static class a<T extends iz<T, ?>> {

        /* renamed from: a  reason: collision with root package name */
        T f11540a;
        ho b;
        boolean c;

        a() {
        }
    }

    private aw(Context context) {
        this.c = context.getApplicationContext();
        this.d = null;
        this.f11539a = i();
        f = r();
        this.h = new ax(this, Looper.getMainLooper());
        Intent k2 = k();
        if (k2 != null) {
            b(k2);
        }
    }

    public static synchronized aw a(Context context) {
        aw awVar;
        synchronized (aw.class) {
            if (b == null) {
                b = new aw(context);
            }
            awVar = b;
        }
        return awVar;
    }

    /* access modifiers changed from: private */
    public void a(String str, bb bbVar, boolean z, HashMap<String, String> hashMap) {
        in inVar;
        String str2;
        if (b.a(this.c).b() && az.c(this.c)) {
            in inVar2 = new in();
            inVar2.a(true);
            Intent j2 = j();
            if (TextUtils.isEmpty(str)) {
                str = ak.a();
                inVar2.a(str);
                inVar = z ? new in(str, true) : null;
                synchronized (am.class) {
                    am.a(this.c).a(str);
                }
            } else {
                inVar2.a(str);
                inVar = z ? new in(str, true) : null;
            }
            switch (ba.f11546a[bbVar.ordinal()]) {
                case 1:
                    inVar2.c(hy.DisablePushMessage.f114a);
                    inVar.c(hy.DisablePushMessage.f114a);
                    if (hashMap != null) {
                        inVar2.a((Map<String, String>) hashMap);
                        inVar.a((Map<String, String>) hashMap);
                    }
                    str2 = "com.xiaomi.mipush.DISABLE_PUSH_MESSAGE";
                    break;
                case 2:
                    inVar2.c(hy.EnablePushMessage.f114a);
                    inVar.c(hy.EnablePushMessage.f114a);
                    if (hashMap != null) {
                        inVar2.a((Map<String, String>) hashMap);
                        inVar.a((Map<String, String>) hashMap);
                    }
                    str2 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE";
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    inVar2.c(hy.ThirdPartyRegUpdate.f114a);
                    if (hashMap != null) {
                        inVar2.a((Map<String, String>) hashMap);
                        break;
                    }
                    break;
            }
            j2.setAction(str2);
            inVar2.b(b.a(this.c).c());
            inVar2.d(this.c.getPackageName());
            a(inVar2, ho.Notification, false, (ib) null);
            if (z) {
                inVar.b(b.a(this.c).c());
                inVar.d(this.c.getPackageName());
                byte[] a2 = iy.a(ap.a(this.c, inVar, ho.Notification, false, this.c.getPackageName(), b.a(this.c).c()));
                if (a2 != null) {
                    dh.a(this.c.getPackageName(), this.c, inVar, ho.Notification, a2.length);
                    j2.putExtra("mipush_payload", a2);
                    j2.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    j2.putExtra("mipush_app_id", b.a(this.c).c());
                    j2.putExtra("mipush_app_token", b.a(this.c).d());
                    c(j2);
                }
            }
            Message obtain = Message.obtain();
            obtain.what = 19;
            int ordinal = bbVar.ordinal();
            obtain.obj = str;
            obtain.arg1 = ordinal;
            this.h.sendMessageDelayed(obtain, 5000);
        }
    }

    private void b(Intent intent) {
        try {
            if (l.a() || Build.VERSION.SDK_INT < 26) {
                this.c.startService(intent);
            } else {
                d(intent);
            }
        } catch (Exception e2) {
            b.a((Throwable) e2);
        }
    }

    private void c(Intent intent) {
        int a2 = ah.a(this.c).a(ht.ServiceBootMode.a(), hp.START.a());
        int h2 = h();
        boolean z = a2 == hp.b.a() && f;
        int a3 = (z ? hp.b : hp.START).a();
        if (a3 != h2) {
            c(a3);
        }
        if (z) {
            d(intent);
        } else {
            b(intent);
        }
    }

    private synchronized void d(int i2) {
        this.c.getSharedPreferences("mipush_extra", 0).edit().putInt(Constants.p, i2).commit();
    }

    private synchronized void d(Intent intent) {
        if (this.j) {
            Message e2 = e(intent);
            if (this.i.size() >= 50) {
                this.i.remove(0);
            }
            this.i.add(e2);
            return;
        } else if (this.e == null) {
            Context context = this.c;
            az azVar = new az(this);
            Context context2 = this.c;
            context.bindService(intent, azVar, 1);
            this.j = true;
            this.i.clear();
            this.i.add(e(intent));
        } else {
            try {
                this.e.send(e(intent));
            } catch (RemoteException unused) {
                this.e = null;
                this.j = false;
            }
        }
        return;
    }

    private Message e(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    private synchronized int h() {
        return this.c.getSharedPreferences("mipush_extra", 0).getInt(Constants.p, -1);
    }

    private boolean i() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(c.f23036a, 4);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Intent j() {
        return (!c() || c.f23036a.equals(this.c.getPackageName())) ? n() : m();
    }

    private Intent k() {
        if (!c.f23036a.equals(this.c.getPackageName())) {
            return l();
        }
        b.c("pushChannel xmsf create own channel");
        return n();
    }

    private Intent l() {
        if (c()) {
            b.c("pushChannel app start miui china channel");
            return m();
        }
        b.c("pushChannel app start  own channel");
        return n();
    }

    private Intent m() {
        Intent intent = new Intent();
        String packageName = this.c.getPackageName();
        intent.setPackage(c.f23036a);
        intent.setClassName(c.f23036a, o());
        intent.putExtra("mipush_app_package", packageName);
        p();
        return intent;
    }

    private Intent n() {
        Intent intent = new Intent();
        String packageName = this.c.getPackageName();
        q();
        intent.setComponent(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    private String o() {
        try {
            return this.c.getPackageManager().getPackageInfo(c.f23036a, 4).versionCode >= 106 ? "com.xiaomi.push.service.XMPushService" : "com.xiaomi.xmsf.push.service.XMPushService";
        } catch (Exception unused) {
            return "com.xiaomi.xmsf.push.service.XMPushService";
        }
    }

    private void p() {
        try {
            PackageManager packageManager = this.c.getPackageManager();
            ComponentName componentName = new ComponentName(this.c, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                packageManager.setComponentEnabledSetting(componentName, 2, 1);
            }
        } catch (Throwable unused) {
        }
    }

    private void q() {
        try {
            PackageManager packageManager = this.c.getPackageManager();
            ComponentName componentName = new ComponentName(this.c, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) != 1) {
                packageManager.setComponentEnabledSetting(componentName, 1, 1);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean r() {
        if (c()) {
            try {
                return this.c.getPackageManager().getPackageInfo(c.f23036a, 4).versionCode >= 108;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    private boolean s() {
        String packageName = this.c.getPackageName();
        return packageName.contains("miui") || packageName.contains(UserMode.f23179a) || (this.c.getApplicationInfo().flags & 1) != 0;
    }

    public void a() {
        b(j());
    }

    public void a(int i2) {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        j2.putExtra(aq.z, this.c.getPackageName());
        j2.putExtra(aq.A, i2);
        c(j2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str) {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.thirdparty");
        j2.putExtra("com.xiaomi.mipush.thirdparty_LEVEL", i2);
        j2.putExtra("com.xiaomi.mipush.thirdparty_DESC", str);
        b(j2);
    }

    /* access modifiers changed from: package-private */
    public void a(Intent intent) {
        intent.fillIn(j(), 24);
        c(intent);
    }

    public final void a(hs hsVar) {
        Intent j2 = j();
        byte[] a2 = iy.a(hsVar);
        if (a2 == null) {
            b.a("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        j2.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        j2.putExtra("mipush_payload", a2);
        b(j2);
    }

    public final void a(io ioVar, boolean z) {
        fd.a(this.c.getApplicationContext()).a(this.c.getPackageName(), "E100003", ioVar.a(), 6001, "construct a register message");
        this.k = null;
        b.a(this.c).f11544a = ioVar.a();
        Intent j2 = j();
        byte[] a2 = iy.a(ap.a(this.c, ioVar, ho.Registration));
        if (a2 == null) {
            b.a("register fail, because msgBytes is null.");
            return;
        }
        j2.setAction("com.xiaomi.mipush.REGISTER_APP");
        j2.putExtra("mipush_app_id", b.a(this.c).c());
        j2.putExtra("mipush_payload", a2);
        j2.putExtra("mipush_session", this.d);
        j2.putExtra("mipush_env_chanage", z);
        j2.putExtra("mipush_env_type", b.a(this.c).m());
        if (!az.c(this.c) || !g()) {
            this.k = j2;
        } else {
            c(j2);
        }
    }

    public final void a(iu iuVar) {
        byte[] a2 = iy.a(ap.a(this.c, iuVar, ho.UnRegistration));
        if (a2 == null) {
            b.a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        j2.putExtra("mipush_app_id", b.a(this.c).c());
        j2.putExtra("mipush_payload", a2);
        c(j2);
    }

    public final <T extends iz<T, ?>> void a(T t, ho hoVar, ib ibVar) {
        a(t, hoVar, !hoVar.equals(ho.Registration), ibVar);
    }

    public <T extends iz<T, ?>> void a(T t, ho hoVar, boolean z) {
        a aVar = new a();
        aVar.f11540a = t;
        aVar.b = hoVar;
        aVar.c = z;
        synchronized (g) {
            g.add(aVar);
            if (g.size() > 10) {
                g.remove(0);
            }
        }
    }

    public final <T extends iz<T, ?>> void a(T t, ho hoVar, boolean z, ib ibVar) {
        a(t, hoVar, z, true, ibVar, true);
    }

    public final <T extends iz<T, ?>> void a(T t, ho hoVar, boolean z, ib ibVar, boolean z2) {
        a(t, hoVar, z, true, ibVar, z2);
    }

    public final <T extends iz<T, ?>> void a(T t, ho hoVar, boolean z, boolean z2, ib ibVar, boolean z3) {
        a(t, hoVar, z, z2, ibVar, z3, this.c.getPackageName(), b.a(this.c).c());
    }

    public final <T extends iz<T, ?>> void a(T t, ho hoVar, boolean z, boolean z2, ib ibVar, boolean z3, String str, String str2) {
        if (b.a(this.c).j()) {
            ik a2 = ap.a(this.c, t, hoVar, z, str, str2);
            if (ibVar != null) {
                a2.a(ibVar);
            }
            byte[] a3 = iy.a(a2);
            if (a3 == null) {
                b.a("send message fail, because msgBytes is null.");
                return;
            }
            dh.a(this.c.getPackageName(), this.c, t, hoVar, a3.length);
            Intent j2 = j();
            j2.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            j2.putExtra("mipush_payload", a3);
            j2.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
            c(j2);
        } else if (z2) {
            a(t, hoVar, z);
        } else {
            b.a("drop the message before initialization.");
        }
    }

    public final void a(String str, bb bbVar, d dVar) {
        am.a(this.c).a(bbVar, "syncing");
        a(str, bbVar, false, h.c(this.c, dVar));
    }

    public void a(String str, String str2) {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        j2.putExtra(aq.z, this.c.getPackageName());
        j2.putExtra(aq.E, str);
        j2.putExtra(aq.F, str2);
        c(j2);
    }

    public final void a(boolean z) {
        a(z, (String) null);
    }

    public final void a(boolean z, String str) {
        bb bbVar;
        if (z) {
            am.a(this.c).a(bb.DISABLE_PUSH, "syncing");
            am.a(this.c).a(bb.ENABLE_PUSH, "");
            bbVar = bb.DISABLE_PUSH;
        } else {
            am.a(this.c).a(bb.ENABLE_PUSH, "syncing");
            am.a(this.c).a(bb.DISABLE_PUSH, "");
            bbVar = bb.ENABLE_PUSH;
        }
        a(str, bbVar, true, (HashMap<String, String>) null);
    }

    public final void b() {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        c(j2);
    }

    public void b(int i2) {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        j2.putExtra(aq.z, this.c.getPackageName());
        j2.putExtra(aq.B, i2);
        String str = aq.D;
        j2.putExtra(str, be.b(this.c.getPackageName() + i2));
        c(j2);
    }

    public boolean c() {
        return this.f11539a && 1 == b.a(this.c).m();
    }

    public boolean c(int i2) {
        if (!b.a(this.c).b()) {
            return false;
        }
        d(i2);
        in inVar = new in();
        inVar.a(ak.a());
        inVar.b(b.a(this.c).c());
        inVar.d(this.c.getPackageName());
        inVar.c(hy.ClientABTest.f114a);
        inVar.f177a = new HashMap();
        Map<String, String> map = inVar.f177a;
        map.put("boot_mode", i2 + "");
        a(this.c).a(inVar, ho.Notification, false, (ib) null);
        return true;
    }

    public void d() {
        if (this.k != null) {
            c(this.k);
            this.k = null;
        }
    }

    public void e() {
        synchronized (g) {
            Iterator<a> it = g.iterator();
            while (it.hasNext()) {
                a next = it.next();
                a(next.f11540a, next.b, next.c, false, (ib) null, true);
            }
            g.clear();
        }
    }

    public void f() {
        Intent j2 = j();
        j2.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        j2.putExtra(aq.z, this.c.getPackageName());
        j2.putExtra(aq.D, be.b(this.c.getPackageName()));
        c(j2);
    }

    public boolean g() {
        if (!c() || !s()) {
            return true;
        }
        if (this.l == null) {
            this.l = Integer.valueOf(at.a(this.c).a());
            if (this.l.intValue() == 0) {
                this.c.getContentResolver().registerContentObserver(at.a(this.c).a(), false, new ay(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.l.intValue() != 0;
    }
}
