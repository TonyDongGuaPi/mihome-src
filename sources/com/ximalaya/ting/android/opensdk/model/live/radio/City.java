package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class City extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private long f2085a;
    private String b;
    @SerializedName("city_code")
    private int c;
    @SerializedName("city_name")
    private String d;

    public long a() {
        return this.f2085a;
    }

    public void a(long j) {
        this.f2085a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }
}
