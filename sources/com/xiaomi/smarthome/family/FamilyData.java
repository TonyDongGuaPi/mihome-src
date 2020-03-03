package com.xiaomi.smarthome.family;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyData {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15678a = "info";
    public static final String b = "memberlist";
    public static final String c = "devicelist";
    public FamilyItemData d;
    public List<FamilyDeviceData> e;
    public List<FamilyMemberData> f;

    private FamilyData() {
    }

    public static List<FamilyData> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                FamilyData a2 = a(jSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public static FamilyData a(JSONObject jSONObject) {
        FamilyData familyData = new FamilyData();
        JSONObject optJSONObject = jSONObject.optJSONObject("info");
        if (optJSONObject == null) {
            return null;
        }
        familyData.d = FamilyItemData.a(optJSONObject);
        if (familyData.d == null) {
            return null;
        }
        familyData.f = new ArrayList();
        familyData.e = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(b);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                FamilyMemberData a2 = FamilyMemberData.a(optJSONArray.optJSONObject(i));
                if (a2 != null) {
                    familyData.f.add(a2);
                }
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray(c);
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                FamilyDeviceData a3 = FamilyDeviceData.a(optJSONArray2.optJSONObject(i2));
                if (a3 != null) {
                    familyData.e.add(a3);
                }
            }
        }
        return familyData;
    }

    public JSONObject a() {
        if (this.d == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("info", this.d.a());
            JSONArray jSONArray = new JSONArray();
            if (this.e != null) {
                for (FamilyDeviceData a2 : this.e) {
                    JSONObject a3 = a2.a();
                    if (a3 != null) {
                        jSONArray.put(a3);
                    }
                }
            }
            JSONArray jSONArray2 = new JSONArray();
            if (this.f != null) {
                for (FamilyMemberData a4 : this.f) {
                    JSONObject a5 = a4.a();
                    if (a5 != null) {
                        jSONArray2.put(a5);
                    }
                }
            }
            jSONObject.put(b, jSONArray2);
            jSONObject.put(c, jSONArray);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
