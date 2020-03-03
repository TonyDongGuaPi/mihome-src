package com.ximalaya.ting.android.opensdk.model.history;

import com.google.gson.annotations.SerializedName;

public class PlayHistoryRadio {
    @SerializedName("radio_id")

    /* renamed from: a  reason: collision with root package name */
    private String f2079a;
    @SerializedName("radio_name")
    private String b;
    @SerializedName("radio_cover_url_small")
    private String c;
    @SerializedName("radio_cover_url_Large")
    private String d;
    @SerializedName("schedule_id")
    private long e;
    @SerializedName("program_name")
    private String f;

    public String a() {
        return this.f2079a;
    }

    public void a(String str) {
        this.f2079a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public String f() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }
}
