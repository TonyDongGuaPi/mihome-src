package com.xiaomi.ai;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceEvent {
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 201;

    /* renamed from: a  reason: collision with root package name */
    JSONObject f9902a;
    private String f;
    private int g;
    private String h;
    private int i = 0;

    public ServiceEvent(int i2, String str, String str2) {
        this.g = i2;
        this.f = str;
        this.h = str2;
    }

    public ServiceEvent(String str, JSONObject jSONObject) {
        this.f = str;
        this.f9902a = jSONObject;
        try {
            this.g = jSONObject.getInt("code");
            this.h = jSONObject.getString("msg");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject a() {
        return this.f9902a;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(JSONObject jSONObject) {
        this.f9902a = jSONObject;
    }

    public int b() {
        return this.g;
    }

    public void b(String str) {
        this.h = str;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.h;
    }

    public int e() {
        return this.i;
    }
}
