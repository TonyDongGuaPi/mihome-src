package com.xiaomi.smarthome.device.bluetooth;

import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BleDeviceGroup extends BleDevice {
    private HashMap<String, List<BleDevice>> e;
    private int f;

    public BleDeviceGroup() {
        if (this.e == null) {
            this.e = new HashMap<>();
        }
    }

    public int r() {
        return this.f;
    }

    public boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String b : this.e.keySet()) {
            if (BluetoothHelper.b(b, str)) {
                return true;
            }
        }
        return false;
    }

    public List<BleDevice> w() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<BleDevice>> value : this.e.entrySet()) {
            arrayList.addAll((List) value.getValue());
        }
        BleDevice.b((List<BleDevice>) arrayList);
        return arrayList;
    }

    public ArrayList<XmBluetoothDevice> x() {
        ArrayList<XmBluetoothDevice> arrayList = new ArrayList<>();
        for (BleDevice a2 : w()) {
            arrayList.add(a2.a());
        }
        return arrayList;
    }

    public void a(BleDevice bleDevice) {
        if (bleDevice != null && !TextUtils.isEmpty(bleDevice.model)) {
            if (y()) {
                this.model = bleDevice.model;
                this.mac = bleDevice.mac;
                this.did = bleDevice.did;
                this.canAuth = false;
                this.b = bleDevice.d();
                setOwner(true);
            }
            List list = this.e.get(bleDevice.model);
            if (list == null) {
                list = new ArrayList();
                this.e.put(bleDevice.model, list);
            }
            if (!list.contains(bleDevice)) {
                list.add(bleDevice);
                this.f++;
            }
        }
    }

    public boolean y() {
        return this.e.isEmpty();
    }

    public ArrayList<String> z() {
        return new ArrayList<>(this.e.keySet());
    }
}
