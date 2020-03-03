package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RadioListByCategory extends XimalayaResponse {
    @SerializedName("total_count")

    /* renamed from: a  reason: collision with root package name */
    private int f2090a;
    @SerializedName("total_page")
    private int b;
    @SerializedName("current_page")
    private int c;
    private List<Radio> d;

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public List<Radio> c() {
        return this.d;
    }

    public void a(List<Radio> list) {
        this.d = list;
    }

    public int d() {
        return this.f2090a;
    }

    public void c(int i) {
        this.f2090a = i;
    }
}
