package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MNGAckPacket extends MNGPacket {
    public String a() {
        return Packet.C;
    }

    public MNGAckPacket(int i, byte[] bArr) {
        super(i, bArr);
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.allocate(this.d.length + 4).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 5);
        order.put((byte) this.c);
        order.put(this.d);
        return order.array();
    }
}
