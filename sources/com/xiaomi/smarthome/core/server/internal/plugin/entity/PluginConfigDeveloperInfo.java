package com.xiaomi.smarthome.core.server.internal.plugin.entity;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginConfigDeveloperInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f14693a;
    public long b;
    public List<DeveloperResult> c = new ArrayList();

    public static PluginConfigDeveloperInfo a(JSONObject jSONObject) throws JSONException {
        PluginConfigDeveloperInfo pluginConfigDeveloperInfo = new PluginConfigDeveloperInfo();
        pluginConfigDeveloperInfo.f14693a = jSONObject.optString("type");
        pluginConfigDeveloperInfo.b = jSONObject.optLong("last_modify");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                if (jSONObject2 != null) {
                    pluginConfigDeveloperInfo.c.add(DeveloperResult.a(jSONObject2));
                }
            }
        }
        return pluginConfigDeveloperInfo;
    }
}
