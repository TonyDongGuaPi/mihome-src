package com.amap.location.collection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.common.util.f;
import com.amap.openapi.as;
import com.amap.openapi.at;
import com.amap.openapi.au;
import com.amap.openapi.av;
import com.amap.openapi.bf;
import com.amap.openapi.bg;
import com.amap.openapi.k;
import com.amap.openapi.m;
import com.amap.openapi.n;
import com.amap.openapi.t;
import java.util.List;

public class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f4565a;
    /* access modifiers changed from: private */
    public CollectionConfig b;
    /* access modifiers changed from: private */
    public IHttpClient c;
    /* access modifiers changed from: private */
    public t d;
    /* access modifiers changed from: private */
    public av e;
    private m f;
    private n g;
    /* access modifiers changed from: private */
    public HandlerThread h;
    /* access modifiers changed from: private */
    public volatile b i;
    /* access modifiers changed from: private */
    public Looper j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public final Object l = new Object();
    /* access modifiers changed from: private */
    public C0036a m;
    private k n;
    private bg o;
    private bf p;

    /* renamed from: com.amap.location.collection.a$a  reason: collision with other inner class name */
    private class C0036a extends BroadcastReceiver {
        private C0036a() {
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action != null) {
                    char c = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != -2128145023) {
                        if (hashCode == -1454123155) {
                            if (action.equals("android.intent.action.SCREEN_ON")) {
                                c = 1;
                            }
                        }
                    } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            if (a.this.b.f()) {
                                a.this.f();
                                return;
                            }
                            return;
                        case 1:
                            if (a.this.b.f()) {
                                a.this.e();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (a.this.m != null) {
                    try {
                        a.this.f4565a.unregisterReceiver(a.this.m);
                        C0036a unused = a.this.m = null;
                    } catch (Throwable unused2) {
                    }
                }
                a.this.f();
                removeCallbacksAndMessages((Object) null);
                a.this.e.b();
                a.this.d.b();
                post(new Runnable() {
                    public void run() {
                        try {
                            a.this.h.quit();
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        }
    }

    public a(Context context, CollectionConfig collectionConfig, IHttpClient iHttpClient) {
        this.f4565a = context;
        this.b = collectionConfig;
        this.c = iHttpClient;
    }

    /* access modifiers changed from: private */
    public void a(Location location, List<ScanResult> list, long j2, long j3) {
        try {
            g();
            if (this.b.g().a()) {
                this.f.a(location, list, j2, j3);
            }
            if (this.b.h().b()) {
                this.g.a(location);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean d() {
        return this.b.g().a() || this.b.h().b();
    }

    /* access modifiers changed from: private */
    public void e() {
        long j2;
        if (this.p == null) {
            boolean a2 = this.b.g().a();
            boolean b2 = this.b.h().b();
            long j3 = 0;
            int i2 = 0;
            if (a2) {
                j3 = 1000;
                i2 = 10;
            }
            if (b2) {
                j2 = a2 ? Math.min(j2, 2000) : 2000;
                i2 = a2 ? Math.min(i2, 5) : 5;
            }
            try {
                this.p = new bf() {
                    public void a(Location location, List<ScanResult> list, long j, long j2) {
                        a.this.a(location, list, j, j2);
                    }
                };
                if (this.o == null) {
                    this.o = new bg(this.f4565a, this.b.g(), this.p, this.j);
                }
                this.o.a("passive", j2, (float) i2);
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        try {
            if (this.p != null && this.o != null) {
                this.o.c();
                this.o.a();
                this.p = null;
                h();
                as.a();
            }
        } catch (Throwable unused) {
        }
    }

    private void g() {
        if (this.b.g().a() && this.f == null) {
            this.f = new m(this.f4565a, this.d, this.b.g(), this.j);
            this.f.a();
        }
        if (this.b.h().b() && this.g == null) {
            this.g = new n(this.f4565a, this.d, this.b.h(), this.j);
            this.g.a();
        }
    }

    private void h() {
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public void a() {
        if (d()) {
            this.h = new HandlerThread("collection") {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x00cd */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x00d9 A[Catch:{ Throwable -> 0x00e3 }, RETURN] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onLooperPrepared() {
                    /*
                        r8 = this;
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.os.Looper r1 = r8.getLooper()     // Catch:{ Throwable -> 0x00e3 }
                        android.os.Looper unused = r0.j = r1     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.t r1 = new com.amap.openapi.t     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r2 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.content.Context r2 = r2.f4565a     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r3 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.os.Looper r3 = r3.j     // Catch:{ Throwable -> 0x00e3 }
                        r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.t unused = r0.d = r1     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.t r0 = r0.d     // Catch:{ Throwable -> 0x00e3 }
                        r0.a()     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.av r7 = new com.amap.openapi.av     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.content.Context r2 = r1.f4565a     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.os.Looper r3 = r1.j     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.t r4 = r1.d     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.common.network.IHttpClient r5 = r1.c     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.CollectionConfig r6 = r1.b     // Catch:{ Throwable -> 0x00e3 }
                        r1 = r7
                        r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.av unused = r0.e = r7     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.openapi.av r0 = r0.e     // Catch:{ Throwable -> 0x00e3 }
                        r0.a()     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        java.lang.Object r0 = r0.l     // Catch:{ Throwable -> 0x00e3 }
                        monitor-enter(r0)     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a$b r2 = new com.amap.location.collection.a$b     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a r3 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a r4 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        android.os.Looper r4 = r4.j     // Catch:{ all -> 0x00e0 }
                        r2.<init>(r4)     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a.b unused = r1.i = r2     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        boolean r1 = r1.k     // Catch:{ all -> 0x00e0 }
                        if (r1 == 0) goto L_0x008f
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        r2 = 0
                        boolean unused = r1.k = r2     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a$b r1 = r1.i     // Catch:{ all -> 0x00e0 }
                        r2 = 1
                        android.os.Message r1 = r1.obtainMessage(r2)     // Catch:{ all -> 0x00e0 }
                        r1.sendToTarget()     // Catch:{ all -> 0x00e0 }
                    L_0x008f:
                        monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.CollectionConfig r0 = r0.b     // Catch:{ Throwable -> 0x00e3 }
                        boolean r0 = r0.f()     // Catch:{ Throwable -> 0x00e3 }
                        if (r0 == 0) goto L_0x00da
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a$a r1 = new com.amap.location.collection.a$a     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r2 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        r3 = 0
                        r1.<init>()     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a.C0036a unused = r0.m = r1     // Catch:{ Throwable -> 0x00e3 }
                        android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch:{ Throwable -> 0x00e3 }
                        r0.<init>()     // Catch:{ Throwable -> 0x00e3 }
                        java.lang.String r1 = "android.intent.action.SCREEN_ON"
                        r0.addAction(r1)     // Catch:{ Throwable -> 0x00e3 }
                        java.lang.String r1 = "android.intent.action.SCREEN_OFF"
                        r0.addAction(r1)     // Catch:{ Throwable -> 0x00e3 }
                        com.amap.location.collection.a r1 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00cd }
                        android.content.Context r1 = r1.f4565a     // Catch:{ Throwable -> 0x00cd }
                        com.amap.location.collection.a r2 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00cd }
                        com.amap.location.collection.a$a r2 = r2.m     // Catch:{ Throwable -> 0x00cd }
                        com.amap.location.collection.a r4 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00cd }
                        com.amap.location.collection.a$b r4 = r4.i     // Catch:{ Throwable -> 0x00cd }
                        r1.registerReceiver(r2, r0, r3, r4)     // Catch:{ Throwable -> 0x00cd }
                    L_0x00cd:
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        android.content.Context r0 = r0.f4565a     // Catch:{ Throwable -> 0x00e3 }
                        boolean r0 = com.amap.openapi.az.c(r0)     // Catch:{ Throwable -> 0x00e3 }
                        if (r0 != 0) goto L_0x00da
                        return
                    L_0x00da:
                        com.amap.location.collection.a r0 = com.amap.location.collection.a.this     // Catch:{ Throwable -> 0x00e3 }
                        r0.e()     // Catch:{ Throwable -> 0x00e3 }
                        return
                    L_0x00e0:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
                        throw r1     // Catch:{ Throwable -> 0x00e3 }
                    L_0x00e3:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amap.location.collection.a.AnonymousClass1.onLooperPrepared():void");
                }
            };
            this.h.start();
        }
    }

    public void a(boolean z, at atVar) {
        if (atVar != null && this.i != null) {
            try {
                au auVar = (au) atVar.b;
                this.e.a(f.a(this.f4565a), (Object) auVar);
                if (z) {
                    this.e.a(auVar);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b() {
        synchronized (this.l) {
            if (this.i != null) {
                this.i.obtainMessage(1).sendToTarget();
            } else {
                this.k = true;
            }
        }
    }

    public at c() {
        au a2;
        byte[] a3;
        if (this.i == null) {
            return null;
        }
        try {
            if (this.n == null) {
                this.n = new k();
            }
            if (this.e.a(f.a(this.f4565a)) <= 0 || (a2 = this.e.a(true, 1, 1024)) == null || a2.b.size() <= 0 || (a3 = this.n.a(this.f4565a, this.b, a2)) == null) {
                return null;
            }
            at atVar = new at();
            try {
                atVar.f4611a = a3;
                atVar.b = a2;
            } catch (Throwable unused) {
            }
            return atVar;
        } catch (Throwable unused2) {
            return null;
        }
    }
}
