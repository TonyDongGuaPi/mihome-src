package com.loc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.mi.mistatistic.sdk.controller.Utils;
import com.taobao.weex.common.Constants;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public final class j implements GeoFenceManagerBase {

    /* renamed from: a  reason: collision with root package name */
    ey f6610a = null;
    Context b = null;
    PendingIntent c = null;
    String d = null;
    GeoFenceListener e = null;
    volatile int f = 1;
    ArrayList<GeoFence> g = new ArrayList<>();
    c h = null;
    Object i = new Object();
    Object j = new Object();
    a k = null;
    b l = null;
    volatile boolean m = false;
    volatile boolean n = false;
    volatile boolean o = false;
    k p = null;
    l q = null;
    AMapLocationClient r = null;
    volatile AMapLocation s = null;
    long t = 0;
    AMapLocationClientOption u = null;
    int v = 0;
    AMapLocationListener w = new AMapLocationListener() {
        public final void onLocationChanged(AMapLocation aMapLocation) {
            boolean z;
            int i;
            try {
                if (!j.this.y && j.this.o) {
                    j.this.s = aMapLocation;
                    if (aMapLocation != null) {
                        i = aMapLocation.getErrorCode();
                        if (aMapLocation.getErrorCode() == 0) {
                            j.this.t = fa.c();
                            j.this.a(5, (Bundle) null, 0);
                            z = true;
                        } else {
                            j.a("定位失败", aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(), "locationDetail:" + aMapLocation.getLocationDetail());
                            z = false;
                        }
                    } else {
                        z = false;
                        i = 8;
                    }
                    if (z) {
                        j.this.v = 0;
                        j.this.a(6, (Bundle) null, 0);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    if (!j.this.m) {
                        j.this.a(7);
                        bundle.putLong(Constants.Name.INTERVAL, 2000);
                        j.this.a(8, bundle, 2000);
                    }
                    j.this.v++;
                    if (j.this.v >= 3) {
                        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                        j.this.a(1002, bundle);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    };
    final int x = 3;
    volatile boolean y = false;
    private Object z = new Object();

    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            String str;
            String str2;
            try {
                int i = 1;
                switch (message.what) {
                    case 0:
                        j jVar = j.this;
                        Bundle data = message.getData();
                        try {
                            ArrayList arrayList = new ArrayList();
                            if (data == null || data.isEmpty()) {
                                str = "";
                            } else {
                                DPoint dPoint = (DPoint) data.getParcelable("centerPoint");
                                str = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (dPoint != null) {
                                    if (dPoint.getLatitude() <= 90.0d && dPoint.getLatitude() >= -90.0d && dPoint.getLongitude() <= 180.0d) {
                                        if (dPoint.getLongitude() >= -180.0d) {
                                            GeoFence a2 = jVar.a(data, false);
                                            i = jVar.a(a2);
                                            if (i == 0) {
                                                arrayList.add(a2);
                                            }
                                        }
                                    }
                                    j.a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                                }
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("errorCode", i);
                            bundle.putParcelableArrayList("resultList", arrayList);
                            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                            jVar.a(1000, bundle);
                            return;
                        } catch (Throwable th) {
                            es.a(th, "GeoFenceManager", "doAddGeoFenceRound");
                            return;
                        }
                    case 1:
                        j jVar2 = j.this;
                        Bundle data2 = message.getData();
                        try {
                            ArrayList arrayList2 = new ArrayList();
                            if (data2 == null || data2.isEmpty()) {
                                str2 = "";
                            } else {
                                ArrayList parcelableArrayList = data2.getParcelableArrayList("pointList");
                                str2 = data2.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (parcelableArrayList != null) {
                                    if (parcelableArrayList.size() > 2) {
                                        GeoFence a3 = jVar2.a(data2, true);
                                        i = jVar2.a(a3);
                                        if (i == 0) {
                                            arrayList2.add(a3);
                                        }
                                    }
                                }
                            }
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
                            bundle2.putInt("errorCode", i);
                            bundle2.putParcelableArrayList("resultList", arrayList2);
                            jVar2.a(1000, bundle2);
                            return;
                        } catch (Throwable th2) {
                            es.a(th2, "GeoFenceManager", "doAddGeoFencePolygon");
                            return;
                        }
                    case 2:
                        j.this.c(message.getData());
                        return;
                    case 3:
                        j.this.b(message.getData());
                        return;
                    case 4:
                        j.this.d(message.getData());
                        return;
                    case 5:
                        j.this.d();
                        return;
                    case 6:
                        j.this.a(j.this.s);
                        return;
                    case 7:
                        j jVar3 = j.this;
                        try {
                            if (jVar3.r != null) {
                                jVar3.c();
                                jVar3.u.setOnceLocation(true);
                                jVar3.r.setLocationOption(jVar3.u);
                                jVar3.r.startLocation();
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            es.a(th3, "GeoFenceManager", "doStartOnceLocation");
                            return;
                        }
                    case 8:
                        j jVar4 = j.this;
                        Bundle data3 = message.getData();
                        if (jVar4.r != null) {
                            long j = 2000;
                            if (data3 != null && !data3.isEmpty()) {
                                j = data3.getLong(Constants.Name.INTERVAL, 2000);
                            }
                            jVar4.u.setOnceLocation(false);
                            jVar4.u.setInterval(j);
                            jVar4.r.setLocationOption(jVar4.u);
                            if (!jVar4.m) {
                                jVar4.r.stopLocation();
                                jVar4.r.startLocation();
                                jVar4.m = true;
                                return;
                            }
                            return;
                        }
                        return;
                    case 9:
                        j.this.a(message.getData());
                        return;
                    case 10:
                        j.this.a();
                        return;
                    case 11:
                        j.this.f(message.getData());
                        return;
                    case 12:
                        j.this.e(message.getData());
                        return;
                    case 13:
                        j.this.e();
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                switch (message.what) {
                    case 1000:
                        j jVar = j.this;
                        if (data != null) {
                            try {
                                if (!data.isEmpty()) {
                                    int i = data.getInt("errorCode");
                                    ArrayList parcelableArrayList = data.getParcelableArrayList("resultList");
                                    if (parcelableArrayList == null) {
                                        parcelableArrayList = new ArrayList();
                                    }
                                    String string = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                    if (string == null) {
                                        string = "";
                                    }
                                    if (jVar.e != null) {
                                        jVar.e.onGeoFenceCreateFinished((ArrayList) parcelableArrayList.clone(), i, string);
                                    }
                                    if (i == 0) {
                                        jVar.b();
                                        return;
                                    }
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                es.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
                                return;
                            }
                        } else {
                            return;
                        }
                    case 1001:
                        try {
                            j.this.b((GeoFence) data.getParcelable("geoFence"));
                            return;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            return;
                        }
                    case 1002:
                        j.this.b(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    public j(Context context) {
        try {
            this.b = context.getApplicationContext();
            f();
        } catch (Throwable th) {
            es.a(th, "GeoFenceManger", "<init>");
        }
    }

    static float a(DPoint dPoint, List<DPoint> list) {
        float f2 = Float.MAX_VALUE;
        if (!(dPoint == null || list == null || list.isEmpty())) {
            for (DPoint a2 : list) {
                f2 = Math.min(f2, fa.a(dPoint, a2));
            }
        }
        return f2;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            for (GeoFence a2 : list) {
                a(a2);
            }
            return 0;
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i2, int i3) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i2);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i3);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    static void a(String str, int i2, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("===========================================\n");
        stringBuffer.append("              " + str + "                ");
        stringBuffer.append("\n");
        stringBuffer.append("-------------------------------------------\n");
        stringBuffer.append("errorCode:" + i2);
        stringBuffer.append("\n");
        stringBuffer.append("错误信息:" + str2);
        stringBuffer.append("\n");
        if (strArr.length > 0) {
            for (String append : strArr) {
                stringBuffer.append(append);
                stringBuffer.append("\n");
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    private static boolean a(GeoFence geoFence, int i2) {
        boolean z2 = false;
        if ((i2 & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z2 = true;
                }
            } catch (Throwable th) {
                es.a(th, Utils.b, "remindStatus");
                return false;
            }
        }
        if ((i2 & 2) == 2 && geoFence.getStatus() == 2) {
            z2 = true;
        }
        if ((i2 & 4) == 4 && geoFence.getStatus() == 3) {
            return true;
        }
        return z2;
    }

    private static boolean a(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z2;
        try {
            if (fa.a(aMapLocation) && geoFence != null && geoFence.getPointList() != null && !geoFence.getPointList().isEmpty()) {
                switch (geoFence.getType()) {
                    case 0:
                    case 2:
                        DPoint center = geoFence.getCenter();
                        if (fa.a(new double[]{center.getLatitude(), center.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()}) <= geoFence.getRadius()) {
                            return true;
                        }
                        break;
                    case 1:
                    case 3:
                        boolean z3 = false;
                        for (List next : geoFence.getPointList()) {
                            try {
                                if (next.size() < 3 ? false : es.a(new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()), (List<DPoint>) next)) {
                                    z3 = true;
                                }
                            } catch (Throwable th) {
                                th = th;
                                z2 = z3;
                                es.a(th, Utils.b, "isInGeoFence");
                                return z2;
                            }
                        }
                        return z3;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            es.a(th, Utils.b, "isInGeoFence");
            return z2;
        }
    }

    static float b(DPoint dPoint, List<DPoint> list) {
        float f2 = Float.MIN_VALUE;
        if (!(dPoint == null || list == null || list.isEmpty())) {
            for (DPoint a2 : list) {
                f2 = Math.max(f2, fa.a(dPoint, a2));
            }
        }
        return f2;
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint = new DPoint();
        if (list == null) {
            return dPoint;
        }
        try {
            double d2 = 0.0d;
            double d3 = 0.0d;
            for (DPoint next : list) {
                d2 += next.getLatitude();
                d3 += next.getLongitude();
            }
            double size = (double) list.size();
            Double.isNaN(size);
            double c2 = fa.c(d2 / size);
            double size2 = (double) list.size();
            Double.isNaN(size2);
            return new DPoint(c2, fa.c(d3 / size2));
        } catch (Throwable th) {
            es.a(th, "GeoFenceUtil", "getPolygonCenter");
            return dPoint;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c7, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x014b, code lost:
        r2 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x014c, code lost:
        if (r9 == null) goto L_0x01bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x014f, code lost:
        if (1 != r0) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0151, code lost:
        r6 = com.loc.l.a(r9, r10, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0156, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0158, code lost:
        if (2 != r0) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x015a, code lost:
        r6 = com.loc.l.a(r9, r10, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015f, code lost:
        if (3 != r0) goto L_0x0169;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0161, code lost:
        r4 = r1.q.b(r9, r10, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0169, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x016c, code lost:
        if (r4 != 10000) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0172, code lost:
        if (r10.isEmpty() == false) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0174, code lost:
        r6 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0179, code lost:
        r6 = a((java.util.List<com.amap.api.fence.GeoFence>) r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x017d, code lost:
        if (r6 != 0) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r7.addAll(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0183, code lost:
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0185, code lost:
        r0 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0187, code lost:
        if (r4 == 1) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0189, code lost:
        if (r4 == 7) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x018b, code lost:
        switch(r4) {
            case 4: goto L_0x01a0;
            case 5: goto L_0x01a0;
            default: goto L_0x018e;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x018e, code lost:
        switch(r4) {
            case 16: goto L_0x01a0;
            case 17: goto L_0x01a0;
            default: goto L_0x0191;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0191, code lost:
        switch(r4) {
            case 10000: goto L_0x019e;
            case 10001: goto L_0x01a1;
            case 10002: goto L_0x01a1;
            case 10003: goto L_0x019c;
            case 10004: goto L_0x019c;
            case 10005: goto L_0x019c;
            case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO :int: goto L_0x019c;
            case 10007: goto L_0x01a1;
            case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START :int: goto L_0x01a1;
            case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_AUDIO_SEEK_RENDERING_START :int: goto L_0x01a1;
            case 10010: goto L_0x019c;
            case 10011: goto L_0x019c;
            case 10012: goto L_0x01a1;
            case 10013: goto L_0x01a1;
            case 10014: goto L_0x019c;
            case 10015: goto L_0x019c;
            case 10016: goto L_0x019c;
            case 10017: goto L_0x019c;
            default: goto L_0x0194;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0194, code lost:
        switch(r4) {
            case 20000: goto L_0x019a;
            case 20001: goto L_0x019a;
            case 20002: goto L_0x019a;
            case 20003: goto L_0x0197;
            default: goto L_0x0197;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0197, code lost:
        r0 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x019a, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x019c, code lost:
        r0 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x019e, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01a0, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a1, code lost:
        if (r0 == 0) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b4, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        a("添加围栏失败", r0, "searchErrCode is " + r0, new java.lang.String[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01ba, code lost:
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bc, code lost:
        r6 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01c1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01c2, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01c1 A[Catch:{ Throwable -> 0x01c1, all -> 0x01be, Throwable -> 0x01e2 }, ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:29:0x00df] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r25, android.os.Bundle r26) {
        /*
            r24 = this;
            r1 = r24
            r0 = r25
            r2 = r26
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            r6 = 0
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01e2 }
            r7.<init>()     // Catch:{ Throwable -> 0x01e2 }
            if (r2 == 0) goto L_0x01d2
            boolean r9 = r26.isEmpty()     // Catch:{ Throwable -> 0x01e2 }
            if (r9 != 0) goto L_0x01d2
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01e2 }
            r10.<init>()     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r11 = "customId"
            java.lang.String r11 = r2.getString(r11)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r12 = "keyWords"
            java.lang.String r15 = r2.getString(r12)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r12 = "city"
            java.lang.String r17 = r2.getString(r12)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r12 = "poiType"
            java.lang.String r16 = r2.getString(r12)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r12 = "centerPoint"
            android.os.Parcelable r12 = r2.getParcelable(r12)     // Catch:{ Throwable -> 0x01e2 }
            com.amap.api.location.DPoint r12 = (com.amap.api.location.DPoint) r12     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r13 = "searchSize"
            r14 = 10
            int r13 = r2.getInt(r13, r14)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r14 = "aroundRadius"
            r9 = 1161527296(0x453b8000, float:3000.0)
            float r2 = r2.getFloat(r14, r9)     // Catch:{ Throwable -> 0x01e2 }
            boolean r9 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Throwable -> 0x01e2 }
            if (r9 == 0) goto L_0x0058
        L_0x0055:
            r4 = 0
            goto L_0x00c8
        L_0x0058:
            switch(r0) {
                case 1: goto L_0x00c0;
                case 2: goto L_0x005c;
                default: goto L_0x005b;
            }     // Catch:{ Throwable -> 0x01e2 }
        L_0x005b:
            goto L_0x00c7
        L_0x005c:
            if (r12 != 0) goto L_0x005f
            goto L_0x0055
        L_0x005f:
            double r18 = r12.getLatitude()     // Catch:{ Throwable -> 0x01e2 }
            r21 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r9 = (r18 > r21 ? 1 : (r18 == r21 ? 0 : -1))
            if (r9 > 0) goto L_0x0093
            double r18 = r12.getLatitude()     // Catch:{ Throwable -> 0x01e2 }
            r21 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r9 = (r18 > r21 ? 1 : (r18 == r21 ? 0 : -1))
            if (r9 < 0) goto L_0x0093
            double r18 = r12.getLongitude()     // Catch:{ Throwable -> 0x01e2 }
            r21 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r9 = (r18 > r21 ? 1 : (r18 == r21 ? 0 : -1))
            if (r9 > 0) goto L_0x0093
            double r18 = r12.getLongitude()     // Catch:{ Throwable -> 0x01e2 }
            r21 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r9 = (r18 > r21 ? 1 : (r18 == r21 ? 0 : -1))
            if (r9 >= 0) goto L_0x00c7
        L_0x0093:
            java.lang.String r9 = "添加围栏失败"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r4 = "经纬度错误，传入的纬度："
            r14.<init>(r4)     // Catch:{ Throwable -> 0x01e2 }
            r23 = r9
            double r8 = r12.getLatitude()     // Catch:{ Throwable -> 0x01e2 }
            r14.append(r8)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r4 = "传入的经度:"
            r14.append(r4)     // Catch:{ Throwable -> 0x01e2 }
            double r8 = r12.getLongitude()     // Catch:{ Throwable -> 0x01e2 }
            r14.append(r8)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r4 = r14.toString()     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String[] r8 = new java.lang.String[r6]     // Catch:{ Throwable -> 0x01e2 }
            r9 = r23
            a(r9, r6, r4, r8)     // Catch:{ Throwable -> 0x01e2 }
            goto L_0x0055
        L_0x00c0:
            boolean r4 = android.text.TextUtils.isEmpty(r16)     // Catch:{ Throwable -> 0x01e2 }
            if (r4 == 0) goto L_0x00c7
            goto L_0x0055
        L_0x00c7:
            r4 = 1
        L_0x00c8:
            if (r4 == 0) goto L_0x01c4
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ Throwable -> 0x01e2 }
            r4.<init>()     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r8 = "customId"
            r4.putString(r8, r11)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r8 = "pendingIntentAction"
            java.lang.String r9 = r1.d     // Catch:{ Throwable -> 0x01e2 }
            r4.putString(r8, r9)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r8 = "expiration"
            r5 = -1
            r4.putLong(r8, r5)     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            java.lang.String r5 = "activatesAction"
            int r6 = r1.f     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            r4.putInt(r5, r6)     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            switch(r0) {
                case 1: goto L_0x0130;
                case 2: goto L_0x00f7;
                case 3: goto L_0x00ee;
                default: goto L_0x00ec;
            }
        L_0x00ec:
            r9 = 0
            goto L_0x014b
        L_0x00ee:
            android.content.Context r2 = r1.b     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r5 = "http://restapi.amap.com/v3/config/district?"
            java.lang.String r9 = com.loc.k.a((android.content.Context) r2, (java.lang.String) r5, (java.lang.String) r15)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            goto L_0x014b
        L_0x00f7:
            double r5 = r12.getLatitude()     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            double r5 = com.loc.fa.c((double) r5)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            double r8 = r12.getLongitude()     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            double r8 = com.loc.fa.c((double) r8)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r12 = "fenceRadius"
            r14 = 1128792064(0x43480000, float:200.0)
            r4.putFloat(r12, r14)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            android.content.Context r12 = r1.b     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r14 = "http://restapi.amap.com/v3/place/around?"
            java.lang.String r17 = java.lang.String.valueOf(r13)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r18 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r19 = java.lang.String.valueOf(r8)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r20 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            r13 = r12
            java.lang.String r9 = com.loc.k.a(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            goto L_0x014b
        L_0x0130:
            java.lang.String r2 = "fenceRadius"
            r5 = 1148846080(0x447a0000, float:1000.0)
            r4.putFloat(r2, r5)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            android.content.Context r2 = r1.b     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            java.lang.String r14 = "http://restapi.amap.com/v3/place/text?"
            java.lang.String r18 = java.lang.String.valueOf(r13)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            r13 = r2
            java.lang.String r9 = com.loc.k.a(r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            goto L_0x014b
        L_0x0145:
            r0 = move-exception
            r2 = 1000(0x3e8, float:1.401E-42)
            r6 = 0
            goto L_0x01f2
        L_0x014b:
            r2 = 4
            if (r9 == 0) goto L_0x01bc
            r5 = 1
            if (r5 != r0) goto L_0x0156
            int r6 = com.loc.l.a(r9, r10, r4)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            goto L_0x0157
        L_0x0156:
            r6 = 0
        L_0x0157:
            r5 = 2
            if (r5 != r0) goto L_0x015e
            int r6 = com.loc.l.a(r9, r10, r4)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
        L_0x015e:
            r5 = 3
            if (r5 != r0) goto L_0x0169
            com.loc.l r0 = r1.q     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            int r0 = r0.b(r9, r10, r4)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            r4 = r0
            goto L_0x016a
        L_0x0169:
            r4 = r6
        L_0x016a:
            r0 = 10000(0x2710, float:1.4013E-41)
            if (r4 != r0) goto L_0x0185
            boolean r0 = r10.isEmpty()     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            if (r0 == 0) goto L_0x0179
            r0 = 16
            r6 = 16
            goto L_0x01c6
        L_0x0179:
            int r6 = r1.a((java.util.List<com.amap.api.fence.GeoFence>) r10)     // Catch:{ Throwable -> 0x01c1, all -> 0x0145 }
            if (r6 != 0) goto L_0x0183
            r7.addAll(r10)     // Catch:{ Throwable -> 0x01e2 }
            goto L_0x01c6
        L_0x0183:
            r2 = r6
            goto L_0x01bc
        L_0x0185:
            r0 = 7
            r5 = 1
            if (r4 == r5) goto L_0x01a0
            if (r4 == r0) goto L_0x01a0
            switch(r4) {
                case 4: goto L_0x01a0;
                case 5: goto L_0x01a0;
                default: goto L_0x018e;
            }
        L_0x018e:
            switch(r4) {
                case 16: goto L_0x01a0;
                case 17: goto L_0x01a0;
                default: goto L_0x0191;
            }
        L_0x0191:
            switch(r4) {
                case 10000: goto L_0x019e;
                case 10001: goto L_0x01a1;
                case 10002: goto L_0x01a1;
                case 10003: goto L_0x019c;
                case 10004: goto L_0x019c;
                case 10005: goto L_0x019c;
                case 10006: goto L_0x019c;
                case 10007: goto L_0x01a1;
                case 10008: goto L_0x01a1;
                case 10009: goto L_0x01a1;
                case 10010: goto L_0x019c;
                case 10011: goto L_0x019c;
                case 10012: goto L_0x01a1;
                case 10013: goto L_0x01a1;
                case 10014: goto L_0x019c;
                case 10015: goto L_0x019c;
                case 10016: goto L_0x019c;
                case 10017: goto L_0x019c;
                default: goto L_0x0194;
            }
        L_0x0194:
            switch(r4) {
                case 20000: goto L_0x019a;
                case 20001: goto L_0x019a;
                case 20002: goto L_0x019a;
                case 20003: goto L_0x0197;
                default: goto L_0x0197;
            }
        L_0x0197:
            r0 = 8
            goto L_0x01a1
        L_0x019a:
            r0 = 1
            goto L_0x01a1
        L_0x019c:
            r0 = 4
            goto L_0x01a1
        L_0x019e:
            r0 = 0
            goto L_0x01a1
        L_0x01a0:
            r0 = r4
        L_0x01a1:
            if (r0 == 0) goto L_0x01ba
            java.lang.String r2 = "添加围栏失败"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            java.lang.String r5 = "searchErrCode is "
            r4.<init>(r5)     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            r4.append(r0)     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x01c1, all -> 0x01be }
            r6 = 0
            java.lang.String[] r5 = new java.lang.String[r6]     // Catch:{ Throwable -> 0x01e2 }
            a(r2, r0, r4, r5)     // Catch:{ Throwable -> 0x01e2 }
        L_0x01ba:
            r6 = r0
            goto L_0x01c6
        L_0x01bc:
            r6 = r2
            goto L_0x01c6
        L_0x01be:
            r0 = move-exception
            r6 = 0
            goto L_0x01df
        L_0x01c1:
            r0 = move-exception
            r6 = 0
            goto L_0x01e3
        L_0x01c4:
            r5 = 1
            r6 = 1
        L_0x01c6:
            java.lang.String r0 = "customId"
            r3.putString(r0, r11)     // Catch:{ Throwable -> 0x01e2 }
            java.lang.String r0 = "resultList"
            r3.putParcelableArrayList(r0, r7)     // Catch:{ Throwable -> 0x01e2 }
            r5 = r6
            goto L_0x01d3
        L_0x01d2:
            r5 = 1
        L_0x01d3:
            java.lang.String r0 = "errorCode"
            r3.putInt(r0, r5)
        L_0x01d8:
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.a((int) r2, (android.os.Bundle) r3)
            return
        L_0x01de:
            r0 = move-exception
        L_0x01df:
            r2 = 1000(0x3e8, float:1.401E-42)
            goto L_0x01f2
        L_0x01e2:
            r0 = move-exception
        L_0x01e3:
            java.lang.String r2 = "GeoFenceManager"
            java.lang.String r4 = "doAddGeoFenceNearby"
            com.loc.es.a(r0, r2, r4)     // Catch:{ all -> 0x01de }
            java.lang.String r0 = "errorCode"
            r2 = 8
            r3.putInt(r0, r2)
            goto L_0x01d8
        L_0x01f2:
            java.lang.String r4 = "errorCode"
            r3.putInt(r4, r6)
            r1.a((int) r2, (android.os.Bundle) r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.j.b(int, android.os.Bundle):void");
    }

    private static boolean b(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z2 = true;
        try {
            if (a(aMapLocation, geoFence)) {
                if (geoFence.getEnterTime() == -1) {
                    if (geoFence.getStatus() != 1) {
                        geoFence.setEnterTime(fa.c());
                        geoFence.setStatus(1);
                        return true;
                    }
                } else if (geoFence.getStatus() != 3 && fa.c() - geoFence.getEnterTime() > 600000) {
                    geoFence.setStatus(3);
                    return true;
                }
            } else if (geoFence.getStatus() != 2) {
                try {
                    geoFence.setStatus(2);
                    geoFence.setEnterTime(-1);
                    return true;
                } catch (Throwable th) {
                    th = th;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            es.a(th, Utils.b, "isFenceStatusChanged");
            return z2;
        }
    }

    private void f() {
        if (!this.o) {
            this.o = true;
        }
        if (!this.n) {
            try {
                this.h = Looper.myLooper() == null ? new c(this.b.getMainLooper()) : new c();
            } catch (Throwable th) {
                es.a(th, "GeoFenceManger", "init 1");
            }
            try {
                this.l = new b("fenceActionThread");
                this.l.setPriority(5);
                this.l.start();
                this.k = new a(this.l.getLooper());
            } catch (Throwable th2) {
                es.a(th2, "GeoFenceManger", "init 2");
            }
            try {
                this.p = new k(this.b);
                this.q = new l();
                this.u = new AMapLocationClientOption();
                this.r = new AMapLocationClient(this.b);
                this.u.setLocationCacheEnable(true);
                this.u.setNeedAddress(false);
                this.r.setLocationListener(this.w);
                if (this.f6610a == null) {
                    this.f6610a = new ey();
                }
            } catch (Throwable th3) {
                es.a(th3, "GeoFenceManger", "initBase");
            }
            this.n = true;
            try {
                if (this.d != null && this.c == null) {
                    createPendingIntent(this.d);
                }
            } catch (Throwable th4) {
                es.a(th4, "GeoFenceManger", "init 4");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int a(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    /* JADX WARNING: type inference failed for: r7v6, types: [android.os.Parcelable] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.amap.api.fence.GeoFence a(android.os.Bundle r6, boolean r7) {
        /*
            r5 = this;
            com.amap.api.fence.GeoFence r0 = new com.amap.api.fence.GeoFence
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.amap.api.location.DPoint r2 = new com.amap.api.location.DPoint
            r2.<init>()
            if (r7 == 0) goto L_0x0030
            r7 = 1
            r0.setType(r7)
            java.lang.String r7 = "pointList"
            java.util.ArrayList r1 = r6.getParcelableArrayList(r7)
            if (r1 == 0) goto L_0x0021
            com.amap.api.location.DPoint r2 = b((java.util.List<com.amap.api.location.DPoint>) r1)
        L_0x0021:
            float r7 = b((com.amap.api.location.DPoint) r2, (java.util.List<com.amap.api.location.DPoint>) r1)
            r0.setMaxDis2Center(r7)
            float r7 = a((com.amap.api.location.DPoint) r2, (java.util.List<com.amap.api.location.DPoint>) r1)
            r0.setMinDis2Center(r7)
            goto L_0x005a
        L_0x0030:
            r7 = 0
            r0.setType(r7)
            java.lang.String r7 = "centerPoint"
            android.os.Parcelable r7 = r6.getParcelable(r7)
            r2 = r7
            com.amap.api.location.DPoint r2 = (com.amap.api.location.DPoint) r2
            if (r2 == 0) goto L_0x0042
            r1.add(r2)
        L_0x0042:
            java.lang.String r7 = "fenceRadius"
            r3 = 1148846080(0x447a0000, float:1000.0)
            float r7 = r6.getFloat(r7, r3)
            r4 = 0
            int r4 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0051
            r7 = 1148846080(0x447a0000, float:1000.0)
        L_0x0051:
            r0.setRadius(r7)
            r0.setMinDis2Center(r7)
            r0.setMaxDis2Center(r7)
        L_0x005a:
            int r7 = r5.f
            r0.setActivatesAction(r7)
            java.lang.String r7 = "customId"
            java.lang.String r6 = r6.getString(r7)
            r0.setCustomId(r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r6.add(r1)
            r0.setPointList(r6)
            r0.setCenter(r2)
            java.lang.String r6 = r5.d
            r0.setPendingIntentAction(r6)
            r6 = -1
            r0.setExpiration(r6)
            android.app.PendingIntent r6 = r5.c
            r0.setPendingIntent(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            long r1 = com.loc.l.a()
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            r0.setFenceId(r6)
            com.loc.ey r6 = r5.f6610a
            if (r6 == 0) goto L_0x00a4
            com.loc.ey r6 = r5.f6610a
            android.content.Context r7 = r5.b
            r1 = 2
            r6.a((android.content.Context) r7, (int) r1)
        L_0x00a4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.j.a(android.os.Bundle, boolean):com.amap.api.fence.GeoFence");
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        try {
            if (this.n) {
                if (this.g != null) {
                    this.g.clear();
                    this.g = null;
                }
                if (!this.o) {
                    try {
                        synchronized (this.i) {
                            if (this.k != null) {
                                this.k.removeCallbacksAndMessages((Object) null);
                            }
                            this.k = null;
                        }
                    } catch (Throwable th) {
                        es.a(th, "GeoFenceManager", "destroyActionHandler");
                    }
                    if (this.r != null) {
                        this.r.stopLocation();
                        this.r.onDestroy();
                    }
                    this.r = null;
                    if (this.l != null) {
                        if (Build.VERSION.SDK_INT >= 18) {
                            this.l.quitSafely();
                        } else {
                            this.l.quit();
                        }
                    }
                    this.l = null;
                    this.p = null;
                    synchronized (this.z) {
                        if (this.c != null) {
                            this.c.cancel();
                        }
                        this.c = null;
                    }
                    try {
                        synchronized (this.j) {
                            if (this.h != null) {
                                this.h.removeCallbacksAndMessages((Object) null);
                            }
                            this.h = null;
                        }
                    } catch (Throwable th2) {
                        es.a(th2, "GeoFenceManager", "destroyResultHandler");
                    }
                    if (this.f6610a != null) {
                        this.f6610a.b(this.b);
                    }
                    this.m = false;
                    this.n = false;
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    this.k.removeMessages(i2);
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, Bundle bundle) {
        try {
            synchronized (this.j) {
                if (this.h != null) {
                    Message obtainMessage = this.h.obtainMessage();
                    obtainMessage.what = i2;
                    obtainMessage.setData(bundle);
                    this.h.sendMessage(obtainMessage);
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, Bundle bundle, long j2) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    Message obtainMessage = this.k.obtainMessage();
                    obtainMessage.what = i2;
                    obtainMessage.setData(bundle);
                    this.k.sendMessageDelayed(obtainMessage, j2);
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Bundle bundle) {
        int i2 = 1;
        if (bundle != null) {
            try {
                i2 = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                es.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f != i2) {
            if (this.g != null && !this.g.isEmpty()) {
                Iterator<GeoFence> it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setStatus(0);
                    next.setEnterTime(-1);
                }
            }
            b();
        }
        this.f = i2;
    }

    /* access modifiers changed from: package-private */
    public final void a(AMapLocation aMapLocation) {
        try {
            if (this.y || this.g == null) {
                return;
            }
            if (!this.g.isEmpty()) {
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    Iterator<GeoFence> it = this.g.iterator();
                    while (it.hasNext()) {
                        GeoFence next = it.next();
                        if (next.isAble() && b(aMapLocation, next) && a(next, this.f)) {
                            next.setCurrentLocation(aMapLocation);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("geoFence", next);
                            a(1001, bundle);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    public final void addDistrictGeoFence(String str, String str2) {
        try {
            f();
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    public final void addKeywordGeoFence(String str, String str2, String str3, int i2, String str4) {
        try {
            f();
            if (i2 <= 0) {
                i2 = 10;
            }
            if (i2 > 25) {
                i2 = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("searchSize", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    public final void addNearbyGeoFence(String str, String str2, DPoint dPoint, float f2, int i2, String str3) {
        try {
            f();
            if (f2 <= 0.0f || f2 > 50000.0f) {
                f2 = 3000.0f;
            }
            if (i2 <= 0) {
                i2 = 10;
            }
            if (i2 > 25) {
                i2 = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f2);
            bundle.putInt("searchSize", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    public final void addPolygonGeoFence(List<DPoint> list, String str) {
        try {
            f();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", new ArrayList(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    public final void addRoundGeoFence(DPoint dPoint, float f2, String str) {
        try {
            f();
            Bundle bundle = new Bundle();
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("fenceRadius", f2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(0, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        if (!this.y && this.k != null) {
            boolean z2 = false;
            if (this.s != null && fa.a(this.s) && fa.c() - this.t < 10000) {
                z2 = true;
            }
            if (z2) {
                a(6, (Bundle) null, 0);
                a(5, (Bundle) null, 0);
                return;
            }
            a(7);
            a(7, (Bundle) null, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(int i2) {
        try {
            if (this.b != null) {
                synchronized (this.z) {
                    if (this.c != null) {
                        Intent intent = new Intent();
                        intent.putExtras(a((GeoFence) null, (String) null, (String) null, 4, i2));
                        this.c.send(this.b, 0, intent);
                    }
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(Bundle bundle) {
        b(2, bundle);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(com.amap.api.fence.GeoFence r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.z     // Catch:{ Throwable -> 0x0058 }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x0058 }
            android.content.Context r1 = r6.b     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0053
            android.app.PendingIntent r1 = r6.c     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x0013
            android.app.PendingIntent r1 = r7.getPendingIntent()     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x0013:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0055 }
            r1.<init>()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = r7.getFenceId()     // Catch:{ all -> 0x0055 }
            java.lang.String r3 = r7.getCustomId()     // Catch:{ all -> 0x0055 }
            int r4 = r7.getStatus()     // Catch:{ all -> 0x0055 }
            r5 = 0
            android.os.Bundle r2 = a(r7, r2, r3, r4, r5)     // Catch:{ all -> 0x0055 }
            r1.putExtras(r2)     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = r6.d     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x0035
            java.lang.String r2 = r6.d     // Catch:{ all -> 0x0055 }
            r1.setAction(r2)     // Catch:{ all -> 0x0055 }
        L_0x0035:
            android.content.Context r2 = r6.b     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = com.loc.u.c(r2)     // Catch:{ all -> 0x0055 }
            r1.setPackage(r2)     // Catch:{ all -> 0x0055 }
            android.app.PendingIntent r2 = r7.getPendingIntent()     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x004e
            android.app.PendingIntent r7 = r7.getPendingIntent()     // Catch:{ all -> 0x0055 }
            android.content.Context r2 = r6.b     // Catch:{ all -> 0x0055 }
        L_0x004a:
            r7.send(r2, r5, r1)     // Catch:{ all -> 0x0055 }
            goto L_0x0053
        L_0x004e:
            android.app.PendingIntent r7 = r6.c     // Catch:{ all -> 0x0055 }
            android.content.Context r2 = r6.b     // Catch:{ all -> 0x0055 }
            goto L_0x004a
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x0055:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r7     // Catch:{ Throwable -> 0x0058 }
        L_0x0058:
            r7 = move-exception
            java.lang.String r0 = "GeoFenceManager"
            java.lang.String r1 = "resultTriggerGeoFence"
            com.loc.es.a(r7, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.j.b(com.amap.api.fence.GeoFence):void");
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        try {
            if (this.m) {
                a(8);
            }
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(Bundle bundle) {
        b(1, bundle);
    }

    public final PendingIntent createPendingIntent(String str) {
        synchronized (this.z) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(u.c(this.b));
                this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
                this.d = str;
                if (this.g != null && !this.g.isEmpty()) {
                    Iterator<GeoFence> it = this.g.iterator();
                    while (it.hasNext()) {
                        GeoFence next = it.next();
                        next.setPendingIntent(this.c);
                        next.setPendingIntentAction(this.d);
                    }
                }
            } catch (Throwable th) {
                es.a(th, "GeoFenceManager", "createPendingIntent");
            }
        }
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        float f2;
        try {
            if (!this.y && fa.a(this.s)) {
                AMapLocation aMapLocation = this.s;
                ArrayList<GeoFence> arrayList = this.g;
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && arrayList != null && !arrayList.isEmpty()) {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    Iterator<GeoFence> it = arrayList.iterator();
                    float f3 = Float.MAX_VALUE;
                    while (true) {
                        if (!it.hasNext()) {
                            f2 = f3;
                            break;
                        }
                        GeoFence next = it.next();
                        if (next.isAble()) {
                            float a2 = fa.a(dPoint, next.getCenter());
                            if (a2 > next.getMinDis2Center() && a2 < next.getMaxDis2Center()) {
                                f2 = 0.0f;
                                break;
                            }
                            if (a2 > next.getMaxDis2Center()) {
                                f3 = Math.min(f3, a2 - next.getMaxDis2Center());
                            }
                            if (a2 < next.getMinDis2Center()) {
                                f3 = Math.min(f3, next.getMinDis2Center() - a2);
                            }
                        }
                    }
                } else {
                    f2 = Float.MAX_VALUE;
                }
                if (f2 != Float.MAX_VALUE) {
                    if (f2 < 1000.0f) {
                        a(7);
                        Bundle bundle = new Bundle();
                        bundle.putLong(Constants.Name.INTERVAL, 2000);
                        a(8, bundle, 500);
                    } else if (f2 < 5000.0f) {
                        c();
                        a(7);
                        a(7, (Bundle) null, 10000);
                    } else {
                        c();
                        a(7);
                        a(7, (Bundle) null, (long) (((f2 - 4000.0f) / 100.0f) * 1000.0f));
                    }
                }
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    /* access modifiers changed from: package-private */
    public final void d(Bundle bundle) {
        b(3, bundle);
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        try {
            a(7);
            a(8);
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "doPauseGeoFence");
        }
    }

    /* access modifiers changed from: package-private */
    public final void e(Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    String string = bundle.getString("fid");
                    if (!TextUtils.isEmpty(string)) {
                        boolean z2 = true;
                        boolean z3 = bundle.getBoolean("ab", true);
                        if (this.g != null && !this.g.isEmpty()) {
                            Iterator<GeoFence> it = this.g.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                GeoFence next = it.next();
                                if (next.getFenceId().equals(string)) {
                                    next.setAble(z3);
                                    break;
                                }
                            }
                        }
                        if (!z3) {
                            if (this.g != null) {
                                if (!this.g.isEmpty()) {
                                    Iterator<GeoFence> it2 = this.g.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            if (it2.next().isAble()) {
                                                z2 = false;
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                            if (z2) {
                                e();
                                return;
                            }
                            return;
                        }
                        b();
                    }
                }
            } catch (Throwable th) {
                es.a(th, "GeoFenceManager", "doSetGeoFenceAble");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void f(Bundle bundle) {
        try {
            if (this.g != null) {
                GeoFence geoFence = (GeoFence) bundle.getParcelable(d.ak);
                if (this.g.contains(geoFence)) {
                    this.g.remove(geoFence);
                }
                if (this.g.size() <= 0) {
                    a();
                } else {
                    b();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final List<GeoFence> getAllGeoFence() {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            return (ArrayList) this.g.clone();
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    public final boolean isPause() {
        return this.y;
    }

    public final void pauseGeoFence() {
        try {
            f();
            this.y = true;
            a(13, (Bundle) null, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "pauseGeoFence");
        }
    }

    public final void removeGeoFence() {
        try {
            this.o = false;
            a(10, (Bundle) null, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "removeGeoFence");
        }
    }

    public final boolean removeGeoFence(GeoFence geoFence) {
        try {
            if (this.g != null) {
                if (!this.g.isEmpty()) {
                    if (!this.g.contains(geoFence)) {
                        return false;
                    }
                    if (this.g.size() == 1) {
                        this.o = false;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(d.ak, geoFence);
                    a(11, bundle, 0);
                    return true;
                }
            }
            this.o = false;
            a(10, (Bundle) null, 0);
            return true;
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
            return false;
        }
    }

    public final void resumeGeoFence() {
        try {
            f();
            if (this.y) {
                this.y = false;
                b();
            }
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "resumeGeoFence");
        }
    }

    public final void setActivateAction(int i2) {
        try {
            f();
            if (i2 > 7 || i2 <= 0) {
                i2 = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i2);
            a(9, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    public final void setGeoFenceAble(String str, boolean z2) {
        try {
            f();
            Bundle bundle = new Bundle();
            bundle.putString("fid", str);
            bundle.putBoolean("ab", z2);
            a(12, bundle, 0);
        } catch (Throwable th) {
            es.a(th, "GeoFenceManager", "setGeoFenceAble");
        }
    }

    public final void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.e = geoFenceListener;
        } catch (Throwable unused) {
        }
    }
}
