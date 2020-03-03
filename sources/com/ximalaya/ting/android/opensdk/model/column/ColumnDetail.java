package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class ColumnDetail extends XimalayaResponse {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2058a;
    @SerializedName("column_intro")
    private String b;
    @SerializedName("column_content_type")
    private int c;
    @SerializedName("cover_url_large")
    private String d;
    @SerializedName("logo_small")
    private String e;
    @SerializedName("column_editor")
    private ColumnEditor f;

    public long a() {
        return this.f2058a;
    }

    public void a(long j) {
        this.f2058a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public ColumnEditor f() {
        return this.f;
    }

    public void a(ColumnEditor columnEditor) {
        this.f = columnEditor;
    }

    public String toString() {
        return "ColumnDetail [columnId=" + this.f2058a + ", columnIntro=" + this.b + ", columnContentType=" + this.c + ", coverUrlLarge=" + this.d + ", logoSmall=" + this.e + ", columnEditor=" + this.f + Operators.ARRAY_END_STR;
    }
}
