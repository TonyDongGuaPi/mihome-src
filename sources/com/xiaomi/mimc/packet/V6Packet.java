package com.xiaomi.mimc.packet;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mimc.cipher.RC4;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.proto.ImsPushService;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class V6Packet {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f11206a;
    public ImsPushService.ClientHeader b;
    public byte[] c;

    public V6Packet a(ImsPushService.ClientHeader clientHeader) {
        this.b = clientHeader;
        return this;
    }

    public V6Packet a(byte[] bArr) throws Exception {
        this.b = ImsPushService.ClientHeader.a(bArr);
        return this;
    }

    public V6Packet b(byte[] bArr) {
        this.c = bArr;
        return this;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (this.b == null) {
            ByteBuffer allocate = ByteBuffer.allocate(12);
            allocate.order(ByteOrder.LITTLE_ENDIAN).clear();
            ByteBuffer slice = allocate.slice();
            slice.putChar(MIMCConstant.w);
            slice.putChar(5);
            slice.putInt(0);
            slice.putInt(MIMCUtils.a(slice.array(), 0, 8));
            return slice.array();
        }
        byte[] K = this.b.K();
        short length = (short) K.length;
        int length2 = (this.c == null || this.c.length == 0) ? 0 : this.c.length;
        ByteBuffer allocate2 = ByteBuffer.allocate(length + 8 + length2);
        allocate2.order(ByteOrder.LITTLE_ENDIAN).clear();
        ByteBuffer slice2 = allocate2.slice();
        slice2.putShort(2);
        slice2.putShort(length);
        slice2.putInt(length2);
        slice2.put(K);
        if (length2 > 0) {
            slice2.put(MIMCConstant.p.equalsIgnoreCase(this.b.m()) ? RC4.a(bArr2, this.c) : this.c);
        }
        this.f11206a = slice2.array();
        if (!MIMCConstant.l.equalsIgnoreCase(this.b.m())) {
            this.f11206a = RC4.a(bArr, this.f11206a);
        }
        int length3 = this.f11206a.length;
        int i = length3 + 8;
        ByteBuffer allocate3 = ByteBuffer.allocate(i + 4);
        allocate3.order(ByteOrder.LITTLE_ENDIAN).clear();
        ByteBuffer slice3 = allocate3.slice();
        slice3.putChar(MIMCConstant.w);
        slice3.putChar(5);
        slice3.putInt(length3);
        slice3.put(this.f11206a);
        slice3.putInt(MIMCUtils.a(slice3.array(), 0, i));
        return slice3.array();
    }

    public String toString() {
        return "V6Packet{\n\nbody=" + Arrays.toString(this.f11206a) + "\n, header=" + this.b + "\n, payload=" + Arrays.toString(this.c) + Operators.BLOCK_END;
    }
}
