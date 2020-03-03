package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon;

import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketReader {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14282a;
    private ByteBuffer b = ByteBuffer.wrap(this.f14282a).order(ByteOrder.LITTLE_ENDIAN);

    public int a(int i, int i2, int i3) {
        return (i >> i2) & ((1 << ((i3 - i2) + 1)) - 1);
    }

    public boolean a(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    public PacketReader(BleAdvertiseItem bleAdvertiseItem) {
        this.f14282a = bleAdvertiseItem.c;
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
        for (int length = strArr.length - 1; length >= 0; length--) {
            strArr[length] = String.format("%02x", new Object[]{Integer.valueOf(b())}).toUpperCase();
        }
        return XMStringUtils.a((Object[]) strArr, ":");
    }

    public int d() {
        a(this.f14282a.length - 2);
        return a();
    }

    public String e() {
        String[] strArr = new String[2];
        for (int length = strArr.length - 1; length >= 0; length--) {
            strArr[length] = String.format("%02x", new Object[]{Integer.valueOf(b())}).toUpperCase();
        }
        return XMStringUtils.a((Object[]) strArr, "");
    }
}
