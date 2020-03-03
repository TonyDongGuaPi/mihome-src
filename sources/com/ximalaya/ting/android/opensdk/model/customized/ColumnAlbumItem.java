package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ColumnAlbumItem {

    /* renamed from: a  reason: collision with root package name */
    private int f2063a;
    private String b;
    @SerializedName("content_type")
    private int c;
    @SerializedName("order_num")
    private int d;
    private String e;
    @SerializedName("category_id")
    private int f;
    private String g;
    @SerializedName("cover_url_original")
    private String h;
    @SerializedName("cover_url_small")
    private String i;
    @SerializedName("cover_url_middle")
    private String j;
    @SerializedName("cover_url_large")
    private String k;
    @SerializedName("play_count")
    private int l;
    @SerializedName("channel_play_count")
    private int m;
    @SerializedName("include_track_count")
    private int n;
    @SerializedName("is_finished")
    private int o;
    @SerializedName("created_at")
    private long p;
    @SerializedName("updated_at")
    private long q;
    @SerializedName("publish_at")
    private long r;
    @SerializedName("dimensions")
    private List<CustomizedDimension> s;
    @SerializedName("is_paid")
    private int t;

    public int a() {
        return this.f2063a;
    }

    public void a(int i2) {
        this.f2063a = i2;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public int d() {
        return this.d;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public String e() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public int f() {
        return this.f;
    }

    public void d(int i2) {
        this.f = i2;
    }

    public String g() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public String j() {
        return this.j;
    }

    public void f(String str) {
        this.j = str;
    }

    public String k() {
        return this.k;
    }

    public void g(String str) {
        this.k = str;
    }

    public int l() {
        return this.l;
    }

    public void e(int i2) {
        this.l = i2;
    }

    public int m() {
        return this.m;
    }

    public void f(int i2) {
        this.m = i2;
    }

    public int n() {
        return this.n;
    }

    public void g(int i2) {
        this.n = i2;
    }

    public int o() {
        return this.o;
    }

    public void h(int i2) {
        this.o = i2;
    }

    public long p() {
        return this.p;
    }

    public void a(long j2) {
        this.p = j2;
    }

    public long q() {
        return this.q;
    }

    public void b(long j2) {
        this.q = j2;
    }

    public long r() {
        return this.r;
    }

    public void c(long j2) {
        this.r = j2;
    }

    public List<CustomizedDimension> s() {
        return this.s;
    }

    public void a(List<CustomizedDimension> list) {
        this.s = list;
    }

    public int t() {
        return this.t;
    }

    public void i(int i2) {
        this.t = i2;
    }
}
