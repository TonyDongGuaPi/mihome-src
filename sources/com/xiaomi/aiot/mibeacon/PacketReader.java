package com.xiaomi.aiot.mibeacon;

import com.xiaomi.aiot.mibeacon.bluetooth.Pdu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketReader {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f9970a;
    private ByteBuffer b = ByteBuffer.wrap(this.f9970a).order(ByteOrder.LITTLE_ENDIAN);

    public int a(int i, int i2, int i3) {
        return (i >> i2) & ((1 << ((i3 - i2) + 1)) - 1);
    }

    public boolean a(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    public PacketReader(Pdu pdu) {
        this.f9970a = pdu.d();
    }

    public void a(int i) {
        this.b.position(i);
    }

    public int a() {
        return this.b.getShort() & 65535;
    }

    public int b() {
        return this.b.get() & 255;
    }

    public int c() {
        return this.b.get();
    }
}
