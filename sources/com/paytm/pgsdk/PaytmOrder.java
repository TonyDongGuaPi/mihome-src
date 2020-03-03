package com.paytm.pgsdk;

import java.util.Map;

public class PaytmOrder {

    /* renamed from: a  reason: collision with root package name */
    public String f8538a;
    public String b;
    public String c;
    public String d;
    public String e;
    private Map<String, String> f;

    public PaytmOrder(String str, String str2, String str3, String str4, String str5) {
        this.f8538a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public PaytmOrder(Map<String, String> map) {
        this.f = map;
    }

    public Map<String, String> a() {
        return this.f;
    }
}
