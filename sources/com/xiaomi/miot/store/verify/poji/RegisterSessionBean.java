package com.xiaomi.miot.store.verify.poji;

import com.google.gson.annotations.SerializedName;

public class RegisterSessionBean {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private int f11436a;
    @SerializedName("message")
    private String b;
    @SerializedName("description")
    private String c;
    @SerializedName("data")
    private RegisterSessionData d;

    public int a() {
        return this.f11436a;
    }

    public void a(int i) {
        this.f11436a = i;
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

    public RegisterSessionData d() {
        return this.d;
    }

    public void a(RegisterSessionData registerSessionData) {
        this.d = registerSessionData;
    }
}
