package com.xiaomi.aiot.mibeacon;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.utils.MacUtils;

public class TestBeaconParser implements IBeaconParser {

    /* renamed from: a  reason: collision with root package name */
    String[] f9971a;

    public TestBeaconParser(String[] strArr) {
        this.f9971a = strArr;
    }

    public MiBeacon a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        if (!a(bluetoothDevice)) {
            return null;
        }
        return new MiBeacon.Builder().b(bluetoothDevice.getAddress()).a(bluetoothDevice.getName()).a((double) i).a(-59).c(MacUtils.a(bluetoothDevice.getAddress())).a();
    }

    private boolean a(BluetoothDevice bluetoothDevice) {
        String address = bluetoothDevice.getAddress();
        for (String equals : this.f9971a) {
            if (TextUtils.equals(equals, address)) {
                return true;
            }
        }
        return false;
    }
}
