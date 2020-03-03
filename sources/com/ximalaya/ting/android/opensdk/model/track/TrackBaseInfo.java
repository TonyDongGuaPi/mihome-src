package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class TrackBaseInfo extends XimalayaResponse {
    @SerializedName("is_fallback")

    /* renamed from: a  reason: collision with root package name */
    private boolean f2124a;
    private long b;
    @SerializedName("e_play_url_24_m4a")
    private String c;
    @SerializedName("play_size_24_m4a")
    private int d;
    @SerializedName("e_play_url_64_m4a")
    private String e;
    @SerializedName("play_size_64_m4a")
    private int f;
    @SerializedName("e_play_url_amr")
    private String g;
    @SerializedName("play_size_amr")
    private int h;
    @SerializedName("can_download")
    private boolean i;
    @SerializedName("e_download_url")
    private String j;
    @SerializedName("download_size")
    private int k;
    @SerializedName("e_play_url_32")
    private String l;
    @SerializedName("play_size_32")
    private int m;
    @SerializedName("e_play_url_64")
    private String n;
    @SerializedName("play_size_64")
    private int o;

    public String a() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public int b() {
        return this.m;
    }

    public void a(int i2) {
        this.m = i2;
    }

    public String c() {
        return this.n;
    }

    public void b(String str) {
        this.n = str;
    }

    public int d() {
        return this.o;
    }

    public void b(int i2) {
        this.o = i2;
    }

    public boolean e() {
        return this.f2124a;
    }

    public void a(boolean z) {
        this.f2124a = z;
    }

    public long f() {
        return this.b;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public String g() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int h() {
        return this.d;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public String i() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public int j() {
        return this.f;
    }

    public void d(int i2) {
        this.f = i2;
    }

    public String k() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public int l() {
        return this.h;
    }

    public void e(int i2) {
        this.h = i2;
    }

    public boolean m() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public String n() {
        return this.j;
    }

    public void f(String str) {
        this.j = str;
    }

    public int o() {
        return this.k;
    }

    public void f(int i2) {
        this.k = i2;
    }

    public String toString() {
        return "TrackBaseInfo{isFallback=" + this.f2124a + ", playUrl24M4a='" + this.c + Operators.SINGLE_QUOTE + ", downloadUrl='" + this.j + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
