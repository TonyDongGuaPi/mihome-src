package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DiscoverExtendedBean {
    @SerializedName("add_time")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Long f13168a;
    @SerializedName("gallery")
    @Expose
    private List<DiscoverExtendedGalleryBean> b = null;
    @SerializedName("haslike")
    @Expose
    private Boolean c;
    @SerializedName("like_cnt")
    @Expose
    private String d;
    @SerializedName("material_id")
    @Expose
    private String e;
    @SerializedName("view_cnt")
    @Expose
    private String f;

    public Long a() {
        return this.f13168a;
    }

    public void a(Long l) {
        this.f13168a = l;
    }

    public List<DiscoverExtendedGalleryBean> b() {
        return this.b;
    }

    public void a(List<DiscoverExtendedGalleryBean> list) {
        this.b = list;
    }

    public Boolean c() {
        return this.c;
    }

    public void a(Boolean bool) {
        this.c = bool;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }
}
