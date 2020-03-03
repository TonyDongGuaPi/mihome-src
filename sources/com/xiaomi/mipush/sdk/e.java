package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ht;
import com.xiaomi.push.service.ah;
import java.util.HashMap;
import java.util.Map;

public class e implements AbstractPushManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile e f11550a;
    /* access modifiers changed from: private */
    public Context b;
    private PushConfiguration c;
    /* access modifiers changed from: private */
    public boolean d = false;
    private Map<d, AbstractPushManager> e = new HashMap();

    private e(Context context) {
        this.b = context.getApplicationContext();
    }

    public static e a(Context context) {
        if (f11550a == null) {
            synchronized (e.class) {
                if (f11550a == null) {
                    f11550a = new e(context);
                }
            }
        }
        return f11550a;
    }

    private void c() {
        AbstractPushManager c2;
        AbstractPushManager c3;
        AbstractPushManager c4;
        AbstractPushManager c5;
        if (this.c != null) {
            if (this.c.b()) {
                StringBuilder sb = new StringBuilder();
                sb.append("ASSEMBLE_PUSH : ");
                sb.append(" HW user switch : " + this.c.b() + " HW online switch : " + h.b(this.b, d.ASSEMBLE_PUSH_HUAWEI) + " HW isSupport : " + an.HUAWEI.equals(m.a(this.b)));
                b.a(sb.toString());
            }
            if (this.c.b() && h.b(this.b, d.ASSEMBLE_PUSH_HUAWEI) && an.HUAWEI.equals(m.a(this.b))) {
                if (!b(d.ASSEMBLE_PUSH_HUAWEI)) {
                    a(d.ASSEMBLE_PUSH_HUAWEI, ar.a(this.b, d.ASSEMBLE_PUSH_HUAWEI));
                }
                b.c("hw manager add to list");
            } else if (b(d.ASSEMBLE_PUSH_HUAWEI) && (c5 = c(d.ASSEMBLE_PUSH_HUAWEI)) != null) {
                a(d.ASSEMBLE_PUSH_HUAWEI);
                c5.b();
            }
            if (this.c.c()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ASSEMBLE_PUSH : ");
                sb2.append(" FCM user switch : " + this.c.c() + " FCM online switch : " + h.b(this.b, d.ASSEMBLE_PUSH_FCM) + " FCM isSupport : " + m.b(this.b));
                b.a(sb2.toString());
            }
            if (this.c.c() && h.b(this.b, d.ASSEMBLE_PUSH_FCM) && m.b(this.b)) {
                if (!b(d.ASSEMBLE_PUSH_FCM)) {
                    a(d.ASSEMBLE_PUSH_FCM, ar.a(this.b, d.ASSEMBLE_PUSH_FCM));
                }
                b.c("fcm manager add to list");
            } else if (b(d.ASSEMBLE_PUSH_FCM) && (c4 = c(d.ASSEMBLE_PUSH_FCM)) != null) {
                a(d.ASSEMBLE_PUSH_FCM);
                c4.b();
            }
            if (this.c.d()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ASSEMBLE_PUSH : ");
                sb3.append(" COS user switch : " + this.c.d() + " COS online switch : " + h.b(this.b, d.ASSEMBLE_PUSH_COS) + " COS isSupport : " + m.c(this.b));
                b.a(sb3.toString());
            }
            if (this.c.d() && h.b(this.b, d.ASSEMBLE_PUSH_COS) && m.c(this.b)) {
                a(d.ASSEMBLE_PUSH_COS, ar.a(this.b, d.ASSEMBLE_PUSH_COS));
            } else if (b(d.ASSEMBLE_PUSH_COS) && (c3 = c(d.ASSEMBLE_PUSH_COS)) != null) {
                a(d.ASSEMBLE_PUSH_COS);
                c3.b();
            }
            if (this.c.e() && h.b(this.b, d.ASSEMBLE_PUSH_FTOS) && m.d(this.b)) {
                a(d.ASSEMBLE_PUSH_FTOS, ar.a(this.b, d.ASSEMBLE_PUSH_FTOS));
            } else if (b(d.ASSEMBLE_PUSH_FTOS) && (c2 = c(d.ASSEMBLE_PUSH_FTOS)) != null) {
                a(d.ASSEMBLE_PUSH_FTOS);
                c2.b();
            }
        }
    }

    public void a() {
        b.a("ASSEMBLE_PUSH : assemble push register");
        if (this.e.size() <= 0) {
            c();
        }
        if (this.e.size() > 0) {
            for (AbstractPushManager next : this.e.values()) {
                if (next != null) {
                    next.a();
                }
            }
            h.a(this.b);
        }
    }

    public void a(PushConfiguration pushConfiguration) {
        this.c = pushConfiguration;
        this.d = ah.a(this.b).a(ht.AggregatePushSwitch.a(), true);
        if (this.c.b() || this.c.c() || this.c.d()) {
            ah.a(this.b).a((ah.a) new f(this, 101, "assemblePush"));
        }
    }

    public void a(d dVar) {
        this.e.remove(dVar);
    }

    public void a(d dVar, AbstractPushManager abstractPushManager) {
        if (abstractPushManager != null) {
            if (this.e.containsKey(dVar)) {
                this.e.remove(dVar);
            }
            this.e.put(dVar, abstractPushManager);
        }
    }

    public void b() {
        b.a("ASSEMBLE_PUSH : assemble push unregister");
        for (AbstractPushManager next : this.e.values()) {
            if (next != null) {
                next.b();
            }
        }
        this.e.clear();
    }

    public boolean b(d dVar) {
        return this.e.containsKey(dVar);
    }

    public AbstractPushManager c(d dVar) {
        return this.e.get(dVar);
    }

    public boolean d(d dVar) {
        boolean z = false;
        switch (g.f11552a[dVar.ordinal()]) {
            case 1:
                if (this.c != null) {
                    return this.c.b();
                }
                return false;
            case 2:
                if (this.c != null) {
                    return this.c.c();
                }
                return false;
            case 3:
                if (this.c != null) {
                    z = this.c.d();
                    break;
                }
                break;
            case 4:
                break;
            default:
                return false;
        }
        return this.c != null ? this.c.e() : z;
    }
}
