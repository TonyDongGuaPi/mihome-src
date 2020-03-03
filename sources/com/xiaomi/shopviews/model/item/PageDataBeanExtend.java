package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PageDataBeanExtend {
    @SerializedName("nav_list_background_img")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public String f13218a;
    @SerializedName("nav_list")
    @Expose
    public List<NavList> b = null;

    public String a() {
        return this.f13218a;
    }

    public void a(String str) {
        this.f13218a = str;
    }

    public List<NavList> b() {
        return this.b;
    }

    public void a(List<NavList> list) {
        this.b = list;
    }

    public static class NavList {
        @SerializedName("view_id")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        public String f13219a;
        @SerializedName("image_url")
        @Expose
        public String b;
        @SerializedName("go_to_url")
        @Expose
        public String c;
        @SerializedName("title")
        @Expose
        public String d;
        @SerializedName("button_color")
        @Expose
        public String e;
        @SerializedName("page_id")
        @Expose
        public String f;

        public String a() {
            return this.f13219a;
        }

        public void a(String str) {
            this.f13219a = str;
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

        public String d() {
            return this.d;
        }

        public void d(String str) {
            this.d = str;
        }

        public String e() {
            return this.e;
        }

        public void e(String str) {
            this.e = str;
        }
    }
}
