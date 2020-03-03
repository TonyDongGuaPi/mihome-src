package com.xiaomi.smarthome.framework.api.model;

import org.json.JSONObject;

public class UploadUserData {

    /* renamed from: a  reason: collision with root package name */
    public String f16460a;
    public String b;
    public String c;

    public void a(JSONObject jSONObject) {
        try {
            this.f16460a = jSONObject.getString("url");
            this.b = jSONObject.getString("obj_name");
            this.c = jSONObject.optString("method");
        } catch (Exception unused) {
        }
    }
}
