package com.amap.openapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.util.d;
import com.amap.location.security.Core;
import com.coloros.mcssdk.mode.CommandMessage;
import com.loc.fc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private d f4648a;
    private Context b;
    /* access modifiers changed from: private */
    public Handler c;
    /* access modifiers changed from: private */
    public a d;
    /* access modifiers changed from: private */
    public ReentrantReadWriteLock e = new ReentrantReadWriteLock();
    private final List<f> f = new ArrayList();
    private a g;
    /* access modifiers changed from: private */
    public boolean h;
    private Runnable i = new Runnable() {
        public void run() {
            c.this.e();
        }
    };

    private final class a extends HandlerThread {

        /* renamed from: a  reason: collision with root package name */
        protected volatile boolean f4652a;

        public a(String str, int i) {
            super(str, i);
        }

        /* access modifiers changed from: protected */
        public final void onLooperPrepared() {
            c.this.e.writeLock().lock();
            try {
                if (this.f4652a) {
                    Looper looper = getLooper();
                    if (looper != null) {
                        looper.quit();
                    }
                    return;
                }
                Handler unused = c.this.c = new Handler(Looper.myLooper());
                c.this.e.writeLock().unlock();
                try {
                    c.this.b();
                    c.this.c();
                } catch (Throwable unused2) {
                }
            } finally {
                c.this.e.writeLock().unlock();
            }
        }
    }

    protected c() {
    }

    private void a(a aVar) {
        synchronized (this.f) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                this.f.get(i2).a(aVar);
            }
        }
    }

    private void a(String str) {
        String str2;
        String str3;
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("LocationCloudConfig", 0);
        a aVar = new a();
        if (aVar.a(str)) {
            long currentTimeMillis = System.currentTimeMillis();
            sharedPreferences.edit().putString(CommandMessage.COMMAND, str).putLong("lasttime", currentTimeMillis).commit();
            aVar.d = currentTimeMillis;
            this.d = aVar;
            a(aVar);
            this.e.readLock().lock();
            if (this.c != null) {
                this.c.postDelayed(this.i, this.d.f4605a);
            }
            this.e.readLock().unlock();
            str2 = "@_2_1_@";
            str3 = "@_2_1_8_@";
        } else {
            h();
            str2 = "@_2_1_@";
            str3 = "@_2_1_9_@";
        }
        ALLog.d(str2, str3);
    }

    private void a(byte[] bArr) {
        String b2 = b(bArr);
        if (b2 != null) {
            a(b2);
            return;
        }
        ALLog.d("@_2_1_@", "@_2_1_7_@");
        h();
    }

    private String b(byte[] bArr) {
        if (bArr != null) {
            try {
                byte[] xxt = Core.xxt(d.b(bArr), -1);
                if (xxt == null) {
                    return null;
                }
                String intern = new String(xxt, "utf-8").intern();
                ALLog.d("@_2_1_@", "@_2_1_10_@" + intern);
                if (e.a(intern)) {
                    return intern;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void b() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("LocationCloudConfig", 0);
        String string = sharedPreferences.getString(CommandMessage.COMMAND, "");
        long j = sharedPreferences.getLong("lasttime", 0);
        if (!TextUtils.isEmpty(string) && e.a(string)) {
            a aVar = new a();
            if (aVar.a(string)) {
                aVar.d = j;
                this.d = aVar;
                a(aVar);
                ALLog.d("@_2_1_@", "@_2_1_3_@");
                return;
            }
        }
        ALLog.d("@_2_1_@", "@_2_1_4_@");
        g();
    }

    /* access modifiers changed from: private */
    public void c() {
        this.e.readLock().lock();
        try {
            if (this.c != null) {
                if (d()) {
                    this.c.post(this.i);
                } else {
                    this.c.postDelayed(this.i, this.d.f4605a);
                }
            }
        } finally {
            this.e.readLock().unlock();
        }
    }

    private boolean d() {
        if (this.d == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.d.d;
        return currentTimeMillis >= this.d.f4605a || currentTimeMillis < 0;
    }

    /* access modifiers changed from: private */
    public void e() {
        ALLog.d("@_2_1_@", "@_2_1_5_@");
        byte[] f2 = f();
        if (f2 != null) {
            a(e.a(d.f4680a ? "http://aps.testing.amap.com/conf/r?type=3&mid=300&sver=140" : "http://control.aps.amap.com/conf/r?type=3&mid=300&sver=140", f2, this.f4648a));
            return;
        }
        ALLog.d("@_2_1_@", "@_2_1_6_@");
        h();
    }

    private byte[] f() {
        try {
            fc fcVar = new fc();
            int a2 = fcVar.a((CharSequence) this.b.getPackageName());
            int a3 = fcVar.a((CharSequence) this.f4648a.b());
            int a4 = fcVar.a((CharSequence) com.amap.location.common.a.c(this.b));
            String e2 = this.f4648a.e();
            if (TextUtils.isEmpty(e2)) {
                e2 = com.amap.location.common.a.b(this.b);
            }
            int a5 = fcVar.a((CharSequence) e2);
            int a6 = fcVar.a((CharSequence) com.amap.location.common.a.a(this.b));
            int a7 = fcVar.a((CharSequence) com.amap.location.common.a.d(this.b));
            int a8 = fcVar.a((CharSequence) com.amap.location.common.a.c());
            int a9 = fcVar.a((CharSequence) com.amap.location.common.a.b());
            int a10 = fcVar.a((CharSequence) this.f4648a.d());
            int a11 = fcVar.a((CharSequence) this.f4648a.c());
            bl.a(fcVar);
            bl.a(fcVar, this.f4648a.a());
            bl.a(fcVar, a2);
            bl.b(fcVar, a3);
            bl.b(fcVar, (byte) com.amap.location.common.a.d());
            bl.c(fcVar, a4);
            bl.d(fcVar, a5);
            bl.e(fcVar, a6);
            bl.f(fcVar, a7);
            bl.a(fcVar, com.amap.location.common.a.e(this.b));
            bl.g(fcVar, a8);
            bl.h(fcVar, a9);
            bl.i(fcVar, a10);
            bl.j(fcVar, a11);
            fcVar.h(bl.b(fcVar));
            return Core.xxt(fcVar.f(), 1);
        } catch (Error | Exception unused) {
            return null;
        }
    }

    private void g() {
        this.h = true;
        synchronized (this.f) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                this.f.get(i2).a();
            }
        }
    }

    private void h() {
        this.e.readLock().lock();
        try {
            if (this.c != null) {
                this.c.postDelayed(this.i, 3600000);
            }
        } finally {
            this.e.readLock().unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        ALLog.d("@_2_1_@", "@_2_1_2_@");
        if (this.g != null) {
            this.g.f4652a = true;
        }
        this.e.writeLock().lock();
        final Handler handler = this.c;
        this.c = null;
        this.e.writeLock().unlock();
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
            handler.post(new Runnable() {
                public void run() {
                    Looper looper = handler.getLooper();
                    if (looper != null) {
                        looper.quit();
                    }
                }
            });
        }
        synchronized (this.f) {
            this.f.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Context context, d dVar) {
        ALLog.d("@_2_1_@", "@_2_1_1_@");
        this.b = context;
        this.f4648a = dVar;
        this.g = new a("LocationCloudScheduler", 10);
        this.g.f4652a = false;
        this.g.start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void a(final f fVar) {
        if (fVar != null) {
            synchronized (this.f) {
                if (!this.f.contains(fVar)) {
                    this.e.readLock().lock();
                    try {
                        Handler handler = this.c;
                        if (handler != null) {
                            handler.post(new Runnable() {
                                public void run() {
                                    if (c.this.d != null) {
                                        a aVar = new a();
                                        aVar.e = c.this.d.e;
                                        aVar.b = c.this.d.b;
                                        fVar.a(aVar);
                                    } else if (c.this.h) {
                                        fVar.a();
                                    }
                                }
                            });
                        }
                        this.e.readLock().unlock();
                        this.f.add(fVar);
                    } catch (Throwable th) {
                        this.e.readLock().unlock();
                        throw th;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(f fVar) {
        if (fVar != null) {
            synchronized (this.f) {
                if (this.f.contains(fVar)) {
                    this.f.remove(fVar);
                }
            }
        }
    }
}
