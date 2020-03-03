package com.xiaomi.aiot.mibeacon;

import android.bluetooth.BluetoothDevice;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.bluetooth.BleAdvertisement;
import com.xiaomi.aiot.mibeacon.bluetooth.Pdu;
import com.xiaomi.aiot.mibeacon.utils.StringUtils;

public class BeaconParserV4 implements IBeaconParser {
    public MiBeacon a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        MiBeacon miBeacon = null;
        for (Pdu a2 : new BleAdvertisement(bArr).a()) {
            MiPacket a3 = a(a2);
            if (a3 != null) {
                miBeacon = new MiBeacon.Builder().b(bluetoothDevice.getAddress()).a(bluetoothDevice.getName()).a((double) i).a(a3.b).c(a3.f9965a).b(a3.c).c(a3.d).d(a3.e).a();
            }
        }
        return miBeacon;
    }

    public MiPacket a(Pdu pdu) {
        int b;
        MiPacket miPacket = new MiPacket();
        if (pdu.a() != 255 || (b = pdu.b()) < 15 || pdu.b() - 1 != pdu.d().length) {
            return null;
        }
        int i = b - 3;
        PacketReader packetReader = new PacketReader(pdu);
        if (packetReader.b() != 143 || packetReader.b() != 3) {
            return null;
        }
        int b2 = packetReader.b();
        while (true) {
            int i2 = b2 + 2;
            if (i < i2) {
                return null;
            }
            int b3 = packetReader.b();
            if (b2 == 10 && b3 == 16) {
                miPacket.c = packetReader.b();
                miPacket.d = packetReader.b();
                miPacket.e = packetReader.b();
                String[] strArr = new String[6];
                for (int length = strArr.length - 1; length >= 0; length--) {
                    strArr[length] = String.format("%02x", new Object[]{Integer.valueOf(packetReader.b())}).toUpperCase();
                }
                miPacket.f9965a = StringUtils.a(strArr, ":");
                miPacket.b = packetReader.c();
                return miPacket;
            } else if (i <= i2) {
                return null;
            } else {
                for (int i3 = 0; i3 < b2; i3++) {
                    packetReader.b();
                }
                i = (i - b2) - 2;
                b2 = packetReader.b();
            }
        }
    }

    class MiPacket {

        /* renamed from: a  reason: collision with root package name */
        String f9965a;
        int b;
        int c;
        int d;
        int e;

        MiPacket() {
        }
    }
}
