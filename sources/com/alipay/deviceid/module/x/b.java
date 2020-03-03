package com.alipay.deviceid.module.x;

public final class b {
    private static b b = new b();

    /* renamed from: a  reason: collision with root package name */
    public int f887a = 0;

    public static b a() {
        return b;
    }

    public final String b() {
        switch (this.f887a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
