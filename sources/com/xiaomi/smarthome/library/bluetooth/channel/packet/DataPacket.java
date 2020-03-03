package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.Packet;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class DataPacket extends Packet {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18495a = 20;
    private final byte[] b;
    private int c;
    private Packet.Bytes d;

    public String a() {
        return "data";
    }

    public DataPacket(int i, Packet.Bytes bytes) {
        this.b = new byte[20];
        this.c = i;
        this.d = bytes;
    }

    public DataPacket(int i, byte[] bArr, int i2, int i3) {
        this(i, new Packet.Bytes(bArr, i2, i3));
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d.a();
    }

    public byte[] d() {
        ByteBuffer byteBuffer;
        int c2 = c() + 2;
        if (c2 == 20) {
            Arrays.fill(this.b, (byte) 0);
            byteBuffer = ByteBuffer.wrap(this.b).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            byteBuffer = ByteBuffer.allocate(c2).order(ByteOrder.LITTLE_ENDIAN);
        }
        byteBuffer.putShort((short) this.c);
        a(byteBuffer);
        return byteBuffer.array();
    }

    public void a(ByteBuffer byteBuffer) {
        byteBuffer.put(this.d.f18497a, this.d.b, c());
    }

    public String toString() {
        return "DataPacket{seq=" + this.c + ", size=" + this.d.a() + Operators.BLOCK_END;
    }
}
