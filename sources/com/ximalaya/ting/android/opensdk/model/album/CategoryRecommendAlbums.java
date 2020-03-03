package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CategoryRecommendAlbums extends XimalayaResponse {
    @SerializedName("category_id")

    /* renamed from: a  reason: collision with root package name */
    private int f2016a;
    @SerializedName("display_tag_name")
    private String b;
    @SerializedName("tag_name")
    private String c;
    @SerializedName("has_more")
    private boolean d;
    @SerializedName("albums")
    private List<Album> e;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public boolean c() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public List<Album> d() {
        return this.e;
    }

    public void a(List<Album> list) {
        this.e = list;
    }

    public int e() {
        return this.f2016a;
    }

    public void a(int i) {
        this.f2016a = i;
    }
}
