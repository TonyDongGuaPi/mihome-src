package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ColumnList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2062a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    private List<Column> d;

    public int a() {
        return this.f2062a;
    }

    public void a(int i) {
        this.f2062a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public List<Column> d() {
        return this.d;
    }

    public void a(List<Column> list) {
        this.d = list;
    }

    public String toString() {
        return "ColumnList [totalPage=" + this.f2062a + ", totalCount=" + this.b + ", currentPage=" + this.c + ", columns=" + this.d + Operators.ARRAY_END_STR;
    }
}
