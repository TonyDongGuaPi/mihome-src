package com.loc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.location.common.model.AmapLoc;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public final class cs {
    static int D = -1;
    public static boolean H = true;
    private static boolean M = false;
    private static int O = -1;
    int A = 12;
    eb B = null;
    boolean C = false;
    cu E = null;
    String F = null;
    ef G = null;
    IntentFilter I = null;
    LocationManager J = null;
    private int K = 0;
    private String L = null;
    private String N = null;
    private boolean P = true;

    /* renamed from: a  reason: collision with root package name */
    Context f6540a = null;
    ConnectivityManager b = null;
    eg c = null;
    ee d = null;
    ei e = null;
    ct f = null;
    ep g = null;
    ArrayList<ScanResult> h = new ArrayList<>();
    a i = null;
    AMapLocationClientOption j = new AMapLocationClientOption();
    AMapLocationServer k = null;
    long l = 0;
    eq m = null;
    boolean n = false;
    en o = null;
    StringBuilder p = new StringBuilder();
    boolean q = true;
    boolean r = true;
    AMapLocationClientOption.GeoLanguage s = AMapLocationClientOption.GeoLanguage.DEFAULT;
    boolean t = true;
    boolean u = false;
    WifiInfo v = null;
    boolean w = true;
    StringBuilder x = null;
    boolean y = false;
    public boolean z = false;

    class a extends BroadcastReceiver {
        a() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (cs.this.c != null) {
                                cs.this.c.e();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED") && cs.this.c != null) {
                            cs.this.c.f();
                        }
                    }
                } catch (Throwable th) {
                    es.a(th, "Aps", "onReceive");
                }
            }
        }
    }

    private static AMapLocationServer a(int i2, String str) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(i2);
        aMapLocationServer.setLocationDetail(str);
        if (i2 == 15) {
            ey.a((String) null, 2151);
        }
        return aMapLocationServer;
    }

    private AMapLocationServer a(AMapLocationServer aMapLocationServer, bk bkVar) {
        if (bkVar != null) {
            try {
                if (bkVar.f6513a != null) {
                    if (bkVar.f6513a.length != 0) {
                        ep epVar = new ep();
                        String str = new String(bkVar.f6513a, "UTF-8");
                        if (str.contains("\"status\":\"0\"")) {
                            AMapLocationServer a2 = epVar.a(str, this.f6540a, bkVar);
                            try {
                                a2.h(this.x.toString());
                                return a2;
                            } catch (Throwable th) {
                                AMapLocationServer aMapLocationServer2 = a2;
                                th = th;
                                aMapLocationServer = aMapLocationServer2;
                                aMapLocationServer.setErrorCode(4);
                                es.a(th, "Aps", "checkResponseEntity");
                                StringBuilder sb = this.p;
                                sb.append("check response exception ex is" + th.getMessage() + "#0403");
                                aMapLocationServer.setLocationDetail(this.p.toString());
                                return aMapLocationServer;
                            }
                        } else if (!str.contains("</body></html>")) {
                            return null;
                        } else {
                            aMapLocationServer.setErrorCode(5);
                            if (this.c == null || !this.c.a(this.b)) {
                                this.p.append("请求可能被劫持了#0502");
                                ey.a((String) null, (int) OlympusCameraSettingsMakernoteDirectory.ah);
                            } else {
                                this.p.append("您连接的是一个需要登录的网络，请确认已经登入网络#0501");
                                ey.a((String) null, 2051);
                            }
                            aMapLocationServer.setLocationDetail(this.p.toString());
                            return aMapLocationServer;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                aMapLocationServer.setErrorCode(4);
                es.a(th, "Aps", "checkResponseEntity");
                StringBuilder sb2 = this.p;
                sb2.append("check response exception ex is" + th.getMessage() + "#0403");
                aMapLocationServer.setLocationDetail(this.p.toString());
                return aMapLocationServer;
            }
        }
        aMapLocationServer.setErrorCode(4);
        this.p.append("网络异常,请求异常#0403");
        aMapLocationServer.h(this.x.toString());
        aMapLocationServer.setLocationDetail(this.p.toString());
        if (bkVar != null) {
            ey.a(bkVar.d, 2041);
        }
        return aMapLocationServer;
    }

    @SuppressLint({"NewApi"})
    private AMapLocationServer a(boolean z2, boolean z3) {
        int i2;
        StringBuilder sb;
        String str;
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        try {
            if (this.m == null) {
                this.m = new eq();
            }
            if (this.j == null) {
                this.j = new AMapLocationClientOption();
            }
            this.m.a(this.f6540a, this.j.isNeedAddress(), this.j.isOffset(), this.d, this.c, this.b, this.G != null ? this.G.b() : null, this.F);
            byte[] a2 = this.m.a();
            this.l = fa.c();
            try {
                es.c(this.f6540a);
                eo a3 = this.o.a(this.f6540a, a2, es.a(), z3);
                el.a(this.f6540a).a(a3);
                bk a4 = this.o.a(a3);
                el.a(this.f6540a).a();
                String str2 = "";
                if (a4 != null) {
                    el.a(this.f6540a).b();
                    aMapLocationServer.a((long) this.o.a());
                    if (!TextUtils.isEmpty(a4.c)) {
                        StringBuilder sb2 = this.p;
                        sb2.append("#csid:" + a4.c);
                    }
                    str2 = a4.d;
                    aMapLocationServer.h(this.x.toString());
                }
                if (!z2) {
                    AMapLocationServer a5 = a(aMapLocationServer, a4);
                    if (a5 != null) {
                        return a5;
                    }
                    byte[] a6 = eh.a(a4.f6513a);
                    if (a6 == null) {
                        aMapLocationServer.setErrorCode(5);
                        this.p.append("解密数据失败#0503");
                        aMapLocationServer.setLocationDetail(this.p.toString());
                        ey.a(str2, (int) OlympusImageProcessingMakernoteDirectory.X);
                        return aMapLocationServer;
                    }
                    aMapLocationServer = this.g.a(aMapLocationServer, a6);
                    if (!fa.a(aMapLocationServer)) {
                        this.L = aMapLocationServer.b();
                        ey.a(str2, !TextUtils.isEmpty(this.L) ? 2062 : 2061);
                        aMapLocationServer.setErrorCode(6);
                        StringBuilder sb3 = this.p;
                        StringBuilder sb4 = new StringBuilder("location faile retype:");
                        sb4.append(aMapLocationServer.d());
                        sb4.append(" rdesc:");
                        sb4.append(TextUtils.isEmpty(this.L) ? "" : this.L);
                        sb4.append("#0601");
                        sb3.append(sb4.toString());
                        aMapLocationServer.h(this.x.toString());
                        aMapLocationServer.setLocationDetail(this.p.toString());
                        return aMapLocationServer;
                    }
                    if (aMapLocationServer.getErrorCode() == 0 && aMapLocationServer.getLocationType() == 0) {
                        if (AmapLoc.n.equals(aMapLocationServer.d()) || "1".equals(aMapLocationServer.d()) || "2".equals(aMapLocationServer.d()) || AmapLoc.u.equals(aMapLocationServer.d()) || AmapLoc.w.equals(aMapLocationServer.d()) || "-1".equals(aMapLocationServer.d())) {
                            aMapLocationServer.setLocationType(5);
                        } else {
                            aMapLocationServer.setLocationType(6);
                        }
                    }
                    aMapLocationServer.setOffset(this.r);
                    aMapLocationServer.a(this.q);
                    aMapLocationServer.f(String.valueOf(this.s));
                }
                aMapLocationServer.e("new");
                aMapLocationServer.setLocationDetail(this.p.toString());
                this.F = aMapLocationServer.a();
                return aMapLocationServer;
            } catch (Throwable th) {
                el.a(this.f6540a).c();
                es.a(th, "Aps", "getApsLoc req");
                ey.a("/mobile/binary", th);
                if (!fa.d(this.f6540a)) {
                    sb = this.p;
                    str = "网络异常，未连接到网络，请连接网络#0401";
                } else {
                    if (th instanceof t) {
                        t tVar = (t) th;
                        if (tVar.a().contains("网络异常状态码")) {
                            StringBuilder sb5 = this.p;
                            sb5.append("网络异常，状态码错误#0404");
                            sb5.append(tVar.e());
                            i2 = 4;
                            AMapLocationServer a7 = a(i2, this.p.toString());
                            a7.h(this.x.toString());
                            return a7;
                        } else if (tVar.e() == 23 || Math.abs((fa.c() - this.l) - this.j.getHttpTimeOut()) < 500) {
                            sb = this.p;
                            str = "网络异常，连接超时#0402";
                        }
                    }
                    sb = this.p;
                    str = "网络异常,请求异常#0403";
                }
                sb.append(str);
                i2 = 4;
                AMapLocationServer a72 = a(i2, this.p.toString());
                a72.h(this.x.toString());
                return a72;
            }
        } catch (Throwable th2) {
            StringBuilder sb6 = this.p;
            sb6.append("get parames error:" + th2.getMessage() + "#0301");
            ey.a((String) null, (int) Constants.TradeCode.ALIPAY_ONE_KEY_CANCEL);
            i2 = 3;
            AMapLocationServer a722 = a(i2, this.p.toString());
            a722.h(this.x.toString());
            return a722;
        }
    }

    private StringBuilder a(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        sb.append(this.d.l());
        sb.append(this.c.i());
        return sb;
    }

    public static void b(Context context) {
        try {
            if (O == -1 || er.g(context)) {
                O = 1;
                er.a(context);
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "initAuth");
        }
    }

    private void c(AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer != null) {
            this.k = aMapLocationServer;
        }
    }

    private void l() {
        if (this.o != null) {
            try {
                if (this.j == null) {
                    this.j = new AMapLocationClientOption();
                }
                int i2 = 0;
                if (this.j.getGeoLanguage() != null) {
                    switch (this.j.getGeoLanguage()) {
                        case DEFAULT:
                            break;
                        case ZH:
                            i2 = 1;
                            break;
                        case EN:
                            i2 = 2;
                            break;
                    }
                }
                this.o.a(this.j.getHttpTimeOut(), this.j.getLocationProtocol().equals(AMapLocationClientOption.AMapLocationProtocol.HTTPS), i2);
            } catch (Throwable unused) {
            }
        }
    }

    private void m() {
        try {
            if (this.i == null) {
                this.i = new a();
            }
            if (this.I == null) {
                this.I = new IntentFilter();
                this.I.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.I.addAction("android.net.wifi.SCAN_RESULTS");
            }
            this.f6540a.registerReceiver(this.i, this.I);
        } catch (Throwable th) {
            es.a(th, "Aps", "initBroadcastListener");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02ed, code lost:
        if (android.text.TextUtils.isEmpty(r0) != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02f3, code lost:
        if (r0.startsWith("#") != false) goto L_0x0304;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02f5, code lost:
        r0 = "#" + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        return com.loc.fa.i() + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01de, code lost:
        if (r11.w == false) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0222, code lost:
        if (r11.w == false) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0225, code lost:
        r1 = "cgi";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0228, code lost:
        r1 = "cgiwifi";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x022a, code lost:
        r0.append(r1);
        r0 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String n() {
        /*
            r11 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "network"
            com.loc.ee r2 = r11.d
            int r2 = r2.f()
            com.loc.ee r3 = r11.d
            com.loc.ed r3 = r3.c()
            java.util.ArrayList<android.net.wifi.ScanResult> r4 = r11.h
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0021
            java.util.ArrayList<android.net.wifi.ScanResult> r4 = r11.h
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r4 = 0
            goto L_0x0022
        L_0x0021:
            r4 = 1
        L_0x0022:
            r7 = 0
            if (r3 != 0) goto L_0x016e
            if (r4 == 0) goto L_0x016e
            android.net.ConnectivityManager r1 = r11.b
            if (r1 != 0) goto L_0x0037
            android.content.Context r1 = r11.f6540a
            java.lang.String r2 = "connectivity"
            java.lang.Object r1 = com.loc.fa.a((android.content.Context) r1, (java.lang.String) r2)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            r11.b = r1
        L_0x0037:
            android.content.Context r1 = r11.f6540a
            boolean r1 = com.loc.fa.a((android.content.Context) r1)
            if (r1 == 0) goto L_0x0057
            com.loc.eg r1 = r11.c
            boolean r1 = r1.p
            if (r1 != 0) goto L_0x0057
            r1 = 18
            r11.A = r1
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关#1801"
            r1.append(r2)
            r1 = 2132(0x854, float:2.988E-42)
            com.loc.ey.a((java.lang.String) r7, (int) r1)
            return r0
        L_0x0057:
            int r1 = com.loc.fa.d()
            r2 = 28
            r3 = 2121(0x849, float:2.972E-42)
            r4 = 12
            if (r1 < r2) goto L_0x0097
            android.location.LocationManager r1 = r11.J
            if (r1 != 0) goto L_0x0077
            android.content.Context r1 = r11.f6540a
            android.content.Context r1 = r1.getApplicationContext()
            java.lang.String r5 = "location"
            java.lang.Object r1 = r1.getSystemService(r5)
            android.location.LocationManager r1 = (android.location.LocationManager) r1
            r11.J = r1
        L_0x0077:
            android.location.LocationManager r1 = r11.J
            java.lang.String r5 = "isLocationEnabled"
            java.lang.Object[] r8 = new java.lang.Object[r6]
            java.lang.Object r1 = com.loc.ew.a(r1, r5, r8)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0097
            r11.A = r4
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "定位服务没有开启，请在设置中打开定位服务开关#1206"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x0097:
            android.content.Context r1 = r11.f6540a
            boolean r1 = com.loc.fa.f((android.content.Context) r1)
            if (r1 != 0) goto L_0x00ad
            r11.A = r4
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "定位权限被禁用,请授予应用定位权限#1201"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x00ad:
            int r1 = com.loc.fa.d()
            r5 = 24
            if (r1 < r5) goto L_0x00d7
            int r1 = com.loc.fa.d()
            if (r1 >= r2) goto L_0x00d7
            android.content.Context r1 = r11.f6540a
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "location_mode"
            int r1 = android.provider.Settings.Secure.getInt(r1, r2, r6)
            if (r1 != 0) goto L_0x00d7
            r11.A = r4
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "定位服务没有开启，请在设置中打开定位服务开关#1206"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x00d7:
            com.loc.ee r1 = r11.d
            java.lang.String r1 = r1.j()
            com.loc.eg r2 = r11.c
            java.lang.String r2 = r2.b()
            com.loc.eg r5 = r11.c
            android.net.ConnectivityManager r6 = r11.b
            boolean r5 = r5.a((android.net.ConnectivityManager) r6)
            if (r5 == 0) goto L_0x00fd
            if (r2 == 0) goto L_0x00fd
            r11.A = r4
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "获取基站与获取WIFI的权限都被禁用，请在安全软件中打开应用的定位权限#1202"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x00fd:
            if (r1 == 0) goto L_0x0119
            r11.A = r4
            com.loc.eg r1 = r11.c
            boolean r1 = r1.p
            if (r1 != 0) goto L_0x010f
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "WIFI开关关闭，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限或者打开WIFI开关#1204"
        L_0x010b:
            r1.append(r2)
            goto L_0x0115
        L_0x010f:
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "获取的WIFI列表为空，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限#1205"
            goto L_0x010b
        L_0x0115:
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x0119:
            com.loc.eg r1 = r11.c
            boolean r1 = r1.p
            if (r1 != 0) goto L_0x0139
            com.loc.ee r1 = r11.d
            boolean r1 = r1.m()
            if (r1 != 0) goto L_0x0139
            r1 = 19
            r11.A = r1
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "没有检查到SIM卡，并且WIFI开关关闭，请打开WIFI开关或者插入SIM卡#1901"
            r1.append(r2)
            r1 = 2133(0x855, float:2.989E-42)
            com.loc.ey.a((java.lang.String) r7, (int) r1)
            return r0
        L_0x0139:
            android.content.Context r1 = r11.f6540a
            boolean r1 = com.loc.fa.g((android.content.Context) r1)
            if (r1 != 0) goto L_0x014f
            r11.A = r4
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "后台定位服务没有开启，请在设置中打开后台定位服务开关#1207"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r3)
            return r0
        L_0x014f:
            com.loc.eg r1 = r11.c
            boolean r1 = r1.p
            if (r1 != 0) goto L_0x015e
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "获取到的基站为空，并且关闭了WIFI开关，请您打开WIFI开关再发起定位#1301"
        L_0x015a:
            r1.append(r2)
            goto L_0x0164
        L_0x015e:
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "获取到的基站和WIFI信息均为空，请移动到有WIFI的区域，若确定当前区域有WIFI，请检查是否授予APP定位权限#1302"
            goto L_0x015a
        L_0x0164:
            r1 = 13
            r11.A = r1
            r1 = 2131(0x853, float:2.986E-42)
            com.loc.ey.a((java.lang.String) r7, (int) r1)
            return r0
        L_0x016e:
            com.loc.eg r4 = r11.c
            android.net.wifi.WifiInfo r4 = r4.g()
            r11.v = r4
            android.net.wifi.WifiInfo r4 = r11.v
            boolean r4 = com.loc.eg.a((android.net.wifi.WifiInfo) r4)
            r11.w = r4
            switch(r2) {
                case 0: goto L_0x0233;
                case 1: goto L_0x01e1;
                case 2: goto L_0x0193;
                default: goto L_0x0181;
            }
        L_0x0181:
            r1 = 11
            r11.A = r1
            r1 = 2111(0x83f, float:2.958E-42)
            com.loc.ey.a((java.lang.String) r7, (int) r1)
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "get cgi failure#1101"
            r1.append(r2)
            goto L_0x02e7
        L_0x0193:
            if (r3 == 0) goto L_0x02e7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r2 = r3.f6578a
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.b
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.g
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.h
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.i
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            r0.append(r1)
            java.lang.String r1 = "#"
            r0.append(r1)
            java.util.ArrayList<android.net.wifi.ScanResult> r1 = r11.h
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0228
            boolean r1 = r11.w
            if (r1 == 0) goto L_0x0225
            goto L_0x0228
        L_0x01e1:
            if (r3 == 0) goto L_0x02e7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r2 = r3.f6578a
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.b
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.c
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            int r2 = r3.d
            r0.append(r2)
            java.lang.String r2 = "#"
            r0.append(r2)
            r0.append(r1)
            java.lang.String r1 = "#"
            r0.append(r1)
            java.util.ArrayList<android.net.wifi.ScanResult> r1 = r11.h
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0228
            boolean r1 = r11.w
            if (r1 == 0) goto L_0x0225
            goto L_0x0228
        L_0x0225:
            java.lang.String r1 = "cgi"
            goto L_0x022a
        L_0x0228:
            java.lang.String r1 = "cgiwifi"
        L_0x022a:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L_0x02e7
        L_0x0233:
            java.util.ArrayList<android.net.wifi.ScanResult> r2 = r11.h
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0242
            boolean r2 = r11.w
            if (r2 == 0) goto L_0x0240
            goto L_0x0242
        L_0x0240:
            r2 = 0
            goto L_0x0243
        L_0x0242:
            r2 = 1
        L_0x0243:
            boolean r3 = r11.w
            r4 = 2021(0x7e5, float:2.832E-42)
            r8 = 2
            if (r3 == 0) goto L_0x0260
            java.util.ArrayList<android.net.wifi.ScanResult> r3 = r11.h
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0260
            r11.A = r8
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "当前基站为伪基站，并且WIFI权限被禁用，请在安全软件中打开应用的定位权限#0201"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r4)
            return r0
        L_0x0260:
            java.util.ArrayList<android.net.wifi.ScanResult> r3 = r11.h
            int r3 = r3.size()
            r9 = 2022(0x7e6, float:2.833E-42)
            if (r3 != r5) goto L_0x02a2
            r11.A = r8
            boolean r3 = r11.w
            if (r3 != 0) goto L_0x027c
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r9)
            return r0
        L_0x027c:
            java.util.ArrayList<android.net.wifi.ScanResult> r3 = r11.h
            java.lang.Object r3 = r3.get(r6)
            android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
            java.lang.String r3 = r3.BSSID
            com.loc.eg r10 = r11.c
            android.net.wifi.WifiInfo r10 = r10.g()
            java.lang.String r10 = r10.getBSSID()
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L_0x02a2
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202"
            r1.append(r2)
            com.loc.ey.a((java.lang.String) r7, (int) r4)
            return r0
        L_0x02a2:
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r3 = "#%s#"
            java.lang.Object[] r4 = new java.lang.Object[r5]
            r4[r6] = r1
            java.lang.String r0 = java.lang.String.format(r0, r3, r4)
            if (r2 == 0) goto L_0x02c3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "wifi"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x02e7
        L_0x02c3:
            java.lang.String r2 = "network"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x02e7
            java.lang.String r0 = ""
            r11.A = r8
            com.loc.eg r1 = r11.c
            boolean r1 = r1.p
            if (r1 != 0) goto L_0x02de
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "当前基站为伪基站,并且关闭了WIFI开关，请在设置中打开WIFI开关#0203"
        L_0x02da:
            r1.append(r2)
            goto L_0x02e4
        L_0x02de:
            java.lang.StringBuilder r1 = r11.p
            java.lang.String r2 = "当前基站为伪基站,并且没有搜索到WIFI，请移动到WIFI比较丰富的区域#0204"
            goto L_0x02da
        L_0x02e4:
            com.loc.ey.a((java.lang.String) r7, (int) r9)
        L_0x02e7:
            java.lang.String r1 = "#"
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0317
            boolean r2 = r0.startsWith(r1)
            if (r2 != 0) goto L_0x0304
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
        L_0x0304:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.loc.fa.i()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x0317:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cs.n():java.lang.String");
    }

    private boolean o() {
        this.h = this.c.c();
        return this.h == null || this.h.size() <= 0;
    }

    public final AMapLocationServer a(double d2, double d3) {
        try {
            String a2 = this.o.a(this.f6540a, d2, d3);
            if (!a2.contains("\"status\":\"1\"")) {
                return null;
            }
            AMapLocationServer a3 = this.g.a(a2);
            a3.setLatitude(d2);
            a3.setLongitude(d3);
            return a3;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer) {
        this.E.a(this.t);
        return this.E.a(aMapLocationServer);
    }

    public final AMapLocationServer a(boolean z2) {
        int i2;
        String str;
        if (this.f6540a == null) {
            this.p.append("context is null#0101");
            ey.a((String) null, 2011);
            i2 = 1;
        } else if (this.c.h()) {
            i2 = 15;
            str = "networkLocation has been mocked!#1502";
            return a(i2, str);
        } else {
            a();
            if (TextUtils.isEmpty(this.N)) {
                i2 = this.A;
            } else {
                AMapLocationServer a2 = a(false, z2);
                if (fa.a(a2)) {
                    this.e.a(this.x.toString());
                    this.e.a(this.d.c());
                    c(a2);
                }
                return a2;
            }
        }
        str = this.p.toString();
        return a(i2, str);
    }

    public final void a() {
        this.o = en.a(this.f6540a);
        l();
        if (this.b == null) {
            this.b = (ConnectivityManager) fa.a(this.f6540a, "connectivity");
        }
        if (this.m == null) {
            this.m = new eq();
        }
    }

    public final void a(Context context) {
        try {
            if (this.f6540a == null) {
                this.E = new cu();
                this.f6540a = context.getApplicationContext();
                er.d(this.f6540a);
                fa.b(this.f6540a);
                if (this.c == null) {
                    this.c = new eg(this.f6540a, (WifiManager) fa.a(this.f6540a, "wifi"));
                }
                if (this.d == null) {
                    this.d = new ee(this.f6540a);
                }
                if (this.e == null) {
                    this.e = new ei();
                }
                if (this.g == null) {
                    this.g = new ep();
                }
                if (this.G == null) {
                    this.G = new ef(this.f6540a);
                }
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "initBase");
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        boolean z2;
        boolean z3;
        boolean z4;
        AMapLocationClientOption.GeoLanguage geoLanguage;
        this.j = aMapLocationClientOption;
        if (this.j == null) {
            this.j = new AMapLocationClientOption();
        }
        if (this.c != null) {
            eg egVar = this.c;
            this.j.isWifiActiveScan();
            egVar.a(this.j.isWifiScan(), this.j.isMockEnable(), AMapLocationClientOption.isOpenAlwaysScanWifi(), aMapLocationClientOption.getScanWifiInterval());
        }
        l();
        if (this.e != null) {
            this.e.a(this.j);
        }
        if (this.g != null) {
            this.g.a(this.j);
        }
        try {
            geoLanguage = this.j.getGeoLanguage();
            try {
                z2 = this.j.isNeedAddress();
                try {
                    z4 = this.j.isOffset();
                } catch (Throwable unused) {
                    z4 = true;
                    z3 = true;
                    this.r = z4;
                    this.q = z2;
                    this.t = z3;
                    this.s = geoLanguage;
                }
                try {
                    z3 = this.j.isLocationCacheEnable();
                    try {
                        this.u = this.j.isOnceLocationLatest();
                        this.C = this.j.isSensorEnable();
                        if (!(z4 == this.r && z2 == this.q && z3 == this.t && geoLanguage == this.s)) {
                            if (this.e != null) {
                                this.e.a();
                            }
                            c((AMapLocationServer) null);
                            this.P = false;
                            if (this.E != null) {
                                this.E.a();
                            }
                        }
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    z3 = true;
                    this.r = z4;
                    this.q = z2;
                    this.t = z3;
                    this.s = geoLanguage;
                }
            } catch (Throwable unused4) {
                z2 = true;
                z4 = true;
                z3 = true;
                this.r = z4;
                this.q = z2;
                this.t = z3;
                this.s = geoLanguage;
            }
        } catch (Throwable unused5) {
            geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT;
            z2 = true;
            z4 = true;
            z3 = true;
            this.r = z4;
            this.q = z2;
            this.t = z3;
            this.s = geoLanguage;
        }
        this.r = z4;
        this.q = z2;
        this.t = z3;
        this.s = geoLanguage;
    }

    public final void b() {
        if (this.B == null) {
            this.B = new eb(this.f6540a);
        }
        if (this.f == null) {
            this.f = new ct(this.f6540a);
        }
        m();
        this.c.b(false);
        this.h = this.c.c();
        this.d.a(false, o());
        this.e.a(this.f6540a);
        this.f.b();
        try {
            if (this.f6540a.checkCallingOrSelfPermission(ad.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFQ1VSRV9TRVRUSU5HUw==")) == 0) {
                this.n = true;
            }
        } catch (Throwable unused) {
        }
        this.z = true;
    }

    public final void b(AMapLocationServer aMapLocationServer) {
        if (fa.a(aMapLocationServer)) {
            this.e.a(this.N, this.x, aMapLocationServer, this.f6540a, true);
        }
    }

    public final void c() {
        if (this.p.length() > 0) {
            this.p.delete(0, this.p.length());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer d() throws java.lang.Throwable {
        /*
            r15 = this;
            r15.c()
            android.content.Context r0 = r15.f6540a
            r1 = 1
            if (r0 != 0) goto L_0x001a
            java.lang.StringBuilder r0 = r15.p
            java.lang.String r2 = "context is null#0101"
            r0.append(r2)
            java.lang.StringBuilder r0 = r15.p
            java.lang.String r0 = r0.toString()
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = a((int) r1, (java.lang.String) r0)
            return r0
        L_0x001a:
            int r0 = r15.K
            int r0 = r0 + r1
            r15.K = r0
            int r0 = r15.K
            if (r0 != r1) goto L_0x002e
            com.loc.eg r0 = r15.c
            if (r0 == 0) goto L_0x002e
            com.loc.eg r0 = r15.c
            boolean r2 = r15.n
            r0.a((boolean) r2)
        L_0x002e:
            long r2 = r15.l
            boolean r0 = r15.P
            r4 = 0
            r6 = 0
            if (r0 != 0) goto L_0x003b
            r15.P = r1
        L_0x0039:
            r0 = 0
            goto L_0x0062
        L_0x003b:
            long r7 = com.loc.fa.c()
            long r7 = r7 - r2
            r2 = 800(0x320, double:3.953E-321)
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0039
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)
            if (r0 == 0) goto L_0x005a
            long r2 = com.loc.fa.b()
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            long r7 = r0.getTime()
            long r2 = r2 - r7
            goto L_0x005b
        L_0x005a:
            r2 = r4
        L_0x005b:
            r7 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0039
            r0 = 1
        L_0x0062:
            r2 = 2
            if (r0 == 0) goto L_0x0085
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)
            if (r0 == 0) goto L_0x0085
            boolean r0 = r15.t
            if (r0 == 0) goto L_0x0082
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            long r0 = r0.getTime()
            boolean r0 = com.loc.er.b((long) r0)
            if (r0 == 0) goto L_0x0082
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            r0.setLocationType(r2)
        L_0x0082:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            return r0
        L_0x0085:
            com.loc.eb r0 = r15.B
            if (r0 == 0) goto L_0x0098
            boolean r0 = r15.C
            if (r0 == 0) goto L_0x0093
            com.loc.eb r0 = r15.B
            r0.a()
            goto L_0x0098
        L_0x0093:
            com.loc.eb r0 = r15.B
            r0.b()
        L_0x0098:
            com.amap.api.location.AMapLocationClientOption r0 = r15.j     // Catch:{ Throwable -> 0x00ba }
            boolean r0 = r0.isOnceLocationLatest()     // Catch:{ Throwable -> 0x00ba }
            if (r0 != 0) goto L_0x00ab
            com.amap.api.location.AMapLocationClientOption r0 = r15.j     // Catch:{ Throwable -> 0x00ba }
            boolean r0 = r0.isOnceLocation()     // Catch:{ Throwable -> 0x00ba }
            if (r0 != 0) goto L_0x00a9
            goto L_0x00ab
        L_0x00a9:
            r0 = 0
            goto L_0x00ac
        L_0x00ab:
            r0 = 1
        L_0x00ac:
            com.loc.eg r3 = r15.c     // Catch:{ Throwable -> 0x00ba }
            r3.b(r0)     // Catch:{ Throwable -> 0x00ba }
            com.loc.eg r0 = r15.c     // Catch:{ Throwable -> 0x00ba }
            java.util.ArrayList r0 = r0.c()     // Catch:{ Throwable -> 0x00ba }
            r15.h = r0     // Catch:{ Throwable -> 0x00ba }
            goto L_0x00c2
        L_0x00ba:
            r0 = move-exception
            java.lang.String r3 = "Aps"
            java.lang.String r7 = "getLocation getScanResultsParam"
            com.loc.es.a(r0, r3, r7)
        L_0x00c2:
            com.loc.ee r0 = r15.d     // Catch:{ Throwable -> 0x00cc }
            boolean r3 = r15.o()     // Catch:{ Throwable -> 0x00cc }
            r0.a((boolean) r6, (boolean) r3)     // Catch:{ Throwable -> 0x00cc }
            goto L_0x00d4
        L_0x00cc:
            r0 = move-exception
            java.lang.String r3 = "Aps"
            java.lang.String r7 = "getLocation getCgiListParam"
            com.loc.es.a(r0, r3, r7)
        L_0x00d4:
            java.lang.String r0 = r15.n()
            r15.N = r0
            java.lang.String r0 = r15.N
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r3 = 4
            if (r0 == 0) goto L_0x010f
            com.loc.ct r0 = r15.f
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r0.e()
            r15.k = r0
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            if (r0 == 0) goto L_0x0102
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            java.lang.StringBuilder r1 = r15.p
            java.lang.String r1 = r1.toString()
            r0.setLocationDetail(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            r0.setTrustedLevel(r3)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            return r0
        L_0x0102:
            int r0 = r15.A
            java.lang.StringBuilder r1 = r15.p
            java.lang.String r1 = r1.toString()
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = a((int) r0, (java.lang.String) r1)
            return r0
        L_0x010f:
            java.lang.StringBuilder r0 = r15.x
            java.lang.StringBuilder r0 = r15.a((java.lang.StringBuilder) r0)
            r15.x = r0
            com.loc.eg r0 = r15.c
            boolean r0 = r0.h()
            if (r0 == 0) goto L_0x012e
            r0 = 15
            java.lang.String r2 = "networkLocation has been mocked!#1502"
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = a((int) r0, (java.lang.String) r2)
            r0.setMock(r1)
            r0.setTrustedLevel(r3)
            return r0
        L_0x012e:
            long r7 = r15.l
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0136
        L_0x0134:
            r9 = 1
            goto L_0x0145
        L_0x0136:
            long r4 = com.loc.fa.c()
            long r7 = r15.l
            long r4 = r4 - r7
            r7 = 20000(0x4e20, double:9.8813E-320)
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0144
            goto L_0x0134
        L_0x0144:
            r9 = 0
        L_0x0145:
            com.loc.ei r7 = r15.e
            com.loc.ee r8 = r15.d
            com.autonavi.aps.amapapi.model.AMapLocationServer r10 = r15.k
            com.loc.eg r11 = r15.c
            java.lang.StringBuilder r12 = r15.x
            java.lang.String r13 = r15.N
            android.content.Context r14 = r15.f6540a
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r7.a(r8, r9, r10, r11, r12, r13, r14)
            boolean r4 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)
            if (r4 == 0) goto L_0x0164
            r0.setTrustedLevel(r2)
            r15.c(r0)
            goto L_0x01a3
        L_0x0164:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.a((boolean) r6, (boolean) r1)
            r15.k = r0
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)
            if (r0 == 0) goto L_0x01a3
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            java.lang.String r4 = "new"
            r0.e(r4)
            com.loc.ei r0 = r15.e
            java.lang.StringBuilder r4 = r15.x
            java.lang.String r4 = r4.toString()
            r0.a((java.lang.String) r4)
            com.loc.ei r0 = r15.e
            com.loc.ee r4 = r15.d
            com.loc.ed r4 = r4.c()
            r0.a((com.loc.ed) r4)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            r15.c(r0)
            com.loc.ef r0 = r15.G
            if (r0 == 0) goto L_0x01a3
            com.loc.ef r0 = r15.G
            com.loc.ee r4 = r15.d
            java.util.ArrayList<android.net.wifi.ScanResult> r5 = r15.h
            com.autonavi.aps.amapapi.model.AMapLocationServer r7 = r15.k
            r0.c(r4, r5, r7)
        L_0x01a3:
            com.loc.eg r0 = r15.c     // Catch:{ Throwable -> 0x01d6 }
            if (r0 == 0) goto L_0x01d6
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k     // Catch:{ Throwable -> 0x01d6 }
            if (r0 == 0) goto L_0x01d6
            long r4 = com.loc.eg.a()     // Catch:{ Throwable -> 0x01d6 }
            r7 = 15
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x01bb
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k     // Catch:{ Throwable -> 0x01d6 }
        L_0x01b7:
            r0.setTrustedLevel(r1)     // Catch:{ Throwable -> 0x01d6 }
            goto L_0x01d6
        L_0x01bb:
            r0 = 120(0x78, double:5.93E-322)
            int r7 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r7 > 0) goto L_0x01c7
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k     // Catch:{ Throwable -> 0x01d6 }
            r0.setTrustedLevel(r2)     // Catch:{ Throwable -> 0x01d6 }
            goto L_0x01d6
        L_0x01c7:
            r0 = 600(0x258, double:2.964E-321)
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x01d1
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k     // Catch:{ Throwable -> 0x01d6 }
            r1 = 3
            goto L_0x01b7
        L_0x01d1:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k     // Catch:{ Throwable -> 0x01d6 }
            r0.setTrustedLevel(r3)     // Catch:{ Throwable -> 0x01d6 }
        L_0x01d6:
            com.loc.ef r0 = r15.G
            if (r0 == 0) goto L_0x01e5
            com.loc.ef r0 = r15.G
            com.loc.ee r1 = r15.d
            java.util.ArrayList<android.net.wifi.ScanResult> r2 = r15.h
            com.autonavi.aps.amapapi.model.AMapLocationServer r3 = r15.k
            r0.b(r1, r2, r3)
        L_0x01e5:
            com.loc.ei r7 = r15.e
            java.lang.String r8 = r15.N
            java.lang.StringBuilder r9 = r15.x
            com.autonavi.aps.amapapi.model.AMapLocationServer r10 = r15.k
            android.content.Context r11 = r15.f6540a
            r12 = 1
            r7.a(r8, r9, r10, r11, r12)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)
            if (r0 != 0) goto L_0x020d
            com.loc.ef r0 = r15.G
            if (r0 == 0) goto L_0x020d
            com.loc.ef r0 = r15.G
            com.loc.ee r1 = r15.d
            java.util.ArrayList<android.net.wifi.ScanResult> r2 = r15.h
            com.autonavi.aps.amapapi.model.AMapLocationServer r3 = r15.k
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r0.a(r1, r2, r3)
            r15.k = r0
        L_0x020d:
            java.lang.StringBuilder r0 = r15.x
            java.lang.StringBuilder r1 = r15.x
            int r1 = r1.length()
            r0.delete(r6, r1)
            boolean r0 = r15.C
            if (r0 == 0) goto L_0x023e
            com.loc.eb r0 = r15.B
            if (r0 == 0) goto L_0x023e
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            com.loc.eb r1 = r15.B
            double r1 = r1.f
            r0.setAltitude(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            com.loc.eb r1 = r15.B
            float r1 = r1.c()
            r0.setBearing(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            com.loc.eb r1 = r15.B
            double r1 = r1.d()
            float r1 = (float) r1
            goto L_0x024d
        L_0x023e:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            r1 = 0
            r0.setAltitude(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            r1 = 0
            r0.setBearing(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
        L_0x024d:
            r0.setSpeed(r1)
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r15.k
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cs.d():com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final void e() {
        try {
            a(this.f6540a);
            a(this.j);
            i();
            b(a(true, true));
        } catch (Throwable th) {
            es.a(th, "Aps", "doFusionLocation");
        }
    }

    @SuppressLint({"NewApi"})
    public final void f() {
        this.F = null;
        this.y = false;
        this.z = false;
        if (this.G != null) {
            this.G.c();
        }
        if (this.f != null) {
            this.f.a();
        }
        if (this.e != null) {
            this.e.b(this.f6540a);
        }
        if (this.E != null) {
            this.E.a();
        }
        if (this.g != null) {
            this.g = null;
        }
        fa.h();
        try {
            if (!(this.f6540a == null || this.i == null)) {
                this.f6540a.unregisterReceiver(this.i);
            }
        } catch (Throwable th) {
            this.i = null;
            throw th;
        }
        this.i = null;
        if (this.d != null) {
            this.d.h();
        }
        if (this.c != null) {
            this.c.j();
        }
        if (this.h != null) {
            this.h.clear();
        }
        if (this.B != null) {
            this.B.e();
        }
        el.d();
        this.k = null;
        this.f6540a = null;
        this.x = null;
        this.J = null;
    }

    public final void g() {
        try {
            if (this.f != null) {
                this.f.c();
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "bindAMapService");
        }
    }

    public final void h() {
        try {
            if (this.f != null) {
                this.f.d();
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "bindOtherService");
        }
    }

    public final void i() {
        try {
            if (!this.y) {
                if (this.N != null) {
                    this.N = null;
                }
                if (this.x != null) {
                    this.x.delete(0, this.x.length());
                }
                if (this.u) {
                    m();
                }
                this.c.b(this.u);
                this.h = this.c.c();
                this.d.a(true, o());
                this.N = n();
                if (!TextUtils.isEmpty(this.N)) {
                    this.x = a(this.x);
                }
                this.y = true;
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "initFirstLocateParam");
        }
    }

    public final AMapLocationServer j() {
        int i2;
        String sb;
        if (this.c.h()) {
            i2 = 15;
            sb = "networkLocation has been mocked!#1502";
        } else if (TextUtils.isEmpty(this.N)) {
            i2 = this.A;
            sb = this.p.toString();
        } else {
            AMapLocationServer a2 = this.e.a(this.f6540a, this.N, this.x, true);
            if (fa.a(a2)) {
                c(a2);
            }
            return a2;
        }
        return a(i2, sb);
    }

    public final void k() {
        if (this.G != null) {
            this.G.a();
        }
    }
}
