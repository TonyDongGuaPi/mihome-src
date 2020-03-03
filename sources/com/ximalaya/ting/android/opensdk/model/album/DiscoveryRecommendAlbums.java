package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class DiscoveryRecommendAlbums extends XimalayaResponse {
    @SerializedName("category_id")

    /* renamed from: a  reason: collision with root package name */
    private String f2018a;
    @SerializedName("display_category_name")
    private String b;
    @SerializedName("category_name")
    private String c;
    @SerializedName("need_show_high_quality_tag")
    private boolean d;
    @SerializedName("albums")
    private List<Album> e;

    public String a() {
        return this.f2018a;
    }

    public void a(String str) {
        this.f2018a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public List<Album> d() {
        return this.e;
    }

    public void a(List<Album> list) {
        this.e = list;
    }

    public boolean e() {
        return this.d;
    }
}
