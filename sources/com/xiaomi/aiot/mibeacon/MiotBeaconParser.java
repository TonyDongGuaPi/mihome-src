package com.xiaomi.aiot.mibeacon;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.bluetooth.BleAdvertisement;
import com.xiaomi.aiot.mibeacon.bluetooth.Pdu;
import com.xiaomi.aiot.mibeacon.utils.MacUtils;

public class MiotBeaconParser implements IBeaconParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9969a = "MiotBeaconParser";

    public MiBeacon a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        BleAdvertisement bleAdvertisement = new BleAdvertisement(bArr);
        if (bleAdvertisement.a() == null) {
            return null;
        }
        for (Pdu next : bleAdvertisement.a()) {
            if (next.a() == 22 && new PacketReader(next).a() == 65173) {
                Log.i(f9969a, "发现miot设备 " + bluetoothDevice.getAddress());
                return new MiBeacon.Builder().b(bluetoothDevice.getAddress()).a(bluetoothDevice.getName()).a((double) i).a(-59).c(MacUtils.a(bluetoothDevice.getAddress())).a();
            }
        }
        return null;
    }
}
