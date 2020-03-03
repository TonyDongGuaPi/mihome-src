package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import java.util.List;

public class ColumnDetailAlbum extends ColumnDetail {
    @SerializedName("column_items")

    /* renamed from: a  reason: collision with root package name */
    private List<Album> f2059a;

    public List<Album> g() {
        return this.f2059a;
    }

    public void a(List<Album> list) {
        this.f2059a = list;
    }

    public String toString() {
        return "ColumnDetailAlbum [albumList=" + this.f2059a + Operators.ARRAY_END_STR;
    }
}
