package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class CustomizedAlbumColumnDetail extends CustomizedAlbum {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2066a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("customized_album_column_items")
    private List<ColumnAlbumItem> d;

    public List<ColumnAlbumItem> n() {
        return this.d;
    }

    public void b(List<ColumnAlbumItem> list) {
        this.d = list;
    }

    public int o() {
        return this.f2066a;
    }

    public void d(int i) {
        this.f2066a = i;
    }

    public int p() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public int q() {
        return this.c;
    }

    public void f(int i) {
        this.c = i;
    }

    public String toString() {
        return "CustomizedAlbumColumnDetail{columnItemses=" + this.d + Operators.BLOCK_END;
    }
}
