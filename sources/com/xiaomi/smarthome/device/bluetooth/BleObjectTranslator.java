package com.xiaomi.smarthome.device.bluetooth;

import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchResult;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class BleObjectTranslator {
    public static List<BleDevice> a(List<BtDevice> list) {
        ArrayList arrayList = new ArrayList();
        if (!ListUtils.a(list)) {
            for (BtDevice a2 : list) {
                arrayList.add(BleDevice.a(a2));
            }
        }
        return arrayList;
    }

    public static XmBluetoothDevice a(BleDevice bleDevice) {
        XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
        xmBluetoothDevice.device = BluetoothUtils.b(bleDevice.mac);
        xmBluetoothDevice.rssi = bleDevice.rssi;
        xmBluetoothDevice.scanRecord = bleDevice.c();
        xmBluetoothDevice.name = bleDevice.name;
        return xmBluetoothDevice;
    }

    public static XmBluetoothDevice a(SearchResult searchResult) {
        XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
        xmBluetoothDevice.device = searchResult.b();
        xmBluetoothDevice.rssi = searchResult.c();
        xmBluetoothDevice.scanRecord = searchResult.d();
        xmBluetoothDevice.deviceType = searchResult.e();
        xmBluetoothDevice.name = searchResult.f();
        return xmBluetoothDevice;
    }
}
