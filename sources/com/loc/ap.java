package com.loc;

import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.dfu.DfuBaseService;

public final class ap extends bj {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f6483a;
    private String b = "1";

    public ap(byte[] bArr, String str) {
        this.f6483a = (byte[]) bArr.clone();
        this.b = str;
    }

    public final Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", DfuBaseService.MIME_TYPE_ZIP);
        hashMap.put("Content-Length", String.valueOf(this.f6483a.length));
        return hashMap;
    }

    public final Map<String, String> b() {
        return null;
    }

    public final String c() {
        String c = ad.c(al.c);
        byte[] a2 = ad.a(al.b);
        byte[] bArr = new byte[(a2.length + 50)];
        System.arraycopy(this.f6483a, 0, bArr, 0, 50);
        System.arraycopy(a2, 0, bArr, 50, a2.length);
        return String.format(c, new Object[]{"1", this.b, "1", "open", aa.a(bArr)});
    }

    public final byte[] d() {
        return this.f6483a;
    }
}
