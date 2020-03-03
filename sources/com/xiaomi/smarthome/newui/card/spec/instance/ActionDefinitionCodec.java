package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.definitions.ActionDefinition;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ActionDefinitionCodec {
    private ActionDefinitionCodec() {
    }

    public static ActionDefinition a(JSONObject jSONObject) {
        return new ActionDefinition(jSONObject.optString("type", ""), jSONObject.optString("description", ""), jSONObject.optJSONArray("in"), jSONObject.optJSONArray("out"));
    }

    public static JSONObject a(ActionDefinition actionDefinition) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", actionDefinition.getType());
        jSONObject.put("description", actionDefinition.getDescription());
        jSONObject.put("in", new JSONArray(actionDefinition.getIn()));
        jSONObject.put("out", new JSONArray(actionDefinition.getOut()));
        return jSONObject;
    }

    public static List<Object> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.opt(i));
            }
        }
        return arrayList;
    }
}
