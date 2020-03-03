package com.xiaomi.miot.store.verify.poji;

import com.google.gson.annotations.SerializedName;

public class RegisterSessionData {
    @SerializedName("eventId")

    /* renamed from: a  reason: collision with root package name */
    private String f11437a;
    @SerializedName("clientId")
    private String b;

    public String a() {
        return this.f11437a;
    }

    public void a(String str) {
        this.f11437a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
