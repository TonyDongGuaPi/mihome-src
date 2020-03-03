package com.amap.location.common.model;

import com.amap.location.common.util.f;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.auth.AuthCode;

public class WiFi {

    /* renamed from: a  reason: collision with root package name */
    public long f4589a;
    public String b;
    public int c = AuthCode.n;
    public int d;
    public long e;
    public long f = 0;
    public int g;
    public boolean h;

    public WiFi(long j, String str, int i, int i2, long j2) {
        this.f4589a = j;
        this.b = str == null ? "" : str;
        this.c = i;
        this.d = i2;
        this.e = j2;
    }

    public WiFi(long j, String str, int i, int i2, long j2, long j3, boolean z, int i3) {
        this.f4589a = j;
        this.b = str == null ? "" : str;
        this.c = i;
        this.d = i2;
        this.e = j2;
        this.f = j3;
        this.h = z;
        this.g = i3;
    }

    public WiFi(long j, String str, int i, int i2, long j2, boolean z) {
        this.f4589a = j;
        this.b = str == null ? "" : str;
        this.c = i;
        this.d = i2;
        this.e = j2;
        this.h = z;
    }

    public String a() {
        return this.h + "#" + this.f4589a;
    }

    /* renamed from: b */
    public WiFi clone() {
        return new WiFi(this.f4589a, this.b, this.c, this.d, this.e, this.f, this.h, this.g);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("WiFi:[");
        stringBuffer.append("mac:" + f.a(this.f4589a) + ",");
        stringBuffer.append("ssid:" + this.b + ",");
        stringBuffer.append("rssi:" + this.c + ",");
        stringBuffer.append("freq:" + this.d + ",");
        stringBuffer.append("time:" + this.e + ",");
        stringBuffer.append("utc:" + this.f + ",");
        stringBuffer.append("conn:" + this.h + ",");
        stringBuffer.append("type:" + this.g + ",");
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }
}
