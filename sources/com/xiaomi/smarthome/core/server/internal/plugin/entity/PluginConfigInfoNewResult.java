package com.xiaomi.smarthome.core.server.internal.plugin.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class PluginConfigInfoNewResult {
    private static final String c = "full";
    private static final String d = "delta";

    /* renamed from: a  reason: collision with root package name */
    public PluginConfigDeviceInfo f14695a;
    public PluginConfigDeveloperInfo b;

    public boolean a() {
        if (this.f14695a == null) {
            return true;
        }
        return "full".equalsIgnoreCase(this.f14695a.f14694a);
    }

    public boolean b() {
        if (this.b == null) {
            return true;
        }
        return "full".equalsIgnoreCase(this.b.f14693a);
    }

    public static PluginConfigInfoNewResult a(JSONObject jSONObject) throws JSONException {
        PluginConfigInfoNewResult pluginConfigInfoNewResult = new PluginConfigInfoNewResult();
        JSONObject optJSONObject = jSONObject.optJSONObject("devices");
        if (optJSONObject != null) {
            pluginConfigInfoNewResult.f14695a = PluginConfigDeviceInfo.a(optJSONObject);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("developers");
        if (optJSONObject2 != null) {
            pluginConfigInfoNewResult.b = PluginConfigDeveloperInfo.a(optJSONObject2);
        }
        return pluginConfigInfoNewResult;
    }
}
