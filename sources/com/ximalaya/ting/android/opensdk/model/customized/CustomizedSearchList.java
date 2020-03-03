package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CustomizedSearchList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2070a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("customized_album_column_items")
    private List<CustomizedSearch> d;

    public List<CustomizedSearch> a() {
        return this.d;
    }

    public void a(List<CustomizedSearch> list) {
        this.d = list;
    }

    public int b() {
        return this.f2070a;
    }

    public void a(int i) {
        this.f2070a = i;
    }

    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }
}
