package com.xiaomi.smarthome.core.server.internal.whitelist;

import org.json.JSONException;
import org.json.JSONObject;

public class WhiteListInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f14733a;
    private String b;

    public WhiteListInfo(String str, String str2) {
        this.f14733a = str;
        this.b = str2;
    }

    WhiteListInfo() {
    }

    static WhiteListInfo a(String str, String str2) {
        WhiteListInfo whiteListInfo = new WhiteListInfo();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            whiteListInfo.f14733a = jSONObject.optString("package_name");
            whiteListInfo.b = jSONObject.optString("cert");
            return whiteListInfo;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String a() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("package_name", this.f14733a);
            jSONObject.put("cert", this.b);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized String b() {
        return this.f14733a;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(String str) {
        this.f14733a = str;
    }

    public synchronized String c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(String str) {
        this.b = str;
    }
}
