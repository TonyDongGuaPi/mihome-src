package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.smarthome.device.api.spec.definitions.EventDefinition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class EventDefinitionCodec {
    private EventDefinitionCodec() {
    }

    public static EventDefinition a(JSONObject jSONObject) {
        return new EventDefinition(jSONObject.optString("type", ""), jSONObject.optString("description", ""), a(jSONObject.optJSONArray(IEntry.b)));
    }

    public static JSONObject a(EventDefinition eventDefinition) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", eventDefinition.getType());
        jSONObject.put("description", eventDefinition.getDescription());
        JSONArray a2 = a(eventDefinition.getArguments());
        if (a2 != null) {
            jSONObject.put("in", a2);
        }
        return jSONObject;
    }

    private static JSONArray a(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (long put : jArr) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static long[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        long[] jArr = new long[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            jArr[i] = jSONArray.optLong(i);
        }
        return jArr;
    }
}
