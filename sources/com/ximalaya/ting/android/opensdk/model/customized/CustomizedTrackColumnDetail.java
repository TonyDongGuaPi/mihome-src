package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class CustomizedTrackColumnDetail extends CustomizedTrack {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2072a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("customized_track_column_items")
    private List<ColumnItems> d;

    public int o() {
        return this.f2072a;
    }

    public void e(int i) {
        this.f2072a = i;
    }

    public int p() {
        return this.b;
    }

    public void f(int i) {
        this.b = i;
    }

    public int q() {
        return this.c;
    }

    public void g(int i) {
        this.c = i;
    }

    public List<ColumnItems> r() {
        return this.d;
    }

    public void b(List<ColumnItems> list) {
        this.d = list;
    }

    public String toString() {
        return "CustomizedTrackColumnDetail{columnItemses=" + this.d + Operators.BLOCK_END;
    }
}
