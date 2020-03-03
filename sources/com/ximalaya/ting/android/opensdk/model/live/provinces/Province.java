package com.ximalaya.ting.android.opensdk.model.live.provinces;

import com.google.gson.annotations.SerializedName;

public class Province {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2083a;
    @SerializedName("province_code")
    private long b;
    @SerializedName("province_name")
    private String c;
    @SerializedName("created_at")
    private long d;

    public long a() {
        return this.f2083a;
    }

    public void a(long j) {
        this.f2083a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public long d() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }
}
