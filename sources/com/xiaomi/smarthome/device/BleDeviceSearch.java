package com.xiaomi.smarthome.device;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.bluetooth.BluetoothReceiver;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BleDeviceSearch extends DeviceSearch<BleDevice> {

    /* renamed from: a  reason: collision with root package name */
    private Handler f14753a = new Handler(Looper.getMainLooper());
    private List<BleDevice> b = Collections.synchronizedList(new ArrayList());
    private Object c = new Object();
    private HashMap<String, Device> d = new HashMap<>();

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public void b(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        super.b(collection, remotestate);
        this.d.clear();
        if (this.k != null) {
            for (Device device : this.k) {
                if (device != null) {
                    this.d.put(device.mac, device);
                }
            }
        }
        this.k = null;
    }

    public void c() {
        BluetoothReceiver.registerBluetoothReceiver(1000);
        this.f14753a.postDelayed(new Runnable() {
            public void run() {
                if (!BluetoothHelper.a()) {
                    if (!CoreApi.a().q() || !BluetoothUtils.b()) {
                        BLEDeviceManager.d();
                    } else if (CommonApplication.getApplication().isAppForeground()) {
                        BLEDeviceManager.a((MiioBtSearchResponse) null);
                    } else {
                        Log.v("BleDeviceSearch", "app is on background, don't search ble device");
                    }
                }
            }
        }, 1000);
    }

    public int g() {
        return Device.PID_BLUETOOTH;
    }

    public List<BleDevice> d() {
        ArrayList arrayList;
        synchronized (this.c) {
            arrayList = new ArrayList(this.b);
        }
        return arrayList;
    }

    public void e() {
        BLEDeviceManager.d();
    }

    public void b() {
        synchronized (this.c) {
            this.b.clear();
            for (BleDevice next : BLEDeviceManager.a()) {
                if (!this.b.contains(next)) {
                    Device b2 = SmartHomeDeviceManager.a().b(next.did);
                    BleDevice d2 = BLEDeviceManager.d(next.mac);
                    if ((b2 != null && b2.isNew) || (d2 != null && d2.isNew)) {
                        next.isNew = true;
                    }
                    a(next);
                    this.b.add(next);
                }
            }
        }
    }

    private void a(BleDevice bleDevice) {
        Device device = this.d.get(bleDevice.mac);
        String str = bleDevice.name;
        int i = bleDevice.permitLevel;
        if (device != null) {
            device.cloneTo(bleDevice);
        }
        bleDevice.name = str;
        bleDevice.permitLevel = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f A[EDGE_INSN: B:18:0x004f->B:16:0x004f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.xiaomi.smarthome.device.Device r7) {
        /*
            r6 = this;
            int r0 = r7.pid
            int r1 = com.xiaomi.smarthome.device.Device.PID_BLUETOOTH
            if (r0 != r1) goto L_0x0054
            boolean r0 = r7 instanceof com.xiaomi.smarthome.device.BleDevice
            if (r0 == 0) goto L_0x0054
            r0 = r7
            com.xiaomi.smarthome.device.BleDevice r0 = (com.xiaomi.smarthome.device.BleDevice) r0
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0054
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.Device> r0 = r6.d
            if (r0 == 0) goto L_0x0054
            r0 = 0
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.Device> r1 = r6.d
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0022:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x004f
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
            java.lang.String r4 = r3.did
            java.lang.String r5 = r7.did
            boolean r4 = android.text.TextUtils.equals(r4, r5)
            if (r4 != 0) goto L_0x0048
            java.lang.String r3 = r3.mac
            java.lang.String r4 = r7.mac
            boolean r3 = android.text.TextUtils.equals(r3, r4)
            if (r3 == 0) goto L_0x0022
        L_0x0048:
            java.lang.Object r7 = r2.getKey()
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x004f:
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.device.Device> r7 = r6.d
            r7.remove(r0)
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.BleDeviceSearch.b(com.xiaomi.smarthome.device.Device):void");
    }
}
