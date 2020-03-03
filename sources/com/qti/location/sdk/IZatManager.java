package com.qti.location.sdk;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import com.qti.debugreport.IDebugReportCallback;
import com.qti.debugreport.IDebugReportService;
import com.qti.flp.IFlpService;
import com.qti.flp.ILocationCallback;
import com.qti.flp.IMaxPowerAllocatedCallback;
import com.qti.flp.ISessionStatusCallback;
import com.qti.flp.ITestService;
import com.qti.geofence.GeofenceData;
import com.qti.geofence.IGeofenceCallback;
import com.qti.geofence.IGeofenceService;
import com.qti.gnssconfig.IGnssConfigCallback;
import com.qti.gnssconfig.IGnssConfigService;
import com.qti.izat.IIzatService;
import com.qti.location.sdk.IZatDBCommon;
import com.qti.location.sdk.IZatDebugReportingService;
import com.qti.location.sdk.IZatFlpService;
import com.qti.location.sdk.IZatGeofenceService;
import com.qti.location.sdk.IZatGnssConfigService;
import com.qti.location.sdk.IZatTestService;
import com.qti.location.sdk.IZatWWANDBProvider;
import com.qti.location.sdk.IZatWWANDBReceiver;
import com.qti.location.sdk.IZatWiFiDBProvider;
import com.qti.location.sdk.IZatWiFiDBReceiver;
import com.qti.location.sdk.utils.IZatDataValidation;
import com.qti.location.sdk.utils.IZatValidationResults;
import com.qti.wifidbprovider.APObsLocData;
import com.qti.wifidbprovider.APScan;
import com.qti.wifidbprovider.IWiFiDBProvider;
import com.qti.wifidbprovider.IWiFiDBProviderResponseListener;
import com.qti.wifidbreceiver.APInfo;
import com.qti.wifidbreceiver.APInfoExt;
import com.qti.wifidbreceiver.APLocationData;
import com.qti.wifidbreceiver.APSpecialInfo;
import com.qti.wifidbreceiver.IWiFiDBReceiver;
import com.qti.wifidbreceiver.IWiFiDBReceiverResponseListener;
import com.qti.wwandbprovider.BSObsLocationData;
import com.qti.wwandbprovider.IWWANDBProvider;
import com.qti.wwandbprovider.IWWANDBProviderResponseListener;
import com.qti.wwandbreceiver.BSInfo;
import com.qti.wwandbreceiver.BSLocationData;
import com.qti.wwandbreceiver.BSSpecialInfo;
import com.qti.wwandbreceiver.IWWANDBReceiver;
import com.qti.wwandbreceiver.IWWANDBReceiverResponseListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IZatManager {
    private static final int A = -1;
    private static final int B = 1;
    private static final int C = 2;
    private static final int D = 4;
    private static final int E = 1;
    private static final int F = 2;
    private static volatile int G = 0;
    /* access modifiers changed from: private */
    public static final Object H = new Object();
    /* access modifiers changed from: private */
    public static final Object I = new Object();
    /* access modifiers changed from: private */
    public static final Object J = new Object();
    /* access modifiers changed from: private */
    public static final Object K = new Object();
    /* access modifiers changed from: private */
    public static final Object L = new Object();
    /* access modifiers changed from: private */
    public static final Object M = new Object();
    /* access modifiers changed from: private */
    public static final Object N = new Object();
    private static final Object O = new Object();
    private static final Object P = new Object();
    private static final Object Q = new Object();
    /* access modifiers changed from: private */
    public static final Object R = new Object();
    /* access modifiers changed from: private */
    public static final Object S = new Object();
    private static final Object T = new Object();
    private static final String U = "com.qualcomm.location.izat.IzatService";
    private static IZatManager W = null;
    private static IIzatService X = null;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f8601a = "IZatManager";
    /* access modifiers changed from: private */
    public static final boolean b = Log.isLoggable(f8601a, 2);
    private static final String c = "7.3.1";
    private static final int d = -1;
    private static final HashSet<String> m = new HashSet<>();
    private static final int y = -1;
    private static final int z = 0;
    private Context V;
    private GeofenceStatusCallbackWrapper Y = new GeofenceStatusCallbackWrapper();
    private DebugReportCallbackWrapper Z = new DebugReportCallbackWrapper();
    private WiFiDBReceiverRespListenerWrapper aa = new WiFiDBReceiverRespListenerWrapper();
    private WiFiDBProviderRespListenerWrapper ab = new WiFiDBProviderRespListenerWrapper();
    private WWANDBReceiverRespListenerWrapper ac = new WWANDBReceiverRespListenerWrapper();
    /* access modifiers changed from: private */
    public GnssConfigCallbackWrapper ad = new GnssConfigCallbackWrapper();
    private WWANDBProviderRespListenerWrapper ae = new WWANDBProviderRespListenerWrapper();
    /* access modifiers changed from: private */
    public int e = -1;
    private final int f = 1;
    private final int g = 2;
    private final int h = 4;
    private final int i = 8;
    private final int j = 16;
    private final int k = 32;
    private final int l = 64;
    /* access modifiers changed from: private */
    public Map<IZatFlpServiceImpl.IZatSessionHandlerImpl, FlpRequestMapItem> n = u();
    /* access modifiers changed from: private */
    public Map<IZatFlpService.IFlpLocationCallback, LocationCallbackWrapper> o = v();
    /* access modifiers changed from: private */
    public Map<IZatTestService.IFlpMaxPowerAllocatedCallback, MaxPowerAllocatedCallbackWrapper> p = w();
    /* access modifiers changed from: private */
    public Map<IZatGeofenceServiceImpl, IZatGeofenceService.IZatGeofenceCallback> q = x();
    /* access modifiers changed from: private */
    public Map<IZatGeofenceServiceImpl.IZatGeofenceHandleImpl, GeofenceMapItem> r = y();
    /* access modifiers changed from: private */
    public Map<IZatDebugReportingServiceImpl, IZatDebugReportingService.IZatDebugReportCallback> s = z();
    /* access modifiers changed from: private */
    public Map<IZatGnssConfigServiceImpl, IZatGnssConfigService.IZatGnssConfigCallback> t = A();
    /* access modifiers changed from: private */
    public IZatWiFiDBReceiverImpl u = null;
    /* access modifiers changed from: private */
    public IZatWWANDBReceiverImpl v = null;
    /* access modifiers changed from: private */
    public IZatWiFiDBProviderImpl w = null;
    /* access modifiers changed from: private */
    public IZatWWANDBProviderImpl x = null;

    static /* synthetic */ int i() {
        int i2 = G;
        G = i2 + 1;
        return i2;
    }

    static /* synthetic */ int l() {
        int i2 = G;
        G = i2 - 1;
        return i2;
    }

    static {
        m.add("umi");
        m.add("lmi");
        m.add("cmi");
    }

    private Map<IZatFlpServiceImpl.IZatSessionHandlerImpl, FlpRequestMapItem> u() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatFlpService.IFlpLocationCallback, LocationCallbackWrapper> v() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatTestService.IFlpMaxPowerAllocatedCallback, MaxPowerAllocatedCallbackWrapper> w() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatGeofenceServiceImpl, IZatGeofenceService.IZatGeofenceCallback> x() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatGeofenceServiceImpl.IZatGeofenceHandleImpl, GeofenceMapItem> y() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatDebugReportingServiceImpl, IZatDebugReportingService.IZatDebugReportCallback> z() {
        return Collections.synchronizedMap(new HashMap());
    }

    private Map<IZatGnssConfigServiceImpl, IZatGnssConfigService.IZatGnssConfigCallback> A() {
        return Collections.synchronizedMap(new HashMap());
    }

    public static synchronized IZatManager a(Context context) throws IZatIllegalArgumentException {
        IZatManager iZatManager;
        synchronized (IZatManager.class) {
            if (!a()) {
                throw new RuntimeException("Not support IZat for this device");
            } else if (context != null) {
                if (W == null) {
                    W = new IZatManager(context);
                }
                iZatManager = W;
            } else {
                throw new IZatIllegalArgumentException("null argument");
            }
        }
        return iZatManager;
    }

    private IZatManager(Context context) {
        G = Process.myTid() << 8;
        if (b) {
            String str = f8601a;
            int i2 = G;
            Log.v(str, "IZatManager ctor sFlpRequestsCnt=" + i2);
        }
        this.V = context;
    }

    public static final boolean a() {
        if ((m.contains(Build.DEVICE.toLowerCase()) && Build.VERSION.SDK_INT >= 29) || SystemProperties.getBoolean("ro.gps.fence", false)) {
            return true;
        }
        if (b) {
            Log.d(f8601a, "Not support!!");
        }
        return false;
    }

    public String b() throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            String version = X.getVersion();
            if (version == null) {
                version = "1.0.0";
            }
            return "7.3.1:" + version;
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IzatService version", e2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:3|4|5|(2:7|(2:8|(3:10|11|(2:26|13)(2:14|15))(0)))(0)|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.qti.flp.IFlpService r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x003f
            java.lang.Object r0 = H
            monitor-enter(r0)
            int r1 = r5.e     // Catch:{ all -> 0x003c }
            r2 = -1
            if (r2 != r1) goto L_0x0022
            r1 = 0
        L_0x000b:
            r3 = 10
            if (r1 >= r3) goto L_0x0022
            int r3 = r6.getAllSupportedFeatures()     // Catch:{ RemoteException | InterruptedException -> 0x0022 }
            r5.e = r3     // Catch:{ RemoteException | InterruptedException -> 0x0022 }
            int r3 = r5.e     // Catch:{ RemoteException | InterruptedException -> 0x0022 }
            if (r2 == r3) goto L_0x001a
            goto L_0x0022
        L_0x001a:
            r3 = 200(0xc8, double:9.9E-322)
            java.lang.Thread.sleep(r3)     // Catch:{ RemoteException | InterruptedException -> 0x0022 }
            int r1 = r1 + 1
            goto L_0x000b
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            java.lang.String r6 = f8601a
            int r0 = r5.e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "got mFlpFeaturMasks"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.d(r6, r0)
            return
        L_0x003c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            throw r6
        L_0x003f:
            com.qti.location.sdk.IZatServiceUnavailableException r6 = new com.qti.location.sdk.IZatServiceUnavailableException
            java.lang.String r0 = "FLP Service is not available."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.a(com.qti.flp.IFlpService):void");
    }

    private synchronized void B() {
        if (X == null) {
            if (b) {
                Log.d(f8601a, "Connecting to Izat service by name [com.qualcomm.location.izat.IzatService]");
            }
            if (this.V.getPackageManager().resolveService(new Intent(U), 0) == null) {
                Log.e(f8601a, "Izat service (com.qualcomm.location.izat.IzatService) not installed");
                throw new IZatServiceUnavailableException("Izat service unavailable.");
            } else if (ServiceManager.getService(U) != null) {
                X = IIzatService.Stub.asInterface(ServiceManager.getService(U));
                if (X == null) {
                    Log.e(f8601a, "Izat service (com.qualcomm.location.izat.IzatService) not started");
                    throw new IZatServiceUnavailableException("Izat service unavailable.");
                }
            } else {
                Log.e(f8601a, "Izat service (com.qualcomm.location.izat.IzatService) is not started");
                throw new IZatServiceUnavailableException("Izat service not started.");
            }
        }
    }

    public IZatFlpService c() throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            IFlpService flpService = X.getFlpService();
            if (flpService != null) {
                a(flpService);
                if ((this.e & 2) > 0) {
                    return new IZatFlpServiceImpl(flpService);
                }
                Log.e(f8601a, "Izat FLP positioning is not supported on this device.");
                return null;
            }
            throw new IZatServiceUnavailableException("FLP Service is not available.");
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IFlpService", e2);
        }
    }

    public void a(IZatFlpService iZatFlpService) throws IZatIllegalArgumentException {
        if (iZatFlpService == null || !(iZatFlpService instanceof IZatFlpServiceImpl)) {
            throw new IZatIllegalArgumentException();
        }
        try {
            synchronized (H) {
                IFlpService flpService = X.getFlpService();
                if (flpService != null) {
                    for (IZatFlpServiceImpl.IZatSessionHandlerImpl iZatSessionHandlerImpl : this.n.keySet()) {
                        FlpRequestMapItem flpRequestMapItem = this.n.get(iZatSessionHandlerImpl);
                        if (flpService.stopFlpSession(flpRequestMapItem.h()) != 0) {
                            Log.e(f8601a, "stopFlpSession failed in disconnecting");
                            return;
                        }
                        if ((this.e & 8) > 0) {
                            if (NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL == flpRequestMapItem.b()) {
                                flpService.unregisterCallback(1, flpRequestMapItem.g());
                            }
                            if (NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX == flpRequestMapItem.b()) {
                                flpService.unregisterCallback(2, flpRequestMapItem.g());
                            }
                        } else {
                            flpService.unregisterCallback(4, flpRequestMapItem.g());
                        }
                        if (flpRequestMapItem.k() != null) {
                            flpService.unregisterForSessionStatus(flpRequestMapItem.k());
                        }
                    }
                    this.n.clear();
                    for (IZatFlpService.IFlpLocationCallback iFlpLocationCallback : this.o.keySet()) {
                        flpService.unregisterCallback(4, this.o.get(iFlpLocationCallback));
                    }
                    this.o.clear();
                    return;
                }
                throw new IZatServiceUnavailableException("FLP Service is not available.");
            }
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed stop all flp sessions", e2);
        }
    }

    public IZatTestService d() throws IZatServiceUnavailableException, IZatSecurityException {
        if (this.V.getApplicationInfo().isSystemApp()) {
            if (X == null) {
                B();
            }
            try {
                ITestService testService = X.getTestService();
                if (testService != null) {
                    return new IZatTestServiceImpl(testService);
                }
                throw new IZatServiceUnavailableException("Test Service is not available.");
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to get ITestService", e2);
            }
        } else {
            throw new IZatSecurityException("Test Service is only available to system app.");
        }
    }

    public void a(IZatTestService iZatTestService) throws IZatIllegalArgumentException {
        if (iZatTestService == null || !(iZatTestService instanceof IZatTestServiceImpl)) {
            throw new IZatIllegalArgumentException();
        }
        synchronized (I) {
            for (IZatTestService.IFlpMaxPowerAllocatedCallback b2 : this.p.keySet()) {
                iZatTestService.b(b2);
            }
            this.p.clear();
        }
    }

    public IZatGeofenceService e() throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            IGeofenceService geofenceService = X.getGeofenceService();
            if (geofenceService != null) {
                synchronized (J) {
                    geofenceService.registerCallback(this.Y);
                }
                return new IZatGeofenceServiceImpl(geofenceService);
            }
            throw new IZatServiceUnavailableException("Geofence Service is not available.");
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IGeofenceService", e2);
        }
    }

    public void a(IZatGeofenceService iZatGeofenceService) throws IZatIllegalArgumentException {
        if (iZatGeofenceService == null || !(iZatGeofenceService instanceof IZatGeofenceServiceImpl)) {
            throw new IZatIllegalArgumentException();
        }
        try {
            synchronized (J) {
                IGeofenceService geofenceService = X.getGeofenceService();
                if (geofenceService != null) {
                    for (IZatGeofenceServiceImpl.IZatGeofenceHandleImpl iZatGeofenceHandleImpl : this.r.keySet()) {
                        geofenceService.removeGeofence(this.r.get(iZatGeofenceHandleImpl).b());
                    }
                    geofenceService.unregisterCallback(this.Y);
                } else {
                    throw new IZatServiceUnavailableException("Geofence Service is not available.");
                }
            }
            synchronized (K) {
                this.r.clear();
            }
            synchronized (L) {
                Iterator<IZatGeofenceServiceImpl> it = this.q.keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        IZatGeofenceServiceImpl next = it.next();
                        if (iZatGeofenceService == next) {
                            this.q.remove(next);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to remove all geofence added", e2);
        }
    }

    public IZatDebugReportingService f() throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            IDebugReportService debugReportService = X.getDebugReportService();
            if (debugReportService != null) {
                synchronized (M) {
                    debugReportService.registerForDebugReporting(this.Z);
                }
                return new IZatDebugReportingServiceImpl(debugReportService);
            }
            throw new IZatServiceUnavailableException("Debug Reporting Service is not available.");
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IDebugReportService", e2);
        }
    }

    public void a(IZatDebugReportingService iZatDebugReportingService) {
        if (iZatDebugReportingService == null || !(iZatDebugReportingService instanceof IZatDebugReportingServiceImpl)) {
            throw new IZatIllegalArgumentException();
        }
        try {
            IDebugReportService debugReportService = X.getDebugReportService();
            if (debugReportService != null) {
                synchronized (M) {
                    debugReportService.unregisterForDebugReporting(this.Z);
                }
                synchronized (N) {
                    Iterator<IZatDebugReportingServiceImpl> it = this.s.keySet().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            IZatDebugReportingServiceImpl next = it.next();
                            if (iZatDebugReportingService == next) {
                                this.s.remove(next);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                return;
            }
            throw new IZatServiceUnavailableException("Debug Report Service is not available.");
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to disconnect DebugReportService", e2);
        }
    }

    @Deprecated
    public IZatWiFiDBReceiver a(IZatWiFiDBReceiver.IZatWiFiDBReceiverResponseListener iZatWiFiDBReceiverResponseListener) throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        if (iZatWiFiDBReceiverResponseListener != null) {
            try {
                if (this.u == null) {
                    IWiFiDBReceiver wiFiDBReceiver = X.getWiFiDBReceiver();
                    synchronized (O) {
                        wiFiDBReceiver.registerResponseListener(this.aa);
                    }
                    this.u = new IZatWiFiDBReceiverImpl(wiFiDBReceiver, iZatWiFiDBReceiverResponseListener);
                }
                return this.u;
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to get IWiFiDBReceiver", e2);
            }
        } else {
            throw new IZatIllegalArgumentException("Listener can not be null.");
        }
    }

    public IZatWiFiDBReceiver a(IZatWiFiDBReceiver.IZatWiFiDBReceiverResponseListenerExt iZatWiFiDBReceiverResponseListenerExt, PendingIntent pendingIntent) throws IZatServiceUnavailableException, IZatIllegalArgumentException, IZatSecurityException {
        if (this.V.getApplicationInfo().isSystemApp()) {
            if (X == null) {
                B();
            }
            if (iZatWiFiDBReceiverResponseListenerExt == null) {
                throw new IZatIllegalArgumentException("Listener can not be null.");
            } else if (pendingIntent != null) {
                try {
                    if (this.u == null) {
                        IWiFiDBReceiver wiFiDBReceiver = X.getWiFiDBReceiver();
                        synchronized (O) {
                            wiFiDBReceiver.registerResponseListenerExt(this.aa, pendingIntent);
                        }
                        this.u = new IZatWiFiDBReceiverImpl(wiFiDBReceiver, iZatWiFiDBReceiverResponseListenerExt);
                    }
                    return this.u;
                } catch (RemoteException e2) {
                    throw new RuntimeException("Failed to get IWiFiDBReceiver", e2);
                }
            } else {
                throw new IZatIllegalArgumentException("Pending intent can not be null.");
            }
        } else {
            throw new IZatSecurityException("WiFiDBReceiver is only available to system apps.");
        }
    }

    public void a(IZatWiFiDBReceiver iZatWiFiDBReceiver) throws IZatIllegalArgumentException, IZatSecurityException {
        if (!this.V.getApplicationInfo().isSystemApp()) {
            throw new IZatSecurityException("WWANDBProvider is only available to system app.");
        } else if (iZatWiFiDBReceiver == null || !(iZatWiFiDBReceiver instanceof IZatWiFiDBReceiverImpl)) {
            throw new IZatIllegalArgumentException();
        } else {
            try {
                IWiFiDBReceiver wiFiDBReceiver = X.getWiFiDBReceiver();
                synchronized (O) {
                    wiFiDBReceiver.removeResponseListener(this.aa);
                }
                this.u = null;
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to disconnect WiFiDBReceiver", e2);
            }
        }
    }

    public IZatWWANDBReceiver a(IZatWWANDBReceiver.IZatWWANDBReceiverResponseListener iZatWWANDBReceiverResponseListener) throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            if (this.v == null) {
                IWWANDBReceiver wWANDBReceiver = X.getWWANDBReceiver();
                synchronized (Q) {
                    wWANDBReceiver.registerResponseListener(this.ac);
                }
                this.v = new IZatWWANDBReceiverImpl(wWANDBReceiver, iZatWWANDBReceiverResponseListener);
            }
            return this.v;
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IWWANDBReceiver", e2);
        }
    }

    public IZatWWANDBReceiver a(IZatWWANDBReceiver.IZatWWANDBReceiverResponseListenerExt iZatWWANDBReceiverResponseListenerExt, PendingIntent pendingIntent) throws IZatServiceUnavailableException, IZatIllegalArgumentException, IZatServiceUnavailableException {
        if (this.V.getApplicationInfo().isSystemApp()) {
            if (X == null) {
                B();
            }
            if (iZatWWANDBReceiverResponseListenerExt == null) {
                throw new IZatIllegalArgumentException("Listener can not be null.");
            } else if (pendingIntent != null) {
                try {
                    if (this.v == null) {
                        IWWANDBReceiver wWANDBReceiver = X.getWWANDBReceiver();
                        synchronized (Q) {
                            wWANDBReceiver.registerResponseListenerExt(this.ac, pendingIntent);
                        }
                        this.v = new IZatWWANDBReceiverImpl(wWANDBReceiver, iZatWWANDBReceiverResponseListenerExt);
                    }
                    return this.v;
                } catch (RemoteException e2) {
                    throw new RuntimeException("Failed to get IWWANDBReceiver", e2);
                }
            } else {
                throw new IZatIllegalArgumentException("Pending intent can not be null.");
            }
        } else {
            throw new IZatSecurityException("WWANDBReceiver is only available to system app.");
        }
    }

    public void a(IZatWWANDBReceiver iZatWWANDBReceiver) throws IZatIllegalArgumentException, IZatSecurityException {
        if (!this.V.getApplicationInfo().isSystemApp()) {
            throw new IZatSecurityException("WWANDBProvider is only available to system app.");
        } else if (iZatWWANDBReceiver == null || !(iZatWWANDBReceiver instanceof IZatWWANDBReceiverImpl)) {
            throw new IZatIllegalArgumentException();
        } else {
            try {
                IWWANDBReceiver wWANDBReceiver = X.getWWANDBReceiver();
                synchronized (Q) {
                    wWANDBReceiver.removeResponseListener(this.ac);
                }
                this.v = null;
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to disconnect WWANDBReceiver", e2);
            }
        }
    }

    public IZatWiFiDBProvider a(IZatWiFiDBProvider.IZatWiFiDBProviderResponseListener iZatWiFiDBProviderResponseListener, PendingIntent pendingIntent) throws IZatServiceUnavailableException, IZatIllegalArgumentException, IZatSecurityException {
        if (this.V.getApplicationInfo().isSystemApp()) {
            if (X == null) {
                B();
            }
            if (iZatWiFiDBProviderResponseListener == null) {
                throw new IZatIllegalArgumentException("Listener can not be null.");
            } else if (pendingIntent != null) {
                try {
                    if (this.w == null) {
                        IWiFiDBProvider wiFiDBProvider = X.getWiFiDBProvider();
                        synchronized (P) {
                            wiFiDBProvider.registerResponseListener(this.ab, pendingIntent);
                        }
                        this.w = new IZatWiFiDBProviderImpl(wiFiDBProvider, iZatWiFiDBProviderResponseListener);
                    }
                    return this.w;
                } catch (RemoteException e2) {
                    throw new RuntimeException("Failed to get IWiFiDBReceiver", e2);
                }
            } else {
                throw new IZatIllegalArgumentException("Pending intent can not be null.");
            }
        } else {
            throw new IZatSecurityException("WiFiDBProvider is only available to system apps.");
        }
    }

    public void a(IZatWiFiDBProvider iZatWiFiDBProvider) throws IZatIllegalArgumentException, IZatSecurityException {
        if (!this.V.getApplicationInfo().isSystemApp()) {
            throw new IZatSecurityException("WWANDBProvider is only available to system app.");
        } else if (iZatWiFiDBProvider == null || !(iZatWiFiDBProvider instanceof IZatWiFiDBProviderImpl)) {
            throw new IZatIllegalArgumentException();
        } else {
            try {
                IWiFiDBProvider wiFiDBProvider = X.getWiFiDBProvider();
                synchronized (O) {
                    wiFiDBProvider.removeResponseListener(this.ab);
                }
                this.w = null;
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to disconnect WifiDBProvider", e2);
            }
        }
    }

    public IZatWWANDBProvider a(IZatWWANDBProvider.IZatWWANDBProviderResponseListener iZatWWANDBProviderResponseListener, PendingIntent pendingIntent) throws IZatServiceUnavailableException, IZatIllegalArgumentException, IZatServiceUnavailableException {
        if (this.V.getApplicationInfo().isSystemApp()) {
            if (X == null) {
                B();
            }
            if (iZatWWANDBProviderResponseListener == null) {
                throw new IZatIllegalArgumentException("Listener can not be null.");
            } else if (pendingIntent != null) {
                try {
                    if (this.x == null) {
                        IWWANDBProvider wWANDBProvider = X.getWWANDBProvider();
                        synchronized (T) {
                            wWANDBProvider.registerResponseListener(this.ae, pendingIntent);
                        }
                        this.x = new IZatWWANDBProviderImpl(wWANDBProvider, iZatWWANDBProviderResponseListener);
                    }
                    return this.x;
                } catch (RemoteException e2) {
                    throw new RuntimeException("Failed to get IWWANDBReceiver", e2);
                }
            } else {
                throw new IZatIllegalArgumentException("Pending intent can not be null.");
            }
        } else {
            throw new IZatSecurityException("WWANDBProvider is only available to system app.");
        }
    }

    public void a(IZatWWANDBProvider iZatWWANDBProvider) throws IZatIllegalArgumentException, IZatSecurityException {
        if (!this.V.getApplicationInfo().isSystemApp()) {
            throw new IZatSecurityException("WWANDBProvider is only available to system app.");
        } else if (iZatWWANDBProvider == null || !(iZatWWANDBProvider instanceof IZatWWANDBProviderImpl)) {
            throw new IZatIllegalArgumentException();
        } else {
            try {
                IWWANDBProvider wWANDBProvider = X.getWWANDBProvider();
                synchronized (O) {
                    wWANDBProvider.removeResponseListener(this.ae);
                }
                this.x = null;
            } catch (RemoteException e2) {
                throw new RuntimeException("Failed to disconnect WWANDBProvider", e2);
            }
        }
    }

    public IZatGnssConfigService g() throws IZatServiceUnavailableException {
        if (X == null) {
            B();
        }
        try {
            a(X.getFlpService());
            IGnssConfigService gnssConfigService = X.getGnssConfigService();
            if (gnssConfigService == null) {
                throw new IZatServiceUnavailableException("Gnss Config Service is not available.");
            } else if ((this.e & 64) > 0) {
                return new IZatGnssConfigServiceImpl(gnssConfigService);
            } else {
                Log.e(f8601a, "Izat Gnss SV constellation config is not supported on this device.");
                return null;
            }
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to get IGnssConfigService", e2);
        }
    }

    public void a(IZatGnssConfigService iZatGnssConfigService) {
        if (iZatGnssConfigService == null || !(iZatGnssConfigService instanceof IZatGnssConfigServiceImpl)) {
            throw new IZatIllegalArgumentException();
        }
        try {
            if (X.getGnssConfigService() != null) {
                synchronized (S) {
                    Iterator<IZatGnssConfigServiceImpl> it = this.t.keySet().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            IZatGnssConfigServiceImpl next = it.next();
                            if (iZatGnssConfigService == next) {
                                this.t.remove(next);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                return;
            }
            throw new IZatServiceUnavailableException("Gnss Config Service is not available.");
        } catch (RemoteException e2) {
            throw new RuntimeException("Failed to disconnect GnssConfigService", e2);
        }
    }

    private class IZatFlpServiceImpl implements IZatFlpService {

        /* renamed from: a  reason: collision with root package name */
        IFlpService f8605a;

        public IZatFlpServiceImpl(IFlpService iFlpService) {
            this.f8605a = iFlpService;
        }

        public IZatFlpService.IZatFlpSessionHandle a(IZatFlpService.IFlpLocationCallback iFlpLocationCallback, IZatFlpService.IzatFlpRequest izatFlpRequest) throws IZatIllegalArgumentException {
            IZatFlpService.IFlpLocationCallback iFlpLocationCallback2 = iFlpLocationCallback;
            IZatFlpService.IzatFlpRequest izatFlpRequest2 = izatFlpRequest;
            if (iFlpLocationCallback2 == null || izatFlpRequest2 == null) {
                throw new IZatIllegalArgumentException("invalid input parameter");
            } else if (izatFlpRequest.d() > 0 || izatFlpRequest.e() > 0 || izatFlpRequest.f() > 0) {
                IZatDataValidation.a(izatFlpRequest);
                try {
                    synchronized (IZatManager.H) {
                        try {
                            NotificationType notificationType = NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX;
                            if (izatFlpRequest2.f) {
                                notificationType = NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL;
                                if ((izatFlpRequest2 instanceof IZatFlpService.IzatFlpBgRequest) && IZatFlpService.IzatFlpBgRequest.IzatFlpBgRequestMode.TRIP_MODE == ((IZatFlpService.IzatFlpBgRequest) izatFlpRequest2).a()) {
                                    if ((IZatManager.this.e & 16) == 0) {
                                        throw new IZatFeatureNotSupportedException("Outdoor trip mode is not supported.");
                                    } else if (izatFlpRequest.f() > 0) {
                                        notificationType = NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED;
                                    }
                                }
                            }
                            NotificationType notificationType2 = notificationType;
                            for (FlpRequestMapItem flpRequestMapItem : IZatManager.this.n.values()) {
                                if (flpRequestMapItem.a() == iFlpLocationCallback2 && flpRequestMapItem.b() == notificationType2 && flpRequestMapItem.d() == izatFlpRequest.d() && flpRequestMapItem.e() == izatFlpRequest.e()) {
                                    if (flpRequestMapItem.f() == izatFlpRequest.f()) {
                                        throw new IZatIllegalArgumentException("this session started already.");
                                    }
                                }
                            }
                            int i = IZatManager.i();
                            long currentTimeMillis = System.currentTimeMillis();
                            LocationCallbackWrapper locationCallbackWrapper = new LocationCallbackWrapper(iFlpLocationCallback2);
                            if ((IZatManager.this.e & 8) <= 0) {
                                this.f8605a.registerCallback(4, i, locationCallbackWrapper, currentTimeMillis);
                            } else if (izatFlpRequest2.f) {
                                this.f8605a.registerCallback(1, i, locationCallbackWrapper, currentTimeMillis);
                            } else {
                                this.f8605a.registerCallback(2, i, locationCallbackWrapper, currentTimeMillis);
                            }
                            int startFlpSessionWithPower = this.f8605a.startFlpSessionWithPower(i, notificationType2.getCode(), izatFlpRequest.d(), izatFlpRequest.e(), izatFlpRequest.f(), izatFlpRequest.g(), izatFlpRequest.h());
                            if (IZatManager.b) {
                                String k = IZatManager.f8601a;
                                Log.v(k, "startFlpSession() returning : " + startFlpSessionWithPower);
                            }
                            if (startFlpSessionWithPower == 0) {
                                try {
                                    IZatSessionHandlerImpl iZatSessionHandlerImpl = new IZatSessionHandlerImpl();
                                    Map b2 = IZatManager.this.n;
                                    LocationCallbackWrapper locationCallbackWrapper2 = locationCallbackWrapper;
                                    FlpRequestMapItem flpRequestMapItem2 = r2;
                                    Map map = b2;
                                    IZatSessionHandlerImpl iZatSessionHandlerImpl2 = iZatSessionHandlerImpl;
                                    FlpRequestMapItem flpRequestMapItem3 = new FlpRequestMapItem(IZatManager.this, iFlpLocationCallback, notificationType2, izatFlpRequest.d(), izatFlpRequest.e(), izatFlpRequest.f(), locationCallbackWrapper2, i, currentTimeMillis, izatFlpRequest.g(), izatFlpRequest.h());
                                    map.put(iZatSessionHandlerImpl2, flpRequestMapItem2);
                                    return iZatSessionHandlerImpl2;
                                } catch (Throwable th) {
                                    th = th;
                                    throw th;
                                }
                            } else {
                                LocationCallbackWrapper locationCallbackWrapper3 = locationCallbackWrapper;
                                if ((IZatManager.this.e & 8) <= 0) {
                                    this.f8605a.unregisterCallback(4, locationCallbackWrapper3);
                                } else if (izatFlpRequest2.f) {
                                    this.f8605a.unregisterCallback(1, locationCallbackWrapper3);
                                } else {
                                    this.f8605a.unregisterCallback(2, locationCallbackWrapper3);
                                }
                                IZatManager.l();
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed startFlpSession", e);
                }
            } else {
                throw new IZatIllegalArgumentException("Atleast one of the parameters in time, distance interval and trip distance must be valid");
            }
        }

        public void a(IZatFlpService.IZatFlpSessionHandle iZatFlpSessionHandle) throws IZatIllegalArgumentException {
            if (iZatFlpSessionHandle == null || !(iZatFlpSessionHandle instanceof IZatSessionHandlerImpl)) {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
            try {
                synchronized (IZatManager.H) {
                    FlpRequestMapItem flpRequestMapItem = (FlpRequestMapItem) IZatManager.this.n.get(iZatFlpSessionHandle);
                    if (flpRequestMapItem == null) {
                        Log.e(IZatManager.f8601a, "this request ID is unknown.");
                    } else if (this.f8605a.stopFlpSession(flpRequestMapItem.h()) != 0) {
                        Log.e(IZatManager.f8601a, "stopFlpSession() failed. ");
                    } else {
                        if ((IZatManager.this.e & 8) > 0) {
                            if (NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL != flpRequestMapItem.b()) {
                                if (NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED != flpRequestMapItem.b()) {
                                    if (NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX == flpRequestMapItem.b()) {
                                        this.f8605a.unregisterCallback(2, flpRequestMapItem.g());
                                    }
                                }
                            }
                            this.f8605a.unregisterCallback(1, flpRequestMapItem.g());
                        } else {
                            this.f8605a.unregisterCallback(4, flpRequestMapItem.g());
                        }
                        IZatManager.this.n.remove(iZatFlpSessionHandle);
                    }
                }
            } catch (RemoteException e) {
                throw new RuntimeException("Failed stopFlpSession", e);
            }
        }

        public void a(IZatFlpService.IFlpLocationCallback iFlpLocationCallback) throws IZatIllegalArgumentException {
            if (iFlpLocationCallback != null) {
                try {
                    synchronized (IZatManager.H) {
                        if (IZatManager.this.o.get(iFlpLocationCallback) == null) {
                            LocationCallbackWrapper locationCallbackWrapper = new LocationCallbackWrapper(iFlpLocationCallback);
                            IZatManager.this.o.put(iFlpLocationCallback, locationCallbackWrapper);
                            this.f8605a.registerCallback(4, -1, locationCallbackWrapper, System.currentTimeMillis());
                        } else {
                            Log.w(IZatManager.f8601a, "this passive callback is already registered.");
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed registerForPassiveLocations", e);
                }
            } else {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
        }

        public void b(IZatFlpService.IFlpLocationCallback iFlpLocationCallback) throws IZatIllegalArgumentException {
            if (iFlpLocationCallback != null) {
                try {
                    synchronized (IZatManager.H) {
                        LocationCallbackWrapper locationCallbackWrapper = (LocationCallbackWrapper) IZatManager.this.o.get(iFlpLocationCallback);
                        if (locationCallbackWrapper == null) {
                            Log.w(IZatManager.f8601a, "this passive callback is not registered.");
                        } else {
                            this.f8605a.unregisterCallback(4, locationCallbackWrapper);
                            IZatManager.this.o.remove(iFlpLocationCallback);
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed deregisterForPassiveLocations", e);
                }
            } else {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
        }

        private class IZatSessionHandlerImpl implements IZatFlpService.IZatFlpSessionHandle {
            private IZatSessionHandlerImpl() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a() {
                /*
                    r7 = this;
                    java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x0071 }
                    monitor-enter(r0)     // Catch:{ RemoteException -> 0x0071 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r1 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x006e }
                    com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x006e }
                    java.util.Map r1 = r1.n     // Catch:{ all -> 0x006e }
                    java.lang.Object r1 = r1.get(r7)     // Catch:{ all -> 0x006e }
                    com.qti.location.sdk.IZatManager$FlpRequestMapItem r1 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r1     // Catch:{ all -> 0x006e }
                    if (r1 != 0) goto L_0x0020
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x006e }
                    java.lang.String r2 = "no flp session undergoing"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x006e }
                    monitor-exit(r0)     // Catch:{ all -> 0x006e }
                    return
                L_0x0020:
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r2 = r1.g()     // Catch:{ all -> 0x006e }
                    if (r2 != 0) goto L_0x0031
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x006e }
                    java.lang.String r2 = "no available callback"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x006e }
                    monitor-exit(r0)     // Catch:{ all -> 0x006e }
                    return
                L_0x0031:
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r2 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x006e }
                    com.qti.flp.IFlpService r2 = r2.f8605a     // Catch:{ all -> 0x006e }
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r3 = r1.g()     // Catch:{ all -> 0x006e }
                    long r4 = r1.i()     // Catch:{ all -> 0x006e }
                    int r6 = r1.h()     // Catch:{ all -> 0x006e }
                    int r2 = r2.pullLocations(r3, r4, r6)     // Catch:{ all -> 0x006e }
                    if (r2 != 0) goto L_0x004e
                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x006e }
                    r1.a((long) r3)     // Catch:{ all -> 0x006e }
                L_0x004e:
                    boolean r1 = com.qti.location.sdk.IZatManager.b     // Catch:{ all -> 0x006e }
                    if (r1 == 0) goto L_0x006c
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x006e }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006e }
                    r3.<init>()     // Catch:{ all -> 0x006e }
                    java.lang.String r4 = "pullLocations() returning : "
                    r3.append(r4)     // Catch:{ all -> 0x006e }
                    r3.append(r2)     // Catch:{ all -> 0x006e }
                    java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x006e }
                    android.util.Log.v(r1, r2)     // Catch:{ all -> 0x006e }
                L_0x006c:
                    monitor-exit(r0)     // Catch:{ all -> 0x006e }
                    return
                L_0x006e:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x006e }
                    throw r1     // Catch:{ RemoteException -> 0x0071 }
                L_0x0071:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Failed pullLocations"
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl.a():void");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c5, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void b() {
                /*
                    r14 = this;
                    java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x00c9 }
                    monitor-enter(r0)     // Catch:{ RemoteException -> 0x00c9 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r1 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    java.util.Map r1 = r1.n     // Catch:{ all -> 0x00c6 }
                    java.lang.Object r1 = r1.get(r14)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$FlpRequestMapItem r1 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r1     // Catch:{ all -> 0x00c6 }
                    if (r1 != 0) goto L_0x0020
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = "no flp session undergoing"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00c6 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x0020:
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r2 = r1.g()     // Catch:{ all -> 0x00c6 }
                    if (r2 != 0) goto L_0x0031
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = "no available callback"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00c6 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x0031:
                    r2 = 0
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$NotificationType r4 = r1.b()     // Catch:{ all -> 0x00c6 }
                    if (r3 == r4) goto L_0x00a6
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r2 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r3 = r2.f8605a     // Catch:{ all -> 0x00c6 }
                    int r4 = r1.h()     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$NotificationType r2 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX     // Catch:{ all -> 0x00c6 }
                    int r5 = r2.getCode()     // Catch:{ all -> 0x00c6 }
                    long r6 = r1.d()     // Catch:{ all -> 0x00c6 }
                    int r8 = r1.e()     // Catch:{ all -> 0x00c6 }
                    r9 = 0
                    int r11 = r1.l()     // Catch:{ all -> 0x00c6 }
                    long r12 = r1.m()     // Catch:{ all -> 0x00c6 }
                    int r2 = r3.updateFlpSessionWithPower(r4, r5, r6, r8, r9, r11, r12)     // Catch:{ all -> 0x00c6 }
                    if (r2 != 0) goto L_0x009d
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX     // Catch:{ all -> 0x00c6 }
                    r1.a((com.qti.location.sdk.IZatManager.NotificationType) r3)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    java.util.Map r3 = r3.n     // Catch:{ all -> 0x00c6 }
                    r3.put(r14, r1)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    int r3 = r3.e     // Catch:{ all -> 0x00c6 }
                    r3 = r3 & 8
                    if (r3 <= 0) goto L_0x00a6
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r3 = r3.f8605a     // Catch:{ all -> 0x00c6 }
                    r4 = 1
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r5 = r1.g()     // Catch:{ all -> 0x00c6 }
                    r3.unregisterCallback(r4, r5)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r4 = r3.f8605a     // Catch:{ all -> 0x00c6 }
                    r5 = 2
                    int r6 = r1.h()     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r7 = r1.g()     // Catch:{ all -> 0x00c6 }
                    long r8 = r1.i()     // Catch:{ all -> 0x00c6 }
                    r4.registerCallback(r5, r6, r7, r8)     // Catch:{ all -> 0x00c6 }
                    goto L_0x00a6
                L_0x009d:
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r3 = "mService.updateFlpSession failed."
                    android.util.Log.v(r1, r3)     // Catch:{ all -> 0x00c6 }
                L_0x00a6:
                    boolean r1 = com.qti.location.sdk.IZatManager.b     // Catch:{ all -> 0x00c6 }
                    if (r1 == 0) goto L_0x00c4
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
                    r3.<init>()     // Catch:{ all -> 0x00c6 }
                    java.lang.String r4 = "setToForeground() returning : "
                    r3.append(r4)     // Catch:{ all -> 0x00c6 }
                    r3.append(r2)     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00c6 }
                    android.util.Log.v(r1, r2)     // Catch:{ all -> 0x00c6 }
                L_0x00c4:
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x00c6:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    throw r1     // Catch:{ RemoteException -> 0x00c9 }
                L_0x00c9:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Failed setToForeground"
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl.b():void");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c5, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void c() {
                /*
                    r14 = this;
                    java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x00c9 }
                    monitor-enter(r0)     // Catch:{ RemoteException -> 0x00c9 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r1 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    java.util.Map r1 = r1.n     // Catch:{ all -> 0x00c6 }
                    java.lang.Object r1 = r1.get(r14)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$FlpRequestMapItem r1 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r1     // Catch:{ all -> 0x00c6 }
                    if (r1 != 0) goto L_0x0020
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = "no flp session undergoing"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00c6 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x0020:
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r2 = r1.g()     // Catch:{ all -> 0x00c6 }
                    if (r2 != 0) goto L_0x0031
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = "no available callback"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00c6 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x0031:
                    r2 = 0
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$NotificationType r4 = r1.b()     // Catch:{ all -> 0x00c6 }
                    if (r3 == r4) goto L_0x00a6
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r2 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r3 = r2.f8605a     // Catch:{ all -> 0x00c6 }
                    int r4 = r1.h()     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$NotificationType r2 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL     // Catch:{ all -> 0x00c6 }
                    int r5 = r2.getCode()     // Catch:{ all -> 0x00c6 }
                    long r6 = r1.d()     // Catch:{ all -> 0x00c6 }
                    int r8 = r1.e()     // Catch:{ all -> 0x00c6 }
                    r9 = 0
                    int r11 = r1.l()     // Catch:{ all -> 0x00c6 }
                    long r12 = r1.m()     // Catch:{ all -> 0x00c6 }
                    int r2 = r3.updateFlpSessionWithPower(r4, r5, r6, r8, r9, r11, r12)     // Catch:{ all -> 0x00c6 }
                    if (r2 != 0) goto L_0x009d
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_BUFFER_IS_FULL     // Catch:{ all -> 0x00c6 }
                    r1.a((com.qti.location.sdk.IZatManager.NotificationType) r3)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    java.util.Map r3 = r3.n     // Catch:{ all -> 0x00c6 }
                    r3.put(r14, r1)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00c6 }
                    int r3 = r3.e     // Catch:{ all -> 0x00c6 }
                    r3 = r3 & 8
                    if (r3 <= 0) goto L_0x00a6
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r3 = r3.f8605a     // Catch:{ all -> 0x00c6 }
                    r4 = 2
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r5 = r1.g()     // Catch:{ all -> 0x00c6 }
                    r3.unregisterCallback(r4, r5)     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00c6 }
                    com.qti.flp.IFlpService r4 = r3.f8605a     // Catch:{ all -> 0x00c6 }
                    r5 = 1
                    int r6 = r1.h()     // Catch:{ all -> 0x00c6 }
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r7 = r1.g()     // Catch:{ all -> 0x00c6 }
                    long r8 = r1.i()     // Catch:{ all -> 0x00c6 }
                    r4.registerCallback(r5, r6, r7, r8)     // Catch:{ all -> 0x00c6 }
                    goto L_0x00a6
                L_0x009d:
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.String r3 = "mService.updateFlpSession failed."
                    android.util.Log.v(r1, r3)     // Catch:{ all -> 0x00c6 }
                L_0x00a6:
                    boolean r1 = com.qti.location.sdk.IZatManager.b     // Catch:{ all -> 0x00c6 }
                    if (r1 == 0) goto L_0x00c4
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00c6 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
                    r3.<init>()     // Catch:{ all -> 0x00c6 }
                    java.lang.String r4 = "setToBackground() returning : "
                    r3.append(r4)     // Catch:{ all -> 0x00c6 }
                    r3.append(r2)     // Catch:{ all -> 0x00c6 }
                    java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00c6 }
                    android.util.Log.v(r1, r2)     // Catch:{ all -> 0x00c6 }
                L_0x00c4:
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    return
                L_0x00c6:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                    throw r1     // Catch:{ RemoteException -> 0x00c9 }
                L_0x00c9:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Failed setToBackground"
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl.c():void");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f6, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void d() throws com.qti.location.sdk.IZatFeatureNotSupportedException {
                /*
                    r14 = this;
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r0 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this
                    com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this
                    int r0 = r0.e
                    r0 = r0 & 16
                    if (r0 == 0) goto L_0x0103
                    java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x00fa }
                    monitor-enter(r0)     // Catch:{ RemoteException -> 0x00fa }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r1 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00f7 }
                    java.util.Map r1 = r1.n     // Catch:{ all -> 0x00f7 }
                    java.lang.Object r1 = r1.get(r14)     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$FlpRequestMapItem r1 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r1     // Catch:{ all -> 0x00f7 }
                    if (r1 != 0) goto L_0x002c
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00f7 }
                    java.lang.String r2 = "no flp session undergoing"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00f7 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00f7 }
                    return
                L_0x002c:
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r2 = r1.g()     // Catch:{ all -> 0x00f7 }
                    if (r2 != 0) goto L_0x003d
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00f7 }
                    java.lang.String r2 = "no available callback"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00f7 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00f7 }
                    return
                L_0x003d:
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r2 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager r2 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00f7 }
                    int r2 = r2.e     // Catch:{ all -> 0x00f7 }
                    r2 = r2 & 16
                    if (r2 != 0) goto L_0x0054
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00f7 }
                    java.lang.String r2 = "Outdoor Trip mode not supported"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00f7 }
                    monitor-exit(r0)     // Catch:{ all -> 0x00f7 }
                    return
                L_0x0054:
                    r2 = 0
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$NotificationType r4 = r1.b()     // Catch:{ all -> 0x00f7 }
                    if (r3 == r4) goto L_0x00d7
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r2 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.flp.IFlpService r3 = r2.f8605a     // Catch:{ all -> 0x00f7 }
                    int r4 = r1.h()     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$NotificationType r2 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED     // Catch:{ all -> 0x00f7 }
                    int r5 = r2.getCode()     // Catch:{ all -> 0x00f7 }
                    long r6 = r1.d()     // Catch:{ all -> 0x00f7 }
                    int r8 = r1.e()     // Catch:{ all -> 0x00f7 }
                    long r9 = r1.f()     // Catch:{ all -> 0x00f7 }
                    int r11 = r1.l()     // Catch:{ all -> 0x00f7 }
                    long r12 = r1.m()     // Catch:{ all -> 0x00f7 }
                    int r2 = r3.updateFlpSessionWithPower(r4, r5, r6, r8, r9, r11, r12)     // Catch:{ all -> 0x00f7 }
                    if (r2 != 0) goto L_0x00ce
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED     // Catch:{ all -> 0x00f7 }
                    r1.a((com.qti.location.sdk.IZatManager.NotificationType) r3)     // Catch:{ all -> 0x00f7 }
                    r3 = 1
                    r1.a((boolean) r3)     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00f7 }
                    java.util.Map r3 = r3.n     // Catch:{ all -> 0x00f7 }
                    r3.put(r14, r1)     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00f7 }
                    int r3 = r3.e     // Catch:{ all -> 0x00f7 }
                    r3 = r3 & 8
                    if (r3 <= 0) goto L_0x00d7
                    com.qti.location.sdk.IZatManager$NotificationType r3 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$NotificationType r4 = r1.c()     // Catch:{ all -> 0x00f7 }
                    if (r3 != r4) goto L_0x00d7
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.flp.IFlpService r3 = r3.f8605a     // Catch:{ all -> 0x00f7 }
                    r4 = 2
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r5 = r1.g()     // Catch:{ all -> 0x00f7 }
                    r3.unregisterCallback(r4, r5)     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x00f7 }
                    com.qti.flp.IFlpService r4 = r3.f8605a     // Catch:{ all -> 0x00f7 }
                    r5 = 1
                    int r6 = r1.h()     // Catch:{ all -> 0x00f7 }
                    com.qti.location.sdk.IZatManager$LocationCallbackWrapper r7 = r1.g()     // Catch:{ all -> 0x00f7 }
                    long r8 = r1.i()     // Catch:{ all -> 0x00f7 }
                    r4.registerCallback(r5, r6, r7, r8)     // Catch:{ all -> 0x00f7 }
                    goto L_0x00d7
                L_0x00ce:
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00f7 }
                    java.lang.String r3 = "mService.updateFlpSession failed."
                    android.util.Log.v(r1, r3)     // Catch:{ all -> 0x00f7 }
                L_0x00d7:
                    boolean r1 = com.qti.location.sdk.IZatManager.b     // Catch:{ all -> 0x00f7 }
                    if (r1 == 0) goto L_0x00f5
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00f7 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f7 }
                    r3.<init>()     // Catch:{ all -> 0x00f7 }
                    java.lang.String r4 = "setToTripMode() returning : "
                    r3.append(r4)     // Catch:{ all -> 0x00f7 }
                    r3.append(r2)     // Catch:{ all -> 0x00f7 }
                    java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00f7 }
                    android.util.Log.v(r1, r2)     // Catch:{ all -> 0x00f7 }
                L_0x00f5:
                    monitor-exit(r0)     // Catch:{ all -> 0x00f7 }
                    return
                L_0x00f7:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00f7 }
                    throw r1     // Catch:{ RemoteException -> 0x00fa }
                L_0x00fa:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Failed setToTripMode"
                    r1.<init>(r2, r0)
                    throw r1
                L_0x0103:
                    com.qti.location.sdk.IZatFeatureNotSupportedException r0 = new com.qti.location.sdk.IZatFeatureNotSupportedException
                    java.lang.String r1 = "Outdoor trip mode is not supported."
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl.d():void");
            }

            public void a(IZatFlpService.IFlpStatusCallback iFlpStatusCallback) throws IZatIllegalArgumentException, IZatFeatureNotSupportedException {
                if ((IZatManager.this.e & 16) == 0) {
                    throw new IZatFeatureNotSupportedException("Session status not supported.");
                } else if (iFlpStatusCallback != null) {
                    try {
                        synchronized (IZatManager.H) {
                            FlpRequestMapItem flpRequestMapItem = (FlpRequestMapItem) IZatManager.this.n.get(this);
                            if (flpRequestMapItem == null) {
                                Log.w(IZatManager.f8601a, "no flp session undergoing");
                                return;
                            }
                            FlpStatusCallbackWrapper flpStatusCallbackWrapper = new FlpStatusCallbackWrapper(iFlpStatusCallback, IZatFlpServiceImpl.this.f8605a);
                            flpRequestMapItem.a(flpStatusCallbackWrapper);
                            IZatFlpServiceImpl.this.f8605a.registerForSessionStatus(flpRequestMapItem.h(), flpStatusCallbackWrapper);
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException("Failed registerForSessionStatus", e);
                    }
                } else {
                    throw new IZatIllegalArgumentException("invalid input parameter");
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void e() throws com.qti.location.sdk.IZatIllegalArgumentException, com.qti.location.sdk.IZatFeatureNotSupportedException {
                /*
                    r4 = this;
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r0 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this
                    com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this
                    int r0 = r0.e
                    r0 = r0 & 16
                    if (r0 == 0) goto L_0x0055
                    java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x004c }
                    monitor-enter(r0)     // Catch:{ RemoteException -> 0x004c }
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r1 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x0049 }
                    com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0049 }
                    java.util.Map r1 = r1.n     // Catch:{ all -> 0x0049 }
                    java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0049 }
                    com.qti.location.sdk.IZatManager$FlpRequestMapItem r1 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r1     // Catch:{ all -> 0x0049 }
                    if (r1 != 0) goto L_0x002c
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x0049 }
                    java.lang.String r2 = "no flp session undergoing"
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x0049 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0049 }
                    return
                L_0x002c:
                    com.qti.location.sdk.IZatManager$FlpStatusCallbackWrapper r2 = r1.k()     // Catch:{ all -> 0x0049 }
                    if (r2 != 0) goto L_0x003c
                    java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x0049 }
                    java.lang.String r2 = "no status callback wrapper is registered."
                    android.util.Log.w(r1, r2)     // Catch:{ all -> 0x0049 }
                    goto L_0x0047
                L_0x003c:
                    com.qti.location.sdk.IZatManager$IZatFlpServiceImpl r3 = com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.this     // Catch:{ all -> 0x0049 }
                    com.qti.flp.IFlpService r3 = r3.f8605a     // Catch:{ all -> 0x0049 }
                    r3.unregisterForSessionStatus(r2)     // Catch:{ all -> 0x0049 }
                    r2 = 0
                    r1.a((com.qti.location.sdk.IZatManager.FlpStatusCallbackWrapper) r2)     // Catch:{ all -> 0x0049 }
                L_0x0047:
                    monitor-exit(r0)     // Catch:{ all -> 0x0049 }
                    return
                L_0x0049:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0049 }
                    throw r1     // Catch:{ RemoteException -> 0x004c }
                L_0x004c:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Failed unregisterForSessionStatus"
                    r1.<init>(r2, r0)
                    throw r1
                L_0x0055:
                    com.qti.location.sdk.IZatFeatureNotSupportedException r0 = new com.qti.location.sdk.IZatFeatureNotSupportedException
                    java.lang.String r1 = "Session status not supported."
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl.e():void");
            }
        }
    }

    private class IZatTestServiceImpl implements IZatTestService {

        /* renamed from: a  reason: collision with root package name */
        ITestService f8609a;

        public IZatTestServiceImpl(ITestService iTestService) {
            this.f8609a = iTestService;
        }

        public void a(long j) throws IZatIllegalArgumentException {
            if (0 != j) {
                try {
                    this.f8609a.deleteAidingData(j);
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed deregisterForPassiveLocations", e);
                }
            } else {
                throw new IZatIllegalArgumentException("invalid input parameter. flags must be filled");
            }
        }

        public void a(boolean z) {
            try {
                this.f8609a.updateXtraThrottle(z);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed updateXtraThrottle", e);
            }
        }

        public void a(IZatTestService.IFlpMaxPowerAllocatedCallback iFlpMaxPowerAllocatedCallback) throws IZatIllegalArgumentException {
            if (iFlpMaxPowerAllocatedCallback != null) {
                try {
                    synchronized (IZatManager.I) {
                        if (IZatManager.this.p.get(iFlpMaxPowerAllocatedCallback) == null) {
                            MaxPowerAllocatedCallbackWrapper maxPowerAllocatedCallbackWrapper = new MaxPowerAllocatedCallbackWrapper(iFlpMaxPowerAllocatedCallback);
                            IZatManager.this.p.put(iFlpMaxPowerAllocatedCallback, maxPowerAllocatedCallbackWrapper);
                            this.f8609a.registerMaxPowerChangeCallback(maxPowerAllocatedCallbackWrapper);
                        } else {
                            Log.w(IZatManager.f8601a, "this max power callback is already registered.");
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed registerForMaxPowerAllocatedChange", e);
                }
            } else {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
        }

        public void b(IZatTestService.IFlpMaxPowerAllocatedCallback iFlpMaxPowerAllocatedCallback) throws IZatIllegalArgumentException {
            if (iFlpMaxPowerAllocatedCallback != null) {
                try {
                    synchronized (IZatManager.I) {
                        MaxPowerAllocatedCallbackWrapper maxPowerAllocatedCallbackWrapper = (MaxPowerAllocatedCallbackWrapper) IZatManager.this.p.get(iFlpMaxPowerAllocatedCallback);
                        if (maxPowerAllocatedCallbackWrapper == null) {
                            Log.w(IZatManager.f8601a, "this passive callback is not registered.");
                        } else {
                            this.f8609a.unregisterMaxPowerChangeCallback(maxPowerAllocatedCallbackWrapper);
                            IZatManager.this.p.remove(iFlpMaxPowerAllocatedCallback);
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed deregisterForMaxPowerAllocatedChange", e);
                }
            } else {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
        }
    }

    private class IZatWiFiDBReceiverImpl extends IZatWiFiDBReceiver {

        /* renamed from: a  reason: collision with root package name */
        IWiFiDBReceiver f8613a;

        public IZatWiFiDBReceiverImpl(IWiFiDBReceiver iWiFiDBReceiver, Object obj) throws IZatIllegalArgumentException {
            super(obj);
            if (iWiFiDBReceiver == null || obj == null) {
                throw new IZatIllegalArgumentException("IZatWiFiDBReceiverImpl: null receiver / listener");
            }
            this.f8613a = iWiFiDBReceiver;
        }

        public void a(int i) {
            try {
                IZatDataValidation.a(i, IZatValidationResults.IZatDataTypes.WIFI_EXPIRE_DAYS);
                this.f8613a.requestAPList(i);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to request AP LIst", e);
            }
        }

        public void a() {
            try {
                this.f8613a.requestScanList();
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to request AP LIst", e);
            }
        }

        public void a(List<IZatWiFiDBReceiver.IZatAPLocationData> list, List<IZatWiFiDBReceiver.IZatAPSpecialInfo> list2, int i) {
            if (500 < list.size()) {
                throw new RuntimeException("Exceeded maximum number of APs in izatLocationDataList: 500");
            } else if (500 >= list2.size()) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (IZatWiFiDBReceiver.IZatAPLocationData next : list) {
                    if (next != null) {
                        IZatDataValidation.a(next);
                        APLocationData aPLocationData = new APLocationData();
                        aPLocationData.f8637a = next.a();
                        aPLocationData.b = next.b();
                        aPLocationData.c = next.c();
                        aPLocationData.k = 0;
                        try {
                            aPLocationData.d = next.d();
                            aPLocationData.k |= 1;
                        } catch (IZatStaleDataException unused) {
                            Log.e(IZatManager.f8601a, "MAR exception ");
                        }
                        try {
                            aPLocationData.e = next.e();
                            aPLocationData.k |= 2;
                        } catch (IZatStaleDataException unused2) {
                            Log.e(IZatManager.f8601a, "HE exception ");
                        }
                        try {
                            aPLocationData.f = next.f().ordinal();
                            aPLocationData.k |= 4;
                        } catch (IZatStaleDataException unused3) {
                            Log.e(IZatManager.f8601a, "REL exception ");
                        }
                        arrayList.add(aPLocationData);
                    }
                }
                for (IZatWiFiDBReceiver.IZatAPSpecialInfo next2 : list2) {
                    if (next2 != null) {
                        IZatDataValidation.a(next2);
                        APSpecialInfo aPSpecialInfo = new APSpecialInfo();
                        aPSpecialInfo.f8638a = next2.f8624a;
                        aPSpecialInfo.b = next2.b.ordinal();
                        arrayList2.add(aPSpecialInfo);
                    }
                }
                try {
                    this.f8613a.pushWiFiDB(arrayList, arrayList2, i);
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed to push WiFi DB", e);
                }
            } else {
                throw new RuntimeException("Exceeded maximum number of APs in izatSpecialInfoList: 500");
            }
        }

        public void a(List<IZatWiFiDBReceiver.IZatAPLocationData> list, List<IZatWiFiDBReceiver.IZatAPSpecialInfo> list2) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (IZatWiFiDBReceiver.IZatAPLocationData next : list) {
                if (next != null) {
                    IZatDataValidation.a(next);
                    APLocationData aPLocationData = new APLocationData();
                    aPLocationData.f8637a = next.a();
                    aPLocationData.b = next.b();
                    aPLocationData.c = next.c();
                    aPLocationData.k = 0;
                    try {
                        aPLocationData.d = next.d();
                        aPLocationData.k |= 1;
                    } catch (IZatStaleDataException unused) {
                        Log.e(IZatManager.f8601a, "MAR exception ");
                    }
                    try {
                        aPLocationData.e = next.e();
                        aPLocationData.k |= 2;
                    } catch (IZatStaleDataException unused2) {
                        Log.e(IZatManager.f8601a, "HE exception ");
                    }
                    try {
                        aPLocationData.f = next.f().ordinal();
                        aPLocationData.k |= 4;
                    } catch (IZatStaleDataException unused3) {
                        Log.e(IZatManager.f8601a, "REL exception ");
                    }
                    arrayList.add(aPLocationData);
                }
            }
            for (IZatWiFiDBReceiver.IZatAPSpecialInfo next2 : list2) {
                if (next2 != null) {
                    IZatDataValidation.a(next2);
                    APSpecialInfo aPSpecialInfo = new APSpecialInfo();
                    aPSpecialInfo.f8638a = next2.f8624a;
                    aPSpecialInfo.b = next2.b.ordinal();
                    arrayList2.add(aPSpecialInfo);
                }
            }
            try {
                this.f8613a.pushLookupResult(arrayList, arrayList2);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to push WiFi DB", e);
            }
        }
    }

    private class IZatWiFiDBProviderImpl extends IZatWiFiDBProvider {

        /* renamed from: a  reason: collision with root package name */
        IWiFiDBProvider f8612a;

        public IZatWiFiDBProviderImpl(IWiFiDBProvider iWiFiDBProvider, IZatWiFiDBProvider.IZatWiFiDBProviderResponseListener iZatWiFiDBProviderResponseListener) throws IZatIllegalArgumentException {
            super(iZatWiFiDBProviderResponseListener);
            if (iWiFiDBProvider == null || iZatWiFiDBProviderResponseListener == null) {
                throw new IZatIllegalArgumentException("IZatWiFiDBProviderImpl: null receiver / listener");
            }
            this.f8612a = iWiFiDBProvider;
        }

        public void a() {
            try {
                this.f8612a.requestAPObsLocData();
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to request AP Observation data", e);
            }
        }
    }

    private class IZatWWANDBReceiverImpl extends IZatWWANDBReceiver {

        /* renamed from: a  reason: collision with root package name */
        IWWANDBReceiver f8611a;

        public IZatWWANDBReceiverImpl(IWWANDBReceiver iWWANDBReceiver, Object obj) throws IZatIllegalArgumentException {
            super(obj);
            if (iWWANDBReceiver == null || obj == null) {
                throw new IZatIllegalArgumentException("IZatWWANDBReceiverImpl: null receiver / listener");
            }
            this.f8611a = iWWANDBReceiver;
        }

        public void a(int i) {
            try {
                IZatDataValidation.a(i, IZatValidationResults.IZatDataTypes.WIFI_EXPIRE_DAYS);
                this.f8611a.requestBSList(i);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to request BS LIst", e);
            }
        }

        public void a(List<IZatWWANDBReceiver.IZatBSLocationData> list, List<IZatWWANDBReceiver.IZatBSSpecialInfo> list2, int i) {
            if (20 < list.size()) {
                throw new RuntimeException("Exceeded maximum number of BSs in location_data: 20");
            } else if (20 >= list2.size()) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (IZatWWANDBReceiver.IZatBSLocationData next : list) {
                    if (next != null) {
                        if (IZatManager.b) {
                            IZatManager.this.a(next);
                        }
                        IZatDataValidation.a(next);
                        BSLocationData bSLocationData = new BSLocationData();
                        bSLocationData.f8641a = next.m().f().ordinal();
                        bSLocationData.b = next.m().b();
                        bSLocationData.c = next.m().c();
                        bSLocationData.d = next.m().d();
                        bSLocationData.e = next.m().e();
                        if (next.l() != null) {
                            bSLocationData.f = (float) next.l().getLatitude();
                            bSLocationData.g = (float) next.l().getLongitude();
                            bSLocationData.p = 0;
                            bSLocationData.q = next.a();
                            bSLocationData.p |= 1;
                            bSLocationData.t = (float) next.l().getAltitude();
                            bSLocationData.p |= 8;
                            bSLocationData.u = next.l().getVerticalAccuracyMeters();
                            bSLocationData.p |= 16;
                            bSLocationData.r = Math.round(next.g());
                            bSLocationData.p |= 2;
                            bSLocationData.v = next.f();
                            bSLocationData.p |= 32;
                        }
                        try {
                            bSLocationData.s = next.n().ordinal();
                            bSLocationData.p |= 4;
                        } catch (IZatStaleDataException unused) {
                            Log.e(IZatManager.f8601a, "HR exception ");
                        }
                        try {
                            bSLocationData.w = next.o().ordinal();
                            bSLocationData.p |= 64;
                        } catch (IZatStaleDataException unused2) {
                            Log.e(IZatManager.f8601a, "ALR exception ");
                        }
                        arrayList.add(bSLocationData);
                    }
                }
                for (IZatWWANDBReceiver.IZatBSSpecialInfo next2 : list2) {
                    if (next2 != null) {
                        if (IZatManager.b) {
                            IZatManager.this.a(next2);
                        }
                        IZatDataValidation.a(next2);
                        BSSpecialInfo bSSpecialInfo = new BSSpecialInfo();
                        bSSpecialInfo.f8642a = next2.a().f().ordinal();
                        bSSpecialInfo.b = next2.a().b();
                        bSSpecialInfo.c = next2.a().c();
                        bSSpecialInfo.d = next2.a().d();
                        bSSpecialInfo.e = next2.a().e();
                        bSSpecialInfo.f = next2.b().ordinal();
                        arrayList2.add(bSSpecialInfo);
                    }
                }
                try {
                    this.f8611a.pushWWANDB(arrayList, arrayList2, i);
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed to push WWAN DB", e);
                }
            } else {
                throw new RuntimeException("Exceeded maximum number of BSs in special_info: 20");
            }
        }
    }

    private class IZatWWANDBProviderImpl extends IZatWWANDBProvider {

        /* renamed from: a  reason: collision with root package name */
        IWWANDBProvider f8610a;

        public IZatWWANDBProviderImpl(IWWANDBProvider iWWANDBProvider, IZatWWANDBProvider.IZatWWANDBProviderResponseListener iZatWWANDBProviderResponseListener) throws IZatIllegalArgumentException {
            super(iZatWWANDBProviderResponseListener);
            if (iWWANDBProvider == null || iZatWWANDBProviderResponseListener == null) {
                throw new IZatIllegalArgumentException("IZatWWANDBProviderImpl: null receiver / listener");
            }
            this.f8610a = iWWANDBProvider;
        }

        public void a() {
            try {
                this.f8610a.requestBSObsLocData();
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to request BS Observation data", e);
            }
        }
    }

    private class IZatGeofenceServiceImpl implements IZatGeofenceService {
        IGeofenceService t;

        public IZatGeofenceServiceImpl(IGeofenceService iGeofenceService) {
            this.t = iGeofenceService;
        }

        public void a(IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback) throws IZatIllegalArgumentException {
            if (iZatGeofenceCallback != null) {
                synchronized (IZatManager.L) {
                    IZatManager.this.q.put(this, iZatGeofenceCallback);
                }
                return;
            }
            throw new IZatIllegalArgumentException("invalid input parameter");
        }

        public void b(IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback) throws IZatIllegalArgumentException {
            if (iZatGeofenceCallback != null) {
                synchronized (IZatManager.L) {
                    IZatManager.this.q.remove(this);
                }
                return;
            }
            throw new IZatIllegalArgumentException("invalid input parameter");
        }

        public void a(PendingIntent pendingIntent) throws IZatIllegalArgumentException {
            if (pendingIntent != null) {
                synchronized (IZatManager.J) {
                    try {
                        this.t.registerPendingIntent(pendingIntent);
                    } catch (RemoteException unused) {
                        throw new RuntimeException("Failed registerPendingIntent");
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            }
            throw new IZatIllegalArgumentException("invalid input parameter");
        }

        public void b(PendingIntent pendingIntent) throws IZatIllegalArgumentException {
            if (pendingIntent != null) {
                synchronized (IZatManager.J) {
                    try {
                        this.t.unregisterPendingIntent(pendingIntent);
                    } catch (RemoteException unused) {
                        throw new RuntimeException("Failed unregisterPendingIntent");
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            }
            throw new IZatIllegalArgumentException("invalid input parameter");
        }

        public Map<IZatGeofenceService.IZatGeofenceHandle, IZatGeofenceService.IzatGeofence> a() {
            IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
            if (iZatGeofenceCallback == null) {
                Log.e(IZatManager.f8601a, "callback is not registered");
                return null;
            }
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            try {
                synchronized (IZatManager.J) {
                    this.t.recoverGeofences(arrayList);
                }
                synchronized (IZatManager.K) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        GeofenceData geofenceData = (GeofenceData) it.next();
                        IZatGeofenceService.IzatGeofence a2 = a(geofenceData);
                        boolean z = false;
                        Iterator it2 = IZatManager.this.r.entrySet().iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Map.Entry entry = (Map.Entry) it2.next();
                            if (((GeofenceMapItem) entry.getValue()).b() == geofenceData.k()) {
                                z = true;
                                hashMap.put((IZatGeofenceService.IZatGeofenceHandle) entry.getKey(), a2);
                                break;
                            }
                        }
                        if (!z) {
                            IZatGeofenceHandleImpl iZatGeofenceHandleImpl = new IZatGeofenceHandleImpl();
                            Object i = geofenceData.i();
                            if (i == null) {
                                i = geofenceData.j();
                            }
                            IZatManager.this.r.put(iZatGeofenceHandleImpl, new GeofenceMapItem(i, geofenceData.k(), iZatGeofenceCallback));
                            hashMap.put(iZatGeofenceHandleImpl, a2);
                        }
                    }
                }
                return hashMap;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to recover geofences", e);
            }
        }

        public IZatGeofenceService.IZatGeofenceHandle a(Object obj, IZatGeofenceService.IzatGeofence izatGeofence) throws IZatIllegalArgumentException {
            int i;
            int i2;
            int i3;
            Object obj2 = obj;
            if (izatGeofence == null) {
                throw new IZatIllegalArgumentException("invalid null geofence");
            } else if (izatGeofence.c() < -90.0d || izatGeofence.c() > 90.0d) {
                throw new IZatIllegalArgumentException("invalid geofence latitude. Expected in range -90..90.");
            } else if (izatGeofence.d() < -180.0d || izatGeofence.d() > 180.0d) {
                throw new IZatIllegalArgumentException("invalid geofence longitude. Expected in range -180..180.");
            } else {
                IZatDataValidation.a(izatGeofence);
                IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
                if (iZatGeofenceCallback == null) {
                    Log.e(IZatManager.f8601a, "callback is not registered.");
                    return null;
                }
                try {
                    synchronized (IZatManager.J) {
                        double c = izatGeofence.c();
                        double d = izatGeofence.d();
                        double e = izatGeofence.e();
                        int value = izatGeofence.f().getValue();
                        int g = izatGeofence.g();
                        int value2 = izatGeofence.h().getValue();
                        if (izatGeofence.i() != null) {
                            int a2 = izatGeofence.i().a();
                            i = izatGeofence.i().b();
                            i2 = a2;
                        } else {
                            i2 = 0;
                            i = 0;
                        }
                        if (obj2 == null || (!(obj2 instanceof String) && !(obj2 instanceof Bundle))) {
                            i3 = this.t.addGeofence(c, d, e, value, g, value2, i2, i);
                        } else {
                            String obj3 = obj2 instanceof String ? obj.toString() : null;
                            Bundle bundle = obj2 instanceof Bundle ? (Bundle) obj2 : null;
                            GeofenceData geofenceData = r6;
                            IGeofenceService iGeofenceService = this.t;
                            GeofenceData geofenceData2 = new GeofenceData(g, c, d, e, value, value2, i, i2, obj3, bundle, -1);
                            i3 = iGeofenceService.addGeofenceObj(geofenceData);
                        }
                    }
                    IZatGeofenceHandleImpl iZatGeofenceHandleImpl = new IZatGeofenceHandleImpl();
                    synchronized (IZatManager.K) {
                        IZatManager.this.r.put(iZatGeofenceHandleImpl, new GeofenceMapItem(obj2, i3, iZatGeofenceCallback));
                    }
                    return iZatGeofenceHandleImpl;
                } catch (RemoteException e2) {
                    throw new RuntimeException("Failed addGeofence", e2);
                }
            }
        }

        public void a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle) throws IZatIllegalArgumentException {
            if (iZatGeofenceHandle == null || !(iZatGeofenceHandle instanceof IZatGeofenceHandleImpl)) {
                throw new IZatIllegalArgumentException("invalid input parameter");
            }
            try {
                synchronized (IZatManager.J) {
                    GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(iZatGeofenceHandle);
                    if (geofenceMapItem == null) {
                        Log.e(IZatManager.f8601a, "this request ID is unknown.");
                        IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
                        if (iZatGeofenceCallback != null) {
                            iZatGeofenceCallback.a(iZatGeofenceHandle, 2, -102);
                            return;
                        }
                        throw new IZatIllegalArgumentException("Invalid Geofence handle");
                    }
                    this.t.removeGeofence(geofenceMapItem.b());
                    synchronized (IZatManager.K) {
                        IZatManager.this.r.remove(iZatGeofenceHandle);
                    }
                }
            } catch (RemoteException e) {
                throw new RuntimeException("Failed removeGeofence", e);
            }
        }

        /* access modifiers changed from: private */
        public GeofenceData a(int i) throws IZatIllegalArgumentException {
            GeofenceData geofenceData;
            ArrayList arrayList = new ArrayList();
            try {
                synchronized (IZatManager.J) {
                    this.t.recoverGeofences(arrayList);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        geofenceData = (GeofenceData) it.next();
                        if (i == geofenceData.k()) {
                        }
                    }
                    throw new IZatIllegalArgumentException("Invalid geofence id");
                }
                return geofenceData;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to recover geofence", e);
            }
        }

        private IZatGeofenceService.IzatGeofence a(GeofenceData geofenceData) {
            IZatGeofenceService.IzatGeofence izatGeofence = new IZatGeofenceService.IzatGeofence(geofenceData.b(), geofenceData.c(), geofenceData.d());
            izatGeofence.a(geofenceData.a());
            izatGeofence.a(IZatGeofenceService.IzatGeofenceTransitionTypes.values()[geofenceData.e().getValue()]);
            izatGeofence.a(IZatGeofenceService.IzatGeofenceConfidence.values()[geofenceData.f().getValue() - 1]);
            izatGeofence.a(new IZatGeofenceService.IzatDwellNotify(geofenceData.h(), geofenceData.g().getValue()));
            return izatGeofence;
        }

        private class IZatGeofenceHandleImpl implements IZatGeofenceService.IZatGeofenceHandle {
            private IZatGeofenceHandleImpl() {
            }

            public Object a() {
                GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(this);
                if (geofenceMapItem != null) {
                    return geofenceMapItem.a();
                }
                return null;
            }

            @Deprecated
            public void a(IZatGeofenceService.IzatGeofenceTransitionTypes izatGeofenceTransitionTypes, int i) throws IZatIllegalArgumentException {
                if (izatGeofenceTransitionTypes == null || i <= 0) {
                    throw new IZatIllegalArgumentException("invalid input parameter");
                }
                IZatGeofenceService.IzatGeofence izatGeofence = new IZatGeofenceService.IzatGeofence((Double) null, (Double) null, (Double) null);
                izatGeofence.a(izatGeofenceTransitionTypes);
                izatGeofence.a(i);
                a(izatGeofence);
            }

            public void a(IZatGeofenceService.IzatGeofence izatGeofence) throws IZatIllegalArgumentException {
                try {
                    synchronized (IZatManager.J) {
                        GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(this);
                        if (geofenceMapItem == null) {
                            Log.e(IZatManager.f8601a, "this request ID is unknown.");
                            IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
                            if (iZatGeofenceCallback != null) {
                                iZatGeofenceCallback.a((IZatGeofenceService.IZatGeofenceHandle) this, 5, -102);
                                return;
                            }
                            throw new IZatIllegalArgumentException("Invalid Geofence handle");
                        }
                        IZatGeofenceServiceImpl.this.t.updateGeofenceData(geofenceMapItem.b(), izatGeofence.c(), izatGeofence.d(), izatGeofence.e(), izatGeofence.f().getValue(), izatGeofence.g(), izatGeofence.h().getValue(), izatGeofence.i().a(), izatGeofence.i().b(), izatGeofence.b());
                        izatGeofence.a();
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed update", e);
                }
            }

            public void b() throws IZatIllegalArgumentException {
                try {
                    synchronized (IZatManager.J) {
                        GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(this);
                        if (geofenceMapItem == null) {
                            Log.e(IZatManager.f8601a, "this request ID is unknown.");
                            IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
                            if (iZatGeofenceCallback != null) {
                                iZatGeofenceCallback.a((IZatGeofenceService.IZatGeofenceHandle) this, 3, -102);
                                return;
                            }
                            throw new IZatIllegalArgumentException("Invalid Geofence handle");
                        }
                        IZatGeofenceServiceImpl.this.t.pauseGeofence(geofenceMapItem.b());
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed pause", e);
                }
            }

            public void c() throws IZatIllegalArgumentException {
                try {
                    synchronized (IZatManager.J) {
                        GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(this);
                        if (geofenceMapItem == null) {
                            Log.e(IZatManager.f8601a, "this request ID is unknown.");
                            IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback = (IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(this);
                            if (iZatGeofenceCallback != null) {
                                iZatGeofenceCallback.a((IZatGeofenceService.IZatGeofenceHandle) this, 4, -102);
                                return;
                            }
                            throw new IZatIllegalArgumentException("Invalid Geofence handle");
                        }
                        IZatGeofenceServiceImpl.this.t.resumeGeofence(geofenceMapItem.b());
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException("Failed resume", e);
                }
            }

            public boolean d() throws IZatIllegalArgumentException {
                boolean l;
                synchronized (IZatManager.J) {
                    GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(this);
                    if (geofenceMapItem != null) {
                        l = IZatGeofenceServiceImpl.this.a(geofenceMapItem.b).l();
                    } else {
                        Log.e(IZatManager.f8601a, "this request ID is unknown.");
                        throw new IZatIllegalArgumentException("Invalid Geofence handle");
                    }
                }
                return l;
            }
        }
    }

    public class IZatDebugReportingServiceImpl implements IZatDebugReportingService {

        /* renamed from: a  reason: collision with root package name */
        IDebugReportService f8604a;

        public IZatDebugReportingServiceImpl(IDebugReportService iDebugReportService) {
            this.f8604a = iDebugReportService;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:11|12|13|14|15) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0028 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.qti.location.sdk.IZatDebugReportingService.IZatDebugReportCallback r3) throws com.qti.location.sdk.IZatIllegalArgumentException {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0038
                java.lang.Object r0 = com.qti.location.sdk.IZatManager.N
                monitor-enter(r0)
                com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0035 }
                java.util.Map r1 = r1.s     // Catch:{ all -> 0x0035 }
                r1.put(r2, r3)     // Catch:{ all -> 0x0035 }
                monitor-exit(r0)     // Catch:{ all -> 0x0035 }
                java.lang.Object r3 = com.qti.location.sdk.IZatManager.M
                monitor-enter(r3)
                com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0032 }
                java.util.Map r0 = r0.s     // Catch:{ all -> 0x0032 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0032 }
                if (r0 != 0) goto L_0x0030
                com.qti.debugreport.IDebugReportService r0 = r2.f8604a     // Catch:{ RemoteException -> 0x0028 }
                r0.startReporting()     // Catch:{ RemoteException -> 0x0028 }
                goto L_0x0030
            L_0x0028:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0032 }
                java.lang.String r1 = "Failed to register for debug reports"
                r0.<init>(r1)     // Catch:{ all -> 0x0032 }
                throw r0     // Catch:{ all -> 0x0032 }
            L_0x0030:
                monitor-exit(r3)     // Catch:{ all -> 0x0032 }
                return
            L_0x0032:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0032 }
                throw r0
            L_0x0035:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0035 }
                throw r3
            L_0x0038:
                com.qti.location.sdk.IZatIllegalArgumentException r3 = new com.qti.location.sdk.IZatIllegalArgumentException
                java.lang.String r0 = "invalid input parameter"
                r3.<init>(r0)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatDebugReportingServiceImpl.a(com.qti.location.sdk.IZatDebugReportingService$IZatDebugReportCallback):void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|17) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0036 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b(com.qti.location.sdk.IZatDebugReportingService.IZatDebugReportCallback r3) throws com.qti.location.sdk.IZatIllegalArgumentException {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0046
                java.lang.Object r3 = com.qti.location.sdk.IZatManager.N
                monitor-enter(r3)
                com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0043 }
                java.util.Map r0 = r0.s     // Catch:{ all -> 0x0043 }
                java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0043 }
                com.qti.location.sdk.IZatDebugReportingService$IZatDebugReportCallback r0 = (com.qti.location.sdk.IZatDebugReportingService.IZatDebugReportCallback) r0     // Catch:{ all -> 0x0043 }
                if (r0 == 0) goto L_0x001e
                com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0043 }
                java.util.Map r0 = r0.s     // Catch:{ all -> 0x0043 }
                r0.remove(r2)     // Catch:{ all -> 0x0043 }
            L_0x001e:
                monitor-exit(r3)     // Catch:{ all -> 0x0043 }
                java.lang.Object r0 = com.qti.location.sdk.IZatManager.M
                monitor-enter(r0)
                com.qti.location.sdk.IZatManager r3 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0040 }
                java.util.Map r3 = r3.s     // Catch:{ all -> 0x0040 }
                boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0040 }
                if (r3 == 0) goto L_0x003e
                com.qti.debugreport.IDebugReportService r3 = r2.f8604a     // Catch:{ RemoteException -> 0x0036 }
                r3.stopReporting()     // Catch:{ RemoteException -> 0x0036 }
                goto L_0x003e
            L_0x0036:
                java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x0040 }
                java.lang.String r1 = "Failed to deregister for debug reports"
                r3.<init>(r1)     // Catch:{ all -> 0x0040 }
                throw r3     // Catch:{ all -> 0x0040 }
            L_0x003e:
                monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                return
            L_0x0040:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                throw r3
            L_0x0043:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0043 }
                throw r0
            L_0x0046:
                com.qti.location.sdk.IZatIllegalArgumentException r3 = new com.qti.location.sdk.IZatIllegalArgumentException
                java.lang.String r0 = "invalid input parameter"
                r3.<init>(r0)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatDebugReportingServiceImpl.b(com.qti.location.sdk.IZatDebugReportingService$IZatDebugReportCallback):void");
        }

        public Bundle a() throws IZatIllegalArgumentException {
            Bundle debugReport;
            synchronized (IZatManager.M) {
                try {
                    debugReport = this.f8604a.getDebugReport();
                } catch (RemoteException unused) {
                    throw new RuntimeException("Failed to get debug report");
                } catch (Throwable th) {
                    throw th;
                }
            }
            return debugReport;
        }
    }

    public class IZatGnssConfigServiceImpl implements IZatGnssConfigService {

        /* renamed from: a  reason: collision with root package name */
        IGnssConfigService f8608a;

        public IZatGnssConfigServiceImpl(IGnssConfigService iGnssConfigService) {
            this.f8608a = iGnssConfigService;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:11|12|13|14|15) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.qti.location.sdk.IZatGnssConfigService.IZatGnssConfigCallback r3) throws com.qti.location.sdk.IZatIllegalArgumentException {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0043
                java.lang.Object r0 = com.qti.location.sdk.IZatManager.S
                monitor-enter(r0)
                com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x0040 }
                java.util.Map r1 = r1.t     // Catch:{ all -> 0x0040 }
                r1.put(r2, r3)     // Catch:{ all -> 0x0040 }
                monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                java.lang.Object r3 = com.qti.location.sdk.IZatManager.R
                monitor-enter(r3)
                com.qti.location.sdk.IZatManager r0 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x003d }
                java.util.Map r0 = r0.t     // Catch:{ all -> 0x003d }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x003d }
                if (r0 != 0) goto L_0x003b
                com.qti.gnssconfig.IGnssConfigService r0 = r2.f8608a     // Catch:{ RemoteException -> 0x0033 }
                com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ RemoteException -> 0x0033 }
                com.qti.location.sdk.IZatManager$GnssConfigCallbackWrapper r1 = r1.ad     // Catch:{ RemoteException -> 0x0033 }
                r0.registerCallback(r1)     // Catch:{ RemoteException -> 0x0033 }
                com.qti.gnssconfig.IGnssConfigService r0 = r2.f8608a     // Catch:{ RemoteException -> 0x0033 }
                r0.getGnssSvTypeConfig()     // Catch:{ RemoteException -> 0x0033 }
                goto L_0x003b
            L_0x0033:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x003d }
                java.lang.String r1 = "Failed to get gnss sv type config"
                r0.<init>(r1)     // Catch:{ all -> 0x003d }
                throw r0     // Catch:{ all -> 0x003d }
            L_0x003b:
                monitor-exit(r3)     // Catch:{ all -> 0x003d }
                return
            L_0x003d:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x003d }
                throw r0
            L_0x0040:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0040 }
                throw r3
            L_0x0043:
                com.qti.location.sdk.IZatIllegalArgumentException r3 = new com.qti.location.sdk.IZatIllegalArgumentException
                java.lang.String r0 = "Invalid gnss config cb"
                r3.<init>(r0)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.IZatGnssConfigServiceImpl.a(com.qti.location.sdk.IZatGnssConfigService$IZatGnssConfigCallback):void");
        }

        public void a(Set<IZatGnssConfigService.IzatGnssSvType> set, Set<IZatGnssConfigService.IzatGnssSvType> set2) throws IZatIllegalArgumentException {
            int i;
            if (set == null && set2 == null) {
                throw new IZatIllegalArgumentException("Both enabled/disabled sv type lists null");
            }
            synchronized (IZatManager.R) {
                int i2 = 0;
                if (set != null) {
                    try {
                        i = set.size() + 0;
                    } catch (RemoteException unused) {
                        throw new RuntimeException("Failed to set gnss sv type config");
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    i = 0;
                }
                if (set2 != null) {
                    i += set2.size();
                }
                int[] iArr = new int[i];
                if (set2 != null) {
                    for (IZatGnssConfigService.IzatGnssSvType next : set2) {
                        if (set != null) {
                            if (set.contains(next)) {
                                throw new IZatIllegalArgumentException("Both enabled/disabled sv type lists contain: " + next);
                            }
                        }
                        iArr[i2] = next.getValue();
                        i2++;
                    }
                }
                if (set != null) {
                    for (IZatGnssConfigService.IzatGnssSvType value : set) {
                        iArr[i2] = value.getValue() ^ -1;
                        i2++;
                    }
                }
                this.f8608a.setGnssSvTypeConfig(iArr);
            }
        }

        public void a() {
            synchronized (IZatManager.R) {
                try {
                    this.f8608a.resetGnssSvTypeConfig();
                } catch (RemoteException unused) {
                    throw new RuntimeException("Failed to reset gnss sv type config");
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private class LocationCallbackWrapper extends ILocationCallback.Stub {
        IZatFlpService.IFlpLocationCallback mCallback;

        public LocationCallbackWrapper(IZatFlpService.IFlpLocationCallback iFlpLocationCallback) {
            this.mCallback = iFlpLocationCallback;
        }

        public void onLocationAvailable(Location[] locationArr) {
            if (this.mCallback == null) {
                Log.w(IZatManager.f8601a, "mCallback is NULL in LocationCallbackWrapper");
                return;
            }
            IZatDataValidation.a(locationArr);
            this.mCallback.a(locationArr);
        }
    }

    private class FlpStatusCallbackWrapper extends ISessionStatusCallback.Stub {
        IZatFlpService.IFlpStatusCallback mCallback;
        IFlpService mService;

        public FlpStatusCallbackWrapper(IZatFlpService.IFlpStatusCallback iFlpStatusCallback, IFlpService iFlpService) {
            this.mCallback = iFlpStatusCallback;
            this.mService = iFlpService;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f9, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onBatchingStatusCb(int r14) {
            /*
                r13 = this;
                com.qti.location.sdk.IZatFlpService$IFlpStatusCallback r0 = r13.mCallback
                if (r0 != 0) goto L_0x000d
                java.lang.String r0 = com.qti.location.sdk.IZatManager.f8601a
                java.lang.String r1 = "mCallback is NULL in FlpStatusCallbackWrapper"
                android.util.Log.w(r0, r1)
            L_0x000d:
                com.qti.location.sdk.utils.IZatValidationResults$IZatDataTypes r0 = com.qti.location.sdk.utils.IZatValidationResults.IZatDataTypes.FLP_STATUS
                com.qti.location.sdk.utils.IZatDataValidation.a((int) r14, (com.qti.location.sdk.utils.IZatValidationResults.IZatDataTypes) r0)
                java.lang.Object r0 = com.qti.location.sdk.IZatManager.H     // Catch:{ RemoteException -> 0x00fd }
                monitor-enter(r0)     // Catch:{ RemoteException -> 0x00fd }
                com.qti.location.sdk.IZatFlpService$IzatFlpStatus[] r1 = com.qti.location.sdk.IZatFlpService.IzatFlpStatus.values()     // Catch:{ all -> 0x00fa }
                r1 = r1[r14]     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatFlpService$IzatFlpStatus r2 = com.qti.location.sdk.IZatFlpService.IzatFlpStatus.OUTDOOR_TRIP_COMPLETED     // Catch:{ all -> 0x00fa }
                if (r1 == r2) goto L_0x0028
                com.qti.location.sdk.IZatFlpService$IFlpStatusCallback r2 = r13.mCallback     // Catch:{ all -> 0x00fa }
                r2.a(r1)     // Catch:{ all -> 0x00fa }
                monitor-exit(r0)     // Catch:{ all -> 0x00fa }
                return
            L_0x0028:
                com.qti.location.sdk.IZatManager r2 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00fa }
                java.util.Map r2 = r2.n     // Catch:{ all -> 0x00fa }
                java.util.Set r2 = r2.keySet()     // Catch:{ all -> 0x00fa }
                java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00fa }
                r3 = 0
                r4 = r3
            L_0x0038:
                boolean r5 = r2.hasNext()     // Catch:{ all -> 0x00fa }
                if (r5 == 0) goto L_0x005b
                java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$IZatFlpServiceImpl$IZatSessionHandlerImpl r4 = (com.qti.location.sdk.IZatManager.IZatFlpServiceImpl.IZatSessionHandlerImpl) r4     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager r5 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00fa }
                java.util.Map r5 = r5.n     // Catch:{ all -> 0x00fa }
                java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$FlpRequestMapItem r5 = (com.qti.location.sdk.IZatManager.FlpRequestMapItem) r5     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$FlpStatusCallbackWrapper r6 = r5.k()     // Catch:{ all -> 0x00fa }
                if (r6 != r13) goto L_0x0059
                r3 = r4
                r4 = r5
                goto L_0x005b
            L_0x0059:
                r4 = r5
                goto L_0x0038
            L_0x005b:
                if (r4 != 0) goto L_0x0068
                java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00fa }
                java.lang.String r2 = "no flp session undergoing"
                android.util.Log.w(r1, r2)     // Catch:{ all -> 0x00fa }
                monitor-exit(r0)     // Catch:{ all -> 0x00fa }
                return
            L_0x0068:
                com.qti.location.sdk.IZatManager$NotificationType r2 = r4.b()     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatFlpService$IzatFlpStatus r5 = com.qti.location.sdk.IZatFlpService.IzatFlpStatus.OUTDOOR_TRIP_COMPLETED     // Catch:{ all -> 0x00fa }
                if (r5 != r1) goto L_0x00f8
                com.qti.location.sdk.IZatManager$NotificationType r5 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED     // Catch:{ all -> 0x00fa }
                if (r5 != r2) goto L_0x00f8
                com.qti.location.sdk.IZatFlpService$IFlpStatusCallback r2 = r13.mCallback     // Catch:{ all -> 0x00fa }
                r2.a(r1)     // Catch:{ all -> 0x00fa }
                boolean r1 = r4.j()     // Catch:{ all -> 0x00fa }
                r2 = 1
                if (r1 == 0) goto L_0x00e3
                com.qti.flp.IFlpService r5 = r13.mService     // Catch:{ all -> 0x00fa }
                int r6 = r4.h()     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$NotificationType r1 = r4.c()     // Catch:{ all -> 0x00fa }
                int r7 = r1.getCode()     // Catch:{ all -> 0x00fa }
                long r8 = r4.d()     // Catch:{ all -> 0x00fa }
                int r10 = r4.e()     // Catch:{ all -> 0x00fa }
                long r11 = r4.f()     // Catch:{ all -> 0x00fa }
                int r1 = r5.startFlpSession(r6, r7, r8, r10, r11)     // Catch:{ all -> 0x00fa }
                if (r1 != 0) goto L_0x00d9
                com.qti.location.sdk.IZatManager$NotificationType r1 = r4.c()     // Catch:{ all -> 0x00fa }
                r4.a((com.qti.location.sdk.IZatManager.NotificationType) r1)     // Catch:{ all -> 0x00fa }
                r1 = 0
                r4.a((boolean) r1)     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00fa }
                int r1 = r1.e     // Catch:{ all -> 0x00fa }
                r1 = r1 & 8
                if (r1 <= 0) goto L_0x00f8
                com.qti.location.sdk.IZatManager$NotificationType r1 = com.qti.location.sdk.IZatManager.NotificationType.NOTIFICATION_ON_EVERY_LOCATION_FIX     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$NotificationType r3 = r4.b()     // Catch:{ all -> 0x00fa }
                if (r1 != r3) goto L_0x00f8
                com.qti.flp.IFlpService r1 = r13.mService     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$LocationCallbackWrapper r3 = r4.g()     // Catch:{ all -> 0x00fa }
                r1.unregisterCallback(r2, r3)     // Catch:{ all -> 0x00fa }
                com.qti.flp.IFlpService r5 = r13.mService     // Catch:{ all -> 0x00fa }
                r6 = 2
                int r7 = r4.h()     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$LocationCallbackWrapper r8 = r4.g()     // Catch:{ all -> 0x00fa }
                long r9 = r4.i()     // Catch:{ all -> 0x00fa }
                r5.registerCallback(r6, r7, r8, r9)     // Catch:{ all -> 0x00fa }
                goto L_0x00f8
            L_0x00d9:
                java.lang.String r1 = com.qti.location.sdk.IZatManager.f8601a     // Catch:{ all -> 0x00fa }
                java.lang.String r2 = "mService.updateFlpSession on trip completed failed."
                android.util.Log.v(r1, r2)     // Catch:{ all -> 0x00fa }
                goto L_0x00f8
            L_0x00e3:
                com.qti.flp.IFlpService r1 = r13.mService     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager$LocationCallbackWrapper r4 = r4.g()     // Catch:{ all -> 0x00fa }
                r1.unregisterCallback(r2, r4)     // Catch:{ all -> 0x00fa }
                r3.e()     // Catch:{ all -> 0x00fa }
                com.qti.location.sdk.IZatManager r1 = com.qti.location.sdk.IZatManager.this     // Catch:{ all -> 0x00fa }
                java.util.Map r1 = r1.n     // Catch:{ all -> 0x00fa }
                r1.remove(r3)     // Catch:{ all -> 0x00fa }
            L_0x00f8:
                monitor-exit(r0)     // Catch:{ all -> 0x00fa }
                return
            L_0x00fa:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00fa }
                throw r1     // Catch:{ RemoteException -> 0x00fd }
            L_0x00fd:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Failed to handle onBatchingStatusCb for status:"
                r1.append(r2)
                r1.append(r14)
                java.lang.String r14 = r1.toString()
                r0.<init>(r14)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.location.sdk.IZatManager.FlpStatusCallbackWrapper.onBatchingStatusCb(int):void");
        }
    }

    private enum NotificationType {
        NOTIFICATION_WHEN_BUFFER_IS_FULL(1),
        NOTIFICATION_ON_EVERY_LOCATION_FIX(2),
        NOTIFICATION_WHEN_TRIP_IS_COMPLETED(3);
        
        private final int mCode;

        private NotificationType(int i) {
            this.mCode = i;
        }

        public int getCode() {
            return this.mCode;
        }
    }

    private class FlpRequestMapItem {

        /* renamed from: a  reason: collision with root package name */
        public IZatFlpService.IFlpLocationCallback f8602a = null;
        public NotificationType b = null;
        public LocationCallbackWrapper c = null;
        public FlpStatusCallbackWrapper d = null;
        public long e = -1;
        public int f = -1;
        public long g = -1;
        public int h = -1;
        final /* synthetic */ IZatManager i;
        private long j = -1;
        private boolean k = false;
        private NotificationType l = null;
        private int m = 0;
        private long n = 0;

        public FlpRequestMapItem(IZatManager iZatManager, IZatFlpService.IFlpLocationCallback iFlpLocationCallback, NotificationType notificationType, long j2, int i2, long j3, LocationCallbackWrapper locationCallbackWrapper, int i3, long j4, int i4, long j5) {
            NotificationType notificationType2 = notificationType;
            this.i = iZatManager;
            this.f8602a = iFlpLocationCallback;
            this.b = notificationType2;
            this.l = notificationType2;
            this.e = j2;
            this.f = i2;
            this.g = j3;
            this.c = locationCallbackWrapper;
            this.h = i3;
            this.j = j4;
            this.k = false;
            this.d = null;
            this.m = i4;
            this.n = j5;
        }

        public IZatFlpService.IFlpLocationCallback a() {
            return this.f8602a;
        }

        public void a(NotificationType notificationType) {
            this.b = notificationType;
            if (NotificationType.NOTIFICATION_WHEN_TRIP_IS_COMPLETED != notificationType) {
                this.l = notificationType;
            }
        }

        public NotificationType b() {
            return this.b;
        }

        public NotificationType c() {
            return this.l;
        }

        public long d() {
            return this.e;
        }

        public int e() {
            return this.f;
        }

        public long f() {
            return this.g;
        }

        public LocationCallbackWrapper g() {
            return this.c;
        }

        public int h() {
            return this.h;
        }

        public long i() {
            return this.j;
        }

        public void a(long j2) {
            this.j = j2;
        }

        public void a(boolean z) {
            this.k = z;
        }

        public boolean j() {
            return this.k;
        }

        public void a(FlpStatusCallbackWrapper flpStatusCallbackWrapper) {
            this.d = flpStatusCallbackWrapper;
        }

        public FlpStatusCallbackWrapper k() {
            return this.d;
        }

        public void a(int i2) {
            if ((this.i.e & 32) != 0) {
                this.m = i2;
                return;
            }
            throw new IZatFeatureNotSupportedException("AGPM is not supported");
        }

        public int l() {
            return this.m;
        }

        public void b(long j2) {
            if ((this.i.e & 32) != 0) {
                this.n = j2;
                return;
            }
            throw new IZatFeatureNotSupportedException("AGPM is not supported");
        }

        public long m() {
            return this.n;
        }
    }

    private class GeofenceMapItem {

        /* renamed from: a  reason: collision with root package name */
        Object f8603a;
        int b;
        IZatGeofenceService.IZatGeofenceCallback c;

        public GeofenceMapItem(Object obj, int i, IZatGeofenceService.IZatGeofenceCallback iZatGeofenceCallback) {
            this.f8603a = obj;
            this.b = i;
            this.c = iZatGeofenceCallback;
        }

        public Object a() {
            return this.f8603a;
        }

        public int b() {
            return this.b;
        }

        public IZatGeofenceService.IZatGeofenceCallback c() {
            return this.c;
        }
    }

    private class MaxPowerAllocatedCallbackWrapper extends IMaxPowerAllocatedCallback.Stub {
        IZatTestService.IFlpMaxPowerAllocatedCallback mCallback;

        public MaxPowerAllocatedCallbackWrapper(IZatTestService.IFlpMaxPowerAllocatedCallback iFlpMaxPowerAllocatedCallback) {
            this.mCallback = iFlpMaxPowerAllocatedCallback;
        }

        public void onMaxPowerAllocatedChanged(double d) {
            if (this.mCallback == null) {
                Log.w(IZatManager.f8601a, "mCallback is NULL in MaxPowerAllocatedCallbackWrapper");
            } else {
                this.mCallback.a(d);
            }
        }
    }

    private class GeofenceStatusCallbackWrapper extends IGeofenceCallback.Stub {
        private GeofenceStatusCallbackWrapper() {
        }

        public void onTransitionEvent(int i, int i2, Location location) {
            if (IZatManager.b) {
                String k = IZatManager.f8601a;
                Log.d(k, "onTransitionEvent - geofenceHwId is " + i + "; event is " + i2);
            }
            IZatDataValidation.a(location);
            IZatDataValidation.a(i2, IZatValidationResults.IZatDataTypes.GEO_EVENT);
            synchronized (IZatManager.K) {
                for (IZatGeofenceServiceImpl.IZatGeofenceHandleImpl iZatGeofenceHandleImpl : IZatManager.this.r.keySet()) {
                    GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(iZatGeofenceHandleImpl);
                    if (geofenceMapItem.b() == i) {
                        geofenceMapItem.c().a((IZatGeofenceService.IZatGeofenceHandle) iZatGeofenceHandleImpl, i2, location);
                        return;
                    }
                }
            }
        }

        public void onRequestResultReturned(int i, int i2, int i3) {
            if (IZatManager.b) {
                String k = IZatManager.f8601a;
                Log.d(k, "onRequestResultReturned - geofenceHwId is " + i + "; requestType is " + i2 + "; result is " + i3);
            }
            IZatDataValidation.a(i2, IZatValidationResults.IZatDataTypes.GEO_REQUEST_TYPE);
            IZatDataValidation.a(i3, IZatValidationResults.IZatDataTypes.GEO_ERROR_CODE);
            if (i3 != 0) {
                synchronized (IZatManager.K) {
                    for (IZatGeofenceServiceImpl.IZatGeofenceHandleImpl iZatGeofenceHandleImpl : IZatManager.this.r.keySet()) {
                        GeofenceMapItem geofenceMapItem = (GeofenceMapItem) IZatManager.this.r.get(iZatGeofenceHandleImpl);
                        if (geofenceMapItem.b() == i) {
                            if (1 == i2) {
                                IZatManager.this.r.remove(iZatGeofenceHandleImpl);
                            }
                            geofenceMapItem.c().a((IZatGeofenceService.IZatGeofenceHandle) iZatGeofenceHandleImpl, i2, i3);
                            return;
                        }
                    }
                }
            }
        }

        public void onEngineReportStatus(int i, Location location) {
            if (IZatManager.b) {
                String k = IZatManager.f8601a;
                Log.d(k, "onEngineReportStatus - status is " + i);
            }
            IZatDataValidation.a(location);
            IZatDataValidation.a(i, IZatValidationResults.IZatDataTypes.GEO_ENGINE_STATUS);
            synchronized (IZatManager.L) {
                for (IZatGeofenceServiceImpl iZatGeofenceServiceImpl : IZatManager.this.q.keySet()) {
                    ((IZatGeofenceService.IZatGeofenceCallback) IZatManager.this.q.get(iZatGeofenceServiceImpl)).a(i, location);
                }
            }
        }
    }

    private class DebugReportCallbackWrapper extends IDebugReportCallback.Stub {
        private DebugReportCallbackWrapper() {
        }

        public void onDebugDataAvailable(Bundle bundle) {
            if (IZatManager.b) {
                Log.v(IZatManager.f8601a, "onDebugDataAvailable");
            }
            synchronized (IZatManager.N) {
                for (IZatDebugReportingServiceImpl iZatDebugReportingServiceImpl : IZatManager.this.s.keySet()) {
                    ((IZatDebugReportingService.IZatDebugReportCallback) IZatManager.this.s.get(iZatDebugReportingServiceImpl)).a(bundle);
                }
            }
        }
    }

    private class WiFiDBReceiverRespListenerWrapper extends IWiFiDBReceiverResponseListener.Stub {
        private WiFiDBReceiverRespListenerWrapper() {
        }

        public void onAPListAvailable(List<APInfo> list) {
            IZatDBCommon.IZatCellInfo iZatCellInfo;
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onAPListAvailable");
            }
            if (IZatManager.this.u != null) {
                ArrayList<IZatWiFiDBReceiver.IZatAPInfo> arrayList = new ArrayList<>();
                Iterator<APInfo> it = list.iterator();
                while (true) {
                    IZatDBCommon.IZatAPSSIDInfo iZatAPSSIDInfo = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    APInfo next = it.next();
                    if (next.c == 0 && next.d == 0 && next.e == 0 && next.f == 0) {
                        iZatCellInfo = null;
                    } else {
                        iZatCellInfo = new IZatDBCommon.IZatCellInfo(next.c, next.d, next.e, next.f, Integer.valueOf(next.b));
                    }
                    if (next.g != null && next.g.length > 0) {
                        iZatAPSSIDInfo = new IZatDBCommon.IZatAPSSIDInfo(next.g, (short) next.g.length);
                    }
                    arrayList.add(new IZatWiFiDBReceiver.IZatAPInfo(next.f8635a, 0, new IZatWiFiDBReceiver.IZatAPInfoExtra(iZatCellInfo, iZatAPSSIDInfo)));
                }
                if (IZatManager.b) {
                    IZatManager.this.a((List<IZatWiFiDBReceiver.IZatAPInfo>) arrayList, (IZatDBCommon.IZatApBsListStatus) null, (Location) null);
                }
                for (IZatWiFiDBReceiver.IZatAPInfo a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                if (IZatManager.this.u.c != null) {
                    IZatManager.this.u.c.a(arrayList);
                }
            }
        }

        private IZatWiFiDBReceiver.IZatAPInfo iZatAPInfoFromAPInfoExt(APInfoExt aPInfoExt) {
            IZatDBCommon.IZatCellInfo iZatCellInfo;
            IZatWiFiDBReceiver.IZatAPInfoExtra iZatAPInfoExtra = null;
            if (aPInfoExt.c == 0 && aPInfoExt.d == 0 && aPInfoExt.e == 0 && aPInfoExt.f == 0) {
                iZatCellInfo = null;
            } else {
                iZatCellInfo = new IZatDBCommon.IZatCellInfo(aPInfoExt.c, aPInfoExt.d, aPInfoExt.e, aPInfoExt.f, Integer.valueOf(aPInfoExt.b));
            }
            IZatDBCommon.IZatAPSSIDInfo iZatAPSSIDInfo = (aPInfoExt.g == null || aPInfoExt.g.length <= 0) ? null : new IZatDBCommon.IZatAPSSIDInfo(aPInfoExt.g, (short) aPInfoExt.g.length);
            if (!(iZatCellInfo == null || iZatAPSSIDInfo == null)) {
                iZatAPInfoExtra = new IZatWiFiDBReceiver.IZatAPInfoExtra(iZatCellInfo, iZatAPSSIDInfo);
            }
            return new IZatWiFiDBReceiver.IZatAPInfo(aPInfoExt.f8636a, aPInfoExt.h, iZatAPInfoExtra);
        }

        public void onAPListAvailableExt(List<APInfoExt> list, int i, Location location) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onAPListAvailable");
            }
            if (IZatManager.this.u != null) {
                ArrayList<IZatWiFiDBReceiver.IZatAPInfo> arrayList = new ArrayList<>();
                for (APInfoExt iZatAPInfoFromAPInfoExt : list) {
                    arrayList.add(iZatAPInfoFromAPInfoExt(iZatAPInfoFromAPInfoExt));
                }
                if (IZatManager.b) {
                    IZatManager.this.a((List<IZatWiFiDBReceiver.IZatAPInfo>) arrayList, IZatDBCommon.IZatApBsListStatus.fromInt(i), location);
                }
                for (IZatWiFiDBReceiver.IZatAPInfo a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                if (IZatManager.this.u.d != null) {
                    IZatManager.this.u.d.a(arrayList, IZatDBCommon.IZatApBsListStatus.fromInt(i), location);
                }
            }
        }

        public void onLookupRequest(List<APInfoExt> list, Location location) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onLookupRequest");
            }
            if (IZatManager.this.u != null) {
                ArrayList<IZatWiFiDBReceiver.IZatAPInfo> arrayList = new ArrayList<>();
                for (APInfoExt next : list) {
                    IZatWiFiDBReceiver.IZatAPInfo iZatAPInfoFromAPInfoExt = iZatAPInfoFromAPInfoExt(next);
                    if (IZatManager.b) {
                        String k = IZatManager.f8601a;
                        int i = next.i;
                        Log.d(k, "onLookupRequest ap.mFdalStatus: " + i);
                    }
                    iZatAPInfoFromAPInfoExt.a(next.i);
                    arrayList.add(iZatAPInfoFromAPInfoExt);
                }
                if (IZatManager.b) {
                    IZatManager.this.a((List<IZatWiFiDBReceiver.IZatAPInfo>) arrayList, IZatDBCommon.IZatApBsListStatus.STD_FINAL, location);
                }
                for (IZatWiFiDBReceiver.IZatAPInfo a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                IZatDataValidation.a(location);
                if (IZatManager.this.u.d != null) {
                    IZatManager.this.u.d.a((List<IZatWiFiDBReceiver.IZatAPInfo>) arrayList, location);
                }
            }
        }

        public void onStatusUpdate(boolean z, String str) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onStatusUpdate");
            }
            if (IZatManager.this.u == null) {
                return;
            }
            if (IZatManager.this.u.d != null) {
                IZatManager.this.u.d.a(z, str);
            } else if (IZatManager.this.u.c != null) {
                IZatManager.this.u.c.a(z, str);
            }
        }

        public void onServiceRequest() {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onServiceRequest");
            }
            if (IZatManager.this.u == null) {
                return;
            }
            if (IZatManager.this.u.d != null) {
                IZatManager.this.u.d.a();
            } else if (IZatManager.this.u.c != null) {
                IZatManager.this.u.c.a();
            }
        }
    }

    private class WiFiDBProviderRespListenerWrapper extends IWiFiDBProviderResponseListener.Stub {
        private WiFiDBProviderRespListenerWrapper() {
        }

        public void onApObsLocDataAvailable(List<APObsLocData> list, int i) {
            IZatDBCommon.IZatCellInfo iZatCellInfo;
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onApObsLocDataAvailable");
            }
            if (IZatManager.this.w != null) {
                ArrayList<IZatWiFiDBProvider.IZatAPObsLocData> arrayList = new ArrayList<>();
                for (APObsLocData next : list) {
                    ArrayList arrayList2 = new ArrayList();
                    APScan[] aPScanArr = next.d;
                    int length = aPScanArr.length;
                    int i2 = 0;
                    while (true) {
                        IZatDBCommon.IZatAPSSIDInfo iZatAPSSIDInfo = null;
                        if (i2 >= length) {
                            break;
                        }
                        APScan aPScan = aPScanArr[i2];
                        if (aPScan.d != null && aPScan.d.length > 0) {
                            iZatAPSSIDInfo = new IZatDBCommon.IZatAPSSIDInfo(aPScan.d, (short) aPScan.d.length);
                        }
                        arrayList2.add(new IZatWiFiDBProvider.IZatAPScan(aPScan.f8634a, aPScan.b, aPScan.c, iZatAPSSIDInfo, aPScan.e));
                        i2++;
                    }
                    if (next.b.b == 0 && next.b.c == 0 && next.b.d == 0 && next.b.e == 0) {
                        iZatCellInfo = null;
                    } else {
                        iZatCellInfo = new IZatDBCommon.IZatCellInfo(next.b.b, next.b.c, next.b.d, next.b.e, Integer.valueOf(next.b.f8640a));
                    }
                    arrayList.add(new IZatWiFiDBProvider.IZatAPObsLocData(next.f8633a, iZatCellInfo, (long) next.c, arrayList2));
                }
                if (IZatManager.b) {
                    IZatManager.this.b(arrayList, IZatDBCommon.IZatApBsListStatus.values()[i]);
                }
                for (IZatWiFiDBProvider.IZatAPObsLocData a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                IZatManager.this.w.c.a(arrayList, IZatDBCommon.IZatApBsListStatus.values()[i]);
            }
        }

        public void onServiceRequest() {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onServiceRequest");
            }
            if (IZatManager.this.w != null) {
                IZatManager.this.w.c.a();
            }
        }
    }

    private class WWANDBReceiverRespListenerWrapper extends IWWANDBReceiverResponseListener.Stub {
        private WWANDBReceiverRespListenerWrapper() {
        }

        public void onBSListAvailable(List<BSInfo> list) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onBSListAvailable legacy");
            }
            if (IZatManager.this.v != null) {
                ArrayList<IZatWWANDBReceiver.IZatBSInfo> arrayList = new ArrayList<>();
                for (BSInfo next : list) {
                    arrayList.add(new IZatWWANDBReceiver.IZatBSInfo(new IZatDBCommon.IZatCellInfo(next.b, next.c, next.d, next.e, Integer.valueOf(next.f8640a))));
                }
                for (IZatWWANDBReceiver.IZatBSInfo a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                if (IZatManager.this.v.c != null) {
                    IZatManager.this.v.c.a(arrayList);
                }
            }
        }

        public void onBSListAvailableExt(List<BSInfo> list, int i, Location location) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onBSListAvailable");
            }
            if (IZatManager.this.v != null) {
                ArrayList<IZatDBCommon.IZatCellInfo> arrayList = new ArrayList<>();
                for (BSInfo next : list) {
                    IZatDBCommon.IZatCellInfo iZatCellInfo = new IZatDBCommon.IZatCellInfo(next.b, next.c, next.d, next.e, Integer.valueOf(next.f8640a));
                    iZatCellInfo.a(next.f);
                    arrayList.add(iZatCellInfo);
                }
                for (IZatDBCommon.IZatCellInfo a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                if (IZatManager.this.v.d != null) {
                    IZatManager.this.v.d.a(arrayList, IZatDBCommon.IZatApBsListStatus.fromInt(i), location);
                }
            }
        }

        public void onStatusUpdate(boolean z, String str) {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onStatusUpdate");
            }
            if (IZatManager.this.v == null) {
                return;
            }
            if (IZatManager.this.v.d != null) {
                IZatManager.this.v.d.a(z, str);
            } else if (IZatManager.this.v.c != null) {
                IZatManager.this.v.c.a(z, str);
            }
        }

        public void onServiceRequest() {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onServiceRequest");
            }
            if (IZatManager.this.v == null) {
                return;
            }
            if (IZatManager.this.v.d != null) {
                IZatManager.this.v.d.a();
            } else if (IZatManager.this.v.c != null) {
                IZatManager.this.v.c.a();
            }
        }
    }

    private class GnssConfigCallbackWrapper extends IGnssConfigCallback.Stub {
        private GnssConfigCallbackWrapper() {
        }

        public void getGnssSvTypeConfigCb(int[] iArr) {
            if (IZatManager.b) {
                Log.v(IZatManager.f8601a, "getGnssSvTypeConfigCb");
            }
            synchronized (IZatManager.S) {
                for (IZatGnssConfigServiceImpl iZatGnssConfigServiceImpl : IZatManager.this.t.keySet()) {
                    HashSet hashSet = new HashSet();
                    HashSet hashSet2 = new HashSet();
                    for (int i : iArr) {
                        IZatGnssConfigService.IzatGnssSvType fromInt = IZatGnssConfigService.IzatGnssSvType.fromInt(i);
                        if (fromInt != null) {
                            hashSet2.add(fromInt);
                        } else {
                            IZatGnssConfigService.IzatGnssSvType fromInt2 = IZatGnssConfigService.IzatGnssSvType.fromInt((i ^ -1) & 255);
                            if (fromInt2 != null) {
                                hashSet.add(fromInt2);
                            } else {
                                Log.e(IZatManager.f8601a, "Invalid sv type: " + i);
                            }
                        }
                    }
                    ((IZatGnssConfigService.IZatGnssConfigCallback) IZatManager.this.t.get(iZatGnssConfigServiceImpl)).a(hashSet, hashSet2);
                }
            }
        }
    }

    private class WWANDBProviderRespListenerWrapper extends IWWANDBProviderResponseListener.Stub {
        private WWANDBProviderRespListenerWrapper() {
        }

        public void onBsObsLocDataAvailable(List<BSObsLocationData> list, int i) throws RemoteException {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onBsObsLocDataAvailable");
            }
            if (IZatManager.this.x != null) {
                ArrayList<IZatWWANDBProvider.IZatBSObsLocationData> arrayList = new ArrayList<>();
                for (BSObsLocationData next : list) {
                    IZatDBCommon.IZatCellInfo iZatCellInfo = new IZatDBCommon.IZatCellInfo(next.b.b, next.b.c, next.b.d, next.b.e, Integer.valueOf(next.b.f8640a));
                    iZatCellInfo.a(next.b.f);
                    IZatWWANDBProvider.IZatBSObsLocationData iZatBSObsLocationData = new IZatWWANDBProvider.IZatBSObsLocationData(iZatCellInfo, next.f8639a, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes.MEDIUM, IZatWWANDBReceiver.IZatBSLocationDataBase.IZatReliablityTypes.MEDIUM);
                    iZatBSObsLocationData.a((long) next.c);
                    arrayList.add(iZatBSObsLocationData);
                }
                if (IZatManager.b) {
                    IZatManager.this.a((List<IZatWWANDBProvider.IZatBSObsLocationData>) arrayList, IZatDBCommon.IZatApBsListStatus.fromInt(i));
                }
                for (IZatWWANDBProvider.IZatBSObsLocationData a2 : arrayList) {
                    IZatDataValidation.a(a2);
                }
                if (IZatManager.this.x.c != null) {
                    IZatManager.this.x.c.a(arrayList, IZatDBCommon.IZatApBsListStatus.fromInt(i));
                }
            }
        }

        public void onServiceRequest() {
            if (IZatManager.b) {
                Log.d(IZatManager.f8601a, "onServiceRequest");
            }
            if (IZatManager.this.x != null && IZatManager.this.x.c != null) {
                IZatManager.this.x.c.a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(IZatWWANDBReceiver.IZatBSLocationData iZatBSLocationData) {
        Log.v(f8601a, "Location data: ");
        a(iZatBSLocationData.l());
        String str = f8601a;
        IZatDBCommon.IZatCellInfo m2 = iZatBSLocationData.m();
        Log.v(str, "Cell data: " + m2);
        a(iZatBSLocationData.m());
    }

    /* access modifiers changed from: private */
    public void a(IZatWWANDBReceiver.IZatBSSpecialInfo iZatBSSpecialInfo) {
        String str = f8601a;
        IZatDBCommon.IZatAPBSSpecialInfoTypes b2 = iZatBSSpecialInfo.b();
        Log.v(str, "Special info: " + b2);
        String str2 = f8601a;
        IZatDBCommon.IZatCellInfo a2 = iZatBSSpecialInfo.a();
        Log.v(str2, "Cell data: " + a2);
        a(iZatBSSpecialInfo.a());
    }

    /* access modifiers changed from: private */
    public void a(List<IZatWWANDBProvider.IZatBSObsLocationData> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus) {
        String str = f8601a;
        int size = list.size();
        Log.v(str, "BS ObsLocData Available size: " + size + " status:" + iZatApBsListStatus);
        int i2 = 1;
        for (IZatWWANDBProvider.IZatBSObsLocationData next : list) {
            String str2 = f8601a;
            Log.v(str2, "Entry IZatBSObsLocationData num. " + i2);
            Log.v(f8601a, "Location data: ");
            a(next.l());
            String str3 = f8601a;
            IZatDBCommon.IZatCellInfo m2 = next.m();
            Log.v(str3, "Cell data: " + m2);
            a(next.m());
            Log.v(f8601a, "Scan data: ");
            String str4 = f8601a;
            long a2 = next.a();
            Log.v(str4, "Scan timestamp: " + a2);
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public void b(List<IZatWiFiDBProvider.IZatAPObsLocData> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus) {
        String str = f8601a;
        int size = list.size();
        Log.v(str, "Ap ObsLocData Available size: " + size + " status:" + iZatApBsListStatus);
        int i2 = 1;
        for (IZatWiFiDBProvider.IZatAPObsLocData next : list) {
            String str2 = f8601a;
            Log.v(str2, "Entry IZatAPObsLocData num. " + i2 + " Location data:");
            a(next.a());
            String str3 = f8601a;
            IZatDBCommon.IZatCellInfo b2 = next.b();
            Log.v(str3, "Cell data: " + b2);
            a(next.b());
            String str4 = f8601a;
            long d2 = next.d();
            int size2 = next.c().size();
            Log.v(str4, "Scan timestamp: " + d2 + "Number of APScan entries: " + size2);
            a(next.c());
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public void a(List<IZatWiFiDBReceiver.IZatAPInfo> list, IZatDBCommon.IZatApBsListStatus iZatApBsListStatus, Location location) {
        String str = f8601a;
        int size = list.size();
        Log.v(str, "AP List size:" + size + " status: " + iZatApBsListStatus);
        a(location);
        for (IZatWiFiDBReceiver.IZatAPInfo next : list) {
            StringBuilder sb = new StringBuilder("AP Info mac: ");
            sb.append(next.a());
            sb.append("AP Info timestamp: ");
            sb.append(next.b());
            if (next.e()) {
                IZatWiFiDBReceiver.IZatAPInfoExtra c2 = next.c();
                if (c2.b() != null) {
                    try {
                        sb.append("SSID utf8: ");
                        sb.append(new String(c2.b().f8595a, "UTF-8"));
                    } catch (UnsupportedEncodingException unused) {
                        sb.append("SSID utf8: invalid utf8");
                    }
                } else {
                    sb.append("SSID utf8: null");
                }
                a(c2.a());
            } else {
                sb.append("AP info no extra data available.");
            }
            Log.v(f8601a, sb.toString());
        }
    }

    private void a(Location location) {
        if (location == null) {
            Log.v(f8601a, "Location is null");
            return;
        }
        String str = f8601a;
        String provider = location.getProvider();
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        String valueOf = location.hasAltitude() ? Double.valueOf(location.getAltitude()) : "EMPTY";
        String valueOf2 = location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : "EMPTY";
        long time = location.getTime();
        String valueOf3 = location.hasVerticalAccuracy() ? Float.valueOf(location.getVerticalAccuracyMeters()) : "EMPTY";
        Log.v(str, "Provider: " + provider + "Longitude: " + longitude + "Latitude: " + latitude + "Altitude: " + valueOf + "Accuracy: " + valueOf2 + "loc. Timestamp: " + time + "VerticalAccuracy: " + valueOf3);
    }

    private void a(IZatDBCommon.IZatCellInfo iZatCellInfo) {
        if (iZatCellInfo == null) {
            Log.v(f8601a, "cellInfo is null");
            return;
        }
        StringBuilder sb = new StringBuilder("RegionID1: ");
        sb.append(iZatCellInfo.b());
        sb.append("RegionID2: ");
        sb.append(iZatCellInfo.c());
        sb.append("RegionID3: ");
        sb.append(iZatCellInfo.d());
        sb.append("RegionID4: ");
        sb.append(iZatCellInfo.e());
        sb.append("Type:      ");
        sb.append(iZatCellInfo.f());
        try {
            sb.append(iZatCellInfo.a());
        } catch (IZatStaleDataException unused) {
            sb.append("Timestamp: invalid");
        }
        Log.v(f8601a, sb.toString());
    }

    private void a(List<IZatWiFiDBProvider.IZatAPScan> list) {
        int i2 = 1;
        for (IZatWiFiDBProvider.IZatAPScan next : list) {
            StringBuilder sb = new StringBuilder("===  Entry IZatAPScan num. ");
            sb.append(i2);
            sb.append("Mac address: ");
            sb.append(next.a());
            sb.append("Rssi: ");
            sb.append(next.b());
            if (next.d() != null) {
                try {
                    sb.append("SSID utf8: ");
                    sb.append(new String(next.d().f8595a, "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                    sb.append("SSID utf8: invalid utf8");
                }
            } else {
                sb.append("SSID utf8: null");
            }
            sb.append("Channel number: ");
            sb.append(next.e());
            sb.append("Delta time:  ");
            sb.append(next.c());
            Log.v(f8601a, sb.toString());
            i2++;
        }
    }
}
