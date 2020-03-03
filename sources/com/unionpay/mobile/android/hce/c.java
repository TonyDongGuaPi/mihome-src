package com.unionpay.mobile.android.hce;

import android.content.ServiceConnection;
import com.unionpay.mobile.android.hce.service.a;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class c implements d {

    /* renamed from: a  reason: collision with root package name */
    private String f9562a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private a g;
    private ServiceConnection h;

    public c(JSONObject jSONObject, String str, a aVar, ServiceConnection serviceConnection) {
        this.f9562a = j.a(jSONObject, "num");
        this.b = j.a(jSONObject, "name");
        this.d = j.a(jSONObject, "type");
        String a2 = j.a(jSONObject, "type");
        this.c = e.f9564a.equals(a2) ? e.e : e.b.equals(a2) ? e.f : e.c.equals(a2) ? e.g : e.d.equals(a2) ? e.h : "";
        this.e = j.a(jSONObject, "instNum");
        this.f = str;
        this.g = aVar;
        this.h = serviceConnection;
    }

    public final String a() {
        return this.f9562a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    public final a g() {
        return this.g;
    }

    public final ServiceConnection h() {
        return this.h;
    }
}
