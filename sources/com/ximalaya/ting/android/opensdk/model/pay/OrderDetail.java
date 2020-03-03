package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("xima_order_no")

    /* renamed from: a  reason: collision with root package name */
    private String f2105a;
    @SerializedName("xima_order_status")
    private int b;
    @SerializedName("xima_order_created_at")
    private long c;
    @SerializedName("xima_order_updated_at")
    private long d;
    private int e;
    @SerializedName("third_uid")
    private String f;
    @SerializedName("order_amount")
    private double g;
    @SerializedName("client_os_type")
    private int h;
    @SerializedName("pay_content")
    private String i;
    @SerializedName("pay_channel")
    private int j;

    public String a() {
        return this.f2105a;
    }

    public void a(String str) {
        this.f2105a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public long c() {
        return this.c;
    }

    public void a(long j2) {
        this.c = j2;
    }

    public long d() {
        return this.d;
    }

    public void b(long j2) {
        this.d = j2;
    }

    public int e() {
        return this.e;
    }

    public void b(int i2) {
        this.e = i2;
    }

    public String f() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public double g() {
        return this.g;
    }

    public void a(double d2) {
        this.g = d2;
    }

    public int h() {
        return this.h;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public String i() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public int j() {
        return this.j;
    }

    public void d(int i2) {
        this.j = i2;
    }
}
