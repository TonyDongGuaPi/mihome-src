package com.loc;

import android.content.Context;
import android.net.wifi.ScanResult;
import com.amap.location.common.model.AmapLoc;
import com.amap.opensdk.co.CoManager;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.taobao.weex.common.Constants;
import com.xiaomi.stat.ab;
import com.xiaomi.stat.d;
import java.util.List;
import org.json.JSONObject;

public final class ef {

    /* renamed from: a  reason: collision with root package name */
    boolean f6582a = false;
    boolean b = false;
    private Context c;
    private CoManager d = null;
    private int e = -1;

    public ef(Context context) {
        this.c = context;
    }

    private static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sv", "4.7.1");
            jSONObject.put("als", "S128DF1572465B890OE3F7A13167KLEI");
            jSONObject.put("pn", u.c(context));
            jSONObject.put("ak", u.f(context));
            jSONObject.put(d.t, x.g(context));
            jSONObject.put("au", x.a(context));
            jSONObject.put("isimei", true);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String a(ee eeVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (eeVar == null) {
                return null;
            }
            ed c2 = eeVar.c();
            ed d2 = eeVar.d();
            if (c2 != null) {
                jSONObject.put("mainCgi", c2.a());
            }
            if (d2 != null) {
                jSONObject.put("mainCgi2", d2.a());
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "buildCgiJsonStr");
            return null;
        }
    }

    private void a(ee eeVar, List<ScanResult> list, AMapLocationServer aMapLocationServer, int i) {
        try {
            if (d() && fa.a(aMapLocationServer)) {
                e();
                if (this.d != null) {
                    String a2 = a(eeVar);
                    ScanResult[] a3 = a(list);
                    if (i == 1) {
                        this.d.a(a2, a3);
                    } else if (i == 2) {
                        this.d.a(a2, a3, aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude());
                    } else {
                        return;
                    }
                    this.b = true;
                }
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("action-");
            sb.append(1 == i ? "training" : "correct");
            es.a(th, "APSCoManager", sb.toString());
        }
    }

    private static ScanResult[] a(List<ScanResult> list) {
        if (list == null) {
            return null;
        }
        try {
            if (list.size() <= 0) {
                return null;
            }
            ScanResult[] scanResultArr = new ScanResult[list.size()];
            for (int i = 0; i < list.size(); i++) {
                scanResultArr[i] = list.get(i);
            }
            return scanResultArr;
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "buildScanResults");
            return null;
        }
    }

    private boolean d() {
        if (!er.w()) {
            c();
            return false;
        } else if (er.x()) {
            return true;
        } else {
            if (this.b) {
                try {
                    if (this.d != null) {
                        this.d.e();
                    }
                } catch (Throwable th) {
                    es.a(th, "APSCoManager", "destroyOffline");
                }
                this.b = false;
            }
            return false;
        }
    }

    private void e() {
        new Object[1][0] = "CoManager ==> init ";
        fa.a();
        try {
            if (this.d == null) {
                int b2 = ez.b(this.c, ab.a.b, "ok5", 0);
                long b3 = ez.b(this.c, ab.a.b, "ok7", 0);
                if (b2 == 0 || b3 == 0 || System.currentTimeMillis() - b3 >= 259200000) {
                    ez.a(this.c, ab.a.b, "ok5", b2 + 1);
                    ez.a(this.c, ab.a.b, "ok7", System.currentTimeMillis());
                    new Object[1][0] = "CoManager ==> initForJar ";
                    fa.a();
                    try {
                        this.d = new CoManager(this.c);
                        if (this.c != null) {
                            String a2 = a(this.c);
                            if (this.d != null) {
                                this.d.a(a2);
                            }
                        }
                    } catch (Throwable th) {
                        es.a(th, "APSCoManager", "initForJar");
                    }
                    this.d.a();
                    ez.a(this.c, ab.a.b, "ok5", 0);
                    ez.a(this.c, ab.a.b, "ok7", 0);
                } else {
                    return;
                }
            }
            int z = er.z();
            if (this.e != z) {
                this.e = z;
                if (this.d != null) {
                    this.d.a(z);
                }
            }
        } catch (Throwable th2) {
            es.a(th2, "APSCoManager", "init");
        }
    }

    public final AMapLocationServer a(ee eeVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        String e2;
        try {
            if (!d()) {
                return aMapLocationServer;
            }
            if (aMapLocationServer != null && aMapLocationServer.getErrorCode() == 7) {
                return aMapLocationServer;
            }
            e();
            if (this.d != null) {
                this.b = true;
                String a2 = this.d.a(a(eeVar), a(list), false);
                if (a2 != null) {
                    JSONObject jSONObject = new JSONObject(a2);
                    AMapLocationServer aMapLocationServer2 = new AMapLocationServer("lbs");
                    aMapLocationServer2.b(jSONObject);
                    if (fa.a(aMapLocationServer2)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        if (aMapLocationServer2.e().equals("file")) {
                            e2 = "基站离线定位";
                        } else if (aMapLocationServer2.e().equals(AmapLoc.h)) {
                            e2 = "WIFI离线定位";
                        } else {
                            stringBuffer.append("离线定位，");
                            e2 = aMapLocationServer2.e();
                        }
                        stringBuffer.append(e2);
                        if (aMapLocationServer != null) {
                            stringBuffer.append("，在线定位失败原因:" + aMapLocationServer.getErrorInfo());
                        }
                        aMapLocationServer2.setTrustedLevel(2);
                        aMapLocationServer2.setLocationDetail(stringBuffer.toString());
                        aMapLocationServer2.setLocationType(8);
                    }
                    return aMapLocationServer2;
                }
            }
            return aMapLocationServer;
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "getOffLoc");
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r3 = this;
            boolean r0 = com.loc.er.w()     // Catch:{ Throwable -> 0x003f }
            if (r0 != 0) goto L_0x000a
            r3.c()     // Catch:{ Throwable -> 0x003f }
            return
        L_0x000a:
            boolean r0 = com.loc.er.y()     // Catch:{ Throwable -> 0x003f }
            if (r0 != 0) goto L_0x002a
            boolean r0 = r3.f6582a     // Catch:{ Throwable -> 0x003f }
            if (r0 == 0) goto L_0x0029
            com.amap.opensdk.co.CoManager r0 = r3.d     // Catch:{ Throwable -> 0x001e }
            if (r0 == 0) goto L_0x0026
            com.amap.opensdk.co.CoManager r0 = r3.d     // Catch:{ Throwable -> 0x001e }
            r0.d()     // Catch:{ Throwable -> 0x001e }
            goto L_0x0026
        L_0x001e:
            r0 = move-exception
            java.lang.String r1 = "APSCoManager"
            java.lang.String r2 = "destroyCollection"
            com.loc.es.a(r0, r1, r2)     // Catch:{ Throwable -> 0x003f }
        L_0x0026:
            r0 = 0
            r3.f6582a = r0     // Catch:{ Throwable -> 0x003f }
        L_0x0029:
            return
        L_0x002a:
            boolean r0 = r3.f6582a     // Catch:{ Throwable -> 0x003f }
            if (r0 == 0) goto L_0x002f
            return
        L_0x002f:
            r3.e()     // Catch:{ Throwable -> 0x003f }
            com.amap.opensdk.co.CoManager r0 = r3.d     // Catch:{ Throwable -> 0x003f }
            if (r0 == 0) goto L_0x003e
            com.amap.opensdk.co.CoManager r0 = r3.d     // Catch:{ Throwable -> 0x003f }
            r0.c()     // Catch:{ Throwable -> 0x003f }
            r0 = 1
            r3.f6582a = r0     // Catch:{ Throwable -> 0x003f }
        L_0x003e:
            return
        L_0x003f:
            r0 = move-exception
            java.lang.String r1 = "APSCoManager"
            java.lang.String r2 = "startCollection"
            com.loc.es.a(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ef.a():void");
    }

    public final String b() {
        try {
            if (!er.w()) {
                c();
                return null;
            } else if (this.d != null) {
                return this.d.b();
            } else {
                return null;
            }
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "getCollectionVersion");
            return null;
        }
    }

    public final void b(ee eeVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        try {
            a(eeVar, list, aMapLocationServer, 1);
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "trainingFps");
        }
    }

    public final void c() {
        try {
            if (this.d != null) {
                this.d.f();
            }
            this.f6582a = false;
            this.b = false;
            this.d = null;
        } catch (Throwable th) {
            es.a(th, "APSCoManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }

    public final void c(ee eeVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        try {
            a(eeVar, list, aMapLocationServer, 2);
        } catch (Throwable th) {
            es.a(th, "APSCoManager", "correctOffLoc");
        }
    }
}
