package com.ximalaya.ting.android.opensdk.model.tag;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class Tag {
    @SerializedName("tag_name")

    /* renamed from: a  reason: collision with root package name */
    private String f2116a;
    @SerializedName("kind")
    private String b;

    public String a() {
        return this.f2116a;
    }

    public void a(String str) {
        this.f2116a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String toString() {
        return "Tag{tagName='" + this.f2116a + Operators.SINGLE_QUOTE + ", kind='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
