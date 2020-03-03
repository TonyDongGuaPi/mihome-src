package com.xiaomi.smarthome.device.bluetooth.advertise;

import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.List;

public class BleAdvertisement {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15117a = "BleAdvertisement";
    private List<Pdu> b;
    private byte[] c;
    private Pdu d;
    private int e;
    private MiotBleAdvPacket f;
    private XmBluetoothDevice g;

    public BleAdvertisement(XmBluetoothDevice xmBluetoothDevice) {
        if (xmBluetoothDevice != null && !ByteUtils.e(xmBluetoothDevice.scanRecord)) {
            this.g = xmBluetoothDevice;
            this.c = this.g.scanRecord;
            try {
                this.b = a(this.c);
                f();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.xiaomi.smarthome.device.bluetooth.advertise.Pdu> a(byte[] r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
        L_0x0006:
            com.xiaomi.smarthome.device.bluetooth.advertise.Pdu r2 = com.xiaomi.smarthome.device.bluetooth.advertise.Pdu.a((byte[]) r5, (int) r1)
            if (r2 == 0) goto L_0x0016
            int r3 = r2.c()
            int r1 = r1 + r3
            int r1 = r1 + 1
            r0.add(r2)
        L_0x0016:
            if (r2 == 0) goto L_0x001b
            int r2 = r5.length
            if (r1 < r2) goto L_0x0006
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.bluetooth.advertise.BleAdvertisement.a(byte[]):java.util.List");
    }

    public List<Pdu> a() {
        return this.b;
    }

    public Pdu b() {
        return this.d;
    }

    private void f() {
        if (!ListUtils.a(this.b)) {
            PacketReader packetReader = new PacketReader(this.c);
            for (Pdu next : this.b) {
                packetReader.a(next);
                if ((next.b() & 255) == 22 && packetReader.a() == 65173) {
                    MiotBleAdvPacket miotBleAdvPacket = new MiotBleAdvPacket(packetReader);
                    if (miotBleAdvPacket.g) {
                        this.d = next;
                        this.f = miotBleAdvPacket;
                        this.e = this.f.b;
                    } else {
                        this.e = packetReader.d();
                    }
                }
            }
        }
    }

    public boolean c() {
        return this.d != null;
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f != null && this.f.a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!(this.g == null || this.g.device == null)) {
            sb.append(String.format("mac: %s\n", new Object[]{this.g.device.getAddress()}));
        }
        if (this.b != null) {
            for (int i = 0; i < this.b.size(); i++) {
                sb.append(this.b.get(i).toString());
                sb.append("\n");
            }
        }
        if (this.e > 0) {
            sb.append("productId: " + this.e);
            sb.append("\n");
        }
        if (this.d != null) {
            sb.append("miotPdu: " + this.d.toString());
            sb.append("\n");
        }
        if (this.f != null) {
            sb.append(this.f.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
