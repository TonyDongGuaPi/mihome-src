package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CustomizedSearch {

    /* renamed from: a  reason: collision with root package name */
    private long f2069a;
    private String b;
    private int c;
    private String d;
    @SerializedName("cover_url_original")
    private String e;
    @SerializedName("cover_url_small")
    private String f;
    @SerializedName("cover_url_middle")
    private String g;
    @SerializedName("cover_url_large")
    private String h;
    @SerializedName("play_count")
    private int i;
    @SerializedName("channel_play_count")
    private int j;
    @SerializedName("include_track_count")
    private int k;
    @SerializedName("publish_at")
    private long l;
    @SerializedName("is_finished")
    private int m;
    @SerializedName("dimensions")
    private List<CustomizedDimension> n;
    @SerializedName("created_at")
    private long o;
    @SerializedName("updated_at")
    private long p;
    @SerializedName("is_paid")
    private int q;

    public long a() {
        return this.f2069a;
    }

    public void a(long j2) {
        this.f2069a = j2;
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

    public void a(int i2) {
        this.c = i2;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
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

    public int i() {
        return this.i;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public int j() {
        return this.j;
    }

    public void c(int i2) {
        this.j = i2;
    }

    public int k() {
        return this.k;
    }

    public void d(int i2) {
        this.k = i2;
    }

    public long l() {
        return this.l;
    }

    public void b(long j2) {
        this.l = j2;
    }

    public int m() {
        return this.m;
    }

    public void e(int i2) {
        this.m = i2;
    }

    public List<CustomizedDimension> n() {
        return this.n;
    }

    public void a(List<CustomizedDimension> list) {
        this.n = list;
    }

    public long o() {
        return this.o;
    }

    public void c(long j2) {
        this.o = j2;
    }

    public long p() {
        return this.p;
    }

    public void d(long j2) {
        this.p = j2;
    }

    public int q() {
        return this.q;
    }

    public void f(int i2) {
        this.q = i2;
    }
}
