package com.xiaomi.smarthome.device.bluetooth.ui;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BleMatchFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    protected BleDevice f15225a;
    protected HashMap<String, BleDevice> b = new HashMap<>();
    protected List<String> c;

    private void b(List<BleDevice> list) {
        this.b.clear();
        BluetoothMyLogger.d(String.format("Matching Devices: ", new Object[0]));
        if (!ListUtils.a(list)) {
            for (BleDevice next : list) {
                this.b.put(next.mac, next);
                BluetoothMyLogger.d(String.format(">>> %s, name = %s, rssi = %d", new Object[]{BluetoothMyLogger.a(next.mac), next.getName(), Integer.valueOf(next.rssi)}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(BleDevice bleDevice, List<String> list) {
        BleDeviceGroup a2;
        this.f15225a = bleDevice;
        ArrayList arrayList = new ArrayList();
        if (bleDevice instanceof BleDeviceGroup) {
            arrayList.addAll(((BleDeviceGroup) bleDevice).w());
        } else {
            arrayList.add(bleDevice);
        }
        if (list != null && list.size() > 0) {
            for (String next : list) {
                if (!TextUtils.equals(next, bleDevice.model) && (a2 = BLEDeviceManager.a(next)) != null && a2.w().size() > 0) {
                    arrayList.addAll(a2.w());
                }
            }
        }
        b(arrayList);
        a(list);
    }

    /* access modifiers changed from: protected */
    public String a() {
        if (this.f15225a == null) {
            return "";
        }
        String str = this.f15225a.model;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (this.f15225a instanceof BleDeviceGroup) {
            return ((BleDeviceGroup) this.f15225a).z().get(0);
        }
        BluetoothLog.e(String.format("BleMatchFragment.getModel return null", new Object[0]));
        return "";
    }

    /* access modifiers changed from: protected */
    public void a(List<String> list) {
        this.c = list;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        if (TextUtils.isEmpty(str) || this.c == null || this.c.size() == 0) {
            return false;
        }
        return this.c.contains(str);
    }
}
