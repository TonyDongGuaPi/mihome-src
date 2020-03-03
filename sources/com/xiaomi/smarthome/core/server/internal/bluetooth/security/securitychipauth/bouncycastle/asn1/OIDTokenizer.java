package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

public class OIDTokenizer {

    /* renamed from: a  reason: collision with root package name */
    private String f14448a;
    private int b = 0;

    public OIDTokenizer(String str) {
        this.f14448a = str;
    }

    public boolean a() {
        return this.b != -1;
    }

    public String b() {
        if (this.b == -1) {
            return null;
        }
        int indexOf = this.f14448a.indexOf(46, this.b);
        if (indexOf == -1) {
            String substring = this.f14448a.substring(this.b);
            this.b = -1;
            return substring;
        }
        String substring2 = this.f14448a.substring(this.b, indexOf);
        this.b = indexOf + 1;
        return substring2;
    }
}
