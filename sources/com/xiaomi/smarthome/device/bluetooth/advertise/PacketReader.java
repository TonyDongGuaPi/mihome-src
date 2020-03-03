package com.xiaomi.smarthome.device.bluetooth.advertise;

import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketReader {

    /* renamed from: a  reason: collision with root package name */
    private Pdu f15122a;
    private ByteBuffer b;

    public int a(int i, int i2, int i3) {
        return (i >> i2) & ((1 << ((i3 - i2) + 1)) - 1);
    }

    public boolean a(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    public PacketReader(byte[] bArr) {
        this.b = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
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

    public String c() {
        String[] strArr = new String[6];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.format("%x", new Object[]{Integer.valueOf(b())});
        }
        return XMStringUtils.a((Object[]) strArr, ":");
    }

    public void a(Pdu pdu) {
        this.f15122a = pdu;
        a(this.f15122a.e());
    }

    public int d() {
        a(this.f15122a.f() - 1);
        return a();
    }

    public boolean e() {
        return this.b.position() > this.f15122a.f() + 1;
    }
}
