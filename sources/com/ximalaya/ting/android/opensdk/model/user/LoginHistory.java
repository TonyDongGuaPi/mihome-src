package com.ximalaya.ting.android.opensdk.model.user;

import com.google.gson.annotations.SerializedName;

class LoginHistory {
    @SerializedName("cty_code")

    /* renamed from: a  reason: collision with root package name */
    private String f2129a;
    @SerializedName("login_times")
    private String b;

    LoginHistory() {
    }

    public String a() {
        return this.f2129a;
    }

    public void a(String str) {
        this.f2129a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
