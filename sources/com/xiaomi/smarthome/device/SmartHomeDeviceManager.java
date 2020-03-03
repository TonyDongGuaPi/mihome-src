package com.xiaomi.smarthome.device;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanState;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.ClientApiStub;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.EventDispatcher;
import com.xiaomi.smarthome.library.safejson.JSONObjectSafe;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.lite.LiteDeviceAbstract;
import com.xiaomi.smarthome.lite.LiteDeviceManager;
import com.xiaomi.smarthome.miio.camera.LiveCameraDeviceManager;
import com.xiaomi.smarthome.miio.camera.match.SearchCameraDevice;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.miio.device.TemporaryDevice;
import com.xiaomi.smarthome.miio.page.DeviceGroup;
import com.xiaomi.smarthome.miio.page.DeviceGroupManager;
import com.xiaomi.stat.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartHomeDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14974a = "SmartHomeDeviceManager";
    public static final String b = "com.smarthome.refresh_list_view";
    public static final String c = "refresh_all";
    public static final String d = "param_did";
    public static final int e = 2;
    public static final int f = 3;
    private static final boolean h = (GlobalSetting.q || GlobalSetting.s);
    private static volatile SmartHomeDeviceManager i;
    /* access modifiers changed from: private */
    public List<Device> A = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> B = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, Device> C = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public List<Device> D = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> E;
    private Map<String, List<String>> F = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public List<String> G = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> H = new ArrayList();
    private Map<String, String> I = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Set<String> J = new HashSet();
    private String K;
    private String L;
    /* access modifiers changed from: private */
    public volatile long M = 0;
    /* access modifiers changed from: private */
    public Runnable N = new Runnable() {
        public void run() {
            SmartHomeDeviceManager.this.j.compareAndSet(true, false);
        }
    };
    public boolean g = true;
    /* access modifiers changed from: private */
    public AtomicBoolean j = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean k = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public volatile boolean l = false;
    /* access modifiers changed from: private */
    public volatile boolean m = false;
    /* access modifiers changed from: private */
    public volatile boolean n = false;
    private BroadcastReceiver o;
    /* access modifiers changed from: private */
    public Handler p;
    /* access modifiers changed from: private */
    public Context q;
    /* access modifiers changed from: private */
    public List<DeviceSearch<?>> r = new ArrayList();
    /* access modifiers changed from: private */
    public List<IClientDeviceListener> s = new ArrayList();
    /* access modifiers changed from: private */
    public List<IsDeviceReadyCallback> t = new ArrayList();
    private List<Device> u = new ArrayList();
    private Map<String, Device> v = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public List<Device> w = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, Device> x = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public List<Device> y = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, Device> z = new ConcurrentHashMap();

    public interface IClientDeviceListener {
        void a(int i);

        void a(int i, Device device);

        void b(int i);
    }

    public interface IDelDeviceBatchCallback {
        void a();

        void a(Error error);
    }

    public interface IsDeviceReadyCallback {
        void onDeviceReady(List<Device> list);
    }

    public static SmartHomeDeviceManager a() {
        if (i == null) {
            synchronized (SmartHomeDeviceManager.class) {
                if (i == null) {
                    i = new SmartHomeDeviceManager(CommonApplication.getAppContext());
                }
            }
        }
        return i;
    }

    private static class CacheDeviceData {

        /* renamed from: a  reason: collision with root package name */
        public List<Device> f14995a;
        public List<Device> b;
        public List<Device> c;
        public List<DeviceGroup> d;
        public List<LiteDeviceAbstract> e;
        public List<Device> f;
        public List<Device> g;

        private CacheDeviceData() {
            this.f14995a = new ArrayList();
            this.b = new ArrayList();
            this.c = new ArrayList();
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.g = new ArrayList();
        }
    }

    private SmartHomeDeviceManager(Context context) {
        this.q = context.getApplicationContext();
        B();
        this.r.add(new MiTVMiWIFIDeviceSearch());
        this.r.add(new CameraDeviceSearch());
        this.r.add(new MiioDeviceSearch());
        this.r.add(GeneralAPDeviceSearch.a());
        this.r.add(new BleDeviceSearch());
        this.r.add(new MiioSubDeviceSearch());
        this.r.add(new VirtualDeviceSearch());
        this.r.add(new MiioVirtualDeviceSearch());
        this.r.add(new PhoneIRDeviceSearch());
        this.r.add(new IrControlDeviceSearch());
        this.r.add(new ApDeviceSearch());
        this.r.add(ZhilianCameraSearch.a());
        this.r.add(TemporaryDeviceSearch.a());
        this.r.add(new BleMeshDeviceSearch());
        this.r.add(new VirtualGroupSearch());
        this.r.add(new NbiotDeviceSearch());
        this.p = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public boolean A() {
        return SmartHomeDeviceHelper.a().h();
    }

    private void B() {
        if (this.o == null) {
            this.o = new MyBroadcastReceiver(this);
            LocalBroadcastManager.getInstance(this.q).registerReceiver(this.o, new IntentFilter(ClientApiStub.ACTION_UPDATE_DEVICE_LIST));
        }
    }

    public List<DeviceSearch<?>> b() {
        return this.r;
    }

    public void c() {
        if (GlobalSetting.u || GlobalSetting.q) {
            LogUtilGrey.a(f14974a, "startNotifySuccess in " + Log.getStackTraceString(new Exception()));
        }
        this.p.post(new Runnable() {
            public void run() {
                AppStateNotifier stateNotifier = ServiceApplication.getStateNotifier();
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "startNotifySuccess: outer want to startNotifyRefresh, mIsRefreshing = " + SmartHomeDeviceManager.this.j.get());
                if (stateNotifier != null && stateNotifier.a() == 4 && !SmartHomeDeviceManager.this.j.get() && SmartHomeDeviceManager.this.u()) {
                    SmartHomeDeviceManager.this.a(false);
                }
            }
        });
    }

    public void a(IClientDeviceListener iClientDeviceListener) {
        synchronized (this.s) {
            if (!this.s.contains(iClientDeviceListener)) {
                this.s.add(iClientDeviceListener);
            }
        }
    }

    public void b(IClientDeviceListener iClientDeviceListener) {
        synchronized (this.s) {
            if (!this.s.contains(iClientDeviceListener)) {
                this.s.add(0, iClientDeviceListener);
            }
        }
    }

    public void c(IClientDeviceListener iClientDeviceListener) {
        synchronized (this.s) {
            this.s.remove(iClientDeviceListener);
        }
    }

    public List<Device> d() {
        if (this.g) {
            return this.u;
        }
        return this.w;
    }

    public List<Device> e() {
        return this.y;
    }

    public List<Device> f() {
        ArrayList arrayList = new ArrayList(this.u.size() + this.y.size());
        arrayList.addAll(this.u);
        arrayList.addAll(this.y);
        return arrayList;
    }

    public Map<String, Device> g() {
        if (this.g) {
            return this.v;
        }
        return this.x;
    }

    public Map<String, Device> h() {
        return this.z;
    }

    public List<Device> i() {
        return this.D;
    }

    public Device a(String str) {
        List<Device> i2;
        if (TextUtils.isEmpty(str) || (i2 = i()) == null) {
            return null;
        }
        for (Device next : i2) {
            if (next.did != null && next.did.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public void a(Device device) {
        if (device != null) {
            LogUtilGrey.a(f14974a, "refreshDevice: name = " + device.name + ", did = " + device.did + ", model = " + device.model + ", mac = " + device.mac);
            synchronized (this.s) {
                for (int i2 = 0; i2 < this.s.size(); i2++) {
                    if (this.s.get(i2) != null) {
                        this.s.get(i2).a(2, device);
                    }
                }
            }
        }
    }

    public void j() {
        LogUtilGrey.a(f14974a, "refreshDeviceAll");
        synchronized (this.s) {
            for (int i2 = 0; i2 < this.s.size(); i2++) {
                if (this.s.get(i2) != null) {
                    this.s.get(i2).a(2, (Device) null);
                }
            }
        }
    }

    public List<Device> k() {
        return this.B;
    }

    public Map<String, Device> l() {
        return this.C;
    }

    public Device b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Device device = g().get(str);
        if (device != null) {
            return device;
        }
        if (this.g) {
            device = l().get(str);
        }
        return device == null ? h().get(str) : device;
    }

    public Device c(String str) {
        for (Device next : this.H) {
            if (TextUtils.equals(next.did, str)) {
                return next;
            }
        }
        return null;
    }

    public List<Device> m() {
        if (this.H != null) {
            return this.H;
        }
        return new ArrayList();
    }

    public List<Device> d(String str) {
        ArrayList arrayList = new ArrayList();
        List<String> list = this.F.get(str);
        if (list != null && list.size() > 0) {
            for (String c2 : list) {
                Device c3 = c(c2);
                if (c3 != null) {
                    arrayList.add(c3);
                }
            }
        }
        return arrayList;
    }

    public List<String> n() {
        return new ArrayList(this.F.keySet());
    }

    public String e(String str) {
        if (!this.I.containsKey(str)) {
            return "3";
        }
        String str2 = this.I.get(str);
        return Arrays.asList(LightGroupManager.d).contains(str2) ? str2 : "3";
    }

    public boolean a(String str, String str2) {
        if (!Arrays.asList(LightGroupManager.d).contains(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        this.I.put(str, str2);
        return true;
    }

    public Device f(String str) {
        List<Device> k2;
        Device device = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<Device> it = d().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Device next = it.next();
            if (next.mac != null && next.mac.equals(str)) {
                device = next;
                break;
            }
        }
        if (device != null || !this.g || (k2 = k()) == null) {
            return device;
        }
        for (Device next2 : k2) {
            if (next2.mac != null && next2.mac.equals(str)) {
                return next2;
            }
        }
        return device;
    }

    public List<Device> g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : d()) {
            if (!TextUtils.isEmpty(next.model) && next.model.equalsIgnoreCase(str) && next.isOwner()) {
                arrayList.add(next);
            }
        }
        for (Device next2 : k()) {
            if (!TextUtils.isEmpty(next2.model) && next2.model.equalsIgnoreCase(str) && next2.isOwner()) {
                boolean z2 = true;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (next2.did.equalsIgnoreCase(((Device) arrayList.get(i2)).did)) {
                        z2 = false;
                    }
                }
                if (z2) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }

    public boolean h(String str) {
        return (b(str) == null && l(str) == null) ? false : true;
    }

    public boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Device device : this.u) {
            if (TextUtils.equals(device.mac, str)) {
                return true;
            }
        }
        return false;
    }

    public List<Device> j(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : this.B) {
            if (next.isSubDevice() && next.parentId.equals(str)) {
                arrayList.add(next);
            }
        }
        return arrayList.isEmpty() ? k(str) : arrayList;
    }

    public List<Device> k(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        Device b2 = b(str);
        if (b2 == null || !b2.isHomeShared()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : this.A) {
            if (next.isSubDevice() && next.parentId.equals(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public Device l(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Device device = this.C.get(str);
        if (device == null || !device.isSubDevice()) {
            return m(str);
        }
        return device;
    }

    public Device m(String str) {
        Device device;
        if (!TextUtils.isEmpty(str) && (device = this.z.get(str)) != null && device.isSubDevice()) {
            return device;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.smarthome.device.Device n(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x000a
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x000a:
            com.xiaomi.smarthome.device.Device r0 = r1.b((java.lang.String) r2)     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0014
            com.xiaomi.smarthome.device.Device r0 = r1.l((java.lang.String) r2)     // Catch:{ all -> 0x0022 }
        L_0x0014:
            if (r0 != 0) goto L_0x001a
            com.xiaomi.smarthome.device.Device r0 = r1.a((java.lang.String) r2)     // Catch:{ all -> 0x0022 }
        L_0x001a:
            if (r0 != 0) goto L_0x0020
            com.xiaomi.smarthome.device.BleDevice r0 = com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager.c((java.lang.String) r2)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r1)
            return r0
        L_0x0022:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.SmartHomeDeviceManager.n(java.lang.String):com.xiaomi.smarthome.device.Device");
    }

    /* access modifiers changed from: private */
    public boolean a(Device device, Device device2) {
        if (device == null || device2 == null) {
            return false;
        }
        String str = device.model;
        String str2 = device2.model;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.length() != str2.length() + 4) {
            return false;
        }
        String[] split = str.split("vtl_");
        if (split.length != 2) {
            return false;
        }
        return str2.equals(split[0] + split[1]);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public void a(final boolean z2) {
        if (GlobalSetting.q || GlobalSetting.u) {
            LogUtilGrey.a(f14974a, "updateInnerDevice, isDeviceReady = " + z2 + Log.getStackTraceString(new Exception()));
        }
        AsyncTaskUtils.a(new AsyncTask<Void, Void, CacheDeviceData>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.xiaomi.smarthome.device.DeviceSearch} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: com.xiaomi.smarthome.device.MiioVirtualDeviceSearch} */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.xiaomi.smarthome.device.SmartHomeDeviceManager.CacheDeviceData doInBackground(java.lang.Void... r11) {
                /*
                    r10 = this;
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r11 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.util.List r11 = r11.r
                    java.util.Iterator r11 = r11.iterator()
                L_0x000a:
                    boolean r0 = r11.hasNext()
                    if (r0 == 0) goto L_0x001a
                    java.lang.Object r0 = r11.next()
                    com.xiaomi.smarthome.device.DeviceSearch r0 = (com.xiaomi.smarthome.device.DeviceSearch) r0
                    r0.b()
                    goto L_0x000a
                L_0x001a:
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager$CacheDeviceData r11 = new com.xiaomi.smarthome.device.SmartHomeDeviceManager$CacheDeviceData
                    r0 = 0
                    r11.<init>()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.lang.String r2 = "SmartHomeDeviceManager"
                    java.lang.String r3 = "updateInnerDevice doInBackground"
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.util.List r2 = r2.r
                    java.util.Iterator r2 = r2.iterator()
                    r3 = r0
                L_0x0037:
                    boolean r4 = r2.hasNext()
                    if (r4 == 0) goto L_0x005b
                    java.lang.Object r4 = r2.next()
                    com.xiaomi.smarthome.device.DeviceSearch r4 = (com.xiaomi.smarthome.device.DeviceSearch) r4
                    boolean r5 = r4 instanceof com.xiaomi.smarthome.device.MiioVirtualDeviceSearch
                    if (r5 == 0) goto L_0x004b
                    r3 = r4
                    com.xiaomi.smarthome.device.MiioVirtualDeviceSearch r3 = (com.xiaomi.smarthome.device.MiioVirtualDeviceSearch) r3
                    goto L_0x0037
                L_0x004b:
                    java.util.List r4 = r4.d()
                    if (r4 == 0) goto L_0x0037
                    int r5 = r4.size()
                    if (r5 <= 0) goto L_0x0037
                    r1.addAll(r4)
                    goto L_0x0037
                L_0x005b:
                    java.util.ArrayList r2 = new java.util.ArrayList
                    r2.<init>()
                    java.util.List r3 = r3.d()
                    r2.addAll(r3)
                    int r3 = r2.size()
                    r4 = 0
                    if (r3 <= 0) goto L_0x00a9
                    java.util.List<com.xiaomi.smarthome.device.Device> r3 = r11.c
                    r3.clear()
                    java.util.Iterator r2 = r2.iterator()
                L_0x0077:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L_0x00a9
                    java.lang.Object r3 = r2.next()
                    com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
                    if (r3 != 0) goto L_0x0086
                    goto L_0x0077
                L_0x0086:
                    java.util.Iterator r5 = r1.iterator()
                L_0x008a:
                    boolean r6 = r5.hasNext()
                    if (r6 == 0) goto L_0x00a0
                    java.lang.Object r6 = r5.next()
                    com.xiaomi.smarthome.device.Device r6 = (com.xiaomi.smarthome.device.Device) r6
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r7 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    boolean r6 = r7.a((com.xiaomi.smarthome.device.Device) r3, (com.xiaomi.smarthome.device.Device) r6)
                    if (r6 == 0) goto L_0x008a
                    r5 = 1
                    goto L_0x00a1
                L_0x00a0:
                    r5 = 0
                L_0x00a1:
                    if (r5 != 0) goto L_0x0077
                    java.util.List<com.xiaomi.smarthome.device.Device> r5 = r11.c
                    r5.add(r3)
                    goto L_0x0077
                L_0x00a9:
                    r11.f14995a = r1
                    java.util.ArrayList r2 = new java.util.ArrayList
                    r2.<init>()
                    java.util.ArrayList r3 = new java.util.ArrayList
                    r3.<init>()
                    java.util.ArrayList r5 = new java.util.ArrayList
                    r5.<init>()
                    java.util.Iterator r6 = r1.iterator()
                L_0x00be:
                    boolean r7 = r6.hasNext()
                    if (r7 == 0) goto L_0x010a
                    java.lang.Object r7 = r6.next()
                    com.xiaomi.smarthome.device.Device r7 = (com.xiaomi.smarthome.device.Device) r7
                    if (r7 != 0) goto L_0x00cd
                    goto L_0x00be
                L_0x00cd:
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.util.List r8 = r8.G
                    java.lang.String r9 = r7.did
                    boolean r8 = r8.contains(r9)
                    if (r8 == 0) goto L_0x00df
                    r5.add(r7)
                    goto L_0x00be
                L_0x00df:
                    boolean r8 = r7.isSubDevice()
                    if (r8 == 0) goto L_0x00e8
                    r3.add(r7)
                L_0x00e8:
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.util.Set r8 = r8.J
                    java.lang.String r9 = r7.did
                    boolean r8 = r8.contains(r9)
                    if (r8 == 0) goto L_0x00f7
                    goto L_0x00be
                L_0x00f7:
                    r2.add(r7)
                    boolean r8 = r7.isSubDevice()
                    if (r8 == 0) goto L_0x00be
                    boolean r8 = r7.isShowMainList()
                    if (r8 != 0) goto L_0x00be
                    r2.remove(r7)
                    goto L_0x00be
                L_0x010a:
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r6 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.util.List unused = r6.H = r5
                    r11.f14995a = r2
                    r11.b = r3
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager$CacheDeviceData r11 = r2.a((com.xiaomi.smarthome.device.SmartHomeDeviceManager.CacheDeviceData) r11)
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    boolean r2 = r2.A()
                    if (r2 == 0) goto L_0x0132
                    com.xiaomi.smarthome.lite.LiteUIConfigManager r2 = com.xiaomi.smarthome.lite.LiteUIConfigManager.a()
                    boolean r1 = r2.a((java.util.List<com.xiaomi.smarthome.device.Device>) r1)
                    if (r1 == 0) goto L_0x0132
                    com.xiaomi.smarthome.lite.LiteUIConfigManager r1 = com.xiaomi.smarthome.lite.LiteUIConfigManager.a()
                    r1.e()
                L_0x0132:
                    java.lang.String r1 = "SmartHomeDeviceManager"
                    java.lang.String r2 = "updateInnerDevice doInBackground 556"
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r2)
                    com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                    java.util.List<com.xiaomi.smarthome.device.Device> r2 = r11.f14995a
                    r1.b((java.util.List<com.xiaomi.smarthome.device.Device>) r2)
                    com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                    java.util.List<com.xiaomi.smarthome.device.Device> r2 = r11.b
                    r1.c((java.util.List<com.xiaomi.smarthome.device.Device>) r2)
                    com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                    java.util.List<com.xiaomi.smarthome.device.Device> r2 = r11.f14995a
                    r1.d(r2)
                    java.lang.String r1 = "SmartHomeDeviceManager"
                    java.lang.String r2 = "updateInnerDevice doInBackground 561"
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r2)
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    boolean r1 = r1.A()
                    if (r1 != 0) goto L_0x01bc
                    com.xiaomi.smarthome.miio.page.DeviceGroupManager r1 = com.xiaomi.smarthome.miio.page.DeviceGroupManager.a()
                    java.util.List<com.xiaomi.smarthome.device.Device> r2 = r11.f14995a
                    java.util.List r1 = r1.b((java.util.List<com.xiaomi.smarthome.device.Device>) r2)
                    r11.d = r1
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.lang.String r1 = r1.x()
                    boolean r1 = android.text.TextUtils.isEmpty(r1)
                    if (r1 != 0) goto L_0x01c8
                    java.util.List<com.xiaomi.smarthome.miio.page.DeviceGroup> r1 = r11.d
                    java.util.Iterator r1 = r1.iterator()
                L_0x0181:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L_0x01c8
                    java.lang.Object r2 = r1.next()
                    com.xiaomi.smarthome.miio.page.DeviceGroup r2 = (com.xiaomi.smarthome.miio.page.DeviceGroup) r2
                    java.util.List<com.xiaomi.smarthome.device.Device> r3 = r2.c
                    java.util.Iterator r3 = r3.iterator()
                L_0x0193:
                    boolean r5 = r3.hasNext()
                    if (r5 == 0) goto L_0x01ae
                    java.lang.Object r5 = r3.next()
                    com.xiaomi.smarthome.device.Device r5 = (com.xiaomi.smarthome.device.Device) r5
                    java.lang.String r6 = r5.did
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r7 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.this
                    java.lang.String r7 = r7.x()
                    boolean r6 = r6.equals(r7)
                    if (r6 == 0) goto L_0x0193
                    goto L_0x01af
                L_0x01ae:
                    r5 = r0
                L_0x01af:
                    if (r5 == 0) goto L_0x0181
                    java.util.List<com.xiaomi.smarthome.device.Device> r3 = r2.c
                    r3.remove(r5)
                    java.util.List<com.xiaomi.smarthome.device.Device> r2 = r2.c
                    r2.add(r4, r5)
                    goto L_0x0181
                L_0x01bc:
                    com.xiaomi.smarthome.lite.LiteDeviceManager r0 = com.xiaomi.smarthome.lite.LiteDeviceManager.a()
                    java.util.List<com.xiaomi.smarthome.device.Device> r1 = r11.b
                    java.util.List r0 = r0.d(r1)
                    r11.e = r0
                L_0x01c8:
                    java.lang.String r0 = "SmartHomeDeviceManager"
                    java.lang.String r1 = "updateInnerDevice doInBackground 586"
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.SmartHomeDeviceManager.AnonymousClass2.doInBackground(java.lang.Void[]):com.xiaomi.smarthome.device.SmartHomeDeviceManager$CacheDeviceData");
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(CacheDeviceData cacheDeviceData) {
                SmartHomeDeviceManager.this.p.removeCallbacks(SmartHomeDeviceManager.this.N);
                List unused = SmartHomeDeviceManager.this.w = cacheDeviceData.f14995a;
                List unused2 = SmartHomeDeviceManager.this.B = cacheDeviceData.b;
                List unused3 = SmartHomeDeviceManager.this.D = cacheDeviceData.c;
                List unused4 = SmartHomeDeviceManager.this.y = cacheDeviceData.f;
                List unused5 = SmartHomeDeviceManager.this.A = cacheDeviceData.g;
                SmartHomeDeviceManager.this.x.clear();
                for (Device device : SmartHomeDeviceManager.this.w) {
                    SmartHomeDeviceManager.this.x.put(device.did, device);
                }
                for (Device device2 : SmartHomeDeviceManager.this.y) {
                    SmartHomeDeviceManager.this.z.put(device2.did, device2);
                }
                SmartHomeDeviceManager.this.C.clear();
                for (Device device3 : SmartHomeDeviceManager.this.B) {
                    SmartHomeDeviceManager.this.C.put(device3.did, device3);
                }
                SmartHomeDeviceManager.this.C();
                if (!SmartHomeDeviceManager.this.A()) {
                    DeviceGroupManager.a().c(cacheDeviceData.d);
                } else {
                    LiteDeviceManager.a().a(cacheDeviceData.e);
                }
                SmartHomeDeviceManager.this.j.compareAndSet(true, false);
                if (z2) {
                    SmartHomeDeviceManager.this.k.compareAndSet(false, true);
                    synchronized (SmartHomeDeviceManager.this.t) {
                        for (IsDeviceReadyCallback onDeviceReady : SmartHomeDeviceManager.this.t) {
                            onDeviceReady.onDeviceReady(SmartHomeDeviceManager.this.w);
                        }
                        SmartHomeDeviceManager.this.t.clear();
                    }
                }
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "final devices to be notified size = " + SmartHomeDeviceManager.this.w.size() + ", isDeviceReady = " + z2 + "  mIsRefreshing:" + SmartHomeDeviceManager.this.j, true);
                if (SmartHomeDeviceManager.this.w.size() == 0) {
                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "final devices to be notified size = " + SmartHomeDeviceManager.this.w.size() + ", core ready=" + CoreApi.a().l(), true);
                }
                synchronized (SmartHomeDeviceManager.this.s) {
                    for (int i = 0; i < SmartHomeDeviceManager.this.s.size(); i++) {
                        if (SmartHomeDeviceManager.this.s.get(i) != null) {
                            ((IClientDeviceListener) SmartHomeDeviceManager.this.s.get(i)).a(3);
                        }
                    }
                }
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public CacheDeviceData a(CacheDeviceData cacheDeviceData) {
        if (cacheDeviceData == null) {
            return cacheDeviceData;
        }
        List<Device> list = cacheDeviceData.f14995a;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        if (list != null && !list.isEmpty()) {
            int i3 = 0;
            while (i3 < list.size()) {
                Device device = list.get(i3);
                if (device != null && device.isHomeShared()) {
                    arrayList.add(device);
                    list.remove(i3);
                    i3--;
                }
                i3++;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        List<Device> list2 = cacheDeviceData.b;
        if (list2 != null && !list2.isEmpty()) {
            while (i2 < list2.size()) {
                Device device2 = list2.get(i2);
                if (device2 != null && device2.isHomeShared()) {
                    arrayList.add(device2);
                    arrayList2.add(device2);
                    list2.remove(i2);
                    i2--;
                }
                i2++;
            }
        }
        cacheDeviceData.f = arrayList;
        cacheDeviceData.g = arrayList2;
        return cacheDeviceData;
    }

    /* access modifiers changed from: private */
    public void C() {
        ArrayList arrayList = new ArrayList(this.w);
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.w.size(); i2++) {
            Device device = this.w.get(i2);
            if (device != null) {
                hashSet.add(device.did);
            }
        }
        for (int i3 = 0; i3 < this.B.size(); i3++) {
            Device device2 = this.B.get(i3);
            if (device2 != null && hashSet.add(device2.did)) {
                arrayList.add(device2);
            }
        }
        this.u = arrayList;
        this.v.clear();
        for (Device next : this.u) {
            this.v.put(next.did, next);
        }
        this.z.clear();
        for (Device next2 : this.y) {
            this.z.put(next2.did, next2);
        }
    }

    public void b(Device device) {
        if (device != null) {
            LogUtilGrey.a(f14974a, "addDevice: name = " + device.name + ", did = " + device.did + ", model = " + device.model + ", mac = " + device.mac);
            for (DeviceSearch<?> a2 : this.r) {
                a2.a(device);
            }
            Device put = this.v.put(device.did, device);
            if (put != null) {
                this.u.remove(put);
            }
            this.u.add(device);
            Device put2 = this.x.put(device.did, device);
            if (put2 != null) {
                this.w.remove(put2);
            }
            this.w.add(device);
            if (device.isSubDevice()) {
                Device put3 = this.C.put(device.did, device);
                if (put3 != null) {
                    this.B.remove(put3);
                }
                this.B.add(device);
            }
            this.p.post(new Runnable() {
                public void run() {
                    SmartHomeDeviceManager.this.s();
                    SmartHomeDeviceManager.this.a(true);
                }
            });
        }
    }

    public void a(List<Device> list) {
        if (list != null) {
            LogUtilGrey.a(f14974a, "addDevices");
            if (list != null) {
                for (Device next : list) {
                    for (DeviceSearch<?> a2 : this.r) {
                        a2.a(next);
                    }
                }
                this.p.post(new Runnable() {
                    public void run() {
                        SmartHomeDeviceManager.this.s();
                        SmartHomeDeviceManager.this.a(true);
                    }
                });
            }
        }
    }

    public void c(Device device) {
        if (device != null) {
            LogUtilGrey.a(f14974a, "delDevice: name = " + device.name + ", did = " + device.did + ", model = " + device.model + ", mac = " + device.mac);
            for (DeviceSearch next : this.r) {
                for (int i2 = 0; i2 < this.B.size(); i2++) {
                    if (TextUtils.equals(device.did, this.B.get(i2).parentId)) {
                        next.b(this.B.get(i2));
                    }
                }
                next.b(device);
            }
            this.p.post(new Runnable() {
                public void run() {
                    SmartHomeDeviceManager.this.a(true);
                }
            });
        }
    }

    public void a(List<String> list, Context context, final IDelDeviceBatchCallback iDelDeviceBatchCallback) {
        if (list != null) {
            if (TextUtils.equals(CoreApi.a().s(), "923522198")) {
                int i2 = 0;
                while (i2 < list.size()) {
                    if (!TextUtils.equals(list.get(i2), "658906")) {
                        i2++;
                    } else if (iDelDeviceBatchCallback != null) {
                        iDelDeviceBatchCallback.a(new Error(-1, "Can't delete test device"));
                        return;
                    } else {
                        return;
                    }
                }
            }
            LogUtilGrey.a(f14974a, "delDeviceBatch");
            final ArrayList arrayList = new ArrayList();
            int i3 = 0;
            while (i3 < list.size()) {
                if (list.get(i3).contains("config") || list.get(i3).contains("xiaofang")) {
                    arrayList.add(list.remove(i3));
                    i3--;
                }
                i3++;
            }
            for (String next : list) {
                Device b2 = a().b(next);
                if (b2 != null) {
                    if (next.startsWith(TemporaryDevice.f13574a)) {
                        TemporaryDeviceSearch.a().a(b2.model, false);
                    } else {
                        if (b2 instanceof MiTVDevice) {
                            MitvDeviceManager.b().b(next);
                        }
                        if (b2 instanceof GeneralAPDevice) {
                            GeneralAPDeviceSearch.a().b(b2);
                        }
                    }
                }
            }
            CoreApi.a().b(list, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            Iterator<String> keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                int optInt = jSONObject.optInt(next);
                                Device b2 = SmartHomeDeviceManager.a().b(next);
                                if (b2 != null) {
                                    if (optInt == 1) {
                                        b2.setUnbind();
                                        if (b2 instanceof MiTVDevice) {
                                            MitvDeviceManager.b().b(next);
                                        } else if (b2 instanceof BleDevice) {
                                            BLEDeviceManager.a((BleDevice) b2);
                                        } else if (b2 instanceof BleMeshDevice) {
                                            BLEDeviceManager.a(b2);
                                        } else if (b2 instanceof RouterDevice) {
                                            if (b2.isOwner()) {
                                                b2.setOwner(false);
                                            } else if (b2.isShared() || b2.isFamily()) {
                                                b2.setShared(false);
                                            }
                                            String str2 = b2.did;
                                            if (str2.startsWith("miwifi.")) {
                                                str2 = str2.substring(7);
                                            }
                                            Intent intent = new Intent("com.xiaomi.router");
                                            intent.putExtra(HomeManager.v, "delete");
                                            intent.putExtra("userId", CoreApi.a().s());
                                            intent.putExtra("id", str2);
                                            SmartHomeDeviceManager.this.q.sendBroadcast(intent);
                                        }
                                        SmartHomeDeviceManager.a().c(b2);
                                    } else if (optInt == 0) {
                                        if (b2 != null && (b2 instanceof BleDevice)) {
                                            BLEDeviceManager.a((BleDevice) b2);
                                        } else if (b2 instanceof BleMeshDevice) {
                                            BLEDeviceManager.a(b2);
                                        }
                                    }
                                }
                            }
                            for (String b3 : arrayList) {
                                Device b4 = SmartHomeDeviceManager.this.b(b3);
                                if (b4 != null) {
                                    SmartHomeDeviceManager.this.c(b4);
                                }
                            }
                            if (iDelDeviceBatchCallback != null) {
                                iDelDeviceBatchCallback.a();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (iDelDeviceBatchCallback != null) {
                        iDelDeviceBatchCallback.a(error);
                    }
                }
            });
        }
    }

    public void o() {
        for (DeviceSearch next : this.r) {
            next.a(false);
            next.c();
        }
    }

    private void D() {
        if (GlobalSetting.u) {
            LogUtilGrey.a(f14974a, "updateSearchList start" + Log.getStackTraceString(new Exception()));
        }
        AsyncTaskUtils.a(new AsyncTask<Void, Void, List<Device>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public List<Device> doInBackground(Void... voidArr) {
                String str;
                Integer num;
                ArrayList arrayList = new ArrayList();
                if (SmartHomeDeviceManager.this.E != null) {
                    try {
                        LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "updateSearchList mCacheDeviceList size=" + SmartHomeDeviceManager.this.E.size());
                        ProcessUtil.b(CommonApplication.getAppContext());
                        for (Device device : SmartHomeDeviceManager.this.E) {
                            Device a2 = DeviceFactory.a(device);
                            if (a2 != null) {
                                arrayList.add(a2);
                            } else if (GlobalSetting.q || GlobalSetting.u) {
                                try {
                                    List<PluginRecord> O = CoreApi.a().O();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("updateSearchList DeviceFactory.createDevice return null,");
                                    if (device == null) {
                                        str = "null";
                                    } else {
                                        str = device.l() + ":" + device.k();
                                    }
                                    sb.append(str);
                                    sb.append(",pluginrecord size=");
                                    if (O == null) {
                                        num = null;
                                    } else {
                                        num = Integer.valueOf(O.size());
                                    }
                                    sb.append(num);
                                    sb.append(",isInMainProcess=");
                                    sb.append(CoreApi.a().m);
                                    sb.append(",PluginManager mPluginRecordMap:");
                                    sb.append(PluginManager.a().o.size());
                                    sb.append(",CoreApi mPluginRecordMapCache:");
                                    sb.append(CoreApi.a().n.size());
                                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, sb.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "updateSearchList doInBackground exception!! " + e2.getMessage() + ",result size" + arrayList.size());
                    }
                }
                return arrayList;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(List<Device> list) {
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "updateSearchList onPostExecute devices size=" + list.size());
                for (DeviceSearch deviceSearch : SmartHomeDeviceManager.this.r) {
                    ArrayList arrayList = new ArrayList();
                    int g = deviceSearch.g();
                    for (Device next : list) {
                        if (g == next.pid) {
                            arrayList.add(next);
                        }
                    }
                    deviceSearch.a(true);
                    deviceSearch.b(arrayList, DeviceSearch.REMOTESTATE.REMOTE_SUCCESS);
                }
                SmartHomeDeviceHelper.a().i();
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "updateSearchList finish, mForceUpdate = " + SmartHomeDeviceManager.this.l + ", mForceUpdateCache = " + SmartHomeDeviceManager.this.m);
                if (SmartHomeDeviceManager.this.l) {
                    boolean unused = SmartHomeDeviceManager.this.l = false;
                    boolean unused2 = SmartHomeDeviceManager.this.m = false;
                    SmartHomeDeviceManager.this.p();
                } else if (SmartHomeDeviceManager.this.m) {
                    boolean unused3 = SmartHomeDeviceManager.this.m = false;
                    SmartHomeDeviceManager.this.q();
                }
                SmartHomeDeviceManager.this.a(true);
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void b(final boolean z2) {
        LogUtilGrey.a(f14974a, "scanDeviceList, useCache = " + z2 + ", mCacheDevicesDirty = " + this.n);
        for (DeviceSearch<?> h2 : this.r) {
            h2.h();
        }
        if (!z2 || this.n) {
            this.M = System.currentTimeMillis();
            DeviceManager.a().a(ScanType.ALL, (IClientCallback) new IClientCallback() {
                public IBinder asBinder() {
                    return null;
                }

                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(ScanState.class.getClassLoader());
                    int i = bundle.getInt("result");
                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "scanDeviceList onSuccess result=" + i, true);
                    AppStateNotifier stateNotifier = ServiceApplication.getStateNotifier();
                    if (stateNotifier == null || stateNotifier.a() != 4) {
                        SmartHomeDeviceManager.this.E();
                        return;
                    }
                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "scanDeviceList onSucces, result = " + i);
                    if (i == ScanState.LOAD_CACHE_SUCCESS.ordinal() || i == ScanState.SYNC_SERVER_SUCCESS.ordinal() || i == ScanState.SCAN_LOCAL_SUCCESS.ordinal()) {
                        LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "scanDeviceList, useCache = " + z2 + ", mCacheDevicesDirty = " + SmartHomeDeviceManager.this.n);
                        StringBuilder sb = new StringBuilder();
                        sb.append("performance:scan complete takes ");
                        sb.append(System.currentTimeMillis() - SmartHomeDeviceManager.this.M);
                        sb.append(d.H);
                        LogUtilGrey.a(SmartHomeDeviceManager.f14974a, sb.toString());
                        SmartHomeDeviceManager.this.F();
                    } else if (i == ScanState.SYNC_SERVER_FAILED.ordinal()) {
                        SmartHomeDeviceManager.this.E();
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "scanDeviceList onFailure, error = " + error2);
                    EventDispatcher.a(1);
                    SmartHomeDeviceManager.this.p.post(new Runnable() {
                        public void run() {
                            SmartHomeDeviceManager.this.E();
                        }
                    });
                }
            });
            return;
        }
        D();
    }

    /* access modifiers changed from: private */
    public void E() {
        this.p.post(new Runnable() {
            public void run() {
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "getRemoteFailed");
                for (DeviceSearch deviceSearch : SmartHomeDeviceManager.this.r) {
                    deviceSearch.a(true);
                    deviceSearch.b(new ArrayList(), DeviceSearch.REMOTESTATE.REMOTE_FAILED);
                }
                SmartHomeDeviceManager.this.a(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void F() {
        if (GlobalSetting.u) {
            LogUtilGrey.a(f14974a, "getDeviceListFromCore start" + Log.getStackTraceString(new Exception()));
        }
        ArrayList<Device> c2 = DeviceManager.a().c();
        StringBuilder sb = new StringBuilder();
        sb.append("getDeviceList onSuccess: ");
        sb.append(c2.size() == 0 ? 0 : c2.size());
        LogUtilGrey.a(f14974a, sb.toString(), true);
        LogUtilGrey.a(f14974a, "performance:get device from core costs " + (System.currentTimeMillis() - this.M) + d.H);
        this.n = false;
        this.E = c2;
        ArrayList arrayList = new ArrayList();
        Iterator<Device> it = c2.iterator();
        while (it.hasNext()) {
            Device next = it.next();
            if (next == null) {
                LogUtilGrey.a(f14974a, "getDeviceListFromCore device is null");
            } else if (next.f() == Device.PID_VIRTUAL_GROUP) {
                arrayList.add(next);
            }
            if (GlobalSetting.u && next != null) {
                LogUtilGrey.a(f14974a, "getDeviceListFromCore device: [ name = " + next.m() + " , did = " + next.k() + " ,model = " + next.l() + " ]");
            }
        }
        b((List<Device>) arrayList);
        D();
    }

    private void b(List<Device> list) {
        if (list == null || list.size() == 0) {
            this.G = new ArrayList();
            this.H = new ArrayList();
            this.F = new HashMap();
            this.I = new HashMap();
            this.J = new HashSet();
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < list.size(); i2++) {
            try {
                String G2 = list.get(i2).G();
                if (G2 != null) {
                    JSONObjectSafe d2 = new JSONObjectSafe(G2).optJSONObject("member_list");
                    String optString = d2.optString("group_did");
                    String optString2 = d2.optString("status");
                    JSONObjectSafe d3 = d2.optJSONObject("member_ship");
                    if (d3 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<String> keys = d3.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if (next.startsWith("group")) {
                                hashSet.add(next);
                            } else {
                                if (!arrayList.contains(next)) {
                                    arrayList.add(next);
                                }
                                arrayList2.add(next);
                            }
                        }
                        hashMap.put(optString, arrayList2);
                        hashMap2.put(optString, optString2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.G = arrayList;
        this.F = hashMap;
        this.I = hashMap2;
        this.J = hashSet;
        for (String next2 : this.J) {
            this.F.remove(next2);
            this.I.remove(next2);
        }
    }

    /* access modifiers changed from: private */
    public void c(final boolean z2) {
        LogUtilGrey.a(f14974a, "updateDevice useCache: " + z2 + " mIsRefreshing:" + this.j, true);
        CommonApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                CoreApi.a().a(SmartHomeDeviceManager.this.q, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        if (ServiceApplication.getStateNotifier().a() != 4) {
                            LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "not login clear useCache: " + z2);
                            SmartHomeDeviceManager.this.v();
                            SmartHomeDeviceManager.this.p.post(new Runnable() {
                                public void run() {
                                    SmartHomeDeviceManager.this.a(false);
                                }
                            });
                        } else if (!SmartHomeDeviceManager.this.j.get()) {
                            SmartHomeDeviceManager.this.j.compareAndSet(false, true);
                            SmartHomeDeviceManager.this.p.removeCallbacks(SmartHomeDeviceManager.this.N);
                            SmartHomeDeviceManager.this.p.postDelayed(SmartHomeDeviceManager.this.N, 5000);
                            LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "updateDevice isPluginCacheReady", true);
                            CoreApi.a().a(SmartHomeDeviceManager.this.q, (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                                public void onPluginCacheReady() {
                                    List<PluginRecord> O = CoreApi.a().O();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("updateDevice onPluginCacheReady callback in plugrecord size = ");
                                    sb.append(O == null ? null : Integer.valueOf(O.size()));
                                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, sb.toString(), true);
                                    if (!z2) {
                                        MitvDeviceManager.b().a(false);
                                        LiveCameraDeviceManager.instance().update();
                                    }
                                    SearchCameraDevice.getInstance().startSearch((SearchCameraDevice.DeviceListener) null);
                                    SmartHomeDeviceManager.this.o();
                                    SmartHomeDeviceManager.this.b(z2);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void p() {
        AppStateNotifier stateNotifier = ServiceApplication.getStateNotifier();
        if (stateNotifier == null) {
            LogUtilGrey.a(f14974a, "updateDeviceRemote notifier is null");
            return;
        }
        LogUtilGrey.a(f14974a, "updateDeviceRemote login state=" + stateNotifier.a());
        stateNotifier.a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
            public void a() {
                SmartHomeDeviceManager.this.c(false);
            }

            public void b() {
                SmartHomeDeviceManager.this.c(false);
            }
        });
    }

    public void q() {
        LogUtilGrey.a(f14974a, "updateDeviceByCache");
        c(true);
    }

    public void r() {
        LogUtilGrey.a(f14974a, "forceUpdateDeviceByCache");
        if (this.j.get()) {
            this.m = true;
        } else {
            q();
        }
    }

    public void s() {
        LogUtilGrey.a(f14974a, "forceUpdateDeviceRemote " + this.j.get());
        if (this.j.get()) {
            this.l = true;
        } else {
            p();
        }
    }

    public boolean t() {
        return this.j.get();
    }

    public boolean u() {
        return this.k.get();
    }

    public void v() {
        LogUtilGrey.a(f14974a, "clear");
        this.k.compareAndSet(true, false);
        this.w.clear();
        this.x.clear();
        this.B.clear();
        this.C.clear();
        this.D.clear();
        this.u.clear();
        this.v.clear();
        this.z.clear();
        LiteDeviceManager.a().e();
        for (DeviceSearch<?> e2 : this.r) {
            e2.e();
        }
    }

    public void a(final boolean z2, final String str, Context context, final AsyncResponseCallback<Void> asyncResponseCallback) {
        DevicelibApi.setShowMode(context, str, z2 ? 1 : 0, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                Device l;
                if (bool.booleanValue() && (l = SmartHomeDeviceManager.this.l(str)) != null) {
                    SmartHomeDeviceManager.this.c(l);
                    l.showMode = z2;
                    SmartHomeDeviceManager.this.b(l);
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(null);
                        return;
                    }
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                }
            }

            public void onFailure(Error error) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a());
                }
            }
        });
    }

    public void a(final boolean z2, final Set<String> set, Context context, final AsyncResponseCallback<Void> asyncResponseCallback) {
        DevicelibApi.setShowMode(context, set, z2 ? 1 : 0, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue()) {
                    for (String l : set) {
                        Device l2 = SmartHomeDeviceManager.this.l(l);
                        if (l2 != null) {
                            for (DeviceSearch b2 : SmartHomeDeviceManager.this.r) {
                                b2.b(l2);
                            }
                            l2.showMode = z2;
                            for (DeviceSearch a2 : SmartHomeDeviceManager.this.r) {
                                a2.a(l2);
                            }
                        }
                    }
                    SmartHomeDeviceManager.this.p.post(new Runnable() {
                        public void run() {
                            SmartHomeDeviceManager.this.s();
                            SmartHomeDeviceManager.this.a(true);
                        }
                    });
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(null);
                }
            }

            public void onFailure(Error error) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a());
                }
            }
        });
    }

    public boolean a(Device device, int i2, int i3) {
        if (device == null || i2 == i3 || i2 == -1 || i3 == -1 || this.w == null || i2 >= this.w.size() || i3 >= this.w.size() || !device.equals(this.w.get(i2))) {
            return false;
        }
        this.w.remove(i2);
        this.w.add(i3, device);
        DeviceGroupManager.a().a(this.w);
        DeviceSortUtil.b(DeviceGroupManager.a().c());
        DeviceSortUtil.b();
        SmartHomeDeviceHelper.a().d(this.w);
        return true;
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null) {
            LogUtilGrey.a(f14974a, "setCacheDirty");
            Intent intent = new Intent();
            intent.setAction(AppConstants.F);
            intent.putStringArrayListExtra(AppConstants.E, arrayList);
            LocalBroadcastManager.getInstance(this.q).sendBroadcast(intent);
        }
    }

    public void a(List<String> list, final AsyncCallback<List<Device>, Error> asyncCallback) {
        if (list != null) {
            LogUtilGrey.a(f14974a, "updateDeviceBatch");
            CoreApi.a().c(list, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    List g = SmartHomeDeviceManager.this.c(list);
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(g);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public List<Device> c(List<Device> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : list) {
            Device b2 = b(next.k());
            if (b2 == null) {
                b2 = l(next.k());
            }
            if (b2 != null) {
                DeviceFactory.a(b2, next);
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    public int w() {
        if (!CoreApi.a().q()) {
            return 0;
        }
        int size = this.w.size();
        for (Device d2 : this.w) {
            if (!d(d2)) {
                size--;
            }
        }
        int size2 = size + this.B.size();
        if (size2 > 0) {
            return size2;
        }
        return 0;
    }

    public static boolean d(Device device) {
        if (device == null || device.pid == Device.PID_VIRTUAL_DEVICE || ((device.isSubDevice() && device.isShowMainList()) || (device instanceof PhoneIRDevice))) {
            return false;
        }
        return true;
    }

    public void a(final IsDeviceReadyCallback isDeviceReadyCallback) {
        if (isDeviceReadyCallback != null) {
            if (this.k.get()) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isDeviceReadyCallback.onDeviceReady(this.w);
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isDeviceReadyCallback.onDeviceReady(SmartHomeDeviceManager.this.w);
                        }
                    });
                }
            } else if (this.j.get()) {
                synchronized (this.t) {
                    this.t.add(isDeviceReadyCallback);
                }
            } else {
                p();
                synchronized (this.t) {
                    this.t.add(isDeviceReadyCallback);
                }
            }
        }
    }

    public DeviceStat o(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Device b2 = b(str);
        if (b2 == null) {
            b2 = l(str);
        }
        if (b2 != null) {
            return b2.newDeviceStat();
        }
        return null;
    }

    public static boolean e(Device device) {
        if (device == null || device.isOwner() || IRDeviceUtil.a(device.did) || device.isVirtualDevice() || (device instanceof MiTVDevice) || (device instanceof RouterDevice) || (device instanceof GeneralAPDevice)) {
            return false;
        }
        if (!(device instanceof BleDevice) || !((BleDevice) device).k()) {
            return true;
        }
        return false;
    }

    public static boolean f(Device device) {
        if ((device instanceof MiTVDevice) && !device.isOwner()) {
            return true;
        }
        if (((device instanceof RouterDevice) && !device.isOwner()) || (device instanceof GeneralAPDevice)) {
            return true;
        }
        if ((!(device instanceof BleDevice) || !((BleDevice) device).k()) && !(device instanceof WatchBandDevice)) {
            return false;
        }
        return true;
    }

    private static class MyBroadcastReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<SmartHomeDeviceManager> f14996a;

        public MyBroadcastReceiver(SmartHomeDeviceManager smartHomeDeviceManager) {
            this.f14996a = new WeakReference<>(smartHomeDeviceManager);
        }

        public void onReceive(Context context, Intent intent) {
            LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "MyBroadcastReceiver onReceive in");
            if (this.f14996a != null) {
                LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "MyBroadcastReceiver onReceive 1430");
                final SmartHomeDeviceManager smartHomeDeviceManager = (SmartHomeDeviceManager) this.f14996a.get();
                if (smartHomeDeviceManager != null) {
                    LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "MyBroadcastReceiver onReceive 1435");
                    if (ServiceApplication.getStateNotifier().a() == 4) {
                        LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "MyBroadcastReceiver onReceive 1437");
                        if (ClientApiStub.ACTION_UPDATE_DEVICE_LIST.equals(intent.getAction())) {
                            LogUtilGrey.a(SmartHomeDeviceManager.f14974a, "MyBroadcastReceiver onReceive receive ACTION_UPDATE_DEVICE_LIST");
                            CoreApi.a().a(ServiceApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                                public void onCoreReady() {
                                    smartHomeDeviceManager.F();
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public String x() {
        return this.L;
    }

    public String y() {
        return this.K;
    }

    public void z() {
        this.K = null;
    }

    public void p(String str) {
        this.K = str;
        this.L = str;
    }

    public void q(String str) {
        this.L = str;
    }
}
