package com.xiaomi.smarthome.auth;

import org.json.JSONObject;

public class AuthAppInfo {

    /* renamed from: a  reason: collision with root package name */
    String f13776a;
    String b;
    long c;
    String d;
    String e;
    String f;
    String g;
    String h;

    public static AuthAppInfo a(JSONObject jSONObject) {
        AuthAppInfo authAppInfo = new AuthAppInfo();
        if (jSONObject != null) {
            authAppInfo.f13776a = jSONObject.optString("title");
            authAppInfo.b = jSONObject.optString("intro");
            authAppInfo.f = jSONObject.optString("app_id");
            authAppInfo.e = jSONObject.optString("application_id");
            authAppInfo.g = jSONObject.optString("issue_time");
            authAppInfo.h = jSONObject.optString("expire_time");
            authAppInfo.d = jSONObject.optString("icon");
        }
        return authAppInfo;
    }
}
