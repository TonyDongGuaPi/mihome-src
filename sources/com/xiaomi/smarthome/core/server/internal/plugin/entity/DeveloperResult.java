package com.xiaomi.smarthome.core.server.internal.plugin.entity;

import org.json.JSONObject;

public class DeveloperResult {

    /* renamed from: a  reason: collision with root package name */
    public long f14690a;
    public String b;

    public static DeveloperResult a(JSONObject jSONObject) {
        DeveloperResult developerResult = new DeveloperResult();
        developerResult.f14690a = jSONObject.optLong("developer_id");
        developerResult.b = jSONObject.optString("key");
        return developerResult;
    }
}
