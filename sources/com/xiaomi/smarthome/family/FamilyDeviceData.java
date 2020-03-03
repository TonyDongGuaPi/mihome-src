package com.xiaomi.smarthome.family;

import org.json.JSONException;
import org.json.JSONObject;

public class FamilyDeviceData {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15679a = "id";
    public static final String b = "name";
    public String c;
    public String d;

    public static FamilyDeviceData a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FamilyDeviceData familyDeviceData = new FamilyDeviceData();
        familyDeviceData.c = jSONObject.optString("id", (String) null);
        familyDeviceData.d = jSONObject.optString("name", (String) null);
        if (familyDeviceData.c == null) {
            return null;
        }
        return familyDeviceData;
    }

    private FamilyDeviceData() {
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.c);
            jSONObject.put("name", this.d);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
