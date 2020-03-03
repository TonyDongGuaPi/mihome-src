package com.loc;

import android.content.Context;

public final class en {
    private static en b;

    /* renamed from: a  reason: collision with root package name */
    bg f6591a = null;
    private Context c = null;
    private int d = 0;
    private int e = es.f;
    private boolean f = false;
    private int g = 0;

    private en(Context context) {
        try {
            z.a().a(context);
        } catch (Throwable unused) {
        }
        this.c = context;
        this.f6591a = bg.a();
    }

    public static en a(Context context) {
        if (b == null) {
            b = new en(context);
        }
        return b;
    }

    public final int a() {
        return this.d;
    }

    public final bk a(eo eoVar) throws Throwable {
        long c2 = fa.c();
        bk a2 = bg.a(eoVar, this.f || fa.k(this.c));
        this.d = Long.valueOf(fa.c() - c2).intValue();
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00c3, code lost:
        r12.put(r14, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00cc, code lost:
        r12.remove(io.reactivex.annotations.SchedulerSupport.CUSTOM);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00d1, code lost:
        r2.l = r12;
        r2.a(r10.e);
        r2.b(r10.e);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00df, code lost:
        if (r10.f != false) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00e5, code lost:
        if (com.loc.fa.k(r11) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00ed, code lost:
        if (r13.startsWith("http:") == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ef, code lost:
        r2.g = r2.c().replace("https:", "https:");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.loc.eo a(android.content.Context r11, byte[] r12, java.lang.String r13, boolean r14) {
        /*
            r10 = this;
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00fe }
            r1 = 16
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00fe }
            com.loc.eo r2 = new com.loc.eo     // Catch:{ Throwable -> 0x00fe }
            com.loc.ac r3 = com.loc.es.b()     // Catch:{ Throwable -> 0x00fe }
            r2.<init>(r11, r3)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/octet-stream"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "Accept-Encoding"
            java.lang.String r4 = "gzip"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "gzipped"
            java.lang.String r4 = "1"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "User-Agent"
            java.lang.String r4 = "AMAP_Location_SDK_Android 4.7.1"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "KEY"
            java.lang.String r4 = com.loc.u.f(r11)     // Catch:{ Throwable -> 0x0100 }
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "enginever"
            java.lang.String r4 = "5.1"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = com.loc.w.a()     // Catch:{ Throwable -> 0x0100 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r5 = "key="
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r5 = com.loc.u.f(r11)     // Catch:{ Throwable -> 0x0100 }
            r4.append(r5)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r4 = com.loc.w.a(r11, r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r5 = "ts"
            r0.put(r5, r3)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "scode"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "encr"
            java.lang.String r4 = "1"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0100 }
            r2.f = r0     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r0 = "loc"
            if (r14 != 0) goto L_0x0077
            java.lang.String r0 = "locf"
        L_0x0077:
            r3 = 1
            r2.m = r3     // Catch:{ Throwable -> 0x0100 }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r5 = "platform=Android&sdkversion=%s&product=%s&loc_channel=%s"
            r6 = 3
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x0100 }
            r8 = 0
            java.lang.String r9 = "4.7.1"
            r7[r8] = r9     // Catch:{ Throwable -> 0x0100 }
            r7[r3] = r0     // Catch:{ Throwable -> 0x0100 }
            r0 = 2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0100 }
            r7[r0] = r3     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r0 = java.lang.String.format(r4, r5, r7)     // Catch:{ Throwable -> 0x0100 }
            r2.k = r0     // Catch:{ Throwable -> 0x0100 }
            r2.j = r14     // Catch:{ Throwable -> 0x0100 }
            r2.g = r13     // Catch:{ Throwable -> 0x0100 }
            byte[] r12 = com.loc.fa.a((byte[]) r12)     // Catch:{ Throwable -> 0x0100 }
            r2.h = r12     // Catch:{ Throwable -> 0x0100 }
            java.net.Proxy r12 = com.loc.ab.a(r11)     // Catch:{ Throwable -> 0x0100 }
            r2.a((java.net.Proxy) r12)     // Catch:{ Throwable -> 0x0100 }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ Throwable -> 0x0100 }
            r12.<init>(r1)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r14 = "output"
            java.lang.String r0 = "bin"
            r12.put(r14, r0)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r14 = "policy"
            java.lang.String r0 = "3103"
            r12.put(r14, r0)     // Catch:{ Throwable -> 0x0100 }
            int r14 = r10.g     // Catch:{ Throwable -> 0x0100 }
            switch(r14) {
                case 0: goto L_0x00cc;
                case 1: goto L_0x00c7;
                case 2: goto L_0x00bf;
                default: goto L_0x00be;
            }     // Catch:{ Throwable -> 0x0100 }
        L_0x00be:
            goto L_0x00cc
        L_0x00bf:
            java.lang.String r14 = "custom"
            java.lang.String r0 = "language:en"
        L_0x00c3:
            r12.put(r14, r0)     // Catch:{ Throwable -> 0x0100 }
            goto L_0x00d1
        L_0x00c7:
            java.lang.String r14 = "custom"
            java.lang.String r0 = "language:cn"
            goto L_0x00c3
        L_0x00cc:
            java.lang.String r14 = "custom"
            r12.remove(r14)     // Catch:{ Throwable -> 0x0100 }
        L_0x00d1:
            r2.l = r12     // Catch:{ Throwable -> 0x0100 }
            int r12 = r10.e     // Catch:{ Throwable -> 0x0100 }
            r2.a((int) r12)     // Catch:{ Throwable -> 0x0100 }
            int r12 = r10.e     // Catch:{ Throwable -> 0x0100 }
            r2.b(r12)     // Catch:{ Throwable -> 0x0100 }
            boolean r12 = r10.f     // Catch:{ Throwable -> 0x0100 }
            if (r12 != 0) goto L_0x00e7
            boolean r11 = com.loc.fa.k(r11)     // Catch:{ Throwable -> 0x0100 }
            if (r11 == 0) goto L_0x0100
        L_0x00e7:
            java.lang.String r11 = "http:"
            boolean r11 = r13.startsWith(r11)     // Catch:{ Throwable -> 0x0100 }
            if (r11 == 0) goto L_0x0100
            java.lang.String r11 = r2.c()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r12 = "https:"
            java.lang.String r13 = "https:"
            java.lang.String r11 = r11.replace(r12, r13)     // Catch:{ Throwable -> 0x0100 }
            r2.g = r11     // Catch:{ Throwable -> 0x0100 }
            goto L_0x0100
        L_0x00fe:
            r11 = 0
            r2 = r11
        L_0x0100:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.en.a(android.content.Context, byte[], java.lang.String, boolean):com.loc.eo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00c9, code lost:
        if (com.loc.fa.k(r7) == false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00cb, code lost:
        r2.g = "http://restapi.amap.com/v3/geocode/regeo".replace("http:", "https:");
        r7 = com.loc.bg.a(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00da, code lost:
        r2.g = "http://restapi.amap.com/v3/geocode/regeo";
        r7 = com.loc.bg.b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return new java.lang.String(r7, "utf-8");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0047, code lost:
        r3.put(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0051, code lost:
        r3.remove("language");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0056, code lost:
        r1 = com.loc.w.a();
        r4 = com.loc.w.a(r7, r1, com.loc.ad.b((java.util.Map<java.lang.String, java.lang.String>) r3));
        r3.put("ts", r1);
        r3.put("scode", r4);
        r2.b(("output=json&radius=1000&extensions=all&location=" + r10 + "," + r8).getBytes("UTF-8"));
        r2.m = false;
        r2.j = true;
        r2.k = java.lang.String.format(java.util.Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new java.lang.Object[]{"4.7.1", "loc", 3});
        r2.l = r3;
        r2.f = r0;
        r2.a(com.loc.ab.a(r7));
        r2.a(com.loc.es.f);
        r2.b(com.loc.es.f);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(android.content.Context r7, double r8, double r10) {
        /*
            r6 = this;
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00f1 }
            r1 = 16
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00f1 }
            com.loc.eo r2 = new com.loc.eo     // Catch:{ Throwable -> 0x00f1 }
            com.loc.ac r3 = com.loc.es.b()     // Catch:{ Throwable -> 0x00f1 }
            r2.<init>(r7, r3)     // Catch:{ Throwable -> 0x00f1 }
            r0.clear()     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/x-www-form-urlencoded"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r3 = "User-Agent"
            java.lang.String r4 = "AMAP_Location_SDK_Android 4.7.1"
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Throwable -> 0x00f1 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r1 = "custom"
            java.lang.String r4 = "26260A1F00020002"
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r1 = "key"
            java.lang.String r4 = com.loc.u.f(r7)     // Catch:{ Throwable -> 0x00f1 }
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x00f1 }
            int r1 = r6.g     // Catch:{ Throwable -> 0x00f1 }
            switch(r1) {
                case 0: goto L_0x0051;
                case 1: goto L_0x004b;
                case 2: goto L_0x0043;
                default: goto L_0x0042;
            }     // Catch:{ Throwable -> 0x00f1 }
        L_0x0042:
            goto L_0x0051
        L_0x0043:
            java.lang.String r1 = "language"
            java.lang.String r4 = "en"
        L_0x0047:
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x00f1 }
            goto L_0x0056
        L_0x004b:
            java.lang.String r1 = "language"
            java.lang.String r4 = "zh-CN"
            goto L_0x0047
        L_0x0051:
            java.lang.String r1 = "language"
            r3.remove(r1)     // Catch:{ Throwable -> 0x00f1 }
        L_0x0056:
            java.lang.String r1 = com.loc.w.a()     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r4 = com.loc.ad.b((java.util.Map<java.lang.String, java.lang.String>) r3)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r4 = com.loc.w.a(r7, r1, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r5 = "ts"
            r3.put(r5, r1)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r1 = "scode"
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r4 = "output=json&radius=1000&extensions=all&location="
            r1.<init>(r4)     // Catch:{ Throwable -> 0x00f1 }
            r1.append(r10)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r10 = ","
            r1.append(r10)     // Catch:{ Throwable -> 0x00f1 }
            r1.append(r8)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r8 = r1.toString()     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r9 = "UTF-8"
            byte[] r8 = r8.getBytes(r9)     // Catch:{ Throwable -> 0x00f1 }
            r2.b(r8)     // Catch:{ Throwable -> 0x00f1 }
            r8 = 0
            r2.m = r8     // Catch:{ Throwable -> 0x00f1 }
            r9 = 1
            r2.j = r9     // Catch:{ Throwable -> 0x00f1 }
            java.util.Locale r10 = java.util.Locale.US     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r11 = "platform=Android&sdkversion=%s&product=%s&loc_channel=%s"
            r1 = 3
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r5 = "4.7.1"
            r4[r8] = r5     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r8 = "loc"
            r4[r9] = r8     // Catch:{ Throwable -> 0x00f1 }
            r8 = 2
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x00f1 }
            r4[r8] = r9     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r8 = java.lang.String.format(r10, r11, r4)     // Catch:{ Throwable -> 0x00f1 }
            r2.k = r8     // Catch:{ Throwable -> 0x00f1 }
            r2.l = r3     // Catch:{ Throwable -> 0x00f1 }
            r2.f = r0     // Catch:{ Throwable -> 0x00f1 }
            java.net.Proxy r8 = com.loc.ab.a(r7)     // Catch:{ Throwable -> 0x00f1 }
            r2.a((java.net.Proxy) r8)     // Catch:{ Throwable -> 0x00f1 }
            int r8 = com.loc.es.f     // Catch:{ Throwable -> 0x00f1 }
            r2.a((int) r8)     // Catch:{ Throwable -> 0x00f1 }
            int r8 = com.loc.es.f     // Catch:{ Throwable -> 0x00f1 }
            r2.b(r8)     // Catch:{ Throwable -> 0x00f1 }
            java.lang.String r8 = "http://restapi.amap.com/v3/geocode/regeo"
            boolean r7 = com.loc.fa.k(r7)     // Catch:{ Throwable -> 0x00e9 }
            if (r7 == 0) goto L_0x00da
            java.lang.String r7 = "http:"
            java.lang.String r9 = "https:"
            java.lang.String r7 = r8.replace(r7, r9)     // Catch:{ Throwable -> 0x00e9 }
            r2.g = r7     // Catch:{ Throwable -> 0x00e9 }
            byte[] r7 = com.loc.bg.a(r2)     // Catch:{ Throwable -> 0x00e9 }
            goto L_0x00e0
        L_0x00da:
            r2.g = r8     // Catch:{ Throwable -> 0x00e9 }
            byte[] r7 = com.loc.bg.b(r2)     // Catch:{ Throwable -> 0x00e9 }
        L_0x00e0:
            java.lang.String r8 = new java.lang.String     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r9 = "utf-8"
            r8.<init>(r7, r9)     // Catch:{ Throwable -> 0x00e9 }
            goto L_0x00f2
        L_0x00e9:
            r7 = move-exception
            java.lang.String r8 = "LocNetManager"
            java.lang.String r9 = "post"
            com.loc.es.a(r7, r8, r9)     // Catch:{ Throwable -> 0x00f1 }
        L_0x00f1:
            r8 = 0
        L_0x00f2:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.en.a(android.content.Context, double, double):java.lang.String");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r2, boolean r4, int r5) {
        /*
            r1 = this;
            r1.f = r4     // Catch:{ Throwable -> 0x0016 }
            com.loc.z r0 = com.loc.z.a()     // Catch:{ Throwable -> 0x0009 }
            r0.a((boolean) r4)     // Catch:{ Throwable -> 0x0009 }
        L_0x0009:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0016 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0016 }
            r1.e = r2     // Catch:{ Throwable -> 0x0016 }
            r1.g = r5     // Catch:{ Throwable -> 0x0016 }
            return
        L_0x0016:
            r2 = move-exception
            java.lang.String r3 = "LocNetManager"
            java.lang.String r4 = "setOption"
            com.loc.es.a(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.en.a(long, boolean, int):void");
    }
}
