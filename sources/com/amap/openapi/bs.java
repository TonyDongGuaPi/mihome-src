package com.amap.openapi;

import com.amap.location.common.model.AmapLoc;
import com.taobao.weex.el.parse.Operators;

public class bs {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4641a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public long g;
    public boolean h = false;
    public AmapLoc i;

    public bs(boolean z, String str, long j, int i2, int i3, int i4, int i5) {
        this.f4641a = z;
        this.f = str;
        this.g = j;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(Operators.BLOCK_START_STR);
        sb.append(this.f4641a);
        sb.append("@");
        sb.append(this.f);
        sb.append("@");
        sb.append(this.b);
        sb.append("@");
        sb.append(this.c);
        sb.append("@");
        sb.append(this.e);
        sb.append("@");
        sb.append(this.h);
        sb.append("@");
        double d2 = 0.0d;
        sb.append(this.i != null ? this.i.d() : 0.0d);
        sb.append("@");
        if (this.i != null) {
            d2 = this.i.c();
        }
        sb.append(d2);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
