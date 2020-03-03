package com.xiaomi.push;

import org.json.JSONObject;

public class cw {

    /* renamed from: a  reason: collision with root package name */
    private int f12681a;
    private long b;
    private long c;
    private String d;
    private long e;

    public cw() {
        this(0, 0, 0, (Exception) null);
    }

    public cw(int i, long j, long j2, Exception exc) {
        this.f12681a = i;
        this.b = j;
        this.e = j2;
        this.c = System.currentTimeMillis();
        if (exc != null) {
            this.d = exc.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.f12681a;
    }

    public cw a(JSONObject jSONObject) {
        this.b = jSONObject.getLong("cost");
        this.e = jSONObject.getLong("size");
        this.c = jSONObject.getLong("ts");
        this.f12681a = jSONObject.getInt("wt");
        this.d = jSONObject.optString("expt");
        return this;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.b);
        jSONObject.put("size", this.e);
        jSONObject.put("ts", this.c);
        jSONObject.put("wt", this.f12681a);
        jSONObject.put("expt", this.d);
        return jSONObject;
    }
}
