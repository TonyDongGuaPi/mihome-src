package com.ximalaya.ting.android.opensdk.model.ranks;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class Rank {
    @SerializedName("rank_key")

    /* renamed from: a  reason: collision with root package name */
    private String f2109a;
    @SerializedName("rank_title")
    private String b;
    @SerializedName("rank_type")
    private int c;
    @SerializedName("rank_sub_title")
    private String d;
    @SerializedName("rank_period")
    private int e;
    @SerializedName("rank_period_type")
    private String f;
    @SerializedName("rank_item_num")
    private int g;
    @SerializedName("rank_order_num")
    private int h;
    @SerializedName("cover_url")
    private String i;
    @SerializedName("category_id")
    private long j;
    @SerializedName("rank_content_type")
    private String k;
    @SerializedName("rank_first_item_id")
    private long l;
    @SerializedName("rank_first_item_title")
    private String m;
    @SerializedName("index_rank_items")
    private List<RankItem> n;

    public String a() {
        return this.f2109a;
    }

    public void a(String str) {
        this.f2109a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public int e() {
        return this.e;
    }

    public void b(int i2) {
        this.e = i2;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public int g() {
        return this.g;
    }

    public void c(int i2) {
        this.g = i2;
    }

    public int h() {
        return this.h;
    }

    public void d(int i2) {
        this.h = i2;
    }

    public String i() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public long j() {
        return this.j;
    }

    public void a(long j2) {
        this.j = j2;
    }

    public String k() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public long l() {
        return this.l;
    }

    public void b(long j2) {
        this.l = j2;
    }

    public String m() {
        return this.m;
    }

    public void g(String str) {
        this.m = str;
    }

    public List<RankItem> n() {
        return this.n;
    }

    public void a(List<RankItem> list) {
        this.n = list;
    }

    public String toString() {
        return "Rank [rankKey=" + this.f2109a + ", rankTitle=" + this.b + ", rankType=" + this.c + ", rankSubTitle=" + this.d + ", rankPeriod=" + this.e + ", rankPeriodType=" + this.f + ", rankItemNum=" + this.g + ", rankOrderNum=" + this.h + ", coverUrl=" + this.i + ", categoryId=" + this.j + ", rankCOntentType=" + this.k + ", rankFirstItemId=" + this.l + ", rankFirstItemTitle=" + this.m + ", rankItemList=" + this.n + Operators.ARRAY_END_STR;
    }
}
