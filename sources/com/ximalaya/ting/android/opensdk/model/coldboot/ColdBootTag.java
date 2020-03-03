package com.ximalaya.ting.android.opensdk.model.coldboot;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ColdBootTag extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private String f2054a;
    @SerializedName("coldboot_genre")
    private String b;
    @SerializedName("coldboot_sub_genre")
    private String c;
    @SerializedName("coldboot_tags")
    private List<String> d;

    public String a() {
        return this.f2054a;
    }

    public void a(String str) {
        this.f2054a = str;
    }

    public String b() {
        return this.b;
    }

    public List<String> c() {
        return this.d;
    }

    public void a(List<String> list) {
        this.d = list;
    }

    public void b(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }
}
