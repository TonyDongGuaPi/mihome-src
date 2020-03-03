package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CustomizedTrack extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2071a;
    private String b;
    @SerializedName("column_title")
    private String c;
    @SerializedName("column_intro")
    private String d;
    @SerializedName("column_content_count")
    private int e;
    @SerializedName("channel_play_count")
    private int f;
    @SerializedName("created_at")
    private long g;
    @SerializedName("updated_at")
    private long h;
    @SerializedName("cover_url_original")
    private String i;
    @SerializedName("cover_url_large")
    private String j;
    @SerializedName("cover_url_middle")
    private String k;
    @SerializedName("cover_url_small")
    private String l;
    @SerializedName("dimensions")
    private List<CustomizedDimension> m;
    @SerializedName("order_num")
    private int n;

    public int a() {
        return this.f2071a;
    }

    public void a(int i2) {
        this.f2071a = i2;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
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

    public int f() {
        return this.f;
    }

    public void c(int i2) {
        this.f = i2;
    }

    public long g() {
        return this.g;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public long h() {
        return this.h;
    }

    public void b(long j2) {
        this.h = j2;
    }

    public String i() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public String j() {
        return this.j;
    }

    public void e(String str) {
        this.j = str;
    }

    public String k() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public String l() {
        return this.l;
    }

    public void g(String str) {
        this.l = str;
    }

    public String toString() {
        return "CustomizedTrack{id=" + this.f2071a + ", kind='" + this.b + Operators.SINGLE_QUOTE + ", columnTitle='" + this.c + Operators.SINGLE_QUOTE + ", columnIntro='" + this.d + Operators.SINGLE_QUOTE + ", columnContentCount=" + this.e + ", channelPlayCount=" + this.f + ", createdAt=" + this.g + ", updatedAt=" + this.h + ", coverUrlOriginal='" + this.i + Operators.SINGLE_QUOTE + ", coverUrlLarge='" + this.j + Operators.SINGLE_QUOTE + ", coverUrlMiddle='" + this.k + Operators.SINGLE_QUOTE + ", coverUrlSmall='" + this.l + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public List<CustomizedDimension> m() {
        return this.m;
    }

    public void a(List<CustomizedDimension> list) {
        this.m = list;
    }

    public int n() {
        return this.n;
    }

    public void d(int i2) {
        this.n = i2;
    }
}
