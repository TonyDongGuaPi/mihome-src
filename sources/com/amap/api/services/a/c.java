package com.amap.api.services.a;

import android.content.Context;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static c f4364a;
    private final Context b;
    private final String c = i.a("amap_device_adiu");
    private String d;

    public c(Context context) {
        this.b = context.getApplicationContext();
    }

    public static c a(Context context) {
        if (f4364a == null) {
            synchronized (c.class) {
                if (f4364a == null) {
                    f4364a = new c(context);
                }
            }
        }
        return f4364a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x008f }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008f }
            r1 = 1
            if (r0 == 0) goto L_0x008d
            java.lang.String r0 = com.amap.api.services.a.g.a()     // Catch:{ all -> 0x008f }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008f }
            if (r0 != 0) goto L_0x0016
            goto L_0x008d
        L_0x0016:
            android.content.Context r0 = r5.b     // Catch:{ all -> 0x008f }
            com.amap.api.services.a.d r0 = com.amap.api.services.a.d.a((android.content.Context) r0)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r5.c     // Catch:{ all -> 0x008f }
            r0.a((java.lang.String) r2)     // Catch:{ all -> 0x008f }
            android.content.Context r0 = r5.b     // Catch:{ all -> 0x008f }
            com.amap.api.services.a.d r0 = com.amap.api.services.a.d.a((android.content.Context) r0)     // Catch:{ all -> 0x008f }
            java.util.List r0 = r0.a()     // Catch:{ all -> 0x008f }
            r2 = 0
            if (r0 == 0) goto L_0x008b
            int r3 = r0.size()     // Catch:{ all -> 0x008f }
            if (r3 <= 0) goto L_0x008b
            java.lang.Object r3 = r0.get(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x008f }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x008f }
            if (r4 != 0) goto L_0x008b
            r5.d = r3     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r5.d     // Catch:{ all -> 0x008f }
            com.amap.api.services.a.g.a(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = ""
            int r3 = r0.size()     // Catch:{ all -> 0x008f }
            if (r3 <= r1) goto L_0x005c
            java.lang.Object r3 = r0.get(r1)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x008f }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x008f }
            if (r4 != 0) goto L_0x005c
            r2 = r3
        L_0x005c:
            int r3 = r0.size()     // Catch:{ all -> 0x008f }
            r4 = 2
            if (r3 <= r4) goto L_0x0080
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x008f }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008f }
            if (r3 != 0) goto L_0x0080
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r2.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ all -> 0x008f }
            r2.append(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008f }
        L_0x0080:
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x008f }
            if (r0 != 0) goto L_0x0089
            com.amap.api.services.a.g.b(r2)     // Catch:{ all -> 0x008f }
        L_0x0089:
            monitor-exit(r5)
            return r1
        L_0x008b:
            monitor-exit(r5)
            return r2
        L_0x008d:
            monitor-exit(r5)
            return r1
        L_0x008f:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.c.a():boolean");
    }

    public String b() {
        return g.a();
    }

    public void a(String str) {
        d.a(this.b).a(this.c);
        d.a(this.b).b(str);
    }
}
