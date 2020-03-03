package com.mi.mistatistic.sdk.data;

import com.miui.tsmclient.net.TSMAuthContants;
import org.json.JSONObject;

public class CustomDataEvent extends AbstractEvent {

    /* renamed from: a  reason: collision with root package name */
    protected String f7357a;
    protected long b;
    private String c;
    private String d;

    public boolean f() {
        return false;
    }

    public String c() {
        return this.f7357a;
    }

    public void a(String str) {
        this.f7357a = str;
    }

    public long d() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public String e() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.f7357a);
            jSONObject.put("dataJson", this.c);
            jSONObject.put("category", this.d);
            jSONObject.put("timestamp", this.b);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
