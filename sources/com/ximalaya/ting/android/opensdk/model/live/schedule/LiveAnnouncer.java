package com.ximalaya.ting.android.opensdk.model.live.schedule;

import com.google.gson.annotations.SerializedName;

public class LiveAnnouncer {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2092a;
    @SerializedName("nickname")
    private String b;
    @SerializedName("avatar_url")
    private String c;

    public long a() {
        return this.f2092a;
    }

    public void a(long j) {
        this.f2092a = j;
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
