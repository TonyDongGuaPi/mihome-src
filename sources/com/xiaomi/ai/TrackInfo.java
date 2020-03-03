package com.xiaomi.ai;

import org.json.JSONObject;

public class TrackInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9907a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = -1;
    public static final int f = -2;
    private String g = "0.2";
    private String h;
    private String i;
    private String j;
    private long k = -1;
    private long l = -1;
    private long m;
    private long n = -1;
    private int o = -1;
    private JSONObject p = null;

    public TrackInfo a(int i2) {
        this.o = i2;
        return this;
    }

    public TrackInfo a(long j2) {
        this.k = j2;
        return this;
    }

    public TrackInfo a(String str) {
        this.g = str;
        return this;
    }

    public JSONObject a() {
        return this.p;
    }

    public void a(JSONObject jSONObject) {
        this.p = jSONObject;
    }

    public TrackInfo b(long j2) {
        this.l = j2;
        return this;
    }

    public TrackInfo b(String str) {
        this.h = str;
        return this;
    }

    public String b() {
        return this.g;
    }

    public TrackInfo c(long j2) {
        this.m = j2;
        return this;
    }

    public TrackInfo c(String str) {
        this.i = str;
        return this;
    }

    public String c() {
        return this.h;
    }

    public TrackInfo d(long j2) {
        this.n = j2;
        return this;
    }

    public TrackInfo d(String str) {
        this.j = str;
        return this;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        return this.j;
    }

    public long f() {
        return this.k;
    }

    public long g() {
        return this.l;
    }

    public long h() {
        return this.m;
    }

    public long i() {
        return this.n;
    }

    public int j() {
        return this.o;
    }
}
