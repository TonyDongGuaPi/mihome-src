package com.ximalaya.ting.android.opensdk.model.history;

import com.google.gson.annotations.SerializedName;

public class PlayHistoryAlbum {
    @SerializedName("album_id")

    /* renamed from: a  reason: collision with root package name */
    private long f2077a;
    @SerializedName("album_title")
    private String b;
    @SerializedName("album_cover_url_small")
    private String c;
    @SerializedName("album_cover_url_middle")
    private String d;
    @SerializedName("album_cover_url_large")
    private String e;
    @SerializedName("track_id")
    private long f;
    @SerializedName("track_title")
    private String g;
    @SerializedName("track_cover_url_small")
    private String h;
    @SerializedName("track_cover_url_middle")
    private String i;
    @SerializedName("track_cover_url_large")
    private String j;

    public long a() {
        return this.f2077a;
    }

    public void a(long j2) {
        this.f2077a = j2;
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

    public long f() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public String g() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void g(String str) {
        this.i = str;
    }

    public String j() {
        return this.j;
    }

    public void h(String str) {
        this.j = str;
    }
}
