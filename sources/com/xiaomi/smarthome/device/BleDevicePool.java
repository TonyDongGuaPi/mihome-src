package com.xiaomi.smarthome.device;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.HashMap;

public class BleDevicePool implements IBleDevicePool {

    /* renamed from: a  reason: collision with root package name */
    private static BleDevicePool f14752a;
    private HashMap<String, BleDevice> b = new HashMap<>();
    private HashMap<String, BleDevice> c = new HashMap<>();
    private final Object d = new Object();

    public static BleDevicePool a() {
        if (f14752a == null) {
            synchronized (BleDevicePool.class) {
                if (f14752a == null) {
                    f14752a = new BleDevicePool();
                }
            }
        }
        return f14752a;
    }

    private BleDevicePool() {
    }

    public BleDevice a(String str) {
        BleDevice bleDevice;
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("%s getDeviceByMac: mac null", new Object[]{getClass().getSimpleName()}));
            return null;
        }
        synchronized (this.d) {
            bleDevice = this.b.get(str);
        }
        return bleDevice;
    }

    public BleDevice b(String str) {
        BleDevice bleDevice;
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("%s getDeviceByDid: did null", new Object[]{getClass().getSimpleName()}));
            return null;
        }
        synchronized (this.d) {
            bleDevice = this.c.get(str);
        }
        return bleDevice;
    }

    public boolean a(BleDevice bleDevice) {
        if (!b(bleDevice)) {
            BluetoothLog.b(String.format("%s addDevice %s failed", new Object[]{getClass().getSimpleName(), bleDevice}));
            BluetoothMyLogger.c(String.format("%s addDevice %s failed", new Object[]{getClass().getSimpleName(), BluetoothMyLogger.a(bleDevice.mac)}));
            return false;
        }
        synchronized (this.d) {
            if (this.b.containsKey(bleDevice.mac)) {
                BluetoothLog.b(String.format("%s addDevice %s failed: device already exist!", new Object[]{getClass().getSimpleName(), bleDevice}));
                BluetoothMyLogger.c(String.format("%s addDevice %s failed: device already exist!", new Object[]{getClass().getSimpleName(), BluetoothMyLogger.a(bleDevice.mac)}));
                return false;
            }
            this.b.put(bleDevice.mac, bleDevice);
            if (this.c.containsKey(bleDevice.did)) {
                BluetoothLog.b(String.format("%s addDevice %s failed: device already exist!", new Object[]{getClass().getSimpleName(), bleDevice}));
                BluetoothMyLogger.c(String.format("%s addDevice %s failed: device already exist!", new Object[]{getClass().getSimpleName(), BluetoothMyLogger.a(bleDevice.mac)}));
                return false;
            }
            this.c.put(bleDevice.did, bleDevice);
            return true;
        }
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("%s removeDevice %s: mac null", new Object[]{getClass().getSimpleName()}));
            return false;
        }
        synchronized (this.d) {
            BleDevice remove = this.b.remove(str);
            if (remove == null) {
                BluetoothMyLogger.c(String.format("%s removeDevice %s: mac not exist", new Object[]{getClass().getSimpleName(), BluetoothMyLogger.a(str)}));
                return false;
            } else if (this.c.remove(remove.did) != null) {
                return true;
            } else {
                BluetoothMyLogger.c(String.format("%s removeDevice %s: did not exist", new Object[]{getClass().getSimpleName(), remove.did}));
                return false;
            }
        }
    }

    public BleDevice d(String str) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("%s allocDevice: mac null", new Object[]{getClass().getSimpleName()}));
            return null;
        }
        try {
            BleDevice newInstance = BleDevice.class.newInstance();
            newInstance.pid = Device.PID_BLUETOOTH;
            newInstance.userId = CoreApi.a().s();
            newInstance.isOnline = true;
            newInstance.canAuth = true;
            newInstance.mac = str;
            String e = BleCacheUtils.e(str);
            if (TextUtils.isEmpty(e)) {
                e = str;
            }
            newInstance.did = e;
            newInstance.canUseNotBind = true;
            newInstance.ownerId = BleCacheUtils.k(str);
            newInstance.ownerName = BleCacheUtils.c(str);
            newInstance.permitLevel = BleCacheUtils.o(str);
            if (newInstance.permitLevel == 0) {
                newInstance.setOwner(true);
            }
            newInstance.model = BleCacheUtils.j(str);
            String a2 = BleCacheUtils.a(str);
            if (TextUtils.isEmpty(a2)) {
                a2 = BleDevice.c(newInstance.model);
            }
            newInstance.name = a2;
            return newInstance;
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return null;
        }
    }

    private boolean b(BleDevice bleDevice) {
        if (bleDevice != null && !TextUtils.isEmpty(bleDevice.mac) && !TextUtils.isEmpty(bleDevice.did) && !TextUtils.isEmpty(bleDevice.model)) {
            return true;
        }
        return false;
    }
}
