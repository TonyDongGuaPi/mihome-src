package com.amap.openapi;

import com.amap.location.common.model.AmapLoc;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;

public class bu {

    /* renamed from: a  reason: collision with root package name */
    public int f4643a = 0;
    public HashMap<Long, bt> b = new HashMap<>();
    public int c = 0;
    public StringBuilder d = new StringBuilder();
    public StringBuilder e = new StringBuilder();
    public AmapLoc f;

    public String toString() {
        StringBuilder sb = new StringBuilder(Operators.BLOCK_START_STR);
        sb.append(this.f4643a);
        sb.append("@");
        sb.append(this.c);
        sb.append("@");
        double d2 = 0.0d;
        sb.append(this.f != null ? this.f.d() : 0.0d);
        sb.append("@");
        if (this.f != null) {
            d2 = this.f.c();
        }
        sb.append(d2);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
