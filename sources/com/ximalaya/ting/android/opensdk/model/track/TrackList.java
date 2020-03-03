package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class TrackList extends CommonTrackList<Track> {
    @SerializedName("album_id")

    /* renamed from: a  reason: collision with root package name */
    private int f2128a;
    @SerializedName("album_title")
    private String b;
    @SerializedName("category_id")
    private int c;
    @SerializedName("album_intro")
    private String d;
    @SerializedName("cover_url_small")
    private String e;
    @SerializedName("cover_url_middle")
    private String f;
    @SerializedName("cover_url_large")
    private String g;
    @SerializedName("current_page")
    private int h;
    @SerializedName("can_download")
    private boolean i;

    public int a() {
        return this.f2128a;
    }

    public void a(int i2) {
        this.f2128a = i2;
    }

    public String g() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int h() {
        return this.c;
    }

    public void d(int i2) {
        this.c = i2;
    }

    public String i() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String j() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public String k() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public String l() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public int m() {
        return this.h;
    }

    public void e(int i2) {
        this.h = i2;
    }

    public String toString() {
        return "TrackList [albumId=" + this.f2128a + ", albumTitle=" + this.b + ", categoryId=" + this.c + ", albumIntro=" + this.d + ", coverUrlSmall=" + this.e + ", coverUrlMiddle=" + this.f + ", coverUrlLarge=" + this.g + ", currentPage=" + this.h + Operators.ARRAY_END_STR;
    }

    public boolean n() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }
}
