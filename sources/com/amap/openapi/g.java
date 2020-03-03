package com.amap.openapi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    protected i f4726a = new i(this.b);
    private ByteBuffer b;

    protected g(int i) {
        this.b = ByteBuffer.allocate(i);
        this.b.order(ByteOrder.LITTLE_ENDIAN);
    }

    public g a() {
        this.f4726a.a(this.b);
        return this;
    }
}
