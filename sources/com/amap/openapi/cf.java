package com.amap.openapi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class cf {

    /* renamed from: a  reason: collision with root package name */
    protected ch f4661a = new ch(this.b);
    private ByteBuffer b;

    protected cf(int i) {
        this.b = ByteBuffer.allocate(i);
        this.b.order(ByteOrder.LITTLE_ENDIAN);
    }

    public cf a() {
        this.f4661a.a(this.b);
        return this;
    }
}
