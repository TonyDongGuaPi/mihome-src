package com.amap.api.services.a;

import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.dfu.DfuBaseService;

public class ck extends df {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f4371a;
    private String b = "1";

    public Map<String, String> d() {
        return null;
    }

    public ck(byte[] bArr, String str) {
        this.f4371a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String a() {
        byte[] a2 = bz.a(cg.b);
        byte[] bArr = new byte[(a2.length + 50)];
        System.arraycopy(this.f4371a, 0, bArr, 0, 50);
        System.arraycopy(a2, 0, bArr, 50, a2.length);
        return bw.a(bArr);
    }

    public Map<String, String> e() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", DfuBaseService.MIME_TYPE_ZIP);
        hashMap.put("Content-Length", String.valueOf(this.f4371a.length));
        return hashMap;
    }

    public String i() {
        return String.format(bz.c(cg.c), new Object[]{"1", this.b, "1", "open", a()});
    }

    public byte[] h() {
        return this.f4371a;
    }
}
