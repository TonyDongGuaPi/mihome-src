package com.xiaomi.jr.http.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.http.model.adapter.MiFiResponseTypeAdapterFactory;

@JsonAdapter(MiFiResponseTypeAdapterFactory.class)
public class MiFiResponse<T> extends RawResponse {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private int f1435a;
    @SerializedName("error")
    private String b;
    @SerializedName("success")
    private boolean c;
    @SerializedName("value")
    private T d;

    public int a() {
        return this.f1435a;
    }

    public void a(int i) {
        this.f1435a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean c() {
        return this.c;
    }

    public void a(Boolean bool) {
        this.c = bool.booleanValue();
    }

    public T d() {
        return this.d;
    }

    public void a(T t) {
        this.d = t;
    }
}
