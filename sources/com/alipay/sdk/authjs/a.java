package com.alipay.sdk.authjs;

import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1095a = "call";
    public static final String b = "callback";
    public static final String c = "bundleName";
    public static final String d = "clientId";
    public static final String e = "param";
    public static final String f = "func";
    public static final String g = "msgType";
    private String h;
    private String i;
    private String j;
    private String k;
    private JSONObject l;
    private boolean m = false;

    /* renamed from: com.alipay.sdk.authjs.a$a  reason: collision with other inner class name */
    public enum C0028a {
        NONE_ERROR,
        FUNCTION_NOT_FOUND,
        INVALID_PARAMETER,
        RUNTIME_ERROR,
        NONE_PERMISS
    }

    public static final String a(C0028a aVar) {
        switch (b.f1097a[aVar.ordinal()]) {
            case 1:
                return "function not found";
            case 2:
                return "invalid parameter";
            case 3:
                return "runtime error";
            default:
                return "none";
        }
    }

    public boolean a() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public a(String str) {
        d(str);
    }

    public String b() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public String c() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String d() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String e() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public JSONObject f() {
        return this.l;
    }

    public void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public String g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("clientId", this.h);
        jSONObject.put("func", this.j);
        jSONObject.put("param", this.l);
        jSONObject.put(g, this.k);
        return jSONObject.toString();
    }
}