package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RadioList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Radio> f2089a;
    @SerializedName("total_page")
    private int b;
    @SerializedName("total_count")
    private int c;
    @SerializedName("current_page")
    private int d;

    public List<Radio> a() {
        return this.f2089a;
    }

    public void a(List<Radio> list) {
        this.f2089a = list;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }
}
