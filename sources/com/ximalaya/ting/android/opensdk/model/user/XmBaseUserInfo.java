package com.ximalaya.ting.android.opensdk.model.user;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class XmBaseUserInfo extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2130a;
    private String b;
    @SerializedName("nickname")
    private String c;
    @SerializedName("avatar_url")
    private String d;
    @SerializedName("is_verified")
    private String e;

    public int a() {
        return this.f2130a;
    }

    public void a(int i) {
        this.f2130a = i;
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

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }
}
