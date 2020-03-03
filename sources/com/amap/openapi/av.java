package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.amap.location.collection.CollectionConfig;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.network.HttpRequest;
import com.amap.location.common.network.HttpResponse;
import com.amap.location.common.network.IHttpClient;
import com.amap.openapi.bj;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class av {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f4613a;
    private Handler b;
    /* access modifiers changed from: private */
    public CollectionConfig c;
    private SharedPreferences d;
    /* access modifiers changed from: private */
    public ConnectivityManager e;
    private BroadcastReceiver f;
    /* access modifiers changed from: private */
    public t g;
    /* access modifiers changed from: private */
    public k h;
    /* access modifiers changed from: private */
    public IHttpClient i;
    private bj j = new bj();
    private a k = new a();
    private Looper l;

    private class a implements bj.a {
        private boolean b;

        private a() {
            this.b = true;
        }

        public Object a(long j) {
            av avVar;
            boolean z;
            if (this.b) {
                avVar = av.this;
                z = true;
            } else {
                avVar = av.this;
                z = false;
            }
            return avVar.a(z, 10000, j);
        }

        public void a() {
        }

        public void a(int i) {
            ALLog.d("@_3_3_@", "@_3_3_2_@" + i);
        }

        public void a(int i, Object obj) {
            av.this.a(i, obj);
        }

        public boolean a(Object obj) {
            au auVar = (au) obj;
            byte[] a2 = av.this.h.a(av.this.f4613a, av.this.c, auVar);
            boolean z = false;
            if (a2 != null) {
                byte[] bArr = null;
                try {
                    boolean z2 = auVar.b.get(0).b() == 0;
                    HashMap hashMap = new HashMap();
                    hashMap.put("Content-Type", "application/octet-stream");
                    HttpRequest httpRequest = new HttpRequest();
                    httpRequest.b = hashMap;
                    httpRequest.c = a2;
                    httpRequest.f4591a = z2 ? CollectionConfig.f4559a ? "http://aps.testing.amap.com/collection/collectData?src=baseCol&ver=v74&" : "http://cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&" : CollectionConfig.f4559a ? "http://aps.testing.amap.com/collection/collectData?src=extCol&ver=v74&" : "http://cgicol.amap.com/collection/collectData?src=extCol&ver=v74&";
                    HttpResponse a3 = av.this.i.a(httpRequest);
                    if (a3 != null && a3.f4592a == 200) {
                        bArr = a3.c;
                    }
                    if (bArr != null && "true".equals(new String(bArr, "UTF-8"))) {
                        z = true;
                    }
                    StringBuilder sb = new StringBuilder("@_3_3_1_@");
                    sb.append(bArr != null ? new String(bArr, "UTF-8") : "null");
                    ALLog.d("@_3_3_@", sb.toString());
                } catch (Exception unused) {
                }
            }
            return z;
        }

        public void b() {
        }

        public void b(Object obj) {
            av.this.a((au) obj);
            this.b = !this.b;
        }

        public boolean b(int i) {
            if (i == 1) {
                return true;
            }
            if (i == 0) {
                return av.this.c.i().b();
            }
            return false;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
            if (com.amap.openapi.av.b(r3.f4615a).d() > 512000) goto L_0x0014;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:
            if (com.amap.openapi.av.b(r3.f4615a).c() > 512000) goto L_0x0014;
         */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0060  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long c() {
            /*
                r3 = this;
                boolean r0 = r3.b
                r1 = 512000(0x7d000, float:7.17465E-40)
                if (r0 != 0) goto L_0x0026
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.d()
                r2 = 1
                if (r0 > 0) goto L_0x0017
            L_0x0014:
                r3.b = r2
                goto L_0x0043
            L_0x0017:
                if (r0 >= r1) goto L_0x0043
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.c()
                if (r0 <= r1) goto L_0x0043
                goto L_0x0014
            L_0x0026:
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.c()
                r2 = 0
                if (r0 > 0) goto L_0x0034
                goto L_0x0014
            L_0x0034:
                if (r0 >= r1) goto L_0x0043
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.d()
                if (r0 <= r1) goto L_0x0043
                goto L_0x0014
            L_0x0043:
                boolean r0 = r3.b
                if (r0 == 0) goto L_0x0052
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.c()
                goto L_0x005c
            L_0x0052:
                com.amap.openapi.av r0 = com.amap.openapi.av.this
                com.amap.openapi.t r0 = r0.g
                int r0 = r0.d()
            L_0x005c:
                r2 = 4000(0xfa0, float:5.605E-42)
                if (r0 <= r2) goto L_0x0063
                r0 = 512000(0x7d000, float:7.17465E-40)
            L_0x0063:
                long r0 = (long) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.av.a.c():long");
        }

        public long c(int i) {
            return av.this.a(i);
        }

        public int d() {
            return av.this.c.i().e();
        }

        public long d(int i) {
            return i == 1 ? 512000 : 51200;
        }

        public long e() {
            return 300000;
        }

        public int f() {
            return 20000;
        }

        public void g() {
        }

        public Executor h() {
            return null;
        }
    }

    public av(Context context, Looper looper, t tVar, IHttpClient iHttpClient, CollectionConfig collectionConfig) {
        this.f4613a = context;
        this.l = looper;
        this.g = tVar;
        this.i = iHttpClient;
        this.c = collectionConfig;
        this.e = (ConnectivityManager) context.getSystemService("connectivity");
        this.h = new k();
        this.d = context.getSharedPreferences("AMAP_LOCATION_COLLECTOR", 0);
        if (!e()) {
            f();
        }
    }

    private void a(String str, int i2) {
        SharedPreferences.Editor edit = this.d.edit();
        edit.putInt(str, i2);
        if (Build.VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    private void c() {
        this.f = new BroadcastReceiver() {
            /* JADX WARNING: Can't wrap try/catch for region: R(7:0|1|2|(1:6)|7|8|(1:16)(2:11|13)) */
            /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0014 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r1, android.content.Intent r2) {
                /*
                    r0 = this;
                    r1 = 1
                    com.amap.openapi.av r2 = com.amap.openapi.av.this     // Catch:{ Throwable -> 0x0014 }
                    android.net.ConnectivityManager r2 = r2.e     // Catch:{ Throwable -> 0x0014 }
                    android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()     // Catch:{ Throwable -> 0x0014 }
                    if (r2 == 0) goto L_0x0013
                    boolean r2 = r2.isConnected()     // Catch:{ Throwable -> 0x0014 }
                    if (r2 != 0) goto L_0x0014
                L_0x0013:
                    r1 = 0
                L_0x0014:
                    boolean r2 = r0.isInitialStickyBroadcast()     // Catch:{ Throwable -> 0x0021 }
                    if (r2 != 0) goto L_0x0021
                    if (r1 == 0) goto L_0x0021
                    com.amap.openapi.av r1 = com.amap.openapi.av.this     // Catch:{ Throwable -> 0x0021 }
                    r1.d()     // Catch:{ Throwable -> 0x0021 }
                L_0x0021:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.av.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.f4613a.registerReceiver(this.f, intentFilter, (String) null, this.b);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        try {
            if (g()) {
                this.j.a(500);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean e() {
        return Calendar.getInstance().get(6) == this.d.getInt("today_value", 0);
    }

    private void f() {
        a("today_value", Calendar.getInstance().get(6));
        a("uploaded_wifi_size", 0);
        a("uploaded_gprs_size", 0);
    }

    private boolean g() {
        return be.a(this.f4613a) || this.c.i().b();
    }

    public synchronized long a(int i2) {
        int i3;
        int d2;
        SharedPreferences sharedPreferences;
        String str;
        i3 = 0;
        if (i2 == 1) {
            try {
                if (!e()) {
                    f();
                }
                d2 = this.c.i().c();
                sharedPreferences = this.d;
                str = "uploaded_wifi_size";
            } catch (Throwable th) {
                throw th;
            }
        } else {
            if (i2 == 0) {
                if (!e()) {
                    f();
                }
                d2 = this.c.i().d();
                sharedPreferences = this.d;
                str = "uploaded_gprs_size";
            }
        }
        i3 = d2 - sharedPreferences.getInt(str, 0);
        return (long) i3;
    }

    public synchronized au a(boolean z, int i2, long j2) {
        return this.g.a(z, i2, j2);
    }

    public void a() {
        this.b = new Handler(this.l);
        this.j.a(this.f4613a, (bj.a) this.k, this.l);
        c();
        d();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r4, java.lang.Object r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 != 0) goto L_0x0005
            monitor-exit(r3)
            return
        L_0x0005:
            boolean r0 = r3.e()     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x000e
            r3.f()     // Catch:{ all -> 0x003e }
        L_0x000e:
            com.amap.openapi.au r5 = (com.amap.openapi.au) r5     // Catch:{ all -> 0x003e }
            r0 = 1
            r1 = 0
            if (r4 != r0) goto L_0x0028
            java.lang.String r4 = "uploaded_wifi_size"
            android.content.SharedPreferences r0 = r3.d     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "uploaded_wifi_size"
            int r0 = r0.getInt(r2, r1)     // Catch:{ all -> 0x003e }
            int r5 = r5.c     // Catch:{ all -> 0x003e }
            int r0 = r0 + r5
            r3.a((java.lang.String) r4, (int) r0)     // Catch:{ all -> 0x003e }
            monitor-exit(r3)
            return
        L_0x0028:
            if (r4 != 0) goto L_0x003c
            java.lang.String r4 = "uploaded_gprs_size"
            android.content.SharedPreferences r0 = r3.d     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "uploaded_gprs_size"
            int r0 = r0.getInt(r2, r1)     // Catch:{ all -> 0x003e }
            int r5 = r5.c     // Catch:{ all -> 0x003e }
            int r0 = r0 + r5
            r3.a((java.lang.String) r4, (int) r0)     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r3)
            return
        L_0x003e:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.av.a(int, java.lang.Object):void");
    }

    public synchronized void a(au auVar) {
        if (auVar != null) {
            this.g.a(auVar);
        }
    }

    public void b() {
        try {
            this.j.a();
            if (this.f != null) {
                this.f4613a.unregisterReceiver(this.f);
                this.f = null;
            }
        } catch (Throwable unused) {
        }
        this.b.removeCallbacksAndMessages((Object) null);
        this.b = null;
    }
}
