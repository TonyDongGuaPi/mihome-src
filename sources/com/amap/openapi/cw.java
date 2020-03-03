package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import java.util.ArrayList;
import java.util.List;

public class cw {

    /* renamed from: a  reason: collision with root package name */
    private a f4670a;
    private a b;

    private static class a implements LocationListener {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public cz f4671a;
        /* access modifiers changed from: private */
        public String b;
        private Context c;
        private C0039a d = new C0039a(this);
        /* access modifiers changed from: private */
        public final List<cx> e = new ArrayList();
        /* access modifiers changed from: private */
        public long f = Long.MAX_VALUE;
        /* access modifiers changed from: private */
        public float g = Float.MAX_VALUE;
        private Location h;

        /* renamed from: com.amap.openapi.cw$a$a  reason: collision with other inner class name */
        private class C0039a extends BroadcastReceiver {
            private LocationListener b;

            public C0039a(LocationListener locationListener) {
                this.b = locationListener;
            }

            public void onReceive(Context context, Intent intent) {
                if (cr.a(context).a("gps")) {
                    synchronized (a.this.e) {
                        if (a.this.e.size() > 0) {
                            a.this.f4671a.a(this.b);
                            a.this.f4671a.a(a.this.b, a.this.f, a.this.g, this.b, Looper.getMainLooper());
                        }
                    }
                }
            }
        }

        a(String str, cz czVar, Context context) {
            this.f4671a = czVar;
            this.b = str;
            this.c = context;
        }

        private void a() {
            float f2 = Float.MAX_VALUE;
            long j = Long.MAX_VALUE;
            if (this.e.isEmpty()) {
                this.f4671a.a((LocationListener) this);
                this.h = null;
                this.f = Long.MAX_VALUE;
                this.g = Float.MAX_VALUE;
                return;
            }
            for (cx next : this.e) {
                j = Math.min(j, next.b);
                f2 = Math.min(f2, next.c);
            }
            if (this.f != j || this.g != f2) {
                this.f = j;
                this.g = f2;
                this.f4671a.a((LocationListener) this);
                this.f4671a.a(this.b, this.f, this.g, this, Looper.getMainLooper());
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(10:2|3|(3:6|(3:26|8|(1:12))|4)|27|15|(2:17|18)|19|20|21|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
            return;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(long r9, float r11, android.location.LocationListener r12, android.os.Looper r13) {
            /*
                r8 = this;
                java.util.List<com.amap.openapi.cx> r0 = r8.e
                monitor-enter(r0)
                java.util.List<com.amap.openapi.cx> r1 = r8.e     // Catch:{ all -> 0x0058 }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0058 }
            L_0x0009:
                boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0058 }
                if (r2 == 0) goto L_0x002e
                java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0058 }
                com.amap.openapi.cx r2 = (com.amap.openapi.cx) r2     // Catch:{ all -> 0x0058 }
                android.location.LocationListener r3 = r2.f4673a     // Catch:{ all -> 0x0058 }
                if (r3 != r12) goto L_0x0009
                long r12 = r2.b     // Catch:{ all -> 0x0058 }
                int r1 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
                if (r1 != 0) goto L_0x0025
                float r12 = r2.c     // Catch:{ all -> 0x0058 }
                int r12 = (r12 > r11 ? 1 : (r12 == r11 ? 0 : -1))
                if (r12 == 0) goto L_0x002c
            L_0x0025:
                r2.b = r9     // Catch:{ all -> 0x0058 }
                r2.c = r11     // Catch:{ all -> 0x0058 }
                r8.a()     // Catch:{ all -> 0x0058 }
            L_0x002c:
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                return
            L_0x002e:
                java.util.List<com.amap.openapi.cx> r1 = r8.e     // Catch:{ all -> 0x0058 }
                int r1 = r1.size()     // Catch:{ all -> 0x0058 }
                if (r1 != 0) goto L_0x0044
                android.content.Context r1 = r8.c     // Catch:{ Exception -> 0x0044 }
                com.amap.openapi.cw$a$a r2 = r8.d     // Catch:{ Exception -> 0x0044 }
                android.content.IntentFilter r3 = new android.content.IntentFilter     // Catch:{ Exception -> 0x0044 }
                java.lang.String r4 = "android.location.PROVIDERS_CHANGED"
                r3.<init>(r4)     // Catch:{ Exception -> 0x0044 }
                r1.registerReceiver(r2, r3)     // Catch:{ Exception -> 0x0044 }
            L_0x0044:
                com.amap.openapi.cx r7 = new com.amap.openapi.cx     // Catch:{ all -> 0x0058 }
                r1 = r7
                r2 = r12
                r3 = r9
                r5 = r11
                r6 = r13
                r1.<init>(r2, r3, r5, r6)     // Catch:{ all -> 0x0058 }
                java.util.List<com.amap.openapi.cx> r9 = r8.e     // Catch:{ all -> 0x0058 }
                r9.add(r7)     // Catch:{ all -> 0x0058 }
                r8.a()     // Catch:{ all -> 0x0058 }
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                return
            L_0x0058:
                r9 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0058 }
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.cw.a.a(long, float, android.location.LocationListener, android.os.Looper):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|4|(2:5|(2:7|(2:21|9))(1:22))|10|(2:13|14)|15|16) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0034 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(android.location.LocationListener r6) {
            /*
                r5 = this;
                java.util.List<com.amap.openapi.cx> r0 = r5.e
                monitor-enter(r0)
                r1 = 0
                java.util.List<com.amap.openapi.cx> r2 = r5.e     // Catch:{ all -> 0x0036 }
                java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0036 }
            L_0x000a:
                boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0036 }
                if (r3 == 0) goto L_0x0023
                java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0036 }
                com.amap.openapi.cx r3 = (com.amap.openapi.cx) r3     // Catch:{ all -> 0x0036 }
                android.location.LocationListener r4 = r3.f4673a     // Catch:{ all -> 0x0036 }
                if (r4 != r6) goto L_0x000a
                java.util.List<com.amap.openapi.cx> r6 = r5.e     // Catch:{ all -> 0x0036 }
                r6.remove(r3)     // Catch:{ all -> 0x0036 }
                r5.a()     // Catch:{ all -> 0x0036 }
                r1 = 1
            L_0x0023:
                java.util.List<com.amap.openapi.cx> r6 = r5.e     // Catch:{ all -> 0x0036 }
                int r6 = r6.size()     // Catch:{ all -> 0x0036 }
                if (r6 != 0) goto L_0x0034
                if (r1 == 0) goto L_0x0034
                android.content.Context r6 = r5.c     // Catch:{ Exception -> 0x0034 }
                com.amap.openapi.cw$a$a r1 = r5.d     // Catch:{ Exception -> 0x0034 }
                r6.unregisterReceiver(r1)     // Catch:{ Exception -> 0x0034 }
            L_0x0034:
                monitor-exit(r0)     // Catch:{ all -> 0x0036 }
                return
            L_0x0036:
                r6 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0036 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.cw.a.a(android.location.LocationListener):void");
        }

        public void onLocationChanged(Location location) {
            if (location != null) {
                float abs = this.h == null ? Float.MAX_VALUE : Math.abs(location.distanceTo(this.h));
                synchronized (this.e) {
                    for (cx a2 : this.e) {
                        a2.a(location, abs);
                    }
                }
                this.h = location;
            }
        }

        public void onProviderDisabled(String str) {
            synchronized (this.e) {
                for (cx a2 : this.e) {
                    a2.a(str, false);
                }
            }
        }

        public void onProviderEnabled(String str) {
            synchronized (this.e) {
                for (cx a2 : this.e) {
                    a2.a(str, true);
                }
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            synchronized (this.e) {
                for (cx a2 : this.e) {
                    a2.a(str, i, bundle);
                }
            }
        }
    }

    public cw(cz czVar, Context context) {
        this.f4670a = new a("gps", czVar, context);
        this.b = new a("passive", czVar, context);
    }

    public void a(LocationListener locationListener) {
        if (locationListener != null) {
            this.f4670a.a(locationListener);
            this.b.a(locationListener);
        }
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public void a(String str, long j, float f, LocationListener locationListener, Looper looper) {
        if (locationListener != null) {
            a aVar = null;
            if ("gps".equals(str)) {
                aVar = this.f4670a;
            } else if ("passive".equals(str)) {
                aVar = this.b;
            }
            a aVar2 = aVar;
            if (aVar2 != null) {
                aVar2.a(j, f, locationListener, looper);
            }
        }
    }
}
