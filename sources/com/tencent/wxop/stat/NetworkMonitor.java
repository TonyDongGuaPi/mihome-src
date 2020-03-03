package com.tencent.wxop.stat;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkMonitor {

    /* renamed from: a  reason: collision with root package name */
    private long f9262a = 0;
    private int b = 0;
    private String c = "";
    private int d = 0;
    private String e = "";

    public long a() {
        return this.f9262a;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(long j) {
        this.f9262a = j;
    }

    public void a(String str) {
        this.c = str;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.d = i;
    }

    public void b(String str) {
        this.e = str;
    }

    public String c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tm", this.f9262a);
            jSONObject.put("st", this.b);
            if (this.c != null) {
                jSONObject.put("dm", this.c);
            }
            jSONObject.put("pt", this.d);
            if (this.e != null) {
                jSONObject.put("rip", this.e);
            }
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
