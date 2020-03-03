package com.xiaomi.mimc.data;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mimc.proto.RtsSignal;
import java.util.Arrays;

public class DialCallContext {

    /* renamed from: a  reason: collision with root package name */
    String f11185a;
    String b;
    RtsSignal.StreamDataType c;
    byte[] d;
    long e;

    public DialCallContext(String str, String str2, RtsSignal.StreamDataType streamDataType, byte[] bArr, long j) {
        this.f11185a = str;
        this.b = str2;
        this.c = streamDataType;
        this.d = bArr;
        this.e = j;
    }

    public String a() {
        return this.f11185a;
    }

    public String b() {
        return this.b;
    }

    public RtsSignal.StreamDataType c() {
        return this.c;
    }

    public byte[] d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public String toString() {
        return "DialCallContext{toAppAccount='" + this.f11185a + Operators.SINGLE_QUOTE + ", toResource='" + this.b + Operators.SINGLE_QUOTE + ", dataType=" + this.c + ", appContent=" + Arrays.toString(this.d) + ", callId=" + this.e + Operators.BLOCK_END;
    }
}
