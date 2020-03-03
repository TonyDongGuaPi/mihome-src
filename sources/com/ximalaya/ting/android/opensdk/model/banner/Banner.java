package com.ximalaya.ting.android.opensdk.model.banner;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class Banner extends XimalayaResponse {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2040a;
    @SerializedName("banner_title")
    private String b;
    @SerializedName("banner_short_title")
    private String c;
    @SerializedName("banner_url")
    private String d;
    @SerializedName("banner_redirect_url")
    private String e;
    @SerializedName("can_share")
    private boolean f;
    @SerializedName("banner_content_type")
    private int g;
    @SerializedName("banner_uid")
    private int h;
    @SerializedName("track_id")
    private long i;
    @SerializedName("column_id")
    private int j;
    @SerializedName("column_content_type")
    private String k;
    @SerializedName("album_id")
    private long l;
    @SerializedName("third_party_url")
    private String m;
    @SerializedName("is_external_url")
    private boolean n;

    public long a() {
        return this.f2040a;
    }

    public void a(long j2) {
        this.f2040a = j2;
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

    public boolean f() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public int g() {
        return this.g;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public int h() {
        return this.h;
    }

    public void b(int i2) {
        this.h = i2;
    }

    public long i() {
        return this.i;
    }

    public void b(long j2) {
        this.i = j2;
    }

    public int j() {
        return this.j;
    }

    public void c(int i2) {
        this.j = i2;
    }

    public String k() {
        return this.k;
    }

    public void e(String str) {
        this.k = str;
    }

    public long l() {
        return this.l;
    }

    public void c(long j2) {
        this.l = j2;
    }

    public String m() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public String toString() {
        return "RankBanners [bannerId=" + this.f2040a + ", bannerTitle=" + this.b + ", bannerShortTitle=" + this.c + ", bannerUrl=" + this.d + ", bannerRedirectUrl=" + this.e + ", canShare=" + this.f + ", bannerContentType=" + this.g + ", bannerUid=" + this.h + ", trackId=" + this.i + ", columnId=" + this.j + ", columnContentType=" + this.k + ", albumId=" + this.l + ", thirdPartyUrl=" + this.m + ", isExternalUrl=" + this.n + Operators.ARRAY_END_STR;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public boolean n() {
        return this.n;
    }
}
