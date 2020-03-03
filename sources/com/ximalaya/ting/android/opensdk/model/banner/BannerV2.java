package com.ximalaya.ting.android.opensdk.model.banner;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class BannerV2 extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2041a;
    private String b;
    @SerializedName("banner_title")
    private String c;
    @SerializedName("banner_short_title")
    private String d;
    @SerializedName("banner_url")
    private String e;
    @SerializedName("banner_content_type")
    private int f;
    @SerializedName("banner_uid")
    private int g;
    @SerializedName("album_id")
    private int h;
    @SerializedName("track_id")
    private int i;
    @SerializedName("column_id")
    private int j;

    public int a() {
        return this.f2041a;
    }

    public void a(int i2) {
        this.f2041a = i2;
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

    public int f() {
        return this.f;
    }

    public void b(int i2) {
        this.f = i2;
    }

    public int g() {
        return this.g;
    }

    public void c(int i2) {
        this.g = i2;
    }

    public int h() {
        return this.h;
    }

    public void d(int i2) {
        this.h = i2;
    }

    public int i() {
        return this.i;
    }

    public void e(int i2) {
        this.i = i2;
    }

    public int j() {
        return this.j;
    }

    public void f(int i2) {
        this.j = i2;
    }
}
