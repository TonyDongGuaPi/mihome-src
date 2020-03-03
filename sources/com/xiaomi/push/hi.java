package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.he;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import java.util.Hashtable;

public class hi {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12774a = fj.PING_RTT.a();

    static class a {

        /* renamed from: a  reason: collision with root package name */
        static Hashtable<Integer, Long> f12775a = new Hashtable<>();
    }

    public static void a() {
        a(0, f12774a);
    }

    public static void a(int i) {
        fk f = hg.a().f();
        f.a(fj.CHANNEL_STATS_COUNTER.a());
        f.c(i);
        hg.a().a(f);
    }

    public static synchronized void a(int i, int i2) {
        synchronized (hi.class) {
            if (i2 < 16777215) {
                try {
                    a.f12775a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                b.d("stats key should less than 16777215");
            }
        }
    }

    public static void a(int i, int i2, int i3, String str, int i4) {
        fk f = hg.a().f();
        f.a((byte) i);
        f.a(i2);
        f.b(i3);
        f.b(str);
        f.c(i4);
        hg.a().a(f);
    }

    public static synchronized void a(int i, int i2, String str, int i3) {
        synchronized (hi.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int i4 = (i << 24) | i2;
            if (a.f12775a.containsKey(Integer.valueOf(i4))) {
                fk f = hg.a().f();
                f.a(i2);
                f.b((int) (currentTimeMillis - a.f12775a.get(Integer.valueOf(i4)).longValue()));
                f.b(str);
                if (i3 > -1) {
                    f.c(i3);
                }
                hg.a().a(f);
                a.f12775a.remove(Integer.valueOf(i2));
            } else {
                b.d("stats key not found");
            }
        }
    }

    public static void a(XMPushService xMPushService, am.b bVar) {
        new hb(xMPushService, bVar).a();
    }

    public static void a(String str, int i, Exception exc) {
        fk f = hg.a().f();
        if (i > 0) {
            f.a(fj.GSLB_REQUEST_SUCCESS.a());
            f.b(str);
            f.b(i);
            hg.a().a(f);
            return;
        }
        try {
            he.a a2 = he.a(exc);
            f.a(a2.f12769a.a());
            f.c(a2.b);
            f.b(str);
            hg.a().a(f);
        } catch (NullPointerException unused) {
        }
    }

    public static void a(String str, Exception exc) {
        try {
            he.a b = he.b(exc);
            fk f = hg.a().f();
            f.a(b.f12769a.a());
            f.c(b.b);
            f.b(str);
            hg.a().a(f);
        } catch (NullPointerException unused) {
        }
    }

    public static void b() {
        a(0, f12774a, (String) null, -1);
    }

    public static void b(String str, Exception exc) {
        try {
            he.a d = he.d(exc);
            fk f = hg.a().f();
            f.a(d.f12769a.a());
            f.c(d.b);
            f.b(str);
            hg.a().a(f);
        } catch (NullPointerException unused) {
        }
    }

    public static byte[] c() {
        fl e = hg.a().e();
        if (e != null) {
            return iy.a(e);
        }
        return null;
    }
}
