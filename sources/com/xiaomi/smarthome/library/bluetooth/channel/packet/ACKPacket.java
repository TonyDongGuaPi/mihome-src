package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;

public class ACKPacket extends Packet {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18493a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private int g;
    private List<Short> h = Collections.emptyList();

    public String a() {
        return Packet.w;
    }

    public ACKPacket(int i) {
        this.g = i;
    }

    public ACKPacket(int i, List<Short> list) {
        this.g = i;
        if (list != null && list.size() > 0) {
            this.h = list;
        }
    }

    public int b() {
        return this.g;
    }

    public List<Short> c() {
        return this.h;
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.allocate((this.h.size() * 2) + 4).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 1);
        order.put((byte) this.g);
        if (this.h.size() > 0) {
            for (Short shortValue : this.h) {
                order.putShort(shortValue.shortValue());
            }
        }
        return order.array();
    }

    public String toString() {
        return "ACKPacket{status=" + this.g + ", seq=" + this.h.toString() + Operators.BLOCK_END;
    }
}
