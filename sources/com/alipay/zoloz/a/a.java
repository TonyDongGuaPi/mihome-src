package com.alipay.zoloz.a;

import com.taobao.weex.el.parse.Operators;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1177a = false;
    private long b;
    private int c;

    public a(boolean z, long j, int i) {
        this.f1177a = z;
        this.b = j;
        this.c = i;
    }

    public boolean a() {
        return this.f1177a;
    }

    public long b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return "IspResult{needSet=" + this.f1177a + ", exposureTime=" + this.b + ", iso=" + this.c + Operators.BLOCK_END;
    }
}
