package com.ximalaya.ting.android.opensdk.model.word;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class AlbumResult {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2132a;
    @SerializedName("album_title")
    private String b;
    @SerializedName("highlight_album_title")
    private String c;
    @SerializedName("category_name")
    private String d;
    @SerializedName("cover_url_small")
    private String e;

    public long a() {
        return this.f2132a;
    }

    public void a(long j) {
        this.f2132a = j;
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

    public String toString() {
        return "AlbumResult [albumId=" + this.f2132a + ", albumTitle=" + this.b + ", hightlightAlbumTitle=" + this.c + ", categoryName=" + this.d + ", coverUrlSmall=" + this.e + Operators.ARRAY_END_STR;
    }
}
