package com.ximalaya.ting.android.opensdk.model.coldboot;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ColdBootDetail extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private String f2053a;
    @SerializedName("coldboot_genre")
    private String b;
    @SerializedName("coldboot_sub_genre")
    private String c;
    @SerializedName("device_type")
    private String d;
    @SerializedName("device_id")
    private String e;
    @SerializedName("coldboot_tags")
    private List<String> f;

    public String a() {
        return this.f2053a;
    }

    public void a(String str) {
        this.f2053a = str;
    }

    public String b() {
        return this.b;
    }

    public List<String> c() {
        return this.f;
    }

    public void a(List<String> list) {
        this.f = list;
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

    public String e() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String f() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }
}
