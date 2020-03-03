package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecPropertyCodec {
    private SpecPropertyCodec() {
    }

    public static Map<Integer, SpecProperty> a(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                SpecProperty specProperty = new SpecProperty(optJSONObject.optInt("iid", -1), SpecPropertyDefinitionCodec.a(optJSONObject));
                linkedHashMap.put(Integer.valueOf(specProperty.getIid()), specProperty);
            }
        }
        return linkedHashMap;
    }

    public static JSONObject a(SpecProperty specProperty) throws JSONException {
        JSONObject a2 = SpecPropertyDefinitionCodec.a(specProperty.getPropertyDefinition());
        a2.put("iid", specProperty.getIid());
        return a2;
    }
}
