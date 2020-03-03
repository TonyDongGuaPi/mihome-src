package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum;
import java.util.List;

public class ColumnItems extends XimalayaResponse {
    @SerializedName("download_url")
    private String A;
    @SerializedName("duration")
    private int B;
    @SerializedName("subordinated_album")
    private SubordinatedAlbum C;
    @SerializedName("publish_at")
    private long D;
    @SerializedName("is_finished")
    private int E;
    @SerializedName("dimensions")
    private List<CustomizedDimension> F;
    @SerializedName("short_ext_info")
    private String G;

    /* renamed from: a  reason: collision with root package name */
    private int f2064a;
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
    @SerializedName("cover_url_large")
    private String j;
    @SerializedName("cover_url_middle")
    private String k;
    @SerializedName("play_count")
    private int l;
    @SerializedName("channel_play_count")
    private int m;
    @SerializedName("include_track_count")
    private int n;
    @SerializedName("play_size_32")
    private long o;
    @SerializedName("play_size_64")
    private long p;
    @SerializedName("play_size_24_m4a")
    private long q;
    @SerializedName("play_size_64_m4a")
    private long r;
    @SerializedName("can_download")
    private boolean s;
    @SerializedName("download_size")
    private int t;
    @SerializedName("created_at")
    private long u;
    @SerializedName("updated_at")
    private long v;
    @SerializedName("play_url_32")
    private String w;
    @SerializedName("play_url_64")
    private String x;
    @SerializedName("play_url_24_m4a")
    private String y;
    @SerializedName("play_url_64_m4a")
    private String z;

    public int a() {
        return this.f2064a;
    }

    public void a(int i2) {
        this.f2064a = i2;
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
        return this.j;
    }

    public void e(String str) {
        this.j = str;
    }

    public String j() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public int k() {
        return this.l;
    }

    public void e(int i2) {
        this.l = i2;
    }

    public int l() {
        return this.m;
    }

    public void f(int i2) {
        this.m = i2;
    }

    public int m() {
        return this.n;
    }

    public void g(int i2) {
        this.n = i2;
    }

    public long n() {
        return this.o;
    }

    public void a(long j2) {
        this.o = j2;
    }

    public long o() {
        return this.p;
    }

    public void b(long j2) {
        this.p = j2;
    }

    public long p() {
        return this.q;
    }

    public void c(long j2) {
        this.q = j2;
    }

    public long q() {
        return this.r;
    }

    public void d(long j2) {
        this.r = j2;
    }

    public boolean r() {
        return this.s;
    }

    public void a(boolean z2) {
        this.s = z2;
    }

    public int s() {
        return this.t;
    }

    public void h(int i2) {
        this.t = i2;
    }

    public long t() {
        return this.u;
    }

    public void e(long j2) {
        this.u = j2;
    }

    public long u() {
        return this.v;
    }

    public void f(long j2) {
        this.v = j2;
    }

    public String v() {
        return this.w;
    }

    public void g(String str) {
        this.w = str;
    }

    public String w() {
        return this.x;
    }

    public void h(String str) {
        this.x = str;
    }

    public String x() {
        return this.y;
    }

    public void i(String str) {
        this.y = str;
    }

    public String y() {
        return this.z;
    }

    public void j(String str) {
        this.z = str;
    }

    public String z() {
        return this.A;
    }

    public void k(String str) {
        this.A = str;
    }

    public String toString() {
        return "ColumnItems{id=" + this.f2064a + ", kind='" + this.b + Operators.SINGLE_QUOTE + ", contentType=" + this.c + ", orderNum=" + this.d + ", title='" + this.e + Operators.SINGLE_QUOTE + ", categoryId=" + this.f + ", intro='" + this.g + Operators.SINGLE_QUOTE + ", coverUrlOriginal='" + this.h + Operators.SINGLE_QUOTE + ", coverUrlLarge='" + this.j + Operators.SINGLE_QUOTE + ", coverUrlMiddle='" + this.k + Operators.SINGLE_QUOTE + ", playCount=" + this.l + ", channelPlayCount=" + this.m + ", includeTrackCount=" + this.n + ", playSize32=" + this.o + ", playSize64=" + this.p + ", playSize24M4a=" + this.q + ", playSize64M4=" + this.r + ", canDownload=" + this.s + ", downloadSize=" + this.t + ", createdAt=" + this.u + ", updatedAt=" + this.v + ", playUrl32='" + this.w + Operators.SINGLE_QUOTE + ", playUrl64='" + this.x + Operators.SINGLE_QUOTE + ", playUrl24M4a='" + this.y + Operators.SINGLE_QUOTE + ", playUrl64M4a='" + this.z + Operators.SINGLE_QUOTE + ", downloadUrl='" + this.A + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public String A() {
        return this.i;
    }

    public void l(String str) {
        this.i = str;
    }

    public int B() {
        return this.B;
    }

    public void i(int i2) {
        this.B = i2;
    }

    public SubordinatedAlbum C() {
        return this.C;
    }

    public void a(SubordinatedAlbum subordinatedAlbum) {
        this.C = subordinatedAlbum;
    }

    public long D() {
        return this.D;
    }

    public void g(long j2) {
        this.D = j2;
    }

    public int E() {
        return this.E;
    }

    public void j(int i2) {
        this.E = i2;
    }

    public List<CustomizedDimension> F() {
        return this.F;
    }

    public void a(List<CustomizedDimension> list) {
        this.F = list;
    }

    public String G() {
        return this.G;
    }

    public void m(String str) {
        this.G = str;
    }
}
