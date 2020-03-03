package com.loc;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.amap.api.location.APSService;
import com.amap.api.location.LocationManagerBase;
import com.amap.api.location.UmidtokenInfo;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import java.util.ArrayList;
import java.util.Iterator;

public final class m implements LocationManagerBase {
    /* access modifiers changed from: private */
    public static boolean E = false;
    /* access modifiers changed from: private */
    public boolean A = false;
    private volatile boolean B = false;
    private boolean C = true;
    /* access modifiers changed from: private */
    public boolean D = true;
    private q F = null;
    private ServiceConnection G = new ServiceConnection() {
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                m.this.h = new Messenger(iBinder);
                boolean unused = m.this.A = true;
                m.this.q = true;
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "onServiceConnected");
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            m.this.h = null;
            boolean unused = m.this.A = false;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    AMapLocationClientOption f6616a = new AMapLocationClientOption();
    public c b;
    p c = null;
    ArrayList<AMapLocationListener> d = new ArrayList<>();
    boolean e = false;
    public boolean f = true;
    r g;
    Messenger h = null;
    Messenger i = null;
    Intent j = null;
    int k = 0;
    b l = null;
    boolean m = false;
    AMapLocationClientOption.AMapLocationMode n = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    Object o = new Object();
    ey p = null;
    boolean q = false;
    n r = null;
    String s = null;
    AMapLocationQualityReport t = null;
    boolean u = false;
    boolean v = false;
    a w = null;
    String x = null;
    boolean y = false;
    private Context z;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                switch (message.what) {
                    case 1002:
                        try {
                            m.a(m.this, (AMapLocationListener) message.obj);
                            return;
                        } catch (Throwable th) {
                            es.a(th, "AMapLocationManage$MHandlerr", "handleMessage SET_LISTENER");
                            return;
                        }
                    case 1003:
                        try {
                            m.this.c();
                            return;
                        } catch (Throwable th2) {
                            es.a(th2, "AMapLocationManage$MHandlerr", "handleMessage START_LOCATION");
                            return;
                        }
                    case 1004:
                        try {
                            m.this.d();
                            return;
                        } catch (Throwable th3) {
                            es.a(th3, "AMapLocationManage$MHandlerr", "handleMessage STOP_LOCATION");
                            return;
                        }
                    case 1005:
                        try {
                            m.b(m.this, (AMapLocationListener) message.obj);
                            return;
                        } catch (Throwable th4) {
                            es.a(th4, "AMapLocationManage$MHandlerr", "handleMessage REMOVE_LISTENER");
                            return;
                        }
                    case 1006:
                    case 1007:
                        return;
                    case 1008:
                        try {
                            m.g(m.this);
                            return;
                        } catch (Throwable th5) {
                            es.a(th5, "AMapLocationManage$MHandlerr", "handleMessage START_SOCKET");
                            return;
                        }
                    case 1009:
                        try {
                            m.h(m.this);
                            return;
                        } catch (Throwable th6) {
                            es.a(th6, "AMapLocationManage$MHandlerr", "handleMessage STOP_SOCKET");
                            return;
                        }
                    case 1010:
                        return;
                    case 1011:
                        try {
                            m.this.a();
                            return;
                        } catch (Throwable th7) {
                            es.a(th7, "AMapLocationManage$MHandlerr", "handleMessage DESTROY");
                            return;
                        }
                    case 1014:
                        m.b(m.this, message);
                        return;
                    case 1015:
                        try {
                            m.this.c.a(m.this.f6616a);
                            m.this.a(1025, (Object) null, 300000);
                            return;
                        } catch (Throwable th8) {
                            es.a(th8, "AMapLocationManage$MHandlerr", "handleMessage START_GPS_LOCATION");
                            return;
                        }
                    case 1016:
                        try {
                            if (m.this.c.b()) {
                                m.this.a(1016, (Object) null, 1000);
                                return;
                            } else {
                                m.d(m.this);
                                return;
                            }
                        } catch (Throwable th9) {
                            es.a(th9, "AMapLocationManage$MHandlerr", "handleMessage START_LBS_LOCATION");
                            return;
                        }
                    case 1017:
                        try {
                            m.this.c.a();
                            m.this.a(1025);
                            return;
                        } catch (Throwable th10) {
                            es.a(th10, "AMapLocationManage$MHandlerr", "handleMessage STOP_GPS_LOCATION");
                            return;
                        }
                    case 1018:
                        try {
                            m.this.f6616a = (AMapLocationClientOption) message.obj;
                            if (m.this.f6616a != null) {
                                m.f(m.this);
                                return;
                            }
                            return;
                        } catch (Throwable th11) {
                            es.a(th11, "AMapLocationManage$MHandlerr", "handleMessage SET_OPTION");
                            return;
                        }
                    case 1019:
                    case 1020:
                    case 1021:
                    case 1022:
                        return;
                    case 1023:
                        try {
                            m.c(m.this, message);
                            return;
                        } catch (Throwable th12) {
                            es.a(th12, "AMapLocationManage$MHandlerr", "handleMessage ACTION_ENABLE_BACKGROUND");
                            return;
                        }
                    case 1024:
                        try {
                            m.d(m.this, message);
                            return;
                        } catch (Throwable th13) {
                            es.a(th13, "AMapLocationManage$MHandlerr", "handleMessage ACTION_DISABLE_BACKGROUND");
                            return;
                        }
                    case 1025:
                        if (m.this.c != null) {
                            if (fa.c() - m.this.c.d > 300000) {
                                m.this.c.a();
                                m.this.c.a(m.this.f6616a);
                            }
                            m.this.a(1025, (Object) null, 300000);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Throwable th14) {
                es.a(th14, "AMapLocationManage$MHandlerr", "handleMessage");
            }
            es.a(th14, "AMapLocationManage$MHandlerr", "handleMessage");
        }
    }

    static class b extends HandlerThread {

        /* renamed from: a  reason: collision with root package name */
        m f6620a = null;

        public b(String str, m mVar) {
            super(str);
            this.f6620a = mVar;
        }

        /* access modifiers changed from: protected */
        public final void onLooperPrepared() {
            try {
                this.f6620a.g.a();
                this.f6620a.f();
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    public class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                if (!m.this.m || es.d()) {
                    switch (message.what) {
                        case 1:
                            try {
                                m.a(m.this, message.getData());
                                return;
                            } catch (Throwable th) {
                                es.a(th, "AmapLocationManager$ActionHandler", "handleMessage RESULT_LBS_LOCATIONSUCCESS");
                                return;
                            }
                        case 2:
                            break;
                        case 3:
                            return;
                        case 5:
                            try {
                                Bundle data = message.getData();
                                data.putBundle("optBundle", es.a(m.this.f6616a));
                                m.this.a(10, data);
                                return;
                            } catch (Throwable th2) {
                                es.a(th2, "AmapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONCHANGE");
                                return;
                            }
                        case 6:
                            try {
                                Bundle data2 = message.getData();
                                if (m.this.c != null) {
                                    p pVar = m.this.c;
                                    if (data2 != null) {
                                        try {
                                            data2.setClassLoader(AMapLocation.class.getClassLoader());
                                            pVar.g = data2.getInt("I_MAX_GEO_DIS");
                                            pVar.h = data2.getInt("I_MIN_GEO_DIS");
                                            AMapLocation aMapLocation = (AMapLocation) data2.getParcelable("loc");
                                            if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                                synchronized (pVar.o) {
                                                    pVar.y = aMapLocation;
                                                }
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th3) {
                                            es.a(th3, "GpsLocation", "setLastGeoLocation");
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } catch (Throwable th4) {
                                es.a(th4, "AmapLocationManager$ActionHandler", "handleMessage RESULT_GPS_GEO_SUCCESS");
                                return;
                            }
                        case 7:
                            try {
                                boolean unused = m.this.D = message.getData().getBoolean("ngpsAble");
                                return;
                            } catch (Throwable th5) {
                                es.a(th5, "AmapLocationManager$ActionHandler", "handleMessage RESULT_NGPS_ABLE");
                                return;
                            }
                        case 8:
                            ey.a((String) null, 2141);
                            break;
                        case 9:
                            boolean unused2 = m.E = message.getData().getBoolean("installMockApp");
                            return;
                        default:
                            return;
                    }
                    try {
                        m.a(m.this, message);
                    } catch (Throwable th6) {
                        es.a(th6, "AmapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONSUCCESS");
                    }
                }
            } catch (Throwable th7) {
                es.a(th7, "AmapLocationManager$MainHandler", "handleMessage");
            }
        }
    }

    public m(Context context, Intent intent) {
        this.z = context;
        this.j = intent;
        try {
            this.b = Looper.myLooper() == null ? new c(this.z.getMainLooper()) : new c();
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "init 1");
        }
        try {
            this.g = new r(this.z);
        } catch (Throwable th2) {
            es.a(th2, "AmapLocationManager", "init 5");
        }
        this.l = new b("amapLocManagerThread", this);
        this.l.setPriority(5);
        this.l.start();
        this.w = a(this.l.getLooper());
        try {
            this.c = new p(this.z, this.b);
        } catch (Throwable th3) {
            es.a(th3, "AmapLocationManager", "init 3");
        }
        if (this.p == null) {
            this.p = new ey();
        }
    }

    private AMapLocationServer a(cs csVar) {
        if (!this.f6616a.isLocationCacheEnable()) {
            return null;
        }
        try {
            return csVar.j();
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "doFirstCacheLoc");
            return null;
        }
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.o) {
            this.w = new a(looper);
            aVar = this.w;
        }
        return aVar;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        synchronized (this.o) {
            if (this.w != null) {
                this.w.removeMessages(i2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                boolean z2 = (th instanceof IllegalStateException) && th.getMessage().contains("sending message to a Handler on a dead thread");
                if ((th instanceof RemoteException) || z2) {
                    this.h = null;
                    this.A = false;
                }
                es.a(th, "AmapLocationManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = es.b(this.z);
        }
        bundle.putString("c", this.s);
        Message obtain = Message.obtain();
        obtain.what = i2;
        obtain.setData(bundle);
        obtain.replyTo = this.i;
        if (this.h != null) {
            this.h.send(obtain);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Object obj, long j2) {
        synchronized (this.o) {
            if (this.w != null) {
                Message obtain = Message.obtain();
                obtain.what = i2;
                if (obj instanceof Bundle) {
                    obtain.setData((Bundle) obj);
                } else {
                    obtain.obj = obj;
                }
                this.w.sendMessageDelayed(obtain, j2);
            }
        }
    }

    private void a(Intent intent, boolean z2) {
        if (this.z != null) {
            if (Build.VERSION.SDK_INT >= 26 && z2) {
                try {
                    this.z.getClass().getMethod("startForegroundService", new Class[]{Intent.class}).invoke(this.z, new Object[]{intent});
                } catch (Throwable unused) {
                }
                this.y = true;
            }
            this.z.startService(intent);
            this.y = true;
        }
    }

    private void a(AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() != 0) {
                aMapLocation.setLocationType(0);
            }
            if (aMapLocation.getErrorCode() == 0) {
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                if ((latitude == 0.0d && longitude == 0.0d) || latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
                    ey.a("errorLatLng", aMapLocation.toStr());
                    aMapLocation.setLocationType(0);
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("LatLng is error#0802");
                }
            }
            if ("gps".equalsIgnoreCase(aMapLocation.getProvider()) || !this.c.b()) {
                aMapLocation.setAltitude(fa.b(aMapLocation.getAltitude()));
                aMapLocation.setBearing(fa.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(fa.a(aMapLocation.getSpeed()));
                Iterator<AMapLocationListener> it = this.d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(com.amap.api.location.AMapLocation r5, long r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x001b
            com.amap.api.location.AMapLocation r5 = new com.amap.api.location.AMapLocation     // Catch:{ Throwable -> 0x0018 }
            java.lang.String r0 = ""
            r5.<init>((java.lang.String) r0)     // Catch:{ Throwable -> 0x0018 }
            r0 = 8
            r5.setErrorCode(r0)     // Catch:{ Throwable -> 0x0018 }
            java.lang.String r0 = "amapLocation is null#0801"
            r5.setLocationDetail(r0)     // Catch:{ Throwable -> 0x0018 }
            goto L_0x001b
        L_0x0015:
            r5 = move-exception
            goto L_0x00ef
        L_0x0018:
            r5 = move-exception
            goto L_0x00e6
        L_0x001b:
            java.lang.String r0 = "gps"
            java.lang.String r1 = r5.getProvider()     // Catch:{ Throwable -> 0x0018 }
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch:{ Throwable -> 0x0018 }
            if (r0 != 0) goto L_0x002c
            java.lang.String r0 = "lbs"
            r5.setProvider(r0)     // Catch:{ Throwable -> 0x0018 }
        L_0x002c:
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            if (r0 != 0) goto L_0x0037
            com.amap.api.location.AMapLocationQualityReport r0 = new com.amap.api.location.AMapLocationQualityReport     // Catch:{ Throwable -> 0x0018 }
            r0.<init>()     // Catch:{ Throwable -> 0x0018 }
            r4.t = r0     // Catch:{ Throwable -> 0x0018 }
        L_0x0037:
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationClientOption r1 = r4.f6616a     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r1.getLocationMode()     // Catch:{ Throwable -> 0x0018 }
            r0.setLocationMode(r1)     // Catch:{ Throwable -> 0x0018 }
            com.loc.p r0 = r4.c     // Catch:{ Throwable -> 0x0018 }
            if (r0 == 0) goto L_0x005c
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            com.loc.p r1 = r4.c     // Catch:{ Throwable -> 0x0018 }
            int r1 = r1.d()     // Catch:{ Throwable -> 0x0018 }
            r0.setGPSSatellites(r1)     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            com.loc.p r1 = r4.c     // Catch:{ Throwable -> 0x0018 }
            int r1 = r1.c()     // Catch:{ Throwable -> 0x0018 }
            r0.setGpsStatus(r1)     // Catch:{ Throwable -> 0x0018 }
        L_0x005c:
            android.content.Context r0 = r4.z     // Catch:{ Throwable -> 0x0018 }
            boolean r0 = com.loc.fa.h((android.content.Context) r0)     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationQualityReport r1 = r4.t     // Catch:{ Throwable -> 0x0018 }
            r1.setWifiAble(r0)     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            android.content.Context r1 = r4.z     // Catch:{ Throwable -> 0x0018 }
            java.lang.String r1 = com.loc.fa.i((android.content.Context) r1)     // Catch:{ Throwable -> 0x0018 }
            r0.setNetworkType(r1)     // Catch:{ Throwable -> 0x0018 }
            int r0 = r5.getLocationType()     // Catch:{ Throwable -> 0x0018 }
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0087
            java.lang.String r0 = "gps"
            java.lang.String r1 = r5.getProvider()     // Catch:{ Throwable -> 0x0018 }
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch:{ Throwable -> 0x0018 }
            if (r0 == 0) goto L_0x0088
        L_0x0087:
            r6 = r2
        L_0x0088:
            com.amap.api.location.AMapLocationQualityReport r0 = r4.t     // Catch:{ Throwable -> 0x0018 }
            r0.setNetUseTime(r6)     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationQualityReport r6 = r4.t     // Catch:{ Throwable -> 0x0018 }
            boolean r7 = E     // Catch:{ Throwable -> 0x0018 }
            r6.setInstallHighDangerMockApp(r7)     // Catch:{ Throwable -> 0x0018 }
            com.amap.api.location.AMapLocationQualityReport r6 = r4.t     // Catch:{ Throwable -> 0x0018 }
            r5.setLocationQualityReport(r6)     // Catch:{ Throwable -> 0x0018 }
            boolean r6 = r4.B     // Catch:{ Throwable -> 0x00c5 }
            if (r6 == 0) goto L_0x00cd
            java.lang.String r6 = r4.x     // Catch:{ Throwable -> 0x00c5 }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ Throwable -> 0x00c5 }
            r7.<init>()     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r0 = "loc"
            r7.putParcelable(r0, r5)     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r0 = "lastLocNb"
            r7.putString(r0, r6)     // Catch:{ Throwable -> 0x00c5 }
            r6 = 1014(0x3f6, float:1.421E-42)
            r4.a((int) r6, (java.lang.Object) r7, (long) r2)     // Catch:{ Throwable -> 0x00c5 }
            android.content.Context r6 = r4.z     // Catch:{ Throwable -> 0x00c5 }
            com.loc.ey.a((android.content.Context) r6, (com.amap.api.location.AMapLocation) r5)     // Catch:{ Throwable -> 0x00c5 }
            android.content.Context r6 = r4.z     // Catch:{ Throwable -> 0x00c5 }
            com.loc.ey.b(r6, r5)     // Catch:{ Throwable -> 0x00c5 }
            com.amap.api.location.AMapLocation r5 = r5.clone()     // Catch:{ Throwable -> 0x00c5 }
            r4.a((com.amap.api.location.AMapLocation) r5)     // Catch:{ Throwable -> 0x00c5 }
            goto L_0x00cd
        L_0x00c5:
            r5 = move-exception
            java.lang.String r6 = "AmapLocationManager"
            java.lang.String r7 = "handlerLocation part2"
            com.loc.es.a(r5, r6, r7)     // Catch:{ Throwable -> 0x0018 }
        L_0x00cd:
            boolean r5 = r4.m     // Catch:{ Throwable -> 0x0018 }
            if (r5 == 0) goto L_0x00d9
            boolean r5 = com.loc.es.d()     // Catch:{ Throwable -> 0x0018 }
            if (r5 != 0) goto L_0x00d9
            monitor-exit(r4)
            return
        L_0x00d9:
            com.amap.api.location.AMapLocationClientOption r5 = r4.f6616a     // Catch:{ Throwable -> 0x0018 }
            boolean r5 = r5.isOnceLocation()     // Catch:{ Throwable -> 0x0018 }
            if (r5 == 0) goto L_0x00e4
            r4.d()     // Catch:{ Throwable -> 0x0018 }
        L_0x00e4:
            monitor-exit(r4)
            return
        L_0x00e6:
            java.lang.String r6 = "AmapLocationManager"
            java.lang.String r7 = "handlerLocation part3"
            com.loc.es.a(r5, r6, r7)     // Catch:{ all -> 0x0015 }
            monitor-exit(r4)
            return
        L_0x00ef:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.m.a(com.amap.api.location.AMapLocation, long):void");
    }

    private static void a(cs csVar, AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer != null) {
            try {
                if (aMapLocationServer.getErrorCode() == 0) {
                    csVar.b(aMapLocationServer);
                }
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "apsLocation:doFirstAddCache");
            }
        }
    }

    static /* synthetic */ void a(m mVar, Bundle bundle) {
        AMapLocation aMapLocation;
        long j2 = 0;
        AMapLocation aMapLocation2 = null;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                mVar.x = bundle.getString(CBConstant.NB);
                long j3 = bundle.getLong("netUseTime", 0);
                if (aMapLocation != null) {
                    try {
                        if (aMapLocation.getErrorCode() == 0 && mVar.c != null) {
                            mVar.c.w = 0;
                            if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                mVar.c.y = aMapLocation;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        j2 = j3;
                        es.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                        mVar.a(aMapLocation2, j2);
                    }
                }
                j2 = j3;
            } catch (Throwable th2) {
                th = th2;
                es.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                mVar.a(aMapLocation2, j2);
            }
        } else {
            aMapLocation = null;
        }
        aMapLocation2 = mVar.c != null ? mVar.c.a(aMapLocation, mVar.x) : aMapLocation;
        mVar.a(aMapLocation2, j2);
    }

    static /* synthetic */ void a(m mVar, Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (mVar.f && mVar.h != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", es.a(mVar.f6616a));
                mVar.a(0, bundle);
                mVar.f = false;
            }
            mVar.a(aMapLocation, 0);
            if (mVar.D) {
                mVar.a(7, (Bundle) null);
            }
            mVar.a(1025);
            mVar.a(1025, (Object) null, 300000);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "resultGpsLocationSuccess");
        }
    }

    static /* synthetic */ void a(m mVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener != null) {
            if (mVar.d == null) {
                mVar.d = new ArrayList<>();
            }
            if (!mVar.d.contains(aMapLocationListener)) {
                mVar.d.add(aMapLocationListener);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("listener参数不能为null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0090 A[Catch:{ Throwable -> 0x00b7, Throwable -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c6 A[Catch:{ Throwable -> 0x00e8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.autonavi.aps.amapapi.model.AMapLocationServer b(com.loc.cs r14) {
        /*
            r13 = this;
            r0 = 0
            com.loc.ex r1 = new com.loc.ex     // Catch:{ Throwable -> 0x0114 }
            r1.<init>()     // Catch:{ Throwable -> 0x0114 }
            r2 = 0
            long r3 = com.loc.fa.c()     // Catch:{ Throwable -> 0x0114 }
            r1.a((long) r3)     // Catch:{ Throwable -> 0x0114 }
            java.lang.String r3 = com.amap.api.location.AMapLocationClientOption.getAPIKEY()     // Catch:{ Throwable -> 0x001e }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x001e }
            if (r4 != 0) goto L_0x0026
            android.content.Context r4 = r13.z     // Catch:{ Throwable -> 0x001e }
            com.loc.v.a((android.content.Context) r4, (java.lang.String) r3)     // Catch:{ Throwable -> 0x001e }
            goto L_0x0026
        L_0x001e:
            r3 = move-exception
            java.lang.String r4 = "AmapLocationManager"
            java.lang.String r5 = "apsLocation setAuthKey"
            com.loc.es.a(r3, r4, r5)     // Catch:{ Throwable -> 0x0114 }
        L_0x0026:
            java.lang.String r3 = com.amap.api.location.UmidtokenInfo.getUmidtoken()     // Catch:{ Throwable -> 0x0034 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0034 }
            if (r4 != 0) goto L_0x003c
            com.loc.x.a((java.lang.String) r3)     // Catch:{ Throwable -> 0x0034 }
            goto L_0x003c
        L_0x0034:
            r3 = move-exception
            java.lang.String r4 = "AmapLocationManager"
            java.lang.String r5 = "apsLocation setUmidToken"
            com.loc.es.a(r3, r4, r5)     // Catch:{ Throwable -> 0x0114 }
        L_0x003c:
            android.content.Context r3 = r13.z     // Catch:{ Throwable -> 0x004a }
            r14.a((android.content.Context) r3)     // Catch:{ Throwable -> 0x004a }
            com.amap.api.location.AMapLocationClientOption r3 = r13.f6616a     // Catch:{ Throwable -> 0x004a }
            r14.a((com.amap.api.location.AMapLocationClientOption) r3)     // Catch:{ Throwable -> 0x004a }
            r14.i()     // Catch:{ Throwable -> 0x004a }
            goto L_0x0052
        L_0x004a:
            r3 = move-exception
            java.lang.String r4 = "AmapLocationManager"
            java.lang.String r5 = "initApsBase"
            com.loc.es.a(r3, r4, r5)     // Catch:{ Throwable -> 0x0114 }
        L_0x0052:
            r3 = 0
            boolean r5 = com.loc.er.v()     // Catch:{ Throwable -> 0x0114 }
            com.autonavi.aps.amapapi.model.AMapLocationServer r6 = r13.a((com.loc.cs) r14)     // Catch:{ Throwable -> 0x0114 }
            r7 = 1
            if (r6 != 0) goto L_0x0082
            r2 = r5 ^ 1
            com.autonavi.aps.amapapi.model.AMapLocationServer r2 = r14.a((boolean) r2)     // Catch:{ Throwable -> 0x0076 }
            if (r2 == 0) goto L_0x006f
            long r8 = r2.k()     // Catch:{ Throwable -> 0x006d }
            r3 = r8
            goto L_0x006f
        L_0x006d:
            r6 = move-exception
            goto L_0x007a
        L_0x006f:
            if (r5 != 0) goto L_0x0074
            a((com.loc.cs) r14, (com.autonavi.aps.amapapi.model.AMapLocationServer) r2)     // Catch:{ Throwable -> 0x006d }
        L_0x0074:
            r6 = 1
            goto L_0x0084
        L_0x0076:
            r2 = move-exception
            r12 = r6
            r6 = r2
            r2 = r12
        L_0x007a:
            java.lang.String r8 = "AmapLocationManager"
            java.lang.String r9 = "apsLocation:doFirstNetLocate"
            com.loc.es.a(r6, r8, r9)     // Catch:{ Throwable -> 0x0110 }
            goto L_0x0074
        L_0x0082:
            r2 = r6
            r6 = 0
        L_0x0084:
            long r8 = com.loc.fa.c()     // Catch:{ Throwable -> 0x0110 }
            r1.b(r8)     // Catch:{ Throwable -> 0x0110 }
            r1.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r2)     // Catch:{ Throwable -> 0x0110 }
            if (r2 == 0) goto L_0x009c
            java.lang.String r0 = r2.l()     // Catch:{ Throwable -> 0x0110 }
            com.amap.api.location.AMapLocation r8 = r2.clone()     // Catch:{ Throwable -> 0x0110 }
            r12 = r8
            r8 = r0
            r0 = r12
            goto L_0x009d
        L_0x009c:
            r8 = r0
        L_0x009d:
            com.amap.api.location.AMapLocationClientOption r9 = r13.f6616a     // Catch:{ Throwable -> 0x00b7 }
            boolean r9 = r9.isLocationCacheEnable()     // Catch:{ Throwable -> 0x00b7 }
            if (r9 == 0) goto L_0x00bf
            com.loc.r r9 = r13.g     // Catch:{ Throwable -> 0x00b7 }
            if (r9 == 0) goto L_0x00bf
            com.loc.r r9 = r13.g     // Catch:{ Throwable -> 0x00b7 }
            com.amap.api.location.AMapLocationClientOption r10 = r13.f6616a     // Catch:{ Throwable -> 0x00b7 }
            long r10 = r10.getLastLocationLifeCycle()     // Catch:{ Throwable -> 0x00b7 }
            com.amap.api.location.AMapLocation r8 = r9.a(r0, r8, r10)     // Catch:{ Throwable -> 0x00b7 }
            r0 = r8
            goto L_0x00bf
        L_0x00b7:
            r8 = move-exception
            java.lang.String r9 = "AmapLocationManager"
            java.lang.String r10 = "fixLastLocation"
            com.loc.es.a(r8, r9, r10)     // Catch:{ Throwable -> 0x0110 }
        L_0x00bf:
            android.os.Bundle r8 = new android.os.Bundle     // Catch:{ Throwable -> 0x00e8 }
            r8.<init>()     // Catch:{ Throwable -> 0x00e8 }
            if (r0 == 0) goto L_0x00d9
            java.lang.String r9 = "loc"
            r8.putParcelable(r9, r0)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r0 = "nb"
            java.lang.String r9 = r2.l()     // Catch:{ Throwable -> 0x00e8 }
            r8.putString(r0, r9)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r0 = "netUseTime"
            r8.putLong(r0, r3)     // Catch:{ Throwable -> 0x00e8 }
        L_0x00d9:
            android.os.Message r0 = android.os.Message.obtain()     // Catch:{ Throwable -> 0x00e8 }
            r0.setData(r8)     // Catch:{ Throwable -> 0x00e8 }
            r0.what = r7     // Catch:{ Throwable -> 0x00e8 }
            com.loc.m$c r3 = r13.b     // Catch:{ Throwable -> 0x00e8 }
            r3.sendMessage(r0)     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x00f0
        L_0x00e8:
            r0 = move-exception
            java.lang.String r3 = "AmapLocationManager"
            java.lang.String r4 = "apsLocation:callback"
            com.loc.es.a(r0, r3, r4)     // Catch:{ Throwable -> 0x0110 }
        L_0x00f0:
            android.content.Context r0 = r13.z     // Catch:{ Throwable -> 0x0110 }
            com.loc.ey.a((android.content.Context) r0, (com.loc.ex) r1)     // Catch:{ Throwable -> 0x0110 }
            if (r6 == 0) goto L_0x010c
            if (r5 == 0) goto L_0x010c
            r14.c()     // Catch:{ Throwable -> 0x0104 }
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r14.a((boolean) r7)     // Catch:{ Throwable -> 0x0104 }
            a((com.loc.cs) r14, (com.autonavi.aps.amapapi.model.AMapLocationServer) r0)     // Catch:{ Throwable -> 0x0104 }
            goto L_0x010c
        L_0x0104:
            r0 = move-exception
            java.lang.String r1 = "AmapLocationManager"
            java.lang.String r3 = "apsLocation:doFirstNetLocate 2"
            com.loc.es.a(r0, r1, r3)     // Catch:{ Throwable -> 0x0110 }
        L_0x010c:
            r14.f()     // Catch:{ Throwable -> 0x011f }
            goto L_0x011f
        L_0x0110:
            r0 = move-exception
            goto L_0x0117
        L_0x0112:
            r0 = move-exception
            goto L_0x0120
        L_0x0114:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0117:
            java.lang.String r1 = "AmapLocationManager"
            java.lang.String r3 = "apsLocation"
            com.loc.es.a(r0, r1, r3)     // Catch:{ all -> 0x0112 }
            goto L_0x010c
        L_0x011f:
            return r2
        L_0x0120:
            r14.f()     // Catch:{ Throwable -> 0x0123 }
        L_0x0123:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.m.b(com.loc.cs):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002d */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035 A[Catch:{ Throwable -> 0x003b }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(com.loc.m r3, android.os.Message r4) {
        /*
            android.os.Bundle r4 = r4.getData()     // Catch:{ Throwable -> 0x003b }
            java.lang.String r0 = "loc"
            android.os.Parcelable r0 = r4.getParcelable(r0)     // Catch:{ Throwable -> 0x003b }
            com.amap.api.location.AMapLocation r0 = (com.amap.api.location.AMapLocation) r0     // Catch:{ Throwable -> 0x003b }
            java.lang.String r1 = "lastLocNb"
            java.lang.String r4 = r4.getString(r1)     // Catch:{ Throwable -> 0x003b }
            if (r0 == 0) goto L_0x002d
            r1 = 0
            com.loc.ej r2 = com.loc.r.b     // Catch:{ Throwable -> 0x002d }
            if (r2 != 0) goto L_0x0024
            com.loc.r r2 = r3.g     // Catch:{ Throwable -> 0x002d }
            if (r2 == 0) goto L_0x002a
            com.loc.r r1 = r3.g     // Catch:{ Throwable -> 0x002d }
            com.amap.api.location.AMapLocation r1 = r1.b()     // Catch:{ Throwable -> 0x002d }
            goto L_0x002a
        L_0x0024:
            com.loc.ej r1 = com.loc.r.b     // Catch:{ Throwable -> 0x002d }
            com.amap.api.location.AMapLocation r1 = r1.a()     // Catch:{ Throwable -> 0x002d }
        L_0x002a:
            com.loc.ey.a((com.amap.api.location.AMapLocation) r1, (com.amap.api.location.AMapLocation) r0)     // Catch:{ Throwable -> 0x002d }
        L_0x002d:
            com.loc.r r1 = r3.g     // Catch:{ Throwable -> 0x003b }
            boolean r4 = r1.a(r0, r4)     // Catch:{ Throwable -> 0x003b }
            if (r4 == 0) goto L_0x003a
            com.loc.r r3 = r3.g     // Catch:{ Throwable -> 0x003b }
            r3.d()     // Catch:{ Throwable -> 0x003b }
        L_0x003a:
            return
        L_0x003b:
            r3 = move-exception
            java.lang.String r4 = "AmapLocationManager"
            java.lang.String r0 = "doSaveLastLocation"
            com.loc.es.a(r3, r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.m.b(com.loc.m, android.os.Message):void");
    }

    static /* synthetic */ void b(m mVar, AMapLocationListener aMapLocationListener) {
        if (!mVar.d.isEmpty() && mVar.d.contains(aMapLocationListener)) {
            mVar.d.remove(aMapLocationListener);
        }
        if (mVar.d.isEmpty()) {
            mVar.d();
        }
    }

    private boolean b() {
        boolean z2 = false;
        int i2 = 0;
        do {
            try {
                if (this.h != null) {
                    break;
                }
                Thread.sleep(100);
                i2++;
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "checkAPSManager");
            }
        } while (i2 < 50);
        if (this.h == null) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setErrorCode(10);
            aMapLocation.setLocationDetail(!fa.l(this.z.getApplicationContext()) ? "请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内#1003" : "启动ApsServcie失败#1001");
            bundle.putParcelable("loc", aMapLocation);
            obtain.setData(bundle);
            obtain.what = 1;
            this.b.sendMessage(obtain);
        } else {
            z2 = true;
        }
        if (!z2) {
            ey.a((String) null, !fa.l(this.z.getApplicationContext()) ? 2103 : 2101);
        }
        return z2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r6 = this;
            monitor-enter(r6)
            com.amap.api.location.AMapLocationClientOption r0 = r6.f6616a     // Catch:{ all -> 0x005f }
            if (r0 != 0) goto L_0x000c
            com.amap.api.location.AMapLocationClientOption r0 = new com.amap.api.location.AMapLocationClientOption     // Catch:{ all -> 0x005f }
            r0.<init>()     // Catch:{ all -> 0x005f }
            r6.f6616a = r0     // Catch:{ all -> 0x005f }
        L_0x000c:
            boolean r0 = r6.B     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0012
            monitor-exit(r6)
            return
        L_0x0012:
            r0 = 1
            r6.B = r0     // Catch:{ all -> 0x005f }
            int[] r0 = com.loc.m.AnonymousClass2.f6618a     // Catch:{ all -> 0x005f }
            com.amap.api.location.AMapLocationClientOption r1 = r6.f6616a     // Catch:{ all -> 0x005f }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r1.getLocationMode()     // Catch:{ all -> 0x005f }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x005f }
            r0 = r0[r1]     // Catch:{ all -> 0x005f }
            r1 = 1015(0x3f7, float:1.422E-42)
            r2 = 1016(0x3f8, float:1.424E-42)
            r3 = 0
            r5 = 0
            switch(r0) {
                case 1: goto L_0x0053;
                case 2: goto L_0x004b;
                case 3: goto L_0x002e;
                default: goto L_0x002d;
            }     // Catch:{ all -> 0x005f }
        L_0x002d:
            goto L_0x005d
        L_0x002e:
            r6.a((int) r1, (java.lang.Object) r5, (long) r3)     // Catch:{ all -> 0x005f }
            com.amap.api.location.AMapLocationClientOption r0 = r6.f6616a     // Catch:{ all -> 0x005f }
            boolean r0 = r0.isGpsFirst()     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0047
            com.amap.api.location.AMapLocationClientOption r0 = r6.f6616a     // Catch:{ all -> 0x005f }
            boolean r0 = r0.isOnceLocation()     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0047
            com.amap.api.location.AMapLocationClientOption r0 = r6.f6616a     // Catch:{ all -> 0x005f }
            long r3 = r0.getGpsFirstTimeout()     // Catch:{ all -> 0x005f }
        L_0x0047:
            r6.a((int) r2, (java.lang.Object) r5, (long) r3)     // Catch:{ all -> 0x005f }
            goto L_0x005d
        L_0x004b:
            r6.a((int) r2)     // Catch:{ all -> 0x005f }
            r6.a((int) r1, (java.lang.Object) r5, (long) r3)     // Catch:{ all -> 0x005f }
            monitor-exit(r6)
            return
        L_0x0053:
            r0 = 1017(0x3f9, float:1.425E-42)
            r6.a((int) r0, (java.lang.Object) r5, (long) r3)     // Catch:{ all -> 0x005f }
            r6.a((int) r2, (java.lang.Object) r5, (long) r3)     // Catch:{ all -> 0x005f }
            monitor-exit(r6)
            return
        L_0x005d:
            monitor-exit(r6)
            return
        L_0x005f:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.m.c():void");
    }

    static /* synthetic */ void c(m mVar, Message message) {
        if (message != null) {
            try {
                Bundle data = message.getData();
                if (data != null) {
                    int i2 = data.getInt("i", 0);
                    Intent g2 = mVar.g();
                    g2.putExtra("i", i2);
                    g2.putExtra("h", (Notification) data.getParcelable("h"));
                    g2.putExtra("g", 1);
                    mVar.a(g2, true);
                }
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "doEnableBackgroundLocation");
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        try {
            a(1025);
            if (this.c != null) {
                this.c.a();
            }
            a(1016);
            this.B = false;
            this.k = 0;
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "stopLocation");
        }
    }

    static /* synthetic */ void d(m mVar) {
        try {
            if (mVar.C) {
                mVar.C = false;
                AMapLocationServer b2 = mVar.b(new cs());
                if (mVar.b()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (b2 != null && (b2.getLocationType() == 2 || b2.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putBundle("optBundle", es.a(mVar.f6616a));
                    bundle.putString("isCacheLoc", str);
                    mVar.a(0, bundle);
                }
            } else {
                if (mVar.q && !mVar.isStarted() && !mVar.v) {
                    mVar.v = true;
                    mVar.f();
                }
                if (mVar.b()) {
                    mVar.v = false;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBundle("optBundle", es.a(mVar.f6616a));
                    bundle2.putString("d", UmidtokenInfo.getUmidtoken());
                    if (!mVar.c.b()) {
                        mVar.a(1, bundle2);
                    }
                }
            }
        } catch (Throwable th) {
            try {
                es.a(th, "AmapLocationManager", "doLBSLocation");
                try {
                    if (!mVar.f6616a.isOnceLocation()) {
                        mVar.e();
                        return;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            } catch (Throwable unused2) {
            }
        }
        try {
            if (!mVar.f6616a.isOnceLocation()) {
                mVar.e();
                return;
            }
            return;
        } catch (Throwable unused3) {
            return;
        }
        throw th;
    }

    static /* synthetic */ void d(m mVar, Message message) {
        if (message != null) {
            try {
                Bundle data = message.getData();
                if (data != null) {
                    boolean z2 = data.getBoolean("j", true);
                    Intent g2 = mVar.g();
                    g2.putExtra("j", z2);
                    g2.putExtra("g", 2);
                    mVar.a(g2, false);
                }
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "doDisableBackgroundLocation");
            }
        }
    }

    private void e() {
        if (this.f6616a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
            long j2 = 1000;
            if (this.f6616a.getInterval() >= 1000) {
                j2 = this.f6616a.getInterval();
            }
            a(1016, (Object) null, j2);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        try {
            if (this.i == null) {
                this.i = new Messenger(this.b);
            }
            this.z.bindService(g(), this.G, 1);
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ void f(m mVar) {
        ey eyVar;
        Context context;
        int i2;
        p pVar = mVar.c;
        AMapLocationClientOption aMapLocationClientOption = mVar.f6616a;
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        pVar.c = aMapLocationClientOption;
        if (!(pVar.c.getLocationMode() == AMapLocationClientOption.AMapLocationMode.Device_Sensors || pVar.f6627a == null)) {
            pVar.f6627a.removeMessages(8);
        }
        if (pVar.r != pVar.c.getGeoLanguage()) {
            synchronized (pVar.o) {
                pVar.y = null;
            }
        }
        pVar.r = pVar.c.getGeoLanguage();
        if (mVar.B && !mVar.f6616a.getLocationMode().equals(mVar.n)) {
            mVar.d();
            mVar.c();
        }
        mVar.n = mVar.f6616a.getLocationMode();
        if (mVar.p != null) {
            if (mVar.f6616a.isOnceLocation()) {
                eyVar = mVar.p;
                context = mVar.z;
                i2 = 0;
            } else {
                eyVar = mVar.p;
                context = mVar.z;
                i2 = 1;
            }
            eyVar.a(context, i2);
            mVar.p.a(mVar.z, mVar.f6616a);
        }
    }

    private Intent g() {
        String str;
        if (this.j == null) {
            this.j = new Intent(this.z, APSService.class);
        }
        try {
            str = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : u.f(this.z);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "startServiceImpl p2");
            str = "";
        }
        this.j.putExtra("a", str);
        this.j.putExtra("b", u.c(this.z));
        this.j.putExtra("d", UmidtokenInfo.getUmidtoken());
        this.j.putExtra("f", AMapLocationClientOption.isDownloadCoordinateConvertLibrary());
        return this.j;
    }

    static /* synthetic */ void g(m mVar) {
        try {
            if (mVar.h != null) {
                mVar.k = 0;
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", es.a(mVar.f6616a));
                mVar.a(2, bundle);
                return;
            }
            mVar.k++;
            if (mVar.k < 10) {
                mVar.a(1008, (Object) null, 50);
            }
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "startAssistantLocationImpl");
        }
    }

    static /* synthetic */ void h(m mVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle("optBundle", es.a(mVar.f6616a));
            mVar.a(3, bundle);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "stopAssistantLocationImpl");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        a(12, (Bundle) null);
        this.C = true;
        this.f = true;
        this.A = false;
        this.q = false;
        d();
        if (this.p != null) {
            this.p.b(this.z);
        }
        ey.a(this.z);
        if (this.r != null) {
            this.r.d.sendEmptyMessage(11);
        } else if (this.G != null) {
            this.z.unbindService(this.G);
        }
        try {
            if (this.y) {
                this.z.stopService(g());
            }
        } catch (Throwable unused) {
        }
        this.y = false;
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.G = null;
        synchronized (this.o) {
            if (this.w != null) {
                this.w.removeCallbacksAndMessages((Object) null);
            }
            this.w = null;
        }
        if (this.l != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    ew.a((Object) this.l, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused2) {
                }
            }
            this.l.quit();
        }
        this.l = null;
        if (this.b != null) {
            this.b.removeCallbacksAndMessages((Object) null);
        }
        if (this.g != null) {
            this.g.c();
            this.g = null;
        }
    }

    public final void disableBackgroundLocation(boolean z2) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("j", z2);
            a(1024, (Object) bundle, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "disableBackgroundLocation");
        }
    }

    public final void enableBackgroundLocation(int i2, Notification notification) {
        if (i2 != 0 && notification != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("i", i2);
                bundle.putParcelable("h", notification);
                a(1023, (Object) bundle, 0);
            } catch (Throwable th) {
                es.a(th, "AmapLocationManager", "disableBackgroundLocation");
            }
        }
    }

    public final AMapLocation getLastKnownLocation() {
        AMapLocation aMapLocation = null;
        try {
            if (this.g == null) {
                return null;
            }
            AMapLocation b2 = this.g.b();
            if (b2 != null) {
                try {
                    b2.setTrustedLevel(3);
                } catch (Throwable th) {
                    AMapLocation aMapLocation2 = b2;
                    th = th;
                    aMapLocation = aMapLocation2;
                }
            }
            return b2;
        } catch (Throwable th2) {
            th = th2;
            es.a(th, "AmapLocationManager", "getLastKnownLocation");
            return aMapLocation;
        }
    }

    public final boolean isStarted() {
        return this.A;
    }

    public final void onDestroy() {
        try {
            if (this.F != null) {
                this.F.b();
                this.F = null;
            }
            a(1011, (Object) null, 0);
            this.m = true;
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", ActivityInfo.TYPE_STR_ONDESTROY);
        }
    }

    public final void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, (Object) aMapLocationListener, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "setLocationListener");
        }
    }

    public final void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a(1018, (Object) aMapLocationClientOption.clone(), 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "setLocationOption");
        }
    }

    public final void startAssistantLocation() {
        try {
            a(1008, (Object) null, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "startAssistantLocation");
        }
    }

    public final void startAssistantLocation(WebView webView) {
        if (this.F == null) {
            this.F = new q(this.z, webView);
        }
        this.F.a();
    }

    public final void startLocation() {
        try {
            a(1003, (Object) null, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "startLocation");
        }
    }

    public final void stopAssistantLocation() {
        try {
            if (this.F != null) {
                this.F.b();
                this.F = null;
            }
            a(1009, (Object) null, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "stopAssistantLocation");
        }
    }

    public final void stopLocation() {
        try {
            a(1004, (Object) null, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "stopLocation");
        }
    }

    public final void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, (Object) aMapLocationListener, 0);
        } catch (Throwable th) {
            es.a(th, "AmapLocationManager", "unRegisterLocationListener");
        }
    }
}
