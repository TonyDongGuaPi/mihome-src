package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;

public class CustomizedDimension {
    @SerializedName("dim_id")

    /* renamed from: a  reason: collision with root package name */
    private int f2068a;
    @SerializedName("dim_name")
    private String b;
    @SerializedName("dim_value")
    private String c;

    public int a() {
        return this.f2068a;
    }

    public void a(int i) {
        this.f2068a = i;
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
}
