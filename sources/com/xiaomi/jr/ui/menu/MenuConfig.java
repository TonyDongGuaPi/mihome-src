package com.xiaomi.jr.ui.menu;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MenuConfig {
    @SerializedName("menu")

    /* renamed from: a  reason: collision with root package name */
    private List<MenuItemConfig> f11035a;

    public List<MenuItemConfig> a() {
        return this.f11035a;
    }

    public void a(List<MenuItemConfig> list) {
        this.f11035a = list;
    }
}
