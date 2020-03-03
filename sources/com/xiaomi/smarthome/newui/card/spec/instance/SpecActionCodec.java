package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecActionCodec {
    private SpecActionCodec() {
    }

    private static SpecAction a(JSONObject jSONObject) {
        return new SpecAction(jSONObject.optInt("iid", -1), ActionDefinitionCodec.a(jSONObject));
    }

    public static Map<Integer, SpecAction> a(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                SpecAction a2 = a(jSONArray.optJSONObject(i));
                linkedHashMap.put(Integer.valueOf(a2.getIid()), a2);
            }
        }
        return linkedHashMap;
    }

    public static JSONObject a(SpecAction specAction) throws JSONException {
        JSONObject a2 = ActionDefinitionCodec.a(specAction.getActionDefinition());
        if (a2 == null) {
            a2 = new JSONObject();
        }
        a2.put("iid", specAction.getIid());
        return a2;
    }
}
