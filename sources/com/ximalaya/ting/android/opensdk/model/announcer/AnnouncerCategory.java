package com.ximalaya.ting.android.opensdk.model.announcer;

import com.google.gson.annotations.SerializedName;

public class AnnouncerCategory {

    /* renamed from: a  reason: collision with root package name */
    private long f2036a;
    private String b;
    @SerializedName("vcategory_name")
    private String c;
    @SerializedName("order_num")
    private String d;

    public long a() {
        return this.f2036a;
    }

    public void a(long j) {
        this.f2036a = j;
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

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }
}
