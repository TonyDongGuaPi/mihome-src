package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import com.xiaomi.smarthome.device.api.spec.instance.SpecEvent;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.stat.a.j;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecServiceCodec {
    private SpecServiceCodec() {
    }

    private static SpecService a(JSONObject jSONObject) {
        return new SpecService(jSONObject.optInt("iid", -1), jSONObject.optString("type", ""), jSONObject.optString("description", ""), SpecPropertyCodec.a(jSONObject.optJSONArray("properties")), SpecActionCodec.a(jSONObject.optJSONArray("actions")), SpecEventCodec.a(jSONObject.optJSONArray(j.b)));
    }

    public static Map<Integer, SpecService> a(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                SpecService a2 = a(jSONArray.optJSONObject(i));
                linkedHashMap.put(Integer.valueOf(a2.getIid()), a2);
            }
        }
        return linkedHashMap;
    }

    public static JSONObject a(SpecService specService) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("iid", specService.getIid());
        jSONObject.put("type", specService.getType());
        jSONObject.put("description", specService.getDesc());
        Map<Integer, SpecProperty> properties = specService.getProperties();
        if (properties != null && properties.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<Integer, SpecProperty> value : properties.entrySet()) {
                jSONArray.put(SpecPropertyCodec.a((SpecProperty) value.getValue()));
            }
            jSONObject.put("properties", jSONArray);
        }
        Map<Integer, SpecAction> actions = specService.getActions();
        if (actions != null && actions.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            for (Map.Entry<Integer, SpecAction> value2 : actions.entrySet()) {
                jSONArray2.put(SpecActionCodec.a((SpecAction) value2.getValue()));
            }
            jSONObject.put("actions", jSONArray2);
        }
        Map<Integer, SpecEvent> events = specService.getEvents();
        if (events != null && events.size() > 0) {
            JSONArray jSONArray3 = new JSONArray();
            for (Map.Entry<Integer, SpecEvent> value3 : events.entrySet()) {
                jSONArray3.put(SpecEventCodec.a((SpecEvent) value3.getValue()));
            }
            jSONObject.put(j.b, jSONArray3);
        }
        return jSONObject;
    }
}
