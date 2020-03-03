package com.xiaomi.smarthome.core.server.internal.bluetooth.mesh;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DfuCommand {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14184a = 17;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private int f;
    private byte[] g;

    public DfuCommand(int i, byte[] bArr) throws Exception {
        if (bArr == null || bArr.length <= 17) {
            this.f = i;
            this.g = bArr;
            return;
        }
        throw new Exception("params exceed max size 17");
    }

    public byte[] a() {
        int length = this.g != null ? this.g.length + 3 : 3;
        ByteBuffer order = ByteBuffer.allocate(length).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort((short) this.f);
        order.put((byte) (length - 3));
        if (this.g != null && this.g.length > 0) {
            order.put(this.g);
        }
        return order.array();
    }

    public String toString() {
        return "DfuCommand{Command=" + this.f + Operators.BLOCK_END;
    }
}
