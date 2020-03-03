package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.taobao.weex.common.Constants;

public final class r {
    static ej b;
    static av e;
    static long g;

    /* renamed from: a  reason: collision with root package name */
    String f6634a = null;
    ej c = null;
    ej d = null;
    long f = 0;
    boolean h = false;
    private Context i;

    public r(Context context) {
        this.i = context.getApplicationContext();
    }

    private void e() {
        if (b == null || fa.c() - g > 180000) {
            ej f2 = f();
            g = fa.c();
            if (f2 != null && fa.a(f2.a())) {
                b = f2;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        r2 = com.loc.eh.d(r2, r6.f6634a);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.loc.ej f() {
        /*
            r6 = this;
            android.content.Context r0 = r6.i
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r6.a()
            com.loc.av r0 = e     // Catch:{ Throwable -> 0x008c }
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            com.loc.av r0 = e     // Catch:{ Throwable -> 0x008c }
            java.lang.String r2 = "_id=1"
            java.lang.Class<com.loc.ej> r3 = com.loc.ej.class
            r4 = 0
            java.util.List r0 = r0.a((java.lang.String) r2, r3, (boolean) r4)     // Catch:{ Throwable -> 0x008c }
            int r2 = r0.size()     // Catch:{ Throwable -> 0x008c }
            if (r2 <= 0) goto L_0x006a
            java.lang.Object r0 = r0.get(r4)     // Catch:{ Throwable -> 0x008c }
            com.loc.ej r0 = (com.loc.ej) r0     // Catch:{ Throwable -> 0x008c }
            java.lang.String r2 = r0.c()     // Catch:{ Throwable -> 0x008a }
            byte[] r2 = com.loc.y.b((java.lang.String) r2)     // Catch:{ Throwable -> 0x008a }
            if (r2 == 0) goto L_0x0045
            int r3 = r2.length     // Catch:{ Throwable -> 0x008a }
            if (r3 <= 0) goto L_0x0045
            java.lang.String r3 = r6.f6634a     // Catch:{ Throwable -> 0x008a }
            byte[] r2 = com.loc.eh.d(r2, r3)     // Catch:{ Throwable -> 0x008a }
            if (r2 == 0) goto L_0x0045
            int r3 = r2.length     // Catch:{ Throwable -> 0x008a }
            if (r3 <= 0) goto L_0x0045
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x008a }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r2, r4)     // Catch:{ Throwable -> 0x008a }
            goto L_0x0046
        L_0x0045:
            r3 = r1
        L_0x0046:
            java.lang.String r2 = r0.b()     // Catch:{ Throwable -> 0x008a }
            byte[] r2 = com.loc.y.b((java.lang.String) r2)     // Catch:{ Throwable -> 0x008a }
            if (r2 == 0) goto L_0x0065
            int r4 = r2.length     // Catch:{ Throwable -> 0x008a }
            if (r4 <= 0) goto L_0x0065
            java.lang.String r4 = r6.f6634a     // Catch:{ Throwable -> 0x008a }
            byte[] r2 = com.loc.eh.d(r2, r4)     // Catch:{ Throwable -> 0x008a }
            if (r2 == 0) goto L_0x0065
            int r4 = r2.length     // Catch:{ Throwable -> 0x008a }
            if (r4 <= 0) goto L_0x0065
            java.lang.String r1 = new java.lang.String     // Catch:{ Throwable -> 0x008a }
            java.lang.String r4 = "UTF-8"
            r1.<init>(r2, r4)     // Catch:{ Throwable -> 0x008a }
        L_0x0065:
            r0.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x008a }
            r1 = r3
            goto L_0x006b
        L_0x006a:
            r0 = r1
        L_0x006b:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x008a }
            if (r2 != 0) goto L_0x0097
            com.amap.api.location.AMapLocation r2 = new com.amap.api.location.AMapLocation     // Catch:{ Throwable -> 0x008a }
            java.lang.String r3 = ""
            r2.<init>((java.lang.String) r3)     // Catch:{ Throwable -> 0x008a }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x008a }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x008a }
            com.loc.es.a((com.amap.api.location.AMapLocation) r2, (org.json.JSONObject) r3)     // Catch:{ Throwable -> 0x008a }
            boolean r1 = com.loc.fa.b((com.amap.api.location.AMapLocation) r2)     // Catch:{ Throwable -> 0x008a }
            if (r1 == 0) goto L_0x0097
            r0.a((com.amap.api.location.AMapLocation) r2)     // Catch:{ Throwable -> 0x008a }
            goto L_0x0097
        L_0x008a:
            r1 = move-exception
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0090:
            java.lang.String r2 = "LastLocationManager"
            java.lang.String r3 = "readLastFix"
            com.loc.es.a(r1, r2, r3)
        L_0x0097:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.r.f():com.loc.ej");
    }

    public final AMapLocation a(AMapLocation aMapLocation, String str, long j) {
        if (aMapLocation == null || aMapLocation.getErrorCode() == 0 || aMapLocation.getLocationType() == 1 || aMapLocation.getErrorCode() == 7) {
            return aMapLocation;
        }
        try {
            e();
            if (b != null) {
                if (b.a() != null) {
                    boolean z = false;
                    if (TextUtils.isEmpty(str)) {
                        long c2 = fa.c() - b.d();
                        if (c2 >= 0 && c2 <= j) {
                            z = true;
                        }
                        aMapLocation.setTrustedLevel(3);
                    } else {
                        z = fa.a(b.b(), str);
                        aMapLocation.setTrustedLevel(2);
                    }
                    if (!z) {
                        return aMapLocation;
                    }
                    AMapLocation a2 = b.a();
                    try {
                        a2.setLocationType(9);
                        a2.setFixLastLocation(true);
                        a2.setLocationDetail(aMapLocation.getLocationDetail());
                        return a2;
                    } catch (Throwable th) {
                        AMapLocation aMapLocation2 = a2;
                        th = th;
                        aMapLocation = aMapLocation2;
                        es.a(th, "LastLocationManager", "fixLastLocation");
                        return aMapLocation;
                    }
                }
            }
            return aMapLocation;
        } catch (Throwable th2) {
            th = th2;
            es.a(th, "LastLocationManager", "fixLastLocation");
            return aMapLocation;
        }
    }

    public final void a() {
        if (!this.h) {
            try {
                if (this.f6634a == null) {
                    this.f6634a = eh.a("MD5", x.u(this.i));
                }
                if (e == null) {
                    e = new av(this.i, av.a((Class<? extends au>) ek.class));
                }
            } catch (Throwable th) {
                es.a(th, "LastLocationManager", "<init>:DBOperation");
            }
            this.h = true;
        }
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.i != null && aMapLocation != null && fa.a(aMapLocation) && aMapLocation.getLocationType() != 2 && !aMapLocation.isMock() && !aMapLocation.isFixLastLocation()) {
            ej ejVar = new ej();
            ejVar.a(aMapLocation);
            if (aMapLocation.getLocationType() == 1) {
                ejVar.a((String) null);
            } else {
                ejVar.a(str);
            }
            try {
                b = ejVar;
                g = fa.c();
                this.c = ejVar;
                if ((this.d == null || fa.a(this.d.a(), ejVar.a()) > 500.0f) && fa.c() - this.f > 30000) {
                    return true;
                }
            } catch (Throwable th) {
                es.a(th, "LastLocationManager", "setLastFix");
            }
        }
        return false;
    }

    public final AMapLocation b() {
        e();
        if (b != null && fa.a(b.a())) {
            return b.a();
        }
        return null;
    }

    public final void c() {
        try {
            d();
            this.f = 0;
            this.h = false;
            this.c = null;
            this.d = null;
        } catch (Throwable th) {
            es.a(th, "LastLocationManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }

    public final void d() {
        String str;
        try {
            a();
            if (this.c != null && fa.a(this.c.a()) && e != null && this.c != this.d) {
                if (this.c.d() == 0) {
                    String str2 = this.c.a().toStr();
                    String b2 = this.c.b();
                    this.d = this.c;
                    String str3 = null;
                    if (!TextUtils.isEmpty(str2)) {
                        str = y.b(eh.c(str2.getBytes("UTF-8"), this.f6634a));
                        if (!TextUtils.isEmpty(b2)) {
                            str3 = y.b(eh.c(b2.getBytes("UTF-8"), this.f6634a));
                        }
                    } else {
                        str = null;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        ej ejVar = new ej();
                        ejVar.b(str);
                        ejVar.a(fa.c());
                        ejVar.a(str3);
                        e.a((Object) ejVar, "_id=1");
                        this.f = fa.c();
                        if (b != null) {
                            b.a(fa.c());
                        }
                    }
                }
            }
        } catch (Throwable th) {
            es.a(th, "LastLocationManager", "saveLastFix");
        }
    }
}
