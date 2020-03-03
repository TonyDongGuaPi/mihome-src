package com.xiaomi.jr.ui.menu;

import com.google.gson.annotations.SerializedName;

public class MenuItemConfig {
    @SerializedName("event")

    /* renamed from: a  reason: collision with root package name */
    private String f11036a;
    @SerializedName("icon")
    private String b;
    @SerializedName("title")
    private String c;

    public String a() {
        return this.f11036a;
    }

    public void a(String str) {
        this.f11036a = str;
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
}
