package com.xiaomi.smarthome.newui.card.spec.instance;

import com.xiaomi.smarthome.device.api.spec.definitions.data.Access;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataFormat;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueRange;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecDefinitionCodec {
    private SpecDefinitionCodec() {
    }

    public static Access a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i, ""));
        }
        return Access.valueOf((List<String>) arrayList);
    }

    public static ValueList a(DataFormat dataFormat, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(dataFormat, jSONArray.optJSONObject(i)));
        }
        return new ValueList((List<ValueDefinition>) arrayList);
    }

    public static ValueRange b(DataFormat dataFormat, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.opt(i));
        }
        return new ValueRange(dataFormat, arrayList);
    }

    public static ValueDefinition a(DataFormat dataFormat, JSONObject jSONObject) {
        return new ValueDefinition(dataFormat, jSONObject.opt("value"), jSONObject.optString("description", ""));
    }

    public static void a(JSONObject jSONObject, ConstraintValue constraintValue) throws JSONException {
        if (constraintValue == null) {
            return;
        }
        if (constraintValue instanceof ValueList) {
            jSONObject.put("value-list", a((ValueList) constraintValue));
        } else if (constraintValue instanceof ValueRange) {
            jSONObject.put("value-range", new JSONArray(((ValueRange) constraintValue).toList()));
        }
    }

    public static JSONArray a(ValueList valueList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (ValueDefinition next : valueList.values()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("value", next.value().getObjectValue());
            jSONObject.put("description", next.description());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
