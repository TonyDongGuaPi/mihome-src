package com.xiaomi.smarthome.mibrain.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiBrainConfigInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f10653a;
    public String b;
    public List<String> c = new ArrayList();

    public static List<MiBrainConfigInfo> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                MiBrainConfigInfo miBrainConfigInfo = new MiBrainConfigInfo();
                miBrainConfigInfo.f10653a = jSONObject.optString("name", "");
                miBrainConfigInfo.b = jSONObject.optString("model", "");
                JSONArray optJSONArray = jSONObject.optJSONArray("desc");
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        miBrainConfigInfo.c.add(optJSONArray.get(i2).toString());
                    }
                }
                arrayList.add(miBrainConfigInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
