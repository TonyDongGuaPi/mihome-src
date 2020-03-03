package com.unionpay.a;

import com.unionpay.utils.j;
import java.net.URL;
import java.util.HashMap;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private int f9538a = 1;
    private String b;
    private HashMap c;
    private byte[] d;
    private String e;

    public d(String str) {
        this.b = str;
        this.c = null;
        this.d = null;
    }

    public final URL a() {
        try {
            return new URL(this.b);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void a(String str) {
        j.b("uppay", "encrypt postData: " + str);
        if (str != null) {
            this.d = str.getBytes();
            this.e = str;
        }
    }

    public final String b() {
        return this.f9538a == 1 ? "POST" : "GET";
    }

    public final String c() {
        return this.e;
    }

    public final HashMap d() {
        return this.c;
    }
}
