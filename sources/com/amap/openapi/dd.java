package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.location.common.log.ALLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class dd {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<a> f4688a = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public cz b;
    private Context c;
    private b d = new b();
    /* access modifiers changed from: private */
    public GnssStatus.Callback e;
    /* access modifiers changed from: private */
    public GpsStatus.Listener f;
    /* access modifiers changed from: private */
    public GpsStatus g;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        cu f4691a;
        private Handler b;

        /* renamed from: com.amap.openapi.dd$a$a  reason: collision with other inner class name */
        private static class C0041a extends Handler {

            /* renamed from: a  reason: collision with root package name */
            private cu f4692a;

            C0041a(cu cuVar, Looper looper) {
                super(looper);
                this.f4692a = cuVar;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.f4692a.a();
                        return;
                    case 2:
                        this.f4692a.b();
                        return;
                    case 3:
                        this.f4692a.a(((Integer) message.obj).intValue());
                        return;
                    case 4:
                        c cVar = (c) message.obj;
                        this.f4692a.a(cVar.f4694a, cVar.b, cVar.c, cVar.d);
                        return;
                    default:
                        return;
                }
            }
        }

        a(cu cuVar, Looper looper) {
            this.f4691a = cuVar;
            this.b = new C0041a(this.f4691a, looper == null ? Looper.getMainLooper() : looper);
        }

        /* access modifiers changed from: package-private */
        public void a(int i, Object obj) {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = obj;
            obtainMessage.sendToTarget();
        }

        /* access modifiers changed from: package-private */
        public boolean a(cu cuVar, Looper looper) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            return this.f4691a == cuVar && this.b.getLooper() == looper;
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            if (cr.a(context).a("gps")) {
                synchronized (dd.this.f4688a) {
                    if (dd.this.f4688a.size() > 0) {
                        try {
                            if (Build.VERSION.SDK_INT >= 24) {
                                if (dd.this.e != null) {
                                    dd.this.b.b(dd.this.e);
                                    dd.this.b.a(dd.this.e);
                                }
                            } else if (dd.this.f != null) {
                                dd.this.b.b(dd.this.f);
                                dd.this.b.a(dd.this.f);
                            }
                        } catch (SecurityException e) {
                            try {
                                ALLog.a("@_24_5_@", "卫星接口权限异常", (Exception) e);
                            } catch (SecurityException e2) {
                                ALLog.a("@_24_5_@", "卫星接口权限异常", (Exception) e2);
                            }
                        }
                    }
                }
            }
        }
    }

    private class c {

        /* renamed from: a  reason: collision with root package name */
        int f4694a;
        int b;
        float c;
        List<ct> d;

        public c(int i, int i2, float f, List<ct> list) {
            this.f4694a = i;
            this.b = i2;
            this.c = f;
            this.d = list;
        }
    }

    public dd(cz czVar, Context context) {
        this.b = czVar;
        this.c = context;
        if (Build.VERSION.SDK_INT >= 24) {
            this.e = new GnssStatus.Callback() {
                public void onFirstFix(int i) {
                    dd.this.a(i);
                }

                public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                    dd.this.a(gnssStatus);
                }

                public void onStarted() {
                    dd.this.a();
                }

                public void onStopped() {
                    dd.this.b();
                }
            };
        } else {
            this.f = new GpsStatus.Listener() {
                public void onGpsStatusChanged(int i) {
                    if (i == 1) {
                        dd.this.a();
                    } else if (i == 2) {
                        dd.this.b();
                    } else if (i == 3) {
                        if (dd.this.g == null) {
                            GpsStatus unused = dd.this.g = dd.this.b.a((GpsStatus) null);
                        } else {
                            dd.this.b.a(dd.this.g);
                        }
                        if (dd.this.g != null) {
                            dd.this.a(dd.this.g.getTimeToFirstFix());
                        }
                    } else if (i == 4) {
                        if (dd.this.g == null) {
                            GpsStatus unused2 = dd.this.g = dd.this.b.a((GpsStatus) null);
                        } else {
                            dd.this.b.a(dd.this.g);
                        }
                        if (dd.this.g != null) {
                            dd.this.a(dd.this.g.getSatellites());
                        }
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        synchronized (this.f4688a) {
            for (a a2 : this.f4688a) {
                a2.a(1, (Object) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        synchronized (this.f4688a) {
            for (a a2 : this.f4688a) {
                a2.a(3, (Object) Integer.valueOf(i));
            }
        }
    }

    private void a(int i, int i2, float f2, List<ct> list) {
        synchronized (this.f4688a) {
            for (a a2 : this.f4688a) {
                a2.a(4, (Object) new c(i, i2, f2, list));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(GnssStatus gnssStatus) {
        try {
            if (Build.VERSION.SDK_INT >= 24 && gnssStatus != null) {
                int satelliteCount = gnssStatus.getSatelliteCount();
                ArrayList arrayList = new ArrayList();
                int i = 0;
                float f2 = 0.0f;
                for (int i2 = 0; i2 < satelliteCount; i2++) {
                    arrayList.add(new ct(gnssStatus.usedInFix(i2), gnssStatus.getSvid(i2), gnssStatus.getCn0DbHz(i2), gnssStatus.getElevationDegrees(i2), gnssStatus.getAzimuthDegrees(i2), gnssStatus.getConstellationType(i2)));
                    if (gnssStatus.usedInFix(i2)) {
                        i++;
                        f2 += gnssStatus.getCn0DbHz(i2);
                    }
                }
                if (i != 0) {
                    f2 /= (float) i;
                }
                a(i, satelliteCount, f2, arrayList);
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void a(Iterable<GpsSatellite> iterable) {
        if (iterable != null) {
            float f2 = 0.0f;
            try {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                int i2 = 0;
                for (GpsSatellite next : iterable) {
                    if (next != null) {
                        i2++;
                        arrayList.add(new ct(next.usedInFix(), next.getPrn(), next.getSnr(), next.getElevation(), next.getAzimuth(), 0));
                        if (next.usedInFix()) {
                            i++;
                            f2 += next.getSnr();
                        }
                    }
                }
                if (i != 0) {
                    f2 /= (float) i;
                }
                a(i, i2, f2, arrayList);
            } catch (Exception unused) {
            }
        }
    }

    private a b(cu cuVar) {
        for (a next : this.f4688a) {
            if (next.f4691a == cuVar) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void b() {
        synchronized (this.f4688a) {
            for (a a2 : this.f4688a) {
                a2.a(2, (Object) null);
            }
        }
    }

    public void a(cu cuVar) {
        if (cuVar != null) {
            synchronized (this.f4688a) {
                a b2 = b(cuVar);
                if (b2 != null) {
                    boolean remove = this.f4688a.remove(b2);
                    if (this.f4688a.size() == 0 && remove) {
                        try {
                            if (Build.VERSION.SDK_INT >= 24) {
                                if (this.e != null) {
                                    this.b.b(this.e);
                                }
                            } else if (this.f != null) {
                                this.b.b(this.f);
                            }
                            this.c.unregisterReceiver(this.d);
                        } catch (Exception e2) {
                            ALLog.a("@_24_5_@", "@_24_5_2_@", e2);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0052 A[Catch:{ SecurityException -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0058 A[SYNTHETIC, Splitter:B:30:0x0058] */
    @android.support.annotation.RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.amap.openapi.cu r5, android.os.Looper r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.List<com.amap.openapi.dd$a> r1 = r4.f4688a
            monitor-enter(r1)
            com.amap.openapi.dd$a r2 = r4.b((com.amap.openapi.cu) r5)     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0013
            boolean r5 = r2.a((com.amap.openapi.cu) r5, (android.os.Looper) r6)     // Catch:{ all -> 0x0073 }
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            return r5
        L_0x0013:
            com.amap.openapi.dd$a r2 = new com.amap.openapi.dd$a     // Catch:{ all -> 0x0073 }
            r2.<init>(r5, r6)     // Catch:{ all -> 0x0073 }
            java.util.List<com.amap.openapi.dd$a> r5 = r4.f4688a     // Catch:{ all -> 0x0073 }
            r5.add(r2)     // Catch:{ all -> 0x0073 }
            java.util.List<com.amap.openapi.dd$a> r5 = r4.f4688a     // Catch:{ all -> 0x0073 }
            int r5 = r5.size()     // Catch:{ all -> 0x0073 }
            r6 = 1
            if (r5 != r6) goto L_0x0071
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ SecurityException -> 0x0047 }
            r6 = 24
            if (r5 < r6) goto L_0x003a
            android.location.GnssStatus$Callback r5 = r4.e     // Catch:{ SecurityException -> 0x0047 }
            if (r5 == 0) goto L_0x0050
            com.amap.openapi.cz r5 = r4.b     // Catch:{ SecurityException -> 0x0047 }
            android.location.GnssStatus$Callback r6 = r4.e     // Catch:{ SecurityException -> 0x0047 }
            boolean r5 = r5.a((android.location.GnssStatus.Callback) r6)     // Catch:{ SecurityException -> 0x0047 }
        L_0x0038:
            r0 = r5
            goto L_0x0050
        L_0x003a:
            android.location.GpsStatus$Listener r5 = r4.f     // Catch:{ SecurityException -> 0x0047 }
            if (r5 == 0) goto L_0x0050
            com.amap.openapi.cz r5 = r4.b     // Catch:{ SecurityException -> 0x0047 }
            android.location.GpsStatus$Listener r6 = r4.f     // Catch:{ SecurityException -> 0x0047 }
            boolean r5 = r5.a((android.location.GpsStatus.Listener) r6)     // Catch:{ SecurityException -> 0x0047 }
            goto L_0x0038
        L_0x0047:
            r5 = move-exception
            java.lang.String r6 = "@_24_5_@"
            java.lang.String r3 = "卫星接口权限异常"
            com.amap.location.common.log.ALLog.a((java.lang.String) r6, (java.lang.String) r3, (java.lang.Exception) r5)     // Catch:{ all -> 0x0073 }
        L_0x0050:
            if (r0 != 0) goto L_0x0058
            java.util.List<com.amap.openapi.dd$a> r5 = r4.f4688a     // Catch:{ all -> 0x0073 }
            r5.remove(r2)     // Catch:{ all -> 0x0073 }
            goto L_0x006f
        L_0x0058:
            android.content.Context r5 = r4.c     // Catch:{ Exception -> 0x0067 }
            com.amap.openapi.dd$b r6 = r4.d     // Catch:{ Exception -> 0x0067 }
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch:{ Exception -> 0x0067 }
            java.lang.String r3 = "android.location.PROVIDERS_CHANGED"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0067 }
            r5.registerReceiver(r6, r2)     // Catch:{ Exception -> 0x0067 }
            goto L_0x006f
        L_0x0067:
            r5 = move-exception
            java.lang.String r6 = "@_24_6_@"
            java.lang.String r2 = "@_24_6_1_@"
            com.amap.location.common.log.ALLog.a((java.lang.String) r6, (java.lang.String) r2, (java.lang.Exception) r5)     // Catch:{ all -> 0x0073 }
        L_0x006f:
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            return r0
        L_0x0071:
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            return r6
        L_0x0073:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.dd.a(com.amap.openapi.cu, android.os.Looper):boolean");
    }
}
