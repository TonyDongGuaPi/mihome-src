package com.xiaomi.smarthome.newui.topwidget;

import org.json.JSONException;
import org.json.JSONObject;

public class EnvDevice {

    /* renamed from: a  reason: collision with root package name */
    public String f20709a;
    public String b;

    public EnvDevice(String str, String str2) {
        this.f20709a = str == null ? "" : str;
        this.b = str2 == null ? "" : str2;
    }

    public EnvDevice() {
    }

    public static EnvDevice a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        EnvDevice envDevice = new EnvDevice();
        envDevice.f20709a = jSONObject.optString("prop_name");
        envDevice.b = jSONObject.optString("did");
        return envDevice;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("prop_name", this.f20709a);
            jSONObject.put("did", this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
