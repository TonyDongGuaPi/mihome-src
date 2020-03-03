package com.xiaomi.smarthome.newui.card.spec.instance;

import android.util.Log;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SpecDeviceCodec {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20611a = "SpecDeviceCodec";

    private SpecDeviceCodec() {
    }

    public static SpecDevice a(JSONObject jSONObject) {
        return new SpecDevice(jSONObject.optString("type", ""), jSONObject.optString("description", ""), SpecServiceCodec.a(jSONObject.optJSONArray("services")));
    }

    public static JSONObject a(SpecDevice specDevice) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", specDevice.getType());
            jSONObject.put("description", specDevice.getDescription());
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<Integer, SpecService> value : specDevice.getServices().entrySet()) {
                jSONArray.put(SpecServiceCodec.a((SpecService) value.getValue()));
            }
            jSONObject.put("services", jSONArray);
        } catch (JSONException e) {
            Log.e(f20611a, "toJSONObject", e);
        }
        return jSONObject;
    }
}
