package com.ximalaya.ting.android.opensdk.datatrasfer;

import com.google.gson.annotations.SerializedName;

public class DataErrorCategory {
    @SerializedName("error_no")

    /* renamed from: a  reason: collision with root package name */
    private int f1982a;
    @SerializedName("error_code")
    private String b;
    @SerializedName("error_desc")
    private String c;

    public int a() {
        return this.f1982a;
    }

    public void a(int i) {
        this.f1982a = i;
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
