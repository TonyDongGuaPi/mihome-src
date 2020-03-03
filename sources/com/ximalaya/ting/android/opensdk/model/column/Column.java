package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class Column {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2057a;
    @SerializedName("column_title")
    private String b;
    @SerializedName("column_sub_title")
    private String c;
    @SerializedName("column_foot_note")
    private String d;
    @SerializedName("column_content_type")
    private int e;
    @SerializedName("cover_url_small")
    private String f;
    @SerializedName("cover_url_large")
    private String g;
    @SerializedName("released_at")
    private long h;
    @SerializedName("is_hot")
    private boolean i;

    public long a() {
        return this.f2057a;
    }

    public void a(long j) {
        this.f2057a = j;
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

    public void a(int i2) {
        this.e = i2;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public long h() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public boolean i() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public String toString() {
        return "Column [columnId=" + this.f2057a + ", columnTitle=" + this.b + ", columnSubTitle=" + this.c + ", columnFootNote=" + this.d + ", columnContentType=" + this.e + ", coverUrlSmall=" + this.f + ", coverUrlLarge=" + this.g + ", releasedAt=" + this.h + ", isHot=" + this.i + Operators.ARRAY_END_STR;
    }
}
