package com.xiaomi.smarthome.core.server.internal.plugin.entity;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginConfigDeviceInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f14694a;
    public long b;
    public List<DeviceResult> c = new ArrayList();

    public static PluginConfigDeviceInfo a(JSONObject jSONObject) throws JSONException {
        PluginConfigDeviceInfo pluginConfigDeviceInfo = new PluginConfigDeviceInfo();
        pluginConfigDeviceInfo.f14694a = jSONObject.optString("type");
        pluginConfigDeviceInfo.b = jSONObject.optLong("last_modify");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                if (jSONObject2 != null) {
                    pluginConfigDeviceInfo.c.add(DeviceResult.a(jSONObject2));
                }
            }
        }
        return pluginConfigDeviceInfo;
    }
}
