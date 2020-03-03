package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.Access;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class SpecPropertyDefinitionCodec {
    private SpecPropertyDefinitionCodec() {
    }

    public static PropertyDefinition a(JSONObject jSONObject) {
        String optString = jSONObject.optString("type", "");
        String optString2 = jSONObject.optString("description", "");
        DataFormat from = DataFormat.from(jSONObject.optString(IjkMediaMeta.IJKM_KEY_FORMAT, ""));
        Access a2 = SpecDefinitionCodec.a(jSONObject.optJSONArray("access"));
        if (!jSONObject.has("value-list") || !jSONObject.has("value-range")) {
            ConstraintValue a3 = jSONObject.has("value-list") ? SpecDefinitionCodec.a(from, jSONObject.optJSONArray("value-list")) : null;
            if (jSONObject.has("value-range")) {
                a3 = SpecDefinitionCodec.b(from, jSONObject.optJSONArray("value-range"));
            }
            return new PropertyDefinition(optString, optString2, a2, from, a3, jSONObject.optString("unit", ""));
        }
        throw new IllegalArgumentException("value-list & value-range both exist!");
    }

    public static JSONObject a(PropertyDefinition propertyDefinition) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", propertyDefinition.getType());
        jSONObject.put("description", propertyDefinition.getDescription());
        jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, propertyDefinition.getFormat().toString());
        jSONObject.put("access", new JSONArray(propertyDefinition.access().toList()));
        SpecDefinitionCodec.a(jSONObject, propertyDefinition.getConstraintValue());
        jSONObject.put("unit", propertyDefinition.getUnit());
        return jSONObject;
    }
}
