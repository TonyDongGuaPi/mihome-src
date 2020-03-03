package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MNGPacket extends Packet {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18496a = 0;
    public static final int b = 1;
    protected int c;
    protected byte[] d;
    protected int e;
    protected int f;
    protected byte[] g;

    public String a() {
        return Packet.B;
    }

    public MNGPacket(int i, byte[] bArr) {
        this.c = i;
        this.d = bArr;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int e() {
        return this.c;
    }

    public byte[] f() {
        return this.g;
    }

    public void g() {
        ByteBuffer order = ByteBuffer.wrap(this.d).order(ByteOrder.LITTLE_ENDIAN);
        if (this.c == 0) {
            this.e = order.get();
            this.f = order.get() & 255;
            return;
        }
        this.g = order.array();
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.allocate(this.d.length + 4).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 4);
        order.put((byte) this.c);
        order.put(this.d);
        return order.array();
    }
}
