package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CTRPacket extends Packet {
    private static final int D = 6;

    /* renamed from: a  reason: collision with root package name */
    public static final int f18494a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final int m = 12;
    public static final int n = 13;
    public static final int o = 14;
    private final byte[] E = new byte[6];
    private int F;
    private int G;

    public String a() {
        return Packet.y;
    }

    public CTRPacket(int i2, int i3) {
        this.F = i2;
        this.G = i3;
    }

    public int b() {
        return this.F;
    }

    public int c() {
        return this.G;
    }

    public byte[] d() {
        ByteBuffer order = ByteBuffer.wrap(this.E).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort(0);
        order.put((byte) 0);
        order.put((byte) this.G);
        order.putShort((short) this.F);
        return order.array();
    }

    public String toString() {
        return "FlowPacket{frameCount=" + this.F + "packageType=" + this.G + Operators.BLOCK_END;
    }
}
