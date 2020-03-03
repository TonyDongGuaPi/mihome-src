package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.DPoint;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.mi.global.shop.model.Tags;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class p {
    static AMapLocation j;
    static long k;
    static Object l = new Object();
    static long q = 0;
    static boolean t = false;
    static boolean u = false;
    /* access modifiers changed from: private */
    public long A = 0;
    private int B = 0;
    /* access modifiers changed from: private */
    public int C = 0;
    /* access modifiers changed from: private */
    public GpsStatus D = null;
    private GpsStatus.Listener E = new GpsStatus.Listener() {
        public final void onGpsStatusChanged(int i) {
            Iterable<GpsSatellite> satellites;
            try {
                if (p.this.b != null) {
                    GpsStatus unused = p.this.D = p.this.b.getGpsStatus(p.this.D);
                    int i2 = 0;
                    switch (i) {
                        case 2:
                            int unused2 = p.this.C = 0;
                            return;
                        case 3:
                            return;
                        case 4:
                            if (!(p.this.D == null || (satellites = p.this.D.getSatellites()) == null)) {
                                Iterator<GpsSatellite> it = satellites.iterator();
                                int maxSatellites = p.this.D.getMaxSatellites();
                                while (it.hasNext() && i2 < maxSatellites) {
                                    if (it.next().usedInFix()) {
                                        i2++;
                                    }
                                }
                            }
                            int unused3 = p.this.C = i2;
                            return;
                        default:
                            return;
                    }
                    es.a(th, "GpsLocation", "onGpsStatusChanged");
                }
            } catch (Throwable th) {
                es.a(th, "GpsLocation", "onGpsStatusChanged");
            }
        }
    };
    /* access modifiers changed from: private */
    public String F = null;
    /* access modifiers changed from: private */
    public boolean G = false;
    /* access modifiers changed from: private */
    public int H = 0;
    private boolean I = false;

    /* renamed from: a  reason: collision with root package name */
    Handler f6627a;
    LocationManager b;
    AMapLocationClientOption c;
    long d = 0;
    boolean e = false;
    cu f = null;
    int g = PsExtractor.VIDEO_STREAM_MASK;
    int h = 80;
    AMapLocation i = null;
    long m = 0;
    float n = 0.0f;
    Object o = new Object();
    Object p = new Object();
    AMapLocationClientOption.GeoLanguage r = AMapLocationClientOption.GeoLanguage.DEFAULT;
    boolean s = true;
    long v = 0;
    int w = 0;
    LocationListener x = new LocationListener() {
        public final void onLocationChanged(Location location) {
            Handler handler;
            if (p.this.f6627a != null) {
                p.this.f6627a.removeMessages(8);
            }
            if (location != null) {
                try {
                    AMapLocation aMapLocation = new AMapLocation(location);
                    if (fa.a(aMapLocation)) {
                        aMapLocation.setProvider("gps");
                        aMapLocation.setLocationType(1);
                        if (!p.this.e && fa.a(aMapLocation)) {
                            ey.a(p.this.z, fa.c() - p.this.A, es.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                            p.this.e = true;
                        }
                        if (fa.a(location, p.this.C)) {
                            aMapLocation.setMock(true);
                            aMapLocation.setTrustedLevel(4);
                            if (!p.this.c.isMockEnable()) {
                                if (p.this.w > 3) {
                                    ey.a((String) null, 2152);
                                    aMapLocation.setErrorCode(15);
                                    aMapLocation.setLocationDetail("GpsLocation has been mocked!#1501");
                                    aMapLocation.setLatitude(0.0d);
                                    aMapLocation.setLongitude(0.0d);
                                    aMapLocation.setAltitude(0.0d);
                                    aMapLocation.setSpeed(0.0f);
                                    aMapLocation.setAccuracy(0.0f);
                                    aMapLocation.setBearing(0.0f);
                                    aMapLocation.setExtras((Bundle) null);
                                    p.this.b(aMapLocation);
                                    return;
                                }
                                p.this.w++;
                                return;
                            }
                        } else {
                            p.this.w = 0;
                        }
                        aMapLocation.setSatellites(p.this.C);
                        p.b(p.this, aMapLocation);
                        p.c(p.this, aMapLocation);
                        p.a(aMapLocation);
                        AMapLocation d = p.d(p.this, aMapLocation);
                        p.e(p.this, d);
                        p pVar = p.this;
                        if (fa.a(d) && pVar.f6627a != null && pVar.c.isNeedAddress()) {
                            long c = fa.c();
                            if (pVar.c.getInterval() <= 8000 || c - pVar.v > pVar.c.getInterval() - 8000) {
                                Bundle bundle = new Bundle();
                                bundle.putDouble(Tags.Nearby.LAT, d.getLatitude());
                                bundle.putDouble(Tags.Nearby.LON, d.getLongitude());
                                Message obtain = Message.obtain();
                                obtain.setData(bundle);
                                obtain.what = 5;
                                synchronized (pVar.o) {
                                    if (pVar.y == null) {
                                        handler = pVar.f6627a;
                                    } else if (fa.a(d, pVar.y) > ((float) pVar.h)) {
                                        handler = pVar.f6627a;
                                    }
                                    handler.sendMessage(obtain);
                                }
                            }
                        }
                        synchronized (p.this.o) {
                            p.a(p.this, d, p.this.y);
                        }
                        try {
                            if (fa.a(d)) {
                                if (p.this.i != null) {
                                    p.this.m = location.getTime() - p.this.i.getTime();
                                    p.this.n = fa.a(p.this.i, d);
                                }
                                synchronized (p.this.p) {
                                    p.this.i = d.clone();
                                }
                                String unused = p.this.F = null;
                                boolean unused2 = p.this.G = false;
                                int unused3 = p.this.H = 0;
                            }
                        } catch (Throwable th) {
                            es.a(th, "GpsLocation", "onLocationChangedLast");
                        }
                        p.this.b(d);
                    }
                } catch (Throwable th2) {
                    es.a(th2, "GpsLocation", "onLocationChanged");
                }
            }
        }

        public final void onProviderDisabled(String str) {
            try {
                if ("gps".equalsIgnoreCase(str)) {
                    p.this.d = 0;
                    int unused = p.this.C = 0;
                }
            } catch (Throwable unused2) {
            }
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                try {
                    p.this.d = 0;
                    int unused = p.this.C = 0;
                } catch (Throwable unused2) {
                }
            }
        }
    };
    public AMapLocation y = null;
    /* access modifiers changed from: private */
    public Context z;

    public p(Context context, Handler handler) {
        this.z = context;
        this.f6627a = handler;
        try {
            this.b = (LocationManager) this.z.getSystemService("location");
        } catch (Throwable th) {
            es.a(th, "GpsLocation", "<init>");
        }
        this.f = new cu();
    }

    private void a(int i2, int i3, String str, long j2) {
        try {
            if (this.f6627a != null && this.c.getLocationMode() == AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                Message obtain = Message.obtain();
                AMapLocation aMapLocation = new AMapLocation("");
                aMapLocation.setProvider("gps");
                aMapLocation.setErrorCode(i3);
                aMapLocation.setLocationDetail(str);
                aMapLocation.setLocationType(1);
                obtain.obj = aMapLocation;
                obtain.what = i2;
                this.f6627a.sendMessageDelayed(obtain, j2);
            }
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ void a(AMapLocation aMapLocation) {
        if (fa.a(aMapLocation) && er.C()) {
            long time = aMapLocation.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            long a2 = et.a(time, currentTimeMillis, er.D());
            if (a2 != time) {
                aMapLocation.setTime(a2);
                ey.a(time, currentTimeMillis);
            }
        }
    }

    static /* synthetic */ void a(p pVar, AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (aMapLocation2 != null && pVar.c.isNeedAddress() && fa.a(aMapLocation, aMapLocation2) < ((float) pVar.g)) {
            es.a(aMapLocation, aMapLocation2);
        }
    }

    private static boolean a(LocationManager locationManager) {
        try {
            if (t) {
                return u;
            }
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders == null || allProviders.size() <= 0) {
                u = false;
            } else {
                u = allProviders.contains("gps");
            }
            t = true;
            return u;
        } catch (Throwable unused) {
            return u;
        }
    }

    private boolean a(String str) {
        try {
            ArrayList<String> d2 = fa.d(str);
            ArrayList<String> d3 = fa.d(this.F);
            if (d2.size() < 8 || d3.size() < 8) {
                return false;
            }
            return fa.a(this.F, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void b(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 15 && !AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(this.c.getLocationMode())) {
            return;
        }
        if (this.c.getLocationMode().equals(AMapLocationClientOption.AMapLocationMode.Device_Sensors) && this.c.getDeviceModeDistanceFilter() > 0.0f) {
            c(aMapLocation);
        } else if (fa.c() - this.v >= this.c.getInterval() - 200) {
            this.v = fa.c();
            c(aMapLocation);
        }
    }

    static /* synthetic */ void b(p pVar, AMapLocation aMapLocation) {
        try {
            if (!es.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) || !pVar.c.isOffset()) {
                aMapLocation.setOffset(false);
                aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
                return;
            }
            DPoint a2 = eu.a(pVar.z, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
            aMapLocation.setLatitude(a2.getLatitude());
            aMapLocation.setLongitude(a2.getLongitude());
            aMapLocation.setOffset(pVar.c.isOffset());
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
        } catch (Throwable unused) {
            aMapLocation.setOffset(false);
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
        }
    }

    private void c(AMapLocation aMapLocation) {
        if (this.f6627a != null) {
            Message obtain = Message.obtain();
            obtain.obj = aMapLocation;
            obtain.what = 2;
            this.f6627a.sendMessage(obtain);
        }
    }

    static /* synthetic */ void c(p pVar, AMapLocation aMapLocation) {
        try {
            if (pVar.C >= 4) {
                aMapLocation.setGpsAccuracyStatus(1);
            } else if (pVar.C == 0) {
                aMapLocation.setGpsAccuracyStatus(-1);
            } else {
                aMapLocation.setGpsAccuracyStatus(0);
            }
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ AMapLocation d(p pVar, AMapLocation aMapLocation) {
        if (!fa.a(aMapLocation) || pVar.B < 3) {
            return aMapLocation;
        }
        if (aMapLocation.getAccuracy() < 0.0f || aMapLocation.getAccuracy() == Float.MAX_VALUE) {
            aMapLocation.setAccuracy(0.0f);
        }
        if (aMapLocation.getSpeed() < 0.0f || aMapLocation.getSpeed() == Float.MAX_VALUE) {
            aMapLocation.setSpeed(0.0f);
        }
        return pVar.f.a(aMapLocation);
    }

    static /* synthetic */ void e(p pVar, AMapLocation aMapLocation) {
        if (fa.a(aMapLocation)) {
            pVar.d = fa.c();
            synchronized (l) {
                k = fa.c();
                j = aMapLocation.clone();
            }
            pVar.B++;
        }
    }

    private static boolean e() {
        try {
            return ((Boolean) ew.a(ad.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), ad.c("UaXNOYXZpU3RhcnRlZA=="), (Object[]) null, (Class<?>[]) null)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:15|16|(2:17|18)|21|22|23|24|(2:25|26)|29|30|31|32|33|34|(3:36|cb|41)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0097 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c9 A[Catch:{ Throwable -> 0x00f6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amap.api.location.AMapLocation f() {
        /*
            r15 = this;
            r0 = 0
            com.amap.api.location.AMapLocation r1 = r15.i     // Catch:{ Throwable -> 0x00f6 }
            boolean r1 = com.loc.fa.a((com.amap.api.location.AMapLocation) r1)     // Catch:{ Throwable -> 0x00f6 }
            if (r1 != 0) goto L_0x000a
            return r0
        L_0x000a:
            boolean r1 = com.loc.er.s()     // Catch:{ Throwable -> 0x00f6 }
            if (r1 != 0) goto L_0x0011
            return r0
        L_0x0011:
            boolean r1 = e()     // Catch:{ Throwable -> 0x00f6 }
            if (r1 == 0) goto L_0x00f6
            java.lang.String r1 = "KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="
            java.lang.String r1 = com.loc.ad.c((java.lang.String) r1)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r2 = "UZ2V0TmF2aUxvY2F0aW9u"
            java.lang.String r2 = com.loc.ad.c((java.lang.String) r2)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Object r1 = com.loc.ew.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r0, (java.lang.Class<?>[]) r0)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x00f6 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00f6 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r1 = "time"
            long r3 = r2.optLong(r1)     // Catch:{ Throwable -> 0x00f6 }
            boolean r1 = r15.I     // Catch:{ Throwable -> 0x00f6 }
            if (r1 != 0) goto L_0x0045
            r1 = 1
            r15.I = r1     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r1 = "useNaviLoc"
            java.lang.String r5 = "use NaviLoc"
            com.loc.ey.a((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ Throwable -> 0x00f6 }
        L_0x0045:
            long r5 = com.loc.fa.b()     // Catch:{ Throwable -> 0x00f6 }
            r1 = 0
            long r5 = r5 - r3
            r7 = 5500(0x157c, double:2.7174E-320)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x00f6
            java.lang.String r1 = "lat"
            r5 = 0
            double r7 = r2.optDouble(r1, r5)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r1 = "lng"
            double r9 = r2.optDouble(r1, r5)     // Catch:{ Throwable -> 0x00f6 }
            r1 = 0
            java.lang.String r11 = "accuracy"
            java.lang.String r12 = "0"
            java.lang.String r11 = r2.optString(r11, r12)     // Catch:{ NumberFormatException -> 0x006d }
            float r11 = java.lang.Float.parseFloat(r11)     // Catch:{ NumberFormatException -> 0x006d }
            goto L_0x006e
        L_0x006d:
            r11 = 0
        L_0x006e:
            java.lang.String r12 = "altitude"
            double r5 = r2.optDouble(r12, r5)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r12 = "bearing"
            java.lang.String r13 = "0"
            java.lang.String r12 = r2.optString(r12, r13)     // Catch:{ NumberFormatException -> 0x0081 }
            float r12 = java.lang.Float.parseFloat(r12)     // Catch:{ NumberFormatException -> 0x0081 }
            goto L_0x0082
        L_0x0081:
            r12 = 0
        L_0x0082:
            java.lang.String r13 = "speed"
            java.lang.String r14 = "0"
            java.lang.String r2 = r2.optString(r13, r14)     // Catch:{ NumberFormatException -> 0x0097 }
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0097 }
            r1 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 * r1
            r1 = 1108344832(0x42100000, float:36.0)
            float r1 = r2 / r1
        L_0x0097:
            com.amap.api.location.AMapLocation r2 = new com.amap.api.location.AMapLocation     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r13 = "lbs"
            r2.<init>((java.lang.String) r13)     // Catch:{ Throwable -> 0x00f6 }
            r13 = 9
            r2.setLocationType(r13)     // Catch:{ Throwable -> 0x00f6 }
            r2.setLatitude(r7)     // Catch:{ Throwable -> 0x00f6 }
            r2.setLongitude(r9)     // Catch:{ Throwable -> 0x00f6 }
            r2.setAccuracy(r11)     // Catch:{ Throwable -> 0x00f6 }
            r2.setAltitude(r5)     // Catch:{ Throwable -> 0x00f6 }
            r2.setBearing(r12)     // Catch:{ Throwable -> 0x00f6 }
            r2.setSpeed(r1)     // Catch:{ Throwable -> 0x00f6 }
            r2.setTime(r3)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r5 = "GCJ02"
            r2.setCoordType(r5)     // Catch:{ Throwable -> 0x00f6 }
            com.amap.api.location.AMapLocation r5 = r15.i     // Catch:{ Throwable -> 0x00f6 }
            float r5 = com.loc.fa.a((com.amap.api.location.AMapLocation) r2, (com.amap.api.location.AMapLocation) r5)     // Catch:{ Throwable -> 0x00f6 }
            r6 = 1133903872(0x43960000, float:300.0)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 > 0) goto L_0x00f6
            java.lang.Object r5 = r15.p     // Catch:{ Throwable -> 0x00f6 }
            monitor-enter(r5)     // Catch:{ Throwable -> 0x00f6 }
            com.amap.api.location.AMapLocation r6 = r15.i     // Catch:{ all -> 0x00f3 }
            r6.setLongitude(r9)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r6 = r15.i     // Catch:{ all -> 0x00f3 }
            r6.setLatitude(r7)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r6 = r15.i     // Catch:{ all -> 0x00f3 }
            r6.setAccuracy(r11)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r6 = r15.i     // Catch:{ all -> 0x00f3 }
            r6.setBearing(r12)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r6 = r15.i     // Catch:{ all -> 0x00f3 }
            r6.setSpeed(r1)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r1 = r15.i     // Catch:{ all -> 0x00f3 }
            r1.setTime(r3)     // Catch:{ all -> 0x00f3 }
            com.amap.api.location.AMapLocation r1 = r15.i     // Catch:{ all -> 0x00f3 }
            java.lang.String r3 = "GCJ02"
            r1.setCoordType(r3)     // Catch:{ all -> 0x00f3 }
            monitor-exit(r5)     // Catch:{ all -> 0x00f3 }
            return r2
        L_0x00f3:
            r1 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00f3 }
            throw r1     // Catch:{ Throwable -> 0x00f6 }
        L_0x00f6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.p.f():com.amap.api.location.AMapLocation");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00aa A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.amap.api.location.AMapLocation a(com.amap.api.location.AMapLocation r16, java.lang.String r17) {
        /*
            r15 = this;
            r1 = r15
            r2 = r17
            com.amap.api.location.AMapLocation r3 = r1.i
            if (r3 != 0) goto L_0x0008
            return r16
        L_0x0008:
            com.amap.api.location.AMapLocationClientOption r3 = r1.c
            boolean r3 = r3.isMockEnable()
            if (r3 != 0) goto L_0x0019
            com.amap.api.location.AMapLocation r3 = r1.i
            boolean r3 = r3.isMock()
            if (r3 == 0) goto L_0x0019
            return r16
        L_0x0019:
            com.amap.api.location.AMapLocation r3 = r1.i
            boolean r3 = com.loc.fa.a((com.amap.api.location.AMapLocation) r3)
            if (r3 != 0) goto L_0x0022
            return r16
        L_0x0022:
            com.amap.api.location.AMapLocation r3 = r15.f()
            r4 = 2
            if (r3 == 0) goto L_0x0033
            boolean r5 = com.loc.fa.a((com.amap.api.location.AMapLocation) r3)
            if (r5 == 0) goto L_0x0033
            r3.setTrustedLevel(r4)
            return r3
        L_0x0033:
            com.amap.api.location.AMapLocation r3 = r1.i
            float r3 = r3.getSpeed()
            r5 = 0
            int r6 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r7 = 0
            if (r6 != 0) goto L_0x005a
            long r9 = r1.m
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x005a
            long r9 = r1.m
            r11 = 8
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x005a
            float r6 = r1.n
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 <= 0) goto L_0x005a
            float r3 = r1.n
            long r9 = r1.m
            float r6 = (float) r9
            float r3 = r3 / r6
        L_0x005a:
            r6 = 0
            r9 = 30000(0x7530, double:1.4822E-319)
            if (r16 == 0) goto L_0x009e
            boolean r11 = com.loc.fa.a((com.amap.api.location.AMapLocation) r16)
            if (r11 == 0) goto L_0x009e
            float r11 = r16.getAccuracy()
            r12 = 1128792064(0x43480000, float:200.0)
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            r12 = 1084227584(0x40a00000, float:5.0)
            if (r11 >= 0) goto L_0x008b
            int r11 = r1.H
            r13 = 1
            int r11 = r11 + r13
            r1.H = r11
            java.lang.String r11 = r1.F
            if (r11 != 0) goto L_0x0081
            int r11 = r1.H
            if (r11 < r4) goto L_0x0081
            r1.G = r13
        L_0x0081:
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x0088
            r11 = 10000(0x2710, double:4.9407E-320)
            goto L_0x009f
        L_0x0088:
            r11 = 15000(0x3a98, double:7.411E-320)
            goto L_0x009f
        L_0x008b:
            java.lang.String r11 = r1.F
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 != 0) goto L_0x0097
            r1.G = r6
            r1.H = r6
        L_0x0097:
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x009e
            r11 = 20000(0x4e20, double:9.8813E-320)
            goto L_0x009f
        L_0x009e:
            r11 = r9
        L_0x009f:
            long r13 = com.loc.fa.c()
            long r4 = r1.d
            long r13 = r13 - r4
            int r3 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x00ab
            return r16
        L_0x00ab:
            int r3 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x00d8
            boolean r3 = r1.G
            if (r3 == 0) goto L_0x00c4
            boolean r2 = r15.a((java.lang.String) r2)
            if (r2 == 0) goto L_0x00c4
            com.amap.api.location.AMapLocation r0 = r1.i
            com.amap.api.location.AMapLocation r0 = r0.clone()
            r2 = 3
            r0.setTrustedLevel(r2)
            return r0
        L_0x00c4:
            r2 = 0
            r1.F = r2
            r1.H = r6
            java.lang.Object r3 = r1.p
            monitor-enter(r3)
            r1.i = r2     // Catch:{ all -> 0x00d5 }
            monitor-exit(r3)     // Catch:{ all -> 0x00d5 }
            r1.m = r7
            r2 = 0
            r1.n = r2
            return r16
        L_0x00d5:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00d5 }
            throw r0
        L_0x00d8:
            java.lang.String r0 = r1.F
            if (r0 != 0) goto L_0x00e4
            int r0 = r1.H
            r3 = 2
            if (r0 < r3) goto L_0x00e5
            r1.F = r2
            goto L_0x00e5
        L_0x00e4:
            r3 = 2
        L_0x00e5:
            com.amap.api.location.AMapLocation r0 = r1.i
            com.amap.api.location.AMapLocation r0 = r0.clone()
            r0.setTrustedLevel(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.p.a(com.amap.api.location.AMapLocation, java.lang.String):com.amap.api.location.AMapLocation");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:3|4|(1:6)|7|8|(1:10)|11|12|(1:14)|15|17) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014 A[Catch:{ Throwable -> 0x001b }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001f A[Catch:{ Throwable -> 0x0026 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r4 = this;
            android.location.LocationManager r0 = r4.b
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            android.location.LocationListener r0 = r4.x     // Catch:{ Throwable -> 0x0010 }
            if (r0 == 0) goto L_0x0010
            android.location.LocationManager r0 = r4.b     // Catch:{ Throwable -> 0x0010 }
            android.location.LocationListener r1 = r4.x     // Catch:{ Throwable -> 0x0010 }
            r0.removeUpdates(r1)     // Catch:{ Throwable -> 0x0010 }
        L_0x0010:
            android.location.GpsStatus$Listener r0 = r4.E     // Catch:{ Throwable -> 0x001b }
            if (r0 == 0) goto L_0x001b
            android.location.LocationManager r0 = r4.b     // Catch:{ Throwable -> 0x001b }
            android.location.GpsStatus$Listener r1 = r4.E     // Catch:{ Throwable -> 0x001b }
            r0.removeGpsStatusListener(r1)     // Catch:{ Throwable -> 0x001b }
        L_0x001b:
            android.os.Handler r0 = r4.f6627a     // Catch:{ Throwable -> 0x0026 }
            if (r0 == 0) goto L_0x0026
            android.os.Handler r0 = r4.f6627a     // Catch:{ Throwable -> 0x0026 }
            r1 = 8
            r0.removeMessages(r1)     // Catch:{ Throwable -> 0x0026 }
        L_0x0026:
            r0 = 0
            r4.C = r0
            r1 = 0
            r4.A = r1
            r4.v = r1
            r4.d = r1
            r4.B = r0
            r4.w = r0
            com.loc.cu r3 = r4.f
            r3.a()
            r3 = 0
            r4.i = r3
            r4.m = r1
            r1 = 0
            r4.n = r1
            r4.F = r3
            r4.I = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.p.a():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0100, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0101, code lost:
        r8.s = false;
        com.loc.ey.a((java.lang.String) null, (int) cn.com.fmsh.tsm.business.constants.Constants.TradeCode.REFUND);
        a(2, 12, r0.getMessage() + "#1201", 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0127, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0099 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b2 A[Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c8 A[Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0100 A[ExcHandler: SecurityException (r0v0 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:10:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.amap.api.location.AMapLocationClientOption r9) {
        /*
            r8 = this;
            r8.c = r9
            com.amap.api.location.AMapLocationClientOption r9 = r8.c
            if (r9 != 0) goto L_0x000d
            com.amap.api.location.AMapLocationClientOption r9 = new com.amap.api.location.AMapLocationClientOption
            r9.<init>()
            r8.c = r9
        L_0x000d:
            android.content.Context r9 = r8.z     // Catch:{ Throwable -> 0x001b }
            java.lang.String r0 = "pref"
            java.lang.String r1 = "lagt"
            long r2 = q     // Catch:{ Throwable -> 0x001b }
            long r0 = com.loc.ez.b((android.content.Context) r9, (java.lang.String) r0, (java.lang.String) r1, (long) r2)     // Catch:{ Throwable -> 0x001b }
            q = r0     // Catch:{ Throwable -> 0x001b }
        L_0x001b:
            android.location.LocationManager r9 = r8.b
            if (r9 != 0) goto L_0x0020
            return
        L_0x0020:
            r9 = 0
            long r0 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            long r2 = k     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r4 = 0
            long r0 = r0 - r2
            r2 = 5000(0x1388, double:2.4703E-320)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0052
            com.amap.api.location.AMapLocation r0 = j     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            boolean r0 = com.loc.fa.a((com.amap.api.location.AMapLocation) r0)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 == 0) goto L_0x0052
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            boolean r0 = r0.isMockEnable()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 != 0) goto L_0x0047
            com.amap.api.location.AMapLocation r0 = j     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            boolean r0 = r0.isMock()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 != 0) goto L_0x0052
        L_0x0047:
            long r0 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r8.d = r0     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            com.amap.api.location.AMapLocation r0 = j     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r8.b((com.amap.api.location.AMapLocation) r0)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
        L_0x0052:
            r0 = 1
            r8.s = r0     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            android.os.Looper r0 = android.os.Looper.myLooper()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 != 0) goto L_0x0061
            android.content.Context r0 = r8.z     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            android.os.Looper r0 = r0.getMainLooper()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
        L_0x0061:
            r7 = r0
            long r0 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r8.A = r0     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            android.location.LocationManager r0 = r8.b     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            boolean r0 = a((android.location.LocationManager) r0)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 == 0) goto L_0x00ea
            long r0 = com.loc.fa.b()     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            long r2 = q     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            r4 = 0
            long r0 = r0 - r2
            r2 = 259200000(0xf731400, double:1.280618154E-315)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0099
            android.location.LocationManager r0 = r8.b     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            java.lang.String r1 = "gps"
            java.lang.String r2 = "force_xtra_injection"
            r0.sendExtraCommand(r1, r2, r9)     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            long r0 = com.loc.fa.b()     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            q = r0     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            android.content.Context r0 = r8.z     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            java.lang.String r1 = "pref"
            java.lang.String r2 = "lagt"
            long r3 = q     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
            com.loc.ez.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2, (long) r3)     // Catch:{ Throwable -> 0x0099, SecurityException -> 0x0100 }
        L_0x0099:
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r0 = r0.getLocationMode()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            boolean r0 = r0.equals(r1)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            if (r0 == 0) goto L_0x00c8
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            float r0 = r0.getDeviceModeDistanceFilter()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x00c8
            android.location.LocationManager r1 = r8.b     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            java.lang.String r2 = "gps"
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            long r3 = r0.getInterval()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            float r5 = r0.getDeviceModeDistanceFilter()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            android.location.LocationListener r6 = r8.x     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
        L_0x00c4:
            r1.requestLocationUpdates(r2, r3, r5, r6, r7)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            goto L_0x00d2
        L_0x00c8:
            android.location.LocationManager r1 = r8.b     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            java.lang.String r2 = "gps"
            r3 = 900(0x384, double:4.447E-321)
            r5 = 0
            android.location.LocationListener r6 = r8.x     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            goto L_0x00c4
        L_0x00d2:
            android.location.LocationManager r0 = r8.b     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            android.location.GpsStatus$Listener r1 = r8.E     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r0.addGpsStatusListener(r1)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r3 = 8
            r4 = 14
            java.lang.String r5 = "no enough satellites#1401"
            com.amap.api.location.AMapLocationClientOption r0 = r8.c     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            long r6 = r0.getHttpTimeOut()     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            r2 = r8
            r2.a(r3, r4, r5, r6)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            return
        L_0x00ea:
            r2 = 8
            r3 = 14
            java.lang.String r4 = "no gps provider#1402"
            r5 = 0
            r1 = r8
            r1.a(r2, r3, r4, r5)     // Catch:{ SecurityException -> 0x0100, Throwable -> 0x00f7 }
            return
        L_0x00f7:
            r9 = move-exception
            java.lang.String r0 = "GpsLocation"
            java.lang.String r1 = "requestLocationUpdates part2"
            com.loc.es.a(r9, r0, r1)
            return
        L_0x0100:
            r0 = move-exception
            r1 = 0
            r8.s = r1
            r1 = 2121(0x849, float:2.972E-42)
            com.loc.ey.a((java.lang.String) r9, (int) r1)
            r3 = 2
            r4 = 12
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = r0.getMessage()
            r9.append(r0)
            java.lang.String r0 = "#1201"
            r9.append(r0)
            java.lang.String r5 = r9.toString()
            r6 = 0
            r2 = r8
            r2.a(r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.p.a(com.amap.api.location.AMapLocationClientOption):void");
    }

    public final boolean b() {
        return fa.c() - this.d <= 2800;
    }

    @SuppressLint({"NewApi"})
    public final int c() {
        if (this.b == null || !a(this.b)) {
            return 1;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            int i2 = Settings.Secure.getInt(this.z.getContentResolver(), "location_mode", 0);
            if (i2 == 0) {
                return 2;
            }
            if (i2 == 2) {
                return 3;
            }
        } else if (!this.b.isProviderEnabled("gps")) {
            return 2;
        }
        return !this.s ? 4 : 0;
    }

    public final int d() {
        return this.C;
    }
}
