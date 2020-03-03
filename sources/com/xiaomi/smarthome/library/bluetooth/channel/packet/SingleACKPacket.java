package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SingleACKPacket extends Packet {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18499a = 0;
    public static final int b = 2;
    private static final int c = 4;
    private final byte[] d = new byte[4];
    private int e;

    public String a() {
        return Packet.A;
    }

    public SingleACKPacket(int i) {
        this.e = i;
    }

    public int b() {
        return this.e;
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.wrap(this.d).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 3);
        order.put((byte) this.e);
        return order.array();
    }
}
