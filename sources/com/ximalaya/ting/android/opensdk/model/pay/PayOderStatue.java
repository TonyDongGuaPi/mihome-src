package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class PayOderStatue extends XimalayaResponse {
    @SerializedName("xima_order_no")

    /* renamed from: a  reason: collision with root package name */
    private String f2108a;
    @SerializedName("xima_order_status")
    private int b;
    @SerializedName("xima_order_created_at")
    private long c;
    @SerializedName("xima_order_updated_at")
    private long d;

    public String a() {
        return this.f2108a;
    }

    public void a(String str) {
        this.f2108a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public long d() {
        return this.d;
    }

    public void b(long j) {
        this.d = j;
    }
}
