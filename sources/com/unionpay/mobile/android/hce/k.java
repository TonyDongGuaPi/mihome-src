package com.unionpay.mobile.android.hce;

import com.unionpay.mobile.android.utils.j;
import miuipub.reflect.Field;
import org.json.JSONObject;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private String f9570a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private JSONObject g;

    public k(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.g = jSONObject;
            this.f9570a = j.a(jSONObject, "package");
            this.b = j.a(jSONObject, "issuer");
            this.c = j.a(jSONObject, "syn_key");
            this.d = j.a(jSONObject, "pub_key");
            this.e = j.a(jSONObject, "status");
            this.f = j.a(jSONObject, "priority");
        }
    }

    public final boolean a() {
        return this.e.equals(Field.h);
    }

    public final String b() {
        return this.f9570a;
    }

    public final String c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.d;
    }

    public final JSONObject f() {
        return this.g;
    }
}
