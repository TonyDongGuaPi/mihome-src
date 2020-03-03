package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;

public class AlbumPriceTypeDetail {
    @SerializedName("price_type")

    /* renamed from: a  reason: collision with root package name */
    private int f2099a;
    @SerializedName("price")
    private double b;
    @SerializedName("discounted_price")
    private double c;
    @SerializedName("price_unit")
    private String d;

    public int a() {
        return this.f2099a;
    }

    public void a(int i) {
        this.f2099a = i;
    }

    public double b() {
        return this.b;
    }

    public void a(double d2) {
        this.b = d2;
    }

    public double c() {
        return this.c;
    }

    public void b(double d2) {
        this.c = d2;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }
}
