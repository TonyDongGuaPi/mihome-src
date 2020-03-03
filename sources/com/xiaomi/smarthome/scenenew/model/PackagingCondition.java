package com.xiaomi.smarthome.scenenew.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PackagingCondition {

    /* renamed from: a  reason: collision with root package name */
    public String f21984a;
    public List<JSONObject> b = new ArrayList();

    public static PackagingCondition a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        PackagingCondition packagingCondition = new PackagingCondition();
        packagingCondition.f21984a = jSONObject.optString("name");
        JSONArray optJSONArray = jSONObject.optJSONArray("fingerprints");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                packagingCondition.b.add(optJSONArray.optJSONObject(i));
            }
        }
        return packagingCondition;
    }
}
