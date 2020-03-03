package com.xiaomi.smarthome.mibrain.model.aitips;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Tips {

    /* renamed from: a  reason: collision with root package name */
    private List<ContentDetail> f10662a;
    private List<String> b;
    private String c;

    public static Tips a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONArray optJSONArray2;
        ContentDetail a2;
        if (jSONObject == null) {
            return null;
        }
        Tips tips = new Tips();
        if (!jSONObject.isNull("content_details") && (optJSONArray2 = jSONObject.optJSONArray("content_details")) != null && optJSONArray2.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray2.length(); i++) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                if (!(optJSONObject == null || (a2 = ContentDetail.a(optJSONObject)) == null)) {
                    arrayList.add(a2);
                }
            }
            tips.a((List<ContentDetail>) arrayList);
        }
        if (!jSONObject.isNull("contents") && (optJSONArray = jSONObject.optJSONArray("contents")) != null && optJSONArray.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    arrayList2.add(optString);
                }
            }
            tips.b(arrayList2);
        }
        tips.a(jSONObject.optString("title"));
        return tips;
    }

    public List<ContentDetail> a() {
        return this.f10662a;
    }

    public void a(List<ContentDetail> list) {
        this.f10662a = list;
    }

    public List<String> b() {
        return this.b;
    }

    public void b(List<String> list) {
        this.b = list;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }
}
