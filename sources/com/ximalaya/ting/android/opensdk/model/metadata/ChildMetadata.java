package com.ximalaya.ting.android.opensdk.model.metadata;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ChildMetadata {
    @SerializedName("display_name")

    /* renamed from: a  reason: collision with root package name */
    private String f2096a;
    private String b;
    private List<ChildAttributes> c;

    public String a() {
        return this.f2096a;
    }

    public void a(String str) {
        this.f2096a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public List<ChildAttributes> c() {
        return this.c;
    }

    public void a(List<ChildAttributes> list) {
        this.c = list;
    }
}
