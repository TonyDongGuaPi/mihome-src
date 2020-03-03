package com.xiaomi.aiot.mibeacon;

import android.bluetooth.BluetoothDevice;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.bluetooth.BleAdvertisement;
import com.xiaomi.aiot.mibeacon.bluetooth.Pdu;
import com.xiaomi.aiot.mibeacon.utils.StringUtils;

public class BeaconParserV3 implements IBeaconParser {
    public MiBeacon a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        MiBeacon miBeacon = null;
        for (Pdu a2 : new BleAdvertisement(bArr).a()) {
            MiPacket a3 = a(a2);
            if (a3 != null) {
                miBeacon = new MiBeacon.Builder().b(bluetoothDevice.getAddress()).a(bluetoothDevice.getName()).a((double) i).a(a3.b).c(a3.f9964a).b(a3.c).c(a3.d).d(a3.e).a();
            }
        }
        return miBeacon;
    }

    private MiPacket a(Pdu pdu) {
        MiPacket miPacket = new MiPacket();
        if (pdu.a() != 255 || pdu.b() != 15) {
            return null;
        }
        PacketReader packetReader = new PacketReader(pdu);
        if (packetReader.b() != 143 || packetReader.b() != 3 || packetReader.b() != 10 || packetReader.b() != 16) {
            return null;
        }
        miPacket.c = packetReader.b();
        miPacket.d = packetReader.b();
        miPacket.e = packetReader.b();
        String[] strArr = new String[6];
        for (int length = strArr.length - 1; length >= 0; length--) {
            strArr[length] = String.format("%02x", new Object[]{Integer.valueOf(packetReader.b())}).toUpperCase();
        }
        miPacket.f9964a = StringUtils.a(strArr, ":");
        miPacket.b = packetReader.c();
        return miPacket;
    }

    class MiPacket {

        /* renamed from: a  reason: collision with root package name */
        String f9964a;
        int b;
        int c;
        int d;
        int e;

        MiPacket() {
        }
    }
}
