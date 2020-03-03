package com.ximalaya.ting.android.opensdk.player.advertis;

import com.google.gson.annotations.SerializedName;

public class XmAdsRecord {

    /* renamed from: a  reason: collision with root package name */
    private String f2178a = "soundShow";
    private long b;
    private long c;
    private String d;
    private long e;
    private long f;
    private int g;
    private String h = "sound_patch";
    private int i;
    private String j;
    @SerializedName("rec_src")
    private String k;
    @SerializedName("rec_track")
    private String l;
    private int m;
    private String n;
    private int o;
    private int p;
    private int q;
    private int r;
    private String s;
    private String t;
    private int u;

    public String a() {
        return this.s;
    }

    public void a(String str) {
        this.s = str;
    }

    public int b() {
        return this.o;
    }

    public void a(int i2) {
        this.o = i2;
    }

    public int c() {
        return this.p;
    }

    public void b(int i2) {
        this.p = i2;
    }

    public int d() {
        return this.q;
    }

    public void c(int i2) {
        this.q = i2;
    }

    public int e() {
        return this.r;
    }

    public void d(int i2) {
        this.r = i2;
    }

    public int f() {
        return this.i;
    }

    public void e(int i2) {
        this.i = i2;
    }

    public String g() {
        return this.k;
    }

    public void b(String str) {
        this.k = str;
    }

    public String h() {
        return this.l;
    }

    public void c(String str) {
        this.l = str;
    }

    public int i() {
        return this.m;
    }

    public void f(int i2) {
        this.m = i2;
    }

    public int hashCode() {
        return 31 + ((int) (this.f ^ (this.f >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f == ((XmAdsRecord) obj).f;
    }

    public String j() {
        return this.f2178a;
    }

    public void d(String str) {
        this.f2178a = str;
    }

    public long k() {
        return this.b;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public long l() {
        return this.c;
    }

    public void b(long j2) {
        this.c = j2;
    }

    public String m() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public long n() {
        return this.e;
    }

    public void c(long j2) {
        this.e = j2;
    }

    public long o() {
        return this.f;
    }

    public void d(long j2) {
        this.f = j2;
    }

    public int p() {
        return this.g;
    }

    public void g(int i2) {
        this.g = i2;
    }

    public String q() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public String r() {
        return this.n;
    }

    public void g(String str) {
        this.n = str;
    }

    public String s() {
        return this.t;
    }

    public void h(String str) {
        this.t = str;
    }

    public String t() {
        return this.j;
    }

    public void i(String str) {
        this.j = str;
    }

    public int u() {
        return this.u;
    }

    public void h(int i2) {
        this.u = i2;
    }
}
