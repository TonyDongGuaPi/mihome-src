package com.xiaomi.smarthome.core.server.internal.bluetooth.mesh;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class DfuCommandCompleteEvent extends AbsDfuEvent {
    private int l = -1;
    private int m = -1;
    private byte[] n = new byte[0];

    public DfuCommandCompleteEvent(byte[] bArr) {
        super(bArr);
        e();
    }

    private void e() {
        if (this.k.length >= 2) {
            ByteBuffer order = ByteBuffer.wrap(this.k).order(ByteOrder.LITTLE_ENDIAN);
            this.l = order.getShort();
            if (order.remaining() > 0) {
                this.m = order.get();
                this.n = new byte[order.remaining()];
                if (this.n.length > 0) {
                    order.get(this.n);
                }
            }
        }
    }

    public int c() {
        return this.l;
    }

    public int a() {
        return this.m;
    }

    public byte[] d() {
        return this.n;
    }

    public String toString() {
        return "DfuCommandCompleteEvent{Command Opcode=" + this.l + "Status=" + this.m + Operators.BLOCK_END;
    }
}
