package com.amap.openapi;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ce {

    /* renamed from: a  reason: collision with root package name */
    public byte f4660a;
    public int b;
    public List<Long> c;
    public List<String> d;
    public HashMap<String, String> e;
    public byte[] f;

    public ce(byte b2, List<Long> list, List<String> list2) {
        this.f4660a = b2;
        this.c = list;
        this.d = list2;
    }

    public String toString() {
        return "OfflineRequest{mType=" + this.f4660a + ", mWifiList=" + this.c + ", mCellList=" + this.d + ", mHeaders=" + this.e + ", mBody=" + Arrays.toString(this.f) + Operators.BLOCK_END;
    }
}
