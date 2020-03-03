package com.ximalaya.ting.android.opensdk.model.word;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class SuggestWords extends XimalayaResponse {
    @SerializedName("album_total_count")

    /* renamed from: a  reason: collision with root package name */
    private int f2136a;
    @SerializedName("albums")
    private List<AlbumResult> b;
    @SerializedName("keyword_total_count")
    private int c;
    @SerializedName("keywords")
    private List<QueryResult> d;

    public int a() {
        return this.f2136a;
    }

    public void a(int i) {
        this.f2136a = i;
    }

    public List<AlbumResult> b() {
        return this.b;
    }

    public void a(List<AlbumResult> list) {
        this.b = list;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public List<QueryResult> d() {
        return this.d;
    }

    public void b(List<QueryResult> list) {
        this.d = list;
    }

    public String toString() {
        return "SuggestWords [albumTotalCount=" + this.f2136a + ", albumList=" + this.b + ", keywordTotalCount=" + this.c + ", keyWordList=" + this.d + Operators.ARRAY_END_STR;
    }
}
