package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.google.gson.annotations.SerializedName;

public class RadioCategory {

    /* renamed from: a  reason: collision with root package name */
    private long f2087a;
    private String b;
    @SerializedName("radio_category_name")
    private String c;
    @SerializedName("order_num")
    private int d;

    public long a() {
        return this.f2087a;
    }

    public void a(long j) {
        this.f2087a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }
}
