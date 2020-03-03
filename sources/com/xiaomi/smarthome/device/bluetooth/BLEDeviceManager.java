package com.xiaomi.smarthome.device.bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BLEDeviceManager extends BluetoothContextManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f15064a = "ble_refresh_choose";
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 5000;
    private static final int f = 4;
    private static final long g = 180000;
    private static final byte h = 0;
    private static final byte i = 1;
    private static final byte j = 2;
    private static final byte k = 3;
    private static HashMap<String, List<BleDevice>> l = new HashMap<>();
    /* access modifiers changed from: private */
    public static HashMap<String, BleDevice> m = new HashMap<>();
    private static HashMap<String, BleDevice> n = new HashMap<>();
    private static final List<BleDeviceGroup> o = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public static MiioBtSearchResponse p;
    private static final List<BleDevice> q = Collections.synchronizedList(new ArrayList());
    private static final List<Device> r = Collections.synchronizedList(new ArrayList());
    private static Map<String, BleScanRecord> s = new HashMap();
    /* access modifiers changed from: private */
    public static volatile byte t = 0;
    private static volatile boolean u;
    /* access modifiers changed from: private */
    public static volatile Runnable v;
    /* access modifiers changed from: private */
    public static Runnable w;
    private static boolean x = true;
    private static final MiioBtSearchResponse y = new MiioBtSearchResponse() {
        public void a() {
            BluetoothLog.c(String.format("BLEDeviceManager onSearchStarted", new Object[0]));
            BLEDeviceManager.s();
        }

        public void a(BleDevice bleDevice) {
            if (BluetoothLog.a()) {
                BluetoothLog.c(String.format("BLEDeviceManager onDeviceFounded %s", new Object[]{bleDevice}));
            }
            BleDevice bleDevice2 = (BleDevice) BLEDeviceManager.m.get(bleDevice.mac);
            if (bleDevice2 == null || bleDevice2.d() == null || bleDevice.d() != null) {
                if (BleCacheUtils.f(bleDevice.mac) == 0) {
                    BLEDeviceManager.f(bleDevice);
                } else if (BleCacheUtils.f(bleDevice.mac) == 1) {
                    BLEDeviceManager.f(bleDevice);
                }
                if (BLEDeviceManager.p != null) {
                    BLEDeviceManager.p.a(bleDevice);
                }
            } else if (BLEDeviceManager.p != null) {
                BLEDeviceManager.p.a(bleDevice);
            }
        }

        public void b() {
            BluetoothLog.c(String.format("BLEDeviceManager onSearchStopped", new Object[0]));
            BLEDeviceManager.r();
            BLEDeviceManager.t();
        }

        public void c() {
            BluetoothLog.c(String.format("BLEDeviceManager onSearchCanceled", new Object[0]));
            BLEDeviceManager.u();
        }
    };
    /* access modifiers changed from: private */
    public static final Handler z = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (BLEDeviceManager.z.hasMessages(2)) {
                    BLEDeviceManager.z.removeMessages(2);
                    BLEDeviceManager.b(true);
                }
                BLEDeviceManager.z.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

    public static void a(boolean z2) {
        x = z2;
    }

    public static List<BleDevice> a() {
        if (BluetoothHelper.a()) {
            return ListUtils.a();
        }
        ArrayList<BleDevice> arrayList = new ArrayList<>();
        List<BleDevice> d2 = BleCacheUtils.d();
        StringBuilder sb = new StringBuilder();
        sb.append("getSmartHomeBluetoothDeviceList RemoteBoundedDevice size = ");
        sb.append(d2 == null ? 0 : d2.size());
        LogUtilGrey.a("BLEDeviceManager", sb.toString());
        if (d2 != null && d2.size() > 0) {
            for (BleDevice next : d2) {
                if (CoreApi.a().c(next.model)) {
                    arrayList.add(next);
                }
            }
        }
        List<BleDevice> b = BleCacheUtils.b();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getSmartHomeBluetoothDeviceList localBoundedDevices size = ");
        sb2.append(b == null ? 0 : b.size());
        LogUtilGrey.a("BLEDeviceManager", sb2.toString());
        if (b != null && b.size() > 0) {
            for (BleDevice next2 : b) {
                if (CoreApi.a().c(next2.model)) {
                    arrayList.add(next2);
                }
            }
        }
        BluetoothLog.e(String.format("getSmartHomeDevices ...", new Object[0]));
        for (BleDevice bleDevice : arrayList) {
            bleDevice.isOnline = true;
            bleDevice.canAuth = bleDevice.j() && bleDevice.isOnline;
            if (TextUtils.isEmpty(bleDevice.did)) {
                bleDevice.did = bleDevice.mac;
            }
            BluetoothLog.e(String.format(">>> %s", new Object[]{bleDevice}));
        }
        synchronized (q) {
            q.clear();
            q.addAll(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Device next3 : SmartHomeDeviceManager.a().d()) {
            if (next3.pid == Device.PID_BLE_MESH) {
                arrayList2.add(next3);
            }
        }
        synchronized (r) {
            r.clear();
            r.addAll(arrayList2);
        }
        return new ArrayList(q);
    }

    public static BleDeviceGroup a(String str) {
        return a(o, str);
    }

    public static BleDeviceGroup b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (o) {
            for (BleDeviceGroup next : o) {
                if (str.equalsIgnoreCase(next.mac)) {
                    return next;
                }
            }
            return null;
        }
    }

    private static BleDeviceGroup a(List<BleDeviceGroup> list, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (BleDeviceGroup next : list) {
            if (next.h(str)) {
                return next;
            }
        }
        return null;
    }

    public static List<BleDevice> b() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry value : new HashMap(m).entrySet()) {
            BleDevice bleDevice = (BleDevice) value.getValue();
            if (TextUtils.equals(bleDevice.did, bleDevice.mac)) {
                arrayList.add(bleDevice);
            }
        }
        return arrayList;
    }

    public static List<BleDeviceGroup> c() {
        ArrayList<BleDeviceGroup> arrayList = new ArrayList<>();
        for (Map.Entry<String, List<BleDevice>> key : l.entrySet()) {
            String str = (String) key.getKey();
            if (!str.equalsIgnoreCase("xiaomi.ble.v1") || !ListUtils.a(BleCacheUtils.c())) {
                List<BleDevice> list = l.get(str);
                if (!ListUtils.a(list)) {
                    if (DeviceFactory.a((BleDevice) list.get(0))) {
                        for (BleDevice bleDevice : list) {
                            if (!c(bleDevice) && bleDevice.d() != null && !TextUtils.isEmpty(bleDevice.d().f)) {
                                BleDeviceGroup bleDeviceGroup = new BleDeviceGroup();
                                arrayList.add(bleDeviceGroup);
                                bleDeviceGroup.a(bleDevice);
                            }
                        }
                    } else {
                        BleDeviceGroup a2 = a((List<BleDeviceGroup>) arrayList, str);
                        if (a2 == null) {
                            a2 = new BleDeviceGroup();
                            arrayList.add(a2);
                        }
                        for (BleDevice bleDevice2 : list) {
                            if (!c(bleDevice2) && !d(bleDevice2)) {
                                a2.a(bleDevice2);
                            }
                        }
                    }
                }
            }
        }
        synchronized (o) {
            o.clear();
            for (BleDeviceGroup bleDeviceGroup2 : arrayList) {
                if (!bleDeviceGroup2.y()) {
                    o.add(bleDeviceGroup2);
                }
            }
        }
        return o;
    }

    /* access modifiers changed from: private */
    public static void f(BleDevice bleDevice) {
        PluginRecord d2 = CoreApi.a().d(bleDevice.model);
        if (d2 == null) {
            BluetoothLog.e("addRecognizedDevice pluginRecord is null: " + bleDevice);
        } else if (d2.c().t() == Device.PID_BLE_MESH && bleDevice.d() == null) {
            BleScanRecord bleScanRecord = s.get(bleDevice.mac);
            if (bleScanRecord != null) {
                bleScanRecord.f15066a--;
            }
        } else {
            s.put(bleDevice.mac, new BleScanRecord(4, System.currentTimeMillis()));
            List list = l.get(bleDevice.model);
            if (list == null) {
                list = new ArrayList();
                l.put(bleDevice.model, list);
            }
            list.remove(bleDevice);
            list.add(bleDevice);
            m.put(bleDevice.mac, bleDevice);
            n.put(bleDevice.did, bleDevice);
            if (!TextUtils.isEmpty(bleDevice.t())) {
                BluetoothHelper.d(bleDevice.t());
            }
            g(bleDevice);
            b(false);
        }
    }

    public static void a(String str, String str2) {
        if (s.containsKey(str2)) {
            s.remove(str2);
        }
        List list = l.get(str);
        int i2 = 0;
        if (list != null && list.size() > 0) {
            int i3 = 0;
            while (i3 < list.size()) {
                if (!TextUtils.isEmpty(str2) && str2.equals(((BleDevice) list.get(i3)).mac)) {
                    list.remove(i3);
                    i3--;
                }
                i3++;
            }
        }
        synchronized (o) {
            while (i2 < o.size()) {
                if (!TextUtils.isEmpty(str2) && str2.equals(o.get(i2).mac)) {
                    o.remove(i2);
                    i2--;
                }
                i2++;
            }
        }
    }

    public static void d() {
        BluetoothLog.c("BLEDeviceManager clearBleDevices");
        f();
        s.clear();
        l.clear();
        synchronized (o) {
            o.clear();
        }
        m.clear();
        n.clear();
        synchronized (q) {
            q.clear();
        }
        synchronized (r) {
            r.clear();
        }
        CoreApi.a().a((String) null, 30, (Bundle) null, (IBleResponse) null);
    }

    public static boolean e() {
        byte b = t;
        return b == 0 || b == 1;
    }

    public static void a(MiioBtSearchResponse miioBtSearchResponse) {
        a((SearchRequest) null, miioBtSearchResponse);
    }

    public static void a(final SearchRequest searchRequest, final MiioBtSearchResponse miioBtSearchResponse) {
        BluetoothMyLogger.d(String.format("BLEDeviceManager searchBleDevice", new Object[0]));
        if (BluetoothHelper.a()) {
            BluetoothMyLogger.c("International server? Bluetooth disabled!");
        } else if (t != 0) {
            BluetoothMyLogger.c(String.format("Previous search ongoing!", new Object[0]));
            if (t == 1) {
                v = new Runnable() {
                    public void run() {
                        BLEDeviceManager.a(searchRequest, miioBtSearchResponse);
                    }
                };
            }
        } else if (BluetoothUtils.b()) {
            t = 3;
            p = miioBtSearchResponse;
            if (searchRequest == null) {
                searchRequest = new SearchRequest.Builder().a(5000, 2).b(10000).a();
            }
            Log.i("startScan", "BDM start");
            BluetoothHelper.a(searchRequest, y);
        } else {
            BluetoothMyLogger.c("Bluetooth not open?");
        }
    }

    /* access modifiers changed from: private */
    public static void r() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<String, BleScanRecord>> it = s.entrySet().iterator();
        while (it.hasNext()) {
            BleScanRecord bleScanRecord = (BleScanRecord) it.next().getValue();
            if (bleScanRecord == null) {
                it.remove();
            } else {
                bleScanRecord.f15066a--;
                if (bleScanRecord.f15066a <= 0 || Math.abs(currentTimeMillis - bleScanRecord.b) > g) {
                    it.remove();
                }
            }
        }
        for (Map.Entry<String, List<BleDevice>> value : l.entrySet()) {
            List list = (List) value.getValue();
            if (list != null && list.size() > 0) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    if (s.get(((BleDevice) it2.next()).mac) == null) {
                        it2.remove();
                    }
                }
            }
        }
        Iterator<Map.Entry<String, BleDevice>> it3 = m.entrySet().iterator();
        while (it3.hasNext()) {
            if (s.get(((BleDevice) it3.next().getValue()).mac) == null) {
                it3.remove();
            }
        }
        Iterator<Map.Entry<String, BleDevice>> it4 = n.entrySet().iterator();
        while (it4.hasNext()) {
            if (s.get(((BleDevice) it4.next().getValue()).mac) == null) {
                it4.remove();
            }
        }
        BluetoothUtils.a(f15064a);
        b(false);
    }

    public static void f() {
        if (t == 3 || t == 2) {
            t = 1;
            BluetoothMyLogger.d(String.format("BLEDeviceManager stopSearchBleDevice", new Object[0]));
            Log.i("stopScan", "BDM stop");
            BluetoothHelper.b();
            v = null;
            Handler globalWorkerHandler = CommonApplication.getGlobalWorkerHandler();
            AnonymousClass3 r1 = new Runnable() {
                public void run() {
                    byte unused = BLEDeviceManager.t = (byte) 0;
                    Runnable unused2 = BLEDeviceManager.v = null;
                    Runnable unused3 = BLEDeviceManager.w = null;
                    MyLog.f("error: BLEDeviceManager.stopSearchBleDevice do not callback");
                    BluetoothLog.b("error: BLEDeviceManager.stopSearchBleDevice do not callback");
                }
            };
            w = r1;
            globalWorkerHandler.postDelayed(r1, 3000);
        }
    }

    /* access modifiers changed from: private */
    public static void s() {
        t = 2;
        if (p != null) {
            p.a();
        }
    }

    /* access modifiers changed from: private */
    public static void t() {
        t = 0;
        if (p != null) {
            p.b();
        }
        p = null;
        CommonApplication.getGlobalWorkerHandler().removeCallbacks(w);
        w = null;
        Runnable runnable = v;
        if (runnable != null) {
            runnable.run();
            v = null;
        }
    }

    /* access modifiers changed from: private */
    public static void u() {
        t = 0;
        if (p != null) {
            p.c();
        }
        p = null;
        CommonApplication.getGlobalWorkerHandler().removeCallbacks(w);
        w = null;
        Runnable runnable = v;
        if (runnable != null) {
            runnable.run();
            v = null;
        }
    }

    private static void g(BleDevice bleDevice) {
        if (!bleDevice.k()) {
            BluetoothUtils.a(f15064a);
        }
    }

    public static void a(Device device) {
        BluetoothMyLogger.d("unbindMeshDevice " + BluetoothMyLogger.a(device.mac));
        XmBluetoothManager.getInstance().disconnect(device.mac);
        BleCacheUtils.x(device.mac);
        BleCacheUtils.a(device.mac, "");
        BleCacheUtils.g(device.mac, "");
        BleCacheUtils.b(device.mac, "");
        BleCacheUtils.b(device.mac, 0);
        BleCacheUtils.a(device.mac, 0);
        r.remove(device);
        m.remove(device.mac);
        n.remove(device.did);
        List list = l.get(device.model);
        if (list != null && list.size() > 0) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BleDevice bleDevice = (BleDevice) it.next();
                if (TextUtils.equals(bleDevice.mac, device.mac)) {
                    list.remove(bleDevice);
                    break;
                }
            }
        }
        b(true);
    }

    public static void a(BleDevice bleDevice) {
        if (bleDevice != null) {
            BluetoothMyLogger.d("unbindBluetoothDevice " + BluetoothMyLogger.a(bleDevice.mac));
            if (com.xiaomi.smarthome.core.server.internal.device.DeviceFactory.t.equalsIgnoreCase(bleDevice.model) || com.xiaomi.smarthome.core.server.internal.device.DeviceFactory.u.equalsIgnoreCase(bleDevice.model)) {
                BluetoothUtils.g(bleDevice.mac);
            } else if ("onemore.soundbox.sm001".equalsIgnoreCase(bleDevice.model)) {
                BluetoothUtils.g(bleDevice.mac);
            } else if ("yeelink.light.ble1".equalsIgnoreCase(bleDevice.model)) {
                BluetoothHelper.a(bleDevice);
            } else if ("runmi.suitcase.v1".equalsIgnoreCase(bleDevice.model)) {
                bleDevice.l();
            } else {
                bleDevice.l();
            }
            XmBluetoothManager.getInstance().unBindDevice(bleDevice.mac);
            BleCacheUtils.x(bleDevice.mac);
            BleCacheUtils.a(bleDevice.mac, "");
            BleCacheUtils.g(bleDevice.mac, "");
            BleCacheUtils.b(bleDevice.mac, "");
            BleCacheUtils.b(bleDevice.mac, 0);
            BleCacheUtils.a(bleDevice.mac, 0);
            q.remove(bleDevice);
            m.remove(bleDevice.mac);
            n.remove(bleDevice.did);
            List list = l.get(bleDevice.model);
            if (list != null) {
                list.remove(bleDevice);
            }
            b(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007b A[EDGE_INSN: B:40:0x007b->B:28:0x007b ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.device.BleDevice c(java.lang.String r5) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0008
            r5 = 0
            return r5
        L_0x0008:
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.BleDevice> r0 = n
            java.lang.Object r0 = r0.get(r5)
            com.xiaomi.smarthome.device.BleDevice r0 = (com.xiaomi.smarthome.device.BleDevice) r0
            if (r0 != 0) goto L_0x0053
            java.util.HashMap r1 = new java.util.HashMap
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.BleDevice> r2 = n
            r1.<init>(r2)
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0021:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r2 = r2.getValue()
            com.xiaomi.smarthome.device.BleDevice r2 = (com.xiaomi.smarthome.device.BleDevice) r2
            if (r2 == 0) goto L_0x0021
            java.lang.String r3 = r2.did
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0021
            r0 = r2
        L_0x003e:
            if (r0 == 0) goto L_0x004f
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.BleDevice> r1 = n
            java.lang.String r2 = r0.mac
            r1.remove(r2)
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.BleDevice> r1 = n
            java.lang.String r2 = r0.did
            r1.put(r2, r0)
            goto L_0x0053
        L_0x004f:
            com.xiaomi.smarthome.device.BleDevice r0 = d((java.lang.String) r5)
        L_0x0053:
            if (r0 != 0) goto L_0x0080
            java.util.List<com.xiaomi.smarthome.device.BleDevice> r1 = q
            monitor-enter(r1)
            java.util.List<com.xiaomi.smarthome.device.BleDevice> r2 = q     // Catch:{ all -> 0x007d }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x007d }
        L_0x005e:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x007d }
            if (r3 == 0) goto L_0x007b
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x007d }
            com.xiaomi.smarthome.device.BleDevice r3 = (com.xiaomi.smarthome.device.BleDevice) r3     // Catch:{ all -> 0x007d }
            java.lang.String r4 = r3.did     // Catch:{ all -> 0x007d }
            boolean r4 = r5.equals(r4)     // Catch:{ all -> 0x007d }
            if (r4 != 0) goto L_0x007a
            java.lang.String r4 = r3.mac     // Catch:{ all -> 0x007d }
            boolean r4 = r5.equals(r4)     // Catch:{ all -> 0x007d }
            if (r4 == 0) goto L_0x005e
        L_0x007a:
            r0 = r3
        L_0x007b:
            monitor-exit(r1)     // Catch:{ all -> 0x007d }
            goto L_0x0080
        L_0x007d:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x007d }
            throw r5
        L_0x0080:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager.c(java.lang.String):com.xiaomi.smarthome.device.BleDevice");
    }

    public static BleDevice d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BleDevice bleDevice = m.get(str);
        if (bleDevice != null) {
            return bleDevice;
        }
        synchronized (q) {
            Iterator<BleDevice> it = q.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BleDevice next = it.next();
                if (str.equals(next.mac)) {
                    bleDevice = next;
                    break;
                }
            }
        }
        return bleDevice;
    }

    public static void b(boolean z2) {
        if (!x) {
            return;
        }
        if (z2) {
            SmartHomeDeviceManager.a().c();
            return;
        }
        if (!z.hasMessages(1)) {
            z.sendEmptyMessage(1);
        }
        z.sendEmptyMessageDelayed(2, 10000);
    }

    public static void a(String str, boolean z2) {
        BleDevice d2 = d(str);
        if (d2 != null) {
            d2.a(z2);
        }
    }

    public static boolean b(BleDevice bleDevice) {
        if (bleDevice == null || bleDevice.d() == null || !DeviceFactory.a(bleDevice)) {
            return false;
        }
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (!ListUtils.a(d2)) {
            for (Device next : d2) {
                if (bleDevice.model.equals(next.model)) {
                    String str = bleDevice.d().f;
                    String f2 = DeviceFactory.f(next.mac);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(f2) && str.equalsIgnoreCase(f2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        r2 = r;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        if (com.xiaomi.smarthome.library.common.util.ListUtils.a(r) != false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        r1 = r.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        if (r1.hasNext() == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        if (r1.next().mac.equalsIgnoreCase(r6.mac) == false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
        if (b(r6) == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006f, code lost:
        if (f(r6.mac) == false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0071, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0072, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(com.xiaomi.smarthome.device.BleDevice r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r6.mac
            boolean r1 = e((java.lang.String) r1)
            if (r1 == 0) goto L_0x000d
            return r0
        L_0x000d:
            java.util.List<com.xiaomi.smarthome.device.BleDevice> r1 = q
            monitor-enter(r1)
            java.util.List<com.xiaomi.smarthome.device.BleDevice> r2 = q     // Catch:{ all -> 0x0076 }
            boolean r2 = com.xiaomi.smarthome.library.common.util.ListUtils.a(r2)     // Catch:{ all -> 0x0076 }
            r3 = 1
            if (r2 != 0) goto L_0x0037
            java.util.List<com.xiaomi.smarthome.device.BleDevice> r2 = q     // Catch:{ all -> 0x0076 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0076 }
        L_0x001f:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0076 }
            if (r4 == 0) goto L_0x0037
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0076 }
            com.xiaomi.smarthome.device.BleDevice r4 = (com.xiaomi.smarthome.device.BleDevice) r4     // Catch:{ all -> 0x0076 }
            java.lang.String r4 = r4.mac     // Catch:{ all -> 0x0076 }
            java.lang.String r5 = r6.mac     // Catch:{ all -> 0x0076 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0076 }
            if (r4 == 0) goto L_0x001f
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            return r3
        L_0x0037:
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            java.util.List<com.xiaomi.smarthome.device.Device> r2 = r
            monitor-enter(r2)
            java.util.List<com.xiaomi.smarthome.device.Device> r1 = r     // Catch:{ all -> 0x0073 }
            boolean r1 = com.xiaomi.smarthome.library.common.util.ListUtils.a(r1)     // Catch:{ all -> 0x0073 }
            if (r1 != 0) goto L_0x0061
            java.util.List<com.xiaomi.smarthome.device.Device> r1 = r     // Catch:{ all -> 0x0073 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0073 }
        L_0x0049:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0073 }
            if (r4 == 0) goto L_0x0061
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0073 }
            com.xiaomi.smarthome.device.Device r4 = (com.xiaomi.smarthome.device.Device) r4     // Catch:{ all -> 0x0073 }
            java.lang.String r4 = r4.mac     // Catch:{ all -> 0x0073 }
            java.lang.String r5 = r6.mac     // Catch:{ all -> 0x0073 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0073 }
            if (r4 == 0) goto L_0x0049
            monitor-exit(r2)     // Catch:{ all -> 0x0073 }
            return r3
        L_0x0061:
            monitor-exit(r2)     // Catch:{ all -> 0x0073 }
            boolean r1 = b((com.xiaomi.smarthome.device.BleDevice) r6)
            if (r1 == 0) goto L_0x0069
            return r3
        L_0x0069:
            java.lang.String r6 = r6.mac
            boolean r6 = f((java.lang.String) r6)
            if (r6 == 0) goto L_0x0072
            return r3
        L_0x0072:
            return r0
        L_0x0073:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0073 }
            throw r6
        L_0x0076:
            r6 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager.c(com.xiaomi.smarthome.device.BleDevice):boolean");
    }

    public static boolean d(BleDevice bleDevice) {
        return (bleDevice == null || bleDevice.d() == null || bleDevice.d().f14276a == null || !bleDevice.d().f14276a.f) ? false : true;
    }

    private static boolean e(String str) {
        BleDevice bleDevice = m.get(str);
        return (bleDevice == null || bleDevice.d() == null || bleDevice.d().k == null || bleDevice.d().k.d != 0) ? false : true;
    }

    private static boolean f(String str) {
        BleDevice bleDevice = m.get(str);
        return (bleDevice == null || bleDevice.d() == null || bleDevice.d().k == null || bleDevice.d().k.d != 3) ? false : true;
    }

    public static void b(Device device) {
        if (device != null) {
            BluetoothLog.d("removeCacheMeshDevice: " + device);
            s.remove(device.mac);
            m.remove(device.mac);
            n.remove(device.mac);
            n.remove(device.did);
            List<BleDevice> list = l.get(device.model);
            if (list != null && list.size() > 0) {
                for (BleDevice bleDevice : list) {
                    if (TextUtils.equals(bleDevice.mac, device.mac)) {
                        list.remove(bleDevice);
                        return;
                    }
                }
            }
        }
    }

    private static class BleScanRecord {

        /* renamed from: a  reason: collision with root package name */
        public int f15066a;
        public long b;

        public BleScanRecord(int i, long j) {
            this.f15066a = i;
            this.b = j;
        }
    }
}
