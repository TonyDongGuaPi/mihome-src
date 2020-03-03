package com.xiaomi.smarthome.config.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LvMiSupportLocalInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f13957a;

    public static List<LvMiSupportLocalInfo> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() <= 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && !optJSONObject.isNull("model")) {
                LvMiSupportLocalInfo lvMiSupportLocalInfo = new LvMiSupportLocalInfo();
                lvMiSupportLocalInfo.f13957a = optJSONObject.optString("model");
                arrayList.add(lvMiSupportLocalInfo);
            }
        }
        return arrayList;
    }
}
