package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.instance.SpecEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecEventCodec {
    private SpecEventCodec() {
    }

    private static SpecEvent a(JSONObject jSONObject) {
        return new SpecEvent(jSONObject.optInt("iid", -1), EventDefinitionCodec.a(jSONObject));
    }

    public static Map<Integer, SpecEvent> a(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                SpecEvent a2 = a(jSONArray.optJSONObject(i));
                linkedHashMap.put(Integer.valueOf(a2.getIid()), a2);
            }
        }
        return linkedHashMap;
    }

    public static JSONObject a(SpecEvent specEvent) throws JSONException {
        JSONObject a2 = EventDefinitionCodec.a(specEvent.getEventDefinition());
        if (a2 == null) {
            a2 = new JSONObject();
        }
        a2.put("iid", specEvent.getIid());
        return a2;
    }
}
