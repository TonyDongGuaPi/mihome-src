package com.xiaomi.smarthome.devicelistswitch.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceListSwitchData {

    /* renamed from: a  reason: collision with root package name */
    private boolean f15514a = false;
    private Map<String, ModelOperations> b = new HashMap();

    public boolean a() {
        return this.f15514a;
    }

    public Map<String, ModelOperations> b() {
        return this.b;
    }

    public void a(boolean z) {
        this.f15514a = z;
    }

    public void a(Map<String, ModelOperations> map) {
        this.b = map;
    }

    public static DeviceListSwitchData a(JSONObject jSONObject) {
        DeviceListSwitchData deviceListSwitchData = new DeviceListSwitchData();
        if (jSONObject.isNull("data")) {
            return deviceListSwitchData;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        ArrayList<ModelOperations> arrayList = new ArrayList<>();
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.addAll(ModelOperations.a(optJSONArray.optJSONObject(i)));
        }
        HashMap hashMap = new HashMap();
        for (ModelOperations modelOperations : arrayList) {
            hashMap.put(modelOperations.b(), modelOperations);
        }
        deviceListSwitchData.a((Map<String, ModelOperations>) hashMap);
        if (!jSONObject.isNull("enabled")) {
            deviceListSwitchData.a(jSONObject.optBoolean("enabled", false));
        }
        return deviceListSwitchData;
    }
}
