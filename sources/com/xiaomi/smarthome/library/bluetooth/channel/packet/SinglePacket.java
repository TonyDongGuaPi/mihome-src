package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SinglePacket extends Packet {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18500a = 0;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 8;
    public static final int g = 9;
    public static final int h = 10;
    public static final int i = 11;
    public static final int j = 12;
    public static final int k = 13;
    public static final int l = 14;
    byte[] m;
    int n;

    public String a() {
        return Packet.z;
    }

    public SinglePacket(int i2, byte[] bArr) {
        this.m = bArr;
        this.n = i2;
    }

    public byte[] b() {
        return this.m;
    }

    public int c() {
        return this.n;
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.allocate(this.m.length + 4).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 2);
        order.put((byte) this.n);
        order.put(this.m);
        return order.array();
    }
}
