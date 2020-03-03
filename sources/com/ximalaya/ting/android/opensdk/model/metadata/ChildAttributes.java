package com.ximalaya.ting.android.opensdk.model.metadata;

import com.google.gson.annotations.SerializedName;

public class ChildAttributes {
    @SerializedName("attr_key")

    /* renamed from: a  reason: collision with root package name */
    private String f2095a;
    @SerializedName("attr_value")
    private String b;
    @SerializedName("display_name")
    private String c;

    public String b() {
        return this.f2095a;
    }

    public void a(String str) {
        this.f2095a = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }
}
