package com.loc;

import android.content.Context;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static c f6530a;
    private final Context b;
    private final String c = i.a("amap_device_adiu");
    private String d;

    private c(Context context) {
        this.b = context.getApplicationContext();
    }

    public static c a(Context context) {
        if (f6530a == null) {
            synchronized (c.class) {
                if (f6530a == null) {
                    f6530a = new c(context);
                }
            }
        }
        return f6530a;
    }

    public static String b() {
        return g.a();
    }

    public final void a(String str) {
        d.a(this.b).a(this.c);
        d.a(this.b).b(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0087, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0089, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008b, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x008c }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008c }
            r1 = 1
            if (r0 == 0) goto L_0x008a
            java.lang.String r0 = com.loc.g.a()     // Catch:{ all -> 0x008c }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x0016
            goto L_0x008a
        L_0x0016:
            android.content.Context r0 = r5.b     // Catch:{ all -> 0x008c }
            com.loc.d r0 = com.loc.d.a((android.content.Context) r0)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r5.c     // Catch:{ all -> 0x008c }
            r0.a((java.lang.String) r2)     // Catch:{ all -> 0x008c }
            android.content.Context r0 = r5.b     // Catch:{ all -> 0x008c }
            com.loc.d r0 = com.loc.d.a((android.content.Context) r0)     // Catch:{ all -> 0x008c }
            java.util.List r0 = r0.a()     // Catch:{ all -> 0x008c }
            r2 = 0
            if (r0 == 0) goto L_0x0088
            int r3 = r0.size()     // Catch:{ all -> 0x008c }
            if (r3 <= 0) goto L_0x0088
            java.lang.Object r3 = r0.get(r2)     // Catch:{ all -> 0x008c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x008c }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x008c }
            if (r4 != 0) goto L_0x0088
            r5.d = r3     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r5.d     // Catch:{ all -> 0x008c }
            com.loc.g.a(r2)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = ""
            int r3 = r0.size()     // Catch:{ all -> 0x008c }
            if (r3 <= r1) goto L_0x005c
            java.lang.Object r3 = r0.get(r1)     // Catch:{ all -> 0x008c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x008c }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x008c }
            if (r4 != 0) goto L_0x005c
            r2 = r3
        L_0x005c:
            int r3 = r0.size()     // Catch:{ all -> 0x008c }
            r4 = 2
            if (r3 <= r4) goto L_0x007d
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x008c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x008c }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008c }
            if (r3 != 0) goto L_0x007d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            java.lang.String r3 = ":"
            r2.<init>(r3)     // Catch:{ all -> 0x008c }
            r2.append(r0)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008c }
        L_0x007d:
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x0086
            com.loc.g.b(r2)     // Catch:{ all -> 0x008c }
        L_0x0086:
            monitor-exit(r5)
            return r1
        L_0x0088:
            monitor-exit(r5)
            return r2
        L_0x008a:
            monitor-exit(r5)
            return r1
        L_0x008c:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.c.a():boolean");
    }
}
