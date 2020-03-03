package com.amap.openapi;

import cn.com.fmsh.communication.core.MessageHead;

public class as {

    /* renamed from: a  reason: collision with root package name */
    private static bn f4608a = new bn();

    public static class a implements bm {

        /* renamed from: a  reason: collision with root package name */
        private int f4609a;
        private int b;
        private int c;

        a(int i, int i2, int i3) {
            this.f4609a = i;
            this.b = i2;
            this.c = i3;
        }

        public long a() {
            return as.a(this.f4609a, this.b);
        }

        public int b() {
            return this.c;
        }
    }

    public static class b implements bm {

        /* renamed from: a  reason: collision with root package name */
        private long f4610a;
        private int b;

        b(long j, int i) {
            this.f4610a = j;
            this.b = i;
        }

        public long a() {
            return this.f4610a;
        }

        public int b() {
            return this.b;
        }
    }

    public static long a(int i, int i2) {
        return (((long) i2) & MessageHead.SERIAL_MAK) | ((((long) i) & MessageHead.SERIAL_MAK) << 32);
    }

    public static synchronized short a(long j) {
        short a2;
        synchronized (as.class) {
            a2 = f4608a.a(j);
        }
        return a2;
    }

    public static synchronized void a() {
        synchronized (as.class) {
            f4608a.a();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0088, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.util.List<com.amap.openapi.r> r6) {
        /*
            java.lang.Class<com.amap.openapi.as> r0 = com.amap.openapi.as.class
            monitor-enter(r0)
            if (r6 == 0) goto L_0x0087
            boolean r1 = r6.isEmpty()     // Catch:{ all -> 0x0084 }
            if (r1 == 0) goto L_0x000d
            goto L_0x0087
        L_0x000d:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0084 }
            int r2 = r6.size()     // Catch:{ all -> 0x0084 }
            r1.<init>(r2)     // Catch:{ all -> 0x0084 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0084 }
        L_0x001a:
            boolean r2 = r6.hasNext()     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x007d
            java.lang.Object r2 = r6.next()     // Catch:{ all -> 0x0084 }
            com.amap.openapi.r r2 = (com.amap.openapi.r) r2     // Catch:{ all -> 0x0084 }
            byte r3 = r2.f4741a     // Catch:{ all -> 0x0084 }
            r4 = 1
            if (r3 != r4) goto L_0x003e
            T r2 = r2.f     // Catch:{ all -> 0x0084 }
            com.amap.openapi.w r2 = (com.amap.openapi.w) r2     // Catch:{ all -> 0x0084 }
            com.amap.openapi.as$a r3 = new com.amap.openapi.as$a     // Catch:{ all -> 0x0084 }
            int r4 = r2.c     // Catch:{ all -> 0x0084 }
            int r5 = r2.d     // Catch:{ all -> 0x0084 }
            int r2 = r2.e     // Catch:{ all -> 0x0084 }
            r3.<init>(r4, r5, r2)     // Catch:{ all -> 0x0084 }
        L_0x003a:
            r1.add(r3)     // Catch:{ all -> 0x0084 }
            goto L_0x001a
        L_0x003e:
            byte r3 = r2.f4741a     // Catch:{ all -> 0x0084 }
            r4 = 3
            if (r3 != r4) goto L_0x0053
            T r2 = r2.f     // Catch:{ all -> 0x0084 }
            com.amap.openapi.x r2 = (com.amap.openapi.x) r2     // Catch:{ all -> 0x0084 }
            com.amap.openapi.as$a r3 = new com.amap.openapi.as$a     // Catch:{ all -> 0x0084 }
            int r4 = r2.c     // Catch:{ all -> 0x0084 }
            int r5 = r2.d     // Catch:{ all -> 0x0084 }
            int r2 = r2.f     // Catch:{ all -> 0x0084 }
            r3.<init>(r4, r5, r2)     // Catch:{ all -> 0x0084 }
            goto L_0x003a
        L_0x0053:
            byte r3 = r2.f4741a     // Catch:{ all -> 0x0084 }
            r4 = 4
            if (r3 != r4) goto L_0x0068
            T r2 = r2.f     // Catch:{ all -> 0x0084 }
            com.amap.openapi.z r2 = (com.amap.openapi.z) r2     // Catch:{ all -> 0x0084 }
            com.amap.openapi.as$a r3 = new com.amap.openapi.as$a     // Catch:{ all -> 0x0084 }
            int r4 = r2.c     // Catch:{ all -> 0x0084 }
            int r5 = r2.d     // Catch:{ all -> 0x0084 }
            int r2 = r2.f     // Catch:{ all -> 0x0084 }
            r3.<init>(r4, r5, r2)     // Catch:{ all -> 0x0084 }
            goto L_0x003a
        L_0x0068:
            byte r3 = r2.f4741a     // Catch:{ all -> 0x0084 }
            r4 = 2
            if (r3 != r4) goto L_0x001a
            T r2 = r2.f     // Catch:{ all -> 0x0084 }
            com.amap.openapi.p r2 = (com.amap.openapi.p) r2     // Catch:{ all -> 0x0084 }
            com.amap.openapi.as$a r3 = new com.amap.openapi.as$a     // Catch:{ all -> 0x0084 }
            int r4 = r2.b     // Catch:{ all -> 0x0084 }
            int r5 = r2.c     // Catch:{ all -> 0x0084 }
            int r2 = r2.f     // Catch:{ all -> 0x0084 }
            r3.<init>(r4, r5, r2)     // Catch:{ all -> 0x0084 }
            goto L_0x003a
        L_0x007d:
            com.amap.openapi.bn r6 = f4608a     // Catch:{ all -> 0x0084 }
            r6.a((java.util.List<com.amap.openapi.bm>) r1)     // Catch:{ all -> 0x0084 }
            monitor-exit(r0)
            return
        L_0x0084:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L_0x0087:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.as.a(java.util.List):void");
    }

    public static synchronized short b(long j) {
        short b2;
        synchronized (as.class) {
            b2 = f4608a.b(j);
        }
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b(java.util.List<android.net.wifi.ScanResult> r6) {
        /*
            java.lang.Class<com.amap.openapi.as> r0 = com.amap.openapi.as.class
            monitor-enter(r0)
            if (r6 == 0) goto L_0x0040
            boolean r1 = r6.isEmpty()     // Catch:{ all -> 0x003d }
            if (r1 == 0) goto L_0x000c
            goto L_0x0040
        L_0x000c:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x003d }
            int r2 = r6.size()     // Catch:{ all -> 0x003d }
            r1.<init>(r2)     // Catch:{ all -> 0x003d }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x003d }
        L_0x0019:
            boolean r2 = r6.hasNext()     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r6.next()     // Catch:{ all -> 0x003d }
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch:{ all -> 0x003d }
            com.amap.openapi.as$b r3 = new com.amap.openapi.as$b     // Catch:{ all -> 0x003d }
            java.lang.String r4 = r2.BSSID     // Catch:{ all -> 0x003d }
            long r4 = com.amap.location.common.util.f.a((java.lang.String) r4)     // Catch:{ all -> 0x003d }
            int r2 = r2.level     // Catch:{ all -> 0x003d }
            r3.<init>(r4, r2)     // Catch:{ all -> 0x003d }
            r1.add(r3)     // Catch:{ all -> 0x003d }
            goto L_0x0019
        L_0x0036:
            com.amap.openapi.bn r6 = f4608a     // Catch:{ all -> 0x003d }
            r6.b((java.util.List<com.amap.openapi.bm>) r1)     // Catch:{ all -> 0x003d }
            monitor-exit(r0)
            return
        L_0x003d:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L_0x0040:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.as.b(java.util.List):void");
    }
}
