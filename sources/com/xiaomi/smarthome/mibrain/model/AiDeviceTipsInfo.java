package com.xiaomi.smarthome.mibrain.model;

import com.google.android.gms.actions.SearchIntents;
import com.xiaomi.smarthome.mibrain.model.aitips.AiDevice;
import com.xiaomi.smarthome.mibrain.model.aitips.AiTipsDevice;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AiDeviceTipsInfo {

    /* renamed from: a  reason: collision with root package name */
    private List<AiDevice> f10652a;
    private AiTipsDevice b;
    private String c;

    public static AiDeviceTipsInfo a(JSONObject jSONObject) {
        AiDevice a2;
        AiDeviceTipsInfo aiDeviceTipsInfo = new AiDeviceTipsInfo();
        if (jSONObject == null) {
            return aiDeviceTipsInfo;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("ai_devices");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || (a2 = AiDevice.a(optJSONObject)) == null)) {
                    arrayList.add(a2);
                }
            }
            aiDeviceTipsInfo.a((List<AiDevice>) arrayList);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("device");
        if (optJSONObject2 != null) {
            aiDeviceTipsInfo.a(AiTipsDevice.a(optJSONObject2));
        }
        aiDeviceTipsInfo.a(jSONObject.optString(SearchIntents.EXTRA_QUERY));
        return aiDeviceTipsInfo;
    }

    public List<AiDevice> a() {
        return this.f10652a;
    }

    public void a(List<AiDevice> list) {
        this.f10652a = list;
    }

    public AiTipsDevice b() {
        return this.b;
    }

    public void a(AiTipsDevice aiTipsDevice) {
        this.b = aiTipsDevice;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }
}
