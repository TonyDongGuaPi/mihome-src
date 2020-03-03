package com.amap.location.common.model;

import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;

public class FPS {

    /* renamed from: a  reason: collision with root package name */
    public CellStatus f4587a = new CellStatus();
    public WifiStatus b = new WifiStatus();
    public double c;
    public double d;
    public double e;
    public byte f;

    private String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("FPS[");
        sb.append(this.f4587a != null ? z ? this.f4587a.c() : this.f4587a.toString() : "cellStatus:null");
        sb.append(i.b);
        sb.append(this.b != null ? z ? this.b.d() : this.b.toString() : "wifiScan:null");
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    /* renamed from: a */
    public FPS clone() {
        FPS fps = new FPS();
        if (this.f4587a != null) {
            fps.f4587a = this.f4587a.clone();
        }
        if (this.b != null) {
            fps.b = this.b.clone();
        }
        return fps;
    }

    public String b() {
        return a(true);
    }

    public String toString() {
        return super.toString();
    }
}
