package com.xiaomi.push;

import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.aq;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class fu {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f12743a;
    private static final AtomicInteger o = new AtomicInteger(0);
    protected int b = 0;
    protected long c = -1;
    protected volatile long d = 0;
    protected volatile long e = 0;
    protected final Map<fz, a> f = new ConcurrentHashMap();
    protected final Map<fz, a> g = new ConcurrentHashMap();
    protected gg h = null;
    protected String i = "";
    protected String j = "";
    protected final int k = o.getAndIncrement();
    protected fv l;
    protected XMPushService m;
    protected long n = 0;
    private LinkedList<Pair<Integer, Long>> p = new LinkedList<>();
    private final Collection<fx> q = new CopyOnWriteArrayList();
    private int r = 2;
    private long s = 0;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private fz f12744a;
        private gh b;

        public a(fz fzVar, gh ghVar) {
            this.f12744a = fzVar;
            this.b = ghVar;
        }

        public void a(fn fnVar) {
            this.f12744a.a(fnVar);
        }

        public void a(gl glVar) {
            if (this.b == null || this.b.a(glVar)) {
                this.f12744a.a(glVar);
            }
        }
    }

    static {
        f12743a = false;
        try {
            f12743a = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception unused) {
        }
        ga.a();
    }

    protected fu(XMPushService xMPushService, fv fvVar) {
        this.l = fvVar;
        this.m = xMPushService;
        h();
    }

    private String a(int i2) {
        return i2 == 1 ? "connected" : i2 == 0 ? "connecting" : i2 == 2 ? "disconnected" : "unknown";
    }

    private void b(int i2) {
        synchronized (this.p) {
            if (i2 == 1) {
                try {
                    this.p.clear();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                this.p.add(new Pair(Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis())));
                if (this.p.size() > 6) {
                    this.p.remove(0);
                }
            }
        }
    }

    public void a(int i2, int i3, Exception exc) {
        if (i2 != this.r) {
            b.a(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{a(this.r), a(i2), aq.a(i3)}));
        }
        if (az.c(this.m)) {
            b(i2);
        }
        if (i2 == 1) {
            this.m.a(10);
            if (this.r != 0) {
                b.a("try set connected while not connecting.");
            }
            this.r = i2;
            for (fx a2 : this.q) {
                a2.a(this);
            }
        } else if (i2 == 0) {
            if (this.r != 2) {
                b.a("try set connecting while not disconnected.");
            }
            this.r = i2;
            for (fx b2 : this.q) {
                b2.b(this);
            }
        } else if (i2 == 2) {
            this.m.a(10);
            if (this.r == 0) {
                for (fx a3 : this.q) {
                    a3.a(this, exc == null ? new CancellationException("disconnect while connecting") : exc);
                }
            } else if (this.r == 1) {
                for (fx a4 : this.q) {
                    a4.a(this, i3, exc);
                }
            }
            this.r = i2;
        }
    }

    public void a(fx fxVar) {
        if (fxVar != null && !this.q.contains(fxVar)) {
            this.q.add(fxVar);
        }
    }

    public void a(fz fzVar, gh ghVar) {
        if (fzVar != null) {
            this.f.put(fzVar, new a(fzVar, ghVar));
            return;
        }
        throw new NullPointerException("Packet listener is null.");
    }

    public abstract void a(gl glVar);

    public abstract void a(am.b bVar);

    public synchronized void a(String str) {
        if (this.r == 0) {
            b.a("setChallenge hash = " + be.a(str).substring(0, 8));
            this.i = str;
            a(1, 0, (Exception) null);
        } else {
            b.a("ignore setChallenge because connection was disconnected");
        }
    }

    public abstract void a(String str, String str2);

    public abstract void a(fn[] fnVarArr);

    public synchronized boolean a(long j2) {
        return this.s >= j2;
    }

    public abstract void b(int i2, Exception exc);

    public abstract void b(fn fnVar);

    public void b(fx fxVar) {
        this.q.remove(fxVar);
    }

    public void b(fz fzVar, gh ghVar) {
        if (fzVar != null) {
            this.g.put(fzVar, new a(fzVar, ghVar));
            return;
        }
        throw new NullPointerException("Packet listener is null.");
    }

    public abstract void b(boolean z);

    public boolean b() {
        return false;
    }

    public fv d() {
        return this.l;
    }

    public String e() {
        return this.l.c();
    }

    public String f() {
        return this.l.b();
    }

    public long g() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void h() {
        String str;
        if (this.l.a() && this.h == null) {
            Class<?> cls = null;
            try {
                str = System.getProperty("smack.debuggerClass");
            } catch (Throwable unused) {
                str = null;
            }
            if (str != null) {
                try {
                    cls = Class.forName(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (cls == null) {
                this.h = new bp(this);
                return;
            }
            try {
                this.h = (gg) cls.getConstructor(new Class[]{fu.class, Writer.class, Reader.class}).newInstance(new Object[]{this});
            } catch (Exception e3) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e3);
            }
        }
    }

    public boolean i() {
        return this.r == 0;
    }

    public boolean j() {
        return this.r == 1;
    }

    public int k() {
        return this.b;
    }

    public int l() {
        return this.r;
    }

    public synchronized void m() {
        this.s = System.currentTimeMillis();
    }

    public synchronized boolean n() {
        return System.currentTimeMillis() - this.s < ((long) ga.b());
    }

    public synchronized boolean o() {
        boolean z;
        z = true;
        if (System.currentTimeMillis() - this.n >= ((long) (ga.b() << 1))) {
            z = false;
        }
        return z;
    }

    public void p() {
        synchronized (this.p) {
            this.p.clear();
        }
    }
}
