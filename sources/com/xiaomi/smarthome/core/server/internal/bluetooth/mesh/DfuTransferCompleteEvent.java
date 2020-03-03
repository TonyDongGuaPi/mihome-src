package com.xiaomi.smarthome.core.server.internal.bluetooth.mesh;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class DfuTransferCompleteEvent extends AbsDfuEvent {
    private int l = -1;
    private int m = -1;

    public DfuTransferCompleteEvent(byte[] bArr) {
        super(bArr);
        d();
    }

    private void d() {
        if (this.k.length >= 3) {
            ByteBuffer order = ByteBuffer.wrap(this.k).order(ByteOrder.LITTLE_ENDIAN);
            this.l = order.get();
            this.m = order.getShort();
        }
    }

    public int a() {
        return this.l;
    }

    public int c() {
        return this.m;
    }

    public String toString() {
        return "DfuTransferCompleteEvent{Status=" + this.l + "Index=" + this.m + Operators.BLOCK_END;
    }
}
