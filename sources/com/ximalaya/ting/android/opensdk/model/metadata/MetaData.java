package com.ximalaya.ting.android.opensdk.model.metadata;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MetaData {
    @SerializedName("display_name")

    /* renamed from: a  reason: collision with root package name */
    private String f2097a;
    private String b;
    private List<Attributes> c;

    public String a() {
        return this.f2097a;
    }

    public void a(String str) {
        this.f2097a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public List<Attributes> c() {
        return this.c;
    }

    public void a(List<Attributes> list) {
        this.c = list;
    }
}
