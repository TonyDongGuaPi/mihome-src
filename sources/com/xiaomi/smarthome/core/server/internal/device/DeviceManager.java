package com.xiaomi.smarthome.core.server.internal.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.MiTVDevice;
import com.xiaomi.smarthome.core.entity.device.RouterDevice;
import com.xiaomi.smarthome.core.entity.device.ScanState;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.entity.message.CoreMessageType;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothService;
import com.xiaomi.smarthome.core.server.internal.device.DeviceSearch;
import com.xiaomi.smarthome.core.server.internal.device.DiscoverManager;
import com.xiaomi.smarthome.core.server.internal.device.api.DeviceApiInternal;
import com.xiaomi.smarthome.core.server.internal.device.api.DeviceListApi;
import com.xiaomi.smarthome.core.server.internal.device.api.RouterRemoteApiInternal;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.network.NetworkManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceManager implements DiscoverManager.DeviceDiscoverListener, NetworkManager.NetworkListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14505a = 16;
    private static final String b = "DeviceManager";
    private static final String c = "DiscoverManager.DEVICE_MANAGER_SARE";
    private static final String d = "DiscoverManager.DEVICE_CACHE_READY39";
    private static DeviceManager e = null;
    private static Object f = new Object();
    private static final int w = 1;
    private static final int x = 2;
    private static final int y = 3;
    private boolean A = false;
    private DiscoverManager.DeviceDiscoverListener g = new DiscoverManager.DeviceDiscoverListener() {
        public void a(Device device) {
            DeviceManager.this.p();
        }

        public void b(Device device) {
            DeviceManager.this.p();
        }
    };
    /* access modifiers changed from: private */
    public Handler h = null;
    private boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public NetHandle l = null;
    private volatile List<Device> m = new ArrayList();
    /* access modifiers changed from: private */
    public volatile List<Device> n = new ArrayList();
    private List<DeviceSearch> o = new ArrayList();
    private ExecutorService p;
    private List<IClientCallback> q = new ArrayList();
    private List<IClientCallback> r = new ArrayList();
    private boolean s = false;
    private boolean t = false;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public boolean v = false;
    /* access modifiers changed from: private */
    public int z = 0;

    private void j() {
    }

    private DeviceManager() {
        this.o.add(new MiioDeviceSearch(CoreService.getAppContext()));
        this.o.add(new MiTVMiWIFIDeviceSearch());
        MitvLocalDeviceManager.a().a(this.g);
        this.o.add(BluetoothDeviceSearch.a());
        this.o.add(new UPnPDeviceSearch());
        this.p = Executors.newCachedThreadPool();
        this.h = new Handler(Looper.myLooper());
        DiscoverManager.a().b();
        LocalRouterDeviceSearch.a().b();
        BluetoothService.a();
        b((IClientCallback) null);
    }

    public static DeviceManager a() {
        if (e == null) {
            synchronized (f) {
                if (e == null) {
                    e = new DeviceManager();
                }
            }
        }
        return e;
    }

    public void a(ScanType scanType, IClientCallback iClientCallback) {
        CoreLogUtilGrey.a(b, "scanDeviceList in");
        NetworkManager.a().a((NetworkManager.NetworkListener) this);
        if (scanType.ordinal() == ScanType.BLUETOOTH.ordinal()) {
            a(scanType);
        } else if (a(scanType, iClientCallback, true)) {
            a(ScanState.START_SUCCESS);
        }
    }

    private boolean a(ScanType scanType, IClientCallback iClientCallback, boolean z2) {
        this.t = z2 | this.t;
        CoreLogUtilGrey.a(b, "doScanDeviceList mScanLocal=" + this.t + ",mIsWorking=" + this.i);
        if (this.i) {
            if (iClientCallback != null) {
                if (this.v) {
                    a(ScanState.LOAD_CACHE_SUCCESS, iClientCallback);
                }
                this.q.add(iClientCallback);
                if (this.v) {
                    a(ScanState.LOAD_CACHE_SUCCESS);
                }
            } else {
                this.s = true;
            }
            return false;
        }
        this.r.clear();
        this.r.addAll(this.q);
        if (iClientCallback != null) {
            this.r.add(iClientCallback);
        }
        if (this.r.isEmpty() && !this.s) {
            return false;
        }
        this.q.clear();
        this.s = false;
        this.i = true;
        this.z = 0;
        CoreLogUtilGrey.a(b, "doScanDeviceList 176");
        if (iClientCallback != null || !this.r.isEmpty()) {
            if (GlobalDynamicSettingManager.a().e()) {
                DiscoverManager.a().e();
                DiscoverManager.a().d();
            } else if (this.A) {
                DiscoverManager.a().c();
            }
            LocalRouterDeviceSearch.a().b();
        }
        CoreLogUtilGrey.a(b, "doScanDeviceList 187");
        b(iClientCallback);
        a(iClientCallback);
        if (this.t) {
            this.t = false;
            a(scanType);
        }
        CoreLogUtilGrey.a(b, "doScanDeviceList end");
        return true;
    }

    /* access modifiers changed from: private */
    public void a(ScanState scanState) {
        CoreLogUtilGrey.a(b, "notifyScanState state=" + scanState + ",mScanningState=" + this.z + ",mDownloaded=" + this.j + ",mWorkingList size=" + this.r.size());
        if (!(scanState == ScanState.LOAD_CACHE_SUCCESS && this.z == 3 && this.j)) {
            if (!this.r.isEmpty()) {
                for (int i2 = 0; i2 < this.r.size(); i2++) {
                    try {
                        IClientCallback iClientCallback = this.r.get(i2);
                        if (iClientCallback != null) {
                            a(scanState, iClientCallback);
                        }
                    } catch (IndexOutOfBoundsException e2) {
                        e2.printStackTrace();
                    }
                }
            } else {
                p();
            }
        }
        if (this.z == 3) {
            this.i = false;
            if (!this.q.isEmpty()) {
                a(ScanType.ALL, (IClientCallback) null);
            }
            this.r.clear();
        }
    }

    private void a(ScanState scanState, IClientCallback iClientCallback) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("result", scanState.ordinal());
            iClientCallback.onSuccess(bundle);
        } catch (RemoteException unused) {
        }
    }

    private void a(IClientCallback iClientCallback) {
        CoreLogUtilGrey.a(b, "downloadDeviceList in mIsDownloading=" + this.k);
        if (iClientCallback != null || !this.k) {
            this.k = true;
            JSONObject jSONObject = new JSONObject();
            try {
                String b2 = WifiUtil.b(CoreService.getAppContext());
                String c2 = WifiUtil.c(CoreService.getAppContext());
                if (!TextUtils.isEmpty(c2) && !TextUtils.isEmpty(b2) && !TextUtils.equals(b2, "02:00:00:00:00:00")) {
                    jSONObject.put(DeviceTagInterface.e, c2);
                    jSONObject.put("bssid", b2.toUpperCase());
                }
                jSONObject.put("getVirtualModel", true);
                jSONObject.put("getHuamiDevices", 1);
            } catch (JSONException unused) {
            }
            CoreLogUtilGrey.a(b, "downloadDeviceList issue device_list req");
            this.l = new DeviceListApi(CoreService.getAppContext()).a(jSONObject, (NetCallback<DeviceListApi.AllDeviceData, NetError>) new NetCallback<DeviceListApi.AllDeviceData, NetError>() {
                /* renamed from: a */
                public void b(DeviceListApi.AllDeviceData allDeviceData) {
                }

                /* renamed from: b */
                public void a(final DeviceListApi.AllDeviceData allDeviceData) {
                    CoreLogUtilGrey.a(DeviceManager.b, "downloadDeviceList device_list req onSuccess");
                    DeviceManager.this.h.post(new Runnable() {
                        public void run() {
                            NetHandle unused = DeviceManager.this.l = null;
                            boolean unused2 = DeviceManager.this.k = false;
                            if (DeviceManager.this.a(allDeviceData)) {
                                boolean unused3 = DeviceManager.this.j = true;
                                int unused4 = DeviceManager.this.z = DeviceManager.this.z | 2;
                                CoreLogUtilGrey.a(DeviceManager.b, "downloadDeviceList start notifyScanState SYNC_SERVER_SUCCESS");
                                DeviceManager.this.a(ScanState.SYNC_SERVER_SUCCESS);
                                return;
                            }
                            int unused5 = DeviceManager.this.z = DeviceManager.this.z | 2;
                            CoreLogUtilGrey.a(DeviceManager.b, "downloadDeviceList start notifyScanState SYNC_SERVER_FAILED");
                            DeviceManager.this.a(ScanState.SYNC_SERVER_FAILED);
                        }
                    });
                }

                public void a(NetError netError) {
                    CoreLogUtilGrey.a(DeviceManager.b, "downloadDeviceList device_list req onFailure");
                    DeviceManager.this.h.post(new Runnable() {
                        public void run() {
                            NetHandle unused = DeviceManager.this.l = null;
                            boolean unused2 = DeviceManager.this.k = false;
                            int unused3 = DeviceManager.this.z = DeviceManager.this.z | 2;
                            DeviceManager.this.a(ScanState.SYNC_SERVER_FAILED);
                        }
                    });
                }
            });
        }
    }

    private void a(ScanType scanType) {
        for (DeviceSearch a2 : this.o) {
            a2.a(scanType, new DeviceSearch.DeviceSearchCallback() {
                public void a(int i, Object obj) {
                }

                public void a(int i, final List<Device> list) {
                    DeviceManager.this.h.post(new Runnable() {
                        public void run() {
                            if (DeviceManager.this.a((List<Device>) list)) {
                                DeviceManager.this.d((List<Device>) list);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            boolean r1 = r7.j     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x0067
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r7.m     // Catch:{ all -> 0x0069 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0069 }
        L_0x0010:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0069 }
            if (r2 == 0) goto L_0x0067
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0069 }
            com.xiaomi.smarthome.core.entity.device.Device r2 = (com.xiaomi.smarthome.core.entity.device.Device) r2     // Catch:{ all -> 0x0069 }
            boolean r3 = r2 instanceof com.xiaomi.smarthome.core.entity.device.MiioDevice     // Catch:{ all -> 0x0069 }
            if (r3 != 0) goto L_0x0021
            goto L_0x0010
        L_0x0021:
            com.xiaomi.smarthome.core.entity.device.MiioDevice r2 = (com.xiaomi.smarthome.core.entity.device.MiioDevice) r2     // Catch:{ all -> 0x0069 }
            java.util.Iterator r3 = r8.iterator()     // Catch:{ all -> 0x0069 }
        L_0x0027:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0069 }
            if (r4 == 0) goto L_0x0010
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0069 }
            com.xiaomi.smarthome.core.entity.device.Device r4 = (com.xiaomi.smarthome.core.entity.device.Device) r4     // Catch:{ all -> 0x0069 }
            boolean r5 = r4 instanceof com.xiaomi.smarthome.core.entity.device.MiioDevice     // Catch:{ all -> 0x0069 }
            if (r5 != 0) goto L_0x0038
            goto L_0x0027
        L_0x0038:
            com.xiaomi.smarthome.core.entity.device.MiioDevice r4 = (com.xiaomi.smarthome.core.entity.device.MiioDevice) r4     // Catch:{ all -> 0x0069 }
            java.lang.String r5 = r2.k()     // Catch:{ all -> 0x0069 }
            if (r5 == 0) goto L_0x0027
            java.lang.String r5 = r4.k()     // Catch:{ all -> 0x0069 }
            if (r5 == 0) goto L_0x0027
            java.lang.String r5 = r2.k()     // Catch:{ all -> 0x0069 }
            java.lang.String r6 = r4.k()     // Catch:{ all -> 0x0069 }
            boolean r5 = r5.equalsIgnoreCase(r6)     // Catch:{ all -> 0x0069 }
            if (r5 == 0) goto L_0x0027
            com.xiaomi.smarthome.core.entity.device.Location r5 = r2.I()     // Catch:{ all -> 0x0069 }
            com.xiaomi.smarthome.core.entity.device.Location r6 = r4.I()     // Catch:{ all -> 0x0069 }
            if (r5 == r6) goto L_0x0027
            com.xiaomi.smarthome.core.entity.device.Location r0 = r4.I()     // Catch:{ all -> 0x0069 }
            r2.a((com.xiaomi.smarthome.core.entity.device.Location) r0)     // Catch:{ all -> 0x0069 }
            r0 = 1
            goto L_0x0027
        L_0x0067:
            monitor-exit(r7)
            return r0
        L_0x0069:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a(java.util.List):boolean");
    }

    /* access modifiers changed from: private */
    public synchronized boolean a(DeviceListApi.AllDeviceData allDeviceData) {
        CoreLogUtilGrey.a(b, "processResult in");
        this.m.clear();
        a(allDeviceData.f14563a);
        a(allDeviceData.d);
        a(allDeviceData.b);
        b(allDeviceData.c);
        b();
        n();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        List<Device> f2 = DiscoverManager.a().f();
        if (f2 != null && f2.size() != 0) {
            ArrayList arrayList = new ArrayList(f2.size());
            Iterator<Device> it = f2.iterator();
            while (true) {
                boolean z2 = false;
                if (it.hasNext()) {
                    Device next = it.next();
                    int size = this.m.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        } else if (this.m.get(i2).k().equals(next.k())) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        arrayList.add(next);
                    }
                } else {
                    this.m.addAll(0, arrayList);
                    return;
                }
            }
        }
    }

    private synchronized void a(ArrayList<Device> arrayList) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Device device = arrayList.get(i2);
            if (!TextUtils.isEmpty(device.k())) {
                if (!device.l().startsWith("xiaomi.router") || device.T()) {
                    this.m.add(device);
                }
            }
        }
    }

    private Device a(JSONObject jSONObject) {
        return DeviceFactory.a(jSONObject);
    }

    private synchronized void b(ArrayList<Device> arrayList) {
        this.m.addAll(arrayList);
    }

    public synchronized ArrayList<Device> c() {
        k();
        m();
        l();
        j();
        for (DeviceSearch e2 : this.o) {
            a(e2.e());
        }
        if (this.j) {
            return new ArrayList<>(this.m);
        }
        return new ArrayList<>(this.n);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void k() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch r0 = com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch.a()     // Catch:{ all -> 0x0039 }
            com.xiaomi.smarthome.core.entity.device.RouterDevice r0 = r0.d()     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0037
            java.lang.String r1 = r0.k()     // Catch:{ all -> 0x0039 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0016
            goto L_0x0037
        L_0x0016:
            boolean r1 = r2.j     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0028
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.m     // Catch:{ all -> 0x0039 }
            boolean r1 = r2.a((java.util.List<com.xiaomi.smarthome.core.entity.device.Device>) r1, (com.xiaomi.smarthome.core.entity.device.Device) r0)     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0035
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.m     // Catch:{ all -> 0x0039 }
            r1.add(r0)     // Catch:{ all -> 0x0039 }
            goto L_0x0035
        L_0x0028:
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.n     // Catch:{ all -> 0x0039 }
            boolean r1 = r2.a((java.util.List<com.xiaomi.smarthome.core.entity.device.Device>) r1, (com.xiaomi.smarthome.core.entity.device.Device) r0)     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0035
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.n     // Catch:{ all -> 0x0039 }
            r1.add(r0)     // Catch:{ all -> 0x0039 }
        L_0x0035:
            monitor-exit(r2)
            return
        L_0x0037:
            monitor-exit(r2)
            return
        L_0x0039:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.k():void");
    }

    private void l() {
        b(BluetoothDeviceSearch.a().d());
    }

    private void m() {
        b(MitvLocalDeviceManager.a().c());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void b(java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x004f
            boolean r0 = r3.isEmpty()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x000a
            goto L_0x004f
        L_0x000a:
            boolean r0 = r2.j     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x002c
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x004c }
        L_0x0012:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x004a
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x004c }
            com.xiaomi.smarthome.core.entity.device.Device r0 = (com.xiaomi.smarthome.core.entity.device.Device) r0     // Catch:{ all -> 0x004c }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.m     // Catch:{ all -> 0x004c }
            boolean r1 = r2.a((java.util.List<com.xiaomi.smarthome.core.entity.device.Device>) r1, (com.xiaomi.smarthome.core.entity.device.Device) r0)     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x0012
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.m     // Catch:{ all -> 0x004c }
            r1.add(r0)     // Catch:{ all -> 0x004c }
            goto L_0x0012
        L_0x002c:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x004c }
        L_0x0030:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x004a
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x004c }
            com.xiaomi.smarthome.core.entity.device.Device r0 = (com.xiaomi.smarthome.core.entity.device.Device) r0     // Catch:{ all -> 0x004c }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.n     // Catch:{ all -> 0x004c }
            boolean r1 = r2.a((java.util.List<com.xiaomi.smarthome.core.entity.device.Device>) r1, (com.xiaomi.smarthome.core.entity.device.Device) r0)     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x0030
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r1 = r2.n     // Catch:{ all -> 0x004c }
            r1.add(r0)     // Catch:{ all -> 0x004c }
            goto L_0x0030
        L_0x004a:
            monitor-exit(r2)
            return
        L_0x004c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x004f:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.b(java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r3, com.xiaomi.smarthome.core.entity.device.Device r4) {
        /*
            r2 = this;
            if (r4 == 0) goto L_0x002f
            java.lang.String r0 = r4.k()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x002f
            java.util.Iterator r3 = r3.iterator()
        L_0x0010:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002f
            java.lang.Object r0 = r3.next()
            com.xiaomi.smarthome.core.entity.device.Device r0 = (com.xiaomi.smarthome.core.entity.device.Device) r0
            boolean r1 = r4.a((com.xiaomi.smarthome.core.entity.device.Device) r0)
            if (r1 != 0) goto L_0x0028
            boolean r1 = r0.a((com.xiaomi.smarthome.core.entity.device.Device) r4)
            if (r1 == 0) goto L_0x0010
        L_0x0028:
            r3 = 1
            com.xiaomi.smarthome.core.entity.device.Location r4 = com.xiaomi.smarthome.core.entity.device.Location.LOCAL
            r0.a((com.xiaomi.smarthome.core.entity.device.Location) r4)
            goto L_0x0030
        L_0x002f:
            r3 = 0
        L_0x0030:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a(java.util.List, com.xiaomi.smarthome.core.entity.device.Device):boolean");
    }

    private synchronized void n() {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.m);
        this.p.submit(new Runnable() {
            public void run() {
                DeviceManager.this.o();
                DeviceManager.this.c((List<Device>) arrayList);
            }
        });
    }

    /* access modifiers changed from: private */
    public void o() {
        SharePrefsManager.a(CoreService.getAppContext(), c, d, true);
        DeviceManagerStoreHelper.a(AccountManager.a().m());
    }

    /* access modifiers changed from: private */
    public void c(List<Device> list) {
        DeviceManagerStoreHelper.a(AccountManager.a().m(), list);
        BluetoothDeviceSearch.a().a(list);
        SharePrefsManager.a(CoreService.getAppContext(), c, d, false);
    }

    private void b(IClientCallback iClientCallback) {
        boolean b2 = SharePrefsManager.b(CoreService.getAppContext(), c, d, true);
        CoreLogUtilGrey.a(b, "loadCacheDeviceList in mIsCacheLoaded=" + this.v + ",mIsCacheLoading=" + this.u + ",first=" + b2);
        synchronized (this) {
            if (!this.v && !this.u) {
                if (!b2) {
                    this.u = true;
                    this.p.submit(new Runnable() {
                        public void run() {
                            long currentTimeMillis = System.currentTimeMillis();
                            List<Device> b = DeviceManagerStoreHelper.b(AccountManager.a().m());
                            StringBuilder sb = new StringBuilder();
                            sb.append(" deviceRecordList size = ");
                            sb.append(b == null ? null : Integer.valueOf(b.size()));
                            CoreLogUtilGrey.a(DeviceManager.b, sb.toString());
                            CoreLogUtilGrey.a(DeviceManager.b, "performance:DeviceManager reading Local devices costs " + (System.currentTimeMillis() - currentTimeMillis));
                            List unused = DeviceManager.this.n = b;
                            synchronized (this) {
                                boolean unused2 = DeviceManager.this.v = true;
                                boolean unused3 = DeviceManager.this.u = false;
                                int unused4 = DeviceManager.this.z = 1 | DeviceManager.this.z;
                            }
                            DeviceManager.this.a(ScanState.LOAD_CACHE_SUCCESS);
                        }
                    });
                    return;
                }
            }
            this.z |= 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0084, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.xiaomi.smarthome.core.entity.device.Device r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x0088
            java.lang.String r0 = r7.k()     // Catch:{ all -> 0x0085 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x000f
            goto L_0x0088
        L_0x000f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r0.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = "DeviceManager onAddDevice ---->"
            r0.append(r1)     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x0085 }
            r0.append(r1)     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0085 }
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.d(r0)     // Catch:{ all -> 0x0085 }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r6.m     // Catch:{ all -> 0x0085 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0085 }
        L_0x002d:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0085 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0074
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0085 }
            com.xiaomi.smarthome.core.entity.device.Device r1 = (com.xiaomi.smarthome.core.entity.device.Device) r1     // Catch:{ all -> 0x0085 }
            java.lang.String r4 = r1.k()     // Catch:{ all -> 0x0085 }
            java.lang.String r5 = r7.k()     // Catch:{ all -> 0x0085 }
            boolean r4 = android.text.TextUtils.equals(r4, r5)     // Catch:{ all -> 0x0085 }
            if (r4 == 0) goto L_0x002d
            java.lang.String r0 = r7.t()     // Catch:{ all -> 0x0085 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x005a
            java.lang.String r0 = r7.t()     // Catch:{ all -> 0x0085 }
            r1.f((java.lang.String) r0)     // Catch:{ all -> 0x0085 }
        L_0x005a:
            com.xiaomi.smarthome.core.entity.device.Location r0 = r7.I()     // Catch:{ all -> 0x0085 }
            r1.a((com.xiaomi.smarthome.core.entity.device.Location) r0)     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r7.s()     // Catch:{ all -> 0x0085 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x0072
            java.lang.String r7 = r7.s()     // Catch:{ all -> 0x0085 }
            r1.e((java.lang.String) r7)     // Catch:{ all -> 0x0085 }
        L_0x0072:
            r7 = 0
            goto L_0x0075
        L_0x0074:
            r7 = 1
        L_0x0075:
            if (r7 == 0) goto L_0x0080
            r6.s = r3     // Catch:{ all -> 0x0085 }
            com.xiaomi.smarthome.core.entity.device.ScanType r7 = com.xiaomi.smarthome.core.entity.device.ScanType.ALL     // Catch:{ all -> 0x0085 }
            r0 = 0
            r6.a((com.xiaomi.smarthome.core.entity.device.ScanType) r7, (com.xiaomi.smarthome.core.client.IClientCallback) r0, (boolean) r2)     // Catch:{ all -> 0x0085 }
            goto L_0x0083
        L_0x0080:
            r6.p()     // Catch:{ all -> 0x0085 }
        L_0x0083:
            monitor-exit(r6)
            return
        L_0x0085:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x0088:
            monitor-exit(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a(com.xiaomi.smarthome.core.entity.device.Device):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.xiaomi.smarthome.core.entity.device.Device r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L_0x005e
            java.lang.String r0 = r6.k()     // Catch:{ all -> 0x005b }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x005b }
            if (r0 == 0) goto L_0x000e
            goto L_0x005e
        L_0x000e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            r0.<init>()     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "DeviceManager onRemoveDevice ---->"
            r0.append(r1)     // Catch:{ all -> 0x005b }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x005b }
            r0.append(r1)     // Catch:{ all -> 0x005b }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005b }
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.d(r0)     // Catch:{ all -> 0x005b }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r5.m     // Catch:{ all -> 0x005b }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x005b }
        L_0x002c:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x005b }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x004a
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x005b }
            com.xiaomi.smarthome.core.entity.device.Device r1 = (com.xiaomi.smarthome.core.entity.device.Device) r1     // Catch:{ all -> 0x005b }
            java.lang.String r1 = r1.k()     // Catch:{ all -> 0x005b }
            java.lang.String r4 = r6.k()     // Catch:{ all -> 0x005b }
            boolean r1 = android.text.TextUtils.equals(r1, r4)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x002c
            r6 = 1
            goto L_0x004b
        L_0x004a:
            r6 = 0
        L_0x004b:
            if (r6 == 0) goto L_0x0056
            r5.s = r2     // Catch:{ all -> 0x005b }
            com.xiaomi.smarthome.core.entity.device.ScanType r6 = com.xiaomi.smarthome.core.entity.device.ScanType.ALL     // Catch:{ all -> 0x005b }
            r0 = 0
            r5.a((com.xiaomi.smarthome.core.entity.device.ScanType) r6, (com.xiaomi.smarthome.core.client.IClientCallback) r0, (boolean) r3)     // Catch:{ all -> 0x005b }
            goto L_0x0059
        L_0x0056:
            r5.p()     // Catch:{ all -> 0x005b }
        L_0x0059:
            monitor-exit(r5)
            return
        L_0x005b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x005e:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.b(com.xiaomi.smarthome.core.entity.device.Device):void");
    }

    /* access modifiers changed from: private */
    public void d(List<Device> list) {
        if (list != null && !list.isEmpty()) {
            p();
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        CoreLogUtilGrey.a(b, "notifyClient UPDATE_DEVICE_LIST");
        CoreManager.a().a(CoreMessageType.UPDATE_DEVICE_LIST, new Bundle());
    }

    public void d() {
        DiscoverManager.a().e();
        if (GlobalDynamicSettingManager.a().e()) {
            DiscoverManager.a().d();
        } else if (this.A) {
            DiscoverManager.a().c();
        }
        LocalRouterDeviceSearch.a().c();
        LocalRouterDeviceSearch.a().b();
        this.s = true;
        a(ScanType.ALL, (IClientCallback) null, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0036, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.smarthome.core.entity.device.Device a(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0037 }
            r1 = 0
            if (r0 == 0) goto L_0x000a
            monitor-exit(r4)
            return r1
        L_0x000a:
            java.util.ArrayList r0 = r4.c()     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0035
            boolean r2 = r0.isEmpty()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0037 }
        L_0x001b:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0033
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0037 }
            com.xiaomi.smarthome.core.entity.device.Device r2 = (com.xiaomi.smarthome.core.entity.device.Device) r2     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r2.k()     // Catch:{ all -> 0x0037 }
            boolean r3 = android.text.TextUtils.equals(r5, r3)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x001b
            monitor-exit(r4)
            return r2
        L_0x0033:
            monitor-exit(r4)
            return r1
        L_0x0035:
            monitor-exit(r4)
            return r1
        L_0x0037:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a(java.lang.String):com.xiaomi.smarthome.core.entity.device.Device");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.concurrent.ExecutorService r0 = r2.p     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            com.xiaomi.smarthome.core.server.internal.device.DeviceManager$6 r1 = new com.xiaomi.smarthome.core.server.internal.device.DeviceManager$6     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r1.<init>()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.submit(r1)     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            android.os.Handler r0 = r2.h     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            com.xiaomi.smarthome.core.server.internal.device.DeviceManager$7 r1 = new com.xiaomi.smarthome.core.server.internal.device.DeviceManager$7     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r1.<init>()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.post(r1)     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager r0 = com.xiaomi.smarthome.core.server.internal.device.DiscoverManager.a()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.e()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager r0 = com.xiaomi.smarthome.core.server.internal.device.DiscoverManager.a()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.d()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch r0 = com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch.a()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.c()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r2.m     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.clear()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r2.n     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0.clear()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r0 = 0
            r2.j = r0     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r2.v = r0     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            goto L_0x003d
        L_0x003a:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x003d:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.e():void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f() {
        /*
            r2 = this;
            monitor-enter(r2)
            android.os.Handler r0 = r2.h     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            com.xiaomi.smarthome.core.server.internal.device.DeviceManager$8 r1 = new com.xiaomi.smarthome.core.server.internal.device.DeviceManager$8     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r1.<init>()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.post(r1)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager r0 = com.xiaomi.smarthome.core.server.internal.device.DiscoverManager.a()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.e()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager r0 = com.xiaomi.smarthome.core.server.internal.device.DiscoverManager.a()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.d()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch r0 = com.xiaomi.smarthome.core.server.internal.device.LocalRouterDeviceSearch.a()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.c()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r2.m     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.clear()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r2.n     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0.clear()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0 = 0
            r2.j = r0     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r2.v = r0     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            goto L_0x0033
        L_0x0030:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0033:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.f():void");
    }

    public synchronized void g() {
        if (!this.A) {
            DiscoverManager.a().a((DiscoverManager.DeviceDiscoverListener) this);
            DiscoverManager.a().c();
            NetworkManager.a().a((NetworkManager.NetworkListener) this);
            this.A = true;
        }
    }

    public synchronized void h() {
        BluetoothLog.d("DeviceManager applicationEnterBackground");
        DiscoverManager.a().d();
        NetworkManager.a().b((NetworkManager.NetworkListener) this);
        this.A = false;
    }

    public NetHandle a(final String str, final String str2, final IClientCallback iClientCallback) {
        return DeviceApiInternal.a().a(str, str2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                DeviceManager.this.h.post(new Runnable() {
                    public void run() {
                        DeviceManager.this.a(str, str2);
                        if (iClientCallback != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("result", "success");
                            try {
                                iClientCallback.onSuccess(bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            public void a(NetError netError) {
                if (iClientCallback != null) {
                    Bundle bundle = new Bundle();
                    if (netError != null) {
                        bundle.putParcelable("error", netError);
                    }
                    try {
                        iClientCallback.onFailure(bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.j     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0008
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r3.m     // Catch:{ all -> 0x0034 }
            goto L_0x000a
        L_0x0008:
            java.util.List<com.xiaomi.smarthome.core.entity.device.Device> r0 = r3.n     // Catch:{ all -> 0x0034 }
        L_0x000a:
            if (r0 == 0) goto L_0x0032
            boolean r1 = r0.isEmpty()     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0013
            goto L_0x0032
        L_0x0013:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0034 }
        L_0x0017:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0034 }
            com.xiaomi.smarthome.core.entity.device.Device r1 = (com.xiaomi.smarthome.core.entity.device.Device) r1     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = r1.k()     // Catch:{ all -> 0x0034 }
            boolean r2 = android.text.TextUtils.equals(r4, r2)     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0017
            r1.c((java.lang.String) r5)     // Catch:{ all -> 0x0034 }
        L_0x0030:
            monitor-exit(r3)
            return
        L_0x0032:
            monitor-exit(r3)
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a(java.lang.String, java.lang.String):void");
    }

    public NetHandle a(List<String> list, final IClientCallback iClientCallback) {
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList = new ArrayList();
        for (String next : list) {
            Device a2 = a(next);
            if (a2 != null) {
                if ((!(a2 instanceof BtDevice) || !((BtDevice) a2).e()) && !(a2 instanceof RouterDevice) && (!(a2 instanceof MiTVDevice) || a2.S() || a2.V())) {
                    int f2 = a2.f();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("did", next);
                        jSONObject.put("pid", f2);
                    } catch (JSONException unused) {
                    }
                    jSONArray.put(jSONObject);
                } else {
                    arrayList.add(a2);
                }
            }
        }
        if (jSONArray.length() > 0) {
            return DeviceApiInternal.a().a(jSONArray, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    if (netResult != null && !TextUtils.isEmpty(netResult.c)) {
                        try {
                            JSONObject jSONObject = new JSONObject(netResult.c);
                            JSONObject optJSONObject = jSONObject.optJSONObject("result");
                            if (optJSONObject != null) {
                                DeviceManager.this.a(0, arrayList, optJSONObject, iClientCallback);
                            } else if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("error", new NetError(jSONObject.optInt("code", -9999), jSONObject.optString("message", "unknown error")));
                                try {
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            if (iClientCallback != null) {
                                Bundle bundle2 = new Bundle();
                                bundle2.putParcelable("error", new NetError(-9999, "unknown error"));
                                try {
                                    iClientCallback.onFailure(bundle2);
                                } catch (RemoteException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    } else if (!arrayList.isEmpty()) {
                        DeviceManager.this.a(0, arrayList, new JSONObject(), iClientCallback);
                    } else if (iClientCallback != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putParcelable("error", new NetError(-9999, "unknown error"));
                        try {
                            iClientCallback.onFailure(bundle3);
                        } catch (RemoteException e4) {
                            e4.printStackTrace();
                        }
                    }
                }

                public void a(NetError netError) {
                    if (!arrayList.isEmpty()) {
                        DeviceManager.this.a(0, arrayList, new JSONObject(), iClientCallback);
                    } else if (iClientCallback != null) {
                        Bundle bundle = new Bundle();
                        if (netError != null) {
                            bundle.putParcelable("error", netError);
                        }
                        try {
                            iClientCallback.onFailure(bundle);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        a(0, arrayList, new JSONObject(), iClientCallback);
        return null;
    }

    /* access modifiers changed from: private */
    public void a(int i2, List<Device> list, final JSONObject jSONObject, final IClientCallback iClientCallback) {
        if (i2 >= 0 && i2 < list.size()) {
            Device device = list.get(i2);
            try {
                if (device instanceof MiTVDevice) {
                    jSONObject.put(device.k(), 1);
                    a(i2 + 1, list, jSONObject, iClientCallback);
                } else if (device instanceof BtDevice) {
                    jSONObject.put(device.k(), 1);
                    a(i2 + 1, list, jSONObject, iClientCallback);
                } else if (device instanceof RouterDevice) {
                    final JSONObject jSONObject2 = jSONObject;
                    final Device device2 = device;
                    final int i3 = i2;
                    final List<Device> list2 = list;
                    final IClientCallback iClientCallback2 = iClientCallback;
                    RouterRemoteApiInternal.a().a(device, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                        /* renamed from: a */
                        public void b(NetResult netResult) {
                        }

                        /* renamed from: b */
                        public void a(NetResult netResult) {
                            try {
                                jSONObject2.put(device2.k(), 1);
                                DeviceManager.this.a(i3 + 1, list2, jSONObject2, iClientCallback2);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }

                        public void a(NetError netError) {
                            DeviceManager.this.a(i3 + 1, list2, jSONObject2, iClientCallback2);
                        }
                    });
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (iClientCallback != null) {
            this.h.post(new Runnable() {
                public void run() {
                    if (jSONObject != null) {
                        DeviceManager.this.b(jSONObject);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", jSONObject.toString());
                        try {
                            iClientCallback.onSuccess(bundle);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            int optInt = jSONObject.optInt(next);
            Device a2 = a(next);
            if (a2 != null && optInt == 1) {
                (this.j ? this.m : this.n).remove(a2);
            }
        }
    }

    public NetHandle b(List<String> list, final IClientCallback iClientCallback) {
        return DeviceApiInternal.a().a(list, (NetCallback<DeviceListApi.AllDeviceData, NetError>) new NetCallback<DeviceListApi.AllDeviceData, NetError>() {
            /* renamed from: a */
            public void b(DeviceListApi.AllDeviceData allDeviceData) {
            }

            /* renamed from: b */
            public void a(final DeviceListApi.AllDeviceData allDeviceData) {
                DeviceManager.this.h.post(new Runnable() {
                    public void run() {
                        ArrayList b2 = DeviceManager.this.b(allDeviceData);
                        if (iClientCallback != null) {
                            Bundle bundle = new Bundle();
                            if (b2 != null && b2.size() > 0) {
                                bundle.putParcelableArrayList("result", b2);
                            }
                            try {
                                iClientCallback.onSuccess(bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            public void a(final NetError netError) {
                DeviceManager.this.h.post(new Runnable() {
                    public void run() {
                        if (iClientCallback != null) {
                            Bundle bundle = new Bundle();
                            if (netError != null) {
                                bundle.putParcelable("error", netError);
                            }
                            try {
                                iClientCallback.onFailure(bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public ArrayList<Device> b(DeviceListApi.AllDeviceData allDeviceData) {
        ArrayList<Device> arrayList = new ArrayList<>();
        ArrayList<Device> arrayList2 = allDeviceData.f14563a;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            Device c2 = c(arrayList2.get(i2));
            if (c2 != null) {
                arrayList.add(c2);
            }
        }
        ArrayList<Device> arrayList3 = allDeviceData.d;
        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
            Device c3 = c(arrayList3.get(i3));
            if (c3 != null) {
                arrayList.add(c3);
            }
        }
        ArrayList<Device> arrayList4 = allDeviceData.b;
        for (int i4 = 0; i4 < arrayList4.size(); i4++) {
            Device c4 = c(arrayList4.get(i4));
            if (c4 != null) {
                arrayList.add(c4);
            }
        }
        return arrayList;
    }

    private Device c(Device device) {
        Device a2 = a(device.k());
        if (a2 != null) {
            a2.b(device);
        }
        return a2;
    }

    public boolean i() {
        return this.v;
    }
}
