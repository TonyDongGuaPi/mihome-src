package com.xiaomi.smarthome.miio.consumables;

import com.mi.global.shop.model.Tags;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceConsumble {

    /* renamed from: a  reason: collision with root package name */
    public int f13554a = 0;
    public List<Consumable> b = new ArrayList();
    public String c;
    public boolean d;
    public boolean e;
    public boolean f;

    public static DeviceConsumble a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        DeviceConsumble deviceConsumble = new DeviceConsumble();
        if (jSONObject == null) {
            return null;
        }
        deviceConsumble.c = jSONObject.optString("did");
        if (jSONObject.has(Tags.MiPhoneDetails.DETAILS) && (optJSONArray = jSONObject.optJSONArray(Tags.MiPhoneDetails.DETAILS)) != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                deviceConsumble.b.add(Consumable.a(optJSONArray.optJSONObject(i)));
            }
        }
        if (jSONObject.has("skip_rpc")) {
            deviceConsumble.d = jSONObject.optBoolean("skip_rpc");
        }
        if (jSONObject.has("ble_gateway")) {
            deviceConsumble.e = jSONObject.optBoolean("ble_gateway");
        }
        deviceConsumble.f13554a = deviceConsumble.b.size();
        return deviceConsumble;
    }

    public static JSONObject a(DeviceConsumble deviceConsumble) {
        if (deviceConsumble == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (deviceConsumble.c != null) {
                jSONObject.put("did", deviceConsumble.c);
            }
            if (deviceConsumble.b != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < deviceConsumble.b.size(); i++) {
                    JSONObject a2 = Consumable.a(deviceConsumble.b.get(i));
                    if (a2 != null) {
                        jSONArray.put(a2);
                    }
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put(Tags.MiPhoneDetails.DETAILS, jSONArray);
                }
                jSONObject.put("skip_rpc", deviceConsumble.d);
                jSONObject.put("ble_gateway", deviceConsumble.e);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
